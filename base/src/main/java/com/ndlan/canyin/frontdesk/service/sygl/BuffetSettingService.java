 package com.ndlan.canyin.frontdesk.service.sygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.sygl.BuffetSetting;
 import com.ndlan.canyin.base.repository.sygl.BuffetSettingDao;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
 
 @Service
 @Lazy
 public class BuffetSettingService extends BaseService<BuffetSettingDao, BuffetSetting>
 {
   @Autowired
   public void setDao(BuffetSettingDao dao)
   {
     super.setDao(dao);
   }
 
   public List<BuffetSetting> findByRestIdAndEnableStatus(String restId) {
     return ((BuffetSettingDao)getDao()).findByRestIdAndEnableStatus(restId, TrueFalseEnum.TRUE.getCode());
   }
 
   public List<BuffetSetting> findByRestId(String restId) {
     return ((BuffetSettingDao)getDao()).findByRestId(restId);
   }
 }

