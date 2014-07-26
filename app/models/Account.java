package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.db.jpa.Transactional;
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
	@Constraints.Required
	@Formats.NonEmpty
	public String email;

	@Constraints.Required
	public String name;

	@Constraints.Required
	public String password;

	@ManyToMany
	@JoinTable(name = "account_roles")
	public List<AccountRole> roles;

	public String getEmail() {
		return email;
	}

	public static Finder<Long, Account> find = new Finder<Long, Account>(Long.class,

	Account.class);

	@Transactional
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
