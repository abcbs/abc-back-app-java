 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardOperationService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.sygl.CashDiscountService;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.qtsy.Takeout;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.repository.qtsy.TakeoutDao;
import com.ndlan.canyin.core.common.BillFromEnum;
import com.ndlan.canyin.core.common.BillOpTypeEnum;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.BillTypeEnum;
import com.ndlan.canyin.core.common.CardOperationTypeEnum;
import com.ndlan.canyin.core.common.CloudPaymentTypeEnum;
import com.ndlan.canyin.core.common.DishesTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.TakeoutStatusEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.BeanUtils;
import com.ndlan.canyin.core.utils.BigDecimalUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 @Service
 public class TakeoutService extends BaseService<TakeoutDao, Takeout>
 {
   private TakeoutDao takeoutDao;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   private PaymentTypeService paymentTypeService;
 
   @Autowired
   private DinerBillPaymentService dinerBillPaymentService;
 
   @Autowired
   private CashDiscountService cashDiscountService;
 
   @Autowired
   private EmployeeService employeeService;
 
   @Autowired
   private MembershipCardOperationService cardOperationService;
 
   public void saveSender(Takeout takeout, LinkedHashMap<String, Object> map)
   {
     OperationTypeEnum o = OperationTypeEnum.CREATE;
     if (StringUtils.isNotEmpty(takeout.gettId()))
       o = OperationTypeEnum.UPDATE;
     else {
       takeout.settId(null);
     }
 
     String senderBy = takeout.getSenderBy();
     if (StringUtils.isNotEmpty(senderBy)) {
       Employee employee = (Employee)this.employeeService.loadOne(takeout.getSenderBy());
       takeout.setSenderName(employee.getName());
     }
 
     this.self.save(takeout);
 
     map.put(takeout.gettId() + "_" + o.getCode(), takeout);
 
     if (StringUtils.isNotEmpty(takeout.getOnlineTakeoutId())) {
       map.put(takeout.getOnlineTakeoutId() + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_takeout set order_status = '" + TakeoutStatusEnum.DELIVERING.getCode() + "' where ut_id = '" + takeout.getOnlineTakeoutId() + "'");
     }
 
     DinerBill dinerBill = takeout.getDinerBill();
     dinerBill.setBillStatus(BillStatusEnum.SENDING.getCode());
     this.dinerBillService.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
     String logNotes = "派送操作";
     logNotes = logNotes + "，单号:" + dinerBill.getBillNo();
 
     this.dinerBillService.saveDinerBillLog(dinerBill.getRestaurant().getRestId(), dinerBill, BillOpTypeEnum.SENDING, logNotes, map);
   }
 
   @Transactional(readOnly=false)
   public DinerBill saveCloudTakeoutBIll(String restId, Employee user, Map<String, Object> takeoutMap, List<Map<String, Object>> takeoutBills, MembershipCard membershipCard, LinkedHashMap<String, Object> map, String orderPeople, String mobile, String linkmanTel, String sendTime, String deliverCost, String sendAddress, String invoiceType, String invoiceTitle)
     throws Exception
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 
     String onlineId = (String)takeoutMap.get("billId");
     String onlineNo = (String)takeoutMap.get("billNo");
     String onlinePaymentType = (String)takeoutMap.get("paymentType");
     String ccdId = (String)takeoutMap.get("ccdId");
     Takeout takeout = new Takeout();
     takeout.setOnlineTakeoutId(onlineId);
     takeout.setOnlinePaymentType(onlinePaymentType);
     takeout.setOnlineTakeoutNo(onlineNo);
     takeout.setBillFrom((String)takeoutMap.get("billFrom"));
     takeout.setOnlineUserMobile((String)takeoutMap.get("onlineUserMobile"));
     takeout.setCcdId(ccdId);
     takeout.setContactName(orderPeople);
     takeout.setTelephone(linkmanTel);
     takeout.setMobile(mobile);
     takeout.setSendTime(sdf.parse(sendTime));
     if (deliverCost.trim().equals("")) {
       deliverCost = "0";
     }
     takeout.setDeliverCost(new BigDecimal(deliverCost));
     String totalCostStr = takeoutMap.get("totalCost").toString();
     String deliverCostStr = takeoutMap.get("deliverCost").toString();
 
     takeout.setTotalCost(takeout.getDeliverCost().add(new BigDecimal(totalCostStr).subtract(new BigDecimal(deliverCostStr))));
     BigDecimal totalCost = takeout.getTotalCost();
     takeout.setSendAddress(sendAddress);
     takeout.setInvoiceTitle(invoiceTitle);
     takeout.setMcId((String)takeoutMap.get("mcId"));
     takeout.setCustomNote((String)takeoutMap.get("customNote"));
     this.self.save(takeout);
 
     map.put(takeout.gettId() + "sctb_" + OperationTypeEnum.CREATE.getCode(), BeanUtils.deepCopy(takeout));
 
     DinerBill d = new DinerBill();
     d.setBillTime(new Date());
     d.setBillType(BillTypeEnum.WAIMAI_BILL.getCode());
     d.setTable(null);
     d.setIsValid(TrueFalseEnum.TRUE.getCode());
     d.setIsShift(TrueFalseEnum.FALSE.getCode());
     d.setBillFrom((String)takeoutMap.get("billFrom"));
     d.setNotes(takeoutMap.get("customNote").toString());
     d.setOnlineBillId(onlineId);
     d.setOnlineBillNo(onlineNo);
 
     if (StringUtils.isNotEmpty(ccdId)) {
       CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(ccdId);
       if (cashDiscount != null)
       {
         d.setCashDiscount(cashDiscount);
 
         d.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
 
         d.setDiscountName(cashDiscount.getDiscountName());
       }
     }
 
     this.dinerBillService.saveCreateTableDinerBill(restId, d, null, takeout.gettId(), map);
     takeout.setDinerBill(d);
     this.self.save(takeout);
 
     map.put(takeout.gettId() + "sctb2_" + OperationTypeEnum.UPDATE.getCode(), takeout);
 
     if ((takeoutBills != null) && (takeoutBills.size() > 0)) {
       for (Map dish : takeoutBills) {
         String dishSetDiv = (String)dish.get("dishSetDiv");
         float unitNum = Float.valueOf((String)dish.get("unitNum")).floatValue();
 
         String dishesNote = null;
         Object objectDishesNote = dish.get("note");
         if (objectDishesNote != null) {
           dishesNote = objectDishesNote.toString();
         }
 
         String dishesTasteIdArray = null;
         Object objectDishesTasteIdArray = dish.get("tasteIdArray");
         if (objectDishesTasteIdArray != null) {
           dishesTasteIdArray = objectDishesTasteIdArray.toString();
         }
 
         String dishesAvoidfoodIdArray = null;
         Object objectAvoidfoodIdArray = dish.get("avoidfoodIdArray");
         if (objectAvoidfoodIdArray != null) {
           dishesAvoidfoodIdArray = objectAvoidfoodIdArray.toString();
         }
 
         Integer dishesPungentLevel = null;
         Object objectDishesPungentLevel = dish.get("pungentLevel");
         if (objectDishesPungentLevel != null) {
           dishesPungentLevel = Integer.valueOf(Integer.parseInt(objectDishesPungentLevel.toString()));
         }
 
         if (DishesTypeEnum.DISHES.getCode().equals(dishSetDiv)) {
           String dishesId = (String)dish.get("dishesId");
           this.dinerBillService.saveJiacai(restId, d.getBillId(), dishesId, unitNum, dishesNote, dishesTasteIdArray, dishesAvoidfoodIdArray, dishesPungentLevel, user, null, true, map);
         }
         else {
           String dsId = (String)dish.get("dsId");
           String dsDishesDesc = (String)dish.get("dsDishesDesc");
           this.dinerBillService.saveDishesSet(restId, d.getBillId(), dsId, BillTypeEnum.WAIMAI_BILL.getCode(), dsDishesDesc, dishesNote, dishesTasteIdArray, dishesAvoidfoodIdArray, dishesPungentLevel, user, Float.valueOf(unitNum), takeout.gettId(), map, map);
         }
 
       }
 
     }
 
     String mcId = (String)takeoutMap.get("mcId");
 
     if (CloudPaymentTypeEnum.MCP.getCode().equals(onlinePaymentType)) {
       if (membershipCard != null)
       {
         if (membershipCard != null) {
           membershipCard.setBalance(membershipCard.getBalance().subtract(totalCost));
 
           this.membershipCardService.save(membershipCard);
           map.put(membershipCard.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), membershipCard);
 
           DinerBillPayment dinerBillPayment = new DinerBillPayment();
           PaymentType pType = this.paymentTypeService.findeByRestIdAndPaymentType(restId, PaymentTypeEnum.WEB_PAY.getCode());
           dinerBillPayment.setDinerBill(d);
           dinerBillPayment.setPaymentType(pType);
           dinerBillPayment.setBillNo(d.getBillNo());
           dinerBillPayment.setRestId(restId);
           dinerBillPayment.setPayTime(new Date());
           dinerBillPayment.setMoney(totalCost);
           this.dinerBillPaymentService.save(dinerBillPayment);
 
           map.put(dinerBillPayment.getDbpId() + "_" + OperationTypeEnum.CREATE.getCode(), dinerBillPayment);
 
           DinerBill dinerBill = this.dinerBillPaymentService.useMembershipCardForPay(d.getBillId(), mcId);
           dinerBill.setOnlineBillId(onlineId);
           dinerBill.setOnlineBillNo(onlineNo);
 
           this.dinerBillService.save(dinerBill);
           map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
           MembershipCardOperation cardOperation = new MembershipCardOperation();
           if (membershipCard.getRestMemberInfo() != null)
           {
             RestMemberInfo restMemberInfo = membershipCard.getRestMemberInfo();
             cardOperation.setRestMemberInfo(restMemberInfo);
           }
           cardOperation.setMembershipCard(membershipCard);
           cardOperation.setCardOperationType(CardOperationTypeEnum.CONSUME.getCode());
           cardOperation.setAddIntegral(BigDecimal.ZERO);
           if (membershipCard.getMemberIntegral() != null)
             cardOperation.setTotalIntegral(membershipCard.getMemberIntegral());
           else {
             cardOperation.setTotalIntegral(BigDecimal.ZERO);
           }
           cardOperation.setIsDrawBill(dinerBill.getIsDrawBill());
           cardOperation.setDrawBillAmount(dinerBill.getDrawBillAmount());
           cardOperation.setDinerBill(dinerBill);
           cardOperation.setBillNo(dinerBill.getBillNo());
           cardOperation.setBalance(membershipCard.getBalance());
           cardOperation.setConsumeCash(totalCost);
           cardOperation.setPaymentType(pType);
 
           StringBuilder remarks = new StringBuilder();
           remarks.append(dinerBillPayment.getPaymentType().getPaymentName());
           remarks.append("：");
           remarks.append(BigDecimalUtil.format(dinerBillPayment.getMoney()));
           cardOperation.setRemarks(remarks.toString());
           this.cardOperationService.save(cardOperation);
 
           map.put(cardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), cardOperation);
         }
       }
 
     }
     else if ((CloudPaymentTypeEnum.COD.getCode().equals(onlinePaymentType)) && 
       (!StringUtils.isEmpty(mcId))) {
       DinerBill dinerBill = this.dinerBillPaymentService.useMembershipCardForPay(d.getBillId(), mcId);
       dinerBill.setOnlineBillId(onlineId);
       dinerBill.setOnlineBillNo(onlineNo);
 
       this.dinerBillService.save(dinerBill);
       map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
     }
 
     Object df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
     String creatTime = (String)takeoutMap.get("createTime");
 
     String billFrom = (String)takeoutMap.get("billFrom");
 
     String note = "";
     try
     {
       BillOpTypeEnum billOpTypeEnum = null;
       if (BillFromEnum.NET_TAKEOUT.getCode().equals(billFrom)) {
         billOpTypeEnum = BillOpTypeEnum.FROM_CLOUD_SUBMIT;
         note = "外卖云订单提交";
       } else if (BillFromEnum.WX_TAKEOUT.getCode().equals(billFrom)) {
         billOpTypeEnum = BillOpTypeEnum.FROM_WEIXIN_SUBMIT;
         note = "外卖微信订单提交";
       }
 
       this.dinerBillService.saveDinerBillLogForOther(restId, onlineId, onlineNo, null, billOpTypeEnum, ((DateFormat)df).parse(creatTime), note, map);
       if (BillFromEnum.NET_TAKEOUT.getCode().equals(billFrom)) {
         note = "外卖云订单审核通过";
       } else if (BillFromEnum.WX_TAKEOUT.getCode().equals(billFrom)) {
         billOpTypeEnum = BillOpTypeEnum.FROM_WEIXIN_SUBMIT;
         note = "外卖微信订单审核通过";
       }
 
       this.dinerBillService.saveDinerBillLogForOther(restId, onlineId, onlineNo, null, BillOpTypeEnum.APPROVED, null, note, map);
     } catch (ParseException e) {
       e.printStackTrace();
     }
     return (DinerBill)d;
   }
 
   public Takeout findByRestIdAndDinerBill(String restId, DinerBill dinerBill)
   {
     return this.takeoutDao.findByRestIdAndDinerBill(restId, dinerBill);
   }
 
   public Takeout findByRestIdAndOnlineTakeoutId(String restId, String onlineTakeoutId)
   {
     return this.takeoutDao.findByRestIdAndOnlineTakeoutId(restId, onlineTakeoutId);
   }
 
   public List<String> getMobileList(Date dateTime, String restId, String mobile)
   {
     return this.takeoutDao.getMobileList(dateTime, restId, mobile);
   }
 
   public List<String> getSendAddressListByMobile(Date dateTime, String restId, String mobile)
   {
     return this.takeoutDao.getSendAddressListByMobile(dateTime, restId, mobile);
   }
 
   public List<String> getSendAddressListByTelephone(Date dateTime, String restId, String telephone)
   {
     return this.takeoutDao.getSendAddressListByTelephone(dateTime, restId, telephone);
   }
 
   public List<Takeout> getLastTakeoutByMobile(String restId, String mobile)
   {
     return this.takeoutDao.findByRestIdAndMobileOrderByCreateTimeDesc(restId, mobile);
   }
 
   public List<Takeout> getLastSendAtOnce() {
     return this.takeoutDao.getLastSendAtOnce();
   }
   @Autowired
   public void setDao(TakeoutDao dao) { super.setDao(dao);
     this.takeoutDao = dao;
   }
   public  List<Takeout> geTakeout(String billId)
   {
     return this.takeoutDao.geTakeout(billId);
   }
 }

