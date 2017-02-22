 package com.ndlan.canyin.core.utils;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 
 public class FileUtil
 {
   public static final int FILE_STATUS_FILE_SUCCESS = 1;
   public static final int FILE_STATUS_ERROR = 0;
 
   public static int copyFile(String oldPath, String newPath)
   {
     try
     {
       int bytesum = 0;
       int byteread = 0;
       File oldfile = new File(oldPath);
       if (oldfile.exists()) {
         InputStream inStream = new FileInputStream(oldPath);
         FileOutputStream fs = new FileOutputStream(newPath);
         byte[] buffer = new byte[1444];
 
         while ((byteread = inStream.read(buffer)) != -1) {
           bytesum += byteread;
           fs.write(buffer, 0, byteread);
         }
         inStream.close();
       }
     }
     catch (Exception e) {
       e.printStackTrace();
       return 0;
     }
     return 1;
   }
 
   public static int copyFile(InputStream inStream, String newPath) {
     try {
       int bytesum = 0;
       int byteread = 0;
       FileOutputStream fs = new FileOutputStream(newPath);
       byte[] buffer = new byte[1444];
 
       while ((byteread = inStream.read(buffer)) != -1) {
         bytesum += byteread;
         fs.write(buffer, 0, byteread);
       }
       inStream.close();
       fs.close();
     }
     catch (Exception e) {
       e.printStackTrace();
       return 0;
     }
     return 1;
   }
 
   public static void copyFolder(String oldPath, String newPath)
   {
     try
     {
       new File(newPath).mkdirs();
       File a = new File(oldPath);
       String[] file = a.list();
       File temp = null;
       for (int i = 0; i < file.length; i++) {
         if (oldPath.endsWith(File.separator)) {
           temp = new File(oldPath + file[i]);
         }
         else {
           temp = new File(oldPath + File.separator + file[i]);
         }
 
         if (temp.isFile()) {
           FileInputStream input = new FileInputStream(temp);
           FileOutputStream output = new FileOutputStream(newPath + "/" + 
             temp.getName().toString());
           byte[] b = new byte[5120];
           int len;
           while ((len = input.read(b)) != -1)
           {
//             int len;
             output.write(b, 0, len);
           }
           output.flush();
           output.close();
           input.close();
         }
         if (temp.isDirectory())
           copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 
   public static String readFileByLines(String fileName)
   {
     StringBuffer flieStr = new StringBuffer();
     File file = new File(fileName);
     BufferedReader reader = null;
     try {
       InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
       reader = new BufferedReader(isr);
 
       String tempString = null;
 
       while ((tempString = reader.readLine()) != null)
       {
         flieStr.append(tempString);
       }
       reader.close();
     } catch (IOException e) {
       e.printStackTrace();
 
       if (reader != null)
         try {
           reader.close();
         }
         catch (IOException localIOException1)
         {
         }
     }
     finally
     {
       if (reader != null)
         try {
           reader.close();
         }
         catch (IOException localIOException2) {
         }
     }
     return flieStr.toString();
   }
 
   public static void deleteFile(String path) {
     try {
       File file = new File(path);
       file.deleteOnExit();
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 
   public static void deleteDirectory(String dest)
   {
     System.out.println("deleteDirectory:" + dest);
     File f = new File(dest);
     if (f.exists())
     {
       if (f.isDirectory())
       {
         File[] fs = f.listFiles();
         if (fs.length > 0)
         {
           for (File file : fs)
           {
             deleteDirectory(file.getAbsolutePath());
           }
         }
       }
       f.delete();
     }
   }
 }

