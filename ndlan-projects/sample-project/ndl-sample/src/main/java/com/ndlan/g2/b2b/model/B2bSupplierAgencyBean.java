package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bSupplierAgencyBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?????°±???????°Ï°„
     **/
    private String supplierName;
    /**
     * ??°Æ®®?°Ï??°„??¢„
     **/
    private String deliveryAddress;
    /**
     * ??°Æ®®?°Ï??°„??¢„ID
     **/
    private String sendAddressId;
    /**
     * ®®?°±??????
     **/
    private String contactUser;
    /**
     * ???®¶°±?
     **/
    private String supplierAgencyId;
    /**
     * ???®¶°±¢„???ID
     **/
    private String agencyId;
    /**
     * ???®¶°±¢„???????°Ï°„
     **/
    private String agencyName;
    /**
     * ?°Ë???°ß
     **/
    private String remake;
    /**
     * ®®?°±???????°±?®®??
     **/
    private String contactPhone;
    /**
     * ?????°±???ID
     **/
    private String supplierId;


  
	

	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	

	
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

	

	
    public void setSendAddressId(String sendAddressId) {
        this.sendAddressId = sendAddressId;
    }

    public String getSendAddressId() {
        return sendAddressId;
    }

	
    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getContactUser() {
        return contactUser;
    }

	
    public void setSupplierAgencyId(String supplierAgencyId) {
        this.supplierAgencyId = supplierAgencyId;
    }

    public String getSupplierAgencyId() {
        return supplierAgencyId;
    }

	

	
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyId() {
        return agencyId;
    }

	
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyName() {
        return agencyName;
    }

	

	
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }

	
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }



}
