 package com.ndlan.canyin.frontdesk.util;
 
 import javax.servlet.ServletContext;
 
 public class TimerManagerFactory
 {
   static TimerManager timer = null;
 
   public static void createTimerManage(int _hour_of_day, int _minute, ServletContext sc)
   {
     if (timer != null)
     {
       timer.shutdown();
       timer = null;
     }
     timer = new TimerManager(_hour_of_day, _minute, sc);
   }
 }

