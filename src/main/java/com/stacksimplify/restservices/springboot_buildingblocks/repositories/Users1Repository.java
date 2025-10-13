package com.stacksimplify.restservices.springboot_buildingblocks.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;

@Repository
public interface Users1Repository extends JpaRepository<Users1,Long> {

	Users1 save(Optional<Users1> users1);
	Users1 findByUserName(String userName);

}
