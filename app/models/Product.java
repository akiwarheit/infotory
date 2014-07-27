package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Product extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Column(unique = true)
	public String description;
	
	public Date created = new Date();
	public Date updated = new Date();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacturer_id")
	public Manufacturer manufacturer;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<UPC> upcs = new ArrayList<UPC>();

	public static Finder<Long, Product> find = new Finder<Long, Product>(Long.class, Product.class);

}
