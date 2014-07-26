package controllers;

import static play.data.Form.form;
import models.Account;
import play.data.Form;
import play.mvc.Result;
import be.objectify.deadbolt.java.actions.SubjectPresent;

public class Security extends EnhancedController {

	public static Result login() {
		return ok(views.html.login.render(form(Login.class)));
	}

	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		Login login = loginForm.get();
		Account account = Account.authenticate(login.email, login.password);
		
		if(account != null) {
			LOG.info("Account found");
			bind(account);
			return redirect(controllers.routes.Application.index());
		} else {
			LOG.info("Account is null");
			flash("Unable to authenticate", "Make sure your credentials are correct.");
			return redirect(controllers.routes.Security.login());
		}
	}

	@SubjectPresent
	public static Result logout() {
		session().clear();
		return redirect(controllers.routes.Security.login());
	}

	public static class Login {

		public String email;
		public String password;

	}

}
