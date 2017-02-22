 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.fasterxml.jackson.core.JsonParseException;
 import com.fasterxml.jackson.databind.JsonMappingException;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrintParaments;
import com.ndlan.canyin.sharelogic.printer.PrintUtil;
import com.ndlan.canyin.sharelogic.util.StringUtil;
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.BillTypeEnum;
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
 import java.util.HashMap;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
 
 public class XiadanPrinter extends AbstractPrinter
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
   private int dishNote_len = 27;
   private int notes_len = 27;
 
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
     this.dishNote_len = 17;
     this.notes_len = 17;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_76(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_x_3 = (this.summary_x_2 + 75.0F);
     this.summary_x_4 = (this.summary_x_3 + 30.0F);
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 115.0F);
 
     this.columus = 4;
 
     this.dishName_len = 15;
     this.dishNote_len = 27;
     this.notes_len = 27;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_80(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_x_3 = (this.summary_x_2 + 80.0F);
     this.summary_x_4 = (this.summary_x_3 + 30.0F);
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 125.0F);
 
     this.dishName_len = 15;
     this.dishNote_len = 27;
     this.notes_len = 27;
 
     this.columus = 2;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     Font font_zy = new Font("宋体", 0, 9);
     Font font_title = new Font("宋体", 0, 20);
     Font font_dish = new Font("宋体", 1, 14);
     Font font_dishes = new Font("宋体", 0, 13);
     Font noteFont = new Font("宋体", 1, 12);
 
     gh.setStroke(PrintParaments.PAY_BASIC_STROKE);
 
     float heigth = font_zy.getSize2D() + 2.0F;
 
     float print_y = (float)this.y;
 
     print_y = (float)(print_y + (30.0D + this.y));
     String tabNo = this.dinerBillVo.getTabName();
     if (StringUtils.isNotEmpty(tabNo))
     {
       tabNo = tabNo;
     }
     else if (this.dinerBillVo.getBillType().equals(BillTypeEnum.KUAICAN_BILL.getCode()))
     {
       tabNo = "快餐";
     }
     else
     {
       tabNo = "外卖";
     }
 
     String title = tabNo;
 
     title = title + ((this.printParaments != null) && (this.printParaments.get("isAddDishes") != null) && 
       (((Boolean)this.printParaments.get("isAddDishes")).booleanValue()) ? 
       "(加菜)" : "");
 
     gh.setFont(font_title);
     gh.drawString(title, this.summary_x_1, print_y);
 
     print_y += 20.0F;
     gh.setFont(font_zy);
     gh.drawString("账单编号：", this.summary_x_1, print_y);
     gh.drawString(this.dinerBillVo.getBillNo(), this.summary_x_2, print_y);
 
     print_y += heigth;
     gh.drawString("下单时间：", this.summary_x_1, print_y);
     gh.drawString(PrintParaments.PAY_DATE_FORMAT.format(this.dinerBillVo.getOrderTime()), this.summary_x_2, print_y);
 
     if (this.columus == 4) {
       gh.drawString("操作人 ：", this.summary_x_3, print_y);
       if (this.dinerBillVo.getWaiterName() != null) {
         gh.drawString(this.dinerBillVo.getOperatorName(), this.summary_x_4, print_y);
       }
     }
     else if (this.columus == 2) {
       print_y += heigth;
       gh.drawString("操作人：", this.summary_x_1, print_y);
       if (this.dinerBillVo.getWaiterName() != null) {
         gh.drawString(this.dinerBillVo.getOperatorName(), this.summary_x_2, print_y);
       }
 
     }
 
     print_y += 5.0F;
     gh.drawLine((int)this.x - 2, (int)print_y, (int)this.x + 183, (int)print_y);
 
     List<DinerBillDisheVo> dinerBillDisheVos = this.dinerBillVo.getDinerBillDishes();
     this.strUtil = new StringUtil();
     String notes;
     for (DinerBillDisheVo dinerBillDisheVo : dinerBillDisheVos) {
       gh.setFont(font_dish);
       String str_dishesName;
       if (TrueFalseEnum.TRUE.getCode().equals(dinerBillDisheVo.getIsSet())) {
         String str_dishesName1;
         try { print_y += 16.0F;
           gh.drawString(PrintUtil.floatToString(dinerBillDisheVo.getUnitNum()) + dinerBillDisheVo.getUnitName(), this.dishes_x_2, print_y);
           ArrayList listDishesName = this.strUtil.splitAsLength(dinerBillDisheVo.getDishesName(), this.dishName_len, this.encoding);
           for (int i = 0; i < listDishesName.size(); i++) {
             str_dishesName1 = (String)listDishesName.get(i);
             gh.drawString(str_dishesName1, this.dishes_x_1, print_y);
             if (i < listDishesName.size() - 1)
               print_y += 16.0F;
           }
         } catch (UnsupportedEncodingException e)
         {
           e.printStackTrace();
         }
 
         List<DinerBillDisheVo> dishesSetDishesList = dinerBillDisheVo.getDishesSetDishesList();
         gh.setFont(font_dishes);
         for (DinerBillDisheVo dbd : dishesSetDishesList) {
           String dName = " -" + dbd.getDishesName();
 
           print_y += 16.0F;
           gh.drawString(PrintUtil.floatToString(dbd.getUnitNum()) + dbd.getUnitName(), this.dishes_x_2, print_y);
           try {
             ArrayList listDishesName = this.strUtil.splitAsLength(dName, this.dishName_len, this.encoding);
             for (int i = 0; i < listDishesName.size(); i++) {
               String str_dishesName2 = (String)listDishesName.get(i);
               gh.drawString(str_dishesName2, this.dishes_x_1, print_y);
               if (i < listDishesName.size() - 1)
                 print_y += 16.0F;
             }
           }
           catch (UnsupportedEncodingException e) {
             e.printStackTrace();
           }
         }
         gh.setFont(font_dish);
       } else {
         print_y += 16.0F;
         gh.drawString(PrintUtil.floatToString(dinerBillDisheVo.getUnitNum()) + dinerBillDisheVo.getUnitName(), this.dishes_x_2, print_y);
         try {
           ArrayList listDishesName = this.strUtil.splitAsLength(dinerBillDisheVo.getDishesName(), this.dishName_len, this.encoding);
           for (int i = 0; i < listDishesName.size(); i++) {
             str_dishesName = (String)listDishesName.get(i);
             gh.drawString(str_dishesName, this.dishes_x_1, print_y);
             if (i < listDishesName.size() - 1)
               print_y += 16.0F;
           }
         }
         catch (UnsupportedEncodingException e) {
           e.printStackTrace();
         }
       }
       gh.setFont(noteFont);
 
       notes = dinerBillDisheVo.getNotes() != null ? dinerBillDisheVo.getNotes().trim() : null;
       if (StringUtils.isNotEmpty(notes)) {
         try {
           for (String str_dishesName3 : this.strUtil.splitAsLength(notes, this.dishNote_len, this.encoding)) {
             int y = (int)print_y;
             print_y += 15.0F;
             gh.setBackground(Color.black);
             gh.clearRect((int)this.dishes_x_1, y + 1, 500, (int)print_y - y + 3);
             gh.setPaint(Color.WHITE);
             gh.drawString(str_dishesName3, this.dishes_x_1, print_y);
           }
         } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
         }
       }
       gh.setPaint(Color.BLACK);
       print_y += 5.0F;
     }
 
     print_y += 5.0F;
     gh.setFont(font_zy);
     gh.drawLine((int)this.x - 2, (int)print_y, (int)this.x + 183, (int)print_y);
 
     gh.setFont(noteFont);
     String notes2 = this.dinerBillVo.getNotes() != null ? this.dinerBillVo.getNotes().trim() : null;
     if (StringUtils.isNotEmpty(notes2)) {
       print_y += 15.0F;
       gh.drawString("整单备注：", this.dishes_x_1, print_y);
       try {
         for (String str : this.strUtil.splitAsLength(notes2, this.notes_len, this.encoding)) {
           int y = (int)print_y;
           print_y += 15.0F;
           gh.setBackground(Color.black);
           gh.clearRect((int)this.dishes_x_1, y + 2, 500, (int)print_y - y + 3);
           gh.setPaint(Color.WHITE);
           gh.drawString(str, this.dishes_x_1, print_y);
         }
       } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
       }
     }
     gh.setPaint(Color.BLACK);
 
     String lastLine = "";
 
     lastLine = lastLine + this.printer.getName();
 
     if ((this.printParaments != null) && (this.printParaments.get("isRePrint") != null) && (((Boolean)this.printParaments.get("isRePrint")).booleanValue())) {
       lastLine = lastLine + "补打小票 ";
     }
 
     if (this.printer.getPrintTimes() > 1) {
       lastLine = lastLine + "第" + this.printerTime + "联";
     }
 
     print_y += 15.0F;
     gh.setFont(noteFont);
     gh.drawString(lastLine, (int)this.x, (int)print_y);
 
     print_y = (float)(print_y + this.d);
     gh.setFont(new Font("宋体", 0, 1));
     gh.drawString(".", this.dishes_x_1, print_y);
 
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
     length += getDinerBillVo().getDinerBillDishes().size() * 7;
     return length;
   }
 }

