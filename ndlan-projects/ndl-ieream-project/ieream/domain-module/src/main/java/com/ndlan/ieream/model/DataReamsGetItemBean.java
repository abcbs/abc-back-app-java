package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class DataReamsGetItemBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    private String price_rule;
    /**
     * 渔三儿
     **/
    private String camera;
    private String summary;
    /**
     * 2424
     **/
    private String wechat;
    private String preface;
    /**
     * 242.00
     **/
    private String price_unit;
    /**
     * “共产主义”小岛闲居3日
     **/
    private String title;
    /**
     * 242
     **/
    private String email;
    /**
     * 1457020800
     **/
    private String sTime;
    /**
     * 3453
     **/
    private String tel;
    private String site;
    /**
     * 桃花岛主
     **/
    private String producer;
    /**
     * 1458494977
     **/
    private String update_time;
    private String why;
    /**
     * 1457280000
     **/
    private String eTime;
    /**
     * 3
     **/
    private String duration;
    /**
     * <p>&nbsp; &nbsp; &nb
     **/
    private String detail;
    private String event;
    /**
     * 0
     **/
    private String is_recommend;
    /**
     * 4242
     **/
    private String mobile;
    private String postscript;
    /**
     * 12
     **/
    private String limit_count;
    /**
     * 7
     **/
    private String service_days_week;
    private String how;
    /**
     * 1456890933
     **/
    private String create_time;
    /**
     * 0
     **/
    private String reply_count;
    /**
     * 68
     **/
    private String view_count;
    private String surprise;
    /**
     * 6
     **/
    private String id;
    /**
     * 獐子岛
     **/
    private String pos_address;
    /**
     * 80
     **/
    private String discount;

      private DistrictDataReamsGetItemBean districtDataReamsGetItem;
      private ProvinceDataReamsGetItemBean provinceDataReamsGetItem;
      private List< Tag_listDataReamsGetItemBean > tag_listDataReamsGetItem;
      private CityDataReamsGetItemBean cityDataReamsGetItem;

  
	
    public void setPrice_rule(String price_rule) {
        this.price_rule = price_rule;
    }

    public String getPrice_rule() {
        return price_rule;
    }

	
    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCamera() {
        return camera;
    }

	
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

	
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

	
    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getPreface() {
        return preface;
    }

	
    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getPrice_unit() {
        return price_unit;
    }

	
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

	
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

	
    public void setSTime(String sTime) {
        this.sTime = sTime;
    }

    public String getSTime() {
        return sTime;
    }

	
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

	
    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

	

	
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

	
    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_time() {
        return update_time;
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

	
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

	
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

	
    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

	
    public void setIs_recommend(String is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getIs_recommend() {
        return is_recommend;
    }

	

	
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

	
    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getPostscript() {
        return postscript;
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

	
    public void setHow(String how) {
        this.how = how;
    }

    public String getHow() {
        return how;
    }

	
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time() {
        return create_time;
    }

	
    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getReply_count() {
        return reply_count;
    }

	
    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getView_count() {
        return view_count;
    }

	
    public void setSurprise(String surprise) {
        this.surprise = surprise;
    }

    public String getSurprise() {
        return surprise;
    }

	

	
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

	
    public void setPos_address(String pos_address) {
        this.pos_address = pos_address;
    }

    public String getPos_address() {
        return pos_address;
    }

	
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

	


    public void setDistrict(DistrictDataReamsGetItemBean districtDataReamsGetItem) {
        this.districtDataReamsGetItem = districtDataReamsGetItem;
    }

    public DistrictDataReamsGetItemBean getDistrict() {
        return districtDataReamsGetItem;
    }
    public void setProvince(ProvinceDataReamsGetItemBean provinceDataReamsGetItem) {
        this.provinceDataReamsGetItem = provinceDataReamsGetItem;
    }

    public ProvinceDataReamsGetItemBean getProvince() {
        return provinceDataReamsGetItem;
    }
    public void setTag_list(List< Tag_listDataReamsGetItemBean > tag_listDataReamsGetItem) {
        this.tag_listDataReamsGetItem = tag_listDataReamsGetItem;
    }

    public List< Tag_listDataReamsGetItemBean > getTag_list() {
        return tag_listDataReamsGetItem;
    }
    public void setCity(CityDataReamsGetItemBean cityDataReamsGetItem) {
        this.cityDataReamsGetItem = cityDataReamsGetItem;
    }

    public CityDataReamsGetItemBean getCity() {
        return cityDataReamsGetItem;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
