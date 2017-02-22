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
@Table(name = "cw_for_help_msg" )
public class CwForHelpMsg  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * 联系地址
     **/
    @Column(name = "fhb_contact_addr"
     ,length = 255
    
    
    
    )
    private String fhbContactAddr;
    /**
     * 联系电话
     **/
    @Column(name = "fhb_contact_phone"
     ,length = 36
    
    
    
    )
    private String fhbContactPhone;
    /**
     * 求助者类型【0组织、1个人、2志愿者、3机构】
     **/
    @Column(name = "for_help_by_type"
     ,length = 20
    
    
    
    )
    private String forHelpByType;
    /**
     * 参与人数
     **/
    @Column(name = "fhb_join_qty"
     ,length = 20
    
    
    
    )
    private String fhbJoinQty;
    /**
     * 创建时间
     **/
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;
    /**
     * 求助者组织ID【志愿者、个人此信息为空】
     **/
    @Column(name = "for_help_org"
     ,length = 36
    
    
    
    )
    private String forHelpOrg;
    /**
     * 创建人
     **/
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * 默认图片
     **/
    @Column(name = "fhb_default_pic"
     ,length = 255
    
    
    
    )
    private String fhbDefaultPic;
    /**
     * 收到物品数量
     **/
    @Column(name = "fhb_rcv_goods_qty"
     ,length = 20
    
    
    
    )
    private String fhbRcvGoodsQty;
    /**
     * 求助分类【0走失、1物品丢失、2物品求助】
     **/
    @Column(name = "fhb_msg_type"
     ,length = 20
    
    
    
    )
    private String fhbMsgType;
    /**
     * 联系人
     **/
    @Column(name = "fhb_contact"
     ,length = 36
    
    
    
    )
    private String fhbContact;
    /**
     * 求助者ID
     **/
    @Column(name = "for_help_by"
     ,length = 36
    
    
    
    )
    private String forHelpBy;
    /**
     * 收到爱心数量
     **/
    @Column(name = "fhb_rcv_love_qty"
     ,length = 20
    
    
    
    )
    private String fhbRcvLoveQty;
    /**
     * 状态【0草稿、1审批中、2活动、3审批拒绝、4截止】
     **/
    @Column(name = "fhb_status"
     ,length = 20
    
    
    
    )
    private String fhbStatus;
    /**
     * 更新人
     **/
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    /**
     * 更新时间
     **/
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    /**
     * 相关图片
     **/
    @Column(name = "fhb_pic"
     ,length = 255
    
    
    
    )
    private String fhbPic;
    /**
     * 求助事物类型【性别】
     **/
    @Column(name = "for_help_busi_type"
     ,length = 36
    
    
    
    )
    private String forHelpBusiType;
    /**
     * 发生时间【走失时间】
     **/
    @Column(name = "fhb_occur_datetime"
    
    
    
    
    )
    private Date fhbOccurDatetime;
    /**
     * 收藏数量
     **/
    @Column(name = "fhb_collection_qty"
     ,length = 20
    
    
    
    )
    private String fhbCollectionQty;
    /**
     * 求助类型
     **/
    @Column(name = "for_help_type"
     ,length = 36
    
    
    
    )
    private String forHelpType;
    /**
     * 求助事物【走失人姓名】
     **/
    @Column(name = "for_help_busi"
     ,length = 255
    
    
    
    )
    private String forHelpBusi;
    /**
     * 发生地点【走失地点】
     **/
    @Column(name = "fhb_occur_place"
     ,length = 255
    
    
    
    )
    private String fhbOccurPlace;
    /**
     * 求助者名称
     **/
    @Column(name = "for_help_by_name"
     ,length = 255
    
    
    
    )
    private String forHelpByName;
    /**
     * 评论数量
     **/
    @Column(name = "fhb_comment_qty"
     ,length = 20
    
    
    
    )
    private String fhbCommentQty;
    /**
     * 物理主键
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cw_help_msg_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cwHelpMsgId;
    /**
     * 求助者组织名称【志愿者、个人此信息为空】
     **/
    @Column(name = "for_help_org_name"
     ,length = 255
    
    
    
    )
    private String forHelpOrgName;
    /**
     * 详细信息描述
     **/
    @Column(name = "fhb_detail_msg"
     ,length = 255
    
    
    
    )
    private String fhbDetailMsg;
    /**
     * 生命状态【0删除、1正常】
     **/
    @Column(name = "fhb_life_status"
     ,length = 20
    
    
    
    )
    private String fhbLifeStatus;
    /**
     * 求助者头像
     **/
    @Column(name = "for_help_by_pic"
     ,length = 36
    
    
    
    )
    private String forHelpByPic;


   

    public void setFhbContactAddr(String fhbContactAddr) {
        this.fhbContactAddr = fhbContactAddr;
    }

    public String getFhbContactAddr() {
        return fhbContactAddr;
    }
    public void setFhbContactPhone(String fhbContactPhone) {
        this.fhbContactPhone = fhbContactPhone;
    }

    public String getFhbContactPhone() {
        return fhbContactPhone;
    }
    public void setForHelpByType(String forHelpByType) {
        this.forHelpByType = forHelpByType;
    }

    public String getForHelpByType() {
        return forHelpByType;
    }
    public void setFhbJoinQty(String fhbJoinQty) {
        this.fhbJoinQty = fhbJoinQty;
    }

    public String getFhbJoinQty() {
        return fhbJoinQty;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setForHelpOrg(String forHelpOrg) {
        this.forHelpOrg = forHelpOrg;
    }

    public String getForHelpOrg() {
        return forHelpOrg;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setFhbDefaultPic(String fhbDefaultPic) {
        this.fhbDefaultPic = fhbDefaultPic;
    }

    public String getFhbDefaultPic() {
        return fhbDefaultPic;
    }
    public void setFhbRcvGoodsQty(String fhbRcvGoodsQty) {
        this.fhbRcvGoodsQty = fhbRcvGoodsQty;
    }

    public String getFhbRcvGoodsQty() {
        return fhbRcvGoodsQty;
    }
    public void setFhbMsgType(String fhbMsgType) {
        this.fhbMsgType = fhbMsgType;
    }

    public String getFhbMsgType() {
        return fhbMsgType;
    }
    public void setFhbContact(String fhbContact) {
        this.fhbContact = fhbContact;
    }

    public String getFhbContact() {
        return fhbContact;
    }
    public void setForHelpBy(String forHelpBy) {
        this.forHelpBy = forHelpBy;
    }

    public String getForHelpBy() {
        return forHelpBy;
    }
    public void setFhbRcvLoveQty(String fhbRcvLoveQty) {
        this.fhbRcvLoveQty = fhbRcvLoveQty;
    }

    public String getFhbRcvLoveQty() {
        return fhbRcvLoveQty;
    }
    public void setFhbStatus(String fhbStatus) {
        this.fhbStatus = fhbStatus;
    }

    public String getFhbStatus() {
        return fhbStatus;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setFhbPic(String fhbPic) {
        this.fhbPic = fhbPic;
    }

    public String getFhbPic() {
        return fhbPic;
    }
    public void setForHelpBusiType(String forHelpBusiType) {
        this.forHelpBusiType = forHelpBusiType;
    }

    public String getForHelpBusiType() {
        return forHelpBusiType;
    }
    public void setFhbOccurDatetime(Date fhbOccurDatetime) {
        this.fhbOccurDatetime = fhbOccurDatetime;
    }

    public Date getFhbOccurDatetime() {
        return fhbOccurDatetime;
    }
    public void setFhbCollectionQty(String fhbCollectionQty) {
        this.fhbCollectionQty = fhbCollectionQty;
    }

    public String getFhbCollectionQty() {
        return fhbCollectionQty;
    }
    public void setForHelpType(String forHelpType) {
        this.forHelpType = forHelpType;
    }

    public String getForHelpType() {
        return forHelpType;
    }
    public void setForHelpBusi(String forHelpBusi) {
        this.forHelpBusi = forHelpBusi;
    }

    public String getForHelpBusi() {
        return forHelpBusi;
    }
    public void setFhbOccurPlace(String fhbOccurPlace) {
        this.fhbOccurPlace = fhbOccurPlace;
    }

    public String getFhbOccurPlace() {
        return fhbOccurPlace;
    }
    public void setForHelpByName(String forHelpByName) {
        this.forHelpByName = forHelpByName;
    }

    public String getForHelpByName() {
        return forHelpByName;
    }
    public void setFhbCommentQty(String fhbCommentQty) {
        this.fhbCommentQty = fhbCommentQty;
    }

    public String getFhbCommentQty() {
        return fhbCommentQty;
    }
    public void setCwHelpMsgId(String cwHelpMsgId) {
        this.cwHelpMsgId = cwHelpMsgId;
    }

    public String getCwHelpMsgId() {
        return cwHelpMsgId;
    }
    public void setForHelpOrgName(String forHelpOrgName) {
        this.forHelpOrgName = forHelpOrgName;
    }

    public String getForHelpOrgName() {
        return forHelpOrgName;
    }
    public void setFhbDetailMsg(String fhbDetailMsg) {
        this.fhbDetailMsg = fhbDetailMsg;
    }

    public String getFhbDetailMsg() {
        return fhbDetailMsg;
    }
    public void setFhbLifeStatus(String fhbLifeStatus) {
        this.fhbLifeStatus = fhbLifeStatus;
    }

    public String getFhbLifeStatus() {
        return fhbLifeStatus;
    }
    public void setForHelpByPic(String forHelpByPic) {
        this.forHelpByPic = forHelpByPic;
    }

    public String getForHelpByPic() {
        return forHelpByPic;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
