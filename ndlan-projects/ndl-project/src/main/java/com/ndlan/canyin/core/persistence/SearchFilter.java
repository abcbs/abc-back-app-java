 package com.ndlan.canyin.core.persistence;
 
 import com.google.common.collect.Maps;
//import com.ndlan.canyin.core.persistence.Operator;
import com.ndlan.canyin.core.persistence.SearchFilter;

 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
 
 public class SearchFilter
 {
   public String fieldName;
   public Object value;
   public Operator operator;
 
   public SearchFilter(String fieldName, Operator operator, Object value)
   {
     this.fieldName = fieldName;
     this.value = value;
     this.operator = operator;
   }
 
   public static Map<String, SearchFilter> parse(Map<String, Object> searchParams)
   {
     Map filters = Maps.newHashMap();
 
     for (Map.Entry entry : searchParams.entrySet())
     {
       String key = (String)entry.getKey();
       Object value = entry.getValue();
       if (StringUtils.isBlank((String)value)) {
         continue;
       }
       if ((value.toString().endsWith("Time")) || (value.toString().endsWith("time"))) {
         SimpleDateFormat format = new SimpleDateFormat();
         try {
           value = format.parse(value.toString());
         } catch (ParseException e) {
           e.printStackTrace();
         }
       }
 
       String[] names = StringUtils.split(key, "_");
       if (names.length != 2) {
         throw new IllegalArgumentException(key + " is not a valid search filter name");
       }
       String filedName = names[1];
       Operator operator = Operator.valueOf(names[0]);
 
       SearchFilter filter = new SearchFilter(filedName, operator, value);
       filters.put(key, filter);
     }
 
     return filters;
   }
 
   public static Map<String, Object> getOnlyKey(Map<String, Object> searchParams, String[] keys)
   {
     Map newParams = new HashMap();
     for (String e : keys)
     {
       newParams.put(e, searchParams.get(e));
     }
     return newParams;
   }
 
   public static Map<String, Object> getRemoveKey(Map<String, Object> searchParams, String[] keys) {
     for (String e : keys)
     {
       searchParams.remove(e);
     }
     return searchParams;
   }
 
   public static enum Operator
   {
     EQ, NEQ, LIKE, GT, LT, GTE, LTE, ISNOTNULL, ISNULL;
   }
 }

