 package com.ndlan.canyin.core.utils;
 
 import java.io.PrintStream;
 import java.net.URI;
 import java.net.URISyntaxException;
 import java.net.URL;
 import java.util.List;
 import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import com.ndlan.canyin.core.utils.DataBaseUtil;
import com.ndlan.canyin.core.utils.FileUtil;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.utils.VersionUpdateUtil;
 
 public class VersionUpdateUtil
 {
   public static final String currentVersion = "230";
   public static final String currentVersionStr = "2.3.0";
   public static final int opType_update = 0;
   public static final int opType_recover = 1;
 
   public static void update(int opType)
   {
     DATA_SQL_v200(opType);
     DATA_SQL_v201(opType);
     DATA_SQL_v210(opType);
     DATA_SQL_v220(opType);
     DATA_SQL_v230(opType);
   }
 
   public static void DATA_SQL_v230(int opType)
   {
     Map versionMap = DataBaseUtil.querySql2(" select * from cm_sys_version ;");
     String verNumber = (String)versionMap.get("ver_number");
     int run_data_sql_version = versionMap.get("run_data_sql_version") == null ? 0 : ((Integer)versionMap.get("run_data_sql_version")).intValue();
     int run_table_sql_version = versionMap.get("run_table_sql_version") == null ? 0 : ((Integer)versionMap.get("run_table_sql_version")).intValue();
     int version = Integer.valueOf(verNumber.replaceAll("\\.", "").trim()).intValue();
     if ((version < 230) && (run_table_sql_version >= 230) && (run_data_sql_version < 230))
     {
       DataBaseUtil.insert("update cm_sys_version set run_data_sql_version = '230',ver_number='2.3.0' , ver_desc='餐饮系统2.3.0版本' , name='餐饮系统2.3.0版本' ;");
     }
   }
 
   public static void DATA_SQL_v220(int opType)
   {
     Map versionMap = DataBaseUtil.querySql2(" select * from cm_sys_version ;");
     String verNumber = (String)versionMap.get("ver_number");
     int run_data_sql_version = versionMap.get("run_data_sql_version") == null ? 0 : ((Integer)versionMap.get("run_data_sql_version")).intValue();
     int run_table_sql_version = versionMap.get("run_table_sql_version") == null ? 0 : ((Integer)versionMap.get("run_table_sql_version")).intValue();
     int version = Integer.valueOf(verNumber.replaceAll("\\.", "").trim()).intValue();
     if ((version < 220) && (run_table_sql_version >= 220) && (run_data_sql_version < 220))
     {
       DataBaseUtil.insert("update cm_sys_version set run_data_sql_version = '220',ver_number='2.2.0' , ver_desc='餐饮系统2.2.0版本' , name='餐饮系统2.2.0版本' ;");
     }
   }
 
   public static void DATA_SQL_v210(int opType)
   {
     Map versionMap = DataBaseUtil.querySql2(" select * from cm_sys_version ;");
     String verNumber = (String)versionMap.get("ver_number");
     int run_data_sql_version = versionMap.get("run_data_sql_version") == null ? 0 : ((Integer)versionMap.get("run_data_sql_version")).intValue();
     int run_table_sql_version = versionMap.get("run_table_sql_version") == null ? 0 : ((Integer)versionMap.get("run_table_sql_version")).intValue();
     int version = Integer.valueOf(verNumber.replaceAll("\\.", "").trim()).intValue();
     if ((version < 210) && (run_table_sql_version >= 210) && (run_data_sql_version < 210))
     {
       if (opType == 0)
       {
         String sql = "update cm_restaurant set busi_hours_open = '00:00';";
         DataBaseUtil.insert(sql);
         sql = "update cm_restaurant set busi_hours_close = '23:59';";
         DataBaseUtil.insert(sql);
       }
       DataBaseUtil.insert("update cm_cashier_desk_setting set is_fastfood_bill_print = '0',is_show_module_desk='1',is_show_module_fastfood='1',is_show_module_bill='1',is_show_module_member='1',is_show_module_order='1',is_show_module_guqing='1',is_show_module_waimai='1'  where emp_id is null or emp_id = '' ;");
       DataBaseUtil.insert("update cm_sys_version set run_data_sql_version = '210',ver_number='2.1.0' , ver_desc='餐饮系统2.1.0版本' , name='餐饮系统2.1.0版本' ;");
     }
   }
 
   public static void DATA_SQL_v201(int opType)
   {
     Map versionMap = DataBaseUtil.querySql2(" select * from cm_sys_version ;");
     String verNumber = (String)versionMap.get("ver_number");
     int run_data_sql_version = versionMap.get("run_data_sql_version") == null ? 0 : ((Integer)versionMap.get("run_data_sql_version")).intValue();
     int run_table_sql_version = versionMap.get("run_table_sql_version") == null ? 0 : ((Integer)versionMap.get("run_table_sql_version")).intValue();
     int version = Integer.valueOf(verNumber.replaceAll("\\.", "").trim()).intValue();
     if ((version < 201) && (run_table_sql_version >= 201) && (run_data_sql_version < 201))
     {
       if (opType == 0)
       {
         DataBaseUtil.insert("delete from md_region where region_id = 'aca118bc-89f1-11e2-8712-080058000005';");
         DataBaseUtil.insert("delete from md_region where region_id = 'aca64e3c-89f1-11e2-8712-080058000005';");
         DataBaseUtil.insert("delete from md_region where region_id = '91db298b-438f-11e4-988a-080058000005';");
         DataBaseUtil.insert("INSERT INTO md_region   (`region_id`, `region_div`, `province_code`, `province_name`, `city_code`, `city_name`, `area_code`, `area_name`, `is_enable`, `show_seq`, `create_by`, `create_time`, `update_by`, `update_time`, `version`, `is_syn`, `syn_version`) VALUES   ('91db298b-438f-11e4-988a-080058000005', '2', '440000', '广东省', '441900', '东莞市', '441901', '东莞市', '1', 0, NULL, NULL, NULL, NULL, 0, '0', 0);");
       }
       DataBaseUtil.insert("update cm_sys_version set run_data_sql_version = '201',ver_number='2.0.1' , ver_desc='餐饮系统2.0.1版本' , name='餐饮系统2.0.1版本' ;");
     }
   }
 
   public static void DATA_SQL_v200(int opType)
   {
     Map versionMap = DataBaseUtil.querySql2(" select * from cm_sys_version ;");
     String verNumber = (String)versionMap.get("ver_number");
     int run_data_sql_version = versionMap.get("run_data_sql_version") == null ? 0 : ((Integer)versionMap.get("run_data_sql_version")).intValue();
     int run_table_sql_version = versionMap.get("run_table_sql_version") == null ? 0 : ((Integer)versionMap.get("run_table_sql_version")).intValue();
     int version = Integer.valueOf(verNumber.replaceAll("\\.", "").trim()).intValue();
 
     if ((version < 200) && (run_table_sql_version >= 200) && (run_data_sql_version < 200))
     {
       Map empMap = DataBaseUtil.querySql2(" select ce.rest_id,ce.emp_id from cm_employee as ce where  ce.sysdata_type = 0");
       String restId = (String)empMap.get("rest_id");
       String empId = (String)empMap.get("emp_id");
 
       DataBaseUtil.insert("update cm_cash_discount set belong_org='1' ;");
 
       Map discountMap = DataBaseUtil.querySql2("select * from cm_cash_discount where discount_name like '%G2%' or discount_name like '%G2%' ");
       String cr_id;
       if (discountMap == null)
       {
         String uuid = Identities.uuid2();
         List<Map<String,Object>> roleMap = DataBaseUtil.querySql("select * from cm_role ");
         String roles_array = "";
         for (Map e : roleMap)
         {
           cr_id = (String)e.get("cr_id");
           roles_array = roles_array + "," + cr_id;
         }
         roles_array = roles_array.replaceFirst(",", "");
         DataBaseUtil.insert("INSERT INTO cm_cash_discount   (ccd_id, rest_id, discount_name, main_discount, is_only_member, enable_status, roles_array, create_by, create_time, update_by, update_time, version,belong_org, is_syn, syn_version) VALUES   ('" + uuid + "', '" + restId + "', 'xxx会员折扣', 95, '0', '1', '" + roles_array + "', '" + empId + "', NOW(), '" + empId + "', NOW(), 1,'2','0','0', '0', 0);");
       }
       else
       {
         String ccd_id = (String)discountMap.get("ccd_id");
         DataBaseUtil.insert("update cm_cash_discount set belong_org='2',enable_status='1' where  ccd_id = '" + ccd_id + "';");
       }
 
       DataBaseUtil.insert("update cm_payment_type set  payment_name = '银行卡',notes='银行卡' where  payment_name = '刷卡' ;");
       DataBaseUtil.insert("update md_base_code_item  set  bci_name= '银行卡'  ,bci_desc= '银行卡'  where bci_id = 'ead5e57b931c4743acbca3b94d3b0fd8';");
 
       DataBaseUtil.insert("INSERT INTO cm_role (cr_id ,create_by,create_time,name,rest_id,update_by,update_time,version,sysdata_type,is_all_tablearea,role_type,is_stop_use, is_syn, syn_version) VALUES (uuid(), '" + empId + "', NOW(), '派送员', '" + restId + "', '" + empId + "',NOW(), '0', '0', '0', '8','0','0','0');");
 
       DataBaseUtil.insert("INSERT INTO cm_payment_type (cpt_id ,is_show,is_included_sales,notes,payment_name,rest_id,sysdata_type,enable_status,show_seq,payment_type,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(), '0', '1', '预订押金', '预订押金', '" + restId + "', '0',  '1', '99', '10', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_payment_type (cpt_id ,is_show,is_included_sales,notes,payment_name,rest_id,sysdata_type,enable_status,show_seq,payment_type,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(), '0', '1', '在线支付', '在线支付', '" + restId + "', '0',  '1', '99', '11', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("update cm_dishes_style set style_type='xiangcai' where code = 'xiangcai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='chuancai' where code = 'chuancai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='yuecai' where code = 'yuecai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='sucai' where code = 'sucai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='lucai' where code = 'lucai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='mincai' where code = 'mincai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='zhecai' where code = 'zhecai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='huicai' where code = 'huicai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='xibeicai' where code = 'xibeicai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='qingzhen' where code = 'qingzhen';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='rishi' where code = 'rishi';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='hanshi' where code = 'hanshi';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='xishi' where code = 'xishi';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='fashi' where code = 'fashi';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='dongnanya' where code = 'dongnanya';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='beijingcai' where code = 'beijingcai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='shanghaicai' where code = 'shanghaicai';");
       DataBaseUtil.insert("update cm_dishes_style set style_type='qita' where code = 'qita';");
 
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '太远', '太远', '9', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '太忙', '太忙', '9', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '不在营业时段', '不在营业时段', '9', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '暂时休息', '暂时休息', '9', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '卖光了', '卖光了', '9', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '下班了', '下班了', '9', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '没有空闲的餐台', '没有空闲的餐台', '10', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '太忙', '太忙', '10', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '不在营业时段', '不在营业时段', '10', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '暂时不接受预订', '暂时不接受预订', '10', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '没有原料', '没有原料', '10', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
       DataBaseUtil.insert("INSERT INTO cm_spe_op_reason (rea_id ,name,notes,rea_type,rest_id,sysdata_type,enable_status,create_by,create_time,update_by,update_time,version, is_syn, syn_version) VALUES (uuid(),  '下班了', '下班了', '10', '" + restId + "', '1','1', '" + empId + "',NOW(),'" + empId + "',NOW(),'0', '0', 0);");
 
       Map specialEnvMap = DataBaseUtil.querySql2("select * from cm_restaurant;");
       if (specialEnvMap != null)
       {
         String special_env = (String)specialEnvMap.get("special_env");
         if (!StringUtils.isEmpty(special_env))
         {
           String[] spa = special_env.split(",");
           if (spa != null)
           {
             String special_env_new = "";
             String[] arrayOfString1 = spa;
             int str1 =  arrayOfString1.length; 
             for (int cr_id1 = 0; cr_id1 < str1; cr_id1++) { 
            	 
            	 String e = arrayOfString1[cr_id1];
 
               if (Integer.valueOf(e).intValue() < 10)
               {
                 special_env_new = special_env_new + ",SE_0" + e;
               }
               else
               {
                 special_env_new = special_env_new + ",SE_" + e;
               }
             }
             special_env_new = special_env_new.replaceFirst(",", "");
             DataBaseUtil.insert("update cm_restaurant set special_env='" + special_env_new + "';");
           }
         }
       }
 
       DataBaseUtil.insert("update cm_employee set rest_id='" + restId + "',create_by='" + empId + "',update_by='" + empId + "' where sysdata_type = '2' ;");
       DataBaseUtil.insert("update cm_sys_version set rest_id='" + restId + "',create_by='" + empId + "',update_by='" + empId + "';");
       DataBaseUtil.insert("delete from cm_backup where rest_id <> '" + restId + "' ;");
 
       if (opType == 0)
       {
         String areaFile = null;
         try {
           areaFile = VersionUpdateUtil.class.getResource("/").toURI().getPath().replaceAll("/", "\\\\") + "sql\\db\\area.sql";
         }
         catch (URISyntaxException e) {
           e.printStackTrace();
         }
         if (areaFile != null)
         {
           areaFile = areaFile.replaceFirst("\\\\", "");
           String areaSql = FileUtil.readFileByLines(areaFile);
           DataBaseUtil.createRecoverProc(areaSql);
           DataBaseUtil.usingProcedure("recoverProc", "1");
           DataBaseUtil.dropProcedure("recoverProc");
         }
       }
       DataBaseUtil.insert("update cm_sys_version set run_data_sql_version = '200',ver_number='2.0.0' , ver_desc='餐饮系统2.0.0版本' , name='餐饮系统2.0.0版本' ;");
     }
   }
 
   public static void main(String[] args)
   {
     String areaFile = VersionUpdateUtil.class.getResource("/").getPath().replaceAll("/", "\\\\") + "sql\\db\\area.sql";
     areaFile = areaFile.replaceFirst("\\\\", "");
     System.out.println(areaFile);
   }
 }

