package br.com.rodrigocardoso.daos;

import br.com.rodrigocardoso.database.tables.Usuarios;
import br.com.rodrigocardoso.database.tables.records.UsuariosRecord;
import br.com.rodrigocardoso.models.Login;
import br.com.rodrigocardoso.models.Usuario;
import org.jooq.DSLContext;
import org.jooq.Record;

import static br.com.rodrigocardoso.database.tables.Usuarios.USUARIOS;

/**
 * Created by rodri on 01/05/2018.
 */
public class UsuarioDao extends AbstractDao<UsuariosRecord, br.com.rodrigocardoso.models.Usuario> {

    private br.com.rodrigocardoso.database.tables.Usuarios U = USUARIOS;

    public UsuarioDao(DSLContext dsl) {
        super(dsl, Usuarios.USUARIOS, br.com.rodrigocardoso.models.Usuario.class);
    }

    public Usuario login(Login login) {
        Record record = dsl
                .select()
                .from(U)
                .where(U.EMAIL.eq(login.getEmail()))
                .and(U.SENHA.eq(login.getSenha()))
                .and(U.ACTIVE.eq(true))
                .fetchOne();
        if (record == null) {
            return null;
        }

        return record.into(Usuario.class);
    }

    public boolean hasUser(String email) {
        return  dsl
                    .selectCount()
                    .from(U)
                    .where(U.EMAIL.eq(email))
                    .fetchOne(0, int.class) > 0;
    }

}
