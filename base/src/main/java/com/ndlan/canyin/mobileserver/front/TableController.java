 package com.ndlan.canyin.mobileserver.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.MessageVO;
import com.ndlan.canyin.frontdesk.dto3c.MobileRspResult;
import com.ndlan.canyin.frontdesk.message.ware.MessageCenterNativeServer;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantNewsService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.ctzh.RoleService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableAreaService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfMessageService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfOrderService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableBillRelationService;
import com.ndlan.canyin.frontdesk.util.MediaUtil;
import com.ndlan.canyin.frontdesk.util.MessageCarrierUtil;
import com.ndlan.canyin.frontdesk.util.SelfServiceCollective;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.RestNews;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.RestaurantPic;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.SelfMessage;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
import com.ndlan.canyin.core.common.DiningEnvEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.RoleTypeEnum;
import com.ndlan.canyin.core.common.SelfServiceMarkEnum;
import com.ndlan.canyin.core.common.SpecialEnvEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TabBillTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.security.utils.Digests;
import com.ndlan.canyin.core.utils.DateUtil;
import com.ndlan.canyin.core.utils.Encodes;
import com.ndlan.canyin.core.web.Servlets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 /**
  * 餐台管理
  * @author zhengjiansong
  *
  */
 @Controller
 @RequestMapping({"/mobile/table"})
 @Lazy
 public class TableController extends BaseManageController
 {
 
   @Autowired
   private TableService tableService;
 
   @Autowired
   private EmployeeService employeeService;
 
   @Autowired
   private DinerBillService dinerBillService;
 
   @Autowired
   RoleService roleService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   TableBillRelationService tableBillRelationService;
 
   @Autowired
   SelfServiceCollective selfServiceCollective;
 
   @Autowired
   MessageCenterNativeServer messageCenterNativeServer;
 
   @Autowired
   SelfMessageService selfMessageService;
 
   @Autowired
   TableAreaService tableAreaService;
 
   @Autowired
   RestaurantNewsService restaurantNewsService;
 
   @Autowired
   SelfOrderService selfOrderService;
/**
 * 获取所有餐台
 * @param sortType
 * @param pageNumber
 * @param model
 * @param request
 * @return
 */
   @RequestMapping(value={"list", ""}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult list(@RequestParam(value="sortType", defaultValue="auto") String sortType, @RequestParam(value="page", defaultValue="1") int pageNumber, Model model, ServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     searchParams.put("EQ_isEnable", "1");
     searchParams.put("EQ_restId", getCurrentUserRestId());
     Page tables = this.tableService.moblieSearch(searchParams, pageNumber, 1000, sortType);
     List tableList = tables.getContent();
     calTableOrderStatus(tableList);
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获取餐台成功", tableList);
   }
 /**
  * 获取所有餐区
  * @return
  */
   @RequestMapping(value={"getAllTableAreas"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult getAllTableAreas()
   {
     List tableAreas = this.tableAreaService.getTableAreaByRestID(getCurrentUserRestId());
     if ((tableAreas != null) && (tableAreas.size() > 0)) {
       return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获取餐台区域成功", tableAreas);
     }
     return new MobileRspResult(StatusCodeEnum.LOGIC_ERROR.getCode(), "没有餐台区域", null);
   }
 /**
  * 呼叫服务员
  * @param tabNO
  * @param type
  * @param contentFromPost
  * @param model
  * @param request
  * @param response
  * @return
  */
   @RequestMapping({"callwaiter"})
   @ResponseBody
   public MobileRspResult callwaiter(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="content", defaultValue="") String contentFromPost, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     SelfMessage selfMessage = null;
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     String content = "";
     if (selfOrder != null) {
       if (!"".equals(contentFromPost))
         content = tabNO + " 号餐台:" + contentFromPost;
       else {
         content = tabNO + " 号餐台客人 呼叫 " + SelfServiceMarkEnum.getDesc(type);
       }
 
       List<SelfMessage> selfMessageList = this.selfMessageService.findBySelfOrderAndMesType(selfOrder, type);
       if ((selfMessageList != null) && (selfMessageList.size() > 0)) {
         for (SelfMessage selfMessageOld : selfMessageList) {
           if (type.equals(selfMessageOld.getMesType())) {
             selfMessage = selfMessageOld;
           }
 
           if (type.equals(selfMessageOld.getMesType())) {
             if (isInTime(selfMessageOld.getUpdateTime(), 1)) {
               return new MobileRspResult(StatusCodeEnum.LOGIC_ERROR.getCode(), "已呼叫 " + SelfServiceMarkEnum.getDesc(type) + " 服务,一分钟内请勿重复呼叫!", null);
             }
 
             if (type.equals(SelfServiceMarkEnum.CUSTOMER.getCode())) {
               selfMessage = new SelfMessage(selfOrder, getCurrentUserRestId(), content, tabNO, TrueFalseEnum.FALSE.getCode(), type);
             }
           }
         }
       }
       String op = OperationTypeEnum.UPDATE.getCode();
       MediaUtil.playSound();
 
       if (selfMessage == null) {
         selfMessage = new SelfMessage(selfOrder, getCurrentUserRestId(), content, tabNO, TrueFalseEnum.FALSE.getCode(), type);
         op = OperationTypeEnum.CREATE.getCode();
       }
       selfMessage.setStatus(TrueFalseEnum.FALSE.getCode());
       selfMessage.setUseredHanleStatus(TrueFalseEnum.FALSE.getCode());
       this.selfMessageService.save(selfMessage);
 
       doSynchSingleTable(op, selfMessage);
 
       this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(tabNO, 2, SelfServiceMarkEnum.getEnumByCode(type), content, selfMessage.getId(), request));
       return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "呼叫 " + SelfServiceMarkEnum.getDesc(type) + " 成功", null);
     }
 
     return new MobileRspResult(StatusCodeEnum.LOGIC_ERROR.getCode(), "请先开台", null);
   }
 
   @RequestMapping({"open/callwaiterByMenu"})
   @ResponseBody
   public String callwaiterByMenu(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="content", defaultValue="") String contentFromPost, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     SelfMessage selfMessage = null;
     String content = "";
 
     if (StringUtils.isEmpty(type))
     {
       type = SelfServiceMarkEnum.OTHER.getCode();
     }
     if (!"".equals(contentFromPost))
       content = tabNO + " 号餐台:" + contentFromPost;
     else {
       content = tabNO + " 号餐台客人 呼叫 " + SelfServiceMarkEnum.getDesc(type);
     }
     silenceLogin(request);
 
     selfMessage = new SelfMessage(null, null, content, tabNO, TrueFalseEnum.FALSE.getCode(), type);
     selfMessage.setStatus(TrueFalseEnum.FALSE.getCode());
     selfMessage.setUseredHanleStatus(TrueFalseEnum.FALSE.getCode());
     this.selfMessageService.save(selfMessage);
 
     doSynchSingleTable(OperationTypeEnum.CREATE.getCode(), selfMessage);
 
     this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(tabNO, 2, SelfServiceMarkEnum.getEnumByCode(type), content, selfMessage.getId(), request));
     return StatusCodeEnum.SUCCESS.getCode();
   }
 
   private void silenceLogin(HttpServletRequest request) {
     Subject subject = SecurityUtils.getSubject();
     UsernamePasswordToken token = new UsernamePasswordToken("selforder", "G2", true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
 
   private boolean isInTime(Date updateTime, int minutes) {
     Date now = new Date();
     Date newUpdateTime = DateUtils.addMinutes(updateTime, minutes);
     return newUpdateTime.after(now);
   }
 
   private SelfOrder getValidateTableOrder(String tabNo)
   {
     return this.selfOrderService.getSelfOrderByTabNo(getCurrentUserRestId(), tabNo);
   }
 
   private void calTableOrderStatus(List<Table> tables)
   {
     this.tableService.flushCurrentTable(tables);
   }
 /**
  * 开台
  * @param dinerBill
  * @param dinerBill_
  * @param model
  * @param request
  * @return
  */
   @RequestMapping(value={"openTable"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public MobileRspResult openTable(@ModelAttribute("dinerBill") DinerBill dinerBill, @RequestBody(required=true) DinerBill dinerBill_, Model model, ServletRequest request)
   {
     if (dinerBill.getTableBillRelation() != null)
     {
       return new MobileRspResult(StatusCodeEnum.CHECK_ERROR.getCode(), "已经被使用了。不能开台", dinerBill_.getBillId());
     }
     if ((dinerBill.getTable() != null) && 
       (dinerBill.getTable().getTabId() != null) && (!dinerBill.getTable().getTabId().isEmpty()))
     {
       Table table = (Table)this.tableService.getOne(dinerBill.getTable().getTabId());
 
       List rs = this.tableBillRelationService.findByTableAndTabBillType(table, TabBillTypeEnum.ORDER.getCode());
       if ((rs != null) && (rs.size() >= 1))
       {
         return new MobileRspResult(StatusCodeEnum.CHECK_ERROR.getCode(), "已经有账单信息了，不能再开台了", dinerBill_.getBillId());
       }
     }
     LinkedHashMap map = new LinkedHashMap();
     boolean result = this.dinerBillService.saveCreateTableDinerBill(getCurrentUserRestId(), dinerBill_, dinerBill_.getOrderId(), null, map);
 
     doSynchMultiTable(map);
 
     if (!result)
     {
       return new MobileRspResult(StatusCodeEnum.CHECK_ERROR.getCode(), "已经有账单信息了，不能再开台了", dinerBill_.getBillId());
     }
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "成功", dinerBill_.getBillId());
   }
/**
 * 
 * @param dinerBill
 * @param dinerBill_
 * @param model
 * @param request
 * @return
 */
   @RequestMapping(value={"openTableUpdate"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult openTableUpdate(@ModelAttribute("dinerBill") @RequestBody(required=true) DinerBill dinerBill, @RequestBody(required=true) DinerBill dinerBill_, Model model, ServletRequest request)
   {
     try
     {
       if (dinerBill_ != null) {
         dinerBill = (DinerBill)this.dinerBillService.getOne(dinerBill_.getBillId());
       }
       BeanUtils.copyProperties(dinerBill, dinerBill_, new String[] { "table", "peopleNum", "salesmanName", "salesmanId", "oldTabId", "orderId" });
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveTableDinerBill(dinerBill_, getCurrentUserRestId(), map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e) {
       e.printStackTrace();
       return new MobileRspResult(StatusCodeEnum.ERROR.getCode(), "开台修改异常", dinerBill_.getBillId());
     }
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "开台修改成功", dinerBill_.getBillId());
   }
 /**
  * 转台
  * @param dinerBill
  * @param dinerBill_
  * @param redirectAttributes
  * @return
  */
   @RequestMapping(value={"transmitTable"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult transmitTable(@Valid @ModelAttribute("dinerBill") DinerBill dinerBill, @RequestBody(required=true) DinerBill dinerBill_, RedirectAttributes redirectAttributes)
   {
     try
     {
       if (dinerBill_ != null) {
         dinerBill = (DinerBill)this.dinerBillService.getOne(dinerBill_.getBillId());
       }
       BeanUtils.copyProperties(dinerBill, dinerBill_, new String[] { "table", "oldTabId" });
       LinkedHashMap map = new LinkedHashMap();
       dinerBill.setWaiterId(getCurrentUserId());
       dinerBill.setWaiterName(getCurrentUser().getName());
       this.dinerBillService.saveZhuantai(getCurrentUserRestId(), dinerBill_, map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e) {
       e.printStackTrace();
       return new MobileRspResult(StatusCodeEnum.ERROR.getCode(), "转台异常", dinerBill_.getBillId());
     }
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "转台成功", dinerBill_.getBillId());
   }
 /**
  * 并台
  * @param dinerBill
  * @param dinerBill_
  * @param redirectAttributes
  * @return
  */
   @RequestMapping(value={"combineTable"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult combineTable(@Valid @ModelAttribute("dinerBill") DinerBill dinerBill, @RequestBody(required=true) DinerBill dinerBill_, RedirectAttributes redirectAttributes)
   {
     try
     {
       if (dinerBill_ != null) {
         dinerBill = (DinerBill)this.dinerBillService.getOne(dinerBill_.getBillId());
       }
       BeanUtils.copyProperties(dinerBill, dinerBill_, new String[] { "table", "oldTabId" });
       LinkedHashMap map = new LinkedHashMap();
       dinerBill.setWaiterId(getCurrentUserId());
       dinerBill.setWaiterName(getCurrentUser().getName());
       this.dinerBillService.saveBingtai(getCurrentUserRestId(), dinerBill_, map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e) {
       e.printStackTrace();
       return new MobileRspResult(StatusCodeEnum.ERROR.getCode(), "并台异常", dinerBill_.getBillId());
     }
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "并台成功", dinerBill_.getBillId());
   }
 /**
  * 清台
  * @param id
  * @param redirectAttributes
  * @return
  */
   @RequestMapping(value={"cleanTable"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult cleanTable(String id, RedirectAttributes redirectAttributes)
   {
     try
     {
       LinkedHashMap map = new LinkedHashMap();
       this.dinerBillService.saveQingtaiDinerBill(id, map);
 
       doSynchMultiTable(map);
     }
     catch (Exception e) {
       e.printStackTrace();
       return new MobileRspResult(StatusCodeEnum.ERROR.getCode(), "清台异常", "");
     }
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "清台成功", "");
   }
   /**
    * 获取营销员
    * @return
    */
   @RequestMapping(value={"getSalesMan"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult getSalesMan() {
     Page page = this.employeeService.searchEmployee(getCurrentUserRestId(), new HashMap(), RoleTypeEnum.YINGXIAO.getCode(), 1, 1000, "empNum");
     List employees = page.getContent();
     if ((employees != null) && (employees.size() > 0)) {
       return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获取营销员成功", employees);
     }
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获取营销员成功", null);
   }
   /**
    * 更改密码
    * @param id
    * @param loginPassword
    * @param oldLoginPassword
    * @param model
    * @param request
    * @return
    */
   @RequestMapping(value={"changePassword"}, produces={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public String changePassword(@RequestParam(value="id", required=false) String id, @RequestParam(value="loginPassword", required=true) String loginPassword, @RequestParam(value="oldLoginPassword", required=true) String oldLoginPassword, Model model, ServletRequest request) { Employee employee = (Employee)this.employeeService.getOne(id);
     byte[] salt = Encodes.decodeHex(employee.getSalt());
     byte[] hashPassword = Digests.sha1(oldLoginPassword.getBytes(), salt, 1024);
     String encodePassword = Encodes.encodeHex(hashPassword);
     if (!encodePassword.equals(employee.getLoginPassword())) {
       return "0";
     }
     employee.setLoginPassword(loginPassword);
     this.employeeService.save(employee);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), employee);
 
     return "1"; } 
   /**
    * 获取当前用户
    * @param model
    * @param request
    * @return
    */
   @RequestMapping(value={"getCurrentUser"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult getCurrentUser(Model model, ServletRequest request) {
     Employee page = this.employeeService.findByLoginNameAndRest(ServletRequestUtils.getStringParameter(request, "loginUsername", ""), "");
     Employee employee = new Employee();
     BeanUtils.copyProperties(page, employee, new String[0]);
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获取当前用户成功", employee);
   }
 /**
  * 获取当前用户权限
  * @param model
  * @param request
  * @param expression
  * @return
  */
   @RequestMapping(value={"hasPermission"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult hasPermission(Model model, ServletRequest request, @RequestParam(value="expression", required=false) String expression)
   {
     Subject currentUser = SecurityUtils.getSubject();
     boolean isPermitted = currentUser.isPermitted(expression);
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获取当前用户权限成功", Boolean.valueOf(isPermitted));
   }
 /**
  * 占用餐台
  * @param tabNo
  * @param id
  * @param redirectAttributes
  * @param request
  * @return
  */
   @RequestMapping(value={"handleSelfTable"}, produces={"application/json"})
   @ResponseBody
   public String[] handleSelfTable(@RequestParam(value="tabNo", required=false) String tabNo, @RequestParam(value="id", required=false) String id, RedirectAttributes redirectAttributes, HttpServletRequest request)
   {
     String[] statusCode = { StatusCodeEnum.LOGIC_ERROR.getCode() };
     try
     {
       SelfMessage selfMessage = (SelfMessage)this.selfMessageService.getOne(id);
       SelfOrder selfOrder = selfMessage.getSelfOrder();
       if ((selfOrder != null) && (selfMessage != null))
       {
         LinkedHashMap map = new LinkedHashMap();
         LinkedHashMap mapBill = new LinkedHashMap();
         statusCode = this.dinerBillService.handleSelfOrder(getCurrentUserRestId(), selfOrder, selfMessage, selfMessage.getTabNo(), getCurrentUser(), map, mapBill);
 
         doSynchMultiTable(mapBill);
         doSynchMultiTable(map);
 
         if (statusCode[0].equals(StatusCodeEnum.SUCCESS.getCode()))
         {
           if (selfMessage != null) {
             selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
             this.selfMessageService.save(selfMessage);
 
             doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
             this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(selfMessage.getTabNo(), 3, SelfServiceMarkEnum.CALLORDER, getCurrentUser().getName() + " 已处理" + selfMessage.getTabNo() + " 号桌客人 的服务请求", id, request));
           }
         }
         else
           statusCode[1] = "处理失败，餐台被占用";
       }
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return statusCode;
   }
 
   private String buildCallMessage(String tabNo, int type, SelfServiceMarkEnum callorder, String content, String serialNo, HttpServletRequest request)
   {
     MessageVO messageVO = new MessageVO();
     messageVO.setType(type);
     messageVO.setTime(DateUtil.toStringHH(new Date()));
     messageVO.setCallType(callorder.getCode());
     messageVO.setCode(1);
     messageVO.setContent(content);
     messageVO.setName("name");
     messageVO.setFrom(tabNo);
     messageVO.setSerialNo(serialNo);
     return MessageCarrierUtil.beanToJson(messageVO);
   }
 /**
  * 请求服务
  * @param tabNo
  * @param id
  * @param msgType
  * @param callType
  * @param redirectAttributes
  * @param request
  * @return
  */
   @RequestMapping(value={"handleSelfCall"}, produces={"application/json"})
   @ResponseBody
   public boolean handleSelfCall(@RequestParam(value="tabNo", required=false) String tabNo, @RequestParam(value="id", required=false) String id, String msgType, @RequestParam(value="callType", required=false) String callType, RedirectAttributes redirectAttributes, HttpServletRequest request)
   {
     SelfMessage selfMessage = (SelfMessage)this.selfMessageService.getOne(id);
     SelfOrder selfOrder = selfMessage.getSelfOrder();
     if ((selfOrder != null) && (selfMessage != null)) {
       try {
         selfMessage = (SelfMessage)this.selfMessageService.getOne(id);
         if (selfMessage == null) return true; 
         selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
         selfMessage.setWaiterId(getCurrentUserId());
         selfMessage.setWaiterName(getCurrentUser().getName());
         this.selfMessageService.save(selfMessage);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
         this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(selfMessage.getTabNo(), 4, SelfServiceMarkEnum.getEnumByCode(callType), getCurrentUser().getName() + " 已处理" + selfMessage.getTabNo() + " 号桌客人 的服务请求", id, request));
       }
       catch (Exception e) {
         e.printStackTrace();
         return false;
       }
     }
     else {
       if (selfMessage != null)
       {
         selfMessage.setStatus(TrueFalseEnum.TRUE.getCode());
         selfMessage.setWaiterId(getCurrentUserId());
         selfMessage.setWaiterName(getCurrentUser().getName());
         this.selfMessageService.save(selfMessage);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
         this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(selfMessage.getTabNo(), 4, SelfServiceMarkEnum.getEnumByCode(callType), getCurrentUser().getName() + " 已处理" + selfMessage.getTabNo() + " 号桌客人 的服务请求", id, request));
         return true;
       }
 
       return false;
     }
      return true;
   }
 /**
  * 获取餐厅信息
  * @param restid
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"getRstInfo"}, produces={"text/html;charset=UTF-8"})
   public String getRstInfo(@RequestParam(value="restid", required=false) String restid)
   {
     JSONObject resultObj = new JSONObject();
     Restaurant rest = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
     try {
       resultObj.put("restName", rest.getRestName());
 
       List restPicUrls = new ArrayList();
       restPicUrls.add(rest.getRestLogo1024x1024());
       List<RestaurantPic> restPics = rest.getCommonRestaurantPics();
       for (RestaurantPic pic : restPics) {
         restPicUrls.add(pic.getPicUrl1200x800());
       }
       JSONArray restPicArray = new JSONArray(restPicUrls);
       resultObj.put("restPicUrls", restPicArray.toString());
 
       resultObj.put("restNotes", rest.getNotes());
 
       resultObj.put("consPerPerson", rest.getConsPerPerson());
 
       StringBuilder diningEnvSb = new StringBuilder("");
       if ((rest != null) && (rest.getDiningEnvIdList() != null)) {
         for (String diningEnv : rest.getDiningEnvIdList()) {
           diningEnvSb.append(DiningEnvEnum.getDesc(diningEnv)).append(",");
         }
       }
 
       resultObj.put("diningEnvStr", diningEnvSb.toString());
 
       StringBuilder specialEnvSb = new StringBuilder("");
       if ((rest != null) && (rest.getSpecialEnvIdList() != null)) {
         for (String specialenv : rest.getSpecialEnvIdList()) {
           specialEnvSb.append(SpecialEnvEnum.getDesc(specialenv)).append(",");
         }
       }
 
       resultObj.put("specialEnvSb", specialEnvSb.toString());
     } catch (JSONException e) {
       e.printStackTrace();
     }
     return resultObj.toString();
   }
 /**
  * 获取餐厅动态
  * @param pageNumber
  * @return
  */
   @ResponseBody
   @RequestMapping(value={"getRestNews"}, produces={"text/html;charset=UTF-8"})
   public String getRestNews(@RequestParam(value="page", defaultValue="1") int pageNumber)
   {
     Page restNewsPg = this.restaurantNewsService.searchRestNews(getCurrentUserRestId(), pageNumber, 15);
     JSONArray resultArray = new JSONArray();
     List<RestNews> restNews = restNewsPg.getContent();
     for (RestNews news : restNews) {
       JSONObject obj = new JSONObject();
       try {
         obj.put("title", news.getTitle());
         obj.put("content", news.getContent());
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
         String creatTimeStr = format.format(news.getCreateTime());
         obj.put("creatTimeStr", creatTimeStr);
         obj.put("creatMan", news.getCreateEmployee().getName());
         resultArray.put(obj);
       } catch (JSONException e) {
         e.printStackTrace();
       }
     }
 
     return resultArray.toString();
   }
 /**
  * 获取餐台状态
  * @param tableId
  * @return
  */
   @RequestMapping(value={"getDinnerStatus"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult getDinnerStatus(@RequestParam(value="tableId", required=true) String tableId)
   {
     Table table = (Table)this.tableService.getOne(tableId);
     String dinnerStatus = table.getDinnerStatus();
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获取餐台状态成功", dinnerStatus);
   }
 }

