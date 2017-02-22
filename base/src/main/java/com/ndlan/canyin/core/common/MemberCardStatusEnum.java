 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.MemberCardStatusEnum;
import java.io.PrintStream;
 
 public enum MemberCardStatusEnum
 {
   NORMAL("1", "正常"), 
   STOP("2", "停用"), 
   LOSS_REGISTER("3", "挂失"), 
   WAIT("4", "待发卡"), 
   CANCEL("5", "退卡");
 
   public static final String enumCode = "MemberCardStatus";
   public static final String enumName = "会员卡状态";
   private String code;
   private String desc;
 
   private MemberCardStatusEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (MemberCardStatusEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public static String getCode(String desc) {
     for (MemberCardStatusEnum status : values()) {
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
       "'" + "MemberCardStatus" + "','" + "会员卡状态" + "','" + "会员卡状态" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (MemberCardStatusEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "MemberCardStatus" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

