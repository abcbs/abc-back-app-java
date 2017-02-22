 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.qtsy.CashierDeskSetting;
 import com.ndlan.canyin.base.repository.qtsy.CashierDeskSettingDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 @Service
 public class CashierDeskSettingService extends BaseService<CashierDeskSettingDao, CashierDeskSetting>
 {
   @Autowired
   public void setDao(CashierDeskSettingDao dao)
   {
     super.setDao(dao);
   }
 
   public CashierDeskSetting findConmonSettingByRestId(String restId)
   {
     List ls = ((CashierDeskSettingDao)getDao()).findByRestIdAndEmpIdIsNull(restId);
     if ((ls != null) && (ls.size() > 0))
     {
       return (CashierDeskSetting)ls.get(0);
     }
     return null;
   }
 
   public CashierDeskSetting findByRestIdAndEmpId(String restId, String empId) {
     List ls = ((CashierDeskSettingDao)getDao()).findByRestIdAndEmpId(restId, empId);
     if ((ls != null) && (ls.size() > 0))
     {
       return (CashierDeskSetting)ls.get(0);
     }
     return null;
   }
 }

