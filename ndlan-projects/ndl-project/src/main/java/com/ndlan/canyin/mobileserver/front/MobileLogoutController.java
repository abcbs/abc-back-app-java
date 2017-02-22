 package com.ndlan.canyin.mobileserver.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.MobileRspResult;
import com.ndlan.canyin.frontdesk.service.xtgl.SysLogService;
import com.ndlan.canyin.base.entity.xtgl.SysLog;
import com.ndlan.canyin.core.common.StatusCodeEnum;
import com.ndlan.canyin.core.common.SysLogTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.DateProvider;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Resource(name="mobileLogoutController")
 @RequestMapping({"/mobile"})
 @Lazy
 public class MobileLogoutController extends BaseManageController
 {
 
   @Autowired
   protected SysLogService sysLogService;
 /**
  * 退出登录
  * @param request
  * @return
  */
   @RequestMapping(value={"logout"}, produces={"application/json"})
   @ResponseBody
   public MobileRspResult logout(HttpServletRequest request)
   {
     try
     {
       SysLog obj = new SysLog();
       obj.setSysLogType(SysLogTypeEnum.LOGINLOG.getCode());
       obj.setNotes("退出");
       obj.setRestId(getCurrentUserRestId());
       obj.setCreateTime(DateProvider.DEFAULT.getDate());
       obj.setCreateEmployee(getCurrentUser());
       obj.setIp(request.getRemoteAddr());
       this.sysLogService.save(obj);
       MobileRspResult localMobileRspResult = new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "退出登录成功", TrueFalseEnum.TRUE.getCode());
       Subject sub;
       return localMobileRspResult;
     }
     catch (Exception e)
     {
       MobileRspResult localMobileRspResult = new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "退出登录失败", TrueFalseEnum.FALSE.getCode());
       Subject sub;
       return localMobileRspResult;
     }
     finally
     {
       Subject sub = SecurityUtils.getSubject();
       sub.logout();
     }
//     throw localObject;
   }
 }

