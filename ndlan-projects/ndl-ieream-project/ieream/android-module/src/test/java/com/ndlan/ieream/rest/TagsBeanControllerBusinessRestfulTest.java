package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.ieream.model.LocationsGetListBean;
import com.ndlan.ieream.model.TagsGetListBean;

public class TagsBeanControllerBusinessRestfulTest {
	TagsBeanControllerBusinessRestful restful=new TagsBeanControllerBusinessRestful();
	@Test
	public void getItemById(  ){
		String  action ="";
		Integer  id=102;
		TagsGetListBean guidesBean=restful.getList(id);
		assertNotNull(guidesBean);
	}
}
