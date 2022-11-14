package dao;

import dao.base.BaseDaoImpl;
import db.connection.properties.MysqlSessionFactory;
import models.M2M_UserRole;
import org.hibernate.SessionFactory;

public class M2M_UserRoleDaoImpl implements M2M_UserRoleDao {

	private final BaseDaoImpl<M2M_UserRole> userRoleDao;

	public M2M_UserRoleDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public M2M_UserRoleDaoImpl(SessionFactory sessionFactory) {
		userRoleDao = new BaseDaoImpl<>(sessionFactory, M2M_UserRole.class);
	}

	@Override
	public void create(M2M_UserRole obj) {
		userRoleDao.create(obj);
	}

	@Override
	public M2M_UserRole findById(long id) {
		return userRoleDao.findById(id);
	}

	@Override
	public void update(M2M_UserRole obj) {
		userRoleDao.update(obj);
	}

	@Override
	public void delete(M2M_UserRole obj) {
		userRoleDao.delete(obj);
	}
}
