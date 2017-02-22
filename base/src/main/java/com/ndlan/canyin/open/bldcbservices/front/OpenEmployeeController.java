 package com.ndlan.canyin.open.bldcbservices.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.open.utils.OpenResponseDataFormat;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.core.common.OpenInterfaceDefineEnum;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import javax.annotation.Resource;
 import javax.servlet.ServletRequest;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("openOtherController")
 @Resource(name="openOtherController")
 @RequestMapping({"/mxbapi/bldcb/employee"})
 @Lazy
 public class OpenEmployeeController extends BaseManageController
 {
 
   @Autowired
   EmployeeService employeeService;
 
   @RequestMapping(value={"modifypassword"}, produces={"application/json"})
   @ResponseBody
   public Object[] modifyPassWord(@RequestParam(value="newpw", defaultValue="") String newpw, @RequestParam(value="oldpw", defaultValue="") String oldpw, Model model, ServletRequest request)
   {
     OpenResponseDataFormat ordf = new OpenResponseDataFormat(OpenInterfaceDefineEnum.EMPL_MPW.getCode(), "");
     if (StringUtils.isEmpty(oldpw)) {
       return ordf.custom(311, "原密码不能为空");
     }
     if (StringUtils.isEmpty(newpw)) {
       return ordf.custom(312, "新密码不能为空");
     }
 
     Employee employee = this.employeeService.findByLoginUsernameAndLoginPassword(getCurrentUser().getLoginUsername(), oldpw, getCurrentUserRestId());
     if (employee == null) {
       return ordf.custom(411, "密码输入错误");
     }
     employee.setLoginPassword(newpw);
     this.employeeService.save(employee);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), employee);
 
     return ordf.succsess();
   }
 }

