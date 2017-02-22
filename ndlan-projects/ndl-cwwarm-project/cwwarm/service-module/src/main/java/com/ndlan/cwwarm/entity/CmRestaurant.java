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
@Table(name = "cm_restaurant" )
public class CmRestaurant  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    @Column(name = "mid"
     ,length = 32
    
    
    
    )
    private String mid;
    /**
     * 市
     **/
    @Column(name = "adr_city"
     ,length = 32
    
    
    
    )
    private String adrCity;
    @Column(name = "syn_database_status"
     ,length = 32
    
    
    
    )
    private String synDatabaseStatus;
    @Column(name = "update_by"
     ,length = 36
    
    
    
    )
    private String updateBy;
    @Column(name = "delivery_charge"
    
    
    
    
    )
    private Long deliveryCharge;
    /**
     * 房屋租凭合同
     **/
    @Column(name = "fangwu"
     ,length = 65535
    
    
    
    )
    private String fangwu;
    @Column(name = "service_score"
    
    
    
    
    )
    private Long serviceScore;
    @Column(name = "overlaycoordinate_x"
     ,length = 32
    
    
    
    )
    private String overlaycoordinateX;
    /**
     * 用逗号隔开多个菜系的ID
     **/
    @Column(name = "dashes_style_id_array"
     ,length = 1024
    
    
    
    )
    private String dashesStyleIdArray;
    /**
     * G1设备码
     **/
    @Column(name = "sn"
     ,length = 64
    
    
    
    )
    private String sn;
    @Column(name = "notes"
     ,length = 2048
    
    
    
    )
    private String notes;
    @Column(name = "store_name"
     ,length = 128
    
    
    
    )
    private String storeName;
    @Column(name = "msg"
     ,length = 128
    
    
    
    )
    private String msg;
    /**
     * 商户描述
     **/
    @Column(name = "description"
     ,length = 360
    
    
    
    )
    private String description;
    @Column(name = "bus_stop"
     ,length = 128
    
    
    
    )
    private String busStop;
    @Column(name = "busi_end"
    
    
    
    
    )
    private String busiEnd;
    @Column(name = "busi_supper_start"
     ,length = 16
    
    
    
    )
    private String busiSupperStart;
    @Column(name = "take_out_status"
    
    
    
    
    )
    private Long takeOutStatus;
    @Column(name = "create_time"
    
    
    
    
    )
    private Date createTime;
    /**
     * 支行名称
     **/
    @Column(name = "bank_branch"
     ,length = 128
    
    
    
    )
    private String bankBranch;
    /**
     * 人均消费
     **/
    @Column(name = "cons_per_person"
    
    
    
    
    )
    private Long consPerPerson;
    /**
     * G2 设备摆放位置照片
     **/
    @Column(name = "file11"
     ,length = 65535
    
    
    
    )
    private String file11;
    @Column(name = "subway"
     ,length = 128
    
    
    
    )
    private String subway;
    @Column(name = "store_id"
     ,length = 36
    
    
    
    )
    private String storeId;
    /**
     * 对应ForeignCategory
     **/
    @Column(name = "foreign_la"
     ,length = 32
    
    
    
    )
    private String foreignLa;
    @Column(name = "update_time"
    
    
    
    
    )
    private Date updateTime;
    /**
     * 电话
     **/
    @Column(name = "linkman_tel"
     ,length = 16
    
    
    
    )
    private String linkmanTel;
    @Column(name = "syn_version_id"
     ,length = 36
    
    
    
    )
    private String synVersionId;
    /**
     * 登陆密码
     **/
    @Column(name = "denglumima"
     ,length = 32
    
    
    
    )
    private String denglumima;
    @Column(name = "bar_path"
     ,length = 65535
    
    
    
    )
    private String barPath;
    @Column(name = "parentshop_id"
     ,length = 65535
    
    
    
    )
    private String parentshopId;
    /**
     * 餐饮服务许可证
     **/
    @Column(name = "file13"
     ,length = 65535
    
    
    
    )
    private String file13;
    @Column(name = "busi_takeout_supper_end"
     ,length = 16
    
    
    
    )
    private String busiTakeoutSupperEnd;
    @Column(name = "overlaycoordinate_y"
     ,length = 32
    
    
    
    )
    private String overlaycoordinateY;
    @Column(name = "taste_score"
    
    
    
    
    )
    private Long tasteScore;
    /**
     * 手机
     **/
    @Column(name = "linkman_phone"
     ,length = 16
    
    
    
    )
    private String linkmanPhone;
    @Column(name = "tilecoordinate_y"
     ,length = 32
    
    
    
    )
    private String tilecoordinateY;
    /**
     * 法人手持身份证正面照
     **/
    @Column(name = "file3"
     ,length = 65535
    
    
    
    )
    private String file3;
    @Column(name = "busi_hours_close"
     ,length = 32
    
    
    
    )
    private String busiHoursClose;
    @Column(name = "event_summary"
     ,length = 1024
    
    
    
    )
    private String eventSummary;
    @Column(name = "busi_breakfast_name"
     ,length = 128
    
    
    
    )
    private String busiBreakfastName;
    @Column(name = "invoice_status"
     ,length = 32
    
    
    
    )
    private String invoiceStatus;
    /**
     * 法人
     **/
    @Column(name = "linkman_name"
     ,length = 128
    
    
    
    )
    private String linkmanName;
    @Column(name = "busi_supper_end"
     ,length = 16
    
    
    
    )
    private String busiSupperEnd;
    /**
     * 注册码
     **/
    @Column(name = "zhucema"
     ,length = 32
    
    
    
    )
    private String zhucema;
    /**
     * 商户营业地址
     **/
    @Column(name = "mch_addr"
     ,length = 128
    
    
    
    )
    private String mchAddr;
    /**
     * 法人
     **/
    @Column(name = "juridical_person"
     ,length = 128
    
    
    
    )
    private String juridicalPerson;
    /**
     * 省
     **/
    @Column(name = "adr_province"
     ,length = 32
    
    
    
    )
    private String adrProvince;
    /**
     * 开户行号联行号
     **/
    @Column(name = "settle_bank"
     ,length = 64
    
    
    
    )
    private String settleBank;
    @Column(name = "viewportcoordinate_y"
     ,length = 32
    
    
    
    )
    private String viewportcoordinateY;
    /**
     * 开户行所在省
     **/
    @Column(name = "province"
     ,length = 128
    
    
    
    )
    private String province;
    /**
     * 法人身份证正面照
     **/
    @Column(name = "file1"
     ,length = 65535
    
    
    
    )
    private String file1;
    @Column(name = "online_order_status"
     ,length = 32
    
    
    
    )
    private String onlineOrderStatus;
    /**
     * 开户行名称
     **/
    @Column(name = "bank_name"
     ,length = 128
    
    
    
    )
    private String bankName;
    /**
     * 开户许可证
     **/
    @Column(name = "kaihuo"
     ,length = 65535
    
    
    
    )
    private String kaihuo;
    @Column(name = "complaint_tel"
     ,length = 32
    
    
    
    )
    private String complaintTel;
    /**
     * 邮箱
     **/
    @Column(name = "email"
     ,length = 20
    
    
    
    )
    private String email;
    /**
     * 对应枚举DiningEnv
     **/
    @Column(name = "dining_env"
     ,length = 1024
    
    
    
    )
    private String diningEnv;
    @Column(name = "busi_takeout_lunch_end"
     ,length = 16
    
    
    
    )
    private String busiTakeoutLunchEnd;
    @Column(name = "busi_start"
    
    
    
    
    )
    private String busiStart;
    /**
     * 法人有效证件种类 1.身份证;2.护照;3军(警)官证;4士兵证; 5台胞证; 6回乡证
     **/
    @Column(name = "cert_type"
     ,length = 1
    
    
    
    )
    private String certType;
    @Column(name = "is_band_cloud_account"
     ,length = 32
    
    
    
    )
    private String isBandCloudAccount;
    /**
     * 营业执照（path）
     **/
    @Column(name = "business_license"
     ,length = 65535
    
    
    
    )
    private String businessLicense;
    @Column(name = "busi_hours_open"
     ,length = 32
    
    
    
    )
    private String busiHoursOpen;
    @Column(name = "online_order_phone"
     ,length = 16
    
    
    
    )
    private String onlineOrderPhone;
    /**
     * 现场照片
     **/
    @Column(name = "xianchang"
     ,length = 65535
    
    
    
    )
    private String xianchang;
    /**
     * 名称
     **/
    @Column(name = "rest_name"
     ,length = 128
    
    
    
    )
    private String restName;
    @Column(name = "busi_breakfast_end"
     ,length = 16
    
    
    
    )
    private String busiBreakfastEnd;
    @Column(name = "special_style"
     ,length = 1024
    
    
    
    )
    private String specialStyle;
    @Column(name = "join_authentication"
    
    
    
    
    )
    private Long joinAuthentication;
    /**
     * 设备类型
     **/
    @Column(name = "shebeileixing"
     ,length = 32
    
    
    
    )
    private String shebeileixing;
    @Column(name = "online_order_dish_status"
     ,length = 32
    
    
    
    )
    private String onlineOrderDishStatus;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "rest_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String restId;
    @Column(name = "viewportcoordinate_x"
     ,length = 32
    
    
    
    )
    private String viewportcoordinateX;
    @Column(name = "shop_type"
     ,length = 20
    
    
    
    )
    private String shopType;
    /**
     * 地铁
     **/
    @Column(name = "adr_detail"
     ,length = 2048
    
    
    
    )
    private String adrDetail;
    @Column(name = "busi_lunch_start"
     ,length = 16
    
    
    
    )
    private String busiLunchStart;
    @Column(name = "shop_status"
     ,length = 65535
    
    
    
    )
    private String shopStatus;
    /**
     * 收单二联单
     **/
    @Column(name = "file14"
     ,length = 65535
    
    
    
    )
    private String file14;
    /**
     * 法人电话
     **/
    @Column(name = "juridical_phone"
     ,length = 60
    
    
    
    )
    private String juridicalPhone;
    /**
     * 开户行账号
     **/
    @Column(name = "settle_accno"
     ,length = 64
    
    
    
    )
    private String settleAccno;
    /**
     * 商户类型
     **/
    @Column(name = "shangleixing"
     ,length = 32
    
    
    
    )
    private String shangleixing;
    @Column(name = "code"
     ,length = 3
    
    
    
    )
    private String code;
    @Column(name = "busi_supper_name"
     ,length = 128
    
    
    
    )
    private String busiSupperName;
    @Column(name = "take_out_end"
    
    
    
    
    )
    private String takeOutEnd;
    /**
     * 用逗号隔开多个菜系name
     **/
    @Column(name = "dishes_style_name_array"
     ,length = 1024
    
    
    
    )
    private String dishesStyleNameArray;
    @Column(name = "pixelcoordinate_y"
     ,length = 32
    
    
    
    )
    private String pixelcoordinateY;
    @Column(name = "busi_lunch_name"
     ,length = 128
    
    
    
    )
    private String busiLunchName;
    /**
     * 法人证件有效日期 格式：YYYYMMDD
     **/
    @Column(name = "cert_expdate"
     ,length = 16
    
    
    
    )
    private String certExpdate;
    /**
     * 组织机构代码
     **/
    @Column(name = "zhouzhijiguo"
     ,length = 65535
    
    
    
    )
    private String zhouzhijiguo;
    @Column(name = "serve_speed_score"
    
    
    
    
    )
    private Long serveSpeedScore;
    @Column(name = "cloud_password"
     ,length = 128
    
    
    
    )
    private String cloudPassword;
    /**
     * 开户姓名
     **/
    @Column(name = "acc_name"
     ,length = 36
    
    
    
    )
    private String accName;
    @Column(name = "cloud_username"
     ,length = 128
    
    
    
    )
    private String cloudUsername;
    /**
     * 详细地址
     **/
    @Column(name = "adr_area"
     ,length = 32
    
    
    
    )
    private String adrArea;
    /**
     * 开户行所在市
     **/
    @Column(name = "city"
     ,length = 128
    
    
    
    )
    private String city;
    @Column(name = "busi_latesnack_start"
     ,length = 16
    
    
    
    )
    private String busiLatesnackStart;
    @Column(name = "event_notice"
     ,length = 1024
    
    
    
    )
    private String eventNotice;
    @Column(name = "pixelcoordinate_x"
     ,length = 32
    
    
    
    )
    private String pixelcoordinateX;
    @Column(name = "order_days"
    
    
    
    
    )
    private Long orderDays;
    @Column(name = "online_takeout_prompts"
     ,length = 2048
    
    
    
    )
    private String onlineTakeoutPrompts;
    @Column(name = "tilecoordinate_x"
     ,length = 32
    
    
    
    )
    private String tilecoordinateX;
    @Column(name = "syn_data_complete_time"
    
    
    
    
    )
    private Date synDataCompleteTime;
    /**
     * 银行账户
     **/
    @Column(name = "bank_account"
     ,length = 128
    
    
    
    )
    private String bankAccount;
    /**
     * 银行卡类型 1.借记卡2.贷记卡
     **/
    @Column(name = "account_type"
     ,length = 3
    
    
    
    )
    private String accountType;
    /**
     * 营业执照名称
     **/
    @Column(name = "c_name"
     ,length = 36
    
    
    
    )
    private String cName;
    @Column(name = "busi_latesnack_name"
     ,length = 128
    
    
    
    )
    private String busiLatesnackName;
    /**
     * 对应SpecialEnv
     **/
    @Column(name = "special_env"
     ,length = 1024
    
    
    
    )
    private String specialEnv;
    @Column(name = "manager"
     ,length = 65535
    
    
    
    )
    private String manager;
    @Column(name = "busi_takeout_lunch_start"
     ,length = 16
    
    
    
    )
    private String busiTakeoutLunchStart;
    /**
     * 面积
     **/
    @Column(name = "rest_area"
    
    
    
    
    )
    private Long restArea;
    /**
     * 法人身份证反面照
     **/
    @Column(name = "file2"
     ,length = 65535
    
    
    
    )
    private String file2;
    @Column(name = "send_price"
    
    
    
    
    )
    private Long sendPrice;
    @Column(name = "total_score"
    
    
    
    
    )
    private Long totalScore;
    @Column(name = "order_tel"
     ,length = 32
    
    
    
    )
    private String orderTel;
    @Column(name = "busi_takeout_supper_start"
     ,length = 16
    
    
    
    )
    private String busiTakeoutSupperStart;
    /**
     * 商户id
     **/
    @Column(name = "merchant_id"
     ,length = 36
    
    
    
    )
    private String merchantId;
    /**
     * 地区编码
     **/
    @Column(name = "area_code"
     ,length = 16
    
    
    
    )
    private String areaCode;
    /**
     * 对公或者对私账户标识   0对公 1对私
     **/
    @Column(name = "is_private"
     ,length = 1
    
    
    
    )
    private String isPrivate;
    @Column(name = "worldcoordinate_x"
     ,length = 32
    
    
    
    )
    private String worldcoordinateX;
    @Column(name = "create_by"
     ,length = 36
    
    
    
    )
    private String createBy;
    @Column(name = "online_rest_status"
     ,length = 32
    
    
    
    )
    private String onlineRestStatus;
    @Column(name = "busi_breakfast_start"
     ,length = 16
    
    
    
    )
    private String busiBreakfastStart;
    /**
     * 店面门头照
     **/
    @Column(name = "file12"
     ,length = 65535
    
    
    
    )
    private String file12;
    @Column(name = "delivery_range"
    
    
    
    
    )
    private Long deliveryRange;
    @Column(name = "advertising_slogan"
     ,length = 128
    
    
    
    )
    private String advertisingSlogan;
    @Column(name = "online_order_prompts"
     ,length = 2048
    
    
    
    )
    private String onlineOrderPrompts;
    @Column(name = "lng"
     ,length = 32
    
    
    
    )
    private String lng;
    /**
     * 法人身份证号
     **/
    @Column(name = "juridical_id"
     ,length = 32
    
    
    
    )
    private String juridicalId;
    @Column(name = "busi_latesnack_end"
     ,length = 16
    
    
    
    )
    private String busiLatesnackEnd;
    /**
     * -1 初始化数据 0未审核 1正在审核 2 审核通过 3 审核被拒绝 4审核被驳回 ,5 走父店结账
     **/
    @Column(name = "rest_examine"
     ,length = 32
    
    
    
    )
    private String restExamine;
    @Column(name = "worldcoordinate_y"
     ,length = 32
    
    
    
    )
    private String worldcoordinateY;
    @Column(name = "busi_lunch_end"
     ,length = 16
    
    
    
    )
    private String busiLunchEnd;
    @Column(name = "environment_score"
    
    
    
    
    )
    private Long environmentScore;
    @Column(name = "lat"
     ,length = 32
    
    
    
    )
    private String lat;
    /**
     * 用逗号隔开多个菜系type
     **/
    @Column(name = "dishes_style_type_array"
     ,length = 1024
    
    
    
    )
    private String dishesStyleTypeArray;
    /**
     * 税务登记证
     **/
    @Column(name = "shuiwu"
     ,length = 65535
    
    
    
    )
    private String shuiwu;
    /**
     * 银行卡正面照
     **/
    @Column(name = "file4"
     ,length = 65535
    
    
    
    )
    private String file4;
    @Column(name = "take_out_start"
    
    
    
    
    )
    private String takeOutStart;
    @Column(name = "syn_data_time"
    
    
    
    
    )
    private Date synDataTime;


   

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMid() {
        return mid;
    }
    public void setAdrCity(String adrCity) {
        this.adrCity = adrCity;
    }

    public String getAdrCity() {
        return adrCity;
    }
    public void setSynDatabaseStatus(String synDatabaseStatus) {
        this.synDatabaseStatus = synDatabaseStatus;
    }

    public String getSynDatabaseStatus() {
        return synDatabaseStatus;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }
    public void setDeliveryCharge(Long deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Long getDeliveryCharge() {
        return deliveryCharge;
    }
    public void setFangwu(String fangwu) {
        this.fangwu = fangwu;
    }

    public String getFangwu() {
        return fangwu;
    }
    public void setServiceScore(Long serviceScore) {
        this.serviceScore = serviceScore;
    }

    public Long getServiceScore() {
        return serviceScore;
    }
    public void setOverlaycoordinateX(String overlaycoordinateX) {
        this.overlaycoordinateX = overlaycoordinateX;
    }

    public String getOverlaycoordinateX() {
        return overlaycoordinateX;
    }
    public void setDashesStyleIdArray(String dashesStyleIdArray) {
        this.dashesStyleIdArray = dashesStyleIdArray;
    }

    public String getDashesStyleIdArray() {
        return dashesStyleIdArray;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public String getBusStop() {
        return busStop;
    }
    public void setBusiEnd(String busiEnd) {
        this.busiEnd = busiEnd;
    }

    public String getBusiEnd() {
        return busiEnd;
    }
    public void setBusiSupperStart(String busiSupperStart) {
        this.busiSupperStart = busiSupperStart;
    }

    public String getBusiSupperStart() {
        return busiSupperStart;
    }
    public void setTakeOutStatus(Long takeOutStatus) {
        this.takeOutStatus = takeOutStatus;
    }

    public Long getTakeOutStatus() {
        return takeOutStatus;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankBranch() {
        return bankBranch;
    }
    public void setConsPerPerson(Long consPerPerson) {
        this.consPerPerson = consPerPerson;
    }

    public Long getConsPerPerson() {
        return consPerPerson;
    }
    public void setFile11(String file11) {
        this.file11 = file11;
    }

    public String getFile11() {
        return file11;
    }
    public void setSubway(String subway) {
        this.subway = subway;
    }

    public String getSubway() {
        return subway;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return storeId;
    }
    public void setForeignLa(String foreignLa) {
        this.foreignLa = foreignLa;
    }

    public String getForeignLa() {
        return foreignLa;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }
    public void setSynVersionId(String synVersionId) {
        this.synVersionId = synVersionId;
    }

    public String getSynVersionId() {
        return synVersionId;
    }
    public void setDenglumima(String denglumima) {
        this.denglumima = denglumima;
    }

    public String getDenglumima() {
        return denglumima;
    }
    public void setBarPath(String barPath) {
        this.barPath = barPath;
    }

    public String getBarPath() {
        return barPath;
    }
    public void setParentshopId(String parentshopId) {
        this.parentshopId = parentshopId;
    }

    public String getParentshopId() {
        return parentshopId;
    }
    public void setFile13(String file13) {
        this.file13 = file13;
    }

    public String getFile13() {
        return file13;
    }
    public void setBusiTakeoutSupperEnd(String busiTakeoutSupperEnd) {
        this.busiTakeoutSupperEnd = busiTakeoutSupperEnd;
    }

    public String getBusiTakeoutSupperEnd() {
        return busiTakeoutSupperEnd;
    }
    public void setOverlaycoordinateY(String overlaycoordinateY) {
        this.overlaycoordinateY = overlaycoordinateY;
    }

    public String getOverlaycoordinateY() {
        return overlaycoordinateY;
    }
    public void setTasteScore(Long tasteScore) {
        this.tasteScore = tasteScore;
    }

    public Long getTasteScore() {
        return tasteScore;
    }
    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }
    public void setTilecoordinateY(String tilecoordinateY) {
        this.tilecoordinateY = tilecoordinateY;
    }

    public String getTilecoordinateY() {
        return tilecoordinateY;
    }
    public void setFile3(String file3) {
        this.file3 = file3;
    }

    public String getFile3() {
        return file3;
    }
    public void setBusiHoursClose(String busiHoursClose) {
        this.busiHoursClose = busiHoursClose;
    }

    public String getBusiHoursClose() {
        return busiHoursClose;
    }
    public void setEventSummary(String eventSummary) {
        this.eventSummary = eventSummary;
    }

    public String getEventSummary() {
        return eventSummary;
    }
    public void setBusiBreakfastName(String busiBreakfastName) {
        this.busiBreakfastName = busiBreakfastName;
    }

    public String getBusiBreakfastName() {
        return busiBreakfastName;
    }
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }
    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanName() {
        return linkmanName;
    }
    public void setBusiSupperEnd(String busiSupperEnd) {
        this.busiSupperEnd = busiSupperEnd;
    }

    public String getBusiSupperEnd() {
        return busiSupperEnd;
    }
    public void setZhucema(String zhucema) {
        this.zhucema = zhucema;
    }

    public String getZhucema() {
        return zhucema;
    }
    public void setMchAddr(String mchAddr) {
        this.mchAddr = mchAddr;
    }

    public String getMchAddr() {
        return mchAddr;
    }
    public void setJuridicalPerson(String juridicalPerson) {
        this.juridicalPerson = juridicalPerson;
    }

    public String getJuridicalPerson() {
        return juridicalPerson;
    }
    public void setAdrProvince(String adrProvince) {
        this.adrProvince = adrProvince;
    }

    public String getAdrProvince() {
        return adrProvince;
    }
    public void setSettleBank(String settleBank) {
        this.settleBank = settleBank;
    }

    public String getSettleBank() {
        return settleBank;
    }
    public void setViewportcoordinateY(String viewportcoordinateY) {
        this.viewportcoordinateY = viewportcoordinateY;
    }

    public String getViewportcoordinateY() {
        return viewportcoordinateY;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }
    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile1() {
        return file1;
    }
    public void setOnlineOrderStatus(String onlineOrderStatus) {
        this.onlineOrderStatus = onlineOrderStatus;
    }

    public String getOnlineOrderStatus() {
        return onlineOrderStatus;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }
    public void setKaihuo(String kaihuo) {
        this.kaihuo = kaihuo;
    }

    public String getKaihuo() {
        return kaihuo;
    }
    public void setComplaintTel(String complaintTel) {
        this.complaintTel = complaintTel;
    }

    public String getComplaintTel() {
        return complaintTel;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setDiningEnv(String diningEnv) {
        this.diningEnv = diningEnv;
    }

    public String getDiningEnv() {
        return diningEnv;
    }
    public void setBusiTakeoutLunchEnd(String busiTakeoutLunchEnd) {
        this.busiTakeoutLunchEnd = busiTakeoutLunchEnd;
    }

    public String getBusiTakeoutLunchEnd() {
        return busiTakeoutLunchEnd;
    }
    public void setBusiStart(String busiStart) {
        this.busiStart = busiStart;
    }

    public String getBusiStart() {
        return busiStart;
    }
    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertType() {
        return certType;
    }
    public void setIsBandCloudAccount(String isBandCloudAccount) {
        this.isBandCloudAccount = isBandCloudAccount;
    }

    public String getIsBandCloudAccount() {
        return isBandCloudAccount;
    }
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }
    public void setBusiHoursOpen(String busiHoursOpen) {
        this.busiHoursOpen = busiHoursOpen;
    }

    public String getBusiHoursOpen() {
        return busiHoursOpen;
    }
    public void setOnlineOrderPhone(String onlineOrderPhone) {
        this.onlineOrderPhone = onlineOrderPhone;
    }

    public String getOnlineOrderPhone() {
        return onlineOrderPhone;
    }
    public void setXianchang(String xianchang) {
        this.xianchang = xianchang;
    }

    public String getXianchang() {
        return xianchang;
    }
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }
    public void setBusiBreakfastEnd(String busiBreakfastEnd) {
        this.busiBreakfastEnd = busiBreakfastEnd;
    }

    public String getBusiBreakfastEnd() {
        return busiBreakfastEnd;
    }
    public void setSpecialStyle(String specialStyle) {
        this.specialStyle = specialStyle;
    }

    public String getSpecialStyle() {
        return specialStyle;
    }
    public void setJoinAuthentication(Long joinAuthentication) {
        this.joinAuthentication = joinAuthentication;
    }

    public Long getJoinAuthentication() {
        return joinAuthentication;
    }
    public void setShebeileixing(String shebeileixing) {
        this.shebeileixing = shebeileixing;
    }

    public String getShebeileixing() {
        return shebeileixing;
    }
    public void setOnlineOrderDishStatus(String onlineOrderDishStatus) {
        this.onlineOrderDishStatus = onlineOrderDishStatus;
    }

    public String getOnlineOrderDishStatus() {
        return onlineOrderDishStatus;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setViewportcoordinateX(String viewportcoordinateX) {
        this.viewportcoordinateX = viewportcoordinateX;
    }

    public String getViewportcoordinateX() {
        return viewportcoordinateX;
    }
    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopType() {
        return shopType;
    }
    public void setAdrDetail(String adrDetail) {
        this.adrDetail = adrDetail;
    }

    public String getAdrDetail() {
        return adrDetail;
    }
    public void setBusiLunchStart(String busiLunchStart) {
        this.busiLunchStart = busiLunchStart;
    }

    public String getBusiLunchStart() {
        return busiLunchStart;
    }
    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getShopStatus() {
        return shopStatus;
    }
    public void setFile14(String file14) {
        this.file14 = file14;
    }

    public String getFile14() {
        return file14;
    }
    public void setJuridicalPhone(String juridicalPhone) {
        this.juridicalPhone = juridicalPhone;
    }

    public String getJuridicalPhone() {
        return juridicalPhone;
    }
    public void setSettleAccno(String settleAccno) {
        this.settleAccno = settleAccno;
    }

    public String getSettleAccno() {
        return settleAccno;
    }
    public void setShangleixing(String shangleixing) {
        this.shangleixing = shangleixing;
    }

    public String getShangleixing() {
        return shangleixing;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setBusiSupperName(String busiSupperName) {
        this.busiSupperName = busiSupperName;
    }

    public String getBusiSupperName() {
        return busiSupperName;
    }
    public void setTakeOutEnd(String takeOutEnd) {
        this.takeOutEnd = takeOutEnd;
    }

    public String getTakeOutEnd() {
        return takeOutEnd;
    }
    public void setDishesStyleNameArray(String dishesStyleNameArray) {
        this.dishesStyleNameArray = dishesStyleNameArray;
    }

    public String getDishesStyleNameArray() {
        return dishesStyleNameArray;
    }
    public void setPixelcoordinateY(String pixelcoordinateY) {
        this.pixelcoordinateY = pixelcoordinateY;
    }

    public String getPixelcoordinateY() {
        return pixelcoordinateY;
    }
    public void setBusiLunchName(String busiLunchName) {
        this.busiLunchName = busiLunchName;
    }

    public String getBusiLunchName() {
        return busiLunchName;
    }
    public void setCertExpdate(String certExpdate) {
        this.certExpdate = certExpdate;
    }

    public String getCertExpdate() {
        return certExpdate;
    }
    public void setZhouzhijiguo(String zhouzhijiguo) {
        this.zhouzhijiguo = zhouzhijiguo;
    }

    public String getZhouzhijiguo() {
        return zhouzhijiguo;
    }
    public void setServeSpeedScore(Long serveSpeedScore) {
        this.serveSpeedScore = serveSpeedScore;
    }

    public Long getServeSpeedScore() {
        return serveSpeedScore;
    }
    public void setCloudPassword(String cloudPassword) {
        this.cloudPassword = cloudPassword;
    }

    public String getCloudPassword() {
        return cloudPassword;
    }
    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccName() {
        return accName;
    }
    public void setCloudUsername(String cloudUsername) {
        this.cloudUsername = cloudUsername;
    }

    public String getCloudUsername() {
        return cloudUsername;
    }
    public void setAdrArea(String adrArea) {
        this.adrArea = adrArea;
    }

    public String getAdrArea() {
        return adrArea;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
    public void setBusiLatesnackStart(String busiLatesnackStart) {
        this.busiLatesnackStart = busiLatesnackStart;
    }

    public String getBusiLatesnackStart() {
        return busiLatesnackStart;
    }
    public void setEventNotice(String eventNotice) {
        this.eventNotice = eventNotice;
    }

    public String getEventNotice() {
        return eventNotice;
    }
    public void setPixelcoordinateX(String pixelcoordinateX) {
        this.pixelcoordinateX = pixelcoordinateX;
    }

    public String getPixelcoordinateX() {
        return pixelcoordinateX;
    }
    public void setOrderDays(Long orderDays) {
        this.orderDays = orderDays;
    }

    public Long getOrderDays() {
        return orderDays;
    }
    public void setOnlineTakeoutPrompts(String onlineTakeoutPrompts) {
        this.onlineTakeoutPrompts = onlineTakeoutPrompts;
    }

    public String getOnlineTakeoutPrompts() {
        return onlineTakeoutPrompts;
    }
    public void setTilecoordinateX(String tilecoordinateX) {
        this.tilecoordinateX = tilecoordinateX;
    }

    public String getTilecoordinateX() {
        return tilecoordinateX;
    }
    public void setSynDataCompleteTime(Date synDataCompleteTime) {
        this.synDataCompleteTime = synDataCompleteTime;
    }

    public Date getSynDataCompleteTime() {
        return synDataCompleteTime;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCName() {
        return cName;
    }
    public void setBusiLatesnackName(String busiLatesnackName) {
        this.busiLatesnackName = busiLatesnackName;
    }

    public String getBusiLatesnackName() {
        return busiLatesnackName;
    }
    public void setSpecialEnv(String specialEnv) {
        this.specialEnv = specialEnv;
    }

    public String getSpecialEnv() {
        return specialEnv;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }
    public void setBusiTakeoutLunchStart(String busiTakeoutLunchStart) {
        this.busiTakeoutLunchStart = busiTakeoutLunchStart;
    }

    public String getBusiTakeoutLunchStart() {
        return busiTakeoutLunchStart;
    }
    public void setRestArea(Long restArea) {
        this.restArea = restArea;
    }

    public Long getRestArea() {
        return restArea;
    }
    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public String getFile2() {
        return file2;
    }
    public void setSendPrice(Long sendPrice) {
        this.sendPrice = sendPrice;
    }

    public Long getSendPrice() {
        return sendPrice;
    }
    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() {
        return totalScore;
    }
    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    public String getOrderTel() {
        return orderTel;
    }
    public void setBusiTakeoutSupperStart(String busiTakeoutSupperStart) {
        this.busiTakeoutSupperStart = busiTakeoutSupperStart;
    }

    public String getBusiTakeoutSupperStart() {
        return busiTakeoutSupperStart;
    }
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantId() {
        return merchantId;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }
    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getIsPrivate() {
        return isPrivate;
    }
    public void setWorldcoordinateX(String worldcoordinateX) {
        this.worldcoordinateX = worldcoordinateX;
    }

    public String getWorldcoordinateX() {
        return worldcoordinateX;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setOnlineRestStatus(String onlineRestStatus) {
        this.onlineRestStatus = onlineRestStatus;
    }

    public String getOnlineRestStatus() {
        return onlineRestStatus;
    }
    public void setBusiBreakfastStart(String busiBreakfastStart) {
        this.busiBreakfastStart = busiBreakfastStart;
    }

    public String getBusiBreakfastStart() {
        return busiBreakfastStart;
    }
    public void setFile12(String file12) {
        this.file12 = file12;
    }

    public String getFile12() {
        return file12;
    }
    public void setDeliveryRange(Long deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public Long getDeliveryRange() {
        return deliveryRange;
    }
    public void setAdvertisingSlogan(String advertisingSlogan) {
        this.advertisingSlogan = advertisingSlogan;
    }

    public String getAdvertisingSlogan() {
        return advertisingSlogan;
    }
    public void setOnlineOrderPrompts(String onlineOrderPrompts) {
        this.onlineOrderPrompts = onlineOrderPrompts;
    }

    public String getOnlineOrderPrompts() {
        return onlineOrderPrompts;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }
    public void setJuridicalId(String juridicalId) {
        this.juridicalId = juridicalId;
    }

    public String getJuridicalId() {
        return juridicalId;
    }
    public void setBusiLatesnackEnd(String busiLatesnackEnd) {
        this.busiLatesnackEnd = busiLatesnackEnd;
    }

    public String getBusiLatesnackEnd() {
        return busiLatesnackEnd;
    }
    public void setRestExamine(String restExamine) {
        this.restExamine = restExamine;
    }

    public String getRestExamine() {
        return restExamine;
    }
    public void setWorldcoordinateY(String worldcoordinateY) {
        this.worldcoordinateY = worldcoordinateY;
    }

    public String getWorldcoordinateY() {
        return worldcoordinateY;
    }
    public void setBusiLunchEnd(String busiLunchEnd) {
        this.busiLunchEnd = busiLunchEnd;
    }

    public String getBusiLunchEnd() {
        return busiLunchEnd;
    }
    public void setEnvironmentScore(Long environmentScore) {
        this.environmentScore = environmentScore;
    }

    public Long getEnvironmentScore() {
        return environmentScore;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }
    public void setDishesStyleTypeArray(String dishesStyleTypeArray) {
        this.dishesStyleTypeArray = dishesStyleTypeArray;
    }

    public String getDishesStyleTypeArray() {
        return dishesStyleTypeArray;
    }
    public void setShuiwu(String shuiwu) {
        this.shuiwu = shuiwu;
    }

    public String getShuiwu() {
        return shuiwu;
    }
    public void setFile4(String file4) {
        this.file4 = file4;
    }

    public String getFile4() {
        return file4;
    }
    public void setTakeOutStart(String takeOutStart) {
        this.takeOutStart = takeOutStart;
    }

    public String getTakeOutStart() {
        return takeOutStart;
    }
    public void setSynDataTime(Date synDataTime) {
        this.synDataTime = synDataTime;
    }

    public Date getSynDataTime() {
        return synDataTime;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
