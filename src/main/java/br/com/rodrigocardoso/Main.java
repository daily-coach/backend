package br.com.rodrigocardoso;

import br.com.rodrigocardoso.endpoints.Initializer;
import br.com.rodrigocardoso.infra.ConnectionFactory;

import static spark.Spark.*;

/**
 * Created by rodri on 26/04/2018.
 */
public class Main {
    public static void main(String[] args) {
        ConnectionFactory.load();
        options("/*", (req, res) -> {
            res.header("Access-Control-Allow-Headers", "Content-Type");
            return "";
        });
        before((req, res) -> {
            res.header("Access-Control-Allow-Credentials", "true");
            res.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
            res.header("Access-Control-Allow-Origin", "*");
        });
        Initializer.init();

    }
}
