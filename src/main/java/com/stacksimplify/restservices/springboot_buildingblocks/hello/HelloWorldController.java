package com.stacksimplify.restservices.springboot_buildingblocks.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@GetMapping("/helloworld1")
	@RequestMapping(method = RequestMethod.GET, path = "/helloworld1")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails getUserDetails()
	{
		return new UserDetails("Lavanya","Nali","Hyderabad");
	}

}
