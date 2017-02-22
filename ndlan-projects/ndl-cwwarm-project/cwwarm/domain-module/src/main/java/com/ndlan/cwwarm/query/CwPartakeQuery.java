package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwPartakeBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwPartakeQuery extends CwPartakeBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 参与者名称
	 **/
     	 private String ptkByNameLike;

         public void setPtkByNameLike(String ptkByNameLike) {
	    this.ptkByNameLike = ptkByNameLike;
	 }

	 public String getPtkByNameLike() {
            return ptkByNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
