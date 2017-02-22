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
@Table(name = "cw_comment" )
public class CwComment  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * 评论内容
     **/
    @Column(name = "cmt_content"
     ,length = 255
    
    
    
    )
    private String cmtContent;
    /**
     * 评论状态【0草稿、1审批中、2审批通过、3审批拒绝】
     **/
    @Column(name = "cmt_busi_status"
     ,length = 20
    
    
    
    )
    private String cmtBusiStatus;
    /**
     * 扩展属性3
     **/
    @Column(name = "cmt_attr3"
     ,length = 255
    
    
    
    )
    private String cmtAttr3;
    /**
     * 评论人名称
     **/
    @Column(name = "cmt_by_name"
     ,length = 255
    
    
    
    )
    private String cmtByName;
    /**
     * 更新人
     **/
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    /**
     * 备注
     **/
    @Column(name = "remark"
     ,length = 255
    
    
    
    )
    private String remark;
    /**
     * 扩展属性1
     **/
    @Column(name = "cmt_attr1"
     ,length = 36
    
    
    
    )
    private String cmtAttr1;
    /**
     * 物理主键
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cw_comment_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cwCommentId;
    /**
     * 更新时间
     **/
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    /**
     * 扩展属性5
     **/
    @Column(name = "cmt_attr5"
     ,length = 1000
    
    
    
    )
    private String cmtAttr5;
    /**
     * 创建时间
     **/
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;
    /**
     * 创建人
     **/
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * 评论对象类型【0项目、1活动、2公益信息、3走失、4物品丢失、5物品求助】
     **/
    @Column(name = "cmt_obj_type"
     ,length = 20
    
    
    
    )
    private String cmtObjType;
    /**
     * 扩展属性4
     **/
    @Column(name = "cmt_attr4"
     ,length = 255
    
    
    
    )
    private String cmtAttr4;
    /**
     * 生命状态【0删除、1正常】
     **/
    @Column(name = "cmt_life_status"
     ,length = 20
    
    
    
    )
    private String cmtLifeStatus;
    /**
     * 评论人ID
     **/
    @Column(name = "cmt_by"
     ,length = 36
    
    
    
    )
    private String cmtBy;
    /**
     * 评论对象ID
     **/
    @Column(name = "cmt_obj_id"
     ,length = 36
    
    
    
    )
    private String cmtObjId;
    /**
     * 评论对象消息
     **/
    @Column(name = "cmt_obj_msg"
     ,length = 255
    
    
    
    )
    private String cmtObjMsg;
    /**
     * 扩展属性2
     **/
    @Column(name = "cmt_attr2"
     ,length = 36
    
    
    
    )
    private String cmtAttr2;
    /**
     * 评论人图片
     **/
    @Column(name = "cmt_by_pic"
     ,length = 36
    
    
    
    )
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
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
