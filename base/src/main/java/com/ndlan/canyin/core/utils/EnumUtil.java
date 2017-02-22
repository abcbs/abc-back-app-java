 package com.ndlan.canyin.core.utils;
 
 import java.io.PrintStream;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.SQLException;
 import java.sql.Statement;
 
 public class EnumUtil
 {
   private static final String url = "jdbc:mysql://127.0.0.1:3309/canyin?useUnicode=true&characterEncoding=utf-8";
   private static final String username = "root";
   private static final String psw = "000000";
   static Connection conn = null;
 
   private static Connection getConnection() {
     try { Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3309/canyin?useUnicode=true&characterEncoding=utf-8", "root", "000000");
     } catch (Exception e) {
       e.printStackTrace();
     }
     return conn;
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
     }
   }
 
   public static void usingProcedure(String procedureName, String p1)
   {
     Connection conn = getConnection();
     PreparedStatement stmt = null;
     try {
       stmt = conn.prepareCall("call " + procedureName + " ( " + p1 + " )");
       stmt.executeQuery();
       System.out.println("存储过程执行完毕");
     } catch (SQLException e) {
       e.printStackTrace();
     }
   }
 
   public void dropProcedure(String procedureName) {
     Connection conn = getConnection();
     Statement stmt = null;
     try {
       String dorpProcedure = "drop procedure if exists " + procedureName + " ;";
       stmt = conn.createStatement();
       stmt.executeUpdate(dorpProcedure);
     } catch (SQLException e) {
       e.printStackTrace();
     }
   }
 
   public static void insert(String sql) {
     Connection conn = getConnection();
     PreparedStatement stmt = null;
     try {
       stmt = conn.prepareStatement(sql);
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
 
   public static void deleteAll() {
     Connection conn = getConnection();
     PreparedStatement stmt = null;
     try {
       stmt = conn.prepareStatement("DELETE FROM md_base_code_item");
       stmt.execute();
       stmt = conn.prepareStatement("DELETE FROM md_base_code");
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
 }

