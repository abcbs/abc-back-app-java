package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class ProvinceDataLocationsGetListBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 北京市
     **/
    private String name;
    /**
     * 3
     **/
    private String count_item;
    /**
     * 110000
     **/
    private String id;
    /**
     * has_child
     **/
    private Integer has_child;

      private List< City_listProvinceDataLocationsGetListBean > city_listProvinceDataLocationsGetList;

  
	
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

	

	
    public void setCount_item(String count_item) {
        this.count_item = count_item;
    }

    public String getCount_item() {
        return count_item;
    }

	
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

	
    public void setHas_child(Integer has_child) {
        this.has_child = has_child;
    }

    public Integer getHas_child() {
        return has_child;
    }


    public void setCity_list(List< City_listProvinceDataLocationsGetListBean > city_listProvinceDataLocationsGetList) {
        this.city_listProvinceDataLocationsGetList = city_listProvinceDataLocationsGetList;
    }

    public List< City_listProvinceDataLocationsGetListBean > getCity_list() {
        return city_listProvinceDataLocationsGetList;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
