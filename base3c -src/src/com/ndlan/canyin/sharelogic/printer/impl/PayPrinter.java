 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.fasterxml.jackson.core.JsonParseException;
 import com.fasterxml.jackson.databind.JsonMappingException;
 import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrintParaments;
import com.ndlan.canyin.sharelogic.printer.PrintUtil;
import com.ndlan.canyin.sharelogic.util.ClassLoaderUtil;
import com.ndlan.canyin.sharelogic.util.StringUtil;
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.BillTypeEnum;
 import com.ndlan.canyin.core.common.DiscountTypeEnum;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.image.BufferedImage;
 import java.awt.print.PageFormat;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.io.UnsupportedEncodingException;
 import java.math.BigDecimal;
 import java.net.URI;
 import java.net.URISyntaxException;
 import java.net.URL;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
 
 public class PayPrinter extends AbstractPrinter
   implements Printable
 {
   private DinerBillVo dinerBillVo;
   private StringUtil strUtil;
   String encoding = "GB2312";
 
   private double x = 0.0D;
   private double y = 0.0D;
   private double d = 0.0D;
 
   private float summary_x_1 = (float)this.x;
   private float summary_x_2 = this.summary_x_1 + 40.0F;
   private float summary_x_3 = this.summary_x_2 + 80.0F;
   private float summary_x_4 = this.summary_x_3 + 30.0F;
   private float summary_row_spacing = 1.4F;
   private int columus = 4;
 
   private float dishes_x_1 = (float)this.x;
   private float dishes_x_2 = this.dishes_x_1 + 90.0F;
   private float dishes_x_3 = this.dishes_x_2 + 35.0F;
   private float dishes_x_4 = this.dishes_x_3 + 35.0F;
   private float dishes_row_spacing = 4.0F;
   private float ds_row_spacing = 1.0F;
   private int dishName_len = 19;
 
   private int footNoteLen = 32;
 
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
 
   public DinerBillVo getDinerBillVo()
   {
     return this.dinerBillVo;
   }
 
   public void setDinerBillVo(DinerBillVo dinerBillVo) {
     this.dinerBillVo = dinerBillVo;
   }
 
   private Graphics2D getGraphics2D_80(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_x_3 = (this.summary_x_2 + 80.0F);
     this.summary_x_4 = (this.summary_x_3 + 30.0F);
     this.summary_row_spacing = 1.4F;
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 90.0F);
     this.dishes_x_3 = (this.dishes_x_2 + 35.0F);
     this.dishes_x_4 = (this.dishes_x_3 + 35.0F);
     this.dishes_row_spacing = 4.0F;
     this.ds_row_spacing = 1.0F;
     this.dishName_len = 19;
 
     this.columus = 2;
 
     this.font_title = PrintParaments.PAY_58_TITLE_FONT;
     this.font_dishes = PrintParaments.PAY_80_DISHES_FONT;
 
     this.footNoteLen = 41;
     this.title_len = 25;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_76(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_x_3 = (this.summary_x_2 + 75.0F);
     this.summary_x_4 = (this.summary_x_3 + 30.0F);
     this.summary_row_spacing = 1.4F;
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 80.0F);
     this.dishes_x_3 = (this.dishes_x_2 + 40.0F);
     this.dishes_x_4 = (this.dishes_x_3 + 30.0F);
     this.dishes_row_spacing = 4.0F;
     this.ds_row_spacing = 1.0F;
     this.dishName_len = 17;
 
     this.columus = 4;
 
     this.font_title = PrintParaments.PAY_76_TITLE_FONT;
     this.font_dishes = PrintParaments.PAY_76_DISHES_FONT;
 
     this.footNoteLen = 39;
     this.title_len = 22;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_58(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 40.0F);
     this.summary_x_3 = (this.summary_x_2 + 80.0F);
     this.summary_x_4 = (this.summary_x_3 + 30.0F);
     this.summary_row_spacing = 1.4F;
 
     this.dishes_x_1 = (float)this.x;
     this.dishes_x_2 = (this.dishes_x_1 + 50.0F);
     this.dishes_x_3 = (this.dishes_x_2 + 23.0F);
     this.dishes_x_4 = (this.dishes_x_3 + 30.0F);
     this.dishes_row_spacing = 4.0F;
     this.ds_row_spacing = 1.0F;
     this.dishName_len = 11;
 
     this.font_title = PrintParaments.PAY_58_TITLE_FONT;
     this.font_dishes = PrintParaments.PAY_58_DISHES_FONT;
 
     this.columus = 2;
 
     this.footNoteLen = 28;
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
     String title = StringUtils.isNotEmpty(this.printer.getTitle()) ? this.printer.getTitle() : this.dinerBillVo.getRestName();
 
     gh.setFont(this.font_title);
 
     FontMetrics fm = gh.getFontMetrics();
     int width = (int)(Double.valueOf(this.template).doubleValue() * 72.0D / 25.399999999999999D - 15.0D + this.margin_left.doubleValue());
     this.strUtil = new StringUtil();
     for (String t : this.strUtil.splitAsLength(title, this.title_len, this.encoding)) {
       int stringWidth = fm.stringWidth(t);
       int x_m = width / 2 - stringWidth / 2;
 
       gh.drawString(t, x_m > this.summary_x_1 ? x_m : this.summary_x_1, (int)print_y);
       print_y += 3.0F * height_title / 2.0F;
     }
 
     if ((this.dinerBillVo.getResettleTime() != null) && (!"预结小票".equalsIgnoreCase(this.dinerBillVo.getMessage()))) {
       gh.drawString("反结账账单", this.summary_x_1, print_y);
       print_y += 3.0F * height_title / 2.0F;
     }
 
     if (TrueFalseEnum.TRUE.getCode().equals(this.printer.getIsPrintCallNo())) {
       gh.drawString("叫号号码：", this.summary_x_1, print_y);
       gh.drawString(this.dinerBillVo.getBillNo().substring(this.dinerBillVo.getBillNo().length() - 4, this.dinerBillVo.getBillNo().length()), 
         this.summary_x_2 + 23.0F, print_y);
       print_y += 3.0F * height_title / 2.0F;
     }
 
     print_y -= 3.0F * height_title / 2.0F;
 
     gh.setFont(this.font_dishes);
 
     String js_ms = "";
     Date jzsj = null;
 
     if ("2".equals(this.dinerBillVo.getBillFrom())) {
       js_ms = "预结小票".equalsIgnoreCase(this.dinerBillVo.getMessage()) ? "预结时间：" : "结账时间：";
       jzsj = "预结小票".equalsIgnoreCase(this.dinerBillVo.getMessage()) ? new Date() : this.dinerBillVo.getPayTime();
     }
     else if (BillTypeEnum.WAIMAI_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
       js_ms = "送餐时间：";
       jzsj = new Date();
     } else {
       js_ms = "预结小票".equalsIgnoreCase(this.dinerBillVo.getMessage()) ? "预结时间：" : "结账时间：";
       jzsj = "预结小票".equalsIgnoreCase(this.dinerBillVo.getMessage()) ? new Date() : this.dinerBillVo.getPayTime();
     }
 
     if (this.columus == 2)
     {
       print_y += height_title * 3.0F / 2.0F;
       gh.drawString(this.dinerBillVo.getMessage(), this.summary_x_1, print_y);
 
       print_y += this.summary_row_spacing + height_dishes;
       gh.drawString("账单编号：", this.summary_x_1, print_y);
       gh.drawString(this.dinerBillVo.getBillNo(), this.summary_x_2, print_y);
 
       print_y += this.summary_row_spacing + height_dishes;
 
       gh.drawString("餐台号：", this.summary_x_1, print_y);
       if (StringUtils.isNotEmpty(this.dinerBillVo.getTabName())) {
         gh.drawString(this.dinerBillVo.getTabName(), this.summary_x_2, print_y);
       }
       else if (!BillTypeEnum.NORMAL_BILL.getCode().equals(this.dinerBillVo.getBillType()))
       {
         if (BillTypeEnum.WAIMAI_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
           gh.drawString("外卖", this.summary_x_2, print_y);
         }
         else if (BillTypeEnum.ZIZHU_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
           gh.drawString("自助", this.summary_x_2, print_y);
         }
         else if (BillTypeEnum.KUAICAN_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
           gh.drawString("快餐", this.summary_x_2, print_y);
         }
 
       }
 
       if (StringUtils.isNotEmpty(this.dinerBillVo.getCardNo())) {
         print_y += this.summary_row_spacing + height_dishes;
         gh.drawString("会员卡号：", this.summary_x_1, print_y);
         gh.drawString(this.dinerBillVo.getCardNo(), this.summary_x_2, print_y);
 
         print_y += this.summary_row_spacing + height_dishes;
         gh.drawString("积分：", this.summary_x_1, print_y);
         gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getMemberIntegral()).toString(), this.summary_x_2, print_y);
       }
 
       print_y += this.summary_row_spacing + height_dishes;
       gh.drawString(js_ms, this.summary_x_1, print_y);
       if (jzsj != null) {
         gh.drawString(PrintParaments.PAY_DATE_FORMAT.format(jzsj), this.summary_x_2, print_y);
       }
       print_y += this.summary_row_spacing + height_dishes;
 
       if ("2".equals(this.dinerBillVo.getBillFrom())) {
         gh.drawString("收银员：", this.summary_x_1, print_y);
         if (this.dinerBillVo.getCashierName() != null) {
           gh.drawString(this.dinerBillVo.getCashierName(), this.summary_x_2, print_y);
         }
 
       }
       else if (BillTypeEnum.WAIMAI_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
         gh.drawString("派送员：", this.summary_x_1, print_y);
         if (this.dinerBillVo.getSenderName() != null)
           gh.drawString(this.dinerBillVo.getSenderName(), this.summary_x_2, print_y);
       }
       else {
         gh.drawString("收银员：", this.summary_x_1, print_y);
         if (this.dinerBillVo.getCashierName() != null) {
           gh.drawString(this.dinerBillVo.getCashierName(), this.summary_x_2, print_y);
         }
       }
 
     }
     else
     {
       print_y += height_title * 3.0F;
       gh.drawString(this.dinerBillVo.getMessage(), this.summary_x_1, print_y);
 
       print_y += this.summary_row_spacing + height_dishes;
       gh.drawString("账单编号：", this.summary_x_1, print_y);
       gh.drawString(this.dinerBillVo.getBillNo(), this.summary_x_2, print_y);
 
       gh.drawString("餐台号：", this.summary_x_3, print_y);
 
       if (StringUtils.isNotEmpty(this.dinerBillVo.getTabName())) {
         gh.drawString(this.dinerBillVo.getTabName(), this.summary_x_4, print_y);
       }
       else if (!BillTypeEnum.NORMAL_BILL.getCode().equals(this.dinerBillVo.getBillType()))
       {
         if (BillTypeEnum.WAIMAI_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
           gh.drawString("外卖", this.summary_x_4, print_y);
         }
         else if (BillTypeEnum.ZIZHU_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
           gh.drawString("自助", this.summary_x_4, print_y);
         }
         else if (BillTypeEnum.KUAICAN_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
           gh.drawString("快餐", this.summary_x_4, print_y);
         }
 
       }
 
       if (StringUtils.isNotEmpty(this.dinerBillVo.getCardNo())) {
         print_y += this.summary_row_spacing + height_dishes;
         gh.drawString("会员卡号：", this.summary_x_1, print_y);
         gh.drawString(this.dinerBillVo.getCardNo(), this.summary_x_2, print_y);
 
         gh.drawString("积分：", this.summary_x_3, print_y);
         gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getMemberIntegral()).toString(), this.summary_x_4, print_y);
       }
 
       print_y += this.summary_row_spacing + height_dishes;
       gh.drawString(js_ms, this.summary_x_1, print_y);
       gh.drawString(PrintParaments.PAY_DATE_FORMAT.format(jzsj), this.summary_x_2, print_y);
 
       if ("2".equals(this.dinerBillVo.getBillFrom())) {
         gh.drawString("收银员：", this.summary_x_3, print_y);
         if (this.dinerBillVo.getCashierName() != null) {
           gh.drawString(this.dinerBillVo.getCashierName(), this.summary_x_4, print_y);
         }
 
       }
       else if (BillTypeEnum.WAIMAI_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
         gh.drawString("派送员：", this.summary_x_3, print_y);
         if (this.dinerBillVo.getSenderName() != null)
           gh.drawString(this.dinerBillVo.getSenderName(), this.summary_x_4, print_y);
       }
       else {
         gh.drawString("收银员：", this.summary_x_3, print_y);
         if (this.dinerBillVo.getCashierName() != null) {
           gh.drawString(this.dinerBillVo.getCashierName(), this.summary_x_4, print_y);
         }
 
       }
 
     }
 
     if ("1".equals(this.dinerBillVo.getBillFrom()))
     {
       if (BillTypeEnum.WAIMAI_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
         print_y += this.summary_row_spacing + height_dishes;
         gh.drawString("联系人：", this.summary_x_1, print_y);
         gh.drawString(this.dinerBillVo.getContactName(), this.summary_x_2, print_y);
 
         print_y += this.summary_row_spacing + height_dishes;
         gh.drawString("联系人手机：", this.summary_x_1, print_y);
         gh.drawString(this.dinerBillVo.getMobile(), this.summary_x_2 + 10.0F, print_y);
 
         print_y += this.summary_row_spacing + height_dishes;
         gh.drawString("联系人电话：", this.summary_x_1, print_y);
         gh.drawString(this.dinerBillVo.getTelephone(), this.summary_x_2 + 10.0F, print_y);
 
         print_y += this.summary_row_spacing + height_dishes;
         String sendAddress = this.dinerBillVo.getSendAddress();
         if ((StringUtils.isNotEmpty(sendAddress)) && (this.printer.getType().equals("0"))) {
           try {
             for (String footNode : this.strUtil.splitAsLength("送餐地址：" + sendAddress, this.footNoteLen, this.encoding)) {
               gh.drawString(footNode, this.dishes_x_1, print_y);
               print_y += 1.0F + height_dishes;
             }
           } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
           }
 
         }
 
       }
 
     }
 
     print_y += height_dishes + 6.0F;
 
     gh.drawString("菜名", this.dishes_x_1, print_y);
     gh.drawString("单价", this.dishes_x_2, print_y);
     gh.drawString("数量", this.dishes_x_3, print_y);
     gh.drawString("小计", this.dishes_x_4, print_y);
 
     print_y += 4.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
 
     boolean firstLine = true;
 
     List<DinerBillDisheVo> dinerBillDisheVos = this.dinerBillVo.getDinerBillDishes();
     for (DinerBillDisheVo dinerBillDisheVo : dinerBillDisheVos) {
       if (firstLine) {
         print_y += 8.0F;
         firstLine = false;
       } else {
         print_y += height_dishes + this.dishes_row_spacing;
       }
       gh.drawString(BigDecimalUtil.format(dinerBillDisheVo.getUnitPrice()).toString(), this.dishes_x_2, print_y);
       gh.drawString(PrintUtil.floatToString(dinerBillDisheVo.getUnitNum()) + dinerBillDisheVo.getUnitName(), this.dishes_x_3, print_y);
 
       gh.drawString(BigDecimalUtil.format(dinerBillDisheVo.getRealCost()).toString(), this.dishes_x_4, print_y);
 
       String dishesNameTemp = "";
       if ((DiscountTypeEnum.OTHER.getCode().equals(dinerBillDisheVo.getDiscountType())) && (dinerBillDisheVo.getDiscount().intValue() < 100)) {
         dishesNameTemp = dishesNameTemp + "%";
       }
       if (DiscountTypeEnum.GIVE.getCode().equals(dinerBillDisheVo.getDiscountType()))
         dishesNameTemp = dishesNameTemp + dinerBillDisheVo.getDishesName() + "(赠)";
       else {
         dishesNameTemp = dishesNameTemp + dinerBillDisheVo.getDishesName();
       }
       this.strUtil = new StringUtil();
       try {
         for (String str_dishesName : this.strUtil.splitAsLength(dishesNameTemp, this.dishName_len, this.encoding)) {
           gh.drawString(str_dishesName, this.dishes_x_1, print_y);
           print_y += 1.0F + height_dishes;
         }
         print_y -= this.ds_row_spacing + height_dishes;
       } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
       }
     }
 
     List<DinerBillDisheVo> dinerBillDishesSetVos = this.dinerBillVo.getDinerBillDishesSet();
     int cou = 0;
     float x_sj = 0.0F;
     for (DinerBillDisheVo dinerBillDisheVo : dinerBillDishesSetVos)
     {
       if (firstLine) {
         print_y += 8.0F;
         firstLine = false;
       } else {
         print_y += height_dishes + this.dishes_row_spacing;
       }
 
       gh.drawString(BigDecimalUtil.format(dinerBillDisheVo.getUnitPrice()).toString(), this.dishes_x_2, print_y);
       gh.drawString(PrintUtil.floatToString(dinerBillDisheVo.getUnitNum()) + dinerBillDisheVo.getUnitName(), this.dishes_x_3, print_y);
 
       gh.drawString(BigDecimalUtil.format(dinerBillDisheVo.getRealCost()).toString(), this.dishes_x_4, print_y);
 
       String dishesNameTemp = "";
       if ((DiscountTypeEnum.OTHER.getCode().equals(dinerBillDisheVo.getDiscountType())) && (dinerBillDisheVo.getDiscount().intValue() < 100)) {
         dishesNameTemp = dishesNameTemp + "%";
       }
 
       dishesNameTemp = dishesNameTemp + "+";
       if (DiscountTypeEnum.GIVE.getCode().equals(dinerBillDisheVo.getDiscountType()))
         dishesNameTemp = dishesNameTemp + dinerBillDisheVo.getDishesName() + "(赠)";
       else {
         dishesNameTemp = dishesNameTemp + dinerBillDisheVo.getDishesName();
       }
       this.strUtil = new StringUtil();
       try {
         for (String str_dishesName : this.strUtil.splitAsLength(dishesNameTemp, this.dishName_len, this.encoding)) {
           gh.drawString(str_dishesName, this.dishes_x_1, print_y);
           print_y += height_dishes + this.ds_row_spacing;
         }
         print_y -= this.ds_row_spacing + height_dishes;
       } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
       }
       try
       {
         ObjectMapper mapper = new ObjectMapper();
 
         List list = (List)mapper.readValue(dinerBillDisheVo.getDsDishesDesc(), List.class);
 
         cou = dishesNameTemp.indexOf("+") + 1;
         x_sj = cou * 5;
         for (int i = 0; i < list.size(); i++) {
           print_y += height_dishes + this.ds_row_spacing;
           Map map = (Map)list.get(i);
           String dishesName = "-" + map.get("dishesName").toString();
           String unitNum = map.get("unitNum").toString();
           String unitName = map.get("unitName").toString();
           gh.drawString(unitNum + unitName, this.dishes_x_3, print_y);
 
           for (String str_dishesName : this.strUtil.splitAsLength(dishesName, this.dishName_len - cou, this.encoding)) {
             gh.drawString(str_dishesName, this.dishes_x_1 + x_sj, print_y);
             print_y += height_dishes + this.ds_row_spacing;
           }
           print_y -= this.ds_row_spacing + height_dishes;
         }
       }
       catch (Exception e) {
         e.printStackTrace();
       }
 
     }
 
     print_y += 5.0F;
 
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += gh.getFont().getSize2D();
 
     gh.drawString("菜品消费：", this.summary_x_1, print_y);
     gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getOriCost()).toString(), this.summary_x_1 + 40.0F, print_y);
 
     if ("2".equals(this.dinerBillVo.getBillFrom())) {
       if ((this.dinerBillVo.getServiceChargeMoney() != null) && (this.dinerBillVo.getServiceChargeMoney().floatValue() > 0.0F)) {
         print_y += 1.0F * height_title;
         gh.drawString("服务费：", this.summary_x_1, print_y);
         gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getServiceChargeMoney()).toString(), this.summary_x_1 + 40.0F, print_y);
       }
 
     }
     else if (BillTypeEnum.WAIMAI_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
       if ((this.dinerBillVo.getDeliverCost() != null) && (this.dinerBillVo.getDeliverCost().floatValue() > 0.0F)) {
         print_y += 1.0F * height_title;
         gh.drawString("送餐费：", this.summary_x_1, print_y);
         gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getDeliverCost()).toString(), this.summary_x_1 + 40.0F, print_y);
       }
     }
     else if ((this.dinerBillVo.getServiceChargeMoney() != null) && (this.dinerBillVo.getServiceChargeMoney().floatValue() > 0.0F)) {
       print_y += 1.0F * height_title;
       gh.drawString("服务费：", this.summary_x_1, print_y);
       gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getServiceChargeMoney()).toString(), this.summary_x_1 + 40.0F, print_y);
     }
 
     if ((this.dinerBillVo.getSaveCost() != null) && (this.dinerBillVo.getSaveCost().floatValue() > 0.0F)) {
       print_y += 1.0F * height_title;
       gh.drawString("折扣优惠：", this.summary_x_1, print_y);
       gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getSaveCost()).toString(), this.summary_x_1 + 40.0F, print_y);
     }
 
     if ((this.dinerBillVo.getMolingModeCost() != null) && (this.dinerBillVo.getMolingModeCost().floatValue() != 0.0F)) {
       print_y += height_title;
       gh.drawString("抹零：", this.summary_x_1, print_y);
       gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getMolingModeCost()).toString(), this.summary_x_1 + 40.0F, print_y);
     }
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += gh.getFont().getSize2D();
 
     Font boldFont = new Font("宋体", 1, 9);
     gh.setFont(boldFont);
     gh.drawString("应付：", this.summary_x_1, print_y);
     gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getPayableCost()).toString(), this.summary_x_1 + 22.0F, print_y);
 
     String payments = this.dinerBillVo.getPayments();
     int i;
     if (StringUtils.isNotEmpty(this.dinerBillVo.getPayments()))
     {
       int col = 3;
       if (this.columus == 2) {
         col = 2;
       }
       else {
         col = 3;
       }
       String[] pays = payments.split("&");
       String[] payLine = new String[pays.length / col + 1];
       int lineNum = 0;
 
       for ( i = 1; i <= pays.length; i++)
       {
         if (payLine[lineNum] == null)
         {
           payLine[lineNum] = "";
         }
         int tmp3850_3848 = lineNum;
         String[] tmp3850_3846 = payLine; tmp3850_3846[tmp3850_3848] = (tmp3850_3846[tmp3850_3848] + pays[(i - 1)] + " ");
         if (i % col != 0)
           continue;
         lineNum++;
       }
 
       print_y += 1.0F * height_title;
       gh.drawString("实收:" + BigDecimalUtil.format(this.dinerBillVo.getRealCost()).toString(), this.summary_x_1, print_y);
       gh.setFont(this.font_dishes);
       float print_x_ss = this.summary_x_1 + 22.0F;
       for (i = 0; i < payLine.length; i++)
       {
         if (!StringUtils.isNotEmpty(payLine[i]))
           continue;
         print_y += gh.getFont().getSize2D();
         gh.drawString(payLine[i], print_x_ss, print_y);
       }
     }
 
     if ((this.dinerBillVo.getOddChange() != null) && (this.dinerBillVo.getOddChange().compareTo(BigDecimal.ZERO) > 0)) {
       gh.setFont(boldFont);
       print_y += 1.0F * height_title;
       gh.drawString("找零：", this.summary_x_1, print_y);
       gh.drawString(BigDecimalUtil.format(this.dinerBillVo.getOddChange()).toString(), this.summary_x_1 + 22.0F, print_y);
     }
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
 
     gh.setFont(this.font_dishes);
     Map map;
     if ((StringUtils.isNotEmpty(this.printer.getFootNote())) && (this.printer.getType().equals("0"))) {
       print_y += 2.0F + height_dishes;
       ObjectMapper mapper = new ObjectMapper();
 
       List list = (List)mapper.readValue(this.printer.getFootNote(), List.class);
       for ( i = 0; i < list.size(); i++) {
         map = (Map)list.get(i);
         map = (Map)list.get(i);
         try {
           if (StringUtils.isNotEmpty(map.get("footNote1").toString())) {
             for (String footNode : this.strUtil.splitAsLength(map.get("footNote1").toString(), this.footNoteLen, this.encoding)) {
               gh.drawString(footNode, this.dishes_x_1, print_y);
               print_y += 1.0F + height_dishes;
             }
             print_y -= this.ds_row_spacing + height_dishes;
             print_y += 2.0F + height_dishes;
           }
           if (StringUtils.isNotEmpty(map.get("footNote2").toString())) {
             for (String footNode : this.strUtil.splitAsLength(map.get("footNote2").toString(), this.footNoteLen, this.encoding)) {
               gh.drawString(footNode, this.dishes_x_1, print_y);
               print_y += 1.0F + height_dishes;
             }
             print_y -= this.ds_row_spacing + height_dishes;
             print_y += 2.0F + height_dishes;
           }
           if (StringUtils.isNotEmpty(map.get("footNote3").toString())) {
             for (String footNode : this.strUtil.splitAsLength(map.get("footNote3").toString(), this.footNoteLen, this.encoding)) {
               gh.drawString(footNode, this.dishes_x_1, print_y);
               print_y += 1.0F + height_dishes;
             }
             print_y -= this.ds_row_spacing + height_dishes;
             print_y += 2.0F + height_dishes;
           }
           if (StringUtils.isNotEmpty(map.get("footNote4").toString())) {
             for (String footNode : this.strUtil.splitAsLength(map.get("footNote4").toString(), this.footNoteLen, this.encoding)) {
               gh.drawString(footNode, this.dishes_x_1, print_y);
               print_y += 1.0F + height_dishes;
             }
             print_y -= this.ds_row_spacing + height_dishes;
             print_y += 2.0F + height_dishes;
           }
           if (StringUtils.isNotEmpty(map.get("footNote5").toString())) {
             for (String footNode : this.strUtil.splitAsLength(map.get("footNote5").toString(), this.footNoteLen, this.encoding)) {
               gh.drawString(footNode, this.dishes_x_1, print_y);
               print_y += 1.0F + height_dishes;
             }
             print_y -= this.ds_row_spacing + height_dishes;
             print_y += 2.0F + height_dishes;
           }
         } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
         }
 
       }
 
     }
 
     String fileName = StringUtils.isNotEmpty(this.printer.getFootImgUrl()) ? this.printer.getFootImgUrl() : null;
     if (StringUtils.isNotEmpty(fileName)) {
       File imgFile = null;
       try {
         URI path = ClassLoaderUtil.getExtendResource("../../../canyin-main" + fileName).toURI();
 
         if (path == null) {
           System.out.println("小票注脚图片路径为NULL !!!");
         }
         System.out.println("小票注脚图片路径：【" + path + "】");
         imgFile = new File(path);
       } catch (URISyntaxException e) {
         System.out.println("PayPrinter 获取脚注图片文件失败。");
         e.printStackTrace();
       }
       try
       {
         BufferedImage footImg = ImageIO.read(imgFile);
         print_y += height_title;
         gh.drawImage(footImg, (int)this.dishes_x_1, (int)print_y, 120, 120, null);
         print_y += 120.0F;
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
 
     gh.setFont(boldFont);
 
     if (TrueFalseEnum.TRUE.getCode().equals(this.dinerBillVo.getIsDrawBill())) {
       String drawStr = "已开发票";
       print_y += height_title;
       gh.drawString(drawStr, (int)this.dishes_x_1, print_y);
     }
 
     if ("1".equals(this.dinerBillVo.getBillFrom()))
     {
       if (BillTypeEnum.WAIMAI_BILL.getCode().equals(this.dinerBillVo.getBillType())) {
         String drawStr = "外卖派送单";
         print_y += height_title;
         gh.drawString(drawStr, (float)(this.pf.getWidth() - 80.0D), print_y);
       }
     }
 
     String lastLine = "";
 
     if ((this.printParaments != null) && (this.printParaments.get("isRePrint") != null) && 
       (((Boolean)this.printParaments.get("isRePrint")).booleanValue())) {
       lastLine = lastLine + "补打小票  ";
     }
 
     if (this.printer.getPrintTimes() > 1) {
       lastLine = lastLine + " 第" + this.printerTime + "联";
     }
 
     if (StringUtils.isNotEmpty(lastLine)) {
       print_y += height_title;
       for (String t : this.strUtil.splitAsLength(lastLine, this.title_len, this.encoding)) {
         int stringWidth = fm.stringWidth(t);
         int x_m = width / 2 - stringWidth / 2;
         gh.drawString(t, x_m > this.summary_x_1 ? x_m : this.summary_x_1, (int)print_y);
         print_y += 3.0F * height_title / 2.0F;
       }
 
     }
 
     print_y = (float)(print_y + this.d);
     gh.setFont(new Font("宋体", 0, 1));
     gh.drawString(".", this.summary_x_1, print_y);
     return gh;
   }
 
   public double getLength()
   {
     double length = 600.0D;
     length = length + getDinerBillVo().getDinerBillDishes().size() * 7 + this.margin_down.doubleValue() + this.margin_up.doubleValue();
     return length;
   }
 
   public Printable getPrintable()
   {
     return this;
   }
 }

