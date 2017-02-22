package com.ndlan.canyin.frontdesk.service.service3c.memberinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndlan.canyin.base.entity.base3c.employee.BaseMembershipCardClass;
import com.ndlan.canyin.base.repository.dao3c.employee.BaseMemberShipCardClass3cDao;
import com.ndlan.canyin.frontdesk.service.BaseService;

@Service
public class BaseMemberShipCardClass3cService extends BaseService<BaseMemberShipCardClass3cDao,BaseMembershipCardClass>{
	
	 @Autowired
	 public void setDao(BaseMemberShipCardClass3cDao dao){
		 super.setDao(dao);
	 }
	public List<BaseMembershipCardClass> findAllBaseMembershipCardClass(){
		return (List<BaseMembershipCardClass>)getDao().findAllBaseMembershipCardClass();
	}

}
