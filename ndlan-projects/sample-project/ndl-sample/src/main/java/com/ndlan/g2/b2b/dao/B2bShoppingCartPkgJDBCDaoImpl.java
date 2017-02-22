package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bShoppingCartPkgBean;

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

@Repository("b2bShoppingCartPkgJDBCDao")
public class B2bShoppingCartPkgJDBCDaoImpl implements B2bShoppingCartPkgJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bShoppingCartPkgBean selectByPrimaryKey(String cartPkgId) {
        try {
            String sql = "select * from b2b_shopping_cart_pkg where cart_pkg_id = ?";

            List<B2bShoppingCartPkgBean> resultList = this.jdbcTemplate.query(sql, new Object[]{cartPkgId},
                    new RowMapper<B2bShoppingCartPkgBean>() {
                        @Override
                        public B2bShoppingCartPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bShoppingCartPkgBean bean = new B2bShoppingCartPkgBean();
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setCartPkgId(rs.getString("cart_pkg_id"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setAmount(rs.getString("amount"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setDerate(rs.getString("derate"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setStatus(rs.getString("status"));
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
    public List<B2bShoppingCartPkgBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_shopping_cart_pkg ";
            } else {
                sql = "select * from b2b_shopping_cart_pkg where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bShoppingCartPkgBean>() {
                        @Override
                        public B2bShoppingCartPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bShoppingCartPkgBean bean = new B2bShoppingCartPkgBean();
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setCartPkgId(rs.getString("cart_pkg_id"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setAmount(rs.getString("amount"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setDerate(rs.getString("derate"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setStatus(rs.getString("status"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bShoppingCartPkgBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_shopping_cart_pkg ";
            } else {
                sql = "select * from b2b_shopping_cart_pkg where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bShoppingCartPkgBean>() {
                        @Override
                        public B2bShoppingCartPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bShoppingCartPkgBean bean = new B2bShoppingCartPkgBean();
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setCartPkgId(rs.getString("cart_pkg_id"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setAmount(rs.getString("amount"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setDerate(rs.getString("derate"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setStatus(rs.getString("status"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bShoppingCartPkgBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_shopping_cart_pkg ";
            } else {
                sql = "select count(1) numCount from b2b_shopping_cart_pkg where " + whereSql;
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
    public int insertSelective(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        try {
            if (null == b2bShoppingCartPkg) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_shopping_cart_pkg(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bShoppingCartPkg.getCartId()) {
                columns.add("cart_id");
                values.add(b2bShoppingCartPkg.getCartId());
            }
            if (null != b2bShoppingCartPkg.getRestId()) {
                columns.add("rest_id");
                values.add(b2bShoppingCartPkg.getRestId());
            }
            if (null != b2bShoppingCartPkg.getStrategyDesc()) {
                columns.add("strategy_desc");
                values.add(b2bShoppingCartPkg.getStrategyDesc());
            }
            if (null != b2bShoppingCartPkg.getCartPkgId()) {
                columns.add("cart_pkg_id");
                values.add(b2bShoppingCartPkg.getCartPkgId());
            }
            if (null != b2bShoppingCartPkg.getDiscount()) {
                columns.add("discount");
                values.add(b2bShoppingCartPkg.getDiscount());
            }
            if (null != b2bShoppingCartPkg.getCustomerName()) {
                columns.add("customer_name");
                values.add(b2bShoppingCartPkg.getCustomerName());
            }
            if (null != b2bShoppingCartPkg.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bShoppingCartPkg.getUpdateTime());
            }
            if (null != b2bShoppingCartPkg.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bShoppingCartPkg.getCreateBy());
            }
            if (null != b2bShoppingCartPkg.getAmount()) {
                columns.add("amount");
                values.add(b2bShoppingCartPkg.getAmount());
            }
            if (null != b2bShoppingCartPkg.getCustomerId()) {
                columns.add("customer_id");
                values.add(b2bShoppingCartPkg.getCustomerId());
            }
            if (null != b2bShoppingCartPkg.getPayType()) {
                columns.add("pay_type");
                values.add(b2bShoppingCartPkg.getPayType());
            }
            if (null != b2bShoppingCartPkg.getAmountPaid()) {
                columns.add("amount_paid");
                values.add(b2bShoppingCartPkg.getAmountPaid());
            }
            if (null != b2bShoppingCartPkg.getRemark()) {
                columns.add("remark");
                values.add(b2bShoppingCartPkg.getRemark());
            }
            if (null != b2bShoppingCartPkg.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bShoppingCartPkg.getSupplierId());
            }
            if (null != b2bShoppingCartPkg.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                values.add(b2bShoppingCartPkg.getSlsPmtnId());
            }
            if (null != b2bShoppingCartPkg.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bShoppingCartPkg.getCreateTime());
            }
            if (null != b2bShoppingCartPkg.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bShoppingCartPkg.getSupplierName());
            }
            if (null != b2bShoppingCartPkg.getDerate()) {
                columns.add("derate");
                values.add(b2bShoppingCartPkg.getDerate());
            }
            if (null != b2bShoppingCartPkg.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bShoppingCartPkg.getTargetClient());
            }
            if (null != b2bShoppingCartPkg.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bShoppingCartPkg.getUpdateBy());
            }
            if (null != b2bShoppingCartPkg.getCodelessSum()) {
                columns.add("codeless_sum");
                values.add(b2bShoppingCartPkg.getCodelessSum());
            }
            if (null != b2bShoppingCartPkg.getRestName()) {
                columns.add("rest_name");
                values.add(b2bShoppingCartPkg.getRestName());
            }
            if (null != b2bShoppingCartPkg.getStatus()) {
                columns.add("status");
                values.add(b2bShoppingCartPkg.getStatus());
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
    public int insertSelectiveAndGetId(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        try {
            if (null == b2bShoppingCartPkg) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_shopping_cart_pkg(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bShoppingCartPkg.getCartId()) {
                columns.add("cart_id");
                parameters.add(":cartId");
            }
            if (null != b2bShoppingCartPkg.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bShoppingCartPkg.getStrategyDesc()) {
                columns.add("strategy_desc");
                parameters.add(":strategyDesc");
            }
            if (null != b2bShoppingCartPkg.getCartPkgId()) {
                columns.add("cart_pkg_id");
                parameters.add(":cartPkgId");
            }
            if (null != b2bShoppingCartPkg.getDiscount()) {
                columns.add("discount");
                parameters.add(":discount");
            }
            if (null != b2bShoppingCartPkg.getCustomerName()) {
                columns.add("customer_name");
                parameters.add(":customerName");
            }
            if (null != b2bShoppingCartPkg.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bShoppingCartPkg.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }
            if (null != b2bShoppingCartPkg.getAmount()) {
                columns.add("amount");
                parameters.add(":amount");
            }
            if (null != b2bShoppingCartPkg.getCustomerId()) {
                columns.add("customer_id");
                parameters.add(":customerId");
            }
            if (null != b2bShoppingCartPkg.getPayType()) {
                columns.add("pay_type");
                parameters.add(":payType");
            }
            if (null != b2bShoppingCartPkg.getAmountPaid()) {
                columns.add("amount_paid");
                parameters.add(":amountPaid");
            }
            if (null != b2bShoppingCartPkg.getRemark()) {
                columns.add("remark");
                parameters.add(":remark");
            }
            if (null != b2bShoppingCartPkg.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bShoppingCartPkg.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                parameters.add(":slsPmtnId");
            }
            if (null != b2bShoppingCartPkg.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bShoppingCartPkg.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bShoppingCartPkg.getDerate()) {
                columns.add("derate");
                parameters.add(":derate");
            }
            if (null != b2bShoppingCartPkg.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bShoppingCartPkg.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bShoppingCartPkg.getCodelessSum()) {
                columns.add("codeless_sum");
                parameters.add(":codelessSum");
            }
            if (null != b2bShoppingCartPkg.getRestName()) {
                columns.add("rest_name");
                parameters.add(":restName");
            }
            if (null != b2bShoppingCartPkg.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bShoppingCartPkg);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bShoppingCartPkg.setCartPkgId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        try {
            if (null == b2bShoppingCartPkg.getCartPkgId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_shopping_cart_pkg SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bShoppingCartPkg.getCartId()) {
                updateSql.add("cart_id = ?");
                params.add(b2bShoppingCartPkg.getCartId());
            }
            if (null != b2bShoppingCartPkg.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bShoppingCartPkg.getRestId());
            }
            if (null != b2bShoppingCartPkg.getStrategyDesc()) {
                updateSql.add("strategy_desc = ?");
                params.add(b2bShoppingCartPkg.getStrategyDesc());
            }
            if (null != b2bShoppingCartPkg.getCartPkgId()) {
                updateSql.add("cart_pkg_id = ?");
                params.add(b2bShoppingCartPkg.getCartPkgId());
            }
            if (null != b2bShoppingCartPkg.getDiscount()) {
                updateSql.add("discount = ?");
                params.add(b2bShoppingCartPkg.getDiscount());
            }
            if (null != b2bShoppingCartPkg.getCustomerName()) {
                updateSql.add("customer_name = ?");
                params.add(b2bShoppingCartPkg.getCustomerName());
            }
            if (null != b2bShoppingCartPkg.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bShoppingCartPkg.getUpdateTime());
            }
            if (null != b2bShoppingCartPkg.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bShoppingCartPkg.getCreateBy());
            }
            if (null != b2bShoppingCartPkg.getAmount()) {
                updateSql.add("amount = ?");
                params.add(b2bShoppingCartPkg.getAmount());
            }
            if (null != b2bShoppingCartPkg.getCustomerId()) {
                updateSql.add("customer_id = ?");
                params.add(b2bShoppingCartPkg.getCustomerId());
            }
            if (null != b2bShoppingCartPkg.getPayType()) {
                updateSql.add("pay_type = ?");
                params.add(b2bShoppingCartPkg.getPayType());
            }
            if (null != b2bShoppingCartPkg.getAmountPaid()) {
                updateSql.add("amount_paid = ?");
                params.add(b2bShoppingCartPkg.getAmountPaid());
            }
            if (null != b2bShoppingCartPkg.getRemark()) {
                updateSql.add("remark = ?");
                params.add(b2bShoppingCartPkg.getRemark());
            }
            if (null != b2bShoppingCartPkg.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bShoppingCartPkg.getSupplierId());
            }
            if (null != b2bShoppingCartPkg.getSlsPmtnId()) {
                updateSql.add("sls_pmtn_id = ?");
                params.add(b2bShoppingCartPkg.getSlsPmtnId());
            }
            if (null != b2bShoppingCartPkg.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bShoppingCartPkg.getCreateTime());
            }
            if (null != b2bShoppingCartPkg.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bShoppingCartPkg.getSupplierName());
            }
            if (null != b2bShoppingCartPkg.getDerate()) {
                updateSql.add("derate = ?");
                params.add(b2bShoppingCartPkg.getDerate());
            }
            if (null != b2bShoppingCartPkg.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bShoppingCartPkg.getTargetClient());
            }
            if (null != b2bShoppingCartPkg.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bShoppingCartPkg.getUpdateBy());
            }
            if (null != b2bShoppingCartPkg.getCodelessSum()) {
                updateSql.add("codeless_sum = ?");
                params.add(b2bShoppingCartPkg.getCodelessSum());
            }
            if (null != b2bShoppingCartPkg.getRestName()) {
                updateSql.add("rest_name = ?");
                params.add(b2bShoppingCartPkg.getRestName());
            }
            if (null != b2bShoppingCartPkg.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bShoppingCartPkg.getStatus());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE cart_pkg_id = ?");
            params.add(b2bShoppingCartPkg.getCartPkgId());

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
    public int deleteByPrimaryKey(String cartPkgId) {
            if(null ==  cartPkgId) {
            return 0;
        }

        String sql = "delete from b2b_shopping_cart_pkg where cart_pkg_id  = ?";
        return jdbcTemplate.update(sql, cartPkgId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_shopping_cart_pkg where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bShoppingCartPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bShoppingCartPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bShoppingCartPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bShoppingCartPkgBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bShoppingCartPkgBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bShoppingCartPkgBean> findAll(Pageable arg0) {
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
	public void delete(B2bShoppingCartPkgBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bShoppingCartPkgBean> arg0) {
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
	public Iterable<B2bShoppingCartPkgBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bShoppingCartPkgBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bShoppingCartPkgBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bShoppingCartPkgBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bShoppingCartPkgBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bShoppingCartPkgBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bShoppingCartPkgBean> findAll(
			Specification<B2bShoppingCartPkgBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bShoppingCartPkgBean> findAll(
			Specification<B2bShoppingCartPkgBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bShoppingCartPkgBean> findAll(
			Specification<B2bShoppingCartPkgBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bShoppingCartPkgBean findOne(
			Specification<B2bShoppingCartPkgBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
