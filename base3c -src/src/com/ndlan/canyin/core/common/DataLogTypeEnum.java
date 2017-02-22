 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.DataLogTypeEnum;
import java.io.PrintStream;
 
 public enum DataLogTypeEnum
 {
   TABLEZONE("1", "餐台分区"), 
   TABLEINFO("2", "餐台信息"), 
   DISHCATEGORY("3", "菜肴分类"), 
   DISHINFO("4", "菜品信息"), 
   DISCOUNT("5", "折扣方案"), 
   CARD("6", "会员卡"), 
   STOCKINFO("7", "仓库信息"), 
   RAWMATERIALINFO("8", "原料信息"), 
   DISHESETINFO("9", "套餐信息"), 
   DISHESETCATEGORY("10", "套餐分类");
 
   public static final String enumCode = "DataLogType";
   public static final String enumName = "数据日志类型";
   private String code;
   private String desc;
 
   private DataLogTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (DataLogTypeEnum status : values()) {
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
       "'" + "DataLogType" + "','" + "数据日志类型" + "','" + "数据日志类型" + "','1',0);";
     System.out.println(baseSql);
 
     for (DataLogTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "DataLogType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
     }
   }
 }

