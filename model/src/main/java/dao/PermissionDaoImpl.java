package dao;

import dao.base.BaseDaoImpl;
import db.connection.properties.MysqlSessionFactory;
import models.Permission;
import org.hibernate.SessionFactory;

public class PermissionDaoImpl implements PermissionDao {

	private final BaseDaoImpl<Permission> permissionDao;

	public PermissionDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public PermissionDaoImpl(SessionFactory sessionFactory) {
		permissionDao = new BaseDaoImpl<>(sessionFactory, Permission.class);
	}

	@Override
	public void create(Permission obj) {
		permissionDao.create(obj);
	}

	@Override
	public Permission findById(long id) {
		return permissionDao.findById(id);
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
