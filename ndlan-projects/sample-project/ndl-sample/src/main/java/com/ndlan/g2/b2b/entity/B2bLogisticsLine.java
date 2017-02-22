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
@Table(name = "b2b_logistics_line" )
public class B2bLogisticsLine  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ?????��???????���
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ??����?��??��??��id
     **/
    @Column(name = "delivery_address_id"
     ,length = 36
    
    
    
    )
    private String deliveryAddressId;
    
    
    
    
    /**
     * ?��????ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "logistics_line_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String logisticsLineId;
    /**
     * ����?��?��??��?��??��?����?
     **/
    @Column(name = "delivery_start_date"
    
    
    
    
    )
    private Date deliveryStartDate;
    /**
     * ?��??????�C??��
     **/
    @Column(name = "logistics_line_code"
     ,length = 36
    
    
    
    )
    private String logisticsLineCode;
    /**
     * ??����?��??��??��
     **/
    @Column(name = "delivery_address"
     ,length = 255
    
    
    
    )
    private String deliveryAddress;
    /**
     * ?????��???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    
    
    
    
    /**
     * ����?��?��???��?��????�C????
     **/
    @Column(name = "delivery_user_phone"
     ,length = 11
    
    
    
    )
    private String deliveryUserPhone;
    /**
     * ����?��?��??��????��?����?
     **/
    @Column(name = "delivery_end_date"
    
    
    
    
    )
    private Date deliveryEndDate;
    /**
     * ?��???��
     **/
    @Column(name = "remake"
     ,length = 255
    
    
    
    )
    private String remake;
    @Column(name = "logistics_line_name"
     ,length = 255
    
    
    
    )
    private String logisticsLineName;
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * ����?��?��???
     **/
    @Column(name = "delivery_user"
     ,length = 36
    
    
    
    )
    private String deliveryUser;


   

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }
    public void setLogisticsLineId(String logisticsLineId) {
        this.logisticsLineId = logisticsLineId;
    }

    public String getLogisticsLineId() {
        return logisticsLineId;
    }
    public void setDeliveryStartDate(Date deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public Date getDeliveryStartDate() {
        return deliveryStartDate;
    }
    public void setLogisticsLineCode(String logisticsLineCode) {
        this.logisticsLineCode = logisticsLineCode;
    }

    public String getLogisticsLineCode() {
        return logisticsLineCode;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setDeliveryUserPhone(String deliveryUserPhone) {
        this.deliveryUserPhone = deliveryUserPhone;
    }

    public String getDeliveryUserPhone() {
        return deliveryUserPhone;
    }
    public void setDeliveryEndDate(Date deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
    }

    public Date getDeliveryEndDate() {
        return deliveryEndDate;
    }
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }
    public void setLogisticsLineName(String logisticsLineName) {
        this.logisticsLineName = logisticsLineName;
    }

    public String getLogisticsLineName() {
        return logisticsLineName;
    }
    public void setDeliveryUser(String deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public String getDeliveryUser() {
        return deliveryUser;
    }


}
