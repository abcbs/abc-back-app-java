 package com.ndlan.canyin.frontdesk.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.xtgl.SysLogService;
 import com.ndlan.canyin.base.entity.xtgl.SysLog;
 import com.ndlan.canyin.core.common.SysLogTypeEnum;
 import java.util.Date;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.shiro.SecurityUtils;
 import org.apache.shiro.subject.Subject;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/"})
 @Lazy
 public class LogoutController extends BaseManageController
 {
 
   @Autowired
   protected SysLogService sysLogService;
 
   @RequestMapping({"/logout"})
   public String logout(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
   {
     try
     {
       SysLog obj = new SysLog();
       obj.setSysLogType(SysLogTypeEnum.LOGINLOG.getCode());
       obj.setNotes("退出");
       obj.setRestId(getCurrentUserRestId());
       obj.setCreateTime(new Date());
       obj.setCreateEmployee(getCurrentUser());
       obj.setIp(request.getRemoteAddr());
       this.sysLogService.save(obj);
     }
     catch (Exception sub)
     {
//       Subject sub;
    	 sub.printStackTrace();
     }
     finally
     {
       Subject sub;
       redirectAttributes.addFlashAttribute("restId", getCurrentUserRestId());
       redirectAttributes.addFlashAttribute("username", getCurrentUser().getLoginUsername());
       sub = SecurityUtils.getSubject();
       sub.logout();
     }
     return "redirect:login";
   }
 }

