package com.example.pojo;

import java.io.Serializable;


public class OrderPak implements Serializable{
	
	private String id;
	private String name;
	private float price;
	private int adultCount;
	private int childCount;
	private int count;	// £”‡ ˝¡ø
	public OrderPak() {
		super();
	}
	public OrderPak(String id, String name, float price, int adultCount, int childCount,int count) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.adultCount = adultCount;
		this.childCount = childCount;
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getAdultCount() {
		return adultCount;
	}
	public void setAdultCount(int adultCount) {
		this.adultCount = adultCount;
	}
	public int getChildCount() {
		return childCount;
	}
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	
}
