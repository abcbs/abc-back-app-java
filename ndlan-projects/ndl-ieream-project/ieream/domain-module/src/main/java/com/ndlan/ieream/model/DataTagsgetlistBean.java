package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class DataTagsgetlistBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * 1
     **/
    private String id;
    /**
     * 默认
     **/
    private String title;


  
	
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


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
