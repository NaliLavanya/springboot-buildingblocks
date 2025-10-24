package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Order;
import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.OrderRepository;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.Users1Repository;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	Users1Repository users1Repository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@GetMapping("/orders/{userid}")
	public List<Order> getOrderByUserId(@PathVariable Long userid) throws UserNotFoundException
	{
		Optional<Users1> users1 = users1Repository.findById(userid);
		if(users1==null)
		{
			throw new UserNotFoundException("users is not found");
		}
		return users1.get().getOrders();
	}
	
	@PostMapping("/{userid}/order")
	public void createOrder(@PathVariable Long userid,@RequestBody Order order) throws UserNotFoundException
	{
		Optional<Users1> users1 = users1Repository.findById(userid);
		if(users1==null)
		{
			throw new UserNotFoundException("user not found");
		}
		Users1 users12 = users1.get();
		order.setUsers1(users12);
		orderRepository.save(order);
		
	}
	
	@GetMapping("/ordersid/{orderid}")
	public Optional<Order> getOrderById(@PathVariable Long orderid)
	{
		return orderRepository.findById(orderid);
	}
}
