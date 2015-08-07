package com.example.pojo;

import java.io.Serializable;


public class OrderTraveller  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -746802011915031981L;
	private String id;
	private String name;//旅客名称
	private String idCard;//身份证号
	public OrderTraveller() {
		super();
	}
	public OrderTraveller(String id, String name, String idCard) {
		super();
		this.id = id;
		this.name = name;
		this.idCard = idCard;
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}
