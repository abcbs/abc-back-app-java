package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwDonationBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwDonationQuery extends CwDonationBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 捐赠人机构名称
	 **/
     	 private String dnDonaterOrgNameLike;
	 /**
	 * 被捐赠人名称
	 **/
     	 private String dnDonatedNameLike;
	 /**
	 * 捐赠人名称
	 **/
     	 private String dnDonaterNameLike;
	 /**
	 * 被捐赠人机构名称
	 **/
     	 private String dnDonatedOrgNameLike;

         public void setDnDonaterOrgNameLike(String dnDonaterOrgNameLike) {
	    this.dnDonaterOrgNameLike = dnDonaterOrgNameLike;
	 }

	 public String getDnDonaterOrgNameLike() {
            return dnDonaterOrgNameLike;
         }
         public void setDnDonatedNameLike(String dnDonatedNameLike) {
	    this.dnDonatedNameLike = dnDonatedNameLike;
	 }

	 public String getDnDonatedNameLike() {
            return dnDonatedNameLike;
         }
         public void setDnDonaterNameLike(String dnDonaterNameLike) {
	    this.dnDonaterNameLike = dnDonaterNameLike;
	 }

	 public String getDnDonaterNameLike() {
            return dnDonaterNameLike;
         }
         public void setDnDonatedOrgNameLike(String dnDonatedOrgNameLike) {
	    this.dnDonatedOrgNameLike = dnDonatedOrgNameLike;
	 }

	 public String getDnDonatedOrgNameLike() {
            return dnDonatedOrgNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
