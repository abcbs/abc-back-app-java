 package com.ndlan.canyin.base.repository;
 
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.util.List;
 import javax.persistence.EntityManager;
 import org.springframework.data.jpa.repository.support.JpaEntityInformation;
 import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.repository.BaseDao;
 
 public class BaseCustomDao<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
   implements BaseDao<T, ID>
 {
   private final EntityManager entityManager;
   private final int FLUSH_COUNT = 30;
 
   public BaseCustomDao(Class<T> domainClass, EntityManager em) {
     super(domainClass, em);
 
     this.entityManager = em;
   }
 
   public BaseCustomDao(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
     super(entityInformation, entityManager);
     this.entityManager = entityManager;
   }
 
   public void shareMethod()
   {
     System.out.println("测试可扩展的dao~~~~");
   }
 
   public EntityManager getEntityManager() {
     return this.entityManager;
   }
 
   @Transactional(readOnly=false)
   public void batchInsert(List<T> list)
   {
     EntityManager em = getEntityManager();
     for (int i = 0; i < list.size(); i++) {
       em.persist(list.get(i));
       if (((i != 0) && (i % 30 == 0)) || (i == list.size() - 1)) {
         em.flush();
         em.clear();
       }
     }
   }
 
   @Transactional(readOnly=false)
   public void batchUpdate(List<T> list)
   {
     EntityManager em = getEntityManager();
     for (int i = 0; i < list.size(); i++) {
       em.merge(list.get(i));
       if (((i != 0) && (i % 30 == 0)) || (i == list.size() - 1)) {
         em.flush();
         em.clear();
       }
     }
   }
 
   @Transactional(readOnly=false)
   public void batchDelete(List<T> list)
   {
     EntityManager em = getEntityManager();
     for (int i = 0; i < list.size(); i++) {
       em.remove(em.merge(list.get(i)));
       if (((i != 0) && (i % 30 == 0)) || (i == list.size() - 1)) {
         em.flush();
         em.clear();
       }
     }
   }
 
   @Transactional(readOnly=false)
   public void batchDelete(String[] ids)
   {
     EntityManager em = getEntityManager();
     Class clazz = (Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     for (int i = 0; i < ids.length; i++) {
       em.remove(em.merge(em.find(clazz, ids[i])));
       if (i % 30 == 0) {
         em.flush();
         em.clear();
       }
     }
   }
 
   public <S extends T> S save(S entity)
   {
     return super.save(entity);
   }
 
   public <S extends T> List<S> save(Iterable<S> entities)
   {
     return super.save(entities);
   }
   @Transactional(readOnly=false)
   public void saveWithId(T entity) {
     EntityManager em = getEntityManager();
     em.persist(entity);
     em.flush();
     em.clear();
   }
 }

