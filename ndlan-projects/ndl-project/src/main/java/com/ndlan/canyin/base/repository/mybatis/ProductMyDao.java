package com.ndlan.canyin.base.repository.mybatis;

import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface ProductMyDao
{

  public abstract List<Product> findProductList(@Param("restId") String paramString1, @Param("key") String paramString2,@Param("parentCategoryId") String  paramString3,@Param("categoryId") String paramString4,@Param("start") int paramString5, @Param("pageSize") int paramString6);
	
  public abstract int findProductCount(@Param("restId") String paramString1, @Param("key") String paramString2,@Param("parentCategoryId") String  paramString3,@Param("categoryId") String paramString4);
  
}

