package com.ndlan.canyin.frontdesk.service.service3c.employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.internal.util.StringUtils;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Role;
import com.ndlan.canyin.base.repository.dao3c.employee.Employee3cDao;
import com.ndlan.canyin.frontdesk.dto3c.SearchPageDto;
import com.ndlan.canyin.frontdesk.dto3c.employee.EmployeeDto;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.frontdesk.service.ctzh.RoleService;
import com.ndlan.canyin.frontdesk.service.service3c.shoppingcart.CartService;
import com.ndlan.canyin.core.security.utils.Digests;
import com.ndlan.canyin.core.utils.Encodes;
/**
 * @Description:
 * @author: fangmeiyu
 * @date: Jan 10, 2016 12:51:20 PM
 */
@Service
@Transactional
public class Employee3cService extends BaseService<Employee3cDao,Employee>{
	
	private static final Logger logger = LoggerFactory.getLogger(Employee3cService.class.getName());
	@Autowired
	private Employee3cDao employee3cDao;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private CartService cartService;
	
	/**
	 * 查询全部员工信息
	 * @param restId 店id
	 * @return
	 */
	public List<EmployeeDto> getEmployeeListByRestId(String restId,String searchValue,int pageNumber,int pageSize){
//		PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize);
		searchValue="%"+searchValue+"%";
//		List<Employee> list=this.employee3cDao.getEmployeeListByRestIdAndSearchValue(restId, searchValue);
//		map.put("LIKE_name", searchValue);
		Page page=this.employeeService.searchEmployeeByRoleType(restId, searchValue, "0", pageNumber, pageSize, "desc");
		List<Employee> list=page.getContent();
//		long a =this.employee3cDao.getCountByRestId(restId);//获取总记录条数
//		int count=Integer.parseInt(String.valueOf(a));
		List<EmployeeDto> lis=new ArrayList();
		if(list.size()!=0){
			for(Employee e:list){
				EmployeeDto employeeDto=new EmployeeDto();
				if(!StringUtils.isEmpty(e.getEmpId())){
					employeeDto.setPayerId(e.getEmpId());
				}
				SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String createTime=simple.format(e.getCreateTime());
				if(!StringUtils.isEmpty(createTime)){
					employeeDto.setCreateBy(createTime);
				}
				if(!StringUtils.isEmpty(e.getEmpNum())){
					employeeDto.setEmpNum(e.getEmpNum());
				}
				if(!StringUtils.isEmpty(e.getJobPic())){
					employeeDto.setJobPic(e.getJobPic());
				}
				if(!StringUtils.isEmpty(e.getLoginPassword())){
					employeeDto.setLoginPassword(e.getLoginPassword());
				}
				if(!StringUtils.isEmpty(e.getMobile())){
					employeeDto.setMobile(e.getMobile());
				}
				if(!StringUtils.isEmpty(e.getName())){
					employeeDto.setName(e.getName());
				}
				if(!StringUtils.isEmpty(e.getRestaurantId())){
					employeeDto.setShopId(e.getRestaurantId());
				}
				if(!StringUtils.isEmpty(e.getJobStatus())){
					employeeDto.setStatus(e.getJobStatus());
				}
				lis.add(employeeDto);
			}
		}
//			Page page = new PageImpl(lis, pageRequest, count);
			
			return lis;
//		}
//		return null;
	}
	/**
	 * 创建收银员
	 * @param empNum 员工工号
	 * @param name 员工姓名
	 * @param mobile 手机号
	 * @param loginPassword 密码
	 * @param restId
	 */
	public String insertEmployee(String empNum,String name,String mobile,String loginPassword,String restId,Employee createBy){
		Employee emp=new Employee();
		if(!StringUtils.isEmpty(restId)){
			emp.setRestaurantId(restId);
		}
		if(!StringUtils.isEmpty(empNum)){
			Employee employee=this.employee3cDao.checkEmpNum(empNum);
			if(employee==null){
				emp.setEmpNum(empNum);
			}else{
				return "empNumIsExist";
			}
		}
		emp.setName(name);
		emp.setMobile(mobile);
		byte[] salt = Digests.generateSalt(8);
	    emp.setSalt(Encodes.encodeHex(salt));
	    emp.setPlainPassword(loginPassword);
	    byte[] hashPassword = Digests.sha1(emp.getPlainPassword().getBytes(), salt, 1024);
	    emp.setLoginPassword(Encodes.encodeHex(hashPassword));
		emp.setLoginUsername(empNum);
		emp.setCreateEmployee(createBy);
		emp.setCreateTime(new Date());
		emp.setJobStatus("1");
		Role role=this.roleService.getOne("402881044fab8a83014fab9736aa0001");
		List list=new ArrayList();
		list.add(role);
		emp.setRoleList(list);
		Employee employee=this.employeeService.save(emp);
		if(!this.cartService.isAddCart(restId, employee.getEmpId(), "wangwei", "123")){
			return "headerError";
		}
		return "success";
	}
	
	/**
	 * 根据员工id获取该员工信息
	 * @param empId 员工id
	 * @return
	 */
	public List<EmployeeDto> getEmployeeByEmpId(String empId){
		Employee emp=this.employeeService.getOne(empId);
		if(emp==null){
			return null;
		}
		List<EmployeeDto> list=new ArrayList();
		EmployeeDto empDto=new EmployeeDto();
		SimpleDateFormat  simple=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String createTime=simple.format(emp.getCreateTime());
		if(!StringUtils.isEmpty(createTime)){
			empDto.setCreateBy(createTime);
		}
		if(!StringUtils.isEmpty(emp.getEmpNum())){
			empDto.setEmpNum(emp.getEmpNum());
		}
		if(!StringUtils.isEmpty(emp.getJobPic())){
			empDto.setJobPic(emp.getJobPic());
		}
		if(!StringUtils.isEmpty(emp.getLoginPassword())){
			empDto.setLoginPassword(emp.getLoginPassword());
		}
		if(!StringUtils.isEmpty(emp.getMobile())){
			empDto.setMobile(emp.getMobile());
		}
		if(!StringUtils.isEmpty(emp.getName())){
			empDto.setName(emp.getName());
		}
		if(!StringUtils.isEmpty(emp.getEmpId())){
			empDto.setPayerId(emp.getEmpId());
		}
		if(!StringUtils.isEmpty(emp.getRestaurantId())){
			empDto.setShopId(emp.getRestaurantId());
		}
		if(!StringUtils.isEmpty(emp.getJobStatus())){
			empDto.setStatus(emp.getJobStatus());
		}
		list.add(empDto);
		return list;
	}
	
	/**
	 * 更新收银员信息
	 * @param empId 收银员主键
	 * @param name 姓名
	 * @param mobile 手机号
	 * @param loginPassword 密码
	 * @param status 状态
	 * @return
	 */
	public String updateEmployee(String empId,String name,String mobile,String loginPassword,String status,Employee UpdateBy){
		Employee emp=this.employeeService.getOne(empId);
		if(emp!=null){
			emp.setName(name);
			emp.setMobile(mobile);
			if(!emp.getPlainPassword().equals(loginPassword)){
				return "passwordError";
			}
			byte[] salt = Digests.generateSalt(8);
		    emp.setSalt(Encodes.encodeHex(salt));
		    emp.setPlainPassword(loginPassword);
		    byte[] hashPassword = Digests.sha1(emp.getPlainPassword().getBytes(), salt, 1024);
		    emp.setLoginPassword(Encodes.encodeHex(hashPassword));
			if(!StringUtils.isEmpty(status)){
				emp.setJobStatus(status);
			}
			emp.setUpdateEmployee(UpdateBy);
			emp.setUpdateTime(new Date());
			this.employeeService.save(emp);	
			return "success";
		}
		return "fail";
	}
	
	
	
	public Employee3cDao getEmployee3cDao() {
		return employee3cDao;
	}

	public void setEmployee3cDao(Employee3cDao employee3cDao) {
		this.employee3cDao = employee3cDao;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public SearchPageDto getSearchPageDto(int pageNumber, int pageSize,
			String pageUpOrDown, String searchValue) {
		SearchPageDto searchPage = new SearchPageDto();
		searchPage.setSearchValue(searchValue);
		searchPage.setPage(pageNumber+"");
		searchPage.setPageSize(pageSize+"");
		searchPage.setPageUpOrDown(pageUpOrDown);
		return searchPage;
	}
	public CartService getCartService() {
		return cartService;
	}
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
}
