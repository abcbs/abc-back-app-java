 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.bill.BillService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableAreaService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesAvoidfoodService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesTasteService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesUnitService;
import com.ndlan.canyin.frontdesk.service.cygl.SpecialDisheServie;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardOperationService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm.ShiroUser;
import com.ndlan.canyin.frontdesk.service.sygl.CashDiscountService;
import com.ndlan.canyin.frontdesk.service.sygl.CashSettingService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDishesSetService;
import com.ndlan.canyin.frontdesk.service.sygl.SpeOpReasonService;
import com.ndlan.canyin.frontdesk.service.xtgl.BusiLogService;
import com.ndlan.canyin.frontdesk.service.xtgl.DataLogService;
import com.ndlan.canyin.frontdesk.service.ylgl.DishesRawService;
import com.ndlan.canyin.frontdesk.service.ylgl.RawMaterialService;
import com.ndlan.canyin.frontdesk.util.CancelBillAndOrder;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.open.defines.MyException;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Role;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
import com.ndlan.canyin.base.entity.cygl.DishesUnit;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.qtsy.BillCombine;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.DinerBillLog;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishe;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.SelfDish;
import com.ndlan.canyin.base.entity.qtsy.SelfMessage;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.qtsy.Takeout;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.entity.sygl.CashSetting;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.entity.sygl.SpeOpReason;
import com.ndlan.canyin.base.entity.xtgl.BusiLog;
import com.ndlan.canyin.base.entity.xtgl.ResettleLog;
import com.ndlan.canyin.base.entity.ylgl.DishesRaw;
import com.ndlan.canyin.base.entity.ylgl.RawMaterial;
import com.ndlan.canyin.base.repository.mybatis.DinerBillMyDao;
import com.ndlan.canyin.base.repository.qtsy.DinerBillDao;
import com.ndlan.canyin.base.repository.ylgl.RawMaterialDao;
import com.ndlan.canyin.core.common.BillFromEnum;
import com.ndlan.canyin.core.common.BillOpTypeEnum;
import com.ndlan.canyin.core.common.BillSeqTypeEnum;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.BillTypeEnum;
import com.ndlan.canyin.core.common.BussLogTypeEnum;
import com.ndlan.canyin.core.common.CardOperationTypeEnum;
import com.ndlan.canyin.core.common.CloudPaymentTypeEnum;
import com.ndlan.canyin.core.common.DataLogActEnum;
import com.ndlan.canyin.core.common.DataLogTypeEnum;
import com.ndlan.canyin.core.common.DinnerStatusEnum;
import com.ndlan.canyin.core.common.DiscountTypeEnum;
import com.ndlan.canyin.core.common.DishesStatusEnum;
import com.ndlan.canyin.core.common.DishesTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.OrderStatusEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.SysDataTypeEnum;
import com.ndlan.canyin.core.common.TabBillTypeEnum;
import com.ndlan.canyin.core.common.TakeoutStatusEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.BeanUtils;
import com.ndlan.canyin.core.utils.BigDecimalUtil;
import com.ndlan.canyin.core.utils.Collections3;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.vo.TakingDishesVo;
import com.ndlan.canyin.sharelogic.service.printer.BingtaiPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.ZhuantaiPrinterService;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Lazy
 public class DinerBillService extends BaseService<DinerBillDao, DinerBill>
 {
   private DinerBillDao dinerBillDao;
 
   @Autowired
   private TableService tableService;
 
   @Autowired
   private TableBillRelationService tableBillRelationService;
 
   @Autowired
   private DisheService disheService;
 
   @Autowired
   private DinerBillDisheService dinerBillDisheService;
 
   @Autowired
   private BillCombineService billCombineService;
 
   @Autowired
   private SpecialDisheServie specialDisheServie;
 
   @Autowired
   private DishesAvoidfoodService dishesAvoidfoodService;
 
   @Autowired
   private DishesTasteService dishesTasteService;
 
   @Autowired
   private DishesCategoryService dishesCategoryService;
 
   @Autowired
   private DinerBillLogService dinerBillLogService;
 
   @Autowired
   private BusiLogService busiLogService;
 
   @Autowired
   DishesUnitService dishesUnitService;
 
   @Autowired
   private SpeOpReasonService speOpReasonService;
 
   @Autowired
   private BillService billService;
 
   @Autowired
   private CashSettingService cashSettingService;
 
   @Autowired
   private RestMemberInfoService restMemberInfoService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   private MembershipCardOperationService membershipCardOperationService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   DinerBillPaymentService dinerBillPaymentService;
 
   @Autowired
   PaymentTypeService paymentTypeService;
 
   @Autowired
   private BingtaiPrinterService bingtaiPrinterService;
 
   @Autowired
   private ZhuantaiPrinterService zhuantaiPrinterService;
 
   @Autowired
   private DinerBillMyDao dinerBillMyDao;
 
   @Autowired
   private ResettleLogService resettleLogService;
 
   @Autowired
   private DishesSetService dishesSetService;
 
   @Autowired
   private DinerBillDishesSetService dinerBillDishesSetService;
 
   @Autowired
   private DataLogService dataLogService;
 
   @Autowired
   SelfMessageService selfMessageService;
 
   @Autowired
   SelfOrderService selfOrderService;
 
   @Autowired
   SelfDishService selfdishService;
 
   @Autowired
   DishesRawService dishesRawService;
 
   @Autowired
   RawMaterialService rawMaterialService;
 
   @Autowired
   private TableOrderService tableOrderService;
 
   @Autowired
   private OrderBillDishesService orderBillDishesService;
 
   @Autowired
   private OrderBillDishesSetService orderBillDishesSetService;
 
   @Autowired
   private EmployeeService employeeService;
 
   @Autowired
   private TakeoutService takeoutService;
 
   @Autowired
   private CashDiscountService cashDiscountService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private RawMaterialDao rawMaterialDao;
 
   @Autowired
   TableAreaService tableAreaService;
 
   public DinerBill findByRestaurantAndBillNo(Restaurant restaurant, String billNo)
   {
     List bills = this.dinerBillDao.findByRestaurantAndBillNo(restaurant, billNo);
     if ((bills != null) && (bills.size() > 0))
     {
       return (DinerBill)bills.get(0);
     }
     return null;
   }
 
   public DinerBill findByRestaurantAndBillNoAndBillStatus(Restaurant restaurant, String billNo, String billStatus) {
     List bills = this.dinerBillDao.findByRestaurantAndBillNoAndBillStatus(restaurant, billNo, billStatus);
     if ((bills != null) && (bills.size() > 0))
     {
       return (DinerBill)bills.get(0);
     }
     return null;
   }
 
   public BigDecimal getCurrentDayPayableCostTotal(String restId)
   {
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
 
     String isHasAllArea = null;
     Employee employee = (Employee)this.employeeService.loadOne(user.id);
     List<Role> roles = employee.getRoleList();
     for (Role r : roles)
     {
       if (TrueFalseEnum.TRUE.getCode().equals(r.getIsAllTablearea())) {
         isHasAllArea = TrueFalseEnum.TRUE.getCode();
         break;
       }
     }
     return this.dinerBillMyDao.getCurrentDayPayableCostTotal(restId, isHasAllArea, user.id);
   }
   public BigDecimal getCurrentDayRealCostTotal(String restId) {
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
 
     String isHasAllArea = null;
     Employee employee = (Employee)this.employeeService.loadOne(user.id);
     List<Role> roles = employee.getRoleList();
     for (Role r : roles)
     {
       if (TrueFalseEnum.TRUE.getCode().equals(r.getIsAllTablearea())) {
         isHasAllArea = TrueFalseEnum.TRUE.getCode();
         break;
       }
     }
     return this.dinerBillMyDao.getCurrentDayRealCostTotal(restId, isHasAllArea, user.id);
   }
 
   public Page<DinerBill> searchDinerBill(String restId, String billStatus, int pageNumber, int pagzSize, String keywords, String pageType, String billType,String startDate,String endDate)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.DESC, new String[] { "billNo" }));
     billStatus = (billStatus == null) || (billStatus.isEmpty()) ? null : billStatus;
     pageType = (pageType == null) || (pageType.isEmpty()) ? null : pageType;
     billType = (billType == null) || (billType.isEmpty()) ? null : billType;
     keywords = (keywords == null) || (keywords.isEmpty()) ? null : keywords;
 
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
     String isVaild = user.isVaildBill();
 
     String isHasAllArea = null;
     Employee employee = (Employee)this.employeeService.loadOne(user.id);
     List<Role> roles = employee.getRoleList();
     for (Role r : roles)
     {
       if (TrueFalseEnum.TRUE.getCode().equals(r.getIsAllTablearea())) {
         isHasAllArea = TrueFalseEnum.TRUE.getCode();
         break;
       }
     }
 
     List<DinerBill> bills = this.dinerBillMyDao.getAll(restId, billStatus, pageType, billType, keywords, isVaild, pageRequest.getOffset(), pageRequest.getPageSize(), isHasAllArea, user.id,startDate,endDate);
     int totalSize = this.dinerBillMyDao.getAllCount(restId, billStatus, pageType, billType, keywords, isVaild, isHasAllArea, user.id,startDate,endDate);
 
     List newBills = new ArrayList();
     for (DinerBill e : bills)
     {
       String billId = e.getBillId();
       DinerBill DinerBill = (DinerBill)this.dinerBillDao.findOne(billId);
      /* String ss= DinerBill.getBillMoney();
       if(ss!=null){
    	   Double index = Double.valueOf(ss);
    	   newBills.add(index);
       }*/
       
       
       newBills.add(DinerBill);
      
    /*  DinerBill dinerBills = this.dinerBillMyDao.getAllss(billId,restId);
       newBills.add(dinerBills);*/
     }
     Page page = new PageImpl(newBills, pageRequest, totalSize);
 
     return page;
   }
 
   public Page<DinerBill> findByRestaurantAndTableIsNull(Restaurant restaurant, int pageNumber, int pagzSize)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, null);
     return this.dinerBillDao.findByRestaurantAndTableIsNullOrderByCreateTimeDesc(restaurant, pageRequest);
   }
 
   public Page<DinerBill> findByKeywords(final String restId,final  String keywords, int pageNumber, int pagzSize)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.DESC, new String[] { "createTime" }));
     return this.dinerBillDao.findAll(new Specification()
     {
//       public Predicate toPredicate(Root<DinerBill> root, CriteriaQuery<?> cq, CriteriaBuilder cb)
       public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb)
       {
         Predicate predicate = cb.conjunction();
 
         if (StringUtils.isNotEmpty(restId)) {
           Path p_restaurant = root.get("restaurant");
           Restaurant restaurant = new Restaurant();
           restaurant.setRestId(restId);
           predicate = cb.and(cb.equal(p_restaurant, restaurant), predicate);
         }
 
         if (StringUtils.isNotEmpty(keywords)) {
           Join table = root.join("table", JoinType.LEFT);
           Join createEmployee = root.join("createEmployee", JoinType.LEFT);
 
           Path billNo = root.get("billNo");
 
           Path ccdId = table.get("tabNo");
 
           Path empNum = createEmployee.get("empNum");
           predicate = cb.and(cb.or(new Predicate[] { cb.like(billNo, "%" + keywords + "%"), cb.like(ccdId, "%" + keywords + "%"), cb.like(empNum, "%" + keywords + "%") }), predicate);
         }
         return predicate;
       }
     }
     , pageRequest);
   }
   @Autowired
   public void setBaseDao(DinerBillDao _dinerBillDao) {
     super.setDao(_dinerBillDao);
     this.dinerBillDao = _dinerBillDao;
   }
 
   @Transactional(readOnly=false)
   public String saveTableDinerBill(DinerBill dinerBill, String restId, LinkedHashMap<String, Object> map)
   {
     if ("".equals(dinerBill.getSalesmanId()))
     {
       dinerBill.setSalesmanId(null);
     }
     if ("".equals(dinerBill.getWaiterId()))
     {
       dinerBill.setWaiterId(null);
     }
     String oldTabId = dinerBill.getOldTabId();
     Table oldTab = (Table)this.tableService.getOne(oldTabId);
 
     Table newTab = dinerBill.getTable();
 
     if ((oldTabId != null) && (!oldTabId.isEmpty()) && 
       (!oldTab.getTabNo().equals(newTab.getTabNo())))
     {
       newTab = this.tableService.findByTabNoAndRestIdAndIsEnable(newTab.getTabNo(), restId);
       if (DinnerStatusEnum.IDLE.getCode().equals(newTab.getDinnerStatus()))
       {
         oldTab.setDinnerStatus(DinnerStatusEnum.IDLE.getCode());
         this.tableService.save(oldTab);
 
         map.put(oldTab.getTabId() + "_" + OperationTypeEnum.UPDATE.getCode(), oldTab);
 
         newTab.setDinnerStatus(DinnerStatusEnum.USING.getCode());
         this.tableService.save(newTab);
 
         map.put(newTab.getTabId() + "_" + OperationTypeEnum.UPDATE.getCode(), newTab);
       }
       else
       {
         return TrueFalseEnum.FALSE.getCode();
       }
     }
     dinerBill.setTable(newTab);
     dinerBill.setTabNo(newTab.getTabNo());
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     TableBillRelation tableBillRelation = dinerBill.getTableBillRelation();
     tableBillRelation.setTable(newTab);
     tableBillRelation.setPeopleNum(dinerBill.getPeopleNum());
     this.tableBillRelationService.save(tableBillRelation);
 
     map.put(tableBillRelation.getTabBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableBillRelation);
 
     return TrueFalseEnum.TRUE.getCode();
   }
 
   @Transactional(readOnly=false)
   public boolean updateCreateTableDinerBill(String restId, DinerBill dinerBill, String orderId, String tId, LinkedHashMap<String, Object> map)
   {

	     DateProvider dateProvider = DateProvider.DEFAULT;
	 
	     if (dinerBill.getTableBillRelation() != null)
	     {
	       return false;
	     }
	 
	     if ((dinerBill.getTable() == null) || 
	       (dinerBill.getTable().getTabNo() == null) || (dinerBill.getTable().getTabNo().isEmpty()))
	     {
	       dinerBill.setTable(null);
	     }
	     else
	     {
	       Table table = null;
	       if ((dinerBill.getTable() != null) && (StringUtils.isNotEmpty(dinerBill.getTable().getTabId())))
	         table = (Table)this.tableService.loadOne(dinerBill.getTable().getTabId());
	       else if ((dinerBill.getTable() != null) && (StringUtils.isNotEmpty(dinerBill.getTable().getTabNo()))) {
	         table = this.tableService.findByTabNoAndRestIdAndIsEnable(dinerBill.getTable().getTabNo(), restId);
	       }
	 
	       List rs = this.tableBillRelationService.findByTableAndTabBillType(table, TabBillTypeEnum.ORDER.getCode());
	       if ((rs != null) && (rs.size() >= 1))
	       {
	         rs = null;
	         return false;
	       }
	 
	       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
	       if (tableArea.getSeviceChargeNum() != null) {
	         dinerBill.setSeviceChargeNum(tableArea.getSeviceChargeNum());
	       }
	       dinerBill.setTable(table);
	       dinerBill.setTabNo(dinerBill.getTable().getTabNo());
	 
	       if (StringUtils.isEmpty(dinerBill.getBillType()))
	       {
	         dinerBill.setBillType(BillTypeEnum.NORMAL_BILL.getCode());
	       }
	     }
	 
	     if ("".equals(dinerBill.getSalesmanId()))
	     {
	       dinerBill.setSalesmanId(null);
	     }
	     if ("".equals(dinerBill.getWaiterId()))
	     {
	       dinerBill.setWaiterId(null);
	     }
	     String billNo = this.dinerBillSeqService.createNewBillNo(restId, BillSeqTypeEnum.BILL, map);
	     dinerBill.setBillNo(billNo);
	     dinerBill.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
	     dinerBill.setBillTime(dateProvider.getDate());
	     dinerBill.setDiscount(Integer.valueOf(100));
	 
	     dinerBill.setOriCost(BigDecimal.ZERO);
	 
	     dinerBill.setBeforePay(BigDecimal.ZERO);
	 
	     dinerBill.setDiscount(Integer.valueOf(100));
	 
	     dinerBill.setPayableCost(BigDecimal.ZERO);
	 
	     dinerBill.setLosseGain(BigDecimal.ZERO);
	 
	     dinerBill.setRealCost(BigDecimal.ZERO);
	 
	     dinerBill.setMolingModeCost(BigDecimal.ZERO);
	     CashSetting cashSetting = this.cashSettingService.findByRestId(restId);
	     if (cashSetting != null) {
	       dinerBill.setMolingMode(cashSetting.getMolingMode());
	       dinerBill.setIsRound(cashSetting.getIsRound());
	     }
	 
	     dinerBill.setOddChange(BigDecimal.ZERO);
	 
	     dinerBill.setServiceChargeMoney(BigDecimal.ZERO);
	 
	     dinerBill.setConsumeCost(BigDecimal.ZERO);
	 
	     dinerBill.setSaveCost(BigDecimal.ZERO);
	 
	     dinerBill.setRateCost(BigDecimal.ZERO);
	     dinerBill.setPungentLevel(0);
	 
	     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
	     Employee employee = user.employee;
	     if (dinerBill.getCreateEmployee() == null)
	     {
	       dinerBill.setCreateEmployee(employee);
	     }
	     if (dinerBill.getCreateTime() == null)
	     {
	       dinerBill.setCreateTime(dateProvider.getDate());
	     }
	     if ((dinerBill.getWaiterId() == null) || (dinerBill.getWaiterId().isEmpty()))
	     {
	       dinerBill.setWaiterId(employee.getEmpId());
	       dinerBill.setWaiterName(employee.getName());
	     }
	     dinerBill.setUpdateEmployee(employee);
	     dinerBill.setUpdateTime(dateProvider.getDate());
	     dinerBill.setRestaurant(employee.getRestaurant());
	     dinerBill.setDinerBillPayments(new ArrayList());
	 
	     dinerBill.setIsValid(user.isVaildBill());
	     dinerBill.setIsShift(TrueFalseEnum.FALSE.getCode());
	 
	     String op = OperationTypeEnum.UPDATE.getCode();
	     if (StringUtils.isEmpty(dinerBill.getBillId()))
	     {
	       dinerBill.setBillId(null);
	       op = OperationTypeEnum.CREATE.getCode();
	     }
	     DinerBill dd=dinerBillDao.findOne(dinerBill.getBillId());
	     dd.setBillType("1");
	     dd.setTable(dinerBill.getTable());
	     dd.setBillTime(new Date());
	     dd.setSalesmanId(dinerBill.getSalesmanId());
	     dd.setSalesmanName(dinerBill.getSalesmanName());
	     dd.setWaiterId(dinerBill.getWaiterId());
	     dd.setWaiterName(dinerBill.getWaiterName());
	     dd.setBillNo(dinerBill.getTable().getTabNo());
	     dd.setPeopleNum(dinerBill.getPeopleNum());
	     dinerBill.setBillId(dinerBill.getBillId());
	     dinerBill.setBillNo(dinerBill.getBillNo());
	     dinerBillDao.save(dd);
	      
	      
	      
	     map.put(dinerBill.getBillId() + "sctdb_" + op, dinerBill);
	     if ((BillTypeEnum.NORMAL_BILL.getCode().equals(dinerBill.getBillType())) || 
	       (BillTypeEnum.SELFORDER_BILL.getCode().equals(dinerBill.getBillType())))
	     {
	       Table t = (Table)this.tableService.getOne(dinerBill.getTable().getTabId());
	       t.setDinnerStatus(DinnerStatusEnum.USING.getCode());
	       this.tableService.save(t);
	 
	       map.put(t.getTabId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), t);
	 
	       TableBillRelation tableBillRelation = new TableBillRelation();
	       tableBillRelation.setTable(t);
	       tableBillRelation.setDinerBill(dinerBill);
	       tableBillRelation.setPeopleNum(dinerBill.getPeopleNum());
	       tableBillRelation.setBillTime(dateProvider.getDate());
	       tableBillRelation.setBillStatus(dinerBill.getBillStatus());
	 
	       tableBillRelation.setTabBillType(TabBillTypeEnum.ORDER.getCode());
	       this.tableBillRelationService.save(tableBillRelation);
	 
	       map.put(tableBillRelation.getTabBillId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), tableBillRelation);
	     }
	 
	     String logNotes = "开台操作";
	     if (dinerBill.getTable() != null)
	     {
	       logNotes = logNotes + "，桌号:" + dinerBill.getTable().getTabNo();
	     }
	     if (dinerBill.getPeopleNum() != null)
	     {
	       logNotes = logNotes + "，人数:" + dinerBill.getPeopleNum();
	     }
	     if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType()))
	     {
	       logNotes = logNotes + "，外卖单";
	     }
	 
	     if ((orderId != null) && (!orderId.isEmpty()))
	     {
	       TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
	       tableOrder.setOrderStatus(OrderStatusEnum.OVER.getCode());
	       tableOrder.setBillId(dinerBill.getBillId());
	       this.tableOrderService.save(tableOrder);
	 
	       map.put(tableOrder.getOrderId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
	 
	       if (tableOrder.getTableBillRelation() != null)
	       {
	         String id = tableOrder.getTableBillRelation().getTabBillId();
	         this.tableBillRelationService.delete(id);
	 
	         map.put(id + "sctdb_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_table_bill_relation,tab_bill_id," + id);
	       }
	 
	       List<OrderBillDishe> orderBillDishes = tableOrder.getOrderBillDishes();
	       List dinerBillDishes = new ArrayList();
	       DishesUnit dishesUnit;
	       if (orderBillDishes != null) {
	         for (OrderBillDishe o : orderBillDishes) {
	           dishesUnit = o.getDishe().getDishesUnit();
	           DinerBillDishe dinerBillDishe = new DinerBillDishe();
	           dinerBillDishe.setDinerBill(dinerBill);
	           dinerBillDishe.setDishesId(o.getDishe().getDishesId());
	           dinerBillDishe.setDinerBill(dinerBill);
	           dinerBillDishe.setDishesCategory(o.getDishe().getDishesCategory());
	           dinerBillDishe.setBillNo(dinerBill.getBillNo());
	           dinerBillDishe.setDishesName(o.getDishe().getDishesName());
	           dinerBillDishe.setDishesCode(o.getDishe().getDishesCode());
	           dinerBillDishe.setUnitPrice(o.getDishe().getPrice());
	           dinerBillDishe.setUnitNum(o.getUnitNum());
	           dinerBillDishe.setOrderEmp(employee);
	           dinerBillDishe.setDishesStatus(DishesStatusEnum.UNXIADAN.getCode());
	           dinerBillDishe.setUnitId(dishesUnit.getUnitId());
	           dinerBillDishe.setUnitName(dishesUnit.getName());
	           dinerBillDishe.setUnitType(dishesUnit.getUnitType());
	           dinerBillDishe.setDiscount(Integer.valueOf(100));
	           dinerBillDishe.setRealUnitPrice(o.getDishe().getPrice());
	           dinerBillDishe.setIsRulingPrice(o.getDishe().getIsRulingPrice());
	           dinerBillDishe.setPungentLevel(o.getPungentLevel());
	           dinerBillDishe.setAvoidfoodIdArray(o.getAvoidfoodIdArray());
	           dinerBillDishe.setAvoidfoodNameArray(o.getAvoidfoodNameArray());
	           dinerBillDishe.setTasteIdArray(o.getTasteIdArray());
	           dinerBillDishe.setTasteNameArray(o.getTasteNameArray());
	           dinerBillDishe.setNotes(o.getNotes());
	           dinerBillDishe.setOrderTime(DateProvider.DEFAULT.getDate());
	           dinerBillDishe.setIsOnsale(o.getDishe().getIsOnsale());
	           dinerBillDishe.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
	 
	           this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, o.getDishe());
	           this.dinerBillDisheService.save(dinerBillDishe);
	 
	           map.put(dinerBillDishe.getBdId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishe);
	 
	           dinerBillDishes.add(dinerBillDishe);
	         }
	         orderBillDishes = null;
	       }
	 
	       List<OrderBillDishesSet> orderBillDishesSets = tableOrder.getOrderBillDishesSets();
	       if (orderBillDishesSets != null) {
	         for (OrderBillDishesSet orderBillDishesSet : orderBillDishesSets) {
	           DinerBillDishesSet dinerBillDishesSet = new DinerBillDishesSet();
	           DishesSet dishesSet = orderBillDishesSet.getDishesSet();
	           dinerBillDishesSet.setDsId(orderBillDishesSet.getDishesSet().getDsId());
	           dinerBillDishesSet.setDinerBill(dinerBill);
	           dinerBillDishesSet.setBillNo(billNo);
	           dinerBillDishesSet.setRestId(restId);
	           dinerBillDishesSet.setDishesSetCategory(dishesSet.getDishesSetCategory());
	           dinerBillDishesSet.setDsName(orderBillDishesSet.getDsName());
	           dinerBillDishesSet.setDsCode(dishesSet.getDsCode());
	           dinerBillDishesSet.setDsStatus(DishesStatusEnum.UNXIADAN.getCode());
	           dinerBillDishesSet.setDsDishesDesc(orderBillDishesSet.getDsDishesDesc());
	           dinerBillDishesSet.setUnitPrice(orderBillDishesSet.getUnitPrice());
	           dinerBillDishesSet.setRealUnitPrice(orderBillDishesSet.getRealUnitPrice());
	           dinerBillDishesSet.setUnitNum(orderBillDishesSet.getUnitNum());
	           dinerBillDishesSet.setUnitId(orderBillDishesSet.getUnitId());
	           dinerBillDishesSet.setUnitName(orderBillDishesSet.getUnitName());
	           dinerBillDishesSet.setDiscount(Integer.valueOf(100));
	           dinerBillDishesSet.setOriCost(orderBillDishesSet.getOriCost());
	           dinerBillDishesSet.setRealCost(orderBillDishesSet.getRealCost());
	           dinerBillDishesSet.setAvoidfoodIdArray(orderBillDishesSet.getAvoidfoodIdArray());
	           dinerBillDishesSet.setAvoidfoodNameArray(orderBillDishesSet.getAvoidfoodNameArray());
	           dinerBillDishesSet.setTasteIdArray(orderBillDishesSet.getTasteIdArray());
	           dinerBillDishesSet.setTasteNameArray(orderBillDishesSet.getTasteNameArray());
	           dinerBillDishesSet.setPungentLevel(orderBillDishesSet.getPungentLevel());
	           dinerBillDishesSet.setNotes(orderBillDishesSet.getNotes());
	           dinerBillDishesSet.setIsRulingPrice(orderBillDishesSet.getIsRulingPrice());
	           dinerBillDishesSet.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
	           dinerBillDishesSet.setOrderEmp(employee);
	           dinerBillDishesSet.setOrderTime(DateProvider.DEFAULT.getDate());
	 
	           this.billService.calculatorSingleDishesSet(restId, dinerBill, dinerBillDishesSet, dishesSet);
	           this.dinerBillDishesSetService.save(dinerBillDishesSet);
	 
	           map.put(dinerBillDishesSet.getBdsId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishesSet);
	         }
	 
	         orderBillDishesSets = null;
	       }
	 
	       if (tableOrder.getMbId() != null) {
	         RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(tableOrder.getMbId());
	         if (StringUtils.isNotEmpty(tableOrder.getMcId()))
	           dinerBill = this.dinerBillPaymentService.useMembershipCardForPay(dinerBill.getBillId(), tableOrder.getMcId());
	         else
	           dinerBill.setRestMemberInfo(restMemberInfo);
	       }
	       DinerBillPayment dinerBillPayment;
	       if ((tableOrder.getPrepay() != null) && (tableOrder.getPrepay().compareTo(BigDecimal.ZERO) > 0))
	       {
	         String onlineOrderId = tableOrder.getOnlineOrderId();
	         String onlinePaymentType = tableOrder.getOnlinePaymentType();
	         PaymentType paymentType = null;
	         if ((StringUtils.isNotEmpty(onlineOrderId)) && (CloudPaymentTypeEnum.MCP.getCode().equals(onlinePaymentType)))
	           paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.WEB_PAY.getCode());
	         else {
	           paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.PREPAY.getCode());
	         }
	 
	         dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, dinerBill.getRestaurant().getRestId(), paymentType);
	         op = OperationTypeEnum.UPDATE.getCode();
	         if (dinerBillPayment == null)
	         {
	           dinerBillPayment = new DinerBillPayment();
	           op = OperationTypeEnum.CREATE.getCode();
	         }
	         dinerBillPayment.setDinerBill(dinerBill);
	         dinerBillPayment.setBillNo(dinerBill.getBillNo());
	         dinerBillPayment.setMoney(tableOrder.getPrepay());
	         dinerBillPayment.setPaymentType(paymentType);
	         dinerBillPayment.setPayTime(DateProvider.DEFAULT.getDate());
	         dinerBillPayment.setRestId(dinerBill.getRestaurant().getRestId());
	         this.dinerBillPaymentService.save(dinerBillPayment);
	 
	         map.put(dinerBillPayment.getDbpId() + "sctdb_" + op, dinerBillPayment);
	       }
	 
	       String ccdId = tableOrder.getCcdId();
	       if (StringUtils.isNotEmpty(ccdId)) {
	         CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(ccdId);
	         dinerBill.setCashDiscount(cashDiscount);
	         dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
	         dinerBill.setDiscountName(cashDiscount.getDiscountName());
	       }
	 
	       String onlineOrderId = tableOrder.getOnlineOrderId();
	       if (StringUtils.isNotEmpty(onlineOrderId))
	       {
	         map.put(tableOrder.getOnlineOrderId() + "_" + OperationTypeEnum.SQL.getCode(), 
	           "update cl_user_order set order_status = '" + OrderStatusEnum.EATING.getCode() + 
	           "' where uo_id = '" + tableOrder.getOnlineOrderId() + "' and order_status not in ('" + OrderStatusEnum.OVER.getCode() + "','" + OrderStatusEnum.CANCEL.getCode() + "','" + OrderStatusEnum.EXPIRE.getCode() + "','" + OrderStatusEnum.EATING.getCode() + "')");
	 
	         map.put(tableOrder.getOnlineUobId() + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_order_bill set order_bill_status = '" + OrderStatusEnum.EATING.getCode() + "' where uo_id = '" + tableOrder.getOnlineOrderId() + "' and uob_id = '" + tableOrder.getOnlineUobId() + "' ");
	       }
	 
	       dinerBill.setDinerBillDishes(dinerBillDishes);
	       this.billService.calculator(restId, dinerBill);
	       dinerBill.setTasteIdArray(tableOrder.getTasteIdArray());
	       dinerBill.setTasteNameArray(tableOrder.getTasteNameArray());
	       dinerBill.setAvoidfoodIdArray(tableOrder.getAvoidfoodIdArray());
	       dinerBill.setAvoidfoodNameArray(tableOrder.getAvoidfoodNameArray());
	       dinerBill.setPungentLevel(tableOrder.getPungentLevel());
	       dinerBill.setNotes(tableOrder.getNotes());
	       dinerBill.setPeopleNum(tableOrder.getPeopleNum());
	       if (tableOrder.getWaiter() != null)
	       {
	         dinerBill.setWaiterId(tableOrder.getWaiter().getEmpId());
	         dinerBill.setWaiterName(tableOrder.getWaiter().getName());
	       }
	       if (tableOrder.getSalesMg() != null)
	       {
	         dinerBill.setSalesmanId(tableOrder.getSalesMg().getEmpId());
	         dinerBill.setSalesmanName(tableOrder.getSalesMg().getName());
	       }
	 
	       dinerBill.setIsValid(user.isVaildBill());
	 
	       dinerBill.setOnlineBillId(tableOrder.getOnlineOrderId());
	 
	       dinerBill.setOnlineBillNo(tableOrder.getOnlineOrderNo());
	 
	       dinerBill.setIsMoling(tableOrder.getIsMoling());
	 
	       dinerBill.setMolingModeCost(tableOrder.getMolingModeCost());
	 
	       dinerBill.setConsumeCost(tableOrder.getOriCost());
	 
	       dinerBill.setBillFrom(tableOrder.getBillFrom());
	       this.self.save(dinerBill);
	 
	       map.put(dinerBill.getBillId() + "sctdb2_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
	       try
	       {
	         for (DinerBillDishe o : dinerBill.getDinerBillDishes())
	         {
	           Dishe d = (Dishe)this.disheService.getOne(o.getDishesId());
	           stockUpdateForDishes(restId, null, d, o.getUnitNum(), true, map);
	         }
	 
	         for (OrderBillDishesSet orderBillDishesSet : orderBillDishesSets)
	           stockUpdateForSet(restId, orderBillDishesSet.getDishesSet().getDsId(), orderBillDishesSet.getDsDishesDesc(), null, Float.valueOf(1.0F), map);
	       }
	       catch (Exception e) {
	         e.printStackTrace();
	       }
	 
	     }
	 
	     if ((BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) && (StringUtils.isNotEmpty(tId))) {
	       Takeout takeout = (Takeout)this.takeoutService.getOne(tId);
	       takeout.setDinerBill(dinerBill);
	       takeout.setBillNo(dinerBill.getBillNo());
	       this.takeoutService.save(takeout);
	 
	       map.put(takeout.gettId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), takeout);
	 
	       dinerBill.setDeliverCost(takeout.getDeliverCost());
	 
	       if (StringUtils.isNotEmpty(takeout.getMcId())) {
	         MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(takeout.getMcId());
	         RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(membershipCard.getRestMemberInfo().getMbId());
	         dinerBill.setMembershipCard(membershipCard);
	         dinerBill.setRestMemberInfo(restMemberInfo);
	       }
	 
	       if (StringUtils.isNotEmpty(takeout.getCcdId())) {
	         CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(takeout.getCcdId());
	         dinerBill.setCashDiscount(cashDiscount);
	         dinerBill.setDiscountName(cashDiscount.getDiscountName());
	         dinerBill.setDiscount(Integer.valueOf(0));
	         dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
	         dinerBill.setOtherDiscount("");
	       }
	       this.self.save(dinerBill);
	 
	       map.put(dinerBill.getBillId() + "sctdb3_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
	     }
	 
	     saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.START_TABLE, logNotes, map);
	     return true;
	   
	   
   }
   
   @Transactional(readOnly=false)
   public boolean saveCreateTableDinerBill(String restId, DinerBill dinerBill, String orderId, String tId, LinkedHashMap<String, Object> map)
   {
     DateProvider dateProvider = DateProvider.DEFAULT;
 
     if (dinerBill.getTableBillRelation() != null)
     {
       return false;
     }
 
     if ((dinerBill.getTable() == null) || 
       (dinerBill.getTable().getTabNo() == null) || (dinerBill.getTable().getTabNo().isEmpty()))
     {
       dinerBill.setTable(null);
     }
     else
     {
       Table table = null;
       if ((dinerBill.getTable() != null) && (StringUtils.isNotEmpty(dinerBill.getTable().getTabId())))
         table = (Table)this.tableService.loadOne(dinerBill.getTable().getTabId());
       else if ((dinerBill.getTable() != null) && (StringUtils.isNotEmpty(dinerBill.getTable().getTabNo()))) {
         table = this.tableService.findByTabNoAndRestIdAndIsEnable(dinerBill.getTable().getTabNo(), restId);
       }
 
       List rs = this.tableBillRelationService.findByTableAndTabBillType(table, TabBillTypeEnum.ORDER.getCode());
       if ((rs != null) && (rs.size() >= 1))
       {
         rs = null;
         return false;
       }
 
       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
       if (tableArea.getSeviceChargeNum() != null) {
         dinerBill.setSeviceChargeNum(tableArea.getSeviceChargeNum());
       }
       dinerBill.setTable(table);
       dinerBill.setTabNo(dinerBill.getTable().getTabNo());
 
       if (StringUtils.isEmpty(dinerBill.getBillType()))
       {
         dinerBill.setBillType(BillTypeEnum.NORMAL_BILL.getCode());
       }
     }
 
     if ("".equals(dinerBill.getSalesmanId()))
     {
       dinerBill.setSalesmanId(null);
     }
     if ("".equals(dinerBill.getWaiterId()))
     {
       dinerBill.setWaiterId(null);
     }
     String billNo = this.dinerBillSeqService.createNewBillNo(restId, BillSeqTypeEnum.BILL, map);
     dinerBill.setBillNo(billNo);
     dinerBill.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
     dinerBill.setBillTime(dateProvider.getDate());
     dinerBill.setDiscount(Integer.valueOf(100));
 
     dinerBill.setOriCost(BigDecimal.ZERO);
 
     dinerBill.setBeforePay(BigDecimal.ZERO);
 
     dinerBill.setDiscount(Integer.valueOf(100));
 
     dinerBill.setPayableCost(BigDecimal.ZERO);
 
     dinerBill.setLosseGain(BigDecimal.ZERO);
 
     dinerBill.setRealCost(BigDecimal.ZERO);
 
     dinerBill.setMolingModeCost(BigDecimal.ZERO);
     CashSetting cashSetting = this.cashSettingService.findByRestId(restId);
     if (cashSetting != null) {
       dinerBill.setMolingMode(cashSetting.getMolingMode());
       dinerBill.setIsRound(cashSetting.getIsRound());
     }
 
     dinerBill.setOddChange(BigDecimal.ZERO);
 
     dinerBill.setServiceChargeMoney(BigDecimal.ZERO);
 
     dinerBill.setConsumeCost(BigDecimal.ZERO);
 
     dinerBill.setSaveCost(BigDecimal.ZERO);
 
     dinerBill.setRateCost(BigDecimal.ZERO);
     dinerBill.setPungentLevel(0);
 
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
     Employee employee = user.employee;
     if (dinerBill.getCreateEmployee() == null)
     {
       dinerBill.setCreateEmployee(employee);
     }
     if (dinerBill.getCreateTime() == null)
     {
       dinerBill.setCreateTime(dateProvider.getDate());
     }
     if ((dinerBill.getWaiterId() == null) || (dinerBill.getWaiterId().isEmpty()))
     {
       dinerBill.setWaiterId(employee.getEmpId());
       dinerBill.setWaiterName(employee.getName());
     }
     dinerBill.setUpdateEmployee(employee);
     dinerBill.setUpdateTime(dateProvider.getDate());
     dinerBill.setRestaurant(employee.getRestaurant());
     dinerBill.setDinerBillPayments(new ArrayList());
 
     dinerBill.setIsValid(user.isVaildBill());
     dinerBill.setIsShift(TrueFalseEnum.FALSE.getCode());
 
     String op = OperationTypeEnum.UPDATE.getCode();
     if (StringUtils.isEmpty(dinerBill.getBillId()))
     {
       dinerBill.setBillId(null);
       op = OperationTypeEnum.CREATE.getCode();
     }
      this.self.save(dinerBill);
     map.put(dinerBill.getBillId() + "sctdb_" + op, dinerBill);
// 如果为普通账单或者自助点餐账单
     if ((BillTypeEnum.NORMAL_BILL.getCode().equals(dinerBill.getBillType())) || 
       (BillTypeEnum.SELFORDER_BILL.getCode().equals(dinerBill.getBillType())))
     {
       Table t = (Table)this.tableService.getOne(dinerBill.getTable().getTabId());
       t.setDinnerStatus(DinnerStatusEnum.USING.getCode());
       this.tableService.save(t);
 
       map.put(t.getTabId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), t);
 
       TableBillRelation tableBillRelation = new TableBillRelation();
       tableBillRelation.setTable(t);
       tableBillRelation.setDinerBill(dinerBill);
       tableBillRelation.setPeopleNum(dinerBill.getPeopleNum());
       tableBillRelation.setBillTime(dateProvider.getDate());
       tableBillRelation.setBillStatus(dinerBill.getBillStatus());
 
       tableBillRelation.setTabBillType(TabBillTypeEnum.ORDER.getCode());
       this.tableBillRelationService.save(tableBillRelation);
 
       map.put(tableBillRelation.getTabBillId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), tableBillRelation);
     }
 
     String logNotes = "开台操作";
     if (dinerBill.getTable() != null)
     {
       logNotes = logNotes + "，桌号:" + dinerBill.getTable().getTabNo();
     }
     if (dinerBill.getPeopleNum() != null)
     {
       logNotes = logNotes + "，人数:" + dinerBill.getPeopleNum();
     }
     if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType()))
     {
       logNotes = logNotes + "，外卖单";
     }
 
     if ((orderId != null) && (!orderId.isEmpty()))
     {
       TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
       tableOrder.setOrderStatus(OrderStatusEnum.OVER.getCode());
       tableOrder.setBillId(dinerBill.getBillId());
       tableOrder.setIsStatus("0");  //0 是shi  1是fou  预定  是否就餐
       this.tableOrderService.save(tableOrder);
 
       map.put(tableOrder.getOrderId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
 
       if (tableOrder.getTableBillRelation() != null)
       {
         String id = tableOrder.getTableBillRelation().getTabBillId();
         this.tableBillRelationService.delete(id);
 
         map.put(id + "sctdb_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_table_bill_relation,tab_bill_id," + id);
       }
 
       List<OrderBillDishe> orderBillDishes = tableOrder.getOrderBillDishes();
       List dinerBillDishes = new ArrayList();
       DishesUnit dishesUnit;
       if (orderBillDishes != null) {
         for (OrderBillDishe o : orderBillDishes) {
           dishesUnit = o.getDishe().getDishesUnit();
           DinerBillDishe dinerBillDishe = new DinerBillDishe();
           dinerBillDishe.setDinerBill(dinerBill);
           dinerBillDishe.setDishesId(o.getDishe().getDishesId());
           dinerBillDishe.setDinerBill(dinerBill);
           dinerBillDishe.setDishesCategory(o.getDishe().getDishesCategory());
           dinerBillDishe.setBillNo(dinerBill.getBillNo());
           dinerBillDishe.setDishesName(o.getDishe().getDishesName());
           dinerBillDishe.setDishesCode(o.getDishe().getDishesCode());
           dinerBillDishe.setUnitPrice(o.getDishe().getPrice());
           dinerBillDishe.setUnitNum(o.getUnitNum());
           dinerBillDishe.setOrderEmp(employee);
           dinerBillDishe.setDishesStatus(DishesStatusEnum.UNXIADAN.getCode());
           dinerBillDishe.setUnitId(dishesUnit.getUnitId());
           dinerBillDishe.setUnitName(dishesUnit.getName());
           dinerBillDishe.setUnitType(dishesUnit.getUnitType());
           dinerBillDishe.setDiscount(Integer.valueOf(100));
           dinerBillDishe.setRealUnitPrice(o.getDishe().getPrice());
           dinerBillDishe.setIsRulingPrice(o.getDishe().getIsRulingPrice());
           dinerBillDishe.setPungentLevel(o.getPungentLevel());
           dinerBillDishe.setAvoidfoodIdArray(o.getAvoidfoodIdArray());
           dinerBillDishe.setAvoidfoodNameArray(o.getAvoidfoodNameArray());
           dinerBillDishe.setTasteIdArray(o.getTasteIdArray());
           dinerBillDishe.setTasteNameArray(o.getTasteNameArray());
           dinerBillDishe.setNotes(o.getNotes());
           dinerBillDishe.setOrderTime(DateProvider.DEFAULT.getDate());
           dinerBillDishe.setIsOnsale(o.getDishe().getIsOnsale());
           dinerBillDishe.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
 
           this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, o.getDishe());
           this.dinerBillDisheService.save(dinerBillDishe);
 
           map.put(dinerBillDishe.getBdId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishe);
 
           dinerBillDishes.add(dinerBillDishe);
         }
         orderBillDishes = null;
       }
 
       List<OrderBillDishesSet> orderBillDishesSets = tableOrder.getOrderBillDishesSets();
       if (orderBillDishesSets != null) {
         for (OrderBillDishesSet orderBillDishesSet : orderBillDishesSets) {
           DinerBillDishesSet dinerBillDishesSet = new DinerBillDishesSet();
           DishesSet dishesSet = orderBillDishesSet.getDishesSet();
           dinerBillDishesSet.setDsId(orderBillDishesSet.getDishesSet().getDsId());
           dinerBillDishesSet.setDinerBill(dinerBill);
           dinerBillDishesSet.setBillNo(billNo);
           dinerBillDishesSet.setRestId(restId);
           dinerBillDishesSet.setDishesSetCategory(dishesSet.getDishesSetCategory());
           dinerBillDishesSet.setDsName(orderBillDishesSet.getDsName());
           dinerBillDishesSet.setDsCode(dishesSet.getDsCode());
           dinerBillDishesSet.setDsStatus(DishesStatusEnum.UNXIADAN.getCode());
           dinerBillDishesSet.setDsDishesDesc(orderBillDishesSet.getDsDishesDesc());
           dinerBillDishesSet.setUnitPrice(orderBillDishesSet.getUnitPrice());
           dinerBillDishesSet.setRealUnitPrice(orderBillDishesSet.getRealUnitPrice());
           dinerBillDishesSet.setUnitNum(orderBillDishesSet.getUnitNum());
           dinerBillDishesSet.setUnitId(orderBillDishesSet.getUnitId());
           dinerBillDishesSet.setUnitName(orderBillDishesSet.getUnitName());
           dinerBillDishesSet.setDiscount(Integer.valueOf(100));
           dinerBillDishesSet.setOriCost(orderBillDishesSet.getOriCost());
           dinerBillDishesSet.setRealCost(orderBillDishesSet.getRealCost());
           dinerBillDishesSet.setAvoidfoodIdArray(orderBillDishesSet.getAvoidfoodIdArray());
           dinerBillDishesSet.setAvoidfoodNameArray(orderBillDishesSet.getAvoidfoodNameArray());
           dinerBillDishesSet.setTasteIdArray(orderBillDishesSet.getTasteIdArray());
           dinerBillDishesSet.setTasteNameArray(orderBillDishesSet.getTasteNameArray());
           dinerBillDishesSet.setPungentLevel(orderBillDishesSet.getPungentLevel());
           dinerBillDishesSet.setNotes(orderBillDishesSet.getNotes());
           dinerBillDishesSet.setIsRulingPrice(orderBillDishesSet.getIsRulingPrice());
           dinerBillDishesSet.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
           dinerBillDishesSet.setOrderEmp(employee);
           dinerBillDishesSet.setOrderTime(DateProvider.DEFAULT.getDate());
 
           this.billService.calculatorSingleDishesSet(restId, dinerBill, dinerBillDishesSet, dishesSet);
           this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
           map.put(dinerBillDishesSet.getBdsId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishesSet);
         }
 
         orderBillDishesSets = null;
       }
 
       if (tableOrder.getMbId() != null) {
         RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(tableOrder.getMbId());
         if (StringUtils.isNotEmpty(tableOrder.getMcId()))
           dinerBill = this.dinerBillPaymentService.useMembershipCardForPay(dinerBill.getBillId(), tableOrder.getMcId());
         else
           dinerBill.setRestMemberInfo(restMemberInfo);
       }
       DinerBillPayment dinerBillPayment;
       if ((tableOrder.getPrepay() != null) && (tableOrder.getPrepay().compareTo(BigDecimal.ZERO) > 0))
       {
         String onlineOrderId = tableOrder.getOnlineOrderId();
         String onlinePaymentType = tableOrder.getOnlinePaymentType();
         PaymentType paymentType = null;
         if ((StringUtils.isNotEmpty(onlineOrderId)) && (CloudPaymentTypeEnum.MCP.getCode().equals(onlinePaymentType)))
           paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.WEB_PAY.getCode());
         else {
           paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.PREPAY.getCode());
         }
 
         dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, dinerBill.getRestaurant().getRestId(), paymentType);
         op = OperationTypeEnum.UPDATE.getCode();
         if (dinerBillPayment == null)
         {
           dinerBillPayment = new DinerBillPayment();
           op = OperationTypeEnum.CREATE.getCode();
         }
         dinerBillPayment.setDinerBill(dinerBill);
         dinerBillPayment.setBillNo(dinerBill.getBillNo());
         dinerBillPayment.setMoney(tableOrder.getPrepay());
         dinerBillPayment.setPaymentType(paymentType);
         dinerBillPayment.setPayTime(DateProvider.DEFAULT.getDate());
         dinerBillPayment.setRestId(dinerBill.getRestaurant().getRestId());
         this.dinerBillPaymentService.save(dinerBillPayment);
 
         map.put(dinerBillPayment.getDbpId() + "sctdb_" + op, dinerBillPayment);
       }
 
       String ccdId = tableOrder.getCcdId();
       if (StringUtils.isNotEmpty(ccdId)) {
         CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(ccdId);
         dinerBill.setCashDiscount(cashDiscount);
         dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
         dinerBill.setDiscountName(cashDiscount.getDiscountName());
       }
 
       String onlineOrderId = tableOrder.getOnlineOrderId();
       if (StringUtils.isNotEmpty(onlineOrderId))
       {
         map.put(tableOrder.getOnlineOrderId() + "_" + OperationTypeEnum.SQL.getCode(), 
           "update cl_user_order set order_status = '" + OrderStatusEnum.EATING.getCode() + 
           "' where uo_id = '" + tableOrder.getOnlineOrderId() + "' and order_status not in ('" + OrderStatusEnum.OVER.getCode() + "','" + OrderStatusEnum.CANCEL.getCode() + "','" + OrderStatusEnum.EXPIRE.getCode() + "','" + OrderStatusEnum.EATING.getCode() + "')");
 
         map.put(tableOrder.getOnlineUobId() + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_order_bill set order_bill_status = '" + OrderStatusEnum.EATING.getCode() + "' where uo_id = '" + tableOrder.getOnlineOrderId() + "' and uob_id = '" + tableOrder.getOnlineUobId() + "' ");
       }
 
       dinerBill.setDinerBillDishes(dinerBillDishes);
       this.billService.calculator(restId, dinerBill);
       dinerBill.setTasteIdArray(tableOrder.getTasteIdArray());
       dinerBill.setTasteNameArray(tableOrder.getTasteNameArray());
       dinerBill.setAvoidfoodIdArray(tableOrder.getAvoidfoodIdArray());
       dinerBill.setAvoidfoodNameArray(tableOrder.getAvoidfoodNameArray());
       dinerBill.setPungentLevel(tableOrder.getPungentLevel());
       dinerBill.setNotes(tableOrder.getNotes());
       dinerBill.setPeopleNum(tableOrder.getPeopleNum());
       if (tableOrder.getWaiter() != null)
       {
         dinerBill.setWaiterId(tableOrder.getWaiter().getEmpId());
         dinerBill.setWaiterName(tableOrder.getWaiter().getName());
       }
       if (tableOrder.getSalesMg() != null)
       {
         dinerBill.setSalesmanId(tableOrder.getSalesMg().getEmpId());
         dinerBill.setSalesmanName(tableOrder.getSalesMg().getName());
       }
 
       dinerBill.setIsValid(user.isVaildBill());
 
       dinerBill.setOnlineBillId(tableOrder.getOnlineOrderId());
 
       dinerBill.setOnlineBillNo(tableOrder.getOnlineOrderNo());
 
       dinerBill.setIsMoling(tableOrder.getIsMoling());
 
       dinerBill.setMolingModeCost(tableOrder.getMolingModeCost());
 
       dinerBill.setConsumeCost(tableOrder.getOriCost());
 
       dinerBill.setBillFrom(tableOrder.getBillFrom());
       this.self.save(dinerBill);
 
       map.put(dinerBill.getBillId() + "sctdb2_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
       try
       {
         for (DinerBillDishe o : dinerBill.getDinerBillDishes())
         {
           Dishe d = (Dishe)this.disheService.getOne(o.getDishesId());
           stockUpdateForDishes(restId, null, d, o.getUnitNum(), true, map);
         }
         if(orderBillDishesSets !=null){
	         for (OrderBillDishesSet orderBillDishesSet : orderBillDishesSets)
	           stockUpdateForSet(restId, orderBillDishesSet.getDishesSet().getDsId(), orderBillDishesSet.getDsDishesDesc(), null, Float.valueOf(1.0F), map);
	         }
         }
       catch (Exception e) {
         e.printStackTrace();
       }
 
     }
 
     if ((BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) && (StringUtils.isNotEmpty(tId))) {
       Takeout takeout = (Takeout)this.takeoutService.getOne(tId);
       takeout.setDinerBill(dinerBill);
       takeout.setBillNo(dinerBill.getBillNo());
       this.takeoutService.save(takeout);
 
       map.put(takeout.gettId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), takeout);
 
       dinerBill.setDeliverCost(takeout.getDeliverCost());
 
       if (StringUtils.isNotEmpty(takeout.getMcId())) {
         MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(takeout.getMcId());
         RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(membershipCard.getRestMemberInfo().getMbId());
         dinerBill.setMembershipCard(membershipCard);
         dinerBill.setRestMemberInfo(restMemberInfo);
       }
 
       if (StringUtils.isNotEmpty(takeout.getCcdId())) {
         CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(takeout.getCcdId());
         dinerBill.setCashDiscount(cashDiscount);
         dinerBill.setDiscountName(cashDiscount.getDiscountName());
         dinerBill.setDiscount(Integer.valueOf(0));
         dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
         dinerBill.setOtherDiscount("");
       }
       if(takeout.getContactName().equals("外带")){
    	   dinerBill.setTabNo("外带");
       }else{
    	   dinerBill.setTabNo("外卖");
       }
       this.self.save(dinerBill);
 
       map.put(dinerBill.getBillId() + "sctdb3_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
     }
 
     saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.START_TABLE, logNotes, map);
     return true;
   }
   
   //快餐
   @Transactional(readOnly=false)
   public boolean saveCreateTableDinerBills(String restId, DinerBill dinerBill, String orderId, String tId, LinkedHashMap<String, Object> map)
   {
     DateProvider dateProvider = DateProvider.DEFAULT;
 
     if (dinerBill.getTableBillRelation() != null)
     {
       return false;
     }
 
     if ((dinerBill.getTable() == null) || 
       (dinerBill.getTable().getTabNo() == null) || (dinerBill.getTable().getTabNo().isEmpty()))
     {
       dinerBill.setTable(null);
     }
     else
     {
       Table table = null;
       if ((dinerBill.getTable() != null) && (StringUtils.isNotEmpty(dinerBill.getTable().getTabId())))
         table = (Table)this.tableService.loadOne(dinerBill.getTable().getTabId());
       else if ((dinerBill.getTable() != null) && (StringUtils.isNotEmpty(dinerBill.getTable().getTabNo()))) {
         table = this.tableService.findByTabNoAndRestIdAndIsEnable(dinerBill.getTable().getTabNo(), restId);
       }
 
       List rs = this.tableBillRelationService.findByTableAndTabBillType(table, TabBillTypeEnum.ORDER.getCode());
       if ((rs != null) && (rs.size() >= 1))
       {
         rs = null;
         return false;
       }
 
       TableArea tableArea = this.tableAreaService.loadTableAreaByTableId(table.getTabId());
       if (tableArea.getSeviceChargeNum() != null) {
         dinerBill.setSeviceChargeNum(tableArea.getSeviceChargeNum());
       }
       dinerBill.setTable(table);
       dinerBill.setTabNo(dinerBill.getTable().getTabNo());
 
       if (StringUtils.isEmpty(dinerBill.getBillType()))
       {
         dinerBill.setBillType(BillTypeEnum.NORMAL_BILL.getCode());
       }
     }
 
     if ("".equals(dinerBill.getSalesmanId()))
     {
       dinerBill.setSalesmanId(null);
     }
     if ("".equals(dinerBill.getWaiterId()))
     {
       dinerBill.setWaiterId(null);
     }
     String billNo = this.dinerBillSeqService.createNewBillNo(restId, BillSeqTypeEnum.BILL, map);
     dinerBill.setBillNo(billNo);
     dinerBill.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
     dinerBill.setBillTime(dateProvider.getDate());
     dinerBill.setDiscount(Integer.valueOf(100));
 
     dinerBill.setOriCost(BigDecimal.ZERO);
 
     dinerBill.setBeforePay(BigDecimal.ZERO);
 
     dinerBill.setDiscount(Integer.valueOf(100));
 
     dinerBill.setPayableCost(BigDecimal.ZERO);
 
     dinerBill.setLosseGain(BigDecimal.ZERO);
 
     dinerBill.setRealCost(BigDecimal.ZERO);
 
     dinerBill.setMolingModeCost(BigDecimal.ZERO);
     CashSetting cashSetting = this.cashSettingService.findByRestId(restId);
     if (cashSetting != null) {
       dinerBill.setMolingMode(cashSetting.getMolingMode());
       dinerBill.setIsRound(cashSetting.getIsRound());
     }
 
     dinerBill.setOddChange(BigDecimal.ZERO);
 
     dinerBill.setServiceChargeMoney(BigDecimal.ZERO);
 
     dinerBill.setConsumeCost(BigDecimal.ZERO);
 
     dinerBill.setSaveCost(BigDecimal.ZERO);
 
     dinerBill.setRateCost(BigDecimal.ZERO);
     dinerBill.setPungentLevel(0);
 
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
     Employee employee = user.employee;
     if (dinerBill.getCreateEmployee() == null)
     {
       dinerBill.setCreateEmployee(employee);
     }
     if (dinerBill.getCreateTime() == null)
     {
       dinerBill.setCreateTime(dateProvider.getDate());
     }
     if ((dinerBill.getWaiterId() == null) || (dinerBill.getWaiterId().isEmpty()))
     {
       dinerBill.setWaiterId(employee.getEmpId());
       dinerBill.setWaiterName(employee.getName());
     }
     dinerBill.setUpdateEmployee(employee);
     dinerBill.setUpdateTime(dateProvider.getDate());
     dinerBill.setRestaurant(employee.getRestaurant());
     dinerBill.setDinerBillPayments(new ArrayList());
 
     dinerBill.setIsValid(user.isVaildBill());
     dinerBill.setIsShift(TrueFalseEnum.FALSE.getCode());
 
     String op = OperationTypeEnum.UPDATE.getCode();
     if (StringUtils.isEmpty(dinerBill.getBillId()))
     {
       dinerBill.setBillId(null);
       op = OperationTypeEnum.CREATE.getCode();
     }
      this.self.save(dinerBill);
     map.put(dinerBill.getBillId() + "sctdb_" + op, dinerBill);
// 如果为普通账单或者自助点餐账单
     if ((BillTypeEnum.NORMAL_BILL.getCode().equals(dinerBill.getBillType())) || 
       (BillTypeEnum.SELFORDER_BILL.getCode().equals(dinerBill.getBillType())))
     {
       Table t = (Table)this.tableService.getOne(dinerBill.getTable().getTabId());
       t.setDinnerStatus(DinnerStatusEnum.USING.getCode());
       this.tableService.save(t);
 
       map.put(t.getTabId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), t);
 
       TableBillRelation tableBillRelation = new TableBillRelation();
       tableBillRelation.setTable(t);
       tableBillRelation.setDinerBill(dinerBill);
       tableBillRelation.setPeopleNum(dinerBill.getPeopleNum());
       tableBillRelation.setBillTime(dateProvider.getDate());
       tableBillRelation.setBillStatus(dinerBill.getBillStatus());
 
       tableBillRelation.setTabBillType(TabBillTypeEnum.ORDER.getCode());
       this.tableBillRelationService.save(tableBillRelation);
 
       map.put(tableBillRelation.getTabBillId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), tableBillRelation);
     }
 
     String logNotes = "开台操作";
     if (dinerBill.getTable() != null)
     {
       logNotes = logNotes + "，桌号:" + dinerBill.getTable().getTabNo();
     }
     if (dinerBill.getPeopleNum() != null)
     {
       logNotes = logNotes + "，人数:" + dinerBill.getPeopleNum();
     }
     if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType()))
     {
       logNotes = logNotes + "，外卖单";
     }
 
     if ((orderId != null) && (!orderId.isEmpty()))
     {
       TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
       tableOrder.setOrderStatus(OrderStatusEnum.OVER.getCode());
       tableOrder.setBillId(dinerBill.getBillId());
       this.tableOrderService.save(tableOrder);
 
       map.put(tableOrder.getOrderId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
 
       if (tableOrder.getTableBillRelation() != null)
       {
         String id = tableOrder.getTableBillRelation().getTabBillId();
         this.tableBillRelationService.delete(id);
 
         map.put(id + "sctdb_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_table_bill_relation,tab_bill_id," + id);
       }
 
       List<OrderBillDishe> orderBillDishes = tableOrder.getOrderBillDishes();
       List dinerBillDishes = new ArrayList();
       DishesUnit dishesUnit;
       if (orderBillDishes != null) {
         for (OrderBillDishe o : orderBillDishes) {
           dishesUnit = o.getDishe().getDishesUnit();
           DinerBillDishe dinerBillDishe = new DinerBillDishe();
           dinerBillDishe.setDinerBill(dinerBill);
           dinerBillDishe.setDishesId(o.getDishe().getDishesId());
           dinerBillDishe.setDinerBill(dinerBill);
           dinerBillDishe.setDishesCategory(o.getDishe().getDishesCategory());
           dinerBillDishe.setBillNo(dinerBill.getBillNo());
           dinerBillDishe.setDishesName(o.getDishe().getDishesName());
           dinerBillDishe.setDishesCode(o.getDishe().getDishesCode());
           dinerBillDishe.setUnitPrice(o.getDishe().getPrice());
           dinerBillDishe.setUnitNum(o.getUnitNum());
           dinerBillDishe.setOrderEmp(employee);
           dinerBillDishe.setDishesStatus(DishesStatusEnum.UNXIADAN.getCode());
           dinerBillDishe.setUnitId(dishesUnit.getUnitId());
           dinerBillDishe.setUnitName(dishesUnit.getName());
           dinerBillDishe.setUnitType(dishesUnit.getUnitType());
           dinerBillDishe.setDiscount(Integer.valueOf(100));
           dinerBillDishe.setRealUnitPrice(o.getDishe().getPrice());
           dinerBillDishe.setIsRulingPrice(o.getDishe().getIsRulingPrice());
           dinerBillDishe.setPungentLevel(o.getPungentLevel());
           dinerBillDishe.setAvoidfoodIdArray(o.getAvoidfoodIdArray());
           dinerBillDishe.setAvoidfoodNameArray(o.getAvoidfoodNameArray());
           dinerBillDishe.setTasteIdArray(o.getTasteIdArray());
           dinerBillDishe.setTasteNameArray(o.getTasteNameArray());
           dinerBillDishe.setNotes(o.getNotes());
           dinerBillDishe.setOrderTime(DateProvider.DEFAULT.getDate());
           dinerBillDishe.setIsOnsale(o.getDishe().getIsOnsale());
           dinerBillDishe.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
 
           this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, o.getDishe());
           this.dinerBillDisheService.save(dinerBillDishe);
 
           map.put(dinerBillDishe.getBdId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishe);
 
           dinerBillDishes.add(dinerBillDishe);
         }
         orderBillDishes = null;
       }
 
       List<OrderBillDishesSet> orderBillDishesSets = tableOrder.getOrderBillDishesSets();
       if (orderBillDishesSets != null) {
         for (OrderBillDishesSet orderBillDishesSet : orderBillDishesSets) {
           DinerBillDishesSet dinerBillDishesSet = new DinerBillDishesSet();
           DishesSet dishesSet = orderBillDishesSet.getDishesSet();
           dinerBillDishesSet.setDsId(orderBillDishesSet.getDishesSet().getDsId());
           dinerBillDishesSet.setDinerBill(dinerBill);
           dinerBillDishesSet.setBillNo(billNo);
           dinerBillDishesSet.setRestId(restId);
           dinerBillDishesSet.setDishesSetCategory(dishesSet.getDishesSetCategory());
           dinerBillDishesSet.setDsName(orderBillDishesSet.getDsName());
           dinerBillDishesSet.setDsCode(dishesSet.getDsCode());
           dinerBillDishesSet.setDsStatus(DishesStatusEnum.UNXIADAN.getCode());
           dinerBillDishesSet.setDsDishesDesc(orderBillDishesSet.getDsDishesDesc());
           dinerBillDishesSet.setUnitPrice(orderBillDishesSet.getUnitPrice());
           dinerBillDishesSet.setRealUnitPrice(orderBillDishesSet.getRealUnitPrice());
           dinerBillDishesSet.setUnitNum(orderBillDishesSet.getUnitNum());
           dinerBillDishesSet.setUnitId(orderBillDishesSet.getUnitId());
           dinerBillDishesSet.setUnitName(orderBillDishesSet.getUnitName());
           dinerBillDishesSet.setDiscount(Integer.valueOf(100));
           dinerBillDishesSet.setOriCost(orderBillDishesSet.getOriCost());
           dinerBillDishesSet.setRealCost(orderBillDishesSet.getRealCost());
           dinerBillDishesSet.setAvoidfoodIdArray(orderBillDishesSet.getAvoidfoodIdArray());
           dinerBillDishesSet.setAvoidfoodNameArray(orderBillDishesSet.getAvoidfoodNameArray());
           dinerBillDishesSet.setTasteIdArray(orderBillDishesSet.getTasteIdArray());
           dinerBillDishesSet.setTasteNameArray(orderBillDishesSet.getTasteNameArray());
           dinerBillDishesSet.setPungentLevel(orderBillDishesSet.getPungentLevel());
           dinerBillDishesSet.setNotes(orderBillDishesSet.getNotes());
           dinerBillDishesSet.setIsRulingPrice(orderBillDishesSet.getIsRulingPrice());
           dinerBillDishesSet.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
           dinerBillDishesSet.setOrderEmp(employee);
           dinerBillDishesSet.setOrderTime(DateProvider.DEFAULT.getDate());
 
           this.billService.calculatorSingleDishesSet(restId, dinerBill, dinerBillDishesSet, dishesSet);
           this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
           map.put(dinerBillDishesSet.getBdsId() + "sctdb_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishesSet);
         }
 
         orderBillDishesSets = null;
       }
 
       if (tableOrder.getMbId() != null) {
         RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(tableOrder.getMbId());
         if (StringUtils.isNotEmpty(tableOrder.getMcId()))
           dinerBill = this.dinerBillPaymentService.useMembershipCardForPay(dinerBill.getBillId(), tableOrder.getMcId());
         else
           dinerBill.setRestMemberInfo(restMemberInfo);
       }
       DinerBillPayment dinerBillPayment;
       if ((tableOrder.getPrepay() != null) && (tableOrder.getPrepay().compareTo(BigDecimal.ZERO) > 0))
       {
         String onlineOrderId = tableOrder.getOnlineOrderId();
         String onlinePaymentType = tableOrder.getOnlinePaymentType();
         PaymentType paymentType = null;
         if ((StringUtils.isNotEmpty(onlineOrderId)) && (CloudPaymentTypeEnum.MCP.getCode().equals(onlinePaymentType)))
           paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.WEB_PAY.getCode());
         else {
           paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.PREPAY.getCode());
         }
 
         dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, dinerBill.getRestaurant().getRestId(), paymentType);
         op = OperationTypeEnum.UPDATE.getCode();
         if (dinerBillPayment == null)
         {
           dinerBillPayment = new DinerBillPayment();
           op = OperationTypeEnum.CREATE.getCode();
         }
         dinerBillPayment.setDinerBill(dinerBill);
         dinerBillPayment.setBillNo(dinerBill.getBillNo());
         dinerBillPayment.setMoney(tableOrder.getPrepay());
         dinerBillPayment.setPaymentType(paymentType);
         dinerBillPayment.setPayTime(DateProvider.DEFAULT.getDate());
         dinerBillPayment.setRestId(dinerBill.getRestaurant().getRestId());
         this.dinerBillPaymentService.save(dinerBillPayment);
 
         map.put(dinerBillPayment.getDbpId() + "sctdb_" + op, dinerBillPayment);
       }
 
       String ccdId = tableOrder.getCcdId();
       if (StringUtils.isNotEmpty(ccdId)) {
         CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(ccdId);
         dinerBill.setCashDiscount(cashDiscount);
         dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
         dinerBill.setDiscountName(cashDiscount.getDiscountName());
       }
 
       String onlineOrderId = tableOrder.getOnlineOrderId();
       if (StringUtils.isNotEmpty(onlineOrderId))
       {
         map.put(tableOrder.getOnlineOrderId() + "_" + OperationTypeEnum.SQL.getCode(), 
           "update cl_user_order set order_status = '" + OrderStatusEnum.EATING.getCode() + 
           "' where uo_id = '" + tableOrder.getOnlineOrderId() + "' and order_status not in ('" + OrderStatusEnum.OVER.getCode() + "','" + OrderStatusEnum.CANCEL.getCode() + "','" + OrderStatusEnum.EXPIRE.getCode() + "','" + OrderStatusEnum.EATING.getCode() + "')");
 
         map.put(tableOrder.getOnlineUobId() + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_order_bill set order_bill_status = '" + OrderStatusEnum.EATING.getCode() + "' where uo_id = '" + tableOrder.getOnlineOrderId() + "' and uob_id = '" + tableOrder.getOnlineUobId() + "' ");
       }
 
       dinerBill.setDinerBillDishes(dinerBillDishes);
       this.billService.calculator(restId, dinerBill);
       dinerBill.setTasteIdArray(tableOrder.getTasteIdArray());
       dinerBill.setTasteNameArray(tableOrder.getTasteNameArray());
       dinerBill.setAvoidfoodIdArray(tableOrder.getAvoidfoodIdArray());
       dinerBill.setAvoidfoodNameArray(tableOrder.getAvoidfoodNameArray());
       dinerBill.setPungentLevel(tableOrder.getPungentLevel());
       dinerBill.setNotes(tableOrder.getNotes());
       dinerBill.setPeopleNum(tableOrder.getPeopleNum());
       if (tableOrder.getWaiter() != null)
       {
         dinerBill.setWaiterId(tableOrder.getWaiter().getEmpId());
         dinerBill.setWaiterName(tableOrder.getWaiter().getName());
       }
       if (tableOrder.getSalesMg() != null)
       {
         dinerBill.setSalesmanId(tableOrder.getSalesMg().getEmpId());
         dinerBill.setSalesmanName(tableOrder.getSalesMg().getName());
       }
 
       dinerBill.setIsValid(user.isVaildBill());
 
       dinerBill.setOnlineBillId(tableOrder.getOnlineOrderId());
 
       dinerBill.setOnlineBillNo(tableOrder.getOnlineOrderNo());
 
       dinerBill.setIsMoling(tableOrder.getIsMoling());
 
       dinerBill.setMolingModeCost(tableOrder.getMolingModeCost());
 
       dinerBill.setConsumeCost(tableOrder.getOriCost());
 
       dinerBill.setBillFrom(tableOrder.getBillFrom());
       this.self.save(dinerBill);
 
       map.put(dinerBill.getBillId() + "sctdb2_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
       try
       {
         for (DinerBillDishe o : dinerBill.getDinerBillDishes())
         {
           Dishe d = (Dishe)this.disheService.getOne(o.getDishesId());
           stockUpdateForDishes(restId, null, d, o.getUnitNum(), true, map);
         }
         if(orderBillDishesSets !=null){
	         for (OrderBillDishesSet orderBillDishesSet : orderBillDishesSets)
	           stockUpdateForSet(restId, orderBillDishesSet.getDishesSet().getDsId(), orderBillDishesSet.getDsDishesDesc(), null, Float.valueOf(1.0F), map);
	         }
         }
       catch (Exception e) {
         e.printStackTrace();
       }
 
     }
 
     if ((BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) && (StringUtils.isNotEmpty(tId))) {
       Takeout takeout = (Takeout)this.takeoutService.getOne(tId);
       takeout.setDinerBill(dinerBill);
       takeout.setBillNo(dinerBill.getBillNo());
       this.takeoutService.save(takeout);
 
       map.put(takeout.gettId() + "sctdb_" + OperationTypeEnum.UPDATE.getCode(), takeout);
 
       dinerBill.setDeliverCost(takeout.getDeliverCost());
 
       if (StringUtils.isNotEmpty(takeout.getMcId())) {
         MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(takeout.getMcId());
         RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(membershipCard.getRestMemberInfo().getMbId());
         dinerBill.setMembershipCard(membershipCard);
         dinerBill.setRestMemberInfo(restMemberInfo);
       }
 
       if (StringUtils.isNotEmpty(takeout.getCcdId())) {
         CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(takeout.getCcdId());
         dinerBill.setCashDiscount(cashDiscount);
         dinerBill.setDiscountName(cashDiscount.getDiscountName());
         dinerBill.setDiscount(Integer.valueOf(0));
         dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
         dinerBill.setOtherDiscount("");
       }
       this.self.save(dinerBill);
 
       map.put(dinerBill.getBillId() + "sctdb3_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
     }
 
     saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.START_TABLE, logNotes, map);
     return true;
   }
 
   public Map<String, String> saveDishesSet(String restId, String billId, String dsId, String billType, String dsDishesDesc, String dishesNote, String dishesTasteIdArray, String dishesAvoidfoodIdArray, Integer dishesPungentLevel, Employee user, Float unitNum, String tId, LinkedHashMap<String, Object> map, LinkedHashMap<String, Object> mapbill) throws Exception
   {
     Map messageMap = new HashMap();
     if ((billId == null) || (billId.isEmpty())) {
       DinerBill d = new DinerBill();
       d.setBillTime(new Date());
       d.setBillType(billType);
       d.setTable(null);
       saveCreateTableDinerBill(restId, d, null, tId, mapbill);
       billId = d.getBillId();
     }
 
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     if (dinerBill.getBillStatus().equals(BillStatusEnum.PLACE_ORDER.getCode())) {
       dinerBill.setBillStatus(BillStatusEnum.SOME_PLACE_ORDER.getCode());
     }
     DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dsId);
     DinerBillDishesSet dinerBillDishesSet = new DinerBillDishesSet();
     dinerBillDishesSet.setDinerBill(dinerBill);
     dinerBillDishesSet.setDsId(dsId);
     dinerBillDishesSet.setDishesSetCategory(dishesSet.getDishesSetCategory());
     dinerBillDishesSet.setBillNo(dinerBill.getBillNo());
     dinerBillDishesSet.setDsName(dishesSet.getDsName());
     dinerBillDishesSet.setDsCode(dishesSet.getDsCode());
     dinerBillDishesSet.setUnitPrice(dishesSet.getPrice());
     if (unitNum == null)
       dinerBillDishesSet.setUnitNum(1.0F);
     else {
       dinerBillDishesSet.setUnitNum(unitNum.floatValue());
     }
     dinerBillDishesSet.setOrderEmp(user);
     dinerBillDishesSet.setDsStatus(DishesStatusEnum.UNXIADAN.getCode());
     dinerBillDishesSet.setUnitId(dishesSet.getDishesUnit().getUnitId());
     dinerBillDishesSet.setUnitName(dishesSet.getDishesUnit().getName());
     dinerBillDishesSet.setDiscount(Integer.valueOf(100));
     dinerBillDishesSet.setRealUnitPrice(dishesSet.getPrice());
     dinerBillDishesSet.setPungentLevel(0);
     dinerBillDishesSet.setOrderTime(DateProvider.DEFAULT.getDate());
     dinerBillDishesSet.setIsOnsale(dishesSet.getIsOnsale());
     dinerBillDishesSet.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
     dinerBillDishesSet.setDsDishesDesc(dsDishesDesc);
     dinerBillDishesSet.setSaleCommission(dishesSet.getSaleCommission());
     dinerBillDishesSet.setServiceCommission(dishesSet.getServiceCommission());
 
     if (dishesNote != null) {
       dinerBillDishesSet.setNotes(dishesNote);
     }
 
     if (dishesTasteIdArray != null) {
       dinerBillDishesSet.setTasteIdArray(dishesTasteIdArray);
       String tasteNameArray = this.dishesTasteService.findNameArray(dishesTasteIdArray);
       dinerBillDishesSet.setTasteNameArray(tasteNameArray);
     }
 
     if (dishesAvoidfoodIdArray != null) {
       dinerBillDishesSet.setAvoidfoodIdArray(dishesAvoidfoodIdArray);
       String avoidfoodNameArray = this.dishesAvoidfoodService.findNameArray(dishesAvoidfoodIdArray);
       dinerBillDishesSet.setAvoidfoodNameArray(avoidfoodNameArray);
     }
 
     if (dishesPungentLevel != null) {
       dinerBillDishesSet.setPungentLevel(dishesPungentLevel.intValue());
     }
 
     this.billService.calculatorSingleDishesSet(restId, dinerBill, dinerBillDishesSet, dishesSet);
     this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
     map.put(dinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishesSet);
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     if (dishesSet.getEstimate() != null)
     {
       if (unitNum != null)
         estimateDishesSet(Float.valueOf(dishesSet.getEstimate().floatValue() - unitNum.floatValue()), dishesSet);
       else {
         estimateDishesSet(Float.valueOf(dishesSet.getEstimate().floatValue() - 1.0F), dishesSet);
       }
 
     }
 
     messageMap = stockUpdateForSet(restId, null, dsDishesDesc, "", unitNum, map);
 
     messageMap.put("code", StatusCodeEnum.SUCCESS.getCode());
     messageMap.put("estimate", String.valueOf(dishesSet.getEstimate() == null ? "" : dishesSet.getEstimate()));
 
     messageMap.put("dinerBillDisheId", dinerBillDishesSet.getBdsId());
     messageMap.put("dishesName", dishesSet.getDsName());
     messageMap.put("realCost", BigDecimalUtil.format(dinerBill.getPayableCost()).toString());
     if (messageMap.containsKey("rmDesc"))
     {
       String rmNotes = BillOpTypeEnum.ORDER_DISH.getDesc() + ":" + (String)messageMap.get("rmDesc");
       this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
     }
 
     String logNotes = "加菜操作";
     logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsName() + "，数量：" + dinerBillDishesSet.getUnitNum();
     saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.ORDER_DISH, logNotes, map);
     return messageMap;
   }
 
   public Map<String, String> dishSetSaveAndUpdateStock(String bdsId, String restId, String dsDishesDesc, String oldDsDishesDesc, LinkedHashMap<String, Object> map)
   {
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
 
     Map messageMap = null;
     try {
       messageMap = stockUpdateForSet(restId, null, dsDishesDesc, dinerBillDishesSet.getDsDishesDesc(), Float.valueOf(1.0F), map);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     dinerBillDishesSet.setDsDishesDesc(dsDishesDesc);
     this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
     map.put(dinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
 
     return messageMap;
   }
 
   public Map<String, String> stockCheckForSet(String restId, String dsId, String dsDishesDesc, String oldDishesDesc)
     throws Exception
   {
     DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dsId);
     ObjectMapper mapper = new ObjectMapper();
     Map messageMap = new HashMap();
     StringBuffer sb0 = new StringBuffer();
     StringBuffer sb1 = new StringBuffer();
     Map oldStockCounts = null;
 
     if (StringUtils.isNotEmpty(oldDishesDesc)) {
       oldStockCounts = new HashMap();
       List oldList = (List)mapper.readValue(dsDishesDesc, List.class);
       for (int i = 0; i < oldList.size(); i++) {
         Map map = (Map)oldList.get(i);
         String dishesId = map.get("dishesId").toString();
         float unitNum = Float.parseFloat(map.get("unitNum").toString());
 
         List<DishesRaw> dishesRaws = this.dishesRawService.getByRestIdAndDishesId(restId, dishesId);
         for (DishesRaw dishesRaw : dishesRaws)
         {
           RawMaterial rawMaterial = dishesRaw.getRawMaterial();
           if (rawMaterial.getRmId() == null)
             continue;
           float rmUserCount = dishesRaw.getUseCount().floatValue() * unitNum;
           if (oldStockCounts.containsKey(rawMaterial.getRmId()))
           {
             oldStockCounts.put(rawMaterial.getRmId(), Float.valueOf(((Float)oldStockCounts.get(rawMaterial.getRmId())).floatValue() + rmUserCount));
           }
           else {
             oldStockCounts.put(rawMaterial.getRmId(), Float.valueOf(rmUserCount));
           }
 
         }
 
       }
 
     }
 
     Map newStockCount = new HashMap();
     List preventNameRepeat0 = new ArrayList();
     List preventNameRepeat1 = new ArrayList();
     List newList = (List)mapper.readValue(dsDishesDesc, List.class);
     for (int i = 0; i < newList.size(); i++) {
       Map map = (Map)newList.get(i);
       String dishesId = map.get("dishesId").toString();
       float unitNum = Float.parseFloat(map.get("unitNum").toString());
 
       List<DishesRaw> dishesRaws = this.dishesRawService.getByRestIdAndDishesId(restId, dishesId);
       for (DishesRaw dishesRaw : dishesRaws)
       {
         RawMaterial rawMaterial = dishesRaw.getRawMaterial();
         float oldDrStockCount = 0.0F;
         if (rawMaterial.getRmId() != null) {
           if ((oldStockCounts != null) && 
             (oldStockCounts.containsKey(rawMaterial.getRmId()))) {
             oldDrStockCount = ((Float)oldStockCounts.get(rawMaterial.getRmId())).floatValue();
           }
 
           float stockCount = 0.0F;
           if (newStockCount.containsKey(rawMaterial.getRmId()))
           {
             stockCount = ((Float)newStockCount.get(rawMaterial.getRmId())).floatValue() - dishesRaw.getUseCount().floatValue() * unitNum;
           }
           else {
             stockCount = oldDrStockCount + rawMaterial.getStockCount().floatValue() - dishesRaw.getUseCount().floatValue() * unitNum;
           }
           newStockCount.put(rawMaterial.getRmId(), Float.valueOf(stockCount));
           if (stockCount < 0.0F) {
             if (!preventNameRepeat0.contains(rawMaterial.getRmId())) {
               messageMap.put("result", "0");
               sb0.append(rawMaterial.getRmName() + ",");
               preventNameRepeat0.add(rawMaterial.getRmId());
             }
           }
           else if (stockCount <= rawMaterial.getWarnCount().floatValue()) {
             if (messageMap.containsKey("result")) {
               if ((((String)messageMap.get("result")).equals("0")) || 
                 (preventNameRepeat1.contains(rawMaterial.getRmId()))) continue;
               messageMap.put("result", "1");
               sb1.append(rawMaterial.getRmName() + ",");
               preventNameRepeat1.add(rawMaterial.getRmId());
             }
             else if (!preventNameRepeat1.contains(rawMaterial.getRmId())) {
               messageMap.put("result", "1");
               sb1.append(rawMaterial.getRmName() + ",");
               preventNameRepeat1.add(rawMaterial.getRmId());
             }
           }
 
         }
         else
         {
           messageMap.put("result", "3");
         }
       }
     }
 
     if (messageMap.containsKey("result")) {
       if (((String)messageMap.get("result")).equals("0"))
         messageMap.put("message", dishesSet.getDsName() + "中" + sb0.substring(0, sb0.length() - 1) + "原料库存不足");
       else if (((String)messageMap.get("result")).equals("1"))
         messageMap.put("message", sb1 + "原料库存预警，请及时补充原料");
     }
     else {
       messageMap.put("result", "2");
     }
     newStockCount = null;
     oldStockCounts = null;
     preventNameRepeat0 = null;
     preventNameRepeat1 = null;
     return messageMap;
   }
 
   public Map<String, String> stockUpdateForSet(String restId, String dsId, String dsDishesDesc, String oldDishesDesc, Float dsUnitNum, LinkedHashMap<String, Object> mapSyn)
     throws Exception
   {
     ObjectMapper mapper = new ObjectMapper();
     Map messageMap = new HashMap();
     StringBuffer sb1 = new StringBuffer();
 
     if (dsId != null) {
       DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dsId);
       if (dishesSet.getEstimate() != null)
       {
         estimateDishesSet(Float.valueOf(dishesSet.getEstimate().floatValue() - dsUnitNum.floatValue()), dishesSet);
       }
     }
 
     StringBuffer sb = new StringBuffer();
     sb.append("修改了属性：");
     boolean isRecordRm = false;
 
     if (StringUtils.isNotEmpty(oldDishesDesc))
     {
       List oldList = (List)mapper.readValue(dsDishesDesc, List.class);
       for (int i = 0; i < oldList.size(); i++) {
         Map map = (Map)oldList.get(i);
         String dishesId = map.get("dishesId").toString();
         float unitNum = Float.parseFloat(map.get("unitNum").toString());
 
         List<DishesRaw> dishesRaws = this.dishesRawService.getByRestIdAndDishesId(restId, dishesId);
         for (DishesRaw dishesRaw : dishesRaws) {
           isRecordRm = true;
 
           RawMaterial rawMaterial = dishesRaw.getRawMaterial();
           if (rawMaterial.getRmId() == null) {
             continue;
           }
           Float oldDescStockCount = Float.valueOf(0.0F);
           if (dsUnitNum != null)
             oldDescStockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() + dishesRaw.getUseCount().floatValue() * unitNum * dsUnitNum.floatValue());
           else {
             oldDescStockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() + dishesRaw.getUseCount().floatValue() * unitNum);
           }
           sb.append(rawMaterial.getRmName() + "数量从" + rawMaterial.getStockCount() + rawMaterial.getDishesUnit().getName() + "改为" + oldDescStockCount + rawMaterial.getDishesUnit().getName() + ",");
           rawMaterial.setStockCount(oldDescStockCount);
           this.rawMaterialService.save(rawMaterial);
 
           map.put(rawMaterial.getRmId() + "1_" + OperationTypeEnum.UPDATE.getCode(), rawMaterial);
         }
       }
 
     }
 
     Set<String> rmNameSet = new HashSet();
     List preventNameRepeat1 = new ArrayList();
     List list = (List)mapper.readValue(dsDishesDesc, List.class);
     Map map;
     for (int i = 0; i < list.size(); i++) {
       map = (Map)list.get(i);
       String dishesId = map.get("dishesId").toString();
       float unitNum = Float.parseFloat(map.get("unitNum").toString());
 
       Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
 
       List<DishesRaw> dishesRaws = this.dishesRawService.findByRestIdAndDishe(restId, dishe);
       for (DishesRaw dishesRaw : dishesRaws) {
         isRecordRm = true;
 
         RawMaterial rawMaterial = dishesRaw.getRawMaterial();
         if (rawMaterial.getRmId() == null)
           continue;
         Float stockCount = Float.valueOf(0.0F);
         if (dsUnitNum != null)
           stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() - dishesRaw.getUseCount().floatValue() * unitNum * dsUnitNum.floatValue());
         else {
           stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() - dishesRaw.getUseCount().floatValue() * unitNum);
         }
 
         sb.append(rawMaterial.getRmName() + "数量从" + rawMaterial.getStockCount() + rawMaterial.getDishesUnit().getName() + "改为" + stockCount + rawMaterial.getDishesUnit().getName() + ",");
         if (stockCount.floatValue() < 0.0F) {
           if (rmNameSet.contains(rawMaterial.getRmName()))
             rmNameSet.remove(rawMaterial.getRmName());
         }
         else if ((stockCount.floatValue() <= rawMaterial.getWarnCount().floatValue()) && 
           (!preventNameRepeat1.contains(rawMaterial.getRmId()))) {
           messageMap.put("result", "1");
           rmNameSet.add(rawMaterial.getRmName());
           preventNameRepeat1.add(rawMaterial.getRmId());
         }
 
         rawMaterial.setStockCount(stockCount);
         this.rawMaterialService.save(rawMaterial);
 
         map.put(rawMaterial.getRmId() + "_" + OperationTypeEnum.UPDATE.getCode(), rawMaterial);
       }
 
     }
 
     for (String rName : rmNameSet) {
       sb1.append(rName + ",");
     }
     if (rmNameSet.size() == 0) {
       messageMap.put("result", "2");
     }
     rmNameSet = null;
     preventNameRepeat1 = null;
     sb.deleteCharAt(sb.length() - 1);
     if (isRecordRm)
     {
       messageMap.put("rmDesc", sb);
     }
 
     if (messageMap.containsKey("result")) {
       if (((String)messageMap.get("result")).equals("1"))
         messageMap.put("message", sb1 + "原料库存预警，请及时补充原料");
     }
     else {
       messageMap.put("result", "2");
       messageMap.put("message", "添加成功");
     }
     return messageMap;
   }
 
   public DwzAjaxDone addDishesSet(String billType, String billId, String restId, String dsDishesDesc, String dsId, Employee user, String tId, LinkedHashMap<String, Object> map, LinkedHashMap<String, Object> mapBill)
     throws Exception
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     if ((billType == null) || (billType.isEmpty())) {
       billType = BillTypeEnum.NORMAL_BILL.getCode();
       ajax.setType(billType);
     }
     if ((billId == null) || (billId.isEmpty()))
     {
       DinerBill d = new DinerBill();
       d.setBillTime(new Date());
       d.setBillType(billType);
       d.setTable(null);
       saveCreateTableDinerBill(restId, d, null, tId, mapBill);
       billId = d.getBillId();
       ajax.setType(billType);
     }
 
     Map messageMap = stockUpdateForSet(restId, null, dsDishesDesc, "", Float.valueOf(1.0F), map);
     ajax.setValue(billId);
     ajax.setSign(formatFloat((String)messageMap.get("estimate")));
     ajax.setStatusCode((String)messageMap.get("code"));
     ajax.setRel(dsId);
     ajax.setMessage("加菜成功");
     ajax.setMessageMap(messageMap);
     return ajax;
   }
 
   public Map<String, String> tcStockUpdateByNumJudge(String bdId, String restId, String oldDishNum, String newDishNum)
     throws Exception
   {
     ObjectMapper mapper = new ObjectMapper();
     Map messageMap = new HashMap();
     StringBuffer sb0 = new StringBuffer();
     StringBuffer sb1 = new StringBuffer();
     boolean disheSetNum = isEstimateEnoughForDishesSetBill(restId, bdId, newDishNum);
     if (disheSetNum) {
       float dishNum = Float.parseFloat(newDishNum) - Float.parseFloat(oldDishNum);
       DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdId);
       String dsDishesDesc = dinerBillDishesSet.getDsDishesDesc();
       List newList = (List)mapper.readValue(dsDishesDesc, List.class);
       Map newStockCount = new HashMap();
       List preventNameRepeat0 = new ArrayList();
       List preventNameRepeat1 = new ArrayList();
       for (int i = 0; i < newList.size(); i++) {
         Map map = (Map)newList.get(i);
         String dishesId = map.get("dishesId").toString();
         float unitNum = Float.parseFloat(map.get("unitNum").toString()) * dishNum;
 
         List<DishesRaw> dishesRaws = this.dishesRawService.getByRestIdAndDishesId(restId, dishesId);
         for (DishesRaw dishesRaw : dishesRaws)
         {
           RawMaterial rawMaterial = dishesRaw.getRawMaterial();
           if (rawMaterial.getRmId() != null)
           {
             float stockCount = 0.0F;
             if (newStockCount.containsKey(rawMaterial.getRmId()))
             {
               stockCount = ((Float)newStockCount.get(rawMaterial.getRmId())).floatValue() - dishesRaw.getUseCount().floatValue() * unitNum;
             }
             else {
               stockCount = rawMaterial.getStockCount().floatValue() - dishesRaw.getUseCount().floatValue() * unitNum;
             }
 
             newStockCount.put(rawMaterial.getRmId(), Float.valueOf(stockCount));
             if (stockCount < 0.0F) {
               if (!preventNameRepeat0.contains(rawMaterial.getRmId())) {
                 messageMap.put("result", "0");
                 sb0.append(rawMaterial.getRmName() + ",");
                 preventNameRepeat0.add(rawMaterial.getRmId());
               }
             } else {
               if (stockCount > rawMaterial.getWarnCount().floatValue())
                 continue;
               if (messageMap.containsKey("result")) {
                 if ((((String)messageMap.get("result")).equals("0")) || 
                   (preventNameRepeat1.contains(rawMaterial.getRmId()))) continue;
                 messageMap.put("result", "1");
                 sb1.append(rawMaterial.getRmName() + ",");
                 preventNameRepeat1.add(rawMaterial.getRmId());
               }
               else if (!preventNameRepeat1.contains(rawMaterial.getRmId())) {
                 messageMap.put("result", "1");
                 sb1.append(rawMaterial.getRmName() + ",");
                 preventNameRepeat1.add(rawMaterial.getRmId());
               }
             }
           }
           else
           {
             messageMap.put("result", "3");
           }
         }
       }
       newStockCount = null;
       preventNameRepeat0 = null;
       preventNameRepeat1 = null;
     }
     else {
       messageMap.put("result", "4");
       messageMap.put("message", "套餐剩余数量不足");
     }
 
     if (messageMap.containsKey("result")) {
       if (((String)messageMap.get("result")).equals("0"))
         messageMap.put("message", sb0.substring(0, sb0.length() - 1) + "原料库存不足，是否继续?");
       else if (((String)messageMap.get("result")).equals("1")) {
         messageMap.put("message", sb1 + "原料库存预警，请及时补充原料");
       }
     }
     else {
       messageMap.put("result", "2");
     }
 
     return messageMap;
   }
 
   public Map<String, String> tcStockUpdateByNum(String bdsId, String billId, String restId, String oldDishNum, String newDishNum)
     throws Exception
   {
     ObjectMapper mapper = new ObjectMapper();
     Map messageMap = new HashMap();
     StringBuffer sb1 = new StringBuffer();
 
     StringBuffer sb = new StringBuffer();
     sb.append("修改了属性:");
     boolean isRecord = false;
     float dishNum = Float.parseFloat(newDishNum) - Float.parseFloat(oldDishNum);
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
     String dsDishesDesc = dinerBillDishesSet.getDsDishesDesc();
     List newList = (List)mapper.readValue(dsDishesDesc, List.class);
     List preventNameRepeat1 = new ArrayList();
 
     Set<String> rmNameSet = new HashSet();
     Map map;
     for (int i = 0; i < newList.size(); i++) {
       map = (Map)newList.get(i);
       String dishesId = map.get("dishesId").toString();
       float unitNum = Float.parseFloat(map.get("unitNum").toString()) * dishNum;
 
       List<DishesRaw> dishesRaws = this.dishesRawService.getByRestIdAndDishesId(restId, dishesId);
       for (DishesRaw dishesRaw : dishesRaws) {
         isRecord = true;
 
         RawMaterial rawMaterial = dishesRaw.getRawMaterial();
         if (rawMaterial.getRmId() == null)
           continue;
         Float stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() - dishesRaw.getUseCount().floatValue() * unitNum);
         sb.append(rawMaterial.getRmName() + "数量从" + rawMaterial.getStockCount() + rawMaterial.getDishesUnit().getName() + "改为" + stockCount + rawMaterial.getDishesUnit().getName() + ",");
         if ((stockCount.floatValue() < 0.0F) || (stockCount.floatValue() <= rawMaterial.getWarnCount().floatValue())) {
           rawMaterial.setStockCount(stockCount);
           this.rawMaterialService.save(rawMaterial);
           if (!preventNameRepeat1.contains(rawMaterial.getRmId())) {
             messageMap.put("result", "1");
             rmNameSet.add(rawMaterial.getRmName());
             preventNameRepeat1.add(rawMaterial.getRmId());
           }
         } else {
           rawMaterial.setStockCount(stockCount);
           this.rawMaterialService.save(rawMaterial);
         }
       }
 
     }
 
     for (String rName : rmNameSet) {
       sb1.append(rName + ",");
     }
 
     rmNameSet = null;
     preventNameRepeat1 = null;
     sb.deleteCharAt(sb.length() - 1);
     if (isRecord) {
       messageMap.put("rmDesc", sb);
     }
     if (messageMap.containsKey("result")) {
       if (((String)messageMap.get("result")).equals("1"))
         messageMap.put("message", sb1 + "原料库存预警，请及时补充原料");
     }
     else {
       messageMap.put("result", "2");
       messageMap.put("message", "添彩成功");
     }
     return messageMap;
   }
 
   public Map<String, String> cyStockUpdateByNum(String bdId, String billId, String restId, String oldDishNum, String newDishNum)
   {
     DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
     float dishesNum = Float.parseFloat(newDishNum) - Float.parseFloat(oldDishNum);
     String dishesId = dinerBillDishe.getDishesId();
     StringBuffer sb = new StringBuffer();
     sb.append("修改了属性:");
 
     Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
 
     List<DishesRaw> dishesRaws = this.dishesRawService.findByRestIdAndDishe(restId, dishe);
     Map messageMap = new HashMap();
     StringBuffer sb1 = new StringBuffer();
     boolean isRecord = false;
     for (DishesRaw dishesRaw : dishesRaws) {
       isRecord = true;
 
       RawMaterial rawMaterial = dishesRaw.getRawMaterial();
       if (rawMaterial.getRmId() == null)
         continue;
       Float stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() - dishesRaw.getUseCount().floatValue() * dishesNum);
       sb.append(rawMaterial.getRmName() + "数量从" + rawMaterial.getStockCount() + rawMaterial.getDishesUnit().getName() + "改为" + stockCount + rawMaterial.getDishesUnit().getName() + ",");
       if ((stockCount.floatValue() < 0.0F) || (stockCount.floatValue() <= rawMaterial.getWarnCount().floatValue())) {
         rawMaterial.setStockCount(stockCount);
         this.rawMaterialService.save(rawMaterial);
         messageMap.put("result", "1");
         sb1.append(rawMaterial.getRmName() + ",");
       } else {
         rawMaterial.setStockCount(stockCount);
         this.rawMaterialService.save(rawMaterial);
       }
     }
 
     sb.deleteCharAt(sb.length() - 1);
     if (isRecord) {
       messageMap.put("rmDesc", sb);
     }
     if (messageMap.containsKey("result")) {
       if (((String)messageMap.get("result")).equals("1"))
         messageMap.put("message", sb1 + "原料库存预警，请及时补充原料");
     }
     else {
       messageMap.put("result", "2");
       messageMap.put("message", "添加成功");
     }
     return messageMap;
   }
 
   public void saveBusiLog(String restId, DinerBill dinerBill, BussLogTypeEnum bussLogTypeEnum, BillOpTypeEnum billOpTypeEnum, String notes)
   {
     BusiLog busiLog = new BusiLog();
     busiLog.setRestId(restId);
 
     busiLog.setBussLogType(bussLogTypeEnum.getCode());
 
     busiLog.setOpType(billOpTypeEnum.getCode());
     if (dinerBill.getTable() != null)
     {
       busiLog.setTabId(dinerBill.getTable().getTabId());
       busiLog.setTabNo(dinerBill.getTable().getTabNo());
     }
     else if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) {
       busiLog.setTabNo("外卖");
     } else if (BillTypeEnum.ZIZHU_BILL.getCode().equals(dinerBill.getBillType())) {
       busiLog.setTabNo("终端自助");
     } else if (BillTypeEnum.SELFORDER_BILL.getCode().equals(dinerBill.getBillType())) {
       busiLog.setTabNo("手机自助");
     } else if (BillTypeEnum.KUAICAN_BILL.getCode().equals(dinerBill.getBillType())) {
       busiLog.setTabNo("快餐");
     }
 
     busiLog.setBillId(dinerBill.getBillId());
 
     busiLog.setBillNo(dinerBill.getBillNo());
 
     busiLog.setPayTime(dinerBill.getPayTime());
     busiLog.setNotes(notes);
 
     this.busiLogService.save(busiLog);
   }
 
   public void saveDinerBillLog(String restId, DinerBill dinerBill, BillOpTypeEnum billOpTypeEnum, String notes, LinkedHashMap<String, Object> map)
   {
     DinerBillLog dinerBillLog = new DinerBillLog();
 
     dinerBillLog.setBillOpType(billOpTypeEnum.getCode());
 
     dinerBillLog.setRestId(restId);
 
     Table table = dinerBill.getTable();
     if (table != null) {
       dinerBillLog.setTable(table);
 
       dinerBillLog.setTabNo(table.getTabNo());
     }
     else if (BillTypeEnum.KUAICAN_BILL.getCode().equals(dinerBill.getBillType())) {
       dinerBillLog.setTabNo("快餐");
     } else if (BillTypeEnum.ZIZHU_BILL.getCode().equals(dinerBill.getBillType())) {
       dinerBillLog.setTabNo("终端自助");
     } else if (BillTypeEnum.SELFORDER_BILL.getCode().equals(dinerBill.getBillType())) {
       dinerBillLog.setTabNo("手机自助");
     } else if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) {
       dinerBillLog.setTabNo("外卖");
     }
 
     dinerBillLog.setDinerBill(dinerBill);
 
     dinerBillLog.setBillNo(dinerBill.getBillNo());
 
     dinerBillLog.setNotes(notes);
 
     dinerBillLog.setOnlineId(dinerBill.getOnlineBillId());
 
     dinerBillLog.setOnlineNo(dinerBill.getOnlineBillNo());
     this.dinerBillLogService.save(dinerBillLog);
 
     map.put(dinerBillLog.getDblId() + "sdbl_" + OperationTypeEnum.CREATE.getCode(), dinerBillLog);
   }
 
   public void saveDinerBillLogForOther(String restId, String onlineId, String onlineNo, DinerBill dinerBill, BillOpTypeEnum billOpTypeEnum, Date createTime, String notes, LinkedHashMap<String, Object> map)
   {
     DinerBillLog dinerBillLog = new DinerBillLog();
 
     dinerBillLog.setBillOpType(billOpTypeEnum.getCode());
 
     dinerBillLog.setRestId(restId);
 
     if (dinerBill != null) {
       dinerBillLog.setDinerBill(dinerBill);
       dinerBillLog.setBillNo(dinerBill.getBillNo());
     }
     dinerBillLog.setOnlineId(onlineId);
     dinerBillLog.setOnlineNo(onlineNo);
     dinerBillLog.setCreateTime(createTime);
 
     dinerBillLog.setNotes(notes);
     this.dinerBillLogService.save(dinerBillLog);
 
     map.put(dinerBillLog.getDblId() + "sdbl_" + OperationTypeEnum.CREATE.getCode(), dinerBillLog);
   }
 
   @Transactional(readOnly=false)
   public void saveChedanDinerBill(String id, String restId, String cancelReasonId, LinkedHashMap<String, Object> map)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(id);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus)) || (BillStatusEnum.SOME_PLACE_ORDER.getCode().equals(billStatus)) || (BillStatusEnum.BING_TAI.getCode().equals(billStatus))) {
       return;
     }
 
     boolean isHaveTable = true;
     if ((dinerBill.getBillType().equals(BillTypeEnum.WAIMAI_BILL.getCode())) || (dinerBill.getBillType().equals(BillTypeEnum.KUAICAN_BILL.getCode())) || (dinerBill.getBillType().equals(BillTypeEnum.ZIZHU_BILL.getCode()))) {
       isHaveTable = false;
     }
 
     if (dinerBill.getIsChedan().equals(TrueFalseEnum.TRUE.getCode())) {
       String cancleReason = "";
 
       if (!isHaveTable) {
         dinerBill.setTable(null);
       }
 
       if (StringUtils.isNotEmpty(cancelReasonId)) {
         SpeOpReason speOpReason = (SpeOpReason)this.speOpReasonService.getOne(cancelReasonId);
         if (speOpReason != null) {
           cancleReason = speOpReason.getName();
         }
         dinerBill.setCancelBillReason(cancleReason);
       }
 
       dinerBill.setBillStatus(BillStatusEnum.CANCEL_BILL.getCode());
       dinerBill.setPayTime(DateProvider.DEFAULT.getDate());
       this.self.save(dinerBill);
 
       map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
       if (isHaveTable) {
         TableBillRelation tableBillRelation = dinerBill.getTableBillRelation();
         if (tableBillRelation != null) {
           String rid = tableBillRelation.getTabBillId();
           this.tableBillRelationService.delete(rid);
 
           map.put(rid + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_table_bill_relation,tab_bill_id," + rid);
         }
 
         Table t = (Table)this.tableService.getOne(dinerBill.getTable().getTabId());
         t.setDinnerStatus(DinnerStatusEnum.IDLE.getCode());
         this.tableService.save(t);
 
         map.put(t.getTabId() + "_" + OperationTypeEnum.UPDATE.getCode(), t);
       }
 
       String logNotes = "撤单操作";
       logNotes = logNotes + "，单号:" + dinerBill.getBillNo();
       saveBusiLog(restId, dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.CHEDAN_BILL, logNotes);
       saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.CHEDAN_BILL, logNotes, map);
 
       String onlineBillId = dinerBill.getOnlineBillId();
       if (StringUtils.isNotEmpty(onlineBillId)) {
         CancelBillAndOrder cancelBillAndOrder = new CancelBillAndOrder();
 
         if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType()))
         {
           BigDecimal membercardCost = returnBalance(restId, dinerBill, map);
 
           cancelBillAndOrder.updateUserTakeout(membercardCost, onlineBillId, dinerBill, cancleReason, map);
         }
         else {
           BigDecimal membercardCost = returnBalance(restId, dinerBill, map);
           TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(dinerBill.getOrderId());
 
           cancelBillAndOrder.updateUserOrder(membercardCost, onlineBillId, tableOrder, cancleReason, map);
         }
       }
     }
   }
 
   private BigDecimal returnBalance(String restId, DinerBill dinerBill, LinkedHashMap<String, Object> map)
   {
     MembershipCard membershipCard = dinerBill.getMembershipCard();
     BigDecimal membercardCost = null;
     if (membershipCard != null) {
       PaymentType paymentTypeOnline = this.paymentTypeService.findeByRestIdAndPaymentType(restId, PaymentTypeEnum.WEB_PAY.getCode());
       DinerBillPayment dinerBillPaymentOnline = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, restId, paymentTypeOnline);
       if (dinerBillPaymentOnline != null) {
         membercardCost = dinerBillPaymentOnline.getMoney();
 
         membershipCard.setBalance(membershipCard.getBalance().add(membercardCost));
         this.membershipCardService.save(membershipCard);
 
         map.put(membershipCard.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), membershipCard);
 
         MembershipCardOperation membershipCardOperation = new MembershipCardOperation();
         RestMemberInfo restMemberInfo = membershipCard.getRestMemberInfo();
         restMemberInfo.getName();
         membershipCardOperation.setRestMemberInfo(restMemberInfo);
         membershipCardOperation.setMembershipCard(membershipCard);
         membershipCardOperation.setCardOperationType(CardOperationTypeEnum.CANCEL_REFUND.getCode());
         membershipCardOperation.setAddIntegral(BigDecimal.ZERO);
         membershipCardOperation.setTotalIntegral(membershipCard.getMemberIntegral());
         membershipCardOperation.setPaidinCash(membercardCost);
         membershipCardOperation.setRechargeCash(membercardCost);
         membershipCardOperation.setIsDrawBill(TrueFalseEnum.FALSE.getCode());
         membershipCardOperation.setBalance(membershipCard.getBalance());
         membershipCardOperation.setBillNo(dinerBill.getBillNo());
         membershipCardOperation.setDinerBill(dinerBill);
         this.membershipCardOperationService.save(membershipCardOperation);
 
         map.put(membershipCardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), membershipCardOperation);
       }
 
     }
 
     StringBuilder message = new StringBuilder();
 
     if ((BillFromEnum.NET_TAKEOUT.getCode().equals(dinerBill.getBillFrom())) || (BillFromEnum.WX_TAKEOUT.getCode().equals(dinerBill.getBillFrom()))) {
       Takeout takeout = this.takeoutService.findByRestIdAndOnlineTakeoutId(restId, dinerBill.getOnlineBillId());
       if (takeout != null) {
         String onlinePaymentType = takeout.getOnlinePaymentType();
         String takeoutNo = takeout.getOnlineTakeoutNo();
         message.append(takeout.getOnlineUserMobile());
         message.append("-");
         if (CloudPaymentTypeEnum.MCP.getCode().equals(onlinePaymentType))
           message.append("您的外卖单(" + takeoutNo + ")已成功取消，" + membercardCost + "元退款已退回到会员卡");
         else {
           message.append("您的外卖单(" + takeoutNo + ")已成功取消");
         }
       }
     }
 
     if ((BillFromEnum.NET_ORDER.getCode().equals(dinerBill.getBillFrom())) || (BillFromEnum.WX_ORDER.getCode().equals(dinerBill.getBillFrom()))) {
       List tableOrderList = this.tableOrderService.findByRestIdAndOnlineOrderId(restId, dinerBill.getOnlineBillId());
       if ((tableOrderList != null) && (tableOrderList.size() > 0)) {
         TableOrder tableOrder = (TableOrder)tableOrderList.get(0);
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
     }
 
     map.put("message_" + OperationTypeEnum.MOBILE_MESSAGE.getCode(), message.toString());
     return membercardCost;
   }
 
   @Transactional(readOnly=false)
   public void saveQingtaiDinerBill(String id, LinkedHashMap<String, Object> map)
   {
     Table t = (Table)this.tableService.getOne(id);
     t.setDinnerStatus(DinnerStatusEnum.IDLE.getCode());
     this.tableService.save(t);
 
     map.put(t.getTabId() + "sqdb_" + OperationTypeEnum.UPDATE.getCode(), t);
 
     TableBillRelation tableBillRelation = this.tableService.getLastedTableNormalBillRelation(t);
     if (tableBillRelation != null)
     {
       String rid = tableBillRelation.getTabBillId();
       this.tableBillRelationService.delete(rid);
 
       map.put(rid + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_table_bill_relation,tab_bill_id," + rid);
     }
   }
 
   @Transactional(readOnly=false)
   public DinerBill saveCuiCai(String id, LinkedHashMap<String, Object> map)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(id);
     dinerBill.setUrgeNum(dinerBill.getUrgeNum() + 1);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     Map dc = new HashMap();
 
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
     for (DinerBillDishe e : dinerBillDishes)
     {
       String dishesStatus = e.getDishesStatus();
       if (!DishesStatusEnum.UNSERVE.getCode().equals(dishesStatus)) {
         continue;
       }
       if (dc.containsKey(e.getDishesCategory().getCategoryId()))
         continue;
       dc.put(e.getDishesCategory().getCategoryId(), e.getDishesCategory());
     }
 
     return dinerBill;
   }
 
   @Transactional(readOnly=false)
   public String[] handleSelfOrder(String restId, SelfOrder selfOrder, SelfMessage selfMessage, String tabNo, Employee user, LinkedHashMap<String, Object> map, LinkedHashMap<String, Object> mapBill)
   {
     Map jiaCaiMessageMap = null;
     boolean result = false;
     Table t = this.tableService.findByTabNoAndRestIdAndIsEnable(tabNo, restId);
     if (t == null)
     {
       return new String[] { StatusCodeEnum.LOGIC_ERROR.getCode() };
     }
 
     if (DinnerStatusEnum.SETTLE.getCode().equals(t.getDinnerStatus()))
     {
       return new String[] { StatusCodeEnum.CHECK_ERROR.getCode() };
     }
 
     DinerBill d = new DinerBill();
     TableBillRelation tableBillRelation = this.tableService.getLastedTableNormalBillRelation(t);
     if (tableBillRelation != null)
     {
       d = tableBillRelation.getDinerBill();
       d.setNotes(selfOrder.getNotes());
       result = true;
     }
     else
     {
       d.setBillTime(new Date());
       d.setTable(t);
       d.setTabNo(t.getTabNo());
       if (t.getWaiter() != null)
       {
         d.setWaiterId(t.getWaiter().getEmpId());
         d.setWaiterName(t.getWaiter().getName());
       }
       d.setBillType(BillTypeEnum.SELFORDER_BILL.getCode());
       d.setNotes(selfOrder.getNotes());
       result = saveCreateTableDinerBill(restId, d, null, null, mapBill);
     }
 
     if (result)
     {
       List<SelfDish> selfDishList = selfOrder.getDishList();
       for (SelfDish selfDish : selfDishList) {
         if (!TrueFalseEnum.TRUE.getCode().equals(selfDish.getStatus())) {
           if (TrueFalseEnum.FALSE.getCode().equals(selfDish.getIsSet()))
           {
             boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
             jiaCaiMessageMap = saveJiacai(restId, d.getBillId(), selfDish.getDishesId(), selfDish.getSaleNum(), null, null, null, null, user, null, isJudgeDishRaws, new LinkedHashMap());
           } else if (TrueFalseEnum.TRUE.getCode().equals(selfDish.getIsSet())) {
             List<DishesSetDishes> temp = new ArrayList(((DishesSet)this.dishesSetService.getOne(selfDish.getDishesId())).getDishesSetDishes());
             JSONArray arr = new JSONArray();
             JSONObject jsonObj = null;
             for (DishesSetDishes dishesSetDishes : temp) {
               jsonObj = new JSONObject();
               try {
                 jsonObj.put("dsDishesId", dishesSetDishes.getDsDishesId());
                 jsonObj.put("dishesId", dishesSetDishes.getDishe().getDishesId());
                 jsonObj.put("dishesName", dishesSetDishes.getDishe().getDishesName());
                 jsonObj.put("unitNum", String.valueOf(dishesSetDishes.getUnitNum()));
                 jsonObj.put("unitName", dishesSetDishes.getUnitName());
                 jsonObj.put("dishesCode", dishesSetDishes.getDishe().getDishesCode());
 
                 jsonObj.put("mr_dishesId", dishesSetDishes.getDishe().getDishesId());
                 jsonObj.put("mr_dishesName", dishesSetDishes.getDishe().getDishesName());
                 jsonObj.put("mr_unitNum", String.valueOf(dishesSetDishes.getUnitNum()));
                 jsonObj.put("mr_unitName", dishesSetDishes.getUnitName());
                 jsonObj.put("mr_dishesCode", dishesSetDishes.getDishe().getDishesCode());
                 arr.put(jsonObj);
               }
               catch (JSONException e) {
                 e.printStackTrace();
               }
             }
 
             String dsDishesDesc = arr.toString();
             try {
               jiaCaiMessageMap = saveDishesSet(restId, d.getBillId(), selfDish.getDishesId(), null, dsDishesDesc, null, null, null, null, user, Float.valueOf(selfDish.getSaleNum()), null, map, mapBill);
             }
             catch (Exception e) {
               e.printStackTrace();
             }
           }
 
           if (StatusCodeEnum.SUCCESS.getCode().equals(jiaCaiMessageMap.get("code"))) {
             selfDish.setStatus(TrueFalseEnum.TRUE.getCode());
             this.selfdishService.save(selfDish);
 
             map.put(selfDish.getId() + "_" + OperationTypeEnum.UPDATE.getCode(), selfDish);
           }
         }
 
       }
 
       selfOrder.setStatus(TrueFalseEnum.TRUE.getCode());
       selfOrder.setBillTime(new Date());
       this.selfOrderService.save(selfOrder);
 
       map.put(selfOrder.getId() + "_" + OperationTypeEnum.UPDATE.getCode(), selfOrder);
 
       return new String[] { StatusCodeEnum.SUCCESS.getCode(), d.getBillId() };
     }
     return new String[] { StatusCodeEnum.LOGIC_ERROR.getCode(), d.getBillId() };
   }
 
   @Transactional(readOnly=false)
   public Map<String, String> saveJiacai(String restId, String billId, String dishesId, float dishesNum, String dishesNote, String dishesTasteIdArray, String dishesAvoidfoodIdArray, Integer dishesPungentLevel, Employee user, DinerBillDishe dinerBillDishe, boolean isJudgeDishRaws, LinkedHashMap<String, Object> map)
   {
     Map messageMap = new HashMap();
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     dinerBill.getOnlineBillNo();
     
     if ((BillStatusEnum.SETTLE.getCode().equals(dinerBill.getBillStatus())) || 
       (BillStatusEnum.BING_TAI.getCode().equals(dinerBill.getBillStatus())) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(dinerBill.getBillStatus())) || 
       (BillStatusEnum.SENDING.getCode().equals(dinerBill.getBillStatus()))) {
       messageMap.put("code", StatusCodeEnum.LOGIC_ERROR.getCode());
       return messageMap;
     }
 
     if (dinerBill.getBillStatus().equals(BillStatusEnum.PLACE_ORDER.getCode()))
     {
       dinerBill.setBillStatus(BillStatusEnum.SOME_PLACE_ORDER.getCode());
     }
 
     Dishe d = (Dishe)this.disheService.getOne(dishesId);
     if ((d.getEstimate() != null) && ((d.getEstimate().floatValue() == 0.0F) || (d.getEstimate().floatValue() < dishesNum)))
     {
       messageMap.put("code", StatusCodeEnum.LOGIC_ERROR.getCode());
       messageMap.put("estimate", String.valueOf(d.getEstimate() == null ? "" : d.getEstimate()));
       messageMap.put("result", "4");
       return messageMap;
     }
 
     String op = OperationTypeEnum.UPDATE.getCode();
     if ((dinerBillDishe == null) || (StringUtils.isEmpty(dinerBillDishe.getBdId()))) {
       if (dinerBillDishe == null) {
         dinerBillDishe = new DinerBillDishe();
       }
       op = OperationTypeEnum.CREATE.getCode();
     }
     dinerBillDishe.setDinerBill(dinerBill);
     dinerBillDishe.setDishesId(d.getDishesId());
     dinerBillDishe.setDishesCategory(d.getDishesCategory());
     dinerBillDishe.setBillNo(dinerBill.getBillNo());
     dinerBillDishe.setDishesName(d.getDishesName());
     dinerBillDishe.setDishesCode(d.getDishesCode());
     dinerBillDishe.setUnitPrice(d.getPrice());
     dinerBillDishe.setUnitNum(dishesNum);
     dinerBillDishe.setOrderEmp(user);
     dinerBillDishe.setDishesStatus(DishesStatusEnum.UNXIADAN.getCode());
     dinerBillDishe.setUnitId(d.getDishesUnit().getUnitId());
     dinerBillDishe.setUnitName(d.getDishesUnit().getName());
     dinerBillDishe.setUnitType(d.getDishesUnit().getUnitType());
     dinerBillDishe.setDiscount(Integer.valueOf(100));
     dinerBillDishe.setRealUnitPrice(d.getPrice());
     dinerBillDishe.setIsRulingPrice(d.getIsRulingPrice());
     dinerBillDishe.setOrderTime(DateProvider.DEFAULT.getDate());
     dinerBillDishe.setIsOnsale(d.getIsOnsale());
     dinerBillDishe.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
     dinerBillDishe.setIsSet(TrueFalseEnum.FALSE.getCode());
     dinerBillDishe.setSaleCommission(d.getSaleCommission());
     dinerBillDishe.setServiceCommission(d.getServiceCommission());
     if (dishesNote != null) {
       dinerBillDishe.setNotes(dishesNote);
     }
 
     if (dishesTasteIdArray != null) {
       dinerBillDishe.setTasteIdArray(dishesTasteIdArray);
       String tasteNameArray = this.dishesTasteService.findNameArray(dishesTasteIdArray);
       dinerBillDishe.setTasteNameArray(tasteNameArray);
     }
 
     if (dishesAvoidfoodIdArray != null) {
       dinerBillDishe.setAvoidfoodIdArray(dishesAvoidfoodIdArray);
       String avoidfoodNameArray = this.dishesAvoidfoodService.findNameArray(dishesAvoidfoodIdArray);
       dinerBillDishe.setAvoidfoodNameArray(avoidfoodNameArray);
     }
 
     if (dishesPungentLevel != null) {
       dinerBillDishe.setPungentLevel(dishesPungentLevel.intValue());
     }
 
     this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, d);
     this.dinerBillDisheService.save(dinerBillDishe);
 
     map.put(dinerBillDishe.getBdId() + "_" + op, dinerBillDishe);
 
     this.billService.calculatorForDishe(restId, dinerBillDishe, dinerBill);
     this.dinerBillService.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "sjc1_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     String logNotes = "加菜操作，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
     saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.ORDER_DISH, logNotes, map);
 
     messageMap = stockUpdateForDishes(restId, "", d, dishesNum, isJudgeDishRaws, map);
 
     String tr_class = "";
     if (("3".equals(dinerBillDishe.getDishesStatus())) || ("4".equals(dinerBillDishe.getDishesStatus())))
     {
       tr_class = "text_gray";
     }
     else if ("5".equals(dinerBillDishe.getDishesStatus()))
     {
       tr_class = "text_red";
     }
 
     String td_style = "";
     if ("2".equals(dinerBillDishe.getDishesStatus()))
     {
       td_style = "background: url('/canyin-frontdesk/static/images/zhangdan/dot_right.png') no-repeat scroll 6px 18px transparent";
     }
 
     String total = "";
     String orderNum = "";
     String price = "";
     String dishesName = "";
     if ((dinerBillDishe.getDiscountType() != null) && ("0".equals(dinerBillDishe.getDiscountType())) && (dinerBillDishe.getDiscount().intValue() < 100))
     {
       dishesName = dishesName + "%";
     }
     if (dinerBillDishe.getDishesName().length() > 11)
     {
       dishesName = dishesName + "<a title=\"" + dinerBillDishe.getDishesName() + "\">" + dinerBillDishe.getDishesName().substring(0, 10) + "..</a>";
     }
     else
     {
       dishesName = dishesName + dinerBillDishe.getDishesName();
     }
 
     if (TrueFalseEnum.TRUE.getCode().equals(dinerBillDishe.getIsUserDefined()))
     {
       dishesName = dishesName + "<span class=\"text_blue\">自</span>";
     }
     if (TrueFalseEnum.TRUE.getCode().equals(dinerBillDishe.getIsRulingPrice()))
     {
       dishesName = dishesName + "<span class=\"text_blue\">时</span>";
     }
     if ((dinerBillDishe.getDiscountType() != null) && ("3".equals(dinerBillDishe.getDiscountType())))
     {
       dishesName = dishesName + "<span class=\"text_blue\">赠</span>";
     }
 
     if (!StringUtils.isEmpty(dinerBillDishe.getAllNotes()))
     {
       dishesName = dishesName + "<br/>";
       if (dinerBillDishe.getAllNotes().length() > 9)
       {
         dishesName = dishesName + "<a title=\"" + dinerBillDishe.getAllNotes() + "\">" + dinerBillDishe.getAllNotes().substring(0, 8) + "..</a>";
       }
       else
       {
         dishesName = dishesName + dinerBillDishe.getAllNotes();
       }
     }
     if (("3".equals(dinerBillDishe.getDishesStatus())) || ("4".equals(dinerBillDishe.getDishesStatus())))
     {
       total = total + "<del>";
       orderNum = orderNum + "<del>";
       price = price + "<del>";
       dishesName = dishesName + "<del>";
     }
     if ((dinerBillDishe.getDiscountType() == null) || ("0".equals(dinerBillDishe.getDiscountType())))
     {
       total = total + BigDecimalUtil.format(dinerBillDishe.getOriCost());
     }
     if (("1".equals(dinerBillDishe.getDiscountType())) || ("2".equals(dinerBillDishe.getDiscountType())) || ("3".equals(dinerBillDishe.getDiscountType())))
     {
       total = total + BigDecimalUtil.format(dinerBillDishe.getRealCost());
     }
 
     if ((dinerBillDishe.getDiscountType() == null) || ("0".equals(dinerBillDishe.getDiscountType())) || ("3".equals(dinerBillDishe.getDiscountType())))
     {
       price = price + BigDecimalUtil.format(dinerBillDishe.getUnitPrice());
     }
     else if (("1".equals(dinerBillDishe.getDiscountType())) || ("2".equals(dinerBillDishe.getDiscountType())))
     {
       price = price + "<del>" + BigDecimalUtil.format(dinerBillDishe.getUnitPrice()) + "/</del>";
       price = price + BigDecimalUtil.format(dinerBillDishe.getRealUnitPrice());
     }
 
     orderNum = orderNum + dinerBillDishe.getUnitNumStr() + dinerBillDishe.getUnitName();
     if (("3".equals(dinerBillDishe.getDishesStatus())) || ("4".equals(dinerBillDishe.getDishesStatus())))
     {
       total = total + "</del>";
       orderNum = orderNum + "</del>";
       price = price + "</del>";
       dishesName = dishesName + "</del>";
     }
 
     String tr = "<tr id=\"" + dishesId + "\" bdId=\"" + dinerBillDishe.getBdId() + "\" isSet=\"" + dinerBillDishe.getIsSet() + "\" dsId=\"" + dinerBillDishe.getDsId() + "\" dishesName=\"" + dinerBillDishe.getDishesName() + "\" " + 
       "discountType=\"" + dinerBillDishe.getDiscountType() + "\" dishesStatus=\"" + dinerBillDishe.getDishesStatus() + "\" " + 
       "notes=\"" + dinerBillDishe.getAllNotes() + "\" unitName=\"" + dinerBillDishe.getUnitName() + "\" unitType=\"" + dinerBillDishe.getUnitType() + "\" " + 
       "orderNum=\"" + dinerBillDishe.getUnitNumStr() + "\" class=\"" + tr_class + "\">" + 
       "<td id=\"seq\" style=\"" + td_style + "\"></td>" + 
       "<td id=\"dishesName\">" + dishesName + "</td>" + 
       " <td id=\"price\">" + price + "</td>" + 
       "<td id=\"orderNum\" style=\"white-space:nowrap;overflow: hidden;\">" + orderNum + "</td>" + 
       "<td id=\"total\">" + total + "</td>" + 
       "</tr>";
     messageMap.put("html", tr);
 
     messageMap.put("bdid", dinerBillDishe.getBdId());
     messageMap.put("estimate", String.valueOf(d.getEstimate() == null ? "" : d.getEstimate()));
     messageMap.put("code", StatusCodeEnum.SUCCESS.getCode());
     messageMap.put("logNotes", logNotes);
 
     messageMap.put("dinerBillDisheId", dinerBillDishe.getBdId());
     messageMap.put("realCost", BigDecimalUtil.format(dinerBill.getPayableCost()).toString());
     messageMap.put("dishesName", d.getDishesName());
     return messageMap;
   }
 
   @Transactional(readOnly=false)
   public HashMap<String, Object> piLiangDianCai(List<TakingDishesVo> dishes, Employee fwy, String orderFlag, LinkedHashMap<String, Object> mapSyn)
     throws MyException
   {
     if ((dishes == null) || (dishes.size() < 1)) {
       throw new MyException("311", "没有可增加的菜肴");
     }
 
     String message = "";
     String restId = "";
     String billId = "";
     for (TakingDishesVo dish : dishes)
     {
       String dishInfo = "";
 
       if (StringUtils.isNotEmpty(dish.getDishId())) {
         dishInfo = dishInfo + "菜肴ID【" + dish.getDishId() + "】";
       }
       if (StringUtils.isNotEmpty(dish.getDishCode())) {
         dishInfo = dishInfo + "菜肴编号【" + dish.getDishCode() + "】";
       }
       if (StringUtils.isNotEmpty(dish.getDishName())) {
         dishInfo = dishInfo + "菜肴名称【" + dish.getDishName() + "】";
       }
 
       Map map = new HashMap();
 
       if ((StringUtils.isEmpty(dish.getDishId())) && 
         (StringUtils.isEmpty(dish.getDishCode())) && 
         (StringUtils.isEmpty(dish.getDishName()))) {
         throw new MyException("312", "点菜失败，无法确定菜肴");
       }
       if (StringUtils.isEmpty(dish.getBillId())) {
         throw new MyException("313", "点菜失败，无法确定账单", "【" + dishInfo + "】");
       }
       if (StringUtils.isEmpty(dish.getRestId())) {
         throw new MyException("314", "点菜失败，无法确定餐厅");
       }
       if (dish.getDishNum() <= 0.0F) {
         throw new MyException("315", "点菜失败，无法确定菜肴数量", "【" + dishInfo + "】的数量:【" + dish.getDishNum() + "】");
       }
 
       if (dish.isUserDefined()) {
         throw new MyException("981", "批量点菜暂不支持自定义菜肴");
       }
 
       List list = null;
       if (StringUtils.isNotEmpty(dish.getDishId())) {
         list = this.disheService.findByDishesIdAndRestIdAndIsStopSell(dish.getDishId(), dish.getRestId());
       }
       else if (StringUtils.isNotEmpty(dish.getDishCode())) {
         list = this.disheService.findByDishesCodeAndRestIdAndIsStopSell(dish.getDishCode(), dish.getRestId());
       }
       else {
         list = this.disheService.findByDishesNameAndRestIdAndIsStopSell(dish.getDishName(), dish.getRestId());
       }
       Dishe d = (list != null) && (list.size() > 0) ? (Dishe)list.get(0) : null;
       if (d == null) {
         throw new MyException("411", "菜肴不可点:" + dishInfo, "【" + dishInfo + "】");
       }
 
       boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
       map = this.dinerBillService.stockCheckForDishes(dish.getRestId(), "", d, dish.getDishNum(), isJudgeDishRaws);
       boolean isJudgeDisgRaws = true;
       if (map == null) {
         throw new MyException("881", "点菜失败，检查原料时发生未知错误");
       }
       if ("0".equalsIgnoreCase((String)map.get("result"))) {
         throw new MyException("415", "点菜失败，" + d.getDishesName() + "原料不足");
       }
       if ("4".equalsIgnoreCase((String)map.get("result"))) {
         throw new MyException("416", "点菜失败，" + d.getDishesName() + "数量不足");
       }
       if ("5".equalsIgnoreCase((String)map.get("result")))
       {
         isJudgeDisgRaws = false;
       }
 
       DinerBillDishe dinerBillDishe = new DinerBillDishe();
 
       int ladu = StringUtils.isNotEmpty(dish.getDishPungentLevelCode()) ? Integer.parseInt(dish.getDishPungentLevelCode()) : 0;
       String avoid = StringUtils.isNotEmpty(dish.getDishAvoidIds()) ? dish.getDishAvoidIds() : "";
       String taste = StringUtils.isNotEmpty(dish.getDishTasteIds()) ? dish.getDishTasteIds() : "";
       String notes = StringUtils.isNotEmpty(dish.getDishCustomNotes()) ? dish.getDishCustomNotes() : "";
       map = this.dinerBillService.saveJiacai(dish.getRestId(), dish.getBillId(), d.getDishesId(), dish.getDishNum(), notes, taste, avoid, Integer.valueOf(ladu), fwy, dinerBillDishe, isJudgeDisgRaws, mapSyn);
       if (map == null) {
         throw new MyException("882", "点菜失败，点菜时发生未知错误");
       }
       if (StatusCodeEnum.LOGIC_ERROR.getCode().equalsIgnoreCase((String)map.get("code"))) {
         throw new MyException("417", "点菜失败，" + d.getDishesName() + "数量不足");
       }
       if (StatusCodeEnum.SUCCESS.getCode().equalsIgnoreCase((String)map.get("code")))
       {
         message = message + "、" + d.getDishesName() + " " + dish.getDishNum();
       }
       else
       {
         throw new MyException("883", "点菜失败，添加菜肴" + d.getDishesName() + "时，发生未知错误");
       }
 
       if ((StringUtils.isNotEmpty(dish.getBillAvoidIds())) || 
         (StringUtils.isNotEmpty(dish.getBillTasteIds())) || 
         (StringUtils.isNotEmpty(dish.getBillPungentLevelCode())) || 
         (StringUtils.isNotEmpty(dish.getBillCustomNotes()))) {
         ladu = StringUtils.isNotEmpty(dish.getDishPungentLevelCode()) ? Integer.parseInt(dish.getDishPungentLevelCode()) : 0;
         avoid = StringUtils.isNotEmpty(dish.getBillAvoidIds()) ? dish.getBillAvoidIds() : "";
         taste = StringUtils.isNotEmpty(dish.getBillTasteIds()) ? dish.getBillTasteIds() : "";
         notes = StringUtils.isNotEmpty(dish.getBillCustomNotes()) ? dish.getBillCustomNotes() : "";
         this.dinerBillService.saveCookingNotes(dish.getBillId(), avoid, taste, ladu, notes, mapSyn);
       }
 
       restId = dish.getRestId();
       billId = dish.getBillId();
     }
 
     HashMap retMap = new HashMap();
     retMap.put("message", message);
     return retMap;
   }
 
   public String formatFloat(String f)
   {
     f = f.replaceAll("\\.00", "").replaceAll("\\.0", "");
     return f;
   }
 
   public Map<String, String> cloudStockCheck(String restId, List<Map<String, Object>> takeoutBills)
   {
     Map messageMap = new HashMap();
     Map<String,Object> dishesMaps = new HashMap();
     String dishSetDiv;
     List<LinkedHashMap> dishesList;
     if ((takeoutBills != null) && (takeoutBills.size() > 0)) {
       for (Map dish : takeoutBills) {
         dishSetDiv = (String)dish.get("dishSetDiv");
         if (DishesTypeEnum.DISHES.getCode().equals(dishSetDiv)) {
           String dishesId = (String)dish.get("dishesId");
           float unitNum = Float.valueOf((String)dish.get("unitNum")).floatValue();
           Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
           if ((dishe.getEstimate() != null) && ((dishe.getEstimate().floatValue() == 0.0F) || (dishe.getEstimate().floatValue() < unitNum)))
           {
             messageMap.put("result", "4");
             messageMap.put("message", dishe.getDishesName() + "已经沽清");
             return messageMap;
           }
           if (dishesMaps.containsKey(dishesId)) {
             Float num = (Float)dishesMaps.get(dishesId);
             num = Float.valueOf(num.floatValue() + unitNum);
             dishesMaps.put(dishesId, num);
           } else {
             dishesMaps.put(dishesId, Float.valueOf(unitNum));
           }
         } else {
           String dishesSetId = (String)dish.get("dsId");
           float dishesSetNum = Float.valueOf((String)dish.get("unitNum")).floatValue();
           DishesSet dishe = (DishesSet)this.dishesSetService.getOne(dishesSetId);
           if ((dishe.getEstimate() != null) && ((dishe.getEstimate().floatValue() == 0.0F) || (dishe.getEstimate().floatValue() < dishesSetNum)))
           {
             messageMap.put("result", "4");
             messageMap.put("message", dishe.getDsName() + "已经沽清");
             return messageMap;
           }
           dishesList = (List)dish.get("dsDishesList");
           for (LinkedHashMap linkedHashMap : dishesList) {
             String dishesId = (String)linkedHashMap.get("dishesId");
             float unitNum = Float.valueOf((String)linkedHashMap.get("unitNum")).floatValue() * dishesSetNum;
             if (dishesMaps.containsKey(dishesId)) {
               Float num = (Float)dishesMaps.get(dishesId);
               num = Float.valueOf(num.floatValue() + unitNum);
               dishesMaps.put(dishesId, num);
             } else {
               dishesMaps.put(dishesId, Float.valueOf(unitNum));
             }
           }
         }
       }
     }
 
     Map<String,Float> dishesRawMaps = new HashMap();
     List<DishesRaw> dishesRaws;
     for (String dishesId : dishesMaps.keySet()) {
       Float dishesNum = (Float)dishesMaps.get(dishesId);
       dishesRaws = this.dishesRawService.getByRestIdAndDishesId(restId, dishesId);
       for (DishesRaw dishesRaw : dishesRaws) {
         String rmId = dishesRaw.getRawMaterial().getRmId();
         Float useCounts = Float.valueOf(dishesRaw.getUseCount().floatValue() * dishesNum.floatValue());
         if (dishesRawMaps.containsKey(rmId)) {
           Float counts = (Float)dishesRawMaps.get(rmId);
           counts = Float.valueOf(counts.floatValue() + useCounts.floatValue());
           dishesRawMaps.put(rmId, counts);
         } else {
           dishesRawMaps.put(rmId, useCounts);
         }
       }
     }
 
     StringBuffer sb0 = new StringBuffer();
     StringBuffer sb1 = new StringBuffer();
 
     for (String rmId : dishesRawMaps.keySet()) {
       Float useCounts = (Float)dishesRawMaps.get(rmId);
       RawMaterial rawMaterial = (RawMaterial)this.rawMaterialDao.findOne(rmId);
       Float stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() - useCounts.floatValue());
       if (stockCount.floatValue() < 0.0F) {
         if (!sb0.toString().contains(rawMaterial.getRmName()))
           sb0.append(rawMaterial.getRmName() + ",");
       } else {
         if ((stockCount.floatValue() > rawMaterial.getWarnCount().floatValue()) || 
           (sb1.toString().contains(rawMaterial.getRmName()))) continue;
         sb1.append(rawMaterial.getRmName() + ",");
       }
 
     }
 
     if (!StringUtils.isEmpty(sb0)) {
       messageMap.put("result", "0");
       messageMap.put("message", sb0.substring(0, sb0.length() - 1) + "原料库存不足");
       return messageMap;
     }
 
     if (!StringUtils.isEmpty(sb1)) {
       messageMap.put("result", "1");
       messageMap.put("message", sb1.substring(0, sb1.length() - 1) + "原料库存预警，请及时补充原料");
       return messageMap;
     }
 
     messageMap.put("result", "2");
     return messageMap;
   }
 
   public Map<String, String> stockCheckForDishes(String restId, String bdId, Dishe dishe, float dishesNum, boolean isJudgeDishRaws)
   {
     Map messageMap = new HashMap();
 
     boolean isEstimateEnough = true;
     if (StringUtils.isNotEmpty(bdId)) {
       DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
       isEstimateEnough = isEstimateEnoughForDishesBill(restId, dishe, dishesNum+"", dinerBillDishe.getUnitNum());
       dishesNum -= dinerBillDishe.getUnitNum();
     } else {
       isEstimateEnough = isEstimateEnoughForDishes(restId, dishe, dishesNum);
     }
     if (!isEstimateEnough)
     {
       messageMap.put("result", "4");
       messageMap.put("estimate", String.valueOf(dishe.getEstimate() == null ? "" : dishe.getEstimate()));
       messageMap.put("message", dishe.getDishesName() + "数量不足");
       return messageMap;
     }
     if (!isJudgeDishRaws)
     {
       messageMap.put("result", "5");
       messageMap.put("message", "不判断原料了");
       return messageMap;
     }
     List<DishesRaw> dishesRaws = this.dishesRawService.getByRestIdAndDishesId(restId, dishe.getDishesId());
     if ((dishesRaws == null) || (dishesRaws.size() == 0))
     {
       messageMap.put("result", "5");
       messageMap.put("message", "不判断原料了");
       return messageMap;
     }
 
     StringBuffer sb0 = new StringBuffer();
     StringBuffer sb1 = new StringBuffer();
     for (DishesRaw dishesRaw : dishesRaws)
     {
       RawMaterial rawMaterial = dishesRaw.getRawMaterial();
       if (rawMaterial.getRmId() != null)
       {
         Float stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() - dishesRaw.getUseCount().floatValue() * dishesNum);
         if (stockCount.floatValue() < 0.0F) {
           stockCount = Float.valueOf(dishesRaw.getUseCount().floatValue() * dishesNum);
           messageMap.put("result", "0");
           sb0.append(rawMaterial.getRmName() + ",");
         } else if (stockCount.floatValue() <= rawMaterial.getWarnCount().floatValue()) {
           if (messageMap.containsKey("result")) {
             if (!((String)messageMap.get("result")).equals("0")) {
               messageMap.put("result", "1");
               sb1.append(rawMaterial.getRmName() + ",");
             }
           } else {
             messageMap.put("result", "1");
             sb1.append(rawMaterial.getRmName() + ",");
           }
         }
       } else {
         messageMap.put("result", "3");
       }
       rawMaterial = null;
       dishesRaw = null;
     }
     dishesRaws = null;
 
     if (messageMap.containsKey("result")) {
       if (((String)messageMap.get("result")).equals("0"))
         messageMap.put("message", sb0.substring(0, sb0.length() - 1) + "原料库存不足");
       else if (((String)messageMap.get("result")).equals("1"))
         messageMap.put("message", sb1 + "原料库存预警，请及时补充原料");
     }
     else {
       messageMap.put("result", "2");
     }
 
     return messageMap;
   }
 
   public Map<String, String> stockUpdateForDishes(String restId, String bdId, Dishe dishe, float dishesNum, boolean isJudgeDishRaws, LinkedHashMap<String, Object> map)
   {
     Map messageMap = new HashMap();
 
     if (dishe.getEstimate() != null)
     {
       estimate(Float.valueOf(dishe.getEstimate().floatValue() - dishesNum), dishe, map);
     }
     if (!isJudgeDishRaws)
     {
       messageMap.put("result", "2");
       messageMap.put("message", "添加成功");
       return messageMap;
     }
     if (StringUtils.isNotEmpty(bdId)) {
       DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
       dishesNum -= dinerBillDishe.getUnitNum();
     }
     StringBuffer sb = new StringBuffer();
     sb.append("修改了属性：");
 
     List<DishesRaw> dishesRaws = this.dishesRawService.findByRestIdAndDishe(restId, dishe);
 
     StringBuffer sb1 = new StringBuffer();
     boolean isRecordRm = false;
     for (DishesRaw dishesRaw : dishesRaws) {
       isRecordRm = true;
 
       RawMaterial rawMaterial = dishesRaw.getRawMaterial();
       if (rawMaterial.getRmId() == null)
         continue;
       Float stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() - dishesRaw.getUseCount().floatValue() * dishesNum);
       sb.append(rawMaterial.getRmName() + "数量从" + rawMaterial.getStockCount() + rawMaterial.getDishesUnit().getName() + "改为" + stockCount + rawMaterial.getDishesUnit().getName() + ",");
       rawMaterial.setStockCount(stockCount);
       this.rawMaterialService.save(rawMaterial);
       map.put(rawMaterial.getRmId() + "sufd_" + OperationTypeEnum.UPDATE.getCode(), rawMaterial);
       if (stockCount.floatValue() <= rawMaterial.getWarnCount().floatValue()) {
         messageMap.put("result", "1");
         sb1.append(rawMaterial.getRmName() + ",");
       }
     }
 
     sb.deleteCharAt(sb.length() - 1);
     if (isRecordRm)
     {
//       messageMap.put("rmDesc", sb);
     }
     if (messageMap.containsKey("result")) {
       if (((String)messageMap.get("result")).equals("1"))
         messageMap.put("message", sb1 + "原料库存预警，请及时补充原料");
     }
     else {
       messageMap.put("result", "2");
       messageMap.put("message", "添加成功");
     }
     return messageMap;
   }
 
   public synchronized void estimate(Float estimateNum, Dishe d, LinkedHashMap<String, Object> map)
   {
     if (d.getEstimate() != null) {
       d.setEstimate(estimateNum);
       this.disheService.save(d);
 
       map.put(d.getDishesId() + "e_" + OperationTypeEnum.UPDATE.getCode(), d);
     }
   }
 
   public synchronized void estimateDishesSet(Float estimateNum, DishesSet ds)
   {
     if (ds.getEstimate() != null) {
       ds.setEstimate(estimateNum);
       this.dishesSetService.save(ds);
     }
   }
 
   @Transactional(readOnly=false)
   public void saveZengcai(String restId, String billId, DinerBillDishe dinerBillDishe, LinkedHashMap<String, Object> map)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     dinerBillDishe.setDiscountType(DiscountTypeEnum.GIVE.getCode());
     this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, null);
 
     String giveOper = dinerBillDishe.getUpdateEmployee() != null ? dinerBillDishe.getUpdateEmployee().getEmpId() : "";
     dinerBillDishe.setGiveOperator(giveOper);
     dinerBillDishe.setGiveTime(dinerBillDishe.getUpdateTime());
 
     this.dinerBillDisheService.save(dinerBillDishe);
 
     map.put(dinerBillDishe.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     String logNotes = "赠菜操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
 
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.GIVE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.GIVE_DISH, logNotes, map);
   }
 
   @Transactional(readOnly=false)
   public void saveCancelZengcai(String restId, String billId, DinerBillDishe dinerBillDishe, LinkedHashMap<String, Object> map)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
 
     Dishe d = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
     dinerBillDishe.setDiscountType(null);
     dinerBillDishe.setUnitPrice(d.getPrice());
     dinerBillDishe.setDiscount(Integer.valueOf(100));
     dinerBillDishe.setRealUnitPrice(d.getPrice());
     dinerBillDishe.setIsRulingPrice(d.getIsRulingPrice());
     this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, d);
 
     dinerBillDishe.setGiveOperator(null);
     dinerBillDishe.setGiveTime(null);
 
     this.dinerBillDisheService.save(dinerBillDishe);
 
     map.put(dinerBillDishe.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     String logNotes = "取消赠菜操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.CANCEL_GIVE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.CANCEL_GIVE_DISH, logNotes, map);
   }
 
   @Transactional(readOnly=false)
   public void saveCancelZengcai(String restId, String billId, DinerBillDishesSet dinerBillDishesSet, LinkedHashMap<String, Object> map)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
 
     DishesSet d = (DishesSet)this.dishesSetService.getOne(dinerBillDishesSet.getDsId());
     dinerBillDishesSet.setDiscountType(null);
     dinerBillDishesSet.setUnitPrice(d.getPrice());
     dinerBillDishesSet.setDiscount(Integer.valueOf(100));
     dinerBillDishesSet.setRealUnitPrice(d.getPrice());
     this.billService.calculatorSingleDishesSet(restId, dinerBill, dinerBillDishesSet, d);
 
     dinerBillDishesSet.setGiveOperator(null);
     dinerBillDishesSet.setGiveTime(null);
 
     this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
     map.put(dinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     String logNotes = "取消赠菜操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishesSet.getDsName() + "，数量：" + dinerBillDishesSet.getUnitNum();
     saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.CANCEL_GIVE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.CANCEL_GIVE_DISH, logNotes, map);
   }
 
   @Transactional(readOnly=false)
   public void saveZengcai(String restId, String billId, DinerBillDishesSet dinerBillDishesSet, LinkedHashMap<String, Object> map)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     dinerBillDishesSet.setDiscountType(DiscountTypeEnum.GIVE.getCode());
     this.billService.calculatorSingleDishesSet(restId, dinerBill, dinerBillDishesSet, null);
 
     String giveOper = dinerBillDishesSet.getUpdateEmployee() != null ? dinerBillDishesSet.getUpdateEmployee().getEmpId() : "";
     dinerBillDishesSet.setGiveOperator(giveOper);
     dinerBillDishesSet.setGiveTime(dinerBillDishesSet.getUpdateTime());
 
     this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
     map.put(dinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     String logNotes = "赠送套餐操作";
     logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsName() + "，数量：" + dinerBillDishesSet.getUnitNum();
 
     saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.GIVE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.GIVE_DISH, logNotes, map);
   }
 
   @Transactional(readOnly=false)
   public void saveOrderJiacai(String orderId, String dishesId, String dishesNote, String dishesTasteIdArray, String dishesAvoidfoodIdArray, Integer dishesPungentLevel, Employee user, String restId, float unitNum, LinkedHashMap<String, Object> map)
   {
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
 
     Dishe d = (Dishe)this.disheService.getOne(dishesId);
     if (d == null)
     {
       return;
     }
     OrderBillDishe orderBillDishe = new OrderBillDishe();
     orderBillDishe.setDishe(d);
     orderBillDishe.setTableOrder(tableOrder);
     orderBillDishe.setDishesName(d.getDishesName());
     orderBillDishe.setUnitPrice(d.getPrice());
 
     orderBillDishe.setRealUnitPrice(this.specialDisheServie.getSpecialPrice(d, restId));
     orderBillDishe.setUnitNum(unitNum);
     orderBillDishe.setOriCost(orderBillDishe.getRealUnitPrice().multiply(new BigDecimal(orderBillDishe.getUnitNum())));
     orderBillDishe.setRealCost(orderBillDishe.getRealUnitPrice().multiply(new BigDecimal(orderBillDishe.getUnitNum())));
     orderBillDishe.setUnitId(d.getDishesUnit().getUnitId());
     orderBillDishe.setUnitName(d.getDishesUnit().getName());
     orderBillDishe.setDiscount(100);
     orderBillDishe.setOrderTime(DateProvider.DEFAULT.getDate());
     orderBillDishe.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
     orderBillDishe.setIsRulingPrice(d.getIsRulingPrice());
 
     if (dishesNote != null) {
       orderBillDishe.setNotes(dishesNote);
     }
 
     if (dishesTasteIdArray != null) {
       orderBillDishe.setTasteIdArray(dishesTasteIdArray);
       String tasteNameArray = this.dishesTasteService.findNameArray(dishesTasteIdArray);
       orderBillDishe.setTasteNameArray(tasteNameArray);
     }
 
     if (dishesAvoidfoodIdArray != null) {
       orderBillDishe.setAvoidfoodIdArray(dishesAvoidfoodIdArray);
       String avoidfoodNameArray = this.dishesAvoidfoodService.findNameArray(dishesAvoidfoodIdArray);
       orderBillDishe.setAvoidfoodNameArray(avoidfoodNameArray);
     }
 
     if (dishesPungentLevel != null) {
       orderBillDishe.setPungentLevel(dishesPungentLevel.intValue());
     }
 
     this.orderBillDishesService.save(orderBillDishe);
 
     map.put(orderBillDishe.getBdId() + "_" + OperationTypeEnum.CREATE.getCode(), orderBillDishe);
 
     if (tableOrder.getOriCost() == null)
     {
       tableOrder.setOriCost(BigDecimal.ZERO);
     }
     BigDecimal paymentAll = tableOrder.getOriCost();
     paymentAll = paymentAll.add(orderBillDishe.getOriCost());
     tableOrder.setOriCost(paymentAll);
     this.tableOrderService.save(tableOrder);
 
     map.put(tableOrder.getOrderId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
   }
 
   @Transactional(readOnly=false)
   public void saveOrderDishesSet(String orderId, String dsId, String dishesNote, String dishesTasteIdArray, String dishesAvoidfoodIdArray, Integer dishesPungentLevel, Employee user, String restId, String dsDishesDesc, float unitNum, LinkedHashMap<String, Object> map)
   {
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
     DishesSet d = (DishesSet)this.dishesSetService.getOne(dsId);
     if (d == null)
     {
       return;
     }
     OrderBillDishesSet orderBillDishesSet = new OrderBillDishesSet();
     orderBillDishesSet.setDishesSet(d);
     orderBillDishesSet.setTableOrder(tableOrder);
     orderBillDishesSet.setDsName(d.getDsName());
     orderBillDishesSet.setUnitPrice(d.getPrice());
     orderBillDishesSet.setRealUnitPrice(d.getPrice());
     orderBillDishesSet.setUnitNum(unitNum);
     orderBillDishesSet.setOriCost(orderBillDishesSet.getRealUnitPrice().multiply(new BigDecimal(orderBillDishesSet.getUnitNum())));
     orderBillDishesSet.setRealCost(orderBillDishesSet.getRealUnitPrice().multiply(new BigDecimal(orderBillDishesSet.getUnitNum())));
     orderBillDishesSet.setUnitId(d.getDishesUnit().getUnitId());
     orderBillDishesSet.setUnitName(d.getDishesUnit().getName());
     orderBillDishesSet.setDiscount(100);
     orderBillDishesSet.setOrderTime(DateProvider.DEFAULT.getDate());
     orderBillDishesSet.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
     orderBillDishesSet.setDsDishesDesc(dsDishesDesc);
 
     if (dishesNote != null) {
       orderBillDishesSet.setNotes(dishesNote);
     }
 
     if (dishesTasteIdArray != null) {
       orderBillDishesSet.setTasteIdArray(dishesTasteIdArray);
       String tasteNameArray = this.dishesTasteService.findNameArray(dishesTasteIdArray);
       orderBillDishesSet.setTasteNameArray(tasteNameArray);
     }
 
     if (dishesAvoidfoodIdArray != null) {
       orderBillDishesSet.setAvoidfoodIdArray(dishesAvoidfoodIdArray);
       String avoidfoodNameArray = this.dishesAvoidfoodService.findNameArray(dishesAvoidfoodIdArray);
       orderBillDishesSet.setAvoidfoodNameArray(avoidfoodNameArray);
     }
 
     if (dishesPungentLevel != null) {
       orderBillDishesSet.setPungentLevel(dishesPungentLevel.intValue());
     }
 
     this.orderBillDishesSetService.save(orderBillDishesSet);
 
     map.put(orderBillDishesSet.getBdsId() + "_" + OperationTypeEnum.CREATE.getCode(), orderBillDishesSet);
 
     if (tableOrder.getOriCost() == null)
     {
       tableOrder.setOriCost(BigDecimal.ZERO);
     }
     BigDecimal paymentAll = tableOrder.getOriCost();
     paymentAll = paymentAll.add(orderBillDishesSet.getOriCost());
     tableOrder.setOriCost(paymentAll);
     this.tableOrderService.save(tableOrder);
 
     map.put(tableOrder.getOrderId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
   }
 
   @Transactional(readOnly=false)
   public void saveUserDefinedDish(String restId, String billId, String dishName, String notes, String dishCatagoryId, String unitId, BigDecimal price, Employee user, String isOnsale, LinkedHashMap<String, Object> map)
   {
     Dishe d = new Dishe();
     d.setDishesName(dishName);
     d.setNotes(notes);
     d.setDishesCategory((DishesCategory)this.dishesCategoryService.getOne(dishCatagoryId));
     d.setDishesUnit((DishesUnit)this.dishesUnitService.getOne(unitId));
     d.setPrice(price);
     d.setIsUserDefined(TrueFalseEnum.TRUE.getCode());
     d.setIsOnsale(isOnsale);
     d.setIsAddMinCharge(TrueFalseEnum.FALSE.getCode());
     this.disheService.save(d);
 
     map.put(d.getDishesId() + "_" + OperationTypeEnum.CREATE.getCode(), d);
 
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
 
     DinerBillDishe dinerBillDishe = new DinerBillDishe();
     dinerBillDishe.setDishesId(d.getDishesId());
     dinerBillDishe.setDinerBill(dinerBill);
     dinerBillDishe.setDishesCategory(d.getDishesCategory());
     dinerBillDishe.setUnitId(unitId);
     dinerBillDishe.setUnitName(d.getDishesUnit().getName());
     dinerBillDishe.setUnitType(d.getDishesUnit().getUnitType());
     dinerBillDishe.setBillNo(dinerBill.getBillNo());
     dinerBillDishe.setDishesName(dishName);
     dinerBillDishe.setUnitPrice(price);
     dinerBillDishe.setUnitNum(1.0F);
     dinerBillDishe.setOrderEmp(user);
     dinerBillDishe.setDishesStatus(DishesStatusEnum.UNXIADAN.getCode());
     dinerBillDishe.setDiscount(Integer.valueOf(100));
     dinerBillDishe.setRealUnitPrice(price);
     dinerBillDishe.setIsUserDefined(TrueFalseEnum.TRUE.getCode());
     dinerBillDishe.setPungentLevel(0);
     dinerBillDishe.setOrderTime(DateProvider.DEFAULT.getDate());
     dinerBillDishe.setNotes(notes);
     this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, d);
     this.dinerBillDisheService.save(dinerBillDishe);
 
     map.put(dinerBillDishe.getBdId() + "_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishe);
 
     if (dinerBill.getBillStatus().equals(BillStatusEnum.PLACE_ORDER.getCode()))
     {
       dinerBill.setBillStatus(BillStatusEnum.SOME_PLACE_ORDER.getCode());
     }
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     String logNotes = "自定义菜操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
 
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.ORDER_DISH, logNotes, map);
   }
 
   @Transactional(readOnly=false)
   public void saveOrderUserDefinedDish(String orderId, String dishName, String notes, String dishCatagoryId, String unitId, BigDecimal price, Employee user, String isOnsale, LinkedHashMap<String, Object> map)
   {
     Dishe d = new Dishe();
     d.setDishesName(dishName);
     d.setNotes(notes);
     d.setDishesCategory((DishesCategory)this.dishesCategoryService.getOne(dishCatagoryId));
     d.setDishesUnit((DishesUnit)this.dishesUnitService.getOne(unitId));
     d.setPrice(price);
     d.setIsUserDefined(TrueFalseEnum.TRUE.getCode());
     d.setIsOnsale(isOnsale);
     d.setIsAddMinCharge(TrueFalseEnum.FALSE.getCode());
     this.disheService.save(d);
 
     map.put(d.getDishesId() + "_" + OperationTypeEnum.CREATE.getCode(), d);
 
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
 
     OrderBillDishe orderBillDishe = new OrderBillDishe();
     orderBillDishe.setDishe(d);
     orderBillDishe.setTableOrder(tableOrder);
     orderBillDishe.setDishesName(d.getDishesName());
     orderBillDishe.setUnitId(unitId);
     orderBillDishe.setUnitName(d.getDishesUnit().getName());
     orderBillDishe.setUnitPrice(d.getPrice());
     orderBillDishe.setRealUnitPrice(d.getPrice());
     orderBillDishe.setUnitNum(1.0F);
     orderBillDishe.setRealCost(d.getPrice());
     orderBillDishe.setOriCost(d.getPrice());
     orderBillDishe.setDiscount(100);
     orderBillDishe.setOrderTime(DateProvider.DEFAULT.getDate());
     orderBillDishe.setIsUserDefined(TrueFalseEnum.TRUE.getCode());
     orderBillDishe.setIsRulingPrice(TrueFalseEnum.FALSE.getCode());
 
     this.orderBillDishesService.save(orderBillDishe);
 
     map.put(orderBillDishe.getBdId() + "_" + OperationTypeEnum.CREATE.getCode(), orderBillDishe);
   }
 
   @Transactional(readOnly=false)
   public DinerBillDishe saveDishCuiCai(String restId, String id, LinkedHashMap<String, Object> map)
   {
     DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(id);
     dinerBillDishe.setUrgeNum(dinerBillDishe.getUrgeNum() + 1);
     this.dinerBillDisheService.save(dinerBillDishe);
 
     map.put(dinerBillDishe.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
 
     String logNotes = "催菜操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，催菜次数：" + dinerBillDishe.getUrgeNum();
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.CUI_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.CUI_DISH, logNotes, map);
 
     return dinerBillDishe;
   }
 
   @Transactional(readOnly=false)
   public DinerBillDishesSet saveDishsSetCuiCai(String restId, String id, LinkedHashMap<String, Object> map)
   {
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(id);
     dinerBillDishesSet.setUrgeNum(dinerBillDishesSet.getUrgeNum() + 1);
     this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
     map.put(dinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
 
     String logNotes = "催菜操作";
     logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsName() + "，催菜次数：" + dinerBillDishesSet.getUrgeNum();
     saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.CUI_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.CUI_DISH, logNotes, map);
 
     return dinerBillDishesSet;
   }
 
   @Transactional(readOnly=false)
   public void saveZhuantai(String restId, DinerBill dinerBill, LinkedHashMap<String, Object> map)
   {
     String oldTabId = dinerBill.getOldTabId();
     Table newTab = dinerBill.getTable();
 
     Table oldTab = null;
     if ((oldTabId != null) && (!oldTabId.isEmpty()) && 
       (!oldTabId.equals(newTab.getTabId())))
     {
       oldTab = (Table)this.tableService.getOne(oldTabId);
       oldTab.setDinnerStatus(DinnerStatusEnum.IDLE.getCode());
       this.tableService.save(oldTab);
 
       map.put(oldTab.getTabId() + "_" + OperationTypeEnum.UPDATE.getCode(), oldTab);
 
       newTab = (Table)this.tableService.getOne(newTab.getTabId());
       newTab.setDinnerStatus(DinnerStatusEnum.USING.getCode());
       this.tableService.save(newTab);
 
       map.put(newTab.getTabId() + "_" + OperationTypeEnum.UPDATE.getCode(), newTab);
 
       dinerBill.setTabNo(newTab.getTabNo());
       dinerBill.setTable(newTab);
 
       this.billService.calculator(restId, dinerBill);
       this.self.save(dinerBill);
 
       map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
       TableBillRelation tableBillRelation = dinerBill.getTableBillRelation();
       tableBillRelation.setTable(newTab);
       tableBillRelation.setPeopleNum(dinerBill.getPeopleNum());
       this.tableBillRelationService.save(tableBillRelation);
 
       map.put(tableBillRelation.getTabBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableBillRelation);
 
       String logNotes = "转台操作";
       logNotes = logNotes + "，从台号:" + oldTab.getTabNo() + "，转到：" + newTab.getTabNo();
       saveBusiLog(restId, dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.CHANGE_TABLE, logNotes);
       saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.CHANGE_TABLE, logNotes, map);
       try
       {
         this.zhuantaiPrinterService.printZhuantai(restId, dinerBill, oldTab.getTabName(), newTab.getTabName());
       }
       catch (PrinterException e) {
         e.printStackTrace();
       }
     }
   }
 
   @Transactional(readOnly=false)
   public void saveBingtai(String restId, DinerBill dinerBill, LinkedHashMap<String, Object> map)
     throws IOException, ClassNotFoundException
   {
     if ((BillStatusEnum.NOT_PLACE_ORDER.getCode().equals(dinerBill.getBillStatus())) || 
       (BillStatusEnum.PLACE_ORDER.getCode().equals(dinerBill.getBillStatus())) || 
       (BillStatusEnum.SOME_PLACE_ORDER.getCode().equals(dinerBill.getBillStatus())))
     {
       String oldTabId = dinerBill.getOldTabId();
       String oldBillStatus = dinerBill.getBillStatus();
       Table newTab = dinerBill.getTable();
 
       Table oldTab = null;
       if ((oldTabId != null) && (!oldTabId.isEmpty()) && 
         (!oldTabId.equals(newTab.getTabId())))
       {
         oldTab = (Table)this.tableService.getOne(oldTabId);
         oldTab.setDinnerStatus(DinnerStatusEnum.IDLE.getCode());
         this.tableService.save(oldTab);
 
         map.put(oldTab.getTabId() + "_" + OperationTypeEnum.UPDATE.getCode(), oldTab);
 
         TableBillRelation cr = this.tableService.getLastedTableNormalBillRelation(oldTab);
         if (cr != null)
         {
           String crId = cr.getTabBillId();
           this.tableBillRelationService.delete(crId);
 
           map.put(crId + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_table_bill_relation,tab_bill_id," + crId);
         }
 
         newTab = (Table)this.tableService.getOne(newTab.getTabId());
         TableBillRelation tableBillRelation = this.tableService.getLastedTableNormalBillRelation(newTab);
         DinerBill newBill = tableBillRelation.getDinerBill();
 
         List<DinerBillDishe> oldDishes = dinerBill.getDinerBillDishes();
         BeanUtils u = new BeanUtils();
         DinerBillDishe n;
         for (DinerBillDishe e : oldDishes)
         {
           n = (DinerBillDishe)BeanUtils.deepCopy(e);
           n.setBdId(null);
           n.setBillNo(newBill.getBillNo());
           n.setDinerBill(newBill);
           this.dinerBillDisheService.save(n);
 
           map.put(n.getBdId() + "_" + OperationTypeEnum.CREATE.getCode(), n);
         }
 
         List<DinerBillDishesSet> oldDishessSet = dinerBill.getDinerBillDishesSets();
         for (DinerBillDishesSet e : oldDishessSet) {
           DinerBillDishesSet set = (DinerBillDishesSet)BeanUtils.deepCopy(e);
           set.setBdsId(null);
           set.setBillNo(newBill.getBillNo());
           set.setDinerBill(newBill);
           this.dinerBillDishesSetService.save(set);
 
           map.put(set.getBdsId() + "_" + OperationTypeEnum.CREATE.getCode(), set);
         }
 
         dinerBill.setBillStatus(BillStatusEnum.BING_TAI.getCode());
         dinerBill.setTable(oldTab);
         this.self.save(dinerBill);
 
         map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
         Integer oldPeopleNum = dinerBill.getPeopleNum();
         Integer newPeopleNum = newBill.getPeopleNum();
         Integer peopleNum = Integer.valueOf(0);
         if ((oldPeopleNum == null) && (newPeopleNum == null))
         {
           peopleNum = null;
           newBill.setPeopleNum(null);
         }
         else if ((oldPeopleNum == null) && (newPeopleNum != null))
         {
           peopleNum = newPeopleNum;
         }
         else if ((oldPeopleNum != null) && (newPeopleNum == null))
         {
           peopleNum = oldPeopleNum;
         }
         else
         {
           peopleNum = Integer.valueOf(oldPeopleNum.intValue() + newPeopleNum.intValue());
         }
         newBill.setPeopleNum(peopleNum);
 
         newBill = setAllNotes(dinerBill, newBill);
 
         newBill.setTable(newTab);
 
         String newBillStatus = newBill.getBillStatus();
         if ((BillStatusEnum.NOT_PLACE_ORDER.getCode().equals(oldBillStatus)) && (BillStatusEnum.NOT_PLACE_ORDER.getCode().equals(newBillStatus)))
           newBill.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
         else if ((BillStatusEnum.NOT_PLACE_ORDER.getCode().equals(oldBillStatus)) && (BillStatusEnum.PLACE_ORDER.getCode().equals(newBillStatus)))
           newBill.setBillStatus(BillStatusEnum.SOME_PLACE_ORDER.getCode());
         else if ((BillStatusEnum.PLACE_ORDER.getCode().equals(oldBillStatus)) && (BillStatusEnum.NOT_PLACE_ORDER.getCode().equals(newBillStatus)))
           newBill.setBillStatus(BillStatusEnum.SOME_PLACE_ORDER.getCode());
         else if ((BillStatusEnum.PLACE_ORDER.getCode().equals(oldBillStatus)) && (BillStatusEnum.PLACE_ORDER.getCode().equals(newBillStatus)))
           newBill.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
         else {
           newBill.setBillStatus(BillStatusEnum.SOME_PLACE_ORDER.getCode());
         }
 
         TableBillRelation ct = this.tableService.getLastedTableNormalBillRelation(newTab);
         if (ct != null)
         {
           ct.setPeopleNum(peopleNum);
           this.tableBillRelationService.save(ct);
         }
         this.billService.calculator(restId, newBill);
         this.self.save(newBill);
 
         map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
         BillCombine billCombine = new BillCombine();
         billCombine.setBillNo(newBill.getBillNo());
         billCombine.setDinerBill(newBill);
         billCombine.setCbillId(dinerBill.getBillId());
         billCombine.setCbillNo(dinerBill.getBillNo());
         this.billCombineService.save(billCombine);
 
         map.put(billCombine.getCbcId() + "_" + OperationTypeEnum.CREATE.getCode(), billCombine);
 
         String logNotes = "并台操作";
         logNotes = logNotes + "，台号:" + oldTab.getTabNo() + "，合并到：" + newTab.getTabNo();
         saveBusiLog(restId, dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.MERGE_TABLE, logNotes);
         saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.MERGE_TABLE, logNotes, map);
         try
         {
           this.bingtaiPrinterService.printBingtai(restId, newBill, oldTab.getTabName(), oldTab.getTabNo(), dinerBill.getBillNo(), newTab.getTabName());
         } catch (PrinterException e1) {
           e1.printStackTrace();
         }
       }
     }
   }
 
   private List<String> mergeList(List<String> list1, List<String> list2)
   {
     ArrayList list = new ArrayList();
     for (String string : list1) {
       if (!list.contains(string)) {
         list.add(string);
       }
     }
     for (String string : list2) {
       if (!list.contains(string)) {
         list.add(string);
       }
     }
     return list;
   }
 
   private DinerBill setAllNotes(DinerBill oldBill, DinerBill newBill)
   {
     List oldAvoidfoodIdList = oldBill.getAvoidfoodIdList();
     List newAvoidfoodIdList = newBill.getAvoidfoodIdList();
 
     List oldAvoidfoodNameList = oldBill.getAvoidfoodNameList();
     List newAvoidfoodNameList = newBill.getAvoidfoodNameList();
 
     List oldTasteIdList = oldBill.getTasteIdList();
     List newTasteIdList = newBill.getTasteIdList();
 
     List oldTasteNameList = oldBill.getTasteNameList();
     List newTasteNameList = newBill.getTasteNameList();
 
     Integer oldPungentLevel = Integer.valueOf(oldBill.getPungentLevel());
     Integer newPungentLevel = Integer.valueOf(newBill.getPungentLevel());
 
     String oldNotes = oldBill.getNotes();
     String newNotes = newBill.getNotes();
 
     String avoidfoodIdArray = "";
     String avoidfoodNameArray = "";
     String tasteIdArray = "";
     String tasteNameArray = "";
     Integer pungentLevel = Integer.valueOf(0);
     String notes = "";
     if ((oldAvoidfoodIdList != null) && (newAvoidfoodIdList != null)) {
       List list = mergeList(newAvoidfoodIdList, oldAvoidfoodIdList);
       avoidfoodIdArray = Collections3.convertToString(list, ",");
     }
     else if (oldAvoidfoodIdList != null) {
       avoidfoodIdArray = Collections3.convertToString(oldAvoidfoodIdList, ",");
     } else if (newAvoidfoodIdList != null) {
       avoidfoodIdArray = Collections3.convertToString(newAvoidfoodIdList, ",");
     }
 
     if ((oldAvoidfoodNameList != null) && (newAvoidfoodNameList != null)) {
       List list = mergeList(newAvoidfoodNameList, oldAvoidfoodNameList);
       avoidfoodNameArray = Collections3.convertToString(list, ",");
     }
     else if (oldAvoidfoodNameList != null) {
       avoidfoodNameArray = Collections3.convertToString(oldAvoidfoodNameList, ",");
     } else if (newAvoidfoodNameList != null) {
       avoidfoodNameArray = Collections3.convertToString(newAvoidfoodNameList, ",");
     }
 
     if ((oldTasteIdList != null) && (newTasteIdList != null)) {
       List list = mergeList(newTasteIdList, oldTasteIdList);
       tasteIdArray = Collections3.convertToString(list, ",");
     }
     else if (oldTasteIdList != null) {
       tasteIdArray = Collections3.convertToString(oldTasteIdList, ",");
     } else if (newTasteIdList != null) {
       tasteIdArray = Collections3.convertToString(newTasteIdList, ",");
     }
 
     if ((oldTasteNameList != null) && (newTasteNameList != null)) {
       List list = mergeList(newTasteNameList, oldTasteNameList);
       tasteNameArray = Collections3.convertToString(list, ",");
     }
     else if (oldTasteNameList != null) {
       tasteNameArray = Collections3.convertToString(oldTasteNameList, ",");
     } else if (newTasteNameList != null) {
       tasteNameArray = Collections3.convertToString(newTasteNameList, ",");
     }
 
     if ((oldPungentLevel != null) && (oldPungentLevel.intValue() != 0) && (newPungentLevel != null) && (newPungentLevel.intValue() != 0)) {
       if (oldPungentLevel.intValue() < newPungentLevel.intValue())
         pungentLevel = oldPungentLevel;
       else {
         pungentLevel = newPungentLevel;
       }
     }
     else if ((oldPungentLevel != null) && (oldPungentLevel.intValue() != 0))
       pungentLevel = oldPungentLevel;
     else if ((newPungentLevel != null) && (newPungentLevel.intValue() != 0)) {
       pungentLevel = newPungentLevel;
     }
 
     if ((oldNotes != null) && (!oldNotes.trim().equals("")) && (newNotes != null) && (!newNotes.trim().equals(""))) {
       notes = oldNotes + "," + newNotes;
     }
     else if ((oldNotes != null) && (!oldNotes.trim().equals("")))
       notes = oldNotes;
     else if ((newNotes != null) && (!newNotes.trim().equals(""))) {
       notes = newNotes;
     }
 
     newBill.setAvoidfoodIdArray(avoidfoodIdArray);
     newBill.setAvoidfoodNameArray(avoidfoodNameArray);
     newBill.setTasteIdArray(tasteIdArray);
     newBill.setTasteNameArray(tasteNameArray);
     newBill.setPungentLevel(pungentLevel.intValue());
     newBill.setNotes(notes);
     return newBill;
   }
 
   @Transactional(readOnly=false)
   public DinerBill xiadan(String restId, String billId, LinkedHashMap<String, Object> mapSyn)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
 
     Boolean isAddDishes = Boolean.FALSE;
 
     if ((dinerBill.getBillStatus().equals(BillStatusEnum.NOT_PLACE_ORDER.getCode())) || 
       (dinerBill.getBillStatus().equals(BillStatusEnum.SOME_PLACE_ORDER.getCode())) || 
       (dinerBill.getBillStatus().equals(BillStatusEnum.RESETTLE.getCode())))
     {
       if (!dinerBill.getBillStatus().equals(BillStatusEnum.RESETTLE.getCode()))
       {
         dinerBill.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
       }
 
       TableBillRelation tableBillRelation = dinerBill.getTableBillRelation();
       if (tableBillRelation != null)
       {
         tableBillRelation.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
         this.tableBillRelationService.save(tableBillRelation);
 
         mapSyn.put(tableBillRelation.getTabBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableBillRelation);
       }
 
       List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
       List<DinerBillDishesSet> dinerBillDishesSets = dinerBill.getDinerBillDishesSets();
       List unXiadanList = new ArrayList();
       List batchUpSetList = new ArrayList();
       for (DinerBillDishe e : dinerBillDishes) {
         if (e.getDishesStatus().equals(DishesStatusEnum.UNXIADAN.getCode())) {
           unXiadanList.add(e);
           e.setDishesStatus(DishesStatusEnum.UNSERVE.getCode());
           e.setFixedTime(DateProvider.DEFAULT.getDate());
 
           mapSyn.put(e.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), e);
         }
         else
         {
           isAddDishes = Boolean.TRUE;
         }
       }
       for (DinerBillDishesSet e : dinerBillDishesSets) {
         if (e.getDsStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
         {
           DinerBillDishe dbd = new DinerBillDishe();
           dbd.setDishesName(e.getDsName());
           dbd.setUnitNum(e.getUnitNum());
           dbd.setUnitName(e.getUnitName());
           dbd.setNotes(e.getAllNotes());
           dbd.setIsSet(TrueFalseEnum.TRUE.getCode());
           dbd.setDiscountType(e.getDiscountType());
 
           List dishesDishesList = new ArrayList();
           ObjectMapper mapper = new ObjectMapper();
           try
           {
             List list = (List)mapper.readValue(e.getDsDishesDesc(), List.class);
             for (int i = 0; i < list.size(); i++) {
               Map map = (Map)list.get(i);
               String dishesId = map.get("dishesId").toString();
               String dishesName = map.get("dishesName").toString();
               String unitNum = map.get("unitNum").toString();
               String unitName = map.get("unitName").toString();
               DinerBillDishe dbdSet = new DinerBillDishe();
               dbdSet.setDishesName(dishesName);
               dbdSet.setUnitNum(Float.valueOf(unitNum).floatValue());
               dbdSet.setUnitName(unitName);
 
               DishesCategory dishesCategory = ((Dishe)this.disheService.getOne(dishesId)).getDishesCategory();
               DishesCategory newDishesCategory = new DishesCategory();
               newDishesCategory.setCategoryId(dishesCategory.getCategoryId());
               dbdSet.setDishesCategory(newDishesCategory);
               dishesDishesList.add(dbdSet);
             }
           }
           catch (Exception e1) {
             e1.printStackTrace();
           }
           dbd.setDishesSetDishesList(dishesDishesList);
           unXiadanList.add(dbd);
 
           e.setDsStatus(DishesStatusEnum.UNSERVE.getCode());
           e.setFixedTime(DateProvider.DEFAULT.getDate());
           batchUpSetList.add(e);
 
           mapSyn.put(e.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), e);
         }
         else
         {
           isAddDishes = Boolean.TRUE;
         }
 
       }
 
       this.dinerBillDishesSetService.batchUpdate(batchUpSetList);
 
       this.dinerBillDisheService.batchUpdate(dinerBillDishes);
       this.self.save(dinerBill);
 
       mapSyn.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
       String onlineBillId = dinerBill.getOnlineBillId();
       if ((StringUtils.isNotEmpty(onlineBillId)) && (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType()))) {
         mapSyn.put(onlineBillId + "_" + OperationTypeEnum.SQL.getCode(), "update cl_user_takeout set order_status = '" + TakeoutStatusEnum.PLACE_ORDER.getCode() + "' where ut_id = '" + onlineBillId + "'");
       }
 
       dinerBill.setDinerBillDishes(unXiadanList);
       String logNotes = "下单操作";
       saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.ORDER_BILL, logNotes, mapSyn);
     }
 
     dinerBill.setIsAddDishes(isAddDishes);
     return dinerBill;
   }
 
   @Transactional(readOnly=false)
   public void quxiaxiadan(String restId, String billId, LinkedHashMap<String, Object> map)
     throws Exception
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       return;
     }
 
     if (!BillStatusEnum.RESETTLE.getCode().equals(dinerBill.getBillStatus())) {
       dinerBill.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
     }
 
     List os = new ArrayList();
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
     String id;
     for (DinerBillDishe e : dinerBillDishes)
     {
       if (e.getDishesStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
       {
         StringBuffer rmDesc = cyRmReturn(e.getDishesId(), restId, e.getUnitNum(), map);
         id = e.getBdId();
         this.dinerBillDisheService.delete(id);
 
         map.put(id + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_dishes,bd_id," + id);
 
         Dishe dishe = (Dishe)this.disheService.getOne(e.getDishesId());
         if (dishe.getEstimate() != null)
         {
           estimate(Float.valueOf(dishe.getEstimate().floatValue() + e.getUnitNum()), dishe, map);
         }
 
         if (rmDesc == null)
           continue;
         String rmNotes = BillOpTypeEnum.DELETE_DISH.getDesc() + ":" + rmDesc;
         this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
       }
       else
       {
         os.add(e);
       }
 
     }
 
     List dbds = new ArrayList();
     List<DinerBillDishesSet> dinerBillDishesSet = dinerBill.getDinerBillDishesSets();
     for (DinerBillDishesSet e : dinerBillDishesSet)
     {
       if (e.getDsStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
       {
         StringBuffer rmDesc = tcRmReturn(e.getDsDishesDesc(), restId);
         id = e.getBdsId();
         this.dinerBillDishesSetService.delete(id);
 
         map.put(id + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_dishes_set,bds_id," + id);
 
         DishesSet d = (DishesSet)this.dishesSetService.getOne(e.getDsId());
         if (d.getEstimate() != null)
         {
           estimateDishesSet(Float.valueOf(d.getEstimate().floatValue() + e.getUnitNum()), d);
         }
 
         if (rmDesc == null)
           continue;
         String rmNotes = BillOpTypeEnum.DELETE_DISH.getDesc() + ":" + rmDesc;
         this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
       }
       else
       {
         dbds.add(e);
       }
     }
     if (!BillStatusEnum.RESETTLE.getCode().equals(dinerBill.getBillStatus())) {
       if ((os.size() == 0) && (dbds.size() == 0))
       {
         billStatus = BillStatusEnum.NOT_PLACE_ORDER.getCode();
       }
       dinerBill.setBillStatus(billStatus);
     }
 
     TableBillRelation tableBillRelation = dinerBill.getTableBillRelation();
     if (tableBillRelation != null)
     {
       tableBillRelation.setBillStatus(billStatus);
       this.tableBillRelationService.save(tableBillRelation);
 
       map.put(tableBillRelation.getTabBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableBillRelation);
     }
 
     dinerBill.setDinerBillDishes(os);
     dinerBill.setDinerBillDishesSets(dbds);
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
   }
 
   public DinerBillDishe dishTuicai(String restId, DinerBillDishe dinerBillDishe, String cancelReasonId, Float cancelNum, String newAddReason, String materialHand, LinkedHashMap<String, Object> map)
   {
     String dStatus = DishesStatusEnum.UNSERVE_CANCEL.getCode();
     if ((DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishe.getDishesStatus())) || 
       (DishesStatusEnum.SERVED.getCode().equals(dinerBillDishe.getDishesStatus())))
     {
       if (DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishe.getDishesStatus()))
       {
         dStatus = DishesStatusEnum.UNSERVE_CANCEL.getCode();
       }
       if (DishesStatusEnum.SERVED.getCode().equals(dinerBillDishe.getDishesStatus()))
       {
         dStatus = DishesStatusEnum.SERVED_CANCEL.getCode();
       }
       float unitNum = dinerBillDishe.getUnitNum();
 
       StringBuffer rmDesc = null;
       if (TrueFalseEnum.TRUE.getCode().equals(materialHand))
       {
         rmDesc = cyRmReturn(dinerBillDishe.getDishesId(), restId, cancelNum.floatValue(), map);
       }
 
       if ((!newAddReason.equals("")) && (newAddReason != null))
       {
         String reasonId = tuicaiReasonAdd(newAddReason, map);
         cancelReasonId = reasonId;
       }
 
       if (unitNum == cancelNum.floatValue())
       {
         dinerBillDishe.setDishesStatus(dStatus);
         dinerBillDishe.setCancelTime(DateProvider.DEFAULT.getDate());
         dinerBillDishe.setCancelReasonId(cancelReasonId);
         SpeOpReason speOpReason = (SpeOpReason)this.speOpReasonService.getOne(cancelReasonId);
         dinerBillDishe.setCancelReasonName(speOpReason.getName());
         dinerBillDishe.setRmReturn(materialHand);
         this.dinerBillDisheService.save(dinerBillDishe);
 
         map.put(dinerBillDishe.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
 
         DinerBill dinerBill = (DinerBill)this.self.getOne(dinerBillDishe.getDinerBill().getBillId());
         this.billService.calculator(restId, dinerBillDishe.getDinerBill());
         this.self.save(dinerBill);
 
         map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
         String logNotes = "退菜";
         logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
         saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.CANCEL_DISH, logNotes);
         saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.CANCEL_DISH, logNotes, map);
 
         if (rmDesc != null)
         {
           String rmNotes = BillOpTypeEnum.CANCEL_DISH.getDesc() + ":" + rmDesc;
           this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
         }
 
         Dishe d = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
         if (d.getEstimate() != null)
         {
           estimate(Float.valueOf(d.getEstimate().floatValue() + cancelNum.floatValue()), d, map);
         }
       }
       else if (cancelNum.floatValue() < unitNum)
       {
         float leftNum = unitNum - cancelNum.floatValue();
         try
         {
           BeanUtils u = new BeanUtils();
           DinerBillDishe newDinerBillDishe = (DinerBillDishe)BeanUtils.deepCopy(dinerBillDishe);
           newDinerBillDishe.setBdId(null);
           newDinerBillDishe.setUnitNum(leftNum);
           this.dinerBillDisheService.save(newDinerBillDishe);
 
           map.put(newDinerBillDishe.getBdId() + "_" + OperationTypeEnum.CREATE.getCode(), newDinerBillDishe);
         }
         catch (Exception e) {
           e.printStackTrace();
         }
 
         dinerBillDishe.setUnitNum(cancelNum.floatValue());
         dinerBillDishe.setDishesStatus(dStatus);
         dinerBillDishe.setCancelTime(DateProvider.DEFAULT.getDate());
         dinerBillDishe.setCancelReasonId(cancelReasonId);
         dinerBillDishe.setOriCost(dinerBillDishe.getUnitPrice().multiply(BigDecimal.valueOf(cancelNum.floatValue())));
         dinerBillDishe.setRealCost(dinerBillDishe.getRealUnitPrice().multiply(BigDecimal.valueOf(cancelNum.floatValue())));
         SpeOpReason speOpReason = (SpeOpReason)this.speOpReasonService.getOne(cancelReasonId);
         dinerBillDishe.setCancelReasonName(speOpReason.getName());
         dinerBillDishe.setRmReturn(materialHand);
         this.dinerBillDisheService.save(dinerBillDishe);
 
         map.put(dinerBillDishe.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
 
         DinerBill dinerBill = (DinerBill)this.self.getOne(dinerBillDishe.getDinerBill().getBillId());
         this.billService.calculator(restId, dinerBillDishe.getDinerBill());
         this.self.save(dinerBill);
 
         map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
         String logNotes = "退菜";
         logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + cancelNum + ",原料退还：" + rmDesc;
         saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.CANCEL_DISH, logNotes);
         saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.CANCEL_DISH, logNotes, map);
 
         if (rmDesc != null)
         {
           String rmNotes = BillOpTypeEnum.CANCEL_DISH.getDesc() + ":" + rmDesc;
           this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
         }
 
         Dishe d = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
         if (d.getEstimate() != null)
         {
           estimate(Float.valueOf(d.getEstimate().floatValue() + cancelNum.floatValue()), d, map);
         }
       }
 
     }
 
     return dinerBillDishe;
   }
 
   public String tuicaiReasonAdd(String reasonName, LinkedHashMap<String, Object> map)
   {
     SpeOpReason speOpReason = new SpeOpReason();
     speOpReason.setName(reasonName);
     speOpReason.setNotes(reasonName);
     speOpReason.setSysdataType(SysDataTypeEnum.USERDEFINED.getCode());
     speOpReason.setReaType("1");
     speOpReason.setEnableStatus("1");
     this.speOpReasonService.save(speOpReason);
 
     map.put(speOpReason.getReaId() + "_" + OperationTypeEnum.UPDATE.getCode(), speOpReason);
 
     return speOpReason.getReaId();
   }
 
   public DinerBillDishesSet dishsSetTuicai(String restId, DinerBillDishesSet dinerBillDishesSet, String cancelReasonId, Float cancelNum, String newAddReason, String materialHand, LinkedHashMap<String, Object> map)
     throws Exception
   {
     String dStatus = DishesStatusEnum.UNSERVE_CANCEL.getCode();
     if ((DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishesSet.getDsStatus())) || 
       (DishesStatusEnum.SERVED.getCode().equals(dinerBillDishesSet.getDsStatus())))
     {
       if (DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishesSet.getDsStatus()))
       {
         dStatus = DishesStatusEnum.UNSERVE_CANCEL.getCode();
       }
       if (DishesStatusEnum.SERVED.getCode().equals(dinerBillDishesSet.getDsStatus()))
       {
         dStatus = DishesStatusEnum.SERVED_CANCEL.getCode();
       }
       float unitNum = dinerBillDishesSet.getUnitNum();
       StringBuffer rmDesc = null;
       if (TrueFalseEnum.TRUE.getCode().equals(materialHand))
       {
         rmDesc = rmReturnForTuicai(restId, dinerBillDishesSet.getDsDishesDesc(), cancelNum.floatValue(), unitNum, map);
       }
       if ((newAddReason != "") && (newAddReason != null))
       {
         String reasonId = tuicaiReasonAdd(newAddReason, map);
         cancelReasonId = reasonId;
       }
 
       if (unitNum == cancelNum.floatValue())
       {
         dinerBillDishesSet.setDsStatus(dStatus);
         dinerBillDishesSet.setCancelTime(DateProvider.DEFAULT.getDate());
         dinerBillDishesSet.setCancelReasonId(cancelReasonId);
         SpeOpReason speOpReason = (SpeOpReason)this.speOpReasonService.getOne(cancelReasonId);
         dinerBillDishesSet.setCancelReasonName(speOpReason.getName());
         dinerBillDishesSet.setRmReturn(materialHand);
         this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
         map.put(dinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
 
         DinerBill dinerBill = (DinerBill)this.self.getOne(dinerBillDishesSet.getDinerBill().getBillId());
         this.billService.calculator(restId, dinerBillDishesSet.getDinerBill());
         this.self.save(dinerBill);
 
         map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
         String logNotes = "退菜";
         logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsStatus() + "，数量：" + dinerBillDishesSet.getUnitNum() + ",原料退回：" + rmDesc;
         saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.CANCEL_DISH, logNotes);
         saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.CANCEL_DISH, logNotes, map);
 
         DishesSet d = (DishesSet)this.dishesSetService.getOne(dinerBillDishesSet.getDsId());
         if (d.getEstimate() != null)
         {
           estimateDishesSet(Float.valueOf(d.getEstimate().floatValue() + cancelNum.floatValue()), d);
         }
       }
       else if (cancelNum.floatValue() < unitNum)
       {
         float leftNum = unitNum - cancelNum.floatValue();
         try
         {
           BeanUtils u = new BeanUtils();
           DinerBillDishesSet newDinerBillDishesSet = (DinerBillDishesSet)BeanUtils.deepCopy(dinerBillDishesSet);
           newDinerBillDishesSet.setBdsId(null);
           newDinerBillDishesSet.setUnitNum(leftNum);
           this.dinerBillDishesSetService.save(newDinerBillDishesSet);
 
           map.put(newDinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.CREATE.getCode(), newDinerBillDishesSet);
         }
         catch (Exception e) {
           e.printStackTrace();
         }
 
         dinerBillDishesSet.setUnitNum(cancelNum.floatValue());
         dinerBillDishesSet.setDsStatus(dStatus);
         dinerBillDishesSet.setCancelTime(DateProvider.DEFAULT.getDate());
         dinerBillDishesSet.setCancelReasonId(cancelReasonId);
         SpeOpReason speOpReason = (SpeOpReason)this.speOpReasonService.getOne(cancelReasonId);
         dinerBillDishesSet.setCancelReasonName(speOpReason.getName());
         dinerBillDishesSet.setRmReturn(materialHand);
         this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
         map.put(dinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
 
         DinerBill dinerBill = (DinerBill)this.self.getOne(dinerBillDishesSet.getDinerBill().getBillId());
         this.billService.calculator(restId, dinerBillDishesSet.getDinerBill());
         this.self.save(dinerBill);
 
         map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
         String logNotes = "退菜";
         logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsName() + "，数量：" + cancelNum + ",原料退回：" + rmDesc;
         saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.CANCEL_DISH, logNotes);
         saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.CANCEL_DISH, logNotes, map);
 
         DishesSet d = (DishesSet)this.dishesSetService.getOne(dinerBillDishesSet.getDsId());
         if (d.getEstimate() != null)
         {
           estimateDishesSet(Float.valueOf(d.getEstimate().floatValue() + cancelNum.floatValue()), d);
         }
       }
       if (rmDesc != null)
       {
         String rmNotes = BillOpTypeEnum.CANCEL_DISH.getDesc() + ":" + rmDesc;
         this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
       }
     }
     return dinerBillDishesSet;
   }
 
   public StringBuffer rmReturnForTuicai(String restId, String dsDishesDesc, float cancelNum, float unitNum, LinkedHashMap<String, Object> mapSyn)
     throws Exception
   {
     StringBuffer sb = new StringBuffer();
     ObjectMapper mapper = new ObjectMapper();
     boolean isRecord = false;
     sb.append("修改了属性:");
     List list = (List)mapper.readValue(dsDishesDesc, List.class);
     for (int i = 0; i < list.size(); i++) {
       Map map = (Map)list.get(i);
       String dishesId = map.get("dishesId").toString();
       float reallyNum = Float.parseFloat(map.get("unitNum").toString()) * cancelNum;
 
       Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
 
       List<DishesRaw> dishesRaws = this.dishesRawService.findByRestIdAndDishe(restId, dishe);
       for (DishesRaw dishesRaw : dishesRaws) {
         isRecord = true;
 
         RawMaterial rawMaterial = dishesRaw.getRawMaterial();
         if (rawMaterial.getRmId() == null)
           continue;
         Float stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() + dishesRaw.getUseCount().floatValue() * reallyNum);
         sb.append(rawMaterial.getRmName() + "数量从" + rawMaterial.getStockCount() + rawMaterial.getDishesUnit().getName() + "改为" + stockCount + rawMaterial.getDishesUnit().getName() + ",");
         rawMaterial.setStockCount(stockCount);
         this.rawMaterialService.save(rawMaterial);
 
         mapSyn.put(rawMaterial.getRmId() + "_" + OperationTypeEnum.UPDATE.getCode(), rawMaterial);
       }
 
     }
 
     sb.deleteCharAt(sb.length() - 1);
     if (!isRecord) {
       sb = null;
     }
     return sb;
   }
 
   @Transactional(readOnly=false)
   public void saveDishDelete(String restId, String billId, String bdId, LinkedHashMap<String, Object> map)
   {
     DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
     StringBuffer rmDesc = null;
     if (dinerBillDishe.getDishesStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
     {
       rmDesc = cyRmReturn(dinerBillDishe.getDishesId(), restId, dinerBillDishe.getUnitNum(), map);
       this.dinerBillDisheService.delete(bdId);
 
       map.put(bdId + "sdd_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_dishes,bd_id," + bdId);
     }
 
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
 
     this.billService.calculator(restId, dinerBill);
 
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
     List<DinerBillDishesSet> dinerBillDisheSets = dinerBill.getDinerBillDishesSets();
 
     int unXiadanNum = 0;
     int xiadanNum = 0;
     for (DinerBillDishe e : dinerBillDishes)
     {
       if (DishesStatusEnum.UNXIADAN.getCode().equals(e.getDishesStatus()))
       {
         unXiadanNum++;
       }
       else
       {
         xiadanNum++;
       }
     }
     for (DinerBillDishesSet e : dinerBillDisheSets)
     {
       if (DishesStatusEnum.UNXIADAN.getCode().equals(e.getDsStatus()))
       {
         unXiadanNum++;
       }
       else
       {
         xiadanNum++;
       }
     }
     if ((unXiadanNum == 0) && (dinerBill.getBillStatus().equals(BillStatusEnum.SOME_PLACE_ORDER.getCode())))
     {
       if (xiadanNum == 0)
       {
         dinerBill.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
       }
       else
       {
         dinerBill.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
       }
 
     }
 
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "sdd_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     Dishe dishe = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
     if (dishe.getEstimate() != null)
     {
       estimate(Float.valueOf(dishe.getEstimate().floatValue() + dinerBillDishe.getUnitNum()), dishe, map);
     }
 
     String logNotes = "删除菜肴操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.DELETE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.DELETE_DISH, logNotes, map);
 
     if (rmDesc != null)
     {
       String rmNotes = BillOpTypeEnum.DELETE_DISH.getDesc() + ":" + rmDesc;
       this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
     }
   }
 /**
  * 删除菜肴
  * @param restId
  * @param billId
  * @param bdId
  * @param map
  * 
  */
   @Transactional(readOnly=false)
   public void saveDishDeletes(String restId, String billId, String bdId, LinkedHashMap<String, Object> map)
   {
     DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
     StringBuffer rmDesc = null;
     if (dinerBillDishe.getDishesStatus().equals(DishesStatusEnum.UNSERVE.getCode()))
     {
    	 String temp = bdId;
   	  String tempBdIds [] = temp.split(",");
   	  
       rmDesc = cyRmReturn(dinerBillDishe.getDishesId(), restId, dinerBillDishe.getUnitNum(), map);
       for(String bdIds:tempBdIds){
       this.dinerBillDisheService.delete(bdIds);
       }
 
       map.put(bdId + "sdd_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_dishes,bd_id," + bdId);
     }
    // DinerBill dinerBill = (DinerBill)this.
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
 
     this.billService.calculator(restId, dinerBill);
 
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
     List<DinerBillDishesSet> dinerBillDisheSets = dinerBill.getDinerBillDishesSets();
 
     int unXiadanNum = 0;
     int xiadanNum = 0;
     for (DinerBillDishe e : dinerBillDishes)
     {
       if (DishesStatusEnum.UNSERVE.getCode().equals(e.getDishesStatus()))
       {
         unXiadanNum++;
       }
       else
       {
         xiadanNum++;
       }
     }
     for (DinerBillDishesSet e : dinerBillDisheSets)
     {
       if (DishesStatusEnum.UNSERVE.getCode().equals(e.getDsStatus()))
       {
         unXiadanNum++;
       }
       else
       {
         xiadanNum++;
       }
     }
     if ((unXiadanNum == 0) && (dinerBill.getBillStatus().equals(BillStatusEnum.PLACE_ORDER.getCode())))
     {
       if (xiadanNum == 0)
       {
         dinerBill.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
       }
       else
       {
         dinerBill.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
       }
 
     }
 
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "sdd_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     Dishe dishe = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
     if (dishe.getEstimate() != null)
     {
       estimate(Float.valueOf(dishe.getEstimate().floatValue() + dinerBillDishe.getUnitNum()), dishe, map);
     }
 
     String logNotes = "删除菜肴操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.DELETE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.DELETE_DISH, logNotes, map);
 
     if (rmDesc != null)
     {
       String rmNotes = BillOpTypeEnum.DELETE_DISH.getDesc() + ":" + rmDesc;
       this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
     }
   }
   /**
    * 删除套餐
    * @param restId
    * @param billId
    * @param bdsId
    * @param map
    * @throws Exception
    */
   
   @Transactional(readOnly=false)
   public void saveDishSetDeletes(String restId, String billId, String bdsId, LinkedHashMap<String, Object> map)
     throws Exception
   {
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
     StringBuffer rmDesc = null;
     if (dinerBillDishesSet.getDsStatus().equals(DishesStatusEnum.UNSERVE.getCode()))
     {
       rmDesc = tcRmReturn(dinerBillDishesSet.getDsDishesDesc(), restId);
 
       this.dinerBillDishesSetService.delete(bdsId);
 
       map.put(bdsId + "sdsd_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_dishes_set,bds_id," + bdsId);
     }
 
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
 
     this.billService.calculator(restId, dinerBill);
 
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
     List<DinerBillDishesSet> dinerBillDisheSets = dinerBill.getDinerBillDishesSets();
 
     int unXiadanNum = 0;
     int xiadanNum = 0;
     for (DinerBillDishe e : dinerBillDishes)
     {
       if (DishesStatusEnum.UNSERVE.getCode().equals(e.getDishesStatus()))
       {
         unXiadanNum++;
       }
       else
       {
         xiadanNum++;
       }
     }
     for (DinerBillDishesSet e : dinerBillDisheSets)
     {
       if (DishesStatusEnum.UNSERVE.getCode().equals(e.getDsStatus()))
       {
         unXiadanNum++;
       }
       else
       {
         xiadanNum++;
       }
     }
     if ((unXiadanNum == 0) && (dinerBill.getBillStatus().equals(BillStatusEnum.SOME_PLACE_ORDER.getCode())))
     {
       if (xiadanNum == 0)
       {
         dinerBill.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
       }
       else
       {
         dinerBill.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
       }
     }
 
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "sdsd_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dinerBillDishesSet.getDsId());
     if (dishesSet.getEstimate() != null)
     {
       estimateDishesSet(Float.valueOf(dishesSet.getEstimate().floatValue() + dinerBillDishesSet.getUnitNum()), dishesSet);
     }
 
     String logNotes = "删除套餐操作";
     logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsName() + "，数量：" + dinerBillDishesSet.getUnitNum();
     saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.DELETE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.DELETE_DISH, logNotes, map);
 
     if (rmDesc != null)
     {
       String rmNotes = BillOpTypeEnum.DELETE_DISH.getDesc() + ":" + rmDesc;
       this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
     }
   }
   
   
   
   
   
   public StringBuffer cyRmReturn(String dishesId, String restId, float dishesNum, LinkedHashMap<String, Object> map)
   {
     StringBuffer sb = new StringBuffer();
 
     Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
     sb.append("修改了属性:");
 
     List<DishesRaw> dishesRaws = this.dishesRawService.findByRestIdAndDishe(restId, dishe);
     boolean isRecord = false;
     for (DishesRaw dishesRaw : dishesRaws) {
       isRecord = true;
 
       RawMaterial rawMaterial = dishesRaw.getRawMaterial();
       if (rawMaterial.getRmId() == null)
         continue;
       Float stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() + dishesRaw.getUseCount().floatValue() * dishesNum);
       sb.append(rawMaterial.getRmName() + "数量从" + rawMaterial.getStockCount() + rawMaterial.getDishesUnit().getName() + "改为" + stockCount + rawMaterial.getDishesUnit().getName() + ",");
       rawMaterial.setStockCount(stockCount);
       this.rawMaterialService.save(rawMaterial);
 
       map.put(rawMaterial.getRmId() + "_" + OperationTypeEnum.UPDATE.getCode(), rawMaterial);
     }
 
     sb.deleteCharAt(sb.length() - 1);
     if (!isRecord) {
       sb = null;
     }
     return sb;
   }
 
   @Transactional(readOnly=false)
   public void saveDishSetDelete(String restId, String billId, String bdsId, LinkedHashMap<String, Object> map)
     throws Exception
   {
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
     StringBuffer rmDesc = null;
     if (dinerBillDishesSet.getDsStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
     {
       rmDesc = tcRmReturn(dinerBillDishesSet.getDsDishesDesc(), restId);
 
       this.dinerBillDishesSetService.delete(bdsId);
 
       map.put(bdsId + "sdsd_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_dishes_set,bds_id," + bdsId);
     }
 
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
 
     this.billService.calculator(restId, dinerBill);
 
     List<DinerBillDishe> dinerBillDishes = dinerBill.getDinerBillDishes();
     List<DinerBillDishesSet> dinerBillDisheSets = dinerBill.getDinerBillDishesSets();
 
     int unXiadanNum = 0;
     int xiadanNum = 0;
     for (DinerBillDishe e : dinerBillDishes)
     {
       if (DishesStatusEnum.UNXIADAN.getCode().equals(e.getDishesStatus()))
       {
         unXiadanNum++;
       }
       else
       {
         xiadanNum++;
       }
     }
     for (DinerBillDishesSet e : dinerBillDisheSets)
     {
       if (DishesStatusEnum.UNXIADAN.getCode().equals(e.getDsStatus()))
       {
         unXiadanNum++;
       }
       else
       {
         xiadanNum++;
       }
     }
     if ((unXiadanNum == 0) && (dinerBill.getBillStatus().equals(BillStatusEnum.SOME_PLACE_ORDER.getCode())))
     {
       if (xiadanNum == 0)
       {
         dinerBill.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
       }
       else
       {
         dinerBill.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
       }
     }
 
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "sdsd_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dinerBillDishesSet.getDsId());
     if (dishesSet.getEstimate() != null)
     {
       estimateDishesSet(Float.valueOf(dishesSet.getEstimate().floatValue() + dinerBillDishesSet.getUnitNum()), dishesSet);
     }
 
     String logNotes = "删除套餐操作";
     logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsName() + "，数量：" + dinerBillDishesSet.getUnitNum();
     saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.DELETE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.DELETE_DISH, logNotes, map);
 
     if (rmDesc != null)
     {
       String rmNotes = BillOpTypeEnum.DELETE_DISH.getDesc() + ":" + rmDesc;
       this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
     }
   }
 
   @Transactional(readOnly=false)
   public void saveDishDeleteAll(String restId, String billId, LinkedHashMap<String, Object> map)
     throws Exception
   {
     DinerBill dinerBill = (DinerBill)super.getOne(billId);
 
     List dinerBillDishes = this.dinerBillDisheService.findByRestIdAndBillId(restId, billId);
     this.dinerBillDisheService.batchDelete(dinerBillDishes);
 
     map.put("dinerBillDishes_" + OperationTypeEnum.BATCH_DELETE.getCode(), dinerBillDishes);
 
     List dinerBillDishesSetList = this.dinerBillDishesSetService.findByRestIdAndDinerBill(restId, dinerBill);
     this.dinerBillDishesSetService.batchDelete(dinerBillDishesSetList);
 
     map.put("dinerBillDishesSetList_" + OperationTypeEnum.BATCH_DELETE.getCode(), dinerBillDishesSetList);
 
     String logNotes = "删除所有菜品操作";
     saveBusiLog(restId, dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.DELETE_DISH, logNotes);
     saveDinerBillLog(restId, dinerBill, BillOpTypeEnum.DELETE_DISH, logNotes, map);
 
     String rmNotes = "删除所有菜品";
     this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
   }
 
   public StringBuffer tcRmReturn(String dsDishesDesc, String restId)
     throws Exception
   {
     StringBuffer sb = new StringBuffer();
     ObjectMapper mapper = new ObjectMapper();
     List list = (List)mapper.readValue(dsDishesDesc, List.class);
     sb.append("修改了属性:");
     boolean isRecord = false;
     for (int i = 0; i < list.size(); i++) {
       Map map = (Map)list.get(i);
       String dishesId = map.get("dishesId").toString();
       float unitNum = Float.parseFloat(map.get("unitNum").toString());
 
       Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
 
       List<DishesRaw> dishesRaws = this.dishesRawService.findByRestIdAndDishe(restId, dishe);
       for (DishesRaw dishesRaw : dishesRaws) {
         isRecord = true;
 
         RawMaterial rawMaterial = dishesRaw.getRawMaterial();
         if (rawMaterial.getRmId() == null)
           continue;
         float stockCount = rawMaterial.getStockCount().floatValue() + dishesRaw.getUseCount().floatValue() * unitNum;
         sb.append(rawMaterial.getRmName() + "数量从" + rawMaterial.getStockCount() + rawMaterial.getDishesUnit().getName() + "改为" + stockCount + rawMaterial.getDishesUnit().getName() + ",");
         rawMaterial.setStockCount(Float.valueOf(stockCount));
         this.rawMaterialService.save(rawMaterial);
       }
 
     }
 
     sb.deleteCharAt(sb.length() - 1);
     if (!isRecord) {
       sb = null;
     }
     return sb;
   }
 
   @Transactional(readOnly=false)
   public DinerBillDishe saveDishHuacai(String restId, String bdId, LinkedHashMap<String, Object> map)
   {
     DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
     if (DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishe.getDishesStatus()))
     {
       dinerBillDishe.setDishesStatus(DishesStatusEnum.SERVED.getCode());
       this.dinerBillDisheService.save(dinerBillDishe);
 
       map.put(dinerBillDishe.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
     }
 
     String logNotes = "划菜操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.HUA_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.HUA_DISH, logNotes, map);
 
     return dinerBillDishe;
   }
 
   @Transactional(readOnly=false)
   public DinerBillDishesSet saveDishesSetHuacai(String restId, String bdsId, LinkedHashMap<String, Object> map) {
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
     if (DishesStatusEnum.UNSERVE.getCode().equals(dinerBillDishesSet.getDsStatus())) {
       dinerBillDishesSet.setDsStatus(DishesStatusEnum.SERVED.getCode());
       this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
       map.put(dinerBillDishesSet.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
     }
 
     String logNotes = "划菜操作";
     logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsName() + "，数量：" + dinerBillDishesSet.getUnitNum();
     saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.HUA_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.HUA_DISH, logNotes, map);
 
     return dinerBillDishesSet;
   }
 
   @Transactional(readOnly=false)
   public Map<String, String> saveDishNumChange(String restId, String billId, String bdId, String newDishNum, LinkedHashMap<String, Object> map)
   {
     float num = Float.parseFloat(newDishNum);
     DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
 
     Map messageMap = new HashMap();
 
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     float oldNum = dinerBillDishe.getUnitNum();
     if (num > 0.0F)
     {
       Dishe d = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
       if (d.getEstimate() != null)
       {
         Float estimateNum = Float.valueOf(num - oldNum);
 
         if ((estimateNum.floatValue() > 0.0F) && (estimateNum.floatValue() > d.getEstimate().floatValue()))
         {
           messageMap.put("code", StatusCodeEnum.LOGIC_ERROR.getCode());
           return messageMap;
         }
         estimate(Float.valueOf(d.getEstimate().floatValue() - estimateNum.floatValue()), d, map);
       }
 
       dinerBillDishe.setUnitNum(num);
       Dishe dishe = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
       this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, dishe);
       this.dinerBillDisheService.save(dinerBillDishe);
 
       map.put(dinerBillDishe.getBdId() + "sdnc_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
     }
     else
     {
       saveDishDelete(restId, billId, bdId, map);
     }
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "sdnc_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     messageMap = cyStockUpdateByNum(bdId, billId, restId, oldNum+"", newDishNum);
     messageMap.put("code", StatusCodeEnum.SUCCESS.getCode());
     if (messageMap.containsKey("rmDesc"))
     {
       String rmNotes = "点餐修改菜肴数量:" + (String)messageMap.get("rmDesc");
       this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
     }
     String logNotes = "修改菜肴数量:";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，原数量：" + oldNum + " 改为：" + dinerBillDishe.getUnitNum();
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.ORDER_DISH, logNotes, map);
     return messageMap;
   }
 
   @Transactional(readOnly=false)
   public Map<String, String> saveDishesSetNumChange(String restId, String billId, String bdsId, String newDishesSetNum, LinkedHashMap<String, Object> map)
     throws Exception
   {
     float num = Float.valueOf(newDishesSetNum).floatValue();
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     float oldNum = dinerBillDishesSet.getUnitNum();
 
     if (num > 0.0F)
     {
       DishesSet ds = (DishesSet)this.dishesSetService.getOne(dinerBillDishesSet.getDsId());
       if (ds.getEstimate() != null)
       {
         Float estimateNum = Float.valueOf(num - oldNum);
 
         if ((estimateNum.floatValue() <= 0.0F) || (estimateNum.floatValue() <= ds.getEstimate().floatValue()))
         {
           estimateDishesSet(Float.valueOf(ds.getEstimate().floatValue() - estimateNum.floatValue()), ds);
         }
 
       }
 
       dinerBillDishesSet.setUnitNum(num);
       this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
       map.put(dinerBillDishesSet.getBdsId() + "sdsnc_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
 
       this.billService.calculatorSingleDishesSet(restId, dinerBill, dinerBillDishesSet, ds);
     }
     else
     {
       saveDishSetDelete(restId, billId, bdsId, map);
     }
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "sdsnc_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     Map messageMap = tcStockUpdateByNum(bdsId, billId, restId, String.valueOf(oldNum), newDishesSetNum);
     if (messageMap.containsKey("rmDesc"))
     {
       String rmNotes = "点餐修改套餐数量:" + (String)messageMap.get("rmDesc");
       this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
     }
     String logNotes = "修改套餐数量:";
     logNotes = logNotes + "，套餐名称:" + dinerBillDishesSet.getDsName() + "，原数量：" + oldNum + " 改为：" + dinerBillDishesSet.getUnitNum();
     saveBusiLog(restId, dinerBillDishesSet.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishesSet.getDinerBill(), BillOpTypeEnum.ORDER_DISH, logNotes, map);
 
     messageMap.put("dishName", dinerBillDishesSet.getDsName());
     messageMap.put("dishNum", dinerBillDishesSet.getUnitNumStr());
     return messageMap;
   }
 
   @Transactional(readOnly=false)
   public boolean isEstimateEnoughForDishesSetBill(String restId, String bdsId, String newDishesSetNum)
   {
     float num = Float.valueOf(newDishesSetNum).floatValue();
     float oldNum = 0.0F;
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
     if ((dinerBillDishesSet != null) && (dinerBillDishesSet.getDsId() != null)) {
       oldNum = dinerBillDishesSet.getUnitNum();
     }
 
     DishesSet ds = (DishesSet)this.dishesSetService.getOne(dinerBillDishesSet.getDsId());
     if (ds.getEstimate() != null)
     {
       Float estimateNum = Float.valueOf(num - oldNum);
 
       if ((estimateNum.floatValue() > 0.0F) && (estimateNum.floatValue() > ds.getEstimate().floatValue()))
       {
         return false;
       }
     }
     return true;
   }
 
   @Transactional(readOnly=false)
   public boolean isEstimateEnoughForDishesBill(String restId, Dishe dishe, String newDishesNum, float oldNum)
   {
     if (dishe == null)
     {
       return false;
     }
     float num = Float.valueOf(newDishesNum).floatValue();
 
     if (dishe.getEstimate() != null) {
       Float disheNum = Float.valueOf(num - oldNum);
 
       if ((disheNum.floatValue() > 0.0F) && (disheNum.floatValue() > dishe.getEstimate().floatValue()))
       {
         return false;
       }
     }
     else {
       return true;
     }
     return true;
   }
 
   @Transactional(readOnly=false)
   public boolean isEstimateEnoughForDishes(String restId, Dishe d, float dishesNum)
   {
     if (d == null)
     {
       return false;
     }
 
     return (d.getEstimate() == null) || ((d.getEstimate().floatValue() != 0.0F) && (d.getEstimate().floatValue() >= dishesNum));
   }
 
   @Transactional(readOnly=false)
   public void saveOrderDishDelete(String orderId, String bdId, String isSet, LinkedHashMap<String, Object> map)
   {
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       OrderBillDishesSet orderBillDishesSet = (OrderBillDishesSet)this.orderBillDishesSetService.getOne(bdId);
       if (tableOrder.getOriCost() != null) {
         tableOrder.setOriCost(tableOrder.getOriCost().subtract(orderBillDishesSet.getOriCost()));
       }
 
       this.tableOrderService.save(tableOrder);
       this.orderBillDishesSetService.delete(bdId);
 
       map.put(tableOrder.getOrderId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
       map.put(bdId + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_order_bill_dishes_set,bds_id," + bdId);
     }
     else
     {
       OrderBillDishe orderBillDishes = (OrderBillDishe)this.orderBillDishesService.getOne(bdId);
       if (tableOrder.getOriCost() != null) {
         tableOrder.setOriCost(tableOrder.getOriCost().subtract(orderBillDishes.getOriCost()));
       }
 
       this.tableOrderService.save(tableOrder);
       this.orderBillDishesService.delete(bdId);
 
       map.put(tableOrder.getOrderId() + "_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
       map.put(bdId + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_order_bill_dishes,bd_id," + bdId);
     }
   }
 
   @Transactional(readOnly=false)
   public void saveOrderDishNumChange(String orderId, String bdId, String newDishNum, String isSet, LinkedHashMap<String, Object> map) {
     float num = Float.valueOf(newDishNum).floatValue();
     if (num > 0.0F)
       if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
         OrderBillDishesSet orderBillDisheSet = (OrderBillDishesSet)this.orderBillDishesSetService.getOne(bdId);
         orderBillDisheSet.setUnitNum(num);
         orderBillDisheSet.setOriCost(orderBillDisheSet.getRealUnitPrice().multiply(new BigDecimal(orderBillDisheSet.getUnitNum())));
         orderBillDisheSet.setRealCost(orderBillDisheSet.getRealUnitPrice().multiply(new BigDecimal(orderBillDisheSet.getUnitNum())));
         this.orderBillDishesSetService.save(orderBillDisheSet);
 
         map.put(orderBillDisheSet.getBdsId() + "_" + OperationTypeEnum.UPDATE.getCode(), orderBillDisheSet);
       }
       else {
         OrderBillDishe orderBillDishe = (OrderBillDishe)this.orderBillDishesService.getOne(bdId);
         orderBillDishe.setUnitNum(num);
         orderBillDishe.setOriCost(orderBillDishe.getRealUnitPrice().multiply(new BigDecimal(orderBillDishe.getUnitNum())));
         orderBillDishe.setRealCost(orderBillDishe.getRealUnitPrice().multiply(new BigDecimal(orderBillDishe.getUnitNum())));
         this.orderBillDishesService.save(orderBillDishe);
 
         map.put(orderBillDishe.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), orderBillDishe);
       }
   }
 
   @Transactional(readOnly=false)
   public DinerBill updateResettle(DinerBill dinerBill, String restId, String reaId, LinkedHashMap<String, Object> map)
   {
     SpeOpReason speOpReason = (SpeOpReason)this.speOpReasonService.getOne(reaId);
 
     BusiLog busiLog = new BusiLog();
 
     DinerBillLog dinerBillLog = new DinerBillLog();
 
     dinerBillLog.setBillOpType(BillOpTypeEnum.RESETTLE.getCode());
 
     dinerBillLog.setRestId(restId);
 
     Table table = dinerBill.getTable();
     if (table != null) {
       dinerBillLog.setTable(table);
 
       dinerBillLog.setTabNo(table.getTabNo());
     }
 
     dinerBillLog.setDinerBill(dinerBill);
 
     dinerBillLog.setBillNo(dinerBill.getBillNo());
 
     dinerBillLog.setNotes(speOpReason.getName());
 
     busiLog.setRestId(restId);
 
     busiLog.setBussLogType(BussLogTypeEnum.RESETTLE.getCode());
 
     busiLog.setOpType(BillOpTypeEnum.RESETTLE.getCode());
     if (table != null)
     {
       busiLog.setTabId(table.getTabId());
       busiLog.setTabNo(table.getTabNo());
     }
 
     busiLog.setBillId(dinerBill.getBillId());
 
     busiLog.setBillNo(dinerBill.getBillNo());
 
     busiLog.setPayTime(dinerBill.getPayTime());
     busiLog.setNotes(speOpReason.getName());
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     if (!BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType()))
     {
       Table t = dinerBill.getTable();
       if (t != null)
       {
         TableBillRelation tr = dinerBill.getTableBillRelation();
 
         if (tr == null)
         {
           List rs = this.tableBillRelationService.findByTableAndTabBillType(table, TabBillTypeEnum.ORDER.getCode());
           if ((rs == null) || (rs.size() < 1))
           {
             t.setDinnerStatus(DinnerStatusEnum.USING.getCode());
             this.tableService.save(t);
 
             map.put(t.getTabId() + "_" + OperationTypeEnum.UPDATE.getCode(), t);
 
             TableBillRelation tableBillRelation = new TableBillRelation();
             tableBillRelation.setTable(t);
             tableBillRelation.setDinerBill(dinerBill);
             tableBillRelation.setBillStatus(dinerBill.getBillStatus());
             tableBillRelation.setPeopleNum(dinerBill.getPeopleNum());
             tableBillRelation.setBillTime(dinerBill.getBillTime());
 
             tableBillRelation.setTabBillType(TabBillTypeEnum.ORDER.getCode());
             this.tableBillRelationService.save(tableBillRelation);
 
             map.put(tableBillRelation.getTabBillId() + "_" + OperationTypeEnum.CREATE.getCode(), tableBillRelation);
           }
 
         }
 
       }
 
     }
 
     this.dinerBillLogService.save(dinerBillLog);
 
     this.busiLogService.save(busiLog);
 
     ResettleLog resettleLog = new ResettleLog();
     resettleLog.setBillId(dinerBill.getBillId());
     resettleLog.setBillNo(dinerBill.getBillNo());
     resettleLog.setReaId(reaId);
     resettleLog.setResettleReason(speOpReason.getName());
     resettleLog.setLogTime(DateProvider.DEFAULT.getDate());
     resettleLog.setPayableCost(dinerBill.getPayableCost());
     resettleLog.setPayTime(dinerBill.getPayTime());
     resettleLog.setResettleTime(DateProvider.DEFAULT.getDate());
     resettleLog.setResettleCost(dinerBill.getRealCost());
     this.resettleLogService.save(resettleLog);
 
     map.put(resettleLog.getLogId() + "_" + OperationTypeEnum.CREATE.getCode(), resettleLog);
 
     MembershipCard membershipCard = dinerBill.getMembershipCard();
     BigDecimal addIntegral = BigDecimal.ZERO;
 
     if (membershipCard != null)
     {
       BigDecimal memberIntegral = membershipCard.getMemberIntegral() == null ? BigDecimal.ZERO : membershipCard.getMemberIntegral();
       addIntegral = BigDecimalUtil.formatRoundDown(dinerBill.getAddIntegral());
       membershipCard.setMemberIntegral(memberIntegral.subtract(addIntegral));
 
       BigDecimal membercardCost = dinerBill.getMembercardCost();
       if ((membercardCost != null) && (BigDecimal.ZERO.compareTo(membercardCost) == -1))
       {
         BigDecimal balance = membershipCard.getBalance() == null ? BigDecimal.ZERO : membershipCard.getBalance();
         membershipCard.setBalance(balance.add(membercardCost));
         this.membershipCardService.save(membershipCard);
 
         map.put(membershipCard.getMcId() + "_" + OperationTypeEnum.UPDATE.getCode(), membershipCard);
 
         MembershipCardOperation membershipCardOperation = new MembershipCardOperation();
         RestMemberInfo restMemberInfo = membershipCard.getRestMemberInfo();
         restMemberInfo.getName();
         membershipCardOperation.setRestMemberInfo(restMemberInfo);
         membershipCardOperation.setMembershipCard(membershipCard);
         membershipCardOperation.setCardOperationType(CardOperationTypeEnum.RESETTLE_REFUND.getCode());
         membershipCardOperation.setAddIntegral(addIntegral);
         membershipCardOperation.setTotalIntegral(membershipCard.getMemberIntegral());
         membershipCardOperation.setPaidinCash(membercardCost);
         membershipCardOperation.setRechargeCash(membercardCost);
         membershipCardOperation.setIsDrawBill(TrueFalseEnum.FALSE.getCode());
         membershipCardOperation.setBalance(membershipCard.getBalance());
         membershipCardOperation.setBillNo(dinerBill.getBillNo());
         membershipCardOperation.setDinerBill(dinerBill);
         this.membershipCardOperationService.save(membershipCardOperation);
 
         map.put(membershipCardOperation.getCmcoId() + "_" + OperationTypeEnum.CREATE.getCode(), membershipCardOperation);
       }
     }
 
     dinerBill.setAddIntegral(null);
     dinerBill.setMembercardCost(null);
     Date updateTime = new Date();
 
     dinerBill.setUpdateTime(updateTime);
 
     dinerBill.setResettleTime(updateTime);
 
     this.self.save(dinerBill);
     return dinerBill;
   }
 
   @Transactional(readOnly=false)
   public void saveCookingNotes(String billId, String avoidArray, String tasteArray, int pungentArray, String notes, LinkedHashMap<String, Object> map)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     dinerBill.setAvoidfoodIdArray(avoidArray);
     dinerBill.setAvoidfoodNameArray(this.dishesAvoidfoodService.findNameArray(avoidArray));
     dinerBill.setTasteIdArray(tasteArray);
     dinerBill.setTasteNameArray(this.dishesTasteService.findNameArray(tasteArray));
     dinerBill.setPungentLevel(pungentArray);
     dinerBill.setNotes(notes);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "scn_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
   }
   @Transactional(readOnly=false)
   public void saveOrderCookingNotes(String orderId, String avoidArray, String tasteArray, int pungentArray, String notes, LinkedHashMap<String, Object> map) {
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
 
     tableOrder.setAvoidfoodNameArray(this.dishesAvoidfoodService.findNameArray(avoidArray));
     tableOrder.setTasteIdArray(tasteArray);
     tableOrder.setTasteNameArray(this.dishesTasteService.findNameArray(tasteArray));
     tableOrder.setPungentLevel(pungentArray);
     tableOrder.setNotes(notes);
     tableOrder.setAvoidfoodIdArray(avoidArray);
     this.tableOrderService.save(tableOrder);
 
     map.put(tableOrder.getOrderId() + "socn_" + OperationTypeEnum.UPDATE.getCode(), tableOrder);
   }
 
   @Transactional(readOnly=false)
   public void saveDishCookingNotes(String ID, String avoidArray, String tasteArray, int pungentArray, String notes, String isSet, LinkedHashMap<String, Object> map)
   {
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(ID);
       dinerBillDishesSet.setAvoidfoodIdArray(avoidArray);
       dinerBillDishesSet.setAvoidfoodNameArray(this.dishesAvoidfoodService.findNameArray(avoidArray));
       dinerBillDishesSet.setTasteIdArray(tasteArray);
       dinerBillDishesSet.setTasteNameArray(this.dishesTasteService.findNameArray(tasteArray));
       dinerBillDishesSet.setPungentLevel(pungentArray);
       dinerBillDishesSet.setNotes(notes);
       this.dinerBillDishesSetService.save(dinerBillDishesSet);
 
       dinerBillDishesSet.getDinerBill().getBillNo();
       map.put(dinerBillDishesSet.getBdsId() + "sdcn_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishesSet);
     }
     else {
       DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(ID);
       dinerBillDishe.setAvoidfoodIdArray(avoidArray);
       dinerBillDishe.setAvoidfoodNameArray(this.dishesAvoidfoodService.findNameArray(avoidArray));
       dinerBillDishe.setTasteIdArray(tasteArray);
       dinerBillDishe.setTasteNameArray(this.dishesTasteService.findNameArray(tasteArray));
       dinerBillDishe.setPungentLevel(pungentArray);
       dinerBillDishe.setNotes(notes);
       this.dinerBillDisheService.save(dinerBillDishe);
 
       dinerBillDishe.getDinerBill().getBillNo();
       map.put(dinerBillDishe.getDsId() + "sdcn_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
     }
   }
 
   @Transactional(readOnly=false)
   public void saveDishCookingNotes(DinerBillDishesSet dinerBillDishesSet, String avoidArray, String tasteArray, int pungentArray, String notes, String isSet)
   {
     dinerBillDishesSet.setAvoidfoodIdArray(avoidArray);
     dinerBillDishesSet.setAvoidfoodNameArray(this.dishesAvoidfoodService.findNameArray(avoidArray));
     dinerBillDishesSet.setTasteIdArray(tasteArray);
     dinerBillDishesSet.setTasteNameArray(this.dishesTasteService.findNameArray(tasteArray));
     dinerBillDishesSet.setPungentLevel(pungentArray);
     dinerBillDishesSet.setNotes(notes);
     this.dinerBillDishesSetService.save(dinerBillDishesSet);
   }
 
   @Transactional(readOnly=false)
   public void saveDishCookingNotes(DinerBillDishe dinerBillDishe, String avoidArray, String tasteArray, int pungentArray, String notes, String isSet, LinkedHashMap<String, Object> map)
   {
     dinerBillDishe.setAvoidfoodIdArray(avoidArray);
     dinerBillDishe.setAvoidfoodNameArray(this.dishesAvoidfoodService.findNameArray(avoidArray));
     dinerBillDishe.setTasteIdArray(tasteArray);
     dinerBillDishe.setTasteNameArray(this.dishesTasteService.findNameArray(tasteArray));
     dinerBillDishe.setPungentLevel(pungentArray);
     dinerBillDishe.setNotes(notes);
     this.dinerBillDisheService.save(dinerBillDishe);
 
     map.put(dinerBillDishe.getBdId() + "sdckn_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
   }
 
   @Transactional(readOnly=false)
   public void saveOrderDishCookingNotes(String dbId, String avoidArray, String tasteArray, int pungentArray, String notes, String isSet, LinkedHashMap<String, Object> map)
   {
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       OrderBillDishesSet orderBillDishesSet = (OrderBillDishesSet)this.orderBillDishesSetService.getOne(dbId);
       orderBillDishesSet.setAvoidfoodIdArray(avoidArray);
       orderBillDishesSet.setAvoidfoodNameArray(this.dishesAvoidfoodService.findNameArray(avoidArray));
       orderBillDishesSet.setTasteIdArray(tasteArray);
       orderBillDishesSet.setTasteNameArray(this.dishesTasteService.findNameArray(tasteArray));
       orderBillDishesSet.setPungentLevel(pungentArray);
       orderBillDishesSet.setNotes(notes);
       this.orderBillDishesSetService.save(orderBillDishesSet);
 
       map.put(orderBillDishesSet.getBdsId() + "sodcn_" + OperationTypeEnum.UPDATE.getCode(), orderBillDishesSet);
     }
     else {
       OrderBillDishe orderBillDishe = (OrderBillDishe)this.orderBillDishesService.getOne(dbId);
       orderBillDishe.setAvoidfoodIdArray(avoidArray);
       orderBillDishe.setAvoidfoodNameArray(this.dishesAvoidfoodService.findNameArray(avoidArray));
       orderBillDishe.setTasteIdArray(tasteArray);
       orderBillDishe.setTasteNameArray(this.dishesTasteService.findNameArray(tasteArray));
       orderBillDishe.setPungentLevel(pungentArray);
       orderBillDishe.setNotes(notes);
       this.orderBillDishesService.save(orderBillDishe);
 
       map.put(orderBillDishe.getBdId() + "sodcn_" + OperationTypeEnum.UPDATE.getCode(), orderBillDishe);
     }
   }
 
   @Transactional(readOnly=false)
   public void saveCancleMemeberCard(String restId, DinerBill dinerBill, String isDeleteCashDiscount, String cptId, LinkedHashMap<String, Object> map)
   {
     if ((dinerBill.getCashDiscount() != null) && (TrueFalseEnum.TRUE.getCode().equals(isDeleteCashDiscount))) {
       dinerBill.setCashDiscount(null);
       dinerBill.setDiscountName(null);
     }
     dinerBill.setMembershipCard(null);
     dinerBill.setRestMemberInfo(null);
     if (TrueFalseEnum.FALSE.getCode().equals(dinerBill.getIsCustomDiscount()))
     {
       dinerBill.setDiscount(Integer.valueOf(0));
       dinerBill.setOtherDiscount("");
     }
     dinerBill.setMembercardPayType(null);
     dinerBill.setAddIntegral(null);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     List types = new ArrayList();
     types.add(PaymentTypeEnum.MEMBER_CARD.getCode());
     types.add(PaymentTypeEnum.HOTEL_CREDIT.getCode());
     types.add(PaymentTypeEnum.TEAM_CREDIT.getCode());
     types.add(PaymentTypeEnum.RESTAURANT_CREDIT.getCode());
     List paymentTypes = this.paymentTypeService.findByRestIdAndEnableStatusAndPaymentTypeIn(restId, types);
     List<DinerBillPayment> dinerBillPayments = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentTypeIn(dinerBill, restId, paymentTypes);
     for (DinerBillPayment dinerBillPayment : dinerBillPayments) {
       String id = dinerBillPayment.getDbpId();
       this.dinerBillPaymentService.delete(id);
 
       map.put(id + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_payment,dbp_id," + id);
     }
   }
 
   @Transactional(readOnly=true)
   public BigDecimal needMoneyForMember(String restId, String billId, String mcId)
   {
     BigDecimal needMoney = BigDecimal.ZERO;
 
     DinerBill dinerBill = this.dinerBillPaymentService.useMembershipCardForPay(billId, mcId);
     super.save(dinerBill);
 
     this.billService.calculator(restId, dinerBill);
 
     needMoney = dinerBill.getNeedMoney();
 
     dinerBill.setCashDiscount(null);
     dinerBill.setDiscountName(null);
     dinerBill.setMembershipCard(null);
     dinerBill.setRestMemberInfo(null);
     dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
     dinerBill.setDiscount(Integer.valueOf(0));
     dinerBill.setOtherDiscount("");
     super.save(dinerBill);
     return needMoney;
   }
 
   public List<DinerBill> findByRestaurantAndBillStatus(Restaurant restaurant, String billStatus) {
     return this.dinerBillDao.findByRestaurantAndBillStatus(restaurant, billStatus);
   }
 
   public Long getCountByBillStatus(String restId, String billStatus)
   {
     return this.dinerBillDao.getCountByBillStatus(restId, billStatus);
   }
 
   public Long getCountByBillTypeAndBillStatusIn(String restId, String billType, List<String> billStatus)
   {
     return this.dinerBillDao.getCountByBillTypeAndBillStatusIn(restId, billType, billStatus);
   }
 
   public List<DinerBill> findByRestaurantAndBillTypeAndBillStatusIn(Restaurant restaurant, String billType, List<String> billStatus) {
     return this.dinerBillDao.findByRestaurantAndBillTypeAndBillStatusIn(restaurant, billType, billStatus);
   }
 
   public List<DinerBill> getByRestaurantAndBillStatus(String restId, String billStatus, String startDate)
   {
     return this.dinerBillDao.getByRestaurantAndBillStatus(restId, billStatus, startDate);
   }
   @Transactional(readOnly=false)
   public DinerBill saveDraw(DinerBill dinerBill) {
     return (DinerBill)this.self.save(dinerBill);
   }
 
   public BigDecimal getOddChangeSum(String restId, String empId, Date createTime) {
     return this.dinerBillDao.getOddChangeSum(restId, empId, createTime, BillStatusEnum.SETTLE.getCode());
   }
   public Long getPeopleNumSum(String restId, String empId, Date createTime) {
     return this.dinerBillDao.getPeopleNumSum(restId, empId, createTime, BillStatusEnum.SETTLE.getCode());
   }
 
   public BigDecimal getForceMoneySum(String restId, String empId, Date forcePayTime, String billStatus, String isForcePay, String isValid)
   {
     return this.dinerBillDao.getForceMoneySum(restId, empId, forcePayTime, billStatus, isForcePay, isValid);
   }
 
   public Page<DinerBill> getPage(PageRequest pageRequest, String restId, String searchType)
   {
     return ((DinerBillDao)getDao()).findAll(DinerBillSpec.searchPage(searchType, restId), pageRequest);
   }
 
   @Transactional(readOnly=false)
   public String[] saveJiacaiBymobile(String billId, String dishesId, Employee user, float dishesNum, String restId, LinkedHashMap<String, Object> map)
   {
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     if (dinerBill.getBillStatus().equals(BillStatusEnum.PLACE_ORDER.getCode()))
     {
       dinerBill.setBillStatus(BillStatusEnum.SOME_PLACE_ORDER.getCode());
     }
     Dishe d = (Dishe)this.disheService.getOne(dishesId);
 
     if ((d.getEstimate() != null) && ((d.getEstimate().floatValue() == 0.0F) || (d.getEstimate().floatValue() < dishesNum)))
     {
       return new String[] { StatusCodeEnum.LOGIC_ERROR.getCode(), String.valueOf(d.getEstimate() == null ? "" : d.getEstimate()) };
     }
     DinerBillDishe dinerBillDishe = new DinerBillDishe();
     dinerBillDishe.setDinerBill(dinerBill);
     dinerBillDishe.setDishesId(d.getDishesId());
     dinerBillDishe.setDishesCategory(d.getDishesCategory());
     dinerBillDishe.setBillNo(dinerBill.getBillNo());
     dinerBillDishe.setDishesName(d.getDishesName());
     dinerBillDishe.setDishesCode(d.getDishesCode());
     dinerBillDishe.setUnitPrice(d.getPrice());
     dinerBillDishe.setUnitNum(dishesNum);
     dinerBillDishe.setOrderEmp(user);
     dinerBillDishe.setDishesStatus(DishesStatusEnum.UNXIADAN.getCode());
     dinerBillDishe.setUnitId(d.getDishesUnit().getUnitId());
     dinerBillDishe.setUnitName(d.getDishesUnit().getName());
     dinerBillDishe.setUnitType(d.getDishesUnit().getUnitType());
     dinerBillDishe.setDiscount(Integer.valueOf(100));
     dinerBillDishe.setRealUnitPrice(d.getPrice());
     dinerBillDishe.setIsRulingPrice(d.getIsRulingPrice());
     dinerBillDishe.setPungentLevel(0);
     dinerBillDishe.setOrderTime(DateProvider.DEFAULT.getDate());
     dinerBillDishe.setIsOnsale(d.getIsOnsale());
     dinerBillDishe.setIsUserDefined(TrueFalseEnum.FALSE.getCode());
 
     this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, d);
     this.dinerBillDisheService.save(dinerBillDishe);
 
     map.put(dinerBillDishe.getBdId() + "sjbm_" + OperationTypeEnum.CREATE.getCode(), dinerBillDishe);
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "sjbm_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     if (d.getEstimate() != null)
     {
       estimate(Float.valueOf(d.getEstimate().floatValue() - dishesNum), d, map);
     }
 
     String logNotes = "加菜操作";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，数量：" + dinerBillDishe.getUnitNum();
 
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.ORDER_DISH, logNotes, map);
     return new String[] { StatusCodeEnum.SUCCESS.getCode(), String.valueOf(d.getEstimate() == null ? "" : d.getEstimate()) };
   }
 
   @Transactional(readOnly=false)
   public String updateJiacaiBymobile(String restId, String billId, String bdId, Employee user, Float num, LinkedHashMap<String, Object> map)
   {
     DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
 
     DinerBill dinerBill = (DinerBill)this.self.getOne(billId);
     float oldNum = dinerBillDishe.getUnitNum();
     if (num.floatValue() > 0.0F)
     {
       Dishe d = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
       if (d.getEstimate() != null)
       {
         Float estimateNum = Float.valueOf(num.floatValue() - oldNum);
 
         if ((estimateNum.floatValue() > 0.0F) && (estimateNum.floatValue() > d.getEstimate().floatValue()))
         {
           return StatusCodeEnum.LOGIC_ERROR.getCode();
         }
         estimate(Float.valueOf(d.getEstimate().floatValue() - estimateNum.floatValue()), d, map);
       }
 
       dinerBillDishe.setUnitNum(num.floatValue());
       Dishe dishe = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
       this.billService.calculatorSingleDishe(restId, dinerBill, dinerBillDishe, dishe);
       this.dinerBillDisheService.save(dinerBillDishe);
 
       map.put(dinerBillDishe.getBdId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBillDishe);
     }
     else
     {
       saveDishDelete(restId, billId, bdId, map);
     }
 
     this.billService.calculator(restId, dinerBill);
     this.self.save(dinerBill);
 
     map.put(dinerBill.getBillId() + "ujbm_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     String logNotes = "修改菜肴数量:";
     logNotes = logNotes + "，菜肴名称:" + dinerBillDishe.getDishesName() + "，原数量：" + oldNum + " 改为：" + dinerBillDishe.getUnitNum();
     saveBusiLog(restId, dinerBillDishe.getDinerBill(), BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
     saveDinerBillLog(restId, dinerBillDishe.getDinerBill(), BillOpTypeEnum.ORDER_DISH, logNotes, map);
 
     return StatusCodeEnum.SUCCESS.getCode();
   }
 
   public List<DinerBill> findByRestaurantAndCesId(Restaurant restaurant, String cesId) {
     return this.dinerBillDao.findByRestaurantAndCesId(restaurant, cesId);
   }
 
   public List<Map<String, String>> getTakeoutList(String restId, String billStatus, String sendTimeType, String startDate, String keywords, String isCount, int start, int pageSize)
   {
     return this.dinerBillMyDao.getTakeoutList(restId, billStatus, sendTimeType, startDate, keywords, isCount, start, pageSize);
   }
 
   public List<Map<String, String>> getTakeoutListSize(String restId, String billStatus, String sendTimeType, String startDate, String keywords)
   {
     return this.dinerBillMyDao.getTakeoutList(restId, billStatus, sendTimeType, startDate, keywords, TrueFalseEnum.TRUE.getCode(), 0, 0);
   }
 
   public long getTakeoutListForNoPayCount(String restId)
   {
     return this.dinerBillMyDao.getTakeoutListForNoPayCount(restId);
   }
 
   public List<Map<String, String>> getTakeoutListForNoPay(String restId)
   {
     return this.dinerBillMyDao.getTakeoutListForNoPay(restId);
   }
 
   public List<DinerBill> getTakeoutToday(String restId)
   {
     return this.dinerBillDao.getTakeoutToday(restId);
   }
 
   public BigDecimal getRealCostSumByRestaurantAndBillStatus(String restId, List<String> billStatus)
   {
     return this.dinerBillDao.getRealCostSumByRestaurantAndBillStatus(restId, billStatus);
   }
 
   public BigDecimal getPayableCostSumByRestaurantAndBillStatus(String restId, List<String> billStatus)
   {
     return this.dinerBillDao.getPayableCostSumByRestaurantAndBillStatus(restId, billStatus);
   }
 
   public Long getCountByBillId(String billId)
   {
     Long l = this.dinerBillMyDao.getCountByBillId(billId);
     if (l == null)
     {
       return Long.valueOf(0L);
     }
     return l;
   }
 }

