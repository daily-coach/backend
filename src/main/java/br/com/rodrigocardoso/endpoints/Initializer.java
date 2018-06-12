package br.com.rodrigocardoso.endpoints;


import br.com.rodrigocardoso.daos.UsuarioDao;
import br.com.rodrigocardoso.infra.Database;
import br.com.rodrigocardoso.utils.JsonUtils;
import br.com.rodrigocardoso.utils.JwtUtils;
import br.com.rodrigocardoso.utils.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static spark.Spark.*;

/**
 * Created by rodri on 26/04/2018.
 */
public class Initializer {

    public static void init() {
        configureHeaders();
        configureAuth();
        new TarefaEndpoint().publish();
        new UsuarioEndpont().publish();
        new DiaEndpoint().publish();
    }

    private static void configureHeaders() {
        options("/*", (req, res) -> {
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
            return "";
        });
        before((req, res) -> {
            res.header("Access-Control-Allow-Credentials", "true");
            res.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
            res.header("Access-Control-Allow-Origin", "*");
        });
    }

    private static void configureAuth() {
        before("/safe/*", (req, res) -> {

            if (req.requestMethod().equals("OPTIONS")) {
                return;
            }

            Response<Object> response = new Response<>();
            AtomicBoolean noAuth = new AtomicBoolean();
            try {
                String token = req.headers("Authorization");
                String subject = JwtUtils.verify(token);
                Database.open(dsl -> {
                    if (!new UsuarioDao(dsl).hasActiveUser(subject)) {
                        noAuth.set(true);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                noAuth.set(true);
            }
            if (noAuth.get()) {
                response.set(401, "Não autorizado", null);
                halt(response.status, JsonUtils.toJson(response));
            }
        });
    }

}
