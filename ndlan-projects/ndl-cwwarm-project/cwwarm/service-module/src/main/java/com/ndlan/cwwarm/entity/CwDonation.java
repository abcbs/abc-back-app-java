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
@Table(name = "cw_donation" )
public class CwDonation  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * 物理主键
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cw_donation_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cwDonationId;
    /**
     * 被捐赠联系电话
     **/
    @Column(name = "dn_donated_phone"
     ,length = 20
    
    
    
    )
    private String dnDonatedPhone;
    /**
     * 创建时间
     **/
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;
    /**
     * 生命状态【0删除、1正常】
     **/
    @Column(name = "dn_life_status"
     ,length = 20
    
    
    
    )
    private String dnLifeStatus;
    /**
     * 被捐赠人头像
     **/
    @Column(name = "dn_donated_pic"
     ,length = 36
    
    
    
    )
    private String dnDonatedPic;
    /**
     * 捐赠人机构名称
     **/
    @Column(name = "dn_donater_org_name"
     ,length = 255
    
    
    
    )
    private String dnDonaterOrgName;
    /**
     * 创建人
     **/
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * 被捐赠人机构ID
     **/
    @Column(name = "dn_donated_org"
     ,length = 36
    
    
    
    )
    private String dnDonatedOrg;
    /**
     * 捐赠人ID
     **/
    @Column(name = "dn_donater_id"
     ,length = 36
    
    
    
    )
    private String dnDonaterId;
    /**
     * 捐赠方式
     **/
    @Column(name = "dn_donation_way"
     ,length = 36
    
    
    
    )
    private String dnDonationWay;
    /**
     * 相关图片
     **/
    @Column(name = "dn_donation_pic"
     ,length = 20
    
    
    
    )
    private String dnDonationPic;
    /**
     * 扩展属性2
     **/
    @Column(name = "dn_attr2"
     ,length = 36
    
    
    
    )
    private String dnAttr2;
    /**
     * 被捐赠项目信息描述
     **/
    @Column(name = "dn_donated_detail_msg"
     ,length = 255
    
    
    
    )
    private String dnDonatedDetailMsg;
    /**
     * 捐赠人机构ID
     **/
    @Column(name = "dn_donater_org"
     ,length = 36
    
    
    
    )
    private String dnDonaterOrg;
    /**
     * 更新人
     **/
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    /**
     * 捐助分类【0捐助、1每日一捐、2捐赠求助】
     **/
    @Column(name = "cw_donation_type"
     ,length = 20
    
    
    
    )
    private String cwDonationType;
    /**
     * 被捐赠项目ID
     **/
    @Column(name = "dn_donated_msg_id"
     ,length = 36
    
    
    
    )
    private String dnDonatedMsgId;
    /**
     * 被捐赠人名称
     **/
    @Column(name = "dn_donated_name"
     ,length = 255
    
    
    
    )
    private String dnDonatedName;
    /**
     * 更新时间
     **/
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    /**
     * 捐赠者头像
     **/
    @Column(name = "dn_donater_pic"
     ,length = 36
    
    
    
    )
    private String dnDonaterPic;
    /**
     * 扩展属性1
     **/
    @Column(name = "dn_attr1"
     ,length = 36
    
    
    
    )
    private String dnAttr1;
    /**
     * 捐赠状态【0捐赠中、1已发货、2已收获】
     **/
    @Column(name = "dn_donation_status"
     ,length = 20
    
    
    
    )
    private String dnDonationStatus;
    /**
     * 捐助物品件数
     **/
    @Column(name = "dn_donate_goods_qty"
     ,length = 20
    
    
    
    )
    private String dnDonateGoodsQty;
    /**
     * 捐赠人名称
     **/
    @Column(name = "dn_donater_name"
     ,length = 255
    
    
    
    )
    private String dnDonaterName;
    /**
     * 扩展属性3
     **/
    @Column(name = "dn_attr3"
     ,length = 255
    
    
    
    )
    private String dnAttr3;
    /**
     * 被捐赠人ID
     **/
    @Column(name = "dn_donated_id"
     ,length = 36
    
    
    
    )
    private String dnDonatedId;
    /**
     * 被捐赠人机构名称
     **/
    @Column(name = "dn_donated_org_name"
     ,length = 255
    
    
    
    )
    private String dnDonatedOrgName;
    /**
     * 扩展属性4
     **/
    @Column(name = "dn_attr4"
     ,length = 255
    
    
    
    )
    private String dnAttr4;
    /**
     * 捐助物品
     **/
    @Column(name = "dn_donate_goods"
     ,length = 255
    
    
    
    )
    private String dnDonateGoods;
    /**
     * 被捐赠联系地址
     **/
    @Column(name = "dn_donated_addr"
     ,length = 255
    
    
    
    )
    private String dnDonatedAddr;
    /**
     * 捐赠描述
     **/
    @Column(name = "dn_donation_desc"
     ,length = 255
    
    
    
    )
    private String dnDonationDesc;
    /**
     * 扩展属性5
     **/
    @Column(name = "dn_attr5"
     ,length = 1000
    
    
    
    )
    private String dnAttr5;
    /**
     * 捐赠人联系方式
     **/
    @Column(name = "dn_donater_phone"
     ,length = 20
    
    
    
    )
    private String dnDonaterPhone;


   

    public void setCwDonationId(String cwDonationId) {
        this.cwDonationId = cwDonationId;
    }

    public String getCwDonationId() {
        return cwDonationId;
    }
    public void setDnDonatedPhone(String dnDonatedPhone) {
        this.dnDonatedPhone = dnDonatedPhone;
    }

    public String getDnDonatedPhone() {
        return dnDonatedPhone;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setDnLifeStatus(String dnLifeStatus) {
        this.dnLifeStatus = dnLifeStatus;
    }

    public String getDnLifeStatus() {
        return dnLifeStatus;
    }
    public void setDnDonatedPic(String dnDonatedPic) {
        this.dnDonatedPic = dnDonatedPic;
    }

    public String getDnDonatedPic() {
        return dnDonatedPic;
    }
    public void setDnDonaterOrgName(String dnDonaterOrgName) {
        this.dnDonaterOrgName = dnDonaterOrgName;
    }

    public String getDnDonaterOrgName() {
        return dnDonaterOrgName;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setDnDonatedOrg(String dnDonatedOrg) {
        this.dnDonatedOrg = dnDonatedOrg;
    }

    public String getDnDonatedOrg() {
        return dnDonatedOrg;
    }
    public void setDnDonaterId(String dnDonaterId) {
        this.dnDonaterId = dnDonaterId;
    }

    public String getDnDonaterId() {
        return dnDonaterId;
    }
    public void setDnDonationWay(String dnDonationWay) {
        this.dnDonationWay = dnDonationWay;
    }

    public String getDnDonationWay() {
        return dnDonationWay;
    }
    public void setDnDonationPic(String dnDonationPic) {
        this.dnDonationPic = dnDonationPic;
    }

    public String getDnDonationPic() {
        return dnDonationPic;
    }
    public void setDnAttr2(String dnAttr2) {
        this.dnAttr2 = dnAttr2;
    }

    public String getDnAttr2() {
        return dnAttr2;
    }
    public void setDnDonatedDetailMsg(String dnDonatedDetailMsg) {
        this.dnDonatedDetailMsg = dnDonatedDetailMsg;
    }

    public String getDnDonatedDetailMsg() {
        return dnDonatedDetailMsg;
    }
    public void setDnDonaterOrg(String dnDonaterOrg) {
        this.dnDonaterOrg = dnDonaterOrg;
    }

    public String getDnDonaterOrg() {
        return dnDonaterOrg;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }
    public void setCwDonationType(String cwDonationType) {
        this.cwDonationType = cwDonationType;
    }

    public String getCwDonationType() {
        return cwDonationType;
    }
    public void setDnDonatedMsgId(String dnDonatedMsgId) {
        this.dnDonatedMsgId = dnDonatedMsgId;
    }

    public String getDnDonatedMsgId() {
        return dnDonatedMsgId;
    }
    public void setDnDonatedName(String dnDonatedName) {
        this.dnDonatedName = dnDonatedName;
    }

    public String getDnDonatedName() {
        return dnDonatedName;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setDnDonaterPic(String dnDonaterPic) {
        this.dnDonaterPic = dnDonaterPic;
    }

    public String getDnDonaterPic() {
        return dnDonaterPic;
    }
    public void setDnAttr1(String dnAttr1) {
        this.dnAttr1 = dnAttr1;
    }

    public String getDnAttr1() {
        return dnAttr1;
    }
    public void setDnDonationStatus(String dnDonationStatus) {
        this.dnDonationStatus = dnDonationStatus;
    }

    public String getDnDonationStatus() {
        return dnDonationStatus;
    }
    public void setDnDonateGoodsQty(String dnDonateGoodsQty) {
        this.dnDonateGoodsQty = dnDonateGoodsQty;
    }

    public String getDnDonateGoodsQty() {
        return dnDonateGoodsQty;
    }
    public void setDnDonaterName(String dnDonaterName) {
        this.dnDonaterName = dnDonaterName;
    }

    public String getDnDonaterName() {
        return dnDonaterName;
    }
    public void setDnAttr3(String dnAttr3) {
        this.dnAttr3 = dnAttr3;
    }

    public String getDnAttr3() {
        return dnAttr3;
    }
    public void setDnDonatedId(String dnDonatedId) {
        this.dnDonatedId = dnDonatedId;
    }

    public String getDnDonatedId() {
        return dnDonatedId;
    }
    public void setDnDonatedOrgName(String dnDonatedOrgName) {
        this.dnDonatedOrgName = dnDonatedOrgName;
    }

    public String getDnDonatedOrgName() {
        return dnDonatedOrgName;
    }
    public void setDnAttr4(String dnAttr4) {
        this.dnAttr4 = dnAttr4;
    }

    public String getDnAttr4() {
        return dnAttr4;
    }
    public void setDnDonateGoods(String dnDonateGoods) {
        this.dnDonateGoods = dnDonateGoods;
    }

    public String getDnDonateGoods() {
        return dnDonateGoods;
    }
    public void setDnDonatedAddr(String dnDonatedAddr) {
        this.dnDonatedAddr = dnDonatedAddr;
    }

    public String getDnDonatedAddr() {
        return dnDonatedAddr;
    }
    public void setDnDonationDesc(String dnDonationDesc) {
        this.dnDonationDesc = dnDonationDesc;
    }

    public String getDnDonationDesc() {
        return dnDonationDesc;
    }
    public void setDnAttr5(String dnAttr5) {
        this.dnAttr5 = dnAttr5;
    }

    public String getDnAttr5() {
        return dnAttr5;
    }
    public void setDnDonaterPhone(String dnDonaterPhone) {
        this.dnDonaterPhone = dnDonaterPhone;
    }

    public String getDnDonaterPhone() {
        return dnDonaterPhone;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
