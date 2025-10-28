package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springboot_buildingblocks.dto.UserMmDto;
import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.services.UserServices;

import jakarta.validation.constraints.Min;

@RequestMapping("/Modelmapper/users")
@RestController
public class UserModelMapperController {
	
	@Autowired
	private UserServices userService;
	
	@Autowired
	private ModelMapper modelMapper;
	@GetMapping("/{id}")
	public UserMmDto getUserById(@PathVariable("id")@Min(1)Long id) throws UserNotFoundException
	{
	
		Optional<Users1> user =  userService.getUserById(id);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User Not Found");
		}
		Users1 user1 = user.get();
		
		UserMmDto userDto = modelMapper.map(user1, UserMmDto.class);
		return userDto;
		
//		
//		catch(UserNotFoundException ex)
//		{
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
//		}
	//	ConstraintViolationException
	}

}
