package ca.mcgill.ecse321.ridesharing.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;

import java.util.List;

import javax.persistence.*;
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
		//aCar.setRoute(null);
		aCar.setDriver(null);
		em.persist(aCar);
		return aCar;
	}

	@Transactional
	public Car getVehicle(int id) {
		return em.find(Car.class, id);
	}

	@Transactional
	public boolean assignUserToCar(String username, int carId) {
		User driver = em.find(User.class, username);
		Car car = getVehicle(carId);
		if(driver == null || car == null) {
			return false;
		}
		int numCarsAssigned = 0;
		TypedQuery<Car> query = em.createQuery("select e from Car e where driver_username = '"+username+"'", Car.class);
		@SuppressWarnings("unchecked")
		List<Car> cars = (List<Car>) query.getResultList();
		for(Car thisCar : cars) {
			numCarsAssigned++;
			if(numCarsAssigned == 1) {
				return false;
			}
		}
		//driver.setCar(car);
		car.setDriver(driver);
		em.persist(car);
		//em.persist(driver);
		return true;
	}

	@Transactional
	public int getByUsername(String username) {
		Query query = em.createNativeQuery("select vehicleid from cars where driver_username = '"+username+"';");
		String idString = query.getSingleResult().toString();
		int id = Integer.parseInt(idString);
		return id;
	}
}