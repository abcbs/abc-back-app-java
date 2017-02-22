package com.ndlan.canyin.frontdesk.dto3c;

public enum SubCode {
	 CHECK_ERROR("1001", "校验错误"), LOGIC_ERROR("1002", "逻辑错误"), ROLE_ERROR("1003",
			"权限错误"), ERROR("1004", "异常错误"), UNLOGIN("1005", "未登录"), YANZHENGSUCCESS("0001", "验证成功"), CONNECT_ERROR("1006",
					"网络异常"), NODATA("1007", "没有数据"),DATAERROR("1008","参数错误"),DATAISNULL("1009","参数为空"),UPDATEFAILE("1010","更新失败"),DELETEFAILE("1011","删除失败"),ISEXIST("1012","参数已存在");

	public static final String enumCode = "SubCode";
	public static final String enumName = "状态编码";
	private String code;
	private String desc;

	private SubCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(String code) {
		for (SubCode status : values()) {
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


