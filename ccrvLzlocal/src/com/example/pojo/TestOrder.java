package com.example.pojo;

import java.io.Serializable;

public class TestOrder implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String price;
	private String city;
	private String startTime;
	public TestOrder() {
		super();
	}
	public TestOrder(String id, String name, String price, String city,
			String startTime) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.city = city;
		this.startTime = startTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}
