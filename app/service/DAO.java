package service;



public interface DAO<K> {

	public K insert(K obj);

	public K delete(K obj);

	public K update(K obj);
	
	public K find(Object id);

}
