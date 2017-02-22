 package com.ndlan.canyin.sharelogic.printer;
 
 import com.ndlan.canyin.sharelogic.printer.impl.BingtaiPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.CuicaiPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.MemberShipCardInfoPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.MemberShipCardRecordPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.PayPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.RechargePrinter;
import com.ndlan.canyin.sharelogic.printer.impl.SalesVolumeDetailPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.ShiftPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.TuicaiPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.XiadanPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.ZhuantaiPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.ZiZhuPrinter;
import com.ndlan.canyin.sharelogic.printer.impl.ZiZhuShiftPrinter;
import com.ndlan.canyin.core.vo.DinerBillVo;
 import com.ndlan.canyin.core.vo.EmployeShiftVo;
 import com.ndlan.canyin.core.vo.MemberCardRechargeVo;
 import com.ndlan.canyin.core.vo.PrintMemberCardInfoDatasVo;
 import com.ndlan.canyin.core.vo.PrintMemberCardRecordDatasVo;
 import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailDatasVo;
 import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailTitleVo;
import com.ndlan.canyin.sharelogic.printer.AbstractPrinter;

 import java.math.BigDecimal;
import java.util.List;
 
 public class PrinterFactory
 {
   public static AbstractPrinter getXiadanPrinter(DinerBillVo dinerBillVo)
   {
     XiadanPrinter xiadanPrinter = new XiadanPrinter();
     xiadanPrinter.setDinerBillVo(dinerBillVo);
     return xiadanPrinter;
   }
 
   public static AbstractPrinter getPayPrinter(DinerBillVo dinerBillVo) {
     PayPrinter payPrinter = new PayPrinter();
     payPrinter.setDinerBillVo(dinerBillVo);
     return payPrinter;
   }
 
   public static AbstractPrinter getTuicaiPrinter(DinerBillVo dinerBillVo) {
     TuicaiPrinter tuicaiPrinter = new TuicaiPrinter();
     tuicaiPrinter.setDinerBillVo(dinerBillVo);
     return tuicaiPrinter;
   }
 
   public static AbstractPrinter getCuicaiPrinter(DinerBillVo dinerBillVo, String cuiCaiType) {
     CuicaiPrinter cuicaiPrinter = new CuicaiPrinter();
     cuicaiPrinter.setDinerBillVo(dinerBillVo);
     cuicaiPrinter.setCuiCaiType(cuiCaiType);
     return cuicaiPrinter;
   }
 
   public static AbstractPrinter getShiftPrinter(EmployeShiftVo employeShiftVo) {
     ShiftPrinter shiftPrinter = new ShiftPrinter();
     shiftPrinter.setEmployeShiftVo(employeShiftVo);
     return shiftPrinter;
   }
 
   public static AbstractPrinter getSalesVolumeDetailPrinter(PrintSalesVolumeDetailTitleVo titleInfo, List<PrintSalesVolumeDetailDatasVo> detailInfos)
   {
     SalesVolumeDetailPrinter salesVolumeDetailPrinter = new SalesVolumeDetailPrinter();
     salesVolumeDetailPrinter.setTitleInfo(titleInfo);
     salesVolumeDetailPrinter.setDetailInfos(detailInfos);
     return salesVolumeDetailPrinter;
   }
 
   public static AbstractPrinter getZizhuShiftPrinter(EmployeShiftVo employeShiftVo, int settleCount, BigDecimal settleSum, int cancleCount, BigDecimal cancleSum) {
     ZiZhuShiftPrinter ziZhuShiftPrinter = new ZiZhuShiftPrinter();
     ziZhuShiftPrinter.setEmployeShiftVo(employeShiftVo);
     ziZhuShiftPrinter.setSettleCount(settleCount);
     ziZhuShiftPrinter.setSettleSum(settleSum);
     ziZhuShiftPrinter.setCancleCount(cancleCount);
     ziZhuShiftPrinter.setCancleSum(cancleSum);
     return ziZhuShiftPrinter;
   }
 
   public static AbstractPrinter getRechargePrinter(MemberCardRechargeVo memberCardRechargeVo) {
     RechargePrinter rechargePrinter = new RechargePrinter();
     rechargePrinter.setMemberCardRechargeVo(memberCardRechargeVo);
     return rechargePrinter;
   }
 
   public static AbstractPrinter getMemberShipCardInfoPrinter(PrintMemberCardInfoDatasVo datas)
   {
     MemberShipCardInfoPrinter memberShipCardInfoPrinter = new MemberShipCardInfoPrinter();
     memberShipCardInfoPrinter.setDatas(datas);
     return memberShipCardInfoPrinter;
   }
 
   public static AbstractPrinter getMemberShipCardRecordPrinter(PrintMemberCardRecordDatasVo datas)
   {
     MemberShipCardRecordPrinter memberShipCardRecordPrinter = new MemberShipCardRecordPrinter();
     memberShipCardRecordPrinter.setDatas(datas);
     return memberShipCardRecordPrinter;
   }
 
   public static AbstractPrinter getBingtaiPrinter(DinerBillVo dinerBillVo) {
     BingtaiPrinter bingtaiPrinter = new BingtaiPrinter();
     bingtaiPrinter.setDinerBillVo(dinerBillVo);
     return bingtaiPrinter;
   }
 
   public static AbstractPrinter getZhuantaiPrinter(DinerBillVo dinerBillVo) {
     ZhuantaiPrinter zhuantaiPrinter = new ZhuantaiPrinter();
     zhuantaiPrinter.setDinerBillVo(dinerBillVo);
     return zhuantaiPrinter;
   }
 
   public static AbstractPrinter getZiZhuPrinter(DinerBillVo dinerBillVo) {
     ZiZhuPrinter ziZhuPrinter = new ZiZhuPrinter();
     ziZhuPrinter.setIsReissue(null);
     ziZhuPrinter.setDinerBillVo(dinerBillVo);
     return ziZhuPrinter;
   }
 
   public static AbstractPrinter getZiZhuPrinter(DinerBillVo dinerBillVo, String printType, String isReissue) {
     ZiZhuPrinter ziZhuPrinter = new ZiZhuPrinter();
     ziZhuPrinter.setDinerBillVo(dinerBillVo);
     ziZhuPrinter.setPrintType(printType);
     ziZhuPrinter.setIsReissue(isReissue);
     return ziZhuPrinter;
   }
 }

