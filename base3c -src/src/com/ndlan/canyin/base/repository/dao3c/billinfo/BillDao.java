/**
 * 
 */
package com.ndlan.canyin.base.repository.dao3c.billinfo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.billinfo.BillEntity;
import com.ndlan.canyin.base.repository.BaseDao;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月7日 下午3:31:25  
 */
public abstract interface BillDao extends BaseDao<BillEntity,String>{
	@Query("select b from BillEntity b where b.mbId=? and b.restId=?")
	List<BillEntity> getOneByMbId(String mbId, String restId);

}
