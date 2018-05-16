/*
 * This file is generated by jOOQ.
*/
package br.com.rodrigocardoso.database.tables.records;


import br.com.rodrigocardoso.database.tables.Tarefas;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
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
public class TarefasRecord extends UpdatableRecordImpl<TarefasRecord> implements Record7<Integer, Timestamp, Timestamp, Boolean, String, String, Integer> {

    private static final long serialVersionUID = 956827860;

    /**
     * Setter for <code>public.tarefas.id</code>.
     */
    public TarefasRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.tarefas.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.tarefas.created</code>.
     */
    public TarefasRecord setCreated(Timestamp value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.tarefas.created</code>.
     */
    public Timestamp getCreated() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>public.tarefas.modified</code>.
     */
    public TarefasRecord setModified(Timestamp value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.tarefas.modified</code>.
     */
    public Timestamp getModified() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>public.tarefas.active</code>.
     */
    public TarefasRecord setActive(Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.tarefas.active</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>public.tarefas.titulo</code>.
     */
    public TarefasRecord setTitulo(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.tarefas.titulo</code>.
     */
    public String getTitulo() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.tarefas.descricao</code>.
     */
    public TarefasRecord setDescricao(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.tarefas.descricao</code>.
     */
    public String getDescricao() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.tarefas.dia</code>.
     */
    public TarefasRecord setDia(Integer value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.tarefas.dia</code>.
     */
    public Integer getDia() {
        return (Integer) get(6);
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
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Timestamp, Timestamp, Boolean, String, String, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Timestamp, Timestamp, Boolean, String, String, Integer> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Tarefas.TAREFAS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return Tarefas.TAREFAS.CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Tarefas.TAREFAS.MODIFIED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field4() {
        return Tarefas.TAREFAS.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Tarefas.TAREFAS.TITULO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Tarefas.TAREFAS.DESCRICAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return Tarefas.TAREFAS.DIA;
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
    public String component5() {
        return getTitulo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getDescricao();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component7() {
        return getDia();
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
    public String value5() {
        return getTitulo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getDescricao();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getDia();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TarefasRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TarefasRecord value2(Timestamp value) {
        setCreated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TarefasRecord value3(Timestamp value) {
        setModified(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TarefasRecord value4(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TarefasRecord value5(String value) {
        setTitulo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TarefasRecord value6(String value) {
        setDescricao(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TarefasRecord value7(Integer value) {
        setDia(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TarefasRecord values(Integer value1, Timestamp value2, Timestamp value3, Boolean value4, String value5, String value6, Integer value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TarefasRecord
     */
    public TarefasRecord() {
        super(Tarefas.TAREFAS);
    }

    /**
     * Create a detached, initialised TarefasRecord
     */
    public TarefasRecord(Integer id, Timestamp created, Timestamp modified, Boolean active, String titulo, String descricao, Integer dia) {
        super(Tarefas.TAREFAS);

        set(0, id);
        set(1, created);
        set(2, modified);
        set(3, active);
        set(4, titulo);
        set(5, descricao);
        set(6, dia);
    }
}
