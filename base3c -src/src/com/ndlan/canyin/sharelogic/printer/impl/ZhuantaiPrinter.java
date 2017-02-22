 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.utils.DateProvider;
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
 
 public class ZhuantaiPrinter extends AbstractPrinter
   implements Printable
 {
   private DinerBillVo dinerBillVo;
   PageFormat pf = new PageFormat();
 
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
     throws PrinterException
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 
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
       if (PrinterTemplateEnum.C_80.getCode().equals(this.template))
       {
         g2_1.setFont(font);
         g2_1.drawString("转台", (float)x + 70.0F, (float)y + 1.0F * heigth1);
         g2_1.setFont(font2);
 
         int y1 = 0;
         g2_1.drawString("账单编号：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.dinerBillVo.getBillNo(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("操作时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(sdf.format(DateProvider.DEFAULT.getDate()), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         g2_1.drawString("服务员：", (float)x + leftOffset2 - 5.0F, (float)y + (5.5F + y1) * heigth);
         if (this.dinerBillVo.getWaiterName() != null) {
           g2_1.drawString(this.dinerBillVo.getWaiterName(), (float)x + leftOffset2 + 35.0F, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         y1++;
         g2_1.setFont(new Font("宋体", 0, 14));
         String oldTabName = this.dinerBillVo.getOldTabName() == null ? "" : this.dinerBillVo.getOldTabName();
         String newTableName = this.dinerBillVo.getTabName() == null ? "" : this.dinerBillVo.getTabName();
         g2_1.drawString(oldTabName + "转到" + newTableName + "  ", (float)x + 20.0F, (float)y + (5.5F + y1) * heigth);
 
         down += (float)y + (5.5F + y1) * heigth;
       } else if (PrinterTemplateEnum.C_76.getCode().equals(this.template))
       {
         g2_1.setFont(font);
         g2_1.drawString("转台", (float)x + 70.0F, (float)y + 1.0F * heigth1);
         g2_1.setFont(font2);
 
         leftOffset1 += 5;
 
         int y1 = 0;
         g2_1.drawString("账单编号：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.dinerBillVo.getBillNo(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("操作时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(sdf.format(DateProvider.DEFAULT.getDate()), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("服务员：", (float)x, (float)y + (5.5F + y1) * heigth);
         if (this.dinerBillVo.getWaiterName() != null) {
           g2_1.drawString(this.dinerBillVo.getWaiterName(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         y1++;
         g2_1.setFont(new Font("宋体", 0, 14));
         String oldTableName = this.dinerBillVo.getOldTabName() == null ? "" : this.dinerBillVo.getOldTabName();
         String newTableName = this.dinerBillVo.getTabName() == null ? "" : this.dinerBillVo.getTabName();
         g2_1.drawString(oldTableName + "转到" + newTableName, (float)x, (float)y + (5.5F + y1) * heigth);
 
         down += (float)y + (5.5F + y1) * heigth;
       }
       else if (PrinterTemplateEnum.C_58.getCode().equals(this.template))
       {
         g2_1.setFont(font);
         g2_1.drawString("转台", (float)x + 50.0F, (float)y + 1.0F * heigth1);
         g2_1.setFont(font2);
 
         int y1 = 0;
         g2_1.drawString("账单编号：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.dinerBillVo.getBillNo(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("操作时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(sdf.format(DateProvider.DEFAULT.getDate()), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("服务员：", (float)x, (float)y + (5.5F + y1) * heigth);
         if (this.dinerBillVo.getWaiterName() != null) {
           g2_1.drawString(this.dinerBillVo.getWaiterName(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         y1++;
         g2_1.setFont(new Font("宋体", 0, 14));
         String oldTableName = this.dinerBillVo.getOldTabName() == null ? "" : this.dinerBillVo.getOldTabName();
         String newTableName = this.dinerBillVo.getTabName() == null ? "" : this.dinerBillVo.getTabName();
         g2_1.drawString(oldTableName, (float)x, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         y1++;
         g2_1.drawString("转到", (float)x, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         y1++;
         g2_1.drawString(newTableName, (float)x, (float)y + (5.5F + y1) * heigth);
 
         down += (float)y + (5.5F + y1) * heigth;
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
 
   public Printable getPrintable()
   {
     return this;
   }
 
   public double getLength()
   {
     return 600.0D;
   }
 }

