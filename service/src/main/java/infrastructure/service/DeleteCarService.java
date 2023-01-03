package infrastructure.service;

import infrastructure.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCarService {

    private final CarService carService;

    private final CarModelService modelService;

    private final CarBrandService brandService;

    public void delete(long id) {
        Car car = carService.getCarById(id);
        long carBrandId = car.getCarBrand().getId();
        long carModelId = car.getCarModel().getId();
        carService.deleteCar(id);
        boolean isEquals = carService.getCarByModelId(carModelId) == null;
        if (isEquals) {
            modelService.deleteModel(carModelId);
            if (modelService.getCountModelsByBrand(carModelId) == 0) {
                brandService.deleteBrand(carBrandId);
            }
        }
    }
}
