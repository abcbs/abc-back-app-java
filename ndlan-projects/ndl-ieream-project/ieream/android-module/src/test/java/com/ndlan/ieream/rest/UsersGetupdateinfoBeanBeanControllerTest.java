package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.ieream.model.UsersGetItemBean;
import com.ndlan.ieream.model.UsersGetupdateinfoBean;

public class UsersGetupdateinfoBeanBeanControllerTest {
	UsersGetupdateinfoBeanBeanControllerBustinessRestful restful=new UsersGetupdateinfoBeanBeanControllerBustinessRestful();
	@Test
	public void getitem(  ){
		String  version_type ="";
		Integer  version_id=102;
		UsersGetupdateinfoBean guidesBean=restful.getupdateinfo(version_type, version_id);
		assertNotNull(guidesBean);
	}
}
