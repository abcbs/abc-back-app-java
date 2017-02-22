package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bOrderBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?¡ª?????¡±????
     **/
    private String codelessSum;
    private String restName;
    /**
     * ?¡è???¡§
     **/
    private String remark;
    /**
     * ¨¨??????¡è?ID
     **/
    private String bId;
    /**
     * ?¡±?????¨C????
     **/
    private String payType;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    private String targetClient;
    /**
     * ??????
     **/
    private String allPrivilege;
    /**
     * ?????¡¤ID
     **/
    private String customerId;
    /**
     * ????¡ë?
     **/
    private String allDiscount;
    /**
     * ?????¡¤????¡ì¡ã
     **/
    private String customerName;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    private String bAmount;
    /**
     * ¨¨?????????¡ì¡ã
     **/
    private String bName;
    /**
     * ¨¨???¡ë?¨¨??id
     **/
    private String cartId;
    /**
     * ¨¨??????¡è???¨C??¡¤
     **/
    private String bNo;
    private String restId;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    private String amountPaid;


  
	

	

	
    public void setCodelessSum(String codelessSum) {
        this.codelessSum = codelessSum;
    }

    public String getCodelessSum() {
        return codelessSum;
    }

	

	
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }

	
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

	
    public void setBId(String bId) {
        this.bId = bId;
    }

    public String getBId() {
        return bId;
    }

	
    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

	
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }

	
    public void setAllPrivilege(String allPrivilege) {
        this.allPrivilege = allPrivilege;
    }

    public String getAllPrivilege() {
        return allPrivilege;
    }

	

	
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

	
    public void setAllDiscount(String allDiscount) {
        this.allDiscount = allDiscount;
    }

    public String getAllDiscount() {
        return allDiscount;
    }

	
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

	
    public void setBAmount(String bAmount) {
        this.bAmount = bAmount;
    }

    public String getBAmount() {
        return bAmount;
    }

	
    public void setBName(String bName) {
        this.bName = bName;
    }

    public String getBName() {
        return bName;
    }

	

	
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

	
    public void setBNo(String bNo) {
        this.bNo = bNo;
    }

    public String getBNo() {
        return bNo;
    }

	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountPaid() {
        return amountPaid;
    }



}
