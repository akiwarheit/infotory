import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.Account;
import models.AccountRole;
import models.Manufacturer;
import models.Product;
import models.UPC;
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
    init();
  }
  
  public void onStop(Application app) {
    LOG.warn("Infobate is now stopping");
  }
  
  @SuppressWarnings("unchecked")
  public void init() {
    Map<String, List<Object>> initValues = (Map<String, List<Object>>) Yaml.load("init.yaml");
    
    LOG.info("Inserting roles");
    if (AccountRole.find.all().isEmpty()) {
      for (Object role : initValues.get("roles")) {
        ((AccountRole) role).save();
      }
      LOG.info("Inserted roles");
    }
    
    LOG.info("Inserting manufacturers");
    if (Manufacturer.find.all().isEmpty()) {
      for (Object manufacturer : initValues.get("manufacturers")) {
        ((Manufacturer) manufacturer).save();
      }
      LOG.info("Inserted manufacturers");
    }
    
    LOG.info("Inserting accounts");
    if (Account.find.all().isEmpty()) {
      for (Object account : initValues.get("accounts")) {
        Account accountObj = ((Account) account);
        accountObj.roles.add(AccountRole.get("admin"));
        accountObj.roles.add(AccountRole.get("manufacturer"));
        accountObj.roles.add(AccountRole.get("distributor"));
        accountObj.save();
      }
      LOG.info("Inserted accounts");
    }
    
    LOG.info("Inserting products");
    if (Product.find.all().isEmpty()) {
      for (Object product : initValues.get("products")) {
        Product prod = ((Product) product);
        prod.upcs.add(UPC.Factory.generate("12 DIGIT UPC", UUID.randomUUID().toString().substring(0, 12), prod));
        prod.upcs.add(UPC.Factory.generate("10 DIGIT UPC", UUID.randomUUID().toString().substring(0, 10), prod));
        prod.upcs.add(UPC.Factory.generate("SLEEVE UPC", UUID.randomUUID().toString().substring(0, 7), prod));
        prod.upcs.add(UPC.Factory.generate("CASE UPC", UUID.randomUUID().toString().substring(0, 7), prod));
        prod.save();
      }
      LOG.info("Inserted products");
    }
  }
}
