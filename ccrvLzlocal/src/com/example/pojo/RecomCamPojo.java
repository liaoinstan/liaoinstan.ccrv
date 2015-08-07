package com.example.pojo;

import java.io.Serializable;


public class RecomCamPojo implements Serializable{
	
	private String addr;
	private String season;
	private String code;
	private String camp_id;
	private String camp_name;
	private String camp_type;
	private String camp_addr;
	private String camp_address;
	private String camp_xy;
	private String comp_img;
	private String comp_good_num;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCamp_id() {
		return camp_id;
	}
	public void setCamp_id(String camp_id) {
		this.camp_id = camp_id;
	}
	public String getCamp_name() {
		return camp_name;
	}
	public void setCamp_name(String camp_name) {
		this.camp_name = camp_name;
	}
	public String getCamp_type() {
		return camp_type;
	}
	public void setCamp_type(String camp_type) {
		this.camp_type = camp_type;
	}
	public String getCamp_addr() {
		return camp_addr;
	}
	public void setCamp_addr(String camp_addr) {
		this.camp_addr = camp_addr;
	}
	public String getCamp_address() {
		return camp_address;
	}
	public void setCamp_address(String camp_address) {
		this.camp_address = camp_address;
	}
	public String getCamp_xy() {
		return camp_xy;
	}
	public void setCamp_xy(String camp_xy) {
		this.camp_xy = camp_xy;
	}
	public String getComp_img() {
		return comp_img;
	}
	public void setComp_img(String comp_img) {
		this.comp_img = comp_img;
	}
	public String getComp_good_num() {
		return comp_good_num;
	}
	public void setComp_good_num(String comp_good_num) {
		this.comp_good_num = comp_good_num;
	}
	@Override
	public String toString() {
		return "RecomCamPojo [addr=" + addr + ", season=" + season + ", code="
				+ code + ", camp_id=" + camp_id + ", camp_name=" + camp_name
				+ ", camp_type=" + camp_type + ", camp_addr=" + camp_addr
				+ ", camp_address=" + camp_address + ", camp_xy=" + camp_xy
				+ ", camp_img=" + comp_img + ", camp_good_num=" + comp_good_num
				+ "]";
	}
	
	
}
