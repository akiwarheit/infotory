package entity;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import models.Account;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.F;
import service.DAO;
import service.GenericDAO;
import utils.RandomString;

public class AccountTest {

	private final String email = RandomString.generate();
	private final String name = RandomString.generate();
	private final String password = RandomString.generate();

	@Before
	@Transactional
	public void before() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				JPA.withTransaction(new F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						DAO<Account> accountManager = new GenericDAO<Account>(JPA.em()) {
							@SuppressWarnings("unchecked")
							@Override
							public List<Account> list() {
								Query query = em
								        .createQuery("SELECT n FROM Account n");
								return query.getResultList();
							}

							@Override
							public List<Account> list(Query query, Map<String, String> parameters) {
								// TODO Auto-generated method stub
								return null;
							}
						};
						Account account = new Account();
						account.setEmail(email);
						account.setName(name);
						account.setPassword(password);
						accountManager.insert(account);
					}
				});
			}
		});
	}

	@Test
	@Transactional
	public void test() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				JPA.withTransaction(new F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						DAO<Account> accountManager = new GenericDAO<Account>(JPA.em()) {
							@Override
							public List<Account> list() {
								return null;
							}

							@Override
                            public List<Account> list(Query query, Map<String, String> parameters) {
	                            return null;
                            }
						};
						Account account = accountManager.find(email);
						Assert.assertNotNull(account);
						account.setName("Kevin Jude A. Deloria");
					}
				});
			}
		});
	}

	@After
	@Transactional
	public void after() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				JPA.withTransaction(new F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						DAO<Account> accountManager = new GenericDAO<Account>(JPA.em()) {
							@Override
							public List<Account> list() {
								return null;
							}

							@Override
                            public List<Account> list(Query query, Map<String, String> parameters) {
	                            return null;
                            }
						};
						Account account = accountManager.find(email);
						Assert.assertTrue(account.getName().equals("Kevin Jude A. Deloria"));
						accountManager.delete(account);
					}
				});
			}
		});
	}
}
