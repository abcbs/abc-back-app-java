 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.TakeoutSendTimeEnum;
import java.io.PrintStream;
 
 public enum TakeoutSendTimeEnum
 {
   TIME_0("0", "预订时间内"), 
   TIME_15("15", "超过送餐时间<=15分钟"), 
   TIME_30("30", "超过送餐时间>15分钟  && <= 30分钟"), 
   TIME_LONG("-1", "超过送餐时间 >30分钟");
 
   public static final String enumCode = "TakeoutSendTime";
   public static final String enumName = "外卖送餐时间类型";
   private String code;
   private String desc;
 
   private TakeoutSendTimeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (TakeoutSendTimeEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public String getDesc() {
     return this.desc;
   }
 
   public void setDesc(String desc) {
     this.desc = desc;
   }
 
   public String getCode() {
     return this.code;
   }
 
   public void setCode(String code) {
     this.code = code;
   }
 
   public static void main(String[] args) {
     generateSQL();
   }
 
   public static void generateSQL()
   {
     String uuid = Identities.uuid2();
     String baseSQl = "insert into md_base_code (bc_id,bc_code,bc_name,bc_desc,is_enable,version) VALUES ( '" + uuid + "' , " + 
       "'" + "TakeoutSendTime" + "','" + "外卖送餐时间类型" + "','" + "外卖送餐时间类型" + "','1',0);";
     System.out.println(baseSQl);
     EnumUtil.insert(baseSQl);
     for (TakeoutSendTimeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "TakeoutSendTime" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

