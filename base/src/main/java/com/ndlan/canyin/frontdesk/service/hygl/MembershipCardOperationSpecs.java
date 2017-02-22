 package com.ndlan.canyin.frontdesk.service.hygl;
 
 import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Join;
 import javax.persistence.criteria.JoinType;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.data.jpa.domain.Specification;
 
 public class MembershipCardOperationSpecs
 {
   public static Specification<MembershipCardOperation> searchPage(final String keyWord,final  String cardOperationType_value,final  String membershipCardId_value,final  String restMemberInfoId_value,final  String updateTime_le_value,final  String updateTime_ge_value,final  String restId)
   {
     return new Specification<MembershipCardOperation>()
     {
       public Predicate toPredicate(Root<MembershipCardOperation> root, CriteriaQuery<?> query, CriteriaBuilder cb)
       {
         Predicate p = cb.conjunction();
 
         if (StringUtils.isNotEmpty(keyWord))
         {
           Join membershipCard = root.join("membershipCard", JoinType.LEFT);
           Path cardNo = membershipCard.get("cardNo");
 
           Join restMemberInfo = membershipCard.join("restMemberInfo", JoinType.LEFT);
           Path tel = restMemberInfo.get("mobile");
 
           Path nameCode = restMemberInfo.get("nameCode");
 
           Path name = restMemberInfo.get("name");
           p = cb.or(new Predicate[] { cb.like(tel, "%" + keyWord + "%"), cb.like(nameCode, "%" + keyWord + "%"), cb.like(cardNo, "%" + keyWord + "%"), cb.like(name, "%" + keyWord + "%") });
         }
 
         if (StringUtils.isNotEmpty(cardOperationType_value)) {
           Path cardOperationType = root.get("cardOperationType");
           p = cb.and(cb.equal(cardOperationType, cardOperationType_value), p);
         }
 
         if (StringUtils.isNotEmpty(membershipCardId_value)) {
           Join membershipCard = root.join("membershipCard", JoinType.LEFT);
           Path membershipCardId = membershipCard.get("mcId");
           p = cb.and(cb.equal(membershipCardId, membershipCardId_value), p);
         }
 
         if (StringUtils.isNotEmpty(restMemberInfoId_value)) {
           Join membershipCard = root.join("membershipCard", JoinType.LEFT);
           Join restMemberInfo = membershipCard.join("restMemberInfo", JoinType.LEFT);
           Path restMemberInfoId = restMemberInfo.get("mbId");
           p = cb.and(cb.equal(restMemberInfoId, restMemberInfoId_value), p);
         }
 
         Path rest_Id = root.get("restId");
         p = cb.and(cb.equal(rest_Id, restId), p);
 
         if ((StringUtils.isNotEmpty(updateTime_le_value)) || (StringUtils.isNotEmpty(updateTime_ge_value))) {
           Path createTime = root.get("createTime");
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           Date date1 = new Date();
           Date date2 = new Date();
           if (StringUtils.isNotEmpty(updateTime_le_value)) {
             try {
               date1 = format.parse(updateTime_le_value);
             }
             catch (ParseException e) {
               e.printStackTrace();
             }
             p = cb.and(cb.lessThanOrEqualTo(createTime, date1), p);
           }
           if (StringUtils.isNotEmpty(updateTime_ge_value)) {
             try {
               date2 = format.parse(updateTime_ge_value);
             }
             catch (ParseException e) {
               e.printStackTrace();
             }
             p = cb.and(cb.greaterThanOrEqualTo(createTime, date2), p);
           }
         }
         return p;
       }
     };
   }
 }

