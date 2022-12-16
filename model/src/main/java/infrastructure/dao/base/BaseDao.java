package infrastructure.dao.base;

import java.util.List;

public interface BaseDao<T> {

	void create(T obj);

	T findById(long id);

	void update(T obj);

	void delete(T obj);

	List<T> findAll();
}
