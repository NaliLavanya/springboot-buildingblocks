package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.services.UserServices;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value="/jacksonfilter/user")
@Validated
public class UserMappingJacksonController {

	@Autowired
	UserServices userService;
	@GetMapping("/{id}")
	public Optional<Users1> getUserById(@PathVariable("id")@Min(1)Long id)
	{
		try {
		return userService.getUserById(id);
		}
		catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	//	ConstraintViolationException
	}
}
