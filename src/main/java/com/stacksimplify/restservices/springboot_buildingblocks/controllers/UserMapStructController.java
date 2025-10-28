package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springboot_buildingblocks.dto.UserMsDto;
import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.mappers.UserMapper;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.Users1Repository;

@RestController
@RequestMapping("/mapstruct/user")
public class UserMapStructController {

	@Autowired
	private Users1Repository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	@GetMapping("/getallusersdto")
	public List<UserMsDto> getallUserinDto()
	{
		return userMapper.usersToUserDtos(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable Long id)
	{
		Optional<Users1> users1 =userRepository.findById(id);
		Users1 users=users1.get();
		return userMapper.usertoUserMsDto(users);
	}
}
