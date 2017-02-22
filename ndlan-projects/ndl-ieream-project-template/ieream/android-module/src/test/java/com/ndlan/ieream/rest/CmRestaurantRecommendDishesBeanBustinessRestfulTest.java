package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.framework.core.status.EnumStatus;
import com.ndlan.framework.core.web.domain.Result;

import com.ndlan.ieream.query.CmRestaurantRecommendDishesQuery;
import com.ndlan.ieream.model.CmRestaurantRecommendDishesBean;

public class CmRestaurantRecommendDishesBeanBustinessRestfulTest {
	
	CmRestaurantRecommendDishesBeanBustinessRestful productCatatoryBustinessRestful=
			new CmRestaurantRecommendDishesBeanBustinessRestful();
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
		CmRestaurantRecommendDishesBean entity=new CmRestaurantRecommendDishesBean();
		//todo
	
		Result result=productCatatoryBustinessRestful.addOne(entity);
		assertNotNull(result);
	}

	@Test
	public void selectList() {
		CmRestaurantRecommendDishesQuery entity=new CmRestaurantRecommendDishesQuery();
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
		CmRestaurantRecommendDishesBean entity=new CmRestaurantRecommendDishesBean();
		//todo
		Result result=productCatatoryBustinessRestful.editOne(entity);
		assertNotNull(result);
	}

	@Test
	public void editSelective() {
		CmRestaurantRecommendDishesBean entity=new CmRestaurantRecommendDishesBean();
		//todo
		Result result=productCatatoryBustinessRestful.editSelective(entity);
		assertNotNull(result);
	}
}
