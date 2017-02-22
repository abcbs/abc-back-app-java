 package com.ndlan.canyin.core.utils;
 
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.ndlan.canyin.core.utils.Reflections;
 
 public class Collections3
 {
   public static Map extractToMap(Collection collection, String keyPropertyName, String valuePropertyName)
   {
     Map map = new HashMap(collection.size());
     try
     {
       for (Iterator localIterator = collection.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
         map.put(PropertyUtils.getProperty(obj, keyPropertyName), 
           PropertyUtils.getProperty(obj, valuePropertyName)); }
     }
     catch (Exception e) {
       throw Reflections.convertReflectionExceptionToUnchecked(e);
     }
 
     return map;
   }
 
   public static List extractToList(Collection collection, String propertyName)
   {
     List list = new ArrayList(collection.size());
     try
     {
       for (Iterator localIterator = collection.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
         if (obj != null)
           list.add(PropertyUtils.getProperty(obj, propertyName));
       }
     }
     catch (Exception e)
     {
       throw Reflections.convertReflectionExceptionToUnchecked(e);
     }
 
     return list;
   }
 
   public static String extractToString(Collection collection, String propertyName, String separator)
   {
     List list = extractToList(collection, propertyName);
     return StringUtils.join(list, separator);
   }
 
   public static String convertToString(Collection collection, String separator)
   {
     return StringUtils.join(collection, separator);
   }
 
   public static String convertToString(Collection collection, String prefix, String postfix)
   {
     StringBuilder builder = new StringBuilder();
     for (Iterator localIterator = collection.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
       builder.append(prefix).append(o).append(postfix);
     }
     return builder.toString();
   }
 
   public static boolean isEmpty(Collection collection)
   {
     return (collection == null) || (collection.isEmpty());
   }
 
   public static boolean isNotEmpty(Collection collection)
   {
     return (collection != null) && (!collection.isEmpty());
   }
 
   public static <T> T getFirst(Collection<T> collection)
   {
     if (isEmpty(collection)) {
       return null;
     }
 
     return collection.iterator().next();
   }
 
   public static <T> T getLast(Collection<T> collection)
   {
     if (isEmpty(collection)) {
       return null;
     }
 
     if ((collection instanceof List)) {
       List list = (List)collection;
       return (T)list.get(list.size() - 1);
     }
 Iterator iterator = collection.iterator();
     Object current;
     do current = iterator.next();
     while (iterator.hasNext());
     return (T)current;
   }
 
   public static <T> List<T> union(Collection<T> a, Collection<T> b)
   {
     List result = new ArrayList(a);
     result.addAll(b);
     return result;
   }
 
   public static <T> List<T> subtract(Collection<T> a, Collection<T> b)
   {
     List list = new ArrayList(a);
     for (Object element : b) {
       list.remove(element);
     }
 
     return list;
   }
 
   public static <T> List<T> intersection(Collection<T> a, Collection<T> b)
   {
     List list = new ArrayList();
 
     for (Object element : a) {
       if (b.contains(element)) {
         list.add(element);
       }
     }
     return list;
   }
 }

