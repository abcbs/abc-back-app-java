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
@Table(name = "b2b_supplier_agency" )
public class B2bSupplierAgency  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    
    
    
    
    /**
     * ?????°±???????°Ï°„
     **/
    @Column(name = "supplier_name"
     ,length = 36
    
    
    
    )
    private String supplierName;
    
    
    
    
    /**
     * ??°Æ®®?°Ï??°„??¢„
     **/
    @Column(name = "delivery_address"
     ,length = 255
    
    
    
    )
    private String deliveryAddress;
    
    
    
    
    /**
     * ??°Æ®®?°Ï??°„??¢„ID
     **/
    @Column(name = "send_address_id"
     ,length = 36
    
    
    
    )
    private String sendAddressId;
    /**
     * ®®?°±??????
     **/
    @Column(name = "contact_user"
     ,length = 255
    
    
    
    )
    private String contactUser;
    /**
     * ???®¶°±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "supplier_agency_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String supplierAgencyId;
    
    
    
    
    /**
     * ???®¶°±¢„???ID
     **/
    @Column(name = "agency_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String agencyId;
    /**
     * ???®¶°±¢„???????°Ï°„
     **/
    @Column(name = "agency_name"
     ,length = 36
    
    
    
    )
    private String agencyName;
    
    
    
    
    /**
     * ?°Ë???°ß
     **/
    @Column(name = "remake"
     ,length = 255
    
    
    
    )
    private String remake;
    /**
     * ®®?°±???????°±?®®??
     **/
    @Column(name = "contact_phone"
     ,length = 36
    
    
    
    )
    private String contactPhone;
    /**
     * ?????°±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String supplierId;


   

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setSendAddressId(String sendAddressId) {
        this.sendAddressId = sendAddressId;
    }

    public String getSendAddressId() {
        return sendAddressId;
    }
    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getContactUser() {
        return contactUser;
    }
    public void setSupplierAgencyId(String supplierAgencyId) {
        this.supplierAgencyId = supplierAgencyId;
    }

    public String getSupplierAgencyId() {
        return supplierAgencyId;
    }
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyId() {
        return agencyId;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyName() {
        return agencyName;
    }
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }


}
