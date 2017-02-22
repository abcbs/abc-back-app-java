package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.ChainStore;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.RestaurantPic;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
/**
 * 
 * @author husitong
 *
 */
public abstract interface RestaurantDao extends BaseDao<Restaurant, String>
{
  public abstract List<Restaurant> findByRestId(String paramString);

  @Query("select a from Restaurant a ")
  public abstract List<Restaurant> findAll();

  public abstract List<Restaurant> findByAdrCity(String paramString);
  
  @Query("select t from Restaurant t where t.storeId in ( select t.storeId from ChainStore t where t.path like ?1 ) and (t.restExamine = '2'  or t.restExamine = '5') ")
  public abstract List<Restaurant> findByStoreId(String paramString1);
  
  @Query("select t from Restaurant t where t.restId = ?1 and t.restExamine <> '-1' ")
  public abstract List<Restaurant> findByUserRestId(String paramString1);
 
  @Query("select t from Restaurant t where t.storeId in ( select t.storeId from ChainStore t where t.path like ?1 ) and ( t.restExamine = '2' or t.restExamine = '5') ")
  public abstract List<Restaurant> findRestByEXmin(String paramString1);
  
  @Query("select t from Restaurant t where t.restId = ?1 and ( t.restExamine = '2' or t.restExamine = '5' ) ")
  public abstract List<Restaurant> findRestByEXminAndRestId(
		String paramString1);
  
  @Query("select t from Restaurant t where t.storeId = ?1 ")
  public abstract List<Restaurant> findOneByStoreId(String paramString1);
  
  @Query("select b.mid,b.restId,b.restName,b.adrDetail,b.linkmanPhone from Restaurant b WHERE b.restId=(select a.restaurant.restId from Employee a WHERE a.loginUsername =?1) ")
  public abstract Object[] getMid(String paramString1);
  @Query("select a.picUrl from RestaurantPic a where a.restaurant.restId=?1 and a.picType='3'")
public abstract List<RestaurantPic> getrestpic(String paramString1);
  
  @Query("select a from RestaurantPic a where a.restaurant.restId=?1 and a.picType=3")
  public abstract List<RestaurantPic> getrestpicTypeThree(String paramString1);

  @Query("select a from RestaurantPic a where a.restaurant.restId=?1 and a.picType <> 3")
  public abstract List<RestaurantPic> getrestpicType(String paramString1);
}

