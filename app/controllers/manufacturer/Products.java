package controllers.manufacturer;

import java.util.List;

import models.Product;
import play.mvc.Result;
import controllers.Application;
import controllers.EnhancedController;

public class Products extends EnhancedController {

	public static Result index() {
	  List<Product> products = Product.find.all();
		return ok(Application.render("Products List", views.html.manufacturer.products.render(products)));
	}
	
	public static Result create() {
	  return ok(Application.render("New Product", views.html.manufacturer.addproduct.render()));
	}

}
