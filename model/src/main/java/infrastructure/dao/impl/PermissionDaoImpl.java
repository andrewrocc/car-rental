package infrastructure.dao.impl;

import infrastructure.dao.PermissionDao;
import infrastructure.dao.base.BaseDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.Permission;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao {
	public PermissionDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Permission.class);
	}
}
