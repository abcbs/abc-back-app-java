 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableAreaService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardOperationService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.frontdesk.service.xtgl.BusiLogService;
import com.ndlan.canyin.frontdesk.util.CancelBillAndOrder;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.ToPinYin;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
 import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
 import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.entity.qtsy.TableOrder;
 import com.ndlan.canyin.base.entity.sygl.PaymentType;
 import com.ndlan.canyin.base.entity.xtgl.BusiLog;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillDisheDao;
 import com.ndlan.canyin.base.repository.qtsy.OrderBillSpecs;
 import com.ndlan.canyin.base.repository.qtsy.TableOrderDao;
 import com.ndlan.canyin.core.common.BillFromEnum;
 import com.ndlan.canyin.core.common.BillOpTypeEnum;
 import com.ndlan.canyin.core.common.BillSeqTypeEnum;
 import com.ndlan.canyin.core.common.BussLogTypeEnum;
 import com.ndlan.canyin.core.common.CardOperationTypeEnum;
 import com.ndlan.canyin.core.common.CloudPaymentTypeEnum;
 import com.ndlan.canyin.core.common.DishesTypeEnum;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.common.OrderStatusEnum;
 import com.ndlan.canyin.core.common.OrderWayEnum;
 import com.ndlan.canyin.core.common.PaymentTypeEnum;
 import com.ndlan.canyin.core.common.TabBillTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.utils.DateProvider;
 import java.math.BigDecimal;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import javax.persistence.criteria.CriteriaBuilder;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Join;
 import javax.persistence.criteria.JoinType;
 import javax.persistence.criteria.Path;
 import javax.persistence.criteria.Predicate;
 import javax.persistence.criteria.Root;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class TableOrderService extends BaseService<TableOrderDao, TableOrder>
 {
   private TableOrderDao tableOrderDao;
   private DinerBillDisheDao dinerBillDisheDao;
 
   @Autowired
   private TableBillRelationService tableBillRelationService;
 
   @Autowired
   private BusiLogService busiLogService;
 
   @Autowired
   PaymentTypeService paymentTypeService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   RestMemberInfoService restMemberInfoService;
 
   @Autowired
   private TableService tableService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private MembershipCardOperationService cardOperationService;
 
   @Autowired
   private TableAreaService tableAreaService;
 
   @Autowired
   DinerBillPaymentService dinerBillPaymentService;
 
   public Page<TableOrder> findByKeywords(final String restId,final Map<String, Object> searchParams,final  String keywords, int pageNumber, int pagzSize, String direction, String sortCol)
   {
     Sort.Direction d = Sort.Direction.DESC;
     if ("asc".equals(direction))
     {
       d = Sort.Direction.ASC;
     }
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { sortCol, "orderNo" }));
     return this.tableOrderDao.findAll(new Specification()
     {
//       public Predicate toPredicate(Root<TableOrder> root, CriteriaQuery<?> cq, CriteriaBuilder cb)
       public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb)
       {
         String areaId = (String)searchParams.get("EQ_tableArea.areaId");
         String orderStatus = (String)searchParams.get("EQ_orderStatus");
         Predicate predicate = cb.conjunction();
 
         if (StringUtils.isNotEmpty(restId))
         {
           Path p_restId = root.get("restId");
           predicate = cb.and(cb.equal(p_restId, restId), predicate);
         }
 
         if (StringUtils.isNotEmpty(areaId)) {
           Join table = root.join("table", JoinType.LEFT);
           Join tableArea = table.join("tableArea", JoinType.LEFT);
           Path p_areaId = tableArea.get("areaId");
           predicate = cb.and(cb.equal(p_areaId, areaId), predicate);
         }
 
         if (StringUtils.isNotEmpty(orderStatus)) {
           if (OrderStatusEnum.EXPIRE.getCode().equals(orderStatus))
           {
             Path p_orderTime = root.get("orderTime");
             predicate = cb.and(cb.lessThan(p_orderTime, cb.currentTimestamp()), predicate);
 
             Path p_orderStatus = root.get("orderStatus");
 
             predicate = cb.and(cb.or(new Predicate[] { cb.equal(p_orderStatus, OrderStatusEnum.WAIT_COMMIT.getCode()), cb.equal(p_orderStatus, OrderStatusEnum.APPLING.getCode()), cb.equal(p_orderStatus, OrderStatusEnum.APPLING.getCode()) }), 
               predicate);
           }
           else if (OrderStatusEnum.APPLING.getCode().equals(orderStatus))
           {
             Path p_orderTime = root.get("orderTime");
             predicate = cb.and(cb.lessThan(cb.currentTimestamp(), p_orderTime), predicate);
 
             Path p_orderStatus = root.get("orderStatus");
 
             predicate = cb.and(cb.or(new Predicate[] { cb.equal(p_orderStatus, OrderStatusEnum.WAIT_COMMIT.getCode()), cb.equal(p_orderStatus, OrderStatusEnum.APPLING.getCode()), cb.equal(p_orderStatus, OrderStatusEnum.APPLING.getCode()) }), 
               predicate);
           }
           else
           {
             Path p_orderStatus = root.get("orderStatus");
             predicate = cb.and(cb.equal(p_orderStatus, orderStatus), predicate);
           }
 
         }
 
         if (StringUtils.isNotEmpty(keywords))
         {
           Path telphone = root.get("telphone");
 
         /*  Path orderNo = root.get("orderNo");
 
           Path orderPeopleName = root.get("orderPeopleName");
           Path orderPeopleNameCode = root.get("orderPeopleNameCode");*/
          // , cb.like(orderNo, "%" + keywords + "%"), cb.like(orderPeopleName, "%" + keywords + "%"), cb.like(orderPeopleNameCode, "%" + keywords + "%")
           predicate = cb.and(cb.or(new Predicate[] { cb.like(telphone, "%" + keywords + "%") }), predicate);
         }
         return predicate;
       }
     }
     , pageRequest);
   }
 
   public Page<TableOrder> getPage(PageRequest pageRequest, String status, String keyWords, String restId)
   {
     return this.tableOrderDao.findAll(OrderBillSpecs.searchPage(status, keyWords, restId), pageRequest);
   }
 
   public String buildOrderNo()
   {
     return null;
   }
 
   public TableOrder findTableOrderById(String id)
   {
     return (TableOrder)this.tableOrderDao.findOne(id);
   }
 
   public List<TableOrder> findTableOrders(Table table) {
     return this.tableOrderDao.findByTable(table);
   }
 
   @Transactional(readOnly=false)
   public TableOrder saveOrder(String restId, TableOrder tableOrder, LinkedHashMap<String, Object> map)
   {
     tableOrder.setOrderTime(DateUtil.toDate(tableOrder.getOrderTimeStr()));
     tableOrder.setOrderPeopleNameCode(ToPinYin.getFirstSpell(tableOrder.getOrderPeopleName()));
 
     tableOrder.setPrepay(tableOrder.getPrepay() == null ? BigDecimal.ZERO : tableOrder.getPrepay());
     String op = OperationTypeEnum.UPDATE.getCode();
     if (StringUtils.isEmpty(tableOrder.getOrderId()))
     {
       op = OperationTypeEnum.CREATE.getCode();
     }
 
     if (tableOrder.getTable() != null)
     {
       tableOrder.getTable().getTabNo();
     }
     this.self.save(tableOrder);
 
     map.put(tableOrder.getOrderId() + "so_" + op, tableOrder);
 
     op = OperationTypeEnum.UPDATE.getCode();
     TableBillRelation tableBillRelation = new TableBillRelation();
     if (tableOrder.getTableBillRelation() != null) {
       tableBillRelation = tableOrder.getTableBillRelation();
       op = OperationTypeEnum.CREATE.getCode();
     }
     tableBillRelation.setTable(tableOrder.getTable());
     tableBillRelation.setPeopleNum(tableOrder.getPeopleNum());
     tableBillRelation.setBillTime(tableOrder.getOrderTime());
     tableBillRelation.setTableOrder(tableOrder);
 
     tableBillRelation.setTabBillType(TabBillTypeEnum.YUDING.getCode());
     this.tableBillRelationService.save(tableBillRelation);
 
     map.put(tableBillRelation.getTabBillId() + "so_" + op, tableBillRelation);
 
     String logNotes = "预订";
     saveBusiLog(restId, tableOrder, BussLogTypeEnum.ORDER, BillOpTypeEnum.YUDING, logNotes);
 
     return tableOrder;
   }
 
   public void saveBusiLog(String restId, TableOrder tableOrder, BussLogTypeEnum bussLogTypeEnum, BillOpTypeEnum billOpTypeEnum, String notes)
   {
     BusiLog busiLog = new BusiLog();
     busiLog.setRestId(restId);
 
     busiLog.setBussLogType(bussLogTypeEnum.getCode());
 
     busiLog.setOpType(billOpTypeEnum.getCode());
     if (tableOrder.getTable() != null)
     {
       busiLog.setTabId(tableOrder.getTable().getTabId());
       busiLog.setTabNo(tableOrder.getTable().getTabNo());
     }
 
     busiLog.setOrderId(tableOrder.getOrderId());
 
     busiLog.setOrderNo(tableOrder.getOrderNo());
 
     busiLog.setPayTime(tableOrder.getOrderTime());
     busiLog.setNotes(notes);
 
     this.busiLogService.save(busiLog);
   }
 
   public List<String> getTableOrderInfo()
   {
     List list = new ArrayList();
     list.add(String.valueOf(this.tableOrderDao.count(OrderBillSpecs.getTableOrderInfo("2"))));
     list.add(String.valueOf(this.tableOrderDao.count(OrderBillSpecs.getTableOrderInfo("3"))));
     return list;
   }
 
   public BigDecimal getPrepaySumByPaymentType(String restId, String empId, Date createTime, String paymentType)
   {
     return this.tableOrderDao.getPrepaySumByPaymentType(restId, empId, createTime, paymentType);
   }
 
   @Transactional(readOnly=false)
   public void saveDinerBill(DinerBill dinerBill)
   {
     this.dinerBillService.save(dinerBill);
   }
 
   public void saveDinerBillDishes(Set<DinerBillDishe> dinerBillDishes)
   {
     this.dinerBillDisheDao.save(dinerBillDishes);
   }
 
   public int[] getOrderStatusNum(String restId)
   {
     int[] nums = new int[2];
 
     List orders = this.tableOrderDao.findByRestIdAndOrderStatus(restId, OrderStatusEnum.APPLING.getCode(), DateProvider.DEFAULT.getDate());
 
     List waiters = this.tableOrderDao.findByRestIdAndOrderStatus(restId, OrderStatusEnum.WAIT_COMMIT.getCode());
 
     if (orders != null)
     {
       nums[0] = orders.size();
     }
     if (waiters != null)
     {
       nums[1] = waiters.size();
     }
     return nums;
   }
 
   @Transactional(readOnly=false)
   public void saveCloudOrderBIll(String restId, Map<String, Object> order, List<Map<String, Object>> orderBills, MembershipCard membershipCard, String tabIds, Employee user, LinkedHashMap<String, Object> map, String orderPeople, String mobile, String eatTime, String peopleNum, String gender)
   {
     String onlineId = (String)order.get("billId");
 
     String onlineNo = (String)order.get("billNo");
 
     String onLinePaymentType = (String)order.get("paymentType");
     String[] tabIdsArray = tabIds.split(",");
     for (int i = 0; i < orderBills.size(); i++)
     {
       String tabId = tabIdsArray[i];
       Map bill = (Map)orderBills.get(i);
       TableOrder tableOrder = new TableOrder();
       tableOrder.setRestId(restId);
       tableOrder.setOrderStatus(OrderStatusEnum.APPLING.getCode());
       tableOrder.setTelphone(mobile);
       tableOrder.setCcdId((String)bill.get("ccdId"));
       tableOrder.setOnlineUobId((String)bill.get("uobId"));
 
       String mcId = (String)order.get("mcId");
       if (membershipCard != null) {
         String mbId = membershipCard.getRestMemberInfo().getMbId();
         tableOrder.setMbId(mbId);
         tableOrder.setMcId(mcId);
       } else if (!StringUtils.isEmpty(mobile)) {
         RestMemberInfo restMemberInfo = this.restMemberInfoService.findByMobile(mobile, restId);
         if (restMemberInfo != null) {
           String mbId = restMemberInfo.getMbId();
           tableOrder.setMbId(mbId);
         }
       }
 
       tableOrder.setOrderPeopleName(orderPeople);
       tableOrder.setPeopleNum(Integer.valueOf(Integer.parseInt(peopleNum)));
       tableOrder.setGender(gender);
 
       tableOrder.setOnlinePaymentType(onLinePaymentType);
       BigDecimal oriCost = new BigDecimal(bill.get("oriCostBill").toString());
       tableOrder.setOriCost(oriCost);
       if (bill.get("isMoling") != null) {
         tableOrder.setIsMoling(bill.get("isMoling").toString());
       }
       if (bill.get("molingModeCost") != null) {
         BigDecimal molingModeCost = new BigDecimal(bill.get("molingModeCost").toString());
         tableOrder.setMolingModeCost(molingModeCost);
       }
 
       if (CloudPaymentTypeEnum.MCP.getCode().equals(onLinePaymentType)) {
         PaymentType paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(restId, PaymentTypeEnum.WEB_PAY.getCode());
         tableOrder.setPaymentType(paymentType);
         BigDecimal realCostOrder = new BigDecimal(bill.get("realCostBill").toString());
         tableOrder.setPrepay(realCostOrder);
       } else if (CloudPaymentTypeEnum.TRP.getCode().equals(onLinePaymentType)) {
         PaymentType paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(restId, PaymentTypeEnum.CASH.getCode());
         tableOrder.setPaymentType(paymentType);
       }
 
       if (!StringUtils.isEmpty(tabId))
       {
         Table table = (Table)this.tableService.getOne(tabId);
         tableOrder.setTabId(tabId);
         tableOrder.setTable(table);
         tableOrder.setTabNo(table.getTabNo());
       }
       tableOrder.setOrderWay(OrderWayEnum.RESTWEB.getCode());
       tableOrder.setOrderTimeStr(eatTime);
       tableOrder.setDiningTime(DateUtil.toDate(eatTime));
       tableOrder.setOrderNo(this.dinerBillSeqService.createNewBillNo(restId, BillSeqTypeEnum.ORDER, map));
 
       tableOrder.setGender((String)order.get("gender"));
       tableOrder.setOnlineOrderId(onlineId);
       tableOrder.setOnlineOrderNo(onlineNo);
       tableOrder.setOnlineDiningTime(tableOrder.getDiningTime());
       tableOrder.setOnlineGender(tableOrder.getGender());
       tableOrder.setOnlineMobile((String)order.get("mobile"));
       tableOrder.setOnlineOrderPeopleName(tableOrder.getOrderPeopleName());
       tableOrder.setOnlinePeopleNum(tableOrder.getPeopleNum());
       tableOrder.setOnlineTelphone(tableOrder.getTelphone());
       tableOrder.setBillFrom((String)order.get("billFrom"));
       tableOrder.setOnlineUserMobile((String)order.get("onlineUserMobile"));
 
       String onlineAreaId = (String)order.get("tableAreaId");
       if (StringUtils.isNotEmpty(onlineAreaId)) {
         TableArea tableArea = (TableArea)this.tableAreaService.getOne(onlineAreaId);
         tableOrder.setOnlineAreaId(onlineAreaId);
         tableOrder.setOnlineAreaName(tableArea.getName());
       }
 
       if (bill.get("note") != null) {
         tableOrder.setNotes(bill.get("note").toString());
       }
       saveOrder(restId, tableOrder, map);
 
       List<Map> dishMap = (List)bill.get("dishMap");
       if ((dishMap == null) || (dishMap.size() <= 0))
         continue;
       for (Map dish : dishMap)
       {
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
 
         if (DishesTypeEnum.DISHES.getCode().equals(dishSetDiv))
         {
           String dishesId = dish.get("dishesId").toString();
           this.dinerBillService.saveOrderJiacai(tableOrder.getOrderId(), dishesId, dishesNote, dishesTasteIdArray, dishesAvoidfoodIdArray, dishesPungentLevel, user, restId, unitNum, map);
         }
         else
         {
           String dsId = (String)dish.get("dsId");
           String dsDishesDesc = (String)dish.get("dsDishesDesc");
           this.dinerBillService.saveOrderDishesSet(tableOrder.getOrderId(), dsId, dishesNote, dishesTasteIdArray, dishesAvoidfoodIdArray, dishesPungentLevel, user, restId, dsDishesDesc, unitNum, map);
         }
 
       }
 
     }
 
     if (CloudPaymentTypeEnum.MCP.getCode().equals(onLinePaymentType)) {
       PaymentType paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(restId, PaymentTypeEnum.WEB_PAY.getCode());
       BigDecimal realCostOrder = new BigDecimal(order.get("realCostOrder").toString());
       membershipCard.setBalance(membershipCard.getBalance().subtract(realCostOrder));
       this.membershipCardService.save(membershipCard);
 
       map.put(membershipCard.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), membershipCard);
 
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
       cardOperation.setIsDrawBill(null);
       cardOperation.setDrawBillAmount(null);
       cardOperation.setDinerBill(null);
       cardOperation.setBillNo(null);
       cardOperation.setBalance(membershipCard.getBalance());
       cardOperation.setConsumeCash(realCostOrder);
       cardOperation.setPaymentType(paymentType);
 
       StringBuilder remarks = new StringBuilder();
       remarks.append(paymentType.getPaymentName());
       remarks.append("：");
       remarks.append(BigDecimalUtil.format(realCostOrder));
       cardOperation.setRemarks(remarks.toString());
       this.cardOperationService.save(cardOperation);
 
       map.put(cardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), cardOperation);
     }
 
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
     String creatTime = (String)order.get("createTime");
 
     String billFrom = (String)order.get("billFrom");
 
     String note = "";
     try
     {
       BillOpTypeEnum billOpTypeEnum = null;
       if (BillFromEnum.NET_ORDER.getCode().equals(billFrom)) {
         billOpTypeEnum = BillOpTypeEnum.FROM_CLOUD_SUBMIT;
         note = "预订云订单提交";
       } else if (BillFromEnum.WX_ORDER.getCode().equals(billFrom)) {
         billOpTypeEnum = BillOpTypeEnum.FROM_WEIXIN_SUBMIT;
         note = "预订微信订单提交";
       }
 
       this.dinerBillService.saveDinerBillLogForOther(restId, onlineId, onlineNo, null, billOpTypeEnum, df.parse(creatTime), note, map);
       if (BillFromEnum.NET_ORDER.getCode().equals(billFrom)) {
         note = "预订云订单审核通过";
       } else if (BillFromEnum.WX_ORDER.getCode().equals(billFrom)) {
         billOpTypeEnum = BillOpTypeEnum.FROM_WEIXIN_SUBMIT;
         note = "预订微信订单审核通过";
       }
 
       this.dinerBillService.saveDinerBillLogForOther(restId, onlineId, onlineNo, null, BillOpTypeEnum.APPROVED, null, note, map);
     } catch (ParseException e) {
       e.printStackTrace();
     }
   }
 
   public List<TableOrder> findByRestIdAndOnlineOrderId(String restId, String onlineOrderId)
   {
     return this.tableOrderDao.findByRestIdAndOnlineOrderId(restId, onlineOrderId);
   }
 
   public TableOrder findByRestIdAndBillId(String restId, String billId)
   {
     return this.tableOrderDao.findByRestIdAndBillId(restId, billId);
   }
 
   @Transactional(readOnly=false)
   public void cancelOrder(String restId, String orderId, String cancleReason, LinkedHashMap<String, Object> map)
   {
     TableOrder tableOrder = (TableOrder)this.self.getOne(orderId);
     tableOrder.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
     tableOrder.setCancleReason(cancleReason);
     this.self.save(tableOrder);
 
     map.put(tableOrder.getOrderId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
 
     List<TableBillRelation> ls = this.tableBillRelationService.findByTableAndTabBillType(tableOrder.getTable(), TabBillTypeEnum.YUDING.getCode());
     for (TableBillRelation e : ls)
     {
       this.tableBillRelationService.delete(e.getTabBillId());
 
       map.put(e.getTabBillId() + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_table_bill_relation,tab_bill_id," + e.getTabBillId());
     }
 
     String onlineBillId = tableOrder.getOnlineOrderId();
     if (StringUtils.isNotEmpty(onlineBillId))
     {
       BigDecimal membercardCost = returnBalance(restId, tableOrder, map);
 
       CancelBillAndOrder cancelBillAndOrder = new CancelBillAndOrder();
       cancelBillAndOrder.updateUserOrder(membercardCost, onlineBillId, tableOrder, cancleReason, map);
     }
   }
 
   private BigDecimal returnBalance(String restId, TableOrder tableOrder, LinkedHashMap<String, Object> map)
   {
     BigDecimal membercardCost = null;
     if (StringUtils.isNotEmpty(tableOrder.getMcId())) {
       MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(tableOrder.getMcId());
       if (membershipCard != null) {
         PaymentType paymentType = tableOrder.getPaymentType();
         if ((paymentType != null) && (PaymentTypeEnum.WEB_PAY.getCode().equals(paymentType.getPaymentType()))) {
           membercardCost = tableOrder.getPrepay();
 
           membershipCard.setBalance(membershipCard.getBalance().add(membercardCost));
           this.membershipCardService.save(membershipCard);
 
           map.put(membershipCard.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), membershipCard);
 
           MembershipCardOperation membershipCardOperation = new MembershipCardOperation();
           RestMemberInfo restMemberInfo = membershipCard.getRestMemberInfo();
           restMemberInfo.getName();
           membershipCardOperation.setRestMemberInfo(restMemberInfo);
           membershipCardOperation.setMembershipCard(membershipCard);
           membershipCardOperation.setCardOperationType(CardOperationTypeEnum.CANCEL_ORDER_REFUND.getCode());
           membershipCardOperation.setAddIntegral(BigDecimal.ZERO);
           membershipCardOperation.setTotalIntegral(membershipCard.getMemberIntegral());
           membershipCardOperation.setPaidinCash(membercardCost);
           membershipCardOperation.setRechargeCash(membercardCost);
           membershipCardOperation.setIsDrawBill(TrueFalseEnum.FALSE.getCode());
           membershipCardOperation.setBalance(membershipCard.getBalance());
           String billId = tableOrder.getBillId();
           if (!StringUtils.isEmpty(billId))
           {
             DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
             membershipCardOperation.setBillNo(dinerBill.getBillNo());
             membershipCardOperation.setDinerBill(dinerBill);
           }
           this.cardOperationService.save(membershipCardOperation);
 
           map.put(membershipCardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), membershipCardOperation);
         }
       }
 
     }
 
     StringBuilder message = new StringBuilder();
 
     if ((BillFromEnum.NET_ORDER.getCode().equals(tableOrder.getBillFrom())) || (BillFromEnum.WX_ORDER.getCode().equals(tableOrder.getBillFrom()))) {
       String onlinePaymentType = tableOrder.getOnlinePaymentType();
       String orderNo = tableOrder.getOnlineOrderNo();
       message.append(tableOrder.getOnlineUserMobile());
       message.append("-");
       if (CloudPaymentTypeEnum.MCP.getCode().equals(onlinePaymentType))
         message.append("您的预订单(" + orderNo + ")已成功取消，" + membercardCost + "元退款已退回到会员卡");
       else {
         message.append("您的预订单(" + orderNo + ")已成功取消");
       }
     }
 
     map.put("message_" + OperationTypeEnum.MOBILE_MESSAGE.getCode(), message.toString());
     return membercardCost;
   }
   @Autowired
   public void setDao(TableOrderDao dao) {
     super.setDao(dao);
     this.tableOrderDao = dao;
   }
   
   public List<TableOrder> findOrdertime(String tabNo, String currentUserRestId,Date datebegin,Date dateend) {
		// TODO Auto-generated method stub
		return this.tableOrderDao.findOrdertime(tabNo,currentUserRestId,datebegin,dateend);
	}
 }

