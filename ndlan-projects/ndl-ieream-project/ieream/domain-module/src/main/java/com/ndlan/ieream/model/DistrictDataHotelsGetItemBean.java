package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class DistrictDataHotelsGetItemBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 同安区
     **/
    private String name;
    /**
     * 350212
     **/
    private String id;
    /**
     * has_location
     **/
    private Integer has_location;


  
	
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

	
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

	
    public void setHas_location(Integer has_location) {
        this.has_location = has_location;
    }

    public Integer getHas_location() {
        return has_location;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
