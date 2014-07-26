import java.util.List;
import java.util.Map;

import models.Account;
import models.AccountRole;
import play.Application;
import play.GlobalSettings;
import play.Logger.ALogger;
import play.libs.Yaml;

import com.avaje.ebean.annotation.Transactional;

public class Global extends GlobalSettings {
	static final ALogger LOG = play.Logger.of("application");

	@Transactional
	public void onStart(Application app) {
		LOG.info("Infobate is now starting!");
		try {
			init();
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
	}

	public void onStop(Application app) {
		LOG.warn("Infobate is now stopping");
	}

	@SuppressWarnings("unchecked")
	public void init() {
		Map<String, List<Object>> initValues = (Map<String, List<Object>>) Yaml.load("init.yaml");
		if (Account.find.all().isEmpty() && AccountRole.find.all().isEmpty()) {
			for (Object role : initValues.get("roles")) {
				((AccountRole) role).save();
			}
			for (Object account : initValues.get("accounts")) {
				((Account) account).roles.add(AccountRole.get("admin"));
				((Account) account).roles.add(AccountRole.get("manufacturer"));
				((Account) account).roles.add(AccountRole.get("distributor"));
				((Account) account).save();
			}
		}
	}
}
