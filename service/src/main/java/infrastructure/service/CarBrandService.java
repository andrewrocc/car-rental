package infrastructure.service;

import infrastructure.dto.CarBrandDTO;
import infrastructure.dto.CarModelDTO;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.repository.CarBrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarBrandService {

	private final CarBrandRepository carBrandRepository;

	private final ModelMapper modelMapper;

	public CarBrand findByName(String name) {
		return carBrandRepository.findByName(name);
	}

	public void update(CarBrand carBrand) {
		carBrandRepository.save(carBrand);
	}

	public CarBrand findById(Long id) {
		if (carBrandRepository.findById(id).isPresent())
			return carBrandRepository.findById(id).get();
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public List<CarBrandDTO> getAllCarBrands(PageRequest pageRequest) {
		List<CarBrand> models = carBrandRepository.findAll(pageRequest).stream().toList();
		return models.stream().map(m -> modelMapper.map(m, CarBrandDTO.class))
				.collect(Collectors.toCollection(() -> new ArrayList<>(models.size())));
	}
}
