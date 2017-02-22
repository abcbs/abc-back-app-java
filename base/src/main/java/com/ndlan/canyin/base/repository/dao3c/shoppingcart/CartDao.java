package com.ndlan.canyin.base.repository.dao3c.shoppingcart;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartEntity;
import com.ndlan.canyin.base.repository.BaseDao;
/**
 * @Description:购物车dao
 * @author: wangwei
 * @date: 2016年1月8日 下午1:38:04
 */
public interface CartDao extends BaseDao<CartEntity, String> {
	@Query("select c from CartEntity c where c.createEmployee.empId=?1 and c.restId=?2 ")
	public CartEntity findByEmplId(String id,String restId);
}
