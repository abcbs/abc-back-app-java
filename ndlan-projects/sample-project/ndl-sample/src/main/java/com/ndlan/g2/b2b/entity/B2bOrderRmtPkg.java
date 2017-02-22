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
@Table(name = "b2b_order_rmt_pkg" )
public class B2bOrderRmtPkg  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ��??��?��??��??�C???
     **/
    @Column(name = "order_rmt_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderRmtPkgCode;
    /**
     * ��??��?��?��?ID
     **/
    @Column(name = "order_rmt_head_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String orderRmtHeadId;
    /**
     * ?��????��???????��id
     **/
    @Column(name = "log_order_pkg_id"
     ,length = 36
    
    
    
    )
    private String logOrderPkgId;
    @Column(name = "cart_id"
     ,length = 36
    
    
    
    )
    private String cartId;
    
    
    
    
    /**
     * ???������??�C??????��?��
     **/
    @Column(name = "strategy_desc"
     ,length = 2
    
    
    
    )
    private String strategyDesc;
    /**
     * ??��?��?��?����??
     **/
    @Column(name = "amount"
     ,length = 100
    
    
    
    )
    private String amount;
    
    
    
    
    /**
     * ????��?
     **/
    @Column(name = "derate"
     ,length = 100
    
    
    
    )
    private String derate;
    /**
     * ?????��???????���
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ???��???????��ID
     **/
    @Column(name = "order_pkg_id"
     ,length = 36
    
    
    
    )
    private String orderPkgId;
    /**
     * ?��?????��????
     **/
    @Column(name = "codeless_sum"
     ,length = 100
    
    
    
    )
    private String codelessSum;
    /**
     * ????��?
     **/
    @Column(name = "discount"
     ,length = 100
    
    
    
    )
    private String discount;
    
    
    
    
    /**
     * ?��?????�C????
     **/
    @Column(name = "pay_type"
     ,length = 36
    
    
    
    )
    private String payType;
    /**
     * ???��??????��?ID
     **/
    @Column(name = "order_head_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String orderHeadId;
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ?????��????���
     **/
    @Column(name = "customer_name"
     ,length = 255
    
    
    
    )
    private String customerName;
    /**
     * ��??��?��??��ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "order_rmt_pkg_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String orderRmtPkgId;
    /**
     * ?????��???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ???��?????????��????0??��???????��?1��?��????????????2???????????????3??��??����?��?��?4?��???����?��?��?5?��??��?��?��?��?6??�C?????��
     **/
    @Column(name = "orign_status"
     ,length = 2
    
    
    
    )
    private String orignStatus;
    /**
     * ???������??�C???
     **/
    @Column(name = "sls_pmtn_id"
     ,length = 2
    
    
    
    )
    private String slsPmtnId;
    @Column(name = "rest_name"
     ,length = 255
    
    
    
    )
    private String restName;
    
    
    
    
    
    
    
    
    /**
     * ????��?��?����??
     **/
    @Column(name = "amount_paid"
     ,length = 100
    
    
    
    )
    private String amountPaid;
    /**
     * ?��????��???????��??�C??��
     **/
    @Column(name = "log_order_pkg_code"
     ,length = 36
    
    
    
    )
    private String logOrderPkgCode;
    /**
     * ?????��ID
     **/
    @Column(name = "customer_id"
     ,length = 36
    
    
    
    )
    private String customerId;
    /**
     * ?��???��
     **/
    @Column(name = "remark"
     ,length = 255
    
    
    
    )
    private String remark;
    /**
     * ???��?????????���
     **/
    @Column(name = "order_pkg_name"
     ,length = 64
    
    
    
    )
    private String orderPkgName;
    /**
     * ??????????��??��??��???�㨦??
     **/
    @Column(name = "goods_cat_qty"
     ,length = 100
    
    
    
    )
    private String goodsCatQty;
    /**
     * ???��???????��??�C??��
     **/
    @Column(name = "order_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderPkgCode;
    /**
     * ?��???��????��?(0????��?��?��???1??��?��???��???2?��??��???��)
     **/
    @Column(name = "storage_status"
     ,length = 20
    
    
    
    )
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
