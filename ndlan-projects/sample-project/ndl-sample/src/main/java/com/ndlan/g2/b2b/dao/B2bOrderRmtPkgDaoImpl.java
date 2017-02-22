package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bOrderRmtPkgBean;

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

@Repository("b2bOrderRmtPkgDao")
public class B2bOrderRmtPkgDaoImpl implements B2bOrderRmtPkgDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bOrderRmtPkgBean selectByPrimaryKey(String orderRmtPkgId) {
        try {
            String sql = "select * from b2b_order_rmt_pkg where order_rmt_pkg_id = ?";

            List<B2bOrderRmtPkgBean> resultList = this.jdbcTemplate.query(sql, new Object[]{orderRmtPkgId},
                    new RowMapper<B2bOrderRmtPkgBean>() {
                        @Override
                        public B2bOrderRmtPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bOrderRmtPkgBean bean = new B2bOrderRmtPkgBean();
                            bean.setOrderRmtPkgCode(rs.getString("order_rmt_pkg_code"));
                            bean.setOrderRmtHeadId(rs.getString("order_rmt_head_id"));
                            bean.setLogOrderPkgId(rs.getString("log_order_pkg_id"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.set(rs.getString(""));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setAmount(rs.getString("amount"));
                            bean.set(rs.getString(""));
                            bean.setDerate(rs.getString("derate"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.set(rs.getDate(""));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setOrderRmtPkgId(rs.getString("order_rmt_pkg_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setLogOrderPkgCode(rs.getString("log_order_pkg_code"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setOrderPkgName(rs.getString("order_pkg_name"));
                            bean.setGoodsCatQty(rs.getString("goods_cat_qty"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setStorageStatus(rs.getString("storage_status"));
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
    public List<B2bOrderRmtPkgBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_rmt_pkg ";
            } else {
                sql = "select * from b2b_order_rmt_pkg where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderRmtPkgBean>() {
                        @Override
                        public B2bOrderRmtPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bOrderRmtPkgBean bean = new B2bOrderRmtPkgBean();
                            bean.setOrderRmtPkgCode(rs.getString("order_rmt_pkg_code"));
                            bean.setOrderRmtHeadId(rs.getString("order_rmt_head_id"));
                            bean.setLogOrderPkgId(rs.getString("log_order_pkg_id"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.set(rs.getString(""));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setAmount(rs.getString("amount"));
                            bean.set(rs.getString(""));
                            bean.setDerate(rs.getString("derate"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.set(rs.getDate(""));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setOrderRmtPkgId(rs.getString("order_rmt_pkg_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setLogOrderPkgCode(rs.getString("log_order_pkg_code"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setOrderPkgName(rs.getString("order_pkg_name"));
                            bean.setGoodsCatQty(rs.getString("goods_cat_qty"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setStorageStatus(rs.getString("storage_status"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderRmtPkgBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_rmt_pkg ";
            } else {
                sql = "select * from b2b_order_rmt_pkg where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderRmtPkgBean>() {
                        @Override
                        public B2bOrderRmtPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bOrderRmtPkgBean bean = new B2bOrderRmtPkgBean();
                            bean.setOrderRmtPkgCode(rs.getString("order_rmt_pkg_code"));
                            bean.setOrderRmtHeadId(rs.getString("order_rmt_head_id"));
                            bean.setLogOrderPkgId(rs.getString("log_order_pkg_id"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.set(rs.getString(""));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setAmount(rs.getString("amount"));
                            bean.set(rs.getString(""));
                            bean.setDerate(rs.getString("derate"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.set(rs.getDate(""));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setOrderRmtPkgId(rs.getString("order_rmt_pkg_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setLogOrderPkgCode(rs.getString("log_order_pkg_code"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setOrderPkgName(rs.getString("order_pkg_name"));
                            bean.setGoodsCatQty(rs.getString("goods_cat_qty"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setStorageStatus(rs.getString("storage_status"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderRmtPkgBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_order_rmt_pkg ";
            } else {
                sql = "select count(1) numCount from b2b_order_rmt_pkg where " + whereSql;
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
    public int insertSelective(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        try {
            if (null == b2bOrderRmtPkg) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_rmt_pkg(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bOrderRmtPkg.getOrderRmtPkgCode()) {
                columns.add("order_rmt_pkg_code");
                values.add(b2bOrderRmtPkg.getOrderRmtPkgCode());
            }
            if (null != b2bOrderRmtPkg.getOrderRmtHeadId()) {
                columns.add("order_rmt_head_id");
                values.add(b2bOrderRmtPkg.getOrderRmtHeadId());
            }
            if (null != b2bOrderRmtPkg.getLogOrderPkgId()) {
                columns.add("log_order_pkg_id");
                values.add(b2bOrderRmtPkg.getLogOrderPkgId());
            }
            if (null != b2bOrderRmtPkg.getCartId()) {
                columns.add("cart_id");
                values.add(b2bOrderRmtPkg.getCartId());
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                values.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.getStrategyDesc()) {
                columns.add("strategy_desc");
                values.add(b2bOrderRmtPkg.getStrategyDesc());
            }
            if (null != b2bOrderRmtPkg.getAmount()) {
                columns.add("amount");
                values.add(b2bOrderRmtPkg.getAmount());
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                values.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.getDerate()) {
                columns.add("derate");
                values.add(b2bOrderRmtPkg.getDerate());
            }
            if (null != b2bOrderRmtPkg.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bOrderRmtPkg.getSupplierName());
            }
            if (null != b2bOrderRmtPkg.getOrderPkgId()) {
                columns.add("order_pkg_id");
                values.add(b2bOrderRmtPkg.getOrderPkgId());
            }
            if (null != b2bOrderRmtPkg.getCodelessSum()) {
                columns.add("codeless_sum");
                values.add(b2bOrderRmtPkg.getCodelessSum());
            }
            if (null != b2bOrderRmtPkg.getDiscount()) {
                columns.add("discount");
                values.add(b2bOrderRmtPkg.getDiscount());
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                values.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.getPayType()) {
                columns.add("pay_type");
                values.add(b2bOrderRmtPkg.getPayType());
            }
            if (null != b2bOrderRmtPkg.getOrderHeadId()) {
                columns.add("order_head_id");
                values.add(b2bOrderRmtPkg.getOrderHeadId());
            }
            if (null != b2bOrderRmtPkg.getRestId()) {
                columns.add("rest_id");
                values.add(b2bOrderRmtPkg.getRestId());
            }
            if (null != b2bOrderRmtPkg.getCustomerName()) {
                columns.add("customer_name");
                values.add(b2bOrderRmtPkg.getCustomerName());
            }
            if (null != b2bOrderRmtPkg.getOrderRmtPkgId()) {
                columns.add("order_rmt_pkg_id");
                values.add(b2bOrderRmtPkg.getOrderRmtPkgId());
            }
            if (null != b2bOrderRmtPkg.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bOrderRmtPkg.getSupplierId());
            }
            if (null != b2bOrderRmtPkg.getOrignStatus()) {
                columns.add("orign_status");
                values.add(b2bOrderRmtPkg.getOrignStatus());
            }
            if (null != b2bOrderRmtPkg.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                values.add(b2bOrderRmtPkg.getSlsPmtnId());
            }
            if (null != b2bOrderRmtPkg.getRestName()) {
                columns.add("rest_name");
                values.add(b2bOrderRmtPkg.getRestName());
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                values.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                values.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.getAmountPaid()) {
                columns.add("amount_paid");
                values.add(b2bOrderRmtPkg.getAmountPaid());
            }
            if (null != b2bOrderRmtPkg.getLogOrderPkgCode()) {
                columns.add("log_order_pkg_code");
                values.add(b2bOrderRmtPkg.getLogOrderPkgCode());
            }
            if (null != b2bOrderRmtPkg.getCustomerId()) {
                columns.add("customer_id");
                values.add(b2bOrderRmtPkg.getCustomerId());
            }
            if (null != b2bOrderRmtPkg.getRemark()) {
                columns.add("remark");
                values.add(b2bOrderRmtPkg.getRemark());
            }
            if (null != b2bOrderRmtPkg.getOrderPkgName()) {
                columns.add("order_pkg_name");
                values.add(b2bOrderRmtPkg.getOrderPkgName());
            }
            if (null != b2bOrderRmtPkg.getGoodsCatQty()) {
                columns.add("goods_cat_qty");
                values.add(b2bOrderRmtPkg.getGoodsCatQty());
            }
            if (null != b2bOrderRmtPkg.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                values.add(b2bOrderRmtPkg.getOrderPkgCode());
            }
            if (null != b2bOrderRmtPkg.getStorageStatus()) {
                columns.add("storage_status");
                values.add(b2bOrderRmtPkg.getStorageStatus());
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
    public int insertSelectiveAndGetId(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        try {
            if (null == b2bOrderRmtPkg) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_rmt_pkg(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bOrderRmtPkg.getOrderRmtPkgCode()) {
                columns.add("order_rmt_pkg_code");
                parameters.add(":orderRmtPkgCode");
            }
            if (null != b2bOrderRmtPkg.getOrderRmtHeadId()) {
                columns.add("order_rmt_head_id");
                parameters.add(":orderRmtHeadId");
            }
            if (null != b2bOrderRmtPkg.getLogOrderPkgId()) {
                columns.add("log_order_pkg_id");
                parameters.add(":logOrderPkgId");
            }
            if (null != b2bOrderRmtPkg.getCartId()) {
                columns.add("cart_id");
                parameters.add(":cartId");
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderRmtPkg.getStrategyDesc()) {
                columns.add("strategy_desc");
                parameters.add(":strategyDesc");
            }
            if (null != b2bOrderRmtPkg.getAmount()) {
                columns.add("amount");
                parameters.add(":amount");
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderRmtPkg.getDerate()) {
                columns.add("derate");
                parameters.add(":derate");
            }
            if (null != b2bOrderRmtPkg.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bOrderRmtPkg.getOrderPkgId()) {
                columns.add("order_pkg_id");
                parameters.add(":orderPkgId");
            }
            if (null != b2bOrderRmtPkg.getCodelessSum()) {
                columns.add("codeless_sum");
                parameters.add(":codelessSum");
            }
            if (null != b2bOrderRmtPkg.getDiscount()) {
                columns.add("discount");
                parameters.add(":discount");
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderRmtPkg.getPayType()) {
                columns.add("pay_type");
                parameters.add(":payType");
            }
            if (null != b2bOrderRmtPkg.getOrderHeadId()) {
                columns.add("order_head_id");
                parameters.add(":orderHeadId");
            }
            if (null != b2bOrderRmtPkg.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bOrderRmtPkg.getCustomerName()) {
                columns.add("customer_name");
                parameters.add(":customerName");
            }
            if (null != b2bOrderRmtPkg.getOrderRmtPkgId()) {
                columns.add("order_rmt_pkg_id");
                parameters.add(":orderRmtPkgId");
            }
            if (null != b2bOrderRmtPkg.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bOrderRmtPkg.getOrignStatus()) {
                columns.add("orign_status");
                parameters.add(":orignStatus");
            }
            if (null != b2bOrderRmtPkg.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                parameters.add(":slsPmtnId");
            }
            if (null != b2bOrderRmtPkg.getRestName()) {
                columns.add("rest_name");
                parameters.add(":restName");
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderRmtPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderRmtPkg.getAmountPaid()) {
                columns.add("amount_paid");
                parameters.add(":amountPaid");
            }
            if (null != b2bOrderRmtPkg.getLogOrderPkgCode()) {
                columns.add("log_order_pkg_code");
                parameters.add(":logOrderPkgCode");
            }
            if (null != b2bOrderRmtPkg.getCustomerId()) {
                columns.add("customer_id");
                parameters.add(":customerId");
            }
            if (null != b2bOrderRmtPkg.getRemark()) {
                columns.add("remark");
                parameters.add(":remark");
            }
            if (null != b2bOrderRmtPkg.getOrderPkgName()) {
                columns.add("order_pkg_name");
                parameters.add(":orderPkgName");
            }
            if (null != b2bOrderRmtPkg.getGoodsCatQty()) {
                columns.add("goods_cat_qty");
                parameters.add(":goodsCatQty");
            }
            if (null != b2bOrderRmtPkg.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                parameters.add(":orderPkgCode");
            }
            if (null != b2bOrderRmtPkg.getStorageStatus()) {
                columns.add("storage_status");
                parameters.add(":storageStatus");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bOrderRmtPkg);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bOrderRmtPkg.setOrderRmtPkgId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        try {
            if (null == b2bOrderRmtPkg.getOrderRmtPkgId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_order_rmt_pkg SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bOrderRmtPkg.getOrderRmtPkgCode()) {
                updateSql.add("order_rmt_pkg_code = ?");
                params.add(b2bOrderRmtPkg.getOrderRmtPkgCode());
            }
            if (null != b2bOrderRmtPkg.getOrderRmtHeadId()) {
                updateSql.add("order_rmt_head_id = ?");
                params.add(b2bOrderRmtPkg.getOrderRmtHeadId());
            }
            if (null != b2bOrderRmtPkg.getLogOrderPkgId()) {
                updateSql.add("log_order_pkg_id = ?");
                params.add(b2bOrderRmtPkg.getLogOrderPkgId());
            }
            if (null != b2bOrderRmtPkg.getCartId()) {
                updateSql.add("cart_id = ?");
                params.add(b2bOrderRmtPkg.getCartId());
            }
            if (null != b2bOrderRmtPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.getStrategyDesc()) {
                updateSql.add("strategy_desc = ?");
                params.add(b2bOrderRmtPkg.getStrategyDesc());
            }
            if (null != b2bOrderRmtPkg.getAmount()) {
                updateSql.add("amount = ?");
                params.add(b2bOrderRmtPkg.getAmount());
            }
            if (null != b2bOrderRmtPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.getDerate()) {
                updateSql.add("derate = ?");
                params.add(b2bOrderRmtPkg.getDerate());
            }
            if (null != b2bOrderRmtPkg.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bOrderRmtPkg.getSupplierName());
            }
            if (null != b2bOrderRmtPkg.getOrderPkgId()) {
                updateSql.add("order_pkg_id = ?");
                params.add(b2bOrderRmtPkg.getOrderPkgId());
            }
            if (null != b2bOrderRmtPkg.getCodelessSum()) {
                updateSql.add("codeless_sum = ?");
                params.add(b2bOrderRmtPkg.getCodelessSum());
            }
            if (null != b2bOrderRmtPkg.getDiscount()) {
                updateSql.add("discount = ?");
                params.add(b2bOrderRmtPkg.getDiscount());
            }
            if (null != b2bOrderRmtPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.getPayType()) {
                updateSql.add("pay_type = ?");
                params.add(b2bOrderRmtPkg.getPayType());
            }
            if (null != b2bOrderRmtPkg.getOrderHeadId()) {
                updateSql.add("order_head_id = ?");
                params.add(b2bOrderRmtPkg.getOrderHeadId());
            }
            if (null != b2bOrderRmtPkg.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bOrderRmtPkg.getRestId());
            }
            if (null != b2bOrderRmtPkg.getCustomerName()) {
                updateSql.add("customer_name = ?");
                params.add(b2bOrderRmtPkg.getCustomerName());
            }
            if (null != b2bOrderRmtPkg.getOrderRmtPkgId()) {
                updateSql.add("order_rmt_pkg_id = ?");
                params.add(b2bOrderRmtPkg.getOrderRmtPkgId());
            }
            if (null != b2bOrderRmtPkg.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bOrderRmtPkg.getSupplierId());
            }
            if (null != b2bOrderRmtPkg.getOrignStatus()) {
                updateSql.add("orign_status = ?");
                params.add(b2bOrderRmtPkg.getOrignStatus());
            }
            if (null != b2bOrderRmtPkg.getSlsPmtnId()) {
                updateSql.add("sls_pmtn_id = ?");
                params.add(b2bOrderRmtPkg.getSlsPmtnId());
            }
            if (null != b2bOrderRmtPkg.getRestName()) {
                updateSql.add("rest_name = ?");
                params.add(b2bOrderRmtPkg.getRestName());
            }
            if (null != b2bOrderRmtPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderRmtPkg.get());
            }
            if (null != b2bOrderRmtPkg.getAmountPaid()) {
                updateSql.add("amount_paid = ?");
                params.add(b2bOrderRmtPkg.getAmountPaid());
            }
            if (null != b2bOrderRmtPkg.getLogOrderPkgCode()) {
                updateSql.add("log_order_pkg_code = ?");
                params.add(b2bOrderRmtPkg.getLogOrderPkgCode());
            }
            if (null != b2bOrderRmtPkg.getCustomerId()) {
                updateSql.add("customer_id = ?");
                params.add(b2bOrderRmtPkg.getCustomerId());
            }
            if (null != b2bOrderRmtPkg.getRemark()) {
                updateSql.add("remark = ?");
                params.add(b2bOrderRmtPkg.getRemark());
            }
            if (null != b2bOrderRmtPkg.getOrderPkgName()) {
                updateSql.add("order_pkg_name = ?");
                params.add(b2bOrderRmtPkg.getOrderPkgName());
            }
            if (null != b2bOrderRmtPkg.getGoodsCatQty()) {
                updateSql.add("goods_cat_qty = ?");
                params.add(b2bOrderRmtPkg.getGoodsCatQty());
            }
            if (null != b2bOrderRmtPkg.getOrderPkgCode()) {
                updateSql.add("order_pkg_code = ?");
                params.add(b2bOrderRmtPkg.getOrderPkgCode());
            }
            if (null != b2bOrderRmtPkg.getStorageStatus()) {
                updateSql.add("storage_status = ?");
                params.add(b2bOrderRmtPkg.getStorageStatus());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE order_rmt_pkg_id = ?");
            params.add(b2bOrderRmtPkg.getOrderRmtPkgId());

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
    public int deleteByPrimaryKey(String orderRmtPkgId) {
            if(null ==  orderRmtPkgId) {
            return 0;
        }

        String sql = "delete from b2b_order_rmt_pkg where order_rmt_pkg_id  = ?";
        return jdbcTemplate.update(sql, orderRmtPkgId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_order_rmt_pkg where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bOrderRmtPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bOrderRmtPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bOrderRmtPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bOrderRmtPkgBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bOrderRmtPkgBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderRmtPkgBean> findAll(Pageable arg0) {
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
	public void delete(B2bOrderRmtPkgBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bOrderRmtPkgBean> arg0) {
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
	public Iterable<B2bOrderRmtPkgBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bOrderRmtPkgBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderRmtPkgBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderRmtPkgBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderRmtPkgBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bOrderRmtPkgBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bOrderRmtPkgBean> findAll(
			Specification<B2bOrderRmtPkgBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderRmtPkgBean> findAll(
			Specification<B2bOrderRmtPkgBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bOrderRmtPkgBean> findAll(
			Specification<B2bOrderRmtPkgBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderRmtPkgBean findOne(
			Specification<B2bOrderRmtPkgBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
