package infrastructure.dao.impl;

import infrastructure.dao.CarModelDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
@Transactional
public class CarModelDaoImpl extends BaseDaoImpl<CarModel> implements CarModelDao {

	public CarModelDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, CarModel.class);
	}

	@Override
	public List<CarModel> findAllCarModelByName(String name) {
		String queryFormat = "SELECT * FROM t_cars_model WHERE t_cars_brand.CB_NAME LIKE '%" + name + "%';";
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery(queryFormat, CarModel.class).list();
		}
	}

	@Override
	public List<CarModel> getAllCarModels() {
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<CarModel> criteria = builder.createQuery(CarModel.class);
		criteria.from(CarModel.class);
		return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
	}
}
