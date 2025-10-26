package com.stacksimplify.restservices.springboot_buildingblocks.exceptions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Order;
import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.OrderRepository;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.Users1Repository;

@RestController
public class OrderHateoasResourceController {

	@Autowired
	private Users1Repository users1Repository;
	@Autowired
	private OrderRepository orderRepository;
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
}
