package com.alipay.api.domain;

import java.util.Date;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 芝麻信用风险记录详情
 *
 * @author auto create
 * @since 1.0, 2015-06-12 16:14:00
 */
public class ZhimaRiskDetail extends AlipayObject {

	private static final long serialVersionUID = 7783741165849119162L;

	/**
	 * 风险代码
	 */
	@ApiField("risk_code")
	private String riskCode;

	/**
	 * 风险类型
	 */
	@ApiField("risk_type")
	private String riskType;

	/**
	 * 对于逾期类风险信息，此字段表示是否结清。T（结清）/F（未结清）
	 */
	@ApiField("settlement")
	private String settlement;

	/**
	 * 当用户进行异议处理，并核查完毕之后，仍有异议时，给出声明。
	 */
	@ApiField("statement")
	private String statement;

	/**
	 * 当用户本人对该条负面记录有异议时，表示该异议处理流程的状态
	 */
	@ApiField("status")
	private String status;

	/**
	 * 名单类型
	 */
	@ApiField("type")
	private String type;

	/**
	 * 数据更新时间
	 */
	@ApiField("update")
	private Date update;

	public String getRiskCode() {
		return this.riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskType() {
		return this.riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getSettlement() {
		return this.settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public String getStatement() {
		return this.statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdate() {
		return this.update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}

}
