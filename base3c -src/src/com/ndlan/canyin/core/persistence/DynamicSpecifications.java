 package com.ndlan.canyin.core.persistence;
 
 import com.google.common.collect.Lists;
import com.ndlan.canyin.core.utils.Collections3;
import com.ndlan.canyin.core.persistence.SearchFilter;
 import java.util.Collection;
 import java.util.List;
 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
 
 public class DynamicSpecifications
 {
   public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, Class<T> clazz)
   {
     return new Specification<T>()
     {
       public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
         if (Collections3.isNotEmpty(filters))
         {
           List predicates = Lists.newArrayList();
           for (SearchFilter filter : filters)
           {
             String[] names = StringUtils.split(filter.fieldName, ".");
 
             Path expression = root.get(names[0]);
             for (int i = 1; i < names.length; i++) {
               expression = expression.get(names[i]);
             }
 
             switch (filter.operator.ordinal()) {
             case 0:
               predicates.add(builder.equal(expression, filter.value));
               break;
             case 1:
               predicates.add(builder.notEqual(expression, filter.value));
               break;
             case 2:
               predicates.add(builder.like(expression, "%" + filter.value + "%"));
               break;
             case 3:
               predicates.add(builder.greaterThan(expression, (Comparable)filter.value));
               break;
             case 4:
               predicates.add(builder.lessThan(expression, (Comparable)filter.value));
               break;
             case 5:
               predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable)filter.value));
               break;
             case 6:
               predicates.add(builder.lessThanOrEqualTo(expression, (Comparable)filter.value));
               break;
             case 7:
               predicates.add(builder.isNotNull(expression));
               break;
             case 8:
               predicates.add(builder.isNull(expression));
             }
 
           }
 
           if (predicates.size() > 0) {
             return builder.and((Predicate[])predicates.toArray(new Predicate[predicates.size()]));
           }
         }
 
         return builder.conjunction();
       } } ;
   }
 
   public static <T> Specification<T> bySearchFilterWithOr(final Collection<SearchFilter> filters, Class<T> clazz) {
     return new Specification<T>()
     {
       public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
         if (Collections3.isNotEmpty(filters))
         {
           List predicates = Lists.newArrayList();
           for (SearchFilter filter : filters)
           {
             String[] names = StringUtils.split(filter.fieldName, ".");
             Path expression = root.get(names[0]);
             for (int i = 1; i < names.length; i++) {
               expression = expression.get(names[i]);
             }
 
             switch (filter.operator.ordinal()) {
             case 0:
               predicates.add(builder.equal(expression, filter.value));
               break;
             case 2:
               predicates.add(builder.like(expression, "%" + filter.value + "%"));
               break;
             case 3:
               predicates.add(builder.greaterThan(expression, (Comparable)filter.value));
               break;
             case 4:
               predicates.add(builder.lessThan(expression, (Comparable)filter.value));
               break;
             case 5:
               predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable)filter.value));
               break;
             case 6:
               predicates.add(builder.lessThanOrEqualTo(expression, (Comparable)filter.value));
             case 1:
             }
 
           }
 
           if (predicates.size() > 0) {
             return builder.or((Predicate[])predicates.toArray(new Predicate[predicates.size()]));
           }
         }
 
         return builder.conjunction();
       }
     };
   }
 }

