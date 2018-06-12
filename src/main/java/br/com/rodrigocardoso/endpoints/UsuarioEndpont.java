package br.com.rodrigocardoso.endpoints;

import br.com.rodrigocardoso.daos.UsuarioDao;
import br.com.rodrigocardoso.infra.Database;
import br.com.rodrigocardoso.models.Login;
import br.com.rodrigocardoso.models.Usuario;
import br.com.rodrigocardoso.utils.JwtUtils;
import br.com.rodrigocardoso.utils.PasswordUtils;
import br.com.rodrigocardoso.utils.Response;
import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;

import static br.com.rodrigocardoso.utils.JsonUtils.*;
import static spark.Spark.*;

/**
 * Created by rodri on 26/05/2018.
 */
public class UsuarioEndpont extends JooqEndpoint<Usuario, UsuarioDao> {

    public UsuarioEndpont() {
        super(Usuario.class, "/safe/usuarios", UsuarioDao.class);
    }

    @Override()
    public void publish() {
        super.publish();
        login();
    }

    @Override()
    protected void save() {
        post("/usuarios", type, (req, res) -> {
            Response<Usuario> response = new Response<>();
            try {

                Usuario usuario = fromJson(req.body(), Usuario.class);
                byte[] salt = PasswordUtils.getNextSalt();
                byte[] hash = PasswordUtils.hash(usuario.getSenha().toCharArray(), salt);
                usuario.setSalt(Base64.encodeBase64String(salt));
                usuario.setSenha(Base64.encodeBase64String(hash));

                Database.transaction(dsl -> {
                    UsuarioDao dao = new UsuarioDao(dsl);
                    if (dao.hasUser(usuario.getEmail())) {
                        usuario.setId(-1);
                    } else {
                        usuario.setId(dao.save(usuario));
                    }
                });

                if (usuario.getId() < 1) {
                    response.set(404, "Usuário já existe na base", null);
                } else {
                    response.set(200, "Salvo com sucesso", usuario);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.set(500, e.getMessage(), null);
            } finally {
                res.status(response.status);
            }
            return response;
        }, json());
    }

    private void login() {
        post("/login", "application/json", (req, res) -> {
            Response<Usuario> response = new Response<>();
            try {
                Login login = fromJson(req.body(), Login.class);

                Database.open(dsl -> {
                    Usuario usuario = new UsuarioDao(dsl).login(login);
                    if (usuario == null) {
                        response.set(401, "Não autorizado", null);
                    } else if (
                            !PasswordUtils.validatePassword(
                                    login.getSenha().toCharArray(),
                                    Base64.decodeBase64(usuario.getSalt()),
                                    Base64.decodeBase64(usuario.getSenha()))
                            ) {
                        response.set(401, "Não autorizado", null);
                    } else {
                        try {
                            usuario.setToken(JwtUtils.createToken(usuario.getEmail()));
                            usuario.setSenha(null);
                            usuario.setSalt(null);
                            response.set(200, "Autorizado", usuario);
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.set(500, e.getMessage(), null);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                response.set(500, e.getMessage(), null);
            } finally {
                res.status(response.status);
            }
            return response;
        }, json());
    }

}
