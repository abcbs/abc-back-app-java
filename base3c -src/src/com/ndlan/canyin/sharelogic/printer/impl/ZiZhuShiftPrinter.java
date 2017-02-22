 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.util.StringUtil;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.EmployeShiftVo;
 import java.awt.BasicStroke;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.print.Book;
 import java.awt.print.PageFormat;
 import java.awt.print.Paper;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.io.UnsupportedEncodingException;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
import java.util.List;
 
 public class ZiZhuShiftPrinter extends AbstractPrinter
   implements Printable
 {
   private EmployeShiftVo employeShiftVo;
   private int cancleCount;
   private BigDecimal cancleSum;
   private int settleCount;
   private BigDecimal settleSum;
   PageFormat pf = new PageFormat();
 
   protected Book getBook()
   {
     Book book = new Book();
 
     this.pf.setOrientation(1);
 
     Paper p = new Paper();
 
     int length = 600;
 
     Double width = getWidth(this.template);
     p.setSize(width.doubleValue(), length);
 
     int xy = 10;
     if (PrinterTemplateEnum.C_58.getCode().equals(this.template))
       xy = 2;
     else if (PrinterTemplateEnum.C_76.getCode().equals(this.template)) {
       xy = 0;
     }
     p.setImageableArea(xy, xy, width.doubleValue(), length);
 
     this.pf.setPaper(p);
 
     book.append(this, this.pf);
     return book;
   }
 
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
 
       double x = this.pf != null ? this.pf.getImageableX() : 10.0D;
       double y = this.pf != null ? this.pf.getImageableY() : 10.0D;
       float heigth = font2.getSize2D() + 2.0F;
       int leftOffset1 = 20;
       int leftOffset2 = 120;
       if (PrinterTemplateEnum.C_80.getCode().equals(this.template)) {
         float line_y = 0.0F;
         Font titleFont = new Font("宋体", 0, 20);
         g2_1.setFont(titleFont);
 
         int y1 = 0;
         y1++;
 
         float print_y = (float)y + (5.5F + y1) * heigth;
         FontMetrics fm = g2_1.getFontMetrics();
         int width = (int)(getWidth(PrinterTemplateEnum.C_80.getCode()).doubleValue() - 15.0D - x);
         StringUtil strUtil = new StringUtil();
         try {
           for (String t : strUtil.splitAsLength(this.printer.getTitle(), 20, "GB2312")) {
             int stringWidth = fm.stringWidth(t);
             int x_m = width / 2 - stringWidth / 2;
 
             g2_1.drawString(t, x_m, (int)print_y);
             print_y += 3.0F * titleFont.getSize2D() / 2.0F;
           }
         }
         catch (UnsupportedEncodingException e1) {
           e1.printStackTrace();
         }
 
         y1++;
         y1++;
         g2_1.setFont(font2);
         g2_1.drawString("菜品销售汇总报表", (float)x + leftOffset1 + 5.0F + 85.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         y1++;
         g2_1.drawString("上次交班时间：" + getEmployeShiftVo().getLastShiftTime(), (float)x + 5.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("本次交班时间：" + getEmployeShiftVo().getCurrentShiftTime(), (float)x + 5.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("结账总数：" + getSettleCount(), (float)x + 5.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("结账总额：" + getSettleSum(), (float)x + 5.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("撤单总数：" + getCancleCount(), (float)x + 5.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("撤单总额：" + getCancleSum(), (float)x + 5.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         y1++;
         g2_1.setFont(font2);
 
         g2_1.drawString("序号", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString("菜品", (float)x + 20.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString("数量", (float)x + 20.0F + 95.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString("金额", (float)x + 20.0F + 95.0F + 40.0F, (float)y + (5.5F + y1) * heigth);
 
         if ((this.employeShiftVo.getDinerBillDishes() != null) && 
           (this.employeShiftVo.getDinerBillDishes().size() > 0))
         {
           for (int i = 0; i < this.employeShiftVo.getDinerBillDishes().size(); i++)
           {
             y1++;
             DinerBillDisheVo e = (DinerBillDisheVo)this.employeShiftVo.getDinerBillDishes().get(i);
             g2_1.drawString(String.valueOf(i + 1), (float)x, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(e.getDishesName(), (float)x + 20.0F, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(BigDecimal.valueOf(e.getUnitNum().doubleValue())).toString(), (float)x + 20.0F + 95.0F, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(e.getRealCost().toString(), (float)x + 20.0F + 95.0F + 40.0F, (float)y + (5.5F + y1) * heigth);
           }
 
         }
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         y1++;
         g2_1.drawString("合计", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.employeShiftVo.getCurrentCashBalance(), (float)x + 20.0F + 95.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.employeShiftVo.getCurrentMoneySum().toString(), (float)x + 20.0F + 95.0F + 40.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         y1++;
         y1++;
         g2_1.drawString("G2", -100.0F, (float)y + (5.5F + y1) * heigth);
       }
 
       return 0;
     }
     return 1;
   }
 
   public Printable getPrintable()
   {
     return this;
   }
 
   public double getLength()
   {
     return 0.0D;
   }
 
   public EmployeShiftVo getEmployeShiftVo() {
     return this.employeShiftVo;
   }
 
   public void setEmployeShiftVo(EmployeShiftVo employeShiftVo) {
     this.employeShiftVo = employeShiftVo;
   }
 
   public int getCancleCount() {
     return this.cancleCount;
   }
 
   public void setCancleCount(int cancleCount) {
     this.cancleCount = cancleCount;
   }
 
   public BigDecimal getCancleSum() {
     return this.cancleSum;
   }
 
   public void setCancleSum(BigDecimal cancleSum) {
     this.cancleSum = cancleSum;
   }
 
   public int getSettleCount() {
     return this.settleCount;
   }
 
   public void setSettleCount(int settleCount) {
     this.settleCount = settleCount;
   }
 
   public BigDecimal getSettleSum() {
     return this.settleSum;
   }
 
   public void setSettleSum(BigDecimal settleSum) {
     this.settleSum = settleSum;
   }
 }

