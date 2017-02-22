 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.DiningEnvEnum;
import java.io.PrintStream;
 
 public enum DiningEnvEnum
 {
   LOVERS("1", "情侣约会"), 
   FAMILY("2", "家庭聚会"), 
   WEDDING("3", "婚礼婚宴"), 
   ARDER("4", "休闲小憩"), 
   FRIEND("5", "朋友聚餐"), 
   BUSINESS("6", "商务宴请"), 
   MEETING("7", "年会会议"), 
   SPETASTE("8", "特色口味"), 
   OTHER("0", "其他");
 
   public static final String enumCode = "DiningEnv";
   public static final String enumName = "就餐氛围";
   private String code;
   private String desc;
 
   private DiningEnvEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (DiningEnvEnum status : values()) {
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
       "'" + "DiningEnv" + "','" + "就餐氛围" + "','" + "就餐氛围" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (DiningEnvEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "DiningEnv" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

