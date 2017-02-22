package com.ndlan.canyin.frontdesk.dto3c.memberinfo;

import java.io.Serializable;

public class MemberCardClassDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mcclassId;
	private String name;
	public String getMcclassId() {
		return mcclassId;
	}
	public void setMcclassId(String mcclassId) {
		this.mcclassId = mcclassId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
