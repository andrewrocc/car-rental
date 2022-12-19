package infrastructure.service;

import infrastructure.dao.CarModelDao;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarModelService {

    @Autowired
    private CarModelDao carModelDao;

    private List<String> modelList;

    @Transactional
    public List<String> getListCarModelByBrand(CarBrand brand) {
        if (modelList == null || modelList.isEmpty()) {
            List<CarModel> models = carModelDao.getAllCarModels();
            return getOnlyModelsNameFromCarModelClass(models, brand);
        } else {
            return modelList;
        }
    }

    private List<String> getOnlyModelsNameFromCarModelClass(List<CarModel> list, CarBrand brand) {
        modelList = new ArrayList<>(list.size());
        for (CarModel carModel : list) {
            if (carModel.getCarBrand().getId() == brand.getId()) {
                modelList.add(carModel.getModelName());
            }
        }

        return modelList;
    }
}
