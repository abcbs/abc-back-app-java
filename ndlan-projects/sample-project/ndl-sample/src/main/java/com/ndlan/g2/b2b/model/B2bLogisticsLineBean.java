package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bLogisticsLineBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?????��???????���
     **/
    private String supplierName;
    /**
     * ??����?��??��??��id
     **/
    private String deliveryAddressId;
    /**
     * ?��????ID
     **/
    private String logisticsLineId;
    /**
     * ����?��?��??��?��??��?����?
     **/
    private Date deliveryStartDate;
    /**
     * ?��??????�C??��
     **/
    private String logisticsLineCode;
    /**
     * ??����?��??��??��
     **/
    private String deliveryAddress;
    /**
     * ?????��???ID
     **/
    private String supplierId;
    /**
     * ����?��?��???��?��????�C????
     **/
    private String deliveryUserPhone;
    /**
     * ����?��?��??��????��?����?
     **/
    private Date deliveryEndDate;
    /**
     * ?��???��
     **/
    private String remake;
    private String logisticsLineName;
    /**
     * ����?��?��???
     **/
    private String deliveryUser;


  
	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	
    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

	

	
    public void setLogisticsLineId(String logisticsLineId) {
        this.logisticsLineId = logisticsLineId;
    }

    public String getLogisticsLineId() {
        return logisticsLineId;
    }

	
    public void setDeliveryStartDate(Date deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public Date getDeliveryStartDate() {
        return deliveryStartDate;
    }

	
    public void setLogisticsLineCode(String logisticsLineCode) {
        this.logisticsLineCode = logisticsLineCode;
    }

    public String getLogisticsLineCode() {
        return logisticsLineCode;
    }

	
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

	

	
    public void setDeliveryUserPhone(String deliveryUserPhone) {
        this.deliveryUserPhone = deliveryUserPhone;
    }

    public String getDeliveryUserPhone() {
        return deliveryUserPhone;
    }

	
    public void setDeliveryEndDate(Date deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
    }

    public Date getDeliveryEndDate() {
        return deliveryEndDate;
    }

	
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }

	
    public void setLogisticsLineName(String logisticsLineName) {
        this.logisticsLineName = logisticsLineName;
    }

    public String getLogisticsLineName() {
        return logisticsLineName;
    }

	

	

	

	
    public void setDeliveryUser(String deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public String getDeliveryUser() {
        return deliveryUser;
    }



}
