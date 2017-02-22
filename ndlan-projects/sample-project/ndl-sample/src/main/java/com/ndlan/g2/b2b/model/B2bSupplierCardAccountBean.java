package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bSupplierCardAccountBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?¡è???¡§
     **/
    private String remake;
    /**
     * ¨¨?¡±¨¨????¡¤
     **/
    private String catNo;
    /**
     * ??????¨¦??¨¨?¡è???1??????0?????¡ë
     **/
    private String isDefault;
    /**
     * ¨¨?¡±????¨C????
     **/
    private String phoneNo;
    /**
     * ?????????¨¨?????¨¨????¡¤
     **/
    private String cardholderId;
    /**
     * ??????????¡®?¨¨???¢ã?card_no+cardholder_name?¢ã¡®
     **/
    private String cardinfoSummay;
    /**
     * ???¨¨????????
     **/
    private String branch;
    /**
     * ?¡±?¨¨????????
     **/
    private String subbranch;
    /**
     * ¨¨????¡¤???¨¦¡±?
     **/
    private String supCardAcntId;
    /**
     * ??¢ã??¡¤¨¨??
     **/
    private String bank;
    /**
     * ?????¡±???Id
     **/
    private String supplierId;
    /**
     * ?????¡¤
     **/
    private String cardNo;
    /**
     * ??????????¡ì¡°???
     **/
    private String cardholderName;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    private String supplierName;


  
	
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }

	
    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    public String getCatNo() {
        return catNo;
    }

	

	
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefault() {
        return isDefault;
    }

	

	
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

	
    public void setCardholderId(String cardholderId) {
        this.cardholderId = cardholderId;
    }

    public String getCardholderId() {
        return cardholderId;
    }

	

	
    public void setCardinfoSummay(String cardinfoSummay) {
        this.cardinfoSummay = cardinfoSummay;
    }

    public String getCardinfoSummay() {
        return cardinfoSummay;
    }

	
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }

	

	
    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getSubbranch() {
        return subbranch;
    }

	
    public void setSupCardAcntId(String supCardAcntId) {
        this.supCardAcntId = supCardAcntId;
    }

    public String getSupCardAcntId() {
        return supCardAcntId;
    }

	
    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

	
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

	
    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardholderName() {
        return cardholderName;
    }

	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	



}
