 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.SpecialStyleEnum;
import java.io.PrintStream;
 
 public enum SpecialStyleEnum
 {
   CHAFINGDISH("1", "火锅店"), 
   BARBECUE("2", "烧烤店"), 
   PRIVATEHOME("3", "私房菜"), 
   SEAFOOD("4", "海鲜酒楼"), 
   EUROPEANFOOD("5", "西餐厅"), 
   FASTFOOD("6", "快餐自助"), 
   OTHER("0", "其他");
 
   public static final String enumCode = "SpecialStyle";
   public static final String enumName = "特色风格";
   private String code;
   private String desc;
 
   private SpecialStyleEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (SpecialStyleEnum status : values()) {
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
       "'" + "SpecialStyle" + "','" + "特色风格" + "','" + "特色风格" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (SpecialStyleEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "SpecialStyle" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

