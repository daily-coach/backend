package br.com.rodrigocardoso.infra;

import br.com.rodrigocardoso.utils.JsonUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by rodri on 29/04/2018.
 */
public class ConnectionFactory {

    private static final URI URI;
    private static final String URL;
    private static final String DATABASE;
    private static final String HOST;
    private static final String USER_NAME;
    private static final String PASSWORD;
    private static final String DRIVER;

    private static final HikariDataSource DATA_SOURCE;
    private static final AtomicBoolean LOADED = new AtomicBoolean();

    static {
        try {
            boolean herokuCon = Boolean.parseBoolean(System.getenv("HEROKU_CON"));
            DRIVER = System.getenv("DRIVER");
            Class.forName(DRIVER);
            if (!herokuCon) {
                HOST = System.getenv("HOST");
                DATABASE = "/" + System.getenv("DATABASE");
                USER_NAME = System.getenv("USER_NAME");
                PASSWORD = System.getenv("PASSWORD");
                URL = getUrl(HOST, 5432, DATABASE, "postgresql");
                URI = null;
                System.out.print(JsonUtils.toJson(PASSWORD));
            } else {
                URI = new URI(System.getenv("DATABASE_URL"));
                USER_NAME = URI.getUserInfo().split(":")[0];

                if (URI.getUserInfo().split(":").length > 1)
                    PASSWORD = URI.getUserInfo().split(":")[1];
                else
                    PASSWORD = "";

                DATABASE = URI.getPath();
                HOST = URI.getHost();
                URL = getUrl(HOST, URI.getPort(), DATABASE, "postgresql");
            }

            DATA_SOURCE = new HikariDataSource();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() {
        DATA_SOURCE.setUsername(USER_NAME);
        DATA_SOURCE.setPassword(PASSWORD);
        DATA_SOURCE.setJdbcUrl(URL);
        DATA_SOURCE.setConnectionTimeout(30000);
        DATA_SOURCE.setIdleTimeout(60_000);
        DATA_SOURCE.setMaxLifetime(600_000);
        DATA_SOURCE.setLeakDetectionThreshold(15000);
        DATA_SOURCE.setMaximumPoolSize(3);
        DATA_SOURCE.setPoolName("ConnectionPool");
        LOADED.set(true);
    }

    private static String getUrl(String host, Integer port, String database, String databaseType) {
        return String.format("jdbc:%s://%s:%d%s", databaseType, host, port, database);
    }

    private static void ensureLoad() throws IllegalArgumentException {
        if (!LOADED.get()) {
            throw new IllegalArgumentException("Call ConnectionFactory.load() to load it");
        }
    }

    public static Connection getConnectionFromPool() {
        ensureLoad();
        try {
            return DATA_SOURCE.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        ensureLoad();
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DSLContext getDSL(Connection con) {
        return DSL.using(con, SQLDialect.POSTGRES_9_5);
    }
}
