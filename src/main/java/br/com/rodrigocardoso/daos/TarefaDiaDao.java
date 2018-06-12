package br.com.rodrigocardoso.daos;

import br.com.rodrigocardoso.database.tables.TarefasDias;
import br.com.rodrigocardoso.database.tables.records.TarefasDiasRecord;
import br.com.rodrigocardoso.models.TarefaDia;
import org.jooq.DSLContext;

/**
 * Created by rodri on 05/06/2018.
 */
public class TarefaDiaDao extends AbstractDao<TarefasDiasRecord, TarefaDia> {
    public TarefaDiaDao(DSLContext dsl) {
        super(dsl, TarefasDias.TAREFAS_DIAS, TarefaDia.class);
    }
}
