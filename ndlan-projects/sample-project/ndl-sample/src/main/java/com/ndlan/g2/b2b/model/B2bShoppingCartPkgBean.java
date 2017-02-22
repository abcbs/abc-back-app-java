package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bShoppingCartPkgBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    private String strategyDesc;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    private String slsPmtnId;
    /**
     * ¨¨???¡ë?¨¨???¡è?ID
     **/
    private String cartId;
    /**
     * ?¡è???¡§
     **/
    private String remark;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    private String amount;
    private String derate;
    private String discount;
    /**
     * ???¨¦¡±¢ã???id
     **/
    private String restId;
    /**
     * ?????¡¤?¡ì¡°???
     **/
    private String customerName;
    /**
     * ¨¨???¡ë?¨¨????¡­ID
     **/
    private String cartPkgId;
    /**
     * ?¡±?????¨C????
     **/
    private String payType;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    private String supplierName;
    /**
     * ?????¡¤ID
     **/
    private String customerId;
    /**
     * ???¨¦¡±¢ã???????¡ì¡ã
     **/
    private String restName;
    /**
     * ?????¡±???ID
     **/
    private String supplierId;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    private String amountPaid;
    /**
     * ?¡ª?????¡±????
     **/
    private String codelessSum;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    private String targetClient;

      private List< B2bShoppingCartDetailBeanBeanBeanBean > cartItem;

  
	
    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }

	
    public void setSlsPmtnId(String slsPmtnId) {
        this.slsPmtnId = slsPmtnId;
    }

    public String getSlsPmtnId() {
        return slsPmtnId;
    }

	
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

	
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
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

	
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
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

	

	

	
    public void setCartPkgId(String cartPkgId) {
        this.cartPkgId = cartPkgId;
    }

    public String getCartPkgId() {
        return cartPkgId;
    }

	
    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	

	
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

	
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

	

	
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

	
    public void setCodelessSum(String codelessSum) {
        this.codelessSum = codelessSum;
    }

    public String getCodelessSum() {
        return codelessSum;
    }

	
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }

	


 public void setCartItem(List< B2bShoppingCartDetailBeanBeanBeanBean > cartItem) {
        this.cartItem = cartItem;
    }

    public List< B2bShoppingCartDetailBeanBeanBeanBean > getCartItem() {
        return cartItem;
    }

}
