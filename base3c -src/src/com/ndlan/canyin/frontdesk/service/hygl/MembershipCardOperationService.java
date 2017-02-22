 package com.ndlan.canyin.frontdesk.service.hygl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.repository.hygl.MembershipCardOperationDao;
 import com.ndlan.canyin.core.common.CardOperationTypeEnum;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageImpl;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 @Lazy
 public class MembershipCardOperationService extends BaseService<MembershipCardOperationDao, MembershipCardOperation>
 {
   private MembershipCardOperationDao membershipCardOperationDao;
 
   public Page<MembershipCardOperation> searchPageCardOperate(Map<String, Object> searchParams, String restId, String cardOperationType, String mcId, int pageNumber, int pagzSize, String[] sortType)
   {
     Sort.Direction d = Sort.Direction.DESC;
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(d, sortType));
     List cardOperationTypeList = new ArrayList();
     if (CardOperationTypeEnum.RECHARGE.getCode().equals(cardOperationType)) {
       cardOperationTypeList.add(cardOperationType);
       cardOperationTypeList.add(CardOperationTypeEnum.RESETTLE_REFUND.getCode());
       cardOperationTypeList.add(CardOperationTypeEnum.CANCEL_REFUND.getCode());
       cardOperationTypeList.add(CardOperationTypeEnum.CANCEL_ORDER_REFUND.getCode());
     } else {
       cardOperationTypeList.add(cardOperationType);
     }
     List<MembershipCardOperation> membershipCardOperations = this.membershipCardOperationDao.findByRestIdAndCardOperationTypeIn(restId, cardOperationTypeList, mcId);
     for(int i =0 ;i<membershipCardOperations.size(); i++){
    	 if(membershipCardOperations.get(i).getCreateEmployee()!=null&&membershipCardOperations.get(i).getCreateEmployee().getName()!=null){
    		 membershipCardOperations.get(i).setIosName(membershipCardOperations.get(i).getCreateEmployee().getName());
    	 }
     }
     Page page = new PageImpl(membershipCardOperations, pageRequest, pagzSize);
     return page;
   }
 
   public Page<MembershipCardOperation> getPage(PageRequest pageRequest, String keyword, String cardOperationType_value, String membershipCardId, String restMemberInfoId, String updateTime_le, String updateTime_ge, String restId)
   {
     return this.membershipCardOperationDao.findAll(MembershipCardOperationSpecs.searchPage(keyword, cardOperationType_value, membershipCardId, restMemberInfoId, updateTime_le, updateTime_ge, restId), pageRequest);
   }
 
   public Page<MembershipCardOperation> getPage(PageRequest pageRequest)
   {
     return this.membershipCardOperationDao.findAll(pageRequest);
   }
 
   public BigDecimal getPaidinCashSumByPaymentType(String restId, String empId, Date createTime, String paymentType, String cardOperationType)
   {
     return this.membershipCardOperationDao.getPaidinCashSumByPaymentType(restId, empId, createTime, paymentType, cardOperationType);
   }
 
   public BigDecimal getCashPledgeSumByPaymentType(String restId, String empId, Date createTime, String paymentType, String cardOperationType)
   {
     return this.membershipCardOperationDao.getCashPledgeSumByPaymentType(restId, empId, createTime, paymentType, cardOperationType);
   }
 
   public BigDecimal getCashPledgeSum(String restId, String empId, Date createTime, String cardOperationType)
   {
     return this.membershipCardOperationDao.getCashPledgeSum(restId, empId, createTime, cardOperationType);
   }
 
   public BigDecimal getBalanceSum(String restId, String empId, Date createTime, String cardOperationType)
   {
     return this.membershipCardOperationDao.getBalanceSum(restId, empId, createTime, cardOperationType);
   }
 
   public List<MembershipCardOperation> findByRestIdAndDinerBill1(String restId, DinerBill dinerBill)
   {
     return this.membershipCardOperationDao.findByRestIdAndDinerBill(restId, dinerBill);
   }
 
   public BigDecimal getConsumeCashSumByMcId(String restId, String mcId, String cardOperationType) {
     return this.membershipCardOperationDao.getConsumeCashSumByMcId(restId, mcId, cardOperationType);
   }
   @Autowired
   public void setDao(MembershipCardOperationDao dao) {
     super.setDao(dao);
     this.membershipCardOperationDao = dao;
   }
 }

