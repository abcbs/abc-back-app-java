package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import com.ndlan.framework.core.api.Identifiable;
import java.util.Date;

public class CwCommentBean extends DefaultBeanIdentifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 评论内容
     **/
    private String cmtContent;
    /**
     * 评论状态【0草稿、1审批中、2审批通过、3审批拒绝】
     **/
    private String cmtBusiStatus;
    /**
     * 扩展属性3
     **/
    private String cmtAttr3;
    /**
     * 评论人名称
     **/
    private String cmtByName;
    /**
     * 更新人
     **/
    private String updateBy;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 扩展属性1
     **/
    private String cmtAttr1;
    /**
     * 物理主键
     **/
    private String cwCommentId;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 扩展属性5
     **/
    private String cmtAttr5;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 创建人
     **/
    private String createBy;
    /**
     * 评论对象类型【0项目、1活动、2公益信息、3走失、4物品丢失、5物品求助】
     **/
    private String cmtObjType;
    /**
     * 扩展属性4
     **/
    private String cmtAttr4;
    /**
     * 生命状态【0删除、1正常】
     **/
    private String cmtLifeStatus;
    /**
     * 评论人ID
     **/
    private String cmtBy;
    /**
     * 评论对象ID
     **/
    private String cmtObjId;
    /**
     * 评论对象消息
     **/
    private String cmtObjMsg;
    /**
     * 扩展属性2
     **/
    private String cmtAttr2;
    /**
     * 评论人图片
     **/
    private String cmtByPic;


  
	
    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public String getCmtContent() {
        return cmtContent;
    }

	
    public void setCmtBusiStatus(String cmtBusiStatus) {
        this.cmtBusiStatus = cmtBusiStatus;
    }

    public String getCmtBusiStatus() {
        return cmtBusiStatus;
    }

	
    public void setCmtAttr3(String cmtAttr3) {
        this.cmtAttr3 = cmtAttr3;
    }

    public String getCmtAttr3() {
        return cmtAttr3;
    }

	
    public void setCmtByName(String cmtByName) {
        this.cmtByName = cmtByName;
    }

    public String getCmtByName() {
        return cmtByName;
    }

	
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

	
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

	
    public void setCmtAttr1(String cmtAttr1) {
        this.cmtAttr1 = cmtAttr1;
    }

    public String getCmtAttr1() {
        return cmtAttr1;
    }

	
    public void setCwCommentId(String cwCommentId) {
        this.cwCommentId = cwCommentId;
    }

    public String getCwCommentId() {
        return cwCommentId;
    }

	
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

	
    public void setCmtAttr5(String cmtAttr5) {
        this.cmtAttr5 = cmtAttr5;
    }

    public String getCmtAttr5() {
        return cmtAttr5;
    }

	
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

	
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

	
    public void setCmtObjType(String cmtObjType) {
        this.cmtObjType = cmtObjType;
    }

    public String getCmtObjType() {
        return cmtObjType;
    }

	
    public void setCmtAttr4(String cmtAttr4) {
        this.cmtAttr4 = cmtAttr4;
    }

    public String getCmtAttr4() {
        return cmtAttr4;
    }

	
    public void setCmtLifeStatus(String cmtLifeStatus) {
        this.cmtLifeStatus = cmtLifeStatus;
    }

    public String getCmtLifeStatus() {
        return cmtLifeStatus;
    }

	
    public void setCmtBy(String cmtBy) {
        this.cmtBy = cmtBy;
    }

    public String getCmtBy() {
        return cmtBy;
    }

	
    public void setCmtObjId(String cmtObjId) {
        this.cmtObjId = cmtObjId;
    }

    public String getCmtObjId() {
        return cmtObjId;
    }

	
    public void setCmtObjMsg(String cmtObjMsg) {
        this.cmtObjMsg = cmtObjMsg;
    }

    public String getCmtObjMsg() {
        return cmtObjMsg;
    }

	
    public void setCmtAttr2(String cmtAttr2) {
        this.cmtAttr2 = cmtAttr2;
    }

    public String getCmtAttr2() {
        return cmtAttr2;
    }

	
    public void setCmtByPic(String cmtByPic) {
        this.cmtByPic = cmtByPic;
    }

    public String getCmtByPic() {
        return cmtByPic;
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
