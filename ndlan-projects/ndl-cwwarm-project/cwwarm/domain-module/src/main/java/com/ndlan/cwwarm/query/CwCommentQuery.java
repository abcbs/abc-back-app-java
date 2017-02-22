package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwCommentBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwCommentQuery extends CwCommentBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 评论人名称
	 **/
     	 private String cmtByNameLike;

         public void setCmtByNameLike(String cmtByNameLike) {
	    this.cmtByNameLike = cmtByNameLike;
	 }

	 public String getCmtByNameLike() {
            return cmtByNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
