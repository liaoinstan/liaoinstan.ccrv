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
	private String code;//标示明细项目的标志
	private String name;//订单明细名称
	private Float value;//价值
	private int flag;//增或减
	public OrderItem() {
		super();
	}
	public OrderItem(String code, Float value) {
		super();
		this.code = code;
		this.value = value;
		
		if(CODE_BASEPRICE.equals(code)){
			this.name = "房车旅游产品金额";
			this.flag = 1;
		}else if(CODE_ALLPRICE.equals(code)){
			this.name = "应付金额";
			this.flag = 1;
		}else if(CODE_ADDSER.equals(code)){
			this.name = "增值服务";
			this.flag = 1;
		}else if(CODE_COUPON.equals(code)){
			this.name = "优惠券";
			this.flag = -1;
		}else if(CODE_CCBEAN.equals(code)){
			this.name = "CC豆";
			this.flag = -1;
		}else if(CODE_REFERRER.equals(code)){
			this.name = "推荐码";
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
