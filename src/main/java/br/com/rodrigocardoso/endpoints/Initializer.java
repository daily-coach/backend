package br.com.rodrigocardoso.endpoints;

/**
 * Created by rodri on 26/04/2018.
 */
public class Initializer {

    public static void init() {
        new TarefaEndpoint().publish();
        new UsuarioEndpont().publish();
    }

}
