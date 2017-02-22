package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bDeliveryAddressBean;

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

@Repository("b2bDeliveryAddressDao")
public class B2bDeliveryAddressDaoImpl implements B2bDeliveryAddressDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bDeliveryAddressBean selectByPrimaryKey(String deliveryAddressId) {
        try {
            String sql = "select * from b2b_delivery_address where delivery_address_id = ?";

            List<B2bDeliveryAddressBean> resultList = this.jdbcTemplate.query(sql, new Object[]{deliveryAddressId},
                    new RowMapper<B2bDeliveryAddressBean>() {
                        @Override
                        public B2bDeliveryAddressBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bDeliveryAddressBean bean = new B2bDeliveryAddressBean();
                            bean.setRegion(rs.getString("region"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.set(rs.getString(""));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setIsDefault(rs.getString("is_default"));
                            bean.setPostCode(rs.getString("post_code"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
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
    public List<B2bDeliveryAddressBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_delivery_address ";
            } else {
                sql = "select * from b2b_delivery_address where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bDeliveryAddressBean>() {
                        @Override
                        public B2bDeliveryAddressBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bDeliveryAddressBean bean = new B2bDeliveryAddressBean();
                            bean.setRegion(rs.getString("region"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.set(rs.getString(""));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setIsDefault(rs.getString("is_default"));
                            bean.setPostCode(rs.getString("post_code"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bDeliveryAddressBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_delivery_address ";
            } else {
                sql = "select * from b2b_delivery_address where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bDeliveryAddressBean>() {
                        @Override
                        public B2bDeliveryAddressBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bDeliveryAddressBean bean = new B2bDeliveryAddressBean();
                            bean.setRegion(rs.getString("region"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.set(rs.getString(""));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setIsDefault(rs.getString("is_default"));
                            bean.setPostCode(rs.getString("post_code"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bDeliveryAddressBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_delivery_address ";
            } else {
                sql = "select count(1) numCount from b2b_delivery_address where " + whereSql;
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
    public int insertSelective(B2bDeliveryAddressBean b2bDeliveryAddress) {
        try {
            if (null == b2bDeliveryAddress) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_delivery_address(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bDeliveryAddress.getRegion()) {
                columns.add("region");
                values.add(b2bDeliveryAddress.getRegion());
            }
            if (null != b2bDeliveryAddress.getReceiveName()) {
                columns.add("receive_name");
                values.add(b2bDeliveryAddress.getReceiveName());
            }
            if (null != b2bDeliveryAddress.getReceivePhone()) {
                columns.add("receive_phone");
                values.add(b2bDeliveryAddress.getReceivePhone());
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                values.add(b2bDeliveryAddress.get());
            }
            if (null != b2bDeliveryAddress.getDetailAddress()) {
                columns.add("detail_address");
                values.add(b2bDeliveryAddress.getDetailAddress());
            }
            if (null != b2bDeliveryAddress.getReceiveTellcall()) {
                columns.add("receive_tellcall");
                values.add(b2bDeliveryAddress.getReceiveTellcall());
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                values.add(b2bDeliveryAddress.get());
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                values.add(b2bDeliveryAddress.get());
            }
            if (null != b2bDeliveryAddress.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bDeliveryAddress.getSupplierId());
            }
            if (null != b2bDeliveryAddress.getDeliveryAddressId()) {
                columns.add("delivery_address_id");
                values.add(b2bDeliveryAddress.getDeliveryAddressId());
            }
            if (null != b2bDeliveryAddress.getRemake()) {
                columns.add("remake");
                values.add(b2bDeliveryAddress.getRemake());
            }
            if (null != b2bDeliveryAddress.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bDeliveryAddress.getSupplierName());
            }
            if (null != b2bDeliveryAddress.getIsDefault()) {
                columns.add("is_default");
                values.add(b2bDeliveryAddress.getIsDefault());
            }
            if (null != b2bDeliveryAddress.getPostCode()) {
                columns.add("post_code");
                values.add(b2bDeliveryAddress.getPostCode());
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                values.add(b2bDeliveryAddress.get());
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                values.add(b2bDeliveryAddress.get());
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
    public int insertSelectiveAndGetId(B2bDeliveryAddressBean b2bDeliveryAddress) {
        try {
            if (null == b2bDeliveryAddress) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_delivery_address(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bDeliveryAddress.getRegion()) {
                columns.add("region");
                parameters.add(":region");
            }
            if (null != b2bDeliveryAddress.getReceiveName()) {
                columns.add("receive_name");
                parameters.add(":receiveName");
            }
            if (null != b2bDeliveryAddress.getReceivePhone()) {
                columns.add("receive_phone");
                parameters.add(":receivePhone");
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bDeliveryAddress.getDetailAddress()) {
                columns.add("detail_address");
                parameters.add(":detailAddress");
            }
            if (null != b2bDeliveryAddress.getReceiveTellcall()) {
                columns.add("receive_tellcall");
                parameters.add(":receiveTellcall");
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bDeliveryAddress.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bDeliveryAddress.getDeliveryAddressId()) {
                columns.add("delivery_address_id");
                parameters.add(":deliveryAddressId");
            }
            if (null != b2bDeliveryAddress.getRemake()) {
                columns.add("remake");
                parameters.add(":remake");
            }
            if (null != b2bDeliveryAddress.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bDeliveryAddress.getIsDefault()) {
                columns.add("is_default");
                parameters.add(":isDefault");
            }
            if (null != b2bDeliveryAddress.getPostCode()) {
                columns.add("post_code");
                parameters.add(":postCode");
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bDeliveryAddress.get()) {
                columns.add("");
                parameters.add(":");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bDeliveryAddress);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bDeliveryAddress.setDeliveryAddressId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bDeliveryAddressBean b2bDeliveryAddress) {
        try {
            if (null == b2bDeliveryAddress.getDeliveryAddressId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_delivery_address SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bDeliveryAddress.getRegion()) {
                updateSql.add("region = ?");
                params.add(b2bDeliveryAddress.getRegion());
            }
            if (null != b2bDeliveryAddress.getReceiveName()) {
                updateSql.add("receive_name = ?");
                params.add(b2bDeliveryAddress.getReceiveName());
            }
            if (null != b2bDeliveryAddress.getReceivePhone()) {
                updateSql.add("receive_phone = ?");
                params.add(b2bDeliveryAddress.getReceivePhone());
            }
            if (null != b2bDeliveryAddress.get()) {
                updateSql.add(" = ?");
                params.add(b2bDeliveryAddress.get());
            }
            if (null != b2bDeliveryAddress.getDetailAddress()) {
                updateSql.add("detail_address = ?");
                params.add(b2bDeliveryAddress.getDetailAddress());
            }
            if (null != b2bDeliveryAddress.getReceiveTellcall()) {
                updateSql.add("receive_tellcall = ?");
                params.add(b2bDeliveryAddress.getReceiveTellcall());
            }
            if (null != b2bDeliveryAddress.get()) {
                updateSql.add(" = ?");
                params.add(b2bDeliveryAddress.get());
            }
            if (null != b2bDeliveryAddress.get()) {
                updateSql.add(" = ?");
                params.add(b2bDeliveryAddress.get());
            }
            if (null != b2bDeliveryAddress.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bDeliveryAddress.getSupplierId());
            }
            if (null != b2bDeliveryAddress.getDeliveryAddressId()) {
                updateSql.add("delivery_address_id = ?");
                params.add(b2bDeliveryAddress.getDeliveryAddressId());
            }
            if (null != b2bDeliveryAddress.getRemake()) {
                updateSql.add("remake = ?");
                params.add(b2bDeliveryAddress.getRemake());
            }
            if (null != b2bDeliveryAddress.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bDeliveryAddress.getSupplierName());
            }
            if (null != b2bDeliveryAddress.getIsDefault()) {
                updateSql.add("is_default = ?");
                params.add(b2bDeliveryAddress.getIsDefault());
            }
            if (null != b2bDeliveryAddress.getPostCode()) {
                updateSql.add("post_code = ?");
                params.add(b2bDeliveryAddress.getPostCode());
            }
            if (null != b2bDeliveryAddress.get()) {
                updateSql.add(" = ?");
                params.add(b2bDeliveryAddress.get());
            }
            if (null != b2bDeliveryAddress.get()) {
                updateSql.add(" = ?");
                params.add(b2bDeliveryAddress.get());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE delivery_address_id = ?");
            params.add(b2bDeliveryAddress.getDeliveryAddressId());

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
    public int deleteByPrimaryKey(String deliveryAddressId) {
            if(null ==  deliveryAddressId) {
            return 0;
        }

        String sql = "delete from b2b_delivery_address where delivery_address_id  = ?";
        return jdbcTemplate.update(sql, deliveryAddressId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_delivery_address where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bDeliveryAddressBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bDeliveryAddressBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bDeliveryAddressBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bDeliveryAddressBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bDeliveryAddressBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bDeliveryAddressBean> findAll(Pageable arg0) {
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
	public void delete(B2bDeliveryAddressBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bDeliveryAddressBean> arg0) {
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
	public Iterable<B2bDeliveryAddressBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bDeliveryAddressBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bDeliveryAddressBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bDeliveryAddressBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bDeliveryAddressBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bDeliveryAddressBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bDeliveryAddressBean> findAll(
			Specification<B2bDeliveryAddressBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bDeliveryAddressBean> findAll(
			Specification<B2bDeliveryAddressBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bDeliveryAddressBean> findAll(
			Specification<B2bDeliveryAddressBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bDeliveryAddressBean findOne(
			Specification<B2bDeliveryAddressBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
