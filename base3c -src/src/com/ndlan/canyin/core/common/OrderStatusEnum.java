 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.OrderStatusEnum;
import java.io.PrintStream;
 
 public enum OrderStatusEnum
 {
   WAIT_COMMIT("1", "待确认"), 
   APPLING("2", "预定中"), 
   OVER("4", "完结"), 
   CANCEL("5", "已取消"), 
   NOT_CONFIRM("6", "餐厅未接受"), 
   EXPIRE("7", "已过期"), 
   EATING("8", "就餐中");
 
   public static final String enumCode = "OrderStatus";
   public static final String enumName = "预订状态";
   private String code;
   private String desc;
 
   private OrderStatusEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (OrderStatusEnum status : values()) {
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
       "'" + "OrderStatus" + "','" + "预订状态" + "','" + "预订状态" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (OrderStatusEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "OrderStatus" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

