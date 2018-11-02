package ca.mcgill.ecse321.ridesharing.controller;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vehicle")
public class CarController {

	@Autowired
	CarRepository repository;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createVehicle(@RequestParam("brand") String brand, 
							 @RequestParam("model") String model, 
							 @RequestParam("plate") String plate) {
		Car result = repository.createVehicle(brand, model, plate);
		if (result != null) {
			return String.valueOf(result.getVehicleId());
		} else {
			return "-1";
		}
	}
	
	@RequestMapping("/assignCar/{username}/{id}/")
	@ResponseBody
	public String assignUserToCar(@PathVariable("username") String username, @PathVariable("id") String id) {
		System.out.println("in assignusertocar");
		int carId = Integer.parseInt(id);
		boolean result = repository.assignUserToCar(username, carId);
		if(result) {
			return username + " assigned to car id: " + id;
		}else {
			return "Could not assign " +username + " to car id: " + id +". Please make sure the fields are correct.";
		}
	}

	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	public Car getVehicle(@PathVariable("id") int id) {
		return repository.getVehicle(id);
	}
}