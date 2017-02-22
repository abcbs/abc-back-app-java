package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bOrderStatusHisBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    private Long orignVersion;
    private Date orignUpdateTime;
    /**
     * ¨¨????????????¢ã?
     **/
    private String orignStatus;
    /**
     * ???????¡ª?¨¦¡ª????????¡ì¡¯??¡ì??¡ë
     **/
    private Date orignCreateTime;
    /**
     * ????¨C¡ã?¡ª?¨¦¡ª????????¡ì¡¯??¡ì??¡ë
     **/
    private String orignUpdateBy;
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    private String orderPkgCode;
    /**
     * ¨¨???????¡­ID
     **/
    private String orderPkgId;
    /**
     * ?????????
     **/
    private String orignCreateBy;
    private Long orignSynVersion;
    /**
     * ???¨¦¡±?
     **/
    private String orderStatusHisId;
    /**
     * ?¡°???????¨¨?¡ã
     **/
    private String updateReason;
    /**
     * ¨¨??????¡è?ID
     **/
    private String orderHeadId;


  
	
    public void setOrignVersion(Long orignVersion) {
        this.orignVersion = orignVersion;
    }

    public Long getOrignVersion() {
        return orignVersion;
    }

	

	
    public void setOrignUpdateTime(Date orignUpdateTime) {
        this.orignUpdateTime = orignUpdateTime;
    }

    public Date getOrignUpdateTime() {
        return orignUpdateTime;
    }

	

	
    public void setOrignStatus(String orignStatus) {
        this.orignStatus = orignStatus;
    }

    public String getOrignStatus() {
        return orignStatus;
    }

	
    public void setOrignCreateTime(Date orignCreateTime) {
        this.orignCreateTime = orignCreateTime;
    }

    public Date getOrignCreateTime() {
        return orignCreateTime;
    }

	
    public void setOrignUpdateBy(String orignUpdateBy) {
        this.orignUpdateBy = orignUpdateBy;
    }

    public String getOrignUpdateBy() {
        return orignUpdateBy;
    }

	

	
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }

	
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }

	
    public void setOrignCreateBy(String orignCreateBy) {
        this.orignCreateBy = orignCreateBy;
    }

    public String getOrignCreateBy() {
        return orignCreateBy;
    }

	
    public void setOrignSynVersion(Long orignSynVersion) {
        this.orignSynVersion = orignSynVersion;
    }

    public Long getOrignSynVersion() {
        return orignSynVersion;
    }

	
    public void setOrderStatusHisId(String orderStatusHisId) {
        this.orderStatusHisId = orderStatusHisId;
    }

    public String getOrderStatusHisId() {
        return orderStatusHisId;
    }

	

	
    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }

    public String getUpdateReason() {
        return updateReason;
    }

	
    public void setOrderHeadId(String orderHeadId) {
        this.orderHeadId = orderHeadId;
    }

    public String getOrderHeadId() {
        return orderHeadId;
    }

	



}
