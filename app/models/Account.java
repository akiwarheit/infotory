package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

@Entity
public class Account {

	@Id
	@Constraints.Required
	@Formats.NonEmpty
	private String email;

	@Constraints.Required
	private String name;

	@Constraints.Required
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transactional
	public static Account authenticate(String email, String password) {
		Account account = JPA.em().find(Account.class, email);
		if (account.getPassword().equals(password)) {
			return account;
		} else {
			return null;
		}
	}
}
