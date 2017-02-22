 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.qtsy.Coupons;
import com.ndlan.canyin.base.repository.qtsy.CouponsDao;
import com.ndlan.canyin.frontdesk.service.BaseService;


 
 @Service
 public class CouponsService extends BaseService<CouponsDao, Coupons>
 {
	 
	 CouponsDao couponsDao;
	 
	 @Autowired
	 public void setDao(CouponsDao couponsDao) {
		super.setDao(couponsDao);
		this.couponsDao = couponsDao;
	}

	 @Transactional(readOnly=false)
	 public Coupons saveCoupons(Coupons cou) {
			// TODO Auto-generated method stub
		return this.self.save(cou);
	 }

	public Coupons updateCoupons(String couId, String couType, String couNo,
			Double couAmount, String couCompany, String couRangeType) {
		Coupons c = this.couponsDao.findByCouId(couId);
		//判断如果对象为null，当前字段不需要update
		if(null != couType)
		c.setCouType(couType);
		if(null != couNo)
		c.setCouNo(couNo);
		if(null != couAmount)
		c.setCouAmount(couAmount);
		if(null != couCompany)
		c.setCouCompany(couCompany);
		if(null != couRangeType)
		c.setCouRangeType(couRangeType);
		return this.self.save(c);
	}  
  
 
 }

