package com.example.pojo;

import java.io.Serializable;


public class RecomTripPojo implements Serializable{
	
	private String code;
	private String t_id;
	private String cus_id;
	private String t_title;
	private String t_intro;
	private String t_share;
	private String t_praise;
	private String t_recom;
	private String t_cover_image;
	private String t_status;
	
	private String update_time;
	private String m_update_time;
	private String usernick;
	private String cus_image;
	private String place;
	private String bearing;
	private String comments;
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getT_id() {
		return t_id;
	}


	public void setT_id(String t_id) {
		this.t_id = t_id;
	}


	public String getCus_id() {
		return cus_id;
	}


	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}


	public String getT_title() {
		return t_title;
	}


	public void setT_title(String t_title) {
		this.t_title = t_title;
	}


	public String getT_intro() {
		return t_intro;
	}


	public void setT_intro(String t_intro) {
		this.t_intro = t_intro;
	}


	public String getT_share() {
		return t_share;
	}


	public void setT_share(String t_share) {
		this.t_share = t_share;
	}


	public String getT_praise() {
		return t_praise;
	}


	public void setT_praise(String t_praise) {
		this.t_praise = t_praise;
	}


	public String getT_recom() {
		return t_recom;
	}


	public void setT_recom(String t_recom) {
		this.t_recom = t_recom;
	}


	public String getT_cover_image() {
		return t_cover_image;
	}


	public void setT_cover_image(String t_cover_image) {
		this.t_cover_image = t_cover_image;
	}


	public String getT_status() {
		return t_status;
	}


	public void setT_status(String t_status) {
		this.t_status = t_status;
	}


	public String getUpdate_time() {
		return update_time;
	}


	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}


	public String getM_update_time() {
		return m_update_time;
	}


	public void setM_update_time(String m_update_time) {
		this.m_update_time = m_update_time;
	}


	public String getUsernick() {
		return usernick;
	}


	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}


	public String getCus_image() {
		return cus_image;
	}


	public void setCus_image(String cus_image) {
		this.cus_image = cus_image;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getBearing() {
		return bearing;
	}


	public void setBearing(String bearing) {
		this.bearing = bearing;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "RecomTripPojo [code=" + code + ", t_id=" + t_id + ", cus_id="
				+ cus_id + ", t_title=" + t_title + ", t_intro=" + t_intro
				+ ", t_share=" + t_share + ", t_praise=" + t_praise
				+ ", t_recom=" + t_recom + ", t_cover_image=" + t_cover_image
				+ ", t_status=" + t_status + ", update_time=" + update_time
				+ ", m_update_time=" + m_update_time + ", usernick=" + usernick
				+ ", cus_image=" + cus_image + ", place=" + place
				+ ", bearing=" + bearing + ", comments=" + comments + "]";
	}
	
	
}
