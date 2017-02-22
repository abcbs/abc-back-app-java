package com.ndlan.g2.b2b.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import com.ndlan.canyin.base.entity.BaseEntity;
import java.util.List;
import java.util.Map;

import java.util.Date;


@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "b2b_shopping_cart_pkg" )
public class B2bShoppingCartPkg  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    @Column(name = "strategy_desc"
     ,length = 2
    
    
    
    )
    private String strategyDesc;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    @Column(name = "sls_pmtn_id"
     ,length = 2
    
    
    
    )
    private String slsPmtnId;
    /**
     * ¨¨???¡ë?¨¨???¡è?ID
     **/
    @Column(name = "cart_id"
     ,length = 36
    
    
    
    )
    private String cartId;
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remark"
     ,length = 255
    
    
    
    )
    private String remark;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    @Column(name = "amount"
     ,length = 100
    
    
    
    )
    private String amount;
    @Column(name = "derate"
     ,length = 100
    
    
    
    )
    private String derate;
    @Column(name = "discount"
     ,length = 100
    
    
    
    )
    private String discount;
    /**
     * ???¨¦¡±¢ã???id
     **/
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ?????¡¤?¡ì¡°???
     **/
    @Column(name = "customer_name"
     ,length = 255
    
    
    
    )
    private String customerName;
    
    
    
    
    
    
    
    
    /**
     * ¨¨???¡ë?¨¨????¡­ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cart_pkg_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cartPkgId;
    /**
     * ?¡±?????¨C????
     **/
    @Column(name = "pay_type"
     ,length = 36
    
    
    
    )
    private String payType;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    
    
    
    
    /**
     * ?????¡¤ID
     **/
    @Column(name = "customer_id"
     ,length = 36
    
    
    
    )
    private String customerId;
    /**
     * ???¨¦¡±¢ã???????¡ì¡ã
     **/
    @Column(name = "rest_name"
     ,length = 255
    
    
    
    )
    private String restName;
    /**
     * ?????¡±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    
    
    
    
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    @Column(name = "amount_paid"
     ,length = 100
    
    
    
    )
    private String amountPaid;
    /**
     * ?¡ª?????¡±????
     **/
    @Column(name = "codeless_sum"
     ,length = 100
    
    
    
    )
    private String codelessSum;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @Column(name = "target_client"
     ,length = 20
    
    
    
    )
    private String targetClient;
    
    
    
    

      private List< B2bShoppingCartDetailBeanBean > cartItem;

   

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

 public void setCartItem(List< B2bShoppingCartDetailBeanBean > cartItem) {
        this.cartItem = cartItem;
    }

    public List< B2bShoppingCartDetailBeanBean > getCartItem() {
        return cartItem;
    }

}
