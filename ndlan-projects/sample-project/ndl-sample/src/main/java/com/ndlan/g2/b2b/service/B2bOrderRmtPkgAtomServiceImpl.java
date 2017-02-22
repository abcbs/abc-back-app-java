package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderRmtPkgBean;
import com.ndlan.g2.b2b.dao.B2bOrderRmtPkgDao;

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
@Component("b2bOrderRmtPkgAtomService")
public class B2bOrderRmtPkgAtomServiceImpl  extends BaseService<B2bOrderRmtPkgDao, 
	B2bOrderRmtPkgBean>      implements B2bOrderRmtPkgAtomService {

    @Resource(name="b2bOrderRmtPkgDao")
    protected B2bOrderRmtPkgDao b2bOrderRmtPkgDao;

    @Override
    public int saveB2bOrderRmtPkgBean(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        trimStringValue(b2bOrderRmtPkg);
        return b2bOrderRmtPkgDao.insertSelective(b2bOrderRmtPkg);
    }

    @Override
    public int saveAndGetId(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        trimStringValue(b2bOrderRmtPkg);
        return b2bOrderRmtPkgDao.insertSelectiveAndGetId(b2bOrderRmtPkg);
    }

    @Override
    public int update(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        trimStringValue(b2bOrderRmtPkg);
        return b2bOrderRmtPkgDao.updateByPrimaryKeySelective(b2bOrderRmtPkg);
    }

    @Override
    public int saveOrUpdateB2bOrderRmtPkgBean(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        if (null == b2bOrderRmtPkg.getOrderRmtPkgId() ||
		"" == b2bOrderRmtPkg.getOrderRmtPkgId()) {
            return saveB2bOrderRmtPkgBean(b2bOrderRmtPkg);
        } else {
            return update(b2bOrderRmtPkg);
        }
    }

    @Override
    public B2bOrderRmtPkgBean getB2bOrderRmtPkgBean(String orderRmtPkgId) {
        return b2bOrderRmtPkgDao.selectByPrimaryKey(orderRmtPkgId);
    }

    @Override
    public List<B2bOrderRmtPkgBean> getAll() {
        return b2bOrderRmtPkgDao.selectAll();
    }

    @Override
    public void delete(String orderRmtPkgId) {
         b2bOrderRmtPkgDao.deleteByPrimaryKey(orderRmtPkgId);
    }

    public List<B2bOrderRmtPkgBean> queryB2bOrderRmtPkgBean
	(B2bOrderRmtPkgBean b2bOrderRmtPkg, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bOrderRmtPkg);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bOrderRmtPkgDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bOrderRmtPkgDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bOrderRmtPkgDao.update(sql, args);
    }

    public List<B2bOrderRmtPkgBean> queryB2bOrderRmtPkgBean
	(B2bOrderRmtPkgBean b2bOrderRmtPkg){
	SQLParam sqlParam=getWhereSQL(b2bOrderRmtPkg);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bOrderRmtPkgDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String cartId = b2bOrderRmtPkg.getCartId();
        if (StringUtils.isNotBlank(cartId) ) {
           sqlBuffer.append("cartId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getCartId());
        }
      
            
        String remark = b2bOrderRmtPkg.getRemark();
        if (StringUtils.isNotBlank(remark) ) {
           sqlBuffer.append("remark=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getRemark());
        }
      
            
        String logOrderPkgCode = b2bOrderRmtPkg.getLogOrderPkgCode();
        if (StringUtils.isNotBlank(logOrderPkgCode) ) {
           sqlBuffer.append("logOrderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getLogOrderPkgCode());
        }
      
            
	Date createTime = b2bOrderRmtPkg.getCreateTime();
	if (createTime!=null  ) {
           sqlBuffer.append("createTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getCreateTime());
        } 
      
            
        String orderHeadId = b2bOrderRmtPkg.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) ) {
           sqlBuffer.append("orderHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getOrderHeadId());
        }
      
            
        String createBy = b2bOrderRmtPkg.getCreateBy();
        if (StringUtils.isNotBlank(createBy) ) {
           sqlBuffer.append("createBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getCreateBy());
        }
      
            
        String restId = b2bOrderRmtPkg.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getRestId());
        }
      
            
        String supplierName = b2bOrderRmtPkg.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getSupplierName());
        }
      
            
        String amountPaid = b2bOrderRmtPkg.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) ) {
           sqlBuffer.append("amountPaid=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getAmountPaid());
        }
      
            
        String goodsCatQty = b2bOrderRmtPkg.getGoodsCatQty();
        if (StringUtils.isNotBlank(goodsCatQty) ) {
           sqlBuffer.append("goodsCatQty=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getGoodsCatQty());
        }
      
            
        String codelessSum = b2bOrderRmtPkg.getCodelessSum();
        if (StringUtils.isNotBlank(codelessSum) ) {
           sqlBuffer.append("codelessSum=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getCodelessSum());
        }
      
            
        String customerName = b2bOrderRmtPkg.getCustomerName();
        if (StringUtils.isNotBlank(customerName) ) {
           sqlBuffer.append("customerName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getCustomerName());
        }
      
            
        String strategyDesc = b2bOrderRmtPkg.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) ) {
           sqlBuffer.append("strategyDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getStrategyDesc());
        }
      
            
        String orderRmtHeadId = b2bOrderRmtPkg.getOrderRmtHeadId();
        if (StringUtils.isNotBlank(orderRmtHeadId) ) {
           sqlBuffer.append("orderRmtHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getOrderRmtHeadId());
        }
      
            
        String status = b2bOrderRmtPkg.getStatus();
        if (StringUtils.isNotBlank(status) ) {
           sqlBuffer.append("status=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getStatus());
        }
      
            
        String orderPkgCode = b2bOrderRmtPkg.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) ) {
           sqlBuffer.append("orderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getOrderPkgCode());
        }
      
            
        String supplierId = b2bOrderRmtPkg.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getSupplierId());
        }
      
            
        String amount = b2bOrderRmtPkg.getAmount();
        if (StringUtils.isNotBlank(amount) ) {
           sqlBuffer.append("amount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getAmount());
        }
      
            
        String derate = b2bOrderRmtPkg.getDerate();
        if (StringUtils.isNotBlank(derate) ) {
           sqlBuffer.append("derate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getDerate());
        }
      
            
        String orderPkgId = b2bOrderRmtPkg.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) ) {
           sqlBuffer.append("orderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getOrderPkgId());
        }
      
            
        String logOrderPkgId = b2bOrderRmtPkg.getLogOrderPkgId();
        if (StringUtils.isNotBlank(logOrderPkgId) ) {
           sqlBuffer.append("logOrderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getLogOrderPkgId());
        }
      
            
        String storageStatus = b2bOrderRmtPkg.getStorageStatus();
        if (StringUtils.isNotBlank(storageStatus) ) {
           sqlBuffer.append("storageStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getStorageStatus());
        }
      
            
        String restName = b2bOrderRmtPkg.getRestName();
        if (StringUtils.isNotBlank(restName) ) {
           sqlBuffer.append("restName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getRestName());
        }
      
            
        String orderPkgName = b2bOrderRmtPkg.getOrderPkgName();
        if (StringUtils.isNotBlank(orderPkgName) ) {
           sqlBuffer.append("orderPkgName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getOrderPkgName());
        }
      
            
        String orderRmtPkgId = b2bOrderRmtPkg.getOrderRmtPkgId();
        if (StringUtils.isNotBlank(orderRmtPkgId) ) {
           sqlBuffer.append("orderRmtPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getOrderRmtPkgId());
        }
      
            
        String payType = b2bOrderRmtPkg.getPayType();
        if (StringUtils.isNotBlank(payType) ) {
           sqlBuffer.append("payType=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getPayType());
        }
      
            
        String slsPmtnId = b2bOrderRmtPkg.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) ) {
           sqlBuffer.append("slsPmtnId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getSlsPmtnId());
        }
      
            
        String customerId = b2bOrderRmtPkg.getCustomerId();
        if (StringUtils.isNotBlank(customerId) ) {
           sqlBuffer.append("customerId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getCustomerId());
        }
      
            
        String discount = b2bOrderRmtPkg.getDiscount();
        if (StringUtils.isNotBlank(discount) ) {
           sqlBuffer.append("discount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getDiscount());
        }
      
            
        String updateBy = b2bOrderRmtPkg.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) ) {
           sqlBuffer.append("updateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getUpdateBy());
        }
      
            
	Date updateTime = b2bOrderRmtPkg.getUpdateTime();
	if (updateTime!=null  ) {
           sqlBuffer.append("updateTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getUpdateTime());
        } 
      
            
        String orderRmtPkgCode = b2bOrderRmtPkg.getOrderRmtPkgCode();
        if (StringUtils.isNotBlank(orderRmtPkgCode) ) {
           sqlBuffer.append("orderRmtPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtPkg.getOrderRmtPkgCode());
        }
      
            
        String orignStatus = b2bOrderRmtPkg.getOrignStatus();
        if (StringUtils.isNotBlank(orignStatus) ) {
           sqlBuffer.append("orignStatus=?");
	    
	     param.add(b2bOrderRmtPkg.getOrignStatus());
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
    public B2bOrderRmtPkgBean trimStringValue(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        String cartId = b2bOrderRmtPkg.getCartId();
        if (StringUtils.isNotBlank(cartId) && cartId.length() > 36) {
            b2bOrderRmtPkg.setCartId(cartId.substring(0, 36));
        }

        String remark = b2bOrderRmtPkg.getRemark();
        if (StringUtils.isNotBlank(remark) && remark.length() > 255) {
            b2bOrderRmtPkg.setRemark(remark.substring(0, 255));
        }

        String logOrderPkgCode = b2bOrderRmtPkg.getLogOrderPkgCode();
        if (StringUtils.isNotBlank(logOrderPkgCode) && logOrderPkgCode.length() > 36) {
            b2bOrderRmtPkg.setLogOrderPkgCode(logOrderPkgCode.substring(0, 36));
        }

        String orderHeadId = b2bOrderRmtPkg.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) && orderHeadId.length() > 36) {
            b2bOrderRmtPkg.setOrderHeadId(orderHeadId.substring(0, 36));
        }

        String createBy = b2bOrderRmtPkg.getCreateBy();
        if (StringUtils.isNotBlank(createBy) && createBy.length() > 36) {
            b2bOrderRmtPkg.setCreateBy(createBy.substring(0, 36));
        }

        String restId = b2bOrderRmtPkg.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bOrderRmtPkg.setRestId(restId.substring(0, 36));
        }

        String supplierName = b2bOrderRmtPkg.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bOrderRmtPkg.setSupplierName(supplierName.substring(0, 255));
        }

        String amountPaid = b2bOrderRmtPkg.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) && amountPaid.length() > 100) {
            b2bOrderRmtPkg.setAmountPaid(amountPaid.substring(0, 100));
        }

        String goodsCatQty = b2bOrderRmtPkg.getGoodsCatQty();
        if (StringUtils.isNotBlank(goodsCatQty) && goodsCatQty.length() > 100) {
            b2bOrderRmtPkg.setGoodsCatQty(goodsCatQty.substring(0, 100));
        }

        String codelessSum = b2bOrderRmtPkg.getCodelessSum();
        if (StringUtils.isNotBlank(codelessSum) && codelessSum.length() > 100) {
            b2bOrderRmtPkg.setCodelessSum(codelessSum.substring(0, 100));
        }

        String customerName = b2bOrderRmtPkg.getCustomerName();
        if (StringUtils.isNotBlank(customerName) && customerName.length() > 255) {
            b2bOrderRmtPkg.setCustomerName(customerName.substring(0, 255));
        }

        String strategyDesc = b2bOrderRmtPkg.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) && strategyDesc.length() > 2) {
            b2bOrderRmtPkg.setStrategyDesc(strategyDesc.substring(0, 2));
        }

        String orderRmtHeadId = b2bOrderRmtPkg.getOrderRmtHeadId();
        if (StringUtils.isNotBlank(orderRmtHeadId) && orderRmtHeadId.length() > 36) {
            b2bOrderRmtPkg.setOrderRmtHeadId(orderRmtHeadId.substring(0, 36));
        }

        String status = b2bOrderRmtPkg.getStatus();
        if (StringUtils.isNotBlank(status) && status.length() > 36) {
            b2bOrderRmtPkg.setStatus(status.substring(0, 36));
        }

        String orderPkgCode = b2bOrderRmtPkg.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) && orderPkgCode.length() > 36) {
            b2bOrderRmtPkg.setOrderPkgCode(orderPkgCode.substring(0, 36));
        }

        String supplierId = b2bOrderRmtPkg.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bOrderRmtPkg.setSupplierId(supplierId.substring(0, 36));
        }

        String amount = b2bOrderRmtPkg.getAmount();
        if (StringUtils.isNotBlank(amount) && amount.length() > 100) {
            b2bOrderRmtPkg.setAmount(amount.substring(0, 100));
        }

        String derate = b2bOrderRmtPkg.getDerate();
        if (StringUtils.isNotBlank(derate) && derate.length() > 100) {
            b2bOrderRmtPkg.setDerate(derate.substring(0, 100));
        }

        String orderPkgId = b2bOrderRmtPkg.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) && orderPkgId.length() > 36) {
            b2bOrderRmtPkg.setOrderPkgId(orderPkgId.substring(0, 36));
        }

        String logOrderPkgId = b2bOrderRmtPkg.getLogOrderPkgId();
        if (StringUtils.isNotBlank(logOrderPkgId) && logOrderPkgId.length() > 36) {
            b2bOrderRmtPkg.setLogOrderPkgId(logOrderPkgId.substring(0, 36));
        }

        String storageStatus = b2bOrderRmtPkg.getStorageStatus();
        if (StringUtils.isNotBlank(storageStatus) && storageStatus.length() > 20) {
            b2bOrderRmtPkg.setStorageStatus(storageStatus.substring(0, 20));
        }

        String restName = b2bOrderRmtPkg.getRestName();
        if (StringUtils.isNotBlank(restName) && restName.length() > 255) {
            b2bOrderRmtPkg.setRestName(restName.substring(0, 255));
        }

        String orderPkgName = b2bOrderRmtPkg.getOrderPkgName();
        if (StringUtils.isNotBlank(orderPkgName) && orderPkgName.length() > 64) {
            b2bOrderRmtPkg.setOrderPkgName(orderPkgName.substring(0, 64));
        }

        String orderRmtPkgId = b2bOrderRmtPkg.getOrderRmtPkgId();
        if (StringUtils.isNotBlank(orderRmtPkgId) && orderRmtPkgId.length() > 36) {
            b2bOrderRmtPkg.setOrderRmtPkgId(orderRmtPkgId.substring(0, 36));
        }

        String payType = b2bOrderRmtPkg.getPayType();
        if (StringUtils.isNotBlank(payType) && payType.length() > 36) {
            b2bOrderRmtPkg.setPayType(payType.substring(0, 36));
        }

        String slsPmtnId = b2bOrderRmtPkg.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) && slsPmtnId.length() > 2) {
            b2bOrderRmtPkg.setSlsPmtnId(slsPmtnId.substring(0, 2));
        }

        String customerId = b2bOrderRmtPkg.getCustomerId();
        if (StringUtils.isNotBlank(customerId) && customerId.length() > 36) {
            b2bOrderRmtPkg.setCustomerId(customerId.substring(0, 36));
        }

        String discount = b2bOrderRmtPkg.getDiscount();
        if (StringUtils.isNotBlank(discount) && discount.length() > 100) {
            b2bOrderRmtPkg.setDiscount(discount.substring(0, 100));
        }

        String updateBy = b2bOrderRmtPkg.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) && updateBy.length() > 36) {
            b2bOrderRmtPkg.setUpdateBy(updateBy.substring(0, 36));
        }

        String orderRmtPkgCode = b2bOrderRmtPkg.getOrderRmtPkgCode();
        if (StringUtils.isNotBlank(orderRmtPkgCode) && orderRmtPkgCode.length() > 36) {
            b2bOrderRmtPkg.setOrderRmtPkgCode(orderRmtPkgCode.substring(0, 36));
        }

        String orignStatus = b2bOrderRmtPkg.getOrignStatus();
        if (StringUtils.isNotBlank(orignStatus) && orignStatus.length() > 2) {
            b2bOrderRmtPkg.setOrignStatus(orignStatus.substring(0, 2));
        }

        return b2bOrderRmtPkg;
    }
}
