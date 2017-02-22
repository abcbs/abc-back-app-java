 package com.ndlan.canyin.frontdesk.util;
 
 import java.io.PrintStream;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Timer;
 import javax.servlet.ServletContext;
 
 public class TimerManager
 {
   private static final long PERIOD_DAY = 86400000L;
   ScheduledTimerTask task;
   Timer timer;
 
   public TimerManager(int _hour_of_day, int _minute, ServletContext sc)
   {
     Calendar calendar = Calendar.getInstance();
     System.out.println("每天备份执行时间：" + _hour_of_day + " : " + _minute);
 
     calendar.set(11, _hour_of_day);
     calendar.set(12, _minute);
     calendar.set(13, 0);
 
     Date date = calendar.getTime();
 
     if (date.before(new Date())) {
       date = addDay(date, 1);
     }
 
     this.timer = new Timer();
     this.task = new ScheduledTimerTask();
     this.task.setSc(sc);
 
     this.timer.schedule(this.task, date, 86400000L);
   }
 
   public void shutdown()
   {
     this.task.cancel();
     this.task = null;
 
     this.timer.purge();
     this.timer.cancel();
     this.timer = null;
   }
 
   public Date addDay(Date date, int num)
   {
     Calendar startDT = Calendar.getInstance();
     startDT.setTime(date);
     startDT.add(5, num);
     return startDT.getTime();
   }
 }

