package com.ndlan.canyin.base.repository.meta;

import com.ndlan.canyin.base.entity.meta.Region;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface RegionDao extends BaseDao<Region, String>
{
  public abstract List<Region> findByProvinceCodeAndRegionDiv(String paramString1, String paramString2);

  public abstract List<Region> findByCityCodeAndRegionDiv(String paramString1, String paramString2);

  public abstract List<Region> findByRegionDiv(String paramString);

  public abstract List<Region> findByCityCode(String paramString);

  public abstract List<Region> findByAreaCode(String paramString);

  public abstract List<Region> findByProvinceCodeAndCityCodeAndAreaCode(String paramString1, String paramString2, String paramString3);

  @Query("select r.cityName,r.cityCode from Region r group by r.cityName,r.cityCode")
  public abstract List<Region> getCity();

  @Query("select r from Region r where  to_pinyin(r.cityName) LIKE ?1 or r.cityName like ?2")
  public abstract List<Region> getCityCode(String paramString1, String paramString2);

  public abstract List<Region> findByCityName(String paramString);

  @Query("select r from Region r where r.cityName like ?1")
  public abstract List<Region> getCityCodeByName(String paramString);

  @Query("select r from Region r where concat(r.provinceName,r.cityName) like ?1")
  public abstract List<Region> getCityByProvinceAndCity(String paramString);
}

