package infrastructure.service;

import infrastructure.dao.CarDao;
import infrastructure.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

	@Autowired
	private CarDao carDao;

	@Transactional
	public void addNewCar(Car c) {
		carDao.create(c);
	}
}
