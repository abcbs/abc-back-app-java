 package com.ndlan.canyin.open.bldcbservices.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.xtgl.SysLogService;
import com.ndlan.canyin.open.utils.OpenResponseDataFormat;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.xtgl.SysLog;
 import com.ndlan.canyin.base.repository.xtgl.SysLogDao;
 import com.ndlan.canyin.core.common.JobStatusEnum;
 import com.ndlan.canyin.core.common.OpenInterfaceDefineEnum;
 import com.ndlan.canyin.core.common.SysLogTypeEnum;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.shiro.SecurityUtils;
 import org.apache.shiro.authc.UsernamePasswordToken;
 import org.apache.shiro.subject.Subject;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("openLoginController")
 @Resource(name="openLoginController")
 @RequestMapping({"/mxbapi/bldcb"})
 @Lazy
 public class OpenLoginController extends BaseManageController
 {
 
   @Autowired
   protected SysLogService sysLogService;
 
   @Autowired
   protected EmployeeService employeeService;
 
   @RequestMapping({"silentlogin"})
   @ResponseBody
   public Object[] silentLoginSystem(@RequestParam(value="username", defaultValue="") String userName, @RequestParam(value="password", defaultValue="") String passWord, Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.LOGIN_LI_S.getCode(), "");
     Employee em = this.employeeService.findByLoginUsernameAndLoginPassword(userName, passWord, "");
 
     if (em != null) {
       List list = new ArrayList();
       Map map = new HashMap();
       String jobStatus = JobStatusEnum.ONJOB.getCode().equalsIgnoreCase(em.getJobStatus()) ? "在职" : "离职";
       map.put("name", em.getName());
       map.put("code", em.getEmpNum());
       map.put("LoginName", em.getLoginUsername());
       map.put("Phone", em.getMobile());
       map.put("jobStatus", em.getJobStatus());
       map.put("jobStatusMessage", jobStatus);
       list.add(map);
       return ordf.succsess(list);
     }
 
     return ordf.custom(411, "登录失败");
   }
 
   @RequestMapping(value={"loginin"}, produces={"application/json"})
   @ResponseBody
   public Object[] loginSystem(@RequestParam(value="username", defaultValue="") String userName, @RequestParam(value="password", defaultValue="") String passWord, Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.LOGIN_LI.getCode(), "");
     Subject subject = SecurityUtils.getSubject();
     try
     {
       Employee em = this.employeeService.findByLoginUsernameAndLoginPassword(userName, passWord, "");
 
       if (em == null) {
         return ordf.custom(411, "登录失败，用户名或密码错误");
       }
       List list = new ArrayList();
       Map map = new HashMap();
       String jobStatus = JobStatusEnum.ONJOB.getCode().equalsIgnoreCase(em.getJobStatus()) ? "在职" : "离职";
       map.put("name", em.getName());
       map.put("code", em.getEmpNum());
       map.put("LoginName", em.getLoginUsername());
       map.put("Phone", em.getMobile());
       map.put("jobStatus", em.getJobStatus());
       map.put("jobStatusMessage", jobStatus);
       list.add(map);
 
       subject.logout();
       UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord, true, request.getHeader("Host"));
       token.setRememberMe(true);
       subject.login(token);
 
       SysLog obj = new SysLog();
       obj.setSysLogType(SysLogTypeEnum.LOGINLOG.getCode());
       obj.setNotes("登录");
       obj.setRestId(getCurrentUserRestId());
       obj.setCreateTime(new Date());
       obj.setCreateEmployee(getCurrentUser());
       obj.setIp(request.getRemoteAddr());
       this.sysLogService.save(obj);
 
       return ordf.succsess(list);
     }
     catch (Exception e) {
       subject.logout();
     }return ordf.exceptionErr();
   }
 
   @RequestMapping(value={"loginout"}, produces={"application/json"})
   @ResponseBody
   public Object[] loginOutSystem(Model model, HttpServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.LOGIN_LO.getCode(), "");
     Subject sub;
     try {
       SysLog obj = new SysLog();
       obj.setSysLogType(SysLogTypeEnum.LOGINLOG.getCode());
       obj.setNotes("退出");
       obj.setRestId(getCurrentUserRestId());
       obj.setCreateEmployee(getCurrentUser());
       obj.setCreateTime(new Date());
       obj.setIp(request.getRemoteAddr());
       ((SysLogDao)this.sysLogService.getDao()).save(obj);
     }
     catch (Exception e)
     {
//       Subject sub;
       return ordf.exceptionErr();
     } finally {
        sub = SecurityUtils.getSubject();
       sub.logout();
     }
     return ordf.custom(200, "登出成功");
   }
 }

