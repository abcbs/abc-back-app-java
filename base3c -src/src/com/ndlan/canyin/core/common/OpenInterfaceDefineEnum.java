 package com.ndlan.canyin.core.common;
 
 import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.common.OpenInterfaceDefineEnum;
import java.io.PrintStream;
 
 public enum OpenInterfaceDefineEnum
 {
   BASIC_AD("BASIC_AD", "获取全部菜肴信息"), 
   BASIC_AC("BASIC_AC", "获取菜肴全部分类信息"), 
   BASIC_AU("BASIC_AU", "获取菜肴全部计量单位信息"), 
   BASIC_AA("BASIC_AA", "获取全部忌口信息"), 
   BASIC_A_TASTE("BASIC_A_TASTE", "获取全部口味信息"), 
   BASIC_A_PUNGENTLEVEL("BASIC_A_PUNGENTLEVEL", "获取全部辣度级别信息"), 
   BASIC_AB("BASIC_AB", "获取全部退菜原因信息"), 
   BASIC_AT("BASIC_AT", "获取全部餐台信息"), 
   BASIC_AT_O("BASIC_AT_O", "获取全部允许开台的餐台信息"), 
 
   LOGIN_LI("LOGIN_LI", "登录"), 
   LOGIN_LI_S("LOGIN_LI_S", "静默登录"), 
   LOGIN_LO("LOGIN_LO", "登出"), 
 
   TABLE_OT("TABLE_OT", "开台"), 
   TABLE_CT("TABLE_CT", "转台"), 
   TABLE_MT("TABLE_MT", "并台"), 
   TABLE_RT("TABLE_RT", "撤台"), 
 
   DISH_TD("DISH_TD", "点菜"), 
   DISH_AD("DISH_AD", "加菜"), 
   DISH_BD("DISH_BD", "退菜"), 
   DISH_PD("DISH_PD", "催菜"), 
   DISH_PD_A("DISH_PD_A", "整桌催菜"), 
 
   BILL_FBT("BILL_FBT", "查询账单"), 
 
   EMPL_MPW("EMPL_MPW", "修改密码");
 
   public static final String enumCode = "interfaceDefine";
   public static final String enumName = "开放接口定义";
   private String code;
   private String desc;
 
   private OpenInterfaceDefineEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (OpenInterfaceDefineEnum status : values()) {
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
     String baseSQl = "insert into md_base_code (bc_id,bc_code,bc_name,bc_desc,is_enable,version) VALUES ( '" + uuid + "' , " + 
       "'" + "interfaceDefine" + "','" + "开放接口定义" + "','" + "开放接口定义" + "','1',0);";
     System.out.println(baseSQl);
     EnumUtil.insert(baseSQl);
     for (OpenInterfaceDefineEnum status : values()) {
       String sub_uuid = Identities.uuid2();
       String itemSql = "insert into md_base_code_item (bci_id,bc_id,bc_code,bci_code,bci_name,bci_desc,is_enable,version) VALUES ( '" + 
         sub_uuid + "' , '" + uuid + "' ,'" + "interfaceDefine" + "', '" + status.getCode() + "','" + status.getDesc() + "','" + status.getDesc() + "','1',0); ";
       System.out.println(itemSql);
       EnumUtil.insert(itemSql);
     }
   }
 }

