 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.BillPlaceEnterPageEnum;
import java.io.PrintStream;
 
 public enum BillPlaceEnterPageEnum
 {
   DIANCAN_PAGE("0", "点餐"), 
   DESK_PAGE("1", "餐台"), 
   PAY_PAGE("2", "结账");
 
   public static final String enumCode = "BillPlaceEnterPage";
   public static final String enumName = "客户端权限";
   private String code;
   private String desc;
 
   private BillPlaceEnterPageEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (BillPlaceEnterPageEnum status : values()) {
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
       "'" + "BillPlaceEnterPage" + "','" + "客户端权限" + "','" + "客户端权限" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (BillPlaceEnterPageEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "BillPlaceEnterPage" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

 