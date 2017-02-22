 package com.ndlan.canyin.mobileserver.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.core.common.AuthEnum;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("mobileLoginController")
 @Resource(name="mobileLoginController")
 @RequestMapping({"/mobile/login"})
 @Lazy
 public class MobileLoginController extends BaseManageController
 {
	 /**
	  * 登录失败
	  * @param userName
	  * @param model
	  * @return
	  */
   @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
   @ResponseBody
   public String fail(@RequestParam("username") String userName, Model model)
   {
     model.addAttribute("username", userName);
     return AuthEnum.FAIL.getCode();
   }
   @RequestMapping({""})
   @ResponseBody
   public String nosession(Model model, HttpServletRequest request) { return AuthEnum.NOSESSION.getCode();
   }
 
   @RequestMapping({"/success"})
   @ResponseBody
   public String index(Model model, HttpServletRequest request)
   {
     return AuthEnum.SUCCESS.getCode();
   }
 }

