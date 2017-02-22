 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.EmpOpTypeEnum;
import java.io.PrintStream;
 
 public enum EmpOpTypeEnum
 {
   LOGIN("1", "登录"), 
   LOGOUT("2", "退出"), 
   START_TABLE("3", "开台"), 
   ORDER_DISH("4", "点菜"), 
   ORDER_BILL("5", "下单"), 
   CHANGE_BILL("6", "改单"), 
   CHANGE_TABLE("7", "改台"), 
   TURN_DISH("8", "转菜"), 
   CHANGE_DISH("9", "改菜"), 
   CANCEL_DISH("10", "退菜"), 
   SETTLE("11", "结账"), 
   OTHER("0", "其他");
 
   public static final String enumCode = "EmpOpType";
   public static final String enumName = "员工操作日志类型";
   private String code;
   private String desc;
 
   private EmpOpTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (EmpOpTypeEnum status : values()) {
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
       "'" + "EmpOpType" + "','" + "员工操作日志类型" + "','" + "员工操作日志类型" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (EmpOpTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "EmpOpType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

