package br.com.rodrigocardoso.daos;

import br.com.rodrigocardoso.database.tables.Usuarios;
import br.com.rodrigocardoso.database.tables.records.UsuariosRecord;
import org.jooq.DSLContext;

/**
 * Created by rodri on 01/05/2018.
 */
public class UsuarioDao extends AbstractDao<UsuariosRecord, br.com.rodrigocardoso.models.Usuario> {

    public UsuarioDao(DSLContext dsl) {
        super(dsl, Usuarios.USUARIOS, br.com.rodrigocardoso.models.Usuario.class);
    }
}
