package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.M2M_UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class M2M_UserRoleDaoImpl implements M2M_UserRoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<M2M_UserRole> userRoleDao;

	public M2M_UserRoleDaoImpl(SessionFactory sessionFactory) {
		userRoleDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(M2M_UserRole obj) {
		userRoleDao.create(obj);
	}

	@Override
	public M2M_UserRole findById(Class classType, long id) {
		return userRoleDao.findById(classType, id);
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
