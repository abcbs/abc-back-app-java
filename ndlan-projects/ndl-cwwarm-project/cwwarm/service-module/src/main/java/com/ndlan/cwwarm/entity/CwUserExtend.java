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
@Table(name = "cw_user_extend" )
public class CwUserExtend  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * 认证时间
     **/
    @Column(name = "authorization_make"
     ,length = 36
     ,nullable = false
    
    
    )
    private String authorizationMake;
    /**
     * 物理主键
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cw_user_extend_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cwUserExtendId;
    /**
     * 状态【0不可用、1可用】
     **/
    @Column(name = "cue_busi_status"
     ,length = 20
     ,nullable = false
    
    
    )
    private String cueBusiStatus;
    /**
     * 职业
     **/
    @Column(name = "residence_adr"
     ,length = 128
     ,nullable = false
    
    
    )
    private String residenceAdr;
    /**
     * 志愿者编号
     **/
    @Column(name = "emp_num"
     ,length = 32
     ,nullable = false
    
    
    )
    private String empNum;
    /**
     * 头像
     **/
    @Column(name = "job_pic"
     ,length = 256
     ,nullable = false
    
    
    )
    private String jobPic;
    /**
     * 用户类型【10超级管理员、11个人、12志愿者、13机构、14组织】
     **/
    @Column(name = "sysdata_type"
     ,length = 32
     ,nullable = false
    
    
    )
    private String sysdataType;
    /**
     * 账号
     **/
    @Column(name = "login_username"
     ,length = 128
     ,nullable = false
    
    
    )
    private String loginUsername;
    /**
     * 姓名
     **/
    @Column(name = "name"
     ,length = 128
     ,nullable = false
    
    
    )
    private String name;
    /**
     * 已评论量
     **/
    @Column(name = "cue_comment_qty"
     ,length = 20
     ,nullable = false
    
    
    )
    private String cueCommentQty;
    /**
     * 组织ID
     **/
    @Column(name = "rest_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String restId;
    /**
     * 更新时间
     **/
    @Column(name = "update_time"
    
     ,nullable = false
    
    
    )
    private Date updateTime;
    /**
     * 
联系方式
     **/
    @Column(name = "moblie"
     ,length = 11
    
    
    
    )
    private String moblie;
    /**
     * 联系地址
     **/
    @Column(name = "address"
     ,length = 128
    
    
    
    )
    private String address;
    /**
     * 参与活动数量
     **/
    @Column(name = "cue_join_qty"
     ,length = 20
     ,nullable = false
    
    
    )
    private String cueJoinQty;
    /**
     * 更新人
     **/
    @Column(name = "update_by"
     ,length = 36
     ,nullable = false
    
    
    )
    private String updateBy;
    /**
     * 志愿者状态【0未申请、2审核中、1审核通过、3审核拒绝】
     **/
    @Column(name = "emp_status"
     ,length = 32
    
    
    
    )
    private String empStatus;
    /**
     * 性别【0男，1女】
     **/
    @Column(name = "gender"
     ,length = 1
    
    
    
    )
    private String gender;
    /**
     * 捐出物品量
     **/
    @Column(name = "cue_rcv_goods_qty"
     ,length = 20
     ,nullable = false
    
    
    )
    private String cueRcvGoodsQty;
    /**
     * 用户ID
     **/
    @Column(name = "emp_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String empId;
    /**
     * 已收藏量
     **/
    @Column(name = "cue_collection_qty"
     ,length = 20
     ,nullable = false
    
    
    )
    private String cueCollectionQty;
    /**
     * 生命状态【0删除、1正常】
     **/
    @Column(name = "cue_life_status"
     ,length = 20
     ,nullable = false
    
    
    )
    private String cueLifeStatus;
    /**
     * 手持身份证照
     **/
    @Column(name = "bar_path"
     ,length = 3600
     ,nullable = false
    
    
    )
    private String barPath;
    /**
     * 创建人
     **/
    @Column(name = "create_by"
     ,length = 36
     ,nullable = false
    
    
    )
    private String createBy;
    /**
     * 职业ID
     **/
    @Column(name = "authorization_code"
     ,length = 36
     ,nullable = false
    
    
    )
    private String authorizationCode;
    /**
     * 创建时间
     **/
    @Column(name = "create_time"
    
     ,nullable = false
    
    
    )
    private Date createTime;
    /**
     * 身份证号
     **/
    @Column(name = "id_card"
     ,length = 18
     ,nullable = false
    
    
    )
    private String idCard;
    /**
     * 昵称
     **/
    @Column(name = "emer_contact"
     ,length = 128
     ,nullable = false
    
    
    )
    private String emerContact;
    /**
     * 献出爱心量
     **/
    @Column(name = "cue_rcv_love_qty"
     ,length = 20
     ,nullable = false
    
    
    )
    private String cueRcvLoveQty;


   

    public void setAuthorizationMake(String authorizationMake) {
        this.authorizationMake = authorizationMake;
    }

    public String getAuthorizationMake() {
        return authorizationMake;
    }
    public void setCwUserExtendId(String cwUserExtendId) {
        this.cwUserExtendId = cwUserExtendId;
    }

    public String getCwUserExtendId() {
        return cwUserExtendId;
    }
    public void setCueBusiStatus(String cueBusiStatus) {
        this.cueBusiStatus = cueBusiStatus;
    }

    public String getCueBusiStatus() {
        return cueBusiStatus;
    }
    public void setResidenceAdr(String residenceAdr) {
        this.residenceAdr = residenceAdr;
    }

    public String getResidenceAdr() {
        return residenceAdr;
    }
    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getEmpNum() {
        return empNum;
    }
    public void setJobPic(String jobPic) {
        this.jobPic = jobPic;
    }

    public String getJobPic() {
        return jobPic;
    }
    public void setSysdataType(String sysdataType) {
        this.sysdataType = sysdataType;
    }

    public String getSysdataType() {
        return sysdataType;
    }
    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginUsername() {
        return loginUsername;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setCueCommentQty(String cueCommentQty) {
        this.cueCommentQty = cueCommentQty;
    }

    public String getCueCommentQty() {
        return cueCommentQty;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getMoblie() {
        return moblie;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setCueJoinQty(String cueJoinQty) {
        this.cueJoinQty = cueJoinQty;
    }

    public String getCueJoinQty() {
        return cueJoinQty;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }
    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpStatus() {
        return empStatus;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
    public void setCueRcvGoodsQty(String cueRcvGoodsQty) {
        this.cueRcvGoodsQty = cueRcvGoodsQty;
    }

    public String getCueRcvGoodsQty() {
        return cueRcvGoodsQty;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }
    public void setCueCollectionQty(String cueCollectionQty) {
        this.cueCollectionQty = cueCollectionQty;
    }

    public String getCueCollectionQty() {
        return cueCollectionQty;
    }
    public void setCueLifeStatus(String cueLifeStatus) {
        this.cueLifeStatus = cueLifeStatus;
    }

    public String getCueLifeStatus() {
        return cueLifeStatus;
    }
    public void setBarPath(String barPath) {
        this.barPath = barPath;
    }

    public String getBarPath() {
        return barPath;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }
    public void setEmerContact(String emerContact) {
        this.emerContact = emerContact;
    }

    public String getEmerContact() {
        return emerContact;
    }
    public void setCueRcvLoveQty(String cueRcvLoveQty) {
        this.cueRcvLoveQty = cueRcvLoveQty;
    }

    public String getCueRcvLoveQty() {
        return cueRcvLoveQty;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
