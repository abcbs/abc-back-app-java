 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.google.common.collect.Maps;
import com.ndlan.canyin.frontdesk.service.BaseService;
 import com.ndlan.canyin.base.entity.ctzh.Role;
 import com.ndlan.canyin.base.repository.ctzh.RoleDao;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import com.ndlan.canyin.core.persistence.SearchFilter.Operator;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class RoleService extends BaseService<RoleDao, Role>
 {
   private RoleDao roleDao;
 
   public List<Role> getAllByRestId(String restId)
   {
     Map filters = Maps.newHashMap();
     filters.put("restId", new SearchFilter("restId", SearchFilter.Operator.EQ, restId));
     Specification spec = DynamicSpecifications.bySearchFilterWithOr(filters.values(), Role.class);
     return this.roleDao.findAll(spec, new Sort(new String[] { "crId" }));
   }
 
   public List<Role> findByRestIdAndRoleType(String restId, String roleType)
   {
     return ((RoleDao)getDao()).findByRestIdAndRoleType(restId, roleType);
   }
   @Autowired
   public void setBaseDao(RoleDao _roleDao) {
     super.setDao(_roleDao);
     this.roleDao = _roleDao;
   }
 }

