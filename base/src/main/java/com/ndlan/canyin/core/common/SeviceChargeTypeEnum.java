 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.SeviceChargeTypeEnum;
import java.io.PrintStream;
 
 public enum SeviceChargeTypeEnum
 {
   SCALE("1", "按比例", "%"), 
   TIME("2", "按时间", "元/5分钟（不足5分钟不计价）"), 
   FIXED("3", "固定", "元"), 
   PEOPLE("4", "按人数", "元/人");
 
   public static final String enumCode = "SeviceChargeType";
   public static final String enumName = "服务费类型";
   private String code;
   private String desc;
   private String appendix;
 
   private SeviceChargeTypeEnum(String code, String desc, String appendix) { this.code = code;
     this.desc = desc;
     this.appendix = appendix; }
 
   public static String getDesc(String code)
   {
     for (SeviceChargeTypeEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public static String getAppendix(String code) {
     for (SeviceChargeTypeEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getAppendix();
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
       "'" + "SeviceChargeType" + "','" + "服务费类型" + "','" + "服务费类型" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (SeviceChargeTypeEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "SeviceChargeType" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.appendix + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 
   public void setAppendix(String appendix) {
     this.appendix = appendix;
   }
 
   public String getAppendix() {
     return this.appendix;
   }
 }

