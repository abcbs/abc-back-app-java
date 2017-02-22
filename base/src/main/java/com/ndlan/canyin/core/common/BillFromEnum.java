 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.BillFromEnum;
import java.io.PrintStream;
 
 public enum BillFromEnum
 {
   SELF_REST("1", "店内"), 
   NET_TAKEOUT("2", "云餐厅外卖"), 
   NET_ORDER("3", "云餐厅预订"), 
   REST_TAKEOUT("4", "店内外卖"), 
   REST_ORDER("5", "店内预订"), 
   WX_TAKEOUT("6", "微信餐厅外卖"), 
   WX_ORDER("7", "微信餐厅预订");
 
   public static final String enumCode = "BillFrom";
   public static final String enumName = "账单来源";
   private String code;
   private String desc;
 
   private BillFromEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (BillFromEnum status : values()) {
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
       "'" + "BillFrom" + "','" + "账单来源" + "','" + "账单来源" + "','1',0);";
     System.out.println(baseSQl);
     EnumUtil.insert(baseSQl);
     for (BillFromEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "BillFrom" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

 