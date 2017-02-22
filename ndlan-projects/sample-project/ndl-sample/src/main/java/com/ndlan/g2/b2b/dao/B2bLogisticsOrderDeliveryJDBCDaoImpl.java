package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bLogisticsOrderDeliveryBean;

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

@Repository("b2bLogisticsOrderDeliveryJDBCDao")
public class B2bLogisticsOrderDeliveryJDBCDaoImpl implements B2bLogisticsOrderDeliveryJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bLogisticsOrderDeliveryBean selectByPrimaryKey(String lgstOrdDlvId) {
        try {
            String sql = "select * from b2b_logistics_order_delivery where lgst_ord_dlv_id = ?";

            List<B2bLogisticsOrderDeliveryBean> resultList = this.jdbcTemplate.query(sql, new Object[]{lgstOrdDlvId},
                    new RowMapper<B2bLogisticsOrderDeliveryBean>() {
                        @Override
                        public B2bLogisticsOrderDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bLogisticsOrderDeliveryBean bean = new B2bLogisticsOrderDeliveryBean();
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.setOrderAmount(rs.getString("order_amount"));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setPostCode(rs.getString("post_code"));
                            bean.setStatus(rs.getString("status"));
                            bean.setOrderDeliveryId(rs.getString("order_delivery_id"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.setLogisticsLineName(rs.getString("logistics_line_name"));
                            bean.setLgstOrdDlvId(rs.getString("lgst_ord_dlv_id"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCustomId(rs.getString("custom_id"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.setRemake(rs.getString("remake"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setLogisticsHeadCode(rs.getString("logistics_head_code"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCustomName(rs.getString("custom_name"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setRegion(rs.getString("region"));
                            Timestamp orderDateTimestamp = rs.getTimestamp("order_date");
                            if (null != orderDateTimestamp) {
                                bean.setOrderDate(new Date(orderDateTimestamp.getTime()));
                            }
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
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
    public List<B2bLogisticsOrderDeliveryBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_logistics_order_delivery ";
            } else {
                sql = "select * from b2b_logistics_order_delivery where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bLogisticsOrderDeliveryBean>() {
                        @Override
                        public B2bLogisticsOrderDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bLogisticsOrderDeliveryBean bean = new B2bLogisticsOrderDeliveryBean();
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.setOrderAmount(rs.getString("order_amount"));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setPostCode(rs.getString("post_code"));
                            bean.setStatus(rs.getString("status"));
                            bean.setOrderDeliveryId(rs.getString("order_delivery_id"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.setLogisticsLineName(rs.getString("logistics_line_name"));
                            bean.setLgstOrdDlvId(rs.getString("lgst_ord_dlv_id"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCustomId(rs.getString("custom_id"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.setRemake(rs.getString("remake"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setLogisticsHeadCode(rs.getString("logistics_head_code"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCustomName(rs.getString("custom_name"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setRegion(rs.getString("region"));
                            Timestamp orderDateTimestamp = rs.getTimestamp("order_date");
                            if (null != orderDateTimestamp) {
                                bean.setOrderDate(new Date(orderDateTimestamp.getTime()));
                            }
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bLogisticsOrderDeliveryBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_logistics_order_delivery ";
            } else {
                sql = "select * from b2b_logistics_order_delivery where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bLogisticsOrderDeliveryBean>() {
                        @Override
                        public B2bLogisticsOrderDeliveryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bLogisticsOrderDeliveryBean bean = new B2bLogisticsOrderDeliveryBean();
                            bean.setReceiveTellcall(rs.getString("receive_tellcall"));
                            bean.setOrderAmount(rs.getString("order_amount"));
                            bean.setDetailAddress(rs.getString("detail_address"));
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setPostCode(rs.getString("post_code"));
                            bean.setStatus(rs.getString("status"));
                            bean.setOrderDeliveryId(rs.getString("order_delivery_id"));
                            bean.setReceiveName(rs.getString("receive_name"));
                            bean.setLogisticsLineName(rs.getString("logistics_line_name"));
                            bean.setLgstOrdDlvId(rs.getString("lgst_ord_dlv_id"));
                            bean.setDeliveryAddressId(rs.getString("delivery_address_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCustomId(rs.getString("custom_id"));
                            bean.setReceivePhone(rs.getString("receive_phone"));
                            bean.setRemake(rs.getString("remake"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setLogisticsHeadCode(rs.getString("logistics_head_code"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCustomName(rs.getString("custom_name"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setRegion(rs.getString("region"));
                            Timestamp orderDateTimestamp = rs.getTimestamp("order_date");
                            if (null != orderDateTimestamp) {
                                bean.setOrderDate(new Date(orderDateTimestamp.getTime()));
                            }
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bLogisticsOrderDeliveryBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_logistics_order_delivery ";
            } else {
                sql = "select count(1) numCount from b2b_logistics_order_delivery where " + whereSql;
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
    public int insertSelective(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        try {
            if (null == b2bLogisticsOrderDelivery) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_logistics_order_delivery(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bLogisticsOrderDelivery.getReceiveTellcall()) {
                columns.add("receive_tellcall");
                values.add(b2bLogisticsOrderDelivery.getReceiveTellcall());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderAmount()) {
                columns.add("order_amount");
                values.add(b2bLogisticsOrderDelivery.getOrderAmount());
            }
            if (null != b2bLogisticsOrderDelivery.getDetailAddress()) {
                columns.add("detail_address");
                values.add(b2bLogisticsOrderDelivery.getDetailAddress());
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsLineId()) {
                columns.add("logistics_line_id");
                values.add(b2bLogisticsOrderDelivery.getLogisticsLineId());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                values.add(b2bLogisticsOrderDelivery.getOrderPkgCode());
            }
            if (null != b2bLogisticsOrderDelivery.getPostCode()) {
                columns.add("post_code");
                values.add(b2bLogisticsOrderDelivery.getPostCode());
            }
            if (null != b2bLogisticsOrderDelivery.getStatus()) {
                columns.add("status");
                values.add(b2bLogisticsOrderDelivery.getStatus());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderDeliveryId()) {
                columns.add("order_delivery_id");
                values.add(b2bLogisticsOrderDelivery.getOrderDeliveryId());
            }
            if (null != b2bLogisticsOrderDelivery.getReceiveName()) {
                columns.add("receive_name");
                values.add(b2bLogisticsOrderDelivery.getReceiveName());
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsLineName()) {
                columns.add("logistics_line_name");
                values.add(b2bLogisticsOrderDelivery.getLogisticsLineName());
            }
            if (null != b2bLogisticsOrderDelivery.getLgstOrdDlvId()) {
                columns.add("lgst_ord_dlv_id");
                values.add(b2bLogisticsOrderDelivery.getLgstOrdDlvId());
            }
            if (null != b2bLogisticsOrderDelivery.getDeliveryAddressId()) {
                columns.add("delivery_address_id");
                values.add(b2bLogisticsOrderDelivery.getDeliveryAddressId());
            }
            if (null != b2bLogisticsOrderDelivery.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bLogisticsOrderDelivery.getSupplierId());
            }
            if (null != b2bLogisticsOrderDelivery.getCustomId()) {
                columns.add("custom_id");
                values.add(b2bLogisticsOrderDelivery.getCustomId());
            }
            if (null != b2bLogisticsOrderDelivery.getReceivePhone()) {
                columns.add("receive_phone");
                values.add(b2bLogisticsOrderDelivery.getReceivePhone());
            }
            if (null != b2bLogisticsOrderDelivery.getRemake()) {
                columns.add("remake");
                values.add(b2bLogisticsOrderDelivery.getRemake());
            }
            if (null != b2bLogisticsOrderDelivery.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bLogisticsOrderDelivery.getCreateTime());
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsHeadCode()) {
                columns.add("logistics_head_code");
                values.add(b2bLogisticsOrderDelivery.getLogisticsHeadCode());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderPkgId()) {
                columns.add("order_pkg_id");
                values.add(b2bLogisticsOrderDelivery.getOrderPkgId());
            }
            if (null != b2bLogisticsOrderDelivery.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bLogisticsOrderDelivery.getCreateBy());
            }
            if (null != b2bLogisticsOrderDelivery.getCustomName()) {
                columns.add("custom_name");
                values.add(b2bLogisticsOrderDelivery.getCustomName());
            }
            if (null != b2bLogisticsOrderDelivery.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bLogisticsOrderDelivery.getUpdateTime());
            }
            if (null != b2bLogisticsOrderDelivery.getRegion()) {
                columns.add("region");
                values.add(b2bLogisticsOrderDelivery.getRegion());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderDate()) {
                columns.add("order_date");
                values.add(b2bLogisticsOrderDelivery.getOrderDate());
            }
            if (null != b2bLogisticsOrderDelivery.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bLogisticsOrderDelivery.getSupplierName());
            }
            if (null != b2bLogisticsOrderDelivery.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bLogisticsOrderDelivery.getUpdateBy());
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
    public int insertSelectiveAndGetId(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        try {
            if (null == b2bLogisticsOrderDelivery) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_logistics_order_delivery(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bLogisticsOrderDelivery.getReceiveTellcall()) {
                columns.add("receive_tellcall");
                parameters.add(":receiveTellcall");
            }
            if (null != b2bLogisticsOrderDelivery.getOrderAmount()) {
                columns.add("order_amount");
                parameters.add(":orderAmount");
            }
            if (null != b2bLogisticsOrderDelivery.getDetailAddress()) {
                columns.add("detail_address");
                parameters.add(":detailAddress");
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsLineId()) {
                columns.add("logistics_line_id");
                parameters.add(":logisticsLineId");
            }
            if (null != b2bLogisticsOrderDelivery.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                parameters.add(":orderPkgCode");
            }
            if (null != b2bLogisticsOrderDelivery.getPostCode()) {
                columns.add("post_code");
                parameters.add(":postCode");
            }
            if (null != b2bLogisticsOrderDelivery.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }
            if (null != b2bLogisticsOrderDelivery.getOrderDeliveryId()) {
                columns.add("order_delivery_id");
                parameters.add(":orderDeliveryId");
            }
            if (null != b2bLogisticsOrderDelivery.getReceiveName()) {
                columns.add("receive_name");
                parameters.add(":receiveName");
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsLineName()) {
                columns.add("logistics_line_name");
                parameters.add(":logisticsLineName");
            }
            if (null != b2bLogisticsOrderDelivery.getLgstOrdDlvId()) {
                columns.add("lgst_ord_dlv_id");
                parameters.add(":lgstOrdDlvId");
            }
            if (null != b2bLogisticsOrderDelivery.getDeliveryAddressId()) {
                columns.add("delivery_address_id");
                parameters.add(":deliveryAddressId");
            }
            if (null != b2bLogisticsOrderDelivery.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bLogisticsOrderDelivery.getCustomId()) {
                columns.add("custom_id");
                parameters.add(":customId");
            }
            if (null != b2bLogisticsOrderDelivery.getReceivePhone()) {
                columns.add("receive_phone");
                parameters.add(":receivePhone");
            }
            if (null != b2bLogisticsOrderDelivery.getRemake()) {
                columns.add("remake");
                parameters.add(":remake");
            }
            if (null != b2bLogisticsOrderDelivery.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsHeadCode()) {
                columns.add("logistics_head_code");
                parameters.add(":logisticsHeadCode");
            }
            if (null != b2bLogisticsOrderDelivery.getOrderPkgId()) {
                columns.add("order_pkg_id");
                parameters.add(":orderPkgId");
            }
            if (null != b2bLogisticsOrderDelivery.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }
            if (null != b2bLogisticsOrderDelivery.getCustomName()) {
                columns.add("custom_name");
                parameters.add(":customName");
            }
            if (null != b2bLogisticsOrderDelivery.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bLogisticsOrderDelivery.getRegion()) {
                columns.add("region");
                parameters.add(":region");
            }
            if (null != b2bLogisticsOrderDelivery.getOrderDate()) {
                columns.add("order_date");
                parameters.add(":orderDate");
            }
            if (null != b2bLogisticsOrderDelivery.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bLogisticsOrderDelivery.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bLogisticsOrderDelivery);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bLogisticsOrderDelivery.setLgstOrdDlvId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        try {
            if (null == b2bLogisticsOrderDelivery.getLgstOrdDlvId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_logistics_order_delivery SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bLogisticsOrderDelivery.getReceiveTellcall()) {
                updateSql.add("receive_tellcall = ?");
                params.add(b2bLogisticsOrderDelivery.getReceiveTellcall());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderAmount()) {
                updateSql.add("order_amount = ?");
                params.add(b2bLogisticsOrderDelivery.getOrderAmount());
            }
            if (null != b2bLogisticsOrderDelivery.getDetailAddress()) {
                updateSql.add("detail_address = ?");
                params.add(b2bLogisticsOrderDelivery.getDetailAddress());
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsLineId()) {
                updateSql.add("logistics_line_id = ?");
                params.add(b2bLogisticsOrderDelivery.getLogisticsLineId());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderPkgCode()) {
                updateSql.add("order_pkg_code = ?");
                params.add(b2bLogisticsOrderDelivery.getOrderPkgCode());
            }
            if (null != b2bLogisticsOrderDelivery.getPostCode()) {
                updateSql.add("post_code = ?");
                params.add(b2bLogisticsOrderDelivery.getPostCode());
            }
            if (null != b2bLogisticsOrderDelivery.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bLogisticsOrderDelivery.getStatus());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderDeliveryId()) {
                updateSql.add("order_delivery_id = ?");
                params.add(b2bLogisticsOrderDelivery.getOrderDeliveryId());
            }
            if (null != b2bLogisticsOrderDelivery.getReceiveName()) {
                updateSql.add("receive_name = ?");
                params.add(b2bLogisticsOrderDelivery.getReceiveName());
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsLineName()) {
                updateSql.add("logistics_line_name = ?");
                params.add(b2bLogisticsOrderDelivery.getLogisticsLineName());
            }
            if (null != b2bLogisticsOrderDelivery.getLgstOrdDlvId()) {
                updateSql.add("lgst_ord_dlv_id = ?");
                params.add(b2bLogisticsOrderDelivery.getLgstOrdDlvId());
            }
            if (null != b2bLogisticsOrderDelivery.getDeliveryAddressId()) {
                updateSql.add("delivery_address_id = ?");
                params.add(b2bLogisticsOrderDelivery.getDeliveryAddressId());
            }
            if (null != b2bLogisticsOrderDelivery.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bLogisticsOrderDelivery.getSupplierId());
            }
            if (null != b2bLogisticsOrderDelivery.getCustomId()) {
                updateSql.add("custom_id = ?");
                params.add(b2bLogisticsOrderDelivery.getCustomId());
            }
            if (null != b2bLogisticsOrderDelivery.getReceivePhone()) {
                updateSql.add("receive_phone = ?");
                params.add(b2bLogisticsOrderDelivery.getReceivePhone());
            }
            if (null != b2bLogisticsOrderDelivery.getRemake()) {
                updateSql.add("remake = ?");
                params.add(b2bLogisticsOrderDelivery.getRemake());
            }
            if (null != b2bLogisticsOrderDelivery.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bLogisticsOrderDelivery.getCreateTime());
            }
            if (null != b2bLogisticsOrderDelivery.getLogisticsHeadCode()) {
                updateSql.add("logistics_head_code = ?");
                params.add(b2bLogisticsOrderDelivery.getLogisticsHeadCode());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderPkgId()) {
                updateSql.add("order_pkg_id = ?");
                params.add(b2bLogisticsOrderDelivery.getOrderPkgId());
            }
            if (null != b2bLogisticsOrderDelivery.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bLogisticsOrderDelivery.getCreateBy());
            }
            if (null != b2bLogisticsOrderDelivery.getCustomName()) {
                updateSql.add("custom_name = ?");
                params.add(b2bLogisticsOrderDelivery.getCustomName());
            }
            if (null != b2bLogisticsOrderDelivery.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bLogisticsOrderDelivery.getUpdateTime());
            }
            if (null != b2bLogisticsOrderDelivery.getRegion()) {
                updateSql.add("region = ?");
                params.add(b2bLogisticsOrderDelivery.getRegion());
            }
            if (null != b2bLogisticsOrderDelivery.getOrderDate()) {
                updateSql.add("order_date = ?");
                params.add(b2bLogisticsOrderDelivery.getOrderDate());
            }
            if (null != b2bLogisticsOrderDelivery.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bLogisticsOrderDelivery.getSupplierName());
            }
            if (null != b2bLogisticsOrderDelivery.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bLogisticsOrderDelivery.getUpdateBy());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE lgst_ord_dlv_id = ?");
            params.add(b2bLogisticsOrderDelivery.getLgstOrdDlvId());

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
    public int deleteByPrimaryKey(String lgstOrdDlvId) {
            if(null ==  lgstOrdDlvId) {
            return 0;
        }

        String sql = "delete from b2b_logistics_order_delivery where lgst_ord_dlv_id  = ?";
        return jdbcTemplate.update(sql, lgstOrdDlvId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_logistics_order_delivery where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bLogisticsOrderDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bLogisticsOrderDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bLogisticsOrderDeliveryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bLogisticsOrderDeliveryBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bLogisticsOrderDeliveryBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bLogisticsOrderDeliveryBean> findAll(Pageable arg0) {
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
	public void delete(B2bLogisticsOrderDeliveryBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bLogisticsOrderDeliveryBean> arg0) {
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
	public Iterable<B2bLogisticsOrderDeliveryBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bLogisticsOrderDeliveryBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bLogisticsOrderDeliveryBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bLogisticsOrderDeliveryBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bLogisticsOrderDeliveryBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bLogisticsOrderDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bLogisticsOrderDeliveryBean> findAll(
			Specification<B2bLogisticsOrderDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bLogisticsOrderDeliveryBean> findAll(
			Specification<B2bLogisticsOrderDeliveryBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bLogisticsOrderDeliveryBean> findAll(
			Specification<B2bLogisticsOrderDeliveryBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bLogisticsOrderDeliveryBean findOne(
			Specification<B2bLogisticsOrderDeliveryBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
