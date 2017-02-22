 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import java.io.PrintStream;
 
 public enum TrueFalseEnum
 {
   FALSE("0", "是"), 
   TRUE("1", "否");
 
   public static final String enumCode = "TrueFalse";
   public static final String enumName = "是否";
   private String code;
   private String desc;
 
   private TrueFalseEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (TrueFalseEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public static String getCode(String desc) {
     for (TrueFalseEnum status : values()) {
       if (status.getDesc().equalsIgnoreCase(desc)) {
         return status.getCode();
       }
     }
     return FALSE.getCode();
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
       "'" + "TrueFalse" + "','" + "是否" + "','" + "是否" + "','1',0);";
     System.out.println(baseSQl);
     EnumUtil.insert(baseSQl);
     for (TrueFalseEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "TrueFalse" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

