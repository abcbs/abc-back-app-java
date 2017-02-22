 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.EmployeShiftIncome;
 import com.ndlan.canyin.base.repository.qtsy.EmployeShiftIncomeDao;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class EmployeShiftIncomeService extends BaseService<EmployeShiftIncomeDao, EmployeShiftIncome>
 {
   @Autowired
   public void setDao(EmployeShiftIncomeDao dao)
   {
     super.setDao(dao);
   }
 }

