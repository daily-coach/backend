package br.com.rodrigocardoso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by rodri on 08/05/2018.
 */
@Entity
@Table(name = "tarefas")
public class Tarefa extends Entidade{

    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "tarefas")
    private Set<TarefaDia> tarefaDias;
    @Column(name = "usuarios_id")
    private Integer usuariosId;

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

    public Set<TarefaDia> getTarefaDias() {
        return tarefaDias;
    }

    public Tarefa setTarefaDias(Set<TarefaDia> tarefaDias) {
        this.tarefaDias = tarefaDias;
        return this;
    }

    public Integer getUsuariosId() {
        return usuariosId;
    }

    public Tarefa setUsuariosId(Integer usuariosId) {
        this.usuariosId = usuariosId;
        return this;
    }
}
