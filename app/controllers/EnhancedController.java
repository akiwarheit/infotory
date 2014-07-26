package controllers;

import models.Account;
import play.Logger.ALogger;
import play.mvc.Controller;

public class EnhancedController extends Controller {
	public static final ALogger LOG = play.Logger.of("application");

	public static void bind(Account account) {
		session().put("email", account.email);
		session().put("name", account.name);
	}

	public static Account getCurrentUser() {
		String email = session().get("email");
		return Account.find.where().eq("email", email).findUnique();
	}
}
