package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;

public class LocationsGetListBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * more
     **/
    private Integer more;
    /**
     * status
     **/
    private Integer status;

      private DataLocationsGetListBean dataLocationsGetList;
      private List< Hot_city_dataLocationsgetlistBean > hot_city_dataLocationsGetList;

  
	
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

	


    public void setData(DataLocationsGetListBean dataLocationsGetList) {
        this.dataLocationsGetList = dataLocationsGetList;
    }

    public DataLocationsGetListBean getData() {
        return dataLocationsGetList;
    }
    public void setHot_city_data(List< Hot_city_dataLocationsgetlistBean > hot_city_dataLocationsGetList) {
        this.hot_city_dataLocationsGetList = hot_city_dataLocationsGetList;
    }
    			//Hot_city_dataLocationsGetListBean
    public List<  Hot_city_dataLocationsgetlistBean > getHot_city_data() {
        return hot_city_dataLocationsGetList;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
