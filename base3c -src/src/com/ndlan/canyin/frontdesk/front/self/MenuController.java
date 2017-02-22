 package com.ndlan.canyin.frontdesk.front.self;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.dto3c.MessageVO;
import com.ndlan.canyin.frontdesk.message.ware.MessageCenterNativeServer;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.ctzh.TableService;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesMaterialService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesPicService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetDishesService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetPicService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesStyleService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesTasteService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesUnitService;
import com.ndlan.canyin.frontdesk.service.cygl.SpecialDisheServie;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.DishesSetDishesReplaceService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfDishService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfMessageService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfOrderService;
import com.ndlan.canyin.frontdesk.service.qtsy.TableBillRelationService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.MediaUtil;
import com.ndlan.canyin.frontdesk.util.MessageCarrierUtil;
import com.ndlan.canyin.frontdesk.util.PathUtil;
import com.ndlan.canyin.frontdesk.util.QRCodeUtil;
import com.ndlan.canyin.frontdesk.util.ZipCompressor;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesMaterial;
import com.ndlan.canyin.base.entity.cygl.DishesPic;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetPic;
import com.ndlan.canyin.base.entity.cygl.DishesStyle;
import com.ndlan.canyin.base.entity.cygl.DishesTaste;
import com.ndlan.canyin.base.entity.cygl.SpecialDishe;
import com.ndlan.canyin.base.entity.cygl.SpecialPriceInterval;
import com.ndlan.canyin.base.entity.qtsy.SelfDish;
import com.ndlan.canyin.base.entity.qtsy.SelfMessage;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
import com.ndlan.canyin.core.common.CategoryLevelEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.SelfServiceMarkEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.NetUtil;
import com.ndlan.canyin.core.web.Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
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
 @RequestMapping({"/self"})
 @Lazy
 public class MenuController extends BaseManageController
 {
   public static int orderCall = 1;
   public static int serviceCall = 2;
 
   @Autowired
   DisheService disheService;
 
   @Autowired
   DishesSetService dishesSetService;
 
   @Autowired
   DishesCategoryService dishesCategoryService;
 
   @Autowired
   DishesUnitService dishesUnitService;
 
   @Autowired
   private TableService tableService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   TableBillRelationService tableBillRelationService;
 
   @Autowired
   DishesTasteService dishesTasteService;
 
   @Autowired
   DishesMaterialService dishesMaterialService;
 
   @Autowired
   DishesStyleService dishesStyleService;
 
   @Autowired
   MessageCenterNativeServer messageCenterNativeServer;
 
   @Autowired
   DishesPicService dishesPicService;
 
   @Autowired
   DishesSetPicService dishesSetPicService;
 
   @Autowired
   SelfMessageService selfMessageService;
 
   @Autowired
   SelfOrderService selfOrderService;
 
   @Autowired
   SelfDishService selfdishService;
 
   @Autowired
   private RestaurantService restaurantService;
 
   @Autowired
   DishesSetCategoryService dishesSetCategoryService;
 
   @Autowired
   DishesSetDishesReplaceService dishesSetDishesReplaceService;
 
   @Autowired
   DishesSetDishesService dishesSetDishesService;
 
   @Autowired
   SpecialDisheServie specialDisheService;
 
   @RequestMapping(value={"teminalLogin"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String teminalLogin(@RequestParam(value="u", required=false) String u, @RequestParam(value="p", required=false) String p, Model model, HttpServletRequest request) { try { silenceLogin(request, u, p);
     }
     catch (Exception e)
     {
       e.printStackTrace();
       return "redirect:/login";
     }
     return "redirect:/index";
   }
 
   @RequestMapping({"desc"})
   public String instruction(@ModelAttribute("tabNO") String tabNO, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="newip", defaultValue="") String newip, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     Restaurant restaurant = (Restaurant)this.restaurantService.findAll().get(0);
 
     String allTableQr = null;
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     searchParams.put("EQ_isEnable", "1");
     Page tables = this.tableService.searchByFilter(restaurant.getRestId(), null, searchParams, 1, 1000, null, new String[] { "tabNo" });
     List<Table> tableList = tables.getContent();
     String host = NetUtil.getLocalHostByNetworkCard();
     if (!StringUtils.isEmpty(newip))
     {
       host = newip;
     }
     try {
       int port = request.getServerPort();
       String realPath = request.getSession().getServletContext().getRealPath("/static/qrcode");
       request.getContextPath();
       QRCodeUtil.generate2(realPath, "c.jpg", "c", allTableQr = new StringBuilder().append("http://").append(host).append(":").append(port).append("/canyin-frontdesk/self/c").toString());
       for (Table table : tableList) {
         String qrCode = new StringBuilder().append("http://").append(host).append(":").append(port).append("/canyin-frontdesk/self?t=").append(table.getTabNo()).toString();
         QRCodeUtil.generate2(realPath, new StringBuilder().append(table.getTabNo()).append(".jpg").toString(), table.getTabNo(), qrCode);
         table.setQrCodeImg(qrCode);
       }
     }
     catch (Exception e)
     {
       int port;
       String realPath;
       e.printStackTrace();
     }
     model.addAttribute("tableList", tableList);
     model.addAttribute("restName", restaurant.getRestName());
     model.addAttribute("allTableQr", allTableQr);
     return "self/description";
   }
 
   @RequestMapping({"saveAs"})
   public void saveAs(@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse res)
     throws IOException, Exception
   {
     File file = new File(new StringBuilder().append(PathUtil.getProjectStaticFilePath()).append("/qrcode/").append(fileName).toString());
     if (file.exists())
     {
       OutputStream os = res.getOutputStream();
       try {
         res.reset();
         res.setHeader("Content-Disposition", new StringBuilder().append("attachment; filename=").append(fileName).append("").toString());
         res.setContentType("image/JPEG; charset=utf-8");
         FileInputStream in = new FileInputStream(file);
         byte[] b = new byte[1024];
         int i = 0;
         while ((i = in.read(b)) > 0)
         {
           os.write(b, 0, i);
         }
         os.flush();
       }
       finally {
         if (os != null)
           os.close();
       }
     }
   }
 
   @RequestMapping({"saveAllAs"})
   public void saveAllAs(HttpServletRequest request, HttpServletResponse res)
     throws IOException, Exception
   {
     String fileName = "all";
     String pathAll = new StringBuilder().append(PathUtil.getProjectStaticFilePath()).append("\\qrcode").toString();
 
     ZipCompressor zipCompressor = new ZipCompressor(new StringBuilder().append(pathAll).append(".zip").toString());
     zipCompressor.compress(pathAll);
 
     File file = new File(new StringBuilder().append(pathAll).append(".zip").toString());
     if (file.exists())
     {
       OutputStream os = res.getOutputStream();
       try {
         res.reset();
         res.setHeader("Content-Disposition", new StringBuilder().append("attachment; filename=").append(fileName).append(".zip").toString());
         res.setContentType("application/octet-stream; charset=utf-8");
         FileInputStream in = new FileInputStream(file);
         byte[] b = new byte[1024];
         int i = 0;
         while ((i = in.read(b)) > 0)
         {
           os.write(b, 0, i);
         }
         os.flush();
       }
       finally {
         if (os != null)
           os.close();
       }
     }
   }
 
   @RequestMapping({"ol"})
   public String ol(@RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     silenceLogin(request);
     HashMap map = this.messageCenterNativeServer.getOnLineMap();
     model.addAttribute("set", map.keySet());
     return "self/online";
   }
 
   @RequestMapping({""})
   public String entery(@RequestParam(value="t", defaultValue="01") String t, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request)
   {
     try
     {
       t = URLDecoder.decode(t, "UTF-8");
       silenceLogin(request);
 
       SelfOrder selfOrder = this.selfOrderService.getSelfOrderByTabNo(getCurrentUserRestId(), t);
       if (selfOrder == null) {
         SelfOrder newSelfOrder = new SelfOrder();
         newSelfOrder.setTabNo(t);
         newSelfOrder.setStatus(TrueFalseEnum.TRUE.getCode());
         this.selfOrderService.save(newSelfOrder);
 
         doSynchSingleTable(OperationTypeEnum.CREATE.getCode(), newSelfOrder);
       }
 
       redirectAttributes.addAttribute("tabNO", t);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return "redirect:/self/main";
   }
 
   private SelfOrder getValidateTableOrder(String tabNo)
   {
     return this.selfOrderService.getSelfOrderByTabNo(getCurrentUserRestId(), tabNo);
   }
 
   private void silenceLogin(HttpServletRequest request, String u, String p) {
     Subject subject = SecurityUtils.getSubject();
     subject.logout();
     UsernamePasswordToken token = new UsernamePasswordToken(u, p, true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
 
   private void silenceLogin(HttpServletRequest request)
   {
     Subject subject = SecurityUtils.getSubject();
     subject.logout();
     UsernamePasswordToken token = new UsernamePasswordToken("vister", "vister", true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
 
   @RequestMapping({"c"})
   public String chooseTable(@ModelAttribute("tabNO") String tabNO, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     silenceLogin(request);
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     searchParams.put("EQ_isEnable", "1");
     Page tables = this.tableService.searchByFilter(getCurrentUserRestId(), null, searchParams, pageNumber, 1000, null, new String[] { "createTime" });
     List tableList = tables.getContent();
 
     model.addAttribute("tableList", tableList);
     model.addAttribute("restName", getCurrentUser().getRestaurant().getRestName());
     return "self/chooseTable";
   }
 
   @RequestMapping({"main"})
   public String main(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="dishCategory", defaultValue="") String dishCategory, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     silenceLogin(request);
     List firstDishesCategorys = this.dishesCategoryService.findByRestIdAndEnableStatusAndCategoryLevel(getCurrentUserRestId(), CategoryLevelEnum.FIRST.getCode());
     model.addAttribute("firstDishesCategorys", firstDishesCategorys);
 
     List dishesCategorys = this.dishesCategoryService.findAllDishesCategoryByRestId(getCurrentUserRestId());
     model.addAttribute("dishesCategorys", dishesCategorys);
     model.addAttribute("restName", getCurrentUser().getRestaurant().getRestName());
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     if (!"0".equals(dishCategory)) {
       searchParams.put("EQ_dishesCategory.categoryId", dishCategory);
     }
 
     List firstDishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusAndCategoryLevelOrderByShowSeqAsc(getCurrentUserRestId(), CategoryLevelEnum.FIRST.getCode());
     model.addAttribute("firstDishesSetCategorys", firstDishesSetCategorys);
     List dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusOrderByShowSeqAsc(getCurrentUserRestId());
     model.addAttribute("dishesSetCategorys", dishesSetCategorys);
 
     model.addAttribute("sortType", sortType);
     model.addAttribute("direction", direction);
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("tabNO", tabNO);
     model.addAttribute("serverIp", NetUtil.getLocalIp());
     return "self/main";
   }
 
   @RequestMapping({"viewPic"})
   public String showDishPic(@RequestParam(value="dishesId", defaultValue="") String dishesId, @RequestParam(value="tabNO", defaultValue="") String tabNO, Model model, HttpServletRequest request) {
     List pics = this.dishesPicService.findByDishesId(dishesId);
     model.addAttribute("pics", pics);
     model.addAttribute("dishesId", dishesId);
     model.addAttribute("tabNO", tabNO);
     model.addAttribute("serverIp", NetUtil.getLocalIp());
     return "self/viewPic";
   }
 
   @RequestMapping({"dishList"})
   public String dishList(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="dishCategory", defaultValue="") String dishCategory, @RequestParam(value="isRecommend", defaultValue="") String isRecommend, @RequestParam(value="isSet", defaultValue="") String isSet, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pagzSize", defaultValue="20") int pagzSize, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     String categoryId = null; String dsCategoryId = null;
     if (!"0".equals(dishCategory)) {
       if ("0".equals(isSet))
         categoryId = dishCategory;
       else if ("1".equals(isSet)) {
         dsCategoryId = dishCategory;
       }
     }
 
     if ("0".equals(sortType))
       sortType = null;
     else {
       sortType = sortType.replace('_', ' ');
     }
     Page dishes = this.disheService.searchDishe4Self(getCurrentUserRestId(), categoryId, dsCategoryId, keywords, null, isRecommend, pageNumber, pagzSize, sortType);
 
     initDishSaleNum(tabNO, dishes.getContent());
     model.addAttribute("dishes", dishes);
 
     model.addAttribute("sortType", sortType);
     model.addAttribute("direction", direction);
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("tabNO", tabNO);
     model.addAttribute("serverIp", NetUtil.getLocalIp());
     model.addAttribute("pageNumber", Integer.valueOf(pageNumber));
     return "self/dishList";
   }
 
   @RequestMapping({"dishList_"})
   public String dishList_(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="dishCategory", defaultValue="") String dishCategory, @RequestParam(value="isRecommend", defaultValue="") String isRecommend, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="pagzSize", defaultValue="20") int pagzSize, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     if (!"0".equals(dishCategory)) {
       searchParams.put("EQ_dishesCategory.categoryId", dishCategory);
     }
     Page dishes = this.disheService.searchDishe(getCurrentUserRestId(), type, searchParams, pageNumber, pagzSize, null, sortType, isRecommend);
     initDishSaleNum(tabNO, dishes.getContent());
     model.addAttribute("dishes", dishes);
 
     model.addAttribute("sortType", sortType);
     model.addAttribute("direction", direction);
     model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
     model.addAttribute("tabNO", tabNO);
     model.addAttribute("serverIp", NetUtil.getLocalIp());
     model.addAttribute("pageNumber", Integer.valueOf(pageNumber));
     return "self/dishList";
   }
 
   private void initDishSaleNum(String tabNO, List<Dishe> dishList) {
     for (Dishe dishe : dishList) {
       if (dishe.getDishAndSetDiv().equals("1")) {
         List picList = this.dishesPicService.findByDishesId(dishe.getDishesId());
         if ((picList != null) && (picList.size() > 0)) {
           dishe.setPicUrl(((DishesPic)picList.get(0)).getPicUrl());
         }
       }
       else if (dishe.getDishAndSetDiv().equals("2")) {
         List picList = this.dishesSetPicService.findByDishesId(dishe.getDishesId());
         if ((picList != null) && (picList.size() > 0)) {
           dishe.setPicUrl(((DishesSetPic)picList.get(0)).getPicUrl());
         }
       }
     }
 
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     try {
       if (selfOrder != null) {
         List selfDishList = selfOrder.getDishList();
         if ((selfDishList != null) && (selfDishList.size() > 0))
           for (Iterator ite = selfDishList.iterator(); ite.hasNext(); )
           { SelfDish selfDish = (SelfDish)ite.next();
             for (Dishe dishe : dishList)
               if ((selfDish.getDishesId().equals(dishe.getDishesId())) && (TrueFalseEnum.FALSE.getCode().equals(selfDish.getStatus()))) {
                 dishe.setSelfDishSaleNum(Integer.valueOf(selfDish.getSaleNum()));
                 dishe.setSpecialPrice(getSpecialDishPrice(dishe.getDishesId(), new Date().toString()));
                 break;
               }
           }
       }
     }
     catch (Exception e)
     {
       Iterator i$;
       SelfDish selfDish;
       e.printStackTrace();
     }
   }
 
   public BigDecimal getSpecialDishPrice(String disheId, String creatTimeStr)
   {
     try {
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
       Date creatTime = null;
       try {
         creatTime = format.parse(creatTimeStr);
       } catch (ParseException e1) {
       }
       Dishe dishe = (Dishe)this.disheService.getOne(disheId);
       List specialDishes = this.specialDisheService.findByDishe(dishe, getCurrentUserRestId());
       if ((specialDishes == null) || (specialDishes.size() == 0))
       {
         return null;
       }
       if (creatTime == null) {
         creatTime = new Date();
       }
       Iterator i$ = specialDishes.iterator(); if (i$.hasNext()) { SpecialDishe specialDishe = (SpecialDishe)i$.next();
         SpecialPriceInterval spi = specialDishe.getSpecialPriceInterval();
         if ((spi != null) && 
           (TrueFalseEnum.TRUE.getCode().equals(spi.getIsTimeLimit()))) {
           if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsDateValid()))
           {
             if ((spi.getStartDate().getTime() > creatTime.getTime()) || (spi.getEndDate().getTime() + 86400000L < creatTime.getTime())) {
               return null;
             }
           }
 
           if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsSpecialDay())) {
             if (((spi.getIsMonday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsMonday()))) && (DateUtil.getWeekDay(creatTime) == 1))
               return null;
             if (((spi.getIsTuesday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsTuesday()))) && (DateUtil.getWeekDay(creatTime) == 2))
               return null;
             if (((spi.getIsWednesday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsWednesday()))) && (DateUtil.getWeekDay(creatTime) == 3))
               return null;
             if (((spi.getIsThursday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsThursday()))) && (DateUtil.getWeekDay(creatTime) == 4))
               return null;
             if (((spi.getIsFriday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsFriday()))) && (DateUtil.getWeekDay(creatTime) == 5))
               return null;
             if (((spi.getIsSaturday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsSaturday()))) && (DateUtil.getWeekDay(creatTime) == 6))
               return null;
             if (((spi.getIsSunday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsSunday()))) && (DateUtil.getWeekDay(creatTime) == 0)) {
               return null;
             }
           }
           if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsTimeValid())) { String ymd = new SimpleDateFormat("yyyy-MM-dd ").format(creatTime);
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             Date stime;
             Date etime;
             try { stime = df.parse(new StringBuilder().append(ymd).append(spi.getStartTime()).toString());
               etime = df.parse(new StringBuilder().append(ymd).append(spi.getEndTime()).toString());
             } catch (ParseException e) {
               e.printStackTrace();
               return null;
             }
             if ((stime.getTime() > creatTime.getTime()) || (etime.getTime() < creatTime.getTime())) {
               return null;
             }
           }
 
         }
 
         return specialDishe.getSpecialPrice();
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     return null;
   }
 
   @RequestMapping({"dishDetail"})
   public String dishDetail(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="isSet", defaultValue="0") Integer isSet, @RequestParam(value="dishId", defaultValue="") String dishId, Model model, HttpServletRequest request)
   {
     model.addAttribute("tabNO", tabNO == null ? "01" : tabNO);
     model.addAttribute("isSet", isSet);
     List pics = null;
     Dishe dish = null;
     try {
       if (!StringUtils.isEmpty(dishId))
       {
         if ("1".equals(new StringBuilder().append(isSet).append("").toString())) {
           dish = (Dishe)this.disheService.getOne(dishId);
           pics = this.dishesPicService.findByDishesId(dishId);
         } else if ("2".equals(new StringBuilder().append(isSet).append("").toString())) {
           DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dishId);
           List<DishesSetPic> pics_ = this.dishesSetPicService.findByDishesId(dishId);
           pics = new ArrayList();
           for (DishesSetPic dishesSetPic : pics_) {
             DishesPic d = new DishesPic();
             BeanUtils.copyProperties(dishesSetPic, d);
             pics.add(d);
           }
           dish = new Dishe();
           BeanUtils.copyProperties(dishesSet, dish);
           dish.setDishesName(dishesSet.getDsName());
           dish.setDishesId(dishesSet.getDsId());
           dish.setSaleNum(Float.valueOf(dishesSet.getSaleNum() == null ? 0.0F : dishesSet.getSaleNum().floatValue()));
           dish.setNotes(dishesSet.getDsCode());
         }
 
         model.addAttribute("pics", pics);
       }
       model.addAttribute("notes", dish.getNotes());
 
       SelfOrder selfOrder = getValidateTableOrder(tabNO);
       if (selfOrder != null) {
         List<SelfDish> selfDishList = selfOrder.getDishList();
         if ((selfDishList != null) && (selfDishList.size() > 0)) {
           for (SelfDish selfDish : selfDishList) {
             if ((selfDish.getDishesId().equals(dish.getDishesId())) && (TrueFalseEnum.FALSE.getCode().equals(selfDish.getStatus()))) {
               dish.setSelfDishSaleNum(Integer.valueOf(selfDish.getSaleNum()));
               break;
             }
           }
         }
 
       }
 
       model.addAttribute("dish", dish);
       String dishesTasteStr = "";
 
       List dishesTastes = this.dishesTasteService.findAllDishesTasteByRestId(getCurrentUserRestId());
       for (Iterator ite = dishesTastes.iterator(); ite.hasNext(); ) 
       { DishesTaste dishesTaste = (DishesTaste)ite.next();
         List<String> tasteIdList = dish.getTasteIdList();
         if (tasteIdList != null)
         {
           for (String dishesTasteId : tasteIdList)
             if (dishesTasteId.equals(dishesTaste.getTasteId()))
               dishesTasteStr = new StringBuilder().append(dishesTasteStr).append(" ").append(dishesTaste.getName()).toString();
         }
       }
       DishesTaste dishesTaste;
       model.addAttribute("dishesTasteStr", dishesTasteStr);
 
       String dishesMaterialStr = "";
       List dishesMaterials = this.dishesMaterialService.findByRestIdAndEnableStatus(getCurrentUserRestId());
       for (Iterator i$ = dishesMaterials.iterator(); i$.hasNext(); ) 
       { DishesMaterial dishesMaterial = (DishesMaterial)i$.next();
         List<String> materialIdList = dish.getMaterialIdList();
         if (materialIdList != null)
         {
           for (String dishesMaterialId : materialIdList)
             if (dishesMaterialId.equals(dishesMaterial.getMaterialId()))
               dishesMaterialStr = new StringBuilder().append(dishesMaterialStr).append(" ").append(dishesMaterial.getName()).toString();
         }
       }
       DishesMaterial dishesMaterial;
       String dishesStylesStr = "";
       List dishesStyles = this.dishesStyleService.findByRestIdAndEnableStatus(getCurrentUserRestId());
       for (Iterator i$ = dishesStyles.iterator(); i$.hasNext(); ) 
       { DishesStyle dishesStyle = (DishesStyle)i$.next();
         List<String> dishesStyleIdList = dish.getDashesStyleIdList();
         if (dishesStyleIdList != null)
         {
           for (String dishesStyleId : dishesStyleIdList)
             if (dishesStyleId.equals(dishesStyle.getDashesStyleId()))
               dishesStylesStr = new StringBuilder().append(dishesStylesStr).append(" ").append(dishesStyle.getName()).toString();
         }
       }
       DishesStyle dishesStyle;
       model.addAttribute("dishesStylesStr", dishesStylesStr);
       model.addAttribute("serverIp", NetUtil.getLocalIp());
       model.addAttribute("dishesMaterialStr", dishesMaterialStr);
       model.addAttribute("categoryName", dish.getDishesCategory().getCategoryName());
       model.addAttribute("saleNum", dish.getSaleNum());
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return "self/dishDetail";
   }
 
   @Deprecated
   @RequestMapping(value={"getAddedDish"}, produces={"application/json"})
   @ResponseBody
   public Collection<Dishe> getAddedDish(@RequestParam(value="tabNO", defaultValue="") String tabNO, Model model, ServletRequest request)
   {
     Collection dishes = null;
 
     return dishes;
   }
 
   @RequestMapping(value={"addDish"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone addDish(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="isSet", defaultValue="0") Integer isSet, String dishId, @RequestParam(value="count", defaultValue="1", required=false) int count, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     DwzAjaxDone done = new DwzAjaxDone();
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     isSet = Integer.valueOf(isSet.intValue() - 1);
     if (selfOrder != null)
     {
       List dishList = this.selfdishService.findBySelfOrderAndStatus(selfOrder, TrueFalseEnum.FALSE.getCode());
       SelfDish selfDish = isContainsDish(dishList, dishId);
       if (selfDish != null) {
         if (count == 0) {
           String id = selfDish.getId();
           this.selfdishService.delete(id);
 
           doSynchSingleTable(OperationTypeEnum.DELETE_ID.getCode(), new StringBuilder().append("cm_self_dinner_bill_dishe,id,").append(id).toString());
         }
         else {
           selfDish.setSaleNum(count);
           this.selfdishService.save(selfDish);
 
           doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfDish);
         }
 
       }
       else if (count > 0) {
         selfDish = new SelfDish();
         Dishe dishe = null;
         if ("0".equals(new StringBuilder().append(isSet).append("").toString())) {
           dishe = (Dishe)this.disheService.getOne(dishId);
           selfDish.setDishesName(dishe.getDishesName());
         } else if ("1".equals(new StringBuilder().append(isSet).append("").toString())) {
           DishesSet dishesSet = (DishesSet)this.dishesSetService.getOne(dishId);
           BeanUtils.copyProperties(dishesSet, dishe = new Dishe());
           selfDish.setDishesName(dishesSet.getDsName());
         }
 
         selfDish.setIsSet(isSet);
         selfDish.setDishesId(dishId);
 
         selfDish.setSaleNum(count);
         selfDish.setRealCost(dishe.getPrice());
         selfDish.setStatus(TrueFalseEnum.FALSE.getCode());
         selfDish.setSelfOrder(selfOrder);
         this.selfdishService.save(selfDish);
 
         doSynchSingleTable(OperationTypeEnum.CREATE.getCode(), selfDish);
       }
 
       done.setValue(String.valueOf(count));
       String totalPrice = selfDish.getRealCost().multiply(BigDecimal.valueOf(selfDish.getSaleNum())).toString();
       done.setSign(totalPrice.replaceAll("\\.00", ""));
       done.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       done.setMessage("点餐成功，请等待服务员确认");
     }
     else
     {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
     return done;
   }
 
   @RequestMapping(value={"praise"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone praise(@RequestParam(value="tabNO", defaultValue="") String tabNO, String dishId, @RequestParam(value="count", defaultValue="1", required=false) int count, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     DwzAjaxDone done = new DwzAjaxDone();
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
 
     if (selfOrder != null) {
       Dishe dishe = (Dishe)this.disheService.getOne(dishId);
       dishe.setPraise(Integer.valueOf(dishe.getPraise().intValue() + 1));
       this.disheService.save(dishe);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishe);
 
       done.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
       done.setMessage("点赞成功！");
     }
     else
     {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
     return done;
   }
 
   @RequestMapping(value={"tread"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone tread(@RequestParam(value="tabNO", defaultValue="") String tabNO, String dishId, @RequestParam(value="count", defaultValue="1", required=false) int count, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     DwzAjaxDone done = new DwzAjaxDone();
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
 
     if (selfOrder != null) {
       Dishe dishe = (Dishe)this.disheService.getOne(dishId);
       if (dishe.getPraise().intValue() > 0) {
         dishe.setPraise(Integer.valueOf(dishe.getPraise().intValue() - 1));
         this.disheService.save(dishe);
 
         doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishe);
 
         done.setMessage("点踩成功！");
       }
 
       done.setStatusCode(StatusCodeEnum.SUCCESS.getCode());
     }
     else
     {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
     return done;
   }
 
   private SelfDish isContainsDish(List<SelfDish> dishList, String dishId) {
     if ((dishList != null) && (dishList.size() > 0)) {
       for (SelfDish selfDish : dishList) {
         if (selfDish.getDishesId().equals(dishId)) {
           return selfDish;
         }
       }
     }
 
     return null;
   }
   @RequestMapping(value={"getShoppingCarInfo"}, produces={"application/json"})
   @ResponseBody
   public String getShoppingCarInfo(@RequestParam(value="tabNO", defaultValue="") String tabNO, Model model, ServletRequest request) { int count = 0;
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     if (selfOrder != null) {
       List<SelfDish> selfDishList = this.selfdishService.findBySelfOrderAndStatus(selfOrder, TrueFalseEnum.FALSE.getCode());
       for (SelfDish selfDish : selfDishList) {
         count += selfDish.getSaleNum();
       }
     }
     return new StringBuilder().append("").append(count).toString();
   }
 
   @RequestMapping({"cookRemark"})
   public String cookRemark(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="cookRemark", defaultValue="") String cookRemark, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     if (selfOrder != null) {
       selfOrder.setNotes(cookRemark);
       this.selfOrderService.save(selfOrder);
 
       doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfOrder);
     }
 
     return "self/orderedDish";
   }
 
   @RequestMapping({"showOrderedDishList"})
   public String showOrderedDishList(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     String transmit = new StringBuilder().append(" 我在 ").append(getCurrentUser().getRestaurant().getRestName()).append(" 餐厅吃了 ").toString();
     if (selfOrder != null) {
       String time = new Date().toString();
       List<SelfDish> orderedSelfDishList = this.selfdishService.findBySelfOrderAndStatus(selfOrder, TrueFalseEnum.TRUE.getCode());
       List<SelfDish> unOrderedSelfDishList = this.selfdishService.findBySelfOrderAndStatus(selfOrder, TrueFalseEnum.FALSE.getCode());
       for (SelfDish selfDish : orderedSelfDishList) {
         transmit = new StringBuilder().append(transmit).append("  ").append(selfDish.getDishesName()).append(",").toString();
         BigDecimal specialDishPrice = getSpecialDishPrice(selfDish.getDishesId(), time);
         if (specialDishPrice != null) {
           selfDish.setRealCost(specialDishPrice);
         }
       }
       for (SelfDish selfDish : unOrderedSelfDishList) {
         transmit = new StringBuilder().append(transmit).append("  ").append(selfDish.getDishesName()).append(",").toString();
         BigDecimal specialDishPrice = getSpecialDishPrice(selfDish.getDishesId(), time);
         if (specialDishPrice != null) {
           selfDish.setRealCost(specialDishPrice);
         }
       }
       model.addAttribute("dishList", unOrderedSelfDishList);
       model.addAttribute("ordereddishList", orderedSelfDishList);
       model.addAttribute("cookRemark", selfOrder.getNotes());
     }
     model.addAttribute("tabNO", tabNO);
     model.addAttribute("restName", getCurrentUser().getRestaurant().getRestName());
     model.addAttribute("transmit", new StringBuilder().append(transmit).append(" 味道棒极了，小伙伴们快来尝尝吧！我是用手机自助点餐点的菜，用自己的手机点的菜，这个应用很有意思--").append(getCurrentUser().getRestaurant().getRestName()).append(",").append(getCurrentUser().getRestaurant().getAdrDetail() != null ? getCurrentUser().getRestaurant().getAdrDetail() : "").toString());
     return "self/orderedDish";
   }
 
   @Deprecated
   @RequestMapping(value={"delDish"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone delDish(@RequestParam(value="tabNO", defaultValue="") String tabNO, String dishId, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     DwzAjaxDone done = new DwzAjaxDone();
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     if (selfOrder != null) {
       List selfDishList = selfOrder.getDishList();
       SelfDish selfDish = isContainsDish(selfDishList, dishId);
       if (selfDish != null) {
         String id = selfDish.getDishesId();
         this.selfdishService.delete(id);
 
         doSynchSingleTable(OperationTypeEnum.DELETE_ID.getCode(), new StringBuilder().append("cm_self_dinner_bill_dishe,id,").append(id).toString());
 
         done.setMessage("该菜已删除成功");
       }
     }
     else {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
 
     return done;
   }
 
   @RequestMapping(value={"delAllDish"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone delAllDish(@RequestParam(value="tabNO", defaultValue="") String tabNO, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     DwzAjaxDone done = new DwzAjaxDone();
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     if (selfOrder != null) {
       List unOrderedSelfDishList = this.selfdishService.findBySelfOrderAndStatus(selfOrder, TrueFalseEnum.FALSE.getCode());
       this.selfdishService.batchDelete(unOrderedSelfDishList);
 
       doSynchSingleTable(OperationTypeEnum.BATCH_DELETE.getCode(), unOrderedSelfDishList);
 
       done.setMessage("整单删除成功");
     }
     else {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
 
     return done;
   }
 
   @RequestMapping({"commitOrder"})
   public String commitOrder(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     if (selfOrder != null) {
       String content = new StringBuilder().append(tabNO).append(" 号餐台客人  呼叫下单").toString();
       MediaUtil.playSound();
       SelfMessage newSelfMessage = null;
 
       List<SelfMessage> selfMessageList = this.selfMessageService.findBySelfOrderAndMesType(selfOrder, SelfServiceMarkEnum.CALLORDER.getCode());
       String op = OperationTypeEnum.UPDATE.getCode();
       if ((selfMessageList != null) && (selfMessageList.size() > 0)) {
         for (SelfMessage selfMessage : selfMessageList) {
           newSelfMessage = selfMessage;
           if (isInTime(selfMessage.getUpdateTime(), 5));
           newSelfMessage.setStatus(TrueFalseEnum.FALSE.getCode());
         }
       } else {
         op = OperationTypeEnum.CREATE.getCode();
         newSelfMessage = new SelfMessage(selfOrder, getCurrentUserRestId(), content, tabNO, TrueFalseEnum.FALSE.getCode(), SelfServiceMarkEnum.CALLORDER.getCode());
       }
       newSelfMessage.setUseredHanleStatus(TrueFalseEnum.FALSE.getCode());
       this.selfMessageService.save(newSelfMessage);
 
       doSynchSingleTable(op, newSelfMessage);
 
       this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(tabNO, orderCall, SelfServiceMarkEnum.CALLORDER, content, newSelfMessage.getId(), request));
     }
 
     model.addAttribute("tabNO", tabNO);
     return "redirect:/self/afterOrdered";
   }
 
   @RequestMapping(value={"callService"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone callService(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="content", defaultValue="") String contentFromPost, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     DwzAjaxDone done = new DwzAjaxDone();
     SelfMessage selfMessage = null;
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     String content = "";
     if (selfOrder != null) {
       if (!"".equals(contentFromPost))
         content = new StringBuilder().append(tabNO).append(" 号餐台:").append(contentFromPost).toString();
       else {
         content = new StringBuilder().append(tabNO).append(" 号餐台客人 呼叫 ").append(SelfServiceMarkEnum.getDesc(type)).toString();
       }
 
       List<SelfMessage> selfMessageList = this.selfMessageService.findBySelfOrderAndMesType(selfOrder, type);
 
       if ((selfMessageList != null) && (selfMessageList.size() > 0)) {
         for (SelfMessage selfMessageOld : selfMessageList) {
           if (type.equals(selfMessageOld.getMesType())) {
             selfMessage = selfMessageOld;
           }
 
           if (type.equals(selfMessageOld.getMesType())) {
             if (isInTime(selfMessageOld.getUpdateTime(), 1)) {
               done.setMessage(new StringBuilder().append("已呼叫 ").append(SelfServiceMarkEnum.getDesc(type)).append(" 服务,一分钟内请勿重复呼叫!").toString());
               return done;
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
 
       this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(tabNO, serviceCall, SelfServiceMarkEnum.getEnumByCode(type), content, selfMessage.getId(), request));
       done.setMessage(new StringBuilder().append("呼叫 ").append(SelfServiceMarkEnum.getDesc(type)).append(" 成功").toString());
     }
     else {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
     return done;
   }
 
   @RequestMapping(value={"urgeDish"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone urgeDish(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="content", defaultValue="") String contentFromPost, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     DwzAjaxDone done = new DwzAjaxDone();
     SelfMessage selfMessage = null;
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     String content = "";
     if (selfOrder != null) {
       if (!"".equals(contentFromPost))
         content = new StringBuilder().append(tabNO).append(" 号餐台客人呼叫:").append(contentFromPost).toString();
       else {
         content = new StringBuilder().append(tabNO).append(" 号餐台客人 呼叫 ").append(SelfServiceMarkEnum.getDesc(type)).toString();
       }
 
       if (type.equals(SelfServiceMarkEnum.URGEDISH.getCode().toString())) {
         if (selfOrder.getBillTime() == null) {
           done.setMessage(new StringBuilder().append("未下单，不能呼叫 ").append(SelfServiceMarkEnum.getDesc(type)).append(" 服务!").toString());
           return done;
         }if ((selfOrder.getBillTime() != null) && (isInTime(selfOrder.getBillTime(), 20))) {
           done.setMessage("下单后，二十分钟内请勿呼叫!");
           return done;
         }
       }
       List<SelfMessage> selfMessageList = this.selfMessageService.findBySelfOrderAndMesType(selfOrder, type);
 
       if ((selfMessageList != null) && (selfMessageList.size() > 0)) {
         for (SelfMessage selfMessageOld : selfMessageList) {
           if (type.equals(selfMessageOld.getMesType())) {
             selfMessage = selfMessageOld;
           }
 
           if ((type.equals(SelfServiceMarkEnum.URGEDISH.getCode().toString())) && (TrueFalseEnum.TRUE.getCode().equals(selfOrder.getStatus())) && 
             (isInTime(selfMessageOld.getUpdateTime(), 3))) {
             done.setMessage(new StringBuilder().append("已呼叫 ").append(SelfServiceMarkEnum.getDesc(type)).append(" 服务,三分钟内请勿重复呼叫!").toString());
             return done;
           }
         }
 
       }
 
       MediaUtil.playSound();
       String op = OperationTypeEnum.UPDATE.getCode();
       if (selfMessage == null) {
         op = OperationTypeEnum.CREATE.getCode();
         selfMessage = new SelfMessage(selfOrder, getCurrentUserRestId(), content, tabNO, TrueFalseEnum.FALSE.getCode(), type);
       }
       selfMessage.setStatus(TrueFalseEnum.FALSE.getCode());
       selfMessage.setUseredHanleStatus(TrueFalseEnum.FALSE.getCode());
       this.selfMessageService.save(selfMessage);
 
       doSynchSingleTable(op, selfMessage);
 
       this.messageCenterNativeServer.notifyAllWaiter(buildCallMessage(tabNO, serviceCall, SelfServiceMarkEnum.getEnumByCode(type), content, selfMessage.getId(), request));
       done.setMessage(new StringBuilder().append("呼叫 ").append(SelfServiceMarkEnum.getDesc(type)).append(" 成功").toString());
     }
     else {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
     return done;
   }
 
   private boolean isInTime(Date updateTime, int minutes) {
     Date now = new Date();
     Date newUpdateTime = DateUtils.addMinutes(updateTime, minutes);
     return newUpdateTime.after(now);
   }
 
   @RequestMapping({"afterOrdered"})
   public String afterOrdered(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     String transmit = new StringBuilder().append(" 我在 ").append(getCurrentUser().getRestaurant().getRestName()).append(" 餐厅吃了 ").toString();
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     if (selfOrder != null) {
       List<SelfDish> unOrderedSelfDishList = this.selfdishService.findBySelfOrderAndStatus(selfOrder, TrueFalseEnum.FALSE.getCode());
       for (SelfDish selfDish : unOrderedSelfDishList) {
         transmit = new StringBuilder().append(transmit).append("  ").append(selfDish.getDishesName()).append(",").toString();
       }
       model.addAttribute("dishList", unOrderedSelfDishList);
     }
 
     model.addAttribute("restName", getCurrentUser().getRestaurant().getRestName());
     model.addAttribute("transmit", new StringBuilder().append(transmit).append(" 味道棒极了，小伙伴们快来尝尝吧！我是用手机自助点餐点的菜，用自己的手机点的菜，这个应用很有意思--").append(getCurrentUser().getRestaurant().getRestName()).append(",").append(getCurrentUser().getRestaurant().getAdrDetail() != null ? getCurrentUser().getRestaurant().getAdrDetail() : "").toString());
     model.addAttribute("tabNO", tabNO);
     return "self/afterOrdered";
   }
 
   @RequestMapping(value={"refreshMessage"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone refreshMessage(@RequestParam(value="tabNO", defaultValue="") String tabNO, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     String mesStr = " 此订单已呼叫服务员响应，请耐心等待服务员进行确认！"; String callStr = "";
     DwzAjaxDone done = new DwzAjaxDone();
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     if (selfOrder != null) {
       List<SelfMessage> messageList = selfOrder.getMessageList();
       if ((messageList != null) && (messageList.size() > 0)) {
         for (SelfMessage selfMessage : messageList) {
           if (selfMessage.getStatus().equals(TrueFalseEnum.TRUE.getCode())) {
             mesStr = new StringBuilder().append(mesStr).append(" <br/>").append(selfMessage.getContent()).append(" 已处理 !  ").toString();
           }
         }
       }
       done.setMessage(mesStr);
     }
     else {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
     return done;
   }
 
   @RequestMapping(value={"showCalledMessage"}, produces={"application/json"})
   @ResponseBody
   public DwzAjaxDone showCalledMessage(@RequestParam(value="tabNO", defaultValue="") String tabNO, Model model, HttpServletRequest request, HttpServletResponse response)
   {
     String mesStr = ""; String callStr = "";
     DwzAjaxDone done = new DwzAjaxDone();
     SelfOrder selfOrder = getValidateTableOrder(tabNO);
     if (selfOrder != null) {
       List<SelfMessage> messageList = selfOrder.getMessageList();
       if ((messageList != null) && (messageList.size() > 0)) {
         for (SelfMessage selfMessage : messageList) {
           if ((selfMessage.getStatus().equals(TrueFalseEnum.TRUE.getCode())) && (TrueFalseEnum.FALSE.getCode().equals(selfMessage.getUseredHanleStatus()))) {
             selfMessage.setUseredHanleStatus(TrueFalseEnum.TRUE.getCode());
             this.selfMessageService.save(selfMessage);
 
             doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), selfMessage);
 
             if (selfMessage.getMesType().equals(SelfServiceMarkEnum.CALLORDER.getCode()))
               mesStr = new StringBuilder().append(mesStr).append("<br/>").append(selfMessage.getContent()).append(" 服务已响应，菜品已在烹饪中，请耐心等待").toString();
             else {
               mesStr = new StringBuilder().append(mesStr).append("<br/>").append(selfMessage.getContent()).append(" 服务已响应，请耐心等待").toString();
             }
 
             done.setStatusCode("201");
           }
         }
       }
       done.setMessage(new StringBuilder().append(mesStr).append("\n").append(callStr).toString());
     }
     else {
       done.setStatusCode(StatusCodeEnum.LOGIC_ERROR.getCode());
       done.setMessage("请正确扫码开台后在进行此操作");
     }
     return done;
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
   @RequestMapping(value={"getDishList"}, produces={"text/html;charset=UTF-8"})
   @ResponseBody
   public String getDishList(@RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="page", defaultValue="1") int pageNumber, Model model, HttpServletRequest request) { silenceLogin(request);
     try {
       SelfOrder selfOrder = getValidateTableOrder(tabNO);
       if (selfOrder != null) {
         List<SelfDish> dishList = selfOrder.getDishList();
         if ((dishList != null) && (dishList.size() > 0)) {
           JSONObject billObj = new JSONObject();
           JSONArray dishArr = new JSONArray();
           JSONObject dishObj = new JSONObject();
           billObj.put("notes", selfOrder.getNotes());
           billObj.put("tabNo", selfOrder.getTabNo());
           for (SelfDish dish : dishList) {
             dishObj = new JSONObject();
             dishObj.put("dishesId", dish.getDishesId());
             dishObj.put("dishesName", dish.getDishesName());
             dishObj.put("price", dish.getRealCost());
             dishObj.put("unitNum", dish.getSaleNum());
             dishArr.put(dishObj);
           }
           billObj.put("dishesStr", dishArr.toString());
 
           return billObj.toString();
         }
       }
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 
   @RequestMapping(value={"getSelfCallList"}, produces={"application/json"})
   @ResponseBody
   public List<MessageVO> getSelfCallList(@RequestParam(value="sortType", defaultValue="updateTime_desc") String sortType, @RequestParam(value="tabNO", defaultValue="") String tabNO, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="page", defaultValue="1") int pageNumber, Model model, HttpServletRequest request)
   {
     ArrayList messageList = new ArrayList();
     try {
       Map searchParams = Servlets.getParametersStartingWith(request, "search_");
       Page page = this.selfMessageService.searchPage(searchParams, pageNumber, 1000, sortType);
       List<SelfMessage> SelfMessageList = page.getContent();
       for (SelfMessage selfMessage : SelfMessageList) {
         MessageVO messageVO = new MessageVO();
         messageVO.setSerialNo(selfMessage.getId());
         if (("1".equals(selfMessage.getStatus())) && (selfMessage.getMesType() == SelfServiceMarkEnum.CALLORDER.getCode()))
           messageVO.setType(3);
         else {
           messageVO.setType(4);
         }
         messageVO.setTime(selfMessage.getCreateTime().toLocaleString());
         messageVO.setStatus(selfMessage.getStatus());
         messageVO.setFrom(selfMessage.getTabNo());
         messageVO.setContent(selfMessage.getContent());
         messageList.add(messageVO);
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
     return messageList;
   }
 
   @RequestMapping(value={"{auto}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String auto(@PathVariable String auto, @RequestParam(value="tn", defaultValue="") String tn, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     return new StringBuilder().append("self/").append(auto).toString();
   }
 
   private Map<String, Map<String, Dishe>> loadTableFromApp(HttpServletRequest request)
   {
     ServletContext appContext = request.getSession().getServletContext();
     Map tablesMap = (Map)appContext.getAttribute("tablesMap");
     return tablesMap;
   }
 }

