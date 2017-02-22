package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwAlertBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwAlertQuery extends CwAlertBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 消息发送者名称
	 **/
     	 private String alertByNameLike;
	 /**
	 * 通知人名称
	 **/
     	 private String alertRcvNameLike;

         public void setAlertByNameLike(String alertByNameLike) {
	    this.alertByNameLike = alertByNameLike;
	 }

	 public String getAlertByNameLike() {
            return alertByNameLike;
         }
         public void setAlertRcvNameLike(String alertRcvNameLike) {
	    this.alertRcvNameLike = alertRcvNameLike;
	 }

	 public String getAlertRcvNameLike() {
            return alertRcvNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
