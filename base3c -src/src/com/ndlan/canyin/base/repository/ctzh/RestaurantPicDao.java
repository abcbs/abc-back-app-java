package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.RestaurantPic;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface RestaurantPicDao extends BaseDao<RestaurantPic, String>
{
  public abstract List<RestaurantPic> findByPicUrl(String paramString);

  public abstract List<RestaurantPic> findByRestaurant(Restaurant paramRestaurant);
}

