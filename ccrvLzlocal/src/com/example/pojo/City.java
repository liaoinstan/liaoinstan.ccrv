package com.example.pojo;

import java.io.Serializable;

public class City implements Serializable{
	private String id;
	private String name;
	private String spell;
	public City() {
		super();
	}
	public City(String id, String name, String spell) {
		super();
		this.id = id;
		this.name = name;
		this.spell = spell;
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
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	
}
