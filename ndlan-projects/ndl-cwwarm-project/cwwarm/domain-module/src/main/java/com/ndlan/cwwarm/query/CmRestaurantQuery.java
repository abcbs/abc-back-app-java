package com.ndlan.cwwarm.query;

import com.ndlan.cwwarm.model.CmRestaurantBean;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CmRestaurantQuery extends CmRestaurantBean{

	private static final long serialVersionUID = 4587505733381942426L;

	 /**
	 * 
	 **/
     	 private String storeNameLike;
	 /**
	 * 
	 **/
     	 private String busiBreakfastNameLike;
	 /**
	 * 法人
	 **/
     	 private String linkmanNameLike;
	 /**
	 * 开户行名称
	 **/
     	 private String bankNameLike;
	 /**
	 * 名称
	 **/
     	 private String restNameLike;
	 /**
	 * 
	 **/
     	 private String busiSupperNameLike;
	 /**
	 * 用逗号隔开多个菜系name
	 **/
     	 private String dishesStyleNameArrayLike;
	 /**
	 * 
	 **/
     	 private String busiLunchNameLike;
	 /**
	 * 开户姓名
	 **/
     	 private String accNameLike;
	 /**
	 * 
	 **/
     	 private String cloudUsernameLike;
	 /**
	 * 营业执照名称
	 **/
     	 private String cNameLike;
	 /**
	 * 
	 **/
     	 private String busiLatesnackNameLike;

         public void setStoreNameLike(String storeNameLike) {
	    this.storeNameLike = storeNameLike;
	 }

	 public String getStoreNameLike() {
            return storeNameLike;
         }
         public void setBusiBreakfastNameLike(String busiBreakfastNameLike) {
	    this.busiBreakfastNameLike = busiBreakfastNameLike;
	 }

	 public String getBusiBreakfastNameLike() {
            return busiBreakfastNameLike;
         }
         public void setLinkmanNameLike(String linkmanNameLike) {
	    this.linkmanNameLike = linkmanNameLike;
	 }

	 public String getLinkmanNameLike() {
            return linkmanNameLike;
         }
         public void setBankNameLike(String bankNameLike) {
	    this.bankNameLike = bankNameLike;
	 }

	 public String getBankNameLike() {
            return bankNameLike;
         }
         public void setRestNameLike(String restNameLike) {
	    this.restNameLike = restNameLike;
	 }

	 public String getRestNameLike() {
            return restNameLike;
         }
         public void setBusiSupperNameLike(String busiSupperNameLike) {
	    this.busiSupperNameLike = busiSupperNameLike;
	 }

	 public String getBusiSupperNameLike() {
            return busiSupperNameLike;
         }
         public void setDishesStyleNameArrayLike(String dishesStyleNameArrayLike) {
	    this.dishesStyleNameArrayLike = dishesStyleNameArrayLike;
	 }

	 public String getDishesStyleNameArrayLike() {
            return dishesStyleNameArrayLike;
         }
         public void setBusiLunchNameLike(String busiLunchNameLike) {
	    this.busiLunchNameLike = busiLunchNameLike;
	 }

	 public String getBusiLunchNameLike() {
            return busiLunchNameLike;
         }
         public void setAccNameLike(String accNameLike) {
	    this.accNameLike = accNameLike;
	 }

	 public String getAccNameLike() {
            return accNameLike;
         }
         public void setCloudUsernameLike(String cloudUsernameLike) {
	    this.cloudUsernameLike = cloudUsernameLike;
	 }

	 public String getCloudUsernameLike() {
            return cloudUsernameLike;
         }
         public void setCNameLike(String cNameLike) {
	    this.cNameLike = cNameLike;
	 }

	 public String getCNameLike() {
            return cNameLike;
         }
         public void setBusiLatesnackNameLike(String busiLatesnackNameLike) {
	    this.busiLatesnackNameLike = busiLatesnackNameLike;
	 }

	 public String getBusiLatesnackNameLike() {
            return busiLatesnackNameLike;
         }
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
