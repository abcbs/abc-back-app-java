 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.TakeoutStatusEnum;
import java.io.PrintStream;
 
 public enum TakeoutStatusEnum
 {
   WAIT_COMMIT("1", "待确认"), 
   CONFIRM("2", "已确认"), 
   NOT_CONFIRM("3", "餐厅未接受"), 
   PLACE_ORDER("4", "已下单"), 
   DELIVERING("5", "派送中 "), 
   DELIVERIES("6", "已送达 "), 
   PAYED("7", "已结账"), 
   CANCEL("8", "已取消");
 
   public static final String enumCode = "TakeoutStatus";
   public static final String enumName = "外卖账单状态";
   private String code;
   private String desc;
 
   private TakeoutStatusEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (TakeoutStatusEnum status : values()) {
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
       "'" + "TakeoutStatus" + "','" + "外卖账单状态" + "','" + "外卖账单状态" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (TakeoutStatusEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "TakeoutStatus" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

