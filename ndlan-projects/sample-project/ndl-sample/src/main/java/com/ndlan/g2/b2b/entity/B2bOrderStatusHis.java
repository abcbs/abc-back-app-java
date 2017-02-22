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
@Table(name = "b2b_order_status_his" )
public class B2bOrderStatusHis  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    @Column(name = "orign_version"
    
    
    
    
    )
    private Long orignVersion;
    
    
    
    
    @Column(name = "orign_update_time"
    
    
    
    
    )
    private Date orignUpdateTime;
    
    
    
    
    /**
     * ¨¨????????????¢ã?
     **/
    @Column(name = "orign_status"
     ,length = 2
    
    
    
    )
    private String orignStatus;
    /**
     * ???????¡ª?¨¦¡ª????????¡ì¡¯??¡ì??¡ë
     **/
    @Column(name = "orign_create_time"
    
    
    
    
    )
    private Date orignCreateTime;
    /**
     * ????¨C¡ã?¡ª?¨¦¡ª????????¡ì¡¯??¡ì??¡ë
     **/
    @Column(name = "orign_update_by"
     ,length = 36
    
    
    
    )
    private String orignUpdateBy;
    
    
    
    
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    @Column(name = "order_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderPkgCode;
    /**
     * ¨¨???????¡­ID
     **/
    @Column(name = "order_pkg_id"
     ,length = 36
    
    
    
    )
    private String orderPkgId;
    /**
     * ?????????
     **/
    @Column(name = "orign_create_by"
     ,length = 36
    
    
    
    )
    private String orignCreateBy;
    @Column(name = "orign_syn_version"
    
    
    
    
    )
    private Long orignSynVersion;
    /**
     * ???¨¦¡±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "order_status_his_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String orderStatusHisId;
    
    
    
    
    /**
     * ?¡°???????¨¨?¡ã
     **/
    @Column(name = "update_reason"
     ,length = 255
    
    
    
    )
    private String updateReason;
    /**
     * ¨¨??????¡è?ID
     **/
    @Column(name = "order_head_id"
     ,length = 36
    
    
    
    )
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
