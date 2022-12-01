package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.Permission;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<Permission> permissionDao;

	public PermissionDaoImpl(SessionFactory sessionFactory) {
		permissionDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(Permission obj) {
		permissionDao.create(obj);
	}

	@Override
	public Permission findById(Class classType, long id) {
		return permissionDao.findById(Permission.class, id);
	}

	@Override
	public void update(Permission obj) {
		permissionDao.update(obj);
	}

	@Override
	public void delete(Permission obj) {
		permissionDao.delete(obj);
	}
}
