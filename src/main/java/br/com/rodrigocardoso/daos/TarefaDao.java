package br.com.rodrigocardoso.daos;

import br.com.rodrigocardoso.database.tables.Tarefas;
import br.com.rodrigocardoso.database.tables.TarefasDias;
import br.com.rodrigocardoso.database.tables.records.TarefasDiasRecord;
import br.com.rodrigocardoso.database.tables.records.TarefasRecord;
import br.com.rodrigocardoso.models.Tarefa;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import java.util.Map;

/**
 * Created by rodri on 08/05/2018.
 */
public class TarefaDao extends AbstractDao<TarefasRecord, Tarefa> {

    private Tarefas T =  Tarefas.TAREFAS;
    private TarefasDias TD = TarefasDias.TAREFAS_DIAS;

    public TarefaDao(DSLContext dsl) {
        super(dsl, Tarefas.TAREFAS, Tarefa.class);
    }

}
