package br.com.rodrigocardoso.endpoints;

import br.com.rodrigocardoso.daos.AbstractDao;
import br.com.rodrigocardoso.infra.Database;
import br.com.rodrigocardoso.models.Entidade;
import br.com.rodrigocardoso.utils.Response;
import org.jooq.DSLContext;

import java.util.ArrayList;
import java.util.List;

import static br.com.rodrigocardoso.utils.JsonUtils.*;
import static spark.Spark.*;

/**
 * Created by rodri on 26/04/2018.
 */
public abstract class AbstractEndpoint <E extends Entidade, D extends AbstractDao> implements IEndpoint {

    protected String uri;
    protected String type;
    protected Class<D> dao;

    private Class<E> typeParameterClass;

    AbstractEndpoint(Class<E> typeParameterClass, String uri, Class<D> dao) {
        this.uri = uri;
        this.type = "application/json";
        this.typeParameterClass = typeParameterClass;
        this.dao = dao;
    }

    @Override()
    public void publish() {
        post(uri, type, (req, res) -> {
            final Response<Object> response = new Response<>();
            final String body = req.body();
            E obj = fromJson(body, typeParameterClass);

            Database.transaction(dsl -> {
                try {
                    this.dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).save(obj);
                    response.set(200, "Post", obj);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.set(500, "Erro no sistema", e);
                }
            });

            res.status(response.status);
            return response;
        }, json());

        put(uri, type, (req, res) -> {
            final Response<Object> response = new Response<>();
            final String body = req.body();
            E obj = fromJson(body, typeParameterClass);

            Database.transaction(dsl -> {
                try {
                    this.dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).update(obj);
                    response.set(200, "Put", obj);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.set(500, "Erro no sistema", e);
                }
            });

            res.status(response.status);
            return response;
        }, json());

        get(uri + "/:id", type, (req, res) -> {
            final Response<Object> response = new Response<Object>();
            final Integer id = Integer.parseInt(req.params("id"));

            Database.open(dsl -> {
                try {
                    Object obj = dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).get(id);
                    response.set(200, "Get", obj);
                } catch (Exception e) {
                    response.set(500, "Error", e);
                }
            });

            res.status(response.status);
            return response;
        }, json());

        get(uri + "/:id/lazy", type, (req, res) -> {
            Integer id = Integer.parseInt(req.params("id"));

            List<String> def = new ArrayList<>();

            def.add("id - " + id);

            Response<List<String>> response = new Response<>(200, "Get/Lazy", def);
            res.status(response.status);
            return response;
        }, json());

        get(uri, type, (req, res) -> {
            final Response<Object> response = new Response<Object>();
            Database.open(dsl -> {
                try {
                    List ret = dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).getAll();
                    response.set(200, "Get", ret);
                } catch (Exception e) {
                    response.set(500, "Error", e);
                }
            });

            res.status(response.status);
            return response;
        }, json());


        delete(uri + "/:id", type, (req, res) -> {
            final Response<Object> response = new Response<Object>();
            final Integer id = Integer.parseInt(req.params("id"));

            Database.transaction(dsl -> {
                try {
                    Object obj = dao.getDeclaredConstructor(DSLContext.class).newInstance(dsl).delete(id);
                    response.set(200, "Delete", obj);
                } catch (Exception e) {
                    response.set(500, "Error", e);
                }
            });

            res.status(response.status);
            return response;
        }, json());

    }

}
