 package com.ndlan.canyin.frontdesk.util;

import java.io.PrintStream;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import org.apache.commons.lang3.StringUtils;
 
 public class DateUtilq
 {
   public static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
 
//   private static final String[] ch = { "�?, "一", "�?, "�?, "�?, "�?, "�? };
   private static final String[] ch = { "日", "一", "二", "三", "四", "五", "六" };
 
   public static Integer getOffsetDate(Date date)
   {
     int dateSum = 0;
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     String dateStr = format.format(date);
     int year = Integer.valueOf(dateStr.substring(0, 4)).intValue();
     int month = Integer.valueOf(dateStr.substring(5, 7)).intValue();
     int day = Integer.valueOf(dateStr.substring(8, 10)).intValue();
     for (int i = 1; i < month; i++) {
       switch (i) {
       case 1:
       case 3:
       case 5:
       case 7:
       case 8:
       case 10:
       case 12:
         dateSum += 31;
         break;
       case 4:
       case 6:
       case 9:
       case 11:
         dateSum += 30;
         break;
       case 2:
         if (((year % 4 == 0 ? 1 : 0) & (year % 100 != 0 ? 1 : 0) | (year % 400 == 0 ? 1 : 0)) != 0)
           dateSum += 29;
         else
           dateSum += 28;
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
 
   public static String getTime4XDaysAgoAll(int x, String time) {
     String preDate = null;
     try {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
   public static String getTime4XDaysAgoMMdd(int x, String time) {
     String preDate = null;
     try {
       SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
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
 
   public static String getTime4XDaysAfter(int x, String time) {
     String preDate = null;
     try {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Calendar c = Calendar.getInstance();
       c.setTime(sdf.parse(time));
       c.add(5, x);
       Date date = c.getTime();
       preDate = sdf.format(date);
     } catch (ParseException e) {
       e.printStackTrace();
     }
     return preDate;
   }
 
   public static int daysBetween(String smdate, String bdate)
     throws ParseException
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Calendar cal = Calendar.getInstance();
     cal.setTime(sdf.parse(smdate));
     long time1 = cal.getTimeInMillis();
     cal.setTime(sdf.parse(bdate));
     long time2 = cal.getTimeInMillis();
     long between_days = (time2 - time1) / 86400000L;
     return Integer.parseInt(String.valueOf(between_days));
   }
 
   public static Date toAllDate(String dateStr)
   {
     if ((dateStr == null) || (dateStr.isEmpty()))
     {
       return null;
     }
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
 
   public static Date toDate(String dateStr) {
     if ((dateStr == null) || (dateStr.isEmpty()))
     {
       return null;
     }
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Date date = null;
     try {
       date = sdf.parse(dateStr);
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static Date toDateMM(String dateStr) {
     if ((dateStr == null) || (dateStr.isEmpty()))
     {
       return null;
     }
     SimpleDateFormat sdf = new SimpleDateFormat("MM");
     Date date = null;
     try {
       date = sdf.parse(dateStr);
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static String toString(Date dateStr)
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     String date = null;
     try {
       date = sdf.format(dateStr);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static String toStringHH(Date dateStr) {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String date = null;
     try {
       date = sdf.format(dateStr);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static String toStringHHmmss(Date dateStr)
   {
     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
     String date = null;
     try {
       date = sdf.format(dateStr);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static String toStringMMdd(Date dateStr)
   {
     SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
     String date = null;
     try {
       date = sdf.format(dateStr);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static String toYearMonthString(String dateStr) {
     return toYearMonthString(toDate(dateStr));
   }
 
   public static String toYearMonthString(Date dateStr)
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
     String date = null;
     try {
       date = sdf.format(dateStr);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return date;
   }
 
   public static int weekOfYear(String date)
   {
     Calendar calendar = Calendar.getInstance();
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     try {
       calendar.setTime(df.parse(date));
     } catch (ParseException e) {
       e.printStackTrace();
     }
     return calendar.get(3);
   }
 
   public static int weekOfYearFromMonday(String date) {
     Calendar calendar = Calendar.getInstance();
     calendar.setFirstDayOfWeek(2);
     calendar.set(7, 2);
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     try {
       calendar.setTime(df.parse(date));
     } catch (ParseException e) {
       e.printStackTrace();
     }
     return calendar.get(3);
   }
 
   public static int dayOfWeek(String date)
   {
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     Calendar cal = Calendar.getInstance();
     try {
       cal.setTime(df.parse(date));
     } catch (ParseException e) {
       e.printStackTrace();
     }
     int w = cal.get(7);
     return w;
   }
   public static int dayOfWeekFromMonday(String date) {
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     Calendar cal = Calendar.getInstance();
     cal.setFirstDayOfWeek(2);
     cal.set(7, 2);
     try {
       cal.setTime(df.parse(date));
     } catch (ParseException e) {
       e.printStackTrace();
     }
     int w = cal.get(7) - 1;
     if (w == 0) {
       w = 7;
     }
     return w;
   }
 
   public static String dayOfWeekDesc(String date)
   {
     return new StringBuilder().append(" xx").append(ch[(dayOfWeek(date) - 1)]).toString();
   }
 
   public static String getLastSaturday(String endTime)
   {
     int i = dayOfWeek(endTime);
     if (i == 7) {
       return endTime;
     }
     return getTime4XDaysAgo(i, endTime);
   }
 
   public static String getLastSunday(String endTime) {
     int i = dayOfWeekFromMonday(endTime);
     if (i == 7) {
       return endTime;
     }
     return getTime4XDaysAgo(i, endTime);
   }
 
   public static String[] getStartAndEdnDate(String startDate, String endDate)
   {
     String[] dateArr = new String[2];
     if (StringUtils.isEmpty(endDate)) {
       endDate = toString(new Date());
     }
 
     if (StringUtils.isEmpty(startDate)) {
       startDate = getTime4XDaysAgo(30, endDate);
     }
     else
     {
       try
       {
         if (daysBetween(startDate, endDate) > 30)
         {
           startDate = getTime4XDaysAgo(30, endDate);
         }
       } catch (ParseException e) {
         startDate = getTime4XDaysAgo(30, endDate);
         e.printStackTrace();
       }
     }
     dateArr[0] = startDate;
     dateArr[1] = endDate;
     return dateArr;
   }
 
   public static Date add2month(Date date)
   {
     Calendar calendar = Calendar.getInstance();
     DateFormat df = new SimpleDateFormat("MM");
     try
     {
       calendar.setTime(date);
       calendar.add(2, 3);
       Date dt1 = calendar.getTime();
       return dt1;
     }
     catch (Exception e) {
       e.printStackTrace();
     }return null;
   }
 
   public static String getMonthLastDay(String date)
     throws ParseException
   {
     Calendar calendar = Calendar.getInstance();
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     calendar.setTime(df.parse(date));
     calendar.set(5, calendar.getActualMaximum(5));
     Date strDateTo = calendar.getTime();
     date = df.format(strDateTo);
     return date;
   }
 
   public static List<String> getDays(String s_start, String s_end)
   {
     List result = new ArrayList();
     Calendar c_start = Calendar.getInstance();
     Calendar c_end = Calendar.getInstance();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     try {
       c_start.setTime(sdf.parse(s_start));
       c_end.setTime(sdf.parse(s_end));
     } catch (Exception e) {
       e.printStackTrace();
     }
     Calendar temp = (Calendar)c_start.clone();
     while ((temp.before(c_end)) || (temp.equals(c_end))) {
       result.add(sdf.format(temp.getTime()));
       temp.add(6, 1);
     }
     return result;
   }
 
   public static String[] getBussiDay(String day, String busiHoursClose)
   {
     String[] dateArr = new String[2];
     String dayStart = new StringBuilder().append(day).append(" 00:00:00").toString();
     String dayEnd = new StringBuilder().append(day).append(" 23:59:59").toString();
     String busiHoursClose_hour = busiHoursClose.substring(0, busiHoursClose.indexOf(":"));
     String busiHoursClose_min = busiHoursClose.substring(busiHoursClose.indexOf(":") + 1, busiHoursClose.length());
     int busiHoursClose_hour_int = Integer.valueOf(busiHoursClose_hour).intValue();
     int busiHoursClose_min_int = Integer.valueOf(busiHoursClose_min).intValue();
     if ((busiHoursClose_hour_int >= 0) && (busiHoursClose_hour_int < 12))
     {
       int dayStart_hour = Integer.valueOf(busiHoursClose_hour).intValue();
       int dayStart_min = busiHoursClose_min_int + 1;
       if (dayStart_min >= 60)
       {
         dayStart_min = 0;
         dayStart_hour++;
       }
 
       dayStart = new StringBuilder().append(day).append(" ").append(dayStart_hour < 10 ? new StringBuilder().append("0").append(dayStart_hour).toString() : Integer.valueOf(dayStart_hour)).append(":").append(dayStart_min < 10 ? new StringBuilder().append("0").append(dayStart_min).toString() : Integer.valueOf(dayStart_min)).append(":00").toString();
       dayEnd = new StringBuilder().append(getTime4XDaysAfter(1, dayStart)).append(" ").append(busiHoursClose).append(":59").toString();
     }
     System.out.println(new StringBuilder().append("dayStart:").append(dayStart).toString());
     System.out.println(new StringBuilder().append("dayEnd:").append(dayEnd).toString());
     dateArr[0] = dayStart;
     dateArr[1] = dayEnd;
     return dateArr;
   }
 
   public static void main(String[] args) throws ParseException {
     System.out.println(getAttributionDay(toAllDate("2014-11-06 11:42:52"), "03:59"));
   }
 
   public static String getAttributionDay(Date payTime, String busiHoursClose)
   {
     String day = toString(payTime);
     String hm = toStringHHmmss(payTime);
     String busiHoursCloseDate = new StringBuilder().append(busiHoursClose).append(":00").toString();
     if ((toAllDate(new StringBuilder().append(day).append(" ").append(busiHoursCloseDate).toString()).getTime() >= toAllDate(new StringBuilder().append(day).append(" 00:00:00").toString()).getTime()) && (toAllDate(new StringBuilder().append(day).append(" ").append(busiHoursCloseDate).toString()).getTime() < toAllDate(new StringBuilder().append(day).append(" 12:00:00").toString()).getTime()))
     {
       if (toAllDate(new StringBuilder().append(day).append(" ").append(hm).toString()).getTime() >= toAllDate(new StringBuilder().append(day).append(" ").append(busiHoursCloseDate).toString()).getTime())
       {
         return day;
       }
 
       return getTime4XDaysAgo(1, day);
     }
 
     return day;
   }
 
   public static String[] getBussiDayForDayBrief(String day, String busiHoursClose, String busiHoursOpen)
   {
     String[] dateArr = new String[2];
     String dayStart = new StringBuilder().append(day).append(" ").append(busiHoursOpen).append(":00").toString();
     String dayEnd = new StringBuilder().append(day).append(" ").append(busiHoursClose).append(":59").toString();
     String busiHoursClose_hour = busiHoursClose.substring(0, busiHoursClose.indexOf(":"));
     String busiHoursOpen_hour = busiHoursOpen.substring(0, busiHoursOpen.indexOf(":"));
     int busiHoursClose_hour_int = Integer.valueOf(busiHoursClose_hour).intValue();
     int busiHoursOpen_hour_int = Integer.valueOf(busiHoursOpen_hour).intValue();
     if ((busiHoursClose_hour_int >= 0) && (busiHoursClose_hour_int < busiHoursOpen_hour_int))
     {
       dayEnd = new StringBuilder().append(getTime4XDaysAfter(1, dayStart)).append(" ").append(busiHoursClose).append(":59").toString();
     }
     System.out.println(new StringBuilder().append("dayStart:").append(dayStart).toString());
     System.out.println(new StringBuilder().append("dayEnd:").append(dayEnd).toString());
     dateArr[0] = dayStart;
     dateArr[1] = dayEnd;
     return dateArr;
   }
 }

