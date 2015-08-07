package com.example.pojo;

import java.io.Serializable;


public class OrderContact  implements Serializable{
	private String id;
	private String name;//联系人名称
	private String phone;//手机
	private String note;//留言
	public OrderContact() {
		super();
	}
	public OrderContact(String id, String name, String phone, String note) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.note = note;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
