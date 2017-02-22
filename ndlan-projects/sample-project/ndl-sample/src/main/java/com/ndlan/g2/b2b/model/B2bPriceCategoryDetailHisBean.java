package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bPriceCategoryDetailHisBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?????¡¤
     **/
    private String price;
    /**
     * ????¨C¡ã?¡ª?¨¦¡ª?
     **/
    private Date orignUpdateTime;
    /**
     * ?????????
     **/
    private String orignCreateBy;
    /**
     * ??¡¤???????????????ID
     **/
    private String priCtyDtlhisId;
    /**
     * ????¨C¡ã???
     **/
    private String orignUpdateBy;
    /**
     * ??¡¤?????????ID
     **/
    private String priCatLineId;
    /**
     * ¨¨?¡¤??????
     **/
    private String startPointQyt;
    private Long orignSynVersion;
    /**
     * ??¡¤??????????¡è?ID
     **/
    private String priCatHeadId;
    /**
     * ?¡è???¡§
     **/
    private String remarks;
    /**
     * ?¡è¡À????¡ª????
     **/
    private Date expiryDate;
    private Long orignVersion;
    /**
     * ?¡±?????¡ª????
     **/
    private Date effectiveDate;
    /**
     * ???????¡ª?¨¦¡ª?
     **/
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
