 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.qtsy.CashierDeskSetting;
 import com.ndlan.canyin.core.common.SynDatabaseStatusEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.sharelogic.queue.Basket;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.commons.beanutils.BeanUtils;
 
 public class UserSettingCache
 {
   private static UserSettingCache userSettingCache = null;
 
   public Basket basket = new Basket();
 
   public Basket transaction = new Basket();
 
   public int syn_index = 0;
 
   public boolean isInitSetting = false;
 
   public String isBandCloudAccount = null;
 
   public String synDatabaseStatus = null;
 
   public String synVersionId = null;
 
   public boolean isJudgeDishRaws = true;
   public String messageTel;
   public int orderWarnTime = 0;
   public int orderLockTime = 0;
   public int orderExpireTime = 0;
 
   private Map<String, UserSettingCacheSetting> userCache = new HashMap();
 
   public static UserSettingCache getInstance()
   {
     if (userSettingCache == null) {
       userSettingCache = new UserSettingCache();
     }
     return userSettingCache;
   }
 
   public void clearCloudSetting()
   {
     this.isInitSetting = false;
   }
 
   public void init(Restaurant rest)
   {
     this.isBandCloudAccount = rest.getIsBandCloudAccount();
     this.synDatabaseStatus = rest.getSynDatabaseStatus();
     this.synVersionId = rest.getSynVersionId();
     this.isInitSetting = true;
   }
 
   public UserSettingCacheSetting getUserCache(String userId)
   {
     if (this.userCache != null)
     {
       UserSettingCacheSetting userSettingCacheSetting = (UserSettingCacheSetting)this.userCache.get(userId);
       return userSettingCacheSetting;
     }
     return null;
   }
 
   public void setCommonUserCache(CashierDeskSetting commonSettingCashierDeskSetting)
   {
     this.messageTel = commonSettingCashierDeskSetting.getMessageTel();
     this.orderWarnTime = commonSettingCashierDeskSetting.getOrderWarnTime();
     this.orderLockTime = commonSettingCashierDeskSetting.getOrderLockTime();
     this.orderExpireTime = commonSettingCashierDeskSetting.getOrderExpireTime();
   }
 
   public void setUserCache(String userId, CashierDeskSetting cashierDeskSetting, CashierDeskSetting commonSettingCashierDeskSetting)
   {
     if (cashierDeskSetting == null) {
       try
       {
         cashierDeskSetting = new CashierDeskSetting();
         BeanUtils.copyProperties(cashierDeskSetting, commonSettingCashierDeskSetting);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
 
     if (!this.userCache.containsKey(userId))
     {
       UserSettingCacheSetting setting = new UserSettingCacheSetting();
       try {
         BeanUtils.copyProperties(setting, cashierDeskSetting);
       } catch (Exception e) {
         e.printStackTrace();
       }
       this.userCache.put(userId, setting);
     }
     else
     {
       UserSettingCacheSetting setting = new UserSettingCacheSetting();
       try {
         BeanUtils.copyProperties(setting, cashierDeskSetting);
       } catch (Exception e) {
         e.printStackTrace();
       }
       this.userCache.put(userId, setting);
     }
     if (commonSettingCashierDeskSetting != null)
     {
       this.messageTel = commonSettingCashierDeskSetting.getMessageTel();
       this.orderWarnTime = commonSettingCashierDeskSetting.getOrderWarnTime();
       this.orderLockTime = commonSettingCashierDeskSetting.getOrderLockTime();
       this.orderExpireTime = commonSettingCashierDeskSetting.getOrderExpireTime();
     }
   }
 
   public void setUserCache(String userId, CashierDeskSetting cashierDeskSetting)
   {
     if (!this.userCache.containsKey(userId))
     {
       UserSettingCacheSetting setting = new UserSettingCacheSetting();
       try {
         BeanUtils.copyProperties(setting, cashierDeskSetting);
       } catch (Exception e) {
         e.printStackTrace();
       }
       this.userCache.put(userId, setting);
     }
     else
     {
       UserSettingCacheSetting setting = new UserSettingCacheSetting();
       try {
         BeanUtils.copyProperties(setting, cashierDeskSetting);
         setting.setUserName(getUserCache(userId).getUserName());
       } catch (Exception e) {
         e.printStackTrace();
       }
       this.userCache.put(userId, setting);
     }
   }
 
   public boolean isCanRunVisitCloudOperate()
   {
     if (getInstance().isInitSetting)
     {
       if ((TrueFalseEnum.TRUE.getCode().equals(getInstance().isBandCloudAccount)) && (
         (SynDatabaseStatusEnum.SYNING.getCode().equals(getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.CLOUD_SYNCOMPLETE.getCode().equals(getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.ALL_SYNCOMPLETE.getCode().equals(getInstance().synDatabaseStatus)) || 
         (SynDatabaseStatusEnum.CLOUD_SYNING.getCode().equals(getInstance().synDatabaseStatus))))
       {
         return true;
       }
     }
     return false;
   }
 }

