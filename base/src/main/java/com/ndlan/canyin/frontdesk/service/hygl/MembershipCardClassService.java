 package com.ndlan.canyin.frontdesk.service.hygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
 import com.ndlan.canyin.base.entity.sygl.CashDiscount;
 import com.ndlan.canyin.base.repository.hygl.MembershipCardClassDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class MembershipCardClassService extends BaseService<MembershipCardClassDao, MembershipCardClass>
 {
   @Autowired
   public void setDao(MembershipCardClassDao dao)
   {
     super.setDao(dao);
   }
 
   public Page<MembershipCardClass> getPage(PageRequest pageRequest, String restId)
   {
     return ((MembershipCardClassDao)getDao()).findAll(MembershipCardClassSpecs.searchPage(restId), pageRequest);
   }
 
   public MembershipCardClass findMembershipCardClassById(String id)
   {
     return (MembershipCardClass)((MembershipCardClassDao)getDao()).findOne(id);
   }
 
   public List<MembershipCardClass> findByName(String name, String restId) {
     return ((MembershipCardClassDao)getDao()).findByNameAndRestId(name, restId);
   }
 
   public List<MembershipCardClass> findByRestId(String restId) {
     return ((MembershipCardClassDao)getDao()).findByRestId(restId);
   }
 
   public List<MembershipCardClass> findByCashDiscount(CashDiscount cashDiscount, String restId)
   {
     return ((MembershipCardClassDao)getDao()).findByCashDiscountAndRestId(cashDiscount, restId);
   }
   
   public List<MembershipCardClass> findMemberCardClassByRestId(String shopId){
	   return (List<MembershipCardClass>)getDao().findMemberCardClassByRestId(shopId);
   }
 }

