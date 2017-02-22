 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.fasterxml.jackson.core.JsonParseException;
 import com.fasterxml.jackson.databind.JsonMappingException;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrintParaments;
import com.ndlan.canyin.sharelogic.printer.PrintUtil;
import com.ndlan.canyin.sharelogic.util.StringUtil;
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.print.PageFormat;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.io.IOException;
 import java.io.UnsupportedEncodingException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
 
 public class TuicaiPrinter extends AbstractPrinter
   implements Printable
 {
   private DinerBillVo dinerBillVo;
   private double x = 0.0D;
   private double y = 0.0D;
   private double d = 0.0D;
 
   private float summary_x_1 = (float)this.x;
   private float summary_x_2 = this.summary_x_1 + 40.0F;
   private float summary_x_3 = this.summary_x_2 + 80.0F;
   private float summary_x_4 = this.summary_x_3 + 30.0F;
 
   private float dishes_x_1 = (float)this.x;
   private float dishes_x_2 = this.dishes_x_1 + 125.0F;
 
   private int dishName_len = 15;
   private int dishNote_len = 31;
 
   private int notes_len = 33;
 
   private int columus = 4;
   private StringUtil strUtil;
   String encoding = "GB2312";
 
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
     throws PrinterException
   {
     Graphics2D gh = (Graphics2D)graphics;
 
     gh.setColor(Color.black);
 
     Font font = new Font("宋体", 0, 9);
     gh.setFont(font);
 
     gh.setStroke(PrintParaments.PAY_BASIC_STROKE);
 
     this.x = this.margin_left.doubleValue();
     this.y = this.margin_up.doubleValue();
     this.d = this.margin_down.doubleValue();
 
     switch (pageIndex) {
     case 0:
       try {
         if (PrinterTemplateEnum.C_80.getCode().equals(this.template)) {
           gh = getGraphics2D_80(gh);
         }
         else if (PrinterTemplateEnum.C_76.getCode().equals(this.template)) {
           gh = getGraphics2D_76(gh);
         }
         else if (PrinterTemplateEnum.C_58.getCode().equals(this.template)) {
           gh = getGraphics2D_58(gh);
         }
         else
           gh = getGraphics2D(gh);
       }
       catch (Exception localException)
       {
       }
       return 0;
     }
     return 1;
   }
 
   private Graphics2D getGraphics2D_58(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 85.0F);
 
     this.columus = 2;
 
     this.dishName_len = 11;
     this.dishNote_len = 23;
     this.notes_len = 23;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_76(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_x_3 = (this.summary_x_2 + 70.0F);
     this.summary_x_4 = (this.summary_x_3 + 30.0F);
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 115.0F);
 
     this.columus = 2;
 
     this.dishName_len = 15;
     this.dishNote_len = 31;
     this.notes_len = 32;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_80(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_x_3 = (this.summary_x_2 + 80.0F);
     this.summary_x_4 = (this.summary_x_3 + 45.0F);
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 125.0F);
 
     this.dishName_len = 15;
     this.dishNote_len = 31;
     this.notes_len = 33;
 
     this.columus = 4;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     Font font_zy = new Font("宋体", 0, 9);
     Font font_title = new Font("宋体", 0, 20);
     Font font_dish = new Font("宋体", 1, 15);
     Font font_dishes = new Font("宋体", 0, 15);
     Font noteFont = new Font("宋体", 1, 10);
 
     gh.setStroke(PrintParaments.PAY_BASIC_STROKE);
 
     float heigth = font_zy.getSize2D() + 2.0F;
     float height_dish = font_dish.getSize2D() + 1.0F;
 
     float print_y = (float)this.y;
 
     print_y = (float)(print_y + (30.0D + this.y));
     String title = StringUtils.isNotEmpty(this.dinerBillVo.getTabName()) ? this.dinerBillVo.getTabName() : "外卖";
     gh.setFont(font_title);
     gh.drawString(title, this.summary_x_1, print_y);
 
     print_y += 20.0F;
     gh.setFont(font_zy);
     gh.drawString("账单编号：", this.summary_x_1, print_y);
     gh.drawString(this.dinerBillVo.getBillNo(), this.summary_x_2, print_y);
 
     if (this.columus == 4) {
       gh.drawString("服务员 ：", this.summary_x_3, print_y);
       if (this.dinerBillVo.getWaiterName() != null)
         gh.drawString(this.dinerBillVo.getWaiterName(), this.summary_x_4, print_y);
     }
     else if (this.columus == 2) {
       print_y += heigth;
       gh.drawString("服务员：", this.summary_x_1, print_y);
       if (this.dinerBillVo.getWaiterName() != null) {
         gh.drawString(this.dinerBillVo.getWaiterName(), this.summary_x_2, print_y);
       }
     }
 
     print_y += heigth;
     gh.drawString("下单时间：", this.summary_x_1, print_y);
     gh.drawString(PrintParaments.PAY_DATE_FORMAT.format(this.dinerBillVo.getOrderTime()), this.summary_x_2, print_y);
 
     if (this.columus == 4) {
       gh.drawString("退菜时间 ：", this.summary_x_3, print_y);
       if (this.dinerBillVo.getWaiterName() != null)
         gh.drawString(PrintParaments.CANCLE_DATE_FORMAT.format(this.dinerBillVo.getCancleTime()), this.summary_x_4, print_y);
     }
     else if (this.columus == 2) {
       print_y += heigth;
       gh.drawString("退菜时间：", this.summary_x_1, print_y);
       if (this.dinerBillVo.getWaiterName() != null) {
         gh.drawString(PrintParaments.CANCLE_DATE_FORMAT.format(this.dinerBillVo.getCancleTime()), this.summary_x_2, print_y);
       }
 
     }
 
     print_y += 5.0F;
     gh.drawLine((int)this.x - 2, (int)print_y, (int)this.x + 183, (int)print_y);
 
     List<DinerBillDisheVo> dinerBillDisheVos = this.dinerBillVo.getDinerBillDishes();
     this.strUtil = new StringUtil();
     for (DinerBillDisheVo dinerBillDisheVo : dinerBillDisheVos)
     {
       gh.setFont(font_dish);
       Object dbd;
       if (TrueFalseEnum.TRUE.getCode().equals(dinerBillDisheVo.getIsSet())) {
         String dishesName = "+" + dinerBillDisheVo.getDishesName();
         try
         {
           print_y += height_dish;
           gh.drawString(PrintUtil.floatToString(dinerBillDisheVo.getCancleNum()) + dinerBillDisheVo.getUnitName(), this.dishes_x_2, print_y);
           for (String str_dishesName : this.strUtil.splitAsLength(dishesName, this.dishName_len, this.encoding)) {
             gh.drawString(str_dishesName, this.dishes_x_1, print_y);
             print_y += height_dish;
           }
           print_y -= height_dish;
         } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
         }
 
         List dishesSetDishesList = dinerBillDisheVo.getDishesSetDishesList();
         gh.setFont(font_dishes);
         for (Iterator localIterator3 = dishesSetDishesList.iterator(); localIterator3.hasNext(); ) { dbd = (DinerBillDisheVo)localIterator3.next();
           String dName = " -" + ((DinerBillDisheVo)dbd).getDishesName();
 
           print_y += height_dish;
           gh.drawString(PrintUtil.floatToString(((DinerBillDisheVo)dbd).getUnitNum()) + ((DinerBillDisheVo)dbd).getUnitName(), this.dishes_x_2, print_y);
           try {
             for (String str_dishesName : this.strUtil.splitAsLength(dName, this.dishName_len, this.encoding)) {
               gh.drawString(str_dishesName, this.dishes_x_1, print_y);
               print_y += height_dish;
             }
             print_y -= height_dish;
           } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
           }
         }
         gh.setFont(font_dish);
       } else {
         String dishesName = dinerBillDisheVo.getDishesName();
         print_y += height_dish;
         gh.drawString(PrintUtil.floatToString(dinerBillDisheVo.getCancleNum()) + dinerBillDisheVo.getUnitName(), this.dishes_x_2, print_y);
         try {
           for (dbd = this.strUtil.splitAsLength(dishesName, this.dishName_len, this.encoding).iterator(); ((Iterator)dbd).hasNext(); ) { String str_dishesName = (String)((Iterator)dbd).next();
             gh.drawString(str_dishesName, this.dishes_x_1, print_y);
             print_y += height_dish;
           }
           print_y -= height_dish;
         } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
         }
       }
       gh.setFont(noteFont);
       if (!StringUtils.isNotEmpty(dinerBillDisheVo.getNotes()))
         continue;
       String notes = dinerBillDisheVo.getNotes();
       try {
         for (dbd = this.strUtil.splitAsLength(notes, this.dishNote_len, this.encoding).iterator(); ((Iterator)dbd).hasNext(); ) { String str_dishesName = (String)((Iterator)dbd).next();
           print_y += 12.0F;
           gh.drawString(str_dishesName, this.dishes_x_1, print_y); }
       }
       catch (UnsupportedEncodingException e) {
         e.printStackTrace();
       }
 
     }
 
     print_y += 10.0F;
     gh.setFont(font_zy);
     gh.drawLine((int)this.x - 2, (int)print_y, (int)this.x + 183, (int)print_y);
 
     print_y += height_dish;
     gh.setFont(noteFont);
     gh.drawString(this.printer.getName(), (int)this.x, (int)print_y);
 
     print_y += height_dish;
     print_y = (float)(print_y + this.d);
     gh.drawString(".", this.dishes_x_1, print_y);
 
     return (Graphics2D)gh;
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
     length += getDinerBillVo().getDinerBillDishes().size() * 7;
     return length;
   }
 }

