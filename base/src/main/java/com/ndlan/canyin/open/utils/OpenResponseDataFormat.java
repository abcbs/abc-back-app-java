 package com.ndlan.canyin.open.utils;
 
 import com.ndlan.canyin.core.common.OpenInterfaceDefineEnum;
 
 public class OpenResponseDataFormat
 {
   private String interfaceNo;
   private String interfaceMessage;
   private String jourNo;
   private String internalJourNo;
   private String success = "200";
   private String paraErr = "300";
   private String logicErr = "400";
   private String exceptionErr = "500";
   private String otherErr = "900";
 
   private String noDataSuccess = "201";
   private String noOpen = "999";
 
   public OpenResponseDataFormat(String interfaceNo, String internalJourNo)
   {
     this.interfaceNo = interfaceNo;
     this.interfaceMessage = OpenInterfaceDefineEnum.getDesc(interfaceNo);
     this.internalJourNo = internalJourNo;
   }
 
   public Object[] succsess()
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.success, this.success, this.interfaceMessage + "成功", "" };
   }
 
   public Object[] succsess(String notes)
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.success, this.success, this.interfaceMessage + "成功", notes };
   }
 
   public Object[] noDataSuccsess()
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.success, this.noDataSuccess, this.interfaceMessage + "成功", "" };
   }
 
   public Object[] noDataSuccsess(String notes)
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.success, this.noDataSuccess, this.interfaceMessage + "成功", notes };
   }
 
   public Object[] succsess(Object notes)
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.success, this.success, this.interfaceMessage + "成功", notes };
   }
 
   public Object[] logicErr()
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.logicErr, this.logicErr, "逻辑错误", "" };
   }
 
   public Object[] paramentsErr()
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.paraErr, this.paraErr, "参数校验失败", "" };
   }
 
   public Object[] exceptionErr()
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.exceptionErr, this.exceptionErr, this.interfaceMessage + "失败，程序异常", "" };
   }
 
   public Object[] otherErr()
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.otherErr, this.otherErr, "其他错误", "" };
   }
 
   public Object[] nonOpen()
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.otherErr, this.noOpen, "该接口尚未开放", "" };
   }
 
   public Object[] nonOpen(String notes)
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, this.otherErr, this.noOpen, "该接口尚未开放", notes };
   }
 
   public Object[] custom(int returnCode, String returnMessage, String errorMessage)
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, returnCode / 100 % 10 * 100 + "", returnCode + "", returnMessage, errorMessage };
   }
 
   public Object[] custom(int returnCode, String returnMessage)
   {
     return new Object[] { this.interfaceNo, this.interfaceMessage, this.internalJourNo, returnCode / 100 % 10 * 100 + "", returnCode + "", returnMessage, "" };
   }
 
   public String getInterfaceNo() {
     return this.interfaceNo;
   }
 
   public void setInterfaceNo(String interfaceNo) {
     this.interfaceNo = interfaceNo;
   }
 
   public String getInterfaceMessage() {
     return this.interfaceMessage;
   }
 
   public void setInterfaceMessage(String interfaceMessage) {
     this.interfaceMessage = interfaceMessage;
   }
 
   public String getJourNo() {
     return this.jourNo;
   }
 
   public void setJourNo(String jourNo) {
     this.jourNo = jourNo;
   }
 
   public String getSuccess() {
     return this.success;
   }
 
   public void setSuccess(String success) {
     this.success = success;
   }
 
   public String getParaErr() {
     return this.paraErr;
   }
 
   public void setParaErr(String paraErr) {
     this.paraErr = paraErr;
   }
 
   public String getLogicErr() {
     return this.logicErr;
   }
 
   public void setLogicErr(String logicErr) {
     this.logicErr = logicErr;
   }
 
   public String getExceptionErr() {
     return this.exceptionErr;
   }
 
   public void setExceptionErr(String exceptionErr) {
     this.exceptionErr = exceptionErr;
   }
 
   public String getOtherErr() {
     return this.otherErr;
   }
 
   public void setOtherErr(String otherErr) {
     this.otherErr = otherErr;
   }
 
   public String getNoDataSuccess() {
     return this.noDataSuccess;
   }
 
   public void setNoDataSuccess(String noDataSuccess) {
     this.noDataSuccess = noDataSuccess;
   }
 
   public String getNoOpen() {
     return this.noOpen;
   }
 
   public void setNoOpen(String noOpen) {
     this.noOpen = noOpen;
   }
 
   public String getInternalJourNo() {
     return this.internalJourNo;
   }
 
   public void setInternalJourNo(String internalJourNo) {
     this.internalJourNo = internalJourNo;
   }
 }

