package com.example.pojo;

import java.io.Serializable;


public class OrderItem  implements Serializable{
	
	public final static String CODE_BASEPRICE = "CODE_BASEPRICE";
	public final static String CODE_ALLPRICE = "CODE_ALLPRICE";
	public final static String CODE_ADDSER = "CODE_ADDSER";
	public final static String CODE_COUPON = "CODE_COUPON";
	public final static String CODE_CCBEAN = "CODE_CCBEAN";
	public final static String CODE_REFERRER = "CODE_REFERRER";
	
	private String id;
	private String code;//��ʾ��ϸ��Ŀ�ı�־
	private String name;//������ϸ����
	private Float value;//��ֵ
	private int flag;//�����
	public OrderItem() {
		super();
	}
	public OrderItem(String code, Float value) {
		super();
		this.code = code;
		this.value = value;
		
		if(CODE_BASEPRICE.equals(code)){
			this.name = "�������β�Ʒ���";
			this.flag = 1;
		}else if(CODE_ALLPRICE.equals(code)){
			this.name = "Ӧ�����";
			this.flag = 1;
		}else if(CODE_ADDSER.equals(code)){
			this.name = "��ֵ����";
			this.flag = 1;
		}else if(CODE_COUPON.equals(code)){
			this.name = "�Ż�ȯ";
			this.flag = -1;
		}else if(CODE_CCBEAN.equals(code)){
			this.name = "CC��";
			this.flag = -1;
		}else if(CODE_REFERRER.equals(code)){
			this.name = "�Ƽ���";
			this.flag = -1;
		}
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
