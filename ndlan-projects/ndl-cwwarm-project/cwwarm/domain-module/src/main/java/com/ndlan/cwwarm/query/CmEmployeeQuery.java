package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CmEmployeeBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//import com.ndlan.framework.core.web.domain.ConditionLike;
import com.ndlan.framework.core.web.domain.PageQuery;

public class CmEmployeeQuery extends CmEmployeeBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 
	 **/
     	 private String loginUsernameLike;
	 /**
	 * 
	 **/
     	 private String nameLike;
     	 
     	private String adrCityOr;
     	
     	private String residenceAdrOr;
     	
     	 private PageQuery pageable ;
     	 
     	 
     	 
         public void setLoginUsernameLike(String loginUsernameLike) {
	    this.loginUsernameLike = loginUsernameLike;
	 }

	 public String getLoginUsernameLike() {
            return loginUsernameLike;
         }
         public void setNameLike(String nameLike) {
	    this.nameLike = nameLike;
	 }

	 public String getNameLike() {
            return nameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public PageQuery getPageable() {
		return pageable;
	}

	public void setPageable(PageQuery pageable) {
		this.pageable = pageable;
	}

	public String getAdrCityOr() {
		return adrCityOr;
	}

	public void setAdrCityOr(String adrCityOr) {
		this.adrCityOr = adrCityOr;
	}

	public String getResidenceAdrOr() {
		return residenceAdrOr;
	}

	public void setResidenceAdrOr(String residenceAdrOr) {
		this.residenceAdrOr = residenceAdrOr;
	}
	
	
}
