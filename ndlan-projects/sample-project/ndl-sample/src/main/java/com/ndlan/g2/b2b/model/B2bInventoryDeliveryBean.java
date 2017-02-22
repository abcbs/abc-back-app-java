package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bInventoryDeliveryBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?????��?��????
     **/
    private Date deliveryDate;
    /**
     * ?��???��
     **/
    private String remark;
    /**
     * ?????��???
     **/
    private String deliveryUser;
    /**
     * ?????��??�㨦??
     **/
    private String deliveryQty;
    /**
     * ??��????��?��?��
     **/
    private String invHeadId;
    /**
     * ???����?
     **/
    private String invDeliveryId;
    /**
     * ?????��??��???
     **/
    private String deliveryPrice;
    /**
     * ?????��???????��???�C???|����????|???????�㡮
     **/
    private String source;


  
	
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

	
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

	

	

	
    public void setDeliveryUser(String deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public String getDeliveryUser() {
        return deliveryUser;
    }

	
    public void setDeliveryQty(String deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getDeliveryQty() {
        return deliveryQty;
    }

	
    public void setInvHeadId(String invHeadId) {
        this.invHeadId = invHeadId;
    }

    public String getInvHeadId() {
        return invHeadId;
    }

	

	
    public void setInvDeliveryId(String invDeliveryId) {
        this.invDeliveryId = invDeliveryId;
    }

    public String getInvDeliveryId() {
        return invDeliveryId;
    }

	
    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

	

	
    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }



}
