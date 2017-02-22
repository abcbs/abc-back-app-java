package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bShoppingCartBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?��?��?����??
     **/
    private String total;
    /**
     * ?????��ID
     **/
    private String customerId;
    /**
     * ????��?
     **/
    private String allDiscount;
    /**
     * ??������?????���
     **/
    private String restName;
    /**
     * ??????
     **/
    private String allPrivilege;
    /**
     * ?????��?�조???
     **/
    private String customerName;
    /**
     * ??������?ID
     **/
    private String restId;
    /**
     * ?????��??????��??��?��CUST|BUSI??��
     **/
    private String targetClient;
    /**
     * ��???��?��?????����?
     **/
    private String cartId;

      private List< B2bShoppingCartPkgBeanBeanBeanBean > cartPackage;

  
	
    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
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

	
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }

	
    public void setAllPrivilege(String allPrivilege) {
        this.allPrivilege = allPrivilege;
    }

    public String getAllPrivilege() {
        return allPrivilege;
    }

	

	
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

	

	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	

	

	
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }

	
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

	


 public void setCartPackage(List< B2bShoppingCartPkgBeanBeanBeanBean > cartPackage) {
        this.cartPackage = cartPackage;
    }

    public List< B2bShoppingCartPkgBeanBeanBeanBean > getCartPackage() {
        return cartPackage;
    }

}
