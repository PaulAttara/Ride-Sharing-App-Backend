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
							  @RequestParam("car") int carId,
							  @RequestParam("date") String date,
							  @RequestParam("time") String time) {
		int seatsAvailable = Integer.parseInt(seats);
		Route result = repository.createRoute(seatsAvailable, carId, date, time);
		if (result != null) {
			return String.valueOf(result.getRouteId());
		} else {
			return "-1";
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
