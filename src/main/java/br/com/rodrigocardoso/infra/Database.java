package br.com.rodrigocardoso.infra;

import org.jooq.DSLContext;

import java.sql.Connection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by rodri on 01/05/2018.
 */
public class Database {

    private Database() {
        throw new IllegalArgumentException("Cant instanciate Database");
    }

    private static final Consumer<Throwable> ACTION_ERROR = throwable -> {
      throwable.printStackTrace();
      throw new RuntimeException(throwable);
    };

    public static void open(Consumer<DSLContext> block, Consumer<? super Throwable> errorBlock) {
        try(Connection con = ConnectionFactory.getConnectionFromPool()) {
            DSLContext dsl = ConnectionFactory.getDSL(con);
            block.accept(dsl);
        } catch (Exception e) {
            errorBlock.accept(e);
        }
    }

    public static void open(Consumer<DSLContext> block) {
        open(block, ACTION_ERROR);
    }

    public static void transaction(Consumer<DSLContext> block, Consumer<Throwable> rollbackBlock, Consumer<Throwable> errorBlock) {
        try(Connection con = ConnectionFactory.getConnectionFromPool()) {
            con.setAutoCommit(false);
            DSLContext dsl = ConnectionFactory.getDSL(con);
            try {
                block.accept(dsl);
                con.commit();
            } catch (Throwable inner) {
                con.rollback();
                rollbackBlock.accept(inner);
            }
        } catch (Exception e) {
            errorBlock.accept(e);
        }
    }

    public static void transaction(Consumer<DSLContext> block, Consumer<Throwable> errorBlock) {
        transaction(block, errorBlock, errorBlock);
    }

    public static void transaction(Consumer<DSLContext> block) {
        transaction(block, ACTION_ERROR, ACTION_ERROR);
    }

}
