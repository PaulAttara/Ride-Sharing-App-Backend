package ca.mcgill.ecse321.ridesharing.controller;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.DTO.*;
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
	public String createUser(@RequestParam("username") String username,
								@RequestParam("password") String password,
								@RequestParam("firstname") String firstname,
								@RequestParam("lastname") String lastname,
								@RequestParam("phonenumber") String phonenumber,
								@RequestParam("city") String city,
								@RequestParam("address") String address,
								@RequestParam("role") String role) {
		User user = repository.createUser(username, password, firstname, lastname, phonenumber, city, address, role);
		if (user != null) {
			return username;
		} else {
			return "-1";
		}
	}
	
	@RequestMapping(value = "/addRating/{username}/{rating}/", method = RequestMethod.GET)
	public String addRating(@PathVariable("username") String username, @PathVariable("rating") String rating) {
		double newRating = Double.parseDouble(rating);
		double avgRating = repository.addToRatings(username, newRating);
		return rating + " was added. " + username + " has an average rating of " + avgRating;
	}
	
	@RequestMapping(value = "/login/{username}/{password}/", method = RequestMethod.GET)
	public boolean login(@PathVariable("username") String username, @PathVariable("password") String password) {
		boolean result = repository.login(username, password);
		if(result) {
			return true;
		}else {
			return false;
		}
	}
	
	@RequestMapping(value = "/getUser/{id}/", method = RequestMethod.GET)
	public UserDTO getId(@PathVariable("id") String id) {
		User user = repository.getUser(id);
		return new UserDTO(user.getRatings(),user.getUsername(),user.getPassword(),user.getFirstName(),
							user.getLastName(),user.getPhoneNumber(),user.getCity(),user.getAddress(),
							user.getRole(),user.getAvgRating(),user.getNumTrips());
	}
}