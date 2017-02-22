 package com.ndlan.canyin.sharelogic.service.printer;
 
 import com.ndlan.canyin.sharelogic.print.ShiftPrint;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
import com.ndlan.canyin.sharelogic.util.PrintUtil;
import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.base.entity.qtsy.EmployeShift;
 import com.ndlan.canyin.base.repository.ctzh.PrinterDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PrinterTypeEnum;
 import com.ndlan.canyin.core.vo.PaymentTypeVO;
 import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailDatasVo;
 import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailTitleVo;
import com.ndlan.canyin.sharelogic.service.printer.PrinterLogicService;
 import java.awt.print.PrinterException;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class ShiftPrinterService extends BaseLogicService<PrinterDao, Printer>
 {
 
   @Autowired
   private PrinterLogicService printerService;
 
   public void printShift(EmployeShift employeShift, String currentCashBalance, String shiftTime, String lastShiftTime, List<PaymentTypeVO> paymentTypeVOs, List<PaymentTypeVO> memberPaymentTypeVOs, List<PaymentTypeVO> orderPaymentTypeVOs, Map<String, Object> dataMap, HashMap<String, Object> printParaments)
     throws PrinterException
   {
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(employeShift.getRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     printers = PrintUtil.getUserFrontdeskPrinter(printers, printParaments);
 
     List paymentTypeVOnew = new ArrayList();
     for (PaymentTypeVO paymentTypeVO : paymentTypeVOs) {
       if ((paymentTypeVO.getMoney() != null) && (paymentTypeVO.getMoney().compareTo(BigDecimal.ZERO) != 0)) {
         paymentTypeVOnew.add(paymentTypeVO);
       }
     }
 
     List memberPaymentTypeVOsNew = new ArrayList();
     for (PaymentTypeVO paymentTypeVO : memberPaymentTypeVOs) {
       if ((paymentTypeVO.getMoney() != null) && (paymentTypeVO.getMoney().compareTo(BigDecimal.ZERO) != 0)) {
         memberPaymentTypeVOsNew.add(paymentTypeVO);
       }
 
     }
 
     Object orderPaymentTypeVOsNew = new ArrayList();
     for (PaymentTypeVO paymentTypeVO : orderPaymentTypeVOs) {
       if ((paymentTypeVO.getMoney() != null) && (paymentTypeVO.getMoney().compareTo(BigDecimal.ZERO) != 0)) {
         ((List)orderPaymentTypeVOsNew).add(paymentTypeVO);
       }
 
     }
 
     for (Printer printer : printers)
       ShiftPrint.print(employeShift, currentCashBalance, shiftTime, lastShiftTime, dataMap, paymentTypeVOnew, memberPaymentTypeVOsNew, (List)orderPaymentTypeVOsNew, printer);
   }
 
   public void printShiftAgain(EmployeShift employeShift, HashMap<String, Object> printParaments)
     throws PrinterException
   {
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(employeShift.getRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     printers = PrintUtil.getUserFrontdeskPrinter(printers, printParaments);
 
     for (Printer printer : printers)
       ShiftPrint.printAgain(employeShift, printer, printParaments);
   }
 
   public void printZizhuShift(EmployeShift employeShift, String shiftTime, String lastShiftTime, List<Map<String, Object>> dataList, int cancleCount, BigDecimal cancleSum, int settleCount, BigDecimal settleSum)
     throws PrinterException
   {
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(employeShift.getRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
 
     int i = 0;
     for (Printer printer : printers) {
       ShiftPrint.printZizhu(employeShift, shiftTime, lastShiftTime, dataList, printer, i, settleCount, settleSum, cancleCount, cancleSum);
       i++;
     }
   }
 
   public void printSalesVolumeDetail(PrintSalesVolumeDetailTitleVo titleInfo, List<PrintSalesVolumeDetailDatasVo> detailInfos)
     throws PrinterException
   {
     List<Printer> printers = this.printerService.findByRestIdAndStatusAndType(titleInfo.getRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     printers = PrintUtil.getUserFrontdeskPrinter(printers, titleInfo.getPrinterId());
 
     for (Printer printer : printers)
       ShiftPrint.printSalesVolumeDetail(printer, titleInfo, detailInfos);
   }
 }

