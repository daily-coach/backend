/*
 * This file is generated by jOOQ.
*/
package br.com.rodrigocardoso.database;


import br.com.rodrigocardoso.database.tables.Entidade;
import br.com.rodrigocardoso.database.tables.Tarefas;
import br.com.rodrigocardoso.database.tables.Usuarios;
import br.com.rodrigocardoso.database.tables.records.EntidadeRecord;
import br.com.rodrigocardoso.database.tables.records.TarefasRecord;
import br.com.rodrigocardoso.database.tables.records.UsuariosRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<EntidadeRecord, Integer> IDENTITY_ENTIDADE = Identities0.IDENTITY_ENTIDADE;
    public static final Identity<TarefasRecord, Integer> IDENTITY_TAREFAS = Identities0.IDENTITY_TAREFAS;
    public static final Identity<UsuariosRecord, Integer> IDENTITY_USUARIOS = Identities0.IDENTITY_USUARIOS;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<EntidadeRecord> ENTIDADE_PKEY = UniqueKeys0.ENTIDADE_PKEY;
    public static final UniqueKey<TarefasRecord> TAREFAS_ID_PK = UniqueKeys0.TAREFAS_ID_PK;
    public static final UniqueKey<UsuariosRecord> USUARIOS_ID_PK = UniqueKeys0.USUARIOS_ID_PK;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<EntidadeRecord, Integer> IDENTITY_ENTIDADE = createIdentity(Entidade.ENTIDADE, Entidade.ENTIDADE.ID);
        public static Identity<TarefasRecord, Integer> IDENTITY_TAREFAS = createIdentity(Tarefas.TAREFAS, Tarefas.TAREFAS.ID);
        public static Identity<UsuariosRecord, Integer> IDENTITY_USUARIOS = createIdentity(Usuarios.USUARIOS, Usuarios.USUARIOS.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<EntidadeRecord> ENTIDADE_PKEY = createUniqueKey(Entidade.ENTIDADE, "entidade_pkey", Entidade.ENTIDADE.ID);
        public static final UniqueKey<TarefasRecord> TAREFAS_ID_PK = createUniqueKey(Tarefas.TAREFAS, "tarefas_id_pk", Tarefas.TAREFAS.ID);
        public static final UniqueKey<UsuariosRecord> USUARIOS_ID_PK = createUniqueKey(Usuarios.USUARIOS, "usuarios_id_pk", Usuarios.USUARIOS.ID);
    }
}