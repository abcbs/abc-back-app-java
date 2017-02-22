 package com.ndlan.canyin.sharelogic.print;
 
 import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;
import com.ndlan.canyin.sharelogic.printer.PrintParaments;
import com.ndlan.canyin.sharelogic.printer.PrinterFactory;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.vo.MemberCardRechargeVo;
 import com.ndlan.canyin.core.vo.PrintMemberCardInfoDatasVo;
 import com.ndlan.canyin.core.vo.PrintMemberCardInfoParamentsVo;
 import com.ndlan.canyin.core.vo.PrintMemberCardRecordDatasVo;
 import com.ndlan.canyin.core.vo.PrintMemberCardRecordParamentsVo;
 import java.awt.print.PrinterException;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;
 
 public class RechargePrint
 {
   public static void print(MembershipCard pageCard, MembershipCard savedCard, String restName, String isDrawBill, Printer printer, BigDecimal new_memberIntegral, String operator)
     throws PrinterException
   {
     MemberCardRechargeVo cardRechargeVo = new MemberCardRechargeVo();
     cardRechargeVo.setAddMemberIntegral(new_memberIntegral);
     cardRechargeVo.setBalance(savedCard.getBalance());
     cardRechargeVo.setCardNo(savedCard.getCardNo());
     cardRechargeVo.setCashPledge(savedCard.getCashPledge());
     cardRechargeVo.setIsDrawBill(isDrawBill);
     cardRechargeVo.setMemberIntegral(savedCard.getMemberIntegral());
     cardRechargeVo.setOperator(operator);
     cardRechargeVo.setPaidinCash(pageCard.getPaidinCash());
     cardRechargeVo.setRechargeCash(pageCard.getRechargeCash());
     cardRechargeVo.setRechargeTime(savedCard.getUpdateTime());
     cardRechargeVo.setRestName(restName);
     AbstractPrinter aprinter = PrinterFactory.getRechargePrinter(cardRechargeVo);
     aprinter.print(printer, 1, null);
   }
 
   public static void print(MembershipCard membershipCard, PrintMemberCardInfoParamentsVo paraments, Printer printer)
     throws PrinterException
   {
     PrintMemberCardInfoDatasVo datas = new PrintMemberCardInfoDatasVo();
 
     datas.setTitle(StringUtils.isEmpty(printer.getTitle()) ? paraments.getRestName() : printer.getTitle());
 
     datas.setOperatorName(paraments.getOperatorName());
     datas.setOperatorTime(PrintParaments.PAY_DATE_FORMAT.format(paraments.getOperatorTime()));
 
     datas.setCardNo(membershipCard.getCardNo());
     datas.setCardClassName(membershipCard.getMembershipCardClass() != null ? membershipCard.getMembershipCardClass().getName() : "");
     datas.setBalance(String.valueOf(BigDecimalUtil.format(membershipCard.getBalance())));
     datas.setMemberIntegral(String.valueOf(membershipCard.getMemberIntegral()));
     datas.setCardStatusName(membershipCard.getCardStatusName());
     datas.setCardIssueTime(PrintParaments.PAY_DATE_FORMAT.format(membershipCard.getCardIssueTime()));
 
     AbstractPrinter aprinter = PrinterFactory.getMemberShipCardInfoPrinter(datas);
     aprinter.print(printer, 1, null);
   }
 
   public static void print(MembershipCard membershipCard, PrintMemberCardRecordParamentsVo paraments, Printer printer)
     throws PrinterException
   {
     PrintMemberCardRecordDatasVo datas = new PrintMemberCardRecordDatasVo();
 
     datas.setOperatorName(paraments.getOperatorName());
     datas.setOperatorTime(PrintParaments.PAY_DATE_FORMAT.format(paraments.getOperatorTime()));
 
     datas.setTitle(StringUtils.isEmpty(printer.getTitle()) ? paraments.getRestName() : printer.getTitle());
 
     datas.setBillNo(String.valueOf(paraments.getBillNo()));
 
     datas.setCardNo(membershipCard.getCardNo());
     datas.setBalance(String.valueOf(BigDecimalUtil.format(membershipCard.getBalance())));
     datas.setMemberIntegral(String.valueOf(membershipCard.getMemberIntegral()));
     datas.setAddMemberIntegral(String.valueOf(paraments.getAddIntegral()));
     datas.setPayMoney(String.valueOf(BigDecimalUtil.format(paraments.getMembercardCost())));
 
     AbstractPrinter aprinter = PrinterFactory.getMemberShipCardRecordPrinter(datas);
     aprinter.print(printer, 1, null);
   }
 }

