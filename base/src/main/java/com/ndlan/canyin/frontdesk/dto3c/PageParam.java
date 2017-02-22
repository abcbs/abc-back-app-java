 package com.ndlan.canyin.frontdesk.dto3c;
 
 public class PageParam
 {
   private long totalCount = 0L;
 
   private int numPerPage = 8;
 
   private int currentPage = 1;
 
   private int pageNumShown = 10;
 
   public PageParam(long totalCount, int numPerPage, int currentPage, int pageNumShown)
   {
     this.totalCount = totalCount;
     this.numPerPage = numPerPage;
     this.currentPage = currentPage;
     this.pageNumShown = pageNumShown;
   }
   public long getTotalCount() {
     return this.totalCount;
   }
   public void setTotalCount(long totalCount) {
     this.totalCount = totalCount;
   }
   public int getNumPerPage() {
     return this.numPerPage;
   }
   public void setNumPerPage(int numPerPage) {
     this.numPerPage = numPerPage;
   }
   public int getCurrentPage() {
     return this.currentPage;
   }
   public void setCurrentPage(int currentPage) {
     this.currentPage = currentPage;
   }
   public int getPageNumShown() {
     return this.pageNumShown;
   }
   public void setPageNumShown(int pageNumShown) {
     this.pageNumShown = pageNumShown;
   }
 }

