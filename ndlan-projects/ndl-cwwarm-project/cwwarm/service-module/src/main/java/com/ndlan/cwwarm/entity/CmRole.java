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
@Table(name = "cm_role" )
public class CmRole  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    @Column(name = "sysdata_type"
     ,length = 32
    
    
    
    )
    private String sysdataType;
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * 角色表
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cr_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String crId;
    @Column(name = "name"
     ,length = 128
    
    
    
    )
    private String name;
    @Column(name = "rest_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String restId;
    @Column(name = "role_type"
     ,length = 32
    
    
    
    )
    private String roleType;
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    @Column(name = "cr_status"
     ,length = 32
    
    
    
    )
    private String crStatus;
    @Column(name = "bar_path"
     ,length = 3600
    
    
    
    )
    private String barPath;
    @Column(name = "is_all_tablearea"
     ,length = 32
    
    
    
    )
    private String isAllTablearea;
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    @Column(name = "is_stop_use"
     ,length = 1
    
    
    
    )
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
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
