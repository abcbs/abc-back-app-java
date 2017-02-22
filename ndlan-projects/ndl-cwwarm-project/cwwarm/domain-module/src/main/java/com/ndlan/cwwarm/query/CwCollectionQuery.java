package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwCollectionBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwCollectionQuery extends CwCollectionBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 收藏者名称
	 **/
     	 private String cltByNameLike;

         public void setCltByNameLike(String cltByNameLike) {
	    this.cltByNameLike = cltByNameLike;
	 }

	 public String getCltByNameLike() {
            return cltByNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
