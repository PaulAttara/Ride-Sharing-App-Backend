package ca.mcgill.ecse321.ridesharing.controller;

import ca.mcgill.ecse321.ridesharing.model.*;
import ca.mcgill.ecse321.ridesharing.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello() {
		return "Hello World";
	}

}
