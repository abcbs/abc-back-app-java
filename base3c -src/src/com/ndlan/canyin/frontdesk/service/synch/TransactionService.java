 package com.ndlan.canyin.frontdesk.service.synch;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.transport.Response;
import com.ndlan.canyin.base.entity.synch.Transaction;
 import com.ndlan.canyin.base.repository.synch.TransactionDao;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class TransactionService extends BaseService<TransactionDao, Transaction>
 {
   TransactionDao transactionDao;
 
   public List<Transaction> findAllByRestId(String id)
   {
     return this.transactionDao.findByRestId(id);
   }
 
   public List<Transaction> findByRestIdAndStatus(String id) {
     return this.transactionDao.findByRestIdAndStatus(id, TrueFalseEnum.FALSE.getCode());
   }
 
   public Response sendRequest(Transaction transaction)
   {
     return null;
   }
 
   public TransactionDao getTransactionDao() {
     return this.transactionDao;
   }
   @Autowired
   public void setBaseDao(TransactionDao transactionDao) { this.transactionDao = transactionDao;
     super.setDao(transactionDao);
   }
 }

