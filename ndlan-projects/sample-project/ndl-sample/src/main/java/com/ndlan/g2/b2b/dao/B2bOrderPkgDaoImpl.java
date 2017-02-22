package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bOrderPkgBean;

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

@Repository("b2bOrderPkgDao")
public class B2bOrderPkgDaoImpl implements B2bOrderPkgDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bOrderPkgBean selectByPrimaryKey(String orderPkgId) {
        try {
            String sql = "select * from b2b_order_pkg where order_pkg_id = ?";

            List<B2bOrderPkgBean> resultList = this.jdbcTemplate.query(sql, new Object[]{orderPkgId},
                    new RowMapper<B2bOrderPkgBean>() {
                        @Override
                        public B2bOrderPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bOrderPkgBean bean = new B2bOrderPkgBean();
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.set(rs.getString(""));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setOrderPkgName(rs.getString("order_pkg_name"));
                            bean.setExceptionDesc(rs.getString("exception_desc"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.set(rs.getDate(""));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setUnpaidAmount(rs.getString("unpaid_amount"));
                            bean.set(rs.getString(""));
                            bean.setRemark(rs.getString("remark"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setStatusDesc(rs.getString("status_desc"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setExceptionSolve(rs.getString("exception_solve"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.set(rs.getString(""));
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.set(rs.getDate(""));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setAmount(rs.getString("amount"));
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
    public List<B2bOrderPkgBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_pkg ";
            } else {
                sql = "select * from b2b_order_pkg where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderPkgBean>() {
                        @Override
                        public B2bOrderPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bOrderPkgBean bean = new B2bOrderPkgBean();
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.set(rs.getString(""));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setOrderPkgName(rs.getString("order_pkg_name"));
                            bean.setExceptionDesc(rs.getString("exception_desc"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.set(rs.getDate(""));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setUnpaidAmount(rs.getString("unpaid_amount"));
                            bean.set(rs.getString(""));
                            bean.setRemark(rs.getString("remark"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setStatusDesc(rs.getString("status_desc"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setExceptionSolve(rs.getString("exception_solve"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.set(rs.getString(""));
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.set(rs.getDate(""));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setAmount(rs.getString("amount"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderPkgBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_pkg ";
            } else {
                sql = "select * from b2b_order_pkg where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderPkgBean>() {
                        @Override
                        public B2bOrderPkgBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bOrderPkgBean bean = new B2bOrderPkgBean();
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.set(rs.getString(""));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setOrderPkgName(rs.getString("order_pkg_name"));
                            bean.setExceptionDesc(rs.getString("exception_desc"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.set(rs.getDate(""));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setUnpaidAmount(rs.getString("unpaid_amount"));
                            bean.set(rs.getString(""));
                            bean.setRemark(rs.getString("remark"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setStatusDesc(rs.getString("status_desc"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setExceptionSolve(rs.getString("exception_solve"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.set(rs.getString(""));
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.set(rs.getDate(""));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setAmount(rs.getString("amount"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderPkgBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_order_pkg ";
            } else {
                sql = "select count(1) numCount from b2b_order_pkg where " + whereSql;
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
    public int insertSelective(B2bOrderPkgBean b2bOrderPkg) {
        try {
            if (null == b2bOrderPkg) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_pkg(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bOrderPkg.getPayType()) {
                columns.add("pay_type");
                values.add(b2bOrderPkg.getPayType());
            }
            if (null != b2bOrderPkg.getCustomerId()) {
                columns.add("customer_id");
                values.add(b2bOrderPkg.getCustomerId());
            }
            if (null != b2bOrderPkg.getOrderPkgId()) {
                columns.add("order_pkg_id");
                values.add(b2bOrderPkg.getOrderPkgId());
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                values.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getCustomerName()) {
                columns.add("customer_name");
                values.add(b2bOrderPkg.getCustomerName());
            }
            if (null != b2bOrderPkg.getOrderPkgName()) {
                columns.add("order_pkg_name");
                values.add(b2bOrderPkg.getOrderPkgName());
            }
            if (null != b2bOrderPkg.getExceptionDesc()) {
                columns.add("exception_desc");
                values.add(b2bOrderPkg.getExceptionDesc());
            }
            if (null != b2bOrderPkg.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bOrderPkg.getSupplierId());
            }
            if (null != b2bOrderPkg.getStrategyDesc()) {
                columns.add("strategy_desc");
                values.add(b2bOrderPkg.getStrategyDesc());
            }
            if (null != b2bOrderPkg.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bOrderPkg.getTargetClient());
            }
            if (null != b2bOrderPkg.getOrderHeadId()) {
                columns.add("order_head_id");
                values.add(b2bOrderPkg.getOrderHeadId());
            }
            if (null != b2bOrderPkg.getAllDiscount()) {
                columns.add("all_discount");
                values.add(b2bOrderPkg.getAllDiscount());
            }
            if (null != b2bOrderPkg.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bOrderPkg.getSupplierName());
            }
            if (null != b2bOrderPkg.getStorageStatus()) {
                columns.add("storage_status");
                values.add(b2bOrderPkg.getStorageStatus());
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                values.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                values.add(b2bOrderPkg.getOrderPkgCode());
            }
            if (null != b2bOrderPkg.getUnpaidAmount()) {
                columns.add("unpaid_amount");
                values.add(b2bOrderPkg.getUnpaidAmount());
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                values.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getRemark()) {
                columns.add("remark");
                values.add(b2bOrderPkg.getRemark());
            }
            if (null != b2bOrderPkg.getRestName()) {
                columns.add("rest_name");
                values.add(b2bOrderPkg.getRestName());
            }
            if (null != b2bOrderPkg.getCartId()) {
                columns.add("cart_id");
                values.add(b2bOrderPkg.getCartId());
            }
            if (null != b2bOrderPkg.getStatusDesc()) {
                columns.add("status_desc");
                values.add(b2bOrderPkg.getStatusDesc());
            }
            if (null != b2bOrderPkg.getAmountPaid()) {
                columns.add("amount_paid");
                values.add(b2bOrderPkg.getAmountPaid());
            }
            if (null != b2bOrderPkg.getExceptionSolve()) {
                columns.add("exception_solve");
                values.add(b2bOrderPkg.getExceptionSolve());
            }
            if (null != b2bOrderPkg.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                values.add(b2bOrderPkg.getSlsPmtnId());
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                values.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getAllPrivilege()) {
                columns.add("all_privilege");
                values.add(b2bOrderPkg.getAllPrivilege());
            }
            if (null != b2bOrderPkg.getCodelessSum()) {
                columns.add("codeless_sum");
                values.add(b2bOrderPkg.getCodelessSum());
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                values.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getRestId()) {
                columns.add("rest_id");
                values.add(b2bOrderPkg.getRestId());
            }
            if (null != b2bOrderPkg.getAmount()) {
                columns.add("amount");
                values.add(b2bOrderPkg.getAmount());
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
    public int insertSelectiveAndGetId(B2bOrderPkgBean b2bOrderPkg) {
        try {
            if (null == b2bOrderPkg) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_pkg(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bOrderPkg.getPayType()) {
                columns.add("pay_type");
                parameters.add(":payType");
            }
            if (null != b2bOrderPkg.getCustomerId()) {
                columns.add("customer_id");
                parameters.add(":customerId");
            }
            if (null != b2bOrderPkg.getOrderPkgId()) {
                columns.add("order_pkg_id");
                parameters.add(":orderPkgId");
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderPkg.getCustomerName()) {
                columns.add("customer_name");
                parameters.add(":customerName");
            }
            if (null != b2bOrderPkg.getOrderPkgName()) {
                columns.add("order_pkg_name");
                parameters.add(":orderPkgName");
            }
            if (null != b2bOrderPkg.getExceptionDesc()) {
                columns.add("exception_desc");
                parameters.add(":exceptionDesc");
            }
            if (null != b2bOrderPkg.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bOrderPkg.getStrategyDesc()) {
                columns.add("strategy_desc");
                parameters.add(":strategyDesc");
            }
            if (null != b2bOrderPkg.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bOrderPkg.getOrderHeadId()) {
                columns.add("order_head_id");
                parameters.add(":orderHeadId");
            }
            if (null != b2bOrderPkg.getAllDiscount()) {
                columns.add("all_discount");
                parameters.add(":allDiscount");
            }
            if (null != b2bOrderPkg.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bOrderPkg.getStorageStatus()) {
                columns.add("storage_status");
                parameters.add(":storageStatus");
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderPkg.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                parameters.add(":orderPkgCode");
            }
            if (null != b2bOrderPkg.getUnpaidAmount()) {
                columns.add("unpaid_amount");
                parameters.add(":unpaidAmount");
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderPkg.getRemark()) {
                columns.add("remark");
                parameters.add(":remark");
            }
            if (null != b2bOrderPkg.getRestName()) {
                columns.add("rest_name");
                parameters.add(":restName");
            }
            if (null != b2bOrderPkg.getCartId()) {
                columns.add("cart_id");
                parameters.add(":cartId");
            }
            if (null != b2bOrderPkg.getStatusDesc()) {
                columns.add("status_desc");
                parameters.add(":statusDesc");
            }
            if (null != b2bOrderPkg.getAmountPaid()) {
                columns.add("amount_paid");
                parameters.add(":amountPaid");
            }
            if (null != b2bOrderPkg.getExceptionSolve()) {
                columns.add("exception_solve");
                parameters.add(":exceptionSolve");
            }
            if (null != b2bOrderPkg.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                parameters.add(":slsPmtnId");
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderPkg.getAllPrivilege()) {
                columns.add("all_privilege");
                parameters.add(":allPrivilege");
            }
            if (null != b2bOrderPkg.getCodelessSum()) {
                columns.add("codeless_sum");
                parameters.add(":codelessSum");
            }
            if (null != b2bOrderPkg.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderPkg.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bOrderPkg.getAmount()) {
                columns.add("amount");
                parameters.add(":amount");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bOrderPkg);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bOrderPkg.setOrderPkgId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bOrderPkgBean b2bOrderPkg) {
        try {
            if (null == b2bOrderPkg.getOrderPkgId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_order_pkg SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bOrderPkg.getPayType()) {
                updateSql.add("pay_type = ?");
                params.add(b2bOrderPkg.getPayType());
            }
            if (null != b2bOrderPkg.getCustomerId()) {
                updateSql.add("customer_id = ?");
                params.add(b2bOrderPkg.getCustomerId());
            }
            if (null != b2bOrderPkg.getOrderPkgId()) {
                updateSql.add("order_pkg_id = ?");
                params.add(b2bOrderPkg.getOrderPkgId());
            }
            if (null != b2bOrderPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getCustomerName()) {
                updateSql.add("customer_name = ?");
                params.add(b2bOrderPkg.getCustomerName());
            }
            if (null != b2bOrderPkg.getOrderPkgName()) {
                updateSql.add("order_pkg_name = ?");
                params.add(b2bOrderPkg.getOrderPkgName());
            }
            if (null != b2bOrderPkg.getExceptionDesc()) {
                updateSql.add("exception_desc = ?");
                params.add(b2bOrderPkg.getExceptionDesc());
            }
            if (null != b2bOrderPkg.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bOrderPkg.getSupplierId());
            }
            if (null != b2bOrderPkg.getStrategyDesc()) {
                updateSql.add("strategy_desc = ?");
                params.add(b2bOrderPkg.getStrategyDesc());
            }
            if (null != b2bOrderPkg.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bOrderPkg.getTargetClient());
            }
            if (null != b2bOrderPkg.getOrderHeadId()) {
                updateSql.add("order_head_id = ?");
                params.add(b2bOrderPkg.getOrderHeadId());
            }
            if (null != b2bOrderPkg.getAllDiscount()) {
                updateSql.add("all_discount = ?");
                params.add(b2bOrderPkg.getAllDiscount());
            }
            if (null != b2bOrderPkg.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bOrderPkg.getSupplierName());
            }
            if (null != b2bOrderPkg.getStorageStatus()) {
                updateSql.add("storage_status = ?");
                params.add(b2bOrderPkg.getStorageStatus());
            }
            if (null != b2bOrderPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getOrderPkgCode()) {
                updateSql.add("order_pkg_code = ?");
                params.add(b2bOrderPkg.getOrderPkgCode());
            }
            if (null != b2bOrderPkg.getUnpaidAmount()) {
                updateSql.add("unpaid_amount = ?");
                params.add(b2bOrderPkg.getUnpaidAmount());
            }
            if (null != b2bOrderPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getRemark()) {
                updateSql.add("remark = ?");
                params.add(b2bOrderPkg.getRemark());
            }
            if (null != b2bOrderPkg.getRestName()) {
                updateSql.add("rest_name = ?");
                params.add(b2bOrderPkg.getRestName());
            }
            if (null != b2bOrderPkg.getCartId()) {
                updateSql.add("cart_id = ?");
                params.add(b2bOrderPkg.getCartId());
            }
            if (null != b2bOrderPkg.getStatusDesc()) {
                updateSql.add("status_desc = ?");
                params.add(b2bOrderPkg.getStatusDesc());
            }
            if (null != b2bOrderPkg.getAmountPaid()) {
                updateSql.add("amount_paid = ?");
                params.add(b2bOrderPkg.getAmountPaid());
            }
            if (null != b2bOrderPkg.getExceptionSolve()) {
                updateSql.add("exception_solve = ?");
                params.add(b2bOrderPkg.getExceptionSolve());
            }
            if (null != b2bOrderPkg.getSlsPmtnId()) {
                updateSql.add("sls_pmtn_id = ?");
                params.add(b2bOrderPkg.getSlsPmtnId());
            }
            if (null != b2bOrderPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getAllPrivilege()) {
                updateSql.add("all_privilege = ?");
                params.add(b2bOrderPkg.getAllPrivilege());
            }
            if (null != b2bOrderPkg.getCodelessSum()) {
                updateSql.add("codeless_sum = ?");
                params.add(b2bOrderPkg.getCodelessSum());
            }
            if (null != b2bOrderPkg.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderPkg.get());
            }
            if (null != b2bOrderPkg.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bOrderPkg.getRestId());
            }
            if (null != b2bOrderPkg.getAmount()) {
                updateSql.add("amount = ?");
                params.add(b2bOrderPkg.getAmount());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE order_pkg_id = ?");
            params.add(b2bOrderPkg.getOrderPkgId());

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
    public int deleteByPrimaryKey(String orderPkgId) {
            if(null ==  orderPkgId) {
            return 0;
        }

        String sql = "delete from b2b_order_pkg where order_pkg_id  = ?";
        return jdbcTemplate.update(sql, orderPkgId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_order_pkg where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bOrderPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bOrderPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bOrderPkgBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bOrderPkgBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bOrderPkgBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderPkgBean> findAll(Pageable arg0) {
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
	public void delete(B2bOrderPkgBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bOrderPkgBean> arg0) {
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
	public Iterable<B2bOrderPkgBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bOrderPkgBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderPkgBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderPkgBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderPkgBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bOrderPkgBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bOrderPkgBean> findAll(
			Specification<B2bOrderPkgBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderPkgBean> findAll(
			Specification<B2bOrderPkgBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bOrderPkgBean> findAll(
			Specification<B2bOrderPkgBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderPkgBean findOne(
			Specification<B2bOrderPkgBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
