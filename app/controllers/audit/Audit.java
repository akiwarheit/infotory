package controllers.audit;

import play.mvc.Result;
import controllers.Application;
import controllers.EnhancedController;

public class Audit extends EnhancedController {
  
  public static Result list() {
    return ok(Application.render("Audit", views.html.audit.logs.render()));
  }
  
}
