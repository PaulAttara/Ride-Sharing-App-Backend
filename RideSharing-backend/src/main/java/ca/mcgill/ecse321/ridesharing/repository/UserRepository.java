package ca.mcgill.ecse321.ridesharing.repository;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.junit.runner.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;
import ch.qos.logback.core.joran.conditional.IfAction;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
		boolean usernameFound =false;
		boolean passwordFound = false;
		Query query_user = em.createNativeQuery("select username from users;");
		@SuppressWarnings("unchecked")
		List<String> usernames = (List<String>) query_user.getResultList();
		
		for (String thisUsername : usernames) {
			if(username.equals(thisUsername)) {
				usernameFound = true;
			}
		}
		
		Query query_pass = em.createNativeQuery("select password from users;");
		@SuppressWarnings("unchecked")
		List<String> passwords = (List<String>) query_pass.getResultList();
		
		for (String thisPassword : passwords) {
			if(password.equals(thisPassword)) {
				passwordFound = true;
			}
		}
		
		if(usernameFound && passwordFound) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Transactional
	public User getUser(String id) {
		return em.find(User.class, id);
	}

}
