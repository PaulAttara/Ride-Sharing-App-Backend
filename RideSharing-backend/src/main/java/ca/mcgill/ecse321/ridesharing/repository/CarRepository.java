package ca.mcgill.ecse321.ridesharing.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Car createVehicle(String brand, String model, String licensePlate) {

		Car aCar = new Car();
		aCar.setBrand(brand);
		aCar.setModel(model);
		aCar.setLicensePlate(licensePlate);
		aCar.setRoute(null);
		aCar.setDriver(null);
		em.persist(aCar);
		return aCar;
	}

	@Transactional
	public Car getVehicle(int id) {
		return em.find(Car.class, id);
	}
}
