 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.front.controller3c.productinfo.PaymentTypeController;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
 import com.ndlan.canyin.base.repository.sygl.PaymentTypeDao;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.SysDataTypeEnum;
 import com.ndlan.canyin.core.persistence.DynamicSpecifications;
 import com.ndlan.canyin.core.persistence.SearchFilter;
 import java.util.List;
 import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.domain.Sort.Direction;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class PaymentTypeService extends BaseService<PaymentTypeDao, PaymentType>
 {
   private static final Logger logger = LoggerFactory.getLogger(PaymentTypeService.class.getName());
	 
   private PaymentTypeDao paymentTypeDao;
 
   public Page<PaymentType> searchPaymentType(Map<String, Object> searchParams, int pageNumber, int pagzSize)
   {
     PageRequest pageRequest = new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.ASC, new String[] { "createTime" }));
     Map filters = SearchFilter.parse(searchParams);
     Specification spec = DynamicSpecifications.bySearchFilter(filters.values(), PaymentType.class);
     return ((PaymentTypeDao)getDao()).findAll(spec, pageRequest);
   }
 
   public List<PaymentType> findPaymentTypeByRestID(String restId)
   {
     return this.paymentTypeDao.findByRestIdAndEnableStatusOrderByShowSeqAscCreateTimeAsc(restId, EnableStatusEnum.NORMAL.getCode());
   }
 
   public List<PaymentType> findPaymentTypeByAllRestID(String restId)
   {
     return this.paymentTypeDao.findByRestIdOrderByShowSeqAscCreateTimeAsc(restId);
   }
 
   public PaymentType findeByRestIdAndPaymentType(String restId, String paymentType)
   {
     return this.paymentTypeDao.findByRestIdAndPaymentTypeAndSysdataTypeAndEnableStatus(restId, paymentType, SysDataTypeEnum.DEFAULT.getCode(), EnableStatusEnum.NORMAL.getCode());
   }
 
   public List<PaymentType> findByRestIdAndEnableStatusAndPaymentTypeIn(String restId, List<String> paymentTypes)
   {
     return this.paymentTypeDao.findByRestIdAndEnableStatusAndPaymentTypeIn(restId, EnableStatusEnum.NORMAL.getCode(), paymentTypes);
   }
   
   
   
   @Autowired
   public void setDao(PaymentTypeDao dao) {
     super.setDao(dao);
     this.paymentTypeDao = dao;
   }
	
   
   /**
    * 获得现金id
    * @param restId
    */
	public PaymentType getCptId(String restId) {
		return this.paymentTypeDao.getCptId(restId);
		
	}

/**
 * @param restId
 * @return
 */
public List<PaymentType> findPaymentTypeByRestIDAndPayStatus(String restId) {
	logger.info("----------------------------------> findPaymentTypeByRestIDAndPayStatus");
	// TODO Auto-generated method stub
	return this.paymentTypeDao.findPaymentTypeByRestIDAndPayStatus(restId);
}
 }

