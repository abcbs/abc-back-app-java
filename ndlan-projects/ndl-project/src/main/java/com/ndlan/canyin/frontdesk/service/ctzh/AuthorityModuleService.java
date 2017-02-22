 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
 import com.ndlan.canyin.base.repository.ctzh.AuthorityModuleDao;
 import com.ndlan.canyin.base.repository.mybatis.EmployeeMyDao;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class AuthorityModuleService extends BaseService<AuthorityModuleDao, AuthorityModule>
 {
   private AuthorityModuleDao authorityModuleDao;
 
   @Autowired
   EmployeeMyDao employeeMyDao;
 
   @Autowired
   public void setBaseDao(AuthorityModuleDao authorityModuleDao)
   {
     this.authorityModuleDao = authorityModuleDao;
     super.setDao(authorityModuleDao);
   }
 
   public List<AuthorityModule> findAll()
   {
     return this.authorityModuleDao.findAll();
   }
 
   public List<AuthorityModule> getUserAuthorityModule(String id, String restId)
   {
     return this.authorityModuleDao.getUserAuthorityModule(id, restId);
   }
 
   public List<AuthorityModule> getParentAuthorityModule(String id, String restId)
   {
     return this.authorityModuleDao.getParentAuthorityModule();
   }
 
   public List<AuthorityModule> getModulesByCamLevelAndParentCamId(String camLevel, String parentCamId)
   {
     return this.authorityModuleDao.getModulesByCamLevelAndParentCamIdOrderByShowSeqAsc(camLevel, parentCamId);
   }
 
   public List<AuthorityModule> getAuthorityModule(String userId, String restId) {
     return this.employeeMyDao.getAuthorityModule(restId, userId, TrueFalseEnum.FALSE.getCode());
   }
 }

