package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;

public class DataLocationsGetListBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	

      private List< ProvinceDataLocationsGetListBean > provinceDataLocationsGetList;

  
	


    public void setProvince(List< ProvinceDataLocationsGetListBean > provinceDataLocationsGetList) {
        this.provinceDataLocationsGetList = provinceDataLocationsGetList;
    }

    public List< ProvinceDataLocationsGetListBean > getProvince() {
        return provinceDataLocationsGetList;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
