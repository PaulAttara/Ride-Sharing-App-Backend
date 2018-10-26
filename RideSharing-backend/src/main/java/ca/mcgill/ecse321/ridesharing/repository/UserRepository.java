package ca.mcgill.ecse321.ridesharing.repository;

import org.junit.runner.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public double addToRatings(String username, double rating) {
		User driver = getUser(username);
		driver.addRating(rating);
		double avgRating = computeRating(username);
		return avgRating;
	}
	
	@Transactional
	public double computeRating(String username) {
		User driver = getUser(username);
		int numPastTrips = driver.getNumTrips();
		List<Double> ratings = driver.getRatings();
		double avgRating=0;
		for (Double rating : ratings) {
			avgRating += rating;
		}
		avgRating /= numPastTrips;
		driver.setAvgRating(avgRating);
		em.persist(driver);
		return avgRating;
	}

	@Transactional
	public User createUser(String username, String password, String firstname, String lastname, String phonenumber,
			String city, String address, String role) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPhoneNumber(phonenumber);
		user.setCity(city);
		user.setAddress(address);
		user.setRole(role);
		em.persist(user);
		return user;
	}
	
	@Transactional
	public User getUser(String id) {
		return em.find(User.class, id);
	}

}
