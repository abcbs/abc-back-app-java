 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.DishesStyleTypeEnum;
import java.io.PrintStream;
 
 public enum DishesStyleTypeEnum
 {
   XIANGCAI("xiangcai", "湘菜"), 
   CHUANCAI("chuancai", "川菜"), 
   YUECAI("yuecai", "粤菜"), 
   SUCAI("sucai", "苏菜"), 
   LUCAI("lucai", "鲁菜"), 
   MINCAI("mincai", "闽菜"), 
   ZHECAI("zhecai", "浙菜"), 
   HUICAI("huicai", "徽菜"), 
   NORTHWESTCAI("xibeicai", "西北菜"), 
   MUSLIM("qingzhen", "清真"), 
   JAPANESESTYLE("rishi", "日式"), 
   KOREAN("hanshi", "韩式"), 
   WESTERN("xishi", "西式"), 
   FRENCH("fashi", "法式"), 
   SOUTHEASTASIA("dongnanya", "东南亚"), 
   BEIJINGCAI("beijingcai", "北京菜"), 
   SHANGHAICAI("shanghaicai", "上海菜"), 
   OTHER("qita", "其他");
 
   public static final String enumCode = "DishesStyleType";
   public static final String enumName = "菜系类型";
   private String code;
   private String desc;
 
   private DishesStyleTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (DishesStyleTypeEnum status : values()) {
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
       "'" + "DishesStyleType" + "','" + "菜系类型" + "','" + "菜系类型" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (DishesStyleTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "DishesStyleType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

