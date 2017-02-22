 package com.ndlan.canyin.frontdesk.interceptor;
 
 import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm.ShiroUser;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.core.utils.DateProvider;

import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
 
 @Aspect
 public class RegistDataUpdateDaoInterceptor
 {
   private DateProvider dateProvider = DateProvider.DEFAULT;
 
   @Pointcut("execution(* com.ndlan.canyin.frontdesk.service.BaseService.save*(..))")
   public void saveMethod() {  }
 
   @Around("saveMethod()")
   public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable { Object targetObject = pjp.getArgs()[0];
 
     Employee employee = null;
     if (ThreadContext.getSubject() != null) {
       ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
       if (user != null) {
         employee = user.employee;
       }
       BaseEntity target = (BaseEntity)targetObject;
       if (target.getCreateEmployee() == null)
       {
         target.setCreateEmployee(employee);
       }
       if (target.getCreateTime() == null)
       {
         target.setCreateTime(this.dateProvider.getDate());
       }
       target.setUpdateEmployee(employee);
       target.setUpdateTime(this.dateProvider.getDate());
 
       Class cla = targetObject.getClass();
       Method[] ms = cla.getDeclaredMethods();
       for (Method m : ms)
       {
         String name = m.getName();
         if ("setRestId".equals(name))
         {
           Method m1 = cla.getDeclaredMethod("setRestId", new Class[] { String.class });
           m1.invoke(targetObject, new Object[] { user.getRestId() });
         }
         if (!"setRestaurant".equals(name))
           continue;
         Method m2 = cla.getDeclaredMethod("setRestaurant", new Class[] { Restaurant.class });
         m2.invoke(targetObject, new Object[] { employee.getRestaurant() });
       }
     }
     else {
       BaseEntity target = (BaseEntity)targetObject;
       if (target.getCreateEmployee() == null)
       {
         target.setCreateTime(this.dateProvider.getDate());
       }
 
       target.setUpdateTime(this.dateProvider.getDate());
     }
     Object object = pjp.proceed();
     return object;
   }
 }

