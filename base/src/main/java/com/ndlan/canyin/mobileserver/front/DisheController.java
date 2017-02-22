 package com.ndlan.canyin.mobileserver.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.MobileRspResult;
import com.ndlan.canyin.frontdesk.service.cygl.DisheService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesMaterialService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesPicService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetCategoryService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetDishesService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesSetService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesStyleService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesTasteService;
import com.ndlan.canyin.frontdesk.service.cygl.DishesUnitService;
import com.ndlan.canyin.frontdesk.service.cygl.SpecialDisheServie;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillSeqService;
import com.ndlan.canyin.frontdesk.service.qtsy.DishesSetDishesReplaceService;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesMaterial;
import com.ndlan.canyin.base.entity.cygl.DishesPic;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishesReplace;
import com.ndlan.canyin.base.entity.cygl.DishesSetPic;
import com.ndlan.canyin.base.entity.cygl.DishesStyle;
import com.ndlan.canyin.base.entity.cygl.DishesTaste;
import com.ndlan.canyin.base.entity.cygl.DishesUnit;
import com.ndlan.canyin.base.entity.cygl.SpecialDishe;
import com.ndlan.canyin.base.entity.cygl.SpecialPriceInterval;
import com.ndlan.canyin.core.common.DishesTypeEnum;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.PungentLevelEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.DateProvider;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 /**
  * 菜肴管理
  * @author zhengjiansong
  *
  */
 @Controller
 @RequestMapping({"/mobile/dishe"})
 @Lazy
 public class DisheController extends BaseManageController
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
   DishesMaterialService dishesMaterialService;
 
   @Autowired
   DishesSetService dishessetService;
 
   @Autowired
   DishesPicService dishesPicService;
 
   @Autowired
   private DishesSetService dishesSetService;
 
   @Autowired
   DinerBillSeqService dinerBillSeqService;
 
   @Autowired
   DishesSetCategoryService dishesSetCategoryService;
 
   @Autowired
   DishesSetDishesReplaceService dishesSetDishesReplaceService;
 
   @Autowired
   DishesSetDishesService dishesSetDishesService;
 
   @Autowired
   SpecialDisheServie specialDisheService;
 
   @Autowired
   DishesStyleService dishesStyleService;
 /**
  * 获取菜肴分类
  * @param pageNumber
  * @param sortType
  * @param direction
  * @return
  */
   @RequestMapping(value={"getAllDisheCategorys"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult getAllDisheCategorys(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="showSeq") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction)
   {
     List list = this.dishesCategoryService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     List newlist = new ArrayList(list);
     List<DishesSetCategory> dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusOrderByShowSeqAsc(getCurrentUserRestId());
     for (DishesSetCategory dsc : dishesSetCategorys) {
       DishesCategory d = new DishesCategory();
       BeanUtils.copyProperties(dsc, d);
       d.setCategoryId(dsc.getDsCategoryId());
       newlist.add(d);
     }
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "获取菜类成功", newlist); } 
   /**
    * 校验菜肴变更
    * @param clientLastUpdateTimeStr
    * @return
    */
   @RequestMapping(value={"checkDishChange"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult checkDishChange(@RequestParam(value="clientLastUpdateTimeStr", defaultValue="") String clientLastUpdateTimeStr) { if (StringUtils.isEmpty(clientLastUpdateTimeStr))
     {
       clientLastUpdateTimeStr = String.valueOf(com.ndlan.canyin.core.utils.DateUtil.toDate("1970-01-01 00:00:00").getTime());
     }
     long clientLastUpdateTimeLong = Long.valueOf(clientLastUpdateTimeStr).longValue();
 
     String isUpdateDish = TrueFalseEnum.FALSE.getCode();
 
     long dishLastTime = DateProvider.DEFAULT.getDate().getTime();
     List<DishesCategory> list = this.dishesCategoryService.findByRestIdAndEnableStatus(getCurrentUserRestId());
     Date updateTime;
     for (DishesCategory each : list) {
       updateTime = each.getUpdateTime();
       long updateTimeLong = updateTime.getTime();
       if (clientLastUpdateTimeLong >= updateTimeLong)
         continue;
       isUpdateDish = TrueFalseEnum.TRUE.getCode();
       dishLastTime = updateTimeLong;
       break;
     }
 
     if (TrueFalseEnum.FALSE.getCode().equals(isUpdateDish))
     {
       List<DishesSetCategory> dishesSetCategorys = this.dishesSetCategoryService.findByRestIdAndEnableStatusOrderByShowSeqAsc(getCurrentUserRestId());
       for (DishesSetCategory each : dishesSetCategorys) {
         updateTime = each.getUpdateTime();
         long updateTimeLong = updateTime.getTime();
         if (clientLastUpdateTimeLong >= updateTimeLong)
           continue;
         isUpdateDish = TrueFalseEnum.TRUE.getCode();
         dishLastTime = updateTimeLong;
         break;
       }
 
     }
 
     if (TrueFalseEnum.FALSE.getCode().equals(isUpdateDish))
     {
       List<Dishe> dishes = this.disheService.findAllNotStopSell(getCurrentUserRestId());
       for (Dishe each : dishes)
       {
         updateTime = each.getUpdateTime();
         long updateTimeLong = updateTime.getTime();
         if (clientLastUpdateTimeLong >= updateTimeLong)
           continue;
         isUpdateDish = TrueFalseEnum.TRUE.getCode();
         dishLastTime = updateTimeLong;
         break;
       }
 
     }
 
     List<DishesSet> dishesSetList = this.dishesSetService.findByRestIdOrderByShowSeqAsc(getCurrentUserRestId());
//     Date updateTime;
     for (DishesSet each : dishesSetList)
     {
       updateTime = each.getUpdateTime();
       long updateTimeLong = updateTime.getTime();
       if (clientLastUpdateTimeLong >= updateTimeLong)
         continue;
       isUpdateDish = TrueFalseEnum.TRUE.getCode();
       dishLastTime = updateTimeLong;
       break;
     }
 
     List<SpecialDishe> specialDishes = this.specialDisheService.getAllSpecialDisheByRestId(getCurrentUserRestId());
     for (SpecialDishe each : specialDishes)
     {
       updateTime = each.getUpdateTime();
       long updateTimeLong = updateTime.getTime();
       if (clientLastUpdateTimeLong >= updateTimeLong)
         continue;
       isUpdateDish = TrueFalseEnum.TRUE.getCode();
       dishLastTime = updateTimeLong;
       break;
     }
 
     Map result = new HashMap();
     result.put("isUpdateDish", isUpdateDish);
     result.put("clientLastUpdateTimeStr", String.valueOf(dishLastTime));
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "检查菜肴是否变化成功", result); } 
   /**
    * 获取所有菜肴
    * @param onlyShownInRest
    * @return
    */
   @ResponseBody
   @RequestMapping(value={"getAllDishes"}, produces={"text/plain;charset=UTF-8"})
   public String getAllDishes(@RequestParam(value="onlyShownInRest", defaultValue="") String onlyShownInRest) {
     JSONArray resultObj = new JSONArray();
     JSONObject obj = null;
     String restId = getCurrentUserRestId();
     List<Dishe> dishes = this.disheService.findAllNotStopSell(restId);
     List<DishesSet> dishesSetList = this.dishesSetService.findByRestIdOrderByShowSeqAsc(restId);
     List specialDishes = this.specialDisheService.getAllSpecialDisheByRestId(restId);
     List<DishesTaste> dishesTastes;
     String dishesMaterialStr;
     String dishesMaterialId;
     if (dishes.size() > 0) {
       for (Dishe dish : dishes) {
         if ((TrueFalseEnum.TRUE.getCode().equals(onlyShownInRest)) && (TrueFalseEnum.TRUE.getCode().equals(dish.getIsInRestUse()))) {
           continue;
         }
         obj = new JSONObject();
         try {
           obj.put("isSet", "false");
           obj.put("categoryId", dish.getDishesCategory() != null ? dish.getDishesCategory().getCategoryId() : "");
           obj.put("dishesId", dish.getDishesId());
           obj.put("price", dish.getPrice());
           obj.put("dishesName", dish.getDishesName());
           obj.put("dishesCode", dish.getDishesCode());
           obj.put("saleNum", dish.getSaleNum());
           obj.put("unitName", dish.getDishesUnit().getName());
           obj.put("estimate", dish.getEstimate());
           if ((dish.getIsRecommend() == null) || (dish.getIsRecommend().equals("0")))
             obj.put("isRecommend", "null");
           else {
             obj.put("isRecommend", "1");
           }
           obj.put("RecommendDesc", dish.getIsRecommendDesc());
           if ((dish.getIsSpecialty() == null) || (dish.getIsSpecialty().equals("0")))
             obj.put("isSpecialty", "null");
           else {
             obj.put("isSpecialty", "1");
           }
           obj.put("SpecialtyDesc", dish.getIsSpecialtyDesc());
           obj.put("memberPrice", dish.getMemberPrice());
 
           JSONArray specialArrays = new JSONArray();
           for (int i = 0; i < specialDishes.size(); i++) {
             SpecialDishe spDish = (SpecialDishe)specialDishes.get(i);
             if ((spDish.getDishe() != null) && (dish.getDishesId().equals(spDish.getDishe().getDishesId()))) {
               JSONObject specialObject = new JSONObject();
               specialObject.put("specialPrice", spDish.getSpecialPrice());
               SpecialPriceInterval spDishInterval = spDish.getSpecialPriceInterval();
               specialObject.put("spiName", spDishInterval.getName());
               if (TrueFalseEnum.TRUE.getCode().equals(spDishInterval.getIsTimeLimit())) {
                 String spTime = "";
                 if (TrueFalseEnum.TRUE.getCode().equals(spDishInterval.getIsTimeValid())) {
                   String startTime = spDishInterval.getStartTime();
                   String endTime = spDishInterval.getEndTime();
                   spTime = startTime + "-" + endTime;
                 }
                 specialObject.put("spTime", spTime);
                 String spDateStr = "";
                 if (TrueFalseEnum.TRUE.getCode().equals(spDishInterval.getIsDateValid())) {
                   Date startDate = spDishInterval.getStartDate();
                   Date endDate = spDishInterval.getEndDate();
                   spDateStr = startDate + "至" + endDate;
                 }
                 if (!spDateStr.equals("")) {
                   spDateStr = spDateStr + "|";
                 }
                 if ((spDishInterval.getIsMonday() != null) && (spDishInterval.getIsMonday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周一/";
                 }
                 if ((spDishInterval.getIsTuesday() != null) && (spDishInterval.getIsTuesday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周二/";
                 }
                 if ((spDishInterval.getIsWednesday() != null) && (spDishInterval.getIsWednesday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周三/";
                 }
                 if ((spDishInterval.getIsThursday() != null) && (spDishInterval.getIsThursday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周四/";
                 }
                 if ((spDishInterval.getIsFriday() != null) && (spDishInterval.getIsFriday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周五/";
                 }
                 if ((spDishInterval.getIsSaturday() != null) && (spDishInterval.getIsSaturday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周六/";
                 }
                 if ((spDishInterval.getIsSunday() != null) && (spDishInterval.getIsSunday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周天/";
                 }
                 specialObject.put("spDateStr", spDateStr);
               }
 
               specialArrays.put(specialObject);
             }
           }
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String creatTimeStr = format.format(new Date(System.currentTimeMillis()));
           MobileRspResult r = judgeSpecialPrice(dish.getDishesId(), DishesTypeEnum.DISHES.getCode(), creatTimeStr);
           BigDecimal specialPrice = null;
           if (r.getObject() != null) {
             specialPrice = (BigDecimal)r.getObject();
           }
           obj.put("specialPrice", specialPrice);
           obj.put("specialDishes", specialArrays);
 
           List<DishesPic> pics = dish.getDishesPics();
           JSONArray picArrays = new JSONArray();
           JSONObject picObject = null;
           for (DishesPic pic : pics) {
             picObject = new JSONObject();
             picObject.put("picId", pic.getPicId());
             picObject.put("picType", pic.getPicType());
             picObject.put("piclowUrl", pic.getPicUrl200x200());
             picObject.put("picUrl", pic.getPicUrl1024x1024());
             picObject.put("restId", pic.getRestId());
             picObject.put("showSeq", pic.getShowSeq());
             picArrays.put(picObject);
           }
           obj.put("disImgs", picArrays);
 
           String dishesTasteStr = "";
           dishesTastes = this.dishesTasteService.findAllDishesTasteByRestId(restId);
           String dishesTasteId;
           for (DishesTaste dishesTaste : dishesTastes) {
             List tasteIdList = dish.getTasteIdList();
             if (tasteIdList == null)
               continue;
             for (Iterator localIterator4 = tasteIdList.iterator(); localIterator4.hasNext(); ) { dishesTasteId = (String)localIterator4.next();
               if (dishesTasteId.equals(dishesTaste.getTasteId())) {
                 dishesTasteStr = dishesTasteStr + " " + dishesTaste.getName();
               }
             }
           }
 
           obj.put("dishesTasteStr", dishesTasteStr);
 
           obj.put("pungentLevel", PungentLevelEnum.getDesc(String.valueOf(dish.getPungentLevel())));
 
           obj.put("isStopSell", dish.getIsOnsale());
 
           obj.put("isRulingPrice", dish.getIsRulingPrice());
 
           String dishesStyleStr = "";
           List<DishesStyle> dishesStyles = this.dishesStyleService.findAllDishesStyleByRestId(restId);
           String dishesStyleId;
           for (DishesStyle dishesStyle : dishesStyles) {
             List styleIdList = dish.getDashesStyleIdList();
             if (styleIdList == null)
               continue;
             for (Iterator localIterator5 = styleIdList.iterator(); localIterator5.hasNext(); ) { dishesStyleId = (String)localIterator5.next();
               if (dishesStyleId.equals(dishesStyle.getDashesStyleId())) {
                 dishesStyleStr = dishesStyleStr + " " + dishesStyle.getName();
               }
             }
           }
 
           obj.put("dishesStyleStr", dishesStyleStr);
 
           dishesMaterialStr = "";
           List<DishesMaterial> dishesMaterials = this.dishesMaterialService.findAllDishesMaterialByRestId(restId);
           for (DishesMaterial dishesMaterial : dishesMaterials) {
             List materialIdList = dish.getMaterialIdList();
             if (materialIdList == null)
               continue;
             for (Iterator localIterator6 = materialIdList.iterator(); localIterator6.hasNext(); ) { dishesMaterialId = (String)localIterator6.next();
               if (dishesMaterialId.equals(dishesMaterial.getMaterialId())) {
                 dishesMaterialStr = dishesMaterialStr + " " + dishesMaterial.getName();
               }
             }
           }
 
           obj.put("dishesMaterialStr", dishesMaterialStr);
 
           obj.put("notes", dish.getNotes());
 
           obj.put("estimate", dish.getEstimate());
 
           resultObj.put(obj);
         } catch (JSONException e) {
           e.printStackTrace();
         }
       }
     }
     if (dishesSetList.size() > 0) {
       for (DishesSet dish : dishesSetList) {
         if ((TrueFalseEnum.TRUE.getCode().equals(onlyShownInRest)) && (TrueFalseEnum.TRUE.getCode().equals(dish.getIsInRestUse()))) {
           continue;
         }
         obj = new JSONObject();
         try {
           obj.put("isSet", "true");
           obj.put("categoryId", dish.getDishesSetCategory() != null ? dish.getDishesSetCategory().getDsCategoryId() : "");
           obj.put("dishesId", dish.getDsId());
           obj.put("price", dish.getPrice());
           obj.put("dishesName", dish.getDsName());
           obj.put("dishesCode", dish.getDsCode());
           obj.put("saleNum", dish.getSaleNum());
           obj.put("unitName", dish.getDishesUnit().getName());
           obj.put("estimate", dish.getEstimate());
           if ((dish.getIsRecommend() == null) || (dish.getIsRecommend().equals("0")))
             obj.put("isRecommend", "null");
           else {
             obj.put("isRecommend", "1");
           }
           obj.put("RecommendDesc", dish.getIsRecommendDesc());
           if ((dish.getIsSpecialty() == null) || (dish.getIsSpecialty().equals("0")))
             obj.put("isSpecialty", "null");
           else {
             obj.put("isSpecialty", "1");
           }
           obj.put("SpecialtyDesc", dish.getIsSpecialtyDesc());
           obj.put("memberPrice", dish.getMemberPrice());
 
           JSONArray specialArrays = new JSONArray();
           for (int i = 0; i < specialDishes.size(); i++) {
             SpecialDishe spDish = (SpecialDishe)specialDishes.get(i);
             if ((spDish.getDishesSet() != null) && (dish.getDsId().equals(spDish.getDishesSet().getDsId()))) {
               JSONObject specialObject = new JSONObject();
               specialObject.put("specialPrice", spDish.getSpecialPrice());
               SpecialPriceInterval spDishInterval = spDish.getSpecialPriceInterval();
               specialObject.put("spiName", spDishInterval.getName());
               if (TrueFalseEnum.TRUE.getCode().equals(spDishInterval.getIsTimeLimit())) {
                 String spTime = "";
                 if (TrueFalseEnum.TRUE.getCode().equals(spDishInterval.getIsTimeValid())) {
                   String startTime = spDishInterval.getStartTime();
                   String endTime = spDishInterval.getEndTime();
                   spTime = startTime + "-" + endTime;
                 }
                 specialObject.put("spTime", spTime);
                 String spDateStr = "";
                 if (TrueFalseEnum.TRUE.getCode().equals(spDishInterval.getIsDateValid())) {
                   Date startDate = spDishInterval.getStartDate();
                   Date endDate = spDishInterval.getEndDate();
                   spDateStr = startDate + "至" + endDate;
                 }
 
                 if (!spDateStr.equals("")) {
                   spDateStr = spDateStr + "|";
                 }
                 if ((spDishInterval.getIsMonday() != null) && (spDishInterval.getIsMonday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周一/";
                 }
                 if ((spDishInterval.getIsTuesday() != null) && (spDishInterval.getIsTuesday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周二/";
                 }
                 if ((spDishInterval.getIsWednesday() != null) && (spDishInterval.getIsWednesday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周三/";
                 }
                 if ((spDishInterval.getIsThursday() != null) && (spDishInterval.getIsThursday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周四/";
                 }
                 if ((spDishInterval.getIsFriday() != null) && (spDishInterval.getIsFriday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周五/";
                 }
                 if ((spDishInterval.getIsSaturday() != null) && (spDishInterval.getIsSaturday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周六/";
                 }
                 if ((spDishInterval.getIsSunday() != null) && (spDishInterval.getIsSunday().equals(TrueFalseEnum.TRUE.getCode()))) {
                   spDateStr = spDateStr + "周天/";
                 }
                 specialObject.put("spDateStr", spDateStr);
               }
 
               specialArrays.put(specialObject);
             }
           }
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String creatTimeStr = format.format(new Date(System.currentTimeMillis()));
           MobileRspResult r = judgeSpecialPrice(dish.getDsId(), DishesTypeEnum.DISHES_SET.getCode(), creatTimeStr);
           BigDecimal specialPrice = null;
           if (r.getObject() != null) {
             specialPrice = (BigDecimal)r.getObject();
           }
           obj.put("specialPrice", specialPrice);
           obj.put("specialDishes", specialArrays);
 
           List pics = dish.getDishesSetPic();
           JSONArray picArrays = new JSONArray();
           JSONObject picObject = null;
           for (dishesTastes = (List<DishesTaste>) pics.iterator(); ((Iterator)dishesTastes).hasNext(); ) { DishesSetPic pic = (DishesSetPic)((Iterator)dishesTastes).next();
             picObject = new JSONObject();
             picObject.put("picId", pic.getPicId());
             picObject.put("picType", pic.getPicType());
             picObject.put("piclowUrl", pic.getPicUrl200x200());
             picObject.put("picUrl", pic.getPicUrl1024x1024());
             picObject.put("restId", pic.getRestId());
             picObject.put("showSeq", pic.getShowSeq());
             picArrays.put(picObject);
           }
           obj.put("disImgs", picArrays);
 
           List<DishesSetDishes> dsDishes = dish.getDishesSetDishes();
           JSONArray dsDishesArray = new JSONArray();
           JSONObject dsDishObject = null;
           for (DishesSetDishes dsDish : dsDishes) {
             dsDishObject = new JSONObject();
             dsDishObject.put("dsDishesId", dsDish.getDsDishesId());
             dsDishObject.put("mr_dishesId", dsDish.getDishe().getDishesId());
             dsDishObject.put("mr_dishesName", dsDish.getDishesName());
             dsDishObject.put("mr_dishesCode", dsDish.getDishe().getDishesCode());
             dsDishObject.put("mr_unitNum", dsDish.getUnitNum());
             dsDishObject.put("mr_unitName", dsDish.getUnitName());
 
             dsDishObject.put("dishesId", dsDish.getDishe().getDishesId());
             dsDishObject.put("dishesName", dsDish.getDishesName());
             dsDishObject.put("dishesCode", dsDish.getDishe().getDishesCode());
             dsDishObject.put("unitNum", dsDish.getUnitNum());
             dsDishObject.put("unitName", dsDish.getUnitName());
             JSONArray dsReplaceArray = new JSONArray();
             JSONObject dsReplaceObject = null;
             List<DishesSetDishesReplace> dsReplaces = dsDish.getDishesSetDishesReplaces();
             for (DishesSetDishesReplace dsReplace : dsReplaces) {
               dsReplaceObject = new JSONObject();
               dsReplaceObject.put("dishesId", dsReplace.getReplaceDishe().getDishesId());
               dsReplaceObject.put("dishesName", dsReplace.getDishesName());
               dsReplaceObject.put("dishesCode", dsReplace.getReplaceDishe().getDishesCode());
               dsReplaceObject.put("dsUnitNum", dsReplace.getUnitNum());
               dsReplaceObject.put("dishesUnitName", dsReplace.getUnitName());
 
               dsReplaceArray.put(dsReplaceObject);
             }
             dsDishObject.put("dishesSetDishesReplaces", dsReplaceArray);
             dsDishesArray.put(dsDishObject);
           }
           obj.put("dsDishes", dsDishesArray);
 
           obj.put("isStopSell", dish.getIsOnsale());
 
           obj.put("estimate", dish.getEstimate());
 
           resultObj.put(obj);
         }
         catch (JSONException e) {
           e.printStackTrace();
         }
       }
     }
     return (String)resultObj.toString();
   }
 /**
  * 判断是否特价菜
  * @param disheId
  * @param dishesType
  * @param creatTimeStr
  * @return
  */
   @RequestMapping(value={"judgeSpecialPrice"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult judgeSpecialPrice(@RequestParam(value="disheId", required=true) String disheId, @RequestParam(value="dishesType", required=true) String dishesType, @RequestParam(value="creatTimeStr", required=true) String creatTimeStr)
   {
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date creatTime = null;
     BigDecimal specialPrice = null;
     int firstEquel = 0;
     try {
       creatTime = format.parse(creatTimeStr);
     } catch (ParseException e1) {
       e1.printStackTrace();
     }
     List<SpecialDishe> specialDishes = null;
     if (dishesType.equals(DishesTypeEnum.DISHES.getCode())) {
       Dishe dishe = (Dishe)this.disheService.getOne(disheId);
       specialDishes = this.specialDisheService.findByDishe(dishe, getCurrentUserRestId());
     } else if (dishesType.equals(DishesTypeEnum.DISHES_SET.getCode())) {
       DishesSet dishesSet = (DishesSet)this.dishessetService.getOne(disheId);
       specialDishes = this.specialDisheService.findByDishesSetAndRestId(dishesSet, getCurrentUserRestId());
     }
 
     if ((specialDishes == null) || (specialDishes.size() == 0))
     {
       return new MobileRspResult(StatusCodeEnum.ERROR.getCode(), "此菜肴不是特价菜肴", null);
     }
     if (creatTime == null) {
       creatTime = new Date();
     }
     for (SpecialDishe specialDishe : specialDishes) {
       SpecialPriceInterval spi = specialDishe.getSpecialPriceInterval();
       if ((spi != null) && 
         (TrueFalseEnum.TRUE.getCode().equals(spi.getIsTimeLimit()))) {
         if ((TrueFalseEnum.TRUE.getCode().equals(spi.getIsDateValid())) && (
           (spi.getStartDate().getTime() > creatTime.getTime()) || (spi.getEndDate().getTime() + 86400000L < creatTime.getTime())))
         {
           continue;
         }
 
         if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsSpecialDay())) {
           if (((spi.getIsMonday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsMonday()))) && (com.ndlan.canyin.frontdesk.util.DateUtil.getWeekDay(creatTime) == 1))
             continue;
           if (((spi.getIsTuesday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsTuesday()))) && (com.ndlan.canyin.frontdesk.util.DateUtil.getWeekDay(creatTime) == 2))
             continue;
           if (((spi.getIsWednesday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsWednesday()))) && (com.ndlan.canyin.frontdesk.util.DateUtil.getWeekDay(creatTime) == 3))
             continue;
           if (((spi.getIsThursday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsThursday()))) && (com.ndlan.canyin.frontdesk.util.DateUtil.getWeekDay(creatTime) == 4))
             continue;
           if (((spi.getIsFriday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsFriday()))) && (com.ndlan.canyin.frontdesk.util.DateUtil.getWeekDay(creatTime) == 5))
             continue;
           if (((spi.getIsSaturday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsSaturday()))) && (com.ndlan.canyin.frontdesk.util.DateUtil.getWeekDay(creatTime) == 6))
             continue;
           if (((spi.getIsSunday() == null) || (TrueFalseEnum.FALSE.getCode().equals(spi.getIsSunday()))) && (com.ndlan.canyin.frontdesk.util.DateUtil.getWeekDay(creatTime) == 0)) {
             continue;
           }
         }
         if (TrueFalseEnum.TRUE.getCode().equals(spi.getIsTimeValid()))
         {
           String ymd = new SimpleDateFormat("yyyy-MM-dd ").format(creatTime);
           DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           try
           {
             Date stime = df.parse(ymd + spi.getStartTime());
             Date etime = df.parse(ymd + spi.getEndTime());
             if ((stime.getTime() > creatTime.getTime()) || (etime.getTime() < creatTime.getTime()))
             {
               continue;
             }
   
           }
           catch (ParseException e)
           {
//             Date etime;
             e.printStackTrace();
             continue;
           }
//           Date etime;
//           Date stime;

         }
 
       }
 
       firstEquel++;
       if (firstEquel == 1) {
         specialPrice = specialDishe.getSpecialPrice();
       }
 
       if (specialPrice.compareTo(specialDishe.getSpecialPrice()) == 1) {
         specialPrice = specialDishe.getSpecialPrice();
       }
     }
 
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "判断是否在特价时段内完毕", specialPrice);
   }
 /**
  * 更改时价价格
  * @param dishId
  * @param rulingPrice
  * @return
  */
   @RequestMapping(value={"updateRulingPrice"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult updateRulingPrice(@RequestParam(value="dishId", required=true) String dishId, @RequestParam(value="rulingPrice", required=true) BigDecimal rulingPrice) {
     Dishe dishe = (Dishe)this.disheService.getOne(dishId);
     dishe.setPrice(rulingPrice);
     this.disheService.save(dishe);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), dishe);
 
     return new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "更改时价价格成功", null);
   }
 }

