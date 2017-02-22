package com.ndlan.canyin.mobileserver.G2;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.core.common.BillOpTypeEnum;
import com.ndlan.canyin.core.common.BillStatusEnum;
import com.ndlan.canyin.core.common.BillTypeEnum;
import com.ndlan.canyin.core.common.BussLogTypeEnum;
import com.ndlan.canyin.core.common.CategoryLevelEnum;
import com.ndlan.canyin.core.common.DataLogActEnum;
import com.ndlan.canyin.core.common.DataLogTypeEnum;
import com.ndlan.canyin.core.common.DishesStatusEnum;
import com.ndlan.canyin.core.common.OrderStatusEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.vo.DishesSetDishesVo;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.front.bill.BillController;
import com.ndlan.canyin.frontdesk.service.bill.BillService;
import com.ndlan.canyin.frontdesk.service.cloud.CloudBillService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetDishesService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetService;
import com.ndlan.canyin.frontdesk.service.qtsy.CouponsService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillPaymentService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.DishesSetDishesReplaceService;
import com.ndlan.canyin.frontdesk.service.qtsy.OrderBillDishesSetService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.frontdesk.service.qtsy.TakeoutService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDisheService;
import com.ndlan.canyin.frontdesk.service.sygl.DinerBillDishesSetService;
import com.ndlan.canyin.frontdesk.service.xtgl.DataLogService;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.sharelogic.util.PriorityExecutor;

@Controller
@RequestMapping({"/ios/bill"})
@Lazy
public class IosBillController extends BaseManageController
{
  @Autowired
  private DishesCategoryService dishesCategoryService;

  @Autowired
  private DisheService disheService;
  
  @Autowired
  private DinerBillDishesSetService dinerBillDishesSetService;

  @Autowired
  private DishesSetService dishesSetService;

  @Autowired
  private DinerBillService dinerBillService;

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
  DinerBillPaymentService dinerBillPaymentService;
  @Autowired
  private BillService billService;
  
  @Autowired
  private TableOrderService tableOrderService;
  
  @Autowired
  private DinerBillDisheService dinerBillDisheService;
  @Autowired
  private DataLogService dataLogService;
  public static final String MONEY = "MONEY";
  public static final String CARD = "CARD";
  public static final String CREDIT = "CREDIT";
  public static final String WEBSITE = "WEBSITE";
  public static final String OTHER = "OTHER";


  @RequestMapping(value={"queryPaymement"}, produces={"application/json"})
  public void jiacai( String out_trade_no,String auth_code ,String total_amount,String subject, Model model,String paymentType)
    throws JsonProcessingException
  {
	  paymentType="402881fa4fca616c014fca87c0d40001";//支付宝类型id
	  ToAlipayBarTradePay toalipaybartradepay=new ToAlipayBarTradePay(); 
		AlipayTradePayResponse response = toalipaybartradepay.barPay(out_trade_no, auth_code, total_amount, subject,"");
		if (null != response && response.isSuccess()) {
			if (response.getCode().equals("10000")) {
				//yes
				DinerBillPayment d=new DinerBillPayment();
			}else if (response.getCode().equals(
			"10003")) {
				//提示输入密码等待操作中 
				
			}
			
			
		}
		
  }
  
  /**
   *    ios保存订单的时候调的 
   * @param billType 账单类型
   * @param billId 
   * @param dishNum 点菜数量
   * @param isJudgeRm
   * @param tId     //外卖单号
   * @param id      //菜肴id   
   * @param model
   * @return
   * @throws JsonProcessingException
   */
  @RequestMapping(value={"jiacai2/{billType}/{id}"}, produces={"application/json"})
  @ResponseBody
  public DwzAjaxDone jiacai2(@PathVariable("billType") String billType, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="unitNumStr", defaultValue="1") String unitNumStr, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="tId", required=false) String tId, @PathVariable("id") String id, Model model)
    throws JsonProcessingException
  {
	  if(billId.equals("(null)")){
		  billId="";
	  }
	  DwzAjaxDone ajax=new DwzAjaxDone();
      String[] ids = id.split(",");
      String[] unitNumStrs = unitNumStr.split(",");
      for (int i = 0; i < ids.length; i++) {
    	  if(ajax.getValue()!="" && ajax.getValue()!=null){
    		  billId=ajax.getValue();
    	  }
    	  ajax=jiacaiFormat(billType,   billId,   unitNumStrs[i],  isJudgeRm,   tId,   ids[i], model);
          
      }
      
	 return ajax;
  }
  

  /**
   *  外卖点餐
   * @param billType 账单类型
   * @param billId 
   * @param dishNum 点菜数量
   * @param isJudgeRm
   * @param tId     //外卖单号
   * @param id      //菜肴id   
   * @param model
   * @return
   * @throws JsonProcessingException
   */
  @RequestMapping(value={"jiacai/{billType}/{id}"}, produces={"application/json"})
  @ResponseBody
  public DwzAjaxDone jiacai(@PathVariable("billType") String billType, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="unitNumStr", defaultValue="1") String unitNumStr, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="tId", required=false) String tId, @PathVariable("id") String id, Model model)
    throws JsonProcessingException
  {
	  if(billId.equals("(null)")){
		  billId="";
	  }
	  DwzAjaxDone ajax=new DwzAjaxDone();
      String[] ids = id.split(",");
      String[] unitNumStrs = unitNumStr.split(",");
      for (int i = 0; i < ids.length; i++) {
    	  if(tId!="" && tId !=null && ajax.getValue()!="" && ajax.getValue()!=null){
    		  billId=ajax.getValue();
    	  }
    	  ajax=jiacaiFormat(billType,   billId,   unitNumStrs[i],  isJudgeRm,   tId,   ids[i], model);
          
      }
      
      if("2".equals(billType) && tId!="" && tId!=null){
    	  DinerBill dinerBilla = (DinerBill)IosBillController.this.dinerBillService.getOne(ajax.getValue());   
      	  dinerBilla.setBillStatus(BillStatusEnum.SENDING.getCode());
      	 this.dinerBillService.save(dinerBilla);
        }
      
	 return ajax;
  }
 


  /**
   *  快餐
   * @param billType 账单类型
   * @param billId 
   * @param dishNum 点菜数量
   * @param isJudgeRm
   * @param tId     //外卖单号
   * @param id      //菜肴id   
   * @param model
   * @return
   * @throws JsonProcessingException
   */
  @RequestMapping(value={"jiacaikuaican/{billType}/{id}"}, produces={"application/json"})
  @ResponseBody
  public DwzAjaxDone jiacai4(@PathVariable("billType") String billType, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="unitNumStr", defaultValue="1") String unitNumStr, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="tId", required=false) String tId, @PathVariable("id") String id, Model model)
    throws JsonProcessingException
  {
	  if(billId.equals("(null)")){
		  billId="";
	  }
	  DwzAjaxDone ajax=new DwzAjaxDone();
	  /*  String temp = id;
		  String tempArray [] = temp.split(",");
		 for(String bdId:tempArray){
    	  if(ajax.getValue()!="" && ajax.getValue()!=null){
    		  billId=ajax.getValue();
    	  }
    	  ajax=jiacaiFormat4(billType,   billId,   unitNumStr,  isJudgeRm,   tId,   bdId, model);
      }*/
		  String[] ids = id.split(",");
	      String[] unitNumStrs = unitNumStr.split(",");
		  for (int i = 0; i < ids.length; i++) {
	    	  if(ajax.getValue()!="" && ajax.getValue()!=null){
	    		  billId=ajax.getValue();
	    	  }
	    	  ajax=jiacaiFormat4(billType,   billId,   unitNumStrs[i],  isJudgeRm,   tId,   ids[i], model);
	          
	      }
		  
		  DinerBill dinerBilla = (DinerBill)IosBillController.this.dinerBillService.getOne(billId);
	      dinerBilla.setBillStatus(BillStatusEnum.PLACE_ORDER.getCode());
	      this.dinerBillService.save(dinerBilla);
	      ajax.setBillId(billId);
	      ajax.getBillId();
	      ajax.getValue();
		  ajax.getSign();
		  ajax.getStatusCode();
		  ajax.getMessageMap();
		  ajax.getRel();
	 return ajax;
  }

/**
   * 快餐
   * @param billType
   * @param billId
   * @param dishNum
   * @param isJudgeRm
   * @param tId
   * @param id
   * @param model
   * @return
   * @throws JsonProcessingException
   */
 
  public DwzAjaxDone jiacaiFormat4( String billType,  String billId,  String unitNumStr,  String isJudgeRm,  String tId,  String id, Model model)
    throws JsonProcessingException
  {
    DwzAjaxDone ajax = new DwzAjaxDone();
    float dishesNum = Float.parseFloat(unitNumStr);

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
      this.dinerBillService.saveCreateTableDinerBills(getCurrentUserRestId(), d, null, tId, map);

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
        DinerBill dinerBill = (DinerBill)IosBillController.this.dinerBillService.getOne(f_billId);
        IosBillController.this.dinerBillService.saveBusiLog(IosBillController.this.getCurrentUserRestId(), dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
        if (f_messageMap.containsKey("rmDesc"))
        {
          String rmNotes = BillOpTypeEnum.ORDER_DISH.getDesc() + ":" + (String)f_messageMap.get("rmDesc");
          IosBillController.this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
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
   
    
    ajax.setBillId(billId);
    ajax.setValue(billId);
    ajax.setSign(formatFloat((String)messageMap.get("estimate")));
    ajax.setStatusCode((String)messageMap.get("code"));
    ajax.setMessageMap(messageMap);
    ajax.setRel(id);

    ajax.setForwardUrl((String)messageMap.get("html"));
    return ajax;
  }
  
 
  /**
   * 点菜
   * @param billType 账单类型
   * @param billId 
   * @param dishNum 点菜数量
   * @param isJudgeRm 
   * @param tId 
   * @param id 菜品id
   * @param model
   * @return
   * @throws JsonProcessingException
   */

  public DwzAjaxDone jiacaiFormat( String billType,  String billId,  String dishNum,  String isJudgeRm,  String tId,  String id, Model model)
    throws JsonProcessingException
  {
    DwzAjaxDone ajax = new DwzAjaxDone();
    float dishesNum = Float.parseFloat(dishNum);

    Map messageMap = null;

    boolean isJudgeDishRaws = UserSettingCache.getInstance().isJudgeDishRaws;
    if (("0".equalsIgnoreCase(isJudgeRm)) && (isJudgeDishRaws))
    {
      Dishe dishe = (Dishe)this.disheService.getOne(id);
//      判断原料是否够用
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

//    如果为空则为普通账单 先开台后下单
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
        DinerBill dinerBill = (DinerBill)IosBillController.this.dinerBillService.getOne(f_billId);
        IosBillController.this.dinerBillService.saveBusiLog(IosBillController.this.getCurrentUserRestId(), dinerBill, BussLogTypeEnum.TABLE, BillOpTypeEnum.ORDER_DISH, logNotes);
        if (f_messageMap.containsKey("rmDesc"))
        {
          String rmNotes = BillOpTypeEnum.ORDER_DISH.getDesc() + ":" + (String)f_messageMap.get("rmDesc");
          IosBillController.this.dataLogService.saveLog(DataLogTypeEnum.RAWMATERIALINFO.getCode(), DataLogActEnum.UPDATE.getCode(), rmNotes);
        }
      }
    });
    DinerBill dinerBilla = (DinerBill)IosBillController.this.dinerBillService.getOne(billId);
    

    //编号
    Date ds=new Date();
    long str =ds.getTime();
    String bsss=Long.toString(str);
    dinerBilla.setBillNumber(bsss);
   // dinerBilla.setOnlineBillNo(bsss);
   DinerBill d= this.dinerBillService.save(dinerBilla);
    ajax.setBillId(billId);
    ajax.setValue(billId);
    messageMap.get("estimate");
    ajax.setSign(formatFloat((String)messageMap.get("estimate")));
    ajax.setStatusCode((String)messageMap.get("code"));
    ajax.setMessageMap(messageMap);
    ajax.setRel(id);

    ajax.setForwardUrl(d.getBillNo());
    return ajax;
  }

  @RequestMapping(value={"addDishesSet/{billType}/{dsId}"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public DwzAjaxDone addDishesSet(@PathVariable("billType") String billType,@PathVariable("unitNum") String unitNum, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="dsDishesDesc", defaultValue="") String dsDishesDesc, @PathVariable("dsId") String dsId, @RequestParam(value="isJudgeRm", defaultValue="0") String isJudgeRm, @RequestParam(value="tId", required=false) String tId, Model model)
    throws JsonProcessingException, UnsupportedEncodingException
  {
	  DwzAjaxDone ajax=new DwzAjaxDone();
      String[] ids = dsId.split("=");
      String[] dsDishesDescs = dsDishesDesc.split("=");
      String[] unitNums = unitNum.split("=");
      for (int i = 0; i < ids.length; i++) {
    	  float unitNumf=Float.parseFloat(unitNums[i]);
    	  ajax=addDishesSetFormat(billType,  billId,  dsDishesDescs[i],   ids[i],  isJudgeRm,   tId,  model,unitNumf);
    	  if(ajax.getMessageMap().get("result").equals("0") ||ajax.getMessageMap().get("result").equals("1")||ajax.getMessageMap().get("result").equals("2")){
    		  ajax=null; 
    		  ajax=addDishesSetFormat(billType,  billId,  dsDishesDescs[i],   ids[i],  "1",   tId,  model,unitNumf);
    		  if(ajax.getMessageMap().get("result").equals("1")){
    			  ajax.setMessage(ajax.getMessageMap().get("message"));
    		  }
    	  }
      }
	 return ajax;
	  
  }

  public DwzAjaxDone addDishesSetFormat( String billType, String billId,  String dsDishesDesc,  String dsId,  String isJudgeRm,  String tId, Model model,float unitNum)
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
      
      messageMap = this.dinerBillService.saveDishesSet(getCurrentUserRestId(), billId, dsId, billType, dsDishesDesc, null, null, null, null, getCurrentUser(), unitNum, tId, map, mapBill);

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

  public String formatFloat(String f) {
	     f = f.replaceAll("\\.00", "").replaceAll("\\.0", "");
	     return f;
	   }
  
  @RequestMapping(value={"pop/addDishesSet"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String addDishesSet(Model model, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="dsId", defaultValue="") String dsId, @RequestParam(value="bdId", defaultValue="") String bdsId, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="isOrder", defaultValue="") String isOrder)
  {
    DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
    DishesSet dishesSet = (DishesSet)this.dishesSetService.loadOne(dsId);
    
    
    if(dishesSet.getDishesSetDishes().size()>0){
    	for(int i =0 ;i<dishesSet.getDishesSetDishes().size(); i++){
    		dishesSet.getDishesSetDishes().get(i).setDishesId(dishesSet.getDishesSetDishes().get(i).getDishe().getDishesId());
    		dishesSet.getDishesSetDishes().get(i).setDishesCode(dishesSet.getDishesSetDishes().get(i).getDishe().getDishesCode());
    	}
    }

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
  
  
  @RequestMapping({"ajax/diancaiContent"})
  public String diancaiContent(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pagzSize", defaultValue="20") int pagzSize, @RequestParam(value="firstCategoryId", defaultValue="") String firstCategoryId, @RequestParam(value="categoryId", defaultValue="") String categoryId, @RequestParam(value="dsCategoryId", defaultValue="") String dsCategoryId, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="billType", defaultValue="1") String billType, Model model, HttpServletRequest request)
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

  
/**
 * 预定点菜
 * @param orderId 预定id
 * @param dishesId 菜id
 * @param model
 * @return
 * @throws JsonProcessingException
 */
  
  @RequestMapping(value={"orderJiacai/{orderId}/{id}"}, produces={"application/json"})
  @ResponseBody
  public DwzAjaxDone jiacaiyuding(@PathVariable("orderId") String orderId, @PathVariable("id") String dishesId, Model model)
    throws JsonProcessingException
  {
	  DwzAjaxDone ajax=new DwzAjaxDone();
		  String[] ids = dishesId.split(",");
	      for (int i = 0; i < ids.length; i++) {
	    	  ajax=orderJiacai(orderId,   ids[i], model);
	      }
	      //点完菜改变状态
	      TableOrder tableOrder = (TableOrder)this.tableOrderService.getOne(orderId);
	      tableOrder.setOrderStatus(OrderStatusEnum.OVER.getCode());
	      this.tableOrderService.save(tableOrder);
	 return ajax;
  }

  public DwzAjaxDone orderJiacai( String orderId,  String dishesId, Model model)
    throws JsonProcessingException
  {
	  //多个赠菜
 	 try {
			Thread.sleep(50);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    DwzAjaxDone ajax = new DwzAjaxDone();
    LinkedHashMap map = new LinkedHashMap();
    
    this.dinerBillService.saveOrderJiacai(orderId, dishesId, null, null, null, null, getCurrentUser(), getCurrentUserRestId(), 1.0F, map);

    doSynchMultiTable(map);

    ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
    ajax.setMessage("加菜成功");
    ajax.setRel(dishesId);
    return ajax;
  }
  
}


