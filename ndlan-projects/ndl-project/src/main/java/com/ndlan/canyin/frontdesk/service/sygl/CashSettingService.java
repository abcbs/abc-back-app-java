 package com.ndlan.canyin.frontdesk.service.sygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.sygl.CashSetting;
 import com.ndlan.canyin.base.repository.sygl.CashSettingDao;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class CashSettingService extends BaseService<CashSettingDao, CashSetting>
 {
   private CashSettingDao cashSettingDao;
 
   public CashSetting findByRestId(String restId)
   {
     return this.cashSettingDao.findByRestId(restId);
   }
   @Autowired
   public void setBaseDao(CashSettingDao dao) {
     super.setDao(dao);
     this.cashSettingDao = dao;
   }
 }

