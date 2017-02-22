package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import com.ndlan.framework.core.api.Identifiable;

public class CmRoleUserBean extends DefaultBeanIdentifiable{

	private static final long serialVersionUID =-1;
	
    private String fkUserId;
    private String restId;
    private String barPath;
    private String fkRoleId;
    private String roleUserId;


  
	
    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getFkUserId() {
        return fkUserId;
    }

	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	
    public void setBarPath(String barPath) {
        this.barPath = barPath;
    }

    public String getBarPath() {
        return barPath;
    }

	
    public void setFkRoleId(String fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    public String getFkRoleId() {
        return fkRoleId;
    }

	
    public void setRoleUserId(String roleUserId) {
        this.roleUserId = roleUserId;
    }

    public String getRoleUserId() {
        return roleUserId;
    }


	@Override
	public String getDefaultId() {
		// TODO Auto-generated method stub
	     return roleUserId;
        }

	@Override
	public void setDefaultId(String roleUserId) {
	     this.roleUserId=roleUserId;
       }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
