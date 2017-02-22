 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.core.common.BackUpTypeEnum;
import com.ndlan.canyin.core.common.InnerUserNameEnum;
import com.ndlan.canyin.core.common.SysDataTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.Identities;
import com.ndlan.canyin.core.utils.PropertyConfig;
import com.ndlan.canyin.core.utils.VersionUpdateUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
 public class DataBaseExportUtil
 {
   private static String url = "";
   private static String username = "root";
   private static String psw = "000000";
   static Connection conn = null;
 
   public static Date recoverStartTime = null;
   public static Date recoverEndTime = null;
 
   public static Map<String, String> tabInsterMap = new HashMap();
 
   public static List<String> tabInsterNameList = new ArrayList();
 
   private static final String[] delTable = { "cm_busi_log", "cm_data_log", "cm_printer_log", "cm_printer_task", "cm_sys_log", "cm_transaction", "cm_transfer_carrier" };
 
   private static final String[] exBackUpTable = { "cm_busi_log", "cm_data_log", "cm_printer_log", "cm_printer_task", "cm_sys_log", "ocm_canyin_version", "md_base_code", "md_base_code_item", "md_region", "cm_authority_module", "cm_transaction", "cm_transfer_carrier" };
 
   private static final String[] exCloudBackUpTable = { "cm_diner_bill_log", "cm_busi_log", "cm_data_log", "cm_printer_log", "cm_printer_task", "cm_sys_log", "ocm_canyin_version", "md_base_code", "md_base_code_item", "md_region", "cm_authority_module", "cm_transaction", "cm_transfer_carrier" };
 
   private static final String[] exRecoverTable = { "cm_busi_log", "cm_data_log", "cm_printer_log", "cm_printer_task", "cm_sys_log", "ocm_canyin_version", "md_base_code", "md_base_code_item", "md_region", "cm_authority_module", "cm_transaction", "cm_transfer_carrier" };
 
   private static final String[] noCreateByTable = { "cm_role_auth", "cm_role_table_area", "cm_role_user" };
 
   private static final String[] deleteExTable = { "hibernate_sequences", 
     "cm_sys_version_setting", "ocm_canyin_version", 
     "md_base_code", "md_base_code_item", 
     "md_region", "cm_authority_module", "cm_role_auth", "cm_role_user", "cm_user_role", "cm_role_table_area" };
   private static final String EMPTY = "CANYIN_EMPTY";
   private static final String NULL = "CANYIN_NULL";
   private static final int pageSize = 1000;
 
   public static void init()
   {
     if (StringUtils.isEmpty(url))
       try
       {
         PropertyConfig propertyConfig = new PropertyConfig("application.properties");
         username = propertyConfig.getProperty("jdbc.username");
         psw = propertyConfig.getProperty("jdbc.password");
         url = propertyConfig.getProperty("jdbc.url");
       } catch (Exception e) {
         e.printStackTrace();
       }
   }
 
   private static Connection getConnection()
   {
     try {
       init();
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(url, username, psw);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return conn;
   }
   public static boolean insert(String sql) {
     if ((StringUtils.isEmpty(sql)) || 
       ("null".equals(sql)))
     {
       return true;
     }
     long startTime = new Date().getTime();
     Connection conn = getConnection();
     PreparedStatement stmt = null;
     try {
       stmt = conn.prepareStatement(sql);
       stmt.execute();
     } catch (SQLException e) {
       e.printStackTrace();
       System.out.println(sql);
       return false;
     } finally {
       try {
         if (stmt != null) stmt.close();
         if (conn != null) conn.close(); 
       }
       catch (SQLException e) {
         e.printStackTrace();
         return false;
       }
     }
     long endTime = new Date().getTime();
     return true;
   }
 
   public static void batchInsert(List<String> sql)
   {
     Connection conn = getConnection();
     Statement stmt = null;
     try {
       conn.setAutoCommit(false);
       stmt = conn.createStatement(1005, 
         1007);
       for (int x = 0; x < sql.size(); x++) {
         stmt.execute((String)sql.get(x));
       }
       conn.commit();
     } catch (SQLException e) {
       e.printStackTrace();
       try
       {
         if (stmt != null) stmt.close();
         if (conn != null) conn.close(); 
       }
       catch (SQLException e1) {
         e1.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (stmt != null) stmt.close();
         if (conn != null) conn.close(); 
       }
       catch (SQLException e) {
         e.printStackTrace();
       }
     }
   }
 
   public static void delete(String table) {
     Connection conn = getConnection();
     PreparedStatement stmt = null;
     try {
       stmt = conn.prepareStatement("DELETE FROM " + table);
       stmt.execute();
     } catch (SQLException e) {
       e.printStackTrace();
       try
       {
         if (stmt != null) stmt.close();
         if (conn != null) conn.close(); 
       }
       catch (SQLException e1) {
         e1.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (stmt != null) stmt.close();
         if (conn != null) conn.close(); 
       }
       catch (SQLException e) {
         e.printStackTrace();
       }
     }
   }
 
   public static void deleteSql(String sql) {
     Connection conn = getConnection();
     PreparedStatement stmt = null;
     try {
       conn.setAutoCommit(false);
       stmt = conn.prepareStatement(sql);
       stmt.execute();
       conn.commit();
     } catch (SQLException e) {
       e.printStackTrace();
       try
       {
         if (stmt != null) stmt.close();
         if (conn != null) conn.close(); 
       }
       catch (SQLException e2) {
         e2.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (stmt != null) stmt.close();
         if (conn != null) conn.close(); 
       }
       catch (SQLException e) {
         e.printStackTrace();
       }
     }
   }
 
   public static void execute(String sql) {
     Connection conn = getConnection();
     Statement stmt = null;
     try {
       stmt = conn.createStatement();
       stmt.execute(sql);
     }
     catch (SQLException e) {
       e.printStackTrace();
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e3) {
         e3.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
   }
 
   public static int getProcess()
   {
     if (tabInsterNameList == null)
     {
       return 0;
     }
     if (tabInsterNameList != null)
     {
       return tabInsterNameList.size();
     }
     return 0;
   }
 
   public static String getProcessName()
   {
     if ((tabInsterNameList == null) || (tabInsterNameList.size() == 0))
     {
       return "开始";
     }
     if ((tabInsterNameList != null) && (tabInsterNameList.size() > 0))
     {
       return (String)tabInsterNameList.get(tabInsterNameList.size() - 1);
     }
     return "开始";
   }
 
   public static void recoverDataBase(String dbPath, String backupType, String synVersionId, String version)
     throws Exception
   {
     tabInsterMap = new HashMap();
     tabInsterNameList = new ArrayList();
 
     recoverStartTime = new Date();
 
     File file = new File(dbPath);
     if (file.isDirectory()) {
       List filelist = getXMLFile(dbPath, file.list());
       if ((filelist != null) && (filelist.size() > 0))
       {
         createRecoverInitProc();
         usingProcedure("recoverProc", "1");
         String table;
         for (int k = 0; k < filelist.size(); k++)
         {
           File eachfile = new File(dbPath + "\\" + (String)filelist.get(k));
           String name = eachfile.getName();
           System.out.println("name:" + name);
           tabInsterNameList.add(name);
           table = name.replace(".xml", "");
 
           if (table.indexOf("-") > 0)
           {
             table = table.substring(0, table.indexOf("-"));
           }
           if (isHasRecoverExTable(table))
             continue;
           String sqlStr = toGet(eachfile, table);
           if ((tabInsterMap.get(table) == null) || (TrueFalseEnum.FALSE.getCode().equals(tabInsterMap.get(table))))
           {
             deleteSql("DELETE FROM " + table);
             tabInsterMap.put(table, TrueFalseEnum.TRUE.getCode());
           }
           boolean isInsertSuccess = insert(sqlStr);
           if (isInsertSuccess)
           {
             continue;
           }
         }
 
         dropProcedure("recoverProc");
 
         int name =   delTable.length; 
         for (int eachfile = 0; eachfile < name; eachfile++) { 
        	 String t = delTable[eachfile];
 
           delete(t);
         }
 
         List<String> tables = getAllTable();
         for (String e : tables)
         {
           if ((tabInsterMap.get(e) != null) || (isHasRecoverExTable(e)))
             continue;
           System.out.println("上次备份没有此表数据，删除所有数据！" + e);
           deleteSql("DELETE FROM " + e);
         }
 
         int versionInt = Integer.valueOf(version.replaceAll("\\.", "").trim()).intValue();
 
         insertHideEmp();
         Map restIdMap = querySql2("select ce.rest_id as rest_id from cm_employee as ce where  ce.sysdata_type = 0");
         String restId = (String)restIdMap.get("rest_id");
 
         Map versionMap = querySql2(" select * from cm_sys_version ;");
         if (versionMap == null)
         {
           String versionSql = "INSERT INTO cm_sys_version (ver_id ,name,rest_id,ver_desc,ver_number,ver_time,create_by,create_time,update_by,update_time,version,run_data_sql_version,run_table_sql_version)VALUES (uuid(), '餐饮系统" + 
             version + "版本', '" + restId + "', '餐饮系统" + version + "版本',  '" + version + "', NOW(), null,NOW(),null,NOW(),'0','" + versionInt + "','" + "230" + "') ";
           insert(versionSql);
         }
         else
         {
           String versionSql = "update cm_sys_version set run_table_sql_version = '230',run_data_sql_version='" + versionInt + "',rest_id ='" + restId + "' where ver_id = '" + (String)versionMap.get("ver_id") + "';";
           insert(versionSql);
         }
 
         if (!"cloud".equals(backupType))
         {
           String uuid = Identities.uuid2();
           insert("update cm_restaurant set syn_database_status = '1' ,syn_version_id='" + uuid + "' where  is_band_cloud_account='1' and (syn_database_status = '2' or syn_database_status = '5'  );");
           insert("update cm_restaurant set syn_database_status = '6' ,syn_version_id='" + uuid + "' where  is_band_cloud_account='1' and (syn_database_status = '3' or syn_database_status = '4'  );");
           insert("update cm_restaurant set syn_database_status = '1' , syn_data_time = null,syn_data_complete_time=null,is_band_cloud_account='0',cloud_username=null,cloud_password=null,syn_version_id=null where  is_band_cloud_account='0' ;");
         }
         else
         {
           insert("update cm_restaurant set syn_version_id='" + synVersionId + "' ;");
         }
 
         VersionUpdateUtil.update(1);
 
         for (String e : delTable)
         {
           deleteSql("DELETE FROM " + e);
         }
         createRecoverEndProc();
         usingProcedure("recoverProc", "1");
         dropProcedure("recoverProc");
 
         recoverEndTime = new Date();
       }
     }
     tabInsterMap = null;
   }
 
   private static void createRecoverInitProc()
   {
     String proc = "create procedure recoverProc(IN no varchar(36)) \n";
     proc = proc + "BEGIN \n";
     proc = proc + "SET GLOBAL FOREIGN_KEY_CHECKS = 0; \n";
     proc = proc + "END";
     createProcedure(proc, "recoverProc");
   }
 
   private static void createRecoverEndProc()
   {
     String proc = "create procedure recoverProc(IN no varchar(36)) \n";
     proc = proc + "BEGIN \n";
     proc = proc + "SET GLOBAL FOREIGN_KEY_CHECKS = 1; \n";
     proc = proc + "END";
     createProcedure(proc, "recoverProc");
   }
 
   private static void createRecoverProc(List<String> sqlList)
   {
     String proc = "create procedure recoverProc(IN no varchar(36)) \n";
     proc = proc + "BEGIN \n";
     proc = proc + "SET FOREIGN_KEY_CHECKS=0; \n";
     for (String eachLine : sqlList)
     {
       proc = proc + eachLine + "\n";
     }
     proc = proc + "END";
     createProcedure(proc, "recoverProc");
   }
 
   public static void createRecoverProc(String sql)
   {
     String proc = "create procedure recoverProc(IN no varchar(36)) \n";
     proc = proc + "BEGIN \n";
     proc = proc + "SET FOREIGN_KEY_CHECKS=0; \n";
     proc = proc + sql;
     proc = proc + "END";
     createProcedure(proc, "recoverProc");
   }
 
   public static List<String> getXMLFile(String updatePath, String[] filelist)
   {
     List list = new ArrayList();
 
     if ((filelist != null) && (filelist.length > 0))
     {
       for (int i = 0; i < filelist.length; i++)
       {
         File lastestfile = new File(updatePath + "\\" + filelist[i]);
         String name = lastestfile.getName();
         String dot = name.substring(name.lastIndexOf(".") + 1, name.length());
         if (!dot.equals("xml"))
           continue;
         list.add(filelist[i]);
       }
     }
 
     return list;
   }
 
   private static boolean isHasCloudBackUpExTable(String tableName)
   {
     for (String t : exCloudBackUpTable)
     {
       if (t.equals(tableName.trim().toLowerCase()))
       {
         return true;
       }
     }
     return false;
   }
 
   private static boolean isHasBackUpExTable(String tableName) {
     for (String t : exBackUpTable)
     {
       if (t.equals(tableName.trim().toLowerCase()))
       {
         return true;
       }
     }
     return false;
   }
 
   private static boolean isHasRecoverExTable(String tableName) {
     for (String t : exRecoverTable)
     {
       if (t.equals(tableName.trim().toLowerCase()))
       {
         return true;
       }
     }
     return false;
   }
 
   public static List<String> getAllTable()
     throws Exception
   {
     List tables = new ArrayList();
     DatabaseMetaData dbmd = getConnection().getMetaData();
     ResultSet rs = dbmd.getTables(null, null, "%", null);
     while (rs.next())
     {
       String tableName = rs.getString(3);
       tables.add(tableName);
     }
 
     return tables;
   }
 
   public static void writeDataBase(String path, String backUpType)
     throws Exception
   {
     try
     {
       List tables = new ArrayList();
       DatabaseMetaData dbmd = getConnection().getMetaData();
       ResultSet rs = dbmd.getTables(null, null, "%", null);
       while (rs.next())
       {
         String tableName = rs.getString(3);
         if (BackUpTypeEnum.LOCAL.getCode().equals(backUpType))
         {
           if (!isHasBackUpExTable(tableName))
           {
             tables.add(tableName);
             toWrite(path, tableName);
           }
         }
         if (!BackUpTypeEnum.CLOUD.getCode().equals(backUpType))
           continue;
         if (isHasCloudBackUpExTable(tableName))
           continue;
         tables.add(tableName);
         toWrite(path, tableName);
       }
 
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 
   public static Map querySql2(String sql)
   {
     Connection conn = getConnection();
     Statement stmt = null;
     try {
       stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(sql);
       ResultSetMetaData md = rs.getMetaData();
       int columnCount = md.getColumnCount();
       Map map = null;
       if (rs.next()) {
         map = new HashMap();
         for (int i = 1; i <= columnCount; i++) {
           map.put(md.getColumnName(i), rs.getObject(i));
         }
       }
 
       Map localMap1 = map;
       return localMap1;
     } catch (SQLException e) {
       e.printStackTrace();
     } finally {
       try {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return null;
   }
 
   public static Map queryEmployee(String sysdataYype) {
     return querySql2("select * from cm_employee where sysdata_type='" + sysdataYype + "' ");
   }
 
   public static void insertHideEmp()
   {
     Map hideEmp = queryEmployee(SysDataTypeEnum.HIDE.getCode());
     if (hideEmp == null)
     {
       Map defaultEmp = queryEmployee(SysDataTypeEnum.DEFAULT.getCode());
       String restId = (String)defaultEmp.get("rest_id");
 
       String sql = "INSERT INTO cm_employee (emp_id,rest_id,emp_num,login_username,login_password,name,job_status,salt,plain_password,sysdata_type,create_by,create_time,update_by,update_time,version) VALUES (uuid(), '" + 
         restId + "', 's0000','super', '9608aa5b3d0da7b6056adcb98406c74da98d6b1a', '超级管理员','1', '896a68550d6993c1', 'G2','2', '" + defaultEmp.get("emp_id") + "',NOW(),'" + defaultEmp.get("emp_id") + "',NOW(),'0');";
       insert(sql);
       sql = "INSERT INTO cm_employee (emp_id,rest_id,emp_num,login_username,login_password,name,job_status,salt,plain_password,sysdata_type,create_by,create_time,update_by,update_time,version) VALUES (uuid(), '" + 
         restId + "', 's0001','vister', '08a87fd0ef4860111df7d9a63f5bed3beaac5b14', '游客','1', '4093776cdb2d5e11', 'vister','2', '" + defaultEmp.get("emp_id") + "',NOW(),'" + defaultEmp.get("emp_id") + "',NOW(),'0');";
       insert(sql);
       sql = "INSERT INTO cm_employee (emp_id,rest_id,emp_num,login_username,login_password,name,job_status,salt,plain_password,sysdata_type,create_by,create_time,update_by,update_time,version) VALUES (uuid(), '" + 
         restId + "', 's0002','test', 'b1cc0a10ea37cd304c7525c4fdcd301cfb64770e', '测试账户','1', 'a3dceab09c2d28af', 'test','2', '" + defaultEmp.get("emp_id") + "',NOW(),'" + defaultEmp.get("emp_id") + "',NOW(),'0');";
       insert(sql);
       sql = "INSERT INTO cm_employee (emp_id,rest_id,emp_num,login_username,login_password,name,job_status,salt,plain_password,sysdata_type,create_by,create_time,update_by,update_time,version) VALUES (uuid(), '" + 
         restId + "', 's0003','selforder', '9608aa5b3d0da7b6056adcb98406c74da98d6b1a', '自助点餐账户','1', '896a68550d6993c1', 'G2','2', '" + defaultEmp.get("emp_id") + "',NOW(),'" + defaultEmp.get("emp_id") + "',NOW(),'0');";
       insert(sql);
     }
   }
 
   public static void main(String[] args)
     throws Exception
   {
     String name = "'哈\\哈\\\\";
 
     System.out.println(StringFilter(name));
   }
 
   public static String getAllSql(List<String> list)
   {
     String head = null;
     String allMid = "";
     for (String each : list)
     {
       if (head == null)
       {
         head = each.substring(0, each.indexOf("values") + 6);
       }
       allMid = allMid + "," + each.substring(each.indexOf("values") + 6, each.length());
     }
     String sqlSql = head + allMid.replaceFirst(",", "");
     return sqlSql;
   }
 
   public static String StringFilter(String str) throws PatternSyntaxException
   {
     String regex = "[`^''//[//]/\\……&‘”“’]";
     str = str.replaceAll("\\\\", "");
 
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(str);
     StringBuffer sb = new StringBuffer();
     while (matcher.find()) {
       matcher.appendReplacement(sb, "\\\\" + matcher.group(0));
     }
     matcher.appendTail(sb);
     return sb.toString();
   }
 
   private static boolean isHasDeleteExTable(String tableName)
   {
     for (String t : deleteExTable)
     {
       if (t.equals(tableName.trim().toLowerCase()))
       {
         return true;
       }
     }
     return false;
   }
 
   private static boolean isHasNoCreateByTable(String tableName) {
     for (String t : noCreateByTable)
     {
       if (t.equals(tableName.trim().toLowerCase()))
       {
         return true;
       }
     }
     return false;
   }
 
   public static void deleteTestData() throws Exception {
     List sqlList = new ArrayList();
     DatabaseMetaData dbmd = getConnection().getMetaData();
     ResultSet rs = dbmd.getTables(null, null, "%", null);
     while (rs.next())
     {
       String tableName = rs.getString(3);
       if (isHasDeleteExTable(tableName))
         continue;
       String sql = "delete t.*  from " + tableName + " t , cm_employee ce where t.create_by = ce.emp_id and ce.login_username = '" + InnerUserNameEnum.TEST.getCode() + "';";
       sqlList.add(sql);
       createRecoverProc(sqlList);
       usingProcedure("recoverProc", "1");
       dropProcedure("recoverProc");
     }
   }
 
   public static String toGet(File eachfile, String table)
     throws Exception
   {
     StringBuffer sqlStr = new StringBuffer();
     String head = null;
     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     DocumentBuilder db = factory.newDocumentBuilder();
     Document doc = db.parse(eachfile);
     Element elmtInfo = doc.getDocumentElement();
     NodeList nodes = elmtInfo.getChildNodes();
     for (int i = 0; i < nodes.getLength(); i++)
     {
       Node RECORD = nodes.item(i);
       if ((RECORD.getNodeType() != 1) || (!RECORD.getNodeName().toLowerCase().equals("record")))
         continue;
       NodeList ns = RECORD.getChildNodes();
       String colSql = "";
       String valueSql = "";
       for (int j = 0; j < ns.getLength(); j++)
       {
         Node col = ns.item(j);
         String nodeName = col.getNodeName();
         if ((RECORD.getNodeType() != 1) || (nodeName.toLowerCase().equals("#text")))
           continue;
         colSql = colSql + "," + nodeName;
         if ((col.getTextContent() == null) || (col.getTextContent().isEmpty()))
         {
           valueSql = valueSql + "," + null;
         }
         else if (col.getTextContent().trim().equals("CANYIN_EMPTY"))
         {
           valueSql = valueSql + ", ''";
         }
         else if (col.getTextContent().trim().equals("CANYIN_NULL"))
         {
           if (nodeName.equalsIgnoreCase("syn_version"))
           {
             valueSql = valueSql + ", 0";
           }
           else
           {
             valueSql = valueSql + ", null";
           }
         }
         else
         {
           NodeList nodeList = col.getChildNodes();
 
           for (int k = 0; k < nodeList.getLength(); k++) {
             Node node = nodeList.item(k);
             if (node.getNodeType() == 4) {
               CDATASection cdataNode = (CDATASection)node;
               valueSql = valueSql + ",'" + editPicPath(table, nodeName, StringFilter(cdataNode.getData())) + "'";
 
               break;
             }
             if (node.getNodeName().toLowerCase().equals("#text")) {
               valueSql = valueSql + ",'" + editPicPath(table, nodeName, StringFilter(col.getTextContent())) + "'";
 
               break;
             }
           }
         }
 
       }
 
       colSql = colSql.replaceFirst(",", "");
       valueSql = valueSql.replaceFirst(",", "");
 
       if (head == null)
       {
         head = " insert into " + table + " ( " + colSql + ") values ";
       }
       String midSql = ",( " + valueSql + ")";
       sqlStr.append(midSql);
     }
 
     return head + sqlStr.toString().replaceFirst(",", "");
   }
 
   private static String editPicPath(String table, String col, String value)
   {
     if ("cm_restaurant_pic".equals(table))
     {
       if (("pic_url".equals(col)) && (value.indexOf("upload") < 0))
       {
         value = "/upload/restaurant/" + value;
       }
     }
     else if ("cm_table_pic".equals(table))
     {
       if (("pic_url".equals(col)) && (value.indexOf("upload") < 0))
       {
         value = "/upload/table/" + value;
       }
     }
     else if ("cm_employee".equals(table))
     {
       if (("job_pic".equals(col)) && (value.indexOf("upload") < 0))
       {
         value = "/upload/employee/" + value;
       }
     }
     else if ("cm_printer".equals(table))
     {
       if (("foot_image_url".equals(col)) && (value.indexOf("upload") < 0))
       {
         value = "/upload/printer/" + value;
       }
     }
     return value;
   }
 
   public static void toWrite(String path, String table)
     throws Exception
   {
     String sql = "";
 
     sql = "select * from " + table;
 
     int size = getSize(table);
     int pageNum = (int)Math.ceil(size / 1000.0D);
 
     for (int j = 0; j < pageNum; j++) {
       int begin = j * 1000;
       String limitSql = sql + " limit " + begin + "," + 1000 + ";";
       String tableName = table + "-" + j;
       List list = querySql(limitSql);
 
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document document = builder.newDocument();
       Element root = document.createElement("RECORDS");
       document.appendChild(root);
 
       for (int i = 0; i < list.size(); i++)
       {
         Element RECORD = document.createElement("RECORD");
         Map<String,Object> each = (Map)list.get(i);
         for (String key : each.keySet())
         {
           Element eachEntity = document.createElement(key);
 
           CDATASection cdata = document.createCDATASection("");
           Object value = each.get(key);
 
           if (value != null)
           {
             if ((value instanceof String))
             {
               String t = (String)value;
               if (t.isEmpty())
               {
                 cdata.setData("CANYIN_EMPTY");
               }
               else
               {
                 cdata.setData(t);
               }
 
             }
             else if ((value instanceof Timestamp))
             {
               Timestamp t = (Timestamp)value;
 
               cdata.setData(t.toString());
             }
             else if ((value instanceof Integer))
             {
               Integer t = (Integer)value;
 
               cdata.setData(t.toString());
             }
             else if ((value instanceof Double))
             {
               Double t = (Double)value;
 
               cdata.setData(t.toString());
             }
             else if ((value instanceof Float))
             {
               Float t = (Float)value;
 
               cdata.setData(t.toString());
             }
             else
             {
               cdata.setData(value.toString());
             }
 
           }
           else
           {
             cdata.setData("CANYIN_NULL");
           }
           eachEntity.appendChild(cdata);
           RECORD.appendChild(eachEntity);
         }
 
         root.appendChild(RECORD);
       }
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer transformer = tf.newTransformer();
       DOMSource source = new DOMSource(document);
       transformer.setOutputProperty("encoding", "UTF-8");
       transformer.setOutputProperty("indent", "yes");
       transformer.setOutputProperty("standalone", "yes");
 
       FileOutputStream fileOutput = new FileOutputStream(path + "\\" + tableName + ".xml");
       PrintWriter pw = new PrintWriter(new OutputStreamWriter(fileOutput, "UTF-8"));
       StreamResult result = new StreamResult(pw);
       transformer.transform(source, result);
     }
   }
 
   public static int getSize(String table)
   {
     int totalSize = 0;
     String sql = "select count(*) as totalSize from " + table;
     Connection conn = getConnection();
     Statement stmt = null;
     try {
       stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(sql);
       ResultSetMetaData md = rs.getMetaData();
       if (rs.next()) {
         Long l = (Long)rs.getObject("totalSize");
         totalSize = l.intValue();
       }
     }
     catch (SQLException e)
     {
       e.printStackTrace();
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e2) {
         e2.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return totalSize;
   }
   public static List<Map<String, Object>> querySql(String sql) {
     List list = new ArrayList();
     Connection conn = getConnection();
     Statement stmt = null;
     try {
       stmt = conn.createStatement();
       System.out.println(sql);
       ResultSet rs = stmt.executeQuery(sql);
       ResultSetMetaData md = rs.getMetaData();
       int columnCount = md.getColumnCount();
       while (rs.next()) {
         Map map = new HashMap();
         for (int i = 1; i <= columnCount; i++) {
           map.put(md.getColumnName(i), rs.getObject(i));
         }
         list.add(map);
       }
       List localList1 = list;
       return localList1;
     } catch (SQLException e) {
       e.printStackTrace();
     } finally {
       try {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return list;
   }
 
   public static void createProcedure(String myprocedure, String procedureName) {
     Connection conn = getConnection();
     Statement stmt = null;
     try {
       stmt = conn.createStatement();
       stmt.execute("drop procedure if exists " + procedureName + " ;");
       stmt.executeUpdate(myprocedure);
     } catch (SQLException e) {
       e.printStackTrace();
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e3) {
         e3.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
   }
 
   public static void usingProcedure(String procedureName, String p1) {
     Connection conn = getConnection();
     PreparedStatement stmt = null;
     try {
       stmt = conn
         .prepareCall("call " + procedureName + " ( " + p1 + " )");
       stmt.executeQuery();
     } catch (SQLException e) {
       e.printStackTrace();
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e4) {
         e4.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
   }
 
   public static void dropProcedure(String procedureName) {
     Connection conn = getConnection();
     Statement stmt = null;
     try {
       String dorpProcedure = "drop procedure if exists " + procedureName + 
         " ;";
       stmt = conn.createStatement();
       stmt.executeUpdate(dorpProcedure);
     } catch (SQLException e) {
       e.printStackTrace();
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e5) {
         e5.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (stmt != null)
           stmt.close();
         if (conn != null)
           conn.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
   }
 
   public static void executeUserDefinedProc(List<String> sqlList)
   {
     createUserDefinedProc(sqlList);
     usingProcedure("userDefinedProc", "1");
     dropProcedure("userDefinedProc");
   }
 
   private static void createUserDefinedProc(List<String> sqlList)
   {
     String proc = "create procedure userDefinedProc(IN no varchar(36)) \n";
     proc = proc + "BEGIN \n";
     proc = proc + "SET FOREIGN_KEY_CHECKS=0; \n";
     for (String eachLine : sqlList)
     {
       proc = proc + eachLine + "\n";
     }
     proc = proc + "END";
     System.out.println(proc);
     createProcedure(proc, "userDefinedProc");
   }
 
   private static void createLockTableProc(String table)
   {
     String proc = "create procedure lockProc(IN no varchar(36)) \n";
     proc = proc + "BEGIN \n";
     proc = proc + "LOCK TABLES " + table + " READ ;";
     proc = proc + "END";
     createProcedure(proc, "lockProc");
   }
 
   public static void executeLockTableProc(String table)
   {
     createLockTableProc(table);
     usingProcedure("lockProc", "1");
     dropProcedure("lockProc");
   }
 }

