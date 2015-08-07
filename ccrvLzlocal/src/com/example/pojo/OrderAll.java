package com.example.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAll implements Serializable{
	
	private String id;
	private String goodsId;
	private int dayCount; //��Ʒ����
	private String goodsName;//��Ʒ��
	private String goodsPic;//��ƷͼƬ
	private float goodsPrice;//��Ʒ�۸�
	private float adultPrice;//���˼۸�
	private float childPrice;//��ͯ�۸�
	private OrderPak orderPak;//�ײ�
	
	private Date startDate;//��������
	private String stratCity;//��������
	
	private int adultCount;//������
	private int childCount;//��ͯ��
	
	private ArrayList<OrderAddSer> addSers = new ArrayList<OrderAddSer>();//��ֵ����
	
	private ArrayList<OrderContact> contacts = new ArrayList<OrderContact>();//��ϵ��
	
	private ArrayList<OrderTraveller> travellers = new ArrayList<OrderTraveller>();//�ÿ�
	
	private String note;
	
	private ArrayList<OrderCoupon> coupons = new ArrayList<OrderCoupon>();//�Ż�ȯ
	private int ccbean;//cc��
	private boolean userbean;//�Ƿ�ʹ��cc��
	private String referrerPhone;//�Ƽ����ֻ�
	private float referrerPrice;//�Ƽ��˿ɵ����۸�
	
//	private ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();//������ϸ

	public OrderAll() {
		super();
	}

	public OrderAll(String goodsId,int dayCount , String goodsName, String goodsPic,String stratCity,float adultPrice ,float childPrice,float price, Date startDate) {
		super();
		this.goodsId = goodsId;
		this.dayCount = dayCount;
		this.goodsName = goodsName;
		this.goodsPic = goodsPic;
		this.childPrice = childPrice;
		this.adultPrice = adultPrice;
		this.goodsPrice = price;
		this.stratCity = stratCity;
		this.startDate = startDate;
	}

//	public OrderItem getOrderItemByCode(String code) {
//		for (int i = 0; i < orderItems.size(); i++) {
//			if (orderItems.get(i).getCode().equals(code)) {
//				return orderItems.get(i);
//			}
//		}
//		OrderItem detail = new OrderItem(code, 0F);
//		int size = orderItems.size();
////		orderItems.add(size==0?0:(size==1?size:size-1),detail);	//���ܼ۷ŵ����·����㷨���ڶ���ʼ�շ�����������׶ˣ�
//		if (OrderItem.CODE_BASEPRICE.endsWith(code)) {	//����ǻ����۸񣬷�������ĵ�һ��
//			orderItems.add(0,detail);
//		}else if(OrderItem.CODE_ALLPRICE.endsWith(code)){	//�ܼ۷������
//			orderItems.add(size,detail);
//		}else {
//			orderItems.add(size==0?0:size-1,detail);	//����ķ��ڵ����ڶ�
//		}
//		return detail;
//	}
//	
//	public void setDetail(String code,float value) {
//		OrderItem item = getOrderItemByCode(code);
//		if (value == 0) {
//			orderItems.remove(item);
//		}else {
//			item.setValue(value);
//		}
//		
//		float total = 0;
//		for (OrderItem detail : orderItems) {
//			if (!detail.getCode().equals(OrderItem.CODE_ALLPRICE)) {
//				total += detail.getFlag()*detail.getValue();
//			}
//		}
//		getOrderItemByCode(OrderItem.CODE_ALLPRICE).setValue(total);
//	}
	
	public OrderPak getOrderPak() {
		return orderPak;
	}

	public void setOrderPak(OrderPak orderPak) {
		this.orderPak = orderPak;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public float getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(float adultPrice) {
		this.adultPrice = adultPrice;
	}

	public float getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(float childPrice) {
		this.childPrice = childPrice;
	}

	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public float getReferrerPrice() {
		return referrerPrice;
	}

	public void setReferrerPrice(float referrerPrice) {
		this.referrerPrice = referrerPrice;
	}

	public boolean isUserbean() {
		return userbean;
	}

	public void setUserbean(boolean userbean) {
		this.userbean = userbean;
	}

	public String getStratCity() {
		return stratCity;
	}

	public void setStratCity(String stratCity) {
		this.stratCity = stratCity;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsPic() {
		return goodsPic;
	}

	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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


	public ArrayList<OrderAddSer> getAddSers() {
		return addSers;
	}

	public void setAddSers(ArrayList<OrderAddSer> addSers) {
		this.addSers = addSers;
	}

	public ArrayList<OrderContact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<OrderContact> contacts) {
		this.contacts = contacts;
	}

	public ArrayList<OrderTraveller> getTravellers() {
		return travellers;
	}

	public void setTravellers(ArrayList<OrderTraveller> travellers) {
		this.travellers = travellers;
	}


	public ArrayList<OrderCoupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<OrderCoupon> coupons) {
		this.coupons = coupons;
	}

//	public ArrayList<OrderItem> getOrderItems() {
//		return orderItems;
//	}
//
//	public void setOrderItems(ArrayList<OrderItem> orderItems) {
//		this.orderItems = orderItems;
//	}

	public int getCcbean() {
		return ccbean;
	}

	public void setCcbean(int ccbean) {
		this.ccbean = ccbean;
	}

	public String getReferrerPhone() {
		return referrerPhone;
	}

	public void setReferrerPhone(String referrerPhone) {
		this.referrerPhone = referrerPhone;
	}

}
