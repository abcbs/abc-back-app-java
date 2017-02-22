package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bOrderDeliveryBean;

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

@Repository("b2bOrderDeliveryDao")
public class B2bOrderDeliveryDaoImpl implements B2bOrderDeliveryDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bOrderDeliveryBean selectByPrimaryKey(String orderDeliveryId) {
        try {
            String sql = "select * from b2b_order_delivery where order_delivery_id = ?";

            List<B2bOrderDeliveryBean> resultList = this.jdbcTemplate.query(sql, new Object[]{orderDeliveryId},
                    new RowMapper<B2bOrderDeliveryBean>() {
                        @Override
                        public B2bOrderDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bOrderDeliveryBean bean = new B2bOrderDeliveryBean();
                            bean.set(rs.getDate(""));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setRegion(rs.getString("region"));
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.set(rs.getString(""));
                            bean.set(rs.getDate(""));
                            bean.setOrderDeliveryId(rs.getString("order_delivery_id"));
                            bean.setCustomId(rs.getString("custom_id"));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.setCustomName(rs.getString("custom_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.set(rs.getString(""));
                            bean.setPostCode(rs.getString("post_code"));
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
    public List<B2bOrderDeliveryBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_delivery ";
            } else {
                sql = "select * from b2b_order_delivery where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderDeliveryBean>() {
                        @Override
                        public B2bOrderDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bOrderDeliveryBean bean = new B2bOrderDeliveryBean();
                            bean.set(rs.getDate(""));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setRegion(rs.getString("region"));
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.set(rs.getString(""));
                            bean.set(rs.getDate(""));
                            bean.setOrderDeliveryId(rs.getString("order_delivery_id"));
                            bean.setCustomId(rs.getString("custom_id"));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.setCustomName(rs.getString("custom_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.set(rs.getString(""));
                            bean.setPostCode(rs.getString("post_code"));
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
    public List<B2bOrderDeliveryBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_delivery ";
            } else {
                sql = "select * from b2b_order_delivery where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderDeliveryBean>() {
                        @Override
                        public B2bOrderDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bOrderDeliveryBean bean = new B2bOrderDeliveryBean();
                            bean.set(rs.getDate(""));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setRegion(rs.getString("region"));
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.set(rs.getString(""));
                            bean.set(rs.getDate(""));
                            bean.setOrderDeliveryId(rs.getString("order_delivery_id"));
                            bean.setCustomId(rs.getString("custom_id"));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.setCustomName(rs.getString("custom_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.set(rs.getString(""));
                            bean.setPostCode(rs.getString("post_code"));
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
    public List<B2bOrderDeliveryBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_order_delivery ";
            } else {
                sql = "select count(1) numCount from b2b_order_delivery where " + whereSql;
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
    public int insertSelective(B2bOrderDeliveryBean b2bOrderDelivery) {
        try {
            if (null == b2bOrderDelivery) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_delivery(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                values.add(b2bOrderDelivery.get());
            }
            if (null != b2bOrderDelivery.getDeliveryAddressId()) {
                columns.add("delivery_address_id");
                values.add(b2bOrderDelivery.getDeliveryAddressId());
            }
            if (null != b2bOrderDelivery.getRegion()) {
                columns.add("region");
                values.add(b2bOrderDelivery.getRegion());
            }
            if (null != b2bOrderDelivery.getReceiveTellcall()) {
                columns.add("receive_tellcall");
                values.add(b2bOrderDelivery.getReceiveTellcall());
            }
            if (null != b2bOrderDelivery.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bOrderDelivery.getSupplierName());
            }
            if (null != b2bOrderDelivery.getReceiveName()) {
                columns.add("receive_name");
                values.add(b2bOrderDelivery.getReceiveName());
            }
            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                values.add(b2bOrderDelivery.get());
            }
            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                values.add(b2bOrderDelivery.get());
            }
            if (null != b2bOrderDelivery.getOrderDeliveryId()) {
                columns.add("order_delivery_id");
                values.add(b2bOrderDelivery.getOrderDeliveryId());
            }
            if (null != b2bOrderDelivery.getCustomId()) {
                columns.add("custom_id");
                values.add(b2bOrderDelivery.getCustomId());
            }
            if (null != b2bOrderDelivery.getDetailAddress()) {
                columns.add("detail_address");
                values.add(b2bOrderDelivery.getDetailAddress());
            }
            if (null != b2bOrderDelivery.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bOrderDelivery.getSupplierId());
            }
            if (null != b2bOrderDelivery.getRemake()) {
                columns.add("remake");
                values.add(b2bOrderDelivery.getRemake());
            }
            if (null != b2bOrderDelivery.getReceivePhone()) {
                columns.add("receive_phone");
                values.add(b2bOrderDelivery.getReceivePhone());
            }
            if (null != b2bOrderDelivery.getCustomName()) {
                columns.add("custom_name");
                values.add(b2bOrderDelivery.getCustomName());
            }
            if (null != b2bOrderDelivery.getOrderPkgId()) {
                columns.add("order_pkg_id");
                values.add(b2bOrderDelivery.getOrderPkgId());
            }
            if (null != b2bOrderDelivery.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                values.add(b2bOrderDelivery.getOrderPkgCode());
            }
            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                values.add(b2bOrderDelivery.get());
            }
            if (null != b2bOrderDelivery.getPostCode()) {
                columns.add("post_code");
                values.add(b2bOrderDelivery.getPostCode());
            }
            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                values.add(b2bOrderDelivery.get());
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
    public int insertSelectiveAndGetId(B2bOrderDeliveryBean b2bOrderDelivery) {
        try {
            if (null == b2bOrderDelivery) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_delivery(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderDelivery.getDeliveryAddressId()) {
                columns.add("delivery_address_id");
                parameters.add(":deliveryAddressId");
            }
            if (null != b2bOrderDelivery.getRegion()) {
                columns.add("region");
                parameters.add(":region");
            }
            if (null != b2bOrderDelivery.getReceiveTellcall()) {
                columns.add("receive_tellcall");
                parameters.add(":receiveTellcall");
            }
            if (null != b2bOrderDelivery.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bOrderDelivery.getReceiveName()) {
                columns.add("receive_name");
                parameters.add(":receiveName");
            }
            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderDelivery.getOrderDeliveryId()) {
                columns.add("order_delivery_id");
                parameters.add(":orderDeliveryId");
            }
            if (null != b2bOrderDelivery.getCustomId()) {
                columns.add("custom_id");
                parameters.add(":customId");
            }
            if (null != b2bOrderDelivery.getDetailAddress()) {
                columns.add("detail_address");
                parameters.add(":detailAddress");
            }
            if (null != b2bOrderDelivery.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bOrderDelivery.getRemake()) {
                columns.add("remake");
                parameters.add(":remake");
            }
            if (null != b2bOrderDelivery.getReceivePhone()) {
                columns.add("receive_phone");
                parameters.add(":receivePhone");
            }
            if (null != b2bOrderDelivery.getCustomName()) {
                columns.add("custom_name");
                parameters.add(":customName");
            }
            if (null != b2bOrderDelivery.getOrderPkgId()) {
                columns.add("order_pkg_id");
                parameters.add(":orderPkgId");
            }
            if (null != b2bOrderDelivery.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                parameters.add(":orderPkgCode");
            }
            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderDelivery.getPostCode()) {
                columns.add("post_code");
                parameters.add(":postCode");
            }
            if (null != b2bOrderDelivery.get()) {
                columns.add("");
                parameters.add(":");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bOrderDelivery);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bOrderDelivery.setOrderDeliveryId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bOrderDeliveryBean b2bOrderDelivery) {
        try {
            if (null == b2bOrderDelivery.getOrderDeliveryId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_order_delivery SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bOrderDelivery.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderDelivery.get());
            }
            if (null != b2bOrderDelivery.getDeliveryAddressId()) {
                updateSql.add("delivery_address_id = ?");
                params.add(b2bOrderDelivery.getDeliveryAddressId());
            }
            if (null != b2bOrderDelivery.getRegion()) {
                updateSql.add("region = ?");
                params.add(b2bOrderDelivery.getRegion());
            }
            if (null != b2bOrderDelivery.getReceiveTellcall()) {
                updateSql.add("receive_tellcall = ?");
                params.add(b2bOrderDelivery.getReceiveTellcall());
            }
            if (null != b2bOrderDelivery.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bOrderDelivery.getSupplierName());
            }
            if (null != b2bOrderDelivery.getReceiveName()) {
                updateSql.add("receive_name = ?");
                params.add(b2bOrderDelivery.getReceiveName());
            }
            if (null != b2bOrderDelivery.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderDelivery.get());
            }
            if (null != b2bOrderDelivery.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderDelivery.get());
            }
            if (null != b2bOrderDelivery.getOrderDeliveryId()) {
                updateSql.add("order_delivery_id = ?");
                params.add(b2bOrderDelivery.getOrderDeliveryId());
            }
            if (null != b2bOrderDelivery.getCustomId()) {
                updateSql.add("custom_id = ?");
                params.add(b2bOrderDelivery.getCustomId());
            }
            if (null != b2bOrderDelivery.getDetailAddress()) {
                updateSql.add("detail_address = ?");
                params.add(b2bOrderDelivery.getDetailAddress());
            }
            if (null != b2bOrderDelivery.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bOrderDelivery.getSupplierId());
            }
            if (null != b2bOrderDelivery.getRemake()) {
                updateSql.add("remake = ?");
                params.add(b2bOrderDelivery.getRemake());
            }
            if (null != b2bOrderDelivery.getReceivePhone()) {
                updateSql.add("receive_phone = ?");
                params.add(b2bOrderDelivery.getReceivePhone());
            }
            if (null != b2bOrderDelivery.getCustomName()) {
                updateSql.add("custom_name = ?");
                params.add(b2bOrderDelivery.getCustomName());
            }
            if (null != b2bOrderDelivery.getOrderPkgId()) {
                updateSql.add("order_pkg_id = ?");
                params.add(b2bOrderDelivery.getOrderPkgId());
            }
            if (null != b2bOrderDelivery.getOrderPkgCode()) {
                updateSql.add("order_pkg_code = ?");
                params.add(b2bOrderDelivery.getOrderPkgCode());
            }
            if (null != b2bOrderDelivery.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderDelivery.get());
            }
            if (null != b2bOrderDelivery.getPostCode()) {
                updateSql.add("post_code = ?");
                params.add(b2bOrderDelivery.getPostCode());
            }
            if (null != b2bOrderDelivery.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderDelivery.get());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE order_delivery_id = ?");
            params.add(b2bOrderDelivery.getOrderDeliveryId());

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
    public int deleteByPrimaryKey(String orderDeliveryId) {
            if(null ==  orderDeliveryId) {
            return 0;
        }

        String sql = "delete from b2b_order_delivery where order_delivery_id  = ?";
        return jdbcTemplate.update(sql, orderDeliveryId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_order_delivery where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bOrderDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bOrderDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bOrderDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bOrderDeliveryBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bOrderDeliveryBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderDeliveryBean> findAll(Pageable arg0) {
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
	public void delete(B2bOrderDeliveryBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bOrderDeliveryBean> arg0) {
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
	public Iterable<B2bOrderDeliveryBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bOrderDeliveryBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderDeliveryBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderDeliveryBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderDeliveryBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bOrderDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bOrderDeliveryBean> findAll(
			Specification<B2bOrderDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderDeliveryBean> findAll(
			Specification<B2bOrderDeliveryBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bOrderDeliveryBean> findAll(
			Specification<B2bOrderDeliveryBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderDeliveryBean findOne(
			Specification<B2bOrderDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
