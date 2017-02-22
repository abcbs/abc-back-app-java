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
@Table(name = "b2b_supplier_card_account" )
public class B2bSupplierCardAccount  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remake"
     ,length = 255
    
    
    
    )
    private String remake;
    /**
     * ¨¨?¡±¨¨????¡¤
     **/
    @Column(name = "cat_no"
     ,length = 36
    
    
    
    )
    private String catNo;
    
    
    
    
    /**
     * ??????¨¦??¨¨?¡è???1??????0?????¡ë
     **/
    @Column(name = "is_default"
     ,length = 2
    
    
    
    )
    private String isDefault;
    
    
    
    
    /**
     * ¨¨?¡±????¨C????
     **/
    @Column(name = "phone_no"
     ,length = 20
    
    
    
    )
    private String phoneNo;
    /**
     * ?????????¨¨?????¨¨????¡¤
     **/
    @Column(name = "cardholder_id"
     ,length = 255
    
    
    
    )
    private String cardholderId;
    
    
    
    
    /**
     * ??????????¡®?¨¨???¢ã?card_no+cardholder_name?¢ã¡®
     **/
    @Column(name = "cardinfo_summay"
     ,length = 64
    
    
    
    )
    private String cardinfoSummay;
    /**
     * ???¨¨????????
     **/
    @Column(name = "branch"
     ,length = 255
    
    
    
    )
    private String branch;
    
    
    
    
    /**
     * ?¡±?¨¨????????
     **/
    @Column(name = "subbranch"
     ,length = 255
    
    
    
    )
    private String subbranch;
    /**
     * ¨¨????¡¤???¨¦¡±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "sup_card_acnt_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String supCardAcntId;
    /**
     * ??¢ã??¡¤¨¨??
     **/
    @Column(name = "bank"
     ,length = 255
    
    
    
    )
    private String bank;
    /**
     * ?????¡±???Id
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ?????¡¤
     **/
    @Column(name = "card_no"
     ,length = 36
    
    
    
    )
    private String cardNo;
    /**
     * ??????????¡ì¡°???
     **/
    @Column(name = "cardholder_name"
     ,length = 36
    
    
    
    )
    private String cardholderName;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "supplier_name"
     ,length = 36
    
    
    
    )
    private String supplierName;
    
    
    
    


   

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }
    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    public String getCatNo() {
        return catNo;
    }
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefault() {
        return isDefault;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    public void setCardholderId(String cardholderId) {
        this.cardholderId = cardholderId;
    }

    public String getCardholderId() {
        return cardholderId;
    }
    public void setCardinfoSummay(String cardinfoSummay) {
        this.cardinfoSummay = cardinfoSummay;
    }

    public String getCardinfoSummay() {
        return cardinfoSummay;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }
    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getSubbranch() {
        return subbranch;
    }
    public void setSupCardAcntId(String supCardAcntId) {
        this.supCardAcntId = supCardAcntId;
    }

    public String getSupCardAcntId() {
        return supCardAcntId;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }
    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardholderName() {
        return cardholderName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }


}
