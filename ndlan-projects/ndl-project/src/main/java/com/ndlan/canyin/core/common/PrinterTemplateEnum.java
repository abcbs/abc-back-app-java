 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.PrinterTemplateEnum;
import java.io.PrintStream;
 
 public enum PrinterTemplateEnum
 {
   C_58("58", "58mm"), 
   C_76("76", "76mm"), 
   C_80("80", "80mm"), 
 
   C_80_LEFT("3.5", "左边距"), 
   C_80_RIGHT("3.5", "右边距"), 
   C_80_UP("0", "上边距"), 
   C_80_DOWN("0", "下边距"), 
 
   C_76_LEFT("1.1", "左边距"), 
   C_76_RIGHT("1.1", "右边距"), 
   C_76_UP("0", "上边距"), 
   C_76_DOWN("0", "下边距"), 
 
   C_58_LEFT("1.4", "左边距"), 
   C_58_RIGHT("1.4", "右边距"), 
   C_58_UP("0", "上边距"), 
   C_58_DOWN("0", "下边距"), 
 
   C_DEFAULT_LEFT("3.5", "左边距"), 
   C_DEFAULT_RIGHT("3.5", "右边距"), 
   C_DEFAULT_UP("0", "上边距"), 
   C_DEFAULT_DOWN("0", "下边距");
 
   public static final String enumCode = "PrinterTemplate";
   public static final String enumName = "打印模板";
   private String code;
   private String desc;
 
   private PrinterTemplateEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (PrinterTemplateEnum status : values()) {
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
       "'" + "PrinterTemplate" + "','" + "打印模板" + "','" + "打印模板" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (PrinterTemplateEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "PrinterTemplate" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

