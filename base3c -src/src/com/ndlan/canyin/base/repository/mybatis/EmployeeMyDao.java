package com.ndlan.canyin.base.repository.mybatis;

import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Table;
import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface EmployeeMyDao
{
  public abstract List<Employee> getAll(@Param("restId") String paramString1, @Param("jobStatus") String paramString2, @Param("roleType") String paramString3, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract int getAllCount(@Param("restId") String paramString1, @Param("jobStatus") String paramString2, @Param("roleType") String paramString3);

  public abstract List<AuthorityModule> getAuthorityModule(@Param("restId") String paramString1, @Param("userId") String paramString2, @Param("isStopUse") String paramString3);

  public abstract List<Table> getAllTable(@Param("restId") String paramString1, @Param("areaId") String paramString2, @Param("dinnerStatus") String paramString3, @Param("start") int paramInt1, @Param("pageSize") int paramInt2, @Param("isHasAllArea") String paramString4, @Param("userId") String paramString5, @Param("tabNo") String paramString6);

  public abstract int getAllTableCount(@Param("restId") String paramString1, @Param("areaId") String paramString2, @Param("dinnerStatus") String paramString3, @Param("isHasAllArea") String paramString4, @Param("userId") String paramString5, @Param("tabNo") String paramString6);

  public abstract List<Map<String, String>> getDisheSales(@Param("restId") String paramString1, @Param("startDate") String paramString2, @Param("endDate") String paramString3);

  public abstract List<Map<String, String>> getDisheSetSales(@Param("restId") String paramString1, @Param("startDate") String paramString2, @Param("endDate") String paramString3);

  public abstract List<Map<String, Object>> getDishesSalesDetail(@Param("restId") String paramString1, @Param("startDate") String paramString2, @Param("endDate") String paramString3, @Param("categoryId") String paramString4);

  public abstract List<Map<String, Object>> getDishesSetSalesDetail(@Param("restId") String paramString1, @Param("startDate") String paramString2, @Param("endDate") String paramString3, @Param("dsCategoryId") String paramString4);

  public abstract List<Table> getOrderAllTable(@Param("restId") String paramString1, @Param("areaId") String paramString2, @Param("dinnerStatus") String paramString3, @Param("start") int paramInt1, @Param("pageSize") int paramInt2, @Param("isHasAllArea") String paramString4, @Param("userId") String paramString5, @Param("tabNo") String paramString6, @Param("datebegin") String paramString7, @Param("dateend") String paramString8);

  public abstract List<Employee> getAllByRoleType(@Param("restId") String paramString1, @Param("jobStatus") String paramString2, @Param("roleType") String paramString3, @Param("searchValue") String paramString4,@Param("start") int paramInt1, @Param("pageSize") int paramInt2);
  
}

