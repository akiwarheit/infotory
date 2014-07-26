package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import play.db.ebean.Model;
import be.objectify.deadbolt.core.models.Permission;

@Entity
@Table(name = "role")
public class AccountRole extends Model implements be.objectify.deadbolt.core.models.Role,
        Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Column(unique = true)
	public String name;

	@ManyToMany(mappedBy = "roles")
	public List<Account> users = new ArrayList<Account>();

	@Override
	public String getValue() {
		return name;
	}

	@Override
	public String getName() {
		return name;
	}

	public static Finder<Long, AccountRole> find = new Finder<Long, AccountRole>(Long.class,
	        AccountRole.class);
	
	public static AccountRole get(String name) {
		return AccountRole.find.where().eq("name", name).findUnique();
	}

}
