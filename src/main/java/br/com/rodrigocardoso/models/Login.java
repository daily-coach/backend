package br.com.rodrigocardoso.models;

/**
 * Created by rodri on 26/05/2018.
 */
public class Login {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public Login setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Login setSenha(String senha) {
        this.senha = senha;
        return this;
    }
}
