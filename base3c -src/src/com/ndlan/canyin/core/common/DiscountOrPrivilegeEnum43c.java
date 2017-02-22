package com.ndlan.canyin.core.common;


public enum DiscountOrPrivilegeEnum43c {
	
	   DISCOUTN("0", "折扣"), 
	   PRIVILEGE("1", "优惠");
	   public static final String enumCode = "DiscountType";
	   public static final String enumName = "折扣类型";
	   private String code;
	   private String desc;
	 
	   private DiscountOrPrivilegeEnum43c(String code, String desc) { this.code = code;
	     this.desc = desc; }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	   
	   

}
