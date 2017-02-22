 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.util.StringUtil;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.vo.MemberCardRechargeVo;
 import java.awt.BasicStroke;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.print.PageFormat;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.io.PrintStream;
 import java.io.UnsupportedEncodingException;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
 
 public class RechargePrinter extends AbstractPrinter
   implements Printable
 {
   private MemberCardRechargeVo memberCardRechargeVo;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
   PageFormat pf = new PageFormat();
 
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
     throws PrinterException
   {
     String encoding = "GB2312";
 
     Graphics2D g2_1 = (Graphics2D)graphics;
 
     g2_1.setColor(Color.black);
 
     switch (pageIndex)
     {
     case 0:
       Font font = new Font("宋体", 0, 14);
       Font font2 = new Font("宋体", 0, 9);
 
       g2_1.setFont(font2);
       float[] dash1 = { 2.0F };
 
       g2_1.setStroke(new BasicStroke(0.5F, 0, 0, 2.0F, dash1, 0.0F));
 
       double x = this.margin_left != null ? this.margin_left.doubleValue() : 0.0D;
       double y = this.margin_up != null ? this.margin_up.doubleValue() : 0.0D;
       double down = this.margin_down != null ? this.margin_down.doubleValue() : 0.0D;
 
       float heigth1 = font.getSize2D();
       float heigth = font2.getSize2D() + 2.0F;
       int leftOffset1 = 40;
       int leftOffset2 = 120;
       int width = (int)(Double.valueOf(this.template).doubleValue() * 72.0D / 25.399999999999999D - 15.0D + this.margin_left.doubleValue());
       Object drawStr;
       if (PrinterTemplateEnum.C_80.getCode().equals(this.template))
       {
         g2_1.setFont(font);
 
         String title = StringUtils.isNotEmpty(this.printer.getTitle()) ? this.printer.getTitle() : this.memberCardRechargeVo.getRestName();
         FontMetrics fm = g2_1.getFontMetrics();
 
         StringUtil strUtil = new StringUtil();
         int y1 = 0;
         try {
           for (String t : strUtil.splitAsLength(title, 25, encoding)) {
             int stringWidth = fm.stringWidth(t);
             int x_m = width / 2 - stringWidth / 2;
             g2_1.drawString(t, x_m < x ? (int)x : x_m, (float)y + (1 + y1) * heigth1);
             y1++;
           }
         }
         catch (UnsupportedEncodingException e) {
           System.out.println("会员卡充值，打印小票标题捕获异常：");
           e.printStackTrace();
         }
         g2_1.setFont(font2);
 
         y1++;
         y1++;
         String message = "欢迎您的光临！";
         g2_1.drawString(message, (float)x, (float)y + y1 * heigth);
 
         y1++;
         g2_1.drawString("会员卡号：", (float)x, (float)y + y1 * heigth);
         g2_1.drawString(this.memberCardRechargeVo.getCardNo(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
         y1++;
         g2_1.drawString("充值时间：", (float)x, (float)y + y1 * heigth);
         g2_1.drawString(this.sdf.format(this.memberCardRechargeVo.getRechargeTime()), (float)x + leftOffset1, (float)y + y1 * heigth);
 
         g2_1.drawString("操作人：", (float)x + leftOffset2, (float)y + y1 * heigth);
         g2_1.drawString(this.memberCardRechargeVo.getOperator(), (float)x + leftOffset2 + 30.0F, (float)y + y1 * heigth);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + y1 * heigth), (int)x + 183, (int)(y + y1 * heigth));
 
         y1++;
         g2_1.drawString("充值金额：", (float)x, (float)y + y1 * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getRechargeCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
         y1++;
         g2_1.drawString("实收金额：", (float)x, (float)y + y1 * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getPaidinCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
         if (this.memberCardRechargeVo.getGiveCash() != null) {
           y1++;
           g2_1.drawString("赠送金额：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getGiveCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
         }
 
         if (this.memberCardRechargeVo.getCashPledge() != null) {
           y1++;
           g2_1.drawString("押金：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getCashPledge()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
         }
 
         if (this.memberCardRechargeVo.getAddMemberIntegral() != null) {
           y1++;
           g2_1.drawString("增加积分：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getAddMemberIntegral()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
         }
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + y1 * heigth), (int)x + 183, (int)(y + y1 * heigth));
 
         y1++;
         g2_1.drawString("余额：", (float)x, (float)y + y1 * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getBalance()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
         g2_1.drawString("积分：", (float)x + leftOffset2, (float)y + y1 * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getMemberIntegral()).toString(), (float)x + leftOffset2 + 30.0F, (float)y + y1 * heigth);
 
         y1++;
         y1++;
         if (TrueFalseEnum.TRUE.getCode().equals(this.memberCardRechargeVo.getIsDrawBill())) {
           drawStr = "已开发票";
           g2_1.drawString((String)drawStr, (float)x + 150.0F, (float)y + y1 * heigth);
         }
 
         y += down;
         g2_1.setFont(new Font("宋体", 0, 1));
         g2_1.drawString(".", (float)x, (float)y + y1 * heigth);
       }
       else
       {
//         Object drawStr;
         if (PrinterTemplateEnum.C_76.getCode().equals(this.template))
         {
           g2_1.setFont(font);
 
           String title = StringUtils.isNotEmpty(this.printer.getTitle()) ? this.printer.getTitle() : this.memberCardRechargeVo.getRestName();
           FontMetrics fm = g2_1.getFontMetrics();
 
           StringUtil strUtil = new StringUtil();
           int y1 = 0;
           try {
             for (drawStr = strUtil.splitAsLength(title, 22, encoding).iterator(); ((Iterator)drawStr).hasNext(); ) { String t = (String)((Iterator)drawStr).next();
               int stringWidth = fm.stringWidth(t);
               int x_m = width / 2 - stringWidth / 2;
               g2_1.drawString(t, x_m < x ? (int)x : x_m, (float)y + (1 + y1) * heigth1);
               y1++; }
           }
           catch (UnsupportedEncodingException e)
           {
             System.out.println("会员卡充值，打印小票标题捕获异常：");
             e.printStackTrace();
           }
 
           g2_1.setFont(font2);
 
           y1++;
           y1++;
           String message = "欢迎您的光临！";
           g2_1.drawString(message, (float)x, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawString("会员卡号：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(this.memberCardRechargeVo.getCardNo(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawString("充值时间：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(this.sdf.format(this.memberCardRechargeVo.getRechargeTime()), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           g2_1.drawString("操作人：", (float)x + leftOffset2, (float)y + y1 * heigth);
           g2_1.drawString(this.memberCardRechargeVo.getOperator(), (float)x + leftOffset2 + 30.0F, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + y1 * heigth), (int)x + 183, (int)(y + y1 * heigth));
 
           y1++;
           g2_1.drawString("充值金额：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getRechargeCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawString("实收金额：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getPaidinCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           if (this.memberCardRechargeVo.getGiveCash() != null) {
             y1++;
             g2_1.drawString("赠送金额：", (float)x, (float)y + y1 * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getGiveCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
           }
 
           if (this.memberCardRechargeVo.getCashPledge() != null) {
             y1++;
             g2_1.drawString("押金：", (float)x, (float)y + y1 * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getCashPledge()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
           }
 
           if (this.memberCardRechargeVo.getAddMemberIntegral() != null) {
             y1++;
             g2_1.drawString("增加积分：", (float)x, (float)y + y1 * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getAddMemberIntegral()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
           }
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + y1 * heigth), (int)x + 183, (int)(y + y1 * heigth));
 
           y1++;
           g2_1.drawString("余额：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getBalance()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           g2_1.drawString("积分：", (float)x + leftOffset2, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getMemberIntegral()).toString(), (float)x + leftOffset2 + 30.0F, (float)y + y1 * heigth);
 
           y1++;
           y1++;
           if (TrueFalseEnum.TRUE.getCode().equals(this.memberCardRechargeVo.getIsDrawBill())) {
             drawStr = "已开发票";
             g2_1.drawString((String)drawStr, (float)x + 120.0F, (float)y + y1 * heigth);
           }
 
           y += down;
           g2_1.setFont(new Font("宋体", 0, 1));
           g2_1.drawString(".", (float)x, (float)y + y1 * heigth);
         } else if (PrinterTemplateEnum.C_58.getCode().equals(this.template))
         {
           g2_1.setFont(font);
 
           String title = StringUtils.isNotEmpty(this.printer.getTitle()) ? this.printer.getTitle() : this.memberCardRechargeVo.getRestName();
           FontMetrics fm = g2_1.getFontMetrics();
 
           StringUtil strUtil = new StringUtil();
           int y1 = 0;
           try {
             for (drawStr = strUtil.splitAsLength(title, 19, encoding).iterator(); ((Iterator)drawStr).hasNext(); ) { String t = (String)((Iterator)drawStr).next();
               int stringWidth = fm.stringWidth(t);
               int x_m = width / 2 - stringWidth / 2;
               g2_1.drawString(t, x_m < x ? (int)x : x_m, (float)y + (1 + y1) * heigth1);
               y1++; }
           }
           catch (UnsupportedEncodingException e)
           {
             System.out.println("会员卡充值，打印小票标题捕获异常：");
             e.printStackTrace();
           }
 
           g2_1.setFont(font2);
 
           y1++;
           y1++;
           String message = "欢迎您的光临！";
           g2_1.drawString(message, (float)x, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawString("会员卡号：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(this.memberCardRechargeVo.getCardNo(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawString("充值时间：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(this.sdf.format(this.memberCardRechargeVo.getRechargeTime()), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawString("操作人：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(this.memberCardRechargeVo.getOperator(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + y1 * heigth), (int)x + 183, (int)(y + y1 * heigth));
 
           y1++;
           g2_1.drawString("充值金额：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getRechargeCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawString("实收金额：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getPaidinCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           if (this.memberCardRechargeVo.getGiveCash() != null) {
             y1++;
             g2_1.drawString("赠送金额：", (float)x, (float)y + y1 * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getGiveCash()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
           }
 
           if (this.memberCardRechargeVo.getCashPledge() != null) {
             y1++;
             g2_1.drawString("押金：", (float)x, (float)y + y1 * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getCashPledge()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
           }
 
           if (this.memberCardRechargeVo.getAddMemberIntegral() != null) {
             y1++;
             g2_1.drawString("增加积分：", (float)x, (float)y + y1 * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getAddMemberIntegral()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
           }
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + y1 * heigth), (int)x + 183, (int)(y + y1 * heigth));
 
           y1++;
           g2_1.drawString("余额：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getBalance()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           y1++;
           g2_1.drawString("积分：", (float)x, (float)y + y1 * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.memberCardRechargeVo.getMemberIntegral()).toString(), (float)x + leftOffset1, (float)y + y1 * heigth);
 
           y1++;
           y1++;
           if (TrueFalseEnum.TRUE.getCode().equals(this.memberCardRechargeVo.getIsDrawBill())) {
             String drawStr1 = "已开发票";
             g2_1.drawString(drawStr1, (float)x + 80.0F, (float)y + y1 * heigth);
           }
 
           y += down;
           g2_1.setFont(new Font("宋体", 0, 1));
           g2_1.drawString(".", (float)x, (float)y + y1 * heigth);
         }
       }
       return 0;
     }
     return 1;
   }
 
   public MemberCardRechargeVo getMemberCardRechargeVo()
   {
     return this.memberCardRechargeVo;
   }
 
   public void setMemberCardRechargeVo(MemberCardRechargeVo memberCardRechargeVo) {
     this.memberCardRechargeVo = memberCardRechargeVo;
   }
 
   public Printable getPrintable()
   {
     return this;
   }
 
   public double getLength()
   {
     return 2000.0D;
   }
 }

