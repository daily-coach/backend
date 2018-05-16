package br.com.rodrigocardoso.endpoints;

import br.com.rodrigocardoso.daos.TarefaDao;
import br.com.rodrigocardoso.models.Tarefa;

/**
 * Created by rodri on 08/05/2018.
 */
public class TarefaEndpoint extends AbstractEndpoint<Tarefa, TarefaDao> {

    public TarefaEndpoint() {
        super(Tarefa.class, "/tarefas", TarefaDao.class);
    }
}
