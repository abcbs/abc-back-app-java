 package com.ndlan.canyin.frontdesk.util;
 
 import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Enumeration;
 import java.util.zip.CRC32;
 import java.util.zip.CheckedOutputStream;
 import java.util.zip.ZipEntry;
 import java.util.zip.ZipFile;
 import java.util.zip.ZipOutputStream;
 
 public class ZipCompressor
 {
   static final int BUFFER = 8192;
   private File zipFile;
 
   public ZipCompressor(String pathName)
   {
     this.zipFile = new File(pathName);
   }
 
   public void compress(String srcPathName) {
     File file = new File(srcPathName);
     if (!file.exists())
       throw new RuntimeException(srcPathName + "不存在！");
     try {
       FileOutputStream fileOutputStream = new FileOutputStream(this.zipFile);
       CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
 
       ZipOutputStream out = new ZipOutputStream(cos);
       String basedir = "";
       compress(file, out, basedir);
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   private void compress(File file, ZipOutputStream out, String basedir)
   {
     if (file.isDirectory()) {
       System.out.println("压缩：" + basedir + file.getName());
       compressDirectory(file, out, basedir);
     } else {
       System.out.println("压缩：" + basedir + file.getName());
       compressFile(file, out, basedir);
     }
   }
 
   private void compressDirectory(File dir, ZipOutputStream out, String basedir)
   {
     if (!dir.exists()) {
       return;
     }
     File[] files = dir.listFiles();
     for (int i = 0; i < files.length; i++)
     {
       compress(files[i], out, basedir + dir.getName() + "/");
     }
   }
 
   private void compressFile(File file, ZipOutputStream out, String basedir)
   {
     if (!file.exists())
       return;
     try
     {
       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
 
       ZipEntry entry = new ZipEntry(basedir + file.getName());
       out.putNextEntry(entry);
 
       byte[] data = new byte[8192];
       int count;
       while ((count = bis.read(data, 0, 8192)) != -1) {
         out.write(data, 0, count);
       }
       bis.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public String unZipFile(String strTmpPath) {
     String folderName = null;
     try {
       ZipFile tempZipFile = new ZipFile(this.zipFile);
       Enumeration emu = tempZipFile.entries();
       while (emu.hasMoreElements()) {
         ZipEntry entry = (ZipEntry)emu.nextElement();
         String entryName = entry.getName();
         System.out.println("-->" + entryName);
         if (entry.isDirectory()) {
           new File(strTmpPath + "\\" + entryName).mkdirs();
           continue;
         }
         BufferedInputStream bis = new BufferedInputStream(tempZipFile.getInputStream(entry));
 
         File file = new File(strTmpPath + "\\" + entryName);
         File parent = file.getParentFile();
         if ((parent != null) && (!parent.exists())) {
           parent.mkdirs();
         }
         if (folderName == null)
         {
           folderName = getRootName(parent);
         }
         FileOutputStream fos = new FileOutputStream(file);
         int data;
         while ((data = bis.read()) != -1) {
           fos.write(data);
         }
         fos.flush();
         fos.close();
         bis.close();
       }
       tempZipFile.close();
       return folderName;
     } catch (Exception e) {
       e.printStackTrace();
     }return folderName;
   }
 
   private String getRootName(File file)
   {
     if ("canyinBackup".equals(file.getParentFile().getName()))
     {
       return file.getName();
     }
 
     return getRootName(file.getParentFile());
   }
 
   public boolean createFile(File file)
     throws IOException
   {
     if (file.isDirectory()) {
       return file.mkdirs();
     }
     if (!file.getParentFile().exists()) {
       file.getParentFile().mkdirs();
     }
     return file.createNewFile();
   }
 }

