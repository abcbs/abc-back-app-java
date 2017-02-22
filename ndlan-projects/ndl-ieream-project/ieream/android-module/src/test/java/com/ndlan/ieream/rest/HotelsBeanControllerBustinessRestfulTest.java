package com.ndlan.ieream.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.ieream.model.GuidesGetListBean;
import com.ndlan.ieream.model.GuidesGetRellistBean;
import com.ndlan.ieream.model.HotelsGetItemBean;
import com.ndlan.ieream.model.HotelsGetRellistBean;
import com.ndlan.ieream.model.HotelsSearchBean;

public class HotelsBeanControllerBustinessRestfulTest {
	HotelsBeanControllerBustinessRestful restful=new HotelsBeanControllerBustinessRestful();
	
	@Test
	public void getList(){
		Integer  p=0; 
		String  dataOrder="DES";
		GuidesGetListBean guidesBean=restful.getList(p, dataOrder);
		assertNotNull(guidesBean);
	}
	
	@Test
	public void getItemById(){
		Integer  p=0; 
		HotelsGetItemBean hotelsBean=restful.getitem( 1);
		assertNotNull(hotelsBean);
	}
	
	@Test
	public void  getRelList(){
		Integer  uid=3;
		Integer  dataType=0;
		HotelsGetRellistBean guidesBean=restful.getRelList(    uid ,     dataType);
		assertNotNull(guidesBean);//
	}
	
	@Test
	public void search(){
		Integer  uid=3;
		Integer  dataType=0;
		HotelsSearchBean guidesBean=restful.search(  uid ,     dataType);
		assertNotNull(guidesBean);//无数据返回
	}
	
}
