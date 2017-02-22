package com.ndlan.canyin.base.repository.dao3c.productinfo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.base.repository.BaseDao;

public abstract interface ProductDao extends BaseDao<Product, String> {

	 public abstract List<Product> findByRestId(String restId);
	 
	 @Query("select c from Product c where c.proId=?1")
	 
	 public Product findByProId(String proId);

}
