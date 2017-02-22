 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.RestaurantPic;
import com.ndlan.canyin.base.repository.ctzh.RestaurantDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional(readOnly=true)
 public class RestaurantService extends BaseService<RestaurantDao, Restaurant>
 {
   @Autowired
   public void setBaseDao(RestaurantDao restaurantDao)
   {
     super.setDao(restaurantDao);
   }
 
   public List<Restaurant> findAllByRestId(String restId) {
     return ((RestaurantDao)getDao()).findByRestId(restId);
   }
 
   public List<Restaurant> findAll() {
     return ((RestaurantDao)getDao()).findAll();
   }

public  Object[] getMid(String username) {
	// TODO Auto-generated method stub
	return ((RestaurantDao)getDao()).getMid(username);
}

public List<RestaurantPic> getrestpic(String string) {
	// TODO Auto-generated method stub
	return ((RestaurantDao)getDao()).getrestpic(string);
}

 }

