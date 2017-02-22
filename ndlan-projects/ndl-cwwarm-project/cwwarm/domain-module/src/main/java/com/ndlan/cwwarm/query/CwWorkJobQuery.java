package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CwWorkJobBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CwWorkJobQuery extends CwWorkJobBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 品类名称
	 **/
     	 private String wjobCategoryNameLike;
	 /**
	 * 父类名称路径
	 **/
     	 private String wjobParentPathNameLike;
	 /**
	 * 父类名称
	 **/
     	 private String wjobParentCategoryNameLike;

         public void setWjobCategoryNameLike(String wjobCategoryNameLike) {
	    this.wjobCategoryNameLike = wjobCategoryNameLike;
	 }

	 public String getWjobCategoryNameLike() {
            return wjobCategoryNameLike;
         }
         public void setWjobParentPathNameLike(String wjobParentPathNameLike) {
	    this.wjobParentPathNameLike = wjobParentPathNameLike;
	 }

	 public String getWjobParentPathNameLike() {
            return wjobParentPathNameLike;
         }
         public void setWjobParentCategoryNameLike(String wjobParentCategoryNameLike) {
	    this.wjobParentCategoryNameLike = wjobParentCategoryNameLike;
	 }

	 public String getWjobParentCategoryNameLike() {
            return wjobParentCategoryNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
