package autenticadores;

import static controllers.UsuarioController.AUTH;

import java.util.Optional;

import javax.inject.Inject;

import controllers.routes;
import daos.UsuarioDAO;
import models.Usuario;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security.Authenticator;

public class UsuarioAutenticado extends Authenticator {

	@Inject
	private UsuarioDAO usuarioDAO;

	@Override
	public String getUsername(Context context) {
		String email = context.session().getOrDefault(AUTH, "");
		Optional<Usuario> possivelUsuario = usuarioDAO.comEmail(email);
		if (!possivelUsuario.isPresent()) {
			return null;
		}
		Usuario usuario = possivelUsuario.get();
		return usuario.getEmail();
	}
	
	@Override
	public Result onUnauthorized(Context context) {
		context.flash().put("danger", "Não autorizado!");
		return redirect(routes.UsuarioController.formularioDeLogin());
	}
}
