package com.ndlan.canyin.base.repository.dao3c.employee;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.repository.BaseDao;
/**
 * @Description:
 * @author: fangmeiyu
 * @date: Jan 10, 2016 12:51:28 PM
 */
public interface Employee3cDao extends BaseDao<Employee,String>{

	@Query("select b from Employee b where b.restaurant.restId=? and b.empNum like ?2 or b.name like ?2")
	List<Employee> getEmployeeListByRestIdAndSearchValue(String paramString1,String paramString2);
	@Query("select b from Employee b where b.empNum=?")
	Employee checkEmpNum(String empNum);
	@Query("select count(b) from Employee b where b.restaurant.restId=?1")
	public abstract Long getCountByRestId(String paramString1);
}
