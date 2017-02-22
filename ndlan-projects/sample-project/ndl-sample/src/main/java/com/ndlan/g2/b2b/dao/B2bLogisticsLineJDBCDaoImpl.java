package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bLogisticsLineBean;

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

@Repository("b2bLogisticsLineJDBCDao")
public class B2bLogisticsLineJDBCDaoImpl implements B2bLogisticsLineJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bLogisticsLineBean selectByPrimaryKey(String logisticsLineId) {
        try {
            String sql = "select * from b2b_logistics_line where logistics_line_id = ?";

            List<B2bLogisticsLineBean> resultList = this.jdbcTemplate.query(sql, new Object[]{logisticsLineId},
                    new RowMapper<B2bLogisticsLineBean>() {
                        @Override
                        public B2bLogisticsLineBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bLogisticsLineBean bean = new B2bLogisticsLineBean();
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            Timestamp deliveryEndDateTimestamp = rs.getTimestamp("delivery_end_date");
                            if (null != deliveryEndDateTimestamp) {
                                bean.setDeliveryEndDate(new Date(deliveryEndDateTimestamp.getTime()));
                            }
                            bean.setRemake(rs.getString("remake"));
                            bean.setDeliveryUserPhone(rs.getString("delivery_user_phone"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setDeliveryUser(rs.getString("delivery_user"));
                            bean.setLogisticsLineCode(rs.getString("logistics_line_code"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setDeliveryAddress(rs.getString("delivery_address"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setLogisticsLineName(rs.getString("logistics_line_name"));
                            bean.setStatus(rs.getString("status"));
                            Timestamp deliveryStartDateTimestamp = rs.getTimestamp("delivery_start_date");
                            if (null != deliveryStartDateTimestamp) {
                                bean.setDeliveryStartDate(new Date(deliveryStartDateTimestamp.getTime()));
                            }
                            bean.setSupplierId(rs.getString("supplier_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
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
    public List<B2bLogisticsLineBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_logistics_line ";
            } else {
                sql = "select * from b2b_logistics_line where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bLogisticsLineBean>() {
                        @Override
                        public B2bLogisticsLineBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bLogisticsLineBean bean = new B2bLogisticsLineBean();
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            Timestamp deliveryEndDateTimestamp = rs.getTimestamp("delivery_end_date");
                            if (null != deliveryEndDateTimestamp) {
                                bean.setDeliveryEndDate(new Date(deliveryEndDateTimestamp.getTime()));
                            }
                            bean.setRemake(rs.getString("remake"));
                            bean.setDeliveryUserPhone(rs.getString("delivery_user_phone"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setDeliveryUser(rs.getString("delivery_user"));
                            bean.setLogisticsLineCode(rs.getString("logistics_line_code"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setDeliveryAddress(rs.getString("delivery_address"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setLogisticsLineName(rs.getString("logistics_line_name"));
                            bean.setStatus(rs.getString("status"));
                            Timestamp deliveryStartDateTimestamp = rs.getTimestamp("delivery_start_date");
                            if (null != deliveryStartDateTimestamp) {
                                bean.setDeliveryStartDate(new Date(deliveryStartDateTimestamp.getTime()));
                            }
                            bean.setSupplierId(rs.getString("supplier_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bLogisticsLineBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_logistics_line ";
            } else {
                sql = "select * from b2b_logistics_line where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bLogisticsLineBean>() {
                        @Override
                        public B2bLogisticsLineBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bLogisticsLineBean bean = new B2bLogisticsLineBean();
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            Timestamp deliveryEndDateTimestamp = rs.getTimestamp("delivery_end_date");
                            if (null != deliveryEndDateTimestamp) {
                                bean.setDeliveryEndDate(new Date(deliveryEndDateTimestamp.getTime()));
                            }
                            bean.setRemake(rs.getString("remake"));
                            bean.setDeliveryUserPhone(rs.getString("delivery_user_phone"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setDeliveryUser(rs.getString("delivery_user"));
                            bean.setLogisticsLineCode(rs.getString("logistics_line_code"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setDeliveryAddress(rs.getString("delivery_address"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setLogisticsLineName(rs.getString("logistics_line_name"));
                            bean.setStatus(rs.getString("status"));
                            Timestamp deliveryStartDateTimestamp = rs.getTimestamp("delivery_start_date");
                            if (null != deliveryStartDateTimestamp) {
                                bean.setDeliveryStartDate(new Date(deliveryStartDateTimestamp.getTime()));
                            }
                            bean.setSupplierId(rs.getString("supplier_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bLogisticsLineBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_logistics_line ";
            } else {
                sql = "select count(1) numCount from b2b_logistics_line where " + whereSql;
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
    public int insertSelective(B2bLogisticsLineBean b2bLogisticsLine) {
        try {
            if (null == b2bLogisticsLine) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_logistics_line(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bLogisticsLine.getLogisticsLineId()) {
                columns.add("logistics_line_id");
                values.add(b2bLogisticsLine.getLogisticsLineId());
            }
            if (null != b2bLogisticsLine.getDeliveryEndDate()) {
                columns.add("delivery_end_date");
                values.add(b2bLogisticsLine.getDeliveryEndDate());
            }
            if (null != b2bLogisticsLine.getRemake()) {
                columns.add("remake");
                values.add(b2bLogisticsLine.getRemake());
            }
            if (null != b2bLogisticsLine.getDeliveryUserPhone()) {
                columns.add("delivery_user_phone");
                values.add(b2bLogisticsLine.getDeliveryUserPhone());
            }
            if (null != b2bLogisticsLine.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bLogisticsLine.getUpdateTime());
            }
            if (null != b2bLogisticsLine.getDeliveryUser()) {
                columns.add("delivery_user");
                values.add(b2bLogisticsLine.getDeliveryUser());
            }
            if (null != b2bLogisticsLine.getLogisticsLineCode()) {
                columns.add("logistics_line_code");
                values.add(b2bLogisticsLine.getLogisticsLineCode());
            }
            if (null != b2bLogisticsLine.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bLogisticsLine.getSupplierName());
            }
            if (null != b2bLogisticsLine.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bLogisticsLine.getUpdateBy());
            }
            if (null != b2bLogisticsLine.getDeliveryAddress()) {
                columns.add("delivery_address");
                values.add(b2bLogisticsLine.getDeliveryAddress());
            }
            if (null != b2bLogisticsLine.getDeliveryAddressId()) {
                columns.add("delivery_address_id");
                values.add(b2bLogisticsLine.getDeliveryAddressId());
            }
            if (null != b2bLogisticsLine.getLogisticsLineName()) {
                columns.add("logistics_line_name");
                values.add(b2bLogisticsLine.getLogisticsLineName());
            }
            if (null != b2bLogisticsLine.getStatus()) {
                columns.add("status");
                values.add(b2bLogisticsLine.getStatus());
            }
            if (null != b2bLogisticsLine.getDeliveryStartDate()) {
                columns.add("delivery_start_date");
                values.add(b2bLogisticsLine.getDeliveryStartDate());
            }
            if (null != b2bLogisticsLine.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bLogisticsLine.getSupplierId());
            }
            if (null != b2bLogisticsLine.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bLogisticsLine.getCreateTime());
            }
            if (null != b2bLogisticsLine.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bLogisticsLine.getCreateBy());
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
    public int insertSelectiveAndGetId(B2bLogisticsLineBean b2bLogisticsLine) {
        try {
            if (null == b2bLogisticsLine) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_logistics_line(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bLogisticsLine.getLogisticsLineId()) {
                columns.add("logistics_line_id");
                parameters.add(":logisticsLineId");
            }
            if (null != b2bLogisticsLine.getDeliveryEndDate()) {
                columns.add("delivery_end_date");
                parameters.add(":deliveryEndDate");
            }
            if (null != b2bLogisticsLine.getRemake()) {
                columns.add("remake");
                parameters.add(":remake");
            }
            if (null != b2bLogisticsLine.getDeliveryUserPhone()) {
                columns.add("delivery_user_phone");
                parameters.add(":deliveryUserPhone");
            }
            if (null != b2bLogisticsLine.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bLogisticsLine.getDeliveryUser()) {
                columns.add("delivery_user");
                parameters.add(":deliveryUser");
            }
            if (null != b2bLogisticsLine.getLogisticsLineCode()) {
                columns.add("logistics_line_code");
                parameters.add(":logisticsLineCode");
            }
            if (null != b2bLogisticsLine.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bLogisticsLine.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bLogisticsLine.getDeliveryAddress()) {
                columns.add("delivery_address");
                parameters.add(":deliveryAddress");
            }
            if (null != b2bLogisticsLine.getDeliveryAddressId()) {
                columns.add("delivery_address_id");
                parameters.add(":deliveryAddressId");
            }
            if (null != b2bLogisticsLine.getLogisticsLineName()) {
                columns.add("logistics_line_name");
                parameters.add(":logisticsLineName");
            }
            if (null != b2bLogisticsLine.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }
            if (null != b2bLogisticsLine.getDeliveryStartDate()) {
                columns.add("delivery_start_date");
                parameters.add(":deliveryStartDate");
            }
            if (null != b2bLogisticsLine.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bLogisticsLine.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bLogisticsLine.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bLogisticsLine);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bLogisticsLine.setLogisticsLineId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bLogisticsLineBean b2bLogisticsLine) {
        try {
            if (null == b2bLogisticsLine.getLogisticsLineId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_logistics_line SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bLogisticsLine.getLogisticsLineId()) {
                updateSql.add("logistics_line_id = ?");
                params.add(b2bLogisticsLine.getLogisticsLineId());
            }
            if (null != b2bLogisticsLine.getDeliveryEndDate()) {
                updateSql.add("delivery_end_date = ?");
                params.add(b2bLogisticsLine.getDeliveryEndDate());
            }
            if (null != b2bLogisticsLine.getRemake()) {
                updateSql.add("remake = ?");
                params.add(b2bLogisticsLine.getRemake());
            }
            if (null != b2bLogisticsLine.getDeliveryUserPhone()) {
                updateSql.add("delivery_user_phone = ?");
                params.add(b2bLogisticsLine.getDeliveryUserPhone());
            }
            if (null != b2bLogisticsLine.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bLogisticsLine.getUpdateTime());
            }
            if (null != b2bLogisticsLine.getDeliveryUser()) {
                updateSql.add("delivery_user = ?");
                params.add(b2bLogisticsLine.getDeliveryUser());
            }
            if (null != b2bLogisticsLine.getLogisticsLineCode()) {
                updateSql.add("logistics_line_code = ?");
                params.add(b2bLogisticsLine.getLogisticsLineCode());
            }
            if (null != b2bLogisticsLine.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bLogisticsLine.getSupplierName());
            }
            if (null != b2bLogisticsLine.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bLogisticsLine.getUpdateBy());
            }
            if (null != b2bLogisticsLine.getDeliveryAddress()) {
                updateSql.add("delivery_address = ?");
                params.add(b2bLogisticsLine.getDeliveryAddress());
            }
            if (null != b2bLogisticsLine.getDeliveryAddressId()) {
                updateSql.add("delivery_address_id = ?");
                params.add(b2bLogisticsLine.getDeliveryAddressId());
            }
            if (null != b2bLogisticsLine.getLogisticsLineName()) {
                updateSql.add("logistics_line_name = ?");
                params.add(b2bLogisticsLine.getLogisticsLineName());
            }
            if (null != b2bLogisticsLine.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bLogisticsLine.getStatus());
            }
            if (null != b2bLogisticsLine.getDeliveryStartDate()) {
                updateSql.add("delivery_start_date = ?");
                params.add(b2bLogisticsLine.getDeliveryStartDate());
            }
            if (null != b2bLogisticsLine.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bLogisticsLine.getSupplierId());
            }
            if (null != b2bLogisticsLine.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bLogisticsLine.getCreateTime());
            }
            if (null != b2bLogisticsLine.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bLogisticsLine.getCreateBy());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE logistics_line_id = ?");
            params.add(b2bLogisticsLine.getLogisticsLineId());

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
    public int deleteByPrimaryKey(String logisticsLineId) {
            if(null ==  logisticsLineId) {
            return 0;
        }

        String sql = "delete from b2b_logistics_line where logistics_line_id  = ?";
        return jdbcTemplate.update(sql, logisticsLineId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_logistics_line where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bLogisticsLineBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bLogisticsLineBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bLogisticsLineBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bLogisticsLineBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bLogisticsLineBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bLogisticsLineBean> findAll(Pageable arg0) {
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
	public void delete(B2bLogisticsLineBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bLogisticsLineBean> arg0) {
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
	public Iterable<B2bLogisticsLineBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bLogisticsLineBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bLogisticsLineBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bLogisticsLineBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bLogisticsLineBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bLogisticsLineBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bLogisticsLineBean> findAll(
			Specification<B2bLogisticsLineBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bLogisticsLineBean> findAll(
			Specification<B2bLogisticsLineBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bLogisticsLineBean> findAll(
			Specification<B2bLogisticsLineBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bLogisticsLineBean findOne(
			Specification<B2bLogisticsLineBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
