package ca.mcgill.ecse321.ridesharing.controller;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.RouteRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/route")
public class RouteController {

	@Autowired
	RouteRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createRoute(@RequestParam("seats") String seats,
							  /*@RequestParam("car") int carId,*/
							  @RequestParam("date") String date,
							  @RequestParam("time") String time) {
		int seatsAvailable = Integer.parseInt(seats);
		Route result = repository.createRoute(seatsAvailable, /*carId,*/ date, time);
		if (result != null) {
			return "Route #" + result.getRouteId() + " created!";
		} else {
			return "Route could not be created.";
		}
	}
	
	@RequestMapping("/assignCar/{id}/{routeId}")
	@ResponseBody
	public String assignUserToCar(@PathVariable("id") String id, @PathVariable("routeId") String routeId) {
		int carId = Integer.parseInt(id);
		int routeIdInt = Integer.parseInt(routeId);
		boolean result = repository.assignCarToRoute(carId, routeIdInt);
		if(result) {
			return "car #" + carId + " assigned to route id # " + routeIdInt;
		}else {
			return "Could not assign car " +carId + " to route id: " + routeIdInt +". Please make sure the fields are correct.";
		}
	}
	
	
	@RequestMapping(value = "/getRoutes/{username}", method = RequestMethod.GET)
	@ResponseBody
	public List<Route> getRoutes(@PathVariable("username") String username){
		return repository.getRoutesForDriver(username);
	}
	
	@RequestMapping(value = "/getRoute/{id}", method = RequestMethod.GET)
	public Route getRoute(@PathVariable("id") int id) {
		return repository.getRoute(id);
	}
}
