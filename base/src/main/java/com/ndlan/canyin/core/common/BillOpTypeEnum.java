 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.BillOpTypeEnum;
import java.io.PrintStream;
 
 public enum BillOpTypeEnum
 {
   START_TABLE("1", "开台"), 
   ORDER_BILL("2", "下单"), 
   CHANGE_BILL("3", "修改开台信息"), 
   CHANGE_TABLE("4", "转台"), 
   MERGE_TABLE("5", "并台"), 
   SETTLE("6", "结账"), 
   RESETTLE("7", "反结账"), 
   CANCEL_DISH("8", "退菜"), 
   CUI_DISH("9", "催菜"), 
   DELETE_DISH("10", "删菜"), 
   HUA_DISH("11", "划菜"), 
   ORDER_DISH("12", "点菜"), 
   CHEDAN_BILL("13", "撤单"), 
   GUA_ZHANG("14", "挂账"), 
   YUDING("15", "预订"), 
   GIVE_DISH("16", "赠菜"), 
   CANCEL_GIVE_DISH("17", "取消赠菜"), 
   OPEN_DRAWER("18", "打开钱箱"), 
   SENDING("19", "派送"), 
 
   ORDER_START_TABLE("30", "转开台"), 
 
   APPROVED("50", "审核通过"), 
   FROM_CLOUD_SUBMIT("51", "云餐厅提交"), 
   FROM_WEIXIN_SUBMIT("52", "微信餐厅提交");
 
   public static final String enumCode = "BillOpType";
   public static final String enumName = "账单操作类型";
   private String code;
   private String desc;
 
   private BillOpTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (BillOpTypeEnum status : values()) {
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
       "'" + "BillOpType" + "','" + "账单操作类型" + "','" + "账单操作类型" + "','1',0);";
     System.out.println(baseSql);
 
     for (BillOpTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "BillOpType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
     }
   }
 }

 