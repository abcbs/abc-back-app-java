 package com.ndlan.canyin.core.vo;
 
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 import java.util.ArrayList;
 
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class PageVo<T>
 {
   private int total;
   private int pageSize;
   private boolean hasNextPage;
   private int pageNumber;
   private String dataStr;
   private String otherData;
   private ArrayList<T> dataList = new ArrayList();
 
   public ArrayList<T> getDataList() {
     return this.dataList;
   }
 
   public void setDataList(ArrayList<T> dataList) {
     this.dataList = dataList;
   }
 
   public void setTotal(int total) {
     this.total = total;
   }
 
   public int getTotal() {
     return this.total;
   }
 
   public int getPageSize() {
     return this.pageSize;
   }
 
   public void setPageSize(int pageSize) {
     this.pageSize = pageSize;
   }
 
   public boolean isHasNextPage() {
     return getPageNumber() * getPageSize() < this.total;
   }
 
   public void setHasNextPage(boolean hasNextPage) {
     this.hasNextPage = hasNextPage;
   }
 
   public int getPageNumber() {
     return this.pageNumber;
   }
 
   public void setPageNumber(int pageNumber) {
     this.pageNumber = pageNumber;
   }
 
   public String getDataStr() {
     return this.dataStr;
   }
 
   public void setDataStr(String dataStr) {
     this.dataStr = dataStr;
   }
 
   public String getOtherData() {
     return this.otherData;
   }
 
   public void setOtherData(String otherData) {
     this.otherData = otherData;
   }
 }

