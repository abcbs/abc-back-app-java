package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.framework.core.status.EnumStatus;
import com.ndlan.framework.core.web.domain.Result;

import com.ndlan.ieream.query.B2bPriceCategoryDetailHisQuery;
import com.ndlan.ieream.model.B2bPriceCategoryDetailHisBean;

public class B2bPriceCategoryDetailHisBeanBustinessRestfulTest {
	
	B2bPriceCategoryDetailHisBeanBustinessRestful productCatatoryBustinessRestful=
			new B2bPriceCategoryDetailHisBeanBustinessRestful();
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
		B2bPriceCategoryDetailHisBean entity=new B2bPriceCategoryDetailHisBean();
		//todo
	
		Result result=productCatatoryBustinessRestful.addOne(entity);
		assertNotNull(result);
	}

	@Test
	public void selectList() {
		B2bPriceCategoryDetailHisQuery entity=new B2bPriceCategoryDetailHisQuery();
		//todo
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
		B2bPriceCategoryDetailHisBean entity=new B2bPriceCategoryDetailHisBean();
		//todo
		Result result=productCatatoryBustinessRestful.editOne(entity);
		assertNotNull(result);
	}

	@Test
	public void editSelective() {
		B2bPriceCategoryDetailHisBean entity=new B2bPriceCategoryDetailHisBean();
		//todo
		Result result=productCatatoryBustinessRestful.editSelective(entity);
		assertNotNull(result);
	}
}
