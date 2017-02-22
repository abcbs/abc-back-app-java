 package com.ndlan.canyin.core.utils;
 
 import java.io.FileOutputStream;
 import java.io.IOException;
import java.io.OutputStream;

import com.ndlan.canyin.core.utils.PortPrinterBase;
 
 public class PortPrinterBase
 {
   private OutputStream out;
   protected int lineCount = 40;
   private String printType = "0";
 
   protected final String LEFT = "LEFT";
   protected final String CENTER = "CENTER";
   protected final String RIGHT = "RIGHT";
   public static final byte HT = 9;
   public static final byte LF = 10;
   public static final byte CR = 13;
   public static final byte ESC = 27;
   public static final byte DLE = 16;
   public static final byte GS = 29;
   public static final byte FS = 28;
   public static final byte STX = 2;
   public static final byte US = 31;
   public static final byte CAN = 24;
   public static final byte CLR = 12;
   public static final byte EOT = 4;
   public static final byte[] ESC_INIT = { 27, 64 };
 
   public static final byte[] ESC_STANDARD = { 27, 83 };
 
   public static final byte[] ESC_CN_FONT = { 28, 38 };
 
   public static final byte[] ESC_SELECT_CHARACTER = { 27, 82, 9 };
 
   public static final byte[] ESC_FS_2 = { 28, 50, 113, 24 };
 
   public static final byte[] ESC_CANCEL_DEFINE_FONT = { 27, 37 };
 
   public static final byte[] ESC_OPEN_DRAWER = { 27, 112, 0, 16, -1 };
 
   public static final byte[] POS_CUT_MODE_FULL = { 29, 86 };
   public static final byte[] POS_CUT_MODE_PARTIAL = { 29, 86, 1 };
 
   public static final byte[] ESC_FONT_A = { 27, 33 };
 
   public static final byte[] ESC_FONT_B = { 27, 33, 1 };
 
   public static final byte[] ESC_FONTA = { 27, 77, 48 };
 
   public static final byte[] ESC_FONTB = { 27, 77, 1 };
 
   public static final byte[] ESC_FONT_COLOR_DEFAULT = { 27, 114 };
 
   public static final byte[] ESC_FONT_COLOR_RED = { 27, 114, 1 };
 
   public static final byte[] FS_FONT_ALIGN = { 28, 33, 1, 27, 33, 1 };
 
   public static final byte[] FS_FONT_ALIGN_DOUBLE = { 28, 33, 4, 27, 33, 4 };
 
   public static final byte[] FS_FONT_VERTICAL_DOUBLE = { 28, 33, 8, 27, 33, 8, 29, 33, 1 };
 
   public static final byte[] FS_FONT_DOUBLE = { 28, 33, 12, 27, 33, 48 };
 
   public static final byte[] ESC_ALIGN_LEFT = { 27, 97 };
 
   public static final byte[] ESC_ALIGN_CENTER = { 27, 97, 1 };
 
   public static final byte[] ESC_ALIGN_RIGHT = { 27, 97, 2 };
 
   public static final byte[] ESC_SETTING_BOLD = { 27, 69, 1 };
 
   public static final byte[] ESC_CANCEL_BOLD = { 27, 69 };
 
   public static final byte[] PRINT_STATE_DLE_EOT = { 16, 4, 1 };
 
   public static void main(String[] args)
     throws Exception
   {
     OutputStream out = null;
     out = new FileOutputStream("e://dayin.txt");
     PortPrinterBase p = new PortPrinterBase(out, "0");
     p.makePrintString(1, "2", "3");
   }
 
   public PortPrinterBase(OutputStream out, String printType)
   {
     this.out = out;
     this.printType = printType;
     initPrinter();
 
     String lineCountStr = "1";
     try {
       int temp = Integer.parseInt(lineCountStr);
       this.lineCount = temp;
     }
     catch (Exception localException)
     {
     }
   }
 
   public void initPrinter()
   {
     try
     {
       this.out.write(ESC_STANDARD);
       this.out.write(ESC_CANCEL_DEFINE_FONT);
       this.out.write(ESC_FONTA);
       this.out.write(ESC_SELECT_CHARACTER);
     }
     catch (IOException e)
     {
       e.printStackTrace();
     }
   }
 
   public void executeLineFeedAndPaperCut()
   {
   }
 
   public void billHeaderPrinter(String str)
   {
     try
     {
       this.out.write(ESC_ALIGN_CENTER);
       this.out.write(FS_FONT_DOUBLE);
       this.out.write((str + "\n").getBytes());
       this.out.write(10);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void callNumPrinter(String str)
   {
     try
     {
       this.out.write(ESC_ALIGN_LEFT);
       this.out.write(FS_FONT_DOUBLE);
       this.out.write((str + "\n").getBytes());
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void doubleSizePrinter(String str, String align)
   {
     try
     {
       if ("CENTER".equals(align))
         this.out.write(ESC_ALIGN_LEFT);
       else if ("RIGHT".equals(align))
         this.out.write(ESC_ALIGN_RIGHT);
       else {
         this.out.write(ESC_ALIGN_LEFT);
       }
       this.out.write(FS_FONT_DOUBLE);
       this.out.write((str + "\n").getBytes());
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void standardPrinterLine(String str, String align)
   {
     try
     {
       if ("CENTER".equals(align)) {
         this.out.write(ESC_ALIGN_CENTER);
         this.out.write(FS_FONT_ALIGN);
         this.out.write(ESC_CN_FONT);
         this.out.write(ESC_CANCEL_BOLD);
         if ("1".equals(this.printType))
           this.out.write(ESC_FONTA);
         else {
           this.out.write(ESC_FONT_B);
         }
         this.out.write(str.getBytes());
       } else if ("RIGHT".equals(align)) {
         this.out.write(ESC_ALIGN_RIGHT);
         this.out.write(FS_FONT_ALIGN);
         this.out.write(ESC_CN_FONT);
         this.out.write(ESC_CANCEL_BOLD);
         if ("1".equals(this.printType))
           this.out.write(ESC_FONTA);
         else {
           this.out.write(ESC_FONT_B);
         }
         this.out.write(str.getBytes());
       } else {
         this.out.write(ESC_ALIGN_LEFT);
         this.out.write(FS_FONT_ALIGN);
         this.out.write(ESC_CN_FONT);
         this.out.write(ESC_CANCEL_BOLD);
         if ("1".equals(this.printType))
           this.out.write(ESC_FONTA);
         else {
           this.out.write(ESC_FONT_B);
         }
         this.out.write(str.getBytes());
       }
       this.out.write("\n".getBytes());
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void standardBoldPrinterLine(String str, String align)
   {
     try
     {
       if ("CENTER".equals(align)) {
         this.out.write(ESC_ALIGN_CENTER);
         this.out.write(FS_FONT_ALIGN);
         this.out.write(ESC_CN_FONT);
         this.out.write(ESC_SETTING_BOLD);
         if ("1".equals(this.printType))
           this.out.write(ESC_FONTA);
         else {
           this.out.write(ESC_FONT_B);
         }
         this.out.write(str.getBytes());
       } else if ("RIGHT".equals(align)) {
         this.out.write(ESC_ALIGN_RIGHT);
         this.out.write(FS_FONT_ALIGN);
         this.out.write(ESC_CN_FONT);
         this.out.write(ESC_SETTING_BOLD);
         if ("1".equals(this.printType))
           this.out.write(ESC_FONTA);
         else {
           this.out.write(ESC_FONT_B);
         }
         this.out.write(str.getBytes());
       } else {
         this.out.write(ESC_ALIGN_LEFT);
         this.out.write(FS_FONT_ALIGN);
         this.out.write(ESC_CN_FONT);
         this.out.write(ESC_SETTING_BOLD);
         if ("1".equals(this.printType))
           this.out.write(ESC_FONTA);
         else {
           this.out.write(ESC_FONT_B);
         }
         this.out.write(str.getBytes());
       }
       this.out.write("\n".getBytes());
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void largeSizePrinterLine(String str, String align)
   {
     try
     {
       if ("CENTER".equals(align)) {
         this.out.write(ESC_ALIGN_CENTER);
         this.out.write(FS_FONT_ALIGN_DOUBLE);
         this.out.write(str.getBytes());
       } else if ("RIGHT".equals(align)) {
         this.out.write(ESC_ALIGN_RIGHT);
         this.out.write(FS_FONT_ALIGN_DOUBLE);
         this.out.write(str.getBytes());
       } else {
         this.out.write(ESC_ALIGN_LEFT);
         this.out.write(FS_FONT_ALIGN_DOUBLE);
         this.out.write(str.getBytes());
       }
       this.out.write("\n".getBytes());
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void largeHSizePrinterLine(String str, String align)
   {
     try
     {
       if ("CENTER".equals(align)) {
         this.out.write(ESC_ALIGN_CENTER);
         this.out.write(FS_FONT_VERTICAL_DOUBLE);
         this.out.write(str.getBytes());
       } else if ("RIGHT".equals(align)) {
         this.out.write(ESC_ALIGN_RIGHT);
         this.out.write(FS_FONT_VERTICAL_DOUBLE);
         this.out.write(str.getBytes());
       } else {
         this.out.write(ESC_ALIGN_LEFT);
         this.out.write(FS_FONT_VERTICAL_DOUBLE);
         this.out.write(str.getBytes());
       }
       this.out.write("\n".getBytes());
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void largeSizeRedPrinterLine(String str, String align)
   {
     try
     {
       if ("CENTER".equals(align)) {
         this.out.write(ESC_ALIGN_CENTER);
         this.out.write(FS_FONT_ALIGN_DOUBLE);
         this.out.write(ESC_FONT_COLOR_RED);
         this.out.write(str.getBytes());
       } else if ("RIGHT".equals(align)) {
         this.out.write(ESC_ALIGN_RIGHT);
         this.out.write(FS_FONT_ALIGN_DOUBLE);
         this.out.write(ESC_FONT_COLOR_RED);
         this.out.write(str.getBytes());
       } else {
         this.out.write(ESC_ALIGN_LEFT);
         this.out.write(FS_FONT_ALIGN_DOUBLE);
         this.out.write(ESC_FONT_COLOR_RED);
         this.out.write(str.getBytes());
       }
       this.out.write("\n".getBytes());
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void openDrawer() {
     try {
       this.out.write(ESC_OPEN_DRAWER);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public String makePrintString(int lineChars, String txt1, String txt2) {
     if (txt1 == null) {
       txt1 = "";
     }
     if (txt2 == null) {
       txt2 = "";
     }
     int spaces = 0;
     String tab = "";
     try {
       spaces = lineChars - (txt1.getBytes().length + txt2.getBytes().length);
       for (int j = 0; j < spaces; j++)
         tab = tab + " ";
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return txt1 + tab + txt2;
   }
   public String makePrintString(int lineChars, String txt1, String txt2, String txt3) {
     if (txt1 == null) {
       txt1 = "";
     }
     if (txt2 == null) {
       txt2 = "";
     }
     if (txt3 == null) {
       txt3 = "";
     }
     int spaces = 0;
     int lineChars1 = lineChars * 2 / 3;
     String tab = "";
     String returnStr = txt1;
     try {
       spaces = lineChars1 - (returnStr.getBytes().length + txt2.getBytes().length);
       for (int j = 0; j < spaces; j++) {
         tab = tab + " ";
       }
       returnStr = txt1 + tab + txt2;
       spaces = lineChars - (returnStr.getBytes().length + txt3.getBytes().length);
       tab = "";
       for (int j = 0; j < spaces; j++) {
         tab = tab + " ";
       }
       returnStr = returnStr + tab + txt3;
     } catch (Exception e) {
       e.printStackTrace();
     }
     return returnStr;
   }
 }

