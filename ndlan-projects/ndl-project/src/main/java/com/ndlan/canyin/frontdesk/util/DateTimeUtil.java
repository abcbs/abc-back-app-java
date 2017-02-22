package com.ndlan.canyin.frontdesk.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	
	/**
	 * 两个日期间隔天数
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static int getBetweenDays(String startDate,String endDate) throws ParseException{ 
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		int betweenDays = 0; 
		Date d1 = format.parse(startDate); 
		Date d2 = format.parse(endDate); 
		Calendar c1 = Calendar.getInstance(); 
		Calendar c2 = Calendar.getInstance(); 
		c1.setTime(d1); 
		c2.setTime(d2); 
		// 保证第二个时间一定大于第一个时间 
		if(c1.after(c2)){ 
		c1 = c2; 
		c2.setTime(d1); 
		} 
		int betweenYears = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR); 
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR); 
		for(int i=0;i<betweenYears;i++){ 
		c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1)); 
		betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR); 
		} 
		return betweenDays; 
		} 
	
	/**
	 * 日期增加N天的方法
	 * @param date
	 * @param n
	 * @return
	 */
	public static String addDay(String date, int n) {   
        try {   
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
  
                 Calendar cd = Calendar.getInstance();   
                 cd.setTime(sdf.parse(date));   
                 cd.add(Calendar.DATE, n);//增加一天   
                 //cd.add(Calendar.MONTH, n);//增加一个月   
  
                 return sdf.format(cd.getTime());   
       
             } catch (Exception e) {   
                 return null;   
             }   
     }  

} 

