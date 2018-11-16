package ca.mcgill.ecse321.ridesharing.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class MainController {

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello() {
		return "Welcome to Share Fare!";
	}

}