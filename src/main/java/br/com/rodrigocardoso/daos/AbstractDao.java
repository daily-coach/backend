package br.com.rodrigocardoso.daos;

import br.com.rodrigocardoso.models.Entidade;
import org.jooq.DSLContext;
import org.jooq.impl.TableImpl;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by rodri on 01/05/2018.
 */
public abstract class AbstractDao <T extends UpdatableRecordImpl<T>, E extends Entidade> implements IDao {

    private DSLContext dsl;
    private TableImpl<T> table;
    private Class<E> model;

    public AbstractDao(DSLContext dsl, TableImpl<T> table, Class<E> model) {
        this.dsl = dsl;
        this.table = table;
        this.model = model;
    }

    @Override
    public Integer save(Entidade value) {
        value.setCreated(LocalDateTime.now());
        UpdatableRecordImpl<T> rec = dsl.newRecord(table, value);
        if (rec.store() > 0) {
            Integer id = (Integer) rec.getValue("id");
            value.setId(id);
            return id;
        }
        return -1;
    }

    @Override
    public Boolean update(Entidade value) {
        value.setModified(LocalDateTime.now());
        UpdatableRecordImpl<T> rec = dsl.newRecord(table, value);
        return rec.update() > 0;
    }

    @Override
    public E get(Integer id) {
        return dsl
                .select()
                .from(table)
                .where("id = " + id)
                .fetchOne()
                .into(model);
    }

    @Override
    public List<? extends Entidade> getAll() {
        return dsl
                .select()
                .from(table)
                .fetch()
                .into(model);
    }

    @Override
    public Boolean delete(Integer id) {
        return dsl.query("UPDATE " + table.getName() + " set active = false where id = " + id).execute() > 0;
    }
}
