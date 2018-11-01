package ca.mcgill.ecse321.ridesharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.ridesharing.model.Location;
import ca.mcgill.ecse321.ridesharing.repository.InvalidInputException;
import ca.mcgill.ecse321.ridesharing.repository.LocationRepository;

@RestController
@RequestMapping("api/location")
public class LocationController {
	
	@Autowired
	LocationRepository repository;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createLocation(@RequestParam("city") String city,
								 @RequestParam("street") String street,
								 @RequestParam("price") double price,
								 @RequestParam("routeId") int routeId) {
		Location location = repository.createLocation(city,street,price,routeId);
		if(location != null) {
			return "Location #" + location.getLocationId() + " created.";
		}else {
			return "Could not create location";
		}
	}
	
	@RequestMapping(value="/addPassenger/{username}/{routeId}/{locationId}", method = RequestMethod.POST)
	@ResponseBody
	public String registerUserToLocation(@PathVariable("username") String username, 
										 @PathVariable("routeId") int routeId,
										 @PathVariable("locationId") int locationId){
		boolean result = repository.addPassenger(username, routeId, locationId);
		if(result) {
			return username + " added to location #" + locationId + " on route #" + routeId; 
		}else {
			return "could not register user to location";
		}
	}
	
	@RequestMapping(value="/assignLocations/{locationId}/{routeId}", method = RequestMethod.POST)
	@ResponseBody
	public String setLocationsToRoute(@PathVariable("locationId") int locationId, @PathVariable("routeId") int routeId) {
		boolean result = repository.assignToRoute(locationId,routeId);
		if(result) {
			return "Stops assigned.";
		}else {
			return "Could not assign stops";
		}
	}
	
	@RequestMapping(value="/getLocationsForPassenger/{routeId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Location> getLocationsForPassenger(@PathVariable("routeId") int routeId){
		return repository.getStops(routeId);
	}
	
	@RequestMapping(value = "/getLocation/{id}", method = RequestMethod.GET)
	public Location getLocation(@PathVariable("id") int id) {
		return repository.getLocation(id);
	}
}
