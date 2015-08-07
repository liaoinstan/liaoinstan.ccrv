package com.example.pojo;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *这个实体对象仅用于数据加载测试，所有的字段都没有任何意义，看到这些变态的构造方法，我一下就萎了
 */
public class TestPojo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String detail;
	private String addr;
	private String addr2;
	private String date;
	private String date2;
	private String price;
	private String price2;
	private int count;
	private int count2;
	private int num;
	private String bigImg;
	private String midImg;
	private String smlImg;
	private String flag;
	private String like;
	private String star;
	private String status;
	private String unit;
	
	public TestPojo() {
		super();
	}
	public TestPojo(String id, String title, String addr, String price,
			String price2, int num, String like) {
		super();
		this.id = id;
		this.title = title;
		this.addr = addr;
		this.price = price;
		this.price2 = price2;
		this.num = num;
		this.like = like;
	}
	public TestPojo(String id, String title, String detail) {
		super();
		this.id = id;
		this.title = title;
		this.detail = detail;
	}
	
	public TestPojo(String id, String title, String detail, String status,int add) {
		super();
		this.id = id;
		this.title = title;
		this.detail = detail;
		this.status = status;
	}
	public TestPojo(String id, String date, String date2, String price,
			String price2, String status) {
		super();
		this.id = id;
		this.date = date;
		this.date2 = date2;
		this.price = price;
		this.price2 = price2;
		this.status = status;
	}
	public TestPojo(String id, String title, String price, int num ,int count2) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.num = num;
		this.count2 = count2;
	}
	public TestPojo(String id, String title, String price, int count, int count2,
			String bigImg, String unit) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.count = count;
		this.count2 = count2;
		this.bigImg = bigImg;
		this.unit = unit;
	}
	public TestPojo(String id, String title, String price, String status) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.status = status;
	}
	public TestPojo(String id, String title, String addr, String addr2,
			String date, String price, String price2, int num, String bigImg,
			String flag, String like, String star) {
		super();
		this.id = id;
		this.title = title;
		this.addr = addr;
		this.addr2 = addr2;
		this.date = date;
		this.price = price;
		this.price2 = price2;
		this.num = num;
		this.bigImg = bigImg;
		this.flag = flag;
		this.like = like;
		this.star = star;
	}
	public TestPojo(String id, String title, String detail, String addr,
			String addr2, String date, String date2, String price,
			String price2, int count, int num, String bigImg, String midImg,
			String smlImg, String flag, String like, String star) {
		super();
		this.id = id;
		this.title = title;
		this.detail = detail;
		this.addr = addr;
		this.addr2 = addr2;
		this.date = date;
		this.date2 = date2;
		this.price = price;
		this.price2 = price2;
		this.count = count;
		this.num = num;
		this.bigImg = bigImg;
		this.midImg = midImg;
		this.smlImg = smlImg;
		this.flag = flag;
		this.like = like;
		this.star = star;
	}
	public int getCount2() {
		return count2;
	}
	public void setCount2(int count2) {
		this.count2 = count2;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getPrice2() {
		return price2;
	}
	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBigImg() {
		return bigImg;
	}
	public void setBigImg(String bigImg) {
		this.bigImg = bigImg;
	}
	public String getMidImg() {
		return midImg;
	}
	public void setMidImg(String midImg) {
		this.midImg = midImg;
	}
	public String getSmlImg() {
		return smlImg;
	}
	public void setSmlImg(String smlImg) {
		this.smlImg = smlImg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	@Override
	public String toString() {
		return "TestPojo [id=" + id + ", title=" + title + ", detail=" + detail
				+ ", addr=" + addr + ", date=" + date + ", price=" + price
				+ ", count=" + count + ", num=" + num + ", bigImg=" + bigImg
				+ ", midImg=" + midImg + ", smlImg=" + smlImg + ", flag="
				+ flag + ", like=" + like + ", star=" + star + "]";
	}
}
