package com.alipay.api.response;

import java.util.List;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import com.alipay.api.domain.PublicBindAccount;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.mobile.public.account.query response.
 * 
 * @author auto create
 * @since 1.0, 2015-02-03 14:09:54
 */
public class AlipayMobilePublicAccountQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 6184113684536994281L;

	/** 
	 * 结果码
	 */
	@ApiField("code")
	private String code;

	/** 
	 * 结果描述
	 */
	@ApiField("msg")
	private String msg;

	/** 
	 * 绑定账户列表
	 */
	@ApiListField("public_bind_accounts")
	@ApiField("public_bind_account")
	private List<PublicBindAccount> publicBindAccounts;

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

	public void setPublicBindAccounts(List<PublicBindAccount> publicBindAccounts) {
		this.publicBindAccounts = publicBindAccounts;
	}
	public List<PublicBindAccount> getPublicBindAccounts( ) {
		return this.publicBindAccounts;
	}

}
