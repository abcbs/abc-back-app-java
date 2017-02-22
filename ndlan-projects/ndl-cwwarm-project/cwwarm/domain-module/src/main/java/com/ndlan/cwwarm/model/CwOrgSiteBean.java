package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import com.ndlan.framework.core.api.Identifiable;
import java.util.Date;

public class CwOrgSiteBean extends DefaultBeanIdentifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 生命状态【0删除、1正常】
     **/
    private String ostLifeStatus;
    /**
     * 站点地址市
     **/
    private String ostSiteAddrCity;
    /**
     * 联系人电话
     **/
    private String ostSiteContactsPhone;
    /**
     * 站点状态【0不可用、1可用】
     **/
    private String ostBusiStatus;
    /**
     * 站点名称路径
     **/
    private String ostParentSiteNamePath;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 站点父ID
     **/
    private String ostParentId;
    /**
     * 站点等级
     **/
    private String ostSiteGrade;
    /**
     * 站点地址明细
     **/
    private String ostSiteAddrDetail;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 站点地址省
     **/
    private String ostSiteAddrProvince;
    /**
     * 联系人
     **/
    private String ostSiteContacts;
    /**
     * 站点地址县
     **/
    private String ostSiteAddrCounty;
    /**
     * 创建人
     **/
    private String createBy;
    /**
     * 站点父名称
     **/
    private String ostParentSiteName;
    /**
     * 站点ID路径
     **/
    private String ostParentIdPath;
    /**
     * 更新人
     **/
    private String updateBy;
    /**
     * 物理主键
     **/
    private String cwOrgSiteId;
    /**
     * 站点名称
     **/
    private String ostSiteName;


  
	
    public void setOstLifeStatus(String ostLifeStatus) {
        this.ostLifeStatus = ostLifeStatus;
    }

    public String getOstLifeStatus() {
        return ostLifeStatus;
    }

	
    public void setOstSiteAddrCity(String ostSiteAddrCity) {
        this.ostSiteAddrCity = ostSiteAddrCity;
    }

    public String getOstSiteAddrCity() {
        return ostSiteAddrCity;
    }

	
    public void setOstSiteContactsPhone(String ostSiteContactsPhone) {
        this.ostSiteContactsPhone = ostSiteContactsPhone;
    }

    public String getOstSiteContactsPhone() {
        return ostSiteContactsPhone;
    }

	
    public void setOstBusiStatus(String ostBusiStatus) {
        this.ostBusiStatus = ostBusiStatus;
    }

    public String getOstBusiStatus() {
        return ostBusiStatus;
    }

	
    public void setOstParentSiteNamePath(String ostParentSiteNamePath) {
        this.ostParentSiteNamePath = ostParentSiteNamePath;
    }

    public String getOstParentSiteNamePath() {
        return ostParentSiteNamePath;
    }

	
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

	
    public void setOstParentId(String ostParentId) {
        this.ostParentId = ostParentId;
    }

    public String getOstParentId() {
        return ostParentId;
    }

	
    public void setOstSiteGrade(String ostSiteGrade) {
        this.ostSiteGrade = ostSiteGrade;
    }

    public String getOstSiteGrade() {
        return ostSiteGrade;
    }

	
    public void setOstSiteAddrDetail(String ostSiteAddrDetail) {
        this.ostSiteAddrDetail = ostSiteAddrDetail;
    }

    public String getOstSiteAddrDetail() {
        return ostSiteAddrDetail;
    }

	
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

	
    public void setOstSiteAddrProvince(String ostSiteAddrProvince) {
        this.ostSiteAddrProvince = ostSiteAddrProvince;
    }

    public String getOstSiteAddrProvince() {
        return ostSiteAddrProvince;
    }

	
    public void setOstSiteContacts(String ostSiteContacts) {
        this.ostSiteContacts = ostSiteContacts;
    }

    public String getOstSiteContacts() {
        return ostSiteContacts;
    }

	
    public void setOstSiteAddrCounty(String ostSiteAddrCounty) {
        this.ostSiteAddrCounty = ostSiteAddrCounty;
    }

    public String getOstSiteAddrCounty() {
        return ostSiteAddrCounty;
    }

	
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

	
    public void setOstParentSiteName(String ostParentSiteName) {
        this.ostParentSiteName = ostParentSiteName;
    }

    public String getOstParentSiteName() {
        return ostParentSiteName;
    }

	
    public void setOstParentIdPath(String ostParentIdPath) {
        this.ostParentIdPath = ostParentIdPath;
    }

    public String getOstParentIdPath() {
        return ostParentIdPath;
    }

	
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

	
    public void setCwOrgSiteId(String cwOrgSiteId) {
        this.cwOrgSiteId = cwOrgSiteId;
    }

    public String getCwOrgSiteId() {
        return cwOrgSiteId;
    }

	
    public void setOstSiteName(String ostSiteName) {
        this.ostSiteName = ostSiteName;
    }

    public String getOstSiteName() {
        return ostSiteName;
    }


	@Override
	public String getDefaultId() {
		// TODO Auto-generated method stub
	     return cwOrgSiteId;
        }

	@Override
	public void setDefaultId(String cwOrgSiteId) {
	     this.cwOrgSiteId=cwOrgSiteId;
       }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
