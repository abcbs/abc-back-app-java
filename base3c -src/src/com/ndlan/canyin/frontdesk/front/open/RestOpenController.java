 package com.ndlan.canyin.frontdesk.front.open;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.cloud.CloudBillService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.PrinterService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.qtsy.CashierDeskSettingService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillLogService;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm.ShiroUser;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Printer;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.qtsy.CashierDeskSetting;
import com.ndlan.canyin.base.entity.qtsy.DinerBillLog;
import com.ndlan.canyin.core.common.BillOpTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.SynDatabaseStatusEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.utils.DateUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/open/rest"})
 public class RestOpenController extends BaseManageController
 {
 
   @Autowired
   CloudBillService cloudBillService;
 
   @Autowired
   DinerBillLogService dinerBillLogService;
 
   @Autowired
   EmployeeService employeeService;
 
   @Autowired
   PrinterService printerService;
 
   @Autowired
   protected CashierDeskSettingService cashierDeskSettingService;
 
   @RequestMapping(value={"notifyCloudStatus"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone notifyCloudStatus(Model model, HttpServletRequest request)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     UserSettingCache.getInstance().clearCloudSetting();
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     return ajax;
   }
 
   @RequestMapping({"ajax/getRestInfo"})
   @ResponseBody
   public DwzAjaxDone getRestInfo(RedirectAttributes redirectAttributes, HttpServletRequest request, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Restaurant restaurant = (Restaurant)this.restaurantService.findAll().get(0);
     String cloudUsername = restaurant.getCloudUsername();
     String isBandCloudAccount = restaurant.getIsBandCloudAccount();
     String synDatabaseStatus = restaurant.getSynDatabaseStatus();
     String onlineRestStatus = restaurant.getOnlineRestStatus();
     String onlineOrderStatus = restaurant.getOnlineOrderStatus();
     String onlineOrderDishStatus = restaurant.getOnlineOrderDishStatus();
     String takeOutStatus = restaurant.getTakeOutStatus();
     Map map = new HashMap();
     map.put("cloudUsername", cloudUsername);
     map.put("restName", restaurant.getRestName());
     map.put("restId", restaurant.getRestId());
     map.put("isBandCloudAccount", isBandCloudAccount);
     map.put("synDatabaseStatus", synDatabaseStatus);
     map.put("onlineRestStatus", onlineRestStatus);
     map.put("onlineOrderStatus", onlineOrderStatus);
     map.put("onlineOrderDishStatus", onlineOrderDishStatus);
     map.put("takeOutStatus", takeOutStatus);
     map.put("currenVersion", "2.3.0");
     map.put("openSynDays", DateUtil.getDays(restaurant.getSynDataCompleteTime(), DateProvider.DEFAULT.getDate()));
     ajax.setDataMap(map);
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     return ajax;
   }
 
   @RequestMapping({"ajax/getCloudRestInfo"})
   @ResponseBody
   public DwzAjaxDone getCloudRestInfo(@RequestParam(value="restId", required=true, defaultValue="") String restId, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
     String cloudUsername = restaurant.getCloudUsername();
     String cloudPassword = restaurant.getCloudPassword();
     String cloudUserValidate = StatusCodeEnum.ERROR.getCode();
     String cloudUserValidateMessage = "";
     String isOpenCloudRest = TrueFalseEnum.FALSE.getCode();
     String isOpenCloudWeixin = TrueFalseEnum.FALSE.getCode();
     String isHasMarketingActivities = TrueFalseEnum.FALSE.getCode();
     Map map = new HashMap();
 
     if (TrueFalseEnum.TRUE.getCode().equals(restaurant.getIsBandCloudAccount()))
     {
       ajax = this.cloudBillService.loginCloud(restaurant.getRestId(), cloudUsername, cloudPassword);
       cloudUserValidate = ajax.getStatusCode();
       cloudUserValidateMessage = ajax.getMessage();
       isOpenCloudRest = ajax.getValue();
       isOpenCloudWeixin = ajax.getSign();
       if (StringUtils.isEmpty(isOpenCloudRest))
       {
         isOpenCloudRest = TrueFalseEnum.FALSE.getCode();
         ajax.setValue(isOpenCloudRest);
       }
       if (StringUtils.isEmpty(isOpenCloudWeixin))
       {
         isOpenCloudWeixin = TrueFalseEnum.FALSE.getCode();
       }
       if (StatusCodeEnum.SUCCESS.getCode().equals(cloudUserValidate))
       {
         Map dataMap = ajax.getDataMap();
         map = dataMap;
       }
 
       isHasMarketingActivities = ajax.getForwardUrl();
 
       this.cloudBillService.notifyCloudStatus();
     }
     map.put("restName", restaurant.getRestName());
     map.put("isOpenCloudRest", isOpenCloudRest);
     map.put("isOpenCloudWeixin", isOpenCloudWeixin);
     map.put("weixinAccountName", ajax.getNavTabId());
     map.put("openSynDays", DateUtil.getDays(restaurant.getSynDataCompleteTime(), DateProvider.DEFAULT.getDate()));
     map.put("cloudUserValidate", cloudUserValidate);
     map.put("cloudUserValidateMessage", cloudUserValidateMessage);
     if (StringUtils.isEmpty(isHasMarketingActivities))
     {
       isHasMarketingActivities = TrueFalseEnum.FALSE.getCode();
     }
     map.put("isHasMarketingActivities", isHasMarketingActivities);
     ajax.setDataMap(map);
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     return ajax;
   }
 
   @RequestMapping({"ajax/getCloudRestSynStatus"})
   @ResponseBody
   public DwzAjaxDone getCloudRestSynStatus(@RequestParam(value="restId", required=true, defaultValue="") String restId, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
     if (SynDatabaseStatusEnum.ALL_SYNCOMPLETE.getCode().equals(restaurant.getSynDatabaseStatus()))
     {
       ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       ajax.setMessage("同步完成");
       return ajax;
     }
     ajax = this.cloudBillService.getCloudRestSynStatus(restId, restaurant.getSynVersionId());
     if (ajax != null)
     {
       if (StatusCodeEnum.SUCCESS.getCode().equals(ajax.getStatusCode()))
       {
         silenceLogin(request, "super", "G2");
         restaurant.setSynDatabaseStatus(SynDatabaseStatusEnum.ALL_SYNCOMPLETE.getCode());
         restaurant.setSynDataCompleteTime(DateProvider.DEFAULT.getDate());
         restaurant.setIsBandCloudAccount(TrueFalseEnum.TRUE.getCode());
         this.restaurantService.save(restaurant);
         this.cloudBillService.notifyCloudStatus();
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), restaurant);
       }
       else if (StatusCodeEnum.NODATA.getCode().equals(ajax.getStatusCode()))
       {
         silenceLogin(request, "super", "G2");
         restaurant.setSynDatabaseStatus(SynDatabaseStatusEnum.INIT.getCode());
         restaurant.setIsBandCloudAccount(TrueFalseEnum.FALSE.getCode());
         this.restaurantService.save(restaurant);
         this.cloudBillService.notifyCloudStatus();
       }
       else if (StatusCodeEnum.ERROR.getCode().equals(ajax.getStatusCode()))
       {
         silenceLogin(request, "super", "G2");
         restaurant.setSynDatabaseStatus(SynDatabaseStatusEnum.ERROR.getCode());
         restaurant.setSynDataCompleteTime(DateProvider.DEFAULT.getDate());
         restaurant.setIsBandCloudAccount(TrueFalseEnum.TRUE.getCode());
         this.restaurantService.save(restaurant);
         this.cloudBillService.notifyCloudStatus();
       }
     }
     else
     {
       ajax = new DwzAjaxDone();
       ajax.setStatusCode(StatusCodeEnum.ERROR.getCode());
       ajax.setMessage("网络故障！");
     }
     return ajax;
   }
 
   @RequestMapping({"ajax/openDrawer"})
   @ResponseBody
   public DwzAjaxDone openDrawer(@RequestParam(value="localUsername", defaultValue="") String localUsername, @RequestParam(value="restId", required=true, defaultValue="") String restId, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model)
     throws Exception
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     Employee employee = this.employeeService.findHideUserByLoginName(localUsername);
     if (employee == null)
     {
       ajax.setStatusCode(StatusCodeEnum.UNLOGIN.getCode());
       ajax.setMessage("没有此用户");
       return ajax;
     }
     silenceLogin(request, localUsername, employee.getPlainPassword());
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
 
     if (!SecurityUtils.getSubject().isPermitted("frontdesk_bill_openDrawer:create")) {
       ajax.setStatusCode(StatusCodeEnum.ROLE_ERROR.getCode());
       ajax.setMessage("没有此权限");
       return ajax;
     }
 
     List<Printer> prints = this.printerService.findByRestIdAndStatus(restId, TrueFalseEnum.TRUE.getCode());
 
     CashierDeskSetting cashierDeskSetting = this.cashierDeskSettingService.findByRestIdAndEmpId(restId, employee.getEmpId());
     String userPrinterId = null;
     if (cashierDeskSetting != null)
     {
       userPrinterId = cashierDeskSetting.getPrinterId();
     }
     Printer userPrinter = null;
     if ((prints != null) && (prints.size() > 0))
     {
       Iterator localIterator;
       if ((StringUtils.isEmpty(userPrinterId)) || (TrueFalseEnum.TRUE.getCode().equals(userPrinterId)) || ("null".equals(userPrinterId)))
       {
         localIterator = prints.iterator(); 
       
       while (true) { Printer printer = (Printer)localIterator.next();
 
         if (TrueFalseEnum.TRUE.getCode().equals(printer.getIsUseDrawer()))
         {
           userPrinter = printer;
         }
         else
         {
           if (localIterator.hasNext())
           {
        	   for (Printer printer1 : prints)
               {
                 if ((!printer.getPrinterId().equals(userPrinterId)) || 
                   (!TrueFalseEnum.TRUE.getCode().equals(printer1.getIsUseDrawer())))
                   continue;
                 userPrinter = printer1;
                 break;
               }
             continue;
           }
 
           break;
 
           
         }
       }
       }
     }
 
     if (userPrinter == null)
     {
       ajax.setStatusCode(StatusCodeEnum.CHECK_ERROR.getCode());
       ajax.setMessage("没有找到打印机");
       return ajax;
     }
 
     ajax.setValue(userPrinter.getSysName());
     String logNotes = "打开钱箱操作：打印机 " + userPrinter.getName();
     logNotes = logNotes + "，操作人:" + user.loginName;
 
     DinerBillLog dinerBillLog = new DinerBillLog();
 
     dinerBillLog.setBillOpType(BillOpTypeEnum.OPEN_DRAWER.getCode());
 
     dinerBillLog.setRestId(restId);
 
     dinerBillLog.setNotes(logNotes);
     this.dinerBillLogService.save(dinerBillLog);
 
     doSynchSingleTable(OperationTypeEnum.CREATE.getCode(), dinerBillLog);
 
     return ajax;
   }
 
   private void silenceLogin(HttpServletRequest request, String userName, String password)
   {
     Subject subject = SecurityUtils.getSubject();
     UsernamePasswordToken token = new UsernamePasswordToken(userName, password, true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
 }

