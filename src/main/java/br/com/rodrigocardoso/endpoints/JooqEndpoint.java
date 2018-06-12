package br.com.rodrigocardoso.endpoints;

import br.com.rodrigocardoso.daos.AbstractDao;
import br.com.rodrigocardoso.infra.Database;
import br.com.rodrigocardoso.models.Entidade;
import org.jooq.DSLContext;

import java.util.List;

/**
 * Created by rodri on 05/06/2018.
 */
public abstract class JooqEndpoint <E extends Entidade, D extends AbstractDao> extends AbstractEndpoint<E, D>{

    protected final String ACTIVE_PARAM = "active = true";

    JooqEndpoint(Class<E> typeParameterClass, String uri, Class<D> dao) {
        super(typeParameterClass, uri, dao);
        this.initPersistenceConsumers();
    }

    protected void initPersistenceConsumers() {
        this.savePersistence = (obj, response) -> {
            Database.transaction(dsl -> {
                try {
                    Integer id = this.dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).save(obj);
                    obj.setId(id);
                    response.set(200, "Post", obj);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.set(500, e.getMessage(), null);
                }
            });
        };

        this.updatePersistence = (obj, response) -> {
            Database.transaction(dsl -> {
                try {
                    this.dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).update(obj);
                    response.set(200, "Put", obj);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.set(500, e.getMessage(), null);
                }
            });
        };

        this.getByIdPersistence = (id, response) -> {
            Database.open(dsl -> {
                try {
                    E obj = (E) dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).get(id);
                    response.set(200, "Get", obj);
                } catch (Exception e) {
                    response.set(500, e.getMessage(), null);
                }
            });
        };

        this.getAllPersistence = (userId, response) -> {
            Database.open(dsl -> {
                try {
                    List ret;
                    if (userId == null) {
                        ret = dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).getAll();
                    } else {
                        String param = "usuarios_id = " + userId;
                        ret = dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).getAll(new String[]{param});
                    }
                    response.set(200, "Get", ret);
                } catch (Exception e) {
                    response.set(500, e.getMessage(), null);
                }
            });
        };

        this.deletePersistence = (id, response) -> {
            Database.transaction(dsl -> {
                try {
                    Boolean obj = dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).delete(id);
                    response.set(200, "Delete", obj);
                } catch (Exception e) {
                    response.set(500, e.getMessage(), null);
                }
            });
        };
    }

}
