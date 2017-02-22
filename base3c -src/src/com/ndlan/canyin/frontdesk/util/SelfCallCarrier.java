 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.core.common.SelfServiceMarkEnum;
 
 public class SelfCallCarrier
 {
   private String tabNo;
   private SelfServiceMarkEnum calledType;
   private boolean isCalled = true;
 
   private boolean isHandled = false;
   private String calledTime;
   private String callOrderhost;
   private String waiterId;
   private String waiterName;
   private String mesId;
 
   public String getTabNo()
   {
     return this.tabNo;
   }
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
   public SelfServiceMarkEnum getCalledType() {
     return this.calledType;
   }
   public void setCalledType(SelfServiceMarkEnum calledType) {
     this.calledType = calledType;
   }
   public boolean isCalled() {
     return this.isCalled;
   }
   public void setCalled(boolean isCalled) {
     this.isCalled = isCalled;
   }
   public boolean isHandled() {
     return this.isHandled;
   }
   public void setHandled(boolean isHandled) {
     this.isHandled = isHandled;
   }
   public String getCalledTime() {
     return this.calledTime;
   }
   public void setCalledTime(String calledTime) {
     this.calledTime = calledTime;
   }
   public String getCallOrderhost() {
     return this.callOrderhost;
   }
   public void setCallOrderhost(String callOrderhost) {
     this.callOrderhost = callOrderhost;
   }
   public String getWaiterId() {
     return this.waiterId;
   }
   public void setWaiterId(String waiterId) {
     this.waiterId = waiterId;
   }
   public String getWaiterName() {
     return this.waiterName;
   }
   public void setWaiterName(String waiterName) {
     this.waiterName = waiterName;
   }
   public String getMesId() {
     return this.mesId;
   }
   public void setMesId(String mesId) {
     this.mesId = mesId;
   }
 }

