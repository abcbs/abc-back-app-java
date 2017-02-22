package com.ndlan.canyin.frontdesk.service.service3c.productinfo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.ndlan.canyin.base.entity.base3c.productinfo.PayItem;
import com.ndlan.canyin.base.repository.dao3c.productinfo.PayItemDao;
import com.ndlan.canyin.base.repository.mybatis.OrderMyDao;
import com.ndlan.canyin.frontdesk.common.Arith;
import com.ndlan.canyin.frontdesk.front.controller3c.billinfo.Bill3cController;
import com.ndlan.canyin.frontdesk.service.BaseService;

@Service
@Lazy
@Transactional
public class PayItemService extends BaseService<PayItemDao, PayItem>{
	private static final Logger logger = LoggerFactory.getLogger(PayItemService.class.getName());
	private PayItemDao payItemDao;
	@Autowired
	private OrderMyDao orderMyDao;
	
	@Autowired
	public void setDao(PayItemDao dao) {
		super.setDao(dao);
		this.payItemDao=dao;
	}

	public PayItemDao getPayItemDao() {
		return payItemDao;
	}

	public void setPayItemDao(PayItemDao payItemDao) {
		this.payItemDao = payItemDao;
	}

	//会员消费记录查询
	public List getPayList(String mbId, String restId, String pageNumber, String pageSize, String searchValue) {
		if(StringUtils.isBlank(searchValue)){
			searchValue=null;
		}else{
			searchValue="%"+searchValue+"%";
		}
		String offset=Arith.mul(pageSize, Arith.sub(pageNumber, "1"));
		return this.orderMyDao.getPayList(mbId,restId,offset,pageSize,searchValue);
	}
	/**
	 * 创建支付明细
	 * @param bId
	 * @param amountPaid
	 * @param currency
	 * @param payStatus
	 * @param payType
	 * @param pNo
	 * @param restId
	 * @return
	 */
	public String insertPayItem(String bId, String amountPaid, String currency,
			String payStatus, String payType, String pNo, String restId) {
		PayItem payItem=new PayItem();
		payItem.setbId(bId);
		payItem.setPayAmount(amountPaid);
		payItem.setCurrency(currency);
		payItem.setPayStatus(payStatus);
		payItem.setPayType(payType);
		payItem.setpNo(pNo);
		payItem.setRestId(restId);
		this.save(payItem);
		return "success";
	}

	/**
	 * 更新支付明细
	 * @param bId
	 * @param payStatus
	 * @param restId
	 * @return
	 */
	public String updatePayItem(String bId, String payStatus, String restId) {
		PayItem payItem = null;
		try{
			payItem=this.payItemDao.getOneByBIdAndPayStatus(bId,"1",restId);//处于支付中的订单
			if(payItem==null){
				return "fail";
			}
		}catch(Exception e){
			logger.error("获取处于支付中的订单明细时出错，"+ e.getLocalizedMessage(), e);
			return "fail";
		}
		payItem.setPayStatus(payStatus);
		this.save(payItem);
		return null;
	}

	
	

}
