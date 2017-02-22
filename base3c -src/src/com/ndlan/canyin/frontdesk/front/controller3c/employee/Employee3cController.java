package com.ndlan.canyin.frontdesk.front.controller3c.employee;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alipay.api.internal.util.StringUtils;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.Code;
import com.ndlan.canyin.frontdesk.dto3c.IosReturnJson;
import com.ndlan.canyin.frontdesk.dto3c.SearchPageDto;
import com.ndlan.canyin.frontdesk.dto3c.SubCode;
import com.ndlan.canyin.frontdesk.dto3c.employee.EmployeeDto;
import com.ndlan.canyin.frontdesk.service.service3c.employee.Employee3cService;
/**
 * @Description:
 * @author: fangmeiyu
 * @date: Jan 10, 2016 12:51:07 PM
 */
@Controller
@RequestMapping("/cashier")
public class Employee3cController extends BaseManageController{

	private static final Logger logger = LoggerFactory.getLogger(Employee3cController.class.getName());
	@Autowired
	private Employee3cService employee3cService;
	
	/**
	 * 获取收银员信息列表
	 * @param searchValue 搜索条件
	 * @param restId 店Id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/getList")
	public IosReturnJson getEmployeeList(@RequestParam(value="searchValue", defaultValue="") String searchValue,
			@RequestParam(value = "shopId", defaultValue = "") String shopId,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
			@RequestParam(value = "pageUpOrDown", defaultValue = "down") String pageUpOrDown,
			Model model, ServletRequest request){
		logger.info("传入参数searchValue:"+searchValue+"---传入参数shopId:"+shopId+"---传入参数pageNumer:"+pageNumber+"---传入参数pageSize:"+pageSize+"---传入参数pageUpOrDown:"+pageUpOrDown);
		if(StringUtils.isEmpty(shopId)){
			shopId=getCurrentUserRestId();
		}
		List<EmployeeDto> list=this.employee3cService.getEmployeeListByRestId(shopId,searchValue,pageNumber,pageSize);
		IosReturnJson json = new IosReturnJson();
		if(list.size()==0){
			logger.info("---------没有数据");
			json=new IosReturnJson(SubCode.NODATA.getCode(),SubCode.NODATA.getDesc());
			return json;
		}
		json.setStatusCode(Code.SUCCESS.getCode());
		json.setMessage(Code.SUCCESS.getDesc());
		SearchPageDto searchPage=this.employee3cService.getSearchPageDto(pageNumber,pageSize,pageUpOrDown,searchValue);
		json.setSearchPage(searchPage);
		json.setObjectzJson(list);
		return json;
	}
	/**
	 * 添加收银员
	 * @param empNum 员工工号
	 * @param name 姓名
	 * @param mobile 手机号
	 * @param loginPassword 密码
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public IosReturnJson addEmployee(@RequestParam(value = "empNum", defaultValue = "") String empNum,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "loginPassword", defaultValue = "") String loginPassword,
			Model model, ServletRequest request){
		logger.info("传入参数empNum:"+empNum+"---传入参数name:"+name+"---传入参数mobile:"+mobile+"---传入参数loginPassword:"+loginPassword);
		IosReturnJson json = new IosReturnJson();
		if(StringUtils.isEmpty(empNum)){
			logger.info("参数empNum不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"empNum"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		if(StringUtils.isEmpty(name)){
			logger.info("参数name不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"name"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		if(StringUtils.isEmpty(mobile)){
			logger.info("参数mobile不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"mobile"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		if(StringUtils.isEmpty(loginPassword)){
			logger.info("参数loginPassword不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"loginPassword"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		String flag=this.employee3cService.insertEmployee(empNum, name, mobile, loginPassword, getCurrentUserRestId(),getCurrentUser());
		if(flag.equals("empNumIsExist")){
			logger.info("empNum:"+empNum+"已经存在");
			json=new IosReturnJson(SubCode.ISEXIST.getCode(),"empNum"+SubCode.ISEXIST.getDesc());
			return json;
		}else if(flag.equals("headerError")){
			logger.info("创建头信息失败");
			json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
			return json;
		}
		return json;
	}
	/**
	 * 根据payerId获取该收银员的信息
	 * @param payerId 收银员主键
	 * @param model 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOne")
	public IosReturnJson getEmployeeByEmpId(@RequestParam(value = "payerId", defaultValue = "") String payerId,
			Model model, ServletRequest request){
		logger.info("传入参数payerId:"+payerId+"---");
		IosReturnJson json = new IosReturnJson();
		if(StringUtils.isEmpty(payerId)){
			logger.info("参数payerId不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"payerId"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		List<EmployeeDto> list=this.employee3cService.getEmployeeByEmpId(payerId);
		if(list==null){
			logger.info("参数错误");
			json=new IosReturnJson(SubCode.DATAERROR.getCode(),"payerId"+SubCode.DATAERROR.getDesc());
			return json;
		}
		if(list.size()==0){
			logger.info("没有数据");
			json=new IosReturnJson(SubCode.NODATA.getCode(),SubCode.NODATA.getDesc());
			return json;
		}
		json.setObjectzJson(list);
		return json;
	}
	/**
	 * 更新收银员信息
	 * @param payerId 收银员主键
	 * @param name 姓名
	 * @param mobile 手机号
	 * @param loginPassword  密码
	 * @param status 状态
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public IosReturnJson updateEmployee(@RequestParam(value = "payerId", defaultValue = "") String payerId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "loginPassword", defaultValue = "") String loginPassword,
			@RequestParam(value = "status", defaultValue = "") String status,
			Model model, ServletRequest request){
		logger.info("传入参数payerId:"+payerId+"---传入参数name:"+name+"---传入参数mobile:"+mobile+"---传入参数loginPassword:"+loginPassword+"---传入参数status:"+status);
		IosReturnJson json = new IosReturnJson();
		if(StringUtils.isEmpty(payerId)){
			logger.info("参数payerId不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"payerId"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		if(StringUtils.isEmpty(name)){
			logger.info("参数name不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"name"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		if(StringUtils.isEmpty(mobile)){
			logger.info("参数mobile不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"mobile"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		if(StringUtils.isEmpty(loginPassword)){
			logger.info("参数loginPassword不能为空");
			json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"loginPassword"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		String flag=this.employee3cService.updateEmployee(payerId, name, mobile, loginPassword, status,getCurrentUser());
		if(flag.equals("fail")){
			logger.info("没有找到payerId相关数据,更新失败");
			json=new IosReturnJson(SubCode.UPDATEFAILE.getCode(),SubCode.UPDATEFAILE.getDesc());
			return json;
		}else if(flag.equals("passwordError")){
			logger.info("密码错误");
			json=new IosReturnJson(SubCode.DATAERROR.getCode(),"loginPassword"+SubCode.DATAERROR.getDesc());
			return json;
		}
		return json;
	}
	public Employee3cService getEmployee3cService() {
		return employee3cService;
	}
	public void setEmployee3cService(Employee3cService employee3cService) {
		this.employee3cService = employee3cService;
	}
}
