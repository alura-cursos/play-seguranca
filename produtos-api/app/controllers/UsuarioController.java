package controllers;

import javax.inject.Inject;

import models.Usuario;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.*;

public class UsuarioController extends Controller {

	@Inject
	private FormFactory formularios;

	public Result formularioDeNovoUsuario() {
		Form<Usuario> formulario = formularios.form(Usuario.class);
		return ok(formularioDeNovoUsuario.render(formulario));
	}
	public Result salvaNovoUsuario() {
		Form<Usuario> formulario = formularios.form(Usuario.class).bindFromRequest();
		Usuario usuario = formulario.get();
		usuario.save();
		flash("success", "Usuário cadastrado com sucesso!");
		return redirect("/login"); //TODO
	}
}
