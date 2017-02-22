 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.RegionDivEnum;
import java.io.PrintStream;
 
 public enum RegionDivEnum
 {
   PROVINCE("0", "省"), 
   CITY("1", "市"), 
   AREA("2", "县");
 
   public static final String enumCode = "RegionDiv";
   public static final String enumName = "省市区分";
   private String code;
   private String desc;
 
   private RegionDivEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (RegionDivEnum status : values()) {
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
       "'" + "RegionDiv" + "','" + "省市区分" + "','" + "省市区分" + "','1',0);";
     System.out.println();
     EnumUtil.insert(baseSql);
     for (RegionDivEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version,show_seq) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "RegionDiv" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0,0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

