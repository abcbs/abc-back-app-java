package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderBean;
import com.ndlan.g2.b2b.dao.B2bOrderDao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.ndlan.canyin.main.service.BaseService;

@Transactional
@Component("b2bOrderAtomService")
public class B2bOrderAtomServiceImpl  extends BaseService<B2bOrderDao, 
	B2bOrderBean>      implements B2bOrderAtomService {

    @Resource(name="b2bOrderDao")
    protected B2bOrderDao b2bOrderDao;

    @Override
    public int saveB2bOrderBean(B2bOrderBean b2bOrder) {
        trimStringValue(b2bOrder);
        return b2bOrderDao.insertSelective(b2bOrder);
    }

    @Override
    public int saveAndGetId(B2bOrderBean b2bOrder) {
        trimStringValue(b2bOrder);
        return b2bOrderDao.insertSelectiveAndGetId(b2bOrder);
    }

    @Override
    public int update(B2bOrderBean b2bOrder) {
        trimStringValue(b2bOrder);
        return b2bOrderDao.updateByPrimaryKeySelective(b2bOrder);
    }

    @Override
    public int saveOrUpdateB2bOrderBean(B2bOrderBean b2bOrder) {
        if (null == b2bOrder.getBId() ||
		"" == b2bOrder.getBId()) {
            return saveB2bOrderBean(b2bOrder);
        } else {
            return update(b2bOrder);
        }
    }

    @Override
    public B2bOrderBean getB2bOrderBean(String bId) {
        return b2bOrderDao.selectByPrimaryKey(bId);
    }

    @Override
    public List<B2bOrderBean> getAll() {
        return b2bOrderDao.selectAll();
    }

    @Override
    public void delete(String bId) {
         b2bOrderDao.deleteByPrimaryKey(bId);
    }

    public List<B2bOrderBean> queryB2bOrderBean
	(B2bOrderBean b2bOrder, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bOrder);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bOrderDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bOrderDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bOrderDao.update(sql, args);
    }

    public List<B2bOrderBean> queryB2bOrderBean
	(B2bOrderBean b2bOrder){
	SQLParam sqlParam=getWhereSQL(b2bOrder);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bOrderDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bOrderBean b2bOrder) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String customerId = b2bOrder.getCustomerId();
        if (StringUtils.isNotBlank(customerId) ) {
           sqlBuffer.append("customerId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getCustomerId());
        }
      
            
        String codelessSum = b2bOrder.getCodelessSum();
        if (StringUtils.isNotBlank(codelessSum) ) {
           sqlBuffer.append("codelessSum=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getCodelessSum());
        }
      
            
        String allDiscount = b2bOrder.getAllDiscount();
        if (StringUtils.isNotBlank(allDiscount) ) {
           sqlBuffer.append("allDiscount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getAllDiscount());
        }
      
            
        String status = b2bOrder.getStatus();
        if (StringUtils.isNotBlank(status) ) {
           sqlBuffer.append("status=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getStatus());
        }
      
            
	Date createTime = b2bOrder.getCreateTime();
	if (createTime!=null  ) {
           sqlBuffer.append("createTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getCreateTime());
        } 
      
            
	Date updateTime = b2bOrder.getUpdateTime();
	if (updateTime!=null  ) {
           sqlBuffer.append("updateTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getUpdateTime());
        } 
      
            
        String targetClient = b2bOrder.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getTargetClient());
        }
      
            
        String updateBy = b2bOrder.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) ) {
           sqlBuffer.append("updateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getUpdateBy());
        }
      
            
        String bAmount = b2bOrder.getBAmount();
        if (StringUtils.isNotBlank(bAmount) ) {
           sqlBuffer.append("bAmount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getBAmount());
        }
      
            
        String cartId = b2bOrder.getCartId();
        if (StringUtils.isNotBlank(cartId) ) {
           sqlBuffer.append("cartId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getCartId());
        }
      
            
        String bNo = b2bOrder.getBNo();
        if (StringUtils.isNotBlank(bNo) ) {
           sqlBuffer.append("bNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getBNo());
        }
      
            
        String payType = b2bOrder.getPayType();
        if (StringUtils.isNotBlank(payType) ) {
           sqlBuffer.append("payType=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getPayType());
        }
      
            
        String bName = b2bOrder.getBName();
        if (StringUtils.isNotBlank(bName) ) {
           sqlBuffer.append("bName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getBName());
        }
      
            
        String restName = b2bOrder.getRestName();
        if (StringUtils.isNotBlank(restName) ) {
           sqlBuffer.append("restName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getRestName());
        }
      
            
        String remark = b2bOrder.getRemark();
        if (StringUtils.isNotBlank(remark) ) {
           sqlBuffer.append("remark=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getRemark());
        }
      
            
        String restId = b2bOrder.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getRestId());
        }
      
            
        String createBy = b2bOrder.getCreateBy();
        if (StringUtils.isNotBlank(createBy) ) {
           sqlBuffer.append("createBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getCreateBy());
        }
      
            
        String bId = b2bOrder.getBId();
        if (StringUtils.isNotBlank(bId) ) {
           sqlBuffer.append("bId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getBId());
        }
      
            
        String amountPaid = b2bOrder.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) ) {
           sqlBuffer.append("amountPaid=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getAmountPaid());
        }
      
            
        String allPrivilege = b2bOrder.getAllPrivilege();
        if (StringUtils.isNotBlank(allPrivilege) ) {
           sqlBuffer.append("allPrivilege=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrder.getAllPrivilege());
        }
      
            
        String customerName = b2bOrder.getCustomerName();
        if (StringUtils.isNotBlank(customerName) ) {
           sqlBuffer.append("customerName=?");
	    
	     param.add(b2bOrder.getCustomerName());
        }
      
            
	
	String sql=sqlBuffer.toString();
	sql=StringUtils.trim(sql);//sql.trim()
	if(sql.endsWith("and")){
		sql=sql.substring(0, sql.lastIndexOf("and"));
	}
	sqlParam.where=sql;
	sqlParam.params=param.toArray(new Object[0]);
	return sqlParam;
     }

    private class  SQLParam {
	String where;
	Object[] params;
    }
    @Override
    public B2bOrderBean trimStringValue(B2bOrderBean b2bOrder) {
        String customerId = b2bOrder.getCustomerId();
        if (StringUtils.isNotBlank(customerId) && customerId.length() > 36) {
            b2bOrder.setCustomerId(customerId.substring(0, 36));
        }

        String codelessSum = b2bOrder.getCodelessSum();
        if (StringUtils.isNotBlank(codelessSum) && codelessSum.length() > 100) {
            b2bOrder.setCodelessSum(codelessSum.substring(0, 100));
        }

        String allDiscount = b2bOrder.getAllDiscount();
        if (StringUtils.isNotBlank(allDiscount) && allDiscount.length() > 100) {
            b2bOrder.setAllDiscount(allDiscount.substring(0, 100));
        }

        String status = b2bOrder.getStatus();
        if (StringUtils.isNotBlank(status) && status.length() > 36) {
            b2bOrder.setStatus(status.substring(0, 36));
        }

        String targetClient = b2bOrder.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bOrder.setTargetClient(targetClient.substring(0, 20));
        }

        String updateBy = b2bOrder.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) && updateBy.length() > 36) {
            b2bOrder.setUpdateBy(updateBy.substring(0, 36));
        }

        String bAmount = b2bOrder.getBAmount();
        if (StringUtils.isNotBlank(bAmount) && bAmount.length() > 100) {
            b2bOrder.setBAmount(bAmount.substring(0, 100));
        }

        String cartId = b2bOrder.getCartId();
        if (StringUtils.isNotBlank(cartId) && cartId.length() > 36) {
            b2bOrder.setCartId(cartId.substring(0, 36));
        }

        String bNo = b2bOrder.getBNo();
        if (StringUtils.isNotBlank(bNo) && bNo.length() > 36) {
            b2bOrder.setBNo(bNo.substring(0, 36));
        }

        String payType = b2bOrder.getPayType();
        if (StringUtils.isNotBlank(payType) && payType.length() > 36) {
            b2bOrder.setPayType(payType.substring(0, 36));
        }

        String bName = b2bOrder.getBName();
        if (StringUtils.isNotBlank(bName) && bName.length() > 64) {
            b2bOrder.setBName(bName.substring(0, 64));
        }

        String restName = b2bOrder.getRestName();
        if (StringUtils.isNotBlank(restName) && restName.length() > 255) {
            b2bOrder.setRestName(restName.substring(0, 255));
        }

        String remark = b2bOrder.getRemark();
        if (StringUtils.isNotBlank(remark) && remark.length() > 255) {
            b2bOrder.setRemark(remark.substring(0, 255));
        }

        String restId = b2bOrder.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bOrder.setRestId(restId.substring(0, 36));
        }

        String createBy = b2bOrder.getCreateBy();
        if (StringUtils.isNotBlank(createBy) && createBy.length() > 36) {
            b2bOrder.setCreateBy(createBy.substring(0, 36));
        }

        String bId = b2bOrder.getBId();
        if (StringUtils.isNotBlank(bId) && bId.length() > 36) {
            b2bOrder.setBId(bId.substring(0, 36));
        }

        String amountPaid = b2bOrder.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) && amountPaid.length() > 100) {
            b2bOrder.setAmountPaid(amountPaid.substring(0, 100));
        }

        String allPrivilege = b2bOrder.getAllPrivilege();
        if (StringUtils.isNotBlank(allPrivilege) && allPrivilege.length() > 100) {
            b2bOrder.setAllPrivilege(allPrivilege.substring(0, 100));
        }

        String customerName = b2bOrder.getCustomerName();
        if (StringUtils.isNotBlank(customerName) && customerName.length() > 255) {
            b2bOrder.setCustomerName(customerName.substring(0, 255));
        }

        return b2bOrder;
    }
}
