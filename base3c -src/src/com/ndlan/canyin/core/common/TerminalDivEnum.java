 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.TerminalDivEnum;
import java.io.PrintStream;
 
 public enum TerminalDivEnum
 {
   IPAD("1", "ipad"), 
   IPHONE("2", "iphone"), 
   GPAD("3", "安卓平板"), 
   GPHONE("4", "安卓手机"), 
   WPAD("5", "WP平板"), 
   WPHONE("6", "WP手机"), 
   OTHER("0", "其他");
 
   public static final String enumCode = "TerminalDiv";
   public static final String enumName = "终端区分";
   private String code;
   private String desc;
 
   private TerminalDivEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (TerminalDivEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public static String getCode(String desc) {
     for (TerminalDivEnum status : values()) {
       if (status.getDesc().equalsIgnoreCase(desc)) {
         return status.getCode();
       }
     }
     return IPAD.getCode();
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
       "'" + "TerminalDiv" + "','" + "终端区分" + "','" + "终端区分" + "','1',0);";
     System.out.println(baseSQl);
     EnumUtil.insert(baseSQl);
     for (TerminalDivEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "TerminalDiv" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

