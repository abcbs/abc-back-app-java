/**
 * 
 */
package com.ndlan.canyin.base.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ndlan.canyin.base.entity.base3c.billinfo.BillEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillReturnItemEntity;
import com.ndlan.canyin.base.repository.MyBatisRepository;
import com.ndlan.canyin.frontdesk.dto3c.billinfo.BillItemDto;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月8日 下午2:18:48  
 */
@MyBatisRepository
public interface OrderMyDao {

	
	List getBillList(@Param("restId") String paramString1 , @Param("startDate") String paramString2,@Param("endDate") String paramString3, @Param("offset") String paramString4 , @Param("pageSize") String paramString5 , @Param("searchValue") String paramString6  );

	
	List<BillItemDto> getBillItemList(@Param("bId") String paramString1 , @Param("restId") String paramString2 );


	List getPayList(@Param("mbId") String paramString1 , @Param("restId") String paramString2, @Param("offset") String paramString3 , @Param("pageSize") String paramString4 , @Param("searchValue") String paramString5  );


	
}
