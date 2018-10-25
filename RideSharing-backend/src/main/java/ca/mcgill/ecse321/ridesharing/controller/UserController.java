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
								@RequestParam("address") String address) {
		User result = repository.createUser(username, password, firstname, lastname, phonenumber, city, address);
		if (result != null) {
			return username + " created!";
		} else {
			return "User could not be created.";
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getVehicle(@PathVariable("id") int id) {
		return repository.getUser(id);
	}
}
