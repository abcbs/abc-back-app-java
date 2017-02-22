 package com.ndlan.canyin.frontdesk.front.open;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.core.common.StatusCodeEnum;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @RequestMapping({"/open/dishe"})
 @Lazy
 public class DisheOpenController extends BaseManageController
 {
 
   @Autowired
   DishesCategoryService dishesCategoryService;
 
   @Autowired
   DisheService disheService;
 
   @RequestMapping(value={"dish_list"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishList(Model model, HttpServletRequest request)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     List list = this.disheService.findByRestIdOrderByShowSeqAsc(getCurrentUserRestId());
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setList(list);
     return ajax; } 
   @RequestMapping(value={"category_list"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone categoryList(Model model, HttpServletRequest request) { DwzAjaxDone ajax = new DwzAjaxDone();
     List list = this.dishesCategoryService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setList(list);
     return ajax;
   }
 }

