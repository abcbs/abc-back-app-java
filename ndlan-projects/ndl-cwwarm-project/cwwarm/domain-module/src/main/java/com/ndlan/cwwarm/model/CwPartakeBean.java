package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import com.ndlan.framework.core.api.Identifiable;
import java.util.Date;

public class CwPartakeBean extends DefaultBeanIdentifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 扩展属性5
     **/
    private String ptkAttr5;
    /**
     * 参与者头像
     **/
    private String ptkByPic;
    /**
     * 扩展属性1
     **/
    private String ptkAttr1;
    /**
     * 参与状态【0草稿、1审批中、2审批通过、3审批拒绝】
     **/
    private String ptkBusiStatus;
    /**
     * 扩展属性4
     **/
    private String ptkAttr4;
    /**
     * 参与对象ID
     **/
    private String ptkObjId;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 扩展属性3
     **/
    private String ptkAttr3;
    /**
     * 生命状态【0删除、1正常】
     **/
    private String ptkLifeStatus;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 更新人
     **/
    private String updateBy;
    /**
     * 物理主键
     **/
    private String cwPartakeId;
    /**
     * 扩展属性2
     **/
    private String ptkAttr2;
    /**
     * 参与者名称
     **/
    private String ptkByName;
    /**
     * 参与对象类型【0参加活动】
     **/
    private String ptkObjType;
    /**
     * 参与对象信息
     **/
    private String ptkObjMsg;
    /**
     * 备注
     **/
    private String ptkRemark;
    /**
     * 参与者ID
     **/
    private String ptkBy;
    /**
     * 创建人
     **/
    private String createBy;


  
	
    public void setPtkAttr5(String ptkAttr5) {
        this.ptkAttr5 = ptkAttr5;
    }

    public String getPtkAttr5() {
        return ptkAttr5;
    }

	
    public void setPtkByPic(String ptkByPic) {
        this.ptkByPic = ptkByPic;
    }

    public String getPtkByPic() {
        return ptkByPic;
    }

	
    public void setPtkAttr1(String ptkAttr1) {
        this.ptkAttr1 = ptkAttr1;
    }

    public String getPtkAttr1() {
        return ptkAttr1;
    }

	
    public void setPtkBusiStatus(String ptkBusiStatus) {
        this.ptkBusiStatus = ptkBusiStatus;
    }

    public String getPtkBusiStatus() {
        return ptkBusiStatus;
    }

	
    public void setPtkAttr4(String ptkAttr4) {
        this.ptkAttr4 = ptkAttr4;
    }

    public String getPtkAttr4() {
        return ptkAttr4;
    }

	
    public void setPtkObjId(String ptkObjId) {
        this.ptkObjId = ptkObjId;
    }

    public String getPtkObjId() {
        return ptkObjId;
    }

	
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

	
    public void setPtkAttr3(String ptkAttr3) {
        this.ptkAttr3 = ptkAttr3;
    }

    public String getPtkAttr3() {
        return ptkAttr3;
    }

	
    public void setPtkLifeStatus(String ptkLifeStatus) {
        this.ptkLifeStatus = ptkLifeStatus;
    }

    public String getPtkLifeStatus() {
        return ptkLifeStatus;
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

	
    public void setCwPartakeId(String cwPartakeId) {
        this.cwPartakeId = cwPartakeId;
    }

    public String getCwPartakeId() {
        return cwPartakeId;
    }

	
    public void setPtkAttr2(String ptkAttr2) {
        this.ptkAttr2 = ptkAttr2;
    }

    public String getPtkAttr2() {
        return ptkAttr2;
    }

	
    public void setPtkByName(String ptkByName) {
        this.ptkByName = ptkByName;
    }

    public String getPtkByName() {
        return ptkByName;
    }

	
    public void setPtkObjType(String ptkObjType) {
        this.ptkObjType = ptkObjType;
    }

    public String getPtkObjType() {
        return ptkObjType;
    }

	
    public void setPtkObjMsg(String ptkObjMsg) {
        this.ptkObjMsg = ptkObjMsg;
    }

    public String getPtkObjMsg() {
        return ptkObjMsg;
    }

	
    public void setPtkRemark(String ptkRemark) {
        this.ptkRemark = ptkRemark;
    }

    public String getPtkRemark() {
        return ptkRemark;
    }

	
    public void setPtkBy(String ptkBy) {
        this.ptkBy = ptkBy;
    }

    public String getPtkBy() {
        return ptkBy;
    }

	
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }


	@Override
	public String getDefaultId() {
		// TODO Auto-generated method stub
	     return cwPartakeId;
        }

	@Override
	public void setDefaultId(String cwPartakeId) {
	     this.cwPartakeId=cwPartakeId;
       }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
