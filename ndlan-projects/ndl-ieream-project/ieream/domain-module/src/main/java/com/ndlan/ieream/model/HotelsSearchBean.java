package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class HotelsSearchBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * status
     **/
    private Integer status;
    private String info;
    /**
     * more
     **/
    private Integer more;

      private List< DataHotelssearchBean > dataHotelsSearch;

  
	
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

	
    public void setMore(Integer more) {
        this.more = more;
    }

    public Integer getMore() {
        return more;
    }

	


    public void setData(List< DataHotelssearchBean > dataHotelsSearch) {
        this.dataHotelsSearch = dataHotelsSearch;
    }

    public List< DataHotelssearchBean > getData() {
        return dataHotelsSearch;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
