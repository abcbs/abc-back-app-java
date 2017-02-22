 package com.ndlan.canyin.frontdesk.front.bill;
 
 import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


















import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Printer;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.qtsy.Coupons;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishe;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.qtsy.Takeout;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.entity.sygl.CashSetting;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.entity.sygl.SpeOpReason;
import com.ndlan.canyin.core.common.BillOpTypeEnum;
import com.ndlan.canyin.core.common.BillPlaceEnterPageEnum;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.BillTypeEnum;
import com.ndlan.canyin.core.common.BussLogTypeEnum;
import com.ndlan.canyin.core.common.CategoryLevelEnum;
import com.ndlan.canyin.core.common.CreditStatusEnum;
import com.ndlan.canyin.core.common.DataLogActEnum;
import com.ndlan.canyin.core.common.DataLogTypeEnum;
import com.ndlan.canyin.core.common.DiscountTypeEnum;
import com.ndlan.canyin.core.common.DishesStatusEnum;
import com.ndlan.canyin.core.common.EnableStatusEnum;
import com.ndlan.canyin.core.common.MemberCardPayTypeEnum;
import com.ndlan.canyin.core.common.MolingModeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.OrderStatusEnum;
import com.ndlan.canyin.core.common.PaymentTypeEnum;
import com.ndlan.canyin.core.common.PrinterTypeEnum;
import com.ndlan.canyin.core.common.SmtCodeEnum;
import com.ndlan.canyin.core.common.SpecialReasonTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.BigDecimalUtil;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.utils.Print;
import com.ndlan.canyin.core.vo.DishesSetDishesVo;
import com.ndlan.canyin.core.web.Servlets;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.bill.BillService;
import com.ndlan.canyin.frontdesk.service.cloud.CloudBillService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.PrinterService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesAvoidfoodService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetDishesService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesTasteService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesUnitService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.qtsy.CouponsService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillPaymentService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.DishesSetDishesReplaceService;
import com.ndlan.canyin.frontdesk.service.qtsy.OrderBillDishesService;
import com.ndlan.canyin.frontdesk.service.qtsy.OrderBillDishesSetService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.frontdesk.service.qtsy.TakeoutService;
import com.ndlan.canyin.frontdesk.service.sygl.CashDiscountService;
import com.ndlan.canyin.frontdesk.service.sygl.CashSettingService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDishesSetService;
import com.ndlan.canyin.frontdesk.service.sygl.SpeOpReasonService;
import com.ndlan.canyin.frontdesk.service.xtgl.DataLogService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.frontdesk.util.ZiZhuUtil;
import com.ndlan.canyin.mobileserver.G2.IosBillController;
import com.ndlan.canyin.sharelogic.service.printer.CuicaiPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.DinerBillPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.PayPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.TuicaiPrinterService;
import com.ndlan.canyin.sharelogic.service.printer.XiadanPrinterService;
import com.ndlan.canyin.sharelogic.util.MessageDataUtil;
import com.ndlan.canyin.sharelogic.util.PrintUtil;
import com.ndlan.canyin.sharelogic.util.PriorityExecutor;
 
 @Controller
 @RequestMapping({"/bill"})
 @Lazy
 public class BillController extends BaseManageController
 {
 
   @Autowired
   private DisheService disheService;
 
   @Autowired
   private DishesCategoryService dishesCategoryService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   private DinerBillDisheService dinerBillDisheService;
 
   @Autowired
   private BillService billService;
 
   @Autowired
   private PaymentTypeService paymentTypeService;
 
   @Autowired
   private DinerBillPaymentService dinerBillPaymentService;
 
   @Autowired
   private MembershipCardService membershipCardService;
 
   @Autowired
   private CashDiscountService cashDiscountService;
 
   @Autowired
   private DishesAvoidfoodService dishesAvoidfoodService;
 
   @Autowired
   private DishesTasteService dishesTasteService;
 
   @Autowired
   private SpeOpReasonService speOpReasonService;
 
   @Autowired
   private DishesUnitService dishesUnitService;
 
   @Autowired
   private TableOrderService tableOrderService;
 
   @Autowired
   private OrderBillDishesService orderBillDishesService;
 
   @Autowired
   private PrinterService printerService;
 
   @Autowired
   private CashSettingService cashSettingService;
 
   @Autowired
   private EmployeeService employeeService;
 
   @Autowired
   private XiadanPrinterService xiadanPrinterService;
 
   @Autowired
   private PayPrinterService payPrinterService;
 
   @Autowired
   private TuicaiPrinterService tuicaiPrinterService;
 
   @Autowired
   private CuicaiPrinterService cuicaiPrinterService;
 
   @Autowired
   private DinerBillPrinterService dinerBillPrinterService;
 
   @Autowired
   private DishesSetService dishesSetService;
 
   @Autowired
   private DinerBillDishesSetService dinerBillDishesSetService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   DishesSetCategoryService dishesSetCategoryService;
 
   @Autowired
   DishesSetDishesReplaceService dishesSetDishesReplaceService;
 
   @Autowired
   DishesSetDishesService dishesSetDishesService;
 
   @Autowired
   OrderBillDishesSetService orderBillDishesSetService;
 
   @Autowired
   TakeoutService takeoutService;
 
   @Autowired
   CloudBillService cloudBillService;
   
   @Autowired 
   CouponsService couponsService;
 
   @Autowired
   private DataLogService dataLogService;
   public static final String MONEY = "MONEY";
   public static final String CARD = "CARD";
   public static final String CREDIT = "CREDIT";
   public static final String WEBSITE = "WEBSITE";
   public static final String OTHER = "OTHER";
   private static Logger logger = LoggerFactory.getLogger(BillController.class);
   
   
   
   @RequestMapping(value={"barPay"}, produces={"application/json"})
   public void barPay( String out_trade_no,String auth_code ,String total_amount,String subject, Model model,String paymentType)
     throws JsonProcessingException
   {
 	  paymentType="402881fa4fca616c014fca87c0d40001";//支付宝类型id
 	  ToAlipayBarTradePay toalipaybartradepay=new ToAlipayBarTradePay(); 
 		AlipayTradePayResponse response = toalipaybartradepay.barPay(out_trade_no, auth_code, total_amount, subject,"");
 		if (null != response && response.isSuccess()) {
 			if (response.getCode().equals("10000")) {
 				//yes
 				savePayment(out_trade_no,  paymentType, total_amount,1);
 			}else if (response.getCode().equals(
 			"10003")) {
 				//提示输入密码等待操作中 
 				savePayment(out_trade_no,  paymentType, total_amount,0);
 			}
 		}
   }
   
   public void savePayment(String out_trade_no,String paymentType,String total_amount,int isSuc){
	   LinkedHashMap map = new LinkedHashMap();
	   String subType = getSubPaymentType(paymentType);
	   this.dinerBillPaymentService.savePayments(subType, getCurrentUserRestId(), out_trade_no, null, null, null, paymentType, total_amount, null, map,isSuc);
   }
   
   
   
   
   @RequestMapping({"list"})
   public String list(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="keywords", required=false) String keywords, Model model, HttpServletRequest request)
   {
     model.addAttribute("billId", billId);
     model.addAttribute("keywords", keywords);
     return "bill/list";
   }
 /**
  * 账单搜索
  * @param billId
  * @param pageNumber
  * @param pageSize
  * @param billStatus
  * @param pageType
  * @param billType
  * @param keywords
  * @param startDate //后加上
  * @param endDate//后加上
  * @param model
  * @param request
  * @return
  */
   @RequestMapping({"ajax/listBillsContent"})
   public String listBillsContent(@RequestParam(value="billId", defaultValue="1") String billId, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pageSize", defaultValue="15") int pageSize, @RequestParam(value="billStatus", defaultValue="") String billStatus, @RequestParam(value="pageType", defaultValue="today") String pageType, @RequestParam(value="billType", defaultValue="") String billType, 
		   @RequestParam(value="keywords", defaultValue="", required=false) String keywords,
		   String startDate,String endDate,
		   Model model, HttpServletRequest request)
   {
     Page<DinerBill> dinerBillList = this.dinerBillService.searchDinerBill(getCurrentUserRestId(), billStatus, pageNumber, pageSize, keywords, pageType, billType,startDate,endDate);
     for (DinerBill dinerBill : dinerBillList.getContent()) {
       this.billService.calculator(getCurrentUserRestId(), dinerBill);
       if(dinerBill.getCreateEmployee()!=null&&dinerBill.getCreateEmployee().getName()!=null){
    	   dinerBill.setCreateEmployeeName(dinerBill.getCreateEmployee().getName());
       }
       if(dinerBill.getCashierEmployee()!=null&&dinerBill.getCashierEmployee().getName()!=null){
    	   dinerBill.setCashierEmployeeName(dinerBill.getCashierEmployee().getName());
       }
     }
 
     model.addAttribute("billStatus", billStatus);
     model.addAttribute("billType", billType);
     model.addAttribute("billStatusList", BillStatusEnum.values());
     model.addAttribute("dinerBillList", dinerBillList);
     model.addAttribute("dinerBillCount", Integer.valueOf(dinerBillList.getContent().size()));
     model.addAttribute("billId", billId);
     model.addAttribute("pageType", pageType);
     model.addAttribute("keywords", keywords);
 
     BigDecimal realCostTotal = this.dinerBillService.getCurrentDayRealCostTotal(getCurrentUserRestId());
 
     model.addAttribute("realCostTotal", realCostTotal == null ? BigDecimal.ZERO : realCostTotal);
 
     BigDecimal payableCostTotal = this.dinerBillService.getCurrentDayPayableCostTotal(getCurrentUserRestId());
 
     model.addAttribute("payableCostTotal", payableCostTotal == null ? BigDecimal.ZERO : payableCostTotal);
 
     return "bill/listBillsContent";
   }
   
 
   @RequestMapping({"diancai"})
   public String diancai(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="billType", defaultValue="1") String billType, @RequestParam(value="tId", required=false) String tId, Model model, HttpServletRequest request)
   {
     model.addAttribute("billId", billId);
     model.addAttribute("billType", billType);
     model.addAttribute("tId", tId);
     model.addAttribute("billPlaceEnterDeskOrPay", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getBillPlaceEnterDeskOrPay());
     return "bill/diancai";
   }
 
   @RequestMapping(value={"pop/addDishesSet"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String addDishesSet(Model model, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="dsId", defaultValue="") String dsId, @RequestParam(value="bdId", defaultValue="") String bdsId, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="isOrder", defaultValue="") String isOrder)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     DishesSet dishesSet = (DishesSet)this.dishesSetService.loadOne(dsId);
 
     if ("editSet".equalsIgnoreCase(type)) {
       ObjectMapper mapper = new ObjectMapper();
       DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
       String dsDishesDesc = dinerBillDishesSet.getDsDishesDesc();
 
       List dishesSetDishesVoList = new ArrayList();
       try {
         List newList = (List)mapper.readValue(dsDishesDesc, List.class);
         for (int i = 0; i < newList.size(); i++) {
           DishesSetDishesVo dishesSetDishesVo = new DishesSetDishesVo();
           Map map = (Map)newList.get(i);
           String dsDishesId = map.get("dsDishesId").toString();
           String dishesId = map.get("dishesId").toString();
           String dishesName = map.get("dishesName").toString();
           String unitNum = map.get("unitNum").toString();
           String unitName = map.get("unitName").toString();
           String dishesCode = map.get("dishesCode").toString();
           String mr_dishesId = map.get("mr_dishesId").toString();
           String mr_dishesName = map.get("mr_dishesName").toString();
           String mr_unitNum = map.get("mr_unitNum").toString();
           String mr_unitName = map.get("mr_unitName").toString();
           String mr_dishesCode = map.get("mr_dishesCode").toString();
           dishesSetDishesVo.setDsDishesId(dsDishesId);
           dishesSetDishesVo.setDishesId(dishesId);
           dishesSetDishesVo.setDishesName(dishesName);
           dishesSetDishesVo.setUnitNum(unitNum);
           dishesSetDishesVo.setUnitName(unitName);
           dishesSetDishesVo.setDishesCode(dishesCode);
           dishesSetDishesVo.setMrDishesId(mr_dishesId);
           dishesSetDishesVo.setMrDishesName(mr_dishesName);
           dishesSetDishesVo.setMrUnitNum(mr_unitNum);
           dishesSetDishesVo.setMrUnitName(mr_unitName);
           dishesSetDishesVo.setMrDishesCode(mr_dishesCode);
           dishesSetDishesVoList.add(dishesSetDishesVo);
         }
       } catch (Exception e) {
         e.printStackTrace();
       }
       model.addAttribute("dishesSetDishesVoList", dishesSetDishesVoList);
     }
     
     model.addAttribute("dinerBill", dinerBill);
     model.addAttribute("dishesSet", dishesSet);
     model.addAttribute("type", type);
     model.addAttribute("isOrder", isOrder);
     model.addAttribute("bdsId", bdsId);
     return "bill/form/addDishesSetForm";
   }
 /**
  * 账单
  * @param billId
  * @param billType
  * @param model
  * @param request
  * @return
  */
   @RequestMapping({"ajax/diancaiBillContent"})
   public String diancaiBillContent(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="billType", defaultValue="1") String billType, Model model, HttpServletRequest request)
   {
     if ((billId != null) && (!billId.isEmpty()))
     {
       DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
       dinerBill.getOriCost();
       if (dinerBill.getTable() != null)
       {
         TableArea tableArea = dinerBill.getTable().getTableArea();
 
         if (tableArea.getConsumeLow() == null)
         {
           dinerBill.setTableConsumeLow(BigDecimal.ZERO);
         }
         else
         {
           BigDecimal dishPrice = getDishesConsume(dinerBill);
           if ((tableArea.getConsumeLow().compareTo(BigDecimal.ZERO) == 1) && 
             (dishPrice == null))
           {
             dinerBill.setTableConsumeLow(tableArea.getConsumeLow());
           }
           if (tableArea.getConsumeLow().compareTo(dishPrice) == 1)
           {
             dinerBill.setTableConsumeLow(tableArea.getConsumeLow());
           }
         }
       }
 
       this.billService.calculator(getCurrentUserRestId(), dinerBill);
 
       List<DinerBillDishe> dinerBillDishes = this.dinerBillDisheService.findByRestIdAndBillId(getCurrentUserRestId(), dinerBill.getBillId());
      
       
       for(int i=0;i<dinerBillDishes.size();i++){
    	   dinerBillDishes.get(i).setOriCostStr(dinerBillDishes.get(i).getUnitPrice()+"");
       }
       
       
       if(billId!=null && billId !="" && billType.equals("2")){  //外卖
		   List<Takeout> takeout=null;
		   List takeout1=new ArrayList();
		   takeout = this.takeoutService.geTakeout(billId);
		   if(takeout!=null&&takeout.size()>0){

		   
		  /* for (int k = 0; k < takeout.size(); k++) {
			   Map map =(Map) takeout.get(k);
			   takeout.get(0);
    		   map.put("takeout1", takeout.get(0));
    		   map.put("takeout2", takeout.get(1));
    		   map.put("takeout3", takeout.get(2));
		}*/
			   if(takeout.get(0).getTakeId()==null||StringUtils.isEmpty(takeout.get(0).getTakeId())){
				   takeout1.add("自取");
			   }else{
				   takeout1.add(takeout.get(0).getTakeId());
			   }
			   if(takeout.get(0).getCustomNote()==null||StringUtils.isEmpty(takeout.get(0).getCustomNote())){
				   takeout1.add("无");
			   }else{
				   takeout1.add(takeout.get(0).getCustomNote());
			   }
			   if(takeout.get(0).getInvoiceTitle()==null||StringUtils.isEmpty(takeout.get(0).getInvoiceTitle())){
				   takeout1.add("否");
			   }else{
				   takeout1.add(takeout.get(0).getInvoiceTitle());
			   }
			   
		   }
    	   model.addAttribute("takeout", takeout1); 
	   }
       
       List<DinerBillDishesSet> dinerBillDishesSets = this.dinerBillDishesSetService.findByRestIdAndDinerBill(getCurrentUserRestId(), dinerBill);
       DinerBillDishe newSet;
       for (DinerBillDishesSet dinerBillDishesSet : dinerBillDishesSets) {
         newSet = new DinerBillDishe();
         newSet.setIsSet(TrueFalseEnum.TRUE.getCode());
         newSet.setDsId(dinerBillDishesSet.getDsId());
         newSet.setDishesId(dinerBillDishesSet.getDsId());
         newSet.setBdId(dinerBillDishesSet.getBdsId());
         newSet.setDishesName(dinerBillDishesSet.getDsName());
         newSet.setDiscountType(dinerBillDishesSet.getDiscountType());
         newSet.setDishesStatus(dinerBillDishesSet.getDsStatus());
         newSet.setAvoidfoodNameArray(dinerBillDishesSet.getAvoidfoodNameArray());
         newSet.setTasteNameArray(dinerBillDishesSet.getTasteNameArray());
         newSet.setPungentLevel(dinerBillDishesSet.getPungentLevel());
         newSet.setNotes(dinerBillDishesSet.getNotes());
         newSet.setUnitName(dinerBillDishesSet.getUnitName());
         newSet.setUnitNum(dinerBillDishesSet.getUnitNum());
         newSet.setDiscount(dinerBillDishesSet.getDiscount());
         newSet.setUnitPrice(dinerBillDishesSet.getUnitPrice());
         newSet.setRealUnitPrice(dinerBillDishesSet.getRealUnitPrice());
         newSet.setOriCost(dinerBillDishesSet.getOriCost());
         System.out.println(dinerBillDishesSet.getOriCost());
         newSet.setOriCostStr(dinerBillDishesSet.getOriCost()+"");
         newSet.setRealCost(dinerBillDishesSet.getRealCost());
         newSet.setDiscount(dinerBillDishesSet.getDiscount());
         newSet.setDiscountType(dinerBillDishesSet.getDiscountType());
         newSet.setCreateTime(dinerBillDishesSet.getCreateTime());
         newSet.setIsDishesSet("yes");
         newSet.setDishesSetDesc(dinerBillDishesSet.getDsDishesDesc());
         dinerBillDishes.add(newSet);
       }
       String isCanPlace = TrueFalseEnum.FALSE.getCode();
       for (DinerBillDishe each : dinerBillDishes) {
         if (!each.getDishesStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
           continue;
         isCanPlace = TrueFalseEnum.TRUE.getCode();
       }
       if(dinerBill.getTable()!=null&&dinerBill.getTable().getTabId()!=null)
       dinerBill.setTableId(dinerBill.getTable().getTabId());
       if(dinerBill.getTable()!=null)
       dinerBill.setTableName(dinerBill.getTable().getTabName());
       
       BigDecimal bb = dinerBill.getOriCost().setScale(2,   BigDecimal.ROUND_HALF_UP);
       
      // Double   f5   =   bb.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
       dinerBill.setOriCost(bb);
       
       model.addAttribute("newBill", dinerBill);
       Collections.sort(dinerBillDishes, new Comparator()
       {
         public int compare(DinerBillDishe o1, DinerBillDishe o2) {
           return o1.getCreateTime().compareTo(o2.getCreateTime());
         }

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			return 0;
		}
       });
       model.addAttribute("dinerBillDishes", dinerBillDishes);
       
       model.addAttribute("isCanPlace", isCanPlace);
       dinerBillDishesSets = null;
       dinerBillDishes = null;
       dinerBill = null;
     }
     else
     {
       DinerBill d = new DinerBill();
       d.setBillTime(new Date());
       d.setOriCost(new BigDecimal(0));
       d.setBillType(billType);
       d.setBillStatus(BillStatusEnum.NOT_PLACE_ORDER.getCode());
       d.setTable(null);
       d.setCreateEmployee(getCurrentUser());
       model.addAttribute("newBill", d);
       model.addAttribute("isCanPlace", TrueFalseEnum.FALSE.getCode());
       d = null;
     }
     model.addAttribute("billPlaceEnterDeskOrPay", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getBillPlaceEnterDeskOrPay());
     model.addAttribute("isShowPlaceBillConfirmWindow", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getIsShowPlaceBillConfirmWindow());
 
     return "bill/diancaiBillContent";
   }
 
   @RequestMapping({"ajax/diancaiContent"})
   public String diancaiContent(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pagzSize", defaultValue="9999") int pagzSize, @RequestParam(value="firstCategoryId", defaultValue="") String firstCategoryId, @RequestParam(value="categoryId", defaultValue="") String categoryId, @RequestParam(value="dsCategoryId", defaultValue="") String dsCategoryId, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="billType", defaultValue="1") String billType, Model model, HttpServletRequest request)
   {
     String isTakeout = null;
 
     if ((BillTypeEnum.WAIMAI_BILL.getCode().equalsIgnoreCase(billType)) || (BillTypeEnum.KUAICAN_BILL.getCode().equalsIgnoreCase(billType))) {
       isTakeout = TrueFalseEnum.TRUE.getCode();
     }
     Page dishes=null;
     dishes = this.disheService.iosDishe(getCurrentUserRestId(), categoryId, dsCategoryId, keywords, null, isTakeout, pageNumber, pagzSize, null);
 
     List firstDishesCategorys = this.dishesCategoryService.findByRestIdAndEnableStatusAndCategoryLevel(getCurrentUserRestId(), CategoryLevelEnum.FIRST.getCode());
     model.addAttribute("firstDishesCategorys", firstDishesCategorys);
 
     boolean isHasDishCategory = false;
     String firstCategoryName = "全部";
     List dishesCategorys = new ArrayList();
     if (!StringUtils.isEmpty(firstCategoryId))
     {
       DishesCategory firstCategory = (DishesCategory)this.dishesCategoryService.loadOne(firstCategoryId);
       if (firstCategory != null)
       {
         firstCategoryName = firstCategory.getCategoryName();
         dishesCategorys = this.dishesCategoryService.findByRestIdAndEnableStatusAndCategoryLevelAndParentCategoryId(getCurrentUserRestId(), CategoryLevelEnum.SECOND.getCode(), firstCategory.getCategoryId());
         isHasDishCategory = true;
       }
       else
       {
         dishesCategorys = this.dishesCategoryService.findByRestIdAndEnableStatus(getCurrentUserRestId());
       }
     }
     else
     {
       dishesCategorys = this.dishesCategoryService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     }
     model.addAttribute("dishesCategorys", dishesCategorys);
 
     List firstDishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusAndCategoryLevelOrderByShowSeqAsc(getCurrentUserRestId(), CategoryLevelEnum.FIRST.getCode());
     model.addAttribute("firstDishesSetCategorys", firstDishesSetCategorys);
 
     if (!isHasDishCategory)
     {
       List dishesSetCategorys = new ArrayList();
       if (!StringUtils.isEmpty(firstCategoryId))
       {
         DishesSetCategory firstCategory = (DishesSetCategory)this.dishesSetCategoryService.loadOne(firstCategoryId);
         if (firstCategory != null)
         {
           firstCategoryName = firstCategory.getCategoryName();
           dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusAndCategoryLevelAndParentCategoryIdOrderByShowSeqAsc(getCurrentUserRestId(), CategoryLevelEnum.SECOND.getCode(), firstCategory.getDsCategoryId());
 
           model.addAttribute("dishesCategorys", null);
         }
         else
         {
           dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusOrderByShowSeqAsc(getCurrentUserRestId());
         }
       }
       else
       {
         dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusOrderByShowSeqAsc(getCurrentUserRestId());
       }
       model.addAttribute("dishesSetCategorys", dishesSetCategorys);
     }
 
     model.addAttribute("dishes", dishes);
 
     model.addAttribute("billId", billId);
     model.addAttribute("categoryId", categoryId);
     model.addAttribute("firstCategoryName", firstCategoryName);
     model.addAttribute("dsCategoryId", dsCategoryId);
     model.addAttribute("keywords", keywords);
     model.addAttribute("billType", billType);
     return "bill/diancaiDishContent";
   }
 
   @RequestMapping({"orderDiancai"})
   public String orderDiancai(@RequestParam(value="orderId", defaultValue="") String orderId, Model model, HttpServletRequest request)
   {
     model.addAttribute("orderId", orderId);
     return "bill/orderDiancai";
   }
   @RequestMapping({"ajax/orderDiancaiBillContent"})
   public String orderDiancaiBillContent(@RequestParam(value="orderId", defaultValue="") String orderId, Model model, HttpServletRequest request) { if ((orderId != null) && (!orderId.isEmpty()))
     {
       TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
       if ((tableOrder.getOrderStatus().equals(OrderStatusEnum.WAIT_COMMIT.getCode())) || 
         (tableOrder.getOrderStatus().equals(OrderStatusEnum.APPLING.getCode())))
       {
         Date orderTime = tableOrder.getOrderTime();
         Date now = DateProvider.DEFAULT.getDate();
         if ((orderTime != null) && (DateUtil.getMinutes(orderTime, now).longValue() > UserSettingCache.getInstance().orderExpireTime))
         {
           tableOrder.setOrderStatus(OrderStatusEnum.EXPIRE.getCode());
         }
       }
 
       model.addAttribute("tableOrder", tableOrder);
       List orderBillDishes = tableOrder.getOrderBillDishes();
       List<OrderBillDishesSet> dinerBillDishesSets = this.orderBillDishesSetService.findByRestIdAndTableOrder(getCurrentUserRestId(), tableOrder);
       for (OrderBillDishesSet orderBillDishesSet : dinerBillDishesSets) {
         OrderBillDishe newSet = new OrderBillDishe();
         newSet.setIsSet(TrueFalseEnum.TRUE.getCode());
         newSet.setDsId(orderBillDishesSet.getDishesSet().getDsId());
         Dishe dishe = new Dishe();
         dishe.setDishesId(orderBillDishesSet.getDishesSet().getDsId());
         newSet.setDishe(dishe);
         newSet.setBdId(orderBillDishesSet.getBdsId());
         newSet.setDishesName(orderBillDishesSet.getDsName());
         newSet.setDiscountType(orderBillDishesSet.getDiscountType());
         newSet.setAvoidfoodNameArray(orderBillDishesSet.getAvoidfoodNameArray());
         newSet.setTasteNameArray(orderBillDishesSet.getTasteNameArray());
         newSet.setPungentLevel(orderBillDishesSet.getPungentLevel());
         newSet.setNotes(orderBillDishesSet.getNotes());
         newSet.setUnitName(orderBillDishesSet.getUnitName());
         newSet.setUnitNum(orderBillDishesSet.getUnitNum());
         newSet.setDiscount(orderBillDishesSet.getDiscount());
         newSet.setUnitPrice(orderBillDishesSet.getUnitPrice());
         newSet.setRealUnitPrice(orderBillDishesSet.getRealUnitPrice());
         newSet.setOriCost(orderBillDishesSet.getOriCost());
         newSet.setRealCost(orderBillDishesSet.getRealCost());
         newSet.setDiscount(orderBillDishesSet.getDiscount());
         newSet.setDiscountType(orderBillDishesSet.getDiscountType());
         orderBillDishes.add(newSet);
       }
       model.addAttribute("orderBillDishes", orderBillDishes);
     }
     return "bill/orderDiancaiBillContent";
   }
 
   @RequestMapping({"ajax/orderDiancaiContent"})
   public String orderDiancaiContent(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="dsCategoryId", defaultValue="") String dsCategoryId, @RequestParam(value="pagzSize", defaultValue="20") int pagzSize, @RequestParam(value="categoryId", defaultValue="") String categoryId, @RequestParam(value="orderId", defaultValue="") String orderId, Model model, HttpServletRequest request)
   {
     Page dishes = this.disheService.searchDishe(getCurrentUserRestId(), categoryId, dsCategoryId, keywords, null, null, pageNumber, pagzSize, null);
     model.addAttribute("dishes", dishes);
 
     List dishesCategorys = this.dishesCategoryService.findAllDishesCategoryByRestId(getCurrentUserRestId());
 
     List dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusOrderByShowSeqAsc(getCurrentUserRestId());
     model.addAttribute("dishesSetCategorys", dishesSetCategorys);
     model.addAttribute("dishesCategorys", dishesCategorys);
     model.addAttribute("orderId", orderId);
     model.addAttribute("dsCategoryId", dsCategoryId);
     model.addAttribute("categoryId", categoryId);
     model.addAttribute("keywords", keywords);
     return "bill/orderDiancaiDishContent";
   }
 
   @RequestMapping(value={"orderJiacai/{orderId}/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone orderJiacai(@PathVariable("orderId") String orderId, @PathVariable("id") String dishesId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveOrderJiacai(orderId, dishesId, null, null, null, null, getCurrentUser(), getCurrentUserRestId(), 1.0F, map);
 
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("加菜成功");
     ajax.setRel(dishesId);
     return ajax;
   }
 
   @RequestMapping(value={"orderJiacaiSet/{orderId}/{id}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone orderJiacaiSet(@PathVariable("orderId") String orderId, @PathVariable("id") String dsId, @RequestParam(value="dsDishesDesc", defaultValue="") String dsDishesDesc, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveOrderDishesSet(orderId, dsId, null, null, null, null, getCurrentUser(), getCurrentUserRestId(), dsDishesDesc, 1.0F, map);
 
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("加套餐成功");
     ajax.setRel(orderId);
     return ajax;
   }
 
   @RequestMapping(value={"orderDishDelete/{orderId}/{bdId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone orderDishDelete(@PathVariable("orderId") String orderId, @PathVariable("bdId") String bdId, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveOrderDishDelete(orderId, bdId, isSet, map);
 
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("删除成功");
     ajax.setRel(orderId);
     return ajax;
   }
 
   @RequestMapping(value={"orderDishNumChange/{orderId}/{bdId}/{newDishNum}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone orderDishNumChange(@PathVariable("orderId") String orderId, @PathVariable("bdId") String bdId, @PathVariable("newDishNum") String newDishNum, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveOrderDishNumChange(orderId, bdId, newDishNum, isSet, map);
 
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("修改成功");
     ajax.setRel(orderId);
     return ajax;
   }
 
   @RequestMapping(value={"pop/orderDishCooking/update/{bdId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String orderDishCooking(@PathVariable("bdId") String bdId, @RequestParam(value="isSet", required=false) String isSet, Model model)
   {
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       OrderBillDishesSet orderBillDishesSet = (OrderBillDishesSet)this.orderBillDishesSetService.getOne(bdId);
       model.addAttribute("orderBillDishe", orderBillDishesSet);
     } else {
       OrderBillDishe orderBillDishe = (OrderBillDishe)this.orderBillDishesService.getOne(bdId);
       model.addAttribute("orderBillDishe", orderBillDishe);
     }
 
     List dishesTastes = this.dishesTasteService.findAllDishesTasteByRestId(getCurrentUserRestId());
     model.addAttribute("dishesTastes", dishesTastes);
 
     List dishesAvoidfoods = this.dishesAvoidfoodService.findAllDishesAvoidfoodByRestId(getCurrentUserRestId());
     model.addAttribute("dishesAvoidfoods", dishesAvoidfoods);
 
     model.addAttribute("bdId", bdId);
     model.addAttribute("type", "order");
     model.addAttribute("note_isSet", isSet);
     return "bill/dishCookingNotes";
   }
 
   @RequestMapping(value={"orderDishCookingNotes/update/{bdId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone orderDishCookingNotesSave(@PathVariable("bdId") String bdId, @RequestParam(value="avoidArray", required=true) String avoidArray, @RequestParam(value="tasteArray", required=true) String tasteArray, @RequestParam(value="pungent", required=true) int pungent, @RequestParam(value="notes", required=true) String notes, @RequestParam(value="isSet", required=true) String isSet, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveOrderDishCookingNotes(bdId, avoidArray, tasteArray, pungent, notes, isSet, map);
 
     doSynchMultiTable(map);
 
     ajax.setMessage("保存备注成功");
     return ajax;
   }
 
   @RequestMapping(value={"pop/orderCooking/update/{orderId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String orderCookingNotes(@PathVariable("orderId") String orderId, Model model)
   {
     TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
 
     List dishesTastes = this.dishesTasteService.findAllDishesTasteByRestId(getCurrentUserRestId());
     model.addAttribute("dishesTastes", dishesTastes);
 
     List dishesAvoidfoods = this.dishesAvoidfoodService.findAllDishesAvoidfoodByRestId(getCurrentUserRestId());
     model.addAttribute("dishesAvoidfoods", dishesAvoidfoods);
 
     model.addAttribute("billId", tableOrder.getOrderId());
     model.addAttribute("dinerBill", tableOrder);
     model.addAttribute("type", "order");
     return "bill/cookingNotes";
   }
 
   @RequestMapping(value={"orderCookingNotes/update/{orderId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone orderCookingNotes(@PathVariable("orderId") String orderId, @RequestParam(value="avoidArray", required=true) String avoidArray, @RequestParam(value="tasteArray", required=true) String tasteArray, @RequestParam(value="pungent", required=true) int pungent, @RequestParam(value="notes", required=true) String notes, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveOrderCookingNotes(orderId, avoidArray, tasteArray, pungent, notes, map);
 
     doSynchMultiTable(map);
 
     ajax.setMessage("保存备注成功");
     return ajax;
   }
 
   @RequestMapping(value={"jiacai/{billType}/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone jiacai(@PathVariable("billType") String billType, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="dishNum", defaultValue="1") String dishNum, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="tId", required=false) String tId, @PathVariable("id") String id, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     float dishesNum = Float.parseFloat(dishNum);
 
     Map messageMap = null;
 
     boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
     if (("0".equalsIgnoreCase(isJudgeRm)) && (isJudgeDishRaws))
     {
       Dishe dishe = (Dishe)this.disheService.getOne(id);
       messageMap = this.dinerBillService.stockCheckForDishes(getCurrentUserRestId(), "", dishe, dishesNum, isJudgeDishRaws);
       String stockResult = (String)messageMap.get("result");
       ajax.setMessageMap(messageMap);
       if (messageMap.get("estimate") != null) {
         ajax.setSign(formatFloat((String)messageMap.get("estimate")));
       }
       if ("0".equals(stockResult))
       {
         return ajax;
       }
       if ("4".equals(stockResult))
       {
         return ajax;
       }
       "1".equals(stockResult);
 
       if ("5".equals(stockResult))
       {
         isJudgeDishRaws = false;
       }
     }
 
     if ((billType == null) || (billType.isEmpty()))
     {
       billType = BillTypeEnum.NORMAL_BILL.getCode();
     }
     if ((billId == null) || (billId.isEmpty()))
     {
       DinerBill d = new DinerBill();
       d.setBillTime(new Date());
       d.setBillType(billType);
       d.setTable(null);
       d.setIsValid(TrueFalseEnum.TRUE.getCode());
       d.setIsShift(TrueFalseEnum.FALSE.getCode());
 
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveCreateTableDinerBill(getCurrentUserRestId(), d, null, tId, map);
 
       doSynchMultiTable(map);
 
       billId = d.getBillId();
       ajax.setType(billType);
       ajax.setCallbackType("单号:" + d.getBillNo() + " (" + d.getBillStatusDesc() + ")");
     }
 
     LinkedHashMap map = new LinkedHashMap();
     messageMap = this.dinerBillService.saveJiacai(getCurrentUserRestId(), billId, id, dishesNum, null, null, null, null, getCurrentUser(), null, isJudgeDishRaws, map);
 
     doSynchMultiTable(map);
 
     final Map f_messageMap = messageMap;
     final String f_billId = billId;
     PriorityExecutor.execLog(new Runnable()
     {
       public void run() {
         String logNotes = (String)f_messageMap.get("logNotes");
         DinerBill dinerBill = (DinerBill)BillController.this.dinerBillService.getOne(f_billId);
         BillController.this.dinerBillService.saveBusiLog(BillController.this.getCurrentUserRestId(), dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
         if (f_messageMap.containsKey("rmDesc"))
         {
           String rmNotes = BillOpTypeEnum.ORDER_DISH.getDesc() + ":" + (String)f_messageMap.get("rmDesc");
           BillController.this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
         }
       }
     });
   //编号
     DinerBill dinerBilla = (DinerBill)this.dinerBillService.getOne(billId);
     
     if(dinerBilla.getBillNumber()==null){
    	 Date ds=new Date();
         long str =ds.getTime();
         String bsss=Long.toString(str);
         dinerBilla.setBillNumber(bsss);
        // dinerBilla.setOnlineBillNo(bsss);
         this.dinerBillService.save(dinerBilla); 
     }
    
     ajax.setValue(billId);
     ajax.setSign(formatFloat((String)messageMap.get("estimate")));
     ajax.setStatusCode((String)messageMap.get("code"));
     ajax.setMessageMap(messageMap);
     ajax.setRel(id);
 
     ajax.setForwardUrl((String)messageMap.get("html"));
     return ajax;
   }
 
   @RequestMapping(value={"calculatorBill/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone calculatorBill(@PathVariable("billId") String billId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     this.billService.calculator(getCurrentUserRestId(), dinerBill);
     String isCanPlace = TrueFalseEnum.FALSE.getCode();
     for (DinerBillDishe each : dinerBill.getDinerBillDishes()) {
       if (!each.getDishesStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
         continue;
       isCanPlace = TrueFalseEnum.TRUE.getCode();
     }
 
     for (DinerBillDishesSet each : dinerBill.getDinerBillDishesSets()) {
       if (!each.getDsStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
         continue;
       isCanPlace = TrueFalseEnum.TRUE.getCode();
     }
 
     ajax.setForwardUrl(dinerBill.getBillStatus());
     ajax.setNavTabId(dinerBill.getBillStatusDesc());
     ajax.setRel(isCanPlace);
     ajax.setValue(billId);
     ajax.setSign(BigDecimalUtil.format(dinerBill.getOriCost()).toString());
     HashMap messageData = new HashMap();
     messageData.put("payableCost", dinerBill.getPayableCost());
     messageData.put("consumeLow", dinerBill.getConsumeLow());
     ajax.setDataMap(messageData);
     ajax.setMessage("计算账单价格");
     return ajax;
   }
 
   @RequestMapping(value={"addDishesSet/{billType}/{dsId}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone addDishesSet(@PathVariable("billType") String billType, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="dsDishesDesc", defaultValue="") String dsDishesDesc, @PathVariable("dsId") String dsId, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="tId", required=false) String tId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Map messageMap = null;
     if (isJudgeRm.equals("0")) {
       try {
         messageMap = this.dinerBillService.stockCheckForSet(getCurrentUserRestId(), dsId, dsDishesDesc, "");
       }
       catch (Exception e) {
         e.printStackTrace();
       }
       ajax.setMessageMap(messageMap);
       return ajax;
     }
 
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
 
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveCreateTableDinerBill(getCurrentUserRestId(), d, null, tId, map);
 
       doSynchMultiTable(map);
 
       billId = d.getBillId();
       ajax.setType(billType);
     }
     try
     {
       LinkedHashMap map = new LinkedHashMap();
       LinkedHashMap mapBill = new LinkedHashMap();
       messageMap = this.dinerBillService.saveDishesSet(getCurrentUserRestId(), billId, dsId, billType, dsDishesDesc, null, null, null, null, getCurrentUser(), null, tId, map, mapBill);
 
       doSynchMultiTable(map);
       doSynchMultiTable(mapBill);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     ajax.setValue(billId);
     ajax.setSign(formatFloat((String)messageMap.get("estimate")));
     ajax.setStatusCode((String)messageMap.get("code"));
     ajax.setRel(dsId);
     ajax.setMessageMap(messageMap);
     ajax.setMessage("加菜成功");
     return ajax;
   }
 
   @RequestMapping(value={"editDishesSet/{bdsId}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone editDishesSet(@PathVariable("bdsId") String bdsId, @RequestParam(value="dsDishesDesc", defaultValue="") String dsDishesDesc, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, Model model)
     throws JsonProcessingException
   {
     Map messageMap = null;
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
     if (isJudgeRm.equals("0")) {
       try {
         messageMap = this.dinerBillService.stockCheckForSet(getCurrentUserRestId(), dinerBillDishesSet.getDsId(), dsDishesDesc, dinerBillDishesSet.getDsDishesDesc());
       } catch (Exception e) {
         e.printStackTrace();
       }
     } else {
       LinkedHashMap map = new LinkedHashMap();
       messageMap = this.dinerBillService.dishSetSaveAndUpdateStock(bdsId, getCurrentUserRestId(), dsDishesDesc, dinerBillDishesSet.getDsDishesDesc(), map);
 
       doSynchMultiTable(map);
     }
 
     ajax.setMessageMap(messageMap);
     return ajax;
   }
 
   @RequestMapping(value={"editDishesSetReplace/{bdsId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone editDishesSetReplace(@PathVariable("bdsId") String bdsId, @RequestParam(value="dsDishesDesc", defaultValue="") String dsDishesDesc, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdsId);
     Map messageMap = this.dinerBillService.dishSetSaveAndUpdateStock(bdsId, getCurrentUserRestId(), dsDishesDesc, dinerBillDishesSet.getDsDishesDesc(), map);
 
     doSynchMultiTable(map);
 
     ajax.setMessageMap(messageMap);
     return ajax;
   }
 
   @RequestMapping(value={"pop/replaceDishesContent/{dsId}/{dsDishesId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String replaceDishesContent(@PathVariable("dsId") String dsId, @PathVariable("dsDishesId") String dsDishesId, Model model, HttpServletRequest request)
   {
     DishesSetDishes dishesSetDishes = (DishesSetDishes)this.dishesSetDishesService.getOne(dsDishesId);
     List dishesSetDishesReplaces = this.dishesSetDishesReplaceService.findByRestIdAndDsIdAndDishesSetDishes(getCurrentUserRestId(), dsId, dishesSetDishes);
     model.addAttribute("dishesSetDishesReplaces", dishesSetDishesReplaces);
     model.addAttribute("dishesSetDishes", dishesSetDishes);
     return "bill/form/replaceDishesContent";
   }
 
   public String formatFloat(String f) {
     f = f.replaceAll("\\.00", "").replaceAll("\\.0", "");
     return f;
   }
   @RequestMapping(value={"xiadan/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone xiadan(HttpSession httpSession, @PathVariable("billId") String billId, @RequestParam(value="enterType", required=false, defaultValue="0") String enterType, Model model) throws JsonProcessingException {
     if (StringUtils.isEmpty(enterType))
     {
       enterType = BillPlaceEnterPageEnum.DIANCAN_PAGE.getCode();
     }
     DwzAjaxDone ajax = new DwzAjaxDone();
     synchronized (lock) {
       Map messageMap = new HashMap();
       DinerBill idinerBill = (DinerBill)this.dinerBillService.getOne(billId);
       messageMap.put("billType", idinerBill.getBillType());
       if ((idinerBill.getBillStatus().equals(BillStatusEnum.NOT_PLACE_ORDER.getCode())) || 
         (idinerBill.getBillStatus().equals(BillStatusEnum.SOME_PLACE_ORDER.getCode())) || 
         (idinerBill.getBillStatus().equals(BillStatusEnum.RESETTLE.getCode())))
       {
         LinkedHashMap map = new LinkedHashMap();
         final String restId = getCurrentUserRestId();
         final DinerBill dinerBill = this.dinerBillService.xiadan(restId, billId, map);
         dinerBill.setUpdateEmployee(getCurrentUser());
 
         doSynchMultiTable(map);
 
         if (BillTypeEnum.WAIMAI_BILL.getCode().equals(dinerBill.getBillType())) {
           Takeout takeout = this.takeoutService.findByRestIdAndDinerBill(restId, dinerBill);
           messageMap.put("sendAtOnce", takeout.getSendAtOnce());
         }
         PriorityExecutor.execPrint(new Runnable()
         {
           public void run() {
             Table table = dinerBill.getTable();
             try
             {
               HashMap printParaments = new HashMap();
               printParaments.put("isAddDishes", dinerBill != null ? dinerBill.getIsAddDishes() : Boolean.FALSE);
               printParaments.put("operatorName", dinerBill.getUpdateEmployee().getName());
               BillController.this.xiadanPrinterService.printXiadan(restId, dinerBill, table, printParaments);
             } catch (Exception e) {
               e.printStackTrace();
               System.out.println("下单成功，打印失败");
             }
           }
         });
         PriorityExecutor.execLog(new Runnable()
         {
           public void run()
           {
             String logNotes = "下单操作";
             BillController.this.dinerBillService.saveBusiLog(restId, dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_BILL, logNotes);
           }
         });
         if (!UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getBillPlaceEnterDeskOrPay().equals(enterType))
         {
           UserSettingCache.getInstance().getUserCache(getCurrentUserId()).setBillPlaceEnterDeskOrPay(enterType);
         }
         enterType = UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getBillPlaceEnterDeskOrPay();
         ajax.setMessageMap(messageMap);
         ajax.setSign(TrueFalseEnum.TRUE.getCode());
         ajax.setStatusCode(enterType);
         ajax.setMessage("下单成功");
         ajax.setRel(billId);
       }
       else
       {
         ajax.setMessageMap(messageMap);
         ajax.setSign(TrueFalseEnum.FALSE.getCode());
         ajax.setStatusCode(enterType);
         ajax.setMessage("此账单已经下单");
         ajax.setRel(billId);
       }
     }
     return ajax;
   }
   @RequestMapping(value={"xiadanPrint/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone xiadanPrint(@PathVariable("billId") String billId, Model model) throws JsonProcessingException {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     String restId = getCurrentUserRestId();
     Table table = dinerBill.getTable();
 
     HashMap retmap = this.dinerBillDisheService.getLastFixedDishes(restId, billId);
     List lastDishs = retmap != null ? (List)retmap.get("dinerBillDishes") : null;
 
     dinerBill.setDinerBillDishes(lastDishs);
     try {
       HashMap printParaments = new HashMap();
       printParaments.put("isAddDishes", retmap.get("isAddDishes"));
       printParaments.put("operatorName", getCurrentUser().getName());
       this.xiadanPrinterService.printXiadan(restId, dinerBill, table, printParaments);
     } catch (Exception e) {
       e.printStackTrace();
       logger.error(e.getMessage());
       ajax.setStatusCode("0");
       ajax.setMessage("打印失败");
       ajax.setRel(billId);
       return ajax;
     }
 
     ajax.setStatusCode("1");
     ajax.setMessage("打印成功");
     ajax.setRel(billId);
     return ajax;
   }
 
   @RequestMapping(value={"quxiao/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone quxiao(@PathVariable("billId") String billId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     try {
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.quxiaxiadan(getCurrentUserRestId(), billId, map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("取消下单成功");
     ajax.setRel(billId);
     return ajax;
   }
 
   @RequestMapping({"payPage/{id}"})
   public String payPage(@PathVariable("id") String billId, Model model, HttpServletRequest request)
   {
     model.addAttribute("billId", billId);
     return "bill/pay";
   }
 
   @RequestMapping({"ajax/payContent/{id}"})
   public String ajaxPayPage(@PathVariable("id") String billId, Model model, HttpServletRequest request)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     setPayData(dinerBill, model);
     return "bill/payContent";
   }
 
   private void setPayData(DinerBill dinerBill, Model model)
   {
     if (!BillStatusEnum.SETTLE.getCode().equals(dinerBill.getBillStatus()))
     {
       this.billService.calculator(getCurrentUserRestId(), dinerBill);
     }
 
     StringBuilder sb = new StringBuilder();
     List dinerBillPayments = dinerBill.getDinerBillPayments();
     PaymentType paymentType;
     for (int i = 0; i < dinerBillPayments.size(); i++) {
       DinerBillPayment dinerBillPayment = (DinerBillPayment)dinerBillPayments.get(i);
       paymentType = dinerBillPayment.getPaymentType();
       sb.append(paymentType.getPaymentName());
       sb.append("：");
       sb.append(BigDecimalUtil.format(dinerBillPayment.getMoney()));
       if (i != dinerBillPayments.size() - 1) {
         sb.append(",");
       }
     }
     dinerBill.setPayments(sb.toString());
 
     model.addAttribute("dinerBill", dinerBill);
 
     if ((dinerBill.getRestMemberInfo() != null) && (dinerBill.getRestMemberInfo().getCompany() != null)) {
       model.addAttribute("company", dinerBill.getRestMemberInfo().getCompany().split(",")[0]);
     }
 
     List<PaymentType> paymentTypes = this.paymentTypeService.findPaymentTypeByRestID(getCurrentUserRestId());
     for (PaymentType paymentType1 : paymentTypes)
     {
       List dinerBillPaymentList = this.dinerBillPaymentService.findByRestIdAndDinerBillAndPaymentType(getCurrentUserRestId(), dinerBill, paymentType1);
       if ((dinerBillPaymentList != null) && (dinerBillPaymentList.size() > 0)) {
         paymentType1.setMoney(BigDecimalUtil.format(((DinerBillPayment)dinerBillPaymentList.get(0)).getMoney()));
       }
     }
     model.addAttribute("paymentTypes", paymentTypes);
 
     model.addAttribute("settle", BillStatusEnum.SETTLE.getCode());
   }
 
   @RequestMapping(value={"haveCredit/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone haveCredit(@PathVariable("billId") String billId, @RequestParam(value="cptId", required=true) String cptId, @RequestParam(value="paymentType", required=true) String paymentType, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     if ((PaymentTypeEnum.HOTEL_CREDIT.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.TEAM_CREDIT.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equalsIgnoreCase(paymentType))) {
       DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
 
       PaymentType pType = (PaymentType)this.paymentTypeService.loadOne(cptId);
       DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, getCurrentUserRestId(), pType);
       if (dinerBillPayment == null) {
         List types = new ArrayList();
         types.add(PaymentTypeEnum.HOTEL_CREDIT.getCode());
         types.add(PaymentTypeEnum.TEAM_CREDIT.getCode());
         types.add(PaymentTypeEnum.RESTAURANT_CREDIT.getCode());
         List paymentTypes = this.paymentTypeService.findByRestIdAndEnableStatusAndPaymentTypeIn(getCurrentUserRestId(), types);
         List dinerBillPayments = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentTypeIn(dinerBill, getCurrentUserRestId(), paymentTypes);
         if ((dinerBillPayments != null) && (dinerBillPayments.size() > 0)) {
           ajax.setValue(TrueFalseEnum.TRUE.getCode());
           ajax.setMessage("只能使用一种挂账方式");
           return ajax;
         }
       }
     }
     return ajax;
   }
 
   @RequestMapping(value={"pop/paymentType"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String paymentType(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="cptId", required=true) String cptId, @RequestParam(value="paymentType", required=true) String paymentType, @RequestParam(value="money", required=false) String money, @RequestParam(value="needMoney", required=false) String needMoney, RedirectAttributes redirectAttributes, Model model)
   {
     model.addAttribute("billId", billId);
     model.addAttribute("cptId", cptId);
 
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     PaymentType pType = (PaymentType)this.paymentTypeService.loadOne(cptId);
     List dinerBillPaymentList = this.dinerBillPaymentService.findByRestIdAndDinerBillAndPaymentType(getCurrentUserRestId(), dinerBill, pType);
     if ((dinerBillPaymentList != null) && (dinerBillPaymentList.size() > 0)) {
       model.addAttribute("dinerBillPayment", dinerBillPaymentList.get(0));
     }
     model.addAttribute("paymentType", paymentType);
     model.addAttribute("paymentTypeDesc", pType.getPaymentName());
     model.addAttribute("needMoney", needMoney);
     model.addAttribute("money", money);
     String subType = getSubPaymentType(paymentType);
 
     if (("CARD".equals(subType)) || ("CREDIT".equals(subType))) {
       MembershipCard membershipCard = dinerBill.getMembershipCard();
       if (membershipCard != null)
       {
         String restId = getCurrentUserRestId();
         PaymentType paymentTypeOnline = this.paymentTypeService.findeByRestIdAndPaymentType(restId, PaymentTypeEnum.WEB_PAY.getCode());
         DinerBillPayment dinerBillPaymentOnline = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, restId, paymentTypeOnline);
         if (dinerBillPaymentOnline != null) {
           model.addAttribute("isWebPay", TrueFalseEnum.TRUE.getCode());
         }
         model.addAttribute("membershipCard", membershipCard);
         model.addAttribute("kewWords", membershipCard.getCardNo());
         model.addAttribute("membercardPayType", dinerBill.getMembercardPayType());
       }
     }
     if ("CARD".equals(subType))
       return "bill/form/cardForm";
     if ("CREDIT".equals(subType)) {
       return "bill/form/creditForm";
     }
     return "bill/form/moneyForm";
   }
 
   @RequestMapping(value={"pop/molingForm"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String molingForm(@RequestParam(value="billId", required=true) String billId, RedirectAttributes redirectAttributes, Model model)
   {
     StringBuffer molingDesc = new StringBuffer();
     model.addAttribute("billId", billId);
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     this.billService.calculator(getCurrentUserRestId(), dinerBill);
     dinerBill.setMolingModeCost(BigDecimalUtil.format(dinerBill.getMolingModeCost()));
     dinerBill.setPayableCost(BigDecimalUtil.format(dinerBill.getPayableCost()));
     dinerBill.setConsumeCost(BigDecimalUtil.format(dinerBill.getConsumeCost()));
     dinerBill.setSaveCost(BigDecimalUtil.format(dinerBill.getSaveCost()));
     if (!MolingModeEnum.NOTDEAL.getCode().equals(dinerBill.getMolingMode()))
     {
       if (TrueFalseEnum.TRUE.getCode().equals(dinerBill.getIsRound())) {
         molingDesc.append("四舍五入位：");
         molingDesc.append(dinerBill.getMolingModeDesc());
         molingDesc.append("  ");
         if (MolingModeEnum.JIAO.getCode().equals(dinerBill.getMolingMode()))
           molingDesc.append("角大于等于5进位，小于5舍掉");
         else if (MolingModeEnum.YUAN.getCode().equals(dinerBill.getMolingMode()))
           molingDesc.append("元大于等于5进位，小于5舍掉");
         else if (MolingModeEnum.TENYUAN.getCode().equals(dinerBill.getMolingMode()))
           molingDesc.append("十位大于等于5进位，小于5舍掉");
       }
       else {
         molingDesc.append("抹零位：");
         molingDesc.append(dinerBill.getMolingModeDesc());
       }
     }
 
     model.addAttribute("billId", billId);
     model.addAttribute("dinerBill", dinerBill);
     model.addAttribute("molingDesc", molingDesc.toString());
     return "bill/form/molingForm";
   }
 
   @RequestMapping(value={"moling/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveMoling(@PathVariable("id") String billId, @RequestParam(value="money", required=true) String money, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     dinerBill.setIsMoling(TrueFalseEnum.TRUE.getCode());
     dinerBill.setMolingMode(null);
     dinerBill.setMolingModeCost(new BigDecimal(money));
     this.billService.save(dinerBill);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     return ajax;
   }
 
   @RequestMapping({"ajax/card/list"})
   public String cardList(@RequestParam(value="kewWords", required=false) String kewWords, @RequestParam(value="billId", required=true) String billId, @RequestParam(value="paymentType", required=true) String paymentType, Model model, HttpServletRequest request)
   {
     List membershipCards = new ArrayList();
     DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
     MembershipCard membershipCard = dinerBill.getMembershipCard();
 
     if (membershipCard != null)
       membershipCards.add(membershipCard);
     else {
       membershipCards = this.membershipCardService.searchCard(getCurrentUserRestId(), kewWords);
     }
     model.addAttribute("membershipCards", membershipCards);
     model.addAttribute("kewWords", kewWords);
     String subType = getSubPaymentType(paymentType);
     if ("CARD".equalsIgnoreCase(subType)) {
       return "bill/form/cardContent";
     }
     return "bill/form/creditCardContent";
   }
 
   @RequestMapping(value={"pop/memberCardInfo/{cardNo}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getMemberCardInfo(@PathVariable("cardNo") String cardNo, Model model)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     List membershipCards = this.membershipCardService.findByCardNo(cardNo, getCurrentUserRestId());
     if ((membershipCards != null) && (membershipCards.size() > 0)) {
       MembershipCard membershipCard = (MembershipCard)membershipCards.get(0);
       a.setStatusCode(TrueFalseEnum.TRUE.getCode());
 
       a.setSign(membershipCard.getCardStatus());
 
       String mbId = null;
       if (membershipCard.getRestMemberInfo() != null) {
         mbId = membershipCard.getRestMemberInfo().getMbId();
       }
 
       a.setValue(membershipCard.getMcId() + "," + BigDecimalUtil.format(membershipCard.getBalance()) + "," + BigDecimalUtil.format(membershipCard.getMemberIntegral()) + "," + 
         mbId + "," + membershipCard.getIsEmptyPassWord());
     } else {
       a.setStatusCode(TrueFalseEnum.FALSE.getCode());
     }
     return a;
   }
   
   
   
 
   @RequestMapping(value={"pop/paymentType/update"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone savePaymentType(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="cptId", required=true) String cptId, @RequestParam(value="dbpId", required=false) String dbpId, @RequestParam(value="mcId", required=false) String mcId, @RequestParam(value="mbId", required=false) String mbId, @RequestParam(value="money", required=false) String money, @RequestParam(value="paymentType", required=true) String paymentType, @RequestParam(value="membercardPayType", required=false) String membercardPayType, Model model, @RequestParam(value="isSuc", defaultValue="-1") int isSuc)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
    // String str1=money;

 	
     String billStatus = dinerBill.getBillStatus();
     if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
       (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
       (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
       a.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       a.setMessage("此账单无法操作，或者已经结账了");
       return a;
     }
     
     //首先判断是不是使用会员卡消费，如果使用则查询会员卡余额是否足够
     if (MemberCardPayTypeEnum.CONSUME.getCode().equals(membercardPayType)) {
       MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
       BigDecimal inputMoney = new BigDecimal(money);
       BigDecimal balance = membershipCard.getBalance();
 
       if (inputMoney.compareTo(balance) == 1) {
         a.setRel("false");
         a.setMessage("会员卡余额不足，请使用现金或充值");
         return a;
       }
     }
     LinkedHashMap map = new LinkedHashMap();
     //获取当前的支付方式为  钱支付  还是会员卡支付  还是挂账支付  还是 其他支付方式
     String subType = getSubPaymentType(paymentType);
     String dbpid=this.dinerBillPaymentService.savePayments(subType, getCurrentUserRestId(), billId, mcId, mbId, dbpId, cptId, money, membercardPayType, map,-1);
     
     DinerBill dinerBills = (DinerBill)this.dinerBillService.getOne(billId);
     dinerBills.getOriCost();
     BigDecimal bd=new BigDecimal(money); 
    // bd.setScale(2,   BigDecimal.ROUND_HALF_UP); 
     dinerBills.setBillMoney(bd);
     this.dinerBillService.save(dinerBills);
     a.setValue("dbpid作为支付宝商户id传入");
     doSynchMultiTable(map);
     a.setForwardUrl(dbpid);
     a.setMessage("支付成功");
     return a;
   }
 
   @RequestMapping(value={"pop/isSameCashDiscount/{id}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone isSameCashDiscount(@PathVariable("id") String billId, Model model)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     CashDiscount cashDiscountBill = dinerBill.getCashDiscount();
     CashDiscount cashDiscountCard = dinerBill.getMembershipCard().getMembershipCardClass().getCashDiscount();
     Boolean isSameCashDiscount = Boolean.valueOf(cashDiscountBill.getCcdId().equals(cashDiscountCard.getCcdId()));
     if (isSameCashDiscount.booleanValue())
       a.setRel(TrueFalseEnum.TRUE.getCode());
     else {
       a.setRel(TrueFalseEnum.FALSE.getCode());
     }
     a.setMessage("是否取消会员卡对应的折扣方案？");
     return a;
   }
 
   @RequestMapping(value={"pop/cancleCashDiscount/{id}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone cancleMemberCard(@PathVariable("id") String billId, @RequestParam(value="cptId", required=true) String cptId, Model model)
   {
     DwzAjaxDone a = new DwzAjaxDone();
 
     DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
 
     String isDeleteCashDiscount = TrueFalseEnum.FALSE.getCode();
     CashDiscount cashDiscount = dinerBill.getCashDiscount();
     if ((cashDiscount != null) && (
       ((dinerBill.getMembershipCard() != null) && (cashDiscount.getCcdId().equals(dinerBill.getMembershipCard().getMembershipCardClass().getCashDiscount().getCcdId()))) || 
       (TrueFalseEnum.TRUE.getCode().equals(cashDiscount.getIsOnlyMember()))))
       isDeleteCashDiscount = TrueFalseEnum.TRUE.getCode();
     else {
       isDeleteCashDiscount = TrueFalseEnum.FALSE.getCode();
     }
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveCancleMemeberCard(getCurrentUserRestId(), dinerBill, isDeleteCashDiscount, cptId, map);
 
     doSynchMultiTable(map);
 
     this.billService.calculator(getCurrentUserRestId(), dinerBill);
     a.setRel(String.valueOf(dinerBill.getNeedMoney()));
     a.setCallbackType(isDeleteCashDiscount);
     return a;
   }
 
   @RequestMapping(value={"pop/cashDiscount/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popCashDiscounts(@PathVariable("id") String billId, @RequestParam(value="page", defaultValue="1") int pageNumber, Model model, HttpServletRequest request)
   {
     Restaurant restaurant = new Restaurant();
     restaurant.setRestId(getCurrentUserRestId());
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     Employee user = (Employee)this.employeeService.loadOne(getCurrentUserId());
     List roleList = user.getRoleList();
     DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
     boolean isMemeber = dinerBill.getMembershipCard() != null;
     Page cashDiscountAll = this.cashDiscountService.search(getCurrentUserRestId(), roleList, isMemeber, searchParams, pageNumber, 7, new String[] { "createTime" });
     model.addAttribute("dinerBill", dinerBill);
     model.addAttribute("cashDiscounts", cashDiscountAll);
     return "bill/form/cashDiscountForm";
   }
 
   @RequestMapping(value={"pop/drawBill/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popDrawBill(@PathVariable("id") String billId, Model model)
   {
     List companys = new ArrayList();
 
     DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
     RestMemberInfo restMemberInfo = dinerBill.getRestMemberInfo();
     if ((restMemberInfo != null) && (StringUtils.isNotEmpty(restMemberInfo.getCompany()))) {
       String companyArr = restMemberInfo.getCompany();
       for (String company : companyArr.split(",")) {
         companys.add(company);
       }
     }
     model.addAttribute("dinerBill", dinerBill);
     model.addAttribute("companys", companys);
     model.addAttribute("drawBillAmount", BigDecimalUtil.format(dinerBill.getDrawBillAmount()));
     return "bill/form/drawBillForm";
   }
 
   @RequestMapping(value={"pop/notes/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popNotes(@PathVariable("id") String billId, Model model)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     model.addAttribute("billId", billId);
     model.addAttribute("billNotes", dinerBill.getBillNotes());
     return "bill/form/notesForm";
   }
 
   @RequestMapping(value={"pop/customDiscount/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popCustomDiscount(@PathVariable("id") String billId, Model model)
   {
     model.addAttribute("billId", billId);
     return "bill/form/customDiscountForm";
   }
 
   @RequestMapping(value={"pop/forcePay/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popForcePay(@PathVariable("id") String billId, @RequestParam(value="isPrint", required=true) String isPrint, Model model)
   {
     model.addAttribute("billId", billId);
     model.addAttribute("isPrint", isPrint);
     return "bill/form/forcePayForm";
   }
 
   @RequestMapping(value={"pop/paySuccess"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popPaySuccess()
   {
     return "bill/form/paySuccess";
   }
 
   @RequestMapping(value={"pop/resettle/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String popResettle(@PathVariable("id") String billId, Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="rows", defaultValue="1000") int rows) {
     PageRequest pageRequest = new PageRequest(page, rows, new Sort(new String[] { "reaId" }));
     Page speOpReasons = this.speOpReasonService.getPage(pageRequest, getCurrentUserRestId(), SpecialReasonTypeEnum.RESETTLE_REASON.getCode(), EnableStatusEnum.NORMAL.getCode());
     model.addAttribute("speOpReasons", speOpReasons);
     model.addAttribute("billId", billId);
     return "bill/form/resettleReasonForm";
   }
 
   @RequestMapping(value={"resettle/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   public String resettle(@PathVariable("id") String billId, RedirectAttributes redirectAttributes, @RequestParam(value="reaId", defaultValue="") String reaId, Model model)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     dinerBill.setBillStatus(BillStatusEnum.RESETTLE.getCode());
     model.addAttribute("billId", billId);
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.updateResettle(dinerBill, getCurrentUserRestId(), reaId, map);
 
     doSynchMultiTable(map);
 
     redirectAttributes.addFlashAttribute("message", "反结账成功");
     return "redirect:/bill/list";
   }
 
   @RequestMapping(value={"isMoling/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String saveIsMoling(@PathVariable("id") String billId, @RequestParam(value="isMoling", required=true) String isMoling, Model model)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     dinerBill.setIsMoling(isMoling);
     if (TrueFalseEnum.TRUE.getCode().equals(isMoling)) {
       CashSetting cashSetting = this.cashSettingService.findByRestId(getCurrentUserRestId());
       if (cashSetting != null) cashSetting.getMolingMode();
 
     }
 
     dinerBill = (DinerBill)this.billService.save(dinerBill);
     setPayData(dinerBill, model);
     return "redirect:/bill/payPage/" + billId;
   }
 
   @RequestMapping(value={"customDiscount/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveCustomDiscount(@PathVariable("id") String billId, @RequestParam(value="discount", required=true) String discount, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     dinerBill.setIsCustomDiscount(TrueFalseEnum.TRUE.getCode());
     dinerBill.setDiscount(Integer.valueOf(discount));
     dinerBill.setCashDiscount(null);
     dinerBill.setDiscountName("");
     dinerBill = (DinerBill)this.billService.save(dinerBill);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     setPayData(dinerBill, model);
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setRel(dinerBill.getBillId());
     return ajax;
   }
 
   @RequestMapping(value={"notes/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveNotes(@PathVariable("id") String billId, @RequestParam(value="notes", required=true) String notes, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     dinerBill.setBillNotes(notes);
     dinerBill = (DinerBill)this.billService.save(dinerBill);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     setPayData(dinerBill, model);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("保存备注信息");
     ajax.setRel(dinerBill.getBillId());
     return ajax;
   }
 
   @RequestMapping(value={"cashDiscount/{id}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone saveCashDiscount(@PathVariable("id") String ccdId, @RequestParam(value="billId", required=true) String billId, Model model)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     CashDiscount cashDiscount = (CashDiscount)this.cashDiscountService.getOne(ccdId);
     dinerBill.setCashDiscount(cashDiscount);
     dinerBill.setDiscountName(cashDiscount.getDiscountName());
     dinerBill.setDiscount(Integer.valueOf(0));
     dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
     this.billService.save(dinerBill);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     return a;
   }
 
   @RequestMapping(value={"cancle/cashDiscount/{id}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public DwzAjaxDone cancleCashDiscount(@PathVariable("id") String ccdId, @RequestParam(value="billId", required=true) String billId, Model model)
   {
     DwzAjaxDone a = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     dinerBill.setCashDiscount(null);
     dinerBill.setDiscountName("");
     dinerBill.setDiscount(Integer.valueOf(0));
     dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
     dinerBill.setOtherDiscount("");
     this.billService.save(dinerBill);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     return a;
   }
 
   @RequestMapping(value={"drawBill/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   public String saveDrawBill(@PathVariable("id") String billId, @RequestParam(value="money", required=true) String money, @RequestParam(value="company", required=false) String company, @RequestParam(value="newCompany", required=false) String newCompany, Model model)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     if (StringUtils.isNotEmpty(newCompany))
       dinerBill.setCompany(newCompany.trim());
     else if (StringUtils.isNotEmpty(company))
       dinerBill.setCompany(company.trim());
     else {
       dinerBill.setCompany(null);
     }
 
     if (StringUtils.isNotEmpty(money)) {
       dinerBill.setIsDrawBill(TrueFalseEnum.TRUE.getCode());
       dinerBill.setDrawBillAmount(new BigDecimal(money));
     } else {
       dinerBill.setIsDrawBill(TrueFalseEnum.FALSE.getCode());
       dinerBill.setDrawBillAmount(null);
     }
 
     this.dinerBillService.saveDraw(dinerBill);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dinerBill);
 
     setPayData(dinerBill, model);
     return "redirect:/bill/payPage/" + billId;
   }
 
   @RequestMapping(value={"saveMoneyPayment/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveMoneyPayment(@PathVariable("id") String billId, @RequestParam(value="money", required=true) String money)
   {
     DinerBill dinerBill = (DinerBill)this.billService.getOne(billId);
     PaymentType pType = this.paymentTypeService.findeByRestIdAndPaymentType(getCurrentUserRestId(), PaymentTypeEnum.CASH.getCode());
     String op = OperationTypeEnum.UPDATE.getCode();
     DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, getCurrentUserRestId(), pType);
     if (dinerBillPayment == null)
     {
       dinerBillPayment = new DinerBillPayment();
       dinerBillPayment.setPaymentType(pType);
       dinerBillPayment.setDinerBill(dinerBill);
       dinerBillPayment.setBillNo(dinerBill.getBillNo());
       dinerBillPayment.setRestId(getCurrentUserRestId());
       dinerBillPayment.setPayTime(new Date());
       if ((PaymentTypeEnum.HOTEL_CREDIT.getCode().equals(pType.getPaymentType())) || 
         (PaymentTypeEnum.TEAM_CREDIT.getCode().equals(pType.getPaymentType())) || 
         (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equals(pType.getPaymentType()))) {
         dinerBillPayment.setCreditStatus(CreditStatusEnum.CREDIT_BILL.getCode());
       }
       op = OperationTypeEnum.CREATE.getCode();
     }
     dinerBillPayment.setMoney(new BigDecimal(money));
     this.dinerBillPaymentService.save(dinerBillPayment);
 
     doSynchSingleTable(op, dinerBillPayment);
 
     DwzAjaxDone ajax = new DwzAjaxDone();
     Table table = dinerBill.getTable();
     if ((table != null) && (table.getTableArea().getConsumeLow() != null) && 
       (getDishesConsume(dinerBill).floatValue() < table.getTableArea().getConsumeLow().floatValue())) {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setMessage("不满足最低消费，不能结账");
       ajax.setRel("2");
       return ajax;
     }
 
     ajax.setRel(TrueFalseEnum.TRUE.getCode());
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     return ajax;
   }
 
   @RequestMapping(value={"pay/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone pay(@PathVariable("id") String billId, @RequestParam(value="isPrint", required=true) String isPrint, @RequestParam(value="isForce", required=true) String isForce)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     synchronized (lock) {
    	 final DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
       String billStatus = dinerBill.getBillStatus();
       if ((BillStatusEnum.SETTLE.getCode().equals(billStatus)) || 
         (BillStatusEnum.BING_TAI.getCode().equals(billStatus)) || 
         (BillStatusEnum.CANCEL_BILL.getCode().equals(billStatus))) {
         ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
         ajax.setMessage("此账单无法结账，或者已经结账了");
         ajax.setRel("2");
         return ajax;
       }
 
       ajax.setType(UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getIsPayEnterDesk());
       final Table table = dinerBill.getTable();
       if ((!"true".equalsIgnoreCase(isForce)) && 
         (table != null) && (table.getTableArea().getConsumeLow() != null) && 
         (getDishesConsume(dinerBill).floatValue() < table.getTableArea().getConsumeLow().floatValue())) {
         ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
         ajax.setMessage("不满足最低消费，不能结账");
         ajax.setRel("2");
         return ajax;
       }
 
       String restId = getCurrentUserRestId();
       MembershipCard membershipCard = dinerBill.getMembershipCard();
       if (membershipCard != null) {
         PaymentType paymentType = this.paymentTypeService.findeByRestIdAndPaymentType(restId, PaymentTypeEnum.MEMBER_CARD.getCode());
         DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, restId, paymentType);
         if (dinerBillPayment != null)
         {
           if (dinerBillPayment.getMoney().compareTo(membershipCard.getBalance()) == 1) {
             ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
             ajax.setMessage("会员卡余额不足，不能结账");
             ajax.setRel("3");
             return ajax;
           }
         }
 
       }
 
       this.billService.calculator(getCurrentUserRestId(), dinerBill);
 
       if ("false".equalsIgnoreCase(isForce))
       {
         if (dinerBill.getPayableCost().compareTo(dinerBill.getRealCost()) != 0) {
           ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
           ajax.setMessage("付款金额不足，不能结账");
           ajax.setRel("1");
           return ajax;
         }
       }
 
       LinkedHashMap map = new LinkedHashMap();
       ArrayList cloudMethodParams = new ArrayList();
       dinerBill.setUpdateEmployee(getCurrentUser());
       this.billService.savePay(getCurrentUser(), dinerBill, membershipCard, getCurrentUserRestId(), isForce, map, cloudMethodParams);
 
       doSynchMultiTable(map);
 
       if (membershipCard != null) {
         if (BigDecimal.ZERO.compareTo(dinerBill.getMembercardCost()) < 0)
         {
           doCallCloudMethod(SmtCodeEnum.CONSUM, cloudMethodParams);
         }
 
         ArrayList cloudMethodParamsBalance = new ArrayList();
         MessageDataUtil messageDataUtil = new MessageDataUtil();
         messageDataUtil.balanceMessageData(cloudMethodParamsBalance, membershipCard);
 
         doCallCloudMethod(SmtCodeEnum.BALANCE, cloudMethodParamsBalance);
       }
 
       final String innerRestId = getCurrentUserRestId();
       final String innerIsPrint = isPrint;
       PriorityExecutor.execPrint(new Runnable()
       {
         public void run() {
           String userId = dinerBill.getUpdateEmployee().getEmpId();
           List<Printer> printerlist = BillController.this.printerService.findByRestIdAndStatusAndTypeAndUserPrintId(innerRestId, EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode(), userId);
           HashMap printParaments = new HashMap();
           printParaments.put("printerId", UserSettingCache.getInstance().getUserCache(userId).getPrinterId());
           printParaments.put("operatorName", dinerBill.getUpdateEmployee().getName());
           printParaments.put("operatorTime", new Date());
 
           printerlist = PrintUtil.getUserFrontdeskPrinter(printerlist, printParaments);
           for (Printer printer : printerlist) {
             if (!TrueFalseEnum.TRUE.getCode().equals(printer.getIsUseDrawer()))
               continue;
             if ((printer.getIp() != null) && (!printer.getIp().isEmpty()))
             {
               if (Print.openDrawer(printer.getIp(), printer.getSysName()))
                 continue;
               System.out.println("钱箱打开失败!");
             }
             else
             {
               System.out.println("没有设置钱箱IP或者端口!");
             }
 
           }
 
           if ((dinerBill.getBillType().equals(BillTypeEnum.KUAICAN_BILL.getCode())) && 
             (TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(userId).getIsFastfoodBillPrint())))
           {
             try
             {
               List unXiadanList = new ArrayList();
               unXiadanList.addAll(BillController.this.dinerBillDisheService.findByRestIdAndBillId(innerRestId, dinerBill.getBillId()));
               List<DinerBillDishesSet> dinerBillDishesSets = BillController.this.dinerBillDishesSetService.findByRestIdAndDinerBill(innerRestId, dinerBill);
               for (DinerBillDishesSet e : dinerBillDishesSets) {
                 if (!e.getDsStatus().equals(DishesStatusEnum.UNXIADAN.getCode()))
                   continue;
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
                     DishesCategory dishesCategory = ((Dishe)BillController.this.disheService.getOne(dishesId)).getDishesCategory();
                     DishesCategory newDishesCategory = new DishesCategory();
                     newDishesCategory.setCategoryId(dishesCategory.getCategoryId());
                     dbdSet.setDishesCategory(newDishesCategory);
                     dishesDishesList.add(dbdSet);
                   }
                 } catch (Exception e1) {
                   e1.printStackTrace();
                 }
                 dbd.setDishesSetDishesList(dishesDishesList);
                 unXiadanList.add(dbd);
               }
 
               dinerBill.setDinerBillDishes(unXiadanList);
               HashMap printParaments_kuaican = new HashMap();
               printParaments_kuaican.put("operatorName", dinerBill.getUpdateEmployee().getName());
               BillController.this.xiadanPrinterService.printXiadan(innerRestId, dinerBill, table, printParaments_kuaican);
             } catch (Exception e) {
               e.printStackTrace();
               System.out.println("结账成功，打印失败");
             }
 
           }
 
           boolean isPrinted = false;
           if ("true".equalsIgnoreCase(innerIsPrint))
             try {
               dinerBill.setDinerBillDishes(BillController.this.dinerBillDisheService.findByRestIdAndBillId(innerRestId, dinerBill.getBillId()));
               dinerBill.setDinerBillDishesSets(BillController.this.dinerBillDishesSetService.findByRestIdAndDinerBill(innerRestId, dinerBill));
               isPrinted = BillController.this.payPrinterService.printPay(innerRestId, dinerBill, table, "PAY", "2", printParaments);
               if (!isPrinted)
                 System.out.println("打印失败，没有找到匹配的打印机");
             }
             catch (Exception e) {
               e.printStackTrace();
               System.out.println("打印失败");
             }
         }
       });
       PriorityExecutor.execLog(new Runnable()
       {
         public void run() {
           LinkedHashMap mapSync = new LinkedHashMap();
           dinerBill.setDinerBillDishes(BillController.this.dinerBillDisheService.findByRestIdAndBillId(innerRestId, dinerBill.getBillId()));
           dinerBill.setDinerBillDishesSets(BillController.this.dinerBillDishesSetService.findByRestIdAndDinerBill(innerRestId, dinerBill));
 
           for (DinerBillDishe dinerBillDishe : dinerBill.getDinerBillDishes()) {
             if ((!dinerBillDishe.getDishesStatus().equals(DishesStatusEnum.UNSERVE.getCode())) && 
               (!dinerBillDishe.getDishesStatus().equals(DishesStatusEnum.SERVED.getCode())) && 
               (!dinerBillDishe.getDishesStatus().equals(DishesStatusEnum.SERVED_CANCEL.getCode())) && 
               (!dinerBillDishe.getDishesStatus().equals(DishesStatusEnum.UNXIADAN.getCode())))
               continue;
             Dishe dishe = (Dishe)BillController.this.disheService.getOne(dinerBillDishe.getDishesId());
             dishe.setSaleNum(Float.valueOf(dishe.getSaleNum().floatValue() + dinerBillDishe.getUnitNum()));
             BillController.this.disheService.save(dishe);
 
             mapSync.put(dishe.getDishesId() + "_" + OperationTypeEnum.UPDATE.getCode(), dishe);
           }
 
           for (DinerBillDishesSet dinerBillDishesSet : dinerBill.getDinerBillDishesSets()) {
             if ((!dinerBillDishesSet.getDsStatus().equals(DishesStatusEnum.UNSERVE.getCode())) && 
               (!dinerBillDishesSet.getDsStatus().equals(DishesStatusEnum.SERVED.getCode())) && 
               (!dinerBillDishesSet.getDsStatus().equals(DishesStatusEnum.SERVED_CANCEL.getCode())) && 
               (!dinerBillDishesSet.getDsStatus().equals(DishesStatusEnum.UNXIADAN.getCode())))
               continue;
             DishesSet d = (DishesSet)BillController.this.dishesSetService.getOne(dinerBillDishesSet.getDsId());
             if (d.getSaleNum() != null)
               d.setSaleNum(Float.valueOf(d.getSaleNum().floatValue() + dinerBillDishesSet.getUnitNum()));
             else {
               d.setSaleNum(Float.valueOf(dinerBillDishesSet.getUnitNum()));
             }
 
             BillController.this.dishesSetService.save(d);
 
             mapSync.put(d.getDsId() + "_" + OperationTypeEnum.UPDATE.getCode(), d);
           }
 
           BillController.this.doSynchMultiTable(mapSync);
 
           String logNotes = "结账：";
 
           StringBuilder sb = new StringBuilder();
           List dinerBillPayments = dinerBill.getDinerBillPayments();
           for (int i = 0; i < dinerBillPayments.size(); i++) {
             DinerBillPayment dinerBillPayment = (DinerBillPayment)dinerBillPayments.get(i);
             PaymentType paymentType = dinerBillPayment.getPaymentType();
             sb.append(paymentType.getPaymentName());
             sb.append("：");
             sb.append(BigDecimalUtil.format(dinerBillPayment.getMoney()));
             if (i != dinerBillPayments.size() - 1) {
               sb.append(",");
             }
           }
           dinerBill.setPayments(sb.toString());
           logNotes = logNotes + dinerBill.getPayments();
           BillController.this.dinerBillService.saveBusiLog(innerRestId, dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.SETTLE, logNotes);
         }
       });
       ajax.setRel("0");
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setMessage("结账成功");
       ajax.setNavTabId(UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getIsPayEnterDesk());
     }
 
     return ajax;
   }
 
   private BigDecimal getDishesConsume(DinerBill dinerBill)
   {
     BigDecimal total = dinerBill.getOriCost() == null ? BigDecimal.ZERO : dinerBill.getOriCost();
     for (DinerBillDishe e : dinerBill.getDinerBillDishes()) {
       Dishe dishe = (Dishe)this.disheService.getOne(e.getDishesId());
 
       if ((TrueFalseEnum.TRUE.getCode().equals(dishe.getIsAddMinCharge())) && (!DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(e.getDishesStatus())) && (!DishesStatusEnum.SERVED_CANCEL.getCode().equals(e.getDishesStatus()))) {
         total = total.subtract(e.getRealCost());
       }
     }
 
     for (DinerBillDishesSet e : dinerBill.getDinerBillDishesSets()) {
       DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(e.getDsId());
 
       if ((TrueFalseEnum.TRUE.getCode().equals(dishesSet.getIsAddMinCharge())) && (!DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(e.getDsStatus())) && (!DishesStatusEnum.SERVED_CANCEL.getCode().equals(e.getDsStatus()))) {
         total = total.subtract(e.getRealCost());
       }
     }
     return total;
   }
 
   @RequestMapping(value={"predict/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String predict(@PathVariable("id") String billId, Model model, RedirectAttributes redirectAttributes)
   {
     model.addAttribute("billId", billId);
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
 
     this.billService.calculator(getCurrentUserRestId(), dinerBill);
 
     boolean isPrint = false;
     try {
       isPrint = this.payPrinterService.printPay(getCurrentUserRestId(), dinerBill, dinerBill.getTable(), "PREDICT", "2", null);
       if (!isPrint) {
         redirectAttributes.addFlashAttribute("message", "打印失败，没有找到匹配的打印机");
         return "redirect:/bill/payPage/" + dinerBill.getBillId();
       }
     } catch (PrinterException e) {
       e.printStackTrace();
       redirectAttributes.addFlashAttribute("message", "打印失败");
       return "redirect:/bill/payPage/" + dinerBill.getBillId();
     }
     redirectAttributes.addFlashAttribute("message", "预结成功");
     return "redirect:/bill/payPage/" + dinerBill.getBillId();
   }
 
   @RequestMapping(value={"dishCuicai/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishCuicai(@PathVariable("id") String id, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     List printers = this.printerService.findByRestIdAndStatusAndTypeAndUserPrintId(getCurrentUserRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.BACK_KITCHEN.getCode(), getCurrentUserId());
     if ((printers == null) || (printers.size() == 0))
     {
       ajax.setStatusCode("500");
       ajax.setMessage("无法催菜，请先设置后厨打印机！");
       ajax.setRel(id);
       return ajax;
     }
 
     DinerBill dinerBill = null;
     DinerBillDishe dinerBillDishe = null;
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       LinkedHashMap map = new LinkedHashMap();
       DinerBillDishesSet dinerBillDishesSet = this.dinerBillService.saveDishsSetCuiCai(getCurrentUserRestId(), id, map);
 
       doSynchMultiTable(map);
 
       dinerBillDishe = new DinerBillDishe();
       dinerBill = dinerBillDishesSet.getDinerBill();
       dinerBillDishe.setCreateTime(dinerBillDishesSet.getCreateTime());
       dinerBillDishe.setDishesName(dinerBillDishesSet.getDsName());
     }
     else {
       LinkedHashMap map = new LinkedHashMap();
       dinerBillDishe = this.dinerBillService.saveDishCuiCai(getCurrentUserRestId(), id, map);
 
       doSynchMultiTable(map);
 
       dinerBill = dinerBillDishe.getDinerBill();
     }
     try
     {
       this.cuicaiPrinterService.printCuiCaiOne(getCurrentUserRestId(), dinerBillDishe, dinerBill);
     } catch (PrinterException e) {
       e.printStackTrace();
       ajax.setStatusCode("500");
       ajax.setMessage("打印失败");
       ajax.setRel(id);
       return ajax;
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage(dinerBillDishe.getDishesName() + " 催菜成功");
     ajax.setRel(id);
     return ajax;
   }
 
   @RequestMapping(value={"dishHuacai/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishHuacai(@PathVariable("id") String id, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       DinerBillDishesSet idinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(id);
       if (DishesStatusEnum.UNSERVE.getCode().equals(idinerBillDishesSet.getDsStatus())) {
         LinkedHashMap map = new LinkedHashMap();
         DinerBillDishesSet dinerBillDishesSet = this.dinerBillService.saveDishesSetHuacai(getCurrentUserRestId(), id, map);
 
         doSynchMultiTable(map);
 
         ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
         ajax.setMessage(dinerBillDishesSet.getDsName() + " 划菜成功");
         ajax.setRel(id);
       } else {
         ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
         ajax.setMessage(idinerBillDishesSet.getDsName() + " 已经划菜");
         ajax.setRel(id);
       }
     } else {
       DinerBillDishe idinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(id);
       if (DishesStatusEnum.UNSERVE.getCode().equals(idinerBillDishe.getDishesStatus())) {
         LinkedHashMap map = new LinkedHashMap();
         DinerBillDishe dinerBillDishe = this.dinerBillService.saveDishHuacai(getCurrentUserRestId(), id, map);
 
         doSynchMultiTable(map);
 
         ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
         ajax.setMessage(dinerBillDishe.getDishesName() + " 划菜成功");
         ajax.setRel(id);
       } else {
         ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
         ajax.setMessage(idinerBillDishe.getDishesName() + " 已经划菜");
         ajax.setRel(id);
       }
 
     }
 
     return ajax;
   }
 
   
   
   
   @RequestMapping(value={"zengcai/{id}/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishZengcai(@PathVariable("id") String id, @PathVariable("billId") String billId, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
	 
     DwzAjaxDone ajax = new DwzAjaxDone();
  
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(id);
       LinkedHashMap map = new LinkedHashMap();
       if (DiscountTypeEnum.GIVE.getCode().equals(dinerBillDishesSet.getDiscountType())) {
         this.dinerBillService.saveCancelZengcai(getCurrentUserRestId(), billId, dinerBillDishesSet, map);
         ajax.setMessage("取消赠送套餐【" + dinerBillDishesSet.getDsName() + "】成功");
       } else {
         this.dinerBillService.saveZengcai(getCurrentUserRestId(), billId, dinerBillDishesSet, map);
         ajax.setMessage("赠送套餐【" + dinerBillDishesSet.getDsName() + "】 成功");
       }
 
       doSynchMultiTable(map);
     }
     else {
       DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(id);
       LinkedHashMap map = new LinkedHashMap();
 
       if (DiscountTypeEnum.GIVE.getCode().equals(dinerBillDishe.getDiscountType())) {
         this.dinerBillService.saveCancelZengcai(getCurrentUserRestId(), billId, dinerBillDishe, map);
         ajax.setMessage(dinerBillDishe.getDishesName() + " 取消赠菜成功");
       }
       else {
         this.dinerBillService.saveZengcai(getCurrentUserRestId(), billId, dinerBillDishe, map);
         ajax.setMessage(dinerBillDishe.getDishesName() + " 赠菜成功");
       }
 
       doSynchMultiTable(map);
     }
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setRel(id);
     return ajax;
   }
 
   @RequestMapping(value={"dishDelete/{billId}/{bdId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishDelete(@PathVariable("billId") String billId, @PathVariable("bdId") String bdId, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
	   
     DwzAjaxDone ajax = new DwzAjaxDone();
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       try {
         LinkedHashMap map = new LinkedHashMap();
         this.dinerBillService.saveDishSetDelete(getCurrentUserRestId(), billId, bdId, map);
 
         doSynchMultiTable(map);
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
     else {
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveDishDelete(getCurrentUserRestId(), billId, bdId, map);
 
       doSynchMultiTable(map);
     }
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("删除成功");
     ajax.setRel(billId);
     return ajax;
   }
 /**
  * 删除菜肴
  * @param billId
  * @param bdId
  * @param isSet
  * @param model
  * @return
  * @throws JsonProcessingException
  */
   
 /*  @RequestMapping(value={"dishDeletes/{billId}/{bdId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone jiacais(@PathVariable("billId") String billId, @PathVariable("bdId") String bdId, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
 	 
 	  DwzAjaxDone ajax=new DwzAjaxDone();
       String[] bdIds = bdId.split(",");
       for (int i = 0; i < bdIds.length; i++) {
     	  if(tId!="" && tId !=null && ajax.getValue()!="" && ajax.getValue()!=null){
     		  billId=ajax.getValue();
     	  }
     	  ajax=dishDeletes(billId,   bdIds[i],   isSet,  model);
           
       }
 	 return ajax;
   }*/
   
   /**
    * 删除菜
    * @param billId
    * @param bdId
    * @param isSet
    * @param model
    * @return
    * @throws JsonProcessingException
    */
   @RequestMapping(value={"dishDeletes/{billId}/{bdId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishDeletes(@PathVariable("billId") String billId, @PathVariable("bdId") String bdId, @RequestParam(value="isSet", required=false) String isSet, Model model)
     throws JsonProcessingException
   {
	
	
     DwzAjaxDone ajax = new DwzAjaxDone();
    
    	 if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
    	       try {
    	         LinkedHashMap map = new LinkedHashMap();
    	         this.dinerBillService.saveDishSetDeletes(getCurrentUserRestId(), billId, bdId, map);
    	 
    	         doSynchMultiTable(map);
    	       }
    	       catch (Exception e)
    	       {
    	         e.printStackTrace();
    	       }
    	     }
    	     else {
    	    	
    	       LinkedHashMap map = new LinkedHashMap();
    	       this.dinerBillService.saveDishDeletes(getCurrentUserRestId(), billId, bdId, map);
    	       doSynchMultiTable(map);
    	     }
     
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("删除成功");
     ajax.setRel(billId);
     return ajax;
   }
   
   
   
   
   
   
   
   
   @RequestMapping(value={"dishNumChange/{billId}/{bdId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishNumChange(@PathVariable("billId") String billId, @PathVariable("bdId") String bdId, @RequestParam(value="newDishNum", defaultValue="1") String newDishNum, @RequestParam(value="isSet", required=false) String isSet, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="oldDishNum", defaultValue="1") String oldDishNum, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Map messageMap = null;
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       if ("0".equals(isJudgeRm)) {
         try {
           messageMap = this.dinerBillService.tcStockUpdateByNumJudge(bdId, getCurrentUserRestId(), oldDishNum, newDishNum);
         } catch (Exception e) {
           e.printStackTrace();
         }
       } else {
         try {
           LinkedHashMap map = new LinkedHashMap();
           messageMap = this.dinerBillService.saveDishesSetNumChange(getCurrentUserRestId(), billId, bdId, newDishNum, map);
 
           doSynchMultiTable(map);
         }
         catch (Exception e) {
           e.printStackTrace();
         }
         ajax.setRel(billId);
       }
 
     }
     else if ("0".equals(isJudgeRm)) {
       boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
       DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
       Dishe dishe = (Dishe)this.disheService.getOne(dinerBillDishe.getDishesId());
       messageMap = this.dinerBillService.stockCheckForDishes(getCurrentUserRestId(), bdId, dishe, Float.parseFloat(newDishNum), isJudgeDishRaws);
     } else {
       LinkedHashMap map = new LinkedHashMap();
       messageMap = this.dinerBillService.saveDishNumChange(getCurrentUserRestId(), billId, bdId, newDishNum, map);
 
       doSynchMultiTable(map);
 
       ajax.setRel(billId);
     }
 
     ajax.setMessageMap(messageMap);
     return ajax;
   }
 
   @RequestMapping(value={"validateCardNo"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone validateCardNoAndPassword(@RequestParam(value="cardNo", required=true) String cardNo, @RequestParam(value="cardPassword", required=true) String cardPassword, Model model) throws JsonProcessingException
   {
     boolean result = false;
     MembershipCard membershipCard = this.membershipCardService.findByCardNoAndCardPassword(cardNo, cardPassword, getCurrentUserRestId());
     if (membershipCard != null) {
       result = true;
     }
     DwzAjaxDone ajax = new DwzAjaxDone();
     ObjectMapper mapper = new ObjectMapper();
     ajax.setMessage(mapper.writeValueAsString(Boolean.valueOf(result)));
     return ajax;
   }
 
   @RequestMapping(value={"needMoney"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone needMoney(@RequestParam(value="mcId", required=true) String mcId, @RequestParam(value="billId", required=true) String billId, Model model) throws JsonProcessingException
   {
     BigDecimal needMoney = this.dinerBillService.needMoneyForMember(getCurrentUserRestId(), billId, mcId);
     DwzAjaxDone ajax = new DwzAjaxDone();
     ajax.setValue(needMoney.toString());
     return ajax;
   }
 
   @RequestMapping(value={"pop/userdefined/create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String userdefinedForm(@RequestParam("billId") String billId, @RequestParam(value="billType", defaultValue="1") String billType, Model model)
   {
     List dishesCategorys = this.dishesCategoryService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     model.addAttribute("dishesCategorys", dishesCategorys);
 
     List dishesUnits = this.dishesUnitService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     model.addAttribute("dishesUnits", dishesUnits);
 
     model.addAttribute("billId", billId);
     model.addAttribute("billType", billType);
     return "bill/userDefinedDishForm";
   }
 
   @RequestMapping(value={"pop/orderUserdefined/create/{orderId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String orderUserdefinedForm(@PathVariable("orderId") String orderId, Model model)
   {
     List dishesCategorys = this.dishesCategoryService.findAllDishesCategoryByRestId(getCurrentUserRestId());
     model.addAttribute("dishesCategorys", dishesCategorys);
 
     List dishesUnits = this.dishesUnitService.findAllDishesUnitByRestId(getCurrentUserRestId());
     model.addAttribute("dishesUnits", dishesUnits);
 
     model.addAttribute("billId", orderId);
     model.addAttribute("type", "order");
     return "bill/userDefinedDishForm";
   }
 
   @RequestMapping(value={"pop/billPrint/{billId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String billPrintForm(@PathVariable("billId") String billId, Model model)
   {
     List printers = this.printerService.findAllPrinters(getCurrentUserRestId());
     model.addAttribute("billId", billId);
     model.addAttribute("printers", printers);
     return "bill/billPrintForm";
   }
 
   @RequestMapping(value={"billPrint/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone billPrint(@PathVariable("billId") String billId, @RequestParam(value="printerId", required=true) String printerId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     Printer printer = (Printer)this.printerService.getOne(printerId);
     String type = printer.getType();
     try {
       HashMap printParaments = new HashMap();
       printParaments.put("isRePrint", Boolean.TRUE);
       if (PrinterTypeEnum.FRONT_DESC.getCode().equals(type)) {
         printParaments.put("printerId", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getPrinterId());
         this.dinerBillPrinterService.printPay(dinerBill, "PAY", printer.getSysName(), printer, "2", printParaments);
       } else if (PrinterTypeEnum.BACK_KITCHEN.getCode().equals(type)) {
         List disheAndDisheSetqsList = new ArrayList();
         disheAndDisheSetqsList.addAll(dinerBill.getDinerBillDishes());
         if (dinerBill.getDinerBillDishesSets() != null)
         {
           for (DinerBillDishesSet e : dinerBill.getDinerBillDishesSets())
           {
             DinerBillDishe dbd = new DinerBillDishe();
             dbd.setDishesName(e.getDsName());
             dbd.setUnitNum(e.getUnitNum());
             dbd.setUnitName(e.getUnitName());
             dbd.setNotes(e.getAllNotes());
             dbd.setIsSet(TrueFalseEnum.TRUE.getCode());
 
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
             disheAndDisheSetqsList.add(dbd);
           }
         }
         dinerBill.setDinerBillDishes(disheAndDisheSetqsList);
         this.dinerBillPrinterService.printXiadan(dinerBill, printer, printParaments);
       }
     } catch (Exception e) {
       e.printStackTrace();
       ajax.setMessage("打印失败！");
       return ajax;
     }
     ajax.setMessage("打印成功！");
     return ajax;
   }
 
   @RequestMapping(value={"userdefined/create"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone userdefinedFormSave(@RequestParam("billId") String billId, @RequestParam(value="billType", defaultValue="1") String billType, @RequestParam(value="dishsName", required=true) String dishsName, @RequestParam(value="dishsCatagoryId", required=true) String dishsCatagoryId, @RequestParam(value="unitId", required=true) String unitId, @RequestParam(value="unitPrice", required=true) String unitPrice, @RequestParam(value="isOnsale", required=true, defaultValue="1") String isOnsale, @RequestParam(value="notes", required=true) String notes, @RequestParam(value="tId", required=true) String tId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     if ((billType == null) || (billType.isEmpty()))
     {
       billType = BillTypeEnum.NORMAL_BILL.getCode();
     }
     if ((billId == null) || (billId.isEmpty()))
     {
       DinerBill d = new DinerBill();
       d.setBillTime(new Date());
       d.setBillType(billType);
       d.setTable(null);
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveCreateTableDinerBill(getCurrentUserRestId(), d, null, tId, map);
 
       doSynchMultiTable(map);
 
       billId = d.getBillId();
       ajax.setType(billType);
     }
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveUserDefinedDish(getCurrentUserRestId(), billId, dishsName, notes, dishsCatagoryId, unitId, new BigDecimal(unitPrice), getCurrentUser(), isOnsale, map);
 
     doSynchMultiTable(map);
 
     ajax.setValue(billId);
     ajax.setMessage("");
     return ajax;
   }
 
   @RequestMapping(value={"orderUserdefined/create/{orderId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone orderUserdefinedFormSave(@PathVariable("orderId") String orderId, @RequestParam(value="dishsName", required=true) String dishsName, @RequestParam(value="dishsCatagoryId", required=true) String dishsCatagoryId, @RequestParam(value="unitId", required=true) String unitId, @RequestParam(value="unitPrice", required=true) String unitPrice, @RequestParam(value="isOnsale", required=true, defaultValue="1") String isOnsale, @RequestParam(value="notes", required=true) String notes, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveOrderUserDefinedDish(orderId, dishsName, notes, dishsCatagoryId, unitId, new BigDecimal(unitPrice), getCurrentUser(), isOnsale, map);
 
     doSynchMultiTable(map);
 
     ajax.setValue(orderId);
     ajax.setMessage("");
     return ajax;
   }
 
   @RequestMapping(value={"pop/cooking/update/{billId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String cookingNotes(@PathVariable("billId") String billId, Model model)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
 
     List dishesTastes = this.dishesTasteService.findAllDishesTasteByRestId(getCurrentUserRestId());
     model.addAttribute("dishesTastes", dishesTastes);
 
     List dishesAvoidfoods = this.dishesAvoidfoodService.findAllDishesAvoidfoodByRestId(getCurrentUserRestId());
     model.addAttribute("dishesAvoidfoods", dishesAvoidfoods);
 
     model.addAttribute("billId", billId);
     model.addAttribute("dinerBill", dinerBill);
     return "bill/cookingNotes";
   }
 
   @RequestMapping(value={"cookingNotes/update/{billId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone cookingNotesSave(@PathVariable("billId") String billId, @RequestParam(value="avoidArray", required=true) String avoidArray, @RequestParam(value="tasteArray", required=true) String tasteArray, @RequestParam(value="pungent", required=true) int pungent, @RequestParam(value="notes", required=true) String notes, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveCookingNotes(billId, avoidArray, tasteArray, pungent, notes, map);
 
     doSynchMultiTable(map);
 
     ajax.setMessage("保存备注成功");
     return ajax;
   }
 
   @RequestMapping(value={"pop/dishCooking/update/{bdId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String dishCookingNotes(@PathVariable("bdId") String bdId, @RequestParam(value="isSet", required=false) String isSet, Model model)
   {
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdId);
       model.addAttribute("orderBillDishe", dinerBillDishesSet);
     }
     else {
       DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
       model.addAttribute("orderBillDishe", dinerBillDishe);
     }
 
     List dishesTastes = this.dishesTasteService.findAllDishesTasteByRestId(getCurrentUserRestId());
     model.addAttribute("dishesTastes", dishesTastes);
 
     List dishesAvoidfoods = this.dishesAvoidfoodService.findAllDishesAvoidfoodByRestId(getCurrentUserRestId());
     model.addAttribute("dishesAvoidfoods", dishesAvoidfoods);
     model.addAttribute("bdId", bdId);
     model.addAttribute("note_isSet", isSet);
 
     return "bill/dishCookingNotes";
   }
   
   
   
   /**
    * 忌口
    * @param model
    * @return
    */
   @RequestMapping(value={"pop/dishCooking/update"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String dishCookingNotesIos( Model model)
   {
 
 
     List dishesAvoidfoods = this.dishesAvoidfoodService.findAllDishesAvoidfoodByRestId(getCurrentUserRestId());
     model.addAttribute("dishesAvoidfoods", dishesAvoidfoods);
 
     return "bill/dishCookingNotes";
   }
   
   /**
    * 忌口多个
    * @param bdId
    * @param avoidArray
    * @param tasteArray
    * @param pungent
    * @param notes
    * @param isSet
    * @param model
    * @return
    * @throws JsonProcessingException
    */
     @RequestMapping(value={"dishCookingNotesd/update/{bdId}"}, produces={"application/json"})
     @ResponseBody
     public DwzAjaxDone dishCookingNotesSaved(@PathVariable("bdId") String bdId, @RequestParam(value="avoidArray", required=true) String avoidArray, @RequestParam(value="tasteArray", required=true) String tasteArray, @RequestParam(value="pungent", required=true) int pungent, @RequestParam(value="notes", required=true) String notes, @RequestParam(value="isSet", defaultValue="") String isSet, Model model)
       throws JsonProcessingException
     {
  	   String temp = bdId;
  		  String tempArray [] = temp.split(",");
       DwzAjaxDone ajax = new DwzAjaxDone();
       for(String bdIds:tempArray){
       LinkedHashMap map = new LinkedHashMap();
   
       this.dinerBillService.saveDishCookingNotes(bdIds, avoidArray, tasteArray, pungent, notes, isSet, map);
   
       doSynchMultiTable(map);
       }
       ajax.setMessage("保存备注成功");
       return ajax;
     }
     /**
      * 赠菜
      * @param id
      * @param billId
      * @param isSet
      * @param model
      * @return
      * @throws JsonProcessingException
      */
     @RequestMapping(value={"zengcais/{id}/{billId}"}, produces={"application/json"})
     @ResponseBody
     public DwzAjaxDone dishZengcais(@PathVariable("id") String id, @PathVariable("billId") String billId, @RequestParam(value="isSet", required=false) String isSet, Model model)
       throws JsonProcessingException
     {
  	   //多个赠菜
    	 try {
			Thread.sleep(50);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  	   String temp = id;
  		  String tempArray [] = temp.split(",");
       DwzAjaxDone ajax = new DwzAjaxDone();
      for(String bdId:tempArray){
       if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
         DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdId);
         LinkedHashMap map = new LinkedHashMap();
         if (DiscountTypeEnum.GIVE.getCode().equals(dinerBillDishesSet.getDiscountType())) {
           this.dinerBillService.saveCancelZengcai(getCurrentUserRestId(), billId, dinerBillDishesSet, map);
           ajax.setMessage("取消赠送套餐【" + dinerBillDishesSet.getDsName() + "】成功");
         } else {
           this.dinerBillService.saveZengcai(getCurrentUserRestId(), billId, dinerBillDishesSet, map);
           ajax.setMessage("赠送套餐【" + dinerBillDishesSet.getDsName() + "】 成功");
         }
   
         doSynchMultiTable(map);
       }
       else {
         DinerBillDishe dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
         LinkedHashMap map = new LinkedHashMap();
   
         if (DiscountTypeEnum.GIVE.getCode().equals(dinerBillDishe.getDiscountType())) {
           this.dinerBillService.saveCancelZengcai(getCurrentUserRestId(), billId, dinerBillDishe, map);
           ajax.setMessage(dinerBillDishe.getDishesName() + " 取消赠菜成功");
         }
         else {
           this.dinerBillService.saveZengcai(getCurrentUserRestId(), billId, dinerBillDishe, map);
           ajax.setMessage(dinerBillDishe.getDishesName() + " 赠菜成功");
         }
   
         doSynchMultiTable(map);
       }
       }
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setRel(id);
       return ajax;
     }
   
   @RequestMapping(value={"dishCookingNotes/update/{bdId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishCookingNotesSave(@PathVariable("bdId") String bdId, @RequestParam(value="avoidArray", required=true) String avoidArray, @RequestParam(value="tasteArray", required=true) String tasteArray, @RequestParam(value="pungent", required=true) int pungent, @RequestParam(value="notes", required=true) String notes, @RequestParam(value="isSet", defaultValue="") String isSet, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
 
     this.dinerBillService.saveDishCookingNotes(bdId, avoidArray, tasteArray, pungent, notes, isSet, map);
 
     doSynchMultiTable(map);
 
     ajax.setMessage("保存备注成功");
     return ajax;
   }
 
   @RequestMapping(value={"pop/xiadanConfirm/{billId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String xiadanConfirm(@PathVariable("billId") String billId, Model model)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     List<DinerBillDishe> all = dinerBill.getDinerBillDishes();
     List<DinerBillDishesSet> allSet = dinerBill.getDinerBillDishesSets();
 
     List newList = new ArrayList();
 
     for (DinerBillDishe d : all)
     {
       if (d.getDishesStatus().equals(DishesStatusEnum.UNXIADAN.getCode())) {
         newList.add(d);
       }
     }
     for (DinerBillDishesSet ds : allSet) {
       if (ds.getDsStatus().equals(DishesStatusEnum.UNXIADAN.getCode())) {
         DinerBillDishe newDbd = new DinerBillDishe();
         newDbd.setDishesName(ds.getDsName());
         newDbd.setUnitNum(ds.getUnitNum());
         newDbd.setUnitName(ds.getUnitName());
         newList.add(newDbd);
       }
     }
     model.addAttribute("dinerBill", dinerBill);
     model.addAttribute("newList", newList);
     model.addAttribute("billPlaceEnterDeskOrPay", UserSettingCache.getInstance().getUserCache(getCurrentUserId()).getBillPlaceEnterDeskOrPay());
 
     return "bill/xiadanConfirm";
   }
 
   private String getSubPaymentType(String paymentType)
   {
     if ((PaymentTypeEnum.CASH.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.CARD.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.CHEQUE.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.COUPON.getCode().equalsIgnoreCase(paymentType)))
       return "MONEY";
     if (PaymentTypeEnum.MEMBER_CARD.getCode().equalsIgnoreCase(paymentType))
       return "CARD";
     if ((PaymentTypeEnum.HOTEL_CREDIT.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.TEAM_CREDIT.getCode().equalsIgnoreCase(paymentType)) || 
       (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equalsIgnoreCase(paymentType))) {
       return "CREDIT";
     }
     if (PaymentTypeEnum.WEB_SITE_MEMBER.getCode().equalsIgnoreCase(paymentType)) {
       return "WEBSITE";
     }
     return "OTHER";
   }
 
   @RequestMapping(value={"pop/tuicaiReason/{bdId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String tuicaiReason(@PathVariable("bdId") String bdId, @RequestParam(value="isSet", required=false) String isSet, Model model, HttpServletRequest request)
   {
     List speOpReasons = this.speOpReasonService.findByRestIdAndReaType(getCurrentUserRestId(), SpecialReasonTypeEnum.CANCEL_DISH_REASON.getCode());
     model.addAttribute("speOpReasons", speOpReasons);
     DinerBillDishe dinerBillDishe = null;
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       dinerBillDishe = new DinerBillDishe();
       DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(bdId);
       if (((dinerBillDishesSet.getCancelReasonId() == null) || (dinerBillDishesSet.getCancelReasonId().isEmpty())) && 
         (speOpReasons != null) && (speOpReasons.size() > 0)) {
         SpeOpReason f = (SpeOpReason)speOpReasons.get(0);
         dinerBillDishe.setCancelReasonId(f.getReaId());
       }
 
       dinerBillDishe.setIsSet(TrueFalseEnum.TRUE.getCode());
       dinerBillDishe.setBdId(dinerBillDishesSet.getBdsId());
       dinerBillDishe.setUnitNum(dinerBillDishesSet.getUnitNum());
     } else {
       dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(bdId);
       if (((dinerBillDishe.getCancelReasonId() == null) || (dinerBillDishe.getCancelReasonId().isEmpty())) && 
         (speOpReasons != null) && (speOpReasons.size() > 0)) {
         SpeOpReason f = (SpeOpReason)speOpReasons.get(0);
         dinerBillDishe.setCancelReasonId(f.getReaId());
       }
     }
 
     model.addAttribute("dinerBillDishe", dinerBillDishe);
 
     return "bill/tuicaiReasonForm";
   }
 
   @RequestMapping(value={"dishTuicai/{id}/{cancelReasonId}/{cancelNum}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishTuicai(@PathVariable("id") String id, @PathVariable("cancelReasonId") String cancelReasonId, @PathVariable("cancelNum") String cancelNum, @RequestParam(value="isSet", required=false) String isSet, @RequestParam(value="newAddReason", required=false) String newAddReason, @RequestParam(value="materialHand", required=false) String materialHand, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = null;
     DinerBillDishe dinerBillDishe = null;
     Table table = null;
     float num = Float.valueOf(cancelNum.replaceAll("-", ".")).floatValue();
     if (TrueFalseEnum.TRUE.getCode().equals(isSet)) {
       DinerBillDishesSet dinerBillDishesSet = (DinerBillDishesSet)this.dinerBillDishesSetService.getOne(id);
       dinerBill = dinerBillDishesSet.getDinerBill();
       table = dinerBill.getTable();
       if ((DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dinerBillDishesSet.getDsStatus())) || 
         (DishesStatusEnum.SERVED_CANCEL.getCode().equals(dinerBillDishesSet.getDsStatus()))) {
         ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
         ajax.setMessage("此套餐已经退菜!");
         ajax.setRel(dinerBillDishesSet.getDsStatus());
         return ajax;
       }
       try {
         LinkedHashMap map = new LinkedHashMap();
         dinerBillDishesSet = this.dinerBillService.dishsSetTuicai(getCurrentUserRestId(), dinerBillDishesSet, cancelReasonId, Float.valueOf(num), newAddReason, materialHand, map);
 
         doSynchMultiTable(map);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
       dinerBillDishe = new DinerBillDishe();
       dinerBillDishe.setOrderTime(dinerBillDishesSet.getOrderTime());
       dinerBillDishe.setDishesName(dinerBillDishesSet.getDsName());
       dinerBillDishe.setUnitNum(dinerBillDishesSet.getUnitNum());
       dinerBillDishe.setUnitName(dinerBillDishesSet.getUnitName());
       dinerBillDishe.setDinerBill(dinerBill);
       dinerBillDishe.setCancelTime(dinerBillDishesSet.getCancelTime());
       dinerBillDishe.setIsSet(isSet);
       List dishesDishesList = new ArrayList();
       ObjectMapper mapper = new ObjectMapper();
       try
       {
         List list = (List)mapper.readValue(dinerBillDishesSet.getDsDishesDesc(), List.class);
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
       } catch (Exception e1) {
         e1.printStackTrace();
       }
       dinerBillDishe.setDishesSetDishesList(dishesDishesList);
     } else {
       dinerBillDishe = (DinerBillDishe)this.dinerBillDisheService.getOne(id);
       dinerBill = dinerBillDishe.getDinerBill();
       table = dinerBill.getTable();
       if ((DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dinerBillDishe.getDishesStatus())) || 
         (DishesStatusEnum.SERVED_CANCEL.getCode().equals(dinerBillDishe.getDishesStatus())))
       {
         ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
         ajax.setMessage("此菜肴已经退菜!");
         ajax.setRel(dinerBillDishe.getDishesStatus());
         return ajax;
       }
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.dishTuicai(getCurrentUserRestId(), dinerBillDishe, cancelReasonId, Float.valueOf(num), newAddReason, materialHand, map);
 
       doSynchMultiTable(map);
     }
     try
     {
       this.tuicaiPrinterService.printTuicai(getCurrentUserRestId(), dinerBillDishe, table, String.valueOf(num));
     } catch (Exception e) {
       e.printStackTrace();
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       ajax.setMessage("打印失败");
       ajax.setRel(dinerBillDishe.getDishesStatus());
       return ajax;
     }
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage(dinerBillDishe.getDishesName() + " 退菜成功");
     ajax.setRel(dinerBillDishe.getDishesStatus());
     return ajax;
   }
 /**
  * 时价跳转页面
  * @param billId
  * @param billType
  * @param dishesId
  * @param dishNum
  * @param model
  * @param request
  * @return
  */
   @RequestMapping(value={"pop/rulingPrice/{dishesId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String rulingPrice(@RequestParam("billId") String billId, @RequestParam(value="billType", defaultValue="1") String billType, @PathVariable("dishesId") String dishesId, @RequestParam(value="dishNum", defaultValue="1") String dishNum, Model model, HttpServletRequest request)
   {
     Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
     model.addAttribute("dishe", dishe);
     model.addAttribute("popBillId", billId);
     model.addAttribute("popBillType", billType);
     model.addAttribute("dishNum", dishNum);
     return "bill/rulingPriceForm";
   }
 /**
  * 添加时价
  * @param billId
  * @param billType
  * @param dishesId
  * @param dishNum
  * @param price
  * @param tId
  * @param model
  * @return
  * @throws JsonProcessingException
  */
   @RequestMapping(value={"addRulingPrice/{dishesId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone addRulingPrice(@RequestParam("billId") String billId, @RequestParam(value="billType", defaultValue="1") String billType, @PathVariable("dishesId") String dishesId, @RequestParam(value="dishNum", defaultValue="1") String dishNum, @RequestParam(value="price", defaultValue="0") String price, @RequestParam(value="tId", required=false) String tId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     if ((billType == null) || (billType.isEmpty()))
     {
       billType = BillTypeEnum.NORMAL_BILL.getCode();
     }
     if ((billId == null) || (billId.isEmpty()))
     {
       DinerBill d = new DinerBill();
       d.setBillTime(new Date());
       d.setBillType(billType);
       d.setTable(null);
 
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveCreateTableDinerBill(getCurrentUserRestId(), d, null, tId, map);
 
       doSynchMultiTable(map);
 
       billId = d.getBillId();
       ajax.setType(billType);
     }
 
     Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
     dishe.setPrice(new BigDecimal(price));
     this.disheService.save(dishe);
     float dishesNum = Float.parseFloat(dishNum);
 
     LinkedHashMap map = new LinkedHashMap();
     this.dinerBillService.saveJiacai(getCurrentUserRestId(), billId, dishe.getDishesId(), dishesNum, null, null, null, null, getCurrentUser(), null, true, map);
 
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("加菜成功");
     ajax.setRel(billId);
     return ajax;
   }
 
   /**
    * 添加时价修改
    * @param billId
    * @param billType
    * @param dishesId
    * @param dishNum
    * @param price
    * @param tId
    * @param model
    * @return
    * @throws JsonProcessingException
    */
   @RequestMapping(value={"addRulingPrices/{dishesId}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone addRulingPrices( @RequestParam(value="billType", defaultValue="1") String billType, @PathVariable("dishesId") String dishesId, @RequestParam(value="dishNum", defaultValue="1") String dishNum, @RequestParam(value="price", defaultValue="0") String price, @RequestParam(value="tId", required=false) String tId, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
     
     dishe.setPrice(new BigDecimal(price));
     this.disheService.save(dishe);
     float dishesNum = Float.parseFloat(dishNum);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("设置时价菜成功");
     return ajax;
   }
   
   
   
   
   @RequestMapping(value={"ajax/bankCardPay"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone getBankCardNo(@RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="money", defaultValue="") String money, @RequestParam(value="cptId", required=true) String cptId, @RequestParam(value="dbpId", required=false) String dbpId, @RequestParam(value="mcId", required=false) String mcId, @RequestParam(value="mbId", required=false) String mbId, @RequestParam(value="paymentType", required=true) String paymentType, @RequestParam(value="membercardPayType", required=false) String membercardPayType, RedirectAttributes redirectAttributes)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     ajax.setValue(billId);
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     PaymentType paymentType2 = this.paymentTypeService.findeByRestIdAndPaymentType(dinerBill.getRestaurant().getRestId(), PaymentTypeEnum.CARD.getCode());
     DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, dinerBill.getRestaurant().getRestId(), paymentType2);
     if (dinerBillPayment != null)
     {
       money = dinerBillPayment.getMoney().toString();
     }
 
     String resultCode = ZiZhuUtil.getBankCardNo(dinerBill.getBillId(), money);
 
     if (TrueFalseEnum.TRUE.getCode().equals(resultCode))
     {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setType(dinerBill.getRealCost().toString());
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
     }
 
     return ajax;
   }
 
   @RequestMapping(value={"ajax/checkBillStatus"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone checkBillStatus(@RequestParam(value="billId", defaultValue="") String billId, RedirectAttributes redirectAttributes) {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     ajax.setValue(billId);
     if (dinerBill.getBillStatus().equals(BillStatusEnum.SETTLE.getCode()))
     {
       ajax.setType(dinerBill.getRealCost().toString());
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     }
     else
     {
       String resultCode = ZiZhuUtil.checkBillPayStatus(dinerBill.getBillId(), dinerBill.getPayableCost().toString());
       if (TrueFalseEnum.TRUE.getCode().equals(resultCode))
       {
         try {
           Thread.sleep(3000L);
         } catch (InterruptedException e) {
           e.printStackTrace();
         }
         ajax.setStatusCode(StatusCodeEnum.YANZHENGSUCCESS.getCode());
         ajax.setType(dinerBill.getRealCost().toString());
       }
       else
       {
         ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       }
     }
     return ajax;
   }
 
   @RequestMapping(value={"ajax/checkBillIsSettled"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public DwzAjaxDone checkBillIsSettled(@RequestParam(value="billId", defaultValue="") String billId, RedirectAttributes redirectAttributes)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     ajax.setValue(billId);
     ajax.setRel(dinerBill.getBillNo());
     if (dinerBill.getBillStatus().equals(BillStatusEnum.SETTLE.getCode()))
     {
       ajax.setType(dinerBill.getRealCost().toString());
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     }
     else
     {
       String cardNotes = dinerBill.getCardNotes();
       if (!StringUtils.isEmpty(cardNotes))
       {
         String[] result = cardNotes.split(",");
 
         if ("1s".equals(result[0]))
         {
           ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
           ajax.setType(dinerBill.getRealCost().toString());
         }
         if ("2s".equals(result[0]))
         {
           ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
           ajax.setType(dinerBill.getRealCost().toString());
         }
         if ("0s".equals(result[0]))
         {
           ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
           ajax.setType(dinerBill.getRealCost().toString());
           ajax.setMessage(cardNotes.split(",")[2]);
         }
       }
       else
       {
         ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
         ajax.setType(dinerBill.getRealCost().toString());
       }
     }
     return ajax;
   }
 
   @RequestMapping(value={"ajax/isSettled"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String isSettled(@RequestParam(value="billId", required=true) String billId, RedirectAttributes redirectAttributes)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     if (dinerBill.getBillStatus().equals(BillStatusEnum.SETTLE.getCode())) {
       return TrueFalseEnum.TRUE.getCode();
     }
 
     return TrueFalseEnum.FALSE.getCode();
   }
 
   @RequestMapping(value={"ajax/isSamePayment"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String isSamePayment(@RequestParam(value="billId", required=true) String billId, @RequestParam(value="cptId", required=true) String cptId, RedirectAttributes redirectAttributes)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     PaymentType paymentType = (PaymentType)this.paymentTypeService.loadOne(cptId);
     DinerBillPayment dinerBillPayment = this.dinerBillPaymentService.findByDinerBillAndRestIdAndPaymentType(dinerBill, getCurrentUserRestId(), paymentType);
     if (dinerBillPayment != null) {
       return TrueFalseEnum.TRUE.getCode();
     }
 
     return TrueFalseEnum.FALSE.getCode();
   }
 
   public static void main(String[] args)
   {
     String money = "0.01";
     Double myMoney = Double.valueOf(money);
     BigDecimal needMoneyDec = new BigDecimal(Double.toString(myMoney.doubleValue()));
     System.out.println(needMoneyDec.setScale(2, 7));
   }
   
  
   
   @RequestMapping(value={"saveCoupons"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone saveCoupons( HttpServletRequest request, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Coupons cou = new Coupons();
     cou.setCouType(request.getParameter("couType"));
     cou.setCouNo(request.getParameter("couNo"));
     String tempAmount = request.getParameter("couAmount");
      if(StringUtils.isNotEmpty(tempAmount)){
    	 try{
    		 cou.setCouAmount (Double.parseDouble(tempAmount));
    	 }catch(NumberFormatException e){
    		 e.printStackTrace();
    	 }
    }
     cou.setCouCompany(request.getParameter("couCompany"));
     cou.setCouRangeType(request.getParameter("couRangeType"));
     Coupons coupons =  this.couponsService.saveCoupons(cou);
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("保存成功");
     return ajax;
   }
   
   @RequestMapping(value={"updateCoupons"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone updateCoupons( HttpServletRequest request, Model model)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Coupons cou = new Coupons();
     String couId = request.getParameter("couId");
     String couType = request.getParameter("couType");
     String couNo = request.getParameter("couNo");
     String tempAmount = request.getParameter("couAmount");
     Double couAmount = null;
     if(StringUtils.isNotEmpty(tempAmount)){
    	 try{
    	 couAmount = Double.parseDouble(tempAmount);
    	 }catch(NumberFormatException e){
    		 e.printStackTrace();
    	 }
    }
     String couCompany = request.getParameter("couCompany");
     String couRangeType = request.getParameter("couRangeType");
     Coupons coupons =  this.couponsService.updateCoupons(couId,couType,couNo,couAmount,couCompany,couRangeType);
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("修改成功");
     return ajax;
   }
 }

