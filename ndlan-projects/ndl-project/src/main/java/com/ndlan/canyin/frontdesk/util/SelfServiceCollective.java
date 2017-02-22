 package com.ndlan.canyin.frontdesk.util;
 
 import java.util.HashMap;
 import java.util.Map;
 
 public class SelfServiceCollective
 {
   Map<String, SelfTableCarrier> selfTableCarrier = null;
 
   public void init()
   {
     this.selfTableCarrier = new HashMap();
   }
 
   public Map<String, SelfTableCarrier> getSelfTableCarrier()
   {
     return this.selfTableCarrier;
   }
 
   public void setSelfTableCarrier(Map<String, SelfTableCarrier> selfTableCarrier) {
     this.selfTableCarrier = selfTableCarrier;
   }
 
   public void removeTable(String tabNo)
   {
     SelfTableCarrier selfTableCarrier = new SelfTableCarrier();
     this.selfTableCarrier.put(tabNo, selfTableCarrier);
   }
 }

