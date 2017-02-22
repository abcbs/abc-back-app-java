/**
 * 
 */
package com.ndlan.canyin.frontdesk.service.service3c.billinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.base3c.billinfo.ReturnReasonEntity;
import com.ndlan.canyin.base.repository.dao3c.billinfo.ReturnReasonDao;
import com.ndlan.canyin.frontdesk.service.BaseService;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月14日 上午10:25:00  
 */
@Service
@Transactional
public class ReturnReasonService  extends BaseService<ReturnReasonDao,ReturnReasonEntity>{
private ReturnReasonDao returnReasonDao;

@Autowired
public void setReturnReasonDao(ReturnReasonDao returnReasonDao) {
	super.setDao(returnReasonDao);
	this.returnReasonDao = returnReasonDao;
}

/**
 * 
 * @param restId
 * @return
 */
public List<ReturnReasonEntity> getList(String restId) {
	return this.returnReasonDao.getList(restId);
}

}
