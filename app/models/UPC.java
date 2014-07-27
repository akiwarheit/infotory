package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name = "upc")
public class UPC extends Model {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Id
  public Long id;
  
  public String code;
  public String description;
  
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id")
  public Product product;
  
  public static class Factory {
    
    public static UPC generate(String description, String code, Product product) {
      UPC upc = new UPC();
      upc.description = description;
      upc.code = code;
      upc.save();
      upc.product = product;
      return upc;
    }
    
  }
  
}
