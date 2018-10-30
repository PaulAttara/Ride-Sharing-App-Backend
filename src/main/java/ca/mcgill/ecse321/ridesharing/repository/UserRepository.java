package ca.mcgill.ecse321.ridesharing.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;
import javax.persistence.*;

import java.util.List;

@Repository
public class UserRepository {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public double addToRatings(String username, double rating) {
		User driver = getUser(username);
		driver.increaseNumTrips();
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
		boolean userValid = validateUsername(username);
		if(userValid) {
			user.setUsername(username);
		}else {
			return null;
		}
		user.setPassword(password);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPhoneNumber(phonenumber);
		user.setCity(city);
		user.setAddress(address);
		user.setRole(role);
		user.setRatings(null);
		user.setAvgRating(0);
		user.setNumTrips(0);
		em.persist(user);
		return user;
	}
	
	public boolean validateUsername(String username) {
		Query query = em.createNativeQuery("select username from users;");
		@SuppressWarnings("unchecked")
		List<String> usernames = (List<String>) query.getResultList();
		for (String thisUsername : usernames) {
			if(username.equals(thisUsername)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean login(String username, String password) {
		Query query_user = em.createNativeQuery("select username from users;");
		@SuppressWarnings("unchecked")
		List<String> usernames = (List<String>) query_user.getResultList();
		
		for (String thisUsername : usernames) {
			if(username.equals(thisUsername)) {
				
				User user = em.find(User.class, username);
				String userPassword = user.getPassword();
				
				if (userPassword.equals(password)) return true;
				
			}
		}
		
		return false;	
	}
	
	@Transactional
	public User getUser(String id) {
		return em.find(User.class, id);
	}

}
