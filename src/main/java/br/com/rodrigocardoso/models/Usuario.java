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
}
