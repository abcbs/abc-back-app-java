 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.RegOriginEunm;
import java.io.PrintStream;
 
 public enum RegOriginEunm
 {
   REST_WAITER("1", "餐厅服务员注册"), 
   MEMBER_REST("2", "会员餐厅内注册"), 
   MEMBER_NET("3", "网上注册"), 
   OTHER("0", "其他来源");
 
   public static final String enumCode = "RegOrigin";
   public static final String enumName = "注册来源";
   private String code;
   private String desc;
 
   private RegOriginEunm(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (RegOriginEunm status : values()) {
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
       "'" + "RegOrigin" + "','" + "注册来源" + "','" + "注册来源" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (RegOriginEunm status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "RegOrigin" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

