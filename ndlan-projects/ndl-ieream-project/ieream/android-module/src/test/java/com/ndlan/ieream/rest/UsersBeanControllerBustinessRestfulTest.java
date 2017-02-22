package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.ieream.model.GuidesGetItemBean;
import com.ndlan.ieream.model.UsersGetItemBean;

public class UsersBeanControllerBustinessRestfulTest {
	UsersGetItemBeanController restful=new UsersGetItemBeanController();
	@Test
	public void getitem(  ){
		String  action ="";
		Integer  id=102;
		UsersGetItemBean guidesBean=restful.getitem( id);
		assertNotNull(guidesBean);
	}
}
