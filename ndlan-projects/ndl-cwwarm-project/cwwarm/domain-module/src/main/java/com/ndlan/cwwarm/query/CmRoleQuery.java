package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CmRoleBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CmRoleQuery extends CmRoleBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 
	 **/
     	 private String nameLike;

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
}
