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
@Table(name = "cm_employee" )
public class CmEmployee  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    @Column(name = "plain_password"
     ,length = 255
    
    
    
    )
    private String plainPassword;
    @Column(name = "emer_con_tel"
     ,length = 11
    
    
    
    )
    private String emerConTel;
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    /**
     * æŽˆæƒç ï¼ˆç™»å½•åˆ›å»ºé¤åŽ…é¡µé¢ï¼‰
     **/
    @Column(name = "authorization_code"
     ,length = 36
    
    
    
    )
    private String authorizationCode;
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    /**
     * ç»„ç»‡ID
     **/
    @Column(name = "store_id"
     ,length = 3600
    
    
    
    )
    private String storeId;
    @Column(name = "login_password"
     ,length = 128
    
    
    
    )
    private String loginPassword;
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * postæŽˆæƒç 
     **/
    @Column(name = "authorization_make"
     ,length = 36
    
    
    
    )
    private String authorizationMake;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "emp_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String empId;
    @Column(name = "mobile"
     ,length = 11
    
    
    
    )
    private String mobile;
    @Column(name = "res__province"
     ,length = 32
    
    
    
    )
    private String resProvince;
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;
    @Column(name = "emer_contact"
     ,length = 128
    
    
    
    )
    private String emerContact;
    @Column(name = "emp_status"
     ,length = 32
    
    
    
    )
    private String empStatus;
    @Column(name = "id_card"
     ,length = 18
    
    
    
    )
    private String idCard;
    @Column(name = "adr_province"
     ,length = 32
    
    
    
    )
    private String adrProvince;
    /**
     * å¯¹åº” JobStatus
     **/
    @Column(name = "job_status"
     ,length = 32
    
    
    
    )
    private String jobStatus;
    @Column(name = "res_city"
     ,length = 32
    
    
    
    )
    private String resCity;
    /**
     * å¯¹åº” Gender
     **/
    @Column(name = "gender"
     ,length = 1
    
    
    
    )
    private String gender;
    @Column(name = "address"
     ,length = 128
    
    
    
    )
    private String address;
    @Column(name = "emp_num"
     ,length = 32
    
    
    
    )
    private String empNum;
    @Column(name = "sysdata_type"
     ,length = 32
    
    
    
    )
    private String sysdataType;
    @Column(name = "job_pic"
     ,length = 256
    
    
    
    )
    private String jobPic;
    @Column(name = "salt"
     ,length = 255
    
    
    
    )
    private String salt;
    /**
     * åˆ¤åˆ«æ˜¯å¦ä¸ºå½“å‰é¤åŽ…äººå‘˜æ ‡è¯†ç¬¦
     **/
    @Column(name = "belong_rest"
     ,length = 1
    
    
    
    )
    private String belongRest;
    @Column(name = "login_username"
     ,length = 128
    
    
    
    )
    private String loginUsername;
    @Column(name = "name"
     ,length = 128
    
    
    
    )
    private String name;
    @Column(name = "residence_adr"
     ,length = 128
    
    
    
    )
    private String residenceAdr;
    @Column(name = "bar_path"
     ,length = 3600
    
    
    
    )
    private String barPath;
    @Column(name = "adr_city"
     ,length = 32
    
    
    
    )
    private String adrCity;


   

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getPlainPassword() {
        return plainPassword;
    }
    public void setEmerConTel(String emerConTel) {
        this.emerConTel = emerConTel;
    }

    public String getEmerConTel() {
        return emerConTel;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return storeId;
    }
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginPassword() {
        return loginPassword;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setAuthorizationMake(String authorizationMake) {
        this.authorizationMake = authorizationMake;
    }

    public String getAuthorizationMake() {
        return authorizationMake;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }
    public void setResProvince(String resProvince) {
        this.resProvince = resProvince;
    }

    public String getResProvince() {
        return resProvince;
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
    public void setEmerContact(String emerContact) {
        this.emerContact = emerContact;
    }

    public String getEmerContact() {
        return emerContact;
    }
    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpStatus() {
        return empStatus;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }
    public void setAdrProvince(String adrProvince) {
        this.adrProvince = adrProvince;
    }

    public String getAdrProvince() {
        return adrProvince;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatus() {
        return jobStatus;
    }
    public void setResCity(String resCity) {
        this.resCity = resCity;
    }

    public String getResCity() {
        return resCity;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getEmpNum() {
        return empNum;
    }
    public void setSysdataType(String sysdataType) {
        this.sysdataType = sysdataType;
    }

    public String getSysdataType() {
        return sysdataType;
    }
    public void setJobPic(String jobPic) {
        this.jobPic = jobPic;
    }

    public String getJobPic() {
        return jobPic;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }
    public void setBelongRest(String belongRest) {
        this.belongRest = belongRest;
    }

    public String getBelongRest() {
        return belongRest;
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
    public void setResidenceAdr(String residenceAdr) {
        this.residenceAdr = residenceAdr;
    }

    public String getResidenceAdr() {
        return residenceAdr;
    }
    public void setBarPath(String barPath) {
        this.barPath = barPath;
    }

    public String getBarPath() {
        return barPath;
    }
    public void setAdrCity(String adrCity) {
        this.adrCity = adrCity;
    }

    public String getAdrCity() {
        return adrCity;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
