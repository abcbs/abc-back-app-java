package com.ndlan.canyin.base.repository.cygl;

public class Payment {
	private String billStatus;
	private String billId;
	private String dbpId;
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getDbpId() {
		return dbpId;
	}
	public void setDbpId(String dbpId) {
		this.dbpId = dbpId;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	
	

}
