 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.DinerBillCreditPayment;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillCreditPaymentDao;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 @Service
 public class DinerBillCreditPaymentService extends BaseService<DinerBillCreditPaymentDao, DinerBillCreditPayment>
 {
   @Autowired
   public void setDao(DinerBillCreditPaymentDao dao)
   {
     super.setDao(dao);
   }
 }

