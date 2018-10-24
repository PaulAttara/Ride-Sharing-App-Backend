package ca.mcgill.ecse321.ridesharing.controller;

import ca.mcgill.ecse321.ridesharing.*;
import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vehicle")
public class CarController {

	@Autowired
	CarRepository repository;

	@RequestMapping("/")
	public String greeting () 
	{
		return "Hello world!";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createVehicle(@RequestParam("brand") String brand, @RequestParam("model") String model, @RequestParam("plate") String plate) {
		Car result = repository.createVehicle(brand, model, plate);
		if (result != null) {
			return model + " created!";
		} else {
			return "Vehicle could not be created.";
		}
	}

	@RequestMapping(value = "/vehicles/{id}", method = RequestMethod.GET)
	public Car getVehicle(@PathVariable("id") int id) {
		return repository.getVehicle(id);
	}
}
