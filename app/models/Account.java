package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import play.db.ebean.Model;
import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;

@Entity
@Table(name = "account")
public class Account extends Model implements Subject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public String email;
	public String name;
	public String password;

	@ManyToMany
	@JoinTable(name = "account_roles")
	public List<AccountRole> roles = new ArrayList<AccountRole>();

	public static Finder<String, Account> find = new Finder<String, Account>(String.class,
	        Account.class);

	public static Account authenticate(String email, String password) {
		return Account.find.where().eq("email", email).findUnique();
	}

	@Override
	public String getIdentifier() {
		return email;
	}

	@Override
	public List<? extends Permission> getPermissions() {
		return roles;
	}

	@Override
	public List<? extends Role> getRoles() {
		return roles;
	}
}
