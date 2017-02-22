 package com.ndlan.canyin.frontdesk.front.self;
 
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.dto3c.MessageVO;
import com.ndlan.canyin.frontdesk.message.ware.MessageCenterNativeServer;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesTasteService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesUnitService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfDishService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfMessageService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfOrderService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.MessageCarrierUtil;
import com.ndlan.canyin.frontdesk.util.SelfServiceCollective;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.qtsy.SelfMessage;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
import com.ndlan.canyin.core.common.Constants;
import com.ndlan.canyin.core.common.DisplayCallEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.SelfServiceMarkEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.web.Servlets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
 
 @Controller
 @RequestMapping({"/self/message"})
 @Lazy
 public class MessageController extends BaseManageController
 {
 
   @Autowired
   DisheService disheService;
 
   @Autowired
   DishesCategoryService dishesCategoryService;
 
   @Autowired
   DishesUnitService dishesUnitService;
 
   @Autowired
   DishesTasteService dishesTasteService;
 
   @Autowired
   SelfServiceCollective selfServiceCollective;
 
   @Autowired
   DinerBillService dinerBillService;
 
   @Autowired
   MessageCenterNativeServer messageCenterNativeServer;
 
   @Autowired
   SelfMessageService selfMessageService;
 
   @Autowired
   SelfOrderService selfOrderService;
 
   @Autowired
   SelfDishService selfdishService;
 
   @Autowired
   RestMemberInfoService restMemberInfoService;
 
   @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String list(@RequestParam(value="tn", defaultValue="") String tn, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
 
     Page dishes = this.disheService.searchDishe(getCurrentUserRestId(), searchParams, pageNumber, Constants.PAGE_SIZE, null, new String[] { "showSeq" });
     model.addAttribute("dishes", dishes);
     model.addAttribute("sortType", sortType);
     model.addAttribute("direction", direction);
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
 
     List dishesCategorys = this.dishesCategoryService.findAllDishesCategoryByRestId(getCurrentUserRestId());
     model.addAttribute("dishesCategorys", dishesCategorys);
 
     List dishesUnits = this.dishesUnitService.findAllDishesUnitByRestId(getCurrentUserRestId());
     model.addAttribute("dishesUnits", dishesUnits);
     model.addAttribute("tableNo", tn);
     return "self/message";
   }
   @RequestMapping({"im"})
   public String call(@RequestParam(value="tn", defaultValue="") String tn, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request) {
     return "self/im";
   }
 
   @RequestMapping(value={"getCustomerOrderInfo"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getCustomerOrderInfo(@RequestParam(value="sortType", defaultValue="updateTime_DESC") String sortType, @RequestParam(value="pagzSize", defaultValue="11") int pagzSize, @RequestParam(value="page", defaultValue="1") int pageNumber, Model model, HttpServletRequest request)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     if (!isLogin()) {
       ajax.setStatusCode(StatusCodeEnum.UNLOGIN.getCode());
       return ajax;
     }
 
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     searchParams.put("EQ_restId", getCurrentUserRestId());
     searchParams.put("EQ_status", TrueFalseEnum.FALSE.getCode());
     Page page = this.selfMessageService.searchPage(searchParams, pageNumber, pagzSize, sortType);
     List selfMessages = page.getContent();
 
     if ((selfMessages != null) && (selfMessages.size() > 0)) {
       SelfMessage last = (SelfMessage)selfMessages.get(selfMessages.size() - 1);
       if (last != null) {
         ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
         ajax.setMessage(last.getContent());
         ajax.setSign(String.valueOf(selfMessages.size()));
         ajax.setValue(DateUtil.toString(last.getUpdateTime()));
       }
       else {
         ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       }
     } else {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
     }
     return ajax;
   }
   @RequestMapping(value={"pop/msgHandle"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String orderDishCooking(Model model) {
     return "index/msgHandle";
   }
 
   @RequestMapping(value={"ajax/pop/msgHandleContent"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String msgHandleContent(@RequestParam(value="status", defaultValue="0") String status, @RequestParam("type") String type, @RequestParam(value="pagzSize", defaultValue="11") int pagzSize, @RequestParam(value="page", defaultValue="1") int pageNumber, Model model, HttpServletRequest request)
   {
     String restId = getCurrentUserRestId();
 
     String[] allArray = { 
       SelfServiceMarkEnum.CALLORDER.getCode(), 
       SelfServiceMarkEnum.ADDTABLEWARE.getCode(), 
       SelfServiceMarkEnum.CHECKOUT.getCode(), 
       SelfServiceMarkEnum.CUSTOMER.getCode(), 
       SelfServiceMarkEnum.URGEDISH.getCode(), 
       SelfServiceMarkEnum.CALLSERVCIE.getCode(), 
       SelfServiceMarkEnum.OTHER.getCode() };
 
     String[] orderArray = { 
       SelfServiceMarkEnum.CALLORDER.getCode() };
 
     String[] serviceArray = { 
       SelfServiceMarkEnum.ADDTABLEWARE.getCode(), 
       SelfServiceMarkEnum.CHECKOUT.getCode(), 
       SelfServiceMarkEnum.CUSTOMER.getCode(), 
       SelfServiceMarkEnum.URGEDISH.getCode(), 
       SelfServiceMarkEnum.CALLSERVCIE.getCode(), 
       SelfServiceMarkEnum.OTHER.getCode() };
 
     String[] displayArray = { 
       SelfServiceMarkEnum.CALLERIDDISPLAY.getCode() };
 
     Page page_all = this.selfMessageService.findByStatusAndMesTypeIn(restId, TrueFalseEnum.FALSE.getCode(), Arrays.asList(allArray), pageNumber, pagzSize);
     model.addAttribute("allCount", Long.valueOf(page_all.getTotalElements()));
 
     Page page_xiadan = this.selfMessageService.findByStatusAndMesTypeIn(restId, TrueFalseEnum.FALSE.getCode(), Arrays.asList(orderArray), pageNumber, pagzSize);
     model.addAttribute("xiadanCount", Long.valueOf(page_xiadan.getTotalElements()));
 
     Page page_fuwu = this.selfMessageService.findByStatusAndMesTypeIn(restId, TrueFalseEnum.FALSE.getCode(), Arrays.asList(serviceArray), pageNumber, pagzSize);
     model.addAttribute("fuwuCount", Long.valueOf(page_fuwu.getTotalElements()));
 
     Page page_xianshi = this.selfMessageService.findByStatusAndMesTypeIn(restId, TrueFalseEnum.FALSE.getCode(), Arrays.asList(displayArray), pageNumber, pagzSize);
     model.addAttribute("xianshiCount", Long.valueOf(page_xianshi.getTotalElements()));
 
     Collection list = new ArrayList();
     Page<SelfMessage> page;
     if (StringUtils.equals("displayCall", type)) {
        page = this.selfMessageService.findByMesTypeIsDisplay(restId, pageNumber, pagzSize);
       for (SelfMessage selfMessage : page) {
         String str = "^[0-9]{11}$";
         String telephoneNo = selfMessage.getContent();
         if (telephoneNo.matches(str)) {
           RestMemberInfo restMemberInfo = this.restMemberInfoService.findByMobile(telephoneNo, restId);
           if ((restMemberInfo == null) || 
             (restMemberInfo.getName() == null)) continue;
           selfMessage.setMemberName(restMemberInfo.getName());
         }
       }
     }
     else
     {
       if (StringUtils.equals("all", type))
         list = Arrays.asList(allArray);
       else if (StringUtils.equals("orderCall", type))
         list = Arrays.asList(orderArray);
       else if (StringUtils.equals("serviceCall", type)) {
         list = Arrays.asList(serviceArray);
       }
       page = this.selfMessageService.findByStatusAndMesTypeIn(restId, status, list, pageNumber, pagzSize);
     }
     model.addAttribute("type", type);
     model.addAttribute("status", status);
     model.addAttribute("selfMessages", page);
     return "index/msgHandleContent";
   }
 
   private SelfOrder getValidateTableOrder(String tabNo)
   {
     return this.selfOrderService.getSelfOrderByTabNo(getCurrentUserRestId(), tabNo);
   }
 
   private String buildCallMessage(String tabNo, int type, SelfServiceMarkEnum callorder, String content, String serialNo, HttpServletRequest request) {
     MessageVO messageVO = new MessageVO();
     messageVO.setType(type);
     messageVO.setTime(new Date().toLocaleString());
     messageVO.setCallType(callorder.getCode());
     messageVO.setCode(1);
     messageVO.setContent(content);
     messageVO.setName("name");
     messageVO.setFrom(tabNo);
     messageVO.setSerialNo(serialNo);
     return MessageCarrierUtil.beanToJson(messageVO);
   }
 
   @RequestMapping(value={"pop/msgHandle/update/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone handleSelfBill(@PathVariable("id") String id, Model model, HttpServletRequest request)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     SelfMessage selfMessage = (SelfMessage)this.selfMessageService.getOne(id);
     SelfOrder selfOrder = selfMessage.getSelfOrder();
     if ((selfOrder != null) && (selfMessage != null))
     {
       LinkedHashMap mapBill = new LinkedHashMap();
       LinkedHashMap map = new LinkedHashMap();
       String[] statusCode = this.dinerBillService.handleSelfOrder(getCurrentUserRestId(), selfOrder, selfMessage, selfMessage.getTabNo(), getCurrentUser(), map, mapBill);
       ajax.setStatusCode(statusCode[0]);
 
       doSynchMultiTable(mapBill);
       doSynchMultiTable(map);
 
       if (statusCode[0].equals(StatusCodeEnum.SUCCESS.getCode()))
       {
         ajax.setSign(statusCode[1]);
         ajax.setMessage("处理成功");
 
         if (selfMessage != null) {
           selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
           this.selfMessageService.save(selfMessage);
 
           doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
           this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(selfMessage.getTabNo(), 3, SelfServiceMarkEnum.CALLORDER, getCurrentUser().getName() + " 已处理" + selfMessage.getTabNo() + " 号桌客人 的服务请求", id, request));
         }
       }
       else if (StatusCodeEnum.CHECK_ERROR.getCode().equals(statusCode[0]))
       {
         ajax.setMessage("此餐桌未清台，请先清台!");
       }
       else
       {
         ajax.setMessage("处理失败，餐台被占用!");
       }
     }
     else if (selfMessage != null)
     {
       selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
       selfMessage.setWaiterId(getCurrentUserId());
       selfMessage.setWaiterName(getCurrentUser().getName());
       this.selfMessageService.save(selfMessage);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
       this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(selfMessage.getTabNo(), 4, SelfServiceMarkEnum.getEnumByCode(selfMessage.getMesType()), getCurrentUser().getName() + " 已处理" + selfMessage.getTabNo() + " 号桌客人 的服务请求", id, request));
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setMessage("处理成功！");
     }
     else
     {
       ajax.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       ajax.setMessage("请正确扫码开台后在进行此操作");
     }
 
     return ajax;
   }
 
   @RequestMapping(value={"pop/msgHandle/deleteMsg/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone handleSelfCall(@PathVariable("id") String id, String callType, Model model, HttpServletRequest request)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     SelfMessage selfMessage = (SelfMessage)this.selfMessageService.getOne(id);
     SelfOrder selfOrder = selfMessage.getSelfOrder();
     if ((selfOrder != null) && (selfMessage != null)) {
       try {
         selfMessage = (SelfMessage)this.selfMessageService.getOne(id);
         if (selfMessage == null) return ajax; 
         selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
         selfMessage.setWaiterId(getCurrentUserId());
         selfMessage.setWaiterName(getCurrentUser().getName());
         this.selfMessageService.save(selfMessage);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
         this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(selfMessage.getTabNo(), 4, SelfServiceMarkEnum.getEnumByCode(callType), getCurrentUser().getName() + " 已处理" + selfMessage.getTabNo() + " 号桌客人 的服务请求", id, request));
         ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
         ajax.setMessage("处理成功");
       }
       catch (Exception e) {
         ajax.setMessage("处理失败");
         e.printStackTrace();
       }
     }
     else if (selfMessage != null)
     {
       selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
       selfMessage.setWaiterId(getCurrentUserId());
       selfMessage.setWaiterName(getCurrentUser().getName());
       this.selfMessageService.save(selfMessage);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
       this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(selfMessage.getTabNo(), 4, SelfServiceMarkEnum.getEnumByCode(selfMessage.getMesType()), getCurrentUser().getName() + " 已处理" + selfMessage.getTabNo() + " 号桌客人 的服务请求", id, request));
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setMessage("处理成功！");
     }
 
      return ajax;
   }
 
   @RequestMapping(value={"pop/msgHandle/reStartService/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone reStartService(@PathVariable("id") String id, String callType, Model model, HttpServletRequest request)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     SelfMessage selfMessage = (SelfMessage)this.selfMessageService.getOne(id);
     if (selfMessage != null) {
       try {
         selfMessage = (SelfMessage)this.selfMessageService.getOne(id);
         if (selfMessage != null) {
           selfMessage.setStatus(TrueFalseEnum.FALSE.getCode());
           selfMessage.setWaiterId(getCurrentUserId());
           selfMessage.setWaiterName(getCurrentUser().getName());
           this.selfMessageService.save(selfMessage);
 
           doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
           int type = 0;
           if (selfMessage.getMesType() == SelfServiceMarkEnum.CALLORDER.getCode())
             type = 1;
           else {
             type = 2;
           }
           this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(selfMessage.getTabNo(), type, SelfServiceMarkEnum.getEnumByCode(selfMessage.getMesType()), "恢复提醒 >" + selfMessage.getContent(), selfMessage.getId(), request));
           ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
           ajax.setMessage("处理成功");
         }
       } catch (Exception e) {
         ajax.setMessage("处理失败");
         e.printStackTrace();
       }
     }
 
     return ajax;
   }
   @RequestMapping(value={"pop/msgHandle/deleteAllMsg"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone deleteAllMsg(Model model) throws JsonProcessingException { DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     List<SelfMessage> selfMessageList = this.selfMessageService.findByRestId(getCurrentUserRestId());
     for (SelfMessage selfMessage : selfMessageList) {
       if (SelfServiceMarkEnum.CALLORDER.getCode().equals(selfMessage.getMesType())) {
         selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
 
         map.put(selfMessage.getId() + "_" + OperationTypeEnum.UPDATE.getCode(), selfMessage);
       }
 
     }
 
     this.selfMessageService.batchUpdate(selfMessageList);
 
     doSynchMultiTable(map);
 
     ajax.setMessage("处理成功");
     return ajax; }
 
   @RequestMapping(value={"pop/msgHandle/displayCall"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone handleDisplayCall(@RequestParam(value="messageId", required=true) String messageId, @RequestParam(value="handleType", required=true) String handleType, Model model, HttpServletRequest request)
     throws JsonProcessingException
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     SelfMessage selfMessage = (SelfMessage)this.selfMessageService.getOne(messageId);
     try {
       if (selfMessage != null) {
         selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
         selfMessage.setUseredHanleStatus(handleType);
         selfMessage.setWaiterId(getCurrentUserId());
         selfMessage.setWaiterName(getCurrentUser().getName());
         this.selfMessageService.save(selfMessage);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
         ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
         ajax.setMessage("处理成功");
       }
     } catch (Exception e) {
       ajax.setMessage("处理失败");
       e.printStackTrace();
     }
 
     return ajax;
   }
 
   @RequestMapping(value={"callerIDDisplay"}, produces={"application/json"})
   @ResponseBody
   public Map<String, String> callerIDDisplay(@RequestParam(value="telephoneNo", required=true) String telephoneNo, Model model, HttpServletRequest request) {
     String restId = getCurrentUserRestId();
     SelfMessage selfMessage = new SelfMessage();
     selfMessage.setMesType(SelfServiceMarkEnum.CALLERIDDISPLAY.getCode());
     selfMessage.setRestId(restId);
     selfMessage.setContent(telephoneNo);
     selfMessage.setStatus(TrueFalseEnum.FALSE.getCode());
     selfMessage.setUseredHanleStatus(DisplayCallEnum.UNTREATED.getCode());
     selfMessage.setWaiterId(getCurrentUserId());
     selfMessage.setWaiterName(getCurrentUser().getName());
     this.selfMessageService.save(selfMessage);
 
     doSynchSingleTable(OperationTypeEnum.CREATE.getCode(), selfMessage);
 
     Map messageMap = new HashMap();
     String str = "^[0-9]{11}$";
     if (telephoneNo.matches(str)) {
       RestMemberInfo restMemberInfo = this.restMemberInfoService.findByMobile(telephoneNo, restId);
       if ((restMemberInfo != null) && 
         (restMemberInfo.getName() != null)) {
         messageMap.put("name", restMemberInfo.getName());
       }
     }
 
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Calendar cal = Calendar.getInstance();
 
     messageMap.put("messageId", selfMessage.getId());
     messageMap.put("time", df.format(cal.getTime()));
     messageMap.put("telephoneNo", telephoneNo);
     return messageMap;
   }
 }

