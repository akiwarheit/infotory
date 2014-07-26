package controllers;

import play.api.templates.Html;
import play.mvc.Result;
import be.objectify.deadbolt.java.actions.SubjectPresent;

public class Application extends EnhancedController {

	@SubjectPresent
	public static Result index() {
		return ok(Application.render("Dashboard", views.html.partials.content.dash.render()));
	}

	public static class Login {

		public String email;
		public String password;

	}

	public static Html render(String title, Html html) {
		return views.html.container.render(title, html);
	}
}
