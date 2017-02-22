 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.frontdesk.service.jour.JourService;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm.ShiroUser;
import com.ndlan.canyin.base.entity.jour.Jour;
import com.ndlan.canyin.core.common.Constants;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
 
 public class CustomShiroFilter extends AccessControlFilter
 {
   private FormAuthenticationFilter formAuthenticationFilter;
 
   @Autowired
   private JourService jourService;
 
   public JourService getJourService()
   {
     return this.jourService;
   }
 
   public void setJourService(JourService jourService) {
     this.jourService = jourService;
   }
 
   protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
     throws Exception
   {
     if (isLoginRequest(request, response)) {
       return true;
     }
     Subject subject = getSubject(request, response);
     return subject.getPrincipal() != null;
   }
 
   protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
     throws Exception
   {
     String username = "";
     String password = "";
     String jourNo = "";
     Cookie[] cookies = ((HttpServletRequest)request).getCookies();
     if (cookies != null) {
       for (Cookie cookie : cookies)
       {
         if ("username".equals(cookie.getName())) {
           username = URLDecoder.decode(cookie.getValue(), "utf-8");
         }
         else if ("password".equals(cookie.getName())) {
           password = cookie.getValue();
         }
         else if ("journo".equalsIgnoreCase(cookie.getName())) {
           jourNo = cookie.getValue();
         }
       }
     }
     if ((StringUtils.isNotEmpty(username)) && (StringUtils.isNotEmpty(password))) {
       UsernamePasswordToken token = new UsernamePasswordToken(username, password, false, request.getRemoteHost());
       Subject subject = getSubject(request, response);
       subject.login(token);
       if (subject.getPrincipal() != null) {
         if (StringUtils.isNotEmpty(jourNo)) {
           ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
           List list = this.jourService.findByJourNoAndStatusAndRestId(jourNo, "", user.getRestId());
           if ((list != null) && (list.size() > 0)) {
             ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + 
               Constants.OPEN_REPEAT_REQUEST_REDIRECT_URL + "?jourid=" + ((Jour)list.get(0)).getJourId());
             return false;
           }
         }
         return true;
       }
     }
 
     ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + getLoginUrl());
     return false;
   }
 }

