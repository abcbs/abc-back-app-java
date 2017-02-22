package com.ndlan.canyin.frontdesk.dto3c;

public enum Code {
	
	SUCCESS("0000", "成功"), FAILE("1000","失败");

	public static final String enumCode = "Code";
	public static final String enumName = "状态编码";
	private String code;
	private String desc;

	private Code(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(String code) {
		for (Code status : values()) {
			if (status.getCode().equalsIgnoreCase(code)) {
				return status.getDesc();
			}
		}
		return "未知";
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}


