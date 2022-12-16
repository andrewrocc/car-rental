package infrastructure.dao;

import infrastructure.dao.base.BaseDao;
import infrastructure.model.CarModel;

import java.util.List;

public interface CarModelDao extends BaseDao<CarModel> {

	List<CarModel> findAllCarModelByName(String name);

	List<CarModel> getAllCarModels();
}
