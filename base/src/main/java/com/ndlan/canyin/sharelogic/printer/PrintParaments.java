 package com.ndlan.canyin.sharelogic.printer;
 
 import java.awt.BasicStroke;
 import java.awt.Font;
 import java.text.SimpleDateFormat;
 
 public class PrintParaments
 {
   public static final String PAY = "PAY";
   public static final String PREDICT = "PREDICT";
   public static final String XIADAN = "XIADAN";
   public static final String TUICAI = "TUICAI";
   public static final String URGE = "URGE";
   public static final String URGEALL = "URGEALL";
   public static final String RECHARGE = "RECHARGE";
   public static final String ZHUANTAI = "ZHUANTAI";
   public static final String BINGTAI = "BINGTAI";
   public static final String SHIFT = "SHIFT";
   public static final String BillFromWaiMai = "1";
   public static final String BillFromJieZhang = "2";
   public static final Font PAY_80_TITLE_FONT = new Font("黑体", 0, 14);
   public static final Font PAY_76_TITLE_FONT = new Font("黑体", 0, 14);
   public static final Font PAY_58_TITLE_FONT = new Font("黑体", 0, 14);
 
   public static final Font PAY_80_DISHES_FONT = new Font("黑体", 0, 9);
   public static final Font PAY_76_DISHES_FONT = new Font("黑体", 0, 9);
   public static final Font PAY_58_DISHES_FONT = new Font("黑体", 0, 9);
 
   public static final BasicStroke PAY_BASIC_STROKE = new BasicStroke(0.5F, 0, 0, 2.0F, new float[] { 2.0F }, 0.0F);
 
   public static final SimpleDateFormat PAY_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 
   public static final SimpleDateFormat CANCLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
 }

