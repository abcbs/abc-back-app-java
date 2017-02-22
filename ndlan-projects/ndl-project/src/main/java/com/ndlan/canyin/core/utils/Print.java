 package com.ndlan.canyin.core.utils;
 
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.net.InetSocketAddress;
 import java.net.Socket;
import org.apache.commons.lang3.StringUtils;

import com.ndlan.canyin.core.utils.BatchUtils;
import com.ndlan.canyin.core.utils.NetUtil;
import com.ndlan.canyin.core.utils.PortPrinterBase;
 
 public class Print
 {
   public static final int PRINTTYPE_USB = 1;
   public static final int PRINTTYPE_NET = 2;
 
   public static boolean openDrawer(String iporlpt, String printName)
   {
     if ((iporlpt != null) && (!iporlpt.isEmpty()))
     {
       if (iporlpt.indexOf(".") > 0)
       {
         return openNetDrawer(iporlpt);
       }
 
       return openUsbDrawer(iporlpt, printName);
     }
 
     return false;
   }
 
   public static boolean openDrawer(String iporlpt, int printType, String printName) {
     if (1 == printType)
     {
       return openUsbDrawer(iporlpt, printName);
     }
     if (2 == printType)
     {
       return openNetDrawer(iporlpt);
     }
     return true;
   }
 
   public static boolean openNetDrawer(String ip) {
     try {
       Socket client = new Socket();
       client.connect(new InetSocketAddress(ip, 9100), 1000);
       PortPrinterBase p = new PortPrinterBase(client.getOutputStream(), "10");
       p.openDrawer();
       client.close();
     } catch (IOException e) {
       e.printStackTrace();
       return false;
     }
     return true;
   }
 
   public static boolean openUsbDrawer(String lpt, String printName)
   {
     try
     {
       boolean isLpt = true;
 
       if ((StringUtils.isEmpty(lpt)) || ((!lpt.equalsIgnoreCase("LPT1")) && (!lpt.equalsIgnoreCase("LPT2")) && (!lpt.equalsIgnoreCase("LPT3")))) {
         lpt = "LPT3";
 
         String cmd = "NET USE " + lpt + " /DELETE";
         BatchUtils.exeBatch(cmd, false);
 
         String hostIp = NetUtil.getLocalHostByNetworkCard();
         cmd = "NET USE " + lpt + " \\\\" + hostIp + "\\" + printName + " /PERSISTENT:YES";
         BatchUtils.exeBatch(cmd, false);
         isLpt = false;
       }
       PrintWriter pw = new PrintWriter(lpt);
 
       char[] c = { '\033', 'p', '\000', '\020', 'ÿ' };
       pw.write(c);
       pw.flush();
       pw.close();
       if (!isLpt)
         BatchUtils.exeBatch("NET USE " + lpt + " /DELETE", false);
     }
     catch (FileNotFoundException ex) {
       ex.printStackTrace();
       return false;
     }
     return true;
   }
 }

