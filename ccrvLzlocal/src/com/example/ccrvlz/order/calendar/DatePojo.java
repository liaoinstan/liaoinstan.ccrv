package com.example.ccrvlz.order.calendar;

import java.util.Date;
import java.util.List;

public class DatePojo{
	  Date date;
	  float price;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public DatePojo(Date date, float price) {
		super();
		this.date = date;
		this.price = price;
	}
	
	public static int isIn(Date date,List<DatePojo> datePojos) {
		for (int i = 0; i < datePojos.size(); i++) {
			DatePojo dp = datePojos.get(i);
			if (dp.getDate().compareTo(date) == 0) {
				return i;
			}
		}
		return -1;
	}
}
