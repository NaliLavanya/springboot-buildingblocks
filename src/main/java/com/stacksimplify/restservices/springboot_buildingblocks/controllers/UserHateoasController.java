package com.stacksimplify.restservices.springboot_buildingblocks.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.Users1Repository;
import com.stacksimplify.restservices.springboot_buildingblocks.services.UserServices;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/hateos/users")
@Validated
public class UserHateoasController {

	@Autowired
	private Users1Repository users1Repository;
	@Autowired
	private UserServices userService;


	//public List<Users1> getAllUsers()

	 @GetMapping
	    public CollectionModel<EntityModel<Users1>> getAllUsers() {
	        List<Users1> usersList = userService.getAllUsers();

	        List<EntityModel<Users1>> userModels = usersList.stream()
	                .map(user -> {
	                    EntityModel<Users1> model = EntityModel.of(user);

	                    // Self link (to this user)
	                    Link selfLink = linkTo(methodOn(UserHateoasController.class)
	                            .getUserById(user.getUserid()))
	                            .withSelfRel();
	                    model.add(selfLink);

	                    // Relational link (to all users)
	                    Link allUsersLink = linkTo(methodOn(UserHateoasController.class)
	                            .getAllUsers())
	                            .withRel("all-users");
	                    model.add(allUsersLink);

	                    // ✅ Relational link (to this user's orders)
	                    Link userOrdersLink =null;
						try {
							userOrdersLink = linkTo(methodOn(OrderHateosController.class)
							        .getAllOrders(user.getUserid()))
							        .withRel("user-orders");
						} catch (UserNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    model.add(userOrdersLink);

	                    return model;
	                })
	                .collect(Collectors.toList());

	        // Add top-level self link
	        Link selfLink = linkTo(methodOn(UserHateoasController.class).getAllUsers()).withSelfRel();
	        return CollectionModel.of(userModels, selfLink);
	    }
//	@GetMapping("/{id}")
//	public EntityModel<Users1> getUserById(@PathVariable("id")@Min(1)Long id)
//	{
//		try {
//			Optional<Users1> users1 = userService.getUserById(id);
//			Users1 users12=users1.get();
//			Long userId = users12.getUserid();
//			EntityModel<Users1> resource = EntityModel.of(users12);
//
//			// Add self link
//			Link selfLink = linkTo(methodOn(this.getClass()).getUserById(userid)).withSelfRel();
//			resource.add(selfLink);
//			//users12.add(selflink);
//		//	Resource<Users1> finalResource = new Resource<Users1>(users12);
//			//return userService.getUserById(id);
//			return resource;
//		}
//		catch(UserNotFoundException ex)
//		{
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
//		}
//	//	ConstraintViolationException
//	}

	@GetMapping("/userbyid/{id}")
	public EntityModel<Users1> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<Users1> optionalUser = userService.getUserById(id);
			if (optionalUser.isEmpty()) {
				throw new UserNotFoundException("User not found with id " + id);
			}

			Users1 user = optionalUser.get();

			// Wrap user in EntityModel for HATEOAS
			EntityModel<Users1> resource = EntityModel.of(user);

			// ✅ Add self link
			Link selfLink = linkTo(methodOn(UserHateoasController.class).getAllUsers()).withRel("all-users");

			resource.add(selfLink);

			// ✅ Add link to all users
//	            Link allUsersLink = linkTo(methodOn(UserHateoasController.class).getAllUsers()).withRel("all-users");
//	            resource.add(allUsersLink);

			return resource;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
}
