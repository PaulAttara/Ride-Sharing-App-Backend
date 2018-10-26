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
	public String addRating(@PathVariable("username") String username, @PathVariable("rating") String rating) {
		double newRating = Double.parseDouble(rating);
		double avgRating = repository.addToRatings(username, newRating);
		return rating + " was added. " + username + " has an average rating of " + avgRating;
	}
	
	@RequestMapping("/login/{username}/{password}")
	public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
		boolean result = repository.login(username, password);
		if(result) {
			return "Login successful";
		}else {
			return "Username and/or Password incorrect!";
		}
	}
	
	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public User getVehicle(@PathVariable("id") String id) {
		return repository.getUser(id);
	}
}
