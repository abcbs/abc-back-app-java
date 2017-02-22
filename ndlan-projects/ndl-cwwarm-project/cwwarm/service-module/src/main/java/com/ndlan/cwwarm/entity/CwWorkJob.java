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
@Table(name = "cw_work_job" )
public class CwWorkJob  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * 父类ID路径
     **/
    @Column(name = "wjob_parent_path_id"
     ,length = 36
    
    
    
    )
    private String wjobParentPathId;
    /**
     * 品类名称
     **/
    @Column(name = "wjob_category_name"
     ,length = 255
    
    
    
    )
    private String wjobCategoryName;
    /**
     * 物理主键
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cw_work_job_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cwWorkJobId;
    /**
     * 品类等级【0行业分级、1业务分级、2职位分级】
     **/
    @Column(name = "wjob_category_grade"
     ,length = 20
    
    
    
    )
    private String wjobCategoryGrade;
    /**
     * 创建人
     **/
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * 父类id
     **/
    @Column(name = "wjob_parent_category_id"
     ,length = 36
    
    
    
    )
    private String wjobParentCategoryId;
    /**
     * 品类状态【0不可用、1可用】
     **/
    @Column(name = "wjob_busi_status"
     ,length = 20
    
    
    
    )
    private String wjobBusiStatus;
    /**
     * 品类描述
     **/
    @Column(name = "wjob_category_desc"
     ,length = 255
    
    
    
    )
    private String wjobCategoryDesc;
    /**
     * 父类名称路径
     **/
    @Column(name = "wjob_parent_path_name"
     ,length = 255
    
    
    
    )
    private String wjobParentPathName;
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
     * 父类名称
     **/
    @Column(name = "wjob_parent_category_name"
     ,length = 255
    
    
    
    )
    private String wjobParentCategoryName;
    /**
     * 生命状态【0删除、1正常】
     **/
    @Column(name = "wjob_life_status"
     ,length = 20
    
    
    
    )
    private String wjobLifeStatus;
    /**
     * 创建时间
     **/
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;


   

    public void setWjobParentPathId(String wjobParentPathId) {
        this.wjobParentPathId = wjobParentPathId;
    }

    public String getWjobParentPathId() {
        return wjobParentPathId;
    }
    public void setWjobCategoryName(String wjobCategoryName) {
        this.wjobCategoryName = wjobCategoryName;
    }

    public String getWjobCategoryName() {
        return wjobCategoryName;
    }
    public void setCwWorkJobId(String cwWorkJobId) {
        this.cwWorkJobId = cwWorkJobId;
    }

    public String getCwWorkJobId() {
        return cwWorkJobId;
    }
    public void setWjobCategoryGrade(String wjobCategoryGrade) {
        this.wjobCategoryGrade = wjobCategoryGrade;
    }

    public String getWjobCategoryGrade() {
        return wjobCategoryGrade;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setWjobParentCategoryId(String wjobParentCategoryId) {
        this.wjobParentCategoryId = wjobParentCategoryId;
    }

    public String getWjobParentCategoryId() {
        return wjobParentCategoryId;
    }
    public void setWjobBusiStatus(String wjobBusiStatus) {
        this.wjobBusiStatus = wjobBusiStatus;
    }

    public String getWjobBusiStatus() {
        return wjobBusiStatus;
    }
    public void setWjobCategoryDesc(String wjobCategoryDesc) {
        this.wjobCategoryDesc = wjobCategoryDesc;
    }

    public String getWjobCategoryDesc() {
        return wjobCategoryDesc;
    }
    public void setWjobParentPathName(String wjobParentPathName) {
        this.wjobParentPathName = wjobParentPathName;
    }

    public String getWjobParentPathName() {
        return wjobParentPathName;
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
    public void setWjobParentCategoryName(String wjobParentCategoryName) {
        this.wjobParentCategoryName = wjobParentCategoryName;
    }

    public String getWjobParentCategoryName() {
        return wjobParentCategoryName;
    }
    public void setWjobLifeStatus(String wjobLifeStatus) {
        this.wjobLifeStatus = wjobLifeStatus;
    }

    public String getWjobLifeStatus() {
        return wjobLifeStatus;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
