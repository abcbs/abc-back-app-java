package com.ndlan.cwwarm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import com.ndlan.canyin.base.entity.BaseEntity;
import java.util.List;
import java.util.Map;

import java.util.Date;


@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "cw_alert" )
public class CwAlert  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * 消息发送者头像
     **/
    @Column(name = "alert_by_pic"
     ,length = 36
    
    
    
    )
    private String alertByPic;
    /**
     * 消息来源分类【0系统通知、1收到捐助通知、2参与活动通知、3审核结果通知、4申请通知】
     **/
    @Column(name = "alert_source_type"
     ,length = 20
    
    
    
    )
    private String alertSourceType;
    /**
     * 物理主键
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cw_alert_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cwAlertId;
    /**
     * 通知人ID
     **/
    @Column(name = "alert_rcv"
     ,length = 36
    
    
    
    )
    private String alertRcv;
    /**
     * 消息发送者名称
     **/
    @Column(name = "alert_by_name"
     ,length = 255
    
    
    
    )
    private String alertByName;
    /**
     * 通知状态【0未处理、1已处理】
     **/
    @Column(name = "alert_busi_status"
     ,length = 20
    
    
    
    )
    private String alertBusiStatus;
    /**
     * 通知人名称
     **/
    @Column(name = "alert_rcv_name"
     ,length = 255
    
    
    
    )
    private String alertRcvName;
    /**
     * 更新时间
     **/
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    /**
     * 链接URI
     **/
    @Column(name = "alert_msg_uri"
     ,length = 255
    
    
    
    )
    private String alertMsgUri;
    /**
     * 创建人
     **/
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * 消息发送者ID
     **/
    @Column(name = "alert_by_id"
     ,length = 36
    
    
    
    )
    private String alertById;
    /**
     * 二级消息来源分类
     **/
    @Column(name = "alert_second_source_type"
     ,length = 20
    
    
    
    )
    private String alertSecondSourceType;
    /**
     * 通知人头像
     **/
    @Column(name = "alert_rcv_pic"
     ,length = 36
    
    
    
    )
    private String alertRcvPic;
    /**
     * 生命状态【0删除、1正常】
     **/
    @Column(name = "alert_life_status"
     ,length = 20
    
    
    
    )
    private String alertLifeStatus;
    /**
     * 更新人
     **/
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    /**
     * 通知消息
     **/
    @Column(name = "alert_msg"
     ,length = 255
    
    
    
    )
    private String alertMsg;
    /**
     * 创建时间
     **/
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;


   

    public void setAlertByPic(String alertByPic) {
        this.alertByPic = alertByPic;
    }

    public String getAlertByPic() {
        return alertByPic;
    }
    public void setAlertSourceType(String alertSourceType) {
        this.alertSourceType = alertSourceType;
    }

    public String getAlertSourceType() {
        return alertSourceType;
    }
    public void setCwAlertId(String cwAlertId) {
        this.cwAlertId = cwAlertId;
    }

    public String getCwAlertId() {
        return cwAlertId;
    }
    public void setAlertRcv(String alertRcv) {
        this.alertRcv = alertRcv;
    }

    public String getAlertRcv() {
        return alertRcv;
    }
    public void setAlertByName(String alertByName) {
        this.alertByName = alertByName;
    }

    public String getAlertByName() {
        return alertByName;
    }
    public void setAlertBusiStatus(String alertBusiStatus) {
        this.alertBusiStatus = alertBusiStatus;
    }

    public String getAlertBusiStatus() {
        return alertBusiStatus;
    }
    public void setAlertRcvName(String alertRcvName) {
        this.alertRcvName = alertRcvName;
    }

    public String getAlertRcvName() {
        return alertRcvName;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setAlertMsgUri(String alertMsgUri) {
        this.alertMsgUri = alertMsgUri;
    }

    public String getAlertMsgUri() {
        return alertMsgUri;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setAlertById(String alertById) {
        this.alertById = alertById;
    }

    public String getAlertById() {
        return alertById;
    }
    public void setAlertSecondSourceType(String alertSecondSourceType) {
        this.alertSecondSourceType = alertSecondSourceType;
    }

    public String getAlertSecondSourceType() {
        return alertSecondSourceType;
    }
    public void setAlertRcvPic(String alertRcvPic) {
        this.alertRcvPic = alertRcvPic;
    }

    public String getAlertRcvPic() {
        return alertRcvPic;
    }
    public void setAlertLifeStatus(String alertLifeStatus) {
        this.alertLifeStatus = alertLifeStatus;
    }

    public String getAlertLifeStatus() {
        return alertLifeStatus;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }
    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }

    public String getAlertMsg() {
        return alertMsg;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
