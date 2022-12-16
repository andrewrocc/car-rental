package infrastructure.dao.impl;

import infrastructure.dao.CarBrandDao;
import infrastructure.dao.base.BaseDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.CarBrand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
@Transactional
public class CarBrandDaoImpl extends BaseDaoImpl<CarBrand> implements CarBrandDao {

	public CarBrandDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, CarBrand.class);
	}

	@Override
	// TODO incorrect sql injection
	public List<CarBrand> findAllBrandByName(String name) {
		String queryFormat = "SELECT * FROM t_cars_brand WHERE t_cars_brand.CB_NAME LIKE '%" + name + "%';";
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery(queryFormat, CarBrand.class).list();
		}
	}

	@Override
	public List<CarBrand> getAllCarBrands() {
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<CarBrand> criteria = builder.createQuery(CarBrand.class);
		criteria.from(CarBrand.class);
		return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
	}
}
