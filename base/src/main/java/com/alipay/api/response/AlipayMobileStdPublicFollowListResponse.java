package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.domain.Data;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.mobile.std.public.follow.list response.
 * 
 * @author auto create
 * @since 1.0, 2015-05-06 22:18:03
 */
public class AlipayMobileStdPublicFollowListResponse extends AlipayResponse {

	private static final long serialVersionUID = 7143347797517513864L;

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
