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
public void passengerDroppedOff (Route aRoute, double rating, Role aRole) {
	if (aRole instanceof Passenger) {
		Set <Request> requests = aRoute.getRequest();
		for(Request r: requests) {
			
			Passenger p =r.getPassenger();
			if (p ==aRole) {
				r.setStatus(Status.Ended);
				double currentRating=aRole.getAvgRating();
				double numPastTrips=(double)aRole.getNumOfPastTrips();
			
				currentRating= ((rating+currentRating*(numPastTrips))/(numPastTrips+1));
				aRole.setNumOfPastTrips((int)numPastTrips+1);
				aRole.setAvgRating(currentRating);
			}
		}
		
	}
}


//	@PostMapping("/participants/{name}")
//	public String createParticipant(@PathVariable("name") String userName) {
//		User user = repository.createUser(userName);
//		return user.getUserName();
//	}
//
//	@GetMapping("/participants/{name}")
//	public String queryParticipant(@PathVariable("name") String userName) {
//		User user = repository.getUser(userName);
//		if(user == null) {
//			return "NOT FOUND";
//		}
//		return user.getUserName();
//	}

}