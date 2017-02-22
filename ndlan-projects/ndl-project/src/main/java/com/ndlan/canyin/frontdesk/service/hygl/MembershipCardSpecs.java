 package com.ndlan.canyin.frontdesk.service.hygl;
 
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.data.jpa.domain.Specification;
 
 public class MembershipCardSpecs
 {
   public static Specification<MembershipCard> searchPage(final String cardNo_value,final  String isProvide_value,final  String membershipCardClassId_value,final  String cardStatus_value,final  String memberIntegral_le_value,final  String memberIntegral_ge_value,final  String is_equalCardNo,final  String restId)
   {
     return new Specification()
     {
//       public Predicate toPredicate(Root<MembershipCard> root, CriteriaQuery<?> query, CriteriaBuilder cb)
       public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb)
       {
         Predicate p = cb.conjunction();
 
         if (StringUtils.isNotEmpty(cardNo_value)) {
           Path cardNo = root.get("cardNo");
           if (StringUtils.isNotEmpty(is_equalCardNo))
             p = cb.and(cb.equal(cardNo, cardNo_value), p);
           else {
             p = cb.and(cb.like(cardNo, "%" + cardNo_value + "%"), p);
           }
         }
 
         if (StringUtils.isNotEmpty(isProvide_value)) {
           Path isProvide = root.get("restMemberInfo");
           if ("0".equalsIgnoreCase(isProvide_value))
             p = cb.and(cb.isNull(isProvide), p);
           else {
             p = cb.and(cb.isNotNull(isProvide), p);
           }
         }
 
         if (StringUtils.isNotEmpty(membershipCardClassId_value)) {
           Path membershipCardClass = root.get("membershipCardClass");
           Path membershipCardClass_id = membershipCardClass.get("mcclassId");
           p = cb.and(cb.equal(membershipCardClass_id, membershipCardClassId_value), p);
         }
 
         if (StringUtils.isNotEmpty(cardStatus_value)) {
           Path cardStatus = root.get("cardStatus");
           p = cb.and(cb.equal(cardStatus, cardStatus_value), p);
         }
 
         if ((StringUtils.isNotEmpty(memberIntegral_le_value)) || (StringUtils.isNotEmpty(memberIntegral_ge_value))) {
           Path memberIntegral = root.get("memberIntegral");
           if (StringUtils.isNotEmpty(memberIntegral_le_value)) {
             p = cb.and(cb.le(memberIntegral, Float.valueOf(memberIntegral_le_value)), p);
           }
           if (StringUtils.isNotEmpty(memberIntegral_ge_value)) {
             p = cb.and(cb.ge(memberIntegral, Float.valueOf(memberIntegral_ge_value)), p);
           }
         }
 
         if (StringUtils.isNotEmpty(restId)) {
           Path rest_Id = root.get("restId");
           p = cb.and(cb.equal(rest_Id, restId), p);
         }
         return p;
       }
     };
   }
 }

