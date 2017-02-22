 package com.ndlan.canyin.frontdesk.front.estimate;
 
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetService;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/estimate"})
 @Lazy
 public class EstimateController extends BaseManageController
 {
 
   @Autowired
   DishesCategoryService dishesCategoryService;
 
   @Autowired
   DisheService disheService;
 
   @Autowired
   DishesSetCategoryService dishesSetCategoryService;
 
   @Autowired
   private DishesSetService dishesSetService;
 
   @RequestMapping({""})
   public String list(Model model, HttpServletRequest request)
   {
     return "estimate/estimate";
   }
 
   @RequestMapping({"ajax/dishes/list"})
   public String dishesList(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pagzSize", defaultValue="9999") int pagzSize, @RequestParam(value="categoryId", defaultValue="") String categoryId, @RequestParam(value="dsCategoryId", defaultValue="") String dsCategoryId, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="estimateStatus", defaultValue="") String estimateStatus, Model model, HttpServletRequest request)
   {
     List dishesCategorys = this.dishesCategoryService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     Page dishes = this.disheService.estimateSearchDishe(getCurrentUserRestId(), categoryId, dsCategoryId, keywords, estimateStatus, null, pageNumber, pagzSize, "estimate");
     model.addAttribute("dishes", dishes);
 
     List dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusOrderByShowSeqAsc(getCurrentUserRestId());
     model.addAttribute("dishesSetCategorys", dishesSetCategorys);
 
     model.addAttribute("categoryId", categoryId);
     model.addAttribute("dsCategoryId", dsCategoryId);
     model.addAttribute("keywords", keywords);
     model.addAttribute("dishesCategorys", dishesCategorys);
     model.addAttribute("dishes", dishes);
     model.addAttribute("estimateStatus", estimateStatus);
     return "estimate/estimateContent";
   }
 
   @RequestMapping(value={"pop/estimate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String dishesEstimate(@RequestParam(value="dishesId", required=false) String dishesId, @RequestParam(value="dishesType", defaultValue="1", required=false) String dishesType, RedirectAttributes redirectAttributes, Model model)
   {
     if ("1".equals(dishesType))
     {
       Dishe dishe = (Dishe)this.disheService.loadOne(dishesId);
       model.addAttribute("dishesName", dishe.getDishesName());
       model.addAttribute("estimate", dishe.getEstimate());
     }
     else
     {
       DishesSet dishesSet = (DishesSet)this.dishesSetService.loadOne(dishesId);
       model.addAttribute("dishesName", dishesSet.getDsName());
       model.addAttribute("estimate", dishesSet.getEstimate());
     }
     model.addAttribute("dishesId", dishesId);
     model.addAttribute("dishesType", dishesType);
     return "estimate/estimateForm";
   }
 
   @RequestMapping(value={"pop/estimate/update"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone dishesEstimateUpdate(@RequestParam("dishesId") String dishesId, @RequestParam(value="estimate", required=false) Float estimate, @RequestParam(value="dishesType", defaultValue="1", required=false) String dishesType, RedirectAttributes redirectAttributes, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
 
     if ("1".equals(dishesType)) {
       Dishe dishe = (Dishe)this.disheService.getOne(dishesId);
       dishe.setEstimate(estimate);
       this.disheService.save(dishe);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishe);
     }
     else
     {
       DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dishesId);
       dishesSet.setEstimate(estimate);
       this.dishesSetService.save(dishesSet);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishesSet);
     }
 
     ajax.setRel("0");
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("估清成功");
     return ajax;
   }
 
   @RequestMapping(value={"qxguqing/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone qxguqing(@PathVariable("id") String id, @RequestParam(value="dishesType", defaultValue="1", required=false) String dishesType, RedirectAttributes redirectAttributes, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     if ("1".equals(dishesType)) {
       Dishe dishe = (Dishe)this.disheService.getOne(id);
       dishe.setEstimate(null);
       this.disheService.save(dishe);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishe);
     }
     else
     {
       DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(id);
       dishesSet.setEstimate(null);
       this.dishesSetService.save(dishesSet);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishesSet);
     }
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("取消沽清成功");
     return ajax;
   }
 
   /**
    * 多选取消沽清
    */
   @RequestMapping(value={"qxguqingd/{id}"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone qxguqingquan(@PathVariable("id") String id, @RequestParam(value="dishesType", defaultValue="1", required=false) String dishesType, RedirectAttributes redirectAttributes, Model model)
   {
	   
	      String temp = id;
		  String tempArray [] = temp.split(",");
		  DwzAjaxDone ajax = new DwzAjaxDone();
		  for(String bdIds:tempArray){
			  if ("1".equals(dishesType)) {
			       Dishe dishe = (Dishe)this.disheService.getOne(bdIds);
			       dishe.setEstimate(null);
			       this.disheService.save(dishe);
			 
			       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishe);
			     }
			     else
			     {
			       DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(bdIds);
			       dishesSet.setEstimate(null);
			       this.dishesSetService.save(dishesSet);
			 
			       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishesSet);
			     }
			  
		  }
    
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("取消沽清成功");
     return ajax;
   }
   
   
   
   @RequestMapping(value={"allQxguqing"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone allQxguqing(RedirectAttributes redirectAttributes, Model model)
   {
     DwzAjaxDone ajax = new DwzAjaxDone();
     LinkedHashMap map = new LinkedHashMap();
     this.disheService.allQxguqing(getCurrentUserRestId(), map);
 
     doSynchMultiTable(map);
 
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage("全部取消沽清成功");
     return ajax;
   }
   @RequestMapping(value={"getEstimateNumber"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone getTableStatusNumber(Model model) throws JsonProcessingException { DwzAjaxDone ajax = new DwzAjaxDone();
 
     int all = 0;
     int yiguqing = 0;
     int yishouxin = 0;
     int jiangshouwan = 0;
     int weiguqing = 0;
 
     List dishes = this.disheService.findByRestIdOrderByShowSeqAsc(getCurrentUserRestId());
 
     List dishesSets = this.dishesSetService.findByRestIdOrderByShowSeqAsc(getCurrentUserRestId());
 
     if (dishes != null)
     {
       all = dishes.size() + dishesSets.size();
       yiguqing = getYiguqing(dishes, dishesSets);
       yishouxin = getYishouxin(dishes, dishesSets);
       jiangshouwan = getJiangshouwan(dishes, dishesSets);
       weiguqing = getWeiguqing(dishes, dishesSets);
     }
 
     String jsonStr = "[{'all':'" + all + "','yiguqing':'" + yiguqing + "','yishouxin':'" + yishouxin + "','jiangshouwan':'" + jiangshouwan + "','weiguqing':'" + weiguqing + "'}]";
     ajax.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     ajax.setMessage(jsonStr);
     ajax.setRel("");
     return ajax;
   }
 
   private int getYiguqing(List<Dishe> dishes, List<DishesSet> dishesSets)
   {
     int num = 0;
     for (Dishe e : dishes)
     {
       if ((e.getEstimate() != null) && (e.getEstimate().floatValue() >= 0.0F))
       {
         num++;
       }
     }
     for (DishesSet e : dishesSets)
     {
       if ((e.getEstimate() != null) && (e.getEstimate().floatValue() >= 0.0F))
       {
         num++;
       }
     }
     return num;
   }
 
   private int getYishouxin(List<Dishe> dishes, List<DishesSet> dishesSets)
   {
     int num = 0;
     for (Dishe e : dishes)
     {
       if ((e.getEstimate() != null) && (e.getEstimate().floatValue() <= 0.0F))
       {
         num++;
       }
     }
     for (DishesSet e : dishesSets)
     {
       if ((e.getEstimate() != null) && (e.getEstimate().floatValue() <= 0.0F))
       {
         num++;
       }
     }
     return num;
   }
 
   private int getJiangshouwan(List<Dishe> dishes, List<DishesSet> dishesSets)
   {
     int num = 0;
     for (Dishe e : dishes)
     {
       if ((e.getEstimate() != null) && (e.getEstimate().floatValue() > 0.0F) && (e.getEstimate().floatValue() < 10.0F))
       {
         num++;
       }
     }
     for (DishesSet e : dishesSets)
     {
       if ((e.getEstimate() != null) && (e.getEstimate().floatValue() > 0.0F) && (e.getEstimate().floatValue() < 10.0F))
       {
         num++;
       }
     }
     return num;
   }
 
   private int getWeiguqing(List<Dishe> dishes, List<DishesSet> dishesSets)
   {
     int num = 0;
     for (Dishe e : dishes)
     {
       if (e.getEstimate() == null)
       {
         num++;
       }
     }
     for (DishesSet e : dishesSets)
     {
       if (e.getEstimate() == null)
       {
         num++;
       }
     }
     return num;
   }
 }

