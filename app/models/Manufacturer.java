package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Manufacturer extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Column(unique = true)
	public String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
	public List<Product> products;

	public static Finder<Long, Manufacturer> find = new Finder<Long, Manufacturer>(Long.class,
	        Manufacturer.class);
}
