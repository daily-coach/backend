package br.com.rodrigocardoso.models;

import javax.persistence.Column;

/**
 * Created by rodri on 02/06/2018.
 */
public class Dia extends Entidade {
    @Column(name = "nome")
    private String nome;
    @Column(name = "show_nome")
    private String showNome;

    public String getNome() {
        return nome;
    }

    public Dia setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getShowNome() {
        return showNome;
    }

    public Dia setShowNome(String showNome) {
        this.showNome = showNome;
        return this;
    }
}
