package com.ndlan.cwwarm.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import com.ndlan.framework.core.status.EnumStatus;
import com.ndlan.framework.core.web.domain.ConditionLike;
import com.ndlan.framework.core.web.domain.ConditionOr;
import com.ndlan.framework.core.web.domain.PageOrder;
import com.ndlan.framework.core.web.domain.PageQuery;
import com.ndlan.framework.core.web.domain.PageSort;
import com.ndlan.framework.core.web.domain.Result;

import com.ndlan.cwwarm.query.CmEmployeeQuery;
import com.ndlan.cwwarm.model.CmEmployeeBean;



public class CmEmployeeBeanBustinessRestfulTest {
	
	CmEmployeeBeanBustinessRestful productCatatoryBustinessRestful=
			new CmEmployeeBeanBustinessRestful();
	@Test
	public void selectAll() {
		Result result= productCatatoryBustinessRestful.selectAll();
		assertNotNull(result);
		
	}

	@Test
	public void deleteList() {
		String[] ids={"002","002"};
		Result result=productCatatoryBustinessRestful.deleteList(ids);
		assertNotNull(result);
	}

	@Test
	public void deleteOne() {
		String defualtId="11";
		Result result=productCatatoryBustinessRestful.deleteOne(defualtId);
		assertNotNull(result);
	}

	@Test
	public void addOneTest() {
		CmEmployeeBean entity=new CmEmployeeBean();
		//todo
	
		Result result=productCatatoryBustinessRestful.addOne(entity);
		assertNotNull(result);
	}

	@Test
	public void selectList() {
		CmEmployeeQuery entity=new CmEmployeeQuery();
		//todo
		ConditionLike like=new ConditionLike("loginUsernameLike","TestTest");
//		entity.setLoginUsernameLike("test");
//		entity.setNameLike("张三");
		entity.setResidenceAdrOr("北京");
		entity.setAdrCityOr("北京");
//		//like.addConditionLike("nameLike", "p1233445");
//		
//		//ConditionOr or=new ConditionOr("adrCity","北京");
//		//or.addConditionOr("adrCity","上海");
//				
////		PageSort sort=new PageSort(new Sort.Order(Direction. DESC,"uuid"));
		PageOrder order=new PageOrder("uuid");
		PageSort  sort=new  PageSort();
		sort.addOrder(order);
		PageQuery pageable=new PageQuery(0,10,sort);
		entity.setPageable(pageable);
		Result result=productCatatoryBustinessRestful.selectList(entity);
		assertNotNull(result);
		
	}

	@Test
	public void viewOne() {
		String defualtId="11";
		Result result=productCatatoryBustinessRestful.viewOne(defualtId);
		assertNotNull(result);

	}

	@Test
	public void editOne() {
		CmEmployeeBean entity=new CmEmployeeBean();
		//todo
		Result result=productCatatoryBustinessRestful.editOne(entity);
		assertNotNull(result);
	}

	@Test
	public void editSelective() {
		CmEmployeeBean entity=new CmEmployeeBean();
		//todo
		Result result=productCatatoryBustinessRestful.editSelective(entity);
		assertNotNull(result);
	}
}
