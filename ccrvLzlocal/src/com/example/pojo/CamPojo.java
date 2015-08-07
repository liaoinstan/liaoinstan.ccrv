package com.example.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.R.integer;

public class CamPojo implements Serializable{
	private integer typeId;
	private integer chanId;
	private integer rows;
	private integer start;
	private List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	private String code;
	public integer getTypeId() {
		return typeId;
	}
	public void setTypeId(integer typeId) {
		this.typeId = typeId;
	}
	public integer getChanId() {
		return chanId;
	}
	public void setChanId(integer chanId) {
		this.chanId = chanId;
	}
	public integer getRows() {
		return rows;
	}
	public void setRows(integer rows) {
		this.rows = rows;
	}
	public integer getStart() {
		return start;
	}
	public void setStart(integer start) {
		this.start = start;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "CamPojo [typeId=" + typeId + ", chanId=" + chanId + ", rows="
				+ rows + ", start=" + start + ", list=" + list + ", code="
				+ code + "]";
	}
	
	
	
}
