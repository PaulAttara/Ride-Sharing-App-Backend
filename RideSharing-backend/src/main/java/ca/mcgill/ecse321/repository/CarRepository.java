package ca.mcgill.ecse321.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.model.*;

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

		User driver = new User();
		driver.setAddress("100");
		driver.setAvgRating(4.5);
		driver.setCity("Mtl");
		driver.setFirstName("Noam");
		driver.setLastName("Suissa");
		driver.setNumTrips(50);
		driver.setPassword("1234");
		driver.setUsername("noamsuissa");
		driver.setPhoneNumber("5147916626");
		em.persist(driver);
		aCar.setDriver(driver);
		//        driver.setRole("Driver");
		//        
		//        Route route = new Route();
		//        driver.setRoute(route);
		//        
		//        Location loc = new Location();
		//        loc.setCity("Nyc");
		//        loc.setDriver(driver);
		//        loc.setPrice(25.0);
		//        loc.setRoute(route);
		//        loc.setDriver(driver);
		//        loc.setPassenger(driver);
		//        
		//        aCar.setDriver(driver);
		//        aCar.setRoute(route);
		//        em.persist(route);
		//        em.persist(driver);
		em.persist(aCar);
		return aCar;
	}

	@Transactional
	public Car getVehicle(int id) {
		return em.find(Car.class, id);
	}
}
