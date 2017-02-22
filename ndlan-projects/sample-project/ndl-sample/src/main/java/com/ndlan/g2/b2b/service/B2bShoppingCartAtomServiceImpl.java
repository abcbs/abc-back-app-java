package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartBean;
import com.ndlan.g2.b2b.dao.B2bShoppingCartDao;

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
@Component("b2bShoppingCartAtomService")
public class B2bShoppingCartAtomServiceImpl  extends BaseService<B2bShoppingCartDao, 
	B2bShoppingCartBean>      implements B2bShoppingCartAtomService {

    @Resource(name="b2bShoppingCartDao")
    protected B2bShoppingCartDao b2bShoppingCartDao;

    @Override
    public int saveB2bShoppingCartBean(B2bShoppingCartBean b2bShoppingCart) {
        trimStringValue(b2bShoppingCart);
        return b2bShoppingCartDao.insertSelective(b2bShoppingCart);
    }

    @Override
    public int saveAndGetId(B2bShoppingCartBean b2bShoppingCart) {
        trimStringValue(b2bShoppingCart);
        return b2bShoppingCartDao.insertSelectiveAndGetId(b2bShoppingCart);
    }

    @Override
    public int update(B2bShoppingCartBean b2bShoppingCart) {
        trimStringValue(b2bShoppingCart);
        return b2bShoppingCartDao.updateByPrimaryKeySelective(b2bShoppingCart);
    }

    @Override
    public int saveOrUpdateB2bShoppingCartBean(B2bShoppingCartBean b2bShoppingCart) {
        if (null == b2bShoppingCart.getCartId() ||
		"" == b2bShoppingCart.getCartId()) {
            return saveB2bShoppingCartBean(b2bShoppingCart);
        } else {
            return update(b2bShoppingCart);
        }
    }

    @Override
    public B2bShoppingCartBean getB2bShoppingCartBean(String cartId) {
        return b2bShoppingCartDao.selectByPrimaryKey(cartId);
    }

    @Override
    public List<B2bShoppingCartBean> getAll() {
        return b2bShoppingCartDao.selectAll();
    }

    @Override
    public void delete(String cartId) {
         b2bShoppingCartDao.deleteByPrimaryKey(cartId);
    }

    public List<B2bShoppingCartBean> queryB2bShoppingCartBean
	(B2bShoppingCartBean b2bShoppingCart, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bShoppingCart);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bShoppingCartDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bShoppingCartDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bShoppingCartDao.update(sql, args);
    }

    public List<B2bShoppingCartBean> queryB2bShoppingCartBean
	(B2bShoppingCartBean b2bShoppingCart){
	SQLParam sqlParam=getWhereSQL(b2bShoppingCart);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bShoppingCartDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bShoppingCartBean b2bShoppingCart) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String customerId = b2bShoppingCart.getCustomerId();
        if (StringUtils.isNotBlank(customerId) ) {
           sqlBuffer.append("customerId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getCustomerId());
        }
      
            
        String restName = b2bShoppingCart.getRestName();
        if (StringUtils.isNotBlank(restName) ) {
           sqlBuffer.append("restName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getRestName());
        }
      
            
        String updateBy = b2bShoppingCart.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) ) {
           sqlBuffer.append("updateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getUpdateBy());
        }
      
            
        String cartId = b2bShoppingCart.getCartId();
        if (StringUtils.isNotBlank(cartId) ) {
           sqlBuffer.append("cartId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getCartId());
        }
      
            
        String status = b2bShoppingCart.getStatus();
        if (StringUtils.isNotBlank(status) ) {
           sqlBuffer.append("status=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getStatus());
        }
      
            
        String allDiscount = b2bShoppingCart.getAllDiscount();
        if (StringUtils.isNotBlank(allDiscount) ) {
           sqlBuffer.append("allDiscount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getAllDiscount());
        }
      
            
        String targetClient = b2bShoppingCart.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getTargetClient());
        }
      
            
        String customerName = b2bShoppingCart.getCustomerName();
        if (StringUtils.isNotBlank(customerName) ) {
           sqlBuffer.append("customerName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getCustomerName());
        }
      
            
	Date updateTime = b2bShoppingCart.getUpdateTime();
	if (updateTime!=null  ) {
           sqlBuffer.append("updateTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getUpdateTime());
        } 
      
            
        String allPrivilege = b2bShoppingCart.getAllPrivilege();
        if (StringUtils.isNotBlank(allPrivilege) ) {
           sqlBuffer.append("allPrivilege=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getAllPrivilege());
        }
      
            
	Date createTime = b2bShoppingCart.getCreateTime();
	if (createTime!=null  ) {
           sqlBuffer.append("createTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getCreateTime());
        } 
      
            
        String createBy = b2bShoppingCart.getCreateBy();
        if (StringUtils.isNotBlank(createBy) ) {
           sqlBuffer.append("createBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getCreateBy());
        }
      
            
        String total = b2bShoppingCart.getTotal();
        if (StringUtils.isNotBlank(total) ) {
           sqlBuffer.append("total=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCart.getTotal());
        }
      
            
        String restId = b2bShoppingCart.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    
	     param.add(b2bShoppingCart.getRestId());
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
    public B2bShoppingCartBean trimStringValue(B2bShoppingCartBean b2bShoppingCart) {
        String customerId = b2bShoppingCart.getCustomerId();
        if (StringUtils.isNotBlank(customerId) && customerId.length() > 50) {
            b2bShoppingCart.setCustomerId(customerId.substring(0, 50));
        }

        String restName = b2bShoppingCart.getRestName();
        if (StringUtils.isNotBlank(restName) && restName.length() > 255) {
            b2bShoppingCart.setRestName(restName.substring(0, 255));
        }

        String updateBy = b2bShoppingCart.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) && updateBy.length() > 36) {
            b2bShoppingCart.setUpdateBy(updateBy.substring(0, 36));
        }

        String cartId = b2bShoppingCart.getCartId();
        if (StringUtils.isNotBlank(cartId) && cartId.length() > 36) {
            b2bShoppingCart.setCartId(cartId.substring(0, 36));
        }

        String status = b2bShoppingCart.getStatus();
        if (StringUtils.isNotBlank(status) && status.length() > 20) {
            b2bShoppingCart.setStatus(status.substring(0, 20));
        }

        String allDiscount = b2bShoppingCart.getAllDiscount();
        if (StringUtils.isNotBlank(allDiscount) && allDiscount.length() > 100) {
            b2bShoppingCart.setAllDiscount(allDiscount.substring(0, 100));
        }

        String targetClient = b2bShoppingCart.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bShoppingCart.setTargetClient(targetClient.substring(0, 20));
        }

        String customerName = b2bShoppingCart.getCustomerName();
        if (StringUtils.isNotBlank(customerName) && customerName.length() > 50) {
            b2bShoppingCart.setCustomerName(customerName.substring(0, 50));
        }

        String allPrivilege = b2bShoppingCart.getAllPrivilege();
        if (StringUtils.isNotBlank(allPrivilege) && allPrivilege.length() > 100) {
            b2bShoppingCart.setAllPrivilege(allPrivilege.substring(0, 100));
        }

        String createBy = b2bShoppingCart.getCreateBy();
        if (StringUtils.isNotBlank(createBy) && createBy.length() > 36) {
            b2bShoppingCart.setCreateBy(createBy.substring(0, 36));
        }

        String total = b2bShoppingCart.getTotal();
        if (StringUtils.isNotBlank(total) && total.length() > 100) {
            b2bShoppingCart.setTotal(total.substring(0, 100));
        }

        String restId = b2bShoppingCart.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bShoppingCart.setRestId(restId.substring(0, 36));
        }

        return b2bShoppingCart;
    }
}
