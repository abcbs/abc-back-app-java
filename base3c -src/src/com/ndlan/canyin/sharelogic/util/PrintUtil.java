 package com.ndlan.canyin.sharelogic.util;
 
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import org.apache.commons.lang3.StringUtils;
 
 public class PrintUtil
 {
   public static List<Printer> getUserFrontdeskPrinter(List<Printer> printers, HashMap<String, Object> printParaments)
   {
     List userPrinters = new ArrayList();
     if (printParaments != null)
     {
       String printerId = (String)printParaments.get("printerId");
 
       if ((StringUtils.isEmpty(printerId)) || (TrueFalseEnum.TRUE.getCode().equals(printerId)) || ("null".equals(printerId)))
       {
         userPrinters = printers;
       }
       else
       {
         for (Printer printer : printers) {
           if (!printer.getPrinterId().equals(printerId))
             continue;
           userPrinters.add(printer);
           break;
         }
 
       }
 
     }
     else
     {
       userPrinters = printers;
     }
     return userPrinters;
   }
 
   public static List<Printer> getUserFrontdeskPrinter(List<Printer> printers, String printerId)
   {
     List userPrinters = new ArrayList();
 
     if ((StringUtils.isEmpty(printerId)) || (TrueFalseEnum.TRUE.getCode().equals(printerId)) || ("null".equals(printerId)))
     {
       userPrinters = printers;
     }
     else
     {
       for (Printer printer : printers) {
         if (!printer.getPrinterId().equals(printerId))
           continue;
         userPrinters.add(printer);
         break;
       }
 
     }
 
     return userPrinters;
   }
 }

