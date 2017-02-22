package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bShoppingCartDetailBean;

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

@Repository("b2bShoppingCartDetailDao")
public class B2bShoppingCartDetailDaoImpl implements B2bShoppingCartDetailDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bShoppingCartDetailBean selectByPrimaryKey(String cartItemId) {
        try {
            String sql = "select * from b2b_shopping_cart_detail where cart_item_id = ?";

            List<B2bShoppingCartDetailBean> resultList = this.jdbcTemplate.query(sql, new Object[]{cartItemId},
                    new RowMapper<B2bShoppingCartDetailBean>() {
                        @Override
                        public B2bShoppingCartDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bShoppingCartDetailBean bean = new B2bShoppingCartDetailBean();
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setPrice(rs.getString("price"));
                            bean.setSubtotal(rs.getString("subtotal"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setPrivilege(rs.getString("privilege"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setCartPkgId(rs.getString("cart_pkg_id"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.set(rs.getDate(""));
                            bean.setName(rs.getString("name"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.set(rs.getString(""));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setPayStatus(rs.getString("pay_status"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setPic(rs.getString("pic"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setCartItemId(rs.getString("cart_item_id"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setIsCodeless(rs.getString("is_codeless"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setPriceAgencyName(rs.getString("price_agency_name"));
                            bean.setPriceAgencyId(rs.getString("price_agency_id"));
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
    public List<B2bShoppingCartDetailBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_shopping_cart_detail ";
            } else {
                sql = "select * from b2b_shopping_cart_detail where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bShoppingCartDetailBean>() {
                        @Override
                        public B2bShoppingCartDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bShoppingCartDetailBean bean = new B2bShoppingCartDetailBean();
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setPrice(rs.getString("price"));
                            bean.setSubtotal(rs.getString("subtotal"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setPrivilege(rs.getString("privilege"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setCartPkgId(rs.getString("cart_pkg_id"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.set(rs.getDate(""));
                            bean.setName(rs.getString("name"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.set(rs.getString(""));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setPayStatus(rs.getString("pay_status"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setPic(rs.getString("pic"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setCartItemId(rs.getString("cart_item_id"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setIsCodeless(rs.getString("is_codeless"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setPriceAgencyName(rs.getString("price_agency_name"));
                            bean.setPriceAgencyId(rs.getString("price_agency_id"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bShoppingCartDetailBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_shopping_cart_detail ";
            } else {
                sql = "select * from b2b_shopping_cart_detail where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bShoppingCartDetailBean>() {
                        @Override
                        public B2bShoppingCartDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bShoppingCartDetailBean bean = new B2bShoppingCartDetailBean();
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setDiscount(rs.getString("discount"));
                            bean.setPrice(rs.getString("price"));
                            bean.setSubtotal(rs.getString("subtotal"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setPrivilege(rs.getString("privilege"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setCartPkgId(rs.getString("cart_pkg_id"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setSlsPmtnId(rs.getString("sls_pmtn_id"));
                            bean.set(rs.getDate(""));
                            bean.setName(rs.getString("name"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setStrategyDesc(rs.getString("strategy_desc"));
                            bean.set(rs.getString(""));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setPayStatus(rs.getString("pay_status"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setPic(rs.getString("pic"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setCartItemId(rs.getString("cart_item_id"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setIsCodeless(rs.getString("is_codeless"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setPriceAgencyName(rs.getString("price_agency_name"));
                            bean.setPriceAgencyId(rs.getString("price_agency_id"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bShoppingCartDetailBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_shopping_cart_detail ";
            } else {
                sql = "select count(1) numCount from b2b_shopping_cart_detail where " + whereSql;
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
    public int insertSelective(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        try {
            if (null == b2bShoppingCartDetail) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_shopping_cart_detail(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bShoppingCartDetail.getProDesc()) {
                columns.add("pro_desc");
                values.add(b2bShoppingCartDetail.getProDesc());
            }
            if (null != b2bShoppingCartDetail.getVolume()) {
                columns.add("volume");
                values.add(b2bShoppingCartDetail.getVolume());
            }
            if (null != b2bShoppingCartDetail.getDiscount()) {
                columns.add("discount");
                values.add(b2bShoppingCartDetail.getDiscount());
            }
            if (null != b2bShoppingCartDetail.getPrice()) {
                columns.add("price");
                values.add(b2bShoppingCartDetail.getPrice());
            }
            if (null != b2bShoppingCartDetail.getSubtotal()) {
                columns.add("subtotal");
                values.add(b2bShoppingCartDetail.getSubtotal());
            }
            if (null != b2bShoppingCartDetail.getBarCode()) {
                columns.add("bar_code");
                values.add(b2bShoppingCartDetail.getBarCode());
            }
            if (null != b2bShoppingCartDetail.getPrivilege()) {
                columns.add("privilege");
                values.add(b2bShoppingCartDetail.getPrivilege());
            }
            if (null != b2bShoppingCartDetail.getRestName()) {
                columns.add("rest_name");
                values.add(b2bShoppingCartDetail.getRestName());
            }
            if (null != b2bShoppingCartDetail.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bShoppingCartDetail.getSpecsName());
            }
            if (null != b2bShoppingCartDetail.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bShoppingCartDetail.getCategoryName());
            }
            if (null != b2bShoppingCartDetail.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bShoppingCartDetail.getSupplierId());
            }
            if (null != b2bShoppingCartDetail.getSize()) {
                columns.add("size");
                values.add(b2bShoppingCartDetail.getSize());
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                values.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bShoppingCartDetail.getSupplierName());
            }
            if (null != b2bShoppingCartDetail.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bShoppingCartDetail.getSpecsId());
            }
            if (null != b2bShoppingCartDetail.getCartPkgId()) {
                columns.add("cart_pkg_id");
                values.add(b2bShoppingCartDetail.getCartPkgId());
            }
            if (null != b2bShoppingCartDetail.getStartPointQyt()) {
                columns.add("start_point_qyt");
                values.add(b2bShoppingCartDetail.getStartPointQyt());
            }
            if (null != b2bShoppingCartDetail.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bShoppingCartDetail.getCategoryId());
            }
            if (null != b2bShoppingCartDetail.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bShoppingCartDetail.getProColorNo());
            }
            if (null != b2bShoppingCartDetail.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                values.add(b2bShoppingCartDetail.getSlsPmtnId());
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                values.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.getName()) {
                columns.add("name");
                values.add(b2bShoppingCartDetail.getName());
            }
            if (null != b2bShoppingCartDetail.getRestId()) {
                columns.add("rest_id");
                values.add(b2bShoppingCartDetail.getRestId());
            }
            if (null != b2bShoppingCartDetail.getStrategyDesc()) {
                columns.add("strategy_desc");
                values.add(b2bShoppingCartDetail.getStrategyDesc());
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                values.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.getApplDesc()) {
                columns.add("appl_desc");
                values.add(b2bShoppingCartDetail.getApplDesc());
            }
            if (null != b2bShoppingCartDetail.getPayStatus()) {
                columns.add("pay_status");
                values.add(b2bShoppingCartDetail.getPayStatus());
            }
            if (null != b2bShoppingCartDetail.getCapacity()) {
                columns.add("capacity");
                values.add(b2bShoppingCartDetail.getCapacity());
            }
            if (null != b2bShoppingCartDetail.getPic()) {
                columns.add("pic");
                values.add(b2bShoppingCartDetail.getPic());
            }
            if (null != b2bShoppingCartDetail.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bShoppingCartDetail.getBaseProNo());
            }
            if (null != b2bShoppingCartDetail.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bShoppingCartDetail.getTargetClient());
            }
            if (null != b2bShoppingCartDetail.getCartId()) {
                columns.add("cart_id");
                values.add(b2bShoppingCartDetail.getCartId());
            }
            if (null != b2bShoppingCartDetail.getProId()) {
                columns.add("pro_id");
                values.add(b2bShoppingCartDetail.getProId());
            }
            if (null != b2bShoppingCartDetail.getCartItemId()) {
                columns.add("cart_item_id");
                values.add(b2bShoppingCartDetail.getCartItemId());
            }
            if (null != b2bShoppingCartDetail.getQuantity()) {
                columns.add("quantity");
                values.add(b2bShoppingCartDetail.getQuantity());
            }
            if (null != b2bShoppingCartDetail.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bShoppingCartDetail.getBaseProId());
            }
            if (null != b2bShoppingCartDetail.getIsCodeless()) {
                columns.add("is_codeless");
                values.add(b2bShoppingCartDetail.getIsCodeless());
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                values.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                values.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.getPriceAgencyName()) {
                columns.add("price_agency_name");
                values.add(b2bShoppingCartDetail.getPriceAgencyName());
            }
            if (null != b2bShoppingCartDetail.getPriceAgencyId()) {
                columns.add("price_agency_id");
                values.add(b2bShoppingCartDetail.getPriceAgencyId());
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
    public int insertSelectiveAndGetId(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        try {
            if (null == b2bShoppingCartDetail) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_shopping_cart_detail(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bShoppingCartDetail.getProDesc()) {
                columns.add("pro_desc");
                parameters.add(":proDesc");
            }
            if (null != b2bShoppingCartDetail.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bShoppingCartDetail.getDiscount()) {
                columns.add("discount");
                parameters.add(":discount");
            }
            if (null != b2bShoppingCartDetail.getPrice()) {
                columns.add("price");
                parameters.add(":price");
            }
            if (null != b2bShoppingCartDetail.getSubtotal()) {
                columns.add("subtotal");
                parameters.add(":subtotal");
            }
            if (null != b2bShoppingCartDetail.getBarCode()) {
                columns.add("bar_code");
                parameters.add(":barCode");
            }
            if (null != b2bShoppingCartDetail.getPrivilege()) {
                columns.add("privilege");
                parameters.add(":privilege");
            }
            if (null != b2bShoppingCartDetail.getRestName()) {
                columns.add("rest_name");
                parameters.add(":restName");
            }
            if (null != b2bShoppingCartDetail.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }
            if (null != b2bShoppingCartDetail.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bShoppingCartDetail.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bShoppingCartDetail.getSize()) {
                columns.add("size");
                parameters.add(":size");
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bShoppingCartDetail.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bShoppingCartDetail.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bShoppingCartDetail.getCartPkgId()) {
                columns.add("cart_pkg_id");
                parameters.add(":cartPkgId");
            }
            if (null != b2bShoppingCartDetail.getStartPointQyt()) {
                columns.add("start_point_qyt");
                parameters.add(":startPointQyt");
            }
            if (null != b2bShoppingCartDetail.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bShoppingCartDetail.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bShoppingCartDetail.getSlsPmtnId()) {
                columns.add("sls_pmtn_id");
                parameters.add(":slsPmtnId");
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bShoppingCartDetail.getName()) {
                columns.add("name");
                parameters.add(":name");
            }
            if (null != b2bShoppingCartDetail.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bShoppingCartDetail.getStrategyDesc()) {
                columns.add("strategy_desc");
                parameters.add(":strategyDesc");
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bShoppingCartDetail.getApplDesc()) {
                columns.add("appl_desc");
                parameters.add(":applDesc");
            }
            if (null != b2bShoppingCartDetail.getPayStatus()) {
                columns.add("pay_status");
                parameters.add(":payStatus");
            }
            if (null != b2bShoppingCartDetail.getCapacity()) {
                columns.add("capacity");
                parameters.add(":capacity");
            }
            if (null != b2bShoppingCartDetail.getPic()) {
                columns.add("pic");
                parameters.add(":pic");
            }
            if (null != b2bShoppingCartDetail.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bShoppingCartDetail.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bShoppingCartDetail.getCartId()) {
                columns.add("cart_id");
                parameters.add(":cartId");
            }
            if (null != b2bShoppingCartDetail.getProId()) {
                columns.add("pro_id");
                parameters.add(":proId");
            }
            if (null != b2bShoppingCartDetail.getCartItemId()) {
                columns.add("cart_item_id");
                parameters.add(":cartItemId");
            }
            if (null != b2bShoppingCartDetail.getQuantity()) {
                columns.add("quantity");
                parameters.add(":quantity");
            }
            if (null != b2bShoppingCartDetail.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bShoppingCartDetail.getIsCodeless()) {
                columns.add("is_codeless");
                parameters.add(":isCodeless");
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bShoppingCartDetail.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bShoppingCartDetail.getPriceAgencyName()) {
                columns.add("price_agency_name");
                parameters.add(":priceAgencyName");
            }
            if (null != b2bShoppingCartDetail.getPriceAgencyId()) {
                columns.add("price_agency_id");
                parameters.add(":priceAgencyId");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bShoppingCartDetail);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bShoppingCartDetail.setCartItemId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        try {
            if (null == b2bShoppingCartDetail.getCartItemId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_shopping_cart_detail SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bShoppingCartDetail.getProDesc()) {
                updateSql.add("pro_desc = ?");
                params.add(b2bShoppingCartDetail.getProDesc());
            }
            if (null != b2bShoppingCartDetail.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bShoppingCartDetail.getVolume());
            }
            if (null != b2bShoppingCartDetail.getDiscount()) {
                updateSql.add("discount = ?");
                params.add(b2bShoppingCartDetail.getDiscount());
            }
            if (null != b2bShoppingCartDetail.getPrice()) {
                updateSql.add("price = ?");
                params.add(b2bShoppingCartDetail.getPrice());
            }
            if (null != b2bShoppingCartDetail.getSubtotal()) {
                updateSql.add("subtotal = ?");
                params.add(b2bShoppingCartDetail.getSubtotal());
            }
            if (null != b2bShoppingCartDetail.getBarCode()) {
                updateSql.add("bar_code = ?");
                params.add(b2bShoppingCartDetail.getBarCode());
            }
            if (null != b2bShoppingCartDetail.getPrivilege()) {
                updateSql.add("privilege = ?");
                params.add(b2bShoppingCartDetail.getPrivilege());
            }
            if (null != b2bShoppingCartDetail.getRestName()) {
                updateSql.add("rest_name = ?");
                params.add(b2bShoppingCartDetail.getRestName());
            }
            if (null != b2bShoppingCartDetail.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bShoppingCartDetail.getSpecsName());
            }
            if (null != b2bShoppingCartDetail.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bShoppingCartDetail.getCategoryName());
            }
            if (null != b2bShoppingCartDetail.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bShoppingCartDetail.getSupplierId());
            }
            if (null != b2bShoppingCartDetail.getSize()) {
                updateSql.add("size = ?");
                params.add(b2bShoppingCartDetail.getSize());
            }
            if (null != b2bShoppingCartDetail.get()) {
                updateSql.add(" = ?");
                params.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bShoppingCartDetail.getSupplierName());
            }
            if (null != b2bShoppingCartDetail.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bShoppingCartDetail.getSpecsId());
            }
            if (null != b2bShoppingCartDetail.getCartPkgId()) {
                updateSql.add("cart_pkg_id = ?");
                params.add(b2bShoppingCartDetail.getCartPkgId());
            }
            if (null != b2bShoppingCartDetail.getStartPointQyt()) {
                updateSql.add("start_point_qyt = ?");
                params.add(b2bShoppingCartDetail.getStartPointQyt());
            }
            if (null != b2bShoppingCartDetail.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bShoppingCartDetail.getCategoryId());
            }
            if (null != b2bShoppingCartDetail.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bShoppingCartDetail.getProColorNo());
            }
            if (null != b2bShoppingCartDetail.getSlsPmtnId()) {
                updateSql.add("sls_pmtn_id = ?");
                params.add(b2bShoppingCartDetail.getSlsPmtnId());
            }
            if (null != b2bShoppingCartDetail.get()) {
                updateSql.add(" = ?");
                params.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.getName()) {
                updateSql.add("name = ?");
                params.add(b2bShoppingCartDetail.getName());
            }
            if (null != b2bShoppingCartDetail.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bShoppingCartDetail.getRestId());
            }
            if (null != b2bShoppingCartDetail.getStrategyDesc()) {
                updateSql.add("strategy_desc = ?");
                params.add(b2bShoppingCartDetail.getStrategyDesc());
            }
            if (null != b2bShoppingCartDetail.get()) {
                updateSql.add(" = ?");
                params.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.getApplDesc()) {
                updateSql.add("appl_desc = ?");
                params.add(b2bShoppingCartDetail.getApplDesc());
            }
            if (null != b2bShoppingCartDetail.getPayStatus()) {
                updateSql.add("pay_status = ?");
                params.add(b2bShoppingCartDetail.getPayStatus());
            }
            if (null != b2bShoppingCartDetail.getCapacity()) {
                updateSql.add("capacity = ?");
                params.add(b2bShoppingCartDetail.getCapacity());
            }
            if (null != b2bShoppingCartDetail.getPic()) {
                updateSql.add("pic = ?");
                params.add(b2bShoppingCartDetail.getPic());
            }
            if (null != b2bShoppingCartDetail.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bShoppingCartDetail.getBaseProNo());
            }
            if (null != b2bShoppingCartDetail.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bShoppingCartDetail.getTargetClient());
            }
            if (null != b2bShoppingCartDetail.getCartId()) {
                updateSql.add("cart_id = ?");
                params.add(b2bShoppingCartDetail.getCartId());
            }
            if (null != b2bShoppingCartDetail.getProId()) {
                updateSql.add("pro_id = ?");
                params.add(b2bShoppingCartDetail.getProId());
            }
            if (null != b2bShoppingCartDetail.getCartItemId()) {
                updateSql.add("cart_item_id = ?");
                params.add(b2bShoppingCartDetail.getCartItemId());
            }
            if (null != b2bShoppingCartDetail.getQuantity()) {
                updateSql.add("quantity = ?");
                params.add(b2bShoppingCartDetail.getQuantity());
            }
            if (null != b2bShoppingCartDetail.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bShoppingCartDetail.getBaseProId());
            }
            if (null != b2bShoppingCartDetail.getIsCodeless()) {
                updateSql.add("is_codeless = ?");
                params.add(b2bShoppingCartDetail.getIsCodeless());
            }
            if (null != b2bShoppingCartDetail.get()) {
                updateSql.add(" = ?");
                params.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.get()) {
                updateSql.add(" = ?");
                params.add(b2bShoppingCartDetail.get());
            }
            if (null != b2bShoppingCartDetail.getPriceAgencyName()) {
                updateSql.add("price_agency_name = ?");
                params.add(b2bShoppingCartDetail.getPriceAgencyName());
            }
            if (null != b2bShoppingCartDetail.getPriceAgencyId()) {
                updateSql.add("price_agency_id = ?");
                params.add(b2bShoppingCartDetail.getPriceAgencyId());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE cart_item_id = ?");
            params.add(b2bShoppingCartDetail.getCartItemId());

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
    public int deleteByPrimaryKey(String cartItemId) {
            if(null ==  cartItemId) {
            return 0;
        }

        String sql = "delete from b2b_shopping_cart_detail where cart_item_id  = ?";
        return jdbcTemplate.update(sql, cartItemId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_shopping_cart_detail where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bShoppingCartDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bShoppingCartDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bShoppingCartDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bShoppingCartDetailBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bShoppingCartDetailBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bShoppingCartDetailBean> findAll(Pageable arg0) {
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
	public void delete(B2bShoppingCartDetailBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bShoppingCartDetailBean> arg0) {
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
	public Iterable<B2bShoppingCartDetailBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bShoppingCartDetailBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bShoppingCartDetailBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bShoppingCartDetailBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bShoppingCartDetailBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bShoppingCartDetailBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bShoppingCartDetailBean> findAll(
			Specification<B2bShoppingCartDetailBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bShoppingCartDetailBean> findAll(
			Specification<B2bShoppingCartDetailBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bShoppingCartDetailBean> findAll(
			Specification<B2bShoppingCartDetailBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bShoppingCartDetailBean findOne(
			Specification<B2bShoppingCartDetailBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
