 package com.ndlan.canyin.frontdesk.front.system;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.ctzh.PrinterService;
import com.ndlan.canyin.frontdesk.service.qtsy.CashierDeskSettingService;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.base.entity.qtsy.CashierDeskSetting;
import com.ndlan.canyin.core.common.EnableStatusEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.PrinterTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.utils.BeanUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/system"})
 @Lazy
 public class SystemController extends BaseManageController
 {
   public static final int HASH_INTERATIONS = 1024;
 
   @Autowired
   private CashierDeskSettingService cashierDeskSettingService;
 
   @Autowired
   private PrinterService printerService;
 
   @RequestMapping({"setting"})
   public String setting(Model model, HttpServletRequest request)
   {
     model.addAttribute("sysVersion", "2.3.0");
 
     CashierDeskSetting commonSettingCashierDeskSetting = this.cashierDeskSettingService.findConmonSettingByRestId(getCurrentUserRestId());
     model.addAttribute("commonSettingCashierDeskSetting", commonSettingCashierDeskSetting);
 
     CashierDeskSetting cashierDeskSetting = this.cashierDeskSettingService.findByRestIdAndEmpId(getCurrentUserRestId(), getCurrentUserId());
 
     if (cashierDeskSetting == null) {
       try
       {
         cashierDeskSetting = (CashierDeskSetting)BeanUtils.deepCopy(commonSettingCashierDeskSetting);
         cashierDeskSetting.setCcdsId(null);
         cashierDeskSetting.setEmpId(getCurrentUserId());
         this.cashierDeskSettingService.save(cashierDeskSetting);
 
         doSynchSingleTable(OperationTypeEnum.CREATE.getCode(), cashierDeskSetting);
       }
       catch (IOException e) {
         e.printStackTrace();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }
     }
 
     model.addAttribute("cashierDeskSetting", cashierDeskSetting);
 
     model.addAttribute("empId", getCurrentUserId());
 
     List printerlist = this.printerService.findByRestIdAndStatusAndType(getCurrentUserRestId(), EnableStatusEnum.NORMAL.getCode(), PrinterTypeEnum.FRONT_DESC.getCode());
     model.addAttribute("printerlist", printerlist);
     return "system/setting"; } 
   @RequestMapping(value={"setting/updateOrder"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone updateOrder(@Valid CashierDeskSetting cashierDeskSetting, RedirectAttributes redirectAttributes) { DwzAjaxDone ajax = new DwzAjaxDone();
     this.cashierDeskSettingService.save(cashierDeskSetting);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), cashierDeskSetting);
 
     UserSettingCache.getInstance().setCommonUserCache(cashierDeskSetting);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("修改成功");
     ajax.setRel(cashierDeskSetting.getCcdsId());
     return ajax; } 
   @RequestMapping(value={"setting/update"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone create(@Valid CashierDeskSetting cashierDeskSetting, RedirectAttributes redirectAttributes) { DwzAjaxDone ajax = new DwzAjaxDone();
     cashierDeskSetting.setEmpId(getCurrentUserId());
     this.cashierDeskSettingService.save(cashierDeskSetting);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), cashierDeskSetting);
 
     UserSettingCache.getInstance().setUserCache(getCurrentUserId(), cashierDeskSetting);
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("修改成功");
     ajax.setRel(cashierDeskSetting.getCcdsId());
     return ajax;
   }
 
   @RequestMapping(value={"setting/updateModule"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone updateModule(@RequestParam(value="moduleId", required=true) String moduleId, @RequestParam(value="isShowModuleDesk", required=false, defaultValue="1") String isShowModuleDesk, @RequestParam(value="isShowModuleFastfood", required=false, defaultValue="1") String isShowModuleFastfood, @RequestParam(value="isShowModuleWaimai", required=false, defaultValue="1") String isShowModuleWaimai, @RequestParam(value="isShowModuleBill", required=false, defaultValue="1") String isShowModuleBill, @RequestParam(value="isShowModuleMember", required=false, defaultValue="1") String isShowModuleMember, @RequestParam(value="isShowModuleOrder", required=false, defaultValue="1") String isShowModuleOrder, @RequestParam(value="isShowModuleGuqing", required=false, defaultValue="1") String isShowModuleGuqing, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     String op = OperationTypeEnum.UPDATE.getCode();
     CashierDeskSetting cashierDeskSetting = this.cashierDeskSettingService.findByRestIdAndEmpId(getCurrentUserRestId(), getCurrentUserId());
     cashierDeskSetting.setIsShowModuleDesk(isShowModuleDesk);
     cashierDeskSetting.setIsShowModuleFastfood(isShowModuleFastfood);
     cashierDeskSetting.setIsShowModuleWaimai(isShowModuleWaimai);
     cashierDeskSetting.setIsShowModuleBill(isShowModuleBill);
     cashierDeskSetting.setIsShowModuleMember(isShowModuleMember);
     cashierDeskSetting.setIsShowModuleOrder(isShowModuleOrder);
     cashierDeskSetting.setIsShowModuleGuqing(isShowModuleGuqing);
     cashierDeskSetting.setEmpId(getCurrentUserId());
     this.cashierDeskSettingService.save(cashierDeskSetting);
 
     doSynchSingleTable(op, cashierDeskSetting);
 
     UserSettingCache.getInstance().setUserCache(getCurrentUserId(), cashierDeskSetting);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("修改成功");
     ajax.setRel(cashierDeskSetting.getCcdsId());
     return ajax;
   }
 
   @RequestMapping(value={"setting/updateOther"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone updateOther(@RequestParam(value="otherId", required=true) String otherId, @RequestParam(value="printerId", required=false) String printerId, @RequestParam(value="leaveTime", required=true) int leaveTime, Model model) {
     DwzAjaxDone ajax = new DwzAjaxDone();
     CashierDeskSetting cashierDeskSetting = this.cashierDeskSettingService.findByRestIdAndEmpId(getCurrentUserRestId(), getCurrentUserId());
     cashierDeskSetting.setPrinterId(printerId);
     cashierDeskSetting.setLeaveTime(Integer.valueOf(leaveTime));
     cashierDeskSetting.setEmpId(getCurrentUserId());
     this.cashierDeskSettingService.save(cashierDeskSetting);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), cashierDeskSetting);
 
     UserSettingCache.getInstance().setUserCache(getCurrentUserId(), cashierDeskSetting);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("修改成功");
     ajax.setRel(cashierDeskSetting.getCcdsId());
     return ajax;
   }
 
   @ModelAttribute
   public void getEntity(@RequestParam(value="id", required=false) String id, Model model)
   {
     if ((id != null) && (!id.isEmpty())) {
       CashierDeskSetting cashierDeskSetting = (CashierDeskSetting)this.cashierDeskSettingService.getOne(id);
       model.addAttribute("cashierDeskSetting", cashierDeskSetting);
     }
   }
 }

