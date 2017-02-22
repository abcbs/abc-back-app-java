 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.BussLogActEnum;
import java.io.PrintStream;
 
 public enum BussLogActEnum
 {
   NOT_PLACE_ORDER("1", "未下单"), 
   PLACE_ORDER("2", "已下单"), 
 
   SETTLE("3", "已结账"), 
 
   RESETTLE("4", "反结账"), 
 
   CANCEL_BILL("8", "撤单"), 
 
   SOME_PLACE_ORDER("9", "部分下单"), 
 
   BING_TAI("10", "已并台"), 
   ORDER("13", "预订"), 
   CONFIRM("14", "确认预订"), 
   CHANGE_OPEN("15", "转开台"), 
   CANCEL("16", "取消预订");
 
   public static final String enumCode = "BussLogAct";
   public static final String enumName = "数据日志类型";
   private String code;
   private String desc;
 
   private BussLogActEnum(String code, String desc) {
     this.code = code;
     this.desc = desc;
   }
 
   public static String getDesc(String code) {
     for (BussLogActEnum status : values()) {
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
       "'" + "BussLogAct" + "','" + "数据日志类型" + "','" + "数据日志类型" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (BussLogActEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "BussLogAct" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

