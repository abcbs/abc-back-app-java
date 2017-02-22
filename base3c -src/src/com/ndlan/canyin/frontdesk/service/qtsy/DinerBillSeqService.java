 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.util.DateUtil;
import com.ndlan.canyin.base.entity.qtsy.DinerBillSeq;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillSeqDao;
 import com.ndlan.canyin.core.common.BillSeqTypeEnum;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.utils.DateProvider;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.LinkedHashMap;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
 
 @Service
 public class DinerBillSeqService extends BaseService<DinerBillSeqDao, DinerBillSeq>
 {
   private DinerBillSeqDao dinerBillSeqDao;
 
   @Autowired
   public void setBaseDao(DinerBillSeqDao dinerBillSeqDao)
   {
     super.setDao(dinerBillSeqDao);
     this.dinerBillSeqDao = dinerBillSeqDao;
   }
 
   public DinerBillSeq findDinerBillSeqListByBill(String restId, Date billDate, LinkedHashMap<String, Object> map) {
     List seqs = this.dinerBillSeqDao.findByRestIdAndBillDateAndBillSeqType(restId, billDate, BillSeqTypeEnum.BILL.getCode());
     DinerBillSeq seq = null;
     if ((seqs != null) && (seqs.size() == 1))
     {
       seq = (DinerBillSeq)seqs.get(0);
     }
     else if ((seqs != null) && (seqs.size() > 1))
     {
       seq = (DinerBillSeq)seqs.get(0);
       for (int i = 0; i < seqs.size(); i++) {
         if (i < 1)
           continue;
         String id = ((DinerBillSeq)seqs.get(i)).getSeqId();
         this.dinerBillSeqDao.delete(id);
 
         map.put(id + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_seq,seq_id," + id);
       }
 
     }
 
     return seq;
   }
 
   public DinerBillSeq findDinerBillSeqListByOrder(String restId, Date billDate, LinkedHashMap<String, Object> map) {
     List seqs = this.dinerBillSeqDao.findByRestIdAndBillDateAndBillSeqType(restId, billDate, BillSeqTypeEnum.ORDER.getCode());
     DinerBillSeq seq = null;
     if ((seqs != null) && (seqs.size() == 1))
     {
       seq = (DinerBillSeq)seqs.get(0);
     }
     else if ((seqs != null) && (seqs.size() > 1))
     {
       seq = (DinerBillSeq)seqs.get(0);
       for (int i = 0; i < seqs.size(); i++) {
         if (i < 1)
           continue;
         String id = ((DinerBillSeq)seqs.get(i)).getSeqId();
         this.dinerBillSeqDao.delete(id);
 
         map.put(id + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_seq,seq_id," + id);
       }
 
     }
 
     return seq;
   }
 
   public DinerBillSeq findSeqListByZiZhu(String restId, Date billDate, LinkedHashMap<String, Object> map)
   {
     List seqs = this.dinerBillSeqDao.findByRestIdAndBillDateAndBillSeqType(restId, billDate, BillSeqTypeEnum.ZIZHU.getCode());
     DinerBillSeq seq = null;
     if ((seqs != null) && (seqs.size() == 1))
     {
       seq = (DinerBillSeq)seqs.get(0);
     }
     else if ((seqs != null) && (seqs.size() > 1))
     {
       seq = (DinerBillSeq)seqs.get(0);
       for (int i = 0; i < seqs.size(); i++) {
         if (i < 1)
           continue;
         String id = ((DinerBillSeq)seqs.get(i)).getSeqId();
         this.dinerBillSeqDao.delete(id);
 
         map.put(id + "_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_seq,seq_id," + id);
       }
 
     }
 
     return seq;
   }
 
   public synchronized String createNewBillNo(String restId, BillSeqTypeEnum SeqTypeEnum, LinkedHashMap<String, Object> map) {
     DinerBillSeq dinerBillSeq = new DinerBillSeq();
     String op = OperationTypeEnum.UPDATE.getCode();
     try {
       dinerBillSeq.setBillSeqType(SeqTypeEnum.getCode());
       dinerBillSeq.setBillDate(DateProvider.DEFAULT.getDate());
       dinerBillSeq.setRestId(restId);
       DinerBillSeq dinerBillSeqDb = null;
       if (BillSeqTypeEnum.BILL.getCode().equals(SeqTypeEnum.getCode()))
         dinerBillSeqDb = findDinerBillSeqListByBill(restId, dinerBillSeq.getBillDate(), map);
       else if (BillSeqTypeEnum.ORDER.getCode().equals(SeqTypeEnum.getCode())) {
         dinerBillSeqDb = findDinerBillSeqListByOrder(restId, dinerBillSeq.getBillDate(), map);
       }
       else {
         dinerBillSeqDb = findSeqListByZiZhu(restId, dinerBillSeq.getBillDate(), map);
       }
 
       if (dinerBillSeqDb != null) {
         dinerBillSeq = dinerBillSeqDb;
         dinerBillSeq.setBillSeq(dinerBillSeqDb.getBillSeq() + 1);
       } else {
         dinerBillSeq.setBillSeq(1);
         op = OperationTypeEnum.CREATE.getCode();
       }
       if ((dinerBillSeq.getBillSeq() == 1) && (BillSeqTypeEnum.BILL.getCode().equals(SeqTypeEnum.getCode())))
       {
         insertNextDaySeq(restId, BillSeqTypeEnum.BILL, map);
       }
       if ((dinerBillSeq.getBillSeq() == 1) && (BillSeqTypeEnum.ORDER.getCode().equals(SeqTypeEnum.getCode())))
       {
         insertNextDaySeq(restId, BillSeqTypeEnum.ORDER, map);
       }
       if ((dinerBillSeq.getBillSeq() == 1) && (BillSeqTypeEnum.ZIZHU.getCode().equals(SeqTypeEnum.getCode())))
       {
         insertNextDaySeq(restId, BillSeqTypeEnum.ZIZHU, map);
       }
       if (BillSeqTypeEnum.BILL.getCode().equals(SeqTypeEnum.getCode()))
         dinerBillSeq.setBillNo(getDinerBillNo(dinerBillSeq.getBillDate(), dinerBillSeq.getBillSeq()));
       else if (BillSeqTypeEnum.ORDER.getCode().equals(SeqTypeEnum.getCode())) {
         dinerBillSeq.setBillNo(getDinerBillOrderNo(dinerBillSeq.getBillDate(), dinerBillSeq.getBillSeq()));
       }
       else {
         dinerBillSeq.setBillNo(getZiZhuNo(dinerBillSeq.getBillDate(), dinerBillSeq.getBillSeq()));
       }
       this.self.save(dinerBillSeq);
 
       map.put(dinerBillSeq.getSeqId() + "_" + op, dinerBillSeq);
     }
     catch (HibernateOptimisticLockingFailureException e) {
       return createNewBillNo(restId, SeqTypeEnum, map);
     }
     return dinerBillSeq.getBillNo();
   }
 
   private void insertNextDaySeq(String restId, BillSeqTypeEnum SeqTypeEnum, LinkedHashMap<String, Object> map)
   {
     DinerBillSeq dinerBillSeq = new DinerBillSeq();
     dinerBillSeq.setBillSeqType(SeqTypeEnum.getCode());
     dinerBillSeq.setBillDate(DateUtil.getTime4XDaysAfter(1, DateProvider.DEFAULT.getDate()));
     dinerBillSeq.setRestId(restId);
     dinerBillSeq.setBillSeq(0);
     if (BillSeqTypeEnum.BILL.getCode().equals(SeqTypeEnum.getCode()))
       dinerBillSeq.setBillNo(getDinerBillNo(dinerBillSeq.getBillDate(), dinerBillSeq.getBillSeq()));
     else if (BillSeqTypeEnum.ORDER.getCode().equals(SeqTypeEnum.getCode())) {
       dinerBillSeq.setBillNo(getDinerBillOrderNo(dinerBillSeq.getBillDate(), dinerBillSeq.getBillSeq()));
     }
     else {
       dinerBillSeq.setBillNo(getZiZhuNo(dinerBillSeq.getBillDate(), dinerBillSeq.getBillSeq()));
     }
     this.self.save(dinerBillSeq);
 
     map.put(dinerBillSeq.getSeqId() + "_" + OperationTypeEnum.CREATE.getCode(), dinerBillSeq);
   }
 
   public String getDinerBillOrderNo(Date date, int seq)
   {
     return "YD" + getDayFormat(date) + getSeq(date, seq);
   }
 
   public String getDinerBillNo(Date date, int seq) {
     return getDayFormat(date) + getSeq(date, seq);
   }
 
   public String getZiZhuNo(Date date, int seq) {
     return "1" + getDayFormat(date) + getSeq(date, seq);
   }
 
   private String getSeq(Date date, int seq)
   {
     String seqStr = "000" + seq;
     String r = seqStr.substring(seqStr.length() - 4, seqStr.length());
 
     return r;
   }
 
   private String getDayFormat(Date date)
   {
     SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
     return format.format(date);
   }
 }

