 package com.ndlan.canyin.core.utils;
 
 import com.ndlan.canyin.core.common.SysDataTypeEnum;
import com.ndlan.canyin.core.utils.PropertyConfig;

 import java.io.File;
 import java.io.PrintStream;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.SQLException;
 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
import org.apache.commons.lang3.StringUtils;
 
 public class DataBaseUtil
 {
   private static String url = "";
   private static String username = "root";
   private static String psw = "000000";
   static Connection conn = null;
 
   public static Date recoverStartTime = null;
   public static Date recoverEndTime = null;
 
   public static Map<String, String> tabInsterMap = new HashMap();
 
   public static List<String> tabInsterNameList = new ArrayList();
 
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
     return true;
   }
 
   public static void batchInsert(List<String> sql) {
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
       catch (SQLException e3) {
         e3.printStackTrace();
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
 
   public static void main(String[] args) throws Exception
   {
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
       } catch (SQLException e6) {
         e6.printStackTrace();
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
       } catch (SQLException e7) {
         e7.printStackTrace();
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

