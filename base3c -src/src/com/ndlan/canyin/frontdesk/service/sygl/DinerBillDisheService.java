 package com.ndlan.canyin.frontdesk.service.sygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillDisheDao;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
 
 @Service
 public class DinerBillDisheService extends BaseService<DinerBillDisheDao, DinerBillDishe>
 {
   private DinerBillDisheDao dinerBillDisheDao;
 
   public List<DinerBillDishe> findByRestIdAndBillId(String restId, String billId)
   {
     return this.dinerBillDisheDao.findByRestIdAndBillId(restId, billId);
   }
 
   public HashMap<String, Object> getLastFixedDishes(String restId, String billId)
   {
     DinerBillDishe lastDish = null;
     List<DinerBillDishe> ds = this.dinerBillDisheDao.findByRestIdAndBillIdOrderbyFixedTimeDesc(restId, billId);
 
     List unXiadanList = new ArrayList();
 
     Boolean isAddDishes = Boolean.FALSE;
 
     if ((ds != null) && (ds.size() > 0))
     {
       lastDish = (DinerBillDishe)ds.get(0);
       for (DinerBillDishe e : ds)
       {
         if (e.getFixedTime().compareTo(lastDish.getFixedTime()) == 0)
         {
           unXiadanList.add(e);
         }
         else {
           isAddDishes = Boolean.TRUE;
         }
       }
     }
     HashMap ret = new HashMap();
     ret.put("dinerBillDishes", unXiadanList);
     ret.put("isAddDishes", isAddDishes);
     return ret;
   }
 
   public List<DinerBillDishe> getDinerBillDisheByDishesStatuss(String billId, String dishesStatus)
   {
     Map filters = new HashMap();
     filters.put("dinerBill.billId", new SearchFilter("dinerBill.billId", SearchFilter.Operator.EQ, billId));
     filters.put("dishesStatus", new SearchFilter("dishesStatus", SearchFilter.Operator.NEQ, dishesStatus));
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), DinerBillDishe.class);
     return this.dinerBillDisheDao.findAll(spec, new Sort(new String[] { "createTime" }));
   }
   @Autowired
   public void setDao(DinerBillDisheDao dao) {
     super.setDao(dao);
     this.dinerBillDisheDao = dao;
   }
 
   public List<DinerBillDishe> getDinerBillDishesByDish(String dishesCode, String restId)
   {
     return this.dinerBillDisheDao.findByRestIdAndDishesCode(restId, dishesCode);
   }
 }

