 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.print.PayPrint;
import com.ndlan.canyin.sharelogic.print.RechargePrint;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.sharelogic.util.PrintUtil;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhu;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.vo.PrintMemberCardRecordParamentsVo;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogicService;
 import java.awt.print.PrinterException;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class PayPrinterService extends BaseLogicService<PrinterDao, Printer>
 {
 
   @Autowired
   private PrinterLogicService printerService;
 
   public boolean printPay(String restId, DinerBill dinerBill, Table table, String type, String billFrom, HashMap<String, Object> printParaments)
     throws PrinterException
   {
     boolean isPrinted = false;
     String area = (table == null) || (table.getTableArea() == null) ? null : table.getTableArea().getAreaId();
     List<Printer> printers = this.printerService.findPrintersByArea(area, dinerBill.getBillType(), restId, EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     printers = PrintUtil.getUserFrontdeskPrinter(printers, printParaments);
 
     boolean businessPrintCardHis = false;
     PrintMemberCardRecordParamentsVo paraments = null;
 
     if (("PAY".equalsIgnoreCase(type)) && 
       (dinerBill.getMembershipCard() != null)) {
       paraments = new PrintMemberCardRecordParamentsVo();
 
       paraments.setOperatorName((printParaments != null) && (printParaments.get("operatorName") != null) ? String.valueOf(printParaments.get("operatorName")) : "");
       paraments.setOperatorTime((printParaments != null) && (printParaments.get("operatorTime") != null) ? (Date)printParaments.get("operatorTime") : new Date());
 
       paraments.setMembercardCost(dinerBill.getMembercardCost());
       paraments.setAddIntegral(dinerBill.getAddIntegral());
       paraments.setBillNo(dinerBill.getBillNo());
       paraments.setRestName(dinerBill.getRestaurant().getRestName());
 
       businessPrintCardHis = true;
     }
 
     for (Printer printer : printers) {
       PayPrint.print(dinerBill, type, printer, billFrom, printParaments);
 
       if ((businessPrintCardHis) && 
         (TrueFalseEnum.TRUE.getCode().equals(printer.getIsPrintCardHis()))) {
         RechargePrint.print(dinerBill.getMembershipCard(), paraments, printer);
       }
 
       isPrinted = true;
     }
     return isPrinted;
   }
 
   public boolean printPay(DinerBillZiZhu dinerBill, MembershipCard membershipCard, String type, Restaurant restaurant)
     throws PrinterException
   {
     boolean isPrinted = false;
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(restaurant.getRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     for (Printer printer : printers) {
       PayPrint.printZiZhu(dinerBill, membershipCard, type, printer, restaurant);
     }
     return isPrinted;
   }
 
   public boolean printPay(DinerBill dinerBill, MembershipCard membershipCard, String type, Restaurant restaurant)
     throws PrinterException
   {
     boolean isPrinted = false;
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(restaurant.getRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     for (Printer printer : printers) {
       PayPrint.printZiZhu(dinerBill, membershipCard, type, printer, restaurant);
     }
     return isPrinted;
   }
 
   public boolean printPayForPrintType(DinerBill dinerBill, MembershipCard membershipCard, String type, Restaurant restaurant, String printType, String isReissue)
     throws PrinterException
   {
     boolean isPrinted = false;
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(restaurant.getRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     for (Printer printer : printers) {
       PayPrint.printZiZhuForPrintType(dinerBill, membershipCard, type, printer, restaurant, printType, isReissue);
     }
     return isPrinted;
   }
 }

