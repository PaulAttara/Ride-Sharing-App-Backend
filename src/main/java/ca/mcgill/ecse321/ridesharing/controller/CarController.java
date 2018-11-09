package ca.mcgill.ecse321.ridesharing.controller;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.DTO.*;
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
			CarDTO carDTO= new CarDTO();
			carDTO.setVehicleId(result.getVehicleId());
			return String.valueOf(carDTO.getVehicleId());
		} else {
			return "-1";
		}
	}
	
	@RequestMapping("/assignCar/{username}/{id}/")
	@ResponseBody
	public String assignUserToCar(@PathVariable("username") String username, @PathVariable("id") String id) {
		int carId = Integer.parseInt(id);
		boolean result = repository.assignUserToCar(username, carId);
		if(result) {
			return username + " assigned to car id: " + id;
		}else {
			return "Could not assign " +username + " to car id: " + id +". Please make sure the fields are correct.";
		}
	}
	
	@RequestMapping(value = "/getIdFromUsername/{username}/", method = RequestMethod.GET)
	public int getVehicleByUser(@PathVariable("username") String username) {
		return repository.getByUsername(username);
	}

	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	public CarDTO getVehicle(@PathVariable("id") int id) {
		Car car = repository.getVehicle(id);
		CarDTO carDTO = new CarDTO(car.getVehicleId(),car.getBrand(),car.getModel(),car.getLicensePlate(),car.getDriver());
		return carDTO;
	}
}