 package com.ndlan.canyin.frontdesk.transport;
 
 import com.ndlan.canyin.frontdesk.util.JsonMapper;

 import java.io.PrintStream;
 import java.io.Serializable;
 import java.util.Date;
 import java.util.List;
import java.util.UUID;
 
 public class Envelope
   implements Serializable
 {
   private int sign;
   private String restId;
   private int idSize;
   private List<String> ids;
   private String serialNO;
   private String content;
   private Date time = new Date();
 
   public Envelope()
   {
     this.serialNO = makeSerialNO();
   }
 
   public Envelope(int sign, String restId)
   {
     this.sign = sign;
     this.restId = restId;
     this.serialNO = makeSerialNO();
   }
 
   private String makeSerialNO() {
     return UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
   }
 
   public int getSign() {
     return this.sign;
   }
 
   public void setSign(int sign) {
     this.sign = sign;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public List<String> getIds() {
     return this.ids;
   }
 
   public void setIds(List<String> ids) {
     this.ids = ids;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getSerialNO() {
     return this.serialNO;
   }
 
   public void setSerialNO(String serialNO) {
     this.serialNO = serialNO;
   }
 
   public static void main(String[] args) {
     System.out.println(new Envelope().getSerialNO());
   }
 
   public String toJsonString() {
     return JsonMapper.nonEmptyMapper().toJson(this);
   }
 
   public int getIdSize() {
     return this.idSize;
   }
 
   public Date getTime() {
     return this.time;
   }
 
   public void setTime(Date time) {
     this.time = time;
   }
 
   public void setIdSize(int idSize) {
     this.idSize = idSize;
   }
 }

