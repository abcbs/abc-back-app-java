 package com.ndlan.canyin.frontdesk.util;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.net.URI;
 import java.net.URISyntaxException;
 import java.net.URL;
 import org.apache.commons.lang3.StringUtils;
 
 public class PathUtil
 {
   private static final int USER = 1;
   private static final int TEST = 2;
   private static int IF_USER_OR_TEST = 1;
   private static String backUpPath;
 
   public static void main(String[] args)
     throws Exception
   {
     String installPath = getMainPath("/upload");
     System.out.println(installPath);
   }
 
   public static String getProjectStaticFilePath() throws IOException {
     if (IF_USER_OR_TEST == 1)
     {
       String installPath = getFrontpath("/static");
       installPath = installPath.replaceFirst("/", "").replaceAll("/", "\\\\");
       return installPath;
     }
     if (IF_USER_OR_TEST == 2)
     {
       String projectPath = System.getProperty("user.dir");
       return projectPath + "\\src\\main\\webapp\\static";
     }
     return "";
   }
 
   public static String getProjectUploadFilePath() throws IOException {
     if (IF_USER_OR_TEST == 1)
     {
       String installPath = getMainPath("/upload");
       installPath = installPath.replaceFirst("/", "").replaceAll("/", "\\\\");
       System.out.println("getProjectUploadFilePath:" + installPath);
       return installPath;
     }
     if (IF_USER_OR_TEST == 2)
     {
       String projectPath = System.getProperty("user.dir");
       return projectPath + "\\src\\main\\webapp\\upload";
     }
     return "";
   }
 
   public static String getApath(String path)
   {
     String apath = "";
     try {
       apath = PathUtil.class.getResource("/").toURI().getPath();
     } catch (URISyntaxException e) {
       e.printStackTrace();
     }
     String a = apath.substring(0, apath.indexOf("/canyin-main") + 12);
     path = a + path;
     return path;
   }
 
   public static void setBackUpPath(String _backUpPath)
   {
     backUpPath = _backUpPath;
   }
 
   public static String getFrontpath(String path) {
     String apath = "";
     try {
       apath = PathUtil.class.getResource("/").toURI().getPath();
     } catch (URISyntaxException e) {
       e.printStackTrace();
     }
     String a = apath.substring(0, apath.indexOf("/canyin-frontdesk") + 17);
     path = a + path;
     return path;
   }
 
   public static String getMainPath(String path)
   {
     String apath = getFrontpath(path).replace("canyin-frontdesk", "canyin-main");
     return apath;
   }
 
   public static String getBackUpPath()
     throws IOException
   {
     if (!StringUtils.isEmpty(backUpPath))
     {
       return backUpPath;
     }
     String backupDataPath = getInstallPath() + "\\canyinBackup";
     File f = new File(backupDataPath);
     if (!f.exists())
     {
       f.mkdir();
     }
     return backupDataPath;
   }
 
   public static String getRootFileName()
   {
     String rootFileName = "canyinBackup";
     try {
       rootFileName = getBackUpPath();
     } catch (IOException e) {
       e.printStackTrace();
     }
     rootFileName = rootFileName.substring(rootFileName.lastIndexOf("\\") + 1, rootFileName.length());
     rootFileName = rootFileName.replaceAll("\\\\", "");
     return rootFileName;
   }
 
   public static String getInstallPath()
   {
     if (IF_USER_OR_TEST == 1)
     {
       String projectPath = System.getProperty("user.dir");
       String installPath = projectPath.substring(0, projectPath.indexOf("G2") + 5);
       return installPath;
     }
     if (IF_USER_OR_TEST == 2)
     {
       String installPath = "E:\\development\\workspace";
       return installPath;
     }
     return "";
   }
 
   public static String getUnzipExe()
   {
     String install = getInstallPath();
 
     return install + "\\G2Login\\UnZip.exe";
   }
 
   public static String getG2Login()
   {
     String install = getInstallPath();
 
     return install + "\\G2Login";
   }
 }

