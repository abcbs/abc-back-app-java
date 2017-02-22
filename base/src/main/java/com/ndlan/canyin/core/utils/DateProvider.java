 package com.ndlan.canyin.core.utils;
 
 import java.util.Date;

//import com.ndlan.canyin.core.utils.CurrentDateProvider;
import com.ndlan.canyin.core.utils.DateProvider;
 
 public abstract interface DateProvider
 {
   public static final DateProvider DEFAULT = new CurrentDateProvider();
 
   public abstract Date getDate();
 
   public static class ConfigurableDateProvider
     implements DateProvider
   {
     private final Date date;
 
     public ConfigurableDateProvider(Date date)
     {
       this.date = date;
     }
 
     public Date getDate()
     {
       return this.date;
     }
   }
 
   public static class CurrentDateProvider
     implements DateProvider
   {
     public Date getDate()
     {
       return new Date();
     }
   }
 }

