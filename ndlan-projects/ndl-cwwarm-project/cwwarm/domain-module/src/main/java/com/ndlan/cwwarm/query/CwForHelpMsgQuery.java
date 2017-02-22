package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwForHelpMsgBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwForHelpMsgQuery extends CwForHelpMsgBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 求助者名称
	 **/
     	 private String forHelpByNameLike;
	 /**
	 * 求助者组织名称【志愿者、个人此信息为空】
	 **/
     	 private String forHelpOrgNameLike;

         public void setForHelpByNameLike(String forHelpByNameLike) {
	    this.forHelpByNameLike = forHelpByNameLike;
	 }

	 public String getForHelpByNameLike() {
            return forHelpByNameLike;
         }
         public void setForHelpOrgNameLike(String forHelpOrgNameLike) {
	    this.forHelpOrgNameLike = forHelpOrgNameLike;
	 }

	 public String getForHelpOrgNameLike() {
            return forHelpOrgNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
