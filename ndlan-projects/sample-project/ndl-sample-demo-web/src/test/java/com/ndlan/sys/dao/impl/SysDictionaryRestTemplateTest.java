package com.ndlan.sys.dao.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.framework.core.status.EnumStatus;
import com.ndlan.framework.core.web.domain.Result;
import com.ndlan.framework.demo.domain.SysDictionary;
import com.ndlan.sys.rest.android.ProductCatatoryBustinessRestful;;

public class SysDictionaryRestTemplateTest {
	
	ProductCatatoryBustinessRestful productCatatoryBustinessRestful=
			new ProductCatatoryBustinessRestful();
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
		SysDictionary entity=new SysDictionary();
		entity.setDicName("test");
		entity.setDicValue("001");
		entity.setDicStatus(EnumStatus.ON);
		Result result=productCatatoryBustinessRestful.addOne(entity);
		assertNotNull(result);
	}

	@Test
	public void selectList() {
		SysDictionary entity=new SysDictionary();
		entity.setDicName("test");
		entity.setDicValue("001");
		entity.setDicStatus(EnumStatus.ON);
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
		SysDictionary entity=new SysDictionary();
		entity.setDicName("test");
		entity.setDicValue("001");
		entity.setDicStatus(EnumStatus.ON);
		Result result=productCatatoryBustinessRestful.editOne(entity);
		assertNotNull(result);
	}


}
