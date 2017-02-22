package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import com.ndlan.framework.core.api.Identifiable;
import java.util.Date;

public class CmRoleBean extends DefaultBeanIdentifiable{

	private static final long serialVersionUID =-1;
	
    private String sysdataType;
    private String createBy;
    /**
     * 角色表
     **/
    private String crId;
    private String name;
    private String restId;
    private String roleType;
    private Date updateTime;
    private String crStatus;
    private String barPath;
    private String isAllTablearea;
    private Date createTime;
    private String updateBy;
    private String isStopUse;


  
	
    public void setSysdataType(String sysdataType) {
        this.sysdataType = sysdataType;
    }

    public String getSysdataType() {
        return sysdataType;
    }

	
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

	
    public void setCrId(String crId) {
        this.crId = crId;
    }

    public String getCrId() {
        return crId;
    }

	
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }

	
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

	
    public void setCrStatus(String crStatus) {
        this.crStatus = crStatus;
    }

    public String getCrStatus() {
        return crStatus;
    }

	
    public void setBarPath(String barPath) {
        this.barPath = barPath;
    }

    public String getBarPath() {
        return barPath;
    }

	
    public void setIsAllTablearea(String isAllTablearea) {
        this.isAllTablearea = isAllTablearea;
    }

    public String getIsAllTablearea() {
        return isAllTablearea;
    }

	
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

	
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

	
    public void setIsStopUse(String isStopUse) {
        this.isStopUse = isStopUse;
    }

    public String getIsStopUse() {
        return isStopUse;
    }


	@Override
	public String getDefaultId() {
		// TODO Auto-generated method stub
	     return crId;
        }

	@Override
	public void setDefaultId(String crId) {
	     this.crId=crId;
       }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
