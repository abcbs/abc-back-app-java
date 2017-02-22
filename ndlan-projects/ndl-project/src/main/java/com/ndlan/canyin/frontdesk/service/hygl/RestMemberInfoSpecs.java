 package com.ndlan.canyin.frontdesk.service.hygl;
 
 import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import java.util.Date;
 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.JoinType;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import javax.persistence.criteria.SetJoin;
 import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
 
 public class RestMemberInfoSpecs
 {
   public static Specification<RestMemberInfo> searchPage(final String keyWords,final  String rest_id,final  String offset)
   {
     return new Specification<RestMemberInfo>()
     {
       public Predicate toPredicate(Root<RestMemberInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb)
       {
         Predicate p = cb.conjunction();
         if (StringUtils.isNotEmpty(keyWords))
         {
           Path tel = root.get("mobile");
 
           Path nameCode = root.get("name");
 
           SetJoin cmMembershipCards = root.joinSet("membershipCards", JoinType.LEFT);
 
           Path cardNo = cmMembershipCards.get("cardNo");
           p = cb.or(new Predicate[] { cb.like(tel, "%" + keyWords + "%"), cb.like(nameCode, "%" + keyWords + "%"), cb.like(cardNo, "%" + keyWords + "%") });
         }
 
         if (StringUtils.isNotEmpty(rest_id)) {
           Path restId = root.get("restId");
           p = cb.and(cb.equal(restId, rest_id), p);
         }
         if (StringUtils.isNotEmpty(offset)) {
           Integer toDayInt = DateUtil.getOffsetDate(new Date());
           Path birthdayInt = root.get("birthdayInt");
           p = cb.and(cb.between(birthdayInt, Integer.valueOf(toDayInt.intValue() - Integer.valueOf(offset).intValue()), toDayInt), p);
         }
         return p;
       }
     };
   }
 }

