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
							  @RequestParam("startLocation") String startLocation,
							  @RequestParam("time") String time) {
		int seatsAvailable = Integer.parseInt(seats);
		Route result = repository.createRoute(seatsAvailable, carId, date, startLocation, time);
		if (result != null) {
			return result.getRouteId() + "";
		} else {
			return "-1";
		}
	}
	
	@RequestMapping(value = "/remove/{id}/")
	@ResponseBody
	public boolean removeRoute(@PathVariable("id") int id) {
		boolean result = repository.removeRoute(id);
		if(result) {
			return true;
		}else {
			return false;
		}
	}
	
	@RequestMapping(value = "/update/{id}/")
	@ResponseBody
	public boolean updateRoute(@PathVariable("id") int id,
				   	 		   @RequestParam("date") String date,
							   @RequestParam("time") String time) {
		boolean result = repository.updateRoute(id, date, time);
		if(result) {
			return true;
		}else {
			return false;
		}
	}
	
	@RequestMapping(value = "/getRoutes/{username}/", method = RequestMethod.GET)
	@ResponseBody
	public List<Route> getRoutes(@PathVariable("username") String username){
		return repository.getRoutesForDriver(username);
	}
	
	@RequestMapping(value = "/getRoute/{id}/", method = RequestMethod.GET)
	public Route getRoute(@PathVariable("id") int id) {
		return repository.getRoute(id);
	}
	
	@RequestMapping(value = "/getStops/{id}/", method = RequestMethod.GET)
	@ResponseBody
	public List<Location> getStops(@PathVariable("id") int id) {
		return repository.getStops(id);
		
	}
}