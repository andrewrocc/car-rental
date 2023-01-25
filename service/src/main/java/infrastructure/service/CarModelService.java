package infrastructure.service;

import infrastructure.dto.CarModelDTO;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;

    private final ModelMapper modelMapper;

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

    public List<CarModelDTO> getAllCarModels(PageRequest pageRequest) {
        List<CarModel> models = carModelRepository.findAll(pageRequest).stream().toList();
        return models.stream().map(m -> modelMapper.map(m, CarModelDTO.class))
                .collect(Collectors.toCollection(() -> new ArrayList<>(models.size())));
    }
}
