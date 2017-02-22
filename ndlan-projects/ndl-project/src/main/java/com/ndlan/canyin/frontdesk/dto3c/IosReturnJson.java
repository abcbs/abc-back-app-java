package com.ndlan.canyin.frontdesk.dto3c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IosReturnJson {

	private String statusCode = "0000";
	private String message = "成功";
	private SearchPageDto  searchPage;
	

	private Object objectzJson;
	/*private List<?> objectzJson;
	private List<?> datalist1;
	private List<?> dataList2= new ArrayList();*/
	

	public IosReturnJson(String statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public IosReturnJson() {
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObjectzJson() {
		return objectzJson;
	}

	public void setObjectzJson(Object objectzJson) {
		this.objectzJson = objectzJson;
	}

	public SearchPageDto getSearchPage() {
		return searchPage;
	}

	public void setSearchPage(SearchPageDto searchPage) {
		this.searchPage = searchPage;
	}

	/*public List<?> getObjectzJson() {
		return objectzJson;
	}

	public void setObjectzJson(List<?> objectzJson) {
		this.objectzJson = objectzJson;
	}

	public List<?> getDatalist1() {
		return datalist1;
	}

	public void setDatalist1(List<?> datalist1) {
		this.datalist1 = datalist1;
	}

	public List<?> getDataList2() {
		return dataList2;
	}

	public void setDataList2(List<?> dataList2) {
		this.dataList2 = dataList2;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}*/
	
	
	
}
