 package com.ndlan.canyin.sharelogic.service;
 
 import com.ndlan.canyin.sharelogic.util.BeanSelfAwareLogic;
import com.ndlan.canyin.base.repository.BaseDao;
import com.ndlan.canyin.sharelogic.service.BaseLogicService;
 import java.util.List;
import org.springframework.transaction.annotation.Transactional;
 
 public class BaseLogicService<D extends BaseDao<T, String>, T>
   implements BeanSelfAwareLogic
 {
   private D dao;
   protected BaseLogicService<D, T> self;
 
   public void setSelf(Object proxyBean)
   {
     this.self = ((BaseLogicService)proxyBean);
   }
 
   public T getOne(String id) {
     return this.dao.findOne(id);
   }
   @Transactional(readOnly=false)
   public T save(T entity) {
     return this.dao.save(entity);
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
 }

