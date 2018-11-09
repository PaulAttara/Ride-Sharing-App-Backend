package ca.mcgill.ecse321.ridesharing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.ridesharing.DTO.LocationDTO;
import ca.mcgill.ecse321.ridesharing.DTO.RouteDTO;
import ca.mcgill.ecse321.ridesharing.model.Location;
import ca.mcgill.ecse321.ridesharing.model.Route;
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
			LocationDTO locationDTO = new LocationDTO();
			locationDTO.setLocationId(location.getLocationId());
			return locationDTO.getLocationId()  + "";
		}else {
			return "-1";
		}
	}
	
	@RequestMapping(value="/addPassenger/{username}/{routeId}/{locationId}/", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/assignLocations/{locationId}/{routeId}/", method = RequestMethod.POST)
	@ResponseBody
	public String setLocationsToRoute(@PathVariable("locationId") int locationId, @PathVariable("routeId") int routeId) {
		boolean result = repository.assignToRoute(locationId,routeId);
		if(result) {
			return "Stops assigned.";
		}else {
			return "Could not assign stops";
		}
	}
	
	@RequestMapping(value = "/getRoutesForPass/{destination}/", method = RequestMethod.GET)
	@ResponseBody
	public List<RouteDTO> getRoutes(@PathVariable("destination") String destination){
		List<Route> routes = repository.getRoutesForPassenger(destination);
		List<RouteDTO> routeDTOs = new ArrayList<RouteDTO>();
		for(Route route : routes) {
			routeDTOs.add(new RouteDTO(route.getRouteId(),route.getSeatsAvailable(),route.getStartLocation(),route.getDate(),route.getCar(),route.getStops()));
		}
		return routeDTOs;
	}
	
	@RequestMapping(value = "/getDestination/{routeId}/", method = RequestMethod.GET)
	@ResponseBody
	public LocationDTO getFinalDest(@PathVariable("routeId") int routeId){
		List<Location> locations = repository.getStops(routeId);
		Location lastStop = null;
		for(Location thisStop: locations) {
			lastStop = thisStop;
		}
		LocationDTO locationDTO = new LocationDTO(lastStop.getLocationId(),lastStop.getCity(),lastStop.getStreet(),lastStop.getPassenger(),lastStop.getRoute(),lastStop.getPrice());
		return locationDTO;
	}
	
	@RequestMapping(value="/getLocationsForPassenger/{routeId}/", method = RequestMethod.GET)
	@ResponseBody
	public List<LocationDTO> getLocationsForPassenger(@PathVariable("routeId") int routeId){
		List<Location> locations =  repository.getStops(routeId);
		List<LocationDTO> locationDTOs = new ArrayList<LocationDTO>();
		for(Location location : locations) {
			locationDTOs.add(new LocationDTO(location.getLocationId(), location.getCity(), location.getStreet(), location.getPassenger(), location.getRoute(), location.getPrice()));
		}
		return locationDTOs;
	}
	
	@RequestMapping(value = "/getLocation/{id}/", method = RequestMethod.GET)
	public LocationDTO getLocation(@PathVariable("id") int id) {
		Location location = repository.getLocation(id);
		return new LocationDTO(location.getLocationId(), location.getCity(), location.getStreet(), location.getPassenger(), location.getRoute(), location.getPrice());
	}
}