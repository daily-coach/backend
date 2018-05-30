package br.com.rodrigocardoso.models;

import javax.persistence.Column;

/**
 * Created by rodri on 01/05/2018.
 */
public class Usuario extends Entidade{
    @Column(name = "nome")
    private String nome;
    @Column(name = "celular")
    private String celular;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "salt")
    private String salt;

    private String token;

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public Usuario setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Usuario setToken(String token) {
        this.token = token;
        return this;
    }
}
