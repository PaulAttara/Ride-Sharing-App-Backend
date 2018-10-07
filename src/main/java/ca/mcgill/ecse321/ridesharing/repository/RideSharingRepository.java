package ca.mcgill.ecse321.ridesharing.repository;

import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.Driver;
import ca.mcgill.ecse321.ridesharing.model.Passenger;
import ca.mcgill.ecse321.ridesharing.model.Request;
import ca.mcgill.ecse321.ridesharing.model.Route;
import ca.mcgill.ecse321.ridesharing.model.Status;
import ca.mcgill.ecse321.ridesharing.model.User;

@Repository
public class RideSharingRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public User createUser(String firstName, String lastName, String userName, String password, String city, String phoneNumber, String address) {
		
		User existingUser = entityManager.find(User.class, userName);
		if(existingUser == null) {
			User userAccount = new User();
			userAccount.setUserName(userName);
			userAccount.setAddress(address);
			userAccount.setCity(city);
			userAccount.setFirstName(firstName);
			userAccount.setLastName(lastName);
			userAccount.setPassword(password);
			userAccount.setPhoneNumber(phoneNumber);
			entityManager.persist(userAccount);
			return userAccount;
		}
		else {
			return null;
		}
			
	}

	@Transactional
	public User getUser(String userName) {
		User userAccount = entityManager.find(User.class, userName);
		return userAccount;

	}

	// this method updates the seats available in a route depending on the status of
	// the route
	@Transactional
	public void updateSeatsAvail(Route aRoute) {
		int seatsAvail = aRoute.getSeatsAvailable();
		Set<Request> requests = aRoute.getRequest();
		for (Request r : requests) {
			Status s = r.getStatus();
			if (s == s.Accepted) {
				seatsAvail--;
			} else if (s == s.Ended) {
				seatsAvail++;
			}

		}
		aRoute.setSeatsAvailable(seatsAvail);
	}

	// this method should be called when a passenger is dropped off
	// this gives the driver the chance to rate the passenger
	// this also increments the num of past trips for the passenger and updates his
	// rating
	// this also updates the status of the specific request of the passenger to
	// ended
	@Transactional
	public void passengerDroppedOff(Route aRoute, double rating, Passenger aPassenger) {

		Set<Request> requests = aRoute.getRequest();
		for (Request r : requests) {

			Passenger p = r.getPassenger();
			if (p == aPassenger) {
				r.setStatus(Status.Ended);
				double currentRating = aPassenger.getAvgRating();
				double numPastTrips = (double) aPassenger.getNumOfPastTrips();

				currentRating = ((rating + currentRating * (numPastTrips)) / (numPastTrips + 1));
				aPassenger.setNumOfPastTrips((int) numPastTrips + 1);
				aPassenger.setAvgRating(currentRating);
			}

		}
	}

	// A passenger will call this when he's dropped off and status of request is
	// ended
	// the route only has one driver so it is not necessary to take in a role
	// this updates the drivers rating and the number of trips
	// this should only be called when the passengerDroppedOff method has been
	// called
	// the status MUST BE = ENDED
	@Transactional
	public void passengerRatesDriver(double rating, Route aRoute) {

		Driver driver = aRoute.getDriver();
		// update rating and past trips
		double currentRating = driver.getAvgRating();
		double numPastTrips = (double) driver.getNumOfPastTrips();
		currentRating = ((rating + currentRating * (numPastTrips)) / (numPastTrips + 1));

		driver.setNumOfPastTrips((int) numPastTrips + 1);
		driver.setAvgRating(currentRating);

	}
	

}