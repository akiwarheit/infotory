package controllers.manufacturer;

import play.mvc.Result;
import controllers.Application;
import controllers.EnhancedController;

public class Products extends EnhancedController {

	public static Result index() {
		return ok(Application.render("Products List", views.html.manufacturer.products.render()));
	}

}
