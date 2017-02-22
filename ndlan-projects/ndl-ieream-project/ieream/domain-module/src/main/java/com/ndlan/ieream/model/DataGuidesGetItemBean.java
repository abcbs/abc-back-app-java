package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class DataGuidesGetItemBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * ieream
     **/
    private String work_unit;
    /**
     * 15614405706
     **/
    private String tel;
    /**
     * aricha
     **/
    private String recommend_person;
    private String self_introduction;
    private String price_rule;
    /**
     * 太多了
     **/
    private String special;
    private String service_days_special;
    /**
     * doctor
     **/
    private String diploma;
    /**
     * 1457745068
     **/
    private String reg_time;
    /**
     * 后崖
     **/
    private String remark_one;
    /**
     * 后崖，无处不在的矫情哈
     **/
    private String signature;
    private String bi_language;
    private String reg_ip;
    /**
     * 1
     **/
    private String last_login_role;
    private String service_days_week;
    /**
     * 1458714395
     **/
    private String last_login_time;
    /**
     * 30
     **/
    private String price_unit;
    /**
     * 17
     **/
    private String login;
    /**
     * 1
     **/
    private String sex;
    /**
     * 北京市
     **/
    private String address;
    private String recommend_desc;
    /**
     * houyaself
     **/
    private String wechat;
    /**
     * harerbin
     **/
    private String domicile;
    private String value_added_items;
    /**
     * 18601013399
     **/
    private String recommend_tel;
    /**
     * 0000-00-00
     **/
    private Date birthday;
    private String service_days_type;
    /**
     * 后崖
     **/
    private String netname;
    /**
     * onc4hy@126.com
     **/
    private String email_1;
    /**
     * 侯义安
     **/
    private String realname;
    private String invite_code;
    private String end_time;
    /**
     * 230108
     **/
    private String idcard;
    /**
     * <p>俺的自述，很简单哈<br/></p
     **/
    private String self_desc;
    /**
     * 102
     **/
    private String uid;
    private String begin_time;
    private String guidecard;
    /**
     * hljuniversity
     **/
    private String institute;
    /**
     * onc4hy
     **/
    private String nickname;
    private String union_count;
    /**
     * 3722193911
     **/
    private String last_login_ip;
    /**
     * 2782948157
     **/
    private String qq;
    /**
     * 100001
     **/
    private String postcode;
    private String pos_community;
    /**
     * 1
     **/
    private String show_role;

      private CityDataGuidesGetItemBean cityDataGuidesGetItem;
      private AvatarDataGuidesGetItemBean avatarDataGuidesGetItem;
      private DistrictDataGuidesGetItemBean districtDataGuidesGetItem;
      private ProvinceDataGuidesGetItemBean provinceDataGuidesGetItem;
      private List< Union_guide_id_listDataGuidesGetItemBean > union_guide_id_listDataGuidesGetItem;

  
	
    public void setWork_unit(String work_unit) {
        this.work_unit = work_unit;
    }

    public String getWork_unit() {
        return work_unit;
    }

	
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

	
    public void setRecommend_person(String recommend_person) {
        this.recommend_person = recommend_person;
    }

    public String getRecommend_person() {
        return recommend_person;
    }

	
    public void setSelf_introduction(String self_introduction) {
        this.self_introduction = self_introduction;
    }

    public String getSelf_introduction() {
        return self_introduction;
    }

	
    public void setPrice_rule(String price_rule) {
        this.price_rule = price_rule;
    }

    public String getPrice_rule() {
        return price_rule;
    }

	

	
    public void setSpecial(String special) {
        this.special = special;
    }

    public String getSpecial() {
        return special;
    }

	
    public void setService_days_special(String service_days_special) {
        this.service_days_special = service_days_special;
    }

    public String getService_days_special() {
        return service_days_special;
    }

	
    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getDiploma() {
        return diploma;
    }

	
    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getReg_time() {
        return reg_time;
    }

	
    public void setRemark_one(String remark_one) {
        this.remark_one = remark_one;
    }

    public String getRemark_one() {
        return remark_one;
    }

	

	
    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

	
    public void setBi_language(String bi_language) {
        this.bi_language = bi_language;
    }

    public String getBi_language() {
        return bi_language;
    }

	
    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    public String getReg_ip() {
        return reg_ip;
    }

	
    public void setLast_login_role(String last_login_role) {
        this.last_login_role = last_login_role;
    }

    public String getLast_login_role() {
        return last_login_role;
    }

	
    public void setService_days_week(String service_days_week) {
        this.service_days_week = service_days_week;
    }

    public String getService_days_week() {
        return service_days_week;
    }

	
    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

	
    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getPrice_unit() {
        return price_unit;
    }

	
    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

	
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

	

	
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

	
    public void setRecommend_desc(String recommend_desc) {
        this.recommend_desc = recommend_desc;
    }

    public String getRecommend_desc() {
        return recommend_desc;
    }

	
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

	
    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getDomicile() {
        return domicile;
    }

	
    public void setValue_added_items(String value_added_items) {
        this.value_added_items = value_added_items;
    }

    public String getValue_added_items() {
        return value_added_items;
    }

	
    public void setRecommend_tel(String recommend_tel) {
        this.recommend_tel = recommend_tel;
    }

    public String getRecommend_tel() {
        return recommend_tel;
    }

	
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

	
    public void setService_days_type(String service_days_type) {
        this.service_days_type = service_days_type;
    }

    public String getService_days_type() {
        return service_days_type;
    }

	
    public void setNetname(String netname) {
        this.netname = netname;
    }

    public String getNetname() {
        return netname;
    }

	
    public void setEmail_1(String email_1) {
        this.email_1 = email_1;
    }

    public String getEmail_1() {
        return email_1;
    }

	
    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRealname() {
        return realname;
    }

	

	

	
    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getInvite_code() {
        return invite_code;
    }

	
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_time() {
        return end_time;
    }

	
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcard() {
        return idcard;
    }

	
    public void setSelf_desc(String self_desc) {
        this.self_desc = self_desc;
    }

    public String getSelf_desc() {
        return self_desc;
    }

	
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

	
    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getBegin_time() {
        return begin_time;
    }

	
    public void setGuidecard(String guidecard) {
        this.guidecard = guidecard;
    }

    public String getGuidecard() {
        return guidecard;
    }

	
    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getInstitute() {
        return institute;
    }

	
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

	
    public void setUnion_count(String union_count) {
        this.union_count = union_count;
    }

    public String getUnion_count() {
        return union_count;
    }

	
    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

	
    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQq() {
        return qq;
    }

	
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

	
    public void setPos_community(String pos_community) {
        this.pos_community = pos_community;
    }

    public String getPos_community() {
        return pos_community;
    }

	
    public void setShow_role(String show_role) {
        this.show_role = show_role;
    }

    public String getShow_role() {
        return show_role;
    }


    public void setCity(CityDataGuidesGetItemBean cityDataGuidesGetItem) {
        this.cityDataGuidesGetItem = cityDataGuidesGetItem;
    }

    public CityDataGuidesGetItemBean getCity() {
        return cityDataGuidesGetItem;
    }
    public void setAvatar(AvatarDataGuidesGetItemBean avatarDataGuidesGetItem) {
        this.avatarDataGuidesGetItem = avatarDataGuidesGetItem;
    }

    public AvatarDataGuidesGetItemBean getAvatar() {
        return avatarDataGuidesGetItem;
    }
    public void setDistrict(DistrictDataGuidesGetItemBean districtDataGuidesGetItem) {
        this.districtDataGuidesGetItem = districtDataGuidesGetItem;
    }

    public DistrictDataGuidesGetItemBean getDistrict() {
        return districtDataGuidesGetItem;
    }
    public void setProvince(ProvinceDataGuidesGetItemBean provinceDataGuidesGetItem) {
        this.provinceDataGuidesGetItem = provinceDataGuidesGetItem;
    }

    public ProvinceDataGuidesGetItemBean getProvince() {
        return provinceDataGuidesGetItem;
    }
    public void setUnion_guide_id_list(List< Union_guide_id_listDataGuidesGetItemBean > union_guide_id_listDataGuidesGetItem) {
        this.union_guide_id_listDataGuidesGetItem = union_guide_id_listDataGuidesGetItem;
    }

    public List< Union_guide_id_listDataGuidesGetItemBean > getUnion_guide_id_list() {
        return union_guide_id_listDataGuidesGetItem;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
