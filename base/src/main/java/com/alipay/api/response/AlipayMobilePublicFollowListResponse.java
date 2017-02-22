package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.domain.Data;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.mobile.public.follow.list response.
 * 
 * @author auto create
 * @since 1.0, 2014-08-07 17:30:19
 */
public class AlipayMobilePublicFollowListResponse extends AlipayResponse {

	private static final long serialVersionUID = 4225657554749693731L;

	/** 
	 * 返回结果码，如200，标识成功
	 */
	@ApiField("code")
	private String code;

	/** 
	 * 当前组的值
	 */
	@ApiField("count")
	private String count;

	/** 
	 * 用户数据
	 */
	@ApiField("data")
	private Data data;

	/** 
	 * 查询分组的userid
	 */
	@ApiField("next_user_id")
	private String nextUserId;

	public void setCode(String code) {
		this.code = code;
	}
	public String getCode( ) {
		return this.code;
	}

	public void setCount(String count) {
		this.count = count;
	}
	public String getCount( ) {
		return this.count;
	}

	public void setData(Data data) {
		this.data = data;
	}
	public Data getData( ) {
		return this.data;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}
	public String getNextUserId( ) {
		return this.nextUserId;
	}

}
