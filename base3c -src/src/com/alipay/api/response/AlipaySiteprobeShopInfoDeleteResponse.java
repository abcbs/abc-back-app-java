package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.siteprobe.shop.info.delete response.
 * 
 * @author auto create
 * @since 1.0, 2014-12-18 09:04:30
 */
public class AlipaySiteprobeShopInfoDeleteResponse extends AlipayResponse {

	private static final long serialVersionUID = 8522378884973341316L;

	/** 
	 * 返回码
	 */
	@ApiField("code")
	private String code;

	/** 
	 * 返回码信息
	 */
	@ApiField("msg")
	private String msg;

	public void setCode(String code) {
		this.code = code;
	}
	public String getCode( ) {
		return this.code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg( ) {
		return this.msg;
	}

}
