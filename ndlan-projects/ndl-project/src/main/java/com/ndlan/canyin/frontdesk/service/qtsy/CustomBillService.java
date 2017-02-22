 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillDao;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.stereotype.Service;
 
 @Service
 public class CustomBillService extends DinerBillService
 {
   public Page<DinerBill> searchPage(PageRequest pageRequest, String currentUserRestId, String keyword)
   {
     return ((DinerBillDao)getDao()).findAll(DinerBillSpec.searchPageBykeyword(keyword, currentUserRestId), pageRequest);
   }
 }

