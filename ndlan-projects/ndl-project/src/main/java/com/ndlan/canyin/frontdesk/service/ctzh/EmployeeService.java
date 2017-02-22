 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.base.entity.ctzh.Role;
 import com.ndlan.canyin.base.repository.ctzh.EmployeeDao;
 import com.ndlan.canyin.base.repository.mybatis.EmployeeMyDao;
 import com.ndlan.canyin.core.common.JobStatusEnum;
 import com.ndlan.canyin.core.common.RoleTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.security.utils.Digests;
 import com.ndlan.canyin.core.utils.Encodes;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang3.StringUtils;
 import org.apache.ibatis.annotations.Param;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageImpl;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class EmployeeService extends BaseService<EmployeeDao, Employee>
 {
   public static final String HASH_ALGORITHM = "SHA-1";
   public static final int HASH_INTERATIONS = 1024;
   private EmployeeDao employeeDao;
 
   @Autowired
   private RestaurantService restaurantService;
   private EmployeeMyDao employeeMyDao;
 
   @Transactional(readOnly=false)
   public Employee findByLoginNameAndRest(String loginUsername, String restId)
   {
     Restaurant restaurant = null;
     if (StringUtils.isEmpty(restId))
     {
       restaurant = (Restaurant)this.restaurantService.findAll().get(0);
     }
     else
     {
       restaurant = (Restaurant)this.restaurantService.getOne(restId);
     }
    // Employee employee = this.employeeDao.findByLoginUsernameAndJobStatusAndRestaurant(loginUsername, TrueFalseEnum.TRUE.getCode(), restaurant);
     Employee employee = this.employeeDao.findByLoginUsernameAndJobStatusAndRestaurant(loginUsername, restId);
     return employee;
   }
 
   @Transactional(readOnly=false)
   public Employee findHideUserByLoginName(String loginUsername) {
     List employees = this.employeeDao.findByLoginUsername(loginUsername);
     if ((employees != null) && (employees.size() > 0))
     {
       Employee e = (Employee)employees.get(0);
       return e;
     }
     return null;
   }
 
   public Employee findByLoginUsernameAndLoginPassword(String loginUsername, String loginPassword, String restId)
   {
     Restaurant restaurant = null;
     if (StringUtils.isEmpty(restId))
     {
       restaurant = (Restaurant)this.restaurantService.findAll().get(0);
     }
     else
     {
       restaurant = (Restaurant)this.restaurantService.getOne(restId);
     }
     Employee employee = this.employeeDao.findByLoginUsernameAndJobStatusAndRestaurant(loginUsername, TrueFalseEnum.TRUE.getCode(), restaurant);
     if (employee != null)
     {
       byte[] salt = Encodes.decodeHex(employee.getSalt());
       byte[] hashPassword = Digests.sha1(loginPassword.getBytes(), salt, 1024);
       String encodePassword = Encodes.encodeHex(hashPassword);
 
       if (encodePassword.equals(employee.getLoginPassword())) {
         return employee;
       }
     }
     return null;
   }
 
   public Page<Employee> searchEmployee(String restId, Map<String, Object> searchParams, String roleType, int pageNumber, int pagzSize, String sortType)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { sortType }));
     List es = this.employeeMyDao.getAll(restId, JobStatusEnum.ONJOB.getCode(), roleType, pageRequest.getOffset(), pageRequest.getPageSize());
     int totalSize = this.employeeMyDao.getAllCount(restId, JobStatusEnum.ONJOB.getCode(), roleType);
     Page page = new PageImpl(es, pageRequest, totalSize);
     return page;
   }
 
   public List<Map<String, String>> getDisheSales(String restId, String startDate, String endDate)
   {
     return this.employeeMyDao.getDisheSales(restId, startDate, endDate);
   }
 
   public List<Map<String, String>> getDisheSetSales(@Param("restId") String restId, @Param("startDate") String startDate, @Param("endDate") String endDate)
   {
     return this.employeeMyDao.getDisheSetSales(restId, startDate, endDate);
   }
 
   public List<Map<String, Object>> getDishesSalesDetail(@Param("restId") String restId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("categoryId") String categoryId)
   {
     return this.employeeMyDao.getDishesSalesDetail(restId, startDate, endDate, categoryId);
   }
 
   public List<Map<String, Object>> getDishesSetSalesDetail(@Param("restId") String restId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("dsCategoryId") String dsCategoryId)
   {
     return this.employeeMyDao.getDishesSetSalesDetail(restId, startDate, endDate, dsCategoryId);
   }
 
   public EmployeeDao getEmployeeDao() {
     return this.employeeDao;
   }
   @Autowired
   public void setEmployeeDao(EmployeeDao employeeDao) { super.setDao(employeeDao);
     this.employeeDao = employeeDao; }
 
   public List<Employee> findAllByRestaurant(Restaurant restaurant)
   {
     return this.employeeDao.findByRestaurant(restaurant);
   }
 
   public boolean isSuperRole(Employee employee, String restId)
   {
     List<Role> roles = employee.getRoleList();
     if (roles != null)
     {
       for (Role r : roles)
       {
         if ((r.getRoleType().equals(RoleTypeEnum.ADMIN.getCode())) || 
           (r.getRoleType().equals(RoleTypeEnum.PM.getCode())))
         {
           return true;
         }
       }
     }
     return false;
   }
 
   public EmployeeMyDao getEmployeeMyDao()
   {
     return this.employeeMyDao;
   }
   @Autowired
   public void setEmployeeMyDao(EmployeeMyDao employeeMyDao) { this.employeeMyDao = employeeMyDao;
   }
   public void entryptPassword(Employee employee)
   {
     byte[] salt = Digests.generateSalt(8);
     employee.setSalt(Encodes.encodeHex(salt));
     employee.setPlainPassword(employee.getLoginPassword());
     byte[] hashPassword = Digests.sha1(employee.getPlainPassword().getBytes(), salt, 1024);
     employee.setLoginPassword(Encodes.encodeHex(hashPassword));
   }
   public Page<Employee> searchEmployeeByRoleType(String restId, String searchValue, String roleType, int pageNumber, int pagzSize, String sortType)
   {
     Sort.Direction d = Sort.Direction.ASC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, new String[] { sortType }));
     List es = this.employeeMyDao.getAllByRoleType(restId, JobStatusEnum.ONJOB.getCode(), roleType,searchValue, pageRequest.getOffset(), pageRequest.getPageSize());
     int totalSize = this.employeeMyDao.getAllCount(restId, JobStatusEnum.ONJOB.getCode(), roleType);
     Page page = new PageImpl(es, pageRequest, totalSize);
     return page;
   }
 }

