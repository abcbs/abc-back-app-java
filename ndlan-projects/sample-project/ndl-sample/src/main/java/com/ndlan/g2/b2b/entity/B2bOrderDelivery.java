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
@Table(name = "b2b_order_delivery" )
public class B2bOrderDelivery  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    
    
    
    
    /**
     * ??°Æ®¶¢„???°„??¢„ID
     **/
    @Column(name = "delivery_address_id"
     ,length = 36
    
    
    
    )
    private String deliveryAddressId;
    /**
     * ?°Î¢„??°ß??°„???
     **/
    @Column(name = "region"
     ,length = 255
    
    
    
    )
    private String region;
    /**
     * ?°±?®®?°Ï????°±?®®??
     **/
    @Column(name = "receive_tellcall"
     ,length = 12
    
    
    
    )
    private String receiveTellcall;
    /**
     * ?????°±???????°Ï°„
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ?°±?®®?°Ï???
     **/
    @Column(name = "receive_name"
     ,length = 36
    
    
    
    )
    private String receiveName;
    
    
    
    
    
    
    
    
    /**
     * ®®?????®¶°≠?®¶¢„?ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "order_delivery_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String orderDeliveryId;
    /**
     * ???®¶°±¢„???ID
     **/
    @Column(name = "custom_id"
     ,length = 36
    
    
    
    )
    private String customId;
    /**
     * ®®???????°„??¢„
     **/
    @Column(name = "detail_address"
     ,length = 255
    
    
    
    )
    private String detailAddress;
    /**
     * ?????°±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ?°Ë???°ß
     **/
    @Column(name = "remake"
     ,length = 255
    
    
    
    )
    private String remake;
    /**
     * ?°±?®®?°Ï???®®?°±????®C????
     **/
    @Column(name = "receive_phone"
     ,length = 11
    
    
    
    )
    private String receivePhone;
    /**
     * ???®¶°±¢„???????°Ï°„
     **/
    @Column(name = "custom_name"
     ,length = 255
    
    
    
    )
    private String customName;
    /**
     * ®®???????°≠ID
     **/
    @Column(name = "order_pkg_id"
     ,length = 36
    
    
    
    )
    private String orderPkgId;
    /**
     * ®®???????°≠??®C??°§
     **/
    @Column(name = "order_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderPkgCode;
    
    
    
    
    /**
     * ®¶????®C
     **/
    @Column(name = "post_code"
     ,length = 36
    
    
    
    )
    private String postCode;
    
    
    
    


   

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
    public void setReceiveTellcall(String receiveTellcall) {
        this.receiveTellcall = receiveTellcall;
    }

    public String getReceiveTellcall() {
        return receiveTellcall;
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
    public void setOrderDeliveryId(String orderDeliveryId) {
        this.orderDeliveryId = orderDeliveryId;
    }

    public String getOrderDeliveryId() {
        return orderDeliveryId;
    }
    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomId() {
        return customId;
    }
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone() {
        return receivePhone;
    }
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomName() {
        return customName;
    }
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }


}
