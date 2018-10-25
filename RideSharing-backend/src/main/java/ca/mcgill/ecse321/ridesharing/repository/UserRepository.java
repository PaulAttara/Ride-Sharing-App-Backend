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
public class UserRepository {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public User createUser(String username, String password, String firstname, String lastname, String phonenumber,
			String city, String address) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPhoneNumber(phonenumber);
		user.setCity(city);
		user.setAddress(address);
		em.persist(user);
		return user;
	}

	@Transactional
	public User getUser(int id) {
		return em.find(User.class, id);
	}

}
