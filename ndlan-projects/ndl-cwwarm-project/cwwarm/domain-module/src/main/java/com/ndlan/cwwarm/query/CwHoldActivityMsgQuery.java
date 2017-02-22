package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwHoldActivityMsgBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwHoldActivityMsgQuery extends CwHoldActivityMsgBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 发布者组织名称
	 **/
     	 private String hamOrgNameLike;
	 /**
	 * 发布者名称
	 **/
     	 private String hamByNameLike;

         public void setHamOrgNameLike(String hamOrgNameLike) {
	    this.hamOrgNameLike = hamOrgNameLike;
	 }

	 public String getHamOrgNameLike() {
            return hamOrgNameLike;
         }
         public void setHamByNameLike(String hamByNameLike) {
	    this.hamByNameLike = hamByNameLike;
	 }

	 public String getHamByNameLike() {
            return hamByNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
