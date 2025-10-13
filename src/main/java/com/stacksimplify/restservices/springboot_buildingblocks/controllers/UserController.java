package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
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
	public Users1 createUser(@RequestBody Users1 users1)
	{
		return userService.createUser(users1);
	}
	
	@GetMapping("/users/{id}")
	public Optional<Users1> getUserById(@PathVariable("id")Long id)
	{
		return userService.getUserById(id);
	}
	@PutMapping("/updateuser/{id}")
	public Users1 updateUserById(@PathVariable("id")Long id,@RequestBody Users1 users1)
	{
		//Optional<Users1> users1 = getUserById(id);
//	users1.setId(100);
//	users1.setFirstName("ABC");
//	users1.setLastName("XYZ");
//	users1.setEmail("abcdef@Gmail.com");
//	users1.setRole("Admin");
//	users1.setSsn("SSN010");
//	
		return userService.updateUser(users1);
		
	}
	
	@DeleteMapping("/deleteuserbyid/{id}")
	public void deleteUserById(@PathVariable("id") Long id)
	{
		userService.deleteUserById(id);
	}
	
	@GetMapping("/getuserbyname/{username}")
	public Users1 findByUserName(@PathVariable("username")String userName)
	{
		return userService.findByUserName(userName);
	}
}
