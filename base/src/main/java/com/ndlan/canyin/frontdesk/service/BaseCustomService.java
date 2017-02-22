 package com.ndlan.canyin.frontdesk.service;
 
 import com.ndlan.canyin.base.repository.BaseDao;
 import java.util.List;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 public class BaseCustomService<D extends BaseDao<T, String>, T>
 {
   private D dao;
 
   public T getOne(String id)
   {
     return this.dao.findOne(id);
   }
   @Transactional(readOnly=false)
   public T save(T entity) {
     this.dao.save(entity);
     return entity;
   }
   @Transactional(readOnly=false)
   public void delete(String id) {
     this.dao.delete(id);
   }
 
   public List<T> findAll(Iterable<String> ids)
   {
     return (List)this.dao.findAll(ids);
   }
 
   public D getDao()
   {
     return this.dao;
   }
 
   public void setDao(D dao) {
     this.dao = dao;
   }
 }

