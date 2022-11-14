package dao;

import dao.base.BaseDaoImpl;
import db.connection.properties.MysqlSessionFactory;
import models.Role;
import org.hibernate.SessionFactory;

public class RoleDaoImpl implements RoleDao {

	private final BaseDaoImpl<Role> roleDao;

	public RoleDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public RoleDaoImpl(SessionFactory sessionFactory) {
		roleDao = new BaseDaoImpl<>(sessionFactory, Role.class);
	}

	@Override
	public void create(Role obj) {
		roleDao.create(obj);
	}

	@Override
	public Role findById(long id) {
		return roleDao.findById(id);
	}

	@Override
	public void update(Role obj) {
		roleDao.update(obj);
	}

	@Override
	public void delete(Role obj) {
		roleDao.delete(obj);
	}
}
