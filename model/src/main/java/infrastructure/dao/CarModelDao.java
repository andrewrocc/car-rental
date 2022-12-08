package infrastructure.dao;

import infrastructure.dao.base.BaseDao;
import infrastructure.models.CarModel;

import java.util.List;

public interface CarModelDao extends BaseDao<CarModel> {

	List<CarModel> findAllCarModelByName(String name);
}
