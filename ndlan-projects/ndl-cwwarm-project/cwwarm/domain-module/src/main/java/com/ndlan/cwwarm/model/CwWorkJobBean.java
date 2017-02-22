package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import com.ndlan.framework.core.api.Identifiable;
import java.util.Date;

public class CwWorkJobBean extends DefaultBeanIdentifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 父类ID路径
     **/
    private String wjobParentPathId;
    /**
     * 品类名称
     **/
    private String wjobCategoryName;
    /**
     * 物理主键
     **/
    private String cwWorkJobId;
    /**
     * 品类等级【0行业分级、1业务分级、2职位分级】
     **/
    private String wjobCategoryGrade;
    /**
     * 创建人
     **/
    private String createBy;
    /**
     * 父类id
     **/
    private String wjobParentCategoryId;
    /**
     * 品类状态【0不可用、1可用】
     **/
    private String wjobBusiStatus;
    /**
     * 品类描述
     **/
    private String wjobCategoryDesc;
    /**
     * 父类名称路径
     **/
    private String wjobParentPathName;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 更新人
     **/
    private String updateBy;
    /**
     * 父类名称
     **/
    private String wjobParentCategoryName;
    /**
     * 生命状态【0删除、1正常】
     **/
    private String wjobLifeStatus;
    /**
     * 创建时间
     **/
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
	public String getDefaultId() {
		// TODO Auto-generated method stub
	     return cwWorkJobId;
        }

	@Override
	public void setDefaultId(String cwWorkJobId) {
	     this.cwWorkJobId=cwWorkJobId;
       }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
