package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.ieream.model.LocationsGetListBean;
import com.ndlan.ieream.model.UsersGetItemBean;

public class LocationsBeanControllerRestfulTest {
	LocationsBeanControllerRestful restful=new LocationsBeanControllerRestful();
	
	@Test
	public void getItemById(  ){
		String  action ="";
		Integer  id=102;
		LocationsGetListBean guidesBean=restful.getitem( id);
		assertNotNull(guidesBean);
	}
}
