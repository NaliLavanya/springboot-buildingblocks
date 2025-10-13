package com.stacksimplify.restservices.springboot_buildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserExistsException;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.Users1Repository;

@Service
public class UserServices {

	@Autowired
	Users1Repository users1Repository;
	
	public List<Users1> getAllUsers(){
		return users1Repository.findAll();
	}
	
	public Users1 createUser(Users1 users1) throws UserExistsException
	{
		Users1 user = users1Repository.findByUserName(users1.getUserName());
		if(user!=null)
		{
			throw new UserExistsException("user already Exists");
		}
		
		return users1Repository.save(users1);
	}

	public Optional<Users1> getUserById(Long id) throws UserNotFoundException{
		// TODO Auto-generated method stub
		if(!users1Repository.findById(id).isPresent())
		{
			throw new UserNotFoundException("User Not Found Exception in repository");
		}
		return users1Repository.findById(id);
	}

	public Users1 updateUser(Users1 users1) {
		// TODO Auto-generated method stub
		return users1Repository.save(users1);
	}
	public void deleteUserById(Long id) throws UserNotFoundException
	{
		if(users1Repository.findById(id).isPresent())
		{
			users1Repository.deleteById(id);
		}
		else
		{
			throw new UserNotFoundException("user with id"+id+"not found");
		}
	}
	public Users1 findByUserName(String userName)
	{
		return users1Repository.findByUserName(userName);
	}
}
