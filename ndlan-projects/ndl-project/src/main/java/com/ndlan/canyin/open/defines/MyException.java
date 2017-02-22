 package com.ndlan.canyin.open.defines;
 
 import com.ndlan.canyin.frontdesk.exception.ServiceException;
 
 public class MyException extends ServiceException
 {
   private String code;
   private String notes;
 
   public String getNotes()
   {
     return this.notes;
   }
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public MyException()
   {
   }
 
   public MyException(String msg)
   {
     super(msg);
   }
 
   public MyException(String code, String msg)
   {
     super(msg);
     this.code = code;
     this.notes = "";
   }
 
   public MyException(String code, String msg, String notes)
   {
     super(msg);
     this.code = code;
     this.notes = notes;
   }
 
   public static void main(String[] args)
   {
   }
 
   public String getCode()
   {
     return this.code;
   }
   public void setCode(String code) {
     this.code = code;
   }
 }

