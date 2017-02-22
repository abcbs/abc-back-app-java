 package com.ndlan.canyin.frontdesk.service.hygl;
 
 import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.data.jpa.domain.Specification;
 
 public class MembershipCardClassSpecs
 {
   public static Specification<MembershipCardClass> searchPage(final String restId)
   {
     return new Specification<MembershipCardClass>()
     {
       public Predicate toPredicate(Root<MembershipCardClass> root, CriteriaQuery<?> query, CriteriaBuilder cb)
       {
         Predicate p = cb.conjunction();
 
         if (StringUtils.isNotEmpty(restId)) {
           Path rest_Id = root.get("restId");
           p = cb.and(cb.equal(rest_Id, restId), p);
         }
         return p;
       }
     };
   }
 }

