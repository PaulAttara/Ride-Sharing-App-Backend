package ca.mcgill.ecse321.ridesharing.controller;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	UserRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createVehicle(@RequestParam("username") String username,
								@RequestParam("password") String password,
								@RequestParam("firstname") String firstname,
								@RequestParam("lastname") String lastname,
								@RequestParam("phonenumber") String phonenumber,
								@RequestParam("city") String city,
								@RequestParam("address") String address,
								@RequestParam("role") String role) {
		User result = repository.createUser(username, password, firstname, lastname, phonenumber, city, address, role);
		if (result != null) {
			return username + " created!";
		} else {
			return "User could not be created.";
		}
	}
	
	@RequestMapping("/addRating/{username}/{rating}")
	public String addRating(@PathVariable("username") String username, @PathVariable("rating") double rating) {
		double avgRating = repository.addToRatings(username, rating);
		return rating + "was added. " + username + " has an average rating of " + avgRating;
	}
	
	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public User getVehicle(@PathVariable("id") String id) {
		return repository.getUser(id);
	}
}
