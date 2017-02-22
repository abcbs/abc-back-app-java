package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class DataHotelsGetItemBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    private String eTime;
    /**
     * 32
     **/
    private String view_count;
    /**
     * 民谣里有一个美丽岛的远古作品
     **/
    private String presentation;
    /**
     * 民谣里有一个美丽岛的远古作品
     **/
    private String summary;
    /**
     * 242
     **/
    private String wechat;
    private String reply_count;
    /**
     * end_time
     **/
    private String end_time;
    /**
     * create_time
     **/
    private String create_time;
    private String price_estimate;
    /**
     * 90
     **/
    private String tel;
    private String price_rule;
    /**
     * 1,2,3
     **/
    private String service_days_week;
    /**
     * /Uploads/Picture/201
     **/
    private String cover;
    /**
     * 1
     **/
    private String id;
    private String service_days_special;
    /**
     * 124
     **/
    private String limit_count;
    /**
     * 2016-03-14 04:41
     **/
    private String update_time;
    private String producer;
    private String sTime;
    /**
     * http://www.sina.com.
     **/
    private String site;
    /**
     * 242
     **/
    private String email;
    private String camera;
    /**
     * 1360.00
     **/
    private String price_unit;
    private String how;
    private String why;
    /**
     * <p><strong>S</strong
     **/
    private String detail;
    /**
     * 岛中央
     **/
    private String pos_address;
    /**
     * 德夫
     **/
    private String designer;
    /**
     * 1
     **/
    private String discount;
    private String sort;
    private String is_recommend;
    /**
     * begin_time
     **/
    private String begin_time;
    private String duration;
    /**
     * 123
     **/
    private String mobile;
    /**
     * 美丽岛
     **/
    private String title;
    /**
     * houyaself
     **/
    private String wechat_public;
    /**
     * 1
     **/
    private String service_days_type;

      private CityDataHotelsGetItemBean cityDataHotelsGetItem;
      private DistrictDataHotelsGetItemBean districtDataHotelsGetItem;
      private ProvinceDataHotelsGetItemBean provinceDataHotelsGetItem;
      private List< Tag_listDataHotelsGetItemBean > tag_listDataHotelsGetItem;

  
	
    public void setETime(String eTime) {
        this.eTime = eTime;
    }

    public String getETime() {
        return eTime;
    }

	
    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getView_count() {
        return view_count;
    }

	
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
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

	
    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getReply_count() {
        return reply_count;
    }

	
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_time() {
        return end_time;
    }

	
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time() {
        return create_time;
    }

	
    public void setPrice_estimate(String price_estimate) {
        this.price_estimate = price_estimate;
    }

    public String getPrice_estimate() {
        return price_estimate;
    }

	
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

	
    public void setPrice_rule(String price_rule) {
        this.price_rule = price_rule;
    }

    public String getPrice_rule() {
        return price_rule;
    }

	
    public void setService_days_week(String service_days_week) {
        this.service_days_week = service_days_week;
    }

    public String getService_days_week() {
        return service_days_week;
    }

	
    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

	
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

	
    public void setService_days_special(String service_days_special) {
        this.service_days_special = service_days_special;
    }

    public String getService_days_special() {
        return service_days_special;
    }

	
    public void setLimit_count(String limit_count) {
        this.limit_count = limit_count;
    }

    public String getLimit_count() {
        return limit_count;
    }

	
    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

	
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

	
    public void setSTime(String sTime) {
        this.sTime = sTime;
    }

    public String getSTime() {
        return sTime;
    }

	
    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

	
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

	

	
    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCamera() {
        return camera;
    }

	

	
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

	
    public void setWhy(String why) {
        this.why = why;
    }

    public String getWhy() {
        return why;
    }

	
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

	
    public void setPos_address(String pos_address) {
        this.pos_address = pos_address;
    }

    public String getPos_address() {
        return pos_address;
    }

	
    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getDesigner() {
        return designer;
    }

	
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

	
    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

	
    public void setIs_recommend(String is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getIs_recommend() {
        return is_recommend;
    }

	
    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getBegin_time() {
        return begin_time;
    }

	
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

	
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

	
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

	
    public void setWechat_public(String wechat_public) {
        this.wechat_public = wechat_public;
    }

    public String getWechat_public() {
        return wechat_public;
    }

	

	
    public void setService_days_type(String service_days_type) {
        this.service_days_type = service_days_type;
    }

    public String getService_days_type() {
        return service_days_type;
    }


    public void setCity(CityDataHotelsGetItemBean cityDataHotelsGetItem) {
        this.cityDataHotelsGetItem = cityDataHotelsGetItem;
    }

    public CityDataHotelsGetItemBean getCity() {
        return cityDataHotelsGetItem;
    }
    public void setDistrict(DistrictDataHotelsGetItemBean districtDataHotelsGetItem) {
        this.districtDataHotelsGetItem = districtDataHotelsGetItem;
    }

    public DistrictDataHotelsGetItemBean getDistrict() {
        return districtDataHotelsGetItem;
    }
    public void setProvince(ProvinceDataHotelsGetItemBean provinceDataHotelsGetItem) {
        this.provinceDataHotelsGetItem = provinceDataHotelsGetItem;
    }

    public ProvinceDataHotelsGetItemBean getProvince() {
        return provinceDataHotelsGetItem;
    }
    public void setTag_list(List< Tag_listDataHotelsGetItemBean > tag_listDataHotelsGetItem) {
        this.tag_listDataHotelsGetItem = tag_listDataHotelsGetItem;
    }

    public List< Tag_listDataHotelsGetItemBean > getTag_list() {
        return tag_listDataHotelsGetItem;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
