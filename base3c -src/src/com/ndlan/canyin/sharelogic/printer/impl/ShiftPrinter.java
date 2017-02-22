 package com.ndlan.canyin.sharelogic.printer.impl;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.vo.EmployeShiftVo;
 import com.ndlan.canyin.core.vo.PaymentTypeVO;
 import java.awt.BasicStroke;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.print.PageFormat;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.math.BigDecimal;
 import java.util.HashMap;
 import java.util.Iterator;
import java.util.List;
 
 public class ShiftPrinter extends AbstractPrinter
   implements Printable
 {
   private EmployeShiftVo employeShiftVo;
   PageFormat pf = new PageFormat();
 
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
     throws PrinterException
   {
     Graphics2D g2_1 = (Graphics2D)graphics;
 
     g2_1.setColor(Color.black);
 
     switch (pageIndex)
     {
     case 0:
       Font font = new Font("宋体", 0, 14);
       Font font2 = new Font("宋体", 0, 9);
       Font fontBold = new Font("宋体", 1, 9);
 
       g2_1.setFont(font2);
       float[] dash1 = { 2.0F };
 
       g2_1.setStroke(new BasicStroke(0.5F, 0, 0, 2.0F, dash1, 0.0F));
 
       double x = this.margin_left != null ? this.margin_left.doubleValue() : 0.0D;
       double y = this.margin_up != null ? this.margin_up.doubleValue() : 0.0D;
       double down = this.margin_down != null ? this.margin_down.doubleValue() : 0.0D;
 
       float heigth1 = font.getSize2D();
       float heigth = font2.getSize2D() + 2.0F;
       int leftOffset1 = 40;
       int lineFontSpace = 5;
       Object sshj;
       if (PrinterTemplateEnum.C_80.getCode().equals(this.template))
       {
         g2_1.setFont(font);
         g2_1.drawString("交接班", (float)x + 70.0F, (float)y + 1.0F * heigth1);
         g2_1.setFont(font2);
 
         int y1 = 0;
         float rightOffset = 90.0F;
 
         y1++;
         g2_1.drawString("交班人：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.employeShiftVo.getShiftEmpName(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("上次交班时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.employeShiftVo.getLastShiftTime(), (float)x + 70.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("本次交班时间：", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.employeShiftVo.getCurrentShiftTime(), (float)x + 70.0F, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.setFont(fontBold);
         y1++;
         g2_1.drawString("单项统计", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         y1++;
         g2_1.setFont(font2);
         g2_1.drawString("就餐人数", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(this.employeShiftVo.getPeopleNum().toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("服务费", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getServiceMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("退菜金额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getTuicaiMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("赠菜金额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getZengcaiMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("本班未结", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentUnPayMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         g2_1.setFont(fontBold);
         y1++;
         g2_1.drawString("营业统计", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         g2_1.setFont(font2);
         for (PaymentTypeVO paymentTypeVO : this.employeShiftVo.getCurrentPaymentTypeVOs()) {
           y1++;
           String snow = "";
           if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
           {
             snow = "*";
           }
           g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         y1++;
         BigDecimal zlhj = this.employeShiftVo.getOddChangeSum() != null ? this.employeShiftVo.getOddChangeSum().multiply(new BigDecimal("-1")) : BigDecimal.ZERO;
         g2_1.drawString("*找零合计:", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(zlhj).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         g2_1.setFont(fontBold);
         y1++;
         g2_1.drawString("实收合计", (float)x, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         g2_1.setFont(font2);
         y1++;
         g2_1.drawString("抹零", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getMolingSum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
         y1++;
         g2_1.drawString("折扣优惠", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getDiscountMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("强制结账差额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getForceMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         y1++;
         g2_1.setFont(fontBold);
         g2_1.drawString("应收合计", (float)x, (float)y + (5.5F + y1) * heigth);
 
         sshj = this.employeShiftVo.getCurrentMoneySum() != null ? this.employeShiftVo.getCurrentMoneySum() : BigDecimal.ZERO;
         BigDecimal ml = this.employeShiftVo.getMolingSum() != null ? this.employeShiftVo.getMolingSum() : BigDecimal.ZERO;
         BigDecimal zkyh = this.employeShiftVo.getDiscountMoneySum() != null ? this.employeShiftVo.getDiscountMoneySum() : BigDecimal.ZERO;
         BigDecimal qzjzce = this.employeShiftVo.getForceMoneySum() != null ? this.employeShiftVo.getForceMoneySum() : BigDecimal.ZERO;
 
         BigDecimal yshj = ((BigDecimal)sshj).add(ml).add(zkyh).add(qzjzce);
 
         g2_1.drawString(BigDecimalUtil.format(yshj).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
         if ((this.employeShiftVo.getMemberPaymentTypeVOs() != null) && (this.employeShiftVo.getMemberPaymentTypeVOs().size() > 0)) {
           y1++;
           y1++;
           y1++;
           g2_1.setFont(fontBold);
           g2_1.drawString("本班会员卡充值与退卡", (float)x, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           g2_1.setFont(font2);
           for (PaymentTypeVO paymentTypeVO : this.employeShiftVo.getMemberPaymentTypeVOs()) {
             y1++;
             String snow = "";
             if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
             {
               snow = "*";
             }
             g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           }
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
           y1++;
           g2_1.setFont(fontBold);
           g2_1.drawString("合计", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getMemberMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
         if ((this.employeShiftVo.getOrderPaymentTypeVOs() != null) && (this.employeShiftVo.getOrderPaymentTypeVOs().size() > 0)) {
           y1++;
           y1++;
           y1++;
           g2_1.setFont(fontBold);
           g2_1.drawString("预付款", (float)x, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           g2_1.setFont(font2);
           for (PaymentTypeVO paymentTypeVO : this.employeShiftVo.getOrderPaymentTypeVOs()) {
             y1++;
             String snow = "";
             if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
             {
               snow = "*";
             }
             g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           }
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
           y1++;
           g2_1.setFont(fontBold);
           g2_1.drawString("合计", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getOrderForegiftSum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
         }
 
         y1++;
         y1++;
         g2_1.setFont(fontBold);
         y1++;
         g2_1.drawString("交班信息", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
         g2_1.setFont(font2);
         y1++;
         g2_1.drawString("*前班结余现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getLastBalanceCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("*新增营业周转资金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentHandoffCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("当前现金总额：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("本班上交现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentHandonCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawString("本班结余现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
         BigDecimal currentCashBalance;
//         BigDecimal currentCashBalance;
         if (this.employeShiftVo.getCurrentCashBalance() != null)
           currentCashBalance = new BigDecimal(this.employeShiftVo.getCurrentCashBalance());
         else {
           currentCashBalance = BigDecimal.ZERO;
         }
         g2_1.drawString(BigDecimalUtil.format(currentCashBalance).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
         y1++;
         g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
         y1++;
         g2_1.drawString("签字", (float)x, (float)y + (5.5F + y1) * heigth);
         y1++;
         y1++;
         y1++;
         g2_1.drawString("注:当前现金总额=所有'*'号标注项相加总和", (float)x, (float)y + (5.5F + y1) * heigth);
 
         if ((this.printParaments != null) && (this.printParaments.get("isRePrint") != null) && (((Boolean)this.printParaments.get("isRePrint")).booleanValue())) {
           y1++;
           y1++;
           g2_1.drawString("补打小票", (float)x + 95.0F, (float)y + (5.5F + y1) * heigth);
         }
 
         y += down;
         g2_1.setFont(new Font("宋体", 0, 1));
         g2_1.drawString(".", (float)x, (float)y + (5.5F + y1) * heigth);
       }
       else
       {
//         Object sshj;
         if (PrinterTemplateEnum.C_76.getCode().equals(this.template))
         {
           g2_1.setFont(font);
           g2_1.drawString("交接班", (float)x + 70.0F, (float)y + 1.0F * heigth1);
           g2_1.setFont(font2);
 
           int y1 = 0;
           float rightOffset = 90.0F;
 
           y1++;
           g2_1.drawString("交班人：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.employeShiftVo.getShiftEmpName(), (float)x + leftOffset1, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("上次交班时间：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.employeShiftVo.getLastShiftTime(), (float)x + 70.0F, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("本次交班时间：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.employeShiftVo.getCurrentShiftTime(), (float)x + 70.0F, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.setFont(fontBold);
           y1++;
           g2_1.drawString("单项统计", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           y1++;
           g2_1.setFont(font2);
           g2_1.drawString("就餐人数", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.employeShiftVo.getPeopleNum().toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("服务费", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getServiceMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("退菜金额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getTuicaiMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("赠菜金额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getZengcaiMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("本班未结", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentUnPayMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           g2_1.setFont(fontBold);
           y1++;
           g2_1.drawString("营业统计", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           g2_1.setFont(font2);
           for (sshj = this.employeShiftVo.getCurrentPaymentTypeVOs().iterator(); ((Iterator)sshj).hasNext(); ) { PaymentTypeVO paymentTypeVO = (PaymentTypeVO)((Iterator)sshj).next();
             y1++;
             String snow = "";
             if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
             {
               snow = "*";
             }
             g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           }
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           y1++;
           BigDecimal zlhj = this.employeShiftVo.getOddChangeSum() != null ? this.employeShiftVo.getOddChangeSum().multiply(new BigDecimal("-1")) : BigDecimal.ZERO;
           g2_1.drawString("*找零合计:", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(zlhj).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           g2_1.setFont(fontBold);
           y1++;
           g2_1.drawString("实收合计", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           g2_1.setFont(font2);
           y1++;
           g2_1.drawString("抹零", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getMolingSum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           y1++;
           g2_1.drawString("折扣优惠", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getDiscountMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("强制结账差额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getForceMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           y1++;
           g2_1.setFont(fontBold);
           g2_1.drawString("应收合计", (float)x, (float)y + (5.5F + y1) * heigth);
 
           sshj = this.employeShiftVo.getCurrentMoneySum() != null ? this.employeShiftVo.getCurrentMoneySum() : BigDecimal.ZERO;
           BigDecimal ml = this.employeShiftVo.getMolingSum() != null ? this.employeShiftVo.getMolingSum() : BigDecimal.ZERO;
           BigDecimal zkyh = this.employeShiftVo.getDiscountMoneySum() != null ? this.employeShiftVo.getDiscountMoneySum() : BigDecimal.ZERO;
           BigDecimal qzjzce = this.employeShiftVo.getForceMoneySum() != null ? this.employeShiftVo.getForceMoneySum() : BigDecimal.ZERO;
 
           BigDecimal yshj = ((BigDecimal)sshj).add(ml).add(zkyh).add(qzjzce);
 
           g2_1.drawString(BigDecimalUtil.format(yshj).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
           if ((this.employeShiftVo.getMemberPaymentTypeVOs() != null) && (this.employeShiftVo.getMemberPaymentTypeVOs().size() > 0)) {
             y1++;
             y1++;
             y1++;
             g2_1.setFont(fontBold);
             g2_1.drawString("本班会员卡充值与退卡", (float)x, (float)y + (5.5F + y1) * heigth);
 
             y1++;
             g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
             g2_1.setFont(font2);
             for (PaymentTypeVO paymentTypeVO : this.employeShiftVo.getMemberPaymentTypeVOs()) {
               y1++;
               String snow = "";
               if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
               {
                 snow = "*";
               }
               g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
               g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
             }
 
             y1++;
             g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
             y1++;
             g2_1.setFont(fontBold);
             g2_1.drawString("合计", (float)x, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getMemberMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           }
 
           y1++;
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
           if ((this.employeShiftVo.getOrderPaymentTypeVOs() != null) && (this.employeShiftVo.getOrderPaymentTypeVOs().size() > 0)) {
             y1++;
             y1++;
             y1++;
             g2_1.setFont(fontBold);
             g2_1.drawString("预付款", (float)x, (float)y + (5.5F + y1) * heigth);
 
             y1++;
             g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
             g2_1.setFont(font2);
             for (PaymentTypeVO paymentTypeVO : this.employeShiftVo.getOrderPaymentTypeVOs()) {
               y1++;
               String snow = "";
               if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
               {
                 snow = "*";
               }
               g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
               g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
             }
 
             y1++;
             g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
             y1++;
             g2_1.setFont(fontBold);
             g2_1.drawString("合计", (float)x, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getOrderForegiftSum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           }
 
           y1++;
           y1++;
           g2_1.setFont(fontBold);
           y1++;
           g2_1.drawString("交班信息", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           g2_1.setFont(font2);
           y1++;
           g2_1.drawString("*前班结余现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getLastBalanceCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("*新增营业周转资金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentHandoffCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("当前现金总额：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("本班上交现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentHandonCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("本班结余现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           BigDecimal currentCashBalance;
//           BigDecimal currentCashBalance;
           if (this.employeShiftVo.getCurrentCashBalance() != null)
             currentCashBalance = new BigDecimal(this.employeShiftVo.getCurrentCashBalance());
           else {
             currentCashBalance = BigDecimal.ZERO;
           }
           g2_1.drawString(BigDecimalUtil.format(currentCashBalance).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
           y1++;
           g2_1.drawString("签字", (float)x, (float)y + (5.5F + y1) * heigth);
           y1++;
           y1++;
           y1++;
           g2_1.drawString("注:当前现金总额=所有'*'号标注项相加总和", (float)x, (float)y + (5.5F + y1) * heigth);
 
           if ((this.printParaments != null) && (this.printParaments.get("isRePrint") != null) && (((Boolean)this.printParaments.get("isRePrint")).booleanValue())) {
             y1++;
             y1++;
             g2_1.drawString("补打小票", (float)x + 90.0F, (float)y + (5.5F + y1) * heigth);
           }
 
           y += down;
           g2_1.setFont(new Font("宋体", 0, 1));
           g2_1.drawString(".", (float)x, (float)y + (5.5F + y1) * heigth);
         } else if (PrinterTemplateEnum.C_58.getCode().equals(this.template))
         {
           g2_1.setFont(font);
           g2_1.drawString("交接班", (float)x + 40.0F, (float)y + 1.0F * heigth1);
           g2_1.setFont(font2);
 
           int y1 = 0;
           float rightOffset = 90.0F;
 
           y1++;
           g2_1.drawString("交班人：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.employeShiftVo.getShiftEmpName(), (float)x + 60.0F, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("上次交班时间：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.employeShiftVo.getLastShiftTime(), (float)x + 60.0F, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("本次交班时间：", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.employeShiftVo.getCurrentShiftTime(), (float)x + 60.0F, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.setFont(fontBold);
           y1++;
           g2_1.drawString("单项统计", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           y1++;
           g2_1.setFont(font2);
           g2_1.drawString("就餐人数", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(this.employeShiftVo.getPeopleNum().toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("服务费", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getServiceMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("退菜金额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getTuicaiMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("赠菜金额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getZengcaiMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("本班未结", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentUnPayMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           g2_1.setFont(fontBold);
           y1++;
           g2_1.drawString("营业统计", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           g2_1.setFont(font2);
           for (sshj = this.employeShiftVo.getCurrentPaymentTypeVOs().iterator(); ((Iterator)sshj).hasNext(); ) { PaymentTypeVO paymentTypeVO = (PaymentTypeVO)((Iterator)sshj).next();
             y1++;
             String snow = "";
             if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
             {
               snow = "*";
             }
             g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           }
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           y1++;
           BigDecimal zlhj = this.employeShiftVo.getOddChangeSum() != null ? this.employeShiftVo.getOddChangeSum().multiply(new BigDecimal("-1")) : BigDecimal.ZERO;
           g2_1.drawString("*找零合计:", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(zlhj).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           g2_1.setFont(fontBold);
           y1++;
           g2_1.drawString("实收合计", (float)x, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           g2_1.setFont(font2);
           y1++;
           g2_1.drawString("抹零", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getMolingSum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           y1++;
           g2_1.drawString("折扣优惠", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getDiscountMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("强制结账差额", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getForceMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           y1++;
           g2_1.setFont(fontBold);
           g2_1.drawString("应收合计", (float)x, (float)y + (5.5F + y1) * heigth);
 
           BigDecimal sshj1 = this.employeShiftVo.getCurrentMoneySum() != null ? this.employeShiftVo.getCurrentMoneySum() : BigDecimal.ZERO;
           BigDecimal ml = this.employeShiftVo.getMolingSum() != null ? this.employeShiftVo.getMolingSum() : BigDecimal.ZERO;
           BigDecimal zkyh = this.employeShiftVo.getDiscountMoneySum() != null ? this.employeShiftVo.getDiscountMoneySum() : BigDecimal.ZERO;
           BigDecimal qzjzce = this.employeShiftVo.getForceMoneySum() != null ? this.employeShiftVo.getForceMoneySum() : BigDecimal.ZERO;
 
           BigDecimal yshj = sshj1.add(ml).add(zkyh).add(qzjzce);
 
           g2_1.drawString(BigDecimalUtil.format(yshj).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
           if ((this.employeShiftVo.getMemberPaymentTypeVOs() != null) && (this.employeShiftVo.getMemberPaymentTypeVOs().size() > 0)) {
             y1++;
             y1++;
             y1++;
             g2_1.setFont(fontBold);
             g2_1.drawString("本班会员卡充值与退卡", (float)x, (float)y + (5.5F + y1) * heigth);
 
             y1++;
             g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
             g2_1.setFont(font2);
             for (PaymentTypeVO paymentTypeVO : this.employeShiftVo.getMemberPaymentTypeVOs()) {
               y1++;
               String snow = "";
               if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
               {
                 snow = "*";
               }
               g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
               g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
             }
 
             y1++;
             g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
             y1++;
             g2_1.setFont(fontBold);
             g2_1.drawString("合计", (float)x, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getMemberMoneySum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           }
 
           y1++;
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
           if ((this.employeShiftVo.getOrderPaymentTypeVOs() != null) && (this.employeShiftVo.getOrderPaymentTypeVOs().size() > 0)) {
             y1++;
             y1++;
             y1++;
             g2_1.setFont(fontBold);
             g2_1.drawString("预付款", (float)x, (float)y + (5.5F + y1) * heigth);
 
             y1++;
             g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
             g2_1.setFont(font2);
             for (PaymentTypeVO paymentTypeVO : this.employeShiftVo.getOrderPaymentTypeVOs()) {
               y1++;
               String snow = "";
               if (paymentTypeVO.getPaymentType().equals(PaymentTypeEnum.CASH.getCode()))
               {
                 snow = "*";
               }
               g2_1.drawString(snow + paymentTypeVO.getPaymentName() + ":", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
               g2_1.drawString(BigDecimalUtil.format(paymentTypeVO.getMoney()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
             }
 
             y1++;
             g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
             y1++;
             g2_1.setFont(fontBold);
             g2_1.drawString("合计", (float)x, (float)y + (5.5F + y1) * heigth);
             g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getOrderForegiftSum()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
           }
 
           y1++;
           y1++;
           g2_1.setFont(fontBold);
           y1++;
           g2_1.drawString("交班信息", (float)x, (float)y + (5.5F + y1) * heigth + lineFontSpace);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
 
           g2_1.setFont(font2);
           y1++;
           g2_1.drawString("*前班结余现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getLastBalanceCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("*新增营业周转资金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentHandoffCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("当前现金总额：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("本班上交现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           g2_1.drawString(BigDecimalUtil.format(this.employeShiftVo.getCurrentHandonCash()).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawString("本班结余现金：", (float)x + 10.0F, (float)y + (5.5F + y1) * heigth);
           BigDecimal currentCashBalance;
//           BigDecimal currentCashBalance;
           if (this.employeShiftVo.getCurrentCashBalance() != null)
             currentCashBalance = new BigDecimal(this.employeShiftVo.getCurrentCashBalance());
           else {
             currentCashBalance = BigDecimal.ZERO;
           }
           g2_1.drawString(BigDecimalUtil.format(currentCashBalance).toString(), (float)x + rightOffset, (float)y + (5.5F + y1) * heigth);
 
           y1++;
           g2_1.drawLine((int)x - 2, (int)(y + (5.5F + y1) * heigth), (int)x + 183, (int)(y + (5.5F + y1) * heigth));
           y1++;
           g2_1.drawString("签字", (float)x, (float)y + (5.5F + y1) * heigth);
           y1++;
           y1++;
           y1++;
           g2_1.drawString("注:当前现金总额=所有'*'号", (float)x, (float)y + (5.5F + y1) * heigth);
           y1++;
           g2_1.drawString("标注项相加总和", (float)x, (float)y + (5.5F + y1) * heigth);
 
           if ((this.printParaments != null) && (this.printParaments.get("isRePrint") != null) && (((Boolean)this.printParaments.get("isRePrint")).booleanValue())) {
             y1++;
             y1++;
             g2_1.drawString("补打小票", (float)x + 65.0F, (float)y + (5.5F + y1) * heigth);
           }
 
           y += down;
           g2_1.setFont(new Font("宋体", 0, 1));
           g2_1.drawString(".", (float)x, (float)y + (5.5F + y1) * heigth);
         }
       }
       return 0;
     }
     return 1;
   }
 
   public EmployeShiftVo getEmployeShiftVo()
   {
     return this.employeShiftVo;
   }
 
   public void setEmployeShiftVo(EmployeShiftVo employeShiftVo) {
     this.employeShiftVo = employeShiftVo;
   }
 
   public Printable getPrintable()
   {
     return this;
   }
 
   public double getLength()
   {
     return 2000.0D;
   }
 }

