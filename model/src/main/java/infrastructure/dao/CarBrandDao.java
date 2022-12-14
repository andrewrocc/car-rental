package infrastructure.dao;

import infrastructure.dao.base.BaseDao;
import infrastructure.model.CarBrand;

import java.util.List;

public interface CarBrandDao extends BaseDao<CarBrand> {

	List<CarBrand> findAllBrandByName(String name);

	List<CarBrand> getAllCarBrands();
}
