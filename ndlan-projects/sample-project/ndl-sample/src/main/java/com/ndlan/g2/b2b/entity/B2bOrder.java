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
@Table(name = "b2b_order" )
public class B2bOrder  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    
    
    
    
    
    
    
    
    /**
     * ?¡ª?????¡±????
     **/
    @Column(name = "codeless_sum"
     ,length = 100
    
    
    
    )
    private String codelessSum;
    
    
    
    
    @Column(name = "rest_name"
     ,length = 255
    
    
    
    )
    private String restName;
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remark"
     ,length = 255
    
    
    
    )
    private String remark;
    /**
     * ¨¨??????¡è?ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "b_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String bId;
    /**
     * ?¡±?????¨C????
     **/
    @Column(name = "pay_type"
     ,length = 36
    
    
    
    )
    private String payType;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @Column(name = "target_client"
     ,length = 20
    
    
    
    )
    private String targetClient;
    /**
     * ??????
     **/
    @Column(name = "all_privilege"
     ,length = 100
    
    
    
    )
    private String allPrivilege;
    
    
    
    
    /**
     * ?????¡¤ID
     **/
    @Column(name = "customer_id"
     ,length = 36
    
    
    
    )
    private String customerId;
    /**
     * ????¡ë?
     **/
    @Column(name = "all_discount"
     ,length = 100
    
    
    
    )
    private String allDiscount;
    /**
     * ?????¡¤????¡ì¡ã
     **/
    @Column(name = "customer_name"
     ,length = 255
    
    
    
    )
    private String customerName;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    @Column(name = "b_amount"
     ,length = 100
    
    
    
    )
    private String bAmount;
    /**
     * ¨¨?????????¡ì¡ã
     **/
    @Column(name = "b_name"
     ,length = 64
    
    
    
    )
    private String bName;
    
    
    
    
    /**
     * ¨¨???¡ë?¨¨??id
     **/
    @Column(name = "cart_id"
     ,length = 36
    
    
    
    )
    private String cartId;
    /**
     * ¨¨??????¡è???¨C??¡¤
     **/
    @Column(name = "b_no"
     ,length = 36
    
    
    
    )
    private String bNo;
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    @Column(name = "amount_paid"
     ,length = 100
    
    
    
    )
    private String amountPaid;


   

    public void setCodelessSum(String codelessSum) {
        this.codelessSum = codelessSum;
    }

    public String getCodelessSum() {
        return codelessSum;
    }
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
    public void setBId(String bId) {
        this.bId = bId;
    }

    public String getBId() {
        return bId;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }
    public void setAllPrivilege(String allPrivilege) {
        this.allPrivilege = allPrivilege;
    }

    public String getAllPrivilege() {
        return allPrivilege;
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
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setBAmount(String bAmount) {
        this.bAmount = bAmount;
    }

    public String getBAmount() {
        return bAmount;
    }
    public void setBName(String bName) {
        this.bName = bName;
    }

    public String getBName() {
        return bName;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
    public void setBNo(String bNo) {
        this.bNo = bNo;
    }

    public String getBNo() {
        return bNo;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountPaid() {
        return amountPaid;
    }


}
