package br.com.rodrigocardoso.daos;

import br.com.rodrigocardoso.database.tables.Dias;
import br.com.rodrigocardoso.database.tables.records.DiasRecord;
import br.com.rodrigocardoso.models.Dia;
import org.jooq.DSLContext;

/**
 * Created by rodri on 02/06/2018.
 */
public class DiaDao extends AbstractDao<DiasRecord, Dia> {
    public DiaDao(DSLContext dsl) {
        super(dsl, Dias.DIAS, Dia.class);
    }
}
