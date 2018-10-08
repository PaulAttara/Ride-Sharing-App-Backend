package ca.mcgill.ecse321.ridesharing.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.Car;
import ca.mcgill.ecse321.ridesharing.model.Driver;
import ca.mcgill.ecse321.ridesharing.model.Location;
import ca.mcgill.ecse321.ridesharing.model.Passenger;
import ca.mcgill.ecse321.ridesharing.model.Request;
import ca.mcgill.ecse321.ridesharing.model.Role;
import ca.mcgill.ecse321.ridesharing.model.Route;
import ca.mcgill.ecse321.ridesharing.model.Status;
import ca.mcgill.ecse321.ridesharing.model.SystemAdministrator;
import ca.mcgill.ecse321.ridesharing.model.User;

/**
 * This class consists of all the controller methods which we created
 * @author Arielle Lasry
 * @author Noam Suissa
 * @author Eden Ovadia
 * @author Kamy Moussavi
 * @author Lucas Bellido
 * @author Paul Attara
 *
 */

@Repository
public class RideSharingRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
<<<<<<< HEAD
	/**
	 * This method is for creating a users account
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param city
	 * @param phoneNumber
	 * @param address
	 * @return
	 */
	public User createUser(String firstName, String lastName, String userName, String password, String city, String phoneNumber, String address) {
		
=======
	public User createUser(String firstName, String lastName, String userName, String password, String city,
			String phoneNumber, String address) {

>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84
		User existingUser = getUser(userName);
		if (existingUser == null) {
			User userAccount = new User();
			userAccount.setUserName(userName);
			userAccount.setAddress(address);
			userAccount.setCity(city);
			userAccount.setFirstName(firstName);
			userAccount.setLastName(lastName);
			userAccount.setPassword(password);
			userAccount.setPhoneNumber(phoneNumber);

			Driver driverRole = new Driver();
			driverRole.setUser(userAccount);

			Passenger passengerRole = new Passenger();
			passengerRole.setUser(userAccount);
			Set<Role> roles = new HashSet<Role>();
			roles.add(driverRole);
			roles.add(passengerRole);
			userAccount.setRole(roles);

			entityManager.persist(userAccount);

			return userAccount;
		} else {
			return null;
		}

	}

	@Transactional
	public User getUser(String userName) {
		User userAccount = entityManager.find(User.class, userName);
		return userAccount;

	}

	@Transactional
<<<<<<< HEAD
	/**
	 * This method returns true if user is found
	 * It returns false is user login is invalid
	 * @param userName
	 * @param password
	 * @return
	 */
=======
	// Returns true if user is found
	// False if user login is invalid
>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84
	public boolean loginAdmin(String userName, String password) {
		if (userName.equals("adminUsername") && password.equals("adminPassword")) {
			return true;
		} else {
			return false;
		}

	}
	@Transactional
	/**
	 * This method adds/registers a new car 
	 * @param brand
	 * @param model
	 * @param licensePlate
	 * @param driver
	 * @return
	 */
	public Car createCar(String brand, String model, String licensePlate, Driver driver) {
<<<<<<< HEAD
		Car existingCar = getCar(licensePlate);
		if(existingCar == null) {
=======
		Car existingCar = entityManager.find(Car.class, licensePlate);
		if (existingCar == null) {
>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84
			Car addedCar = new Car();
			addedCar.setBrand(brand);
			addedCar.setModel(model);
			addedCar.setLicensePlate(licensePlate);
			addedCar.setDriver(driver);
			entityManager.persist(addedCar);
			return addedCar;
		} else {
			return null;
		}
	}

<<<<<<< HEAD
	@Transactional
	public Car getCar(String licensePlate) {
		Car addedCar = entityManager.find(Car.class, licensePlate);
		return addedCar;	
	}
	
	/**
	 * This method is for a driver to accept a passenger's route request 
	 * @param aRoute
	 * @param aPassenger
	 */
	public void acceptPassengerRequest(Route aRoute, Passenger aPassenger) {
=======
	public void acceptPassengerRequest(Route aRoute, Request aRequest) {

		aRequest.setStatus(Status.Accepted);
		int seats = aRoute.getSeatsAvailable();
		seats--;
		aRoute.setSeatsAvailable(seats);
		SortedSet<Location> location = aRoute.getLocation();
		location.add(aRequest.getPickUp());
		aRoute.setLocation(location);
>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84

	}
<<<<<<< HEAD
	@Transactional
	/**
	 * This method is for when a driver wants to deny/cancel a passengers route request
	 * There is no return type
	 * @param aRoute
	 * @param aPassenger
	 */
	public void denyPassengerRequest(Route aRoute, Passenger aPassenger) {
=======
>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84

	public void denyPassengerRequest(Request aRequest) {
		if(aRequest != null)
		aRequest.setStatus(Status.Cancelled);

	}

<<<<<<< HEAD
	
=======
	// this method updates the seats available in a route depending on the status of
	// the route
>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84
	@Transactional
	/**
	 * This method updates the seats available in a route depending on the status of
	 * the route
	 * @param aRoute
	 */
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

<<<<<<< HEAD

=======
	// this method should be called when a passenger is dropped off
	// this allows the driver to to rate the passenger and updates the passengers
	// rating
	// this also increments the num of past trips for the passenger and updates his
	// rating
	// this also updates the status of the specific request of the passenger to
	// ended
>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84
	@Transactional
	/**
	 * This method should be called when a passenger is dropped off
	 * It allows the driver to to rate the passenger and then updates the passengers rating 
	 * It increments the number of past trips for the passenger
	 * It also updates the status of the specific request of the passenger to ended
	 * 
	 * @param aRoute
	 * @param rating
	 * @param aPassenger
	 */
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


	@Transactional
	/**
	 * This method is for when a passenger is dropped off and status of request is ended
	 * It updates the drivers rating and the number of trips completed
	 * 
	 * @param rating
	 * @param aRoute
	 */
	public void passengerRatesDriver(double rating, Route aRoute) {

		Driver driver = aRoute.getDriver();
		// update rating and past trips
		double currentRating = driver.getAvgRating();
		double numPastTrips = (double) driver.getNumOfPastTrips();
		currentRating = ((rating + currentRating * (numPastTrips)) / (numPastTrips + 1));

		driver.setNumOfPastTrips((int) numPastTrips + 1);
		driver.setAvgRating(currentRating);

	}

	@Transactional
<<<<<<< HEAD
	/**
	 * This method returns true is user is found
	 * It returns false if user login is invalid
	 * @param userName
	 * @param password
	 * @return
	 */
=======
	// Returns true if user is found
	// False if user login is invalid
>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84
	public boolean loginUser(String userName, String password) {
		User user = entityManager.find(User.class, userName);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Transactional
<<<<<<< HEAD
	/**
	 * 
	 * @param endLocation
	 * @return
	 */
	public List<Route> getRelevantRoutes(Location endLocation){
=======
	public List<Route> getRelevantRoutes(Location endLocation) {
>>>>>>> bbd35d03b92019bb66c8191ec371e831ace48c84
		List<Route> routes = entityManager.createQuery("Select route from Route route", Route.class).getResultList();
		List<Route> relevantRoutes = new ArrayList<Route>();
		for (Route r : routes) {
			if (endLocation.equals(r.getLocation().last())) {
				relevantRoutes.add(r);
			}
		}
		return relevantRoutes;
	}
}