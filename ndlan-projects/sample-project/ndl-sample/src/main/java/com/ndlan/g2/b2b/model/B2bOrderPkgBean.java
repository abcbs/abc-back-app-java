package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bOrderPkgBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?��?????�C????
     **/
    private String payType;
    /**
     * ?????��ID
     **/
    private String customerId;
    /**
     * ��???????��ID
     **/
    private String orderPkgId;
    /**
     * ?????��????���
     **/
    private String customerName;
    /**
     * ��?????????���
     **/
    private String orderPkgName;
    /**
     * ??????��???????????
     **/
    private String exceptionDesc;
    /**
     * ?????��???ID
     **/
    private String supplierId;
    /**
     * ???������??�C??????��?��
     **/
    private String strategyDesc;
    /**
     * ?????��??????��??��?��CUST|BUSI??��
     **/
    private String targetClient;
    /**
     * ��??????��?ID
     **/
    private String orderHeadId;
    /**
     * ????��?
     **/
    private String allDiscount;
    /**
     * ?????��???????���
     **/
    private String supplierName;
    /**
     * ?��???��????��?(0????��?��?��???1??��?��???��???2��?��????��???��???3?��??��???��)
     **/
    private String storageStatus;
    /**
     * ��???????��??�C??��
     **/
    private String orderPkgCode;
    /**
     * ????��????��?����???��??����????��???????????�㡮
     **/
    private String unpaidAmount;
    /**
     * ?��???��
     **/
    private String remark;
    private String restName;
    /**
     * ��???��?��??id
     **/
    private String cartId;
    /**
     * ��?????????��????��?��
     **/
    private String statusDesc;
    /**
     * ????��?��?����??
     **/
    private String amountPaid;
    /**
     * ??????��??????��??????��??????��?��
     **/
    private String exceptionSolve;
    /**
     * ???������??�C???
     **/
    private String slsPmtnId;
    /**
     * ??????
     **/
    private String allPrivilege;
    /**
     * ?��?????��????
     **/
    private String codelessSum;
    private String restId;
    /**
     * ??��?��?��?����??
     **/
    private String amount;


  
	
    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

	
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

	
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }

	

	
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

	
    public void setOrderPkgName(String orderPkgName) {
        this.orderPkgName = orderPkgName;
    }

    public String getOrderPkgName() {
        return orderPkgName;
    }

	
    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

	
    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }

	
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }

	
    public void setOrderHeadId(String orderHeadId) {
        this.orderHeadId = orderHeadId;
    }

    public String getOrderHeadId() {
        return orderHeadId;
    }

	
    public void setAllDiscount(String allDiscount) {
        this.allDiscount = allDiscount;
    }

    public String getAllDiscount() {
        return allDiscount;
    }

	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	
    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public String getStorageStatus() {
        return storageStatus;
    }

	

	
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }

	
    public void setUnpaidAmount(String unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public String getUnpaidAmount() {
        return unpaidAmount;
    }

	

	
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

	
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }

	
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

	
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

	
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

	
    public void setExceptionSolve(String exceptionSolve) {
        this.exceptionSolve = exceptionSolve;
    }

    public String getExceptionSolve() {
        return exceptionSolve;
    }

	
    public void setSlsPmtnId(String slsPmtnId) {
        this.slsPmtnId = slsPmtnId;
    }

    public String getSlsPmtnId() {
        return slsPmtnId;
    }

	

	
    public void setAllPrivilege(String allPrivilege) {
        this.allPrivilege = allPrivilege;
    }

    public String getAllPrivilege() {
        return allPrivilege;
    }

	
    public void setCodelessSum(String codelessSum) {
        this.codelessSum = codelessSum;
    }

    public String getCodelessSum() {
        return codelessSum;
    }

	

	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }



}
