 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import org.apache.commons.lang3.StringUtils;
 
 public class ZiZhuUtil
 {
   private static final String receiveFile_memberCard = PathUtil.getInstallPath() + "/ReadCard/ReceiveFile/1.txt";
 
   private static final String receiveFile_goadmin = PathUtil.getInstallPath() + "/ReadCard/ReceiveFile/3.txt";
 
   private static final String receiveFile_bankCard = PathUtil.getInstallPath() + "/ReadCard/ReceiveFile/";
   public static BufferedReader bufread;
   private static final String exeName = "ReadCard.exe";
   private static int connectNum = 0;
   private static final String filePath = "/ReadCard/Info.txt";
 
   public static void creatTxtFile(String name)
     throws IOException
   {
     File filename = new File(name);
     if (!filename.exists()) {
       filename.createNewFile();
       System.err.println(filename + "已创建！");
     }
   }
 
   public static boolean findProcess(String processName)
   {
     BufferedReader bufferedReader = null;
     try {
       Process proc = Runtime.getRuntime().exec(" tasklist /fi \" imagename eq " + processName + " \" ");
       bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
       String line = null;
       while ((line = bufferedReader.readLine()) != null)
         if (line.contains(processName)) {
        	 boolean  i = true;
           return i;
         }
//       int i = 0;
       return false;
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
//       String line = 0;
//       return line;
     }
     finally
     {
       if (bufferedReader != null)
         try {
           bufferedReader.close(); } catch (Exception ex) {
         } 
       return false;
     }
//     throw localObject;
   }
 
   public static boolean isCloseRateCard()
   {
     if (connectNum > 20)
     {
       connectNum = 0;
       return false;
     }
     if (findProcess("ReadCard.exe"))
     {
       try {
         Thread.sleep(2000L);
       } catch (InterruptedException e) {
         e.printStackTrace();
       }
       connectNum += 1;
       System.out.println("没有关闭exe：" + connectNum);
       return isCloseRateCard();
     }
 
     connectNum = 0;
     return true;
   }
 
   public static String goAdmin()
   {
     try
     {
       return checkProcess(receiveFile_goadmin);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static String checkProcess(String cardType)
   {
     String result = null;
     try
     {
       creatTxtFile(cardType);
       result = TrueFalseEnum.TRUE.getCode();
     } catch (IOException e) {
       e.printStackTrace();
       result = TrueFalseEnum.FALSE.getCode();
     }
 
     return result;
   }
 
   public static void main(String[] args)
   {
     try {
       System.out.println(checkProcess(receiveFile_memberCard));
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public static String getMemberCardNo()
   {
     try
     {
       return checkProcess(receiveFile_memberCard);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static String getBankCardNo(String billNo, String money)
   {
     try
     {
       return checkProcess(receiveFile_bankCard + "2," + billNo + "," + money + ".txt");
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static String checkBillPayStatus(String billNo, String money)
   {
     try
     {
       return checkProcess(receiveFile_bankCard + "4," + billNo + "," + money + ".txt");
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static String getTxtInfo()
   {
     String txtInfo = null;
     try
     {
       txtInfo = readFileByLines(PathUtil.getInstallPath() + "/ReadCard/Info.txt");
       if (!StringUtils.isEmpty(txtInfo))
       {
         deleteFile(PathUtil.getInstallPath() + "/ReadCard/Info.txt");
       }
     }
     catch (Exception e) {
       return null;
     }
     return txtInfo;
   }
 
   public static void deleteFile(String path) {
     File file = new File(path);
     if (file.exists())
     {
       file.delete();
     }
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
 }

