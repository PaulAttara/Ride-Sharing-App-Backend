package ca.mcgill.ecse321.ridesharing.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.RideSharingRepository;

@RestController
public class RideSharingController {

	

	@Autowired
	RideSharingRepository repository;

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}
	// this method updates the seats available in a route depending on the status of the route
	public void updateSeatsAvail (Route aRoute) {
	int seatsAvail= aRoute.getSeatsAvailable();
	Set <Request> requests = aRoute.getRequest();
	for(Request r: requests) {
		Status s = r.getStatus();
		if (s==s.Accepted) {
			seatsAvail--;
		}
		else if (s==s.Ended) {
			seatsAvail++;
		}
		
	}
	aRoute.setSeatsAvailable(seatsAvail);
}
// this method should be called when a passenger is dropped off
// this gives the driver the chance to rate the passenger
// this also increments the num of past trips for the passenger and updates his rating
// this also updates the status of the specific request of the passenger to ended
public void passengerDroppedOff (Route aRoute, double rating, Passenger aPassenger) {
	
		Set <Request> requests = aRoute.getRequest();
		for(Request r: requests) {
			
			Passenger p =r.getPassenger();
			if (p ==aPassenger) {
				r.setStatus(Status.Ended);
				double currentRating=aPassenger.getAvgRating();
				double numPastTrips=(double)aPassenger.getNumOfPastTrips();
			
				currentRating= ((rating+currentRating*(numPastTrips))/(numPastTrips+1));
				aPassenger.setNumOfPastTrips((int)numPastTrips+1);
				aPassenger.setAvgRating(currentRating);
			}
		
		
	}
}
//A passenger will call this when he's dropped off and status of request is ended
//the route only has one driver so it is not necessary to take in a role
// this updates the drivers rating and the number of trips
// this should only be called when the passengerDroppedOff method has been called
// the status MUST BE = ENDED
public void passengerRatesDriver (double rating, Route aRoute) {
	
		Driver driver = aRoute.getDriver();
		// update rating and past trips
		double currentRating=driver.getAvgRating();
		double numPastTrips=(double)driver.getNumOfPastTrips();
		currentRating= ((rating+currentRating*(numPastTrips))/(numPastTrips+1));
		
		driver.setNumOfPastTrips((int)numPastTrips+1);
		driver.setAvgRating(currentRating);
	
}


	@PostMapping("/participants/{name}")
	public String createUser(@PathVariable("name") String userName) {
		User user = repository.createUser(userName);
		return user.getUserName();
	}

	/*@GetMapping("/participants/{name}")
	public String queryUser(@PathVariable("name") String userName) {
		User user = repository.getUser(userName);
		if(user == null) {
			return "NOT FOUND";
		}
		return user.getUserName();
	}
	*/

}