package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import com.ndlan.framework.core.api.Identifiable;
import java.util.Date;

public class CwDonationBean extends DefaultBeanIdentifiable{
	private static final long serialVersionUID =-1;
	
    /**
     * 物理主键
     **/
    private String cwDonationId;
    /**
     * 被捐赠联系电话
     **/
    private String dnDonatedPhone;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 生命状态【0删除、1正常】
     **/
    private String dnLifeStatus;
    /**
     * 被捐赠人头像
     **/
    private String dnDonatedPic;
    /**
     * 捐赠人机构名称
     **/
    private String dnDonaterOrgName;
    /**
     * 创建人
     **/
    private String createBy;
    /**
     * 被捐赠人机构ID
     **/
    private String dnDonatedOrg;
    /**
     * 捐赠人ID
     **/
    private String dnDonaterId;
    /**
     * 捐赠方式
     **/
    private String dnDonationWay;
    /**
     * 相关图片
     **/
    private String dnDonationPic;
    /**
     * 扩展属性2
     **/
    private String dnAttr2;
    /**
     * 被捐赠项目信息描述
     **/
    private String dnDonatedDetailMsg;
    /**
     * 捐赠人机构ID
     **/
    private String dnDonaterOrg;
    /**
     * 更新人
     **/
    private String updateBy;
    /**
     * 捐助分类【0捐助、1每日一捐、2捐赠求助】
     **/
    private String cwDonationType;
    /**
     * 被捐赠项目ID
     **/
    private String dnDonatedMsgId;
    /**
     * 被捐赠人名称
     **/
    private String dnDonatedName;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 捐赠者头像
     **/
    private String dnDonaterPic;
    /**
     * 扩展属性1
     **/
    private String dnAttr1;
    /**
     * 捐赠状态【0捐赠中、1已发货、2已收获】
     **/
    private String dnDonationStatus;
    /**
     * 捐助物品件数
     **/
    private String dnDonateGoodsQty;
    /**
     * 捐赠人名称
     **/
    private String dnDonaterName;
    /**
     * 扩展属性3
     **/
    private String dnAttr3;
    /**
     * 被捐赠人ID
     **/
    private String dnDonatedId;
    /**
     * 被捐赠人机构名称
     **/
    private String dnDonatedOrgName;
    /**
     * 扩展属性4
     **/
    private String dnAttr4;
    /**
     * 捐助物品
     **/
    private String dnDonateGoods;
    /**
     * 被捐赠联系地址
     **/
    private String dnDonatedAddr;
    /**
     * 捐赠描述
     **/
    private String dnDonationDesc;
    /**
     * 扩展属性5
     **/
    private String dnAttr5;
    /**
     * 捐赠人联系方式
     **/
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
	public String getDefaultId() {
		// TODO Auto-generated method stub
	     return cwDonationId;
        }

	@Override
	public void setDefaultId(String cwDonationId) {
	     this.cwDonationId=cwDonationId;
       }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
