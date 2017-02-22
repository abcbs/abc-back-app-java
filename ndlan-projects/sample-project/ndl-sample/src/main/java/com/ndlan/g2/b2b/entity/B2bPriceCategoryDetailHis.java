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
@Table(name = "b2b_price_category_detail_his" )
public class B2bPriceCategoryDetailHis  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ?????¡¤
     **/
    @Column(name = "price"
     ,length = 100
    
    
    
    )
    private String price;
    /**
     * ????¨C¡ã?¡ª?¨¦¡ª?
     **/
    @Column(name = "orign_update_time"
    
    
    
    
    )
    private Date orignUpdateTime;
    
    
    
    
    
    
    
    
    /**
     * ?????????
     **/
    @Column(name = "orign_create_by"
     ,length = 36
    
    
    
    )
    private String orignCreateBy;
    /**
     * ??¡¤???????????????ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "pri_cty_dtlhis_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String priCtyDtlhisId;
    /**
     * ????¨C¡ã???
     **/
    @Column(name = "orign_update_by"
     ,length = 36
    
    
    
    )
    private String orignUpdateBy;
    /**
     * ??¡¤?????????ID
     **/
    @Column(name = "pri_cat_line_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String priCatLineId;
    
    
    
    
    /**
     * ¨¨?¡¤??????
     **/
    @Column(name = "start_point_qyt"
     ,length = 100
    
    
    
    )
    private String startPointQyt;
    @Column(name = "orign_syn_version"
    
    
    
    
    )
    private Long orignSynVersion;
    
    
    
    
    /**
     * ??¡¤??????????¡è?ID
     **/
    @Column(name = "pri_cat_head_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String priCatHeadId;
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remarks"
     ,length = 255
    
    
    
    )
    private String remarks;
    /**
     * ?¡è¡À????¡ª????
     **/
    @Column(name = "expiry_date"
    
    
    
    
    )
    private Date expiryDate;
    
    
    
    
    @Column(name = "orign_version"
    
    
    
    
    )
    private Long orignVersion;
    /**
     * ?¡±?????¡ª????
     **/
    @Column(name = "effective_date"
    
    
    
    
    )
    private Date effectiveDate;
    /**
     * ???????¡ª?¨¦¡ª?
     **/
    @Column(name = "orign_create_time"
    
    
    
    
    )
    private Date orignCreateTime;


   

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    public void setOrignUpdateTime(Date orignUpdateTime) {
        this.orignUpdateTime = orignUpdateTime;
    }

    public Date getOrignUpdateTime() {
        return orignUpdateTime;
    }
    public void setOrignCreateBy(String orignCreateBy) {
        this.orignCreateBy = orignCreateBy;
    }

    public String getOrignCreateBy() {
        return orignCreateBy;
    }
    public void setPriCtyDtlhisId(String priCtyDtlhisId) {
        this.priCtyDtlhisId = priCtyDtlhisId;
    }

    public String getPriCtyDtlhisId() {
        return priCtyDtlhisId;
    }
    public void setOrignUpdateBy(String orignUpdateBy) {
        this.orignUpdateBy = orignUpdateBy;
    }

    public String getOrignUpdateBy() {
        return orignUpdateBy;
    }
    public void setPriCatLineId(String priCatLineId) {
        this.priCatLineId = priCatLineId;
    }

    public String getPriCatLineId() {
        return priCatLineId;
    }
    public void setStartPointQyt(String startPointQyt) {
        this.startPointQyt = startPointQyt;
    }

    public String getStartPointQyt() {
        return startPointQyt;
    }
    public void setOrignSynVersion(Long orignSynVersion) {
        this.orignSynVersion = orignSynVersion;
    }

    public Long getOrignSynVersion() {
        return orignSynVersion;
    }
    public void setPriCatHeadId(String priCatHeadId) {
        this.priCatHeadId = priCatHeadId;
    }

    public String getPriCatHeadId() {
        return priCatHeadId;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setOrignVersion(Long orignVersion) {
        this.orignVersion = orignVersion;
    }

    public Long getOrignVersion() {
        return orignVersion;
    }
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
    public void setOrignCreateTime(Date orignCreateTime) {
        this.orignCreateTime = orignCreateTime;
    }

    public Date getOrignCreateTime() {
        return orignCreateTime;
    }


}
