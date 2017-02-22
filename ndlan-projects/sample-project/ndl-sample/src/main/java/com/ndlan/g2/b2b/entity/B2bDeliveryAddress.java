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
@Table(name = "b2b_delivery_address" )
public class B2bDeliveryAddress  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ?°Î¢„??°ß??°„???
     **/
    @Column(name = "region"
     ,length = 255
    
    
    
    )
    private String region;
    /**
     * ?°±?®®?°Ï???
     **/
    @Column(name = "receive_name"
     ,length = 36
    
    
    
    )
    private String receiveName;
    /**
     * ?°±?®®?°Ï???®®?°±????®C????
     **/
    @Column(name = "receive_phone"
     ,length = 11
    
    
    
    )
    private String receivePhone;
    
    
    
    
    /**
     * ®®???????°„??¢„
     **/
    @Column(name = "detail_address"
     ,length = 255
    
    
    
    )
    private String detailAddress;
    /**
     * ?°±?®®?°Ï????°±?®®??
     **/
    @Column(name = "receive_tellcall"
     ,length = 12
    
    
    
    )
    private String receiveTellcall;
    
    
    
    
    
    
    
    
    /**
     * ???®¶°±¢„???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ??°Æ®¶¢„???°„??¢„ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "delivery_address_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String deliveryAddressId;
    /**
     * ?°Ë???°ß
     **/
    @Column(name = "remake"
     ,length = 255
    
    
    
    )
    private String remake;
    /**
     * ???®¶°±¢„???????°Ï°„
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ??????®¶??®®?°Ë?°±?®®?°§??°„??¢„???1??????0?????°Î
     **/
    @Column(name = "is_default"
     ,length = 2
    
    
    
    )
    private String isDefault;
    /**
     * ®¶????®C
     **/
    @Column(name = "post_code"
     ,length = 36
    
    
    
    )
    private String postCode;
    
    
    
    
    
    
    
    


   

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveName() {
        return receiveName;
    }
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone() {
        return receivePhone;
    }
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }
    public void setReceiveTellcall(String receiveTellcall) {
        this.receiveTellcall = receiveTellcall;
    }

    public String getReceiveTellcall() {
        return receiveTellcall;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefault() {
        return isDefault;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }


}
