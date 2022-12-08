package infrastructure.service;

import infrastructure.dao.CarBrandDao;
import infrastructure.models.CarBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarBrandService {

	@Autowired
	private CarBrandDao carBrandDao;

	private List<String> brandList;

	@Transactional
	public List<String> getListCarBrand() {
		if (brandList == null || brandList.isEmpty()) {
			List<CarBrand> brands = carBrandDao.getAllCarBrands();
			return getOnlyBrandNameFromCarBrandClass(brands);
		} else {
			return brandList;
		}
	}

	private List<String> getOnlyBrandNameFromCarBrandClass(List<CarBrand> list) {
		brandList = new ArrayList<>(list.size());
		for (CarBrand carBrand : list) {
			brandList.add(carBrand.getBrandName());
		}

		return brandList;
	}
}
