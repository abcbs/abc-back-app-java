package com.ndlan.cwwarm.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class CmEmployeeBean extends DefaultBeanIdentifiable{

	private static final long serialVersionUID =-1;
	
    private String plainPassword;
    private String emerConTel;
    private Date updateTime;
    /**
     * æŽˆæƒç ï¼ˆç™»å½•åˆ›å»ºé¤åŽ…é¡µé¢ï¼‰
     **/
    private String authorizationCode;
    private String createBy;
    /**
     * ç»„ç»‡ID
     **/
    private String storeId;
    private String loginPassword;
    private String restId;
    /**
     * postæŽˆæƒç 
     **/
    private String authorizationMake;
    private String empId;
    private String mobile;
    private String resProvince;
    private String updateBy;
    private Date createTime;
    private String emerContact;
    private String empStatus;
    private String idCard;
    private String adrProvince;
    /**
     * å¯¹åº” JobStatus
     **/
    private String jobStatus;
    private String resCity;
    /**
     * å¯¹åº” Gender
     **/
    private String gender;
    private String address;
    private String empNum;
    private String sysdataType;
    private String jobPic;
    private String salt;
    /**
     * åˆ¤åˆ«æ˜¯å¦ä¸ºå½“å‰é¤åŽ…äººå‘˜æ ‡è¯†ç¬¦
     **/
    private String belongRest;
    private String loginUsername;
    private String name;
    private String residenceAdr;
    private String barPath;
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
	public String getDefaultId() {
		// TODO Auto-generated method stub
	     return empId;
        }

	@Override
	public void setDefaultId(String empId) {
	     this.empId=empId;
       }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
