 package com.ndlan.canyin.frontdesk.service.synch;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.synch.TransferCarrier;
 import com.ndlan.canyin.base.repository.synch.TransferCarrierDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class TransferCarrierService extends BaseService<TransferCarrierDao, TransferCarrier>
 {
   TransferCarrierDao transferCarrierDao;
 
   public List<TransferCarrier> findAllByRestId(String id)
   {
     return (List)this.transferCarrierDao.findAll();
   }
 
   public List<TransferCarrier> findBytransactionIdOrderBySeqAsc(int transactionId) {
     return this.transferCarrierDao.findBytransactionIdOrderBySeqAsc(transactionId);
   }
   public TransferCarrierDao getTransferCarrierDao() {
     return this.transferCarrierDao;
   }
   @Autowired
   public void setBaseDao(TransferCarrierDao transferCarrierDao) { this.transferCarrierDao = transferCarrierDao;
     super.setDao(transferCarrierDao);
   }
 
   public List<TransferCarrier> searchByTransaction(int transactionId)
   {
     return this.transferCarrierDao.findBytransactionIdOrderBySeqAsc(transactionId);
   }
 }

