/**
 * 
 */
package com.ndlan.canyin.base.repository.dao3c.billinfo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.billinfo.ReturnReasonEntity;
import com.ndlan.canyin.base.repository.BaseDao;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月14日 上午10:23:32  
 */
public abstract interface ReturnReasonDao extends BaseDao<ReturnReasonEntity, String>{

	@Query("select r from ReturnReasonEntity r where r.restId=? ")
	List<ReturnReasonEntity> getList(String restId);

}
