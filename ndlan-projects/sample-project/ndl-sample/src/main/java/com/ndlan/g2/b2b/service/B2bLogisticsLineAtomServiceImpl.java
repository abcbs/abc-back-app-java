package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsLineBean;
import com.ndlan.g2.b2b.dao.B2bLogisticsLineDao;

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
@Component("b2bLogisticsLineAtomService")
public class B2bLogisticsLineAtomServiceImpl  extends BaseService<B2bLogisticsLineDao, 
	B2bLogisticsLineBean>      implements B2bLogisticsLineAtomService {

    @Resource(name="b2bLogisticsLineDao")
    protected B2bLogisticsLineDao b2bLogisticsLineDao;

    @Override
    public int saveB2bLogisticsLineBean(B2bLogisticsLineBean b2bLogisticsLine) {
        trimStringValue(b2bLogisticsLine);
        return b2bLogisticsLineDao.insertSelective(b2bLogisticsLine);
    }

    @Override
    public int saveAndGetId(B2bLogisticsLineBean b2bLogisticsLine) {
        trimStringValue(b2bLogisticsLine);
        return b2bLogisticsLineDao.insertSelectiveAndGetId(b2bLogisticsLine);
    }

    @Override
    public int update(B2bLogisticsLineBean b2bLogisticsLine) {
        trimStringValue(b2bLogisticsLine);
        return b2bLogisticsLineDao.updateByPrimaryKeySelective(b2bLogisticsLine);
    }

    @Override
    public int saveOrUpdateB2bLogisticsLineBean(B2bLogisticsLineBean b2bLogisticsLine) {
        if (null == b2bLogisticsLine.getLogisticsLineId() ||
		"" == b2bLogisticsLine.getLogisticsLineId()) {
            return saveB2bLogisticsLineBean(b2bLogisticsLine);
        } else {
            return update(b2bLogisticsLine);
        }
    }

    @Override
    public B2bLogisticsLineBean getB2bLogisticsLineBean(String logisticsLineId) {
        return b2bLogisticsLineDao.selectByPrimaryKey(logisticsLineId);
    }

    @Override
    public List<B2bLogisticsLineBean> getAll() {
        return b2bLogisticsLineDao.selectAll();
    }

    @Override
    public void delete(String logisticsLineId) {
         b2bLogisticsLineDao.deleteByPrimaryKey(logisticsLineId);
    }

    public List<B2bLogisticsLineBean> queryB2bLogisticsLineBean
	(B2bLogisticsLineBean b2bLogisticsLine, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bLogisticsLine);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bLogisticsLineDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bLogisticsLineDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bLogisticsLineDao.update(sql, args);
    }

    public List<B2bLogisticsLineBean> queryB2bLogisticsLineBean
	(B2bLogisticsLineBean b2bLogisticsLine){
	SQLParam sqlParam=getWhereSQL(b2bLogisticsLine);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bLogisticsLineDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bLogisticsLineBean b2bLogisticsLine) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String status = b2bLogisticsLine.getStatus();
        if (StringUtils.isNotBlank(status) ) {
           sqlBuffer.append("status=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getStatus());
        }
      
            
	Date deliveryEndDate = b2bLogisticsLine.getDeliveryEndDate();
	if (deliveryEndDate!=null  ) {
           sqlBuffer.append("deliveryEndDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getDeliveryEndDate());
        } 
      
            
	Date updateTime = b2bLogisticsLine.getUpdateTime();
	if (updateTime!=null  ) {
           sqlBuffer.append("updateTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getUpdateTime());
        } 
      
            
        String supplierName = b2bLogisticsLine.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getSupplierName());
        }
      
            
        String logisticsLineName = b2bLogisticsLine.getLogisticsLineName();
        if (StringUtils.isNotBlank(logisticsLineName) ) {
           sqlBuffer.append("logisticsLineName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getLogisticsLineName());
        }
      
            
        String logisticsLineCode = b2bLogisticsLine.getLogisticsLineCode();
        if (StringUtils.isNotBlank(logisticsLineCode) ) {
           sqlBuffer.append("logisticsLineCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getLogisticsLineCode());
        }
      
            
        String supplierId = b2bLogisticsLine.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getSupplierId());
        }
      
            
        String deliveryAddressId = b2bLogisticsLine.getDeliveryAddressId();
        if (StringUtils.isNotBlank(deliveryAddressId) ) {
           sqlBuffer.append("deliveryAddressId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getDeliveryAddressId());
        }
      
            
        String remake = b2bLogisticsLine.getRemake();
        if (StringUtils.isNotBlank(remake) ) {
           sqlBuffer.append("remake=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getRemake());
        }
      
            
	Date createTime = b2bLogisticsLine.getCreateTime();
	if (createTime!=null  ) {
           sqlBuffer.append("createTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getCreateTime());
        } 
      
            
        String logisticsLineId = b2bLogisticsLine.getLogisticsLineId();
        if (StringUtils.isNotBlank(logisticsLineId) ) {
           sqlBuffer.append("logisticsLineId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getLogisticsLineId());
        }
      
            
        String deliveryUser = b2bLogisticsLine.getDeliveryUser();
        if (StringUtils.isNotBlank(deliveryUser) ) {
           sqlBuffer.append("deliveryUser=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getDeliveryUser());
        }
      
            
        String deliveryUserPhone = b2bLogisticsLine.getDeliveryUserPhone();
        if (StringUtils.isNotBlank(deliveryUserPhone) ) {
           sqlBuffer.append("deliveryUserPhone=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getDeliveryUserPhone());
        }
      
            
        String updateBy = b2bLogisticsLine.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) ) {
           sqlBuffer.append("updateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getUpdateBy());
        }
      
            
	Date deliveryStartDate = b2bLogisticsLine.getDeliveryStartDate();
	if (deliveryStartDate!=null  ) {
           sqlBuffer.append("deliveryStartDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getDeliveryStartDate());
        } 
      
            
        String deliveryAddress = b2bLogisticsLine.getDeliveryAddress();
        if (StringUtils.isNotBlank(deliveryAddress) ) {
           sqlBuffer.append("deliveryAddress=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsLine.getDeliveryAddress());
        }
      
            
        String createBy = b2bLogisticsLine.getCreateBy();
        if (StringUtils.isNotBlank(createBy) ) {
           sqlBuffer.append("createBy=?");
	    
	     param.add(b2bLogisticsLine.getCreateBy());
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
    public B2bLogisticsLineBean trimStringValue(B2bLogisticsLineBean b2bLogisticsLine) {
        String status = b2bLogisticsLine.getStatus();
        if (StringUtils.isNotBlank(status) && status.length() > 2) {
            b2bLogisticsLine.setStatus(status.substring(0, 2));
        }

        String supplierName = b2bLogisticsLine.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bLogisticsLine.setSupplierName(supplierName.substring(0, 255));
        }

        String logisticsLineName = b2bLogisticsLine.getLogisticsLineName();
        if (StringUtils.isNotBlank(logisticsLineName) && logisticsLineName.length() > 255) {
            b2bLogisticsLine.setLogisticsLineName(logisticsLineName.substring(0, 255));
        }

        String logisticsLineCode = b2bLogisticsLine.getLogisticsLineCode();
        if (StringUtils.isNotBlank(logisticsLineCode) && logisticsLineCode.length() > 36) {
            b2bLogisticsLine.setLogisticsLineCode(logisticsLineCode.substring(0, 36));
        }

        String supplierId = b2bLogisticsLine.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bLogisticsLine.setSupplierId(supplierId.substring(0, 36));
        }

        String deliveryAddressId = b2bLogisticsLine.getDeliveryAddressId();
        if (StringUtils.isNotBlank(deliveryAddressId) && deliveryAddressId.length() > 36) {
            b2bLogisticsLine.setDeliveryAddressId(deliveryAddressId.substring(0, 36));
        }

        String remake = b2bLogisticsLine.getRemake();
        if (StringUtils.isNotBlank(remake) && remake.length() > 255) {
            b2bLogisticsLine.setRemake(remake.substring(0, 255));
        }

        String logisticsLineId = b2bLogisticsLine.getLogisticsLineId();
        if (StringUtils.isNotBlank(logisticsLineId) && logisticsLineId.length() > 36) {
            b2bLogisticsLine.setLogisticsLineId(logisticsLineId.substring(0, 36));
        }

        String deliveryUser = b2bLogisticsLine.getDeliveryUser();
        if (StringUtils.isNotBlank(deliveryUser) && deliveryUser.length() > 36) {
            b2bLogisticsLine.setDeliveryUser(deliveryUser.substring(0, 36));
        }

        String deliveryUserPhone = b2bLogisticsLine.getDeliveryUserPhone();
        if (StringUtils.isNotBlank(deliveryUserPhone) && deliveryUserPhone.length() > 11) {
            b2bLogisticsLine.setDeliveryUserPhone(deliveryUserPhone.substring(0, 11));
        }

        String updateBy = b2bLogisticsLine.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) && updateBy.length() > 36) {
            b2bLogisticsLine.setUpdateBy(updateBy.substring(0, 36));
        }

        String deliveryAddress = b2bLogisticsLine.getDeliveryAddress();
        if (StringUtils.isNotBlank(deliveryAddress) && deliveryAddress.length() > 255) {
            b2bLogisticsLine.setDeliveryAddress(deliveryAddress.substring(0, 255));
        }

        String createBy = b2bLogisticsLine.getCreateBy();
        if (StringUtils.isNotBlank(createBy) && createBy.length() > 36) {
            b2bLogisticsLine.setCreateBy(createBy.substring(0, 36));
        }

        return b2bLogisticsLine;
    }
}
