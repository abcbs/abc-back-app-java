 package com.ndlan.canyin.frontdesk.service.cloud;
 
 import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.synch.TransactionService;
import com.ndlan.canyin.frontdesk.service.synch.TransferCarrierService;
import com.ndlan.canyin.frontdesk.util.CommunicationUtil;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.synch.Transaction;
import com.ndlan.canyin.base.entity.synch.TransferCarrier;
import com.ndlan.canyin.base.repository.qtsy.DinerBillDao;
import com.ndlan.canyin.base.repository.synch.DataPackage;
import com.ndlan.canyin.core.common.Constants;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.SynDatabaseStatusEnum;
import com.ndlan.canyin.core.common.SynResultStatusEnum;
import com.ndlan.canyin.core.common.TakeoutOrderDivEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.BeanUtils;
import com.ndlan.canyin.core.utils.DateProvider;
import com.ndlan.canyin.core.utils.DateUtil;
import com.ndlan.canyin.core.utils.EnumUtil;
import com.ndlan.canyin.core.vo.OrderAndTakeoutVo;
import com.ndlan.canyin.core.vo.PageVo;
import com.ndlan.canyin.sharelogic.queue.Basket;
import com.ndlan.canyin.sharelogic.util.PriorityExecutor;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
 
 @Component
 @Transactional(readOnly=true)
 public class CloudBillService extends BaseService<DinerBillDao, DinerBill>
 {
 
   @Autowired
   RestaurantService restaurantService;
 
   @Autowired
   TransactionService transactionService;
 
   @Autowired
   TransferCarrierService transferCarrierService;
   private static Map<String, Map<String, Object>> userTakeoutDetailCache = new HashMap();
   private static Map<String, List<Map<String, Object>>> userTakeoutDetailDishCache = new HashMap();
 
   private static Map<String, Map<String, Object>> userOrderDetailCache = new HashMap();
   private static Map<String, List<Map<String, Object>>> userOrderDetailDishCache = new HashMap();
 
   private static byte[] lock = new byte[0];
 
   public DwzAjaxDone loginCloud(String restId, String cloudUsername, String cloudPassword)
   {
     ObjectMapper objectMapper = new ObjectMapper();
     DwzAjaxDone p = new DwzAjaxDone();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/loginCloud?restId" + 
       "=" + restId + "&cloudUsername=" + cloudUsername + "&cloudPassword=" + cloudPassword + "&version=" + "230");
     if (StringUtils.isEmpty(jsonStr))
     {
       p.setStatusCode(StatusCodeEnum.CONNECT_ERROR.getCode());
       p.setMessage("网络异常");
       return p;
     }
     try {
       p = (DwzAjaxDone)objectMapper.readValue(jsonStr, DwzAjaxDone.class);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return p;
   }
 
   public DwzAjaxDone getCloudRestSynStatus(String restId, String synVersionId)
   {
     ObjectMapper objectMapper = new ObjectMapper();
     DwzAjaxDone p = new DwzAjaxDone();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/getCloudRestSynStatus?restId" + 
       "=" + restId + "&synVersionId=" + synVersionId + "&version=" + "230");
     if (StringUtils.isEmpty(jsonStr))
     {
       p.setStatusCode(StatusCodeEnum.CONNECT_ERROR.getCode());
       p.setMessage("网络异常");
       return p;
     }
     try {
       p = (DwzAjaxDone)objectMapper.readValue(jsonStr, DwzAjaxDone.class);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return p;
   }
 
   public Page<OrderAndTakeoutVo> getCloudBill(String restId, int pageNumber, int pagzSize, String keywords, String searchType, String cloudBillStatus, Map<String, Object> map)
   {
     clearCache();
 
     Page page = null;
     List ls = new ArrayList();
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/getOrderAndTakeout?restId" + 
       "=" + restId + "&pageNumber=" + pageNumber + "&pagzSize=" + pagzSize + "&keywords=" + keywords + "&searchType=" + searchType + "&cloudBillStatus=" + cloudBillStatus);
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try
     {
       PageVo p = (PageVo)objectMapper.readValue(jsonStr, PageVo.class);
//       OrderAndTakeoutVo[] arr = (OrderAndTakeoutVo[])objectMapper.readValue(p.getDataStr(), OrderAndTakeoutVo.class);
//       for (int i = 0; i < arr.length; i++) {
//         ls.add(arr[i]);
//       }
       OrderAndTakeoutVo arr = (OrderAndTakeoutVo)objectMapper.readValue(p.getDataStr(), OrderAndTakeoutVo.class);

         ls.add(arr);

       
       PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, null);
       page = new PageImpl(ls, pageRequest, p.getTotal());
 
       Map mapData = (Map)objectMapper.readValue(p.getOtherData(), Map.class);
       map.put("takeoutCountToday", mapData.get("takeoutCountToday"));
       map.put("orderCountToday", mapData.get("orderCountToday"));
     } catch (Exception e) {
       e.printStackTrace();
     }
     return page;
   }
 
   private void clearCache()
   {
     userTakeoutDetailCache = new HashMap();
     userTakeoutDetailDishCache = new HashMap();
     userOrderDetailCache = new HashMap();
     userOrderDetailDishCache = new HashMap();
   }
 
   public Map<String, Object> getUserTakeoutDetail(String billId)
   {
     if (userTakeoutDetailCache.get(billId) != null)
     {
       return (Map)userTakeoutDetailCache.get(billId);
     }
     Map map = new HashMap();
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/getUserTakeoutDetail?billId" + 
       "=" + billId);
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try {
       map = (Map)objectMapper.readValue(jsonStr, Map.class);
       userTakeoutDetailCache.put(billId, map);
     } catch (JsonParseException e) {
       e.printStackTrace();
     } catch (JsonMappingException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
     return map;
   }
 
   public List<Map<String, Object>> getUserTakeoutDetailDish(String billId)
   {
     if (userTakeoutDetailDishCache.get(billId) != null)
     {
       return (List)userTakeoutDetailDishCache.get(billId);
     }
     List page = null;
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/getUserTakeoutDetailDish?billId" + 
       "=" + billId);
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try {
       page = (List)objectMapper.readValue(jsonStr, List.class);
       userTakeoutDetailDishCache.put(billId, page);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return page;
   }
 
   public Map<String, Object> getUserOrderDetail(String billId)
   {
     if (userOrderDetailCache.get(billId) != null)
     {
       return (Map)userOrderDetailCache.get(billId);
     }
     Map map = new HashMap();
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/getUserOrderDetail?billId" + 
       "=" + billId);
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try {
       map = (Map)objectMapper.readValue(jsonStr, Map.class);
       userOrderDetailCache.put(billId, map);
     } catch (JsonParseException e) {
       e.printStackTrace();
     } catch (JsonMappingException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
     return map;
   }
 
   public List<Map<String, Object>> getUserOrderDetailDish(String billId) {
     if (userOrderDetailDishCache.get(billId) != null)
     {
       return (List)userOrderDetailDishCache.get(billId);
     }
     List<Map<String, Object>> page = null;
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/getUserOrderDetailDish?billId" + 
       "=" + billId);
 
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try {
       page = (List)objectMapper.readValue(jsonStr, List.class);
       for (Map e : page)
       {
         String dishArray = (String)e.get("dishArray");
         List dishMap = (List)objectMapper.readValue(dishArray, List.class);
         e.put("dishMap", dishMap);
       }
       Collections.sort(page, new Comparator() {
//         public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
//           return ((Integer)arg0.get("tabNum")).intValue() - ((Integer)arg1.get("tabNum")).intValue();
//         }
         
         public int compare(Object arg0, Object arg1) {
             return ((Integer)((Map<String, Object>)arg0).get("tabNum")).intValue() - ((Integer)((Map<String, Object>)arg1).get("tabNum")).intValue();
           }


       });
       userOrderDetailDishCache.put(billId, page);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return page;
   }
 
   public Map<String, Object> reviewBillFailed(String billId, String takeoutOrderDiv, String notes, String failReasonDesc, String userId)
   {
     if (!StringUtils.isEmpty(notes)) {
       failReasonDesc = notes;
     }
     Map page = null;
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/reviewBillFailed?billId" + 
       "=" + billId + "&takeoutOrderDiv=" + takeoutOrderDiv + "&notes=" + notes + "&failReasonDesc=" + failReasonDesc + "&checkBy=" + userId);
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try {
       page = (Map)objectMapper.readValue(jsonStr, Map.class);
       clearCache();
     } catch (Exception e) {
       e.printStackTrace();
     }
     return page;
   }
 
   public Map<String, Object> reviewBillPass(String billId, String takeoutOrderDiv, String takeoutStatus, String reviewPassNotes, String checkName, String checkTime)
   {
     Map page = null;
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/reviewBillPass?billId" + 
       "=" + billId + "&takeoutOrderDiv=" + takeoutOrderDiv + "&takeoutStatus=" + takeoutStatus + "&reviewPassNotes=" + reviewPassNotes + "&checkName=" + checkName + "&checkTime=" + checkTime);
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try {
       page = (Map)objectMapper.readValue(jsonStr, Map.class);
       String statusCode = (String)page.get("statusCode");
       if (StatusCodeEnum.SUCCESS.getCode().equals(statusCode))
       {
         if (TakeoutOrderDivEnum.TAKEOUT.getCode().equals(takeoutOrderDiv))
         {
           Map order = getUserTakeoutDetail(billId);
           order.put("takeoutStatus", (String)page.get("orderStatus"));
         }
         if (TakeoutOrderDivEnum.ORDER.getCode().equals(takeoutOrderDiv))
         {
           Map order = getUserOrderDetail(billId);
           order.put("orderStatus", (String)page.get("orderStatus"));
         }
       }
 
       clearCache();
     } catch (Exception e) {
       e.printStackTrace();
     }
     return page;
   }
 
   public Map<String, Object> updateBillStatus(String billId, String takeoutOrderDiv, String orderStatus)
   {
     Map page = null;
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/updateBillStatus?billId" + 
       "=" + billId + "&takeoutOrderDiv=" + takeoutOrderDiv + "&orderStatus=" + orderStatus);
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try {
       page = (Map)objectMapper.readValue(jsonStr, Map.class);
       String statusCode = (String)page.get("statusCode");
       if (StatusCodeEnum.SUCCESS.getCode().equals(statusCode))
       {
         if (TakeoutOrderDivEnum.TAKEOUT.getCode().equals(takeoutOrderDiv))
         {
           Map order = getUserTakeoutDetail(billId);
           order.put("takeoutStatus", (String)page.get("orderStatus"));
         }
         if (TakeoutOrderDivEnum.ORDER.getCode().equals(takeoutOrderDiv))
         {
           Map order = getUserTakeoutDetail(billId);
           order.put("orderStatus", (String)page.get("orderStatus"));
         }
       }
 
       clearCache();
     } catch (Exception e) {
       e.printStackTrace();
     }
     return page;
   }
 
   public Map<String, Object> getCloudBillCountForAll(String restId)
   {
     Map mapData = null;
     ObjectMapper objectMapper = new ObjectMapper();
     String jsonStr = CommunicationUtil.get(Constants.getDataServer() + "/open/cloudBill/getCloudBillCountForAll?restId=" + restId);
     if (StringUtils.isEmpty(jsonStr))
     {
       return null;
     }
     try {
       mapData = (Map)objectMapper.readValue(jsonStr, Map.class);
       clearCache();
     } catch (Exception e) {
       e.printStackTrace();
     }
     return mapData;
   }
 
   private List<LinkedHashMap<String, Object>> getJsonArrayForString(String objectDesc)
   {
     List list = null;
     if (StringUtils.isNotEmpty(objectDesc)) {
       ObjectMapper mapper = new ObjectMapper();
       try {
         list = (List)mapper.readValue(objectDesc, List.class);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return list;
   }
 
   public List<Map<String, Object>> convertedUserOrderDetailDishToObjects(List<Map<String, Object>> dishes)
   {
     if (dishes != null) {
       for (Map map : dishes) {
         if (map != null) {
           List<Map<String,String>> dishMap = (List)map.get("dishMap");
           if (dishMap != null) {
             for (Map linkedHashMap : dishMap) {
               if (linkedHashMap != null) {
                 Object dsDishesObject = linkedHashMap.get("dsDishesDesc");
                 if (dsDishesObject != null) {
                   String dsDishesDesc = dsDishesObject.toString();
                   List dsdishes = getJsonArrayForString(dsDishesDesc);
                   linkedHashMap.put("dsDishesList", dsdishes);
                 }
               }
             }
           }
         }
       }
     }
     return dishes;
   }
 
   public List<Map<String, Object>> convertedUserTakeoutDetailDishToObjects(List<Map<String, Object>> dishes)
   {
     if (dishes != null) {
       for (Map map : dishes) {
         if (map != null) {
           Object object = map.get("dsDishesDesc");
           if (object != null) {
             String dsDishesDesc = object.toString();
             try {
               List dsdishes = getJsonArrayForString(dsDishesDesc);
               map.put("dsDishesList", dsdishes);
             } catch (Exception e) {
               e.printStackTrace();
             }
           }
         }
       }
     }
     return dishes;
   }
 
   public void synCloudReturnResult(String restId, String statusCode)
   {
     if (StatusCodeEnum.SUCCESS.getCode().equals(statusCode))
     {
       EnumUtil.insert("update cm_restaurant set syn_database_status = '" + SynDatabaseStatusEnum.ALL_SYNCOMPLETE.getCode() + "', syn_data_complete_time = '" + DateUtil.toString(DateProvider.DEFAULT.getDate()) + "' where rest_id = '" + restId + "'");
     }
   }
 
   public static void main(String[] args)
   {
     EnumUtil.insert("update cm_restaurant set syn_database_status = '" + SynDatabaseStatusEnum.ALL_SYNCOMPLETE.getCode() + "', syn_data_complete_time = '" + DateUtil.toString(DateProvider.DEFAULT.getDate()) + "'");
   }
 
   public void notifyCloudStatus()
   {
     UserSettingCache.getInstance().clearCloudSetting();
   }
 
   public void saveQueueToDB()
   {
     SaveConsumer saveConsumer = new SaveConsumer(UserSettingCache.getInstance().basket, UserSettingCache.getInstance().transaction);
     PriorityExecutor.execSave(saveConsumer);
   }
 
   public void submitQueueToCloud()
   {
     SynConsumer synConsumer = new SynConsumer(UserSettingCache.getInstance().transaction);
     PriorityExecutor.execSyn(synConsumer);
   }
 
   public void submitQueueToCloud(String restId)
   {
     List<Transaction> ts = this.transactionService.findByRestIdAndStatus(restId);
     if ((ts != null) && (ts.size() > 0))
     {
       for (Transaction e : ts) {
         try
         {
           List transferCarriers = this.transferCarrierService.findBytransactionIdOrderBySeqAsc(e.getId().intValue());
           e.setTransferCarriers(transferCarriers);
           UserSettingCache.getInstance().transaction.produceTransaction(e);
         } catch (InterruptedException e1) {
           e1.printStackTrace();
         }
       }
     }
     SynConsumer synConsumer = new SynConsumer(UserSettingCache.getInstance().transaction);
     PriorityExecutor.execSyn(synConsumer);
   }
 
   public void submitTransaction(Transaction transaction)
   {
     try
     {
       if ((TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().isBandCloudAccount)) && (
         (SynDatabaseStatusEnum.SYNING.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.CLOUD_SYNCOMPLETE.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.ALL_SYNCOMPLETE.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.CLOUD_SYNING.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus))))
       {
         boolean result = notifyToSendData(transaction);
 
         if (result)
         {
           return;
         }
 
         Thread.sleep(Constants.THREAD_SECONDS);
 
         submitTransaction(transaction);
       }
 
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 
   public Transaction saveAndGetTransactionNo(String restId)
   {
     Transaction entity = new Transaction();
     entity.setRestId(restId);
     return (Transaction)this.transactionService.save(entity);
   }
 
   public Transaction saveTransferCarrier(Map<String, Object> fmap) {
     Transaction transactionNo = null;
     try
     {
       if ((TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().isBandCloudAccount)) && (
         (SynDatabaseStatusEnum.SYNING.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.CLOUD_SYNCOMPLETE.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.ALL_SYNCOMPLETE.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.CLOUD_SYNING.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus))))
       {
         String restId = (String)fmap.get("restId");
         String separatorCloud = "┬";
         fmap.remove("restId");
         transactionNo = saveAndGetTransactionNo(restId);
         Set<String> operations = fmap.keySet();
         int i = 0;
         List list = new ArrayList();
         for (String key : operations) {
           Object entity = fmap.get(key);
           String id = key.substring(0, key.indexOf("_"));
           String method = key.substring(key.indexOf("_") + 1, key.length());
           TransferCarrier entity_ = null;
           if ((entity instanceof String))
           {
             if (OperationTypeEnum.UPLOAD.getCode().equals(method))
             {
               String path = (String)entity;
               String realPath = path.split(",")[0];
               String type = path.split(",")[1];
               entity_ = new TransferCarrier(transactionNo, method, (String)entity, i);
               entity_.setSynVersionId(UserSettingCache.getInstance().synVersionId);
               entity_.setContent(realPath);
               entity_.setCommand(type);
             }
             else if (OperationTypeEnum.MOBILE_MESSAGE.getCode().equals(method))
             {
               String path = (String)entity;
               String methodCode = path.split("-")[0];
               String message = path.split("-")[1];
               entity_ = new TransferCarrier(transactionNo, method, message, i);
               entity_.setSynVersionId(UserSettingCache.getInstance().synVersionId);
               entity_.setContent(message);
               entity_.setCommand(methodCode);
             }
             else if (OperationTypeEnum.METHOD.getCode().equals(method))
             {
               String path = (String)entity;
               String method_code = path.split(separatorCloud)[0];
               String params = path.split(separatorCloud)[1];
               entity_ = new TransferCarrier(transactionNo, method, params, i);
               entity_.setSynVersionId(UserSettingCache.getInstance().synVersionId);
               entity_.setCommand(method_code);
             }
             else
             {
               entity_ = new TransferCarrier(transactionNo, method, (String)entity, i);
               entity_.setSynVersionId(UserSettingCache.getInstance().synVersionId);
             }
           }
           else
           {
             entity_ = new TransferCarrier(transactionNo, method, BeanUtils.objectToStr(entity), i);
             entity_.setSynVersionId(UserSettingCache.getInstance().synVersionId);
           }
           entity_.setRestId(restId);
           this.transferCarrierService.save(entity_);
           list.add(entity_);
           i++;
         }
         transactionNo.setTransferCarriers(list);
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
       return null;
     }
 
     return transactionNo;
   }
 
   @RequestMapping(value={"test"}, produces={"application/json"})
   @ResponseBody
   public boolean notifyToSendData(Transaction transactionNo)
   {
     return work(transactionNo);
   }
 
   public boolean work(Transaction transaction)
   {
     try
     {
       String restId = transaction.getRestId();
       List carrierList = transaction.getTransferCarriers();
       if ((carrierList == null) || (carrierList.size() == 0)) {
         transaction.setStatus(TrueFalseEnum.TRUE.getCode());
         this.transactionService.save(transaction);
         return true;
       }
       DataPackage dataPackage = new DataPackage();
       dataPackage.setTransferCarrierList(carrierList);
 
       ResponseEntity result = postEntity(Constants.getPullDataServer(), dataPackage.getClass(), dataPackage, String.class);
       if (result == null)
       {
         return false;
       }
 
       if (SynResultStatusEnum.SUCCESS.getCode().equals(result.getBody()))
       {
         transaction.setStatus(TrueFalseEnum.TRUE.getCode());
         this.transactionService.save(transaction);
       } else {
         if ((result == null) || (StringUtils.isEmpty(result.toString())))
         {
           System.err.println("------------------------------------------网断了，稍后重连！");
           return false;
         }
         System.err.println("------------------------------------------同步失败：" + SynResultStatusEnum.getDesc((String)result.getBody()));
         if (SynResultStatusEnum.RETRY.getCode().equals(result.getBody())) {
           return false;
         }
         if (SynResultStatusEnum.ERROR.getCode().equals(result.getBody())) {
           Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
           restaurant.setSynDatabaseStatus(SynDatabaseStatusEnum.ERROR.getCode());
           this.restaurantService.save(restaurant);
           UserSettingCache.getInstance().init(restaurant);
 
           transaction.setStatus(TrueFalseEnum.TRUE.getCode());
           this.transactionService.save(transaction);
         }
         if (SynResultStatusEnum.VERSION_DIFFERENT.getCode().equals(result.getBody())) {
           Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
           if (!SynDatabaseStatusEnum.ERROR.getCode().equals(restaurant.getSynDatabaseStatus()))
           {
             restaurant.setSynDatabaseStatus(SynDatabaseStatusEnum.VERSION_DIFFERENT.getCode());
             this.restaurantService.save(restaurant);
             UserSettingCache.getInstance().init(restaurant);
 
             transaction.setStatus(TrueFalseEnum.TRUE.getCode());
             this.transactionService.save(transaction);
           }
         }
         if (SynResultStatusEnum.CLOUD_NO_DATA.getCode().equals(result.getBody())) {
           Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
           restaurant.setIsBandCloudAccount(TrueFalseEnum.FALSE.getCode());
           restaurant.setSynDatabaseStatus(SynDatabaseStatusEnum.INIT.getCode());
           this.restaurantService.save(restaurant);
           UserSettingCache.getInstance().init(restaurant);
 
           transaction.setStatus(TrueFalseEnum.TRUE.getCode());
           this.transactionService.save(transaction);
         }
 
         if (SynResultStatusEnum.CHECK_ERROR.getCode().equals(result.getBody())) {
           Restaurant restaurant = (Restaurant)this.restaurantService.getOne(restId);
           restaurant.setSynDatabaseStatus(SynDatabaseStatusEnum.ERROR.getCode());
           this.restaurantService.save(restaurant);
           UserSettingCache.getInstance().init(restaurant);
 
           transaction.setStatus(TrueFalseEnum.TRUE.getCode());
           this.transactionService.save(transaction);
         }
       }
 
     }
     catch (Exception e)
     {
       e.printStackTrace();
       return true;
     }
     return true;
   }
 
   protected static <E, T> ResponseEntity<T> postEntity(String url, Class<E> reqType, Object reqTypeObj, Class<T> responseType)
   {
     RestTemplate restTemplate = new RestTemplate();
     restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
     ResponseEntity response = null;
     HttpHeaders requestHeaders = new HttpHeaders();
     try {
       HttpEntity entity = new HttpEntity(reqTypeObj, requestHeaders);
       response = restTemplate.postForEntity(url, entity, responseType, new Object[0]);
     }
     catch (Exception e) {
       System.err.println("发送同步数据：" + e.getMessage());
     }
     return response;
   }
 
   class SaveConsumer
     implements Runnable
   {
     private Basket basket;
     private Basket transaction;
 
     public SaveConsumer(Basket basket, Basket transaction)
     {
       this.basket = basket;
       this.transaction = transaction;
     }
 
     public void run()
     {
       try
       {
         while (true) {
           int sleepSecond = 10000;
           if (this.basket.isEmpty())
           {
             sleepSecond = 30000;
           }
           Map map = this.basket.consume();
           Thread.sleep(sleepSecond);
           if (map == null) {
             continue;
           }
           Transaction transactionNo = CloudBillService.this.saveTransferCarrier(map);
           if (transactionNo == null)
             continue;
           this.transaction.produceTransaction(transactionNo);
         }
       }
       catch (InterruptedException localInterruptedException)
       {
       }
     }
   }
 
   class SynConsumer
     implements Runnable
   {
     private Basket transaction;
 
     public SynConsumer(Basket transaction)
     {
       this.transaction = transaction;
     }
 
     public void run()
     {
       try
       {
         synchronized (CloudBillService.lock)
         {
           Transaction transaction = this.transaction.consumeTransaction();
           if (transaction != null)
           {
             CloudBillService.this.submitTransaction(transaction);
           }
         }
       }
       catch (InterruptedException localInterruptedException)
       {
       }
     }
   }
 }

