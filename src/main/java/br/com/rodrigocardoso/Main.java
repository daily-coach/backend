package br.com.rodrigocardoso;

import br.com.rodrigocardoso.endpoints.Initializer;
import br.com.rodrigocardoso.infra.ConnectionFactory;

/**
 * Created by rodri on 26/04/2018.
 */
public class Main {
    public static void main(String[] args) {
        ConnectionFactory.load();
        Initializer.init();

    }
}
