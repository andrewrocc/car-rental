package infrastructure.service;

import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    private List<String> modelList;

    @Transactional
    public List<String> getListCarModelByBrand(CarBrand brand) {
        if (modelList == null || modelList.isEmpty()) {
            List<CarModel> models = carModelRepository.findAll();
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
