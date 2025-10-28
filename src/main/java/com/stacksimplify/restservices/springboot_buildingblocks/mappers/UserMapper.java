package com.stacksimplify.restservices.springboot_buildingblocks.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.stacksimplify.restservices.springboot_buildingblocks.dto.UserMsDto;
import com.stacksimplify.restservices.springboot_buildingblocks.entities.Users1;

@Mapper(componentModel = "spring")
public interface UserMapper {
	// serMapper INSTANCE =Mappers.getMapper(UserMapper.class);
//	@Mapping(source = "email", target = "emailAddress")
	UserMsDto usertoUserMsDto(Users1 users1);

//	@Mapping(source = "email", target = "emailAddress")
	List<UserMsDto> usersToUserDtos(List<Users1> users1);

}
