 package com.ndlan.canyin.base.repository.synch;
 
 public class SynchResult
 {
   private boolean complete;
   private String desc;
 
   public boolean isComplete()
   {
     return this.complete;
   }
 
   public void setComplete(boolean complete) {
     this.complete = complete;
   }
 
   public String getDesc() {
     return this.desc;
   }
 
   public void setDesc(String desc) {
     this.desc = desc;
   }
 }

