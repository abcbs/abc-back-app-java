 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.BillCombine;
 import com.ndlan.canyin.base.repository.qtsy.BillCombineDao;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class BillCombineService extends BaseService<BillCombineDao, BillCombine>
 {
   @Autowired
   public void setDao(BillCombineDao dao)
   {
     super.setDao(dao);
   }
 }

