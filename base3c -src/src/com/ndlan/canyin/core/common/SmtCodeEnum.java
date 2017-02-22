 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.SmtCodeEnum;
import java.io.PrintStream;
 
 public enum SmtCodeEnum
 {
   RECHARGE("0", "充值"), 
   CONSUM("1", "会员消费"), 
   BALANCE("2", "余额不足"), 
   LOSS_REGISTER("3", "挂失"), 
   BIRTHDAY("4", "生日短信"), 
   NEWMEMBER("10", "新会员"), 
   RECHARGE_MARKETING("11", "充值"), 
   CONSUM_MARKETING("12", "消费"), 
   FESTIVAL_MARKETING("13", "节假日"), 
   BIRTHDAY_MARKETING("14", "生日促销"), 
   AWAKEN_MARKETING("15", "唤醒促销");
 
   public static final String enumCode = "SmtCode";
   public static final String enumName = "短信模板编码";
   private String code;
   private String desc;
 
   private SmtCodeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (SmtCodeEnum status : values()) {
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
       "'" + "SmtCode" + "','" + "短信模板编码" + "','" + "短信模板编码" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (SmtCodeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "SmtCode" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

