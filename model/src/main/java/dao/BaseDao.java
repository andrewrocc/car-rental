package dao;

public interface BaseDao<T> {

	void create(T obj);

	T findById(long id);

	void update(T obj);

	void delete(T obj);
}
