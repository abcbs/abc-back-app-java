package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CmRestaurantBean;
import com.ndlan.cwwarm.query.CmRestaurantQuery;

import com.ndlan.cwwarm.component.CmRestaurantBeanBusinessService;



@Service("cmRestaurantBeanBusinessService")
public class CmRestaurantBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CmRestaurantBean>  
	implements CmRestaurantBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cmRestaurantBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
