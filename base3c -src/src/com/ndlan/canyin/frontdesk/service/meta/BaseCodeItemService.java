 package com.ndlan.canyin.frontdesk.service.meta;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.meta.BaseCodeItem;
 import com.ndlan.canyin.base.repository.meta.BaseCodeItemDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 @Lazy
 public class BaseCodeItemService extends BaseService<BaseCodeItemDao, BaseCodeItem>
 {
   private BaseCodeItemDao baseCodeItemDao;
 
   public List<BaseCodeItem> findItemsByBcCode(String bcCode)
   {
     return this.baseCodeItemDao.findByBcCode(bcCode);
   }
 
   public List<BaseCodeItem> findItemsByBcCodeOrderByBciCodeAsc(String bcCode)
   {
     return this.baseCodeItemDao.findByBcCodeOrderByShowSeqAsc(bcCode);
   }
 
   public BaseCodeItem findItemByBciCode(String bcCode, String bciCode)
   {
     return this.baseCodeItemDao.findByBcCodeAndBciCode(bcCode, bciCode);
   }
 
   public BaseCodeItemDao getBaseCodeItemDao()
   {
     return this.baseCodeItemDao;
   }
   @Autowired
   public void setBaseCodeItemDao(BaseCodeItemDao baseCodeItemDao) {
     this.baseCodeItemDao = baseCodeItemDao;
     super.setDao(baseCodeItemDao);
   }
 }

