package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwUserExtendBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwUserExtendQuery extends CwUserExtendBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 账号
	 **/
     	 private String loginUsernameLike;
	 /**
	 * 姓名
	 **/
     	 private String nameLike;

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
}
