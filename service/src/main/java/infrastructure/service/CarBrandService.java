package infrastructure.service;

import infrastructure.dao.CarBrandDao;
import infrastructure.model.CarBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarBrandService {

	@Autowired
	private CarBrandDao carBrandDao;

	private List<CarBrand> carBrands;

	@Transactional
	public List<CarBrand> getListCarBrand() {
		if (carBrands == null || carBrands.isEmpty()) {
			return carBrands = carBrandDao.getAllCarBrands();
//			return getOnlyBrandNameFromCarBrandClass(brands);
		} else {
			return carBrands;
		}
	}

//	private List<String> getOnlyBrandNameFromCarBrandClass(List<CarBrand> list) {
//		carBrands = new ArrayList<>(list.size());
//		for (CarBrand carBrand : list) {
//			carBrands.add(carBrand.getBrandName());
//		}
//
//		return carBrands;
//	}
}
