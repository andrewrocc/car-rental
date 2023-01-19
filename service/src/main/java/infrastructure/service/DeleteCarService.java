package infrastructure.service;

import infrastructure.model.Car;
import infrastructure.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCarService {

    private final CarService carService;

    private final CarModelService modelService;

    public void delete(long id) {
        Car car = carService.getCarById(id);
        Set<Order> orders = car.getOrders();
        long carModelId = car.getCarModel().getId();
        orders.forEach(car::removeOrder);
        carService.deleteCar(id);
        boolean isEquals = carService.getCarByModelId(carModelId) == null;
        if (isEquals) {
            modelService.deleteModel(carModelId);
        }
    }
}
