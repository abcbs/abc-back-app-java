 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.utils.DateUtil;
import com.ndlan.canyin.core.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
 
 public class DataBackup
 {
   private static final String filePath = PathUtil.getG2Login() + "/folderName.txt";
 
   public static void main(String[] args)
     throws Exception
   {
     System.out.println(getTxtInfo());
   }
 
   public static String[] unZip(String file)
   {
     String result = TrueFalseEnum.FALSE.getCode();
     String unZipFileName = "";
     String filePath = PathUtil.getUnzipExe();
     BufferedReader bufferedReader = null;
     try {
       Process p = Runtime.getRuntime().exec(filePath + " \"" + file + "\"");
       bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
       int retval = p.waitFor();
       if (retval == 1)
       {
         result = TrueFalseEnum.TRUE.getCode();
         unZipFileName = getTxtInfo();
       }
       String[] arrayOfString = { result, unZipFileName };
       return arrayOfString;
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
//       int retval = { result, unZipFileName };
//       return retval;
     }
     finally
     {
       if (bufferedReader != null)
         try {
           bufferedReader.close(); } catch (Exception ex) {
         } 
       return null;
     }
//     throw localObject;
   }
 
   public static String getTxtInfo()
   {
     String txtInfo = null;
     try
     {
       txtInfo = readFileByLines(filePath);
     }
     catch (Exception e) {
       return null;
     }
     return txtInfo;
   }
 
   public static String readFileByLines(String fileName)
   {
     String no = "";
     File file = new File(fileName);
     BufferedReader reader = null;
     try {
       reader = new BufferedReader(new FileReader(file));
       String tempString = null;
       int line = 1;
 
       while ((tempString = reader.readLine()) != null)
       {
         no = tempString;
       }
       reader.close();
     } catch (IOException e1) {
       e1.printStackTrace();
     } finally {
       if (reader != null)
         try {
           reader.close();
         }
         catch (IOException e1) {
         }
     }
     return no;
   }
 
   public static String createBackUpFile(String backUpVersionName, String restId, String version, String synDataVersionId, String backUpType)
     throws Exception
   {
     String backupDataPath = PathUtil.getBackUpPath();
     File backupDataFile = new File(backupDataPath);
     if (!backupDataFile.exists())
     {
       backupDataFile.mkdir();
     }
 
     String backUpVersionPath = backupDataPath + "\\" + backUpVersionName;
     File dataFile = new File(backUpVersionPath);
     if (!dataFile.exists())
     {
       dataFile.mkdir();
     }
 
     String backUpVersionDBPath = backUpVersionPath + "\\db";
     File dataDBFile = new File(backUpVersionDBPath);
     if (!dataDBFile.exists())
     {
       dataDBFile.mkdir();
     }
     String backUpVersionUploadPath = backUpVersionPath + "\\upload";
     File dataUploadFile = new File(backUpVersionUploadPath);
     if (!dataUploadFile.exists())
     {
       dataUploadFile.mkdir();
     }
 
     String info = backUpVersionPath + "\\info.properties";
     File infoFile = new File(info);
     if (!infoFile.exists())
     {
       infoFile.createNewFile();
       FileOutputStream out = new FileOutputStream(infoFile);
       out.write(("version=" + version + "\r\n").getBytes());
       out.write(("restId=" + restId + "\r\n").getBytes());
       out.write(("createTime=" + DateUtil.toString(new Date()) + "\r\n").getBytes());
       out.write(("type=" + backUpType + "\r\n").getBytes());
       out.write(("synDataVersionId=" + synDataVersionId + "\r\n").getBytes());
       out.close();
     }
 
     DataBaseExportUtil.writeDataBase(backUpVersionDBPath, backUpType);
     FileUtil.copyFolder(PathUtil.getProjectUploadFilePath(), backUpVersionUploadPath);
 
     ZipCompressor zipCompressor = new ZipCompressor(backUpVersionPath + ".zip");
     zipCompressor.compress(backUpVersionPath);
     System.out.println(backUpVersionPath + ".zip");
 
     return backUpVersionPath + ".zip";
   }
 
   private static String getBackupType(String uploadPath, String fileName) throws Exception
   {
     String infoName = "info.properties";
     File pf = new File(uploadPath + "\\" + fileName + "\\" + infoName);
     if (!pf.exists())
     {
       return "local";
     }
     Properties p = new Properties();
     InputStream in = new FileInputStream(pf);
     p.load(in);
     in.close();
     String type = "";
     if (p.containsKey("type")) {
       type = p.getProperty("type");
     }
     return type;
   }
 
   private static String getSynDataVersionId(String uploadPath, String fileName) throws Exception
   {
     String infoName = "info.properties";
     File pf = new File(uploadPath + "\\" + fileName + "\\" + infoName);
     if (!pf.exists())
     {
       return null;
     }
     Properties p = new Properties();
     InputStream in = new FileInputStream(pf);
     p.load(in);
     in.close();
     String synDataVersionId = "";
     if (p.containsKey("synDataVersionId")) {
       synDataVersionId = p.getProperty("synDataVersionId");
     }
     return synDataVersionId;
   }
 
   private static String getVersion(String uploadPath, String fileName) throws Exception
   {
     String infoName = "info.properties";
     File pf = new File(uploadPath + "\\" + fileName + "\\" + infoName);
     if (!pf.exists())
     {
       return "1.6.4";
     }
     Properties p = new Properties();
     InputStream in = new FileInputStream(pf);
     p.load(in);
     in.close();
     String version = "";
     if (p.containsKey("version")) {
       version = p.getProperty("version");
     }
     return version;
   }
 
   public static void recoverBackUpFile(String backUpVersionName)
     throws Exception
   {
     String backupDataPath = PathUtil.getBackUpPath();
 
     String backUpVersionPath = backupDataPath + "\\" + backUpVersionName;
     String backUpVersionDBPath = backUpVersionPath + "\\db";
     String backUpVersionUploadPath = backUpVersionPath + "\\upload";
     String backupType = getBackupType(backupDataPath, backUpVersionName);
     String synVersionId = getSynDataVersionId(backupDataPath, backUpVersionName);
     String version = getVersion(backupDataPath, backUpVersionName);
 
     System.out.println("backUpVersionPath:" + backUpVersionPath);
     System.out.println("backUpVersionDBPath:" + backUpVersionDBPath);
     System.out.println("backUpVersionUploadPath:" + backUpVersionUploadPath);
 
     System.out.println("backupType:" + backupType);
     System.out.println("synVersionId:" + synVersionId);
     System.out.println("version:" + version);
 
     DataBaseExportUtil.recoverDataBase(backUpVersionDBPath, backupType, synVersionId, version);
 
     FileUtil.copyFolder(backUpVersionUploadPath, PathUtil.getProjectUploadFilePath());
   }
 
   public static DwzAjaxDone recoverBackUpFileByFilePath(String filePath)
     throws Exception
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     File file = new File(filePath);
     if ((file != null) && (file.exists())) {
       if (file.getName().indexOf(".zip") < 0)
       {
         ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
         ajax.setMessage("文件格式不正确，应该是zip文件");
       }
       String fileName = file.getName().replaceAll(".zip", "");
       try {
         String[] unZipResult = unZip(PathUtil.getBackUpPath() + "\\" + file.getName());
         if (TrueFalseEnum.TRUE.getCode().equals(unZipResult[0]))
         {
           if (StringUtils.isNotEmpty(unZipResult[1])) {
             System.out.println("f:" + unZipResult[1]);
             recoverData(unZipResult[1]);
             ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
             ajax.setMessage("数据恢复成功!");
           }
         }
         else
         {
           ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
           ajax.setMessage("数据解压失败，请重新操作");
         }
       } catch (Exception e) {
         e.printStackTrace();
         ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
         ajax.setMessage("数据失败，请重新操作");
       }
     }
     else {
       ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       ajax.setMessage("文件不存在!");
     }
     return ajax;
   }
 
   public static String backupData(String restId, String version, String synDataVersionId, String backUpType)
     throws Exception
   {
     SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
     String dateStr = format.format(DateProvider.DEFAULT.getDate());
 
     createBackUpFile(dateStr, restId, version, synDataVersionId, backUpType);
 
     return dateStr;
   }
 
   public static String recoverData(String dataFileName)
     throws Exception
   {
     recoverBackUpFile(dataFileName);
     return null;
   }
 
   public static String reset(String filePath, String sqlFileName)
     throws IOException
   {
     String fileName = "/application.properties";
     String batFullName = filePath + "\\backupfile\\reset.bat";
     String userName = "";
     String password = "";
     String databaseIp = "";
     Properties p = new Properties();
     InputStream in = DataBackup.class.getResourceAsStream(fileName);
     p.load(in);
     in.close();
     if (p.containsKey("jdbc.username")) {
       userName = p.getProperty("jdbc.username");
     }
     if (p.containsKey("jdbc.password")) {
       password = p.getProperty("jdbc.password");
     }
     if (p.containsKey("database.ip")) {
       databaseIp = p.getProperty("database.ip");
     }
     String installPath = PathUtil.getInstallPath();
 
     FileWriter writer = new FileWriter(batFullName);
     if (StringUtils.isNotEmpty(password)) {
       String procCreateRestSql = filePath + "\\WEB-INF\\classes\\sql\\db\\proc_create_rest.sql";
       writer.write("cd " + installPath + "\\mysql\\bin \n mysql -h" + databaseIp + " -u" + userName + " -p" + password + " -e \"source " + procCreateRestSql + "\" canyin \n");
       writer.write("mysql -h" + databaseIp + " -u" + userName + " -p" + password + " -e \"call addRestData(1)\" canyin");
     }
     else {
       String procCreateRestSql = filePath + "\\WEB-INF\\classes\\sql\\db\\proc_create_rest.sql";
       writer.write("cd " + installPath + "\\mysql\\bin \n mysql -h" + databaseIp + " -u" + userName + " -e \"source " + procCreateRestSql + "\" canyin  \n");
       writer.write("mysql -h" + databaseIp + " -u" + userName + " -e \"call addRestData(1)\" canyin ");
     }
 
     writer.close();
     File file = new File(batFullName);
     while (!file.exists()) {
       writer = new FileWriter(batFullName);
       if (StringUtils.isNotEmpty(password)) {
         String procCreateRestSql = filePath + "\\WEB-INF\\classes\\sql\\db\\proc_create_rest.sql";
         writer.write("cd " + installPath + "\\mysql\\bin \n mysql -h" + databaseIp + " -u" + userName + " -p" + password + " -e \"source " + procCreateRestSql + "\" canyin \n");
         writer.write("mysql -h" + databaseIp + " -u" + userName + " -p" + password + " -e \"call addRestData(1)\" canyin");
       }
       else {
         String procCreateRestSql = filePath + "\\WEB-INF\\classes\\sql\\db\\proc_create_rest.sql";
         writer.write("cd " + installPath + "\\mysql\\bin \n mysql -h" + databaseIp + " -u" + userName + " -e \"source " + procCreateRestSql + "\" canyin  \n");
         writer.write("mysql -h" + databaseIp + " -u" + userName + " -e \"call addRestData(1)\" canyin ");
       }
 
       writer.close();
       file = new File(batFullName);
     }
 
     Runtime rt = Runtime.getRuntime();
     Process process = rt.exec(batFullName);
     try
     {
       final InputStream is1 = process.getInputStream();
 
       final InputStream is2 = process.getErrorStream();
 
       new Thread() {
         public void run() {
           BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
           try {
             String line1 = null;
             while ((line1 = br1.readLine()) != null)
               if (line1 != null)
                 System.out.println("我想被打印..." + line1);
           }
           catch (IOException e)
           {
             e.printStackTrace();
           } finally {
             try {
               is1.close();
             } catch (IOException e) {
               e.printStackTrace();
             }
           }
         }
       }
       .start();
 
       new Thread() {
         public void run() {
           BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
           try {
             String line2 = null;
             while ((line2 = br2.readLine()) != null)
               if (line2 != null)
                 System.out.println("我想被打印..." + line2);
           }
           catch (IOException e)
           {
             e.printStackTrace();
           } finally {
             try {
               is1.close();
             } catch (IOException e) {
               e.printStackTrace();
             }
           }
         }
       }
       .start();
 
       process.waitFor();
       process.destroy();
       System.out.println("我想被打印...");
     } catch (Exception e) {
       try {
         process.getErrorStream().close();
         process.getInputStream().close();
         process.getOutputStream().close();
       }
       catch (Exception ee) {
       }
     }
     return "success";
   }
 }

