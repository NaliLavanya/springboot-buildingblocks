package com.stacksimplify.restservices.springboot_buildingblocks.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue
	private int orderid;
	private String orderDescription;
	@ManyToOne(fetch=FetchType.LAZY)
@JsonIgnore
	private Users1 users1;
	
	public Order() {
		super();
	}
	public Order(int orderid, String orderDescription, Users1 users1) {
		super();
		this.orderid = orderid;
		this.orderDescription = orderDescription;
		this.users1 = users1;
	}
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public Users1 getUsers1() {
		return users1;
	}

	public void setUsers1(Users1 users1) {
		this.users1 = users1;
	}

}
