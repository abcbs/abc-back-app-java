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
@Table(name = "cw_org_site" )
public class CwOrgSite  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * 生命状态【0删除、1正常】
     **/
    @Column(name = "ost_life_status"
     ,length = 20
    
    
    
    )
    private String ostLifeStatus;
    /**
     * 站点地址市
     **/
    @Column(name = "ost_site_addr_city"
     ,length = 255
    
    
    
    )
    private String ostSiteAddrCity;
    /**
     * 联系人电话
     **/
    @Column(name = "ost_site_contacts_phone"
     ,length = 36
    
    
    
    )
    private String ostSiteContactsPhone;
    /**
     * 站点状态【0不可用、1可用】
     **/
    @Column(name = "ost_busi_status"
     ,length = 20
    
    
    
    )
    private String ostBusiStatus;
    /**
     * 站点名称路径
     **/
    @Column(name = "ost_parent_site_name_path"
     ,length = 1000
    
    
    
    )
    private String ostParentSiteNamePath;
    /**
     * 创建时间
     **/
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;
    /**
     * 站点父ID
     **/
    @Column(name = "ost_parent_id"
     ,length = 36
    
    
    
    )
    private String ostParentId;
    /**
     * 站点等级
     **/
    @Column(name = "ost_site_grade"
     ,length = 20
    
    
    
    )
    private String ostSiteGrade;
    /**
     * 站点地址明细
     **/
    @Column(name = "ost_site_addr_detail"
     ,length = 255
    
    
    
    )
    private String ostSiteAddrDetail;
    /**
     * 更新时间
     **/
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    /**
     * 站点地址省
     **/
    @Column(name = "ost_site_addr_province"
     ,length = 255
    
    
    
    )
    private String ostSiteAddrProvince;
    /**
     * 联系人
     **/
    @Column(name = "ost_site_contacts"
     ,length = 255
    
    
    
    )
    private String ostSiteContacts;
    /**
     * 站点地址县
     **/
    @Column(name = "ost_site_addr_county"
     ,length = 255
    
    
    
    )
    private String ostSiteAddrCounty;
    /**
     * 创建人
     **/
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * 站点父名称
     **/
    @Column(name = "ost_parent_site_name"
     ,length = 255
    
    
    
    )
    private String ostParentSiteName;
    /**
     * 站点ID路径
     **/
    @Column(name = "ost_parent_id_path"
     ,length = 255
    
    
    
    )
    private String ostParentIdPath;
    /**
     * 更新人
     **/
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    /**
     * 物理主键
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cw_org_site_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cwOrgSiteId;
    /**
     * 站点名称
     **/
    @Column(name = "ost_site_name"
     ,length = 255
    
    
    
    )
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
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
