package com.example.pojo;

import java.io.Serializable;
import java.util.Date;


public class OrderCoupon  implements Serializable{
	private String id;
	private float price;//�Żݼ۵����۸�
	private float limit;//��Ҫ����ļ۸�
	private Date startDate;//��ʼ����
	private Date endDate;//��������
	private int status;//״̬��1���� 2������
	private int select;//�Ƿ�ѡ��;
	public OrderCoupon() {
		super();
	}
	public OrderCoupon(String id, float price, float limit, Date startDate,
			Date endDate, int status, int select) {
		super();
		this.id = id;
		this.price = price;
		this.limit = limit;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.select = select;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getLimit() {
		return limit;
	}
	public void setLimit(float limit) {
		this.limit = limit;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSelect() {
		return select;
	}
	public void setSelect(int select) {
		this.select = select;
	}
}
