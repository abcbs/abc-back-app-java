package com.ndlan.canyin.base.repository.manager;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.manager.Sendsms;
import com.ndlan.canyin.base.repository.BaseDao;

public abstract interface SendsmsDao extends BaseDao<Sendsms, String> {
	@Query("select a from Sendsms a ")
	 public abstract Sendsms getSendsmsById();

}
