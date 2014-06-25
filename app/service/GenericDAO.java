package service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDAO<K> implements DAO<K> {

	protected Class<K> type;
	protected EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDAO(EntityManager em) {
		this.em = em;
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public K insert(K obj) {
		em.persist(obj);
		return obj;
	}

	@Override
	public K delete(K obj) {
		em.remove(obj);
		return obj;
	}

	@Override
	public K update(K obj) {
		return obj;
	}

	@Override
	public K find(Object id) {
		return em.find(type, id);
	}

	public abstract List<K> list();

	public abstract List<K> list(Query query, Map<String, String> parameters);

	public Class<K> getType() {
		return type;
	}

	public void setType(Class<K> type) {
		this.type = type;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
