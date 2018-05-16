package br.com.rodrigocardoso.daos;

import br.com.rodrigocardoso.database.tables.Tarefas;
import br.com.rodrigocardoso.database.tables.records.TarefasRecord;
import br.com.rodrigocardoso.models.Tarefa;
import org.jooq.DSLContext;

/**
 * Created by rodri on 08/05/2018.
 */
public class TarefaDao extends AbstractDao<TarefasRecord, Tarefa> {

    public TarefaDao(DSLContext dsl) {
        super(dsl, Tarefas.TAREFAS, Tarefa.class);
    }

}
