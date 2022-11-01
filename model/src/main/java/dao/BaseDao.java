package dao;

import java.util.List;

public interface BaseDao<T> {

	void create(T obj);

	T findById(long id);

	List<T> getAllElements(String query);

	void update(T obj);

	void delete(T obj);
}
