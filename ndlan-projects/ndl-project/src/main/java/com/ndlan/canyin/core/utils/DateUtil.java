 package com.ndlan.canyin.core.utils;
 
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
import java.util.Locale;

import com.ndlan.canyin.core.utils.DateProvider;
 
 public class DateUtil
 {
   public static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
 
   public static boolean isToday(Date day)
   {
     Date now = DateProvider.DEFAULT.getDate();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
     String nowStr = sdf.format(now);
     String dayStr = sdf.format(day);
 
     return nowStr.equals(dayStr);
   }
 
   public static boolean isAfterNow(Date day)
   {
     Date now = DateProvider.DEFAULT.getDate();
 
     return day.after(now);
   }
 
   public static Integer getOffsetDate(Date date)
   {
     int dateSum = 0;
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     String dateStr = format.format(date);
     int year = Integer.valueOf(dateStr.substring(0, 4)).intValue();
     int month = Integer.valueOf(dateStr.substring(5, 7)).intValue();
     int day = Integer.valueOf(dateStr.substring(8, 10)).intValue();
     for (int i = 1; i < month; i++) {
       switch (i) { case 1:
       case 3:
       case 5:
       case 7:
       case 8:
       case 10:
       case 12:
         dateSum += 31; break;
       case 4:
       case 6:
       case 9:
       case 11:
         dateSum += 30; break;
       case 2:
         if (((year % 4 == 0 ? 1 : 0) & (year % 100 != 0 ? 1 : 0) | (year % 400 == 0 ? 1 : 0)) != 0)
           dateSum += 29;
         else dateSum += 28;
       }
     }
     dateSum += day;
     return Integer.valueOf(dateSum);
   }
 
   public static String getTime4XDaysAgo(int x, String time)
   {
     String preDate = null;
     try {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Calendar c = Calendar.getInstance();
       c.setTime(sdf.parse(time));
       c.add(5, -x);
       Date date = c.getTime();
       preDate = sdf.format(date);
     } catch (ParseException e) {
       e.printStackTrace();
     }
     return preDate;
   }
 
   public static Date getTime4XDaysAfter(int x, Date time) {
     Calendar c = Calendar.getInstance();
     c.setTime(time);
     c.add(5, x);
     Date date = c.getTime();
     return date;
   }
 
   public static Long getMinutes(Date start, Date end)
   {
     return Long.valueOf((end.getTime() - start.getTime()) / 60000L);
   }
 
   public static Long getDays(Date start, Date end) {
     if ((start == null) || (end == null))
     {
       return Long.valueOf(0L);
     }
     Long mins = getMinutes(start, end);
     return Long.valueOf(mins.longValue() / 1440L);
   }
 
   public static int getWeekDay(Date date)
   {
     Calendar c = Calendar.getInstance();
     c.setTime(date);
     return c.get(7) - 1;
   }
 
   public static String toString(Date date) {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     return sdf.format(date);
   }
 
   public static String toDayString(Date date) {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     return sdf.format(date);
   }
 
   public static String toStringHH(Date dateStr)
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String date = null;
     try {
       date = sdf.format(dateStr);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static Date toDate(String dateStr)
   {
     if ((dateStr == null) || (dateStr.isEmpty()))
     {
       return null;
     }
     dateStr = dateStr.trim();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date date = null;
     try {
       date = sdf.parse(dateStr);
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static Date toSimpleDate(String dateStr) {
     if ((dateStr == null) || (dateStr.isEmpty()))
     {
       return null;
     }
     dateStr = dateStr.trim();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date date = null;
     try {
       date = sdf.parse(dateStr + " 00:00:00");
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static Date toDateTime(String dateStr) {
     if ((dateStr == null) || (dateStr.isEmpty()))
     {
       return null;
     }
     dateStr = dateStr.trim();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date date = null;
     try {
       date = sdf.parse("2000-11-11 " + dateStr);
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static Date toDateTime(Long millis)
   {
     if (millis.longValue() <= 0L) {
       return null;
     }
     Calendar cal = Calendar.getInstance();
     cal.setTimeInMillis(millis.longValue());
     return cal.getTime();
   }
 }

