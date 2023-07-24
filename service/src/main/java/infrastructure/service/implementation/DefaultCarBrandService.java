package infrastructure.service.implementation;

import infrastructure.dto.CarBrandDTO;
import infrastructure.mapper.CarBrandMapper;
import infrastructure.model.CarBrand;
import infrastructure.repository.CarBrandRepository;
import infrastructure.service.CarBrandService;
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
public class DefaultCarBrandService implements CarBrandService {

	private final CarBrandRepository carBrandRepository;

	private final CarBrandMapper carBrandMapper;

	@Override
	public CarBrand findByName(String name) {
		return carBrandRepository.findByName(name);
	}

	@Override
	public void update(CarBrand carBrand) {
		carBrandRepository.save(carBrand);
	}

	@Override
	public List<CarBrandDTO> getAllCarBrands(PageRequest pageRequest) {
		List<CarBrand> models = carBrandRepository.findAll(pageRequest).getContent();
		return models.stream()
				.map(carBrandMapper::mapToDto)
				.collect(Collectors.toCollection(() -> new ArrayList<>(models.size())));
	}
}
