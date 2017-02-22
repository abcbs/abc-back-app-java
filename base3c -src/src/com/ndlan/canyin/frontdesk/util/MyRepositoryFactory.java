 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.base.repository.BaseCustomDao;
 import javax.persistence.EntityManager;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.support.JpaEntityInformation;
 import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
 import org.springframework.data.repository.core.RepositoryMetadata;
 
 public class MyRepositoryFactory extends JpaRepositoryFactory
 {
   public MyRepositoryFactory(EntityManager entityManager)
   {
     super(entityManager);
   }
 
   protected JpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager em)
   {
     JpaEntityInformation entityInformation = getEntityInformation(metadata.getDomainType());
     return new BaseCustomDao(entityInformation, em);
   }
 
   protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata)
   {
     return BaseCustomDao.class;
   }
 }

