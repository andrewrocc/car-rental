package infrastructure.dao.impl;

import infrastructure.dao.RoleDao;
import infrastructure.dao.base.BaseDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Role.class);
	}

}
