package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import com.ndlan.framework.core.api.Identifiable;
import java.util.Date;

public class CwCollectionBean extends DefaultBeanIdentifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 生命状态【0删除、1正常】
     **/
    private String cltLifeStatus;
    /**
     * 收藏者头像
     **/
    private String cltByPic;
    /**
     * 扩展属性5
     **/
    private String cltAttr5;
    /**
     * 创建人
     **/
    private String createBy;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 收藏对象ID
     **/
    private String cltObjId;
    /**
     * 收藏者ID
     **/
    private String cltBy;
    /**
     * 收藏者名称
     **/
    private String cltByName;
    /**
     * 扩展属性1
     **/
    private String cltAttr1;
    /**
     * 扩展属性3
     **/
    private String cltAttr3;
    /**
     * 更新人
     **/
    private String updateBy;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 收藏对象类型【0项目、1活动、2公益信息、3走失、4物品丢失、5物品求助】
     **/
    private String cltObjType;
    /**
     * 扩展属性4
     **/
    private String cltAttr4;
    /**
     * 收藏对象信息
     **/
    private String cltObjMsg;
    /**
     * 收藏状态
     **/
    private String cltBusiStatus;
    /**
     * 扩展属性2
     **/
    private String cltAttr2;
    /**
     * 物理主键
     **/
    private String cwCommentId;


  
	
    public void setCltLifeStatus(String cltLifeStatus) {
        this.cltLifeStatus = cltLifeStatus;
    }

    public String getCltLifeStatus() {
        return cltLifeStatus;
    }

	
    public void setCltByPic(String cltByPic) {
        this.cltByPic = cltByPic;
    }

    public String getCltByPic() {
        return cltByPic;
    }

	
    public void setCltAttr5(String cltAttr5) {
        this.cltAttr5 = cltAttr5;
    }

    public String getCltAttr5() {
        return cltAttr5;
    }

	
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

	
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

	
    public void setCltObjId(String cltObjId) {
        this.cltObjId = cltObjId;
    }

    public String getCltObjId() {
        return cltObjId;
    }

	
    public void setCltBy(String cltBy) {
        this.cltBy = cltBy;
    }

    public String getCltBy() {
        return cltBy;
    }

	
    public void setCltByName(String cltByName) {
        this.cltByName = cltByName;
    }

    public String getCltByName() {
        return cltByName;
    }

	
    public void setCltAttr1(String cltAttr1) {
        this.cltAttr1 = cltAttr1;
    }

    public String getCltAttr1() {
        return cltAttr1;
    }

	
    public void setCltAttr3(String cltAttr3) {
        this.cltAttr3 = cltAttr3;
    }

    public String getCltAttr3() {
        return cltAttr3;
    }

	
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

	
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

	
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

	
    public void setCltObjType(String cltObjType) {
        this.cltObjType = cltObjType;
    }

    public String getCltObjType() {
        return cltObjType;
    }

	
    public void setCltAttr4(String cltAttr4) {
        this.cltAttr4 = cltAttr4;
    }

    public String getCltAttr4() {
        return cltAttr4;
    }

	
    public void setCltObjMsg(String cltObjMsg) {
        this.cltObjMsg = cltObjMsg;
    }

    public String getCltObjMsg() {
        return cltObjMsg;
    }

	
    public void setCltBusiStatus(String cltBusiStatus) {
        this.cltBusiStatus = cltBusiStatus;
    }

    public String getCltBusiStatus() {
        return cltBusiStatus;
    }

	
    public void setCltAttr2(String cltAttr2) {
        this.cltAttr2 = cltAttr2;
    }

    public String getCltAttr2() {
        return cltAttr2;
    }

	
    public void setCwCommentId(String cwCommentId) {
        this.cwCommentId = cwCommentId;
    }

    public String getCwCommentId() {
        return cwCommentId;
    }


	@Override
	public String getDefaultId() {
		// TODO Auto-generated method stub
	     return cwCommentId;
        }

	@Override
	public void setDefaultId(String cwCommentId) {
	     this.cwCommentId=cwCommentId;
       }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
