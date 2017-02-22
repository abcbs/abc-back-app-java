 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.qtsy.EmployeShift;
 import com.ndlan.canyin.base.entity.qtsy.EmployeShiftIncome;
 import com.ndlan.canyin.base.repository.qtsy.EmployeShiftDao;
 import com.ndlan.canyin.base.repository.yygl.DinerBillDisheMyDao;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.vo.PaymentTypeVO;
 import java.util.Date;
 import java.util.LinkedHashMap;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class EmployeShiftService extends BaseService<EmployeShiftDao, EmployeShift>
 {
   private EmployeShiftDao employeShiftDao;
 
   @Autowired
   private EmployeShiftIncomeService employeShiftIncomeService;
 
   @Autowired
   private DinerBillDisheMyDao dinerBillDisheMyDao;
 
   public List<EmployeShift> findByRestIdAndCreateEmployeeOrderByCreateTimeDesc(String restId, Employee createEmployee)
   {
     return this.employeShiftDao.findByRestIdAndCreateEmployeeOrderByCreateTimeDesc(restId, createEmployee);
   }
 
   public List<EmployeShift> findByRestIdOrderByCreateTimeDesc(String restId)
   {
     return this.employeShiftDao.findByRestIdOrderByCreateTimeDesc(restId);
   }
 
   public List<EmployeShift> findByRestIdAndCreateTimeLessThanOrderByCreateTimeDesc(String restId, Date createTime)
   {
     return this.employeShiftDao.findByRestIdAndCreateTimeLessThanOrderByCreateTimeDesc(restId, createTime);
   }
 
   public EmployeShift saveShift(EmployeShift employeShift, List<PaymentTypeVO> paymentTypeVOs, String restId, LinkedHashMap<String, Object> map)
   {
     this.self.save(employeShift);
 
     map.put(employeShift.getCesId() + "_" + OperationTypeEnum.CREATE.getCode(), employeShift);
 
     for (PaymentTypeVO paymentTypeVO : paymentTypeVOs) {
       EmployeShiftIncome shiftIncome = new EmployeShiftIncome();
       shiftIncome.setEmployeShift(employeShift);
       shiftIncome.setCptId(paymentTypeVO.getCptId());
       shiftIncome.setRestId(restId);
       shiftIncome.setCptMoney(paymentTypeVO.getMoney());
       this.employeShiftIncomeService.save(shiftIncome);
 
       map.put(shiftIncome.getCesiId() + "_" + OperationTypeEnum.CREATE.getCode(), shiftIncome);
     }
 
     return employeShift;
   }
 
   public List<?> getCurrentShiftDishesStatByCesId(String restId, String cesId, String billStatus) {
     return this.dinerBillDisheMyDao.getCurrentShiftDishesStatByCesId(restId, cesId, billStatus);
   }
   @Autowired
   public void setDao(EmployeShiftDao dao) { super.setDao(dao);
     this.employeShiftDao = dao;
   }
 }

