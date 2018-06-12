package br.com.rodrigocardoso.endpoints;

import br.com.rodrigocardoso.daos.DiaDao;
import br.com.rodrigocardoso.models.Dia;

/**
 * Created by rodri on 02/06/2018.
 */
public class DiaEndpoint extends JooqEndpoint<Dia, DiaDao> {
    public DiaEndpoint() {
        super(Dia.class, "/safe/dias", DiaDao.class);
    }
}
