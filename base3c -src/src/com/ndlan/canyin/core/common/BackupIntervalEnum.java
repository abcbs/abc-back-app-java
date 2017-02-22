 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.BackupIntervalEnum;
import java.io.PrintStream;
 
 public enum BackupIntervalEnum
 {
   ONE_DAY("1", "一天"), 
   TWO_DAY("2", "二天"), 
   ONE_WEEK("3", "一周"), 
   ONE_MONTH("4", "一个月"), 
   TWO_MONTH("5", "二个月"), 
   SIX_MONTH("6", "六个月"), 
   ONE_YEAR("7", "一年");
 
   public static final String enumCode = "BackupInterval";
   public static final String enumName = "备份设置";
   private String code;
   private String desc;
 
   private BackupIntervalEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (BackupIntervalEnum status : values()) {
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
 
   public static void main(String[] args)
   {
     generateSQL();
   }
 
   public static void generateSQL()
   {
     String uuid = Identities.uuid2();
     String baseSql = "insert into md_base_code (bc_id,bc_code,bc_name,bc_desc,is_enable,version) VALUES ( '" + uuid + "' , " + 
       "'" + "BackupInterval" + "','" + "备份设置" + "','" + "备份设置" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (BackupIntervalEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "BackupInterval" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

 