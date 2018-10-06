package ca.mcgill.ecse321.ridesharing.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.User;

@Repository
public class RideSharingRepository {

	@Autowired
	EntityManager entityManager;
	

	@Transactional
	public User createUser(String userName) {
		User participant = new User();
		participant.setUserName(userName);
		entityManager.persist(participant);
		return participant;
	}

	@Transactional
	public User getUser(String name) {
		User participant = entityManager.find(User.class, name);
		return participant;
	}

} 