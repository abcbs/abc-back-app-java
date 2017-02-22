 package com.ndlan.canyin.base.repository.qtsy;
 
 import com.ndlan.canyin.base.entity.qtsy.TableOrder;

 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
 
 public class OrderBillSpecs
 {
   public static Specification<TableOrder> searchPage(final String status,final  String keyWords,final  String rest_id)
   {
     return new Specification<TableOrder>()
     {
       public Predicate toPredicate(Root<TableOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb)
       {
         Predicate p = cb.conjunction();
//         if (StringUtils.isNotEmpty(OrderBillSpecs.this))
        if (StringUtils.isNotEmpty(keyWords))
         {
           Path telphone = root.get("telphone");
 
           Path order_No = root.get("orderNo");
 
           Path order_people = root.get("orderPeople");
//           p = cb.or(new Predicate[] { cb.equal(telphone, OrderBillSpecs.this), cb.equal(order_No, OrderBillSpecs.this), cb.equal(order_people, OrderBillSpecs.this) });
           p = cb.or(new Predicate[] { cb.equal(telphone, keyWords), cb.equal(order_No, keyWords), cb.equal(order_people,keyWords) });
           
         }
         if (StringUtils.isNotEmpty(status))
         {
           Path tableOrder_status = root.get("orderStatus");
           p = cb.and(cb.equal(tableOrder_status, status), p);
         }
         p = cb.and(new Predicate[] { cb.equal(root.get("restId"), rest_id) });
         return p;
       }
     };
   }
 
   public static Specification<TableOrder> getTableOrderInfo(final String status)
   {
     return new Specification<TableOrder>()
     {
       public Predicate toPredicate(Root<TableOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb)
       {
         Predicate p = cb.conjunction();
//         if (StringUtils.isNotEmpty(OrderBillSpecs.this))
        if (StringUtils.isNotEmpty(status))
         {
           Path tableOrder_status = root.get("orderStatus");
//           p = cb.and(cb.equal(tableOrder_status, OrderBillSpecs.this), p);
           p = cb.and(cb.equal(tableOrder_status, status), p);
         }
         return p;
       }
     };
   }
 }

