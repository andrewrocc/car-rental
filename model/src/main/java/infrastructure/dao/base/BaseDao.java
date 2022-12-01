package infrastructure.dao.base;

public interface BaseDao<T> {

	void create(T obj);

	T findById(Class classType, long id);

	void update(T obj);

	void delete(T obj);
}
