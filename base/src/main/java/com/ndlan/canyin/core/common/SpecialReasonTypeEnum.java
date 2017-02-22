 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.SpecialReasonTypeEnum;
import java.io.PrintStream;
 
 public enum SpecialReasonTypeEnum
 {
   CANCEL_DISH_REASON("1", "退菜原因"), 
 
   CANCEL_BILL_REASON("3", "撤单原因"), 
 
   RESETTLE_REASON("5", "反结账原因"), 
 
   CALL_SERVICE("7", "呼叫服务"), 
   CANCEL_ORDER("8", "取消预订原因"), 
   TAKEOUT_REVIEWFAILED_REASON("9", "外卖审核失败原因"), 
   ORDER_REVIEWFAILED_REASON("10", "预订审核失败原因");
 
   public static final String enumCode = "SpecialReasonType";
   public static final String enumName = "特殊原因";
   private String code;
   private String desc;
 
   private SpecialReasonTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (SpecialReasonTypeEnum status : values()) {
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
       "'" + "SpecialReasonType" + "','" + "特殊原因" + "','" + "特殊原因" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (SpecialReasonTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "SpecialReasonType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

