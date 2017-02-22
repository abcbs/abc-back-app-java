package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bOrderItemBean;

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

@Repository("b2bOrderItemDao")
public class B2bOrderItemDaoImpl implements B2bOrderItemDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bOrderItemBean selectByPrimaryKey(String bdId) {
        try {
            String sql = "select * from b2b_order_item where bd_id = ?";

            List<B2bOrderItemBean> resultList = this.jdbcTemplate.query(sql, new Object[]{bdId},
                    new RowMapper<B2bOrderItemBean>() {
                        @Override
                        public B2bOrderItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bOrderItemBean bean = new B2bOrderItemBean();
                            bean.setPrice(rs.getString("price"));
                            bean.setBId(rs.getString("b_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setRmtDetailId(rs.getString("rmt_detail_id"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setNumberOfBroken(rs.getString("number_of_broken"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setPriceAgencyName(rs.getString("price_agency_name"));
                            bean.set(rs.getString(""));
                            bean.setPrivilege(rs.getString("privilege"));
                            bean.setPic(rs.getString("pic"));
                            bean.setIsCodeless(rs.getString("is_codeless"));
                            bean.set(rs.getDate(""));
                            bean.setRmtPkgId(rs.getString("rmt_pkg_id"));
                            bean.setBdNo(rs.getString("bd_no"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setRmtStatus(rs.getString("rmt_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setSize(rs.getString("size"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.setPriceAgencyId(rs.getString("price_agency_id"));
                            bean.setSubtotal(rs.getString("subtotal"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setCartItemId(rs.getString("cart_item_id"));
                            bean.setDamageBdId(rs.getString("damage_bd_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setBdId(rs.getString("bd_id"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            bean.setBarCode(rs.getString("bar_code"));
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
    public List<B2bOrderItemBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_item ";
            } else {
                sql = "select * from b2b_order_item where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderItemBean>() {
                        @Override
                        public B2bOrderItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bOrderItemBean bean = new B2bOrderItemBean();
                            bean.setPrice(rs.getString("price"));
                            bean.setBId(rs.getString("b_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setRmtDetailId(rs.getString("rmt_detail_id"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setNumberOfBroken(rs.getString("number_of_broken"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setPriceAgencyName(rs.getString("price_agency_name"));
                            bean.set(rs.getString(""));
                            bean.setPrivilege(rs.getString("privilege"));
                            bean.setPic(rs.getString("pic"));
                            bean.setIsCodeless(rs.getString("is_codeless"));
                            bean.set(rs.getDate(""));
                            bean.setRmtPkgId(rs.getString("rmt_pkg_id"));
                            bean.setBdNo(rs.getString("bd_no"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setRmtStatus(rs.getString("rmt_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setSize(rs.getString("size"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.setPriceAgencyId(rs.getString("price_agency_id"));
                            bean.setSubtotal(rs.getString("subtotal"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setCartItemId(rs.getString("cart_item_id"));
                            bean.setDamageBdId(rs.getString("damage_bd_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setBdId(rs.getString("bd_id"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            bean.setBarCode(rs.getString("bar_code"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderItemBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_item ";
            } else {
                sql = "select * from b2b_order_item where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderItemBean>() {
                        @Override
                        public B2bOrderItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bOrderItemBean bean = new B2bOrderItemBean();
                            bean.setPrice(rs.getString("price"));
                            bean.setBId(rs.getString("b_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setRmtDetailId(rs.getString("rmt_detail_id"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setNumberOfBroken(rs.getString("number_of_broken"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setPriceAgencyName(rs.getString("price_agency_name"));
                            bean.set(rs.getString(""));
                            bean.setPrivilege(rs.getString("privilege"));
                            bean.setPic(rs.getString("pic"));
                            bean.setIsCodeless(rs.getString("is_codeless"));
                            bean.set(rs.getDate(""));
                            bean.setRmtPkgId(rs.getString("rmt_pkg_id"));
                            bean.setBdNo(rs.getString("bd_no"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setRmtStatus(rs.getString("rmt_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.setSize(rs.getString("size"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.setPriceAgencyId(rs.getString("price_agency_id"));
                            bean.setSubtotal(rs.getString("subtotal"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setCartItemId(rs.getString("cart_item_id"));
                            bean.setDamageBdId(rs.getString("damage_bd_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setBdId(rs.getString("bd_id"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStorageStatus(rs.getString("storage_status"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            bean.setBarCode(rs.getString("bar_code"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderItemBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_order_item ";
            } else {
                sql = "select count(1) numCount from b2b_order_item where " + whereSql;
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
    public int insertSelective(B2bOrderItemBean b2bOrderItem) {
        try {
            if (null == b2bOrderItem) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_item(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bOrderItem.getPrice()) {
                columns.add("price");
                values.add(b2bOrderItem.getPrice());
            }
            if (null != b2bOrderItem.getBId()) {
                columns.add("b_id");
                values.add(b2bOrderItem.getBId());
            }
            if (null != b2bOrderItem.getProDesc()) {
                columns.add("pro_desc");
                values.add(b2bOrderItem.getProDesc());
            }
            if (null != b2bOrderItem.getRmtDetailId()) {
                columns.add("rmt_detail_id");
                values.add(b2bOrderItem.getRmtDetailId());
            }
            if (null != b2bOrderItem.getQuantity()) {
                columns.add("quantity");
                values.add(b2bOrderItem.getQuantity());
            }
            if (null != b2bOrderItem.getVolume()) {
                columns.add("volume");
                values.add(b2bOrderItem.getVolume());
            }
            if (null != b2bOrderItem.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                values.add(b2bOrderItem.getOrderPkgCode());
            }
            if (null != b2bOrderItem.getNumberOfBroken()) {
                columns.add("number_of_broken");
                values.add(b2bOrderItem.getNumberOfBroken());
            }
            if (null != b2bOrderItem.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bOrderItem.getSupplierId());
            }
            if (null != b2bOrderItem.getPriceAgencyName()) {
                columns.add("price_agency_name");
                values.add(b2bOrderItem.getPriceAgencyName());
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                values.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.getPrivilege()) {
                columns.add("privilege");
                values.add(b2bOrderItem.getPrivilege());
            }
            if (null != b2bOrderItem.getPic()) {
                columns.add("pic");
                values.add(b2bOrderItem.getPic());
            }
            if (null != b2bOrderItem.getIsCodeless()) {
                columns.add("is_codeless");
                values.add(b2bOrderItem.getIsCodeless());
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                values.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.getRmtPkgId()) {
                columns.add("rmt_pkg_id");
                values.add(b2bOrderItem.getRmtPkgId());
            }
            if (null != b2bOrderItem.getBdNo()) {
                columns.add("bd_no");
                values.add(b2bOrderItem.getBdNo());
            }
            if (null != b2bOrderItem.getStartPointQyt()) {
                columns.add("start_point_qyt");
                values.add(b2bOrderItem.getStartPointQyt());
            }
            if (null != b2bOrderItem.getAmountPaid()) {
                columns.add("amount_paid");
                values.add(b2bOrderItem.getAmountPaid());
            }
            if (null != b2bOrderItem.getCapacity()) {
                columns.add("capacity");
                values.add(b2bOrderItem.getCapacity());
            }
            if (null != b2bOrderItem.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bOrderItem.getProColorNo());
            }
            if (null != b2bOrderItem.getRestName()) {
                columns.add("rest_name");
                values.add(b2bOrderItem.getRestName());
            }
            if (null != b2bOrderItem.getDiscount()) {
                columns.add("discount");
                values.add(b2bOrderItem.getDiscount());
            }
            if (null != b2bOrderItem.getRmtStatus()) {
                columns.add("rmt_status");
                values.add(b2bOrderItem.getRmtStatus());
            }
            if (null != b2bOrderItem.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bOrderItem.getBaseProId());
            }
            if (null != b2bOrderItem.getParentNamePath()) {
                columns.add("parent_name_path");
                values.add(b2bOrderItem.getParentNamePath());
            }
            if (null != b2bOrderItem.getStrategyDesc()) {
                columns.add("strategy_desc");
                values.add(b2bOrderItem.getStrategyDesc());
            }
            if (null != b2bOrderItem.getSize()) {
                columns.add("size");
                values.add(b2bOrderItem.getSize());
            }
            if (null != b2bOrderItem.getParentIdPath()) {
                columns.add("parent_id_path");
                values.add(b2bOrderItem.getParentIdPath());
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                values.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bOrderItem.getSupplierName());
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                values.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                values.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.getApplDesc()) {
                columns.add("appl_desc");
                values.add(b2bOrderItem.getApplDesc());
            }
            if (null != b2bOrderItem.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                values.add(b2bOrderItem.getSlsPmtnId());
            }
            if (null != b2bOrderItem.getPriceAgencyId()) {
                columns.add("price_agency_id");
                values.add(b2bOrderItem.getPriceAgencyId());
            }
            if (null != b2bOrderItem.getSubtotal()) {
                columns.add("subtotal");
                values.add(b2bOrderItem.getSubtotal());
            }
            if (null != b2bOrderItem.getProName()) {
                columns.add("pro_name");
                values.add(b2bOrderItem.getProName());
            }
            if (null != b2bOrderItem.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bOrderItem.getSpecsId());
            }
            if (null != b2bOrderItem.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bOrderItem.getTargetClient());
            }
            if (null != b2bOrderItem.getOrderPkgId()) {
                columns.add("order_pkg_id");
                values.add(b2bOrderItem.getOrderPkgId());
            }
            if (null != b2bOrderItem.getProId()) {
                columns.add("pro_id");
                values.add(b2bOrderItem.getProId());
            }
            if (null != b2bOrderItem.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bOrderItem.getCategoryName());
            }
            if (null != b2bOrderItem.getCartItemId()) {
                columns.add("cart_item_id");
                values.add(b2bOrderItem.getCartItemId());
            }
            if (null != b2bOrderItem.getDamageBdId()) {
                columns.add("damage_bd_id");
                values.add(b2bOrderItem.getDamageBdId());
            }
            if (null != b2bOrderItem.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bOrderItem.getSpecsName());
            }
            if (null != b2bOrderItem.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bOrderItem.getBaseProNo());
            }
            if (null != b2bOrderItem.getBdId()) {
                columns.add("bd_id");
                values.add(b2bOrderItem.getBdId());
            }
            if (null != b2bOrderItem.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bOrderItem.getCategoryId());
            }
            if (null != b2bOrderItem.getRestId()) {
                columns.add("rest_id");
                values.add(b2bOrderItem.getRestId());
            }
            if (null != b2bOrderItem.getStorageStatus()) {
                columns.add("storage_status");
                values.add(b2bOrderItem.getStorageStatus());
            }
            if (null != b2bOrderItem.getPriCatLineId()) {
                columns.add("pri_cat_line_id");
                values.add(b2bOrderItem.getPriCatLineId());
            }
            if (null != b2bOrderItem.getBarCode()) {
                columns.add("bar_code");
                values.add(b2bOrderItem.getBarCode());
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
    public int insertSelectiveAndGetId(B2bOrderItemBean b2bOrderItem) {
        try {
            if (null == b2bOrderItem) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_item(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bOrderItem.getPrice()) {
                columns.add("price");
                parameters.add(":price");
            }
            if (null != b2bOrderItem.getBId()) {
                columns.add("b_id");
                parameters.add(":bId");
            }
            if (null != b2bOrderItem.getProDesc()) {
                columns.add("pro_desc");
                parameters.add(":proDesc");
            }
            if (null != b2bOrderItem.getRmtDetailId()) {
                columns.add("rmt_detail_id");
                parameters.add(":rmtDetailId");
            }
            if (null != b2bOrderItem.getQuantity()) {
                columns.add("quantity");
                parameters.add(":quantity");
            }
            if (null != b2bOrderItem.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bOrderItem.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                parameters.add(":orderPkgCode");
            }
            if (null != b2bOrderItem.getNumberOfBroken()) {
                columns.add("number_of_broken");
                parameters.add(":numberOfBroken");
            }
            if (null != b2bOrderItem.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bOrderItem.getPriceAgencyName()) {
                columns.add("price_agency_name");
                parameters.add(":priceAgencyName");
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderItem.getPrivilege()) {
                columns.add("privilege");
                parameters.add(":privilege");
            }
            if (null != b2bOrderItem.getPic()) {
                columns.add("pic");
                parameters.add(":pic");
            }
            if (null != b2bOrderItem.getIsCodeless()) {
                columns.add("is_codeless");
                parameters.add(":isCodeless");
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderItem.getRmtPkgId()) {
                columns.add("rmt_pkg_id");
                parameters.add(":rmtPkgId");
            }
            if (null != b2bOrderItem.getBdNo()) {
                columns.add("bd_no");
                parameters.add(":bdNo");
            }
            if (null != b2bOrderItem.getStartPointQyt()) {
                columns.add("start_point_qyt");
                parameters.add(":startPointQyt");
            }
            if (null != b2bOrderItem.getAmountPaid()) {
                columns.add("amount_paid");
                parameters.add(":amountPaid");
            }
            if (null != b2bOrderItem.getCapacity()) {
                columns.add("capacity");
                parameters.add(":capacity");
            }
            if (null != b2bOrderItem.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bOrderItem.getRestName()) {
                columns.add("rest_name");
                parameters.add(":restName");
            }
            if (null != b2bOrderItem.getDiscount()) {
                columns.add("discount");
                parameters.add(":discount");
            }
            if (null != b2bOrderItem.getRmtStatus()) {
                columns.add("rmt_status");
                parameters.add(":rmtStatus");
            }
            if (null != b2bOrderItem.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bOrderItem.getParentNamePath()) {
                columns.add("parent_name_path");
                parameters.add(":parentNamePath");
            }
            if (null != b2bOrderItem.getStrategyDesc()) {
                columns.add("strategy_desc");
                parameters.add(":strategyDesc");
            }
            if (null != b2bOrderItem.getSize()) {
                columns.add("size");
                parameters.add(":size");
            }
            if (null != b2bOrderItem.getParentIdPath()) {
                columns.add("parent_id_path");
                parameters.add(":parentIdPath");
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderItem.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderItem.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderItem.getApplDesc()) {
                columns.add("appl_desc");
                parameters.add(":applDesc");
            }
            if (null != b2bOrderItem.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                parameters.add(":slsPmtnId");
            }
            if (null != b2bOrderItem.getPriceAgencyId()) {
                columns.add("price_agency_id");
                parameters.add(":priceAgencyId");
            }
            if (null != b2bOrderItem.getSubtotal()) {
                columns.add("subtotal");
                parameters.add(":subtotal");
            }
            if (null != b2bOrderItem.getProName()) {
                columns.add("pro_name");
                parameters.add(":proName");
            }
            if (null != b2bOrderItem.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bOrderItem.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bOrderItem.getOrderPkgId()) {
                columns.add("order_pkg_id");
                parameters.add(":orderPkgId");
            }
            if (null != b2bOrderItem.getProId()) {
                columns.add("pro_id");
                parameters.add(":proId");
            }
            if (null != b2bOrderItem.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bOrderItem.getCartItemId()) {
                columns.add("cart_item_id");
                parameters.add(":cartItemId");
            }
            if (null != b2bOrderItem.getDamageBdId()) {
                columns.add("damage_bd_id");
                parameters.add(":damageBdId");
            }
            if (null != b2bOrderItem.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }
            if (null != b2bOrderItem.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bOrderItem.getBdId()) {
                columns.add("bd_id");
                parameters.add(":bdId");
            }
            if (null != b2bOrderItem.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bOrderItem.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bOrderItem.getStorageStatus()) {
                columns.add("storage_status");
                parameters.add(":storageStatus");
            }
            if (null != b2bOrderItem.getPriCatLineId()) {
                columns.add("pri_cat_line_id");
                parameters.add(":priCatLineId");
            }
            if (null != b2bOrderItem.getBarCode()) {
                columns.add("bar_code");
                parameters.add(":barCode");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bOrderItem);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bOrderItem.setBdId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bOrderItemBean b2bOrderItem) {
        try {
            if (null == b2bOrderItem.getBdId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_order_item SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bOrderItem.getPrice()) {
                updateSql.add("price = ?");
                params.add(b2bOrderItem.getPrice());
            }
            if (null != b2bOrderItem.getBId()) {
                updateSql.add("b_id = ?");
                params.add(b2bOrderItem.getBId());
            }
            if (null != b2bOrderItem.getProDesc()) {
                updateSql.add("pro_desc = ?");
                params.add(b2bOrderItem.getProDesc());
            }
            if (null != b2bOrderItem.getRmtDetailId()) {
                updateSql.add("rmt_detail_id = ?");
                params.add(b2bOrderItem.getRmtDetailId());
            }
            if (null != b2bOrderItem.getQuantity()) {
                updateSql.add("quantity = ?");
                params.add(b2bOrderItem.getQuantity());
            }
            if (null != b2bOrderItem.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bOrderItem.getVolume());
            }
            if (null != b2bOrderItem.getOrderPkgCode()) {
                updateSql.add("order_pkg_code = ?");
                params.add(b2bOrderItem.getOrderPkgCode());
            }
            if (null != b2bOrderItem.getNumberOfBroken()) {
                updateSql.add("number_of_broken = ?");
                params.add(b2bOrderItem.getNumberOfBroken());
            }
            if (null != b2bOrderItem.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bOrderItem.getSupplierId());
            }
            if (null != b2bOrderItem.getPriceAgencyName()) {
                updateSql.add("price_agency_name = ?");
                params.add(b2bOrderItem.getPriceAgencyName());
            }
            if (null != b2bOrderItem.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.getPrivilege()) {
                updateSql.add("privilege = ?");
                params.add(b2bOrderItem.getPrivilege());
            }
            if (null != b2bOrderItem.getPic()) {
                updateSql.add("pic = ?");
                params.add(b2bOrderItem.getPic());
            }
            if (null != b2bOrderItem.getIsCodeless()) {
                updateSql.add("is_codeless = ?");
                params.add(b2bOrderItem.getIsCodeless());
            }
            if (null != b2bOrderItem.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.getRmtPkgId()) {
                updateSql.add("rmt_pkg_id = ?");
                params.add(b2bOrderItem.getRmtPkgId());
            }
            if (null != b2bOrderItem.getBdNo()) {
                updateSql.add("bd_no = ?");
                params.add(b2bOrderItem.getBdNo());
            }
            if (null != b2bOrderItem.getStartPointQyt()) {
                updateSql.add("start_point_qyt = ?");
                params.add(b2bOrderItem.getStartPointQyt());
            }
            if (null != b2bOrderItem.getAmountPaid()) {
                updateSql.add("amount_paid = ?");
                params.add(b2bOrderItem.getAmountPaid());
            }
            if (null != b2bOrderItem.getCapacity()) {
                updateSql.add("capacity = ?");
                params.add(b2bOrderItem.getCapacity());
            }
            if (null != b2bOrderItem.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bOrderItem.getProColorNo());
            }
            if (null != b2bOrderItem.getRestName()) {
                updateSql.add("rest_name = ?");
                params.add(b2bOrderItem.getRestName());
            }
            if (null != b2bOrderItem.getDiscount()) {
                updateSql.add("discount = ?");
                params.add(b2bOrderItem.getDiscount());
            }
            if (null != b2bOrderItem.getRmtStatus()) {
                updateSql.add("rmt_status = ?");
                params.add(b2bOrderItem.getRmtStatus());
            }
            if (null != b2bOrderItem.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bOrderItem.getBaseProId());
            }
            if (null != b2bOrderItem.getParentNamePath()) {
                updateSql.add("parent_name_path = ?");
                params.add(b2bOrderItem.getParentNamePath());
            }
            if (null != b2bOrderItem.getStrategyDesc()) {
                updateSql.add("strategy_desc = ?");
                params.add(b2bOrderItem.getStrategyDesc());
            }
            if (null != b2bOrderItem.getSize()) {
                updateSql.add("size = ?");
                params.add(b2bOrderItem.getSize());
            }
            if (null != b2bOrderItem.getParentIdPath()) {
                updateSql.add("parent_id_path = ?");
                params.add(b2bOrderItem.getParentIdPath());
            }
            if (null != b2bOrderItem.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bOrderItem.getSupplierName());
            }
            if (null != b2bOrderItem.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderItem.get());
            }
            if (null != b2bOrderItem.getApplDesc()) {
                updateSql.add("appl_desc = ?");
                params.add(b2bOrderItem.getApplDesc());
            }
            if (null != b2bOrderItem.getSlsPmtnId()) {
                updateSql.add("sls_pmtn_id = ?");
                params.add(b2bOrderItem.getSlsPmtnId());
            }
            if (null != b2bOrderItem.getPriceAgencyId()) {
                updateSql.add("price_agency_id = ?");
                params.add(b2bOrderItem.getPriceAgencyId());
            }
            if (null != b2bOrderItem.getSubtotal()) {
                updateSql.add("subtotal = ?");
                params.add(b2bOrderItem.getSubtotal());
            }
            if (null != b2bOrderItem.getProName()) {
                updateSql.add("pro_name = ?");
                params.add(b2bOrderItem.getProName());
            }
            if (null != b2bOrderItem.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bOrderItem.getSpecsId());
            }
            if (null != b2bOrderItem.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bOrderItem.getTargetClient());
            }
            if (null != b2bOrderItem.getOrderPkgId()) {
                updateSql.add("order_pkg_id = ?");
                params.add(b2bOrderItem.getOrderPkgId());
            }
            if (null != b2bOrderItem.getProId()) {
                updateSql.add("pro_id = ?");
                params.add(b2bOrderItem.getProId());
            }
            if (null != b2bOrderItem.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bOrderItem.getCategoryName());
            }
            if (null != b2bOrderItem.getCartItemId()) {
                updateSql.add("cart_item_id = ?");
                params.add(b2bOrderItem.getCartItemId());
            }
            if (null != b2bOrderItem.getDamageBdId()) {
                updateSql.add("damage_bd_id = ?");
                params.add(b2bOrderItem.getDamageBdId());
            }
            if (null != b2bOrderItem.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bOrderItem.getSpecsName());
            }
            if (null != b2bOrderItem.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bOrderItem.getBaseProNo());
            }
            if (null != b2bOrderItem.getBdId()) {
                updateSql.add("bd_id = ?");
                params.add(b2bOrderItem.getBdId());
            }
            if (null != b2bOrderItem.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bOrderItem.getCategoryId());
            }
            if (null != b2bOrderItem.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bOrderItem.getRestId());
            }
            if (null != b2bOrderItem.getStorageStatus()) {
                updateSql.add("storage_status = ?");
                params.add(b2bOrderItem.getStorageStatus());
            }
            if (null != b2bOrderItem.getPriCatLineId()) {
                updateSql.add("pri_cat_line_id = ?");
                params.add(b2bOrderItem.getPriCatLineId());
            }
            if (null != b2bOrderItem.getBarCode()) {
                updateSql.add("bar_code = ?");
                params.add(b2bOrderItem.getBarCode());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE bd_id = ?");
            params.add(b2bOrderItem.getBdId());

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
    public int deleteByPrimaryKey(String bdId) {
            if(null ==  bdId) {
            return 0;
        }

        String sql = "delete from b2b_order_item where bd_id  = ?";
        return jdbcTemplate.update(sql, bdId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_order_item where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bOrderItemBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bOrderItemBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bOrderItemBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bOrderItemBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bOrderItemBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderItemBean> findAll(Pageable arg0) {
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
	public void delete(B2bOrderItemBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bOrderItemBean> arg0) {
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
	public Iterable<B2bOrderItemBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bOrderItemBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderItemBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderItemBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderItemBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bOrderItemBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bOrderItemBean> findAll(
			Specification<B2bOrderItemBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderItemBean> findAll(
			Specification<B2bOrderItemBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bOrderItemBean> findAll(
			Specification<B2bOrderItemBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderItemBean findOne(
			Specification<B2bOrderItemBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
