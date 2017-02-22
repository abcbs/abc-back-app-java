 package com.ndlan.canyin.frontdesk.front.employe;
 
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardOperationService;
import com.ndlan.canyin.frontdesk.service.qtsy.CostExpendService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillPaymentService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.EmployeShiftService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.frontdesk.util.UserSettingCacheSetting;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.entity.qtsy.CostExpend;
import com.ndlan.canyin.base.entity.qtsy.EmployeShift;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.CardOperationTypeEnum;
import com.ndlan.canyin.core.common.Constants;
import com.ndlan.canyin.core.common.DishesTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.SysDataTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.security.utils.Digests;
import com.ndlan.canyin.core.utils.BigDecimalUtil;
import com.ndlan.canyin.core.utils.Encodes;
import com.ndlan.canyin.core.vo.PaymentTypeVO;
import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailDatasVo;
import com.ndlan.canyin.core.vo.PrintSalesVolumeDetailTitleVo;
import com.ndlan.canyin.core.web.Servlets;
import com.ndlan.canyin.sharelogic.service.printer.ShiftPrinterService;

import java.awt.print.PrinterException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/employe"})
 @Lazy
 public class EmployeController extends BaseManageController
 {
   public static final int HASH_INTERATIONS = 1024;
 
   @Autowired
   private DinerBillPaymentService billPaymentService;
 
   @Autowired
   private MembershipCardOperationService cardOperationService;
 
   @Autowired
   private TableOrderService tableOrderService;
 
   @Autowired
   private EmployeShiftService employeShiftService;
 
   @Autowired
   private EmployeeService employeService;
 
   @Autowired
   private PaymentTypeService paymentTypeService;
 
   @Autowired
   private ShiftPrinterService shiftPrinterService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private CostExpendService costExpendService;
 
   @Autowired
   private DishesCategoryService dishesCategoryService;
 
   @Autowired
   private DishesSetCategoryService dishesSetCategoryService;
   private static Logger logger = LoggerFactory.getLogger(EmployeController.class);
 
   @RequestMapping({"shift"})
   public String shift(Model model, HttpServletRequest request ,String mobile) {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
     Date lastShiftTime = getShiftTime();
 
     BigDecimal cashMoney = new BigDecimal(0);
     List paymentTypeVOs = new ArrayList();
     List paymentTypeVOsMember = new ArrayList();
     List paymentTypeVOsOrder = new ArrayList();
     HashMap map = new HashMap();
     if (lastShiftTime != null) {
       cashMoney = setPaymentTypeVOForForm(lastShiftTime, paymentTypeVOs, paymentTypeVOsMember, paymentTypeVOsOrder, map);
     } else {
       lastShiftTime = new Date(0L);
       cashMoney = setPaymentTypeVOForForm(lastShiftTime, paymentTypeVOs, paymentTypeVOsMember, paymentTypeVOsOrder, map);
     }
 
     BigDecimal lastCurrentHandonCash = new BigDecimal(0);
     List employeShifts = this.employeShiftService.findByRestIdAndCreateEmployeeOrderByCreateTimeDesc(getCurrentUserRestId(), getCurrentUser());
     if ((employeShifts != null) && (employeShifts.size() > 0)) {
       EmployeShift lastEmployeShift = (EmployeShift)employeShifts.get(0);
 
       lastCurrentHandonCash = lastEmployeShift.getCurrentCash().subtract(lastEmployeShift.getCurrentHandonCash());
       if (lastCurrentHandonCash.compareTo(BigDecimal.ZERO) == 1) {
         model.addAttribute("lastCurrentHandonCash", lastCurrentHandonCash);
 
         cashMoney = cashMoney.add(lastCurrentHandonCash);
       }
     }
    
     cashMoney = tuikaBalance(lastShiftTime, cashMoney);
     cashMoney = BigDecimalUtil.format(cashMoney);
     model.addAttribute("currentCash", cashMoney);
     model.addAttribute("iosCurrentCash", cashMoney+"");
     model.addAttribute("lastShiftTime", lastShiftTime);
     model.addAttribute("iosLastShiftTime", sdf.format(lastShiftTime));
     model.addAttribute("shiftTime", sdf.format(new Date()));
     model.addAttribute("paymentTypeVOs", paymentTypeVOs);
     model.addAttribute("paymentTypeVOsForm", paymentTypeVOsMember);
     model.addAttribute("paymentTypeVOsOrder", paymentTypeVOsOrder);
     model.addAttribute("oddChangeSum", map.get("oddChangeSum"));
     model.addAttribute("employeeName", getCurrentUser().getName());
     if(mobile!=null&&mobile.equals("ios")){
     Double zengcaiMoney=0.0;
     //套餐
     Double comboMoney=this.billPaymentService.getdishes(lastShiftTime, getCurrentUserRestId());
     //普通菜品赠菜价格
     Double commonMoney=this.billPaymentService.getdishesSet(lastShiftTime, getCurrentUserRestId());
     	if(comboMoney!=null&&commonMoney!=null){
     		zengcaiMoney=Double.parseDouble(comboMoney+"")+Double.parseDouble(commonMoney+"");
     	}else{
     		if(comboMoney!=null){
       		 zengcaiMoney=Double.parseDouble(comboMoney+"");
       	 }else if(commonMoney!=null){
       		 zengcaiMoney=Double.parseDouble(commonMoney+"");
       	 }
     	}
     model.addAttribute("zengcaiDishesSetMoney", zengcaiMoney);
     }
     return "employe/shift";
   }
 
   @RequestMapping(value={"/ajax/shift/create"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone shiftCreate(@RequestParam(value="currentCash", required=true) String currentCash, @RequestParam(value="currentHandonCash", required=true) String currentHandonCash, @RequestParam(value="currentHandoffCash", required=false) String currentHandoffCash, @RequestParam(value="lastCurrentHandonCash", required=false) String lastCurrentHandonCash, @RequestParam(value="currentCashBalance", required=false) String currentCashBalance, @RequestParam(value="shiftTime", required=false) String shiftTime, @RequestParam(value="isPrint", required=true) String isPrint, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     EmployeShift employeShift = new EmployeShift();
     Date lastShiftTime = getShiftTime();
     lastShiftTime = lastShiftTime == null ? new Date(0L) : lastShiftTime;
 
     List paymentTypeVOsIncome = new ArrayList();
 
     List<PaymentTypeVO> paymentTypeVOs = new ArrayList();
 
     List<PaymentTypeVO> paymentTypeVOsMember = new ArrayList();
 
     List<PaymentTypeVO> paymentTypeVOsOrder = new ArrayList();
 
     setPaymentTypeVOForSave(lastShiftTime, paymentTypeVOsIncome);
 
     Map hashMap = new HashMap();
     setPaymentTypeVOForForm(lastShiftTime, paymentTypeVOs, paymentTypeVOsMember, paymentTypeVOsOrder, hashMap);
 
     employeShift.setLastShiftTime(lastShiftTime);
 
     if (currentCashBalance != null) {
       employeShift.setCurrentCashBalance(new BigDecimal(currentCashBalance));
     }
 
     if (hashMap.get("peopleNum") != null) {
       Integer peopleNum = Integer.valueOf(Integer.parseInt(hashMap.get("peopleNum").toString()));
       employeShift.setPeopleNum(peopleNum);
     }
 
     if (hashMap.get("totalServiceChargeMoney") != null) {
       BigDecimal serviceMoneySum = new BigDecimal(hashMap.get("totalServiceChargeMoney").toString());
       employeShift.setServiceMoneySum(serviceMoneySum);
     }
 
     if (hashMap.get("totalTuiCaiMoney") != null) {
       BigDecimal tuicaiMoneySum = new BigDecimal(hashMap.get("totalTuiCaiMoney").toString());
       employeShift.setTuicaiMoneySum(tuicaiMoneySum);
     }
 
     if (hashMap.get("totalZengCaiMoney") != null) {
       BigDecimal zengcaiMoneySum = new BigDecimal(hashMap.get("totalZengCaiMoney").toString());
       employeShift.setZengcaiMoneySum(zengcaiMoneySum);
     }
 
     try
     {
       JSONArray jsonArray = new JSONArray();
       for (PaymentTypeVO paymentTypeVO : paymentTypeVOs) {
         JSONObject json = new JSONObject();
         json.put("name", paymentTypeVO.getPaymentName());
         json.put("paymentType", paymentTypeVO.getPaymentType());
         json.put("money", paymentTypeVO.getMoney() == null ? BigDecimal.ZERO : paymentTypeVO.getMoney());
         jsonArray.put(json);
       }
       employeShift.setShiftPaymentDetail(jsonArray.toString());
     } catch (JSONException e) {
       e.printStackTrace();
     }
 
     if (hashMap.get("oddChangeSum") != null) {
       BigDecimal oddChangeSum = new BigDecimal(hashMap.get("oddChangeSum").toString());
       employeShift.setOddChangeSum(oddChangeSum);
     }
 
     if (hashMap.get("totalMolingModeCost") != null) {
       BigDecimal molingSum = new BigDecimal(hashMap.get("totalMolingModeCost").toString());
       employeShift.setMolingSum(molingSum);
     }
 
     if (hashMap.get("totalSaveCost") != null) {
       BigDecimal discountMoneySum = new BigDecimal(hashMap.get("totalSaveCost").toString());
       employeShift.setDiscountMoneySum(discountMoneySum);
     }
 
     if (hashMap.get("totalForceMoney") != null) {
       BigDecimal forcePaySum = new BigDecimal(hashMap.get("totalForceMoney").toString());
       employeShift.setForcePaySum(forcePaySum);
     }
 
     try
     {
       JSONArray jsonArrayCards = new JSONArray();
       for (PaymentTypeVO paymentTypeVO : paymentTypeVOsMember) {
         JSONObject json = new JSONObject();
         json.put("name", paymentTypeVO.getPaymentName());
         json.put("paymentType", paymentTypeVO.getPaymentType());
         json.put("money", paymentTypeVO.getMoney() == null ? BigDecimal.ZERO : paymentTypeVO.getMoney());
         jsonArrayCards.put(json);
       }
       employeShift.setCardPaymentDetail(jsonArrayCards.toString());
     } catch (JSONException e) {
       e.printStackTrace();
     }
 
     try
     {
       JSONArray jsonArrayOrders = new JSONArray();
       for (PaymentTypeVO paymentTypeVO : paymentTypeVOsOrder) {
         JSONObject json = new JSONObject();
         json.put("name", paymentTypeVO.getPaymentName());
         json.put("paymentType", paymentTypeVO.getPaymentType());
         json.put("money", paymentTypeVO.getMoney() == null ? BigDecimal.ZERO : paymentTypeVO.getMoney());
         jsonArrayOrders.put(json);
       }
       employeShift.setPrepayPaymentDetail(jsonArrayOrders.toString());
     } catch (JSONException e) {
       e.printStackTrace();
     }
 
     if (hashMap.get("totalUnPayBillCost") != null) {
       BigDecimal unPaybillcostSum = new BigDecimal(hashMap.get("totalUnPayBillCost").toString());
       employeShift.setUnPaybillcostSum(unPaybillcostSum);
     }
 
     employeShift.setRestId(getCurrentUserRestId());
     employeShift.setCurrentCash(new BigDecimal(currentCash));
     employeShift.setCurrentHandonCash(new BigDecimal(currentHandonCash));
     if (StringUtils.isNotEmpty(lastCurrentHandonCash)) {
       employeShift.setLastBalanceCash(new BigDecimal(lastCurrentHandonCash));
     }
     if (StringUtils.isNotEmpty(currentHandoffCash)) {
       employeShift.setCurrentHandoffCash(new BigDecimal(currentHandoffCash));
     }
     LinkedHashMap map = new LinkedHashMap();
 
     employeShift = this.employeShiftService.saveShift(employeShift, paymentTypeVOsIncome, getCurrentUserRestId(), map);
 
     doSynchMultiTable(map);
 
     shiftTime = sdf.format(employeShift.getCreateTime());
     if (TrueFalseEnum.TRUE.getCode().equals(isPrint)) {
       try {
         String lastStr = sdf.format(lastShiftTime);
         Object paymentTypeVOsList = new ArrayList();
         List paymentTypeVOsMemberList = new ArrayList();
         List paymentTypeVOsOrderList = new ArrayList();
         Map dataMap = new HashMap();
         setPaymentTypeVOForForm(lastShiftTime, (List)paymentTypeVOsList, paymentTypeVOsMemberList, paymentTypeVOsOrderList, dataMap);
         HashMap printParaments = new HashMap();
         printParaments.put("printerId", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId());
         this.shiftPrinterService.printShift(employeShift, currentCashBalance, shiftTime, lastStr, (List)paymentTypeVOsList, paymentTypeVOsMemberList, paymentTypeVOsOrderList, dataMap, printParaments);
       } catch (PrinterException e) {
         e.printStackTrace();
         ajax.setMessage("交班打印失败!");
         return ajax;
       }
     }
 
     ajax.setStatusCode("200");
     ajax.setMessage("交班成功");
     ajax.setRel("");
     return (DwzAjaxDone)ajax;
   }
 
   @RequestMapping(value={"/password"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popUpdatePsd(Model model) {
     model.addAttribute("empId", getCurrentUserId());
     return "employe/updatePsd";
   }
 
   @RequestMapping(value={"/ajax/psdUpdate"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone pswUpdate(@RequestParam(value="id", required=false) String id, @RequestParam(value="loginPassword", required=true) String loginPassword, @RequestParam(value="oldLoginPassword", required=true) String oldLoginPassword, Model model)
   {
     Employee employee = (Employee)this.employeService.getOne(id);
     byte[] salt = Encodes.decodeHex(employee.getSalt());
     byte[] hashPassword = Digests.sha1(oldLoginPassword.getBytes(), salt, 1024);
     String encodePassword = Encodes.encodeHex(hashPassword);
 
     if (!encodePassword.equals(employee.getLoginPassword())) {
       DwzAjaxDone ajax = new DwzAjaxDone();
       ajax.setStatusCode("400");
       ajax.setMessage("原始密码错误");
       ajax.setRel("");
       return ajax;
     }
     employee.setLoginPassword(loginPassword);
     employeService.entryptPassword(employee);
     DwzAjaxDone ajax = new DwzAjaxDone();
     this.employeService.save(employee);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), employee);
 
     ajax.setStatusCode("200");
     ajax.setMessage("修改成功");
     ajax.setRel("");
     return ajax;
   }
 
   private Date getShiftTime()
   {
     List employeShifts = this.employeShiftService.findByRestIdAndCreateEmployeeOrderByCreateTimeDesc(getCurrentUserRestId(), getCurrentUser());
     Date shiftTime = null;
     if ((employeShifts != null) && (employeShifts.size() > 0)) {
       EmployeShift employeShift = (EmployeShift)employeShifts.get(0);
       if ((employeShift != null) && (employeShift.getCreateTime() != null)) {
         shiftTime = employeShift.getCreateTime();
       }
     }
     return shiftTime;
   }
 
   private BigDecimal setPaymentTypeVOForSave(Date shiftTime, List<PaymentTypeVO> paymentTypeVOs)
   {
     List<PaymentType> paymentTypes = this.paymentTypeService.findPaymentTypeByAllRestID(getCurrentUserRestId());
     BigDecimal cashMoney = new BigDecimal(0);
     BigDecimal money = new BigDecimal(0);
     for (PaymentType paymentType : paymentTypes) {
       PaymentTypeVO paymentTypeVO = new PaymentTypeVO();
       paymentTypeVO.setPaymentType(paymentType.getPaymentType());
       paymentTypeVO.setPaymentName(paymentType.getPaymentName());
       paymentTypeVO.setCptId(paymentType.getCptId());
       paymentTypeVO.setEnableStatus(paymentType.getEnableStatus());
       money = BigDecimal.ZERO;
       if ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) || (PaymentTypeEnum.CARD.getCode().equals(paymentType.getPaymentType())))
       {
         Object[] billMoney = this.billPaymentService.getMoneySumByCptId(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getCptId());
 
         BigDecimal rechargeMoney = this.cardOperationService.getPaidinCashSumByPaymentType(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getPaymentType(), CardOperationTypeEnum.RECHARGE.getCode());
 
         BigDecimal pledgeMoney = this.cardOperationService.getCashPledgeSumByPaymentType(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getPaymentType(), CardOperationTypeEnum.CASH_PLEDGE.getCode());
 
         BigDecimal prepayMoney = this.tableOrderService.getPrepaySumByPaymentType(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getPaymentType());
         money = billMoney[0] == null ? money : money.add((BigDecimal) billMoney[0]);
         money = rechargeMoney == null ? money : money.add(rechargeMoney);
         money = pledgeMoney == null ? money : money.add(pledgeMoney);
         money = prepayMoney == null ? money : money.add(prepayMoney);
 
         if ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) && (SysDataTypeEnum.DEFAULT.getCode().equals(paymentType.getSysdataType()))) {
           cashMoney = cashMoney.add(money);
 
           BigDecimal oddChangeSum = this.dinerBillService.getOddChangeSum(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
           if (oddChangeSum != null)
           {
             cashMoney = cashMoney.subtract(oddChangeSum);
           }
           if (oddChangeSum != null)
           {
             money = money.subtract(oddChangeSum);
           }
         }
       }
       else {
         Object[] billPayment = this.billPaymentService.getMoneySumByCptId(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getCptId());
         money = billPayment[0] == null ? money : money.add((BigDecimal) billPayment[0]);
       }
 
       if (PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) {
         money = tuikaBalance(shiftTime, money);
       }
 
       if (money.compareTo(BigDecimal.ZERO) != 0) {
         paymentTypeVO.setMoney(money);
       }
       paymentTypeVOs.add(paymentTypeVO);
     }
 
     return cashMoney;
   }
 
   private BigDecimal setPaymentTypeVOForForm(Date shiftTime, List<PaymentTypeVO> paymentTypeVOs, List<PaymentTypeVO> paymentTypeVOsMember, List<PaymentTypeVO> paymentTypeVOsOrder, Map<String, Object> model)
   {
     List<PaymentType> paymentTypes = this.paymentTypeService.findPaymentTypeByAllRestID(getCurrentUserRestId());
 
     BigDecimal cashMoney = new BigDecimal(0);
 
     BigDecimal currentMoneySum = new BigDecimal(0);
 
     BigDecimal memberMoneySum = new BigDecimal(0);
 
     BigDecimal money = new BigDecimal(0);
 
     Long peopleNum = Long.valueOf(0L);
 
     BigDecimal orderForegiftSum = BigDecimal.ZERO;
 
     for (PaymentType paymentType : paymentTypes) {
       PaymentTypeVO paymentTypeVO = new PaymentTypeVO();
       paymentTypeVO.setPaymentType(paymentType.getPaymentType());
       paymentTypeVO.setPaymentName(paymentType.getPaymentName());
       paymentTypeVO.setCptId(paymentType.getCptId());
       paymentTypeVO.setEnableStatus(paymentType.getEnableStatus());
       money = BigDecimal.ZERO;
       String iosPeopleNum ="";  //就餐人数
       String realCost =""; //实收金额
       String molingModeCost ="";  //抹零金额
       if ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) || (PaymentTypeEnum.CARD.getCode().equals(paymentType.getPaymentType())))
       {
         Object[] billMoney = this.billPaymentService.getMoneySumByCptId(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getCptId());
         money = billMoney[0] == null ? money : money.add((BigDecimal) billMoney[0]);
         iosPeopleNum=billMoney[1]+"";
         realCost=billMoney[2]+"";
         molingModeCost=billMoney[3]+"";
         if ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) && (SysDataTypeEnum.DEFAULT.getCode().equals(paymentType.getSysdataType())))
           cashMoney = cashMoney.add(money);
       }
       else
       {
    	   Object[] billPayment = this.billPaymentService.getMoneySumByCptId(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getCptId());
         money = billPayment[0] == null ? money : money.add((BigDecimal) billPayment[0]);
         iosPeopleNum=billPayment[1]+"";
         realCost=billPayment[2]+"";
         molingModeCost=billPayment[3]+"";
       }
       if (money.compareTo(BigDecimal.ZERO) != 0) {
         paymentTypeVO.setMoney(money);
         paymentTypeVO.setPeopleNum(iosPeopleNum);
         paymentTypeVO.setMolingModeCost(molingModeCost);
         paymentTypeVO.setRealCost(realCost);
         currentMoneySum = currentMoneySum.add(money);
       }
       paymentTypeVOs.add(paymentTypeVO);
     }
 
     List paymentTypeStr = new ArrayList();
     paymentTypeStr.add(PaymentTypeEnum.CASH.getCode());
     paymentTypeStr.add(PaymentTypeEnum.CARD.getCode());
     List<PaymentType> paymentTypesMember = this.paymentTypeService.findByRestIdAndEnableStatusAndPaymentTypeIn(getCurrentUserRestId(), paymentTypeStr);
     BigDecimal moneyMember = new BigDecimal(0);
     BigDecimal cashMoneyMember = new BigDecimal(0);
     BigDecimal rechargeMoney;
     for (PaymentType paymentType : paymentTypesMember) {
       PaymentTypeVO paymentTypeVO = new PaymentTypeVO();
       paymentTypeVO.setPaymentType(paymentType.getPaymentType());
       paymentTypeVO.setPaymentName(paymentType.getPaymentName());
       paymentTypeVO.setCptId(paymentType.getCptId());
       paymentTypeVO.setEnableStatus(paymentType.getEnableStatus());
       moneyMember = BigDecimal.ZERO;
 
       rechargeMoney = this.cardOperationService.getPaidinCashSumByPaymentType(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getPaymentType(), CardOperationTypeEnum.RECHARGE.getCode());
 
       BigDecimal pledgeMoney = this.cardOperationService.getCashPledgeSumByPaymentType(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getPaymentType(), CardOperationTypeEnum.CASH_PLEDGE.getCode());
 
       moneyMember = rechargeMoney == null ? moneyMember : moneyMember.add(rechargeMoney);
       moneyMember = pledgeMoney == null ? moneyMember : moneyMember.add(pledgeMoney);
 
       if ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) && (SysDataTypeEnum.DEFAULT.getCode().equals(paymentType.getSysdataType()))) {
         cashMoneyMember = cashMoneyMember.add(moneyMember);
       }
 
       if (PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) {
         moneyMember = tuikaBalance(shiftTime, moneyMember);
       }
 
       if (moneyMember.compareTo(BigDecimal.ZERO) != 0) {
         paymentTypeVO.setMoney(moneyMember);
         memberMoneySum = memberMoneySum.add(moneyMember);
       }
       paymentTypeVOsMember.add(paymentTypeVO);
     }
 
     List paymentTypeListOrder = new ArrayList();
     paymentTypeListOrder.add(PaymentTypeEnum.CASH.getCode());
     paymentTypeListOrder.add(PaymentTypeEnum.CARD.getCode());
     paymentTypeListOrder.add(PaymentTypeEnum.WEB_PAY.getCode());
     List<PaymentType> paymentTypesOrder = this.paymentTypeService.findByRestIdAndEnableStatusAndPaymentTypeIn(getCurrentUserRestId(), paymentTypeListOrder);
 
     for (PaymentType paymentType : paymentTypesOrder) {
       PaymentTypeVO paymentTypeVO = new PaymentTypeVO();
       paymentTypeVO.setPaymentType(paymentType.getPaymentType());
       paymentTypeVO.setPaymentName(paymentType.getPaymentName());
       paymentTypeVO.setCptId(paymentType.getCptId());
       paymentTypeVO.setEnableStatus(paymentType.getEnableStatus());
 
       BigDecimal prepayMoney = this.tableOrderService.getPrepaySumByPaymentType(getCurrentUserRestId(), getCurrentUserId(), shiftTime, paymentType.getPaymentType());
       prepayMoney = prepayMoney == null ? BigDecimal.ZERO : prepayMoney;
       if ((PaymentTypeEnum.CASH.getCode().equals(paymentType.getPaymentType())) && (SysDataTypeEnum.DEFAULT.getCode().equals(paymentType.getSysdataType()))) {
         cashMoney = cashMoney.add(prepayMoney);
       }
       if (prepayMoney.compareTo(BigDecimal.ZERO) != 0) {
         paymentTypeVO.setMoney(prepayMoney);
         orderForegiftSum = orderForegiftSum.add(prepayMoney);
       }
       paymentTypeVOsOrder.add(paymentTypeVO);
     }
 
     peopleNum = this.dinerBillService.getPeopleNumSum(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
     peopleNum = Long.valueOf(peopleNum == null ? 0L : peopleNum.longValue());
 
     BigDecimal oddChangeSum = this.dinerBillService.getOddChangeSum(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
     if (oddChangeSum != null)
     {
       cashMoney = cashMoney.subtract(oddChangeSum);
       currentMoneySum = currentMoneySum.subtract(oddChangeSum);
     }
 
     BigDecimal totalMolingModeCost = this.billPaymentService.getCurrentTotalMolingModeCost(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
 
     BigDecimal totalSaveCost = this.billPaymentService.getCurrentTotalSaveCost(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
 
     BigDecimal totalServiceChargeMoney = this.billPaymentService.getCurrentTotalServiceChargeMoney(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
 
     BigDecimal totalTuiCaiMoney = this.billPaymentService.getCurrentTotalTuiCaiMoney(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
 
     BigDecimal totalZengCaiMoney = this.billPaymentService.getCurrentTotalZengCaiMoney(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
 
     BigDecimal totalUnPayBillCost = this.billPaymentService.getCurrentTotalUnPayBillCost(getCurrentUserRestId(), getCurrentUserId(), shiftTime);
 
     BigDecimal totalForceMoney = this.dinerBillService.getForceMoneySum(getCurrentUserRestId(), getCurrentUserId(), shiftTime, BillStatusEnum.SETTLE.getCode(), TrueFalseEnum.TRUE.getCode(), TrueFalseEnum.TRUE.getCode());
 
     model.put("oddChangeSum", oddChangeSum);
     model.put("totalForceMoney", totalForceMoney);
     model.put("currentMoneySum", currentMoneySum);
     model.put("memberMoneySum", memberMoneySum);
     model.put("peopleNum", peopleNum);
     model.put("totalMolingModeCost", totalMolingModeCost);
     model.put("totalSaveCost", totalSaveCost);
     model.put("totalServiceChargeMoney", totalServiceChargeMoney);
     model.put("totalTuiCaiMoney", totalTuiCaiMoney);
     model.put("totalZengCaiMoney", totalZengCaiMoney);
     model.put("totalUnPayBillCost", totalUnPayBillCost);
     model.put("orderForegiftSum", orderForegiftSum);
 
     return cashMoney.add(cashMoneyMember);
   }
 
   private BigDecimal tuikaBalance(Date lastShiftTime, BigDecimal cashMoney)
   {
     BigDecimal tuikaBalance = this.cardOperationService.getBalanceSum(getCurrentUserRestId(), getCurrentUserId(), lastShiftTime, CardOperationTypeEnum.CANCEL_CARD.getCode());
 
     BigDecimal tuikaCashPledge = this.cardOperationService.getCashPledgeSum(getCurrentUserRestId(), getCurrentUserId(), lastShiftTime, CardOperationTypeEnum.CANCEL_CARD.getCode());
     cashMoney = tuikaBalance == null ? cashMoney : cashMoney.subtract(tuikaBalance);
     cashMoney = tuikaCashPledge == null ? cashMoney : cashMoney.subtract(tuikaCashPledge);
     return cashMoney;
   }
 
   @ModelAttribute
   public void getEntity(@RequestParam(value="id", required=false) String id, Model model)
   {
     if ((id != null) && (!id.isEmpty())) {
       Employee employe = (Employee)this.employeService.getOne(id);
       model.addAttribute("employe", employe);
     }
   }
 
   @RequestMapping({"costExpend"})
   public String costExpend(@RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="tabNo", defaultValue="0") String tabNo, Model model, ServletRequest request, String endDate, String startDate, String yearTime, String season, String endSeason, String seasonYear)
     throws Exception
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     Page costExpends = this.costExpendService.searchWithTime(getCurrentUserRestId(), startDate, endDate, searchParams, pageNumber, Constants.PAGE_SIZE, sortType);
     model.addAttribute("costExpends", costExpends);
     model.addAttribute("startDate", startDate);
     model.addAttribute("endDate", endDate);
     return "employe/costExpend";
   }
 
   @RequestMapping({"costExpendEntering"})
   public String costExpendEntering(Model model, HttpServletRequest request, @RequestParam(value="id", defaultValue="") String id, String toEdit)
   {
     CostExpend costExpend = null;
     if (!"".equals(id))
       costExpend = (CostExpend)this.costExpendService.getOne(id);
     else {
       costExpend = new CostExpend();
     }
     model.addAttribute("costExpend", costExpend);
     model.addAttribute("id", id);
     model.addAttribute("toEdit", toEdit);
     return "employe/costExpendEntering";
   }
 
   @RequestMapping({"costExpendsubmit/create"})
   public String costExpendsubmit(CostExpend newCostExpend, RedirectAttributes redirectAttributes, @RequestParam(value="editStr", required=false) String editStr)
     throws Exception
   {
     this.costExpendService.save(newCostExpend);
 
     doSynchSingleTable(OperationTypeEnum.CREATE.getCode(), newCostExpend);
 
     redirectAttributes.addFlashAttribute("message", "成本支出 录入成功");
     return "redirect:/employe/costExpend";
   }
 
   @RequestMapping({"costExpendsubmit/update"})
   public String costExpendsubmitUpdate(@ModelAttribute("costExpend") CostExpend newCostExpend, RedirectAttributes redirectAttributes, @RequestParam(value="editStr", required=false) String editStr)
     throws Exception
   {
     this.costExpendService.save(newCostExpend);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), newCostExpend);
 
     redirectAttributes.addFlashAttribute("message", "成本支出 录入成功");
     return "redirect:/employe/costExpend";
   }
 
   @RequestMapping({"costExpendDelete/{id}"})
   public String costExpendDelete(@PathVariable("id") String id, @RequestParam(value="pn", required=false) String pn, @RequestParam(value="pv", required=false) String pv, RedirectAttributes redirectAttributes, HttpServletRequest request)
   {
     String message = "删除成功";
     CostExpend costExpend = (CostExpend)this.costExpendService.getOne(id);
     try {
       Method method = costExpend.getClass().getMethod("set" + pn, new Class[] { BigDecimal.class });
       method.invoke(costExpend, new Object[1]);
       this.costExpendService.save(costExpend);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), costExpend);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     redirectAttributes.addFlashAttribute("message", message);
     return "redirect:/employe/costExpend";
   }
 
   @RequestMapping({"sales"})
   public String sales(Model model, HttpServletRequest request, @RequestParam(value="startDate", required=false, defaultValue="") String startDate, @RequestParam(value="endDate", required=false, defaultValue="") String endDate)
   {
     if ((StringUtils.isEmpty(startDate)) && (StringUtils.isEmpty(endDate))) {
       Date nowDate = new Date();
       startDate = DateUtil.shortSdf.format(nowDate) + " 00:00";
       endDate = DateUtil.toStringNoSecond(nowDate);
     }
     List disheSales = this.employeService.getDisheSales(getCurrentUserRestId(), startDate, endDate);
     List disheSetSales = this.employeService.getDisheSetSales(getCurrentUserRestId(), startDate, endDate);
 
     model.addAttribute("startDate", startDate);
     model.addAttribute("endDate", endDate);
     model.addAttribute("disheSales", disheSales);
     model.addAttribute("disheSetSales", disheSetSales);
     return "employe/sales";
   }
 
   @RequestMapping(value={"pop/dishesSalesDetail"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String disheSalesDetail(Model model, @RequestParam(value="categoryId", required=false, defaultValue="") String categoryId, @RequestParam(value="startDate", required=false, defaultValue="") String startDate, @RequestParam(value="endDate", required=false, defaultValue="") String endDate)
   {
     DishesCategory dishesCategory = (DishesCategory)this.dishesCategoryService.getOne(categoryId);
     List dishesSalesDetailList = this.employeService.getDishesSalesDetail(getCurrentUserRestId(), startDate, endDate, categoryId);
     model.addAttribute("categoryName", dishesCategory.getCategoryName());
     model.addAttribute("categoryId", dishesCategory.getCategoryId());
     model.addAttribute("startDate", startDate);
     model.addAttribute("endDate", endDate);
     model.addAttribute("dishesSalesDetailList", dishesSalesDetailList);
     return "employe/dishesSalesDetail";
   }
 
   @RequestMapping(value={"pop/dishesSetSalesDetail"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String dishesSetSalesDetail(Model model, @RequestParam(value="dsCategoryId", required=false, defaultValue="") String dsCategoryId, @RequestParam(value="startDate", required=false, defaultValue="") String startDate, @RequestParam(value="endDate", required=false, defaultValue="") String endDate)
   {
     DishesSetCategory dishesSetCategory = (DishesSetCategory)this.dishesSetCategoryService.getOne(dsCategoryId);
     List dishesSetSalesDetailList = this.employeService.getDishesSetSalesDetail(getCurrentUserRestId(), startDate, endDate, dsCategoryId);
     model.addAttribute("dsCategoryId", dishesSetCategory.getDsCategoryId());
     model.addAttribute("categoryName", dishesSetCategory.getCategoryName());
     model.addAttribute("startDate", startDate);
     model.addAttribute("endDate", endDate);
     model.addAttribute("dishesSetSalesDetailList", dishesSetSalesDetailList);
     return "employe/dishesSetSalesDetail";
   }
 
   @RequestMapping(value={"printDishesSalesDetail/{categoryId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone printDishesSalesDetail(@PathVariable("categoryId") String categoryId, @RequestParam(value="startDate", required=false, defaultValue="") String startDate, @RequestParam(value="endDate", required=false, defaultValue="") String endDate, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     try {
       String restId = getCurrentUserRestId();
       Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
       String printerId = UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId();
       List<Map<String,Object>> dishesSalesDetailList = this.employeService.getDishesSalesDetail(restId, startDate, endDate, categoryId);
 
       PrintSalesVolumeDetailTitleVo titleInfo = new PrintSalesVolumeDetailTitleVo();
       DishesCategory dishesCategory = (DishesCategory)this.dishesCategoryService.getOne(categoryId);
       titleInfo.setCategoryName(dishesCategory.getCategoryName());
       titleInfo.setDateEnd(endDate);
       titleInfo.setDateStart(startDate);
       titleInfo.setOperationDate(DateUtil.toString(new Date()));
       titleInfo.setOperationName(getCurrentUser().getName());
       titleInfo.setPrinterId(printerId);
       titleInfo.setRestId(restId);
       titleInfo.setType(DishesTypeEnum.DISHES.getCode());
       titleInfo.setRestName(restaurant.getRestName());
 
       List detailInfos = new ArrayList();
       for (Map map : dishesSalesDetailList) {
         PrintSalesVolumeDetailDatasVo detailDatasVo = new PrintSalesVolumeDetailDatasVo();
         detailDatasVo.setDishName(map.get("dishesName").toString());
         detailDatasVo.setSalesVolume(BigDecimalUtil.formatDouble(Double.valueOf(new StringBuilder().append(map.get("unitNumSum")).toString())));
         detailDatasVo.setSumMoney(BigDecimalUtil.formatDouble(Double.valueOf(new StringBuilder().append(map.get("oriCostSum")).toString())));
         detailInfos.add(detailDatasVo);
       }
       this.shiftPrinterService.printSalesVolumeDetail(titleInfo, detailInfos);
     } catch (Exception e) {
       e.printStackTrace();
       logger.error(e.getMessage());
       ajax.setMessage("打印失败");
       ajax.setStatusCode(TrueFalseEnum.FALSE.getCode());
       return ajax;
     }
     ajax.setStatusCode(TrueFalseEnum.TRUE.getCode());
     ajax.setMessage("打印成功");
     return ajax;
   }
 
   @RequestMapping(value={"printDishesSetSalesDetail/{dsCategoryId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone printDishesSetSalesDetail(@PathVariable("dsCategoryId") String dsCategoryId, @RequestParam(value="startDate", required=false, defaultValue="") String startDate, @RequestParam(value="endDate", required=false, defaultValue="") String endDate, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     try {
       String restId = getCurrentUserRestId();
       Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
       String printerId = UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId();
       List<Map<String,Object>> dishesSetSalesDetailList = this.employeService.getDishesSetSalesDetail(getCurrentUserRestId(), startDate, endDate, dsCategoryId);
 
       PrintSalesVolumeDetailTitleVo titleInfo = new PrintSalesVolumeDetailTitleVo();
       DishesSetCategory dishesSetCategory = (DishesSetCategory)this.dishesSetCategoryService.getOne(dsCategoryId);
       titleInfo.setCategoryName(dishesSetCategory.getCategoryName());
       titleInfo.setDateEnd(endDate);
       titleInfo.setDateStart(startDate);
       titleInfo.setOperationDate(DateUtil.toString(new Date()));
       titleInfo.setOperationName(getCurrentUser().getName());
       titleInfo.setPrinterId(printerId);
       titleInfo.setRestId(restId);
       titleInfo.setType(DishesTypeEnum.DISHES_SET.getCode());
       titleInfo.setRestName(restaurant.getRestName());
 
       List detailInfos = new ArrayList();
       for (Map map : dishesSetSalesDetailList) {
         PrintSalesVolumeDetailDatasVo detailDatasVo = new PrintSalesVolumeDetailDatasVo();
         detailDatasVo.setDishName(map.get("dsName").toString());
         detailDatasVo.setSalesVolume(BigDecimalUtil.formatDouble(Double.valueOf(new StringBuilder().append(map.get("unitNumSum")).toString())));
         detailDatasVo.setSumMoney(BigDecimalUtil.formatDouble(Double.valueOf(new StringBuilder().append(map.get("oriCostSum")).toString())));
         detailInfos.add(detailDatasVo);
       }
       this.shiftPrinterService.printSalesVolumeDetail(titleInfo, detailInfos);
     } catch (Exception e) {
       e.printStackTrace();
       logger.error(e.getMessage());
       ajax.setMessage("打印失败");
       ajax.setStatusCode(TrueFalseEnum.FALSE.getCode());
       return ajax;
     }
     ajax.setStatusCode(TrueFalseEnum.TRUE.getCode());
     ajax.setMessage("打印成功");
     return ajax;
   }
 
   @ModelAttribute
   public void getEntityInDB(@RequestParam(value="id", required=false) String id, Model model)
   {
     if ((id != null) && (!id.isEmpty())) {
       CostExpend costExpend = (CostExpend)this.costExpendService.getOne(id);
       model.addAttribute("costExpend", costExpend);
     }
   }
 }

