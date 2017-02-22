 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.SynDatabaseStatusEnum;
import java.io.PrintStream;
 
 public enum SynDatabaseStatusEnum
 {
   INIT("1", "创建"), 
   SYNING("2", "同步中"), 
   CLOUD_SYNCOMPLETE("3", "云端同步完成"), 
   ALL_SYNCOMPLETE("4", "全部同步完成"), 
   CLOUD_SYNING("5", "云端同步中"), 
   VERSION_DIFFERENT("6", "同步版本不一致"), 
   ERROR("9", "同步数据异常");
 
   public static final String enumCode = "SynDatabaseStatus";
   public static final String enumName = "云餐厅数据同步状态";
   private String code;
   private String desc;
 
   private SynDatabaseStatusEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (SynDatabaseStatusEnum status : values()) {
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
       "'" + "SynDatabaseStatus" + "','" + "云餐厅数据同步状态" + "','" + "云餐厅数据同步状态" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (SynDatabaseStatusEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "SynDatabaseStatus" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

