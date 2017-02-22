package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.ieream.model.LocationsGetListBean;

public class LocationsGetListBeanBeanControllerBustinessRestfulTest {

	LocationsGetListBeanBeanControllerBustinessRestful restful=new 
			LocationsGetListBeanBeanControllerBustinessRestful();
	
	
	@Test
	public void getItemById(  ){
		String  action ="";
		Integer  id=102;
		LocationsGetListBean guidesBean=restful.getlist(null, null, null, null, null);
		assertNotNull(guidesBean);
	}
}
