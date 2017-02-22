package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryDeliveryBean;
import com.ndlan.g2.b2b.dao.B2bInventoryDeliveryDao;

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
@Component("b2bInventoryDeliveryAtomService")
public class B2bInventoryDeliveryAtomServiceImpl  extends BaseService<B2bInventoryDeliveryDao, 
	B2bInventoryDeliveryBean>      implements B2bInventoryDeliveryAtomService {

    @Resource(name="b2bInventoryDeliveryDao")
    protected B2bInventoryDeliveryDao b2bInventoryDeliveryDao;

    @Override
    public int saveB2bInventoryDeliveryBean(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        trimStringValue(b2bInventoryDelivery);
        return b2bInventoryDeliveryDao.insertSelective(b2bInventoryDelivery);
    }

    @Override
    public int saveAndGetId(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        trimStringValue(b2bInventoryDelivery);
        return b2bInventoryDeliveryDao.insertSelectiveAndGetId(b2bInventoryDelivery);
    }

    @Override
    public int update(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        trimStringValue(b2bInventoryDelivery);
        return b2bInventoryDeliveryDao.updateByPrimaryKeySelective(b2bInventoryDelivery);
    }

    @Override
    public int saveOrUpdateB2bInventoryDeliveryBean(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        if (null == b2bInventoryDelivery.getInvDeliveryId() ||
		"" == b2bInventoryDelivery.getInvDeliveryId()) {
            return saveB2bInventoryDeliveryBean(b2bInventoryDelivery);
        } else {
            return update(b2bInventoryDelivery);
        }
    }

    @Override
    public B2bInventoryDeliveryBean getB2bInventoryDeliveryBean(String invDeliveryId) {
        return b2bInventoryDeliveryDao.selectByPrimaryKey(invDeliveryId);
    }

    @Override
    public List<B2bInventoryDeliveryBean> getAll() {
        return b2bInventoryDeliveryDao.selectAll();
    }

    @Override
    public void delete(String invDeliveryId) {
         b2bInventoryDeliveryDao.deleteByPrimaryKey(invDeliveryId);
    }

    public List<B2bInventoryDeliveryBean> queryB2bInventoryDeliveryBean
	(B2bInventoryDeliveryBean b2bInventoryDelivery, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bInventoryDelivery);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bInventoryDeliveryDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bInventoryDeliveryDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bInventoryDeliveryDao.update(sql, args);
    }

    public List<B2bInventoryDeliveryBean> queryB2bInventoryDeliveryBean
	(B2bInventoryDeliveryBean b2bInventoryDelivery){
	SQLParam sqlParam=getWhereSQL(b2bInventoryDelivery);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bInventoryDeliveryDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bInventoryDeliveryBean b2bInventoryDelivery) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
	Date deliveryDate = b2bInventoryDelivery.getDeliveryDate();
	if (deliveryDate!=null  ) {
           sqlBuffer.append("deliveryDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getDeliveryDate());
        } 
      
            
        String createBy = b2bInventoryDelivery.getCreateBy();
        if (StringUtils.isNotBlank(createBy) ) {
           sqlBuffer.append("createBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getCreateBy());
        }
      
            
        String deliveryPrice = b2bInventoryDelivery.getDeliveryPrice();
        if (StringUtils.isNotBlank(deliveryPrice) ) {
           sqlBuffer.append("deliveryPrice=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getDeliveryPrice());
        }
      
            
        String deliveryQty = b2bInventoryDelivery.getDeliveryQty();
        if (StringUtils.isNotBlank(deliveryQty) ) {
           sqlBuffer.append("deliveryQty=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getDeliveryQty());
        }
      
            
        String updateBy = b2bInventoryDelivery.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) ) {
           sqlBuffer.append("updateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getUpdateBy());
        }
      
            
        String invHeadId = b2bInventoryDelivery.getInvHeadId();
        if (StringUtils.isNotBlank(invHeadId) ) {
           sqlBuffer.append("invHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getInvHeadId());
        }
      
            
        String deliveryUser = b2bInventoryDelivery.getDeliveryUser();
        if (StringUtils.isNotBlank(deliveryUser) ) {
           sqlBuffer.append("deliveryUser=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getDeliveryUser());
        }
      
            
        String invDeliveryId = b2bInventoryDelivery.getInvDeliveryId();
        if (StringUtils.isNotBlank(invDeliveryId) ) {
           sqlBuffer.append("invDeliveryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getInvDeliveryId());
        }
      
            
	Date createTime = b2bInventoryDelivery.getCreateTime();
	if (createTime!=null  ) {
           sqlBuffer.append("createTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getCreateTime());
        } 
      
            
        String remark = b2bInventoryDelivery.getRemark();
        if (StringUtils.isNotBlank(remark) ) {
           sqlBuffer.append("remark=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getRemark());
        }
      
            
	Date updateTime = b2bInventoryDelivery.getUpdateTime();
	if (updateTime!=null  ) {
           sqlBuffer.append("updateTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryDelivery.getUpdateTime());
        } 
      
            
        String source = b2bInventoryDelivery.getSource();
        if (StringUtils.isNotBlank(source) ) {
           sqlBuffer.append("source=?");
	    
	     param.add(b2bInventoryDelivery.getSource());
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
    public B2bInventoryDeliveryBean trimStringValue(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        String createBy = b2bInventoryDelivery.getCreateBy();
        if (StringUtils.isNotBlank(createBy) && createBy.length() > 36) {
            b2bInventoryDelivery.setCreateBy(createBy.substring(0, 36));
        }

        String deliveryPrice = b2bInventoryDelivery.getDeliveryPrice();
        if (StringUtils.isNotBlank(deliveryPrice) && deliveryPrice.length() > 100) {
            b2bInventoryDelivery.setDeliveryPrice(deliveryPrice.substring(0, 100));
        }

        String deliveryQty = b2bInventoryDelivery.getDeliveryQty();
        if (StringUtils.isNotBlank(deliveryQty) && deliveryQty.length() > 100) {
            b2bInventoryDelivery.setDeliveryQty(deliveryQty.substring(0, 100));
        }

        String updateBy = b2bInventoryDelivery.getUpdateBy();
        if (StringUtils.isNotBlank(updateBy) && updateBy.length() > 36) {
            b2bInventoryDelivery.setUpdateBy(updateBy.substring(0, 36));
        }

        String invHeadId = b2bInventoryDelivery.getInvHeadId();
        if (StringUtils.isNotBlank(invHeadId) && invHeadId.length() > 36) {
            b2bInventoryDelivery.setInvHeadId(invHeadId.substring(0, 36));
        }

        String deliveryUser = b2bInventoryDelivery.getDeliveryUser();
        if (StringUtils.isNotBlank(deliveryUser) && deliveryUser.length() > 255) {
            b2bInventoryDelivery.setDeliveryUser(deliveryUser.substring(0, 255));
        }

        String invDeliveryId = b2bInventoryDelivery.getInvDeliveryId();
        if (StringUtils.isNotBlank(invDeliveryId) && invDeliveryId.length() > 36) {
            b2bInventoryDelivery.setInvDeliveryId(invDeliveryId.substring(0, 36));
        }

        String remark = b2bInventoryDelivery.getRemark();
        if (StringUtils.isNotBlank(remark) && remark.length() > 255) {
            b2bInventoryDelivery.setRemark(remark.substring(0, 255));
        }

        String source = b2bInventoryDelivery.getSource();
        if (StringUtils.isNotBlank(source) && source.length() > 36) {
            b2bInventoryDelivery.setSource(source.substring(0, 36));
        }

        return b2bInventoryDelivery;
    }
}
