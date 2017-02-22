 package com.ndlan.canyin.sharelogic.util;
 
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.Future;
 
 public class PriorityExecutor
 {
   private static ExecutorService printService = Executors.newCachedThreadPool();
 
   private static ExecutorService logService = Executors.newCachedThreadPool();
 
   private static ExecutorService taskService = Executors.newCachedThreadPool();
 
   private static ExecutorService synService = Executors.newCachedThreadPool();
 
   private static ExecutorService saveService = Executors.newCachedThreadPool();
 
   public static Future execPrint(Runnable task)
   {
     return printService.submit(task);
   }
 
   public static Future execLog(Runnable task)
   {
     return logService.submit(task);
   }
 
   public static Future execSyn(Runnable task) {
     return synService.submit(task);
   }
 
   public static Future execTask(Runnable task) {
     return taskService.submit(task);
   }
 
   public static Future execSave(Runnable task) {
     return saveService.submit(task);
   }
 }

