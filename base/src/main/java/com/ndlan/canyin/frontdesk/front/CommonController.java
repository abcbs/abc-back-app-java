 package com.ndlan.canyin.frontdesk.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.common.Constants;
import com.ndlan.canyin.frontdesk.service.cloud.CloudBillService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
import com.ndlan.canyin.frontdesk.service.qtsy.SelfMessageService;
import com.ndlan.canyin.frontdesk.util.CommunicationUtil;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.core.common.StatusCodeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.web.Servlets;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.HashMap;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.data.domain.Page;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @RequestMapping({"/common"})
 @Lazy
 public class CommonController extends BaseManageController
 {
 
   @Autowired
   DinerBillService dinerBillService;
 
   @Autowired
   SelfMessageService selfMessageService;
 
   @Autowired
   CloudBillService cloudBillService;
 
   @RequestMapping(value={"getMessages"}, produces={"application/json"})
   @ResponseBody
   public Map<String, String> getCitys(Model model, HttpServletRequest request)
   {
     String restId = getCurrentUserRestId();
     Map messageMap = new HashMap();
 
     if (StringUtils.isEmpty(restId)) {
       messageMap.put("statusCode", StatusCodeEnum.UNLOGIN.getCode());
       return messageMap;
     }
     long noReadSelfMessagesSize = 0L;
 
     boolean isConnect = false;
     if (!UserSettingCache.getInstance().isInitSetting)
     {
       Restaurant rest = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
       UserSettingCache.getInstance().init(rest);
     }
     if (UserSettingCache.getInstance().isCanRunVisitCloudOperate())
     {
       isConnect = CommunicationUtil.isConnectG2();
     }
 
     if (!isConnect) {
       Constants.BOOK_SIZE = 0;
       Constants.TAKEOUT_SIZE = 0;
       Constants.TIME_LAST = 0L;
       Constants.BOOK_CHANGED = true;
       Constants.TAKEOUT_CHANGED = true;
       Constants.NEED_REFRESH_CLOUD = false;
     }
     int noOperationCloudSize = Constants.BOOK_SIZE + Constants.TAKEOUT_SIZE;
 
     long noPayTakeoutSize = this.dinerBillService.getTakeoutListForNoPayCount(restId);
 
     Map searchParams = Servlets.getParametersStartingWith(request, "search_");
     searchParams.put("EQ_restId", getCurrentUserRestId());
     searchParams.put("EQ_status", TrueFalseEnum.FALSE.getCode());
     Page page = this.selfMessageService.searchPage(searchParams, 1, 1, null);
     noReadSelfMessagesSize = page == null ? 0L : page.getTotalElements();
     page = null;
 
     boolean hasNewOrder = (Constants.BOOK_CHANGED) || (Constants.TAKEOUT_CHANGED);
 
     if ((Constants.TIME_LAST == 0L) && (isConnect) && (hasNewOrder))
     {
       Map mapData = this.cloudBillService.getCloudBillCountForAll(restId);
       if (mapData != null) {
         String statusCode = mapData.get("statusCode").toString();
         if (StatusCodeEnum.SUCCESS.getCode().equals(statusCode)) {
           Constants.TAKEOUT_SIZE = Integer.valueOf(mapData.get("takeoutCount").toString()).intValue();
           Constants.BOOK_SIZE = Integer.valueOf(mapData.get("orderCount").toString()).intValue();
           noOperationCloudSize = Constants.BOOK_SIZE + Constants.TAKEOUT_SIZE;
           Constants.BOOK_CHANGED = false;
           Constants.TAKEOUT_CHANGED = false;
           Constants.NEED_REFRESH_CLOUD = true;
         }
       }
       mapData = null;
     }
     else if ((hasNewOrder) && (isConnect)) {
       Constants.BOOK_CHANGED = false;
       Constants.TAKEOUT_CHANGED = false;
       Constants.NEED_REFRESH_CLOUD = true;
     }
 
     if ((Constants.TIME_LAST != 0L) && (Constants.NEED_REFRESH_CLOUD)) {
       messageMap.put("needRefreshCloud", "1");
       Constants.NEED_REFRESH_CLOUD = false;
     } else {
       messageMap.put("needRefreshCloud", "0");
     }
 
     messageMap.put("noPayTakeoutSize", String.valueOf(noPayTakeoutSize));
     messageMap.put("noReadSelfMessagesSize", String.valueOf(noReadSelfMessagesSize));
     messageMap.put("noOperationCloudSize", String.valueOf(noOperationCloudSize));
     return messageMap;
   }
 
   @RequestMapping(value={"getDateTime"}, produces={"application/json"})
   @ResponseBody
   public Map<String, String> getDateTime(Model model)
   {
     String restId = getCurrentUserRestId();
     Map messageMap = new HashMap();
 
     if (StringUtils.isEmpty(restId)) {
       messageMap.put("statusCode", StatusCodeEnum.UNLOGIN.getCode());
       return messageMap;
     }
     DateFormat df = new SimpleDateFormat("HH:mm:ss");
     Calendar cal = Calendar.getInstance();
     messageMap.put("nowDate", DateUtil.shortSdf.format(cal.getTime()));
     messageMap.put("nowTime", df.format(cal.getTime()));
     return messageMap;
   }
 }

