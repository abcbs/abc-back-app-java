package com.ndlan.canyin.frontdesk.front.controller3c.memberinfo;

import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.Code;
import com.ndlan.canyin.frontdesk.dto3c.IosReturnJson;
import com.ndlan.canyin.frontdesk.dto3c.SearchPageDto;
import com.ndlan.canyin.frontdesk.dto3c.SubCode;
import com.ndlan.canyin.frontdesk.dto3c.memberinfo.MemberCardClassDto;
import com.ndlan.canyin.frontdesk.service.service3c.memberinfo.RestMemberInfo3cService;

/**
 * 
 * @Description:
 * @author: fangmeiyu
 * @date: Jan 8, 2016 6:13:26 PM
 */
@Controller
@RequestMapping({"/member"})
public class MeminfoController  extends BaseManageController{
	
	private static final Logger logger = LoggerFactory.getLogger(MeminfoController.class.getName());
	@Autowired
	private RestMemberInfo3cService restMemberInfo3cService;
	
	/**
	 * 获取会员信息
	 * @param searchValue 搜索条件
	 * @param restId 店id
	 * @param model 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getList")
	public IosReturnJson getMemberList(@RequestParam(value = "searchValue", defaultValue = "")String searchValue,
			@RequestParam(value = "shopId", defaultValue = "")String shopId,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "100") int pageSize,
			@RequestParam(value = "pageUpOrDown", defaultValue = "down") String pageUpOrDown,
			Model model, ServletRequest request){
			logger.info("传入参数searchValue:"+searchValue+"---传入参数shopId:"+shopId+"---传入参数pageNumer:"+pageNumber+"---传入参数pageSize:"+pageSize+"---传入参数pageUpOrDown:"+pageUpOrDown);
			if(StringUtils.isEmpty(shopId)){
				shopId=getCurrentUserRestId();
			}
		    Page page=this.restMemberInfo3cService.getMemberList(shopId,searchValue,pageNumber,pageSize);
		    IosReturnJson json=new IosReturnJson();
			if(page==null){
				logger.info("---------没有数据");
				json=new IosReturnJson(SubCode.NODATA.getCode(),SubCode.NODATA.getDesc());
				return json;
			}
			json.setStatusCode(Code.SUCCESS.getCode());
			json.setMessage(Code.SUCCESS.getDesc());
			SearchPageDto searchPage=this.restMemberInfo3cService.getSearchPageDto(pageNumber,pageSize,pageUpOrDown,searchValue);
			json.setSearchPage(searchPage);
			json.setObjectzJson(page.getContent());
			return json;
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
	 * @param salesmanId 营销员
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public IosReturnJson createMember(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "gender", defaultValue = "") String gender,
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "cardType", defaultValue = "") String cardType,
			@RequestParam(value = "brithdayDay", defaultValue = "") String brithdayDay,
			@RequestParam(value = "edu", defaultValue = "") String edu,
			@RequestParam(value = "work", defaultValue = "") String work,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "notes", defaultValue = "") String notes,
			@RequestParam(value = "salesmanName", defaultValue = "") String salesmanName,
			Model model, ServletRequest request){
		logger.info("传入参数name:"+name+"---传入参数gender:"+gender+"---传入参数mobile:"+mobile+"---传入参数cardType:"+cardType+"传入参数brithdayDay:"+brithdayDay+"---传入参数edu:"+edu+"---传入参数work:"+work+"---传入参数email:"+email+"---传入参数notes:"+notes+"---传入参数salesmanName:"+salesmanName);
		IosReturnJson json = new IosReturnJson();
		if(StringUtils.isEmpty(name)){
			logger.info("参数name不能为空");
			 json =new IosReturnJson(SubCode.DATAISNULL.getCode(),"name"+SubCode.DATAISNULL.getDesc());
			 return json;
		 }else if(StringUtils.isEmpty(gender)){
			 logger.info("参数gender不能为空");
			 json =new IosReturnJson(SubCode.DATAISNULL.getCode(),"gender"+SubCode.DATAISNULL.getDesc());
			 return json;
		 }else if(StringUtils.isEmpty(mobile)){
			 logger.info("参数mobile不能为空");
			 json =new IosReturnJson(SubCode.DATAISNULL.getCode(),"mobile"+SubCode.DATAISNULL.getDesc());
			 return json;
		 }else if(StringUtils.isEmpty(cardType)){
			 logger.info("参数cardType不能为空");
			 json =new IosReturnJson(SubCode.DATAISNULL.getCode(),"cardType"+SubCode.DATAISNULL.getDesc());
			 return json;
		 }
		String flag=this.restMemberInfo3cService.insertMember(getCurrentUserRestId(),name,gender,mobile,cardType,brithdayDay,edu,work,email,notes,salesmanName,getCurrentUser());
		if(flag.equals("fail")){
			logger.info("创建会员失败,cardType参数错误");
			json =new IosReturnJson(SubCode.DATAERROR.getCode(),"cardType"+SubCode.DATAERROR.getDesc());
			return json;
		}
		/*else if(flag.equals("employeeIsNull")){
			logger.info("创建会员失败,salesmanId参数错误");
			json =new IosReturnJson(SubCode.DATAERROR.getCode(),"salesmanId"+SubCode.DATAERROR.getDesc());
			return json;
		}*/
		else if(flag.equals("mobileIsExist")){
			logger.info("创建会员失败,mobile参数已存在");
			json =new IosReturnJson(SubCode.ISEXIST.getCode(),"mobile"+SubCode.ISEXIST.getDesc());
			return json;
		}
		return json;
	}
	/**
	 * 更新会员状态
	 * @param mcId 会员卡主键(非空)
	 * @param cardNo 会员卡号(非空)
	 * @param cardStatus 会员卡状态(0正常1停用2挂失3退卡)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping({"/udpateStatus"})
	public IosReturnJson updateMemberCardStatus(@RequestParam(value="mcId", defaultValue="") String mcId,
			@RequestParam(value="cardNo", defaultValue="") String cardNo,
			@RequestParam(value="cardStatus", defaultValue="") String cardStatus,
			@RequestParam(value="shopId", defaultValue="") String shopId,
			Model model, ServletRequest request){
		logger.info("传入参数mcId:"+mcId+"---传入参数cardNo:"+cardNo+"---传入参数cardStatus:"+cardStatus+"---传入参数shopId:"+shopId);
		IosReturnJson json = new IosReturnJson();
		if(StringUtils.isEmpty(mcId)){
			logger.info("mcId不能为空");
			json = new IosReturnJson(SubCode.DATAISNULL.getCode(),"mcId"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		this.restMemberInfo3cService.updateMemberStatus(mcId,cardNo, getCurrentUserRestId(), cardStatus);
		return json;
		
	}
	/**
	 * 修改会员信息
	 * @param mcId 会员卡Id(必填)
	 * @param cardNo 会员卡号
	 * @param name 姓名
	 * @param gender 性别
	 * @param mobile 手机号
	 * @param cardType 类型
	 * @param brithdayDay 生日
	 * @param edu 学历
	 * @param work 职业
	 * @param email 邮箱
	 * @param notes 备注
	 * @param salesmanId 营销员id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping({"/udpateMember"})
	public IosReturnJson updateMember(@RequestParam(value="mbId", defaultValue="") String mbId,
			@RequestParam(value="cardNo", defaultValue="") String cardNo,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "gender", defaultValue = "") String gender,
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "cardType", defaultValue = "") String cardType,
			@RequestParam(value = "brithdayDay", defaultValue = "") String brithdayDay,
			@RequestParam(value = "edu", defaultValue = "") String edu,
			@RequestParam(value = "work", defaultValue = "") String work,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "notes", defaultValue = "") String notes,
			@RequestParam(value = "salesmanName", defaultValue = "") String salesmanName,
			Model model, ServletRequest request){
		logger.info("传入参数mbId:"+mbId+"---传入参数cardNo:"+cardNo+"传入参数name:"+name+"---传入参数gender:"+gender+"---传入参数mobile:"+mobile+"---传入参数cardType:"+cardType+"传入参数brithdayDay:"+brithdayDay+"---传入参数edu:"+edu+"---传入参数work:"+work+"---传入参数email:"+email+"---传入参数notes:"+notes+"---传入参数salesmanName:"+salesmanName);
		IosReturnJson json = new IosReturnJson();
		if(StringUtils.isEmpty(mbId)){
			logger.info("mbId不能为空");
			json = new IosReturnJson(SubCode.DATAISNULL.getCode(),"mbId"+SubCode.DATAISNULL.getDesc());
			return json;
		}else if(StringUtils.isEmpty(name)){
			logger.info("name不能为空");
			 json =new IosReturnJson(SubCode.DATAISNULL.getCode(),"name"+SubCode.DATAISNULL.getDesc());
			 return json;
		 }else if(StringUtils.isEmpty(gender)){
			 logger.info("gender不能为空");
			 json =new IosReturnJson(SubCode.DATAISNULL.getCode(),"gender"+SubCode.DATAISNULL.getDesc());
			 return json;
		 }else if(StringUtils.isEmpty(mobile)){
			 logger.info("mobile不能为空");
			 json =new IosReturnJson(SubCode.DATAISNULL.getCode(),"mobile"+SubCode.DATAISNULL.getDesc());
			 return json;
		 }else if(StringUtils.isEmpty(cardType)){
			 logger.info("cardType不能为空");
			 json =new IosReturnJson(SubCode.DATAISNULL.getCode(),"cardType"+SubCode.DATAISNULL.getDesc());
			 return json;
		 }
		String flag=this.restMemberInfo3cService.updateMember(mbId,cardNo,getCurrentUserRestId(),name,gender,mobile,cardType,brithdayDay,edu,work,email,notes,salesmanName,getCurrentUser());
		if(flag.equals("mobileIsExist")){
			logger.info("更新会员失败,mobile参数已存在");
			json =new IosReturnJson(SubCode.ISEXIST.getCode(),"mobile"+SubCode.ISEXIST.getDesc());
			return json;
		}
		/*else if(flag.equals("employeeIsNull")){
			logger.info("创建会员失败,salesmanId参数错误");
			json =new IosReturnJson(SubCode.DATAERROR.getCode(),"salesmanId"+SubCode.DATAERROR.getDesc());
			return json;
		}*/
		else if(flag.equals("fail")){
			logger.info("没有找到mcId相关数据,更新失败");
			json =new IosReturnJson(SubCode.UPDATEFAILE.getCode(),SubCode.UPDATEFAILE.getDesc());
			return json;
		}
		return json;
	}
	
	//会员查看消费记录(按卡号或会员姓名查询)ByQipeng
	@RequestMapping({"/getPayList"})
	   public IosReturnJson getItemList(
			   @RequestParam(value="mbId", defaultValue="") String mbId,@RequestParam(value="shopId", defaultValue="") String restId,
			   @RequestParam(value = "page", defaultValue = "1") String pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
			   @RequestParam(value = "searchValue", defaultValue = "") String searchValue,@RequestParam(value = "pageUpOrDown", defaultValue = "down") String pageUpOrDown,
			   Model model, ServletRequest request)
{
		logger.info("开始查询会员查询记录,mbId={},searchValue={}",mbId,searchValue);
		IosReturnJson json = new IosReturnJson();
		if(StringUtils.isBlank(mbId)){
			logger.info("mbId不能为空");
			json = new IosReturnJson(SubCode.DATAISNULL.getCode(),"mbId"+SubCode.DATAISNULL.getDesc());
			return json;
		}
		if(StringUtils.isEmpty(restId)){
			restId=getCurrentUserRestId();
		}
		
			List list=this.restMemberInfo3cService.getPayList(mbId,restId,pageNumber,pageSize,searchValue);
			if(list.size()==0){
				logger.info("与此mbId={}相关的消费记录无数据",mbId);
				json=new IosReturnJson(SubCode.DATAERROR.getCode(),SubCode.DATAERROR.getDesc());
				return json;
			}
			json.setObjectzJson(list);
			SearchPageDto sp=this.restMemberInfo3cService.getSearchPageDto(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), pageUpOrDown, searchValue);
			json.setSearchPage(sp);
		return json;
	}
	/**
	 * 获取会员卡类型类表
	 * @param shopId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping({"/getCardClassList"})
	public IosReturnJson getCardClassList(@RequestParam(value="shopId", defaultValue="") String shopId,
			Model model, ServletRequest request){
		logger.info("开始获取会员卡类型列表,shopId={}",shopId);
		IosReturnJson json = new IosReturnJson();
		List<MemberCardClassDto> list=this.restMemberInfo3cService.getCardTypeList(shopId);
		if(list.size()==0){
			logger.info("没有卡类型数据");
			json=new IosReturnJson(SubCode.DATAERROR.getCode(),SubCode.DATAERROR.getDesc());
			return json;
		}
		json.setObjectzJson(list);
		return json;
		
	}
	public RestMemberInfo3cService getRestMemberInfo3cService() {
		return restMemberInfo3cService;
	}
	public void setRestMemberInfo3cService(
			RestMemberInfo3cService restMemberInfo3cService) {
		this.restMemberInfo3cService = restMemberInfo3cService;
	}
}
