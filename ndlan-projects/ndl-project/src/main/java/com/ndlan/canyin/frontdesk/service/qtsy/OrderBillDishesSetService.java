 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishe;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.ylgl.DishesRaw;
import com.ndlan.canyin.base.entity.ylgl.RawMaterial;
import com.ndlan.canyin.base.repository.cygl.DisheDao;
import com.ndlan.canyin.base.repository.cygl.DishesSetDao;
import com.ndlan.canyin.base.repository.qtsy.OrderBillDishesSetDao;
import com.ndlan.canyin.base.repository.ylgl.DishesRawDao;
import com.ndlan.canyin.base.repository.ylgl.RawMaterialDao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class OrderBillDishesSetService extends BaseService<OrderBillDishesSetDao, OrderBillDishesSet>
 {
   private OrderBillDishesSetDao orderBillDishesSetDao;
 
   @Autowired
   DishesRawDao dishesRawDao;
 
   @Autowired
   RawMaterialDao rawMaterialDao;
 
   @Autowired
   DisheDao disheDao;
 
   @Autowired
   DishesSetDao dishesSetDao;
 
   public List<OrderBillDishesSet> findByRestIdAndTableOrder(String restId, TableOrder tableOrder)
   {
     return this.orderBillDishesSetDao.findByRestIdAndTableOrder(restId, tableOrder);
   }
   @Autowired
   public void setDao(OrderBillDishesSetDao dao) { this.orderBillDishesSetDao = dao;
     super.setDao(dao);
   }
 
   public Map<String, String> scheduleStockCheck(String restId, List<OrderBillDishe> orderBillDishes, List<OrderBillDishesSet> orderBillDishesSets)
     throws Exception
   {
     Map messageMap = new HashMap();
     Map<String,Object> dishesMaps = new HashMap();
     if ((orderBillDishes != null) && (orderBillDishes.size() > 0)) {
       for (OrderBillDishe orderBillDishe : orderBillDishes) {
         Dishe dishe = orderBillDishe.getDishe();
         Float unitNum = Float.valueOf(orderBillDishe.getUnitNum());
         String dishesId = dishe.getDishesId();
         if (dishesMaps.containsKey(dishesId))
         {
           Float num = (Float)dishesMaps.get(dishesId);
           num = Float.valueOf(num.floatValue() + unitNum.floatValue());
           dishesMaps.put(dishesId, num);
         }
         else {
           dishesMaps.put(dishesId, unitNum);
         }
       }
     }
 
     for (String dishesId : dishesMaps.keySet()) {
       Float unitNum = (Float)dishesMaps.get(dishesId);
       Dishe dishe = (Dishe)this.disheDao.findOne(dishesId);
       if ((dishe.getEstimate() != null) && (
         (dishe.getEstimate().floatValue() == 0.0F) || (dishe.getEstimate().floatValue() < unitNum.floatValue())))
       {
         messageMap.put("result", "4");
         messageMap.put("message", dishe.getDishesName() + "已经沽清");
         return messageMap;
       }
 
     }
 
     Map<String,Object> dishesSetUnitNumMaps = new HashMap();
     if ((orderBillDishesSets != null) && (orderBillDishesSets.size() > 0))
       for (OrderBillDishesSet orderBillDishesSet : orderBillDishesSets) {
         DishesSet dishe = orderBillDishesSet.getDishesSet();
         Float dishesSetNum = Float.valueOf(orderBillDishesSet.getUnitNum());
         String dsId = dishe.getDsId();
 
         if (dishesSetUnitNumMaps.containsKey(dsId))
         {
           Float num = (Float)dishesSetUnitNumMaps.get(dsId);
           num = Float.valueOf(num.floatValue() + dishesSetNum.floatValue());
           dishesSetUnitNumMaps.put(dsId, num);
         }
         else {
           dishesSetUnitNumMaps.put(dsId, dishesSetNum);
         }
 
         String dsDishesDesc = orderBillDishesSet.getDsDishesDesc();
         ObjectMapper mapper = new ObjectMapper();
         List<LinkedHashMap> dishesList = (List)mapper.readValue(dsDishesDesc, List.class);
         for (LinkedHashMap linkedHashMap : dishesList) {
           String dishesId = (String)linkedHashMap.get("dishesId");
           float unitNum = Float.valueOf((String)linkedHashMap.get("unitNum")).floatValue() * dishesSetNum.floatValue();
           if (dishesMaps.containsKey(dishesId)) {
             Float num = (Float)dishesMaps.get(dishesId);
             num = Float.valueOf(num.floatValue() + unitNum);
             dishesMaps.put(dishesId, num);
           } else {
             dishesMaps.put(dishesId, Float.valueOf(unitNum));
           }
         }
       }
     Float dishesSetNum;
     for (String dsId : dishesSetUnitNumMaps.keySet()) {
       Float unitNum = (Float)dishesSetUnitNumMaps.get(dsId);
       DishesSet disheSet = (DishesSet)this.dishesSetDao.findOne(dsId);
       if ((disheSet.getEstimate() != null) && (
         (disheSet.getEstimate().floatValue() == 0.0F) || (disheSet.getEstimate().floatValue() < unitNum.floatValue())))
       {
         messageMap.put("result", "4");
         messageMap.put("message", disheSet.getDsName() + "已经沽清");
         return messageMap;
       }
 
     }
 
     Map<String,Object> dishesRawMaps = new HashMap();
 
     for (String dishesId : dishesMaps.keySet()) {
       Float dishesNum = (Float)dishesMaps.get(dishesId);
       List<DishesRaw> dishesRaws = this.dishesRawDao.getByRestIdAndDishesId(restId, dishesId);
       for (DishesRaw dishesRaw : dishesRaws) {
         String rmId = dishesRaw.getRawMaterial().getRmId();
         Float useCounts = Float.valueOf(dishesRaw.getUseCount().floatValue() * dishesNum.floatValue());
         if (dishesRawMaps.containsKey(rmId)) {
           Float counts = (Float)dishesRawMaps.get(rmId);
           counts = Float.valueOf(counts.floatValue() + useCounts.floatValue());
           dishesRawMaps.put(rmId, counts);
         } else {
           dishesRawMaps.put(rmId, useCounts);
         }
       }
     }
     Float dishesNum;
     StringBuffer sb0 = new StringBuffer();
     StringBuffer sb1 = new StringBuffer();
 
     for (String rmId : dishesRawMaps.keySet()) {
       Float useCounts = (Float)dishesRawMaps.get(rmId);
       RawMaterial rawMaterial = (RawMaterial)this.rawMaterialDao.findOne(rmId);
       Float stockCount = Float.valueOf(rawMaterial.getStockCount().floatValue() - useCounts.floatValue());
       if (stockCount.floatValue() < 0.0F) {
         if (!sb0.toString().contains(rawMaterial.getRmName()))
           sb0.append(rawMaterial.getRmName() + ",");
       }
       else if ((stockCount.floatValue() <= rawMaterial.getWarnCount().floatValue()) && 
         (!sb1.toString().contains(rawMaterial.getRmName()))) {
         sb1.append(rawMaterial.getRmName() + ",");
       }
 
     }
 
     if (!StringUtils.isEmpty(sb0)) {
       messageMap.put("result", "0");
       messageMap.put("message", sb0.substring(0, sb0.length() - 1) + "原料库存不足");
       return messageMap;
     }
 
     if (!StringUtils.isEmpty(sb1)) {
       messageMap.put("result", "1");
       messageMap.put("message", sb1.substring(0, sb1.length() - 1) + "原料库存预警，请及时补充原料");
       return messageMap;
     }
 
     messageMap.put("result", "2");
     return messageMap;
   }
 }

