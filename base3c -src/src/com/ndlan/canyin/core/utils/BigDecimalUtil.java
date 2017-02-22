 package com.ndlan.canyin.core.utils;
 
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.text.DecimalFormat;
 
 public class BigDecimalUtil
 {
   public static BigDecimal format(BigDecimal data)
   {
     if (data == null) {
       data = BigDecimal.ZERO;
     }
     DecimalFormat df = new DecimalFormat("#.##");
     return new BigDecimal(df.format(data));
   }
 
   public static BigDecimal formatRoundDown(BigDecimal data)
   {
     if (data == null) {
       data = BigDecimal.ZERO;
     }
     return data.setScale(0, 1);
   }
 
   public static String formatFloat(Float data)
   {
     if (data.compareTo(new Float(data.intValue())) == 0) {
       return String.valueOf(data.intValue());
     }
     return String.valueOf(data);
   }
 
   public static String formatDouble(Double data)
   {
     if (data.compareTo(new Double(data.intValue())) == 0) {
       return String.valueOf(data.intValue());
     }
     return String.valueOf(data);
   }
 
   public static void main(String[] args)
   {
     BigDecimal data = new BigDecimal(20.91D);
     System.out.println(data.setScale(0, 1));
   }
 }

