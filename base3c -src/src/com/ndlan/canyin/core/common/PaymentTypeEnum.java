 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import java.io.PrintStream;
 
 public enum PaymentTypeEnum
 {
   CASH("1", "现金"), 
   CARD("2", "银行卡"), 
   MEMBER_CARD("3", "会员卡支付"), 
   HOTEL_CREDIT("4", "酒店挂账"), 
   TEAM_CREDIT("5", "团队挂账"), 
   RESTAURANT_CREDIT("6", "餐厅挂账"), 
   COUPON("7", "优惠券/码支付"), 
   CHEQUE("8", "支票"), 
   WEB_SITE_MEMBER("9", "网站会员"), 
   PREPAY("10", "预订押金"), 
   WEB_PAY("11", "在线支付"),
   WEB_Alipay("12", "支付宝支付"), 
   WEB_WeChat("13", "微信支付"), 
   WEB_Credit("14", "刷卡支付"), 
   WEB_BaiDu("15", "百度钱包支付"),
   WEB_QQ("16", "QQ钱包支付"), 
   OTHER("0", "其他支付");
 
   public static final String enumCode = "PaymentType";
   public static final String enumName = "付款方式";
   private String code;
   private String desc;
 
   private PaymentTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (PaymentTypeEnum status : values()) {
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
       "'" + "PaymentType" + "','" + "付款方式" + "','" + "付款方式" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (PaymentTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "PaymentType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

