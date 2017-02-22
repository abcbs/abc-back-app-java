 package com.ndlan.canyin.base.entity.hygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.ComplaintSuggest;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.hygl.MemberDishesFavorite;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.core.common.GenderEnum;
 import com.ndlan.canyin.core.common.MemberEduEnum;
 import com.ndlan.canyin.core.common.MemberWorkEnum;
 import java.io.Serializable;
 import java.util.Date;
 import java.util.List;
 import java.util.Set;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_rest_member_info")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer","memberShipCardNoDesc"})
 public class RestMemberInfo extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="mb_id", unique=true, nullable=false, length=36)
   private String mbId;
 
   @Column(name="address", length=128)
   private String address;
 
   @Column(name="adr_city", length=32)
   private String adrCity;
 
   @Column(name="adr_province", length=32)
   private String adrProvince;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="birthday_day")
   private Date birthday;
 
   @Transient
   private String birthdayStr;
 
   @Column(name="birthday_int")
   private int birthdayInt;
 
   @Column(name="edu")
   private String edu;
 
   @Transient
   private String eduName;
 
   @Column(name="email", length=128)
   private String email;
 
   @Column(name="gender", length=1)
   private String gender = GenderEnum.MALE.getCode();
 
   @Column(name="login_password", length=128)
   private String loginPassword;
 
   @Column(name="login_username", length=128)
   private String loginUsername;
 
   @Column(name="mobile", length=11)
   private String mobile;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="name_code", length=128)
   private String nameCode;
 
   @Column(name="notes", length=1204)
   private String notes;
 
   @Column(name="origin_rest_id", length=36)
   private String originRestId;
 
   @Column(name="reg_origin", length=32)
   private String regOrigin;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="salesman_id")
   private Employee salesman;
 
   @Transient
   private String workName;
 
   @Column(name="company", length=256)
   private String company;
 
   @Column(name="invoice_list", length=5000)
   private String invoiceList;
 
   @Column(name="work")
   private String work;
 
   @JsonIgnore
   @OneToMany(mappedBy="restMemberInfo")
   private Set<ComplaintSuggest> complaintSuggests;
 
   @JsonIgnore
   @OneToMany(mappedBy="restMemberInfo")
   private Set<MemberDishesFavorite> memberDishesFavorites;
 
   @JsonIgnore
   @OneToMany(mappedBy="restMemberInfo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
   private Set<MembershipCard> membershipCards;
 
   @Transient
   private List<MembershipCard> shipCards;
 
   @JsonIgnore
   @OneToMany(mappedBy="restMemberInfo")
   private Set<DinerBill> dinerBills;
 
   public String getEdu()
   {
     return this.edu;
   }
 
   public void setEdu(String edu) {
     this.edu = edu;
   }
 
   public String getEduName() {
     return MemberEduEnum.getDesc(this.edu);
   }
 
   public String getCompany()
   {
     return this.company;
   }
 
   public void setCompany(String company) {
     this.company = company;
   }
 
   public String getWork() {
     return this.work;
   }
 
   public void setWork(String work) {
     this.work = work;
   }
 
   public String getWorkName() {
     return MemberWorkEnum.getDesc(this.work);
   }
 
   public String getMemberShipCardNoDesc()
   {
     String desc = "";
     for (MembershipCard e : this.membershipCards)
     {
       desc = desc + "," + e.getCardNo();
     }
     return desc;
   }
 
   public String getMbId()
   {
     return this.mbId;
   }
 
   public void setMbId(String mbId) {
     this.mbId = mbId;
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
 
   public String getEmail() {
     return this.email;
   }
 
   public void setEmail(String email) {
     this.email = email;
   }
 
   public String getGender() {
     return this.gender;
   }
 
   public void setGender(String gender) {
     this.gender = gender;
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
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getOriginRestId() {
     return this.originRestId;
   }
 
   public void setOriginRestId(String originRestId) {
     this.originRestId = originRestId;
   }
 
   public String getRegOrigin() {
     return this.regOrigin;
   }
 
   public void setRegOrigin(String regOrigin) {
     this.regOrigin = regOrigin;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Employee getSalesman() {
     return this.salesman;
   }
 
   public void setSalesman(Employee salesman) {
     this.salesman = salesman;
   }
 
   public String getNameCode() {
     return this.nameCode;
   }
 
   public void setNameCode(String nameCode) {
     this.nameCode = nameCode;
   }
 
   public Date getBirthday() {
     return this.birthday;
   }
 
   public void setBirthday(Date birthday) {
     this.birthday = birthday;
   }
 
   public int getBirthdayInt() {
     return this.birthdayInt;
   }
 
   public void setBirthdayInt(int birthdayInt) {
     this.birthdayInt = birthdayInt;
   }
 
   public Set<ComplaintSuggest> getComplaintSuggests() {
     return this.complaintSuggests;
   }
 
   public void setComplaintSuggests(Set<ComplaintSuggest> complaintSuggests) {
     this.complaintSuggests = complaintSuggests;
   }
 
   public Set<MemberDishesFavorite> getMemberDishesFavorites() {
     return this.memberDishesFavorites;
   }
 
   public void setMemberDishesFavorites(Set<MemberDishesFavorite> memberDishesFavorites)
   {
     this.memberDishesFavorites = memberDishesFavorites;
   }
 
   public Set<MembershipCard> getMembershipCards() {
     return this.membershipCards;
   }
 
   public void setMembershipCards(Set<MembershipCard> membershipCards) {
     this.membershipCards = membershipCards;
   }
 
   public Set<DinerBill> getDinerBills() {
     return this.dinerBills;
   }
 
   public void setDinerBills(Set<DinerBill> dinerBills) {
     this.dinerBills = dinerBills;
   }
 
   public void setEduName(String eduName) {
     this.eduName = eduName;
   }
 
   public void setWorkName(String workName) {
     this.workName = workName;
   }
 
   public List<MembershipCard> getShipCards() {
     return this.shipCards;
   }
 
   public void setShipCards(List<MembershipCard> shipCards) {
     this.shipCards = shipCards;
   }
 
   public String getBirthdayStr() {
     return this.birthdayStr;
   }
 
   public void setBirthdayStr(String birthdayStr) {
     this.birthdayStr = birthdayStr;
   }
 
   public String getInvoiceList() {
     return this.invoiceList;
   }
 
   public void setInvoiceList(String invoiceList) {
     this.invoiceList = invoiceList;
   }
 }

