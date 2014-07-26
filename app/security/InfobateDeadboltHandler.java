package security;

import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Http.Context;
import play.mvc.SimpleResult;
import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import controllers.EnhancedController;

public class InfobateDeadboltHandler extends EnhancedController implements DeadboltHandler {

	@Override
	public Promise<SimpleResult> beforeAuthCheck(Context arg0) {
		return null;
	}

	@Override
	public DynamicResourceHandler getDynamicResourceHandler(Context arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject getSubject(Context arg0) {
		return getCurrentUser();
	}

	@Override
	public Promise<SimpleResult> onAuthFailure(Context arg0, String arg1) {
		LOG.info(arg0.request().host() + " failed to access " + arg0.request().uri() + ". Arg1 :"
		        + arg1);
		return F.Promise.promise(new F.Function0<SimpleResult>() {
			@Override
			public SimpleResult apply() throws Throwable {
				flash("Could not log-in. Please check username and password.");
				return redirect(controllers.routes.Security.login());
			}
		});
	}

}
