 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.CardOperationTypeEnum;
import java.io.PrintStream;
 
 public enum CardOperationTypeEnum
 {
   RECHARGE("1", "充值"), 
   CONSUME("2", "消费"), 
   CASH_PLEDGE("3", "办卡"), 
   CANCEL_CARD("4", "退卡"), 
   RESETTLE_REFUND("5", "反结账退款"), 
   CANCEL_REFUND("6", "撤单退款"), 
   CANCEL_ORDER_REFUND("7", "取消预订退款");
 
   public static final String enumCode = "CardOperationType";
   public static final String enumName = "会员卡操作类型";
   private String code;
   private String desc;
 
   private CardOperationTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (CardOperationTypeEnum status : values()) {
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
       "'" + "CardOperationType" + "','" + "会员卡操作类型" + "','" + "会员卡操作类型" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (CardOperationTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "CardOperationType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

