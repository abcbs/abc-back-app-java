package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bLogisticsOrderDeliveryBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ��???????��??�C??��
     **/
    private String orderPkgCode;
    /**
     * ???������???????���
     **/
    private String customName;
    /**
     * ��?��?��??��???????0��??????��?1��??��?��??��
     **/
    private String goodsType;
    /**
     * ��???????��ID
     **/
    private String orderPkgId;
    /**
     * ��?????��?����??
     **/
    private String orderAmount;
    /**
     * ����?����??��????ID
     **/
    private String lgstOrdDlvId;
    /**
     * ?��?��?��??��??��ID
     **/
    private String deliveryAddressId;
    /**
     * ???������???ID
     **/
    private String customId;
    /**
     * ?��???��
     **/
    private String remake;
    /**
     * ?��????ID
     **/
    private String logisticsLineId;
    /**
     * ?��????????���
     **/
    private String logisticsLineName;
    /**
     * ��????�C
     **/
    private String postCode;
    /**
     * ?��?��?��????��?��??
     **/
    private String receiveTellcall;
    /**
     * ?��?��?��???��?��????�C????
     **/
    private String receivePhone;
    /**
     * ��?????����?����?ID
     **/
    private String orderDeliveryId;
    /**
     * ?????��???ID
     **/
    private String supplierId;
    /**
     * ?���??��??��???
     **/
    private String region;
    /**
     * ��??????��????
     **/
    private Date orderDate;
    /**
     * ?????��???????���
     **/
    private String supplierName;
    /**
     * ?��?��?��???
     **/
    private String receiveName;
    /**
     * ?��??????�C??��
     **/
    private String logisticsHeadCode;
    /**
     * ��???????��??��
     **/
    private String detailAddress;


  
	
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }

	
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomName() {
        return customName;
    }

	
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsType() {
        return goodsType;
    }

	
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }

	
    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

	
    public void setLgstOrdDlvId(String lgstOrdDlvId) {
        this.lgstOrdDlvId = lgstOrdDlvId;
    }

    public String getLgstOrdDlvId() {
        return lgstOrdDlvId;
    }

	
    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

	
    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomId() {
        return customId;
    }

	
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }

	
    public void setLogisticsLineId(String logisticsLineId) {
        this.logisticsLineId = logisticsLineId;
    }

    public String getLogisticsLineId() {
        return logisticsLineId;
    }

	
    public void setLogisticsLineName(String logisticsLineName) {
        this.logisticsLineName = logisticsLineName;
    }

    public String getLogisticsLineName() {
        return logisticsLineName;
    }

	
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

	
    public void setReceiveTellcall(String receiveTellcall) {
        this.receiveTellcall = receiveTellcall;
    }

    public String getReceiveTellcall() {
        return receiveTellcall;
    }

	
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

	

	

	
    public void setOrderDeliveryId(String orderDeliveryId) {
        this.orderDeliveryId = orderDeliveryId;
    }

    public String getOrderDeliveryId() {
        return orderDeliveryId;
    }

	

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

	

	
    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

	

	
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveName() {
        return receiveName;
    }

	
    public void setLogisticsHeadCode(String logisticsHeadCode) {
        this.logisticsHeadCode = logisticsHeadCode;
    }

    public String getLogisticsHeadCode() {
        return logisticsHeadCode;
    }

	
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }



}
