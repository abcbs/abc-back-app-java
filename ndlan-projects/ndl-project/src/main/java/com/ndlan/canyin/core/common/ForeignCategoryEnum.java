 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.ForeignCategoryEnum;
import java.io.PrintStream;
 
 public enum ForeignCategoryEnum
 {
   ENGLISH("1", "英文"), 
   JAPANESE("2", "日文"), 
   KOREAN("3", "韩文"), 
   FRENCH("4", "法文"), 
   GERMAN("5", "德文"), 
   RUSSIAN("6", "俄文"), 
   SPANISH("7", "西班牙"), 
   PORTUGUESA("8", "葡萄牙"), 
   DUTCH("9", "荷兰"), 
   SWEDEN("10", "瑞典"), 
   OTHER("0", "其他");
 
   public static final String enumCode = "ForeignCategory";
   public static final String enumName = "外语种类";
   private String code;
   private String desc;
 
   private ForeignCategoryEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (ForeignCategoryEnum status : values()) {
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
     String baseSql = "insert into md_base_code (bc_id,bc_code,bc_name,bc_desc,is_enable,version) VALUES ( '" + uuid + "' , " + 
       "'" + "ForeignCategory" + "','" + "外语种类" + "','" + "外语种类" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (ForeignCategoryEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "ForeignCategory" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

