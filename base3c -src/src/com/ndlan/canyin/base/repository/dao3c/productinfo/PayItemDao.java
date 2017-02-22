/**
 * 
 */
package com.ndlan.canyin.base.repository.dao3c.productinfo;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.productinfo.PayItem;
import com.ndlan.canyin.base.repository.BaseDao;

/**
 * @Description:
 * @author zhangts
 * @date: 2016-1-11 上午10:33:58
 */
public abstract interface PayItemDao extends BaseDao<PayItem, String>{

	@Query("select p from PayItem p where p.bId=? and p.payStatus=? and p.restId=?")
	PayItem getOneByBIdAndPayStatus(String bId, String payStatus, String restId);

}
