package com.ndlan.ieream.query;

import com.ndlan.ieream.model.District_listCity_listProvinceDataLocationsGetListBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class District_listCity_listProvinceDataLocationsGetListQuery extends District_listCity_listProvinceDataLocationsGetListBean{

	private static final long serialVersionUID = 4587505733381942426L;


	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
