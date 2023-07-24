package infrastructure.service.implementation;

import infrastructure.dto.CarModelDTO;
import infrastructure.mapper.CarModelMapper;
import infrastructure.model.CarModel;
import infrastructure.repository.CarModelRepository;
import infrastructure.service.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCarModelService implements CarModelService {

    private final CarModelRepository carModelRepository;

    private final CarModelMapper modelMapper;

    @Override
    public CarModel findByName(String name) {
        return carModelRepository.findByModelName(name);
    }

    @Override
    public void update(CarModel carModel) {
        carModelRepository.save(carModel);
    }

    @Override
    public void deleteModel(long id) {
        carModelRepository.deleteById(id);
    }

    @Override
    public List<CarModelDTO> getAllCarModels(PageRequest pageRequest) {
        List<CarModel> models = carModelRepository.findAll(pageRequest).getContent();
        return models.stream()
                .map(modelMapper::mapToDto)
                .collect(Collectors.toCollection(() -> new ArrayList<>(models.size())));
    }
}
