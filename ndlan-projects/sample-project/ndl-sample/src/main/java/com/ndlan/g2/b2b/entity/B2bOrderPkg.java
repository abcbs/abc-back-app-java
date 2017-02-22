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
@Table(name = "b2b_order_pkg" )
public class B2bOrderPkg  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ?¡±?????¨C????
     **/
    @Column(name = "pay_type"
     ,length = 36
    
    
    
    )
    private String payType;
    /**
     * ?????¡¤ID
     **/
    @Column(name = "customer_id"
     ,length = 36
    
    
    
    )
    private String customerId;
    /**
     * ¨¨???????¡­ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "order_pkg_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String orderPkgId;
    
    
    
    
    /**
     * ?????¡¤????¡ì¡ã
     **/
    @Column(name = "customer_name"
     ,length = 255
    
    
    
    )
    private String customerName;
    /**
     * ¨¨?????????¡ì¡ã
     **/
    @Column(name = "order_pkg_name"
     ,length = 64
    
    
    
    )
    private String orderPkgName;
    /**
     * ??????¨¨???????????
     **/
    @Column(name = "exception_desc"
     ,length = 255
    
    
    
    )
    private String exceptionDesc;
    /**
     * ?????¡±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    @Column(name = "strategy_desc"
     ,length = 255
    
    
    
    )
    private String strategyDesc;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @Column(name = "target_client"
     ,length = 20
    
    
    
    )
    private String targetClient;
    /**
     * ¨¨??????¡è?ID
     **/
    @Column(name = "order_head_id"
     ,length = 36
    
    
    
    )
    private String orderHeadId;
    /**
     * ????¡ë?
     **/
    @Column(name = "all_discount"
     ,length = 100
    
    
    
    )
    private String allDiscount;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ?¡­???¡°????¢ã?(0????¡±?¨¨?¡ì???1??¡­?¡­???¡°???2¨¦?¡§????¡­???¡°???3?¡¤??¡­???¡°)
     **/
    @Column(name = "storage_status"
     ,length = 20
    
    
    
    )
    private String storageStatus;
    
    
    
    
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    @Column(name = "order_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderPkgCode;
    /**
     * ????¡±????¨¦?¡®¨¦???¢ã??¡±¡§????¡±???????????¢ã¡®
     **/
    @Column(name = "unpaid_amount"
     ,length = 255
    
    
    
    )
    private String unpaidAmount;
    
    
    
    
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remark"
     ,length = 255
    
    
    
    )
    private String remark;
    @Column(name = "rest_name"
     ,length = 255
    
    
    
    )
    private String restName;
    /**
     * ¨¨???¡ë?¨¨??id
     **/
    @Column(name = "cart_id"
     ,length = 36
    
    
    
    )
    private String cartId;
    /**
     * ¨¨?????????¢ã????¨¨?¡ã
     **/
    @Column(name = "status_desc"
     ,length = 255
    
    
    
    )
    private String statusDesc;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    @Column(name = "amount_paid"
     ,length = 100
    
    
    
    )
    private String amountPaid;
    /**
     * ??????¨¨??????¡è??????¡°??????¨¨?¡ã
     **/
    @Column(name = "exception_solve"
     ,length = 255
    
    
    
    )
    private String exceptionSolve;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    @Column(name = "sls_pmtn_id"
     ,length = 36
    
    
    
    )
    private String slsPmtnId;
    
    
    
    
    /**
     * ??????
     **/
    @Column(name = "all_privilege"
     ,length = 100
    
    
    
    )
    private String allPrivilege;
    /**
     * ?¡ª?????¡±????
     **/
    @Column(name = "codeless_sum"
     ,length = 100
    
    
    
    )
    private String codelessSum;
    
    
    
    
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    @Column(name = "amount"
     ,length = 100
    
    
    
    )
    private String amount;


   

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setOrderPkgName(String orderPkgName) {
        this.orderPkgName = orderPkgName;
    }

    public String getOrderPkgName() {
        return orderPkgName;
    }
    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }
    public void setOrderHeadId(String orderHeadId) {
        this.orderHeadId = orderHeadId;
    }

    public String getOrderHeadId() {
        return orderHeadId;
    }
    public void setAllDiscount(String allDiscount) {
        this.allDiscount = allDiscount;
    }

    public String getAllDiscount() {
        return allDiscount;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public String getStorageStatus() {
        return storageStatus;
    }
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }
    public void setUnpaidAmount(String unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public String getUnpaidAmount() {
        return unpaidAmount;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountPaid() {
        return amountPaid;
    }
    public void setExceptionSolve(String exceptionSolve) {
        this.exceptionSolve = exceptionSolve;
    }

    public String getExceptionSolve() {
        return exceptionSolve;
    }
    public void setSlsPmtnId(String slsPmtnId) {
        this.slsPmtnId = slsPmtnId;
    }

    public String getSlsPmtnId() {
        return slsPmtnId;
    }
    public void setAllPrivilege(String allPrivilege) {
        this.allPrivilege = allPrivilege;
    }

    public String getAllPrivilege() {
        return allPrivilege;
    }
    public void setCodelessSum(String codelessSum) {
        this.codelessSum = codelessSum;
    }

    public String getCodelessSum() {
        return codelessSum;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }


}
