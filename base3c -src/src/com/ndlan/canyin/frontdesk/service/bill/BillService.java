 package com.ndlan.canyin.frontdesk.service.bill;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableAreaService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardOperationService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillLogService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfOrderService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableBillRelationService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.frontdesk.service.qtsy.TakeoutService;
import com.ndlan.canyin.frontdesk.service.sygl.CashDishesTypeDiscountService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDishesSetService;
import com.ndlan.canyin.frontdesk.service.xtgl.BusiLogService;
import com.ndlan.canyin.frontdesk.service.ylgl.DishesRawService;
import com.ndlan.canyin.frontdesk.service.ylgl.RawMaterialService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.SelfServiceCollective;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.frontdesk.util.UserSettingCacheSetting;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
 import com.ndlan.canyin.base.entity.cygl.Dishe;
 import com.ndlan.canyin.base.entity.cygl.DishesCategory;
 import com.ndlan.canyin.base.entity.cygl.DishesSet;
 import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
 import com.ndlan.canyin.base.entity.cygl.SpecialDishe;
 import com.ndlan.canyin.base.entity.cygl.SpecialPriceInterval;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
 import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
 import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
 import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
 import com.ndlan.canyin.base.entity.qtsy.TableOrder;
 import com.ndlan.canyin.base.entity.qtsy.Takeout;
 import com.ndlan.canyin.base.entity.sygl.CashDiscount;
 import com.ndlan.canyin.base.entity.sygl.CashDishesTypeDiscount;
 import com.ndlan.canyin.base.entity.sygl.PaymentType;
 import com.ndlan.canyin.base.repository.cygl.SpecialDisheDao;
 import com.ndlan.canyin.base.repository.cygl.SpecialPriceIntervalDao;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillDao;
 import com.ndlan.canyin.core.common.BillOpTypeEnum;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.BillTypeEnum;
 import com.ndlan.canyin.core.common.CardOperationTypeEnum;
 import com.ndlan.canyin.core.common.CloudPaymentTypeEnum;
 import com.ndlan.canyin.core.common.DinnerStatusEnum;
 import com.ndlan.canyin.core.common.DiscountTypeEnum;
 import com.ndlan.canyin.core.common.DishesStatusEnum;
 import com.ndlan.canyin.core.common.MemberCardStatusEnum;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.common.OrderStatusEnum;
 import com.ndlan.canyin.core.common.PaymentTypeEnum;
 import com.ndlan.canyin.core.common.SeviceChargeTypeEnum;
 import com.ndlan.canyin.core.common.TakeoutStatusEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.maths.WipeZeroMaths;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.utils.DateProvider;
 import com.ndlan.canyin.sharelogic.util.MessageDataUtil;
 import java.math.BigDecimal;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.LinkedHashMap;
 import java.util.List;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional(readOnly=true)
 public class BillService extends BaseService<DinerBillDao, DinerBill>
 {
 
   @Autowired
   SpecialPriceIntervalDao specialPriceIntervalDao;
 
   @Autowired
   SpecialDisheDao specialDisheDao;
 
   @Autowired
   DisheService disheService;
 
   @Autowired
   MembershipCardService membershipCardService;
 
   @Autowired
   BusiLogService busiLogService;
 
   @Autowired
   CashDishesTypeDiscountService cashDishesTypeDiscountService;
 
   @Autowired
   TableBillRelationService tableBillRelationService;
 
   @Autowired
   TableService tableService;
 
   @Autowired
   DinerBillDisheService dinerBillDisheService;
 
   @Autowired
   DinerBillLogService dinerBillLogService;
 
   @Autowired
   MembershipCardOperationService cardOperationService;
 
   @Autowired
   DinerBillService dinerBillService;
 
   @Autowired
   SelfServiceCollective selfServiceCollective;
 
   @Autowired
   DinerBillDishesSetService dinerBillDishesSetService;
 
   @Autowired
   DishesSetService dishesSetService;
 
   @Autowired
   DishesSetCategoryService dishesSetCategoryService;
 
   @Autowired
   RawMaterialService rawMaterialService;
 
   @Autowired
   DishesRawService dishesRawService;
 
   @Autowired
   DishesCategoryService dishesCategoryService;
 
   @Autowired
   TakeoutService takeoutService;
 
   @Autowired
   TableOrderService tableOrderService;
 
   @Autowired
   RestMemberInfoService restMemberInfoService;
 
   @Autowired
   SelfOrderService selfOrderService;
 
   @Autowired
   MembershipCardOperationService membershipCardOperationService;
 
   @Autowired
   DinerBillDao dinerBillDao;
 
   @Autowired
   TableAreaService tableAreaService;
 
   @Transactional(readOnly=false)
   public void savePay(Employee employee, DinerBill dinerBill, MembershipCard membershipCard, String restId, String isForce, LinkedHashMap<String, Object> map, ArrayList<String> cloudMethodParams)
   {
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       return;
     }
 
     Date nowTime = new Date();
 
     if (!BillStatusEnum.RESETTLE.getCode().equals(billStatus)) {
       dinerBill.setPayTime(nowTime);
       dinerBill.setCashierEmployee(employee);
     }
 
     if (("false".equalsIgnoreCase(isForce)) || 
       (TrueFalseEnum.FALSE.getCode().equalsIgnoreCase(isForce))) {
       dinerBill.setIsForcePay(TrueFalseEnum.FALSE.getCode());
     }
     else
     {
       dinerBill.setIsForcePay(TrueFalseEnum.TRUE.getCode());
       dinerBill.setForcePayOperator(employee);
       dinerBill.setForcePayTime(DateProvider.DEFAULT.getDate());
     }
     dinerBill.setBillStatus(BillStatusEnum.SETTLE.getCode());
     dinerBill.setCashierName(employee.getName());
     this.dinerBillService.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     if (membershipCard != null)
     {
       List billPayments = dinerBill.getDinerBillPayments();
       BigDecimal memberIntegral = membershipCard.getMemberIntegral();
       BigDecimal addIntegral = BigDecimal.ZERO;
       PaymentType paymentType = null;
 
       BigDecimal consumeCash = BigDecimal.ZERO;
       if (memberIntegral == null) {
         memberIntegral = BigDecimal.ZERO;
       }
 
       StringBuilder remarks = new StringBuilder();
 
       boolean isCloudMemberPay = false;
       for (int i = 0; i < billPayments.size(); i++) {
         DinerBillPayment dinerBillPayment = (DinerBillPayment)billPayments.get(i);
         String payType = dinerBillPayment.getPaymentType().getPaymentType();
         if (TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral()))
         {
           if ((PaymentTypeEnum.CASH.getCode().equals(payType)) || (PaymentTypeEnum.CARD.getCode().equals(payType)) || (PaymentTypeEnum.MEMBER_CARD.getCode().equals(payType)) || (PaymentTypeEnum.WEB_PAY.getCode().equals(payType))) {
             addIntegral = addIntegral.add(dinerBillPayment.getMoney());
           }
 
         }
 
         if (PaymentTypeEnum.MEMBER_CARD.getCode().equals(payType)) {
           membershipCard.setBalance(membershipCard.getBalance().subtract(dinerBillPayment.getMoney()));
         }
 
         if ((PaymentTypeEnum.MEMBER_CARD.getCode().equals(payType)) || (BillStatusEnum.RESETTLE.getCode().equals(billStatus))) {
           consumeCash = consumeCash.add(dinerBillPayment.getMoney());
         }
 
         if ((PaymentTypeEnum.MEMBER_CARD.getCode().equals(payType)) || 
           (PaymentTypeEnum.HOTEL_CREDIT.getCode().equals(payType)) || 
           (PaymentTypeEnum.TEAM_CREDIT.getCode().equals(payType)) || 
           (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equals(payType)) || (isCloudMemberPay)) {
           paymentType = dinerBillPayment.getPaymentType();
         }
 
         remarks.append(dinerBillPayment.getPaymentType().getPaymentName());
         remarks.append("：");
         remarks.append(BigDecimalUtil.format(dinerBillPayment.getMoney()));
 
         if (i != billPayments.size() - 1) {
           remarks.append("、");
         }
       }
 
       if (TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral()))
       {
         if (addIntegral.compareTo(dinerBill.getPayableCost()) >= 0) {
           addIntegral = dinerBill.getPayableCost();
         }
 
         memberIntegral = BigDecimalUtil.formatRoundDown(memberIntegral.add(BigDecimalUtil.formatRoundDown(addIntegral)));
       }
       membershipCard.setMemberIntegral(memberIntegral);
 
       MembershipCardOperation cardOperation = new MembershipCardOperation();
       if (dinerBill.getRestMemberInfo() != null) {
         RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(dinerBill.getRestMemberInfo().getMbId());
         cardOperation.setRestMemberInfo(restMemberInfo);
       }
       cardOperation.setMembershipCard(membershipCard);
       cardOperation.setCardOperationType(CardOperationTypeEnum.CONSUME.getCode());
       cardOperation.setAddIntegral(addIntegral);
       if (membershipCard.getMemberIntegral() != null)
         cardOperation.setTotalIntegral(membershipCard.getMemberIntegral());
       else {
         cardOperation.setTotalIntegral(addIntegral);
       }
       cardOperation.setIsDrawBill(dinerBill.getIsDrawBill());
       cardOperation.setDrawBillAmount(dinerBill.getDrawBillAmount());
       cardOperation.setDinerBill(dinerBill);
       cardOperation.setBillNo(dinerBill.getBillNo());
       cardOperation.setBalance(membershipCard.getBalance());
       cardOperation.setConsumeCash(consumeCash);
       cardOperation.setPaymentType(paymentType);
 
       if (BillStatusEnum.RESETTLE.getCode().equals(billStatus))
         cardOperation.setRemarks("(反结账)" + remarks.toString());
       else {
         cardOperation.setRemarks(remarks.toString());
       }
 
       this.cardOperationService.save(cardOperation);
 
       map.put(cardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), cardOperation);
 
       this.membershipCardService.save(membershipCard);
 
       map.put(membershipCard.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), membershipCard);
 
       MessageDataUtil messageDataUtil = new MessageDataUtil();
       messageDataUtil.consumeMessageData(cloudMethodParams, membershipCard, consumeCash, addIntegral);
     }
 
     TableBillRelation tableBillRelation = dinerBill.getTableBillRelation();
     if (tableBillRelation != null) {
       tableBillRelation.setBillStatus(BillStatusEnum.SETTLE.getCode());
       this.tableBillRelationService.save(tableBillRelation);
 
       map.put(tableBillRelation.getTabBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableBillRelation);
 
       Table t = tableBillRelation.getTable();
       t.setDinnerStatus(DinnerStatusEnum.SETTLE.getCode());
       this.tableService.save(t);
 
       map.put(t.getTabId() + "_" + OperationTypeEnum.UPDATE.getCode(), t);
 
       if (BillTypeEnum.SELFORDER_BILL.getCode().equals(dinerBill.getBillType()))
       {
         SelfOrder selfOrder = this.selfOrderService.getSelfOrderByTabNo(restId, t.getTabNo());
         if (selfOrder != null)
         {
           selfOrder.setStatus(TrueFalseEnum.FALSE.getCode());
           this.selfOrderService.save(selfOrder);
 
           map.put(selfOrder.getId() + "_" + OperationTypeEnum.UPDATE.getCode(), selfOrder);
         }
 
       }
 
     }
 
     if (TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(employee.getEmpId()).getIsAutoQingtai()))
     {
       if (tableBillRelation != null)
       {
         Table t = dinerBill.getTable();
         if ((t != null) && (t.getTabId() != null) && (!t.getTabId().isEmpty()))
         {
           this.dinerBillService.saveQingtaiDinerBill(t.getTabId(), map);
         }
 
       }
 
     }
 
     if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) {
       Takeout takeout = this.takeoutService.findByRestIdAndDinerBill(restId, dinerBill);
 
       if ((takeout != null) && (StringUtils.isNotEmpty(takeout.getOnlineTakeoutId())))
       {
         map.put(takeout.getOnlineTakeoutId() + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_takeout set order_status = '" + TakeoutStatusEnum.PAYED.getCode() + "' where ut_id = '" + takeout.getOnlineTakeoutId() + "'");
       }
 
     }
     else if (StringUtils.isNotEmpty(dinerBill.getOrderId())) {
       TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(dinerBill.getOrderId());
 
       if ((tableOrder != null) && (StringUtils.isNotEmpty(tableOrder.getOnlineOrderId())))
       {
         map.put(tableOrder.getOnlineOrderId() + "_" + OperationTypeEnum.SQL.getCode(), 
           "update cl_user_order set order_status = '" + OrderStatusEnum.OVER.getCode() + 
           "' where uo_id = '" + tableOrder.getOnlineOrderId() + "' and order_status not in ('" + OrderStatusEnum.OVER.getCode() + "','" + OrderStatusEnum.CANCEL.getCode() + "','" + OrderStatusEnum.EXPIRE.getCode() + "')");
 
         map.put(tableOrder.getOnlineUobId() + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_order_bill set order_bill_status = '" + OrderStatusEnum.OVER.getCode() + "' where uo_id = '" + tableOrder.getOnlineOrderId() + "' and uob_id = '" + tableOrder.getOnlineUobId() + "' ");
       }
 
     }
 
     String logNotes = "结账：";
 
     StringBuilder sb = new StringBuilder();
     List dinerBillPayments = dinerBill.getDinerBillPayments();
     for (int i = 0; i < dinerBillPayments.size(); i++) {
       DinerBillPayment dinerBillPayment = (DinerBillPayment)dinerBillPayments.get(i);
       PaymentType paymentType = dinerBillPayment.getPaymentType();
//       sb.append(paymentType.getPaymentName());
       sb.append("：");
       sb.append(BigDecimalUtil.format(dinerBillPayment.getMoney()));
       if (i != dinerBillPayments.size() - 1) {
         sb.append(",");
       }
     }
     dinerBill.setPayments(sb.toString());
     logNotes = logNotes + dinerBill.getPayments();
     this.dinerBillService.saveDinerBillLog(dinerBill.getRestaurant().getRestId(), dinerBill, BillOpTypeEnum.SETTLE, logNotes, map);
   }
 
   @Transactional(readOnly=true)
   public void calculator(String restId, DinerBill dinerBill)
   {
     boolean isTableSpecialPrice = false;
     boolean isTableDiscount = false;
     Table table = dinerBill.getTable();
 
     if (table != null) {
       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
       if (TrueFalseEnum.TRUE.getCode().equals(tableArea.getIsSpecialPrice()))
       {
         isTableSpecialPrice = true;
       }
     }
 
     if (table != null) {
       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
       if (TrueFalseEnum.TRUE.getCode().equals(tableArea.getIsOnSale()))
       {
         isTableDiscount = true;
       }
 
     }
 
     if ((BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) || 
       (BillTypeEnum.KUAICAN_BILL.getCode().equals(dinerBill.getBillType()))) {
       isTableSpecialPrice = true;
       isTableDiscount = true;
     }
 
     resetData(dinerBill);
 
     List<DinerBillDishe> dinerBillDishes = this.dinerBillDisheService.findByRestIdAndBillId(restId, dinerBill.getBillId());
     Dishe dishe;
     for (DinerBillDishe dbd : dinerBillDishes)
     {
       dishe = (Dishe)this.disheService.loadOne(dbd.getDishesId());
 
       if ((dbd.getDishesStatus().equals(DishesStatusEnum.SERVED_CANCEL.getCode())) || 
         (dbd.getDishesStatus().equals(DishesStatusEnum.UNSERVE_CANCEL.getCode())))
       {
         continue;
       }
       calculatorDishe(restId, dinerBill, dbd, isTableSpecialPrice, isTableDiscount, dishe);
 
       if ((!TrueFalseEnum.TRUE.getCode().equals(dishe.getIsAddMinCharge())) && (!DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dbd.getDishesStatus())) && (!DishesStatusEnum.SERVED_CANCEL.getCode().equals(dbd.getDishesStatus()))) {
         dinerBill.setConsumeLow(dinerBill.getConsumeLow().add(dbd.getOriCost()));
       }
       if ((DiscountTypeEnum.SPECIAL.getCode().equals(dbd.getDiscountType())) || (DiscountTypeEnum.MEMBER.getCode().equals(dbd.getDiscountType())))
       {
         dinerBill.setOriCost(dinerBill.getOriCost().add(dbd.getRealCost()));
       }
       else {
         dinerBill.setOriCost(dinerBill.getOriCost().add(dbd.getOriCost()));
 
         dinerBill.setSaveCost(BigDecimalUtil.format(dinerBill.getSaveCost().add(dbd.getOriCost()).subtract(dbd.getRealCost())));
       }
     }
 
     List<DinerBillDishesSet> dinerBillDishesSets = this.dinerBillDishesSetService.findByRestIdAndDinerBill(restId, dinerBill);
 
     for (DinerBillDishesSet dbds : dinerBillDishesSets)
     {
       DishesSet dishesSet = (DishesSet)this.dishesSetService.loadOne(dbds.getDsId());
 
       if ((dbds.getDsStatus().equals(DishesStatusEnum.SERVED_CANCEL.getCode())) || (dbds.getDsStatus().equals(DishesStatusEnum.UNSERVE_CANCEL.getCode())))
       {
         continue;
       }
       calculatorDishesSet(restId, dinerBill, dbds, isTableSpecialPrice, isTableDiscount, dishesSet);
 
       if ((!TrueFalseEnum.TRUE.getCode().equals(dishesSet.getIsAddMinCharge())) && (!DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dbds.getDsStatus())) && (!DishesStatusEnum.SERVED_CANCEL.getCode().equals(dbds.getDsStatus()))) {
         dinerBill.setConsumeLow(dinerBill.getConsumeLow().add(dbds.getOriCost()));
       }
       if ((DiscountTypeEnum.SPECIAL.getCode().equals(dbds.getDiscountType())) || (DiscountTypeEnum.MEMBER.getCode().equals(dbds.getDiscountType())))
       {
         dinerBill.setOriCost(dinerBill.getOriCost().add(dbds.getRealCost()));
       }
       else {
         dinerBill.setOriCost(dinerBill.getOriCost().add(dbds.getOriCost()));
 
         dinerBill.setSaveCost(BigDecimalUtil.format(dinerBill.getSaveCost().add(dbds.getOriCost()).subtract(dbds.getRealCost())));
       }
     }
 
     dinerBill.setConsumeLow(BigDecimalUtil.format(dinerBill.getConsumeLow()));
 
     dinerBill.setOriCost(BigDecimalUtil.format(dinerBill.getOriCost()));
 
     dinerBill.setSaveCost(BigDecimalUtil.format(dinerBill.getSaveCost()));
 
     setServiceChargeMoney(dinerBill);
 
     dinerBill.setConsumeCost(BigDecimalUtil.format(dinerBill.getOriCost().add(dinerBill.getServiceChargeMoney())));
 
     if (dinerBill.getConsumeCost() != null) {
       BigDecimal temp = dinerBill.getConsumeCost();
       if (dinerBill.getSaveCost() != null) {
         temp = temp.subtract(dinerBill.getSaveCost());
       }
       if (dinerBill.getRateCost() != null) {
         temp = temp.subtract(dinerBill.getRateCost());
       }
       dinerBill.setPayableCost(BigDecimalUtil.format(temp));
     }
 
     setDeliverCost(restId, dinerBill);
 
     setMoling(dinerBill);
     BigDecimal moling;
 if(dinerBill.getMolingModeCost()==null||(dinerBill.getMolingModeCost()).equals("")){
	 moling=  BigDecimal.ZERO;
 }else{
	 moling=dinerBill.getMolingModeCost();
 }
     dinerBill.setPayableCost(BigDecimalUtil.format(dinerBill.getPayableCost().subtract(moling)));
 
     BigDecimal paymentAll = BigDecimal.ZERO;
     BigDecimal addIntegral = BigDecimal.ZERO;
     BigDecimal membercardCost = BigDecimal.ZERO;
     MembershipCard membershipCard = dinerBill.getMembershipCard();
 
     List dinerBillPayments = dinerBill.getDinerBillPayments();
     for (int i = 0; i < dinerBillPayments.size(); i++) {
       DinerBillPayment dinerBillPayment = (DinerBillPayment)dinerBillPayments.get(i);
       paymentAll = paymentAll.add(dinerBillPayment.getMoney());
 
       if (membershipCard != null) {
         PaymentType paymentType = dinerBillPayment.getPaymentType();
         if (TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral()))
         {
           if ((dinerBill.getMembershipCard() != null) && ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) || (PaymentTypeEnum.CARD.getCode().equals(paymentType.getPaymentType())) || (PaymentTypeEnum.MEMBER_CARD.getCode().equals(paymentType.getPaymentType())))) {
             addIntegral = addIntegral.add(dinerBillPayment.getMoney());
           }
 
           if (PaymentTypeEnum.WEB_PAY.getCode().equals(paymentType.getPaymentType()))
           {
             TableOrder tableOrder = this.tableOrderService.findByRestIdAndBillId(restId, dinerBill.getBillId());
             if ((tableOrder != null) && (StringUtils.isNotEmpty(tableOrder.getOnlineOrderId())) && (CloudPaymentTypeEnum.MCP.getCode().equals(tableOrder.getOnlinePaymentType())) && 
               (tableOrder.getPrepay() != null) && (tableOrder.getPrepay().compareTo(BigDecimal.ZERO) > 0)) {
               addIntegral = addIntegral.add(dinerBillPayment.getMoney());
             }
 
             Takeout takeout = this.takeoutService.findByRestIdAndDinerBill(restId, dinerBill);
             if ((takeout != null) && (StringUtils.isNotEmpty(takeout.getOnlineTakeoutId())) && (CloudPaymentTypeEnum.MCP.getCode().equals(takeout.getOnlinePaymentType())) && 
               (takeout.getTotalCost() != null) && (takeout.getTotalCost().compareTo(BigDecimal.ZERO) > 0)) {
               addIntegral = addIntegral.add(dinerBillPayment.getMoney());
             }
 
           }
 
         }
 
         if (PaymentTypeEnum.MEMBER_CARD.getCode().equals(paymentType.getPaymentType())) {
           membercardCost = dinerBillPayment.getMoney();
         }
       }
     }
 
     if (paymentAll.compareTo(dinerBill.getPayableCost()) == 1) {
       dinerBill.setOddChange(BigDecimalUtil.format(paymentAll.subtract(dinerBill.getPayableCost())));
       dinerBill.setNeedMoney(BigDecimalUtil.format(BigDecimal.valueOf(0L)));
       dinerBill.setRealCost(BigDecimalUtil.format(dinerBill.getPayableCost()));
     }
     else {
       dinerBill.setNeedMoney(BigDecimalUtil.format(dinerBill.getPayableCost().subtract(paymentAll)));
       dinerBill.setOddChange(BigDecimalUtil.format(BigDecimal.valueOf(0L)));
       dinerBill.setRealCost(BigDecimalUtil.format(paymentAll));
     }
 
     if (membershipCard != null) {
       if ((TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral())) && 
         (addIntegral.compareTo(dinerBill.getPayableCost()) >= 0)) {
         addIntegral = dinerBill.getPayableCost();
       }
 
       dinerBill.setAddIntegral(BigDecimalUtil.formatRoundDown(addIntegral));
 
       dinerBill.setMembercardCost(membercardCost);
     }
   }
 
   @Transactional(readOnly=false)
   public void calculatorForDishe(String restId, DinerBillDishe dbd, DinerBill dinerBill)
   {
     dinerBill = (DinerBill)this.dinerBillDao.findOne(dinerBill.getBillId());
 
     if (dinerBill.getOriCost() == null)
     {
       dinerBill.setOriCost(new BigDecimal(0.0D));
     }
 
     if (dinerBill.getConsumeCost() == null)
     {
       dinerBill.setConsumeCost(new BigDecimal(0.0D));
     }
 
     if (dinerBill.getSaveCost() == null)
     {
       dinerBill.setSaveCost(new BigDecimal(0.0D));
     }
 
     if (dinerBill.getPayableCost() == null)
     {
       dinerBill.setPayableCost(new BigDecimal(0.0D));
     }
 
     if (dinerBill.getConsumeLow() == null)
     {
       dinerBill.setConsumeLow(new BigDecimal(0.0D));
     }
 
     if (dinerBill.getNeedMoney() == null)
     {
       dinerBill.setNeedMoney(new BigDecimal(0.0D));
     }
 
     Dishe dishe = (Dishe)this.disheService.loadOne(dbd.getDishesId());
 
     if ((!TrueFalseEnum.TRUE.getCode().equals(dishe.getIsAddMinCharge())) && (!DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dbd.getDishesStatus())) && (!DishesStatusEnum.SERVED_CANCEL.getCode().equals(dbd.getDishesStatus()))) {
       dinerBill.setConsumeLow(dinerBill.getConsumeLow().add(dbd.getOriCost()));
     }
     if ((DiscountTypeEnum.SPECIAL.getCode().equals(dbd.getDiscountType())) || (DiscountTypeEnum.MEMBER.getCode().equals(dbd.getDiscountType())))
     {
       dinerBill.setOriCost(dinerBill.getOriCost().add(dbd.getRealCost()));
     }
     else {
       dinerBill.setOriCost(dinerBill.getOriCost().add(dbd.getOriCost()));
 
       dinerBill.setSaveCost(BigDecimalUtil.format(dinerBill.getSaveCost().add(dbd.getOriCost()).subtract(dbd.getRealCost())));
     }
 
     dinerBill.setConsumeCost(BigDecimalUtil.format(dinerBill.getOriCost().add(dinerBill.getServiceChargeMoney())));
 
     if (dinerBill.getConsumeCost() != null) {
       BigDecimal temp = dinerBill.getConsumeCost();
       if (dinerBill.getSaveCost() != null) {
         temp = temp.subtract(dinerBill.getSaveCost());
       }
       if (dinerBill.getRateCost() != null) {
         temp = temp.subtract(dinerBill.getRateCost());
       }
       dinerBill.setPayableCost(BigDecimalUtil.format(temp));
     }
 
     setMoling(dinerBill);
     if(dinerBill.getMolingModeCost()==null)
     dinerBill.setPayableCost(BigDecimalUtil.format(dinerBill.getPayableCost().subtract(new BigDecimal(0))));
     else
     dinerBill.setPayableCost(BigDecimalUtil.format(dinerBill.getPayableCost().subtract(dinerBill.getMolingModeCost())));
 
     BigDecimal paymentAll = BigDecimal.ZERO;
     BigDecimal addIntegral = BigDecimal.ZERO;
     MembershipCard membershipCard = dinerBill.getMembershipCard();
 
     List dinerBillPayments = dinerBill.getDinerBillPayments();
     for (int i = 0; i < dinerBillPayments.size(); i++) {
       DinerBillPayment dinerBillPayment = (DinerBillPayment)dinerBillPayments.get(i);
       paymentAll = paymentAll.add(dinerBillPayment.getMoney());
 
       if (membershipCard != null) {
         PaymentType paymentType = dinerBillPayment.getPaymentType();
         if (!TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral()))
           continue;
         if ((dinerBill.getMembershipCard() != null) && ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) || (PaymentTypeEnum.CARD.getCode().equals(paymentType.getPaymentType())) || (PaymentTypeEnum.MEMBER_CARD.getCode().equals(paymentType.getPaymentType())))) {
           addIntegral = addIntegral.add(dinerBillPayment.getMoney());
         }
 
         if (!PaymentTypeEnum.WEB_PAY.getCode().equals(paymentType.getPaymentType()))
           continue;
         TableOrder tableOrder = this.tableOrderService.findByRestIdAndBillId(restId, dinerBill.getBillId());
         if ((tableOrder != null) && (StringUtils.isNotEmpty(tableOrder.getOnlineOrderId())) && (CloudPaymentTypeEnum.MCP.getCode().equals(tableOrder.getOnlinePaymentType())) && 
           (tableOrder.getPrepay() != null) && (tableOrder.getPrepay().compareTo(BigDecimal.ZERO) > 0)) {
           addIntegral = addIntegral.add(dinerBillPayment.getMoney());
         }
 
         Takeout takeout = this.takeoutService.findByRestIdAndDinerBill(restId, dinerBill);
         if ((takeout == null) || (!StringUtils.isNotEmpty(takeout.getOnlineTakeoutId())) || (!CloudPaymentTypeEnum.MCP.getCode().equals(takeout.getOnlinePaymentType())) || 
           (takeout.getTotalCost() == null) || (takeout.getTotalCost().compareTo(BigDecimal.ZERO) <= 0)) continue;
         addIntegral = addIntegral.add(dinerBillPayment.getMoney());
       }
 
     }
 
     if (paymentAll.compareTo(dinerBill.getPayableCost()) == 1) {
       dinerBill.setOddChange(BigDecimalUtil.format(paymentAll.subtract(dinerBill.getPayableCost())));
       dinerBill.setNeedMoney(BigDecimalUtil.format(BigDecimal.valueOf(0L)));
       dinerBill.setRealCost(BigDecimalUtil.format(dinerBill.getPayableCost()));
     }
     else {
       dinerBill.setNeedMoney(BigDecimalUtil.format(dinerBill.getPayableCost().subtract(paymentAll)));
       dinerBill.setOddChange(BigDecimalUtil.format(BigDecimal.valueOf(0L)));
       dinerBill.setRealCost(BigDecimalUtil.format(paymentAll));
     }
 
     if (membershipCard != null) {
       if ((TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral())) && 
         (addIntegral.compareTo(dinerBill.getPayableCost()) >= 0)) {
         addIntegral = dinerBill.getPayableCost();
       }
 
       dinerBill.setAddIntegral(BigDecimalUtil.formatRoundDown(addIntegral));
     }
   }
 
   @Transactional(readOnly=true)
   public void calculatorForDisheSet(String restId, DinerBill dinerBill, DinerBillDishesSet dbds)
   {
     dinerBill = (DinerBill)this.dinerBillDao.findOne(dinerBill.getBillId());
 
     DishesSet dishesSet = (DishesSet)this.dishesSetService.loadOne(dbds.getDsId());
 
     if ((!TrueFalseEnum.TRUE.getCode().equals(dishesSet.getIsAddMinCharge())) && (!DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dbds.getDsStatus())) && (!DishesStatusEnum.SERVED_CANCEL.getCode().equals(dbds.getDsStatus()))) {
       dinerBill.setConsumeLow(dinerBill.getConsumeLow().add(dbds.getOriCost()));
     }
     if ((DiscountTypeEnum.SPECIAL.getCode().equals(dbds.getDiscountType())) || (DiscountTypeEnum.MEMBER.getCode().equals(dbds.getDiscountType())))
     {
       dinerBill.setOriCost(dinerBill.getOriCost().add(dbds.getRealCost()));
     }
     else {
       dinerBill.setOriCost(dinerBill.getOriCost().add(dbds.getOriCost()));
 
       dinerBill.setSaveCost(BigDecimalUtil.format(dinerBill.getSaveCost().add(dbds.getOriCost()).subtract(dbds.getRealCost())));
     }
 
     dinerBill.setConsumeCost(BigDecimalUtil.format(dinerBill.getOriCost().add(dinerBill.getServiceChargeMoney())));
 
     if (dinerBill.getConsumeCost() != null) {
       BigDecimal temp = dinerBill.getConsumeCost();
       if (dinerBill.getSaveCost() != null) {
         temp = temp.subtract(dinerBill.getSaveCost());
       }
       if (dinerBill.getRateCost() != null) {
         temp = temp.subtract(dinerBill.getRateCost());
       }
       dinerBill.setPayableCost(BigDecimalUtil.format(temp));
     }
 
     setMoling(dinerBill);
 
     dinerBill.setPayableCost(BigDecimalUtil.format(dinerBill.getPayableCost().subtract(dinerBill.getMolingModeCost())));
 
     BigDecimal paymentAll = BigDecimal.ZERO;
     BigDecimal addIntegral = BigDecimal.ZERO;
     BigDecimal membercardCost = BigDecimal.ZERO;
     MembershipCard membershipCard = dinerBill.getMembershipCard();
 
     List dinerBillPayments = dinerBill.getDinerBillPayments();
     for (int i = 0; i < dinerBillPayments.size(); i++) {
       DinerBillPayment dinerBillPayment = (DinerBillPayment)dinerBillPayments.get(i);
       paymentAll = paymentAll.add(dinerBillPayment.getMoney());
 
       if (membershipCard != null) {
         PaymentType paymentType = dinerBillPayment.getPaymentType();
         if (TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral()))
         {
           if ((dinerBill.getMembershipCard() != null) && ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) || (PaymentTypeEnum.CARD.getCode().equals(paymentType.getPaymentType())) || (PaymentTypeEnum.MEMBER_CARD.getCode().equals(paymentType.getPaymentType())))) {
             addIntegral = addIntegral.add(dinerBillPayment.getMoney());
           }
 
           if (PaymentTypeEnum.WEB_PAY.getCode().equals(paymentType.getPaymentType()))
           {
             TableOrder tableOrder = this.tableOrderService.findByRestIdAndBillId(restId, dinerBill.getBillId());
             if ((tableOrder != null) && (StringUtils.isNotEmpty(tableOrder.getOnlineOrderId())) && (CloudPaymentTypeEnum.MCP.getCode().equals(tableOrder.getOnlinePaymentType())) && 
               (tableOrder.getPrepay() != null) && (tableOrder.getPrepay().compareTo(BigDecimal.ZERO) > 0)) {
               addIntegral = addIntegral.add(dinerBillPayment.getMoney());
             }
 
             Takeout takeout = this.takeoutService.findByRestIdAndDinerBill(restId, dinerBill);
             if ((takeout != null) && (StringUtils.isNotEmpty(takeout.getOnlineTakeoutId())) && (CloudPaymentTypeEnum.MCP.getCode().equals(takeout.getOnlinePaymentType())) && 
               (takeout.getTotalCost() != null) && (takeout.getTotalCost().compareTo(BigDecimal.ZERO) > 0)) {
               addIntegral = addIntegral.add(dinerBillPayment.getMoney());
             }
 
           }
 
         }
 
         if (PaymentTypeEnum.MEMBER_CARD.getCode().equals(paymentType.getPaymentType())) {
           membercardCost = dinerBillPayment.getMoney();
         }
       }
     }
 
     if (paymentAll.compareTo(dinerBill.getPayableCost()) == 1) {
       dinerBill.setOddChange(BigDecimalUtil.format(paymentAll.subtract(dinerBill.getPayableCost())));
       dinerBill.setNeedMoney(BigDecimalUtil.format(BigDecimal.valueOf(0L)));
       dinerBill.setRealCost(BigDecimalUtil.format(dinerBill.getPayableCost()));
     }
     else {
       dinerBill.setNeedMoney(BigDecimalUtil.format(dinerBill.getPayableCost().subtract(paymentAll)));
       dinerBill.setOddChange(BigDecimalUtil.format(BigDecimal.valueOf(0L)));
       dinerBill.setRealCost(BigDecimalUtil.format(paymentAll));
     }
 
     if (membershipCard != null) {
       if ((TrueFalseEnum.TRUE.getCode().equals(membershipCard.getMembershipCardClass().getIsConsumeIntegral())) && 
         (addIntegral.compareTo(dinerBill.getPayableCost()) >= 0)) {
         addIntegral = dinerBill.getPayableCost();
       }
 
       dinerBill.setAddIntegral(BigDecimalUtil.formatRoundDown(addIntegral));
 
       dinerBill.setMembercardCost(membercardCost);
     }
   }
 
   public void calculatorSingleDishe(String restId, DinerBill dinerBill, DinerBillDishe dbd, Dishe dishe)
   {
     boolean isTableSpecialPrice = false;
     boolean isTableDiscount = false;
     Table table = dinerBill.getTable();
 
     if (table != null) {
       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
       if (TrueFalseEnum.TRUE.getCode().equals(tableArea.getIsSpecialPrice()))
       {
         isTableSpecialPrice = true;
       }
     }
 
     if (table != null) {
       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
       if (TrueFalseEnum.TRUE.getCode().equals(tableArea.getIsOnSale()))
       {
         isTableDiscount = true;
       }
 
     }
 
     calculatorDishe(restId, dinerBill, dbd, isTableSpecialPrice, isTableDiscount, dishe);
   }
 
   public void calculatorSingleDishesSet(String restId, DinerBill dinerBill, DinerBillDishesSet dbds, DishesSet dishesSet)
   {
     boolean isTableSpecialPrice = false;
     boolean isTableDiscount = false;
     Table table = dinerBill.getTable();
 
     if (table != null) {
       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
       if (TrueFalseEnum.TRUE.getCode().equals(tableArea.getIsSpecialPrice()))
       {
         isTableSpecialPrice = true;
       }
     }
 
     if (table != null) {
       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
       if (TrueFalseEnum.TRUE.getCode().equals(tableArea.getIsOnSale()))
       {
         isTableDiscount = true;
       }
 
     }
 
     calculatorDishesSet(restId, dinerBill, dbds, isTableSpecialPrice, isTableDiscount, dishesSet);
   }
 
   private void calculatorDishe(String restId, DinerBill db, DinerBillDishe dbd, boolean isTableSpecialPrice, boolean isTableDiscount, Dishe dishe)
   {
     if (DiscountTypeEnum.GIVE.getCode().equals(dbd.getDiscountType())) {
       dbd.setUnitPrice(BigDecimal.ZERO);
       setDinerBillDishe(dbd, BigDecimal.ZERO, DiscountTypeEnum.GIVE.getCode(), Integer.valueOf(0));
       return;
     }
 
     if (TrueFalseEnum.FALSE.getCode().equals(dbd.getIsUserDefined()))
     {
       boolean isHaveDiscount = false;
 
       if (isTableSpecialPrice) {
         if ((validationMemberCard(db)) && 
           (dishe.getMemberPrice() != null) && (dishe.getMemberPrice().floatValue() > 0.0F))
         {
           setDinerBillDishe(dbd, dishe.getMemberPrice(), DiscountTypeEnum.MEMBER.getCode(), Integer.valueOf(0));
           isHaveDiscount = true;
           return;
         }
 
         if (!isHaveDiscount)
         {
           BigDecimal specialPrice = validationSpecialPrice(dishe, dbd, restId);
           if (specialPrice != null)
           {
             setDinerBillDishe(dbd, specialPrice, DiscountTypeEnum.SPECIAL.getCode(), Integer.valueOf(0));
             isHaveDiscount = true;
             return;
           }
 
         }
 
       }
 
       if ((isTableDiscount) && (!isHaveDiscount))
       {
         if (TrueFalseEnum.TRUE.getCode().equals(dishe.getIsOnsale())) {
           if (db.getCashDiscount() != null)
           {
             setDisheDiscount(restId, dbd, db.getCashDiscount(), db);
             setDinerBillDishe(dbd, dbd.getUnitPrice().multiply(BigDecimal.valueOf(dbd.getDiscount().intValue())).divide(BigDecimal.valueOf(100L)), DiscountTypeEnum.OTHER.getCode(), dbd.getDiscount());
             isHaveDiscount = true;
             return;
           }if (TrueFalseEnum.TRUE.getCode().equals(db.getIsCustomDiscount()))
           {
             setDinerBillDishe(dbd, dbd.getUnitPrice().multiply(BigDecimal.valueOf(db.getDiscount().intValue())).divide(BigDecimal.valueOf(100L)), DiscountTypeEnum.OTHER.getCode(), db.getDiscount());
             isHaveDiscount = true;
             return;
           }
         }
       }
       if (!isHaveDiscount) {
         setDinerBillDishe(dbd, dbd.getUnitPrice(), null, Integer.valueOf(100));
       }
     }
     else
     {
       if ((isTableDiscount) && 
         (TrueFalseEnum.TRUE.getCode().equals(dishe.getIsOnsale()))) {
         if (db.getCashDiscount() != null)
         {
           setDisheDiscount(restId, dbd, db.getCashDiscount(), db);
           setDinerBillDishe(dbd, dbd.getUnitPrice().multiply(BigDecimal.valueOf(dbd.getDiscount().intValue())).divide(BigDecimal.valueOf(100L)), DiscountTypeEnum.OTHER.getCode(), dbd.getDiscount());
           return;
         }if (TrueFalseEnum.TRUE.getCode().equals(db.getIsCustomDiscount()))
         {
           setDinerBillDishe(dbd, dbd.getUnitPrice().multiply(BigDecimal.valueOf(db.getDiscount().intValue())).divide(BigDecimal.valueOf(100L)), DiscountTypeEnum.OTHER.getCode(), db.getDiscount());
           return;
         }
       }
 
       setDinerBillDishe(dbd, dbd.getUnitPrice(), DiscountTypeEnum.OTHER.getCode(), Integer.valueOf(100));
     }
   }
 
   private void calculatorDishesSet(String restId, DinerBill db, DinerBillDishesSet dbds, boolean isTableSpecialPrice, boolean isTableDiscount, DishesSet dishesSet)
   {
     if (DiscountTypeEnum.GIVE.getCode().equals(dbds.getDiscountType())) {
       dbds.setUnitPrice(BigDecimal.ZERO);
       setDinerBillDishesSet(dbds, BigDecimal.ZERO, DiscountTypeEnum.GIVE.getCode(), Integer.valueOf(0));
       return;
     }
 
     if (TrueFalseEnum.FALSE.getCode().equals(dbds.getIsUserDefined()))
     {
       boolean isHaveDiscount = false;
 
       if (isTableSpecialPrice) {
         if ((validationMemberCard(db)) && 
           (dishesSet.getMemberPrice() != null) && (dishesSet.getMemberPrice().floatValue() > 0.0F))
         {
           setDinerBillDishesSet(dbds, dishesSet.getMemberPrice(), DiscountTypeEnum.MEMBER.getCode(), Integer.valueOf(0));
           isHaveDiscount = true;
           return;
         }
 
         if (!isHaveDiscount)
         {
           BigDecimal specialPrice = validationSpecialPriceSet(dishesSet, dbds, restId);
           if (specialPrice != null)
           {
             setDinerBillDishesSet(dbds, specialPrice, DiscountTypeEnum.SPECIAL.getCode(), Integer.valueOf(0));
             isHaveDiscount = true;
             return;
           }
 
         }
 
       }
 
       if ((isTableDiscount) && (!isHaveDiscount))
       {
         if (TrueFalseEnum.TRUE.getCode().equals(dishesSet.getIsOnsale())) {
           if (db.getCashDiscount() != null)
           {
             setDishesSetDiscount(restId, dbds, db.getCashDiscount(), db);
             setDinerBillDishesSet(dbds, dbds.getUnitPrice().multiply(BigDecimal.valueOf(dbds.getDiscount().intValue())).divide(BigDecimal.valueOf(100L)), DiscountTypeEnum.OTHER.getCode(), dbds.getDiscount());
             isHaveDiscount = true;
             return;
           }if (TrueFalseEnum.TRUE.getCode().equals(db.getIsCustomDiscount()))
           {
             setDinerBillDishesSet(dbds, dbds.getUnitPrice().multiply(BigDecimal.valueOf(db.getDiscount().intValue())).divide(BigDecimal.valueOf(100L)), DiscountTypeEnum.OTHER.getCode(), db.getDiscount());
             isHaveDiscount = true;
             return;
           }
         }
       }
       if (!isHaveDiscount) {
         setDinerBillDishesSet(dbds, dbds.getUnitPrice(), null, Integer.valueOf(100));
       }
     }
     else
     {
       if (isTableDiscount) {
         if (db.getCashDiscount() != null)
         {
           setDishesSetDiscount(restId, dbds, db.getCashDiscount(), db);
           setDinerBillDishesSet(dbds, dbds.getUnitPrice().multiply(BigDecimal.valueOf(dbds.getDiscount().intValue())).divide(BigDecimal.valueOf(100L)), DiscountTypeEnum.OTHER.getCode(), dbds.getDiscount());
           return;
         }if (TrueFalseEnum.TRUE.getCode().equals(db.getIsCustomDiscount()))
         {
           setDinerBillDishesSet(dbds, dbds.getUnitPrice().multiply(BigDecimal.valueOf(db.getDiscount().intValue())).divide(BigDecimal.valueOf(100L)), DiscountTypeEnum.OTHER.getCode(), db.getDiscount());
           return;
         }
       }
       setDinerBillDishesSet(dbds, dbds.getUnitPrice(), DiscountTypeEnum.OTHER.getCode(), Integer.valueOf(100));
     }
   }
 
   private void setDinerBillDishe(DinerBillDishe dbd, BigDecimal RealUnitPrice, String discountType, Integer discount)
   {
     dbd.setRealUnitPrice(RealUnitPrice);
     dbd.setDiscountType(discountType);
     dbd.setDiscount(discount);
     dbd.setOriCost(BigDecimalUtil.format(dbd.getUnitPrice().multiply(BigDecimal.valueOf(dbd.getUnitNum()))));
     dbd.setRealCost(BigDecimalUtil.format(dbd.getRealUnitPrice().multiply(BigDecimal.valueOf(dbd.getUnitNum()))));
   }
 
   private void setDinerBillDishesSet(DinerBillDishesSet dbds, BigDecimal RealUnitPrice, String discountType, Integer discount) {
     dbds.setRealUnitPrice(RealUnitPrice);
     dbds.setDiscountType(discountType);
     dbds.setDiscount(discount);
     dbds.setOriCost(BigDecimalUtil.format(dbds.getUnitPrice().multiply(BigDecimal.valueOf(dbds.getUnitNum()))));
     dbds.setRealCost(BigDecimalUtil.format(dbds.getRealUnitPrice().multiply(BigDecimal.valueOf(dbds.getUnitNum()))));
   }
 
   private void resetData(DinerBill db)
   {
     db.setOriCost(new BigDecimal(0.0D));
 
     db.setConsumeCost(new BigDecimal(0.0D));
 
     db.setSaveCost(new BigDecimal(0.0D));
 
     db.setPayableCost(new BigDecimal(0.0D));
 
     db.setConsumeLow(new BigDecimal(0.0D));
 
     db.setNeedMoney(new BigDecimal(0.0D));
 
     db.setDishesTypeDiscountDesc("");
   }
 
   private BigDecimal validationSpecialPrice(Dishe dishe, DinerBillDishe dbd, String restId)
   {
     BigDecimal bd = null;
     List<SpecialDishe> specialDishes = this.specialDisheDao.findByDisheAndRestId(dishe, restId);
     if ((specialDishes == null) || (specialDishes.size() == 0))
     {
       return null;
     }
     if (dbd.getCreateTime() == null) {
       dbd.setCreateTime(new Date());
     }
     for (SpecialDishe specialDishe : specialDishes) {
       SpecialPriceInterval spi = specialDishe.getSpecialPriceInterval();
 
       if ((spi != null) && 
         (TrueFalseEnum.TRUE.getCode().equals(spi.getIsTimeLimit()))) {
         if ((TrueFalseEnum.TRUE.getCode().equals(spi.getIsDateValid())) && (
           (spi.getStartDate().getTime() > dbd.getCreateTime().getTime()) || (spi.getEndDate().getTime() + 86400000L < dbd.getCreateTime().getTime())))
         {
           continue;
         }
 
         if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsSpecialDay())) {
           if (((spi.getIsMonday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsMonday()))) && (DateUtil.getWeekDay(dbd.getCreateTime()) == 1)) {
             continue;
           }
           if (((spi.getIsTuesday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsTuesday()))) && (DateUtil.getWeekDay(dbd.getCreateTime()) == 2)) {
             continue;
           }
           if (((spi.getIsWednesday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsWednesday()))) && (DateUtil.getWeekDay(dbd.getCreateTime()) == 3)) {
             continue;
           }
           if (((spi.getIsThursday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsThursday()))) && (DateUtil.getWeekDay(dbd.getCreateTime()) == 4)) {
             continue;
           }
           if (((spi.getIsFriday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsFriday()))) && (DateUtil.getWeekDay(dbd.getCreateTime()) == 5)) {
             continue;
           }
           if (((spi.getIsSaturday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsSaturday()))) && (DateUtil.getWeekDay(dbd.getCreateTime()) == 6)) {
             continue;
           }
           if (((spi.getIsSunday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsSunday()))) && (DateUtil.getWeekDay(dbd.getCreateTime()) == 0))
           {
             continue;
           }
         }
         if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsTimeValid()))
         {
           String ymd = new SimpleDateFormat("yyyy-MM-dd ").format(dbd.getCreateTime());
           DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           Date stime ;
           Date etime;
           try
           {
              stime = df.parse(ymd + spi.getStartTime());
              etime = df.parse(ymd + spi.getEndTime());
           }
           catch (ParseException e)
           {
 
             e.printStackTrace();
             continue;
           }
 
           if ((stime.getTime() > dbd.getCreateTime().getTime()) || (etime.getTime() < dbd.getCreateTime().getTime()))
           {
             continue;
           }
 
         }
 
       }
 
       if (bd == null) {
         bd = specialDishe.getSpecialPrice();
       }
       else if (bd.subtract(specialDishe.getSpecialPrice()).doubleValue() > 0.0D) {
         bd = specialDishe.getSpecialPrice();
       }
 
     }
 
     return bd;
   }
 
   private BigDecimal validationSpecialPriceSet(DishesSet dishesSet, DinerBillDishesSet dbds, String restId)
   {
     BigDecimal bd = null;
     List<SpecialDishe> specialDishes = this.specialDisheDao.findByDishesSetAndRestId(dishesSet, restId);
     if ((specialDishes == null) || (specialDishes.size() == 0))
     {
       return null;
     }
 
     if (dbds.getCreateTime() == null) {
       dbds.setCreateTime(new Date());
     }
     for (SpecialDishe specialDishe : specialDishes) {
       SpecialPriceInterval spi = specialDishe.getSpecialPriceInterval();
 
       if ((spi != null) && 
         (TrueFalseEnum.TRUE.getCode().equals(spi.getIsTimeLimit()))) {
         if ((TrueFalseEnum.TRUE.getCode().equals(spi.getIsDateValid())) && (
           (spi.getStartDate().getTime() > dbds.getCreateTime().getTime()) || (spi.getEndDate().getTime() + 86400000L < dbds.getCreateTime().getTime())))
         {
           continue;
         }
 
         if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsSpecialDay())) {
           if (((spi.getIsMonday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsMonday()))) && (DateUtil.getWeekDay(dbds.getCreateTime()) == 1)) {
             continue;
           }
           if (((spi.getIsTuesday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsTuesday()))) && (DateUtil.getWeekDay(dbds.getCreateTime()) == 2)) {
             continue;
           }
           if (((spi.getIsWednesday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsWednesday()))) && (DateUtil.getWeekDay(dbds.getCreateTime()) == 3)) {
             continue;
           }
           if (((spi.getIsThursday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsThursday()))) && (DateUtil.getWeekDay(dbds.getCreateTime()) == 4)) {
             continue;
           }
           if (((spi.getIsFriday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsFriday()))) && (DateUtil.getWeekDay(dbds.getCreateTime()) == 5)) {
             continue;
           }
           if (((spi.getIsSaturday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsSaturday()))) && (DateUtil.getWeekDay(dbds.getCreateTime()) == 6)) {
             continue;
           }
           if (((spi.getIsSunday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsSunday()))) && (DateUtil.getWeekDay(dbds.getCreateTime()) == 7))
           {
             continue;
           }
         }
 
         if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsTimeValid()))
         {
           String ymd = new SimpleDateFormat("yyyy-MM-dd ").format(dbds.getCreateTime());
           DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           Date etime;
           Date stime;
           
           try
           {
             stime = df.parse(ymd + spi.getStartTime());
             etime = df.parse(ymd + spi.getEndTime());
           }
           catch (ParseException e)
           {
 
             e.printStackTrace();
             continue;
           }
           
           if ((stime.getTime() > dbds.getCreateTime().getTime()) || (etime.getTime() < dbds.getCreateTime().getTime()))
           {
             continue;
           }
 
         }
 
       }
 
       if (bd == null) {
         bd = specialDishe.getSpecialPrice();
       }
       else if (bd.subtract(specialDishe.getSpecialPrice()).doubleValue() > 0.0D) {
         bd = specialDishe.getSpecialPrice();
       }
     }
 
     return bd;
   }
 
   private void setDisheDiscount(String restId, DinerBillDishe dbd, CashDiscount cashDiscount, DinerBill db)
   {
     CashDishesTypeDiscount cashDishesTypeDiscount = this.cashDishesTypeDiscountService.findOneByCcdIdAndCategoryId(restId, cashDiscount.getCcdId(), dbd.getDishesCategory().getCategoryId());
     if ((cashDishesTypeDiscount != null) && (StringUtils.isNotEmpty(cashDishesTypeDiscount.getCategoryId()))) {
       DishesCategory dishesCategory = (DishesCategory)this.dishesCategoryService.loadOne(cashDishesTypeDiscount.getCategoryId());
 
       dbd.setDiscount(cashDishesTypeDiscount.getCategoryDicount());
       StringBuffer sb = new StringBuffer(db.getDishesTypeDiscountDesc() == null ? "" : db.getDishesTypeDiscountDesc());
       if ((cashDiscount.getMainDiscount().compareTo(cashDishesTypeDiscount.getCategoryDicount()) == 0) || (sb.indexOf(dishesCategory.getCategoryName()) > 0)) {
         return;
       }
       sb.append(",");
       if (cashDishesTypeDiscount.getCategoryDicount().intValue() == 0)
         sb.append(dishesCategory.getCategoryName() + cashDishesTypeDiscount.getCategoryDicount() + "免费");
       else if (cashDishesTypeDiscount.getCategoryDicount().intValue() == 100)
         sb.append(dishesCategory.getCategoryName() + cashDishesTypeDiscount.getCategoryDicount() + "不打折");
       else {
         sb.append(dishesCategory.getCategoryName() + cashDishesTypeDiscount.getCategoryDicount() + "折");
       }
       db.setDishesTypeDiscountDesc(sb.toString());
     }
     else {
       dbd.setDiscount(cashDiscount.getMainDiscount());
     }
   }
 
   private void setDishesSetDiscount(String restId, DinerBillDishesSet dbds, CashDiscount cashDiscount, DinerBill db)
   {
     CashDishesTypeDiscount cashDishesTypeDiscount = this.cashDishesTypeDiscountService.findOneByCcdIdAndDsCategoryId(restId, cashDiscount.getCcdId(), dbds.getDishesSetCategory().getDsCategoryId());
     DishesSetCategory dishesSetCategory = (DishesSetCategory)this.dishesSetCategoryService.loadOne(dbds.getDishesSetCategory().getDsCategoryId());
     if (cashDishesTypeDiscount != null)
     {
       dbds.setDiscount(cashDishesTypeDiscount.getCategoryDicount());
       StringBuffer sb = new StringBuffer(db.getDishesTypeDiscountDesc() == null ? "" : db.getDishesTypeDiscountDesc());
       if ((cashDiscount.getMainDiscount().compareTo(cashDishesTypeDiscount.getCategoryDicount()) == 0) || (sb.indexOf(dishesSetCategory.getCategoryName()) > 0)) {
         return;
       }
       sb.append(",");
       if (cashDishesTypeDiscount.getCategoryDicount().intValue() == 0)
         sb.append(dishesSetCategory.getCategoryName() + cashDishesTypeDiscount.getCategoryDicount() + "免费");
       else if (cashDishesTypeDiscount.getCategoryDicount().intValue() == 100)
         sb.append(dishesSetCategory.getCategoryName() + cashDishesTypeDiscount.getCategoryDicount() + "不打折");
       else {
         sb.append(dishesSetCategory.getCategoryName() + cashDishesTypeDiscount.getCategoryDicount() + "折");
       }
       db.setDishesTypeDiscountDesc(sb.toString());
     }
     else {
       dbds.setDiscount(cashDiscount.getMainDiscount());
     }
   }
 
   private boolean validationMemberCard(DinerBill db)
   {
     MembershipCard memberCard = db.getMembershipCard();
     if (memberCard == null) {
       return false;
     }
     return memberCard.getCardStatus().equals(MemberCardStatusEnum.NORMAL.getCode());
   }
 
   private void setServiceChargeMoney(DinerBill db)
   {
     if ((db.getTable() == null) || (db.getTable().getTableArea().getSeviceChargeType() == null) || (db.getTable().getTableArea().getSeviceChargeNum() == null))
     {
       db.setServiceChargeMoney(BigDecimalUtil.format(BigDecimal.ZERO));
       return;
     }
     db.setServiceChargeType(db.getTable().getTableArea().getSeviceChargeType());
     BigDecimal seviceChargeNum = db.getTable().getTableArea().getSeviceChargeNum();
     db.setSeviceChargeNum(seviceChargeNum);
     if (SeviceChargeTypeEnum.SCALE.getCode().equals(db.getServiceChargeType()))
     {
       db.setServiceChargeMoney(BigDecimalUtil.format(db.getOriCost().multiply(seviceChargeNum).divide(new BigDecimal(100))));
     } else if (SeviceChargeTypeEnum.TIME.getCode().equals(db.getServiceChargeType()))
     {
       int minutes = (int)(DateUtil.getMinutes(db.getCreateTime(), new Date()).longValue() / 5L);
       db.setServiceChargeMoney(BigDecimalUtil.format(BigDecimal.valueOf(minutes).multiply(seviceChargeNum)));
     } else if (SeviceChargeTypeEnum.FIXED.getCode().equals(db.getServiceChargeType()))
     {
       db.setServiceChargeMoney(BigDecimalUtil.format(seviceChargeNum));
     } else if ((SeviceChargeTypeEnum.PEOPLE.getCode().equals(db.getServiceChargeType())) && (db.getPeopleNum() != null))
     {
       db.setServiceChargeMoney(BigDecimalUtil.format(seviceChargeNum.multiply(BigDecimal.valueOf(db.getPeopleNum().intValue()))));
     }
   }
 
   private void setMoling(DinerBill db)
   {
     String molingMode = db.getMolingMode();
     if (StringUtils.isNotEmpty(molingMode)) {
       boolean isRound = TrueFalseEnum.TRUE.getCode().equals(db.getIsRound());
       WipeZeroMaths wipeZeroMaths = new WipeZeroMaths();
       BigDecimal molingAfterCost = wipeZeroMaths.getMolingMoney(db.getPayableCost(), isRound, molingMode);
       db.setMolingModeCost(molingAfterCost);
     }
   }
 
   private void setDeliverCost(String restId, DinerBill dinerBill)
   {
     if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) {
       Takeout takeout = this.takeoutService.findByRestIdAndDinerBill(restId, dinerBill);
       if (takeout != null) {
         BigDecimal deliverCost = takeout.getDeliverCost();
         deliverCost = deliverCost == null ? BigDecimal.ZERO : deliverCost;
         dinerBill.setDeliverCost(deliverCost);
         dinerBill.setPayableCost(dinerBill.getPayableCost().add(deliverCost));
       }
     }
   }
 
   @Autowired
   public void setDao(DinerBillDao dao) {
     super.setDao(dao);
   }
 }

