 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.fasterxml.jackson.core.JsonParseException;
 import com.fasterxml.jackson.databind.JsonMappingException;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrintParaments;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.utils.DateProvider;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.print.PageFormat;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.io.IOException;
 import java.io.PrintStream;
import java.text.SimpleDateFormat;
 
 public class BingtaiPrinter extends AbstractPrinter
   implements Printable
 {
   private DinerBillVo dinerBillVo;
   private double x = 0.0D;
   private double y = 0.0D;
   private double d = 0.0D;
 
   private Font font = new Font("宋体", 0, 14);
   private Font font2 = new Font("宋体", 0, 9);
   private Font font3 = new Font("宋体", 0, 14);
 
   private double column_1 = this.x;
   private double column_2 = this.column_1 + 40.0D;
   private double column_3 = this.column_2 + 75.0D;
   private double column_4 = this.column_3 + 35.0D;
 
   private double column_main = this.x + 20.0D;
 
   private int columus = 1;
 
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
     throws PrinterException
   {
     Graphics2D gh = (Graphics2D)graphics;
 
     gh.setColor(Color.black);
 
     gh.setStroke(PrintParaments.PAY_BASIC_STROKE);
 
     this.x = (this.margin_left != null ? this.margin_left.doubleValue() : 0.0D);
     this.y = (this.margin_up != null ? this.margin_up.doubleValue() : 0.0D);
     this.d = (this.margin_down != null ? this.margin_down.doubleValue() : 0.0D);
 
     switch (pageIndex) {
     case 0:
       try {
         if (PrinterTemplateEnum.C_80.getCode().equals(this.template))
           gh = getGraphics2D_80(gh);
         else if (PrinterTemplateEnum.C_76.getCode().equals(this.template))
           gh = getGraphics2D_76(gh);
         else if (PrinterTemplateEnum.C_58.getCode().equals(this.template))
           gh = getGraphics2D_58(gh);
       }
       catch (Exception e)
       {
         System.out.println("并台打印捕获异常：");
         e.printStackTrace();
       }
       return 0;
     }
     return 1;
   }
 
   private Graphics2D getGraphics2D_80(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.column_1 = this.x;
     this.column_2 = (this.column_1 + 40.0D);
     this.column_3 = (this.column_2 + 75.0D);
     this.column_4 = (this.column_3 + 35.0D);
 
     this.column_main = (this.x + 20.0D);
 
     this.columus = 1;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_76(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.column_1 = this.x;
     this.column_2 = (this.column_1 + 40.0D);
     this.column_3 = (this.column_2 + 75.0D);
     this.column_4 = (this.column_3 + 35.0D);
 
     this.column_main = (this.x + 18.0D);
 
     this.columus = 2;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_58(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.column_1 = this.x;
     this.column_2 = (this.column_1 + 40.0D);
     this.column_3 = (this.column_2 + 75.0D);
     this.column_4 = (this.column_3 + 35.0D);
 
     this.column_main = this.x;
 
     this.columus = 2;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     float heigth1 = this.font.getSize2D();
     float heigth2 = this.font2.getSize2D() + 2.0F;
     float heigth3 = this.font3.getSize2D() + 2.0F;
 
     float print_y = (float)this.y;
 
     print_y += heigth1;
     gh.setFont(this.font);
 
     FontMetrics fm = gh.getFontMetrics();
     int width = (int)(getWidth(this.template).doubleValue() - 15.0D - this.x);
     int stringWidth = fm.stringWidth("并台");
     int x_m = width / 2 - stringWidth / 2;
     gh.drawString("并台", x_m, print_y);
 
     print_y = (float)(print_y + 3.5D * heigth2);
     gh.setFont(this.font2);
     gh.drawString("操作时间：", (float)this.column_1, print_y);
     gh.drawString(PrintParaments.PAY_DATE_FORMAT.format(DateProvider.DEFAULT.getDate()), (float)this.column_2, print_y);
 
     float c1 = (float)this.column_3;
     float c2 = (float)this.column_4;
     if (this.columus == 2) {
       c1 = (float)this.column_1;
       c2 = (float)this.column_2;
       print_y += heigth2;
     }
     gh.drawString("服务员：", c1, print_y);
     if (this.dinerBillVo.getWaiterName() != null) {
       gh.drawString(this.dinerBillVo.getWaiterName(), c2, print_y);
     }
 
     print_y += 2.0F * heigth2;
     gh.setFont(this.font3);
     String oldTableName = this.dinerBillVo.getOldTabName() == null ? "" : this.dinerBillVo.getOldTabName();
     String oldBillNo = this.dinerBillVo.getOldBillNo() == null ? "" : this.dinerBillVo.getOldBillNo();
     gh.drawString(oldTableName + " " + oldBillNo + "单", (float)this.column_main, print_y);
 
     print_y += heigth3;
     fm = gh.getFontMetrics();
     width = (int)(getWidth(this.template).doubleValue() - 15.0D - this.x);
     stringWidth = fm.stringWidth("并入");
     x_m = width / 2 - stringWidth / 2;
     gh.drawString("并入", x_m, print_y);
 
     print_y += heigth3;
     gh.setFont(new Font("宋体", 0, 14));
     String newTableName = this.dinerBillVo.getTabName() == null ? "" : this.dinerBillVo.getTabName();
     String billNo = this.dinerBillVo.getBillNo() == null ? "" : this.dinerBillVo.getBillNo();
     gh.drawString(newTableName + " " + billNo + "单", (float)this.column_main, print_y);
 
     print_y = (float)(print_y + this.d);
     gh.setFont(new Font("宋体", 0, 1));
     gh.drawString(".", (float)this.column_1, print_y);
 
     return gh;
   }
 
   public DinerBillVo getDinerBillVo() {
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
     double length = 600.0D;
     double down = this.margin_down != null ? this.margin_down.doubleValue() : 0.0D;
     double up = this.margin_up != null ? this.margin_up.doubleValue() : 0.0D;
     length = length + down + up;
     return length;
   }
 }

