 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.fasterxml.jackson.core.JsonParseException;
 import com.fasterxml.jackson.databind.JsonMappingException;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrintParaments;
import com.ndlan.canyin.sharelogic.util.StringUtil;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.vo.PrintMemberCardRecordDatasVo;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.print.PageFormat;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
import java.io.IOException;
 
 public class MemberShipCardRecordPrinter extends AbstractPrinter
   implements Printable
 {
   private PrintMemberCardRecordDatasVo datas;
   String encoding = "GB2312";
 
   private double x = 0.0D;
   private double y = 0.0D;
   private double d = 0.0D;
   private float summary_x_1;
   private float summary_x_2;
   private Font stateFont;
   private int stateLineLenght;
   private int title_len;
 
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
     this.summary_x_2 = (this.summary_x_1 + 80.0F);
 
     this.stateFont = new Font("黑体", 0, 9);
     this.stateLineLenght = 38;
     this.title_len = 25;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_76(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 80.0F);
 
     this.stateFont = new Font("黑体", 0, 9);
     this.stateLineLenght = 36;
     this.title_len = 22;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D_58(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     this.summary_x_1 = (float)this.x;
     this.summary_x_2 = (this.summary_x_1 + 50.0F);
     this.stateFont = new Font("黑体", 0, 7);
     this.stateLineLenght = 35;
     this.title_len = 19;
 
     return getGraphics2D(gh);
   }
 
   private Graphics2D getGraphics2D(Graphics2D gh)
     throws JsonParseException, JsonMappingException, IOException
   {
     float print_y = 0.0F;
 
     gh.setFont(PrintParaments.PAY_58_TITLE_FONT);
     print_y = (float)(print_y + (gh.getFont().getSize2D() + this.y));
     FontMetrics fm = gh.getFontMetrics();
     int width = (int)(Double.valueOf(this.template).doubleValue() * 72.0D / 25.399999999999999D - 15.0D + this.margin_left.doubleValue());
     StringUtil strUtil = new StringUtil();
     for (String t : strUtil.splitAsLength(this.datas.getTitle(), this.title_len, this.encoding)) {
       int stringWidth = fm.stringWidth(t);
       int x_m = width / 2 - stringWidth / 2;
 
       gh.drawString(t, x_m > this.summary_x_1 ? x_m : this.summary_x_1, (int)print_y);
       print_y += 3.0F * gh.getFont().getSize2D() / 2.0F;
     }
 
     gh.setFont(PrintParaments.PAY_58_DISHES_FONT);
 
     print_y = (float)(print_y + (gh.getFont().getSize2D() + this.y));
     gh.drawString("单  号", this.summary_x_1, print_y);
     gh.drawString(this.datas.getBillNo(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += 5.0F;
 
     print_y = (float)(print_y + (gh.getFont().getSize2D() + this.y));
     gh.drawString("卡  号", this.summary_x_1, print_y);
     gh.drawString(this.datas.getCardNo(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += 5.0F;
 
     print_y += gh.getFont().getSize2D();
     gh.drawString("消费金额", this.summary_x_1, print_y);
     gh.drawString(this.datas.getPayMoney(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += 5.0F;
 
     print_y += gh.getFont().getSize2D();
     gh.drawString("获得积分", this.summary_x_1, print_y);
     gh.drawString(this.datas.getAddMemberIntegral(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += 5.0F;
 
     print_y += gh.getFont().getSize2D();
     gh.drawString("当前余额", this.summary_x_1, print_y);
     gh.drawString(this.datas.getBalance(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += 5.0F;
 
     print_y += gh.getFont().getSize2D();
     gh.drawString("当前积分", this.summary_x_1, print_y);
     gh.drawString(this.datas.getMemberIntegral(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += 5.0F;
 
     print_y += gh.getFont().getSize2D();
     gh.drawString("操作人", this.summary_x_1, print_y);
     gh.drawString(this.datas.getOperatorName(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += 5.0F;
 
     print_y += gh.getFont().getSize2D();
     gh.drawString("操作时间", this.summary_x_1, print_y);
     gh.drawString(this.datas.getOperatorTime(), this.summary_x_2, print_y);
 
     print_y += 5.0F;
     gh.drawLine((int)this.summary_x_1, (int)print_y, (int)this.summary_x_1 + 183, (int)print_y);
     print_y += 9.0F;
 
     gh.setFont(this.stateFont);
     strUtil = new StringUtil();
     for (String t : strUtil.splitAsLength("部分卡类型和支付方式消费不予积分，具体解释权归餐厅所有。", this.stateLineLenght, this.encoding)) {
       gh.drawString(t, this.summary_x_1, (int)print_y);
       print_y += gh.getFont().getSize2D();
     }
 
     print_y = (float)(print_y + this.d);
     gh.setFont(new Font("宋体", 0, 1));
     gh.drawString(".", this.summary_x_1, print_y);
     return gh;
   }
 
   public PrintMemberCardRecordDatasVo getDatas()
   {
     return this.datas;
   }
 
   public void setDatas(PrintMemberCardRecordDatasVo datas) {
     this.datas = datas;
   }
 
   public Printable getPrintable()
   {
     return this;
   }
 
   public double getLength()
   {
     return 0.0D;
   }
 }

