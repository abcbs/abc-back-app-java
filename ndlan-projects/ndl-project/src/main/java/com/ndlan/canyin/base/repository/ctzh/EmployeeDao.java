package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface EmployeeDao extends BaseDao<Employee, String>
{
  public abstract Employee findByLoginUsernameAndJobStatus(String paramString1, String paramString2);

  public abstract Employee findByMobile(String paramString);

  public abstract Employee findByLoginUsernameAndRestaurant(String paramString, Restaurant paramRestaurant);

  public abstract Employee findByEmpNumAndRestaurant(String paramString, Restaurant paramRestaurant);

  public abstract List<Employee> findByRestaurant(Restaurant paramRestaurant);

  public abstract Employee findByLoginUsernameAndLoginPassword(String paramString1, String paramString2);

  public abstract Employee findByEmpNum(String paramString);

  public abstract Employee findByEmpIdAndRestaurant(String paramString, Restaurant paramRestaurant);

  public abstract Employee findByLoginUsernameAndJobStatusAndRestaurant(String paramString1, String paramString2, Restaurant paramRestaurant);

  @Query("select e from Employee e where e.loginUsername=?1 and e.restaurant.restId=?2")
  public abstract Employee findByLoginUsernameAndJobStatusAndRestaurant(String paramString1, String paramString2);
  
  public abstract List<Employee> findByLoginUsername(String paramString);

  public abstract List<Employee> findByJobStatusAndRestaurant(String paramString, Restaurant paramRestaurant);

  public abstract List<Employee> findByJobStatusAndRestaurantAndSysdataTypeNot(String paramString, Restaurant paramRestaurant, Integer paramInteger);

  @Query("select e from Employee e where e.mobile=?1 and e.authorizationCode=?2")
  public abstract Employee findByEmployeeMobileAndAuthorizationCode(String paramString1, String paramString2);
  @Query("select count(e) from Employee e where e.loginUsername=?1")
  public abstract Long getCountByName(String paramString1);
}

