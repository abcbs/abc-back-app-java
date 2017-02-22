 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.base.entity.cygl.Dishe;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 public class SelfTableCarrier
 {
   private String tabNo;
   private boolean isCalled;
   private boolean isHandled;
   private String calledTime;
   private String callOrderhost;
   private String notes;
   private String mesId;
   private Map<String, Dishe> dishMap = new HashMap();
 
   private Map<String, Dishe> orderedDishMap = new HashMap();
 
   private List<SelfCallCarrier> callList = new ArrayList();
 
   public void addDishToOrderedDish(List<Dishe> successAddDishs)
   {
     for (Dishe d : successAddDishs)
     {
       this.orderedDishMap.put(d.getDishesId(), d);
       this.dishMap.remove(d.getDishesId());
     }
   }
 
   public boolean isHandled() {
     return this.isHandled;
   }
 
   public void setHandled(boolean isHandled) {
     this.isHandled = isHandled;
   }
 
   public boolean isCalled() {
     return this.isCalled;
   }
 
   public void setCalled(boolean isCalled) {
     this.isCalled = isCalled;
   }
 
   public String getCalledTime() {
     return this.calledTime;
   }
 
   public void setCalledTime(String calledTime) {
     this.calledTime = calledTime;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public Map<String, Dishe> getDishMap() {
     return this.dishMap;
   }
 
   public void setDishMap(Map<String, Dishe> dishMap) {
     this.dishMap = dishMap;
   }
 
   public String getCallOrderhost() {
     return this.callOrderhost;
   }
 
   public void setCallOrderhost(String callOrderhost) {
     this.callOrderhost = callOrderhost;
   }
 
   public Map<String, Dishe> getOrderedDishMap() {
     return this.orderedDishMap;
   }
 
   public void setOrderedDishMap(Map<String, Dishe> orderedDishMap) {
     this.orderedDishMap = orderedDishMap;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public List<SelfCallCarrier> getCallList() {
     return this.callList;
   }
 
   public void setCallList(List<SelfCallCarrier> callList) {
     this.callList = callList;
   }
 
   public String getMesId() {
     return this.mesId;
   }
 
   public void setMesId(String mesId) {
     this.mesId = mesId;
   }
 }

