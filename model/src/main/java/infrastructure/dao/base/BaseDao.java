package infrastructure.dao.base;

import java.util.List;

/**
 * Base dao is not actual now
 * @deprecated
 * <br>
 * Use high-level abstraction {@link dao#repository} interfaces instead.
 */
@Deprecated
public interface BaseDao<T> {

	void create(T obj);

	T findById(long id);

	void update(T obj);

	void delete(T obj);

	List<T> findAll();
}
