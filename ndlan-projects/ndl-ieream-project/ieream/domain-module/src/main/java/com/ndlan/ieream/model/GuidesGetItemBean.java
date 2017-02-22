package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class GuidesGetItemBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * more
     **/
    private Integer more;
    /**
     * status
     **/
    private Integer status;
    private String info;

      private DataGuidesGetItemBean dataGuidesGetItem;

  
	
    public void setMore(Integer more) {
        this.more = more;
    }

    public Integer getMore() {
        return more;
    }

	

	
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

	
    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }


    public void setData(DataGuidesGetItemBean dataGuidesGetItem) {
        this.dataGuidesGetItem = dataGuidesGetItem;
    }

    public DataGuidesGetItemBean getData() {
        return dataGuidesGetItem;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
