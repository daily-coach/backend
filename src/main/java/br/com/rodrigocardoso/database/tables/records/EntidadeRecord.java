/*
 * This file is generated by jOOQ.
*/
package br.com.rodrigocardoso.database.tables.records;


import br.com.rodrigocardoso.database.tables.Entidade;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EntidadeRecord extends UpdatableRecordImpl<EntidadeRecord> implements Record4<Integer, Timestamp, Timestamp, Boolean> {

    private static final long serialVersionUID = 1518832099;

    /**
     * Setter for <code>public.entidade.id</code>.
     */
    public EntidadeRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.entidade.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.entidade.created</code>.
     */
    public EntidadeRecord setCreated(Timestamp value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.entidade.created</code>.
     */
    public Timestamp getCreated() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>public.entidade.modified</code>.
     */
    public EntidadeRecord setModified(Timestamp value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.entidade.modified</code>.
     */
    public Timestamp getModified() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>public.entidade.active</code>.
     */
    public EntidadeRecord setActive(Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.entidade.active</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Timestamp, Timestamp, Boolean> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Timestamp, Timestamp, Boolean> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Entidade.ENTIDADE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return Entidade.ENTIDADE.CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Entidade.ENTIDADE.MODIFIED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field4() {
        return Entidade.ENTIDADE.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component2() {
        return getCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component3() {
        return getModified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component4() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value2() {
        return getCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getModified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value4() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntidadeRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntidadeRecord value2(Timestamp value) {
        setCreated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntidadeRecord value3(Timestamp value) {
        setModified(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntidadeRecord value4(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntidadeRecord values(Integer value1, Timestamp value2, Timestamp value3, Boolean value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EntidadeRecord
     */
    public EntidadeRecord() {
        super(Entidade.ENTIDADE);
    }

    /**
     * Create a detached, initialised EntidadeRecord
     */
    public EntidadeRecord(Integer id, Timestamp created, Timestamp modified, Boolean active) {
        super(Entidade.ENTIDADE);

        set(0, id);
        set(1, created);
        set(2, modified);
        set(3, active);
    }
}
