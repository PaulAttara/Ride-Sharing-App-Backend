package ca.mcgill.ecse321.ridesharing.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.RideSharingRepository;

@RestController
public class RideSharingController {

	

	@Autowired
	RideSharingRepository repository;

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

	@PostMapping("/participants/{name}")
	public String createUser(@PathVariable("name") String userName) {
		User user = repository.createUser(userName);
		return user.getUserName();
	}

	@GetMapping("/participants/{name}")
	public String queryUser(@PathVariable("name") String userName) {
		User user = repository.getUser(userName);
		if(user == null) {
			return "NOT FOUND";
		}
		return user.getUserName();
	}
	

}