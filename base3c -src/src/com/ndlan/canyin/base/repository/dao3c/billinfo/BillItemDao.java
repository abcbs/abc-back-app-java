/**
 * 
 */
package com.ndlan.canyin.base.repository.dao3c.billinfo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.billinfo.BillEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillItemEntity;
import com.ndlan.canyin.base.repository.BaseDao;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月7日 下午3:36:37  
 */
public abstract interface BillItemDao extends BaseDao<BillItemEntity,String>{
	@Query("select b from BillItemEntity b where b.bId=? and b.restId=?")
	List<BillItemEntity> getBillItemList(String bId, String restId);

}
