 package com.ndlan.canyin.frontdesk.service;
 
 import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm;
import com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm.ShiroUser;
import com.ndlan.canyin.frontdesk.util.BeanSelfAware;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.repository.BaseDao;
import com.ndlan.canyin.core.persistence.DynamicSpecifications;
import com.ndlan.canyin.core.persistence.SearchFilter;
import com.ndlan.canyin.core.utils.DateProvider;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
 
 public class BaseService<D extends BaseDao<T, String>, T>
   implements BeanSelfAware
 {
   private D dao;
   protected BaseService<D, T> self;
 
   public Page<T> search(Map<String, Object> searchParams, int pageNumber, int pagzSize, String sortType)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, getRequiredSort(sortType));
     Map filters = SearchFilter.parse(searchParams);
     Class clazz = (Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), clazz);
     return this.dao.findAll(spec, pageRequest);
   }
 
   public void setSelf(Object proxyBean)
   {
     this.self = ((BaseService)proxyBean);
   }
 
   public T loadOne(String id) {
     return this.dao.findOne(id);
   }
 
   public T getOne(String id) {
     return this.dao.findOne(id);
   }
   @Transactional(readOnly=false)
   public T save(T entity) {
     updateEmployee(entity);
     return this.dao.save(entity);
   }
 
   private void updateEmployee(T entity)
   {
     try
     {
       DateProvider dateProvider = DateProvider.DEFAULT;
       Object targetObject = entity;
 
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
           target.setCreateTime(dateProvider.getDate());
         }
         target.setUpdateEmployee(employee);
         target.setUpdateTime(dateProvider.getDate());
 
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
           target.setCreateTime(dateProvider.getDate());
         }
         target.setUpdateTime(dateProvider.getDate());
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 
   @Transactional(readOnly=false)
   public void delete(String id) {
     this.dao.delete(id);
   }
 
   public List<T> findAll(Iterable<String> ids)
   {
     return (List)this.dao.findAll(ids);
   }
   public List<T> findAll() {
     return (List)this.dao.findAll();
   }
 
   public Sort getRequiredSort(String sortType) {
     Sort sort = null;
     if ((sortType != null) && (!"".equals(sortType)) && (!"auto".equals(sortType))) {
       String[] sortArr = sortType.split("_");
       if (sortArr.length == 2) {
         if ("DESC".equals(sortArr[1]))
           sort = new Sort(Sort.Direction.DESC, new String[] { sortArr[0] });
         else if ("ASC".equals(sortArr[1]))
           sort = new Sort(Sort.Direction.ASC, new String[] { sortArr[0] });
       }
     }
     else if ((sortType == null) || ("".equals(sortType)) || ("auto".equals(sortType))) {
       sort = new Sort(Sort.Direction.ASC, new String[] { "createTime" });
     }
     return sort;
   }
 
   public D getDao()
   {
     return this.dao;
   }
 
   public void setDao(D dao) {
     this.dao = dao;
   }
 
   public void batchInsert(List<T> list) {
     this.dao.batchInsert(list);
   }
 
   public void batchUpdate(List<T> list) {
     this.dao.batchUpdate(list);
   }
 
   public void batchDelete(List<T> list) {
     this.dao.batchDelete(list);
   }
 
   public void batchDelete(String[] ids) {
     this.dao.batchDelete(ids);
   }
   //生成支付、订单（明细）编号
   public String getBillNo()
   {
   	   	Date date=new Date();
   		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
   		String time=sdf.format(date);
   		String a=time.substring(2);
   		String billNo=a;
   		return billNo;
   }
 }

