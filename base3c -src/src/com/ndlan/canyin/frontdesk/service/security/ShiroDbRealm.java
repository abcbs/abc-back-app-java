 package com.ndlan.canyin.frontdesk.service.security;
 
 import com.ndlan.canyin.frontdesk.service.ctzh.AuthorityModuleService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.frontdesk.service.qtsy.CashierDeskSettingService;
import com.ndlan.canyin.frontdesk.util.UserSettingCache;
import com.ndlan.canyin.frontdesk.util.UserSettingCacheSetting;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Role;
import com.ndlan.canyin.base.entity.qtsy.CashierDeskSetting;
import com.ndlan.canyin.core.common.InnerUserNameEnum;
import com.ndlan.canyin.core.common.SysDataTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;
import com.ndlan.canyin.core.utils.Collections3;
import com.ndlan.canyin.core.utils.Encodes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import net.sf.ehcache.CacheManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.ByteSource.Util;
import org.springframework.beans.factory.annotation.Autowired;
 
 public class ShiroDbRealm extends AuthorizingRealm
 {
 
   @Autowired
   protected EmployeeService employeeService;
 
   @Autowired
   protected AuthorityModuleService authorityModuleService;
 
   @Autowired
   protected CashierDeskSettingService cashierDeskSettingService;
 
   @Autowired
   CacheManager cacheManager;
   private List<String> defaultPermissions;
 /**
  * 身份校验
  */
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
     throws AuthenticationException
   {
     UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
     String[] array = token.getUsername().split(",");
     String loginUsername = array[0];
     String restId = array.length > 1 ? array[1] : null;
 
     Employee employee = null;
     if ((InnerUserNameEnum.TEST.getCode().equals(loginUsername)) || 
       (InnerUserNameEnum.VISTER.getCode().equals(loginUsername)) || 
       (InnerUserNameEnum.SELFORDER.getCode().equals(loginUsername)))
     {
       employee = this.employeeService.findHideUserByLoginName(loginUsername);
     }
     else
     {
    	 if(restId.equals("mobilefirst")){
    		 employee = this.employeeService.findHideUserByLoginName(loginUsername);
    	 }
    	 
    	 else if (restId!=null&&!restId.equals("")&&!restId.equals("mobilefirst")){
    		 employee = this.employeeService.findByLoginNameAndRest(loginUsername, restId);
    	 }else{
    		 employee= null;
    	 }
    	 
     }
     token.setUsername(loginUsername);
     if (employee != null) {
       Employee employee4shiro = new Employee();
       employee4shiro.setEmpId(employee.getEmpId());
       employee4shiro.setName(employee.getName());
       employee4shiro.setSysdataType(employee.getSysdataType());
       employee4shiro.setLoginUsername(token.getUsername());
       Restaurant restaurant = new Restaurant();
       restaurant.setRestId(employee.getRestaurant().getRestId());
       restaurant.setRestName(employee.getRestaurant().getRestName());
       employee4shiro.setRestaurant(restaurant);
       byte[] salt = Encodes.decodeHex(employee.getSalt());
 
       String roledesc = "";
       if ((employee.getRoleList() != null) && (employee.getRoleList().size() > 0))
       {
         Role r = (Role)employee.getRoleList().get(0);
         roledesc = r.getName();
       }
 //740ce6b1-f54f-11e4-af9a-02004c4f4f50
//       CashierDeskSetting commonSettingCashierDeskSetting = this.cashierDeskSettingService.findConmonSettingByRestId(restaurant.getRestId());
      CashierDeskSetting cashierDeskSetting = this.cashierDeskSettingService.findByRestIdAndEmpId("740ce6b1-f54f-11e4-af9a-02004c4f4f50", "72e12622-f54f-11e4-af9a-02004c4f4f50");
       CashierDeskSetting commonSettingCashierDeskSetting = this.cashierDeskSettingService.getOne("740ce6b1-f54f-11e4-af9a-02004c4f4f50");
       
       UserSettingCache.getInstance().setUserCache(employee.getEmpId(), cashierDeskSetting, commonSettingCashierDeskSetting);
       UserSettingCache.getInstance().getUserCache(employee.getEmpId()).setUserName(employee.getName());
 
       this.cacheManager.clearAll();
 
       SimpleAuthenticationInfo s = new SimpleAuthenticationInfo(new ShiroUser(employee.getEmpId(), employee.getLoginUsername(), employee.getName(), employee.getRestaurant().getRestId(), employee4shiro, roledesc), 
         employee.getLoginPassword(), ByteSource.Util.bytes(salt), getName());
       return s;
     }
    	 
    	 return null;
     
 
     
   }
 /**
  * 权限校验
  */
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
   {
     ShiroUser shiroUser = (ShiroUser)principals.getPrimaryPrincipal();
     SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
 
     List authorizationInfo = new ArrayList();
     if ((InnerUserNameEnum.SUPER.getCode().equals(shiroUser.loginName)) || 
       (InnerUserNameEnum.TEST.getCode().equals(shiroUser.loginName)) ||
       ("01".equals(shiroUser.loginName)) ||
       (SysDataTypeEnum.DEFAULT.getCode().equals(shiroUser.getSysdataType())))
     {
       authorizationInfo = this.authorityModuleService.findAll();
     }
     else
     {
    	 authorizationInfo = this.authorityModuleService.findAll();
//       authorizationInfo = this.authorityModuleService.getAuthorityModule(shiroUser.id, shiroUser.getRestId());
     }
     if ((authorizationInfo != null) && (authorizationInfo.size() > 1)) {
       addPermissions(info, authorizationInfo);
     }
 
     return info;
   }
 
   @PostConstruct
   public void initCredentialsMatcher()
   {
     HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
     matcher.setHashIterations(1024);
 
     setCredentialsMatcher(matcher);
   }
 
   public EmployeeService getEmployeeService()
   {
     return this.employeeService;
   }
 
   public void setEmployeeService(EmployeeService employeeService) {
     this.employeeService = employeeService;
   }
 
   public void setDefaultPermissions(List<String> defaultPermissions) {
     this.defaultPermissions = defaultPermissions;
   }
 
   private void addPermissions(SimpleAuthorizationInfo info, List<AuthorityModule> authorizationInfo)
   {
     List temp = Collections3.extractToList(authorizationInfo, "permission");
     List permissions = getValue(temp, "perms\\[(.*?)\\]");
     List union = null;
 
     if (Collections3.isNotEmpty(this.defaultPermissions)) {
       union = Collections3.union(permissions, this.defaultPermissions);
     }
 
     info.addStringPermissions(union);
   }
 
   private List<String> getValue(List<String> obj, String regex)
   {
     List result = new ArrayList();
 
     if (Collections3.isEmpty(obj)) {
       return result;
     }
 
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(StringUtils.join(obj, ","));
 
     while (matcher.find()) {
       if (StringUtils.isNotEmpty(matcher.group(1).trim())) {
         result.add(matcher.group(1));
       }
     }
     return result;
   }
 
   public static class ShiroUser
     implements Serializable
   {
     private static final long serialVersionUID = -1373760761780840081L;
     public String id;
     public String loginName;
     public String name;
     public String restId;
     public String roleDesc;
     public Employee employee;
     public String sysdataType = SysDataTypeEnum.USERDEFINED.getCode();
 
     public ShiroUser(String id, String loginName, String name, String restId, Employee employee, String roleDesc) {
       this.id = id;
       this.loginName = loginName;
       this.name = name;
       this.restId = restId;
       this.employee = employee;
       this.roleDesc = roleDesc;
       this.sysdataType = String.valueOf(employee.getSysdataType());
     }
 
     public String isVaildBill()
     {
       return InnerUserNameEnum.TEST.getCode().equals(this.loginName) ? TrueFalseEnum.FALSE.getCode() : TrueFalseEnum.TRUE.getCode();
     }
 
     public String getLoginName() {
       return this.loginName;
     }
 
     public String getSysdataType() {
       return this.sysdataType;
     }
 
     public String getName() {
       return this.name;
     }
     public String getRestId() {
       return this.restId;
     }
     public String getRoleDesc() {
       return this.roleDesc;
     }
 
     public String toString()
     {
       return this.loginName;
     }
 
     public int hashCode()
     {
       return this.loginName.hashCode();
     }
 
     public boolean equals(Object obj)
     {
       if (this == obj)
         return true;
       if (obj == null)
         return false;
       if (getClass() != obj.getClass())
         return false;
       ShiroUser other = (ShiroUser)obj;
       if (this.loginName == null) {
         if (other.loginName != null)
           return false;
       } else if (!this.loginName.equals(other.loginName))
         return false;
       return true;
     }
   }
 }

