 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.MemberEduEnum;
import java.io.PrintStream;
 
 public enum MemberEduEnum
 {
   GRADUATE_AND_ABOVE("1", "研究生及以上"), 
   UNDERGRADUATE("2", "本科"), 
   JUNIOR_COLLEGE("3", "大专"), 
   COMMUNITY_COLLEGE("4", "专科"), 
   HIGH_SCHOOL("5", "高中"), 
   MIDDLE_SCHOOL("6", "初中"), 
   PRIMARY_SCHOOL("7", "小学"), 
   OTHER("0", "其它");
 
   public static final String enumCode = "MemberEdu";
   public static final String enumName = "会员学历";
   private String code;
   private String desc;
 
   private MemberEduEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (MemberEduEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public static String getCode(String desc) {
     for (MemberEduEnum status : values()) {
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
       "'" + "MemberEdu" + "','" + "会员学历" + "','" + "会员学历" + "','1',0);";
     System.out.println(baseSql);
     EnumUtil.insert(baseSql);
     for (MemberEduEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "MemberEdu" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

