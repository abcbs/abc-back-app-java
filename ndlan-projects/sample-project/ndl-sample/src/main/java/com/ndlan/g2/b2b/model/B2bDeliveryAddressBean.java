package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bDeliveryAddressBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?���??��??��???
     **/
    private String region;
    /**
     * ?��?��?��???
     **/
    private String receiveName;
    /**
     * ?��?��?��???��?��????�C????
     **/
    private String receivePhone;
    /**
     * ��???????��??��
     **/
    private String detailAddress;
    /**
     * ?��?��?��????��?��??
     **/
    private String receiveTellcall;
    /**
     * ???������???ID
     **/
    private String supplierId;
    /**
     * ??������???��??��ID
     **/
    private String deliveryAddressId;
    /**
     * ?��???��
     **/
    private String remake;
    /**
     * ???������???????���
     **/
    private String supplierName;
    /**
     * ??????��??��?��?��?��?��??��??��???1??????0?????��
     **/
    private String isDefault;
    /**
     * ��????�C
     **/
    private String postCode;


  
	
    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

	
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveName() {
        return receiveName;
    }

	
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

	

	
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

	
    public void setReceiveTellcall(String receiveTellcall) {
        this.receiveTellcall = receiveTellcall;
    }

    public String getReceiveTellcall() {
        return receiveTellcall;
    }

	

	

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

	
    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

	
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }

	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefault() {
        return isDefault;
    }

	
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

	

	



}
