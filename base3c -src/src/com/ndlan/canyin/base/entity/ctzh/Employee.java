 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.ctzh.ComplaintSuggestReplay;
import com.ndlan.canyin.base.entity.ctzh.EmployeeWork;
import com.ndlan.canyin.base.entity.ctzh.IndexFavorite;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Role;

 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.JoinTable;
 import javax.persistence.ManyToMany;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 /**
  * Ա��ʵ��
  * @author zhengjiansong
  *
  */
 @Entity
 @Table(name="cm_employee")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class Employee extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="emp_id", unique=true, nullable=false, length=36)
   private String empId;
 
   @Column(name="address", length=128)
   private String address;
   
   @Column(name="store_id", length=36)
   private String storeId;
 
   @Column(name="adr_city", length=32)
   private String adrCity;
 
   @Column(name="adr_province", length=32)
   private String adrProvince;
 
   @Column(name="emer_con_tel", length=11)
   private String emerConTel;
 
   @Column(name="emer_contact", length=128)
   private String emerContact;
 
   @Column(name="emp_num", length=32)
   private String empNum;
 
   @Column(name="gender", length=1)
   private String gender;
 
   @Column(name="id_card", length=18)
   private String idCard;
 
   @Column(name="job_pic", length=256)
   private String jobPic;
 
   @Column(name="job_status", length=32)
   private String jobStatus;
 
   @Column(name="login_password", length=128)
   private String loginPassword;
 
   @Column(name="login_username", length=128)
   private String loginUsername;
 
   @Column(name="mobile", length=11)
   private String mobile;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="res__province", length=32)
   private String resProvince;
 
   @Column(name="res_city", length=32)
   private String resCity;
 
   @Column(name="belong_rest",length=1)
   private String belongRest;
   
   @Column(name="authorization_code",length=36)
   private String authorizationCode;

   @Column(name="authorization_make",length=36)
   private String authorizationmake;
   

public String getAuthorizationmake() {
	return authorizationmake;
}

public void setAuthorizationmake(String authorizationmake) {
	this.authorizationmake = authorizationmake;
}

@Column(name="residence_adr", length=128)
   private String residenceAdr;
 

   @Column(name="salt", length=255)
   private String salt;
 

   private String plainPassword;
 

   @Column(name="sysdata_type", length=32)
   private int sysdataType = 1;
 
   @OneToMany(mappedBy="employee")
   @JsonIgnore
   private List<ComplaintSuggestReplay> complaintSuggestReplays;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="rest_id")
   @JsonIgnore
   private Restaurant restaurant;
 
   @OneToMany(mappedBy="employee")
   @JsonIgnore
   private List<EmployeeWork> cmployeeWorks;
 
   @OneToMany(mappedBy="employee")
   @JsonIgnore
   private List<IndexFavorite> indexFavorites;
 
   @OneToMany(mappedBy="cashierEmployee")
   @JsonIgnore
   private List<DinerBill> dinerBills;
 
   @ManyToMany(fetch=FetchType.EAGER)
   @JoinTable(name="CM_ROLE_USER", joinColumns={@JoinColumn(name="FK_USER_ID")}, inverseJoinColumns={@JoinColumn(name="FK_ROLE_ID")})
   @JsonIgnore
   private List<Role> roleList = new ArrayList();
 
   @OneToMany(mappedBy="salesMg")
   @JsonIgnore
   private List<TableOrder> salesMgTableOrders = new ArrayList();
 
   @OneToMany(mappedBy="waiter")
   @JsonIgnore
   private List<TableOrder> waiterTableOrders = new ArrayList();
 
   @Transient
   private String restaurantId;
 
   public String getRoleListDesc()
   {
     String desc = "";
     for (Role e : this.roleList)
     {
       desc = desc + "," + e.getName();
     }
     return desc.replaceFirst(",", "");
   }
 
   public static long getSerialversionuid() {
	return serialVersionUID;
}
   
   



public String getEmpId()
   {
     return this.empId;
   }
 
   public void setEmpId(String empId) {
     this.empId = empId;
   }
 
   public String getAddress() {
     return this.address;
   }
 
   public void setAddress(String address) {
     this.address = address;
   }
 
   public String getAdrCity() {
     return this.adrCity;
   }
 
   public void setAdrCity(String adrCity) {
     this.adrCity = adrCity;
   }
 
   public String getAdrProvince() {
     return this.adrProvince;
   }
 
   public void setAdrProvince(String adrProvince) {
     this.adrProvince = adrProvince;
   }
 
   public String getEmerConTel()
   {
     return this.emerConTel;
   }
 
   public void setEmerConTel(String emerConTel) {
     this.emerConTel = emerConTel;
   }
 
   public String getEmerContact() {
     return this.emerContact;
   }
 
   public void setEmerContact(String emerContact) {
     this.emerContact = emerContact;
   }
 
   public String getEmpNum() {
     return this.empNum;
   }
 
   public void setEmpNum(String empNum) {
     this.empNum = empNum;
   }
 
   public String getGender() {
     return this.gender;
   }
 
   public void setGender(String gender) {
     this.gender = gender;
   }
 
   public String getIdCard() {
     return this.idCard;
   }
 
   public void setIdCard(String idCard) {
     this.idCard = idCard;
   }
 
   public String getJobPic() {
     return this.jobPic;
   }
 
   public void setJobPic(String jobPic) {
     this.jobPic = jobPic;
   }
 
   public String getJobStatus() {
     return this.jobStatus;
   }
 
   public void setJobStatus(String jobStatus) {
     this.jobStatus = jobStatus;
   }
 
   public String getLoginPassword() {
     return this.loginPassword;
   }
 
   public void setLoginPassword(String loginPassword) {
     this.loginPassword = loginPassword;
   }
 
   public String getLoginUsername() {
     return this.loginUsername;
   }
 
   public void setLoginUsername(String loginUsername) {
     this.loginUsername = loginUsername;
   }
 
   public String getMobile() {
     return this.mobile;
   }
 
   public void setMobile(String mobile) {
     this.mobile = mobile;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getResProvince() {
     return this.resProvince;
   }
 
   public void setResProvince(String resProvince) {
     this.resProvince = resProvince;
   }
 
   public String getResCity() {
     return this.resCity;
   }
 
   public void setResCity(String resCity) {
     this.resCity = resCity;
   }
 
   public String getResidenceAdr() {
     return this.residenceAdr;
   }
 
   public void setResidenceAdr(String residenceAdr) {
     this.residenceAdr = residenceAdr;
   }
 
   public String getSalt() {
     return this.salt;
   }
 
   public void setSalt(String salt) {
     this.salt = salt;
   }
 
   public String getPlainPassword()
   {
     return this.plainPassword;
   }
 
   public void setPlainPassword(String plainPassword) {
     this.plainPassword = plainPassword;
   }
 
   public List<ComplaintSuggestReplay> getComplaintSuggestReplays() {
     return this.complaintSuggestReplays;
   }
 
   public void setComplaintSuggestReplays(List<ComplaintSuggestReplay> complaintSuggestReplays)
   {
     this.complaintSuggestReplays = complaintSuggestReplays;
   }
 
   public Restaurant getRestaurant() {
     return this.restaurant;
   }
 
   public void setRestaurant(Restaurant restaurant) {
     this.restaurant = restaurant;
   }
 
   public List<EmployeeWork> getCmployeeWorks() {
     return this.cmployeeWorks;
   }
 
   public void setCmployeeWorks(List<EmployeeWork> cmployeeWorks) {
     this.cmployeeWorks = cmployeeWorks;
   }
 
   public List<IndexFavorite> getIndexFavorites() {
     return this.indexFavorites;
   }
 
   public void setIndexFavorites(List<IndexFavorite> indexFavorites) {
     this.indexFavorites = indexFavorites;
   }
 
   public List<Role> getRoleList()
   {
     return this.roleList;
   }
 
   public void setRoleList(List<Role> roleList) {
     this.roleList = roleList;
   }
 
   public List<DinerBill> getDinerBills() {
     return this.dinerBills;
   }
 
   public void setDinerBills(List<DinerBill> dinerBills) {
     this.dinerBills = dinerBills;
   }
 
   public int getSysdataType() {
     return this.sysdataType;
   }
 
   public void setSysdataType(int sysdataType) {
     this.sysdataType = sysdataType;
   }
 
   public List<TableOrder> getSalesMgTableOrders() {
     return this.salesMgTableOrders;
   }
 
   public void setSalesMgTableOrders(List<TableOrder> salesMgTableOrders) {
     this.salesMgTableOrders = salesMgTableOrders;
   }
 
   public List<TableOrder> getWaiterTableOrders() {
     return this.waiterTableOrders;
   }
 
   public void setWaiterTableOrders(List<TableOrder> waiterTableOrders) {
     this.waiterTableOrders = waiterTableOrders;
   }
 
   public String getRestaurantId() {
     if (this.restaurant != null) {
       this.restaurantId = this.restaurant.getRestId();
     }
     return this.restaurantId;
   }
 
   public String getStoreId() {
	return storeId;
}
   
   public String getBelongRest() {
	return belongRest;
	}
	
	public void setBelongRest(String belongRest) {
		this.belongRest = belongRest;
	}

public void setStoreId(String storeId) {
	this.storeId = storeId;
}

public void setRestaurantId(String restaurantId) {
     this.restaurantId = restaurantId;
   }

public String getAuthorizationCode() {
	return authorizationCode;
}

public void setAuthorizationCode(String authorizationCode) {
	this.authorizationCode = authorizationCode;
}


 }

