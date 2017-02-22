/**
 * 
 */
package com.ndlan.canyin.base.repository.dao3c.billinfo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.billinfo.BillReturnItemEntity;
import com.ndlan.canyin.base.repository.BaseDao;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月9日 上午11:48:13  
 */
public abstract interface BillRetrunItemDao extends BaseDao<BillReturnItemEntity,String>{
	@Query("select b from BillReturnItemEntity b where b.bId=? and b.restId=? ")
	List<BillReturnItemEntity> getBillReturnListByBId(String bId, String restId);
	@Query("select b from BillReturnItemEntity b where b.bdId=? and restId=? ")
	List<BillReturnItemEntity> getBillReturnItemByBdId(String bdId,String restId);

}
