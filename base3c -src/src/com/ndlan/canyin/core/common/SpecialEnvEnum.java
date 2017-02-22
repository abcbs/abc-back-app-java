 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.SpecialEnvEnum;
import java.io.PrintStream;
 
 public enum SpecialEnvEnum
 {
   WIFI("SE_01", "免费WIFI"), 
   KALAOK("SE_02", "卡拉OK"), 
   GAME("SE_03", "桌游"), 
   NOSOMKING("SE_04", "有无烟区"), 
   FREESTOP("SE_05", "免费停车"), 
   TEA("SE_06", "茶楼"), 
   BAR("SE_07", "酒吧"), 
   CHESS("SE_08", "棋牌"), 
   COUNTRYSIDE("SE_09", "郊外养生"), 
   BABYCHAIR("SE_10", "宝宝椅"), 
   OPENAIR("SE_11", "露天位"), 
   OUTDORR("SE_12", "户外运动"), 
   FREEPERFORM("SE_13", "免费表演"), 
   OTHER("SE_00", "其他");
 
   public static final String enumCode = "SpecialEnv";
   public static final String enumName = "特色环境";
   private String code;
   private String desc;
 
   private SpecialEnvEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (SpecialEnvEnum status : values()) {
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
       "'" + "SpecialEnv" + "','" + "特色环境" + "','" + "特色环境" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (SpecialEnvEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "SpecialEnv" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

