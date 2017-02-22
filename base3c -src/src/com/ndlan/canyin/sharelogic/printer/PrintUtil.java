 package com.ndlan.canyin.sharelogic.printer;
 
 import java.math.BigDecimal;
 
 public class PrintUtil
 {
   public static String floatToString(Float f)
   {
     if (f == null) return "";
     Integer i = Integer.valueOf(f.intValue());
     Float f2 = Float.valueOf(i.floatValue());
     if (f.compareTo(f2) == 0) {
       return new BigDecimal(f.floatValue()).toString();
     }
     return f.toString();
   }
 }

