package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<Role> roleDao;

	public RoleDaoImpl(SessionFactory sessionFactory) {
		roleDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(Role obj) {
		roleDao.create(obj);
	}

	@Override
	public Role findById(Class classType, long id) {
		return roleDao.findById(classType, id);
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
