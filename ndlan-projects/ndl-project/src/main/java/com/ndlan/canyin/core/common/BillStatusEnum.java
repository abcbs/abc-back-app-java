 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.BillStatusEnum;
import java.io.PrintStream;
 
 public enum BillStatusEnum
 {
   NOT_PLACE_ORDER("1", "未下单"), 
   PLACE_ORDER("2", "已下单"), 
 
   SETTLE("3", "已结账"), 
 
   RESETTLE("4", "反结账"), 
 
   CANCEL_BILL("8", "撤单"), 
 
   SOME_PLACE_ORDER("9", "部分下单"), 
 
   BING_TAI("10", "已并台"), 
   SENDING("11", "派送中");
 
   public static final String enumCode = "BillStatus";
   public static final String enumName = "账单状态";
   private String code;
   private String desc;
 
   private BillStatusEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (BillStatusEnum status : values()) {
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
       "'" + "BillStatus" + "','" + "账单状态" + "','" + "账单状态" + "','1',0);";
     System.out.println(baseSQl);
     EnumUtil.insert(baseSQl);
     for (BillStatusEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "BillStatus" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

 