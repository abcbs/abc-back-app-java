package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bOrderRmtDetailBean;

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

@Repository("b2bOrderRmtDetailJDBCDao")
public class B2bOrderRmtDetailJDBCDaoImpl implements B2bOrderRmtDetailJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bOrderRmtDetailBean selectByPrimaryKey(String orderRmtDetailId) {
        try {
            String sql = "select * from b2b_order_rmt_detail where order_rmt_detail_id = ?";

            List<B2bOrderRmtDetailBean> resultList = this.jdbcTemplate.query(sql, new Object[]{orderRmtDetailId},
                    new RowMapper<B2bOrderRmtDetailBean>() {
                        @Override
                        public B2bOrderRmtDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bOrderRmtDetailBean bean = new B2bOrderRmtDetailBean();
                            bean.setOrderRmtDetailId(rs.getString("order_rmt_detail_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setOrderDetailId(rs.getString("order_detail_id"));
                            bean.setPic(rs.getString("pic"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setSize(rs.getString("size"));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setOrderRmtDetailCode(rs.getString("order_rmt_detail_code"));
                            bean.setOrderRmtHeadId(rs.getString("order_rmt_head_id"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setOrderRmtPkgId(rs.getString("order_rmt_pkg_id"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setProCode(rs.getString("pro_code"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setDamageSpec(rs.getString("damage_spec"));
                            bean.setOrderDetailNo(rs.getString("order_detail_no"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            bean.setStatus(rs.getString("status"));
                            bean.setRestId(rs.getString("rest_id"));
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
    public List<B2bOrderRmtDetailBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_rmt_detail ";
            } else {
                sql = "select * from b2b_order_rmt_detail where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderRmtDetailBean>() {
                        @Override
                        public B2bOrderRmtDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bOrderRmtDetailBean bean = new B2bOrderRmtDetailBean();
                            bean.setOrderRmtDetailId(rs.getString("order_rmt_detail_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setOrderDetailId(rs.getString("order_detail_id"));
                            bean.setPic(rs.getString("pic"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setSize(rs.getString("size"));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setOrderRmtDetailCode(rs.getString("order_rmt_detail_code"));
                            bean.setOrderRmtHeadId(rs.getString("order_rmt_head_id"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setOrderRmtPkgId(rs.getString("order_rmt_pkg_id"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setProCode(rs.getString("pro_code"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setDamageSpec(rs.getString("damage_spec"));
                            bean.setOrderDetailNo(rs.getString("order_detail_no"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            bean.setStatus(rs.getString("status"));
                            bean.setRestId(rs.getString("rest_id"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderRmtDetailBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_rmt_detail ";
            } else {
                sql = "select * from b2b_order_rmt_detail where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderRmtDetailBean>() {
                        @Override
                        public B2bOrderRmtDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bOrderRmtDetailBean bean = new B2bOrderRmtDetailBean();
                            bean.setOrderRmtDetailId(rs.getString("order_rmt_detail_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setOrderDetailId(rs.getString("order_detail_id"));
                            bean.setPic(rs.getString("pic"));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setSize(rs.getString("size"));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setOrderRmtDetailCode(rs.getString("order_rmt_detail_code"));
                            bean.setOrderRmtHeadId(rs.getString("order_rmt_head_id"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setOrderRmtPkgId(rs.getString("order_rmt_pkg_id"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setRemake(rs.getString("remake"));
                            bean.setProCode(rs.getString("pro_code"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setDamageSpec(rs.getString("damage_spec"));
                            bean.setOrderDetailNo(rs.getString("order_detail_no"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            bean.setStatus(rs.getString("status"));
                            bean.setRestId(rs.getString("rest_id"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderRmtDetailBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_order_rmt_detail ";
            } else {
                sql = "select count(1) numCount from b2b_order_rmt_detail where " + whereSql;
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
    public int insertSelective(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        try {
            if (null == b2bOrderRmtDetail) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_rmt_detail(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bOrderRmtDetail.getOrderRmtDetailId()) {
                columns.add("order_rmt_detail_id");
                values.add(b2bOrderRmtDetail.getOrderRmtDetailId());
            }
            if (null != b2bOrderRmtDetail.getProDesc()) {
                columns.add("pro_desc");
                values.add(b2bOrderRmtDetail.getProDesc());
            }
            if (null != b2bOrderRmtDetail.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bOrderRmtDetail.getCreateTime());
            }
            if (null != b2bOrderRmtDetail.getRestName()) {
                columns.add("rest_name");
                values.add(b2bOrderRmtDetail.getRestName());
            }
            if (null != b2bOrderRmtDetail.getOrderPkgId()) {
                columns.add("order_pkg_id");
                values.add(b2bOrderRmtDetail.getOrderPkgId());
            }
            if (null != b2bOrderRmtDetail.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bOrderRmtDetail.getSupplierId());
            }
            if (null != b2bOrderRmtDetail.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bOrderRmtDetail.getCreateBy());
            }
            if (null != b2bOrderRmtDetail.getOrderDetailId()) {
                columns.add("order_detail_id");
                values.add(b2bOrderRmtDetail.getOrderDetailId());
            }
            if (null != b2bOrderRmtDetail.getPic()) {
                columns.add("pic");
                values.add(b2bOrderRmtDetail.getPic());
            }
            if (null != b2bOrderRmtDetail.getCustomerId()) {
                columns.add("customer_id");
                values.add(b2bOrderRmtDetail.getCustomerId());
            }
            if (null != b2bOrderRmtDetail.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bOrderRmtDetail.getSpecsId());
            }
            if (null != b2bOrderRmtDetail.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bOrderRmtDetail.getCategoryName());
            }
            if (null != b2bOrderRmtDetail.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bOrderRmtDetail.getUpdateBy());
            }
            if (null != b2bOrderRmtDetail.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bOrderRmtDetail.getBaseProNo());
            }
            if (null != b2bOrderRmtDetail.getCustomerName()) {
                columns.add("customer_name");
                values.add(b2bOrderRmtDetail.getCustomerName());
            }
            if (null != b2bOrderRmtDetail.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bOrderRmtDetail.getSupplierName());
            }
            if (null != b2bOrderRmtDetail.getParentIdPath()) {
                columns.add("parent_id_path");
                values.add(b2bOrderRmtDetail.getParentIdPath());
            }
            if (null != b2bOrderRmtDetail.getSize()) {
                columns.add("size");
                values.add(b2bOrderRmtDetail.getSize());
            }
            if (null != b2bOrderRmtDetail.getSeriesId()) {
                columns.add("series_id");
                values.add(b2bOrderRmtDetail.getSeriesId());
            }
            if (null != b2bOrderRmtDetail.getVolume()) {
                columns.add("volume");
                values.add(b2bOrderRmtDetail.getVolume());
            }
            if (null != b2bOrderRmtDetail.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                values.add(b2bOrderRmtDetail.getOrderPkgCode());
            }
            if (null != b2bOrderRmtDetail.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bOrderRmtDetail.getUpdateTime());
            }
            if (null != b2bOrderRmtDetail.getOrderRmtDetailCode()) {
                columns.add("order_rmt_detail_code");
                values.add(b2bOrderRmtDetail.getOrderRmtDetailCode());
            }
            if (null != b2bOrderRmtDetail.getOrderRmtHeadId()) {
                columns.add("order_rmt_head_id");
                values.add(b2bOrderRmtDetail.getOrderRmtHeadId());
            }
            if (null != b2bOrderRmtDetail.getProName()) {
                columns.add("pro_name");
                values.add(b2bOrderRmtDetail.getProName());
            }
            if (null != b2bOrderRmtDetail.getParentNamePath()) {
                columns.add("parent_name_path");
                values.add(b2bOrderRmtDetail.getParentNamePath());
            }
            if (null != b2bOrderRmtDetail.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bOrderRmtDetail.getCategoryId());
            }
            if (null != b2bOrderRmtDetail.getOrderRmtPkgId()) {
                columns.add("order_rmt_pkg_id");
                values.add(b2bOrderRmtDetail.getOrderRmtPkgId());
            }
            if (null != b2bOrderRmtDetail.getBarCode()) {
                columns.add("bar_code");
                values.add(b2bOrderRmtDetail.getBarCode());
            }
            if (null != b2bOrderRmtDetail.getCapacity()) {
                columns.add("capacity");
                values.add(b2bOrderRmtDetail.getCapacity());
            }
            if (null != b2bOrderRmtDetail.getSeriesName()) {
                columns.add("series_name");
                values.add(b2bOrderRmtDetail.getSeriesName());
            }
            if (null != b2bOrderRmtDetail.getOrderHeadId()) {
                columns.add("order_head_id");
                values.add(b2bOrderRmtDetail.getOrderHeadId());
            }
            if (null != b2bOrderRmtDetail.getRemake()) {
                columns.add("remake");
                values.add(b2bOrderRmtDetail.getRemake());
            }
            if (null != b2bOrderRmtDetail.getProCode()) {
                columns.add("pro_code");
                values.add(b2bOrderRmtDetail.getProCode());
            }
            if (null != b2bOrderRmtDetail.getQuantity()) {
                columns.add("quantity");
                values.add(b2bOrderRmtDetail.getQuantity());
            }
            if (null != b2bOrderRmtDetail.getDamageSpec()) {
                columns.add("damage_spec");
                values.add(b2bOrderRmtDetail.getDamageSpec());
            }
            if (null != b2bOrderRmtDetail.getOrderDetailNo()) {
                columns.add("order_detail_no");
                values.add(b2bOrderRmtDetail.getOrderDetailNo());
            }
            if (null != b2bOrderRmtDetail.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bOrderRmtDetail.getProColorNo());
            }
            if (null != b2bOrderRmtDetail.getStorageStatus()) {
                columns.add("storage_status");
                values.add(b2bOrderRmtDetail.getStorageStatus());
            }
            if (null != b2bOrderRmtDetail.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bOrderRmtDetail.getBaseProId());
            }
            if (null != b2bOrderRmtDetail.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bOrderRmtDetail.getSpecsName());
            }
            if (null != b2bOrderRmtDetail.getOrignStatus()) {
                columns.add("orign_status");
                values.add(b2bOrderRmtDetail.getOrignStatus());
            }
            if (null != b2bOrderRmtDetail.getStatus()) {
                columns.add("status");
                values.add(b2bOrderRmtDetail.getStatus());
            }
            if (null != b2bOrderRmtDetail.getRestId()) {
                columns.add("rest_id");
                values.add(b2bOrderRmtDetail.getRestId());
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
    public int insertSelectiveAndGetId(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        try {
            if (null == b2bOrderRmtDetail) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_rmt_detail(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bOrderRmtDetail.getOrderRmtDetailId()) {
                columns.add("order_rmt_detail_id");
                parameters.add(":orderRmtDetailId");
            }
            if (null != b2bOrderRmtDetail.getProDesc()) {
                columns.add("pro_desc");
                parameters.add(":proDesc");
            }
            if (null != b2bOrderRmtDetail.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bOrderRmtDetail.getRestName()) {
                columns.add("rest_name");
                parameters.add(":restName");
            }
            if (null != b2bOrderRmtDetail.getOrderPkgId()) {
                columns.add("order_pkg_id");
                parameters.add(":orderPkgId");
            }
            if (null != b2bOrderRmtDetail.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bOrderRmtDetail.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }
            if (null != b2bOrderRmtDetail.getOrderDetailId()) {
                columns.add("order_detail_id");
                parameters.add(":orderDetailId");
            }
            if (null != b2bOrderRmtDetail.getPic()) {
                columns.add("pic");
                parameters.add(":pic");
            }
            if (null != b2bOrderRmtDetail.getCustomerId()) {
                columns.add("customer_id");
                parameters.add(":customerId");
            }
            if (null != b2bOrderRmtDetail.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bOrderRmtDetail.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bOrderRmtDetail.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bOrderRmtDetail.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bOrderRmtDetail.getCustomerName()) {
                columns.add("customer_name");
                parameters.add(":customerName");
            }
            if (null != b2bOrderRmtDetail.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bOrderRmtDetail.getParentIdPath()) {
                columns.add("parent_id_path");
                parameters.add(":parentIdPath");
            }
            if (null != b2bOrderRmtDetail.getSize()) {
                columns.add("size");
                parameters.add(":size");
            }
            if (null != b2bOrderRmtDetail.getSeriesId()) {
                columns.add("series_id");
                parameters.add(":seriesId");
            }
            if (null != b2bOrderRmtDetail.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bOrderRmtDetail.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                parameters.add(":orderPkgCode");
            }
            if (null != b2bOrderRmtDetail.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bOrderRmtDetail.getOrderRmtDetailCode()) {
                columns.add("order_rmt_detail_code");
                parameters.add(":orderRmtDetailCode");
            }
            if (null != b2bOrderRmtDetail.getOrderRmtHeadId()) {
                columns.add("order_rmt_head_id");
                parameters.add(":orderRmtHeadId");
            }
            if (null != b2bOrderRmtDetail.getProName()) {
                columns.add("pro_name");
                parameters.add(":proName");
            }
            if (null != b2bOrderRmtDetail.getParentNamePath()) {
                columns.add("parent_name_path");
                parameters.add(":parentNamePath");
            }
            if (null != b2bOrderRmtDetail.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bOrderRmtDetail.getOrderRmtPkgId()) {
                columns.add("order_rmt_pkg_id");
                parameters.add(":orderRmtPkgId");
            }
            if (null != b2bOrderRmtDetail.getBarCode()) {
                columns.add("bar_code");
                parameters.add(":barCode");
            }
            if (null != b2bOrderRmtDetail.getCapacity()) {
                columns.add("capacity");
                parameters.add(":capacity");
            }
            if (null != b2bOrderRmtDetail.getSeriesName()) {
                columns.add("series_name");
                parameters.add(":seriesName");
            }
            if (null != b2bOrderRmtDetail.getOrderHeadId()) {
                columns.add("order_head_id");
                parameters.add(":orderHeadId");
            }
            if (null != b2bOrderRmtDetail.getRemake()) {
                columns.add("remake");
                parameters.add(":remake");
            }
            if (null != b2bOrderRmtDetail.getProCode()) {
                columns.add("pro_code");
                parameters.add(":proCode");
            }
            if (null != b2bOrderRmtDetail.getQuantity()) {
                columns.add("quantity");
                parameters.add(":quantity");
            }
            if (null != b2bOrderRmtDetail.getDamageSpec()) {
                columns.add("damage_spec");
                parameters.add(":damageSpec");
            }
            if (null != b2bOrderRmtDetail.getOrderDetailNo()) {
                columns.add("order_detail_no");
                parameters.add(":orderDetailNo");
            }
            if (null != b2bOrderRmtDetail.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bOrderRmtDetail.getStorageStatus()) {
                columns.add("storage_status");
                parameters.add(":storageStatus");
            }
            if (null != b2bOrderRmtDetail.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bOrderRmtDetail.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }
            if (null != b2bOrderRmtDetail.getOrignStatus()) {
                columns.add("orign_status");
                parameters.add(":orignStatus");
            }
            if (null != b2bOrderRmtDetail.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }
            if (null != b2bOrderRmtDetail.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bOrderRmtDetail);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bOrderRmtDetail.setOrderRmtDetailId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        try {
            if (null == b2bOrderRmtDetail.getOrderRmtDetailId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_order_rmt_detail SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bOrderRmtDetail.getOrderRmtDetailId()) {
                updateSql.add("order_rmt_detail_id = ?");
                params.add(b2bOrderRmtDetail.getOrderRmtDetailId());
            }
            if (null != b2bOrderRmtDetail.getProDesc()) {
                updateSql.add("pro_desc = ?");
                params.add(b2bOrderRmtDetail.getProDesc());
            }
            if (null != b2bOrderRmtDetail.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bOrderRmtDetail.getCreateTime());
            }
            if (null != b2bOrderRmtDetail.getRestName()) {
                updateSql.add("rest_name = ?");
                params.add(b2bOrderRmtDetail.getRestName());
            }
            if (null != b2bOrderRmtDetail.getOrderPkgId()) {
                updateSql.add("order_pkg_id = ?");
                params.add(b2bOrderRmtDetail.getOrderPkgId());
            }
            if (null != b2bOrderRmtDetail.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bOrderRmtDetail.getSupplierId());
            }
            if (null != b2bOrderRmtDetail.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bOrderRmtDetail.getCreateBy());
            }
            if (null != b2bOrderRmtDetail.getOrderDetailId()) {
                updateSql.add("order_detail_id = ?");
                params.add(b2bOrderRmtDetail.getOrderDetailId());
            }
            if (null != b2bOrderRmtDetail.getPic()) {
                updateSql.add("pic = ?");
                params.add(b2bOrderRmtDetail.getPic());
            }
            if (null != b2bOrderRmtDetail.getCustomerId()) {
                updateSql.add("customer_id = ?");
                params.add(b2bOrderRmtDetail.getCustomerId());
            }
            if (null != b2bOrderRmtDetail.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bOrderRmtDetail.getSpecsId());
            }
            if (null != b2bOrderRmtDetail.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bOrderRmtDetail.getCategoryName());
            }
            if (null != b2bOrderRmtDetail.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bOrderRmtDetail.getUpdateBy());
            }
            if (null != b2bOrderRmtDetail.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bOrderRmtDetail.getBaseProNo());
            }
            if (null != b2bOrderRmtDetail.getCustomerName()) {
                updateSql.add("customer_name = ?");
                params.add(b2bOrderRmtDetail.getCustomerName());
            }
            if (null != b2bOrderRmtDetail.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bOrderRmtDetail.getSupplierName());
            }
            if (null != b2bOrderRmtDetail.getParentIdPath()) {
                updateSql.add("parent_id_path = ?");
                params.add(b2bOrderRmtDetail.getParentIdPath());
            }
            if (null != b2bOrderRmtDetail.getSize()) {
                updateSql.add("size = ?");
                params.add(b2bOrderRmtDetail.getSize());
            }
            if (null != b2bOrderRmtDetail.getSeriesId()) {
                updateSql.add("series_id = ?");
                params.add(b2bOrderRmtDetail.getSeriesId());
            }
            if (null != b2bOrderRmtDetail.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bOrderRmtDetail.getVolume());
            }
            if (null != b2bOrderRmtDetail.getOrderPkgCode()) {
                updateSql.add("order_pkg_code = ?");
                params.add(b2bOrderRmtDetail.getOrderPkgCode());
            }
            if (null != b2bOrderRmtDetail.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bOrderRmtDetail.getUpdateTime());
            }
            if (null != b2bOrderRmtDetail.getOrderRmtDetailCode()) {
                updateSql.add("order_rmt_detail_code = ?");
                params.add(b2bOrderRmtDetail.getOrderRmtDetailCode());
            }
            if (null != b2bOrderRmtDetail.getOrderRmtHeadId()) {
                updateSql.add("order_rmt_head_id = ?");
                params.add(b2bOrderRmtDetail.getOrderRmtHeadId());
            }
            if (null != b2bOrderRmtDetail.getProName()) {
                updateSql.add("pro_name = ?");
                params.add(b2bOrderRmtDetail.getProName());
            }
            if (null != b2bOrderRmtDetail.getParentNamePath()) {
                updateSql.add("parent_name_path = ?");
                params.add(b2bOrderRmtDetail.getParentNamePath());
            }
            if (null != b2bOrderRmtDetail.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bOrderRmtDetail.getCategoryId());
            }
            if (null != b2bOrderRmtDetail.getOrderRmtPkgId()) {
                updateSql.add("order_rmt_pkg_id = ?");
                params.add(b2bOrderRmtDetail.getOrderRmtPkgId());
            }
            if (null != b2bOrderRmtDetail.getBarCode()) {
                updateSql.add("bar_code = ?");
                params.add(b2bOrderRmtDetail.getBarCode());
            }
            if (null != b2bOrderRmtDetail.getCapacity()) {
                updateSql.add("capacity = ?");
                params.add(b2bOrderRmtDetail.getCapacity());
            }
            if (null != b2bOrderRmtDetail.getSeriesName()) {
                updateSql.add("series_name = ?");
                params.add(b2bOrderRmtDetail.getSeriesName());
            }
            if (null != b2bOrderRmtDetail.getOrderHeadId()) {
                updateSql.add("order_head_id = ?");
                params.add(b2bOrderRmtDetail.getOrderHeadId());
            }
            if (null != b2bOrderRmtDetail.getRemake()) {
                updateSql.add("remake = ?");
                params.add(b2bOrderRmtDetail.getRemake());
            }
            if (null != b2bOrderRmtDetail.getProCode()) {
                updateSql.add("pro_code = ?");
                params.add(b2bOrderRmtDetail.getProCode());
            }
            if (null != b2bOrderRmtDetail.getQuantity()) {
                updateSql.add("quantity = ?");
                params.add(b2bOrderRmtDetail.getQuantity());
            }
            if (null != b2bOrderRmtDetail.getDamageSpec()) {
                updateSql.add("damage_spec = ?");
                params.add(b2bOrderRmtDetail.getDamageSpec());
            }
            if (null != b2bOrderRmtDetail.getOrderDetailNo()) {
                updateSql.add("order_detail_no = ?");
                params.add(b2bOrderRmtDetail.getOrderDetailNo());
            }
            if (null != b2bOrderRmtDetail.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bOrderRmtDetail.getProColorNo());
            }
            if (null != b2bOrderRmtDetail.getStorageStatus()) {
                updateSql.add("storage_status = ?");
                params.add(b2bOrderRmtDetail.getStorageStatus());
            }
            if (null != b2bOrderRmtDetail.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bOrderRmtDetail.getBaseProId());
            }
            if (null != b2bOrderRmtDetail.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bOrderRmtDetail.getSpecsName());
            }
            if (null != b2bOrderRmtDetail.getOrignStatus()) {
                updateSql.add("orign_status = ?");
                params.add(b2bOrderRmtDetail.getOrignStatus());
            }
            if (null != b2bOrderRmtDetail.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bOrderRmtDetail.getStatus());
            }
            if (null != b2bOrderRmtDetail.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bOrderRmtDetail.getRestId());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE order_rmt_detail_id = ?");
            params.add(b2bOrderRmtDetail.getOrderRmtDetailId());

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
    public int deleteByPrimaryKey(String orderRmtDetailId) {
            if(null ==  orderRmtDetailId) {
            return 0;
        }

        String sql = "delete from b2b_order_rmt_detail where order_rmt_detail_id  = ?";
        return jdbcTemplate.update(sql, orderRmtDetailId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_order_rmt_detail where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bOrderRmtDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bOrderRmtDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bOrderRmtDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bOrderRmtDetailBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bOrderRmtDetailBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderRmtDetailBean> findAll(Pageable arg0) {
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
	public void delete(B2bOrderRmtDetailBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bOrderRmtDetailBean> arg0) {
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
	public Iterable<B2bOrderRmtDetailBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bOrderRmtDetailBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderRmtDetailBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderRmtDetailBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderRmtDetailBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bOrderRmtDetailBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bOrderRmtDetailBean> findAll(
			Specification<B2bOrderRmtDetailBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderRmtDetailBean> findAll(
			Specification<B2bOrderRmtDetailBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bOrderRmtDetailBean> findAll(
			Specification<B2bOrderRmtDetailBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderRmtDetailBean findOne(
			Specification<B2bOrderRmtDetailBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
