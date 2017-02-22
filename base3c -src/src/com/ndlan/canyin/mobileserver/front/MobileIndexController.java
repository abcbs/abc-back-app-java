 package com.ndlan.canyin.mobileserver.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.ctzh.RestaurantService;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.ServletRequest;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("mobileIndexController")
 @Resource(name="mobileIndexController")
 @RequestMapping({"/mobile/index"})
 @Lazy
 public class MobileIndexController extends BaseManageController
 {
 
   @Autowired
   EmployeeService employeeservice;
 
   @RequestMapping({"download"})
   public String index(Model model, HttpServletRequest request)
   {
     return "index/download";
   }
 
   @RequestMapping(value={"updateVersion"}, produces={"application/json"})
   @ResponseBody
   public ClientVersion updateVersion(Model model, ServletRequest request)
   {
     ClientVersion client = new ClientVersion();
     client.setVersionCode(27);
     client.setVersionName("2.3.0");
     client.setApkName("canyin-order-lastest.apk");
     return client;
   }
   @RequestMapping(value={"updateMenuVersion"}, produces={"application/json"})
   @ResponseBody
   public ClientVersion updateMenuVersion(Model model, ServletRequest request) { ClientVersion client = new ClientVersion();
     client.setAppName("canyin-menu");
     client.setVersionCode(27);
     client.setVersionName("2.3.0");
     client.setApkName("canyin-menu-lastest.apk");
     return client; } 
   @RequestMapping(value={"isServerOk"}, produces={"text/plain; charset=UTF-8"})
   @ResponseBody
   public String isServerOk() {
     return "canyin-mobileserver";
   }
   @RequestMapping(value={"getRestId"}, produces={"text/plain"})
   @ResponseBody
   public String getRestId(Model model, ServletRequest request) {
	   List rs = this.restaurantService.findAll();
     return ((Restaurant)rs.get(0)).getRestId(); } 
   @RequestMapping(value={"getCookie"}, produces={"text/plain"})
   @ResponseBody
   public String getCookie(Model model, ServletRequest request) {
     List<Employee> employees = this.employeeservice.findAll();
     String name = null;
     String password = null;
     for (Employee employee : employees) {
       if ((employee.getLoginUsername() != null) && (employee.getPlainPassword() != null) && (!employee.getLoginUsername().equals("")) && (!employee.getPlainPassword().equals(""))) {
         name = employee.getLoginUsername();
         password = employee.getPlainPassword();
         break;
       }
     }
     return "username=" + name + ";" + "password=" + password;
   }
 
   class ClientVersion
   {
     private String appName = "canyin-order";
     private String apkName = "canyin-order-1.0.1.apk";
     private String ipaName = "canyin-order-1.0.1.ipa";
     private String versionName = "1.0.1";
     private int versionCode;
 
     ClientVersion()
     {
     }
 
     public String getAppName()
     {
       return this.appName;
     }
     public void setAppName(String appName) {
       this.appName = appName;
     }
     public String getApkName() {
       return this.apkName;
     }
     public void setApkName(String apkName) {
       this.apkName = apkName;
     }
     public String getIpaName() {
       return this.ipaName;
     }
     public void setIpaName(String ipaName) {
       this.ipaName = ipaName;
     }
     public String getVersionName() {
       return this.versionName;
     }
     public void setVersionName(String versionName) {
       this.versionName = versionName;
     }
     public int getVersionCode() {
       return this.versionCode;
     }
     public void setVersionCode(int versionCode) {
       this.versionCode = versionCode;
     }
   }
 }

