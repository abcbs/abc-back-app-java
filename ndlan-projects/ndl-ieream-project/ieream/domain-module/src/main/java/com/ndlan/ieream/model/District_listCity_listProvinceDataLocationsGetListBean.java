package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class District_listCity_listProvinceDataLocationsGetListBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 110116
     **/
    private String id;
    /**
     * 怀柔区
     **/
    private String name;
    /**
     * 2
     **/
    private String count_item;
    /**
     * has_child
     **/
    private Integer has_child;


  
	
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

	
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

	
    public void setHas_child(Integer has_child) {
        this.has_child = has_child;
    }

    public Integer getHas_child() {
        return has_child;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
