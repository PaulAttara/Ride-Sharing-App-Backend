package ca.mcgill.ecse321.ridesharing.controller;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.RouteRepository;
import ca.mcgill.ecse321.ridesharing.DTO.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
			RouteDTO routeDTO = new RouteDTO();
			routeDTO.setRouteId(result.getRouteId());
			return routeDTO.getRouteId() + "";
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
	
	@RequestMapping(value = "/getAllRoutes/", method = RequestMethod.GET)
	@ResponseBody
	public List<RouteDTO> getAllRoutes(){
		List<Route> allRoutesForDriver = repository.getAllRoutes();
		//		return routesForDriver.stream().map(r -> r.getRouteId()).collect(Collectors.toList());
		List<RouteDTO> routeDTOs = new ArrayList<RouteDTO>();
		for(Route route : allRoutesForDriver) {
			routeDTOs.add(new RouteDTO(route.getRouteId(), route.getSeatsAvailable(), route.getStartLocation(), route.getDate(), route.getCar(), route.getStops()));
		}
		return routeDTOs;
	}

	@RequestMapping(value = "/getRoutes/{username}/", method = RequestMethod.GET)
	@ResponseBody
	public List<RouteDTO> getRoutes(@PathVariable("username") String username){
		List<Route> routesForDriver = repository.getRoutesForDriver(username);
		//		return routesForDriver.stream().map(r -> r.getRouteId()).collect(Collectors.toList());
		List<RouteDTO> routeDTOs = new ArrayList<RouteDTO>();
		for(Route route : routesForDriver) {
			routeDTOs.add(new RouteDTO(route.getRouteId(), route.getSeatsAvailable(), route.getStartLocation(), route.getDate(), route.getCar(), route.getStops()));
		}
		return routeDTOs;
	}

	@RequestMapping(value = "/getRoute/{id}/", method = RequestMethod.GET)
	public RouteDTO getRoute(@PathVariable("id") int id) {
		Route route = repository.getRoute(id);
		RouteDTO routeDTO = new RouteDTO(route.getRouteId(), route.getSeatsAvailable(), route.getStartLocation(), route.getDate(), route.getCar(), route.getStops());
		return routeDTO;
	}

	@RequestMapping(value = "/getStops/{id}/", method = RequestMethod.GET)
	@ResponseBody
	public List<LocationDTO> getStops(@PathVariable("id") int id) {
		List<Location> locations = repository.getStops(id);
		List<LocationDTO> locationDTOs = new ArrayList<LocationDTO>();
		for(Location location : locations) {
			locationDTOs.add(new LocationDTO(location.getLocationId(), location.getCity(), location.getStreet(), location.getPassenger(), location.getRoute(), location.getPrice()));
		}
		return locationDTOs;
	}
}