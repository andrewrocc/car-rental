package infrastructure.dao.impl;

import infrastructure.dao.UserDao;
import infrastructure.dao.base.BaseDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public UserDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, User.class);
	}
}
