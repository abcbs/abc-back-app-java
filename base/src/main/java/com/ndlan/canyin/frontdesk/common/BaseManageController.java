 package com.ndlan.canyin.frontdesk.common;
 
 import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm.ShiroUser;
import com.ndlan.canyin.frontdesk.transport.Envelope;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.synch.Transaction;
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.SmtCodeEnum;
import com.ndlan.canyin.core.common.SynDatabaseStatusEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.sharelogic.queue.TransferCarrierProduct;
import com.ndlan.canyin.sharelogic.util.PriorityExecutor;

import java.beans.PropertyEditorSupport;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
 
 public class BaseManageController
   implements InitializingBean
 {
 
   @Autowired
   protected RestaurantService restaurantService;
   public static byte[] lock = new byte[0];
 
   public void afterPropertiesSet()
     throws Exception
   {
   }
 
   public boolean isLogin()
   {
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
 
     return user != null;
   }
 
   public String getCurrentUserId()
   {
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
     return user.id;
   }
 
   public Employee getCurrentUser()
   {
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
     return user.employee;
   }
 
   public String getCurrentUserRestId()
   {
     ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
     if (user == null) {
       return null;
     }
     return user.getRestId();
   }
 
   public void pullDataFromCloud(Envelope envelope)
   {
     if (envelope.getSign() != 1)
     {
       if (envelope.getSign() != 2);
     }
   }
 
   public void check_(String serStr) {
     ObjectInputStream ois = null;
     Table t = null;
     try {
       ois = new ObjectInputStream(new ByteArrayInputStream(serStr.getBytes("ISO-8859-1")));
 
       t = (Table)ois.readObject();
     }
     catch (UnsupportedEncodingException e) {
       e.printStackTrace();
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     System.out.println(t.getTabName());
   }
 
   public Transaction doSynchSingleTable(String operation, Object entity)
   {
     LinkedHashMap map = new LinkedHashMap();
     map.put("1_" + operation, entity);
     doSynchMultiTable(map);
     return null;
   }
 
   public Transaction doCallCloudMethod(SmtCodeEnum smtCodeEnum, String[] cloudMethodParams)
   {
     LinkedHashMap map = new LinkedHashMap();
     String methodParamsC = "";
     for (String e : cloudMethodParams)
     {
       methodParamsC = methodParamsC + "," + e;
     }
     String content = smtCodeEnum.getCode() + "cc" + methodParamsC.replaceFirst(",", "");
     map.put("1_" + OperationTypeEnum.METHOD.getCode(), content);
     doSynchMultiTable(map);
     return null;
   }
 
   public Transaction doCallCloudMethod(SmtCodeEnum smtCodeEnum, ArrayList<String> cloudMethodParams)
   {
     LinkedHashMap map = new LinkedHashMap();
     String methodParamsC = "";
     for (String e : cloudMethodParams)
     {
       methodParamsC = methodParamsC + "," + e;
     }
     String content = smtCodeEnum.getCode() + "bb" + methodParamsC.replaceFirst(",", "");
     map.put("1_" + OperationTypeEnum.METHOD.getCode(), content);
     doSynchMultiTable(map);
     return null;
   }
 
   public void doSynchMultiTable(Map<String, Object> map)
   {
     if (!UserSettingCache.getInstance().isInitSetting)
     {
       Restaurant rest = (Restaurant)this.restaurantService.getOne(getCurrentUserRestId());
       UserSettingCache.getInstance().init(rest);
     }
     if ((TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().isBandCloudAccount)) && ((SynDatabaseStatusEnum.SYNING.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || (SynDatabaseStatusEnum.CLOUD_SYNCOMPLETE.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || (SynDatabaseStatusEnum.ALL_SYNCOMPLETE.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus)) || (SynDatabaseStatusEnum.CLOUD_SYNING.getCode().equals(UserSettingCache.getInstance().synDatabaseStatus))))
     {
       UserSettingCache.getInstance().syn_index += 1;
       TransferCarrierProduct transferCarrier = new TransferCarrierProduct(UserSettingCache.getInstance().syn_index, UserSettingCache.getInstance().basket, map, getCurrentUserRestId());
       PriorityExecutor.execTask(transferCarrier);
     }
   }
 
   public HashMap<String, Object> getTransferCarrierContainer(Map<String, Object> map) {
     return new HashMap();
   }
 
   public static class StringEditor extends PropertyEditorSupport
   {
     public void setAsText(String text)
       throws IllegalArgumentException
     {
       if (text == null)
         setValue(Integer.valueOf(0));
       else
         setValue(text);
     }
 
     public void setValue(Object value)
     {
       if (value == null)
         super.setValue(Integer.valueOf(0));
       else
         super.setValue(value);
     }
   }
 }

