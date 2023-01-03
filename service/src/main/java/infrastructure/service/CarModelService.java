package infrastructure.service;

import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    private List<String> modelList;

    public List<String> getListCarModelByBrand(CarBrand brand) {
        if (modelList == null || modelList.isEmpty()) {
            List<CarModel> models = carModelRepository.findAll();
            return getOnlyModelsNameFromCarModelClass(models, brand);
        } else {
            return modelList;
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private List<String> getOnlyModelsNameFromCarModelClass(List<CarModel> list, CarBrand brand) {
        modelList = new ArrayList<>(list.size());
        for (CarModel carModel : list) {
            if (carModel.getCarBrand().getId() == brand.getId()) {
                modelList.add(carModel.getModelName());
            }
        }

        return modelList;
    }

    public CarModel findByName(String name) {
        return carModelRepository.findByName(name);
    }

    public void update(CarModel carModel) {
        carModelRepository.save(carModel);
    }

    public void deleteModel(long id) {
        carModelRepository.deleteById(id);
    }

    public int getCountModelsByBrand(long id) {
        return carModelRepository.findByBrandId(id).size();
    }
}
