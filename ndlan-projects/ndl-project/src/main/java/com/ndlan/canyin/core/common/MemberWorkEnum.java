 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.MemberWorkEnum;
import java.io.PrintStream;
 
 public enum MemberWorkEnum
 {
   OFFICIAL("1", "公务员"), 
   SALES("2", "市场销售"), 
   ADMIN_ASSISTANT("3", "行政助理"), 
   ADMINISTRATIVE_STAFF("4", "中高级管理人员"), 
   GENERAL_STAFF("5", "普通员工"), 
   INDIVIDUAL_HOUSEHOLD("6", "个体户"), 
   UNEMPLOYED("7", "无业"), 
   STUDENT("8", "学生"), 
   OTHER("0", "其它");
 
   public static final String enumCode = "MemberWork";
   public static final String enumName = "会员职业";
   private String code;
   private String desc;
 
   private MemberWorkEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (MemberWorkEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
   public static String getCode(String desc) {
     for (MemberWorkEnum status : values()) {
       if (status.getDesc().equalsIgnoreCase(desc)) {
         return status.getCode();
       }
     }
     return null;
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
       "'" + "MemberWork" + "','" + "会员职业" + "','" + "会员职业" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (MemberWorkEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "MemberWork" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

