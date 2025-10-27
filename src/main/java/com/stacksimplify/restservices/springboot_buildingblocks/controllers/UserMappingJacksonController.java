package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.services.UserServices;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/JacksonFilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
		UserServices userService;
		@GetMapping("/{id}")
		public MappingJacksonValue getUserById(@PathVariable("id")@Min(1)Long id)
		{
			try {
			Optional<Users1> users =  userService.getUserById(id);
			Users1 user = users.get();
			
			Set<String> fields = new HashSet<String>();
			fields.add("userid");
			fields.add("userName");
			fields.add("email");
			fields.add("ssn");
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
			}
			catch(UserNotFoundException ex)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
			}
		//	ConstraintViolationException
		}
		@GetMapping("/param/{id}")
		public MappingJacksonValue getUserById2(@PathVariable("id")@Min(1)Long id, @RequestParam Set<String> fields)
		{
			try {
			Optional<Users1> users =  userService.getUserById(id);
			Users1 user = users.get();
			
//			Set<String> fields = new HashSet<String>();
//			fields.add("userid");
//			fields.add("userName");
//			fields.add("email");
//			fields.add("ssn");
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
			}
			catch(UserNotFoundException ex)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
			}
		//	ConstraintViolationException
		}
}
