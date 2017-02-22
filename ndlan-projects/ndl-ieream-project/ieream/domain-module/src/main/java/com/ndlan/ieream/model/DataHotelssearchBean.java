package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class DataHotelssearchBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 2016-03-20 06:13
     **/
    private String create_time;
    private String sort;
    /**
     * 文艺的民宿 一缕茶香
     **/
    private String title;
    /**
     * 999
     **/
    private String limit_count;
    /**
     * 0571-881777882
     **/
    private String tel;
    /**
     * 2016-03-20
     **/
    private Date begin_time;
    /**
     * 2016-03-20
     **/
    private Date end_time;
    private String reply_count;
    private String duration;
    private String how;
    /**
     * 1458403200
     **/
    private String sTime;
    /**
     * 1,2,3,4,5,6,7
     **/
    private String service_days_week;
    /**
     * 2016-03-21 12:33
     **/
    private String update_time;
    /**
     * <p>一期一会，一缕茶香</p><p>享
     **/
    private String detail;
    private String price_rule;
    private String discount;
    private String designer;
    private String camera;
    private String site;
    private String is_recommend;
    /**
     * 13305816576
     **/
    private String mobile;
    /**
     * /Uploads/Picture/201
     **/
    private String cover;
    private String email;
    /**
     * 380.00
     **/
    private String price_unit;
    /**
     * 1
     **/
    private String service_days_type;
    private String wechat;
    /**
     * yuanlutanluzhe
     **/
    private String wechat_public;
    private String presentation;
    private String service_days_special;
    /**
     * 11
     **/
    private String view_count;
    private String why;
    /**
     * 四眼井180号(近虎跑路)
     **/
    private String pos_address;
    /**
     * 18
     **/
    private String id;
    private String producer;
    /**
     * 不详
     **/
    private String price_estimate;
    private String summary;
    /**
     * 1458403200
     **/
    private String eTime;

      private ProvinceDataHotelssearchBean provinceDataHotelssearch;
      private List< Tag_listDataHotelssearchBean > tag_listDataHotelssearch;
      private CityDataHotelssearchBean cityDataHotelssearch;
      private DistrictDataHotelssearchBean districtDataHotelssearch;

  
	
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time() {
        return create_time;
    }

	
    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
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

	
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

	
    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getBegin_time() {
        return begin_time;
    }

	
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

	
    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getReply_count() {
        return reply_count;
    }

	

	
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

	
    public void setHow(String how) {
        this.how = how;
    }

    public String getHow() {
        return how;
    }

	
    public void setSTime(String sTime) {
        this.sTime = sTime;
    }

    public String getSTime() {
        return sTime;
    }

	
    public void setService_days_week(String service_days_week) {
        this.service_days_week = service_days_week;
    }

    public String getService_days_week() {
        return service_days_week;
    }

	
    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

	
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

	
    public void setPrice_rule(String price_rule) {
        this.price_rule = price_rule;
    }

    public String getPrice_rule() {
        return price_rule;
    }

	
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

	
    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getDesigner() {
        return designer;
    }

	

	
    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCamera() {
        return camera;
    }

	

	
    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
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

	
    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

	

	
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

	
    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getPrice_unit() {
        return price_unit;
    }

	
    public void setService_days_type(String service_days_type) {
        this.service_days_type = service_days_type;
    }

    public String getService_days_type() {
        return service_days_type;
    }

	
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

	
    public void setWechat_public(String wechat_public) {
        this.wechat_public = wechat_public;
    }

    public String getWechat_public() {
        return wechat_public;
    }

	
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

	
    public void setService_days_special(String service_days_special) {
        this.service_days_special = service_days_special;
    }

    public String getService_days_special() {
        return service_days_special;
    }

	
    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getView_count() {
        return view_count;
    }

	
    public void setWhy(String why) {
        this.why = why;
    }

    public String getWhy() {
        return why;
    }

	
    public void setPos_address(String pos_address) {
        this.pos_address = pos_address;
    }

    public String getPos_address() {
        return pos_address;
    }

	
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

	
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

	
    public void setPrice_estimate(String price_estimate) {
        this.price_estimate = price_estimate;
    }

    public String getPrice_estimate() {
        return price_estimate;
    }

	
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

	
    public void setETime(String eTime) {
        this.eTime = eTime;
    }

    public String getETime() {
        return eTime;
    }


    public void setProvince(ProvinceDataHotelssearchBean provinceDataHotelssearch) {
        this.provinceDataHotelssearch = provinceDataHotelssearch;
    }

    public ProvinceDataHotelssearchBean getProvince() {
        return provinceDataHotelssearch;
    }
    public void setTag_list(List< Tag_listDataHotelssearchBean > tag_listDataHotelssearch) {
        this.tag_listDataHotelssearch = tag_listDataHotelssearch;
    }

    public List< Tag_listDataHotelssearchBean > getTag_list() {
        return tag_listDataHotelssearch;
    }
    public void setCity(CityDataHotelssearchBean cityDataHotelssearch) {
        this.cityDataHotelssearch = cityDataHotelssearch;
    }

    public CityDataHotelssearchBean getCity() {
        return cityDataHotelssearch;
    }
    public void setDistrict(DistrictDataHotelssearchBean districtDataHotelssearch) {
        this.districtDataHotelssearch = districtDataHotelssearch;
    }

    public DistrictDataHotelssearchBean getDistrict() {
        return districtDataHotelssearch;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
