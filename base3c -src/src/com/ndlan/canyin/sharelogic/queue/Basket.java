 package com.ndlan.canyin.sharelogic.queue;
 
 import com.ndlan.canyin.base.entity.synch.Transaction;
 import java.util.Map;
 import java.util.concurrent.BlockingQueue;
 import java.util.concurrent.LinkedBlockingQueue;
 
 public class Basket
 {
   BlockingQueue<Map<String, Object>> basket = new LinkedBlockingQueue();
 
   BlockingQueue<Transaction> transactionBasket = new LinkedBlockingQueue();
 
   public void produce(Map<String, Object> map)
     throws InterruptedException
   {
     this.basket.put(map);
   }
 
   public Map<String, Object> consume()
     throws InterruptedException
   {
     return (Map)this.basket.take();
   }
 
   public boolean isEmpty()
   {
     return this.basket.isEmpty();
   }
 
   public int size() {
     return this.basket.size();
   }
 
   public void produceTransaction(Transaction map) throws InterruptedException
   {
     this.transactionBasket.put(map);
   }
 
   public Transaction consumeTransaction() throws InterruptedException {
     return (Transaction)this.transactionBasket.take();
   }
 }

