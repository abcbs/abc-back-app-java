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



@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "cm_role_user" )
public class CmRoleUser  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    @Column(name = "fk_user_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String fkUserId;
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    @Column(name = "bar_path"
     ,length = 3600
    
    
    
    )
    private String barPath;
    @Column(name = "fk_role_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String fkRoleId;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "role_user_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
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
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
