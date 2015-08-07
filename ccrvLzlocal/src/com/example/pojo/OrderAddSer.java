package com.example.pojo;

import java.io.Serializable;


public class OrderAddSer implements Serializable{
	
	private String id;
	private String name;//增值服务名称
	private float price;//价格
	private String unit;//单位
	private int per;//1：单价每单 2：单价每天
	private String smallPic;//图片
	private int count;//已选数量
	private int max;//上限
	private int min;//下限
	public OrderAddSer() {
		super();
	}
	public OrderAddSer(String id, String name, float price, String unit, int per,
			String smallPic, int count, int min, int max) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.per = per;
		this.smallPic = smallPic;
		this.count = count;
		this.max = max;
		this.min = min;
	}
	public int getPer() {
		return per;
	}
	public void setPer(int per) {
		this.per = per;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
}
