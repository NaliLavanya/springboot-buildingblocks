package com.stacksimplify.restservices.springboot_buildingblocks.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Users1 {
	
	@Id
	@GeneratedValue
	private long id;
	@NotEmpty(message="Username is mandatory field .please provide username")
//	@Column(name="USER_NAME",length=50,unique=true)
	private String userName;
	@Size(min=3, message="minimum firstname length should be there")
	@Column(name="FIRST_NAME",length=50,nullable=false)
	private String firstName;
	@Column(name="LAST_NAME",length=50,nullable=false)
	private String lastName;
	@Column(name="EMAIL_ADDRESS",length=50,nullable = false)
	private String email;
	@Column(name="ROLE",length=50,nullable=false)
	private String role;
	@Column(name="SSN",length=50,nullable=false,unique=true)
	private String ssn;
	public Users1(long id, String userName, String firstName, String lastName, String email, String role, String ssn) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public Users1() {
		super();
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
	
}
