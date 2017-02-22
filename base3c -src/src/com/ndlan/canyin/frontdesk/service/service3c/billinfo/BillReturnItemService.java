/**
 * 
 */
package com.ndlan.canyin.frontdesk.service.service3c.billinfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.base3c.billinfo.BillItemEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillReturnItemEntity;
import com.ndlan.canyin.base.repository.dao3c.billinfo.BillRetrunItemDao;
import com.ndlan.canyin.frontdesk.front.controller3c.billinfo.Bill3cController;
import com.ndlan.canyin.frontdesk.service.BaseService;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月9日 上午11:49:37  
 */
@Service
@Transactional
public class BillReturnItemService extends BaseService<BillRetrunItemDao,BillReturnItemEntity>{
	private static final Logger logger = LoggerFactory.getLogger(BillReturnItemService.class.getName());
	private BillRetrunItemDao billReturnItemDao;

	@Autowired
	public void setBillReturnItemDao(BillRetrunItemDao billReturnItemDao) {
		super.setDao(billReturnItemDao);
		this.billReturnItemDao = billReturnItemDao;
	}

	public BillRetrunItemDao getBillReturnItemDao() {
		return billReturnItemDao;
	}

	public List<BillReturnItemEntity> getBillReturnItemByBdId(String bdId,
			String restId) {
		logger.info("----------------------------------> getBillReturnItemByBdId");
		return this.billReturnItemDao.getBillReturnItemByBdId(bdId,restId);
	}

	/**
	 * 创建退货明细
	 * @param billItem
	 * @param restId
	 * @param returnReason
	 * @param returnTotal
	 * @param returnType
	 * @param returnQuantity
	 */
	public String insertBillReturnItem(BillItemEntity billItem, String restId,
			String returnReason, String returnTotal, String returnType,
			String returnQuantity) {
		BillReturnItemEntity billReturn=new BillReturnItemEntity();
		billReturn.setBdId(billItem.getBdId());
		billReturn.setProId(billItem.getProId());
		billReturn.setProDesc(billItem.getProDesc());
		billReturn.setBarCode(billItem.getBarCode());
		billReturn.setBdNo(billItem.getBdNo());
		billReturn.setbId(billItem.getbId());
		billReturn.setCategoryId(billItem.getCategoryId());
		billReturn.setDerate(billItem.getDerate());
		billReturn.setDiscount(billItem.getDiscount());
		billReturn.setName(billItem.getName());
		billReturn.setPayStatus(billItem.getPayStatus());
		billReturn.setPic(billItem.getPic());
		billReturn.setPrice(billItem.getPrice());
		billReturn.setQuantity(billItem.getQuantity());
		billReturn.setRestId(restId);
		billReturn.setReturnPrice(billItem.getPrice());
		billReturn.setReturnQuantity(returnQuantity);
		billReturn.setReturnReason(returnReason);
		billReturn.setReturnTotal(returnTotal);
		billReturn.setReturnType(returnType);
		billReturn.setSubtotal(billItem.getSubtotal());
		save(billReturn);	
		return "success";
	}
	
}
