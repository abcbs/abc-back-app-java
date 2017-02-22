 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.BillTypeEnum;
import java.io.PrintStream;
 
 public enum BillTypeEnum
 {
   NORMAL_BILL("1", "普通账单"), 
   WAIMAI_BILL("2", "外卖账单"), 
   ZIZHU_BILL("3", "终端自助结账账单"), 
   KUAICAN_BILL("4", "快餐账单"), 
   SELFORDER_BILL("5", "自助点餐账单"),
   FELLIN_BILL("6", "排队账单");
   public static final String enumCode = "BillType";
   public static final String enumName = "账单类型";
   private String code;
   private String desc;
 
   private BillTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (BillTypeEnum status : values()) {
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
       "'" + "BillType" + "','" + "账单类型" + "','" + "账单类型" + "','1',0);";
     System.out.println(baseSQl);
     EnumUtil.insert(baseSQl);
     for (BillTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "BillType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

 