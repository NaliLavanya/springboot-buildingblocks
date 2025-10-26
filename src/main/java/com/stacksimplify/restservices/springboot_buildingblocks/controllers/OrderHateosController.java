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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Order;
import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.OrderRepository;
import com.stacksimplify.restservices.springboot_buildingblocks.repositories.Users1Repository;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateosController {

    @Autowired
    private Users1Repository users1Repository;

    @Autowired
    private OrderRepository orderRepository;

    // ✅ Get all orders for a specific user
    @GetMapping("/{userid}/orders")
    public CollectionModel<EntityModel<Order>> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

        Optional<Users1> userOptional = users1Repository.findById(userid);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with id " + userid);
        }

        Users1 user = userOptional.get();
        List<Order> orders = user.getOrders(); // assuming @OneToMany relationship exists

        List<EntityModel<Order>> orderModels = orders.stream()
                .map(order -> {
                    EntityModel<Order> orderModel = EntityModel.of(order);

                    // Self link (order belongs to this user)
                    Link selfLink =null;
					try {
						selfLink = linkTo(methodOn(OrderHateosController.class)
						        .getAllOrders(userid))
						        .withSelfRel();
					} catch (UserNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    orderModel.add(selfLink);

                    // ✅ Relational link back to the user
                    Link userLink = linkTo(methodOn(UserHateoasController.class)
                            .getUserById(userid))
                            .withRel("user-details");
                    orderModel.add(userLink);

                    return orderModel;
                })
                .collect(Collectors.toList());

        // ✅ Add top-level link to user’s orders
        Link selfLink = linkTo(methodOn(OrderHateosController.class).getAllOrders(userid)).withSelfRel();
        return CollectionModel.of(orderModels, selfLink);
    }
}
