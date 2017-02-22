 package com.ndlan.canyin.core.utils;
 
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.Future;
 
 public class LowPriorityExecutor
 {
   private static ExecutorService logService = Executors.newFixedThreadPool(2);
 
   public static Future execLog(Runnable task)
   {
     return logService.submit(task);
   }
 }

