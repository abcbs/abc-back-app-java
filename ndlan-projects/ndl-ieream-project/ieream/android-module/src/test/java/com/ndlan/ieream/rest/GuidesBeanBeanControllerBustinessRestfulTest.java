package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.ieream.model.GuidesGetItemBean;
import com.ndlan.ieream.model.GuidesGetListBean;
import com.ndlan.ieream.model.GuidesGetRellistBean;

public class GuidesBeanBeanControllerBustinessRestfulTest {
	GuidesBeanBeanControllerBustinessRestful restful=
			new GuidesBeanBeanControllerBustinessRestful();
	
	@Test
	public void getList(){
		Integer  p=0; 
		String  dataOrder="DES";
		GuidesGetListBean guidesBean=restful.getList(p, dataOrder);
		assertNotNull(guidesBean);
	}
	
	@Test
	public void getItemById(  ){
		String  action ="";
		Integer  id=102;
		GuidesGetItemBean guidesBean=restful.getItemById(   action ,    id);
		assertNotNull(guidesBean);
	}
	
	@Test
	public void  getRelList(){
		Integer  uid=3;
		Integer  dataType=0;
		GuidesGetRellistBean guidesBean=restful.getRelList(    uid ,     dataType);
		assertNotNull(guidesBean);//无数据返回
	}
}
