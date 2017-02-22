 package com.ndlan.canyin.frontdesk.service.xtgl;
 
 import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.entity.xtgl.SysLog;
import com.ndlan.canyin.base.repository.xtgl.SysLogDao;
import com.ndlan.canyin.frontdesk.service.BaseService;
 
 @Component
 @Transactional(readOnly=true)
 public class SysLogService extends BaseService<SysLogDao, SysLog>
 {
   @Autowired
   public void setBaseDao(SysLogDao sysLogDao)
   {
     super.setDao(sysLogDao);
   }

public List<Table> logoutCheck(String currentUserRestId) {
	// TODO Auto-generated method stub
	return  getDao().logoutCheck(currentUserRestId);
}
 }

