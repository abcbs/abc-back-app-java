package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwOrgSiteBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwOrgSiteQuery extends CwOrgSiteBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 站点名称路径
	 **/
     	 private String ostParentSiteNamePathLike;
	 /**
	 * 站点父名称
	 **/
     	 private String ostParentSiteNameLike;
	 /**
	 * 站点名称
	 **/
     	 private String ostSiteNameLike;

         public void setOstParentSiteNamePathLike(String ostParentSiteNamePathLike) {
	    this.ostParentSiteNamePathLike = ostParentSiteNamePathLike;
	 }

	 public String getOstParentSiteNamePathLike() {
            return ostParentSiteNamePathLike;
         }
         public void setOstParentSiteNameLike(String ostParentSiteNameLike) {
	    this.ostParentSiteNameLike = ostParentSiteNameLike;
	 }

	 public String getOstParentSiteNameLike() {
            return ostParentSiteNameLike;
         }
         public void setOstSiteNameLike(String ostSiteNameLike) {
	    this.ostSiteNameLike = ostSiteNameLike;
	 }

	 public String getOstSiteNameLike() {
            return ostSiteNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
