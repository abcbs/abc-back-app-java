 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.util.ClassLoaderUtil;
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.OpenPrintTypeEnum;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 import com.ndlan.canyin.core.vo.DinerBillVo;
 import java.awt.BasicStroke;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.image.BufferedImage;
 import java.awt.print.Book;
 import java.awt.print.PageFormat;
 import java.awt.print.Paper;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.io.File;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.net.URI;
 import java.net.URL;
 import java.text.DecimalFormat;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
 
 public class ZiZhuPrinter extends AbstractPrinter
   implements Printable
 {
   private DinerBillVo dinerBillVo;
   private String printType;
   private String cuiCaiType;
   private String isReissue;
   PageFormat pf = new PageFormat();
 
   protected Book getBook()
   {
     Book book = new Book();
 
     this.pf.setOrientation(1);
 
     Paper p = new Paper();
 
     int length = 600;
 
     Double width = getWidth(this.template);
     p.setSize(width.doubleValue(), length);
 
     int xy = 0;
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
     DecimalFormat df = new DecimalFormat("#.00");
 
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
       if (PrinterTemplateEnum.C_80.getCode().equals(this.template))
       {
         float line_y = 0.0F;
         Font titleFont = new Font("宋体", 0, 20);
         g2_1.setFont(titleFont);
 
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
             if (!imgFile.canRead()) 
//            	 break label391; 
            	 g2_1.setFont(font2);
             
             BufferedImage footImg = ImageIO.read(imgFile);
             g2_1.drawImage(footImg, 2, (int)line_y, 200, 60, null);
           }
           catch (Exception e) {
             System.out.println("PayPrinter 获取脚注图片文件失败。");
             e.printStackTrace();
           }
         } else {
           g2_1.drawString(this.printer.getTitle(), (float)x + 20.0F, 20.0F);
         }
 
//         label391: g2_1.setFont(font2);
         g2_1.setFont(font2);
 
         int y1 = 0;
         g2_1.drawString("单号：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.dinerBillVo.getBillNo(), (float)x + leftOffset1 + 5.0F, (float)y + (5.5F + y1) * heigth);
 
         if (StringUtils.isNotEmpty(this.printType)) {
           String reissue = "";
           float temp = 26.0F;
           if (TrueFalseEnum.TRUE.getCode().equals(this.isReissue)) {
             reissue = "(补打)";
             temp = 0.0F;
           }
 
           if (OpenPrintTypeEnum.RESTAURANT.getCode().equals(this.printType))
             g2_1.drawString(reissue + "商户留存联", (float)x + 20.0F + 95.0F + temp, (float)y + (5.5F + y1) * heigth);
           else if (OpenPrintTypeEnum.CUSTOMER.getCode().equals(this.printType)) {
             g2_1.drawString(reissue + "顾客留存联", (float)x + 20.0F + 95.0F + temp, (float)y + (5.5F + y1) * heigth);
           }
         }
         else if (this.printerTime == 1) {
           g2_1.drawString("商户留存联", (float)x + 20.0F + 95.0F + 26.0F, (float)y + (5.5F + y1) * heigth);
         }
         else if (this.printerTime == 2) {
           g2_1.drawString("顾客留存联", (float)x + 20.0F + 95.0F + 26.0F, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         y1++;
         g2_1.setFont(font2);
 
         g2_1.drawString("序号", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString("名称", (float)x + 20.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString("数量", (float)x + 20.0F + 95.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString("总价", (float)x + 20.0F + 95.0F + 40.0F, (float)y + (5.5F + y1) * heigth);
 
         for (int i = 0; i < this.dinerBillVo.getDinerBillDishes().size(); i++)
         {
           y1++;
           DinerBillDisheVo e = (DinerBillDisheVo)this.dinerBillVo.getDinerBillDishes().get(i);
           g2_1.drawString(String.valueOf(i + 1), (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(e.getDishesName(), (float)x + 20.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(BigDecimal.valueOf(e.getUnitNum().doubleValue())).toString(), (float)x + 20.0F + 95.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(df.format(e.getRealCost()), (float)x + 20.0F + 95.0F + 40.0F, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         y1++;
         g2_1.drawString(df.format(this.dinerBillVo.getRealCost()), (float)x + 20.0F + 95.0F + 40.0F, (float)y + (5.5F + y1) * heigth);
 
         if (getDinerBillVo().getBillType().equals("1"))
         {
           if (getDinerBillVo().getCardNo() != null)
           {
             y1++;
             g2_1.drawString("会员卡号：", (float)x, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(getDinerBillVo().getCardNo(), (float)x + 45.0F, (float)y + (5.5F + y1) * heigth);
           }
 
           if (getDinerBillVo().getCardBalance() != null) {
             y1++;
             g2_1.drawString("您的账户余额：", (float)x, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(getDinerBillVo().getCardBalance(), (float)x + 60.0F, (float)y + (5.5F + y1) * heigth);
           }
 
         }
         else
         {
           String[] sa = getDinerBillVo().getNotes().split(",");
           String cardNo = sa[2];
           y1++;
           g2_1.drawString("银行卡号：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(cardNo, (float)x + 45.0F, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("参数考号：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(sa[4], (float)x + 45.0F, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("流水号：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(sa[5], (float)x + 45.0F, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("批次号：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(sa[6], (float)x + 45.0F, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         g2_1.drawString("时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         if (getDinerBillVo().getOrderTime() == null)
         {
           g2_1.drawString(sdf.format(new Date()), (float)x + 45.0F, (float)y + (5.5F + y1) * heigth);
         }
         else
         {
           g2_1.drawString(sdf.format(getDinerBillVo().getOrderTime()), (float)x + 45.0F, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         y1++;
         g2_1.drawString("客户签字：", (float)x, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         y1++;
         y1++;
         g2_1.drawString("G2", -100.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         y1++;
 
         ObjectMapper mapper = new ObjectMapper();
         String footNote1 = "";
         String footNote2 = "";
         try
         {
           List list = (List)mapper.readValue(this.printer.getFootNote(), List.class);
           for (int i = 0; i < list.size(); i++) {
             Map map = (Map)list.get(i);
             map = (Map)list.get(i);
             if (StringUtils.isNotEmpty(map.get("footNote1").toString())) {
               y1++;
               g2_1.drawString(map.get("footNote1").toString(), (float)x, (float)y + (5.5F + y1) * heigth);
             }
             if (StringUtils.isNotEmpty(map.get("footNote2").toString())) {
               y1++;
               g2_1.drawString(map.get("footNote2").toString(), (float)x, (float)y + (5.5F + y1) * heigth);
             }
             if (StringUtils.isNotEmpty(map.get("footNote3").toString())) {
               y1++;
               g2_1.drawString(map.get("footNote3").toString(), (float)x, (float)y + (5.5F + y1) * heigth);
             }
             if (StringUtils.isNotEmpty(map.get("footNote4").toString())) {
               footNote1 = map.get("footNote4").toString();
             }
             if (StringUtils.isNotEmpty(map.get("footNote5").toString()))
               footNote2 = map.get("footNote5").toString();
           }
         }
         catch (Exception e1)
         {
           e1.printStackTrace();
         }
 
         if (!StringUtils.isEmpty(footNote1))
         {
           y1++;
           g2_1.drawString(footNote1, (int)x + 10, (float)y + (5.5F + y1) * heigth);
         }
 
         if (!StringUtils.isEmpty(footNote2))
         {
           y1++;
           g2_1.drawString("*****************************************", (float)x, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString(footNote2, (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
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
     return 0.0D;
   }
 
   public String getPrintType() {
     return this.printType;
   }
 
   public void setPrintType(String printType) {
     this.printType = printType;
   }
 
   public String getIsReissue() {
     return this.isReissue;
   }
 
   public void setIsReissue(String isReissue) {
     this.isReissue = isReissue;
   }
 }

