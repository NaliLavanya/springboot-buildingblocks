package com.stacksimplify.restservices.springboot_buildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.Users1Repository;

@Service
public class UserServices {

	@Autowired
	Users1Repository users1Repository;
	
	public List<Users1> getAllUsers(){
		return users1Repository.findAll();
	}
	
	public Users1 createUser(Users1 users1)
	{
		return users1Repository.save(users1);
	}

	public Optional<Users1> getUserById(Long id) {
		// TODO Auto-generated method stub
		return users1Repository.findById(id);
	}

	public Users1 updateUser(Users1 users1) {
		// TODO Auto-generated method stub
		return users1Repository.save(users1);
	}
	public void deleteUserById(Long id)
	{
		if(users1Repository.findById(id).isPresent())
		{
			users1Repository.deleteById(id);
		}
	}
	public Users1 findByUserName(String userName)
	{
		return users1Repository.findByUserName(userName);
	}
}
