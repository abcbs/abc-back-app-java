package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class DataReamsgetlistBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    private String producer;
    /**
     * 1456329600
     **/
    private String sTime;
    /**
     * 1456290460
     **/
    private String create_time;
    /**
     * 0.00
     **/
    private String price_unit;
    /**
     * 5
     **/
    private String limit_count;
    private String preface;
    private String camera;
    /**
     * 1456329600
     **/
    private String eTime;
    /**
     * 6,7,5
     **/
    private String service_days_week;
    /**
     * 标有
     **/
    private String pos_address;
    private String postscript;
    private String summary;
    /**
     * 离离
     **/
    private String title;
    private String event;
    private String email;
    /**
     * 1
     **/
    private String id;
    private String duration;
    private String how;
    private String surprise;
    private String price_rule;
    private String reply_count;
    /**
     * <p>242243<br/></p>
     **/
    private String detail;
    private String site;
    /**
     * 1456290460
     **/
    private String update_time;
    private String wechat;
    private String is_recommend;
    private String discount;
    private String why;
    private String mobile;
    private String tel;
    private String view_count;

      private CityDataReamsgetlistBean cityDataReamsgetlist;
      private ProvinceDataReamsgetlistBean provinceDataReamsgetlist;
      private List< Tag_listDataReamsgetlistBean > tag_listDataReamsgetlist;
      private DistrictDataReamsgetlistBean districtDataReamsgetlist;

  
	
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

	

	
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time() {
        return create_time;
    }

	
    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getPrice_unit() {
        return price_unit;
    }

	
    public void setLimit_count(String limit_count) {
        this.limit_count = limit_count;
    }

    public String getLimit_count() {
        return limit_count;
    }

	
    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getPreface() {
        return preface;
    }

	
    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCamera() {
        return camera;
    }

	
    public void setETime(String eTime) {
        this.eTime = eTime;
    }

    public String getETime() {
        return eTime;
    }

	
    public void setService_days_week(String service_days_week) {
        this.service_days_week = service_days_week;
    }

    public String getService_days_week() {
        return service_days_week;
    }

	
    public void setPos_address(String pos_address) {
        this.pos_address = pos_address;
    }

    public String getPos_address() {
        return pos_address;
    }

	
    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getPostscript() {
        return postscript;
    }

	
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

	
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

	
    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

	
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

	
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

	
    public void setSurprise(String surprise) {
        this.surprise = surprise;
    }

    public String getSurprise() {
        return surprise;
    }

	
    public void setPrice_rule(String price_rule) {
        this.price_rule = price_rule;
    }

    public String getPrice_rule() {
        return price_rule;
    }

	
    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getReply_count() {
        return reply_count;
    }

	

	
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

	
    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

	
    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

	
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

	

	
    public void setIs_recommend(String is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getIs_recommend() {
        return is_recommend;
    }

	
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

	
    public void setWhy(String why) {
        this.why = why;
    }

    public String getWhy() {
        return why;
    }

	
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

	
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

	
    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getView_count() {
        return view_count;
    }


    public void setCity(CityDataReamsgetlistBean cityDataReamsgetlist) {
        this.cityDataReamsgetlist = cityDataReamsgetlist;
    }

    public CityDataReamsgetlistBean getCity() {
        return cityDataReamsgetlist;
    }
    public void setProvince(ProvinceDataReamsgetlistBean provinceDataReamsgetlist) {
        this.provinceDataReamsgetlist = provinceDataReamsgetlist;
    }

    public ProvinceDataReamsgetlistBean getProvince() {
        return provinceDataReamsgetlist;
    }
    public void setTag_list(List< Tag_listDataReamsgetlistBean > tag_listDataReamsgetlist) {
        this.tag_listDataReamsgetlist = tag_listDataReamsgetlist;
    }

    public List< Tag_listDataReamsgetlistBean > getTag_list() {
        return tag_listDataReamsgetlist;
    }
    public void setDistrict(DistrictDataReamsgetlistBean districtDataReamsgetlist) {
        this.districtDataReamsgetlist = districtDataReamsgetlist;
    }

    public DistrictDataReamsgetlistBean getDistrict() {
        return districtDataReamsgetlist;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
