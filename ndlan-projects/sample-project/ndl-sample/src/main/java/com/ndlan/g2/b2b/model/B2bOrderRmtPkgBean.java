package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bOrderRmtPkgBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ¨¨??¨¨?¡ì??¡­??¨C???
     **/
    private String orderRmtPkgCode;
    /**
     * ¨¨??¨¨?¡ì?¡è?ID
     **/
    private String orderRmtHeadId;
    /**
     * ?¡ë????¨¨???????¡­id
     **/
    private String logOrderPkgId;
    private String cartId;
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    private String strategyDesc;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    private String amount;
    /**
     * ????¡­?
     **/
    private String derate;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    private String supplierName;
    /**
     * ???¨¨???????¡­ID
     **/
    private String orderPkgId;
    /**
     * ?¡ª?????¡±????
     **/
    private String codelessSum;
    /**
     * ????¡ë?
     **/
    private String discount;
    /**
     * ?¡±?????¨C????
     **/
    private String payType;
    /**
     * ???¨¨??????¡è?ID
     **/
    private String orderHeadId;
    private String restId;
    /**
     * ?????¡¤????¡ì¡ã
     **/
    private String customerName;
    /**
     * ¨¨??¨¨?¡ì??¡­ID
     **/
    private String orderRmtPkgId;
    /**
     * ?????¡±???ID
     **/
    private String supplierId;
    /**
     * ???¨¨?????????¢ã????0??¡­???????¢ã?1¨¦?¡§????????????2???????????????3??¡­??¡®¨¨?¡ì?¢ã?4?¡¤???¡®¨¨?¡ì?¢ã?5?¡¤??¡±?¨¨?¡ì?¢ã?6??¨C?????¡ë
     **/
    private String orignStatus;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    private String slsPmtnId;
    private String restName;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    private String amountPaid;
    /**
     * ?¡ë????¨¨???????¡­??¨C??¡¤
     **/
    private String logOrderPkgCode;
    /**
     * ?????¡¤ID
     **/
    private String customerId;
    /**
     * ?¡è???¡§
     **/
    private String remark;
    /**
     * ???¨¨?????????¡ì¡ã
     **/
    private String orderPkgName;
    /**
     * ??????????¡°??¡ì??¡À???¡ã¨¦??
     **/
    private String goodsCatQty;
    /**
     * ???¨¨???????¡­??¨C??¡¤
     **/
    private String orderPkgCode;
    /**
     * ?¡­???¡°????¢ã?(0????¡±?¨¨?¡ì???1??¡­?¡­???¡°???2?¡¤??¡­???¡°)
     **/
    private String storageStatus;


  
	
    public void setOrderRmtPkgCode(String orderRmtPkgCode) {
        this.orderRmtPkgCode = orderRmtPkgCode;
    }

    public String getOrderRmtPkgCode() {
        return orderRmtPkgCode;
    }

	
    public void setOrderRmtHeadId(String orderRmtHeadId) {
        this.orderRmtHeadId = orderRmtHeadId;
    }

    public String getOrderRmtHeadId() {
        return orderRmtHeadId;
    }

	
    public void setLogOrderPkgId(String logOrderPkgId) {
        this.logOrderPkgId = logOrderPkgId;
    }

    public String getLogOrderPkgId() {
        return logOrderPkgId;
    }

	
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

	

	
    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }

	
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

	

	
    public void setDerate(String derate) {
        this.derate = derate;
    }

    public String getDerate() {
        return derate;
    }

	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }

	
    public void setCodelessSum(String codelessSum) {
        this.codelessSum = codelessSum;
    }

    public String getCodelessSum() {
        return codelessSum;
    }

	
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

	

	
    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

	
    public void setOrderHeadId(String orderHeadId) {
        this.orderHeadId = orderHeadId;
    }

    public String getOrderHeadId() {
        return orderHeadId;
    }

	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

	
    public void setOrderRmtPkgId(String orderRmtPkgId) {
        this.orderRmtPkgId = orderRmtPkgId;
    }

    public String getOrderRmtPkgId() {
        return orderRmtPkgId;
    }

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

	
    public void setOrignStatus(String orignStatus) {
        this.orignStatus = orignStatus;
    }

    public String getOrignStatus() {
        return orignStatus;
    }

	
    public void setSlsPmtnId(String slsPmtnId) {
        this.slsPmtnId = slsPmtnId;
    }

    public String getSlsPmtnId() {
        return slsPmtnId;
    }

	
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }

	

	

	
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

	
    public void setLogOrderPkgCode(String logOrderPkgCode) {
        this.logOrderPkgCode = logOrderPkgCode;
    }

    public String getLogOrderPkgCode() {
        return logOrderPkgCode;
    }

	
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

	
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

	
    public void setOrderPkgName(String orderPkgName) {
        this.orderPkgName = orderPkgName;
    }

    public String getOrderPkgName() {
        return orderPkgName;
    }

	
    public void setGoodsCatQty(String goodsCatQty) {
        this.goodsCatQty = goodsCatQty;
    }

    public String getGoodsCatQty() {
        return goodsCatQty;
    }

	
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }

	
    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public String getStorageStatus() {
        return storageStatus;
    }



}
