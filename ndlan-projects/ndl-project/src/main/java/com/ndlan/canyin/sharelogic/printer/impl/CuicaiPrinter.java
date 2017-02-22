 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.BasicStroke;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.print.PageFormat;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
 
 public class CuicaiPrinter extends AbstractPrinter
   implements Printable
 {
   private DinerBillVo dinerBillVo;
   private String cuiCaiType;
   PageFormat pf = new PageFormat();
 
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
     throws PrinterException
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
     SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
 
     Graphics2D g2_1 = (Graphics2D)graphics;
 
     g2_1.setColor(Color.black);
 
     switch (pageIndex)
     {
     case 0:
       Font font2 = new Font("宋体", 0, 9);
 
       g2_1.setFont(font2);
       float[] dash1 = { 2.0F };
 
       g2_1.setStroke(new BasicStroke(0.5F, 0, 0, 2.0F, dash1, 0.0F));
 
       double x = this.margin_left != null ? this.margin_left.doubleValue() : 0.0D;
       double y = this.margin_up != null ? this.margin_up.doubleValue() : 0.0D;
       double down = this.margin_down != null ? this.margin_down.doubleValue() : 0.0D;
 
       float heigth = font2.getSize2D() + 2.0F;
       int leftOffset1 = 40;
       int leftOffset2 = 120;
 
       float line_y = (float)y;
 
       if (PrinterTemplateEnum.C_80.getCode().equals(this.template))
       {
         Font titleFont = new Font("宋体", 0, 20);
         g2_1.setFont(titleFont);
         String title = this.dinerBillVo.getRestName();
         if (this.dinerBillVo.getTabName() != null) {
           title = this.dinerBillVo.getTabName();
         }
         else {
           title = "外卖";
         }
 
         line_y += 30.0F;
         g2_1.drawString(title, (float)x, line_y);
 
         g2_1.setFont(font2);
 
         int y1 = 0;
         g2_1.drawString("账单编号：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.dinerBillVo.getBillNo(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         g2_1.drawString("服务员：", (float)x + leftOffset2 - 5.0F, (float)y + (5.5F + y1) * heigth);
         if (this.dinerBillVo.getWaiterName() != null) {
           g2_1.drawString(this.dinerBillVo.getWaiterName(), (float)x + leftOffset2 + 35.0F, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         g2_1.drawString("下单时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(sdf.format(this.dinerBillVo.getOrderTime()), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         g2_1.drawString("催菜时间：", (float)x + leftOffset2 - 5.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(sdf2.format(this.dinerBillVo.getUrgeTime()), (float)x + leftOffset2 + 35.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         y1++;
         g2_1.setFont(new Font("宋体", 0, 14));
         if ("URGEALL".equalsIgnoreCase(this.cuiCaiType)) {
           g2_1.drawString("客人催菜：全部未上菜肴", (float)x + 20.0F, (float)y + (5.5F + y1) * heigth);
         } else {
           g2_1.drawString("客人催菜：", (float)x + 20.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.dinerBillVo.getDinerBillDisheVo().getDishesName(), (float)x + 85.0F, (float)y + (5.5F + y1) * heigth);
         }
         down += (float)y + (5.5F + y1) * heigth;
       } else if (PrinterTemplateEnum.C_76.getCode().equals(this.template))
       {
         Font titleFont = new Font("宋体", 0, 20);
         g2_1.setFont(titleFont);
         String title = this.dinerBillVo.getRestName();
         if (this.dinerBillVo.getTabName() != null) {
           title = this.dinerBillVo.getTabName();
         }
         else {
           title = "外卖";
         }
         leftOffset1 += 5;
         leftOffset2 -= 15;
         line_y += 30.0F;
         g2_1.drawString(title, (float)x, line_y);
 
         g2_1.setFont(font2);
 
         int y1 = 0;
         g2_1.drawString("账单编号：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.dinerBillVo.getBillNo(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         g2_1.drawString("服务员：", (float)x + leftOffset2 - 5.0F, (float)y + (5.5F + y1) * heigth);
         if (this.dinerBillVo.getWaiterName() != null) {
           g2_1.drawString(this.dinerBillVo.getWaiterName(), (float)x + leftOffset2 + 35.0F, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         g2_1.drawString("下单时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(sdf.format(this.dinerBillVo.getOrderTime()), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("催菜时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(sdf2.format(this.dinerBillVo.getUrgeTime()), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         y1++;
         g2_1.setFont(new Font("宋体", 0, 14));
         if ("URGEALL".equalsIgnoreCase(this.cuiCaiType)) {
           g2_1.drawString("客人催菜：全部未上菜肴", (float)x, (float)y + (5.5F + y1) * heigth);
         } else {
           g2_1.drawString("客人催菜：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.dinerBillVo.getDinerBillDisheVo().getDishesName(), (float)x + 65.0F, (float)y + (5.5F + y1) * heigth);
         }
         down += (float)y + (5.5F + y1) * heigth;
       } else if (PrinterTemplateEnum.C_58.getCode().equals(this.template))
       {
         Font titleFont = new Font("宋体", 0, 20);
         g2_1.setFont(titleFont);
         String title = this.dinerBillVo.getRestName();
         if (this.dinerBillVo.getTabName() != null) {
           title = this.dinerBillVo.getTabName();
         }
         else {
           title = "外卖";
         }
 
         line_y += 30.0F;
         g2_1.drawString(title, (float)x, line_y);
 
         g2_1.setFont(font2);
 
         line_y += 20.0F;
         g2_1.drawString("账单编号：", (float)x, line_y);
         g2_1.drawString(this.dinerBillVo.getBillNo(), (float)x + leftOffset1, line_y);
 
         line_y += 10.0F;
         g2_1.drawString("服务员：", (float)x, line_y);
         if (this.dinerBillVo.getWaiterName() != null) {
           g2_1.drawString(this.dinerBillVo.getWaiterName(), (float)x + leftOffset1, line_y);
         }
 
         line_y += 10.0F;
         g2_1.drawString("下单时间：", (float)x, line_y);
         g2_1.drawString(sdf.format(this.dinerBillVo.getOrderTime()), (float)x + leftOffset1, line_y);
 
         line_y += 10.0F;
         g2_1.drawString("催菜时间：", (float)x, line_y);
         g2_1.drawString(sdf2.format(this.dinerBillVo.getUrgeTime()), (float)x + leftOffset1, line_y);
 
         line_y += 20.0F;
 
         Font disheFont = new Font("宋体", 0, 14);
         g2_1.setFont(disheFont);
         if ("URGEALL".equalsIgnoreCase(this.cuiCaiType)) {
           g2_1.drawString("客人催菜：全部未上菜肴", (float)x, line_y);
         } else {
           g2_1.drawString("客人催菜：", (float)x, line_y);
           line_y += 10.0F;
 
           float xiadanDisheHeigth = disheFont.getSize2D() + 3.0F;
           String dishesName = this.dinerBillVo.getDinerBillDisheVo().getDishesName();
           int wenziNum = 9;
           int length = dishesName.length() % wenziNum == 0 ? dishesName.length() / wenziNum : dishesName.length() / wenziNum + 1;
           for (int i = 0; i < length; i++) {
             int start = i * wenziNum;
             int end = i == length - 1 ? i * wenziNum + (dishesName.length() % wenziNum == 0 ? wenziNum : dishesName.length() % wenziNum) : i * wenziNum + wenziNum;
             String lineDishesName = dishesName.substring(start, end);
             line_y += xiadanDisheHeigth;
             g2_1.drawString(lineDishesName, (float)x, line_y);
           }
         }
 
         down += line_y;
       }
 
       g2_1.setFont(new Font("宋体", 0, 1));
       g2_1.drawString(".", (float)x, (float)down);
 
       return 0;
     }
     return 1;
   }
 
   public DinerBillVo getDinerBillVo()
   {
     return this.dinerBillVo;
   }
 
   public void setDinerBillVo(DinerBillVo dinerBillVo) {
     this.dinerBillVo = dinerBillVo;
   }
 
   public String getCuiCaiType() {
     return this.cuiCaiType;
   }
 
   public void setCuiCaiType(String cuiCaiType) {
     this.cuiCaiType = cuiCaiType;
   }
 
   public Printable getPrintable()
   {
     return this;
   }
 
   public double getLength()
   {
     return 600.0D;
   }
 }

