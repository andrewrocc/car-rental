package infrastructure.service;

import infrastructure.model.Car;
import infrastructure.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCarService {

    private final CarService carService;

    private final CarModelService modelService;

    public void delete(long id) {
        Car car = carService.getCarById(id);
        Order[] orders = car.getAllCars();
        long carModelId = car.getCarModel().getId();
        for (int i = 0; i < orders.length; i++) {       // can't replace lambda cuz java.util.ConcurrentModificationException
            car.removeOrder(orders[i]);
        }
        carService.deleteCar(id);
        boolean isEquals = carService.getCarByModelId(carModelId) == null;
        if (isEquals) {
            modelService.deleteModel(carModelId);
        }
    }
}
