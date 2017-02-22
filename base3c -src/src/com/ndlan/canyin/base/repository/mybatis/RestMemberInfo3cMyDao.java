package com.ndlan.canyin.base.repository.mybatis;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ndlan.canyin.base.repository.MyBatisRepository;
import com.ndlan.canyin.frontdesk.dto3c.memberinfo.MemberInfoEntityDto;

@MyBatisRepository
public interface RestMemberInfo3cMyDao {
	public abstract List<MemberInfoEntityDto> getMemberList(String paramString1,String paramString2,int start,int pageSize);
	public abstract int countMemberList(String paramString1,String paramString2);
}
