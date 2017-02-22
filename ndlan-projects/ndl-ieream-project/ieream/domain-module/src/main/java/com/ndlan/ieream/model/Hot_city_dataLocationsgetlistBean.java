package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class Hot_city_dataLocationsgetlistBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 5
     **/
    private String count_item;
    /**
     * id
     **/
    private Integer id;
    /**
     * 默认
     **/
    private String name;


  
	
    public void setCount_item(String count_item) {
        this.count_item = count_item;
    }

    public String getCount_item() {
        return count_item;
    }

	
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

	
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
