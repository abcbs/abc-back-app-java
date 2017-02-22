 package com.ndlan.canyin.frontdesk.service.security;
 
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import org.apache.shiro.authc.AuthenticationException;
 import org.apache.shiro.authc.AuthenticationToken;
 import org.apache.shiro.subject.Subject;
 import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
 import org.apache.shiro.web.util.WebUtils;
 
 public class MixAuthenticationFilter extends FormAuthenticationFilter
 {
   protected boolean executeLogin(ServletRequest request, ServletResponse response)
     throws Exception
   {
     return super.executeLogin(request, response);
   }
 
   protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
   {
     return super.onAccessDenied(request, response);
   }
 
   protected boolean isLoginSubmission(ServletRequest request, ServletResponse response)
   {
     return super.isLoginSubmission(request, response);
   }
 
   protected AuthenticationToken createToken(ServletRequest request, ServletResponse response)
   {
     return super.createToken(request, response);
   }
 
   protected boolean isRememberMe(ServletRequest request)
   {
     return super.isRememberMe(request);
   }
 
   protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response)
     throws Exception
   {
     return super.onLoginSuccess(token, subject, request, response);
   }
 
   protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response)
   {
     return super.onLoginFailure(token, e, request, response);
   }
 
   protected void setFailureAttribute(ServletRequest request, AuthenticationException ae)
   {
     super.setFailureAttribute(request, ae);
   }
 
   protected String getUsername(ServletRequest request)
   {
     return super.getUsername(request);
   }
 
   protected String getPassword(ServletRequest request)
   {
     return super.getPassword(request);
   }
 
   protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
     WebUtils.redirectToSavedRequest(request, response, getSuccessUrl() + "?from=" + request.getParameter("from")+"&username="+request.getParameter("username"));
   }
 }

