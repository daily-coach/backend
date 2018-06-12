package br.com.rodrigocardoso.models;

import javax.persistence.*;

/**
 * Created by rodri on 05/06/2018.
 */
@Entity
@Table(name = "tarefas_dias")
public class TarefaDia extends Entidade {
    @Column(name = "tarefas_id")
    private Integer tarefasId;
    @Column(name = "dias_id")
    private Integer diasId;

    public Integer getTarefasId() {
        return tarefasId;
    }

    public TarefaDia setTarefasId(Integer tarefasId) {
        this.tarefasId = tarefasId;
        return this;
    }

    public Integer getDiasId() {
        return diasId;
    }

    public TarefaDia setDiasId(Integer diasId) {
        this.diasId = diasId;
        return this;
    }
}
