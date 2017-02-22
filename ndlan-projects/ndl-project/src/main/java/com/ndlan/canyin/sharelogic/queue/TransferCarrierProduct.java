 package com.ndlan.canyin.sharelogic.queue;
 
 import java.io.PrintStream;
import java.util.Map;

import com.ndlan.canyin.sharelogic.queue.Basket;
 
 public class TransferCarrierProduct
   implements Runnable
 {
   private int instance;
   private Basket basket;
   private Map<String, Object> map;
   private String restId;
 
   public TransferCarrierProduct(int instance, Basket basket, Map<String, Object> map, String restId)
   {
     this.instance = instance;
     this.basket = basket;
     this.map = map;
     this.restId = restId;
   }
 
   public void run()
   {
     try {
       System.out.println("用户准备生产操作记录：" + this.instance);
       this.map.put("restId", this.restId);
       this.basket.produce(this.map);
       System.out.println("!生产者生产操作完毕：" + this.instance + "_" + this.basket.size());
     } catch (InterruptedException ex) {
       System.out.println("Producer Interrupted");
     }
   }
 }

