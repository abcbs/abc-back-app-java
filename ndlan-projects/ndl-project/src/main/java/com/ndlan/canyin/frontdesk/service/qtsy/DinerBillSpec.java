 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.data.jpa.domain.Specification;
 
 public class DinerBillSpec
 {
   public static Specification<DinerBill> searchPage(final String billStatus,final  String restId_value)
   {
     return new Specification<DinerBill>()
     {
       public Predicate toPredicate(Root<DinerBill> root, CriteriaQuery<?> query, CriteriaBuilder cb)
       {
         Predicate p = cb.conjunction();
         if (StringUtils.isNotEmpty(billStatus)) {
           Path status = root.get("billStatus");
 
           if ("1".equals(billStatus)) {
             p = cb.and(new Predicate[] { cb.equal(status, "1") });
           }
 
           if ("2".equals(billStatus)) {
             p = cb.or(new Predicate[] { cb.equal(status, "2"), cb.equal(status, "4"), cb.equal(status, "9") });
           }
 
           if ("3".equals(billStatus)) {
             p = cb.or(new Predicate[] { cb.equal(status, "3"), cb.equal(status, "8"), cb.equal(status, "10") });
           }
         }
         if (StringUtils.isNotEmpty(restId_value)) {
           Path restaurant = root.get("restaurant");
           Path restId = restaurant.get("restId");
           p = cb.and(cb.equal(restId, restId_value), p);
         }
 
         return p;
       } } ;
   }
 
   public static Specification<DinerBill> searchPageBykeyword(final String keyword,final  String restId_value) {
     return new Specification<DinerBill>()
     {
       public Predicate toPredicate(Root<DinerBill> root, CriteriaQuery<?> query, CriteriaBuilder cb)
       {
         Predicate p = cb.conjunction();
         if (StringUtils.isNotEmpty(keyword))
         {
           Path billNo = root.get("billNo");
           p = cb.or(new Predicate[] { cb.equal(billNo, keyword) });
 
           Path tabNo = root.get("tabNo");
           p = cb.or(cb.equal(tabNo, keyword), p);
 
           Path employee = root.get("updateEmployee");
           Path empNum = employee.get("empNum");
           p = cb.or(cb.equal(empNum, keyword), p);
         }
         if (StringUtils.isNotEmpty(restId_value)) {
           Path restaurant = root.get("restaurant");
           Path restId = restaurant.get("restId");
           p = cb.and(cb.equal(restId, restId_value), p);
         }
         return p;
       }
     };
   }
 }

