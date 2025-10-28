package com.stacksimplify.restservices.springboot_buildingblocks.dto;

public class UserMsDto {

	private long userid;
	private String userName;
	private String email;
	public UserMsDto() {
		super();
	}
	public UserMsDto(long userid, String userName, String email) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.email = email;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserId(long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
