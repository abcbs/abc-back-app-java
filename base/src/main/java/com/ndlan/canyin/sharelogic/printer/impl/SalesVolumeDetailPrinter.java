 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.fasterxml.jackson.core.JsonParseException;
 import com.fasterxml.jackson.databind.JsonMappingException;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrintParaments;
import com.ndlan.canyin.sharelogic.util.StringUtil;
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.DishesTypeEnum;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailDatasVo;
 import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailTitleVo;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.print.PageFormat;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.io.IOException;
 import java.io.UnsupportedEncodingException;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
 
 public class SalesVolumeDetailPrinter extends AbstractPrinter
   implements Printable
 {
   private PrintSalesVolumeDetailTitleVo titleInfo;
   private List<PrintSalesVolumeDetailDatasVo> detailInfos;
   private StringUtil strUtil;
   String encoding = "GB2312";
 
   private double x = 0.0D;
   private double y = 0.0D;
   private double d = 0.0D;
 
   private float summary_x_1 = (float)this.x;
   private float summary_x_2 = this.summary_x_1 + 40.0F;
   private float summary_row_spacing = 1.4F;
 
   private float dishes_x_1 = (float)this.x;
   private float dishes_x_2 = this.dishes_x_1 + 90.0F;
   private float dishes_x_3 = this.dishes_x_2 + 35.0F;
   private float ds_row_spacing = 1.0F;
   private int dishName_len = 19;
 
   private Font font_title = PrintParaments.PAY_80_TITLE_FONT;
   private Font font_dishes = PrintParaments.PAY_80_DISHES_FONT;
 
   private int title_len = 29;
 
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
       if (PrinterTemplateEnum.C_80.getCode().equals(this.template)) {
         try {
           gh = getGraphics2D_80(gh);
         }
         catch (JsonParseException e) {
           e.printStackTrace();
         }
         catch (JsonMappingException e) {
           e.printStackTrace();
         }
         catch (IOException e) {
           e.printStackTrace();
         }
       }
       else if (PrinterTemplateEnum.C_76.getCode().equals(this.template)) {
         try {
           gh = getGraphics2D_76(gh);
         }
         catch (JsonParseException e) {
           e.printStackTrace();
         }
         catch (JsonMappingException e) {
           e.printStackTrace();
         }
         catch (IOException e) {
           e.printStackTrace();
         }
       }
       else if (PrinterTemplateEnum.C_58.getCode().equals(this.template))
         try {
           gh = getGraphics2D_58(gh);
         }
         catch (JsonParseException e) {
           e.printStackTrace();
         }
         catch (JsonMappingException e) {
           e.printStackTrace();
         }
         catch (IOException e) {
           e.printStackTrace();
         }
       else {
         try
         {
           gh = getGraphics2D_80(gh);
         }
         catch (JsonParseException e) {
           e.printStackTrace();
         }
         catch (JsonMappingException e) {
           e.printStackTrace();
         }
         catch (IOException e) {
           e.printStackTrace();
         }
       }
       return 0;
     }
     return 1;
   }
 
   private Graphics2D getGraphics2D_80(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_row_spacing = 1.4F;
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 90.0F);
     this.dishes_x_3 = (this.dishes_x_2 + 40.0F);
     this.ds_row_spacing = 1.0F;
     this.dishName_len = 19;
 
     this.font_title = PrintParaments.PAY_58_TITLE_FONT;
     this.font_dishes = PrintParaments.PAY_80_DISHES_FONT;
 
     this.title_len = 25;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_76(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_row_spacing = 1.4F;
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 80.0F);
     this.dishes_x_3 = (this.dishes_x_2 + 40.0F);
     this.ds_row_spacing = 1.0F;
     this.dishName_len = 17;
 
     this.font_title = PrintParaments.PAY_76_TITLE_FONT;
     this.font_dishes = PrintParaments.PAY_76_DISHES_FONT;
 
     this.title_len = 22;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_58(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_row_spacing = 1.4F;
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 50.0F);
     this.dishes_x_3 = (this.dishes_x_2 + 23.0F);
     this.dishName_len = 11;
 
     this.font_title = PrintParaments.PAY_58_TITLE_FONT;
     this.font_dishes = PrintParaments.PAY_58_DISHES_FONT;
 
     this.title_len = 19;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     float height_title = this.font_title.getSize2D();
     float height_dishes = this.font_dishes.getSize2D();
 
     float print_y = 0.0F;
 
     print_y = (float)(print_y + (height_title + this.y));
     String title = this.titleInfo.getTitle();
 
     gh.setFont(this.font_title);
 
     FontMetrics fm = gh.getFontMetrics();
     int width = (int)(Double.valueOf(this.template).doubleValue() * 72.0D / 25.399999999999999D - 15.0D + this.margin_left.doubleValue());
     this.strUtil = new StringUtil();
     int x_m;
     for (String t : this.strUtil.splitAsLength(title, this.title_len, this.encoding)) {
       int stringWidth = fm.stringWidth(t);
       x_m = width / 2 - stringWidth / 2;
 
       gh.drawString(t, x_m > this.summary_x_1 ? x_m : this.summary_x_1, (int)print_y);
       print_y += 3.0F * height_title / 2.0F;
     }
 
     print_y -= 3.0F * height_title / 2.0F;
 
     gh.setFont(this.font_dishes);
 
     print_y += height_title * 3.0F / 2.0F;
 
     gh.drawString("统计时段开始：", this.summary_x_1, print_y);
     gh.drawString(this.titleInfo.getDateStart(), this.summary_x_2 + 20.0F, print_y);
 
     print_y += this.summary_row_spacing + height_dishes;
     gh.drawString("统计时段结束：", this.summary_x_1, print_y);
     gh.drawString(this.titleInfo.getDateEnd(), this.summary_x_2 + 20.0F, print_y);
 
     print_y += this.summary_row_spacing + height_dishes;
     gh.drawString("操作时间：", this.summary_x_1, print_y);
     gh.drawString(this.titleInfo.getOperationDate(), this.summary_x_2, print_y);
 
     print_y += this.summary_row_spacing + height_dishes;
     gh.drawString("操作人：", this.summary_x_1, print_y);
     gh.drawString(this.titleInfo.getOperationName(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
 
     print_y += this.summary_row_spacing + height_dishes;
     String desc = "";
     String dishTitle1 = "";
     if (DishesTypeEnum.DISHES.getCode().equals(this.titleInfo.getType())) {
       desc = "菜肴分类名称:";
       dishTitle1 = "菜肴名称";
     }
     else if (DishesTypeEnum.DISHES_SET.getCode().equals(this.titleInfo.getType())) {
       desc = "套餐分类名称:";
       dishTitle1 = "套餐名称";
     }
     else {
       desc = "菜品分类名称";
       dishTitle1 = "菜品名称";
     }
     gh.drawString(desc, this.summary_x_1, print_y);
     gh.drawString(this.titleInfo.getCategoryName(), this.summary_x_2 + 20.0F, print_y);
 
     print_y += this.summary_row_spacing + height_dishes * 2.0F;
     gh.drawString(dishTitle1, this.dishes_x_1, print_y);
     gh.drawString("销量", this.dishes_x_2, print_y);
     gh.drawString("原价合计", this.dishes_x_3, print_y);
 
     for (PrintSalesVolumeDetailDatasVo detailInfo : this.detailInfos) {
       print_y += this.summary_row_spacing + height_dishes;
       gh.drawString(detailInfo.getSalesVolume(), this.dishes_x_2, print_y);
       gh.drawString(detailInfo.getSumMoney(), this.dishes_x_3, print_y);
 
       this.strUtil = new StringUtil();
       try {
         for (String str_dishesName : this.strUtil.splitAsLength(detailInfo.getDishName(), this.dishName_len, this.encoding)) {
           gh.drawString(str_dishesName, this.dishes_x_1, print_y);
           print_y += 1.0F + height_dishes;
         }
         print_y -= this.ds_row_spacing + height_dishes;
       } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
       }
 
     }
 
     String lastLine = this.printer.getPrintTimes() > 1 ? " 第" + this.printerTime + "联" : "";
     if (StringUtils.isNotEmpty(lastLine)) {
       print_y += height_title;
       for (String t : this.strUtil.splitAsLength(lastLine, this.title_len, this.encoding)) {
         int stringWidth = fm.stringWidth(t);
         int x_m1 = width / 2 - stringWidth / 2;
         gh.drawString(t, x_m1 > this.summary_x_1 ? x_m1 : this.summary_x_1, (int)print_y);
         print_y += 3.0F * height_title / 2.0F;
       }
 
     }
 
     print_y = (float)(print_y + this.d);
     gh.setFont(new Font("宋体", 0, 1));
     gh.drawString(".", this.summary_x_1, print_y);
     return gh;
   }
 
   public PrintSalesVolumeDetailTitleVo getTitleInfo()
   {
     return this.titleInfo;
   }
 
   public void setTitleInfo(PrintSalesVolumeDetailTitleVo titleInfo)
   {
     this.titleInfo = titleInfo;
   }
 
   public List<PrintSalesVolumeDetailDatasVo> getDetailInfos()
   {
     return this.detailInfos;
   }
 
   public void setDetailInfos(List<PrintSalesVolumeDetailDatasVo> detailInfos)
   {
     this.detailInfos = detailInfos;
   }
 
   public double getLength()
   {
     double length = 600.0D;
     length = length + getDetailInfos().size() * 7 + this.margin_down.doubleValue() + this.margin_up.doubleValue();
     return length;
   }
 
   public Printable getPrintable()
   {
     return this;
   }
 }

