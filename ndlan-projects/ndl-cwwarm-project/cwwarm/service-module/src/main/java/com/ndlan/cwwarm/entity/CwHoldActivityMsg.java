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
@Table(name = "cw_hold_activity_msg" )
public class CwHoldActivityMsg  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * 评论数量
     **/
    @Column(name = "ham_comment_qty"
     ,length = 20
    
    
    
    )
    private String hamCommentQty;
    /**
     * 项目状态【0草稿、1审批中、3审批拒绝、5报名中、6进行中、7结束】
     **/
    @Column(name = "ham_busi_status"
     ,length = 20
    
    
    
    )
    private String hamBusiStatus;
    /**
     * 项目概述
     **/
    @Column(name = "ham_busi_summary"
     ,length = 255
    
    
    
    )
    private String hamBusiSummary;
    /**
     * 相关图片
     **/
    @Column(name = "ham_pic"
     ,length = 20
    
    
    
    )
    private String hamPic;
    /**
     * 参与人数
     **/
    @Column(name = "ham_join_qty"
     ,length = 20
    
    
    
    )
    private String hamJoinQty;
    /**
     * 更新时间
     **/
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    /**
     * 更新人
     **/
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    /**
     * 发布者组织名称
     **/
    @Column(name = "ham_org_name"
     ,length = 255
    
    
    
    )
    private String hamOrgName;
    /**
     * 默认图片
     **/
    @Column(name = "ham_default_pic"
     ,length = 255
    
    
    
    )
    private String hamDefaultPic;
    /**
     * 物理主键
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cw_activity_msg_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cwActivityMsgId;
    /**
     * 活动地址
     **/
    @Column(name = "ham_busi_addr"
     ,length = 36
    
    
    
    )
    private String hamBusiAddr;
    /**
     * 创建时间
     **/
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;
    /**
     * 发布者组织ID
     **/
    @Column(name = "ham_org"
     ,length = 36
    
    
    
    )
    private String hamOrg;
    /**
     * 发布分类【0公益、1项目、2活动】
     **/
    @Column(name = "ham_type"
     ,length = 20
    
    
    
    )
    private String hamType;
    /**
     * 主办方
     **/
    @Column(name = "ham_sponsor"
     ,length = 36
    
    
    
    )
    private String hamSponsor;
    /**
     * 收到安心数量【针对项目】
     **/
    @Column(name = "ham_rcv_love_qty"
     ,length = 20
    
    
    
    )
    private String hamRcvLoveQty;
    /**
     * 发布者类型【0组织、1机构、2总部】
     **/
    @Column(name = "ham_by_type"
     ,length = 20
    
    
    
    )
    private String hamByType;
    /**
     * 活动开始时间
     **/
    @Column(name = "ham_busi_start_place"
    
    
    
    
    )
    private Date hamBusiStartPlace;
    /**
     * 项目类型
     **/
    @Column(name = "ham_busi_type"
     ,length = 36
    
    
    
    )
    private String hamBusiType;
    /**
     * 报名结束时间
     **/
    @Column(name = "ham_enroll_end_datetime"
    
    
    
    
    )
    private Date hamEnrollEndDatetime;
    /**
     * 活动详情
     **/
    @Column(name = "ham_busi_detail_msg"
     ,length = 255
    
    
    
    )
    private String hamBusiDetailMsg;
    /**
     * 发布者头像
     **/
    @Column(name = "ham_by_pic"
     ,length = 36
    
    
    
    )
    private String hamByPic;
    /**
     * 联系方式
     **/
    @Column(name = "ham_contact_phone"
     ,length = 255
    
    
    
    )
    private String hamContactPhone;
    /**
     * 生命状态【0删除、1正常】
     **/
    @Column(name = "ham_life_status"
     ,length = 20
    
    
    
    )
    private String hamLifeStatus;
    /**
     * 报名开始时间
     **/
    @Column(name = "ham_enroll_start_datetime"
    
    
    
    
    )
    private Date hamEnrollStartDatetime;
    /**
     * 发布者ID
     **/
    @Column(name = "ham_by"
     ,length = 36
    
    
    
    )
    private String hamBy;
    /**
     * 活动结束时间
     **/
    @Column(name = "ham_busi_end_place"
    
    
    
    
    )
    private Date hamBusiEndPlace;
    /**
     * 创建人
     **/
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * 收到物品数量【针对项目】
     **/
    @Column(name = "ham_rcv_goods_qty"
     ,length = 20
    
    
    
    )
    private String hamRcvGoodsQty;
    /**
     * 收藏数量
     **/
    @Column(name = "ham_collection_qty"
     ,length = 20
    
    
    
    )
    private String hamCollectionQty;
    /**
     * 发布者名称
     **/
    @Column(name = "ham_by_name"
     ,length = 255
    
    
    
    )
    private String hamByName;


   

    public void setHamCommentQty(String hamCommentQty) {
        this.hamCommentQty = hamCommentQty;
    }

    public String getHamCommentQty() {
        return hamCommentQty;
    }
    public void setHamBusiStatus(String hamBusiStatus) {
        this.hamBusiStatus = hamBusiStatus;
    }

    public String getHamBusiStatus() {
        return hamBusiStatus;
    }
    public void setHamBusiSummary(String hamBusiSummary) {
        this.hamBusiSummary = hamBusiSummary;
    }

    public String getHamBusiSummary() {
        return hamBusiSummary;
    }
    public void setHamPic(String hamPic) {
        this.hamPic = hamPic;
    }

    public String getHamPic() {
        return hamPic;
    }
    public void setHamJoinQty(String hamJoinQty) {
        this.hamJoinQty = hamJoinQty;
    }

    public String getHamJoinQty() {
        return hamJoinQty;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }
    public void setHamOrgName(String hamOrgName) {
        this.hamOrgName = hamOrgName;
    }

    public String getHamOrgName() {
        return hamOrgName;
    }
    public void setHamDefaultPic(String hamDefaultPic) {
        this.hamDefaultPic = hamDefaultPic;
    }

    public String getHamDefaultPic() {
        return hamDefaultPic;
    }
    public void setCwActivityMsgId(String cwActivityMsgId) {
        this.cwActivityMsgId = cwActivityMsgId;
    }

    public String getCwActivityMsgId() {
        return cwActivityMsgId;
    }
    public void setHamBusiAddr(String hamBusiAddr) {
        this.hamBusiAddr = hamBusiAddr;
    }

    public String getHamBusiAddr() {
        return hamBusiAddr;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setHamOrg(String hamOrg) {
        this.hamOrg = hamOrg;
    }

    public String getHamOrg() {
        return hamOrg;
    }
    public void setHamType(String hamType) {
        this.hamType = hamType;
    }

    public String getHamType() {
        return hamType;
    }
    public void setHamSponsor(String hamSponsor) {
        this.hamSponsor = hamSponsor;
    }

    public String getHamSponsor() {
        return hamSponsor;
    }
    public void setHamRcvLoveQty(String hamRcvLoveQty) {
        this.hamRcvLoveQty = hamRcvLoveQty;
    }

    public String getHamRcvLoveQty() {
        return hamRcvLoveQty;
    }
    public void setHamByType(String hamByType) {
        this.hamByType = hamByType;
    }

    public String getHamByType() {
        return hamByType;
    }
    public void setHamBusiStartPlace(Date hamBusiStartPlace) {
        this.hamBusiStartPlace = hamBusiStartPlace;
    }

    public Date getHamBusiStartPlace() {
        return hamBusiStartPlace;
    }
    public void setHamBusiType(String hamBusiType) {
        this.hamBusiType = hamBusiType;
    }

    public String getHamBusiType() {
        return hamBusiType;
    }
    public void setHamEnrollEndDatetime(Date hamEnrollEndDatetime) {
        this.hamEnrollEndDatetime = hamEnrollEndDatetime;
    }

    public Date getHamEnrollEndDatetime() {
        return hamEnrollEndDatetime;
    }
    public void setHamBusiDetailMsg(String hamBusiDetailMsg) {
        this.hamBusiDetailMsg = hamBusiDetailMsg;
    }

    public String getHamBusiDetailMsg() {
        return hamBusiDetailMsg;
    }
    public void setHamByPic(String hamByPic) {
        this.hamByPic = hamByPic;
    }

    public String getHamByPic() {
        return hamByPic;
    }
    public void setHamContactPhone(String hamContactPhone) {
        this.hamContactPhone = hamContactPhone;
    }

    public String getHamContactPhone() {
        return hamContactPhone;
    }
    public void setHamLifeStatus(String hamLifeStatus) {
        this.hamLifeStatus = hamLifeStatus;
    }

    public String getHamLifeStatus() {
        return hamLifeStatus;
    }
    public void setHamEnrollStartDatetime(Date hamEnrollStartDatetime) {
        this.hamEnrollStartDatetime = hamEnrollStartDatetime;
    }

    public Date getHamEnrollStartDatetime() {
        return hamEnrollStartDatetime;
    }
    public void setHamBy(String hamBy) {
        this.hamBy = hamBy;
    }

    public String getHamBy() {
        return hamBy;
    }
    public void setHamBusiEndPlace(Date hamBusiEndPlace) {
        this.hamBusiEndPlace = hamBusiEndPlace;
    }

    public Date getHamBusiEndPlace() {
        return hamBusiEndPlace;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setHamRcvGoodsQty(String hamRcvGoodsQty) {
        this.hamRcvGoodsQty = hamRcvGoodsQty;
    }

    public String getHamRcvGoodsQty() {
        return hamRcvGoodsQty;
    }
    public void setHamCollectionQty(String hamCollectionQty) {
        this.hamCollectionQty = hamCollectionQty;
    }

    public String getHamCollectionQty() {
        return hamCollectionQty;
    }
    public void setHamByName(String hamByName) {
        this.hamByName = hamByName;
    }

    public String getHamByName() {
        return hamByName;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
