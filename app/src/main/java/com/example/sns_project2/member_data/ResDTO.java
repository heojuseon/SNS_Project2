package com.example.sns_project2.member_data;

import java.util.HashMap;

public class ResDTO<T> {
	
	private String resCode;
	private String resMsg;
	
	private HashMap<String, T>  hashmap;

	
	
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public HashMap<String, T> getHashmap() {
		return hashmap;
	}

	public void setHashmap(HashMap<String, T> hashmap) {
		this.hashmap = hashmap;
	}
	
	
	
}
