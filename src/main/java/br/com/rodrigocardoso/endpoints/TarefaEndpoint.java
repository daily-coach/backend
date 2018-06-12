package br.com.rodrigocardoso.endpoints;

import br.com.rodrigocardoso.daos.TarefaDao;
import br.com.rodrigocardoso.daos.TarefaDiaDao;
import br.com.rodrigocardoso.daos.UsuarioDao;
import br.com.rodrigocardoso.infra.Database;
import br.com.rodrigocardoso.models.Tarefa;
import br.com.rodrigocardoso.models.TarefaDia;
import br.com.rodrigocardoso.utils.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static spark.Spark.*;
import static br.com.rodrigocardoso.utils.JsonUtils.*;


/**
 * Created by rodri on 08/05/2018.
 */
public class TarefaEndpoint extends JooqEndpoint<Tarefa, TarefaDao> {

    public TarefaEndpoint() {
        super(Tarefa.class, "/safe/tarefas", TarefaDao.class);
    }

    @Override
    public void publish() {
        super.publish();
    }

    @Override
    protected void initPersistenceConsumers() {
        savePersistence();
        updatePersistence();
        getByIdPersistence();
        getAllPersistence();
        deletePersistence();
    }

    private void savePersistence() {
        savePersistence = (obj, response) -> {
            Database.transaction(dsl -> {
                try {
                    TarefaDao tarefaDao = new TarefaDao(dsl);
                    TarefaDiaDao tarefaDiaDao = new TarefaDiaDao(dsl);

                    Integer id = tarefaDao.save(obj);

                    if (id < 1) {
                        throw new Exception("Não foi possível salvar a tarefa");
                    }

                    obj.setId(id);

                    obj.setTarefaDias(
                            obj.getTarefaDias().stream().map(tarefaDia -> {
                                tarefaDia.setTarefasId(id);
                                Integer tarefaDiaId = tarefaDiaDao.save(tarefaDia);
                                tarefaDia.setId(tarefaDiaId);
                                return tarefaDia;
                            }).collect(Collectors.toSet())
                    );
                    response.set(200, "Salvo com sucesso", obj);

                } catch (Exception e) {
                    response.set(500, e.getMessage(), null);
                }
            });
        };
    }

    private void updatePersistence() {
        updatePersistence = (obj, response) -> {
            Database.transaction(dsl -> {
                try {
                    TarefaDao tarefaDao = new TarefaDao(dsl);
                    TarefaDiaDao tarefaDiaDao = new TarefaDiaDao(dsl);

                    Boolean updated = tarefaDao.update(obj);

                    if (!updated) {
                        throw new Exception("Não foi possível salvar a tarefa");
                    }

                    obj.getTarefaDias().forEach(tarefaDiaDao::update);
                    response.set(200, "Salvo com sucesso", obj);
                } catch (Exception e) {
                    response.set(500, e.getMessage(), null);
                }
            });
        };
    }

    private void getByIdPersistence() {
        getByIdPersistence = (id, response) -> {
            Database.open(dsl -> {
                try {
                    TarefaDao tarefaDao = new TarefaDao(dsl);
                    TarefaDiaDao tarefaDiaDao = new TarefaDiaDao(dsl);

                    Tarefa tarefa = tarefaDao.get(id);

                    String[] params = new String[]{
                            "tarefas_id = " + id,
                            ACTIVE_PARAM
                    };

                    Set<TarefaDia> tarefaDias = (Set<TarefaDia>) tarefaDiaDao.getAll(params).stream().collect(Collectors.toSet());
                    tarefa.setTarefaDias(tarefaDias);

                    response.set(200, "Resultado", tarefa);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.set(500, e.getMessage(), null);
                }
            });
        };
    }

    private void getAllPersistence() {
        getAllPersistence = (id, response) -> {
            Database.open(dsl -> {
                try {
                    TarefaDao tarefaDao = new TarefaDao(dsl);
                    TarefaDiaDao tarefaDiaDao = new TarefaDiaDao(dsl);

                    List<Tarefa> tarefas = (List<Tarefa>) tarefaDao.getAll(new String[]{"usuarios_id = " + id, ACTIVE_PARAM});

                    tarefas = tarefas.stream().map(tarefa -> {
                        String[] params = new String[]{
                                "tarefas_id = " + tarefa.getId(),
                                "active = true"
                        };

                        Set<TarefaDia> tarefaDias = (Set<TarefaDia>) tarefaDiaDao.getAll(params).stream().collect(Collectors.toSet());
                        return tarefa.setTarefaDias(tarefaDias);
                    }).collect(Collectors.toList());

                    response.set(200, "Resultado", tarefas);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.set(500, e.getMessage(), null);
                }

            });
        };
    }

    private void deletePersistence() {
        deletePersistence = (id, response) -> {
            Database.transaction(dsl -> {
                try {
                    Boolean deleted = new TarefaDao(dsl).delete(id);
                    if (!deleted) {
                        throw new Exception("Não foi possível deletar a tarefa");
                    }
                    response.set(200, "Operação realizada com sucesso", deleted);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.set(500, e.getMessage(), null);
                }
            });
        };
    }

}
