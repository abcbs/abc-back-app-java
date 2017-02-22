package com.ndlan.canyin.base.entity.ctzh;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.ComplaintSuggest;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.RestaurantAppraise;
import com.ndlan.canyin.base.entity.ctzh.RestaurantPic;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
import com.ndlan.canyin.core.common.PicTypeEnum;
import com.ndlan.canyin.core.utils.Collections3;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * 锟斤拷锟斤拷实锟斤拷
 * 
 * @author zhengjiansong
 * 
 */
@Entity
@Table(name = "cm_restaurant")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Restaurant extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "rest_id", unique = true, nullable = false, length = 36)
	private String restId;

	@Column(name = "adr_city", length = 32)
	private String adrCity;

	@Column(name = "adr_detail", length = 2048)
	private String adrDetail;

	/*
	 * Add by husitong
	 */
	@Column(name = "store_name", length = 128)
	private String storeName;
	/*
	 * Add by husitong
	 */
	@Column(name = "store_id", length = 36)
	private String storeId;

	@Column(name = "adr_province", length = 32)
	private String adrProvince;

	@Column(name = "adr_area", length = 32)
	private String adrArea;
	
	@Column(name="c_name")
	private String cName;

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Column(name = "advertising_slogan", length = 128)
	private String advertisingSlogan;

	@Column(name = "bus_stop", length = 128)
	private String busStop;

	@Column(name = "busi_end")
	private String busiEnd;

	@Column(name = "busi_start")
	private String busiStart;

	@Column(name = "take_out_end")
	private String takeOutEnd;

	@Column(name = "take_out_start")
	private String takeOutStart;

	@Column(name = "send_price")
	private BigDecimal sendPrice = BigDecimal.ZERO;

	@Column(name = "delivery_charge")
	private BigDecimal deliveryCharge = BigDecimal.ZERO;

	@Column(name = "delivery_range")
	private Integer deliveryRange;

	@Column(name = "take_out_status")
	private String takeOutStatus;

	@Column(name = "event_notice", length = 1024)
	private String eventNotice;

	@Column(name = "event_summary", length = 1024)
	private String eventSummary;

	@Column(name = "join_authentication")
	private int joinAuthentication;

	@Column(name = "complaint_tel", length = 32)
	private String complaintTel;

	@Column(name = "cons_per_person")
	private Integer consPerPerson;

	@Column(name = "dashes_style_id_array", length = 1024)
	private String dashesStyleIdArray;

	@Column(name = "dishes_style_type_array", length = 1024)
	private String dishesStyleTypeArray;

	@Column(name = "dishes_style_name_array", length = 1024)
	private String dishesStyleNameArray;

	@Transient
	private String mainDiscount;

	@Transient
	@JsonIgnore
	private List<String> dashesStyleIdList;

	@Column(name = "special_style", length = 1024)
	private String specialStyle;

	@Transient
	@JsonIgnore
	private List<String> specialStyleIdList;

	@Transient
	private String restOrOpen;

	@Column(name = "dining_env", length = 1024)
	private String diningEnv;

	@Transient
	@JsonIgnore
	private List<String> diningEnvIdList;

	@Column(name = "environment_score")
	private int environmentScore;

	@Column(name = "foreign_la", length = 32)
	private String foreignLa;

	@Column(name = "lat", length = 32)
	private String lat;

	@Column(name = "linkman_name", length = 128)
	private String linkmanName;

	@Column(name = "linkman_phone", length = 16)
	private String linkmanPhone;

	@Column(name = "linkman_tel", length = 16)
	private String linkmanTel;

	@Column(name = "lng", length = 32)
	private String lng;

	@Column(name = "notes", length = 2048)
	private String notes;

	@Column(name = "order_tel", length = 32)
	private String orderTel;

	@Column(name = "overlaycoordinate_x", length = 32)
	private String overlaycoordinateX;

	@Column(name = "overlaycoordinate_y", length = 32)
	private String overlaycoordinateY;

	@Column(name = "pixelcoordinate_x", length = 32)
	private String pixelcoordinateX;

	@Column(name = "pixelcoordinate_y", length = 32)
	private String pixelcoordinateY;

	@Column(name = "rest_area")
	private Integer restArea;

	@Column(name = "rest_name", length = 128)
	private String restName;

	@Column(name = "serve_speed_score")
	private int serveSpeedScore;

	@Column(name = "service_score")
	private int serviceScore;

	@Column(name = "special_env", length = 1024)
	private String specialEnv;

	@Transient
	@JsonIgnore
	private List<String> specialEnvIdList;

	@Column(name = "subway", length = 128)
	private String subway;

	@Column(name = "taste_score")
	private int tasteScore;

	@Column(name = "tilecoordinate_x", length = 32)
	private String tilecoordinateX;

	@Column(name = "tilecoordinate_y", length = 32)
	private String tilecoordinateY;

	@Column(name = "total_score")
	private int totalScore;

	@Column(name = "viewportcoordinate_x", length = 32)
	private String viewportcoordinateX;

	@Column(name = "viewportcoordinate_y", length = 32)
	private String viewportcoordinateY;

	@Column(name = "worldcoordinate_x", length = 32)
	private String worldcoordinateX;

	@Column(name = "worldcoordinate_y", length = 32)
	private String worldcoordinateY;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = { javax.persistence.CascadeType.ALL })
	private List<ComplaintSuggest> complaintSuggests;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Employee> employees = new ArrayList();

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = { javax.persistence.CascadeType.ALL })
	private List<RestaurantAppraise> restaurantAppraises;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = { javax.persistence.CascadeType.ALL })
	private List<RestaurantPic> restaurantPics;

	@Transient
	private String disheStyleName;

	@Column(name = "order_days")
	private Integer orderDays;

	@Column(name = "busi_breakfast_start")
	private String busiBreakFastStart;

	@Column(name = "busi_breakfast_end")
	private String busiBreakFastEnd;

	@Column(name = "busi_breakfast_name")
	private String busiBreakfastName;

	@Column(name = "busi_lunch_start")
	private String busiLunchStart;

	@Column(name = "busi_lunch_end")
	private String busiLunchEnd;

	@Column(name = "busi_lunch_name")
	private String busiLunchName;

	@Column(name = "busi_supper_start")
	private String busiSupperStart;

	@Column(name = "busi_supper_end")
	private String busiSupperEnd;

	@Column(name = "busi_supper_name")
	private String busiSupperName;

	@Column(name = "busi_latesnack_start")
	private String busiLateSnackStart;

	@Column(name = "busi_latesnack_end")
	private String busiLateSnackEnd;

	@Column(name = "busi_hours_open")
	private String busiHoursOpen;

	@Column(name = "busi_hours_close")
	private String busiHoursClose;

	@Column(name = "busi_latesnack_name")
	private String busiLatesnackName;

	@Column(name = "online_rest_status")
	private String onlineRestStatus;

	@Column(name = "online_order_status")
	private String onlineOrderStatus;

	@Column(name = "online_order_dish_status")
	private String onlineOrderDishStatus;

	@Column(name = "invoice_status")
	private String invoiceStatus;

	@Column(name = "online_order_phone")
	private String onlineOrderPhone;

	@Column(name = "online_order_prompts")
	private String onlineOrderPrompts;

	@Column(name = "online_takeout_prompts")
	private String onlineTakeoutPrompts;

	@Column(name = "busi_takeout_lunch_start")
	private String busiTakeoutLunchStart;

	@Column(name = "busi_takeout_lunch_end")
	private String busiTakeoutLunchEnd;

	@Column(name = "busi_takeout_supper_start")
	private String busiTakeoutSupperStart;

	@Column(name = "busi_takeout_supper_end")
	private String busiTakeoutSupperEnd;

	@OneToMany(mappedBy = "restaurant")
	@JsonIgnore
	private List<TableArea> tableAreas;

	@Column(name = "cloud_username")
	private String cloudUsername;

	@Column(name = "cloud_password")
	private String cloudPassword;

	@Column(name = "is_band_cloud_account")
	private String isBandCloudAccount;

	@Column(name = "syn_database_status")
	private String synDatabaseStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "syn_data_time")
	private Date synDataTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "syn_data_complete_time")
	private Date synDataCompleteTime;

	@Column(name = "syn_version_id")
	private String synVersionId;

	// 地区编码
	@Column(name = "area_code")
	private String areaCode;

	// 法人有效证件种类 1.身份证;2.护照;3军(警)官证;4士兵证; 5台胞证; 6回乡证
	@Column(name = "cert_type")
	private String certType;

	// 法人证件有效日期 格式：YYYYMMDD
	@Column(name = "cert_expdate")
	private String certExpdate;

	// 商户营业地址
	@Column(name = "mch_addr")
	private String mchAddr;

	// 银行卡类型 1.借记卡 2.贷记卡
	@Column(name = "account_type")
	private String accountType;

	// 对公或者对私账户标识 0对公 1对私
	@Column(name = "is_private")
	private String isPrivate;

	// 开户行所在省
	@Column(name = "province")
	private String province;

	// 开户行所在市
	@Column(name = "city")
	private String city;

	// 支行名称
	@Column(name = "bank_branch")
	private String bankBranch;

	// 开户行账号
	@Column(name = "settle_accno")
	private String settleAccno;

	// 开户行号联行号
	@Column(name = "settle_bank")
	private String settleBank;

	// 开户姓名
	@Column(name = "acc_name")
	private String accName;

	// G1设备码
	@Column(name = "sn")
	private String sn;

	// 法人身份证正面照
	@Column(name = "file1")
	private String file1;

	// 法人身份证反面照
	@Column(name = "file2")
	private String file2;

	// 法人手持身份证正面照
	@Column(name = "file3")
	private String file3;

	// 银行卡正面照
	@Column(name = "file4")
	private String file4;

	// 法人
	@Column(name = "juridical_person")
	private String juridicalPerson;

	// 法人电话
	@Column(name = "juridical_phone")
	private String juridicalPhone;

	// 银行账户
	@Column(name = "bank_account")
	private String bankAccount;

	// 开户行名称
	@Column(name = "bank_name")
	private String bankName;

	// 营业执照（path）
	@Column(name = "business_license")
	private String businessLicense;

	// 法人身份证号
	@Column(name = "juridical_id")
	private String juridicalId;

	// 审核状态 -1初始化数据（默认不显示） 0未审核 1 正在审核 2 审核通过 3 审核未通过
	@Column(name = "rest_examine")
	private String restExamine;
	
	   //商户id
	   @Column(name="merchant_id",length=36)
	   private String merchantid;
	   //商户描述
	   @Column(name="description",length=360)
	   private String description;
	   
	   //设备类型
	   @Column(name="shebeileixing")
	   private String mchType;
	   //注册码
	   @Column(name="zhucema")
	   private String authCode;
	   //商户类型
	   @Column(name="shangleixing")
	   private String merType;
	   //密码
	   @Column(name="denglumima")
	   private String passwd;
	   
	   //开户许可证
	   @Column(name="kaihuo")
	   private String file5;
	   //组织机构代码证
	   @Column(name="zhouzhijiguo")
	   private String file6;
	   //房屋租赁合同
	   @Column(name="fangwu")
	   private String file7;
	   //税务登记证
	   @Column(name="shuiwu")
	   private String file8;
	   //现场照片
	   @Column(name="xianchang")
	   private String file10;
	 //G2 设备摆放位置照片
	   @Column(name="file11")
	   private String file11;
	 //店面门头照
	   @Column(name="file12")
	   private String file12;
	  public String getFile11() {
		return file11;
	}

	public void setFile11(String file11) {
		this.file11 = file11;
	}

	public String getFile12() {
		return file12;
	}

	public void setFile12(String file12) {
		this.file12 = file12;
	}

	//注册结果 0.成功  1.失败
	   @Column(name="code")
	   private String code;
	   //腾势平台商户ID号
	   @Column(name="mid")
	   private String mid;
	   //若服务端数据校验通过则返回“注册成功，请等待审核！”若失败返回结果。
	   @Column(name="msg")
	   private String msg;

	   
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMchType() {
		return mchType;
	}

	public void setMchType(String mchType) {
		this.mchType = mchType;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getFile5() {
		return file5;
	}

	public void setFile5(String file5) {
		this.file5 = file5;
	}

	public String getFile6() {
		return file6;
	}

	public void setFile6(String file6) {
		this.file6 = file6;
	}

	public String getFile7() {
		return file7;
	}

	public void setFile7(String file7) {
		this.file7 = file7;
	}

	public String getFile8() {
		return file8;
	}

	public void setFile8(String file8) {
		this.file8 = file8;
	}

	public String getFile10() {
		return file10;
	}

	public void setFile10(String file10) {
		this.file10 = file10;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertExpdate() {
		return certExpdate;
	}

	public void setCertExpdate(String certExpdate) {
		this.certExpdate = certExpdate;
	}

	public String getMchAddr() {
		return mchAddr;
	}

	public void setMchAddr(String mchAddr) {
		this.mchAddr = mchAddr;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getSettleAccno() {
		return settleAccno;
	}

	public void setSettleAccno(String settleAccno) {
		this.settleAccno = settleAccno;
	}

	public String getSettleBank() {
		return settleBank;
	}

	public void setSettleBank(String settleBank) {
		this.settleBank = settleBank;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	public String getFile4() {
		return file4;
	}

	public void setFile4(String file4) {
		this.file4 = file4;
	}

	public String getJuridicalPerson() {
		return juridicalPerson;
	}

	public void setJuridicalPerson(String juridicalPerson) {
		this.juridicalPerson = juridicalPerson;
	}

	public String getJuridicalPhone() {
		return juridicalPhone;
	}

	public void setJuridicalPhone(String juridicalPhone) {
		this.juridicalPhone = juridicalPhone;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getJuridicalId() {
		return juridicalId;
	}

	public void setJuridicalId(String juridicalId) {
		this.juridicalId = juridicalId;
	}

	public String getRestExamine() {
		return restExamine;
	}

	public void setRestExamine(String restExamine) {
		this.restExamine = restExamine;
	}

	public String getRestOrOpen() {
		return this.restOrOpen;
	}

	public void setRestOrOpen(String restOrOpen) {
		this.restOrOpen = restOrOpen;
	}

	public String getRestLogo() {
		String logo = "";
		if (this.restaurantPics != null) {
			for (RestaurantPic e : this.restaurantPics) {
				if (!PicTypeEnum.LOGO.getCode().equals(e.getPicType()))
					continue;
				logo = e.getPicUrl();
			}
		}

		return logo;
	}

	public String getRestLogo200x200() {
		String logo = "";
		if (this.restaurantPics != null) {
			for (RestaurantPic e : this.restaurantPics) {
				if (!PicTypeEnum.LOGO.getCode().equals(e.getPicType()))
					continue;
				logo = e.getPicUrl200x200();
			}
		}

		return logo;
	}

	public String getRestLogo300x200() {
		String logo = "";
		if (this.restaurantPics != null) {
			for (RestaurantPic e : this.restaurantPics) {
				if (!PicTypeEnum.LOGO.getCode().equals(e.getPicType()))
					continue;
				logo = e.getPicUrl300x200();
			}
		}

		return logo;
	}

	public String getRestLogo1024x1024() {
		String logo = "";
		if (this.restaurantPics != null) {
			for (RestaurantPic e : this.restaurantPics) {
				if (!PicTypeEnum.LOGO.getCode().equals(e.getPicType()))
					continue;
				logo = e.getPicUrl1024x1024();
			}
		}

		return logo;
	}

	public String getRestId() {
		return this.restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

	public String getAdrCity() {
		return this.adrCity;
	}

	public void setAdrCity(String adrCity) {
		this.adrCity = adrCity;
	}

	public String getAdrDetail() {
		return this.adrDetail;
	}

	public void setAdrDetail(String adrDetail) {
		this.adrDetail = adrDetail;
	}

	public String getAdrProvince() {
		return this.adrProvince;
	}

	public void setAdrProvince(String adrProvince) {
		this.adrProvince = adrProvince;
	}

	public String getAdvertisingSlogan() {
		return this.advertisingSlogan;
	}

	public void setAdvertisingSlogan(String advertisingSlogan) {
		this.advertisingSlogan = advertisingSlogan;
	}

	public String getBusStop() {
		return this.busStop;
	}

	public void setBusStop(String busStop) {
		this.busStop = busStop;
	}

	public String getBusiEnd() {
		return this.busiEnd;
	}

	public void setBusiEnd(String busiEnd) {
		this.busiEnd = busiEnd;
	}

	public String getBusiStart() {
		return this.busiStart;
	}

	public String getTakeOutEnd() {
		return this.takeOutEnd;
	}

	public void setTakeOutEnd(String takeOutEnd) {
		this.takeOutEnd = takeOutEnd;
	}

	public String getTakeOutStart() {
		return this.takeOutStart;
	}

	public void setTakeOutStart(String takeOutStart) {
		this.takeOutStart = takeOutStart;
	}

	public void setBusiStart(String busiStart) {
		this.busiStart = busiStart;
	}

	public String getComplaintTel() {
		return this.complaintTel;
	}

	public void setComplaintTel(String complaintTel) {
		this.complaintTel = complaintTel;
	}

	public Integer getConsPerPerson() {
		return this.consPerPerson;
	}

	public void setConsPerPerson(Integer consPerPerson) {
		this.consPerPerson = consPerPerson;
	}

	public String getDashesStyleIdArray() {
		return this.dashesStyleIdArray;
	}

	public void setDashesStyleIdArray(String dashesStyleIdArray) {
		this.dashesStyleIdArray = dashesStyleIdArray;
	}

	public String getDiningEnv() {
		return this.diningEnv;
	}

	public void setDiningEnv(String diningEnv) {
		this.diningEnv = diningEnv;
	}

	public int getEnvironmentScore() {
		return this.environmentScore;
	}

	public void setEnvironmentScore(int environmentScore) {
		this.environmentScore = environmentScore;
	}

	public String getForeignLa() {
		return this.foreignLa;
	}

	public void setForeignLa(String foreignLa) {
		this.foreignLa = foreignLa;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLinkmanName() {
		return this.linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLinkmanPhone() {
		return this.linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public String getLinkmanTel() {
		return this.linkmanTel;
	}

	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}

	public String getLng() {
		return this.lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOrderTel() {
		return this.orderTel;
	}

	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}

	public String getOverlaycoordinateX() {
		return this.overlaycoordinateX;
	}

	public void setOverlaycoordinateX(String overlaycoordinateX) {
		this.overlaycoordinateX = overlaycoordinateX;
	}

	public String getOverlaycoordinateY() {
		return this.overlaycoordinateY;
	}

	public void setOverlaycoordinateY(String overlaycoordinateY) {
		this.overlaycoordinateY = overlaycoordinateY;
	}

	public String getPixelcoordinateX() {
		return this.pixelcoordinateX;
	}

	public void setPixelcoordinateX(String pixelcoordinateX) {
		this.pixelcoordinateX = pixelcoordinateX;
	}

	public String getPixelcoordinateY() {
		return this.pixelcoordinateY;
	}

	public void setPixelcoordinateY(String pixelcoordinateY) {
		this.pixelcoordinateY = pixelcoordinateY;
	}

	public Integer getRestArea() {
		return this.restArea;
	}

	public void setRestArea(Integer restArea) {
		this.restArea = restArea;
	}

	public String getRestName() {
		return this.restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public int getServeSpeedScore() {
		return this.serveSpeedScore;
	}

	public void setServeSpeedScore(int serveSpeedScore) {
		this.serveSpeedScore = serveSpeedScore;
	}

	public int getServiceScore() {
		return this.serviceScore;
	}

	public void setServiceScore(int serviceScore) {
		this.serviceScore = serviceScore;
	}

	public String getSpecialEnv() {
		return this.specialEnv;
	}

	public void setSpecialEnv(String specialEnv) {
		this.specialEnv = specialEnv;
	}

	public String getSubway() {
		return this.subway;
	}

	public void setSubway(String subway) {
		this.subway = subway;
	}

	public int getTasteScore() {
		return this.tasteScore;
	}

	public void setTasteScore(int tasteScore) {
		this.tasteScore = tasteScore;
	}

	public String getTilecoordinateX() {
		return this.tilecoordinateX;
	}

	public void setTilecoordinateX(String tilecoordinateX) {
		this.tilecoordinateX = tilecoordinateX;
	}

	public String getTilecoordinateY() {
		return this.tilecoordinateY;
	}

	public void setTilecoordinateY(String tilecoordinateY) {
		this.tilecoordinateY = tilecoordinateY;
	}

	public int getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public String getViewportcoordinateX() {
		return this.viewportcoordinateX;
	}

	public void setViewportcoordinateX(String viewportcoordinateX) {
		this.viewportcoordinateX = viewportcoordinateX;
	}

	public String getViewportcoordinateY() {
		return this.viewportcoordinateY;
	}

	public void setViewportcoordinateY(String viewportcoordinateY) {
		this.viewportcoordinateY = viewportcoordinateY;
	}

	public String getWorldcoordinateX() {
		return this.worldcoordinateX;
	}

	public void setWorldcoordinateX(String worldcoordinateX) {
		this.worldcoordinateX = worldcoordinateX;
	}

	public String getWorldcoordinateY() {
		return this.worldcoordinateY;
	}

	public void setWorldcoordinateY(String worldcoordinateY) {
		this.worldcoordinateY = worldcoordinateY;
	}

	public List<ComplaintSuggest> getComplaintSuggests() {
		return this.complaintSuggests;
	}

	public void setComplaintSuggests(List<ComplaintSuggest> complaintSuggests) {
		this.complaintSuggests = complaintSuggests;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<RestaurantAppraise> getRestaurantAppraises() {
		return this.restaurantAppraises;
	}

	public void setRestaurantAppraises(
			List<RestaurantAppraise> restaurantAppraises) {
		this.restaurantAppraises = restaurantAppraises;
	}

	public List<RestaurantPic> getRestaurantPics() {
		return this.restaurantPics;
	}

	public void setRestaurantPics(List<RestaurantPic> restaurantPics) {
		this.restaurantPics = restaurantPics;
	}

	public List<TableArea> getTableAreas() {
		return this.tableAreas;
	}

	public void setTableAreas(List<TableArea> tableAreas) {
		this.tableAreas = tableAreas;
	}

	public void setDashesStyleIdList(List<String> dashesStyleIdList) {
		this.dashesStyleIdArray = Collections3.convertToString(
				dashesStyleIdList, ",");
		this.dashesStyleIdList = dashesStyleIdList;
	}

	public List<String> getDashesStyleIdList() {
		if (this.dashesStyleIdArray != null) {
			this.dashesStyleIdList = ImmutableList.copyOf(StringUtils.split(
					this.dashesStyleIdArray, ","));
		}
		return this.dashesStyleIdList;
	}

	public String getSpecialStyle() {
		return this.specialStyle;
	}

	public void setSpecialStyle(String specialStyle) {
		this.specialStyle = specialStyle;
	}

	public List<String> getSpecialStyleIdList() {
		if (this.specialStyle != null) {
			this.specialStyleIdList = ImmutableList.copyOf(StringUtils.split(
					this.specialStyle, ","));
		}
		return this.specialStyleIdList;
	}

	public void setSpecialStyleIdList(List<String> specialStyleIdList) {
		this.specialStyle = Collections3.convertToString(specialStyleIdList,
				",");
		this.specialStyleIdList = specialStyleIdList;
	}

	public String getDishesStyleTypeArray() {
		return this.dishesStyleTypeArray;
	}

	public void setDishesStyleTypeArray(String dishesStyleTypeArray) {
		this.dishesStyleTypeArray = dishesStyleTypeArray;
	}

	public String getDishesStyleNameArray() {
		return this.dishesStyleNameArray;
	}

	public void setDishesStyleNameArray(String dishesStyleNameArray) {
		this.dishesStyleNameArray = dishesStyleNameArray;
	}

	public List<String> getDiningEnvIdList() {
		if (this.diningEnv != null) {
			this.diningEnvIdList = ImmutableList.copyOf(StringUtils.split(
					this.diningEnv, ","));
		}
		return this.diningEnvIdList;
	}

	public void setDiningEnvIdList(List<String> diningEnvIdList) {
		this.diningEnv = Collections3.convertToString(diningEnvIdList, ",");
		this.diningEnvIdList = diningEnvIdList;
	}

	public List<String> getSpecialEnvIdList() {
		if (this.specialEnv != null) {
			this.specialEnvIdList = ImmutableList.copyOf(StringUtils.split(
					this.specialEnv, ","));
		}
		return this.specialEnvIdList;
	}

	public void setSpecialEnvIdList(List<String> specialEnvIdList) {
		this.specialEnv = Collections3.convertToString(specialEnvIdList, ",");
		this.specialEnvIdList = specialEnvIdList;
	}

	public BigDecimal getSendPrice() {
		return this.sendPrice;
	}

	public void setSendPrice(BigDecimal sendPrice) {
		this.sendPrice = sendPrice;
	}

	public BigDecimal getDeliveryCharge() {
		return this.deliveryCharge;
	}

	public void setDeliveryCharge(BigDecimal deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public Integer getDeliveryRange() {
		return this.deliveryRange;
	}

	public void setDeliveryRange(Integer deliveryRange) {
		this.deliveryRange = deliveryRange;
	}

	public String getTakeOutStatus() {
		return this.takeOutStatus;
	}

	public void setTakeOutStatus(String takeOutStatus) {
		this.takeOutStatus = takeOutStatus;
	}

	public String getEventNotice() {
		return this.eventNotice;
	}

	public void setEventNotice(String eventNotice) {
		this.eventNotice = eventNotice;
	}

	public String getEventSummary() {
		return this.eventSummary;
	}

	public void setEventSummary(String eventSummary) {
		this.eventSummary = eventSummary;
	}

	public int getJoinAuthentication() {
		return this.joinAuthentication;
	}

	public void setJoinAuthentication(int joinAuthentication) {
		this.joinAuthentication = joinAuthentication;
	}

	public Integer getOrderDays() {
		return this.orderDays;
	}

	public void setOrderDays(Integer orderDays) {
		this.orderDays = orderDays;
	}

	public String getBusiBreakFastStart() {
		return this.busiBreakFastStart;
	}

	public void setBusiBreakFastStart(String busiBreakFastStart) {
		this.busiBreakFastStart = busiBreakFastStart;
	}

	public String getBusiBreakFastEnd() {
		return this.busiBreakFastEnd;
	}

	public void setBusiBreakFastEnd(String busiBreakFastEnd) {
		this.busiBreakFastEnd = busiBreakFastEnd;
	}

	public String getBusiLunchStart() {
		return this.busiLunchStart;
	}

	public void setBusiLunchStart(String busiLunchStart) {
		this.busiLunchStart = busiLunchStart;
	}

	public String getBusiLunchEnd() {
		return this.busiLunchEnd;
	}

	public void setBusiLunchEnd(String busiLunchEnd) {
		this.busiLunchEnd = busiLunchEnd;
	}

	public String getBusiSupperStart() {
		return this.busiSupperStart;
	}

	public void setBusiSupperStart(String busiSupperStart) {
		this.busiSupperStart = busiSupperStart;
	}

	public String getBusiSupperEnd() {
		return this.busiSupperEnd;
	}

	public void setBusiSupperEnd(String busiSupperEnd) {
		this.busiSupperEnd = busiSupperEnd;
	}

	public String getBusiLateSnackStart() {
		return this.busiLateSnackStart;
	}

	public void setBusiLateSnackStart(String busiLateSnackStart) {
		this.busiLateSnackStart = busiLateSnackStart;
	}

	public String getBusiLateSnackEnd() {
		return this.busiLateSnackEnd;
	}

	public void setBusiLateSnackEnd(String busiLateSnackEnd) {
		this.busiLateSnackEnd = busiLateSnackEnd;
	}

	public String getOnlineRestStatus() {
		return this.onlineRestStatus;
	}

	public void setOnlineRestStatus(String onlineRestStatus) {
		this.onlineRestStatus = onlineRestStatus;
	}

	public String getOnlineOrderStatus() {
		return this.onlineOrderStatus;
	}

	public void setOnlineOrderStatus(String onlineOrderStatus) {
		this.onlineOrderStatus = onlineOrderStatus;
	}

	public String getOnlineOrderDishStatus() {
		return this.onlineOrderDishStatus;
	}

	public void setOnlineOrderDishStatus(String onlineOrderDishStatus) {
		this.onlineOrderDishStatus = onlineOrderDishStatus;
	}

	public String getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getOnlineOrderPhone() {
		return this.onlineOrderPhone;
	}

	public void setOnlineOrderPhone(String onlineOrderPhone) {
		this.onlineOrderPhone = onlineOrderPhone;
	}

	public String getOnlineOrderPrompts() {
		return this.onlineOrderPrompts;
	}

	public void setOnlineOrderPrompts(String onlineOrderPrompts) {
		this.onlineOrderPrompts = onlineOrderPrompts;
	}

	public String getRestInfoDesc() {
		return "RestName:" + getRestName() + ";AdrProvince:" + getAdrProvince()
				+ ";AdrCity:" + getAdrCity() + ";ComplaintTel:"
				+ getComplaintTel() + ";ConsPerPerson:" + getConsPerPerson()
				+ ";LinkmanName:" + getLinkmanName() + ";LinkmanPhone:"
				+ getLinkmanPhone() + ";LinkmanTel:" + getLinkmanTel()
				+ ";OrderTel:" + getOrderTel() + ";BusiStart:" + getBusiStart()
				+ ";BusiEnd:" + getBusiEnd() + ";RestArea:" + getRestArea()
				+ ";AdrDetail:" + getAdrDetail();
	}

	public String getAdrArea() {
		return this.adrArea;
	}

	public void setAdrArea(String adrArea) {
		this.adrArea = adrArea;
	}

	public String getOnlineTakeoutPrompts() {
		return this.onlineTakeoutPrompts;
	}

	public void setOnlineTakeoutPrompts(String onlineTakeoutPrompts) {
		this.onlineTakeoutPrompts = onlineTakeoutPrompts;
	}

	public String getBusiTakeoutLunchStart() {
		return this.busiTakeoutLunchStart;
	}

	public void setBusiTakeoutLunchStart(String busiTakeoutLunchStart) {
		this.busiTakeoutLunchStart = busiTakeoutLunchStart;
	}

	public String getBusiTakeoutLunchEnd() {
		return this.busiTakeoutLunchEnd;
	}

	public void setBusiTakeoutLunchEnd(String busiTakeoutLunchEnd) {
		this.busiTakeoutLunchEnd = busiTakeoutLunchEnd;
	}

	public String getBusiTakeoutSupperStart() {
		return this.busiTakeoutSupperStart;
	}

	public void setBusiTakeoutSupperStart(String busiTakeoutSupperStart) {
		this.busiTakeoutSupperStart = busiTakeoutSupperStart;
	}

	public String getBusiTakeoutSupperEnd() {
		return this.busiTakeoutSupperEnd;
	}

	public void setBusiTakeoutSupperEnd(String busiTakeoutSupperEnd) {
		this.busiTakeoutSupperEnd = busiTakeoutSupperEnd;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public String getCloudUsername() {
		return this.cloudUsername;
	}

	public void setCloudUsername(String cloudUsername) {
		this.cloudUsername = cloudUsername;
	}

	public String getCloudPassword() {
		return this.cloudPassword;
	}

	public void setCloudPassword(String cloudPassword) {
		this.cloudPassword = cloudPassword;
	}

	public String getIsBandCloudAccount() {
		return this.isBandCloudAccount;
	}

	public void setIsBandCloudAccount(String isBandCloudAccount) {
		this.isBandCloudAccount = isBandCloudAccount;
	}

	public String getSynDatabaseStatus() {
		return this.synDatabaseStatus;
	}

	public void setSynDatabaseStatus(String synDatabaseStatus) {
		this.synDatabaseStatus = synDatabaseStatus;
	}

	public Date getSynDataTime() {
		return this.synDataTime;
	}

	public void setSynDataTime(Date synDataTime) {
		this.synDataTime = synDataTime;
	}

	public Date getSynDataCompleteTime() {
		return this.synDataCompleteTime;
	}

	public void setSynDataCompleteTime(Date synDataCompleteTime) {
		this.synDataCompleteTime = synDataCompleteTime;
	}

	public String getSynVersionId() {
		return this.synVersionId;
	}

	public void setSynVersionId(String synVersionId) {
		this.synVersionId = synVersionId;
	}

	public String getDisheStyleName() {
		return this.disheStyleName;
	}

	public void setDisheStyleName(String disheStyleName) {
		this.disheStyleName = disheStyleName;
	}

	public String getMainDiscount() {
		return this.mainDiscount;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setMainDiscount(String mainDiscount) {
		this.mainDiscount = mainDiscount;
	}

	public String getBusiBreakfastName() {
		return this.busiBreakfastName;
	}

	public void setBusiBreakfastName(String busiBreakfastName) {
		this.busiBreakfastName = busiBreakfastName;
	}

	public String getBusiLunchName() {
		return this.busiLunchName;
	}

	public void setBusiLunchName(String busiLunchName) {
		this.busiLunchName = busiLunchName;
	}

	public String getBusiSupperName() {
		return this.busiSupperName;
	}

	public void setBusiSupperName(String busiSupperName) {
		this.busiSupperName = busiSupperName;
	}

	public String getBusiLatesnackName() {
		return this.busiLatesnackName;
	}

	public void setBusiLatesnackName(String busiLatesnackName) {
		this.busiLatesnackName = busiLatesnackName;
	}

	public String getBusiHoursOpen() {
		return this.busiHoursOpen;
	}

	public void setBusiHoursOpen(String busiHoursOpen) {
		this.busiHoursOpen = busiHoursOpen;
	}

	public String getBusiHoursClose() {
		return this.busiHoursClose;
	}

	public void setBusiHoursClose(String busiHoursClose) {
		this.busiHoursClose = busiHoursClose;
	}

	@JsonIgnore
	public List<RestaurantPic> getCommonRestaurantPics() {
		ArrayList commonRestaurantPics = new ArrayList();
		if ((this.restaurantPics != null) && (this.restaurantPics.size() > 0)) {
			for (RestaurantPic e : this.restaurantPics) {
				if (e.getPicType().equals(PicTypeEnum.COMMON.getCode())) {
					commonRestaurantPics.add(e);
				}
			}
		}
		return commonRestaurantPics;
	}
}
