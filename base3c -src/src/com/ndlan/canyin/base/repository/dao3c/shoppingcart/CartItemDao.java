package com.ndlan.canyin.base.repository.dao3c.shoppingcart;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.base.repository.BaseDao;
/**
 * 
 * @Description:购物车明细dao
 * @author: wangwei
 * @date: 2016年1月8日 下午1:38:18
 */
public interface CartItemDao extends BaseDao<CartItemEntity, String> {
	@Query("select c from CartItemEntity c where c.cartId=?1  and c.status='1' and c.isCodeless='0' ")
	public List<CartItemEntity> findByCartId(String cartId);
	@Query("select c from CartItemEntity c where c.cartId=?1 and c.proId=?2  and c.status='1'")
	public CartItemEntity findByCartId(String cartId,String proId);
	@Query("select c from CartItemEntity c where c.cartId=?  and c.status='1' ")
	public List<CartItemEntity> findAllByCartId(String cartId);
}
