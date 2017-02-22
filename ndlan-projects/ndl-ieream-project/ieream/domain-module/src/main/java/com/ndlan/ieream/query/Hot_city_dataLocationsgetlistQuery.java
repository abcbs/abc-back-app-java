package com.ndlan.ieream.query;

import com.ndlan.ieream.model.Hot_city_dataLocationsgetlistBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Hot_city_dataLocationsgetlistQuery extends Hot_city_dataLocationsgetlistBean{

	private static final long serialVersionUID = 4587505733381942426L;


	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
