package br.com.rodrigocardoso.endpoints;

import br.com.rodrigocardoso.daos.AbstractDao;
import br.com.rodrigocardoso.infra.Database;
import br.com.rodrigocardoso.models.Entidade;
import br.com.rodrigocardoso.utils.Response;
import org.jooq.DSLContext;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static br.com.rodrigocardoso.utils.JsonUtils.*;
import static spark.Spark.*;

/**
 * Created by rodri on 26/04/2018.
 */
public abstract class AbstractEndpoint <E extends Entidade, D extends AbstractDao> implements IEndpoint {

    protected String uri;
    protected String type;
    protected Class<D> dao;

    protected BiConsumer<E, Response<E>> savePersistence;
    protected BiConsumer<E, Response<E>> updatePersistence;
    protected BiConsumer<Integer, Response<E>> getByIdPersistence;
    protected BiConsumer<Integer, Response<E>> getByIdLazyPersistence;
    protected BiConsumer<Integer, Response<List<E>>> getAllPersistence;
    protected BiConsumer<Integer, Response<Boolean>> deletePersistence;

    private Class<E> typeParameterClass;

    AbstractEndpoint(Class<E> typeParameterClass, String uri, Class<D> dao) {
        this.uri = uri;
        this.type = "application/json";
        this.typeParameterClass = typeParameterClass;
        this.dao = dao;
    }

    @Override()
    public void publish() {
        this.save();
        this.update();
        this.getById();
        this.getByIdLazy();
        this.getAll();
        this.delete();
    }

    protected void save() {
        post(uri, type, (req, res) -> {
            final Response<E> response = new Response<>();
            final String body = req.body();
            E obj = fromJson(body, typeParameterClass);

            this.savePersistence.accept(obj, response);
            res.status(response.status);
            return response;
        }, json());
    }

    protected void update() {
        put(uri, type, (req, res) -> {
            final Response<E> response = new Response<>();
            final String body = req.body();
            E obj = fromJson(body, typeParameterClass);

            this.updatePersistence.accept(obj, response);
            res.status(response.status);
            return response;
        }, json());
    }

    protected void getById() {
        get(uri + "/:id", type, (req, res) -> {
            final Response<E> response = new Response<>();
            final Integer id = Integer.parseInt(req.params("id"));

            this.getByIdPersistence.accept(id, response);
            res.status(response.status);
            return response;
        }, json());
    }

    protected void getByIdLazy() {
        get(uri + "/:id/lazy", type, (req, res) -> {
            Integer id = Integer.parseInt(req.params("id"));

            List<String> def = new ArrayList<>();

            def.add("id - " + id);

            Response<List<String>> response = new Response<>(200, "Get/Lazy", def);
            res.status(response.status);
            return response;
        }, json());
    }

    protected void getAll() {
        get(uri, type, (req, res) -> {
            final String userIdString = req.queryParams("user_id");
            final Integer userId;
            if (userIdString != null) {
                userId = Integer.parseInt(userIdString);
            } else {
                userId = null;
            }

            final Response<List<E>> response = new Response<>();

            this.getAllPersistence.accept(userId, response);
            res.status(response.status);
            return response;
        }, json());
    }

    protected void delete() {
        Spark.delete(uri + "/:id", type, (req, res) -> {
            final Response<Boolean> response = new Response<>();
            final Integer id = Integer.parseInt(req.params("id"));

            this.deletePersistence.accept(id, response);
            res.status(response.status);
            return response;
        }, json());
    }

}
