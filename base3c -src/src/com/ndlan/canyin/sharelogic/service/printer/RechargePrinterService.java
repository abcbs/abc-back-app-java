 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.print.RechargePrint;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.sharelogic.util.PrintUtil;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
 import com.ndlan.canyin.core.vo.PrintMemberCardInfoParamentsVo;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogicService;
 import java.awt.print.PrinterException;
 import java.math.BigDecimal;
 import java.util.HashMap;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class RechargePrinterService extends BaseLogicService<PrinterDao, Printer>
 {
 
   @Autowired
   private PrinterLogicService printerService;
 
   public void printRecharge(String restId, String restName, MembershipCard membershipCard, MembershipCard savedCard, String isDrawBill, BigDecimal new_memberIntegral, String operator, HashMap<String, Object> printParaments)
     throws PrinterException
   {
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(restId, EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     printers = PrintUtil.getUserFrontdeskPrinter(printers, printParaments);
     for (Printer printer : printers)
       RechargePrint.print(membershipCard, savedCard, restName, isDrawBill, printer, new_memberIntegral, operator);
   }
 
   public void printMemberCardInfo(MembershipCard membershipCard, PrintMemberCardInfoParamentsVo paraments)
     throws PrinterException
   {
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(paraments.getRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     printers = PrintUtil.getUserFrontdeskPrinter(printers, paraments.getPrinterId());
 
     for (Printer printer : printers)
       RechargePrint.print(membershipCard, paraments, printer);
   }
 }

