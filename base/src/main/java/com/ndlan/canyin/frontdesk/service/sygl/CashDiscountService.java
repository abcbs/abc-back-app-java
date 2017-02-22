 package com.ndlan.canyin.frontdesk.service.sygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.Role;
 import com.ndlan.canyin.base.entity.sygl.CashDiscount;
 import com.ndlan.canyin.base.repository.sygl.CashDiscountDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.IdentityHashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
 
 @Service
 public class CashDiscountService extends BaseService<CashDiscountDao, CashDiscount>
 {
   private CashDiscountDao cashDiscountDao;
 
   public Page<CashDiscount> search(String restId, List<Role> roleList, boolean isMemeber, Map<String, Object> searchParams, int pageNumber, int pagzSize, String[] sortType)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.ASC, sortType));
     Map filters = SearchFilter.parse(searchParams);
     filters.put("restaurant.restId", new SearchFilter("restaurant.restId", SearchFilter.Operator.EQ, restId));
     filters.put("enableStatus", new SearchFilter("enableStatus", SearchFilter.Operator.EQ, EnableStatusEnum.NORMAL.getCode()));
 
     if (!isMemeber) {
       filters.put("isOnlyMember", new SearchFilter("isOnlyMember", SearchFilter.Operator.EQ, TrueFalseEnum.FALSE.getCode()));
     }
     Map filtersOr = new IdentityHashMap();
     for (Role r : roleList) {
       filtersOr.put(new String("rolesArray"), new SearchFilter("rolesArray", SearchFilter.Operator.LIKE, r.getCrId()));
     }
     Specification specOr = DynamicSpecifications.bySearchFilterWithOr(filtersOr.values(), CashDiscount.class);
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), CashDiscount.class);
     return this.cashDiscountDao.findAll(Specifications.where(specOr).and(spec), pageRequest);
   }
   @Autowired
   public void setDao(CashDiscountDao dao) {
     super.setDao(dao);
     this.cashDiscountDao = dao;
   }
 }

