package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.qtsy.EmployeShift;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.Date;
import java.util.List;

public abstract interface EmployeShiftDao extends BaseDao<EmployeShift, String>
{
  public abstract List<EmployeShift> findByRestIdAndCreateEmployeeOrderByCreateTimeDesc(String paramString, Employee paramEmployee);

  public abstract List<EmployeShift> findByRestIdOrderByCreateTimeDesc(String paramString);

  public abstract List<EmployeShift> findByRestIdAndCreateTimeLessThanOrderByCreateTimeDesc(String paramString, Date paramDate);
}

