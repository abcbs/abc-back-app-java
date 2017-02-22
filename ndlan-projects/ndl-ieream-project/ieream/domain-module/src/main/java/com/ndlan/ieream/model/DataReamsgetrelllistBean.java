package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class DataReamsgetrelllistBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 30.00
     **/
    private String price_unit;
    private String how;
    private String summary;
    private String surprise;
    /**
     * 1458492354
     **/
    private String create_time;
    private String price_rule;
    private String is_recommend;
    /**
     * 8
     **/
    private String duration;
    /**
     * guochuntao@ieream.co
     **/
    private String email;
    /**
     * 道里区
     **/
    private String pos_address;
    private String preface;
    /**
     * 1
     **/
    private String reply_count;
    private String site;
    /**
     * 1458489600
     **/
    private String sTime;
    private String why;
    /**
     * 1458489600
     **/
    private String eTime;
    private String event;
    /**
     * 60638183
     **/
    private String tel;
    private String postscript;
    /**
     * 1458493353
     **/
    private String update_time;
    /**
     * 13901052797
     **/
    private String mobile;
    /**
     * 22
     **/
    private String id;
    /**
     * 想在中国了解东正教，去哪儿？哈尔滨呗
     **/
    private String title;
    /**
     * 10
     **/
    private String limit_count;
    /**
     * 5,6,7
     **/
    private String service_days_week;
    /**
     * 90
     **/
    private String discount;
    /**
     * <p>东正教文化在哈尔滨的前世今生</p
     **/
    private String detail;
    /**
     * 21
     **/
    private String view_count;
    /**
     * dayuchawen
     **/
    private String wechat;
    /**
     * 丫蛋儿村
     **/
    private String producer;
    /**
     * 丫蛋儿
     **/
    private String camera;

      private ProvinceDataReamsgetrelllistBean provinceDataReamsgetrelllist;
      private CityDataReamsgetrelllistBean cityDataReamsgetrelllist;
      private List< Tag_listDataReamsgetrelllistBean > tag_listDataReamsgetrelllist;
      private DistrictDataReamsgetrelllistBean districtDataReamsgetrelllist;

  
	
    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getPrice_unit() {
        return price_unit;
    }

	
    public void setHow(String how) {
        this.how = how;
    }

    public String getHow() {
        return how;
    }

	
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

	
    public void setSurprise(String surprise) {
        this.surprise = surprise;
    }

    public String getSurprise() {
        return surprise;
    }

	

	
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time() {
        return create_time;
    }

	
    public void setPrice_rule(String price_rule) {
        this.price_rule = price_rule;
    }

    public String getPrice_rule() {
        return price_rule;
    }

	
    public void setIs_recommend(String is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getIs_recommend() {
        return is_recommend;
    }

	
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

	
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

	
    public void setPos_address(String pos_address) {
        this.pos_address = pos_address;
    }

    public String getPos_address() {
        return pos_address;
    }

	
    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getPreface() {
        return preface;
    }

	
    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getReply_count() {
        return reply_count;
    }

	
    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

	
    public void setSTime(String sTime) {
        this.sTime = sTime;
    }

    public String getSTime() {
        return sTime;
    }

	
    public void setWhy(String why) {
        this.why = why;
    }

    public String getWhy() {
        return why;
    }

	

	
    public void setETime(String eTime) {
        this.eTime = eTime;
    }

    public String getETime() {
        return eTime;
    }

	
    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

	
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

	
    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getPostscript() {
        return postscript;
    }

	
    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

	
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

	
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

	
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

	
    public void setLimit_count(String limit_count) {
        this.limit_count = limit_count;
    }

    public String getLimit_count() {
        return limit_count;
    }

	
    public void setService_days_week(String service_days_week) {
        this.service_days_week = service_days_week;
    }

    public String getService_days_week() {
        return service_days_week;
    }

	

	
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

	
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

	

	
    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getView_count() {
        return view_count;
    }

	
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

	
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

	
    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCamera() {
        return camera;
    }


    public void setProvince(ProvinceDataReamsgetrelllistBean provinceDataReamsgetrelllist) {
        this.provinceDataReamsgetrelllist = provinceDataReamsgetrelllist;
    }

    public ProvinceDataReamsgetrelllistBean getProvince() {
        return provinceDataReamsgetrelllist;
    }
    public void setCity(CityDataReamsgetrelllistBean cityDataReamsgetrelllist) {
        this.cityDataReamsgetrelllist = cityDataReamsgetrelllist;
    }

    public CityDataReamsgetrelllistBean getCity() {
        return cityDataReamsgetrelllist;
    }
    public void setTag_list(List< Tag_listDataReamsgetrelllistBean > tag_listDataReamsgetrelllist) {
        this.tag_listDataReamsgetrelllist = tag_listDataReamsgetrelllist;
    }

    public List< Tag_listDataReamsgetrelllistBean > getTag_list() {
        return tag_listDataReamsgetrelllist;
    }
    public void setDistrict(DistrictDataReamsgetrelllistBean districtDataReamsgetrelllist) {
        this.districtDataReamsgetrelllist = districtDataReamsgetrelllist;
    }

    public DistrictDataReamsgetrelllistBean getDistrict() {
        return districtDataReamsgetrelllist;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
