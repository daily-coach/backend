package br.com.rodrigocardoso.models;

import javax.persistence.Column;

/**
 * Created by rodri on 08/05/2018.
 */
public class Tarefa extends Entidade{

    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "dia")
    private Integer dia;

    public String getTitulo() {
        return titulo;
    }

    public Tarefa setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Tarefa setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Integer getDia() {
        return dia;
    }

    public Tarefa setDia(Integer dia) {
        this.dia = dia;
        return this;
    }
}
