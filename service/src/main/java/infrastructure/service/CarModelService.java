package infrastructure.service;

import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    public CarModel findByName(String name) {
        return carModelRepository.findByName(name);
    }

    public void update(CarModel carModel) {
        carModelRepository.save(carModel);
    }

    public void deleteModel(long id) {
        carModelRepository.deleteById(id);
    }

    public CarModel findById(Long id) {
        if (carModelRepository.findById(id).isPresent())
            return carModelRepository.findById(id).get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
