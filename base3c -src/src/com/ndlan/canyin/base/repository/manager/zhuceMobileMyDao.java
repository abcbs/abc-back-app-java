package com.ndlan.canyin.base.repository.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ndlan.canyin.base.entity.ctzh.Accredit;
import com.ndlan.canyin.base.repository.MyBatisRepository;


@MyBatisRepository
public abstract interface zhuceMobileMyDao {
	
	  public abstract List getZhuceMobilelsit(@Param("endDate") String paramString1, @Param("startDate") String paramString2, @Param("restId") String paramString3);
	  
	  public abstract List getOneByaccId(@Param("authorization") String paramString1);
	  public abstract int countById(@Param("authorization") String paramString1);
	  public abstract void createEmplee(@Param("authorization") String paramString1,@Param("phone") String paramString2,@Param("password") String paramString3,@Param("empId") String paramString4,@Param("string") String paramString5);
}
