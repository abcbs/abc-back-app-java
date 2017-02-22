package com.ndlan.canyin.frontdesk.service.service3c.memberinfo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.base3c.employee.BaseMembershipCardClass;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.repository.hygl.RestMemberInfoDao;
import com.ndlan.canyin.base.repository.mybatis.RestMemberInfo3cMyDao;
import com.ndlan.canyin.frontdesk.dto3c.SearchPageDto;
import com.ndlan.canyin.frontdesk.dto3c.memberinfo.MemberCardClassDto;
import com.ndlan.canyin.frontdesk.dto3c.memberinfo.MemberInfoEntityDto;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardClassService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.frontdesk.service.service3c.billinfo.Bill3cService;
import com.ndlan.canyin.frontdesk.service.service3c.productinfo.PayItemService;
/**
 * 
 * @Description:
 * @author: fangmeiyu
 * @date: Jan 9, 2016 2:10:40 PM
 */
@Service
@Transactional
public class RestMemberInfo3cService extends BaseService<RestMemberInfoDao, RestMemberInfo>{
	
	private static final Logger logger = LoggerFactory.getLogger(RestMemberInfo3cService.class.getName());
	
	 @Autowired
	   private RestMemberInfo3cMyDao restMemberInfo3cMyDao;
	 @Autowired
	   private MembershipCardService membershipCardService;
	 @Autowired
	 private BaseMemberShipCardClass3cService baseMemberCardClassService;
	 @Autowired
	   private EmployeeService employeeService;
	 @Autowired
	 private Bill3cService bill3cService;
	 @Autowired
	 private PayItemService payItemService;
	 @Autowired
	 private MembershipCardClassService membershipCardClassService;
	 @Autowired
	 private RestMemberInfoService restMemberInfoService;
	 private RestMemberInfoDao restMemberInfoDao;
	 @Autowired
	   public void setDao(RestMemberInfoDao dao)
	   {
	     super.setDao(dao);
	     this.restMemberInfoDao = dao;
	   }
	 
	/**
	  * 获取会员列表
	  * @param searchValue 搜索条件(姓名，卡号，手机号)
	  * @return
	  */

	   public Page getMemberList(String restId,String searchValue,int pageNumber,int pageSize){
		   if(StringUtils.isBlank(searchValue)){
			   searchValue=null;
		   }else{
			   searchValue="%"+searchValue+"%";
		   }
		   PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize);
		   List<MemberInfoEntityDto> list=this.restMemberInfo3cMyDao.getMemberList(restId,searchValue,pageRequest.getOffset(),pageRequest.getPageSize());
		   int count=this.restMemberInfo3cMyDao.countMemberList(restId,searchValue);
		   Page page = new PageImpl(list, pageRequest, count);
		   return page;
	   }
	   /**
	    * 创建会员
	    * @param name 姓名(必填)
	    * @param gender 性别(必填)
	    * @param mobile 手机号(必填)
	    * @param cardType 类型(必填)
	    * @param brithdayDay 生日
	    * @param edu 学历
	    * @param work 职业
	    * @param email 邮箱
	    * @param notes 备注
	    * @param salesmanId 营销员Id
	    */
	 public String insertMember(String restId,String name,String gender,String mobile,String cardType,String brithdayDay,String edu,
			 String work,String email,String notes,String salesmanName,Employee createBy){
		 RestMemberInfo memberInfo=new RestMemberInfo();
		 memberInfo.setName(name);
		 memberInfo.setGender(gender);
		//验证电话号是否存在
		 List<RestMemberInfo> mobileIsExist=this.restMemberInfoDao.findByMobile(mobile);
		 if(mobileIsExist.size()==0){//不存在，添加mobile
			 memberInfo.setMobile(mobile);
		 }else{//存在返回mobile已存在
			 return "mobileIsExist";
		 }
		 memberInfo.setRestId(restId);
		 memberInfo.setCreateTime(new Date());
		 memberInfo.setCreateEmployee(createBy);
		 SimpleDateFormat  formate=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		 if(brithdayDay!=null&&brithdayDay.length()!=0){
			 try {
				 memberInfo.setBirthday(formate.parse(brithdayDay));
				} catch (ParseException e) {
					logger.error("------------ insertMember 格式化日期");
					e.printStackTrace();
				}
		 }
		memberInfo.setEdu(edu);
		memberInfo.setWork(work);
		memberInfo.setEmail(email);
		memberInfo.setNotes(notes);
		 /*if(salesmanId!=null&&salesmanId.length()!=0){
			 Employee employee=this.employeeService.getOne(salesmanId);
			 if(employee!=null){
				 memberInfo.setSalesman(employee);
			 }else{
				 return "employeeIsNull";
			 }
		 }*/
		 Employee employee=new Employee();
		 employee.setName(salesmanName);
		 this.employeeService.save(employee);
		 memberInfo.setSalesman(employee);
		 this.restMemberInfoDao.save(memberInfo);
		 MembershipCard memberCard=new MembershipCard();
		//验证类型是否正确
		 MembershipCardClass memberCardClass=this.membershipCardClassService.getOne(cardType);
		 if(memberCardClass!=null){//类型正确添加
			 memberCard.setMembershipCardClass(memberCardClass);
		 }else{//类型不正确，返回失败
			 return "fail";
		 }
		 memberCard.setBalance(new BigDecimal(0));
		 memberCard.setCardNo(mobile);
		 memberCard.setCardStatus("0");
		 memberCard.setMemberIntegral(new BigDecimal(0));
		 memberCard.setRestMemberInfo(memberInfo);
		 this.membershipCardService.save(memberCard);
		 return "success";
	 }
	 /**
	  * 更新会员状态
	  * @param mcId 会员卡主键(非空)
	  * @param cardNo 卡号
	  * @param restId 店id
	  * @param cardStatus 卡状态(0正常1停用2挂失3退卡)
	  */
	 
	 public void updateMemberStatus(String mcId,String cardNo,String restId,String cardStatus){
//		 List<MembershipCard> memberCardList=this.membershipCardService.findByCardNo(cardNo, restId);//根据电话号码修改会员状态
//		 MembershipCard member=this.membershipCardService.getOne(memberCardList.get(0).getMcId());
		 MembershipCard member=this.membershipCardService.getOne(mcId);
		 if(!StringUtils.isEmpty(cardStatus)){
			 member.setCardStatus(cardStatus);
		 }
		 this.membershipCardService.save(member);
	 }
	 /**
	  * 修改会员信息
	  * @param cardNo 卡号
	  * @param restId 店Id
	  * @param name 姓名
	  * @param gender 性别
	  * @param mobile 手机
	  * @param cardType 卡类型
	  * @param brithdayDay 生日
	  * @param edu 学历
	  * @param work 职业
	  * @param email 邮箱
	  * @param notes 备注
	  * @param salesmanId 营销员
	  */
	 public String updateMember(String mbId,String cardNo, String restId,String name,String gender,String mobile,String cardType,
			 String brithdayDay,String edu,String work,String email,String notes,String salesmanName,Employee updateBy){
		 RestMemberInfo restMemberInfo=this.restMemberInfoService.getOne(mbId);
		// MembershipCard memberCard=this.membershipCardService.getOne(mcId);
		 Set<MembershipCard> memberSet= restMemberInfo.getMembershipCards();
		 Iterator<MembershipCard> iter=memberSet.iterator();
		 MembershipCard memberCard=null;
		 if(iter.hasNext()){
			 MembershipCard m=iter.next();
			 memberCard=this.membershipCardService.getOne(m.getMcId());
		 }
		 
		 if(restMemberInfo!=null){
		//	RestMemberInfo restMemberInfo=memberCard.getRestMemberInfo();
			restMemberInfo.setName(name);
			restMemberInfo.setGender(gender);
			//验证电话号是否存在
			List<RestMemberInfo> mobileList=this.restMemberInfoDao.findByMobile(mobile);
			if(mobileList.size()==0){//不存在，更新mobile
				restMemberInfo.setMobile(mobile);
			}else{//存在返回mobile已存在
				 return "mobileIsExist";
			}
			if(brithdayDay!=null&&brithdayDay.length()!=0){
				SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
				try {
					restMemberInfo.setBirthday(simple.parse(brithdayDay));
				} catch (ParseException e) {
					logger.error("------------ 格式化日期错误");
					e.printStackTrace();
				}
			}
			if(edu!=null&&edu.length()!=0){
				restMemberInfo.setEdu(edu);
			}
			if(work!=null&&work.length()!=0){
				restMemberInfo.setWork(work);
			}
			if(email!=null&&email.length()!=0){
				restMemberInfo.setEmail(email);
			}
			if(notes!=null&&notes.length()!=0){
				restMemberInfo.setNotes(notes);
			}
			/*if(salesmanId!=null&&salesmanId.length()!=0){
				Employee employee=this.employeeService.getOne(salesmanId);
				if(employee!=null){
					restMemberInfo.setSalesman(employee);
				}else{
					return "employeeIsNull";
				}
			}*/
			 Employee employee=new Employee();
			 employee.setName(salesmanName);
			 this.employeeService.save(employee);
			 restMemberInfo.setSalesman(employee);
			restMemberInfo.setUpdateEmployee(updateBy);
			restMemberInfo.setUpdateTime(new Date());
			this.restMemberInfoDao.save(restMemberInfo);
			memberCard.setRestMemberInfo(restMemberInfo);
			if(mobile!=null&&mobile.length()!=0){
				memberCard.setCardNo(mobile);
			}
			this.membershipCardService.save(memberCard);
			return "success";
		 }
		 return "fail";
	 }
	 /**
	  * 获取会员卡类型列表
	  * @param shopId
	  * @return
	  */
	 public List<MemberCardClassDto> getCardTypeList(String shopId){
		 if(!StringUtils.isEmpty(shopId)){//di
			 List<MembershipCardClass> list=this.membershipCardClassService.findMemberCardClassByRestId(shopId);
			 if(list.size()!=0){
				 List<MemberCardClassDto> memberCardClassDtoList=new ArrayList();
				 for(MembershipCardClass cardClass:list){
					 MemberCardClassDto memberCardClass=new MemberCardClassDto();
					 memberCardClass.setMcclassId(cardClass.getMcclassId());
					 memberCardClass.setName(cardClass.getName());
					 memberCardClassDtoList.add(memberCardClass);
				 }
				 return memberCardClassDtoList;
			 }
			 List<BaseMembershipCardClass> list1=this.baseMemberCardClassService.findAllBaseMembershipCardClass();
			 List<MemberCardClassDto> memberCardClassDtoList=new ArrayList();
			 for(BaseMembershipCardClass cardClass:list1){
				 MemberCardClassDto memberCardClass=new MemberCardClassDto();
				 memberCardClass.setMcclassId(cardClass.getMcclassId());
				 memberCardClass.setName(cardClass.getName());
				 memberCardClassDtoList.add(memberCardClass);
			 }
			 return memberCardClassDtoList;
		 }
			 List<BaseMembershipCardClass> list=this.baseMemberCardClassService.findAllBaseMembershipCardClass();
			 List<MemberCardClassDto> memberCardClassDtoList=new ArrayList();
			 for(BaseMembershipCardClass cardClass:list){
				 MemberCardClassDto memberCardClass=new MemberCardClassDto();
				 memberCardClass.setMcclassId(cardClass.getMcclassId());
				 memberCardClass.setName(cardClass.getName());
				 memberCardClassDtoList.add(memberCardClass);
			 }
			 return memberCardClassDtoList;
		 
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
	 
	 public Bill3cService getBill3cService() {
		return bill3cService;
	}

	public void setBill3cService(Bill3cService bill3cService) {
		this.bill3cService = bill3cService;
	}

	public RestMemberInfo3cMyDao getRestMemberInfo3cMyDao() {
		return restMemberInfo3cMyDao;
	}

	public void setRestMemberInfo3cMyDao(RestMemberInfo3cMyDao restMemberInfo3cMyDao) {
		this.restMemberInfo3cMyDao = restMemberInfo3cMyDao;
	}

	public MembershipCardService getMembershipCardService() {
		return membershipCardService;
	}

	public void setMembershipCardService(MembershipCardService membershipCardService) {
		this.membershipCardService = membershipCardService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public MembershipCardClassService getMembershipCardClassService() {
		return membershipCardClassService;
	}

	public void setMembershipCardClassService(
			MembershipCardClassService membershipCardClassService) {
		this.membershipCardClassService = membershipCardClassService;
	}

	public RestMemberInfoDao getRestMemberInfoDao() {
		return restMemberInfoDao;
	}

	public void setRestMemberInfoDao(RestMemberInfoDao restMemberInfoDao) {
		this.restMemberInfoDao = restMemberInfoDao;
	}

	/**
	 * 会员查询消费记录
	 * @param mbId
	 * @param restId
	 * @param pageNumber
	 * @param pageSize
	 * @param searchValue
	 * @return
	 */
	public List getPayList(String mbId, String restId, String pageNumber,
			String pageSize, String searchValue) {
		return this.payItemService.getPayList(mbId, restId, pageNumber, pageSize, searchValue);
	}
	
}
