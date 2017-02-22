 package com.ndlan.canyin.frontdesk.service.sygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.sygl.CashDishesTypeDiscount;
 import com.ndlan.canyin.base.repository.sygl.CashDishesTypeDiscountDao;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.HashMap;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
 
 @Service
 public class CashDishesTypeDiscountService extends BaseService<CashDishesTypeDiscountDao, CashDishesTypeDiscount>
 {
   private CashDishesTypeDiscountDao cashDishesTypeDiscountDao;
 
   public CashDishesTypeDiscount findOneByCcdIdAndCategoryId(String restId, String ccdId, String categoryId)
   {
     Map filters = new HashMap();
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     filters.put("cashDiscount.ccdId", new SearchFilter("cashDiscount.ccdId", SearchFilter.Operator.EQ, ccdId));
     filters.put("categoryId", new SearchFilter("categoryId", SearchFilter.Operator.EQ, categoryId));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), CashDishesTypeDiscount.class);
     return (CashDishesTypeDiscount)this.cashDishesTypeDiscountDao.findOne(spec);
   }
 
   public CashDishesTypeDiscount findOneByCcdIdAndDsCategoryId(String restId, String ccdId, String dsCategoryId)
   {
     Map filters = new HashMap();
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     filters.put("cashDiscount.ccdId", new SearchFilter("cashDiscount.ccdId", SearchFilter.Operator.EQ, ccdId));
     filters.put("dsCategoryId", new SearchFilter("dsCategoryId", SearchFilter.Operator.EQ, dsCategoryId));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), CashDishesTypeDiscount.class);
     return (CashDishesTypeDiscount)this.cashDishesTypeDiscountDao.findOne(spec);
   }
   @Autowired
   public void setDao(CashDishesTypeDiscountDao dao) {
     super.setDao(dao);
     this.cashDishesTypeDiscountDao = dao;
   }
 }

