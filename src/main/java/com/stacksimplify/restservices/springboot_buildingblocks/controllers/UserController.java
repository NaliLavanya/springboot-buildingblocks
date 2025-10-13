package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserExistsException;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.services.UserServices;

@RestController
public class UserController {

	@Autowired
	private UserServices userService;
	 
	
	@GetMapping("/users")
	public List<Users1> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/createuser")
	public ResponseEntity<Void> createUser(@RequestBody Users1 users1,UriComponentsBuilder builder)
	{
		try {
		 userService.createUser(users1);
		 HttpHeaders header = new HttpHeaders();
		 header.setLocation(builder.path("/createuser/{id}").buildAndExpand(users1.getId()).toUri());
		 return new ResponseEntity<Void>(header,HttpStatus.CREATED);
		}
		catch(UserExistsException ex)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@GetMapping("/users/{id}")
	public Optional<Users1> getUserById(@PathVariable("id")Long id)
	{
		try {
		return userService.getUserById(id);
		}
		catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	@PutMapping("/updateuser/{id}")
	public Users1 updateUserById(@PathVariable("id")Long id,@RequestBody Users1 users1) throws UserNotFoundException {
		//Optional<Users1> users1 = getUserById(id);
//	users1.setId(100);
//	users1.setFirstName("ABC");
//	users1.setLastName("XYZ");
//	users1.setEmail("abcdef@Gmail.com");
//	users1.setRole("Admin");
//	users1.setSsn("SSN010");
//	
		try {
			Optional<Users1> users12 = userService.getUserById(id);
			 //  Users1 user = users1.orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

			return userService.updateUser(users1);
		}catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage()+"to update");
		}
		
	}
	
	@DeleteMapping("/deleteuserbyid/{id}")
	public void deleteUserById(@PathVariable("id") Long id)
	{
		try {
		userService.deleteUserById(id);
		}
		catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@GetMapping("/getuserbyname/{username}")
	public Users1 findByUserName(@PathVariable("username")String userName)
	{
		return userService.findByUserName(userName);
	}
}
