 package com.ndlan.canyin.frontdesk.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.Code;
import com.ndlan.canyin.frontdesk.dto3c.IosReturnJson;
import com.ndlan.canyin.frontdesk.dto3c.LoginModel;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.core.common.AuthEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @RequestMapping({"/login"})
 public class LoginController extends BaseManageController
 {
	 private static final Logger logger = LoggerFactory.getLogger(LoginController.class.getName());
 
   @Autowired
   protected EmployeeService employeeService;
   
   @Autowired
   private RestaurantService restaurantService;
   @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String login(Model model)
   {
     List rs = this.restaurantService.findAll();
     if ((rs != null) && (rs.size() > 0))
     {
       Restaurant e = (Restaurant)rs.get(0);
       model.addAttribute("restName", e.getRestName());
     }
     model.addAttribute("restaurants", rs);
     return "login";
   }
 
   @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
   public Object fail(@RequestParam("username") String userName, @RequestParam(value="from", required=false) String from, Model model) {
     if ("mobile".equals(from)) {
//       return "redirect:/login/mobileFail";
    	 IosReturnJson json = new IosReturnJson();
  	     json = new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
  	     return json;
     }
 
     String[] array = userName.split(",");
     if (array.length == 0) {
       return "login";
     }
     userName = array[0];
     String restId = array.length > 1 ? array[1] : null;
     if (SecurityUtils.getSubject().isAuthenticated())
     {
       return "redirect:/index";
     }
     model.addAttribute("restId", restId);
     model.addAttribute("username", userName);
     List rs = this.restaurantService.findAll();
     if ((rs != null) && (rs.size() > 0))
     {
       Restaurant e = (Restaurant)rs.get(0);
       model.addAttribute("restName", e.getRestName());
     }
     model.addAttribute("restaurants", rs);
     return "login";
   }
 
   @RequestMapping({"success"})
   public Object success(Model model, @RequestParam(value="from", required=false) String from,String username ,HttpServletRequest request, HttpServletResponse response) {
     try {
         if ("mobile".equals(from)) {
         return "redirect:/login/mobileSucess?username="+username;
       	}
       return "redirect:/index";
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return "redirect:/index";
   }
   @RequestMapping({"mobileSucess"})
   @ResponseBody
   /**
    * 登陆成功
    * @param username 用户名
    * @param model
    * @param request
    * @param response
    * @return
    */
   public IosReturnJson mobileSucess(String username,Model model, HttpServletRequest request, HttpServletResponse response) {
	  /* String[] array = username.split(",");
	   username = array[0];
	   Object[] r=restaurantService.getMid(username);
	   List<RestaurantPic> r2=restaurantService.getrestpic(r[1]+"");
	   String name=""; */
	   IosReturnJson json = new IosReturnJson();
	   LoginModel login=new LoginModel();
	   Employee employeeShior=getCurrentUser();
	   if(employeeShior!=null){
		   Employee employee= this.employeeService.getOne(employeeShior.getEmpId());
		   String status=employee.getJobStatus();
		   if(status.equals("0")){
			   logger.info("员工账号被关闭");
			   json = new IosReturnJson(Code.FAILE.getCode(),"账号被关闭"+
					   Code.FAILE.getDesc());
			   return json;
		   }
		   Restaurant restaurant=this.restaurantService.getOne(employee.getRestaurantId());
		   if(restaurant.getMid()!=null&&restaurant.getMid().length()!=0&&restaurant.getAdrDetail()!=null&&
				   restaurant.getAdrDetail().length()!=0&&restaurant.getLinkmanPhone()!=null&&restaurant.getLinkmanPhone().length()!=0
				   &&employee.getName()!=null&&employee.getName().length()!=0&&employee.getRestaurantId()!=null&&employee.getRestaurantId().length()!=0&&restaurant.getRestLogo()!=null&&
						   restaurant.getRestLogo().length()!=0&&employee.getRoleList().get(0).getRoleType()!=null&&employee.getRoleList().get(0).getRoleType().length()!=0){
			   login.setmId(restaurant.getMid());
			   login.setAdrDetail(restaurant.getAdrDetail());
			   login.setJuridicalPhone(restaurant.getLinkmanPhone());
			   login.setName(employee.getName());
			   login.setRestId(employee.getRestaurantId());
			   login.setRestName(restaurant.getRestName());
			   login.setRestpic(restaurant.getRestLogo());
			   login.setUserRole(employee.getRoleList().get(0).getRoleType());
		   }else{
			   json = new IosReturnJson(Code.FAILE.getCode(),
					   Code.FAILE.getDesc());
			   return json;
		   }
	   }else{
		   json = new IosReturnJson(Code.FAILE.getCode(),
				   Code.FAILE.getDesc());
		   return json;
	   }
	   List<LoginModel> loginList=new ArrayList();
	   loginList.add(login);
		if (loginList.size() > 0) {
			json.setObjectzJson(loginList);
		}
		return json;
	  /* DwzAjaxDone a=new DwzAjaxDone();
	   a.setMessage("登录成功");
	   a.setValue("0");
	   a.setRestId(r[1]+"");    //餐厅id
	   a.setmId(r[0]+"");      //mid 腾势商户号
	   a.setRestName(r[2]+"");        //餐厅名称
	   if(r2.size()>0)
	   a.setRestpic(r2.get(0)+"");	  //logo图片
	   a.setAdrDetail(r[3]+"");  //详细地址
	   a.setJuridicalPhone(r[4]+"");//法人手机号 juridicalPhone
	   if(getCurrentUser().getName()==null||getCurrentUser().getName().equals("")){
		   name="01";
	   }else{
		   
		   name=getCurrentUser().getName();  
	   }
	   a.setName(name);
	   Map<String, Object> map=new HashMap<String, Object>();
	   map.put("login", a);
	   return map;*/

   
   } 
   /**
    * 登陆失败
    * @return
    */
   @RequestMapping({"mobileFail"})
   @ResponseBody
   public IosReturnJson fail() { 
	   /*DwzAjaxDone a=new DwzAjaxDone();
	   a.setMessage("登录失败");
	   a.setValue("1");
	   Map<String, Object> map=new HashMap<String, Object>();
	   map.put("login", a);
	   return map;*/
	   IosReturnJson json = new IosReturnJson();
	   json = new IosReturnJson(Code.FAILE.getCode(),
			   Code.FAILE.getDesc());
	   return json;
   
   } 
   @RequestMapping({""})
   @ResponseBody
   public String nosession(Model model, HttpServletRequest request) {
     return AuthEnum.NOSESSION.getCode();
   }
 
   @RequestMapping(value={"clientLogin"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String clientLogin(@RequestParam(value="u", required=false) String u, @RequestParam(value="p", required=false) String p, @RequestParam(value="type", required=false, defaultValue="login") String type, Model model, HttpServletRequest request)
   {
     if ("check".equals(type))
     {
       Employee employee = this.employeeService.findByLoginUsernameAndLoginPassword(u, p, null);
       if (employee == null)
       {
         model.addAttribute("notes", "false");
         return "open/getBill";
       }
 
       model.addAttribute("notes", "true");
       return "open/getBill";
     }
 
     try
     {
       silenceLogin(request, u, p);
     }
     catch (Exception e)
     {
       e.printStackTrace();
       return "redirect:/login";
     }
     return "redirect:/index";
   }
 
   private void silenceLogin(HttpServletRequest request, String u, String p)
   {
     Subject subject = SecurityUtils.getSubject();
     subject.logout();
     UsernamePasswordToken token = new UsernamePasswordToken(u, p, true, request.getHeader("Host"));
     token.setRememberMe(true);
     subject.login(token);
   }
 }

