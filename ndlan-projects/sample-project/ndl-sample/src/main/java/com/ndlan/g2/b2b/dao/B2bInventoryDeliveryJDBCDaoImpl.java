package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bInventoryDeliveryBean;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ndlan.canyin.base.repository.BaseDao;

@Repository("b2bInventoryDeliveryJDBCDao")
public class B2bInventoryDeliveryJDBCDaoImpl implements B2bInventoryDeliveryJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bInventoryDeliveryBean selectByPrimaryKey(String invDeliveryId) {
        try {
            String sql = "select * from b2b_inventory_delivery where inv_delivery_id = ?";

            List<B2bInventoryDeliveryBean> resultList = this.jdbcTemplate.query(sql, new Object[]{invDeliveryId},
                    new RowMapper<B2bInventoryDeliveryBean>() {
                        @Override
                        public B2bInventoryDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bInventoryDeliveryBean bean = new B2bInventoryDeliveryBean();
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setInvDeliveryId(rs.getString("inv_delivery_id"));
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setDeliveryUser(rs.getString("delivery_user"));
                            bean.setSource(rs.getString("source"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setDeliveryQty(rs.getString("delivery_qty"));
                            Timestamp deliveryDateTimestamp = rs.getTimestamp("delivery_date");
                            if (null != deliveryDateTimestamp) {
                                bean.setDeliveryDate(new Date(deliveryDateTimestamp.getTime()));
                            }
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setDeliveryPrice(rs.getString("delivery_price"));
							return bean;
						}
                    });

            if (null == resultList || resultList.isEmpty()) {
                return null;
            } else {
                return resultList.get(0);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bInventoryDeliveryBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_inventory_delivery ";
            } else {
                sql = "select * from b2b_inventory_delivery where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bInventoryDeliveryBean>() {
                        @Override
                        public B2bInventoryDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bInventoryDeliveryBean bean = new B2bInventoryDeliveryBean();
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setInvDeliveryId(rs.getString("inv_delivery_id"));
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setDeliveryUser(rs.getString("delivery_user"));
                            bean.setSource(rs.getString("source"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setDeliveryQty(rs.getString("delivery_qty"));
                            Timestamp deliveryDateTimestamp = rs.getTimestamp("delivery_date");
                            if (null != deliveryDateTimestamp) {
                                bean.setDeliveryDate(new Date(deliveryDateTimestamp.getTime()));
                            }
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setDeliveryPrice(rs.getString("delivery_price"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bInventoryDeliveryBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_inventory_delivery ";
            } else {
                sql = "select * from b2b_inventory_delivery where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bInventoryDeliveryBean>() {
                        @Override
                        public B2bInventoryDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bInventoryDeliveryBean bean = new B2bInventoryDeliveryBean();
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setInvDeliveryId(rs.getString("inv_delivery_id"));
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setDeliveryUser(rs.getString("delivery_user"));
                            bean.setSource(rs.getString("source"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setDeliveryQty(rs.getString("delivery_qty"));
                            Timestamp deliveryDateTimestamp = rs.getTimestamp("delivery_date");
                            if (null != deliveryDateTimestamp) {
                                bean.setDeliveryDate(new Date(deliveryDateTimestamp.getTime()));
                            }
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setDeliveryPrice(rs.getString("delivery_price"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bInventoryDeliveryBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_inventory_delivery ";
            } else {
                sql = "select count(1) numCount from b2b_inventory_delivery where " + whereSql;
            }

            return this.jdbcTemplate.queryForObject(sql, params,
                    new RowMapper<Long>() {
                        @Override
                        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return rs.getLong("numCount");
                        }
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insertSelective(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        try {
            if (null == b2bInventoryDelivery) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_inventory_delivery(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bInventoryDelivery.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bInventoryDelivery.getUpdateTime());
            }
            if (null != b2bInventoryDelivery.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bInventoryDelivery.getCreateBy());
            }
            if (null != b2bInventoryDelivery.getRemark()) {
                columns.add("remark");
                values.add(b2bInventoryDelivery.getRemark());
            }
            if (null != b2bInventoryDelivery.getInvDeliveryId()) {
                columns.add("inv_delivery_id");
                values.add(b2bInventoryDelivery.getInvDeliveryId());
            }
            if (null != b2bInventoryDelivery.getInvHeadId()) {
                columns.add("inv_head_id");
                values.add(b2bInventoryDelivery.getInvHeadId());
            }
            if (null != b2bInventoryDelivery.getDeliveryUser()) {
                columns.add("delivery_user");
                values.add(b2bInventoryDelivery.getDeliveryUser());
            }
            if (null != b2bInventoryDelivery.getSource()) {
                columns.add("source");
                values.add(b2bInventoryDelivery.getSource());
            }
            if (null != b2bInventoryDelivery.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bInventoryDelivery.getUpdateBy());
            }
            if (null != b2bInventoryDelivery.getDeliveryQty()) {
                columns.add("delivery_qty");
                values.add(b2bInventoryDelivery.getDeliveryQty());
            }
            if (null != b2bInventoryDelivery.getDeliveryDate()) {
                columns.add("delivery_date");
                values.add(b2bInventoryDelivery.getDeliveryDate());
            }
            if (null != b2bInventoryDelivery.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bInventoryDelivery.getCreateTime());
            }
            if (null != b2bInventoryDelivery.getDeliveryPrice()) {
                columns.add("delivery_price");
                values.add(b2bInventoryDelivery.getDeliveryPrice());
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (");

            List<String> questions = new ArrayList<String>(columns.size());
            for (Object column : columns) {
                questions.add("?");
            }
			
            sql.append(StringUtils.join(questions, ',')).append(")");

            return jdbcTemplate.update(sql.toString(), values.toArray());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insertSelectiveAndGetId(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        try {
            if (null == b2bInventoryDelivery) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_inventory_delivery(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bInventoryDelivery.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bInventoryDelivery.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }
            if (null != b2bInventoryDelivery.getRemark()) {
                columns.add("remark");
                parameters.add(":remark");
            }
            if (null != b2bInventoryDelivery.getInvDeliveryId()) {
                columns.add("inv_delivery_id");
                parameters.add(":invDeliveryId");
            }
            if (null != b2bInventoryDelivery.getInvHeadId()) {
                columns.add("inv_head_id");
                parameters.add(":invHeadId");
            }
            if (null != b2bInventoryDelivery.getDeliveryUser()) {
                columns.add("delivery_user");
                parameters.add(":deliveryUser");
            }
            if (null != b2bInventoryDelivery.getSource()) {
                columns.add("source");
                parameters.add(":source");
            }
            if (null != b2bInventoryDelivery.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bInventoryDelivery.getDeliveryQty()) {
                columns.add("delivery_qty");
                parameters.add(":deliveryQty");
            }
            if (null != b2bInventoryDelivery.getDeliveryDate()) {
                columns.add("delivery_date");
                parameters.add(":deliveryDate");
            }
            if (null != b2bInventoryDelivery.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bInventoryDelivery.getDeliveryPrice()) {
                columns.add("delivery_price");
                parameters.add(":deliveryPrice");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bInventoryDelivery);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bInventoryDelivery.setInvDeliveryId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        try {
            if (null == b2bInventoryDelivery.getInvDeliveryId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_inventory_delivery SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bInventoryDelivery.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bInventoryDelivery.getUpdateTime());
            }
            if (null != b2bInventoryDelivery.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bInventoryDelivery.getCreateBy());
            }
            if (null != b2bInventoryDelivery.getRemark()) {
                updateSql.add("remark = ?");
                params.add(b2bInventoryDelivery.getRemark());
            }
            if (null != b2bInventoryDelivery.getInvDeliveryId()) {
                updateSql.add("inv_delivery_id = ?");
                params.add(b2bInventoryDelivery.getInvDeliveryId());
            }
            if (null != b2bInventoryDelivery.getInvHeadId()) {
                updateSql.add("inv_head_id = ?");
                params.add(b2bInventoryDelivery.getInvHeadId());
            }
            if (null != b2bInventoryDelivery.getDeliveryUser()) {
                updateSql.add("delivery_user = ?");
                params.add(b2bInventoryDelivery.getDeliveryUser());
            }
            if (null != b2bInventoryDelivery.getSource()) {
                updateSql.add("source = ?");
                params.add(b2bInventoryDelivery.getSource());
            }
            if (null != b2bInventoryDelivery.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bInventoryDelivery.getUpdateBy());
            }
            if (null != b2bInventoryDelivery.getDeliveryQty()) {
                updateSql.add("delivery_qty = ?");
                params.add(b2bInventoryDelivery.getDeliveryQty());
            }
            if (null != b2bInventoryDelivery.getDeliveryDate()) {
                updateSql.add("delivery_date = ?");
                params.add(b2bInventoryDelivery.getDeliveryDate());
            }
            if (null != b2bInventoryDelivery.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bInventoryDelivery.getCreateTime());
            }
            if (null != b2bInventoryDelivery.getDeliveryPrice()) {
                updateSql.add("delivery_price = ?");
                params.add(b2bInventoryDelivery.getDeliveryPrice());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE inv_delivery_id = ?");
            params.add(b2bInventoryDelivery.getInvDeliveryId());

            return this.jdbcTemplate.update(sql.toString(), params.toArray());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(String sql, Object... args) {
        return this.jdbcTemplate.update(sql, args);
    }

    @Override
    public int update(String sql, PreparedStatementSetter pss) {
        return this.jdbcTemplate.update(sql, pss);
    }

    @Override
    public int deleteByPrimaryKey(String invDeliveryId) {
            if(null ==  invDeliveryId) {
            return 0;
        }

        String sql = "delete from b2b_inventory_delivery where inv_delivery_id  = ?";
        return jdbcTemplate.update(sql, invDeliveryId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_inventory_delivery where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bInventoryDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bInventoryDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bInventoryDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bInventoryDeliveryBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bInventoryDeliveryBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bInventoryDeliveryBean> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(B2bInventoryDeliveryBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bInventoryDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<B2bInventoryDeliveryBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bInventoryDeliveryBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bInventoryDeliveryBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bInventoryDeliveryBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bInventoryDeliveryBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bInventoryDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bInventoryDeliveryBean> findAll(
			Specification<B2bInventoryDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bInventoryDeliveryBean> findAll(
			Specification<B2bInventoryDeliveryBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bInventoryDeliveryBean> findAll(
			Specification<B2bInventoryDeliveryBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bInventoryDeliveryBean findOne(
			Specification<B2bInventoryDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
