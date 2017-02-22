package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bProductBean;

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

@Repository("b2bProductDao")
public class B2bProductDaoImpl implements B2bProductDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bProductBean selectByPrimaryKey(String proId) {
        try {
            String sql = "select * from b2b_product where pro_id = ?";

            List<B2bProductBean> resultList = this.jdbcTemplate.query(sql, new Object[]{proId},
                    new RowMapper<B2bProductBean>() {
                        @Override
                        public B2bProductBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bProductBean bean = new B2bProductBean();
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setPicAll(rs.getString("pic_all"));
                            bean.setName(rs.getString("name"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setPrimeCost(rs.getString("prime_cost"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setGoodsAttr3(rs.getString("goods_attr3"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setPic(rs.getString("pic"));
                            bean.set(rs.getString(""));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setPrice(rs.getString("price"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setGoodsAttr2(rs.getString("goods_attr2"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setGoodsAttr4(rs.getString("goods_attr4"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setBaseStatus(rs.getString("base_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setGoodsAttr1(rs.getString("goods_attr1"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.set(rs.getDate(""));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setGoodsAttr5(rs.getString("goods_attr5"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setSpecsName(rs.getString("specs_name"));
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
    public List<B2bProductBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_product ";
            } else {
                sql = "select * from b2b_product where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bProductBean>() {
                        @Override
                        public B2bProductBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bProductBean bean = new B2bProductBean();
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setPicAll(rs.getString("pic_all"));
                            bean.setName(rs.getString("name"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setPrimeCost(rs.getString("prime_cost"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setGoodsAttr3(rs.getString("goods_attr3"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setPic(rs.getString("pic"));
                            bean.set(rs.getString(""));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setPrice(rs.getString("price"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setGoodsAttr2(rs.getString("goods_attr2"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setGoodsAttr4(rs.getString("goods_attr4"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setBaseStatus(rs.getString("base_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setGoodsAttr1(rs.getString("goods_attr1"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.set(rs.getDate(""));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setGoodsAttr5(rs.getString("goods_attr5"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setSpecsName(rs.getString("specs_name"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bProductBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_product ";
            } else {
                sql = "select * from b2b_product where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bProductBean>() {
                        @Override
                        public B2bProductBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bProductBean bean = new B2bProductBean();
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setPicAll(rs.getString("pic_all"));
                            bean.setName(rs.getString("name"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setPrimeCost(rs.getString("prime_cost"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setGoodsAttr3(rs.getString("goods_attr3"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setPic(rs.getString("pic"));
                            bean.set(rs.getString(""));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setPrice(rs.getString("price"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setGoodsAttr2(rs.getString("goods_attr2"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setGoodsAttr4(rs.getString("goods_attr4"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setBaseStatus(rs.getString("base_status"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setGoodsAttr1(rs.getString("goods_attr1"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.set(rs.getDate(""));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setGoodsAttr5(rs.getString("goods_attr5"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.set(rs.getDate(""));
                            bean.set(rs.getString(""));
                            bean.setSpecsName(rs.getString("specs_name"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bProductBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_product ";
            } else {
                sql = "select count(1) numCount from b2b_product where " + whereSql;
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
    public int insertSelective(B2bProductBean b2bProduct) {
        try {
            if (null == b2bProduct) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_product(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bProduct.getSize()) {
                columns.add("size");
                values.add(b2bProduct.getSize());
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                values.add(b2bProduct.get());
            }
            if (null != b2bProduct.getSupplierType()) {
                columns.add("supplier_type");
                values.add(b2bProduct.getSupplierType());
            }
            if (null != b2bProduct.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bProduct.getBaseProNo());
            }
            if (null != b2bProduct.getVolume()) {
                columns.add("volume");
                values.add(b2bProduct.getVolume());
            }
            if (null != b2bProduct.getProId()) {
                columns.add("pro_id");
                values.add(b2bProduct.getProId());
            }
            if (null != b2bProduct.getPicAll()) {
                columns.add("pic_all");
                values.add(b2bProduct.getPicAll());
            }
            if (null != b2bProduct.getName()) {
                columns.add("name");
                values.add(b2bProduct.getName());
            }
            if (null != b2bProduct.getSpuulierName()) {
                columns.add("spuulier_name");
                values.add(b2bProduct.getSpuulierName());
            }
            if (null != b2bProduct.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bProduct.getSupplierId());
            }
            if (null != b2bProduct.getPrimeCost()) {
                columns.add("prime_cost");
                values.add(b2bProduct.getPrimeCost());
            }
            if (null != b2bProduct.getProDesc()) {
                columns.add("pro_desc");
                values.add(b2bProduct.getProDesc());
            }
            if (null != b2bProduct.getApplDesc()) {
                columns.add("appl_desc");
                values.add(b2bProduct.getApplDesc());
            }
            if (null != b2bProduct.getGoodsAttr3()) {
                columns.add("goods_attr3");
                values.add(b2bProduct.getGoodsAttr3());
            }
            if (null != b2bProduct.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bProduct.getCategoryId());
            }
            if (null != b2bProduct.getParentIdPath()) {
                columns.add("parent_id_path");
                values.add(b2bProduct.getParentIdPath());
            }
            if (null != b2bProduct.getPic()) {
                columns.add("pic");
                values.add(b2bProduct.getPic());
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                values.add(b2bProduct.get());
            }
            if (null != b2bProduct.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bProduct.getCategoryName());
            }
            if (null != b2bProduct.getPrice()) {
                columns.add("price");
                values.add(b2bProduct.getPrice());
            }
            if (null != b2bProduct.getQuantity()) {
                columns.add("quantity");
                values.add(b2bProduct.getQuantity());
            }
            if (null != b2bProduct.getGoodsAttr2()) {
                columns.add("goods_attr2");
                values.add(b2bProduct.getGoodsAttr2());
            }
            if (null != b2bProduct.getCapacity()) {
                columns.add("capacity");
                values.add(b2bProduct.getCapacity());
            }
            if (null != b2bProduct.getGoodsAttr4()) {
                columns.add("goods_attr4");
                values.add(b2bProduct.getGoodsAttr4());
            }
            if (null != b2bProduct.getParentNamePath()) {
                columns.add("parent_name_path");
                values.add(b2bProduct.getParentNamePath());
            }
            if (null != b2bProduct.getBaseStatus()) {
                columns.add("base_status");
                values.add(b2bProduct.getBaseStatus());
            }
            if (null != b2bProduct.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bProduct.getBaseProId());
            }
            if (null != b2bProduct.getGoodsAttr1()) {
                columns.add("goods_attr1");
                values.add(b2bProduct.getGoodsAttr1());
            }
            if (null != b2bProduct.getBarCode()) {
                columns.add("bar_code");
                values.add(b2bProduct.getBarCode());
            }
            if (null != b2bProduct.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bProduct.getSpecsId());
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                values.add(b2bProduct.get());
            }
            if (null != b2bProduct.getRemarks()) {
                columns.add("remarks");
                values.add(b2bProduct.getRemarks());
            }
            if (null != b2bProduct.getRestId()) {
                columns.add("rest_id");
                values.add(b2bProduct.getRestId());
            }
            if (null != b2bProduct.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bProduct.getTargetClient());
            }
            if (null != b2bProduct.getGoodsAttr5()) {
                columns.add("goods_attr5");
                values.add(b2bProduct.getGoodsAttr5());
            }
            if (null != b2bProduct.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bProduct.getProColorNo());
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                values.add(b2bProduct.get());
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                values.add(b2bProduct.get());
            }
            if (null != b2bProduct.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bProduct.getSpecsName());
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
    public int insertSelectiveAndGetId(B2bProductBean b2bProduct) {
        try {
            if (null == b2bProduct) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_product(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bProduct.getSize()) {
                columns.add("size");
                parameters.add(":size");
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProduct.getSupplierType()) {
                columns.add("supplier_type");
                parameters.add(":supplierType");
            }
            if (null != b2bProduct.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bProduct.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bProduct.getProId()) {
                columns.add("pro_id");
                parameters.add(":proId");
            }
            if (null != b2bProduct.getPicAll()) {
                columns.add("pic_all");
                parameters.add(":picAll");
            }
            if (null != b2bProduct.getName()) {
                columns.add("name");
                parameters.add(":name");
            }
            if (null != b2bProduct.getSpuulierName()) {
                columns.add("spuulier_name");
                parameters.add(":spuulierName");
            }
            if (null != b2bProduct.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bProduct.getPrimeCost()) {
                columns.add("prime_cost");
                parameters.add(":primeCost");
            }
            if (null != b2bProduct.getProDesc()) {
                columns.add("pro_desc");
                parameters.add(":proDesc");
            }
            if (null != b2bProduct.getApplDesc()) {
                columns.add("appl_desc");
                parameters.add(":applDesc");
            }
            if (null != b2bProduct.getGoodsAttr3()) {
                columns.add("goods_attr3");
                parameters.add(":goodsAttr3");
            }
            if (null != b2bProduct.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bProduct.getParentIdPath()) {
                columns.add("parent_id_path");
                parameters.add(":parentIdPath");
            }
            if (null != b2bProduct.getPic()) {
                columns.add("pic");
                parameters.add(":pic");
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProduct.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bProduct.getPrice()) {
                columns.add("price");
                parameters.add(":price");
            }
            if (null != b2bProduct.getQuantity()) {
                columns.add("quantity");
                parameters.add(":quantity");
            }
            if (null != b2bProduct.getGoodsAttr2()) {
                columns.add("goods_attr2");
                parameters.add(":goodsAttr2");
            }
            if (null != b2bProduct.getCapacity()) {
                columns.add("capacity");
                parameters.add(":capacity");
            }
            if (null != b2bProduct.getGoodsAttr4()) {
                columns.add("goods_attr4");
                parameters.add(":goodsAttr4");
            }
            if (null != b2bProduct.getParentNamePath()) {
                columns.add("parent_name_path");
                parameters.add(":parentNamePath");
            }
            if (null != b2bProduct.getBaseStatus()) {
                columns.add("base_status");
                parameters.add(":baseStatus");
            }
            if (null != b2bProduct.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bProduct.getGoodsAttr1()) {
                columns.add("goods_attr1");
                parameters.add(":goodsAttr1");
            }
            if (null != b2bProduct.getBarCode()) {
                columns.add("bar_code");
                parameters.add(":barCode");
            }
            if (null != b2bProduct.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProduct.getRemarks()) {
                columns.add("remarks");
                parameters.add(":remarks");
            }
            if (null != b2bProduct.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bProduct.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bProduct.getGoodsAttr5()) {
                columns.add("goods_attr5");
                parameters.add(":goodsAttr5");
            }
            if (null != b2bProduct.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProduct.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProduct.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bProduct);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bProduct.setProId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bProductBean b2bProduct) {
        try {
            if (null == b2bProduct.getProId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_product SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bProduct.getSize()) {
                updateSql.add("size = ?");
                params.add(b2bProduct.getSize());
            }
            if (null != b2bProduct.get()) {
                updateSql.add(" = ?");
                params.add(b2bProduct.get());
            }
            if (null != b2bProduct.getSupplierType()) {
                updateSql.add("supplier_type = ?");
                params.add(b2bProduct.getSupplierType());
            }
            if (null != b2bProduct.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bProduct.getBaseProNo());
            }
            if (null != b2bProduct.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bProduct.getVolume());
            }
            if (null != b2bProduct.getProId()) {
                updateSql.add("pro_id = ?");
                params.add(b2bProduct.getProId());
            }
            if (null != b2bProduct.getPicAll()) {
                updateSql.add("pic_all = ?");
                params.add(b2bProduct.getPicAll());
            }
            if (null != b2bProduct.getName()) {
                updateSql.add("name = ?");
                params.add(b2bProduct.getName());
            }
            if (null != b2bProduct.getSpuulierName()) {
                updateSql.add("spuulier_name = ?");
                params.add(b2bProduct.getSpuulierName());
            }
            if (null != b2bProduct.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bProduct.getSupplierId());
            }
            if (null != b2bProduct.getPrimeCost()) {
                updateSql.add("prime_cost = ?");
                params.add(b2bProduct.getPrimeCost());
            }
            if (null != b2bProduct.getProDesc()) {
                updateSql.add("pro_desc = ?");
                params.add(b2bProduct.getProDesc());
            }
            if (null != b2bProduct.getApplDesc()) {
                updateSql.add("appl_desc = ?");
                params.add(b2bProduct.getApplDesc());
            }
            if (null != b2bProduct.getGoodsAttr3()) {
                updateSql.add("goods_attr3 = ?");
                params.add(b2bProduct.getGoodsAttr3());
            }
            if (null != b2bProduct.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bProduct.getCategoryId());
            }
            if (null != b2bProduct.getParentIdPath()) {
                updateSql.add("parent_id_path = ?");
                params.add(b2bProduct.getParentIdPath());
            }
            if (null != b2bProduct.getPic()) {
                updateSql.add("pic = ?");
                params.add(b2bProduct.getPic());
            }
            if (null != b2bProduct.get()) {
                updateSql.add(" = ?");
                params.add(b2bProduct.get());
            }
            if (null != b2bProduct.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bProduct.getCategoryName());
            }
            if (null != b2bProduct.getPrice()) {
                updateSql.add("price = ?");
                params.add(b2bProduct.getPrice());
            }
            if (null != b2bProduct.getQuantity()) {
                updateSql.add("quantity = ?");
                params.add(b2bProduct.getQuantity());
            }
            if (null != b2bProduct.getGoodsAttr2()) {
                updateSql.add("goods_attr2 = ?");
                params.add(b2bProduct.getGoodsAttr2());
            }
            if (null != b2bProduct.getCapacity()) {
                updateSql.add("capacity = ?");
                params.add(b2bProduct.getCapacity());
            }
            if (null != b2bProduct.getGoodsAttr4()) {
                updateSql.add("goods_attr4 = ?");
                params.add(b2bProduct.getGoodsAttr4());
            }
            if (null != b2bProduct.getParentNamePath()) {
                updateSql.add("parent_name_path = ?");
                params.add(b2bProduct.getParentNamePath());
            }
            if (null != b2bProduct.getBaseStatus()) {
                updateSql.add("base_status = ?");
                params.add(b2bProduct.getBaseStatus());
            }
            if (null != b2bProduct.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bProduct.getBaseProId());
            }
            if (null != b2bProduct.getGoodsAttr1()) {
                updateSql.add("goods_attr1 = ?");
                params.add(b2bProduct.getGoodsAttr1());
            }
            if (null != b2bProduct.getBarCode()) {
                updateSql.add("bar_code = ?");
                params.add(b2bProduct.getBarCode());
            }
            if (null != b2bProduct.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bProduct.getSpecsId());
            }
            if (null != b2bProduct.get()) {
                updateSql.add(" = ?");
                params.add(b2bProduct.get());
            }
            if (null != b2bProduct.getRemarks()) {
                updateSql.add("remarks = ?");
                params.add(b2bProduct.getRemarks());
            }
            if (null != b2bProduct.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bProduct.getRestId());
            }
            if (null != b2bProduct.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bProduct.getTargetClient());
            }
            if (null != b2bProduct.getGoodsAttr5()) {
                updateSql.add("goods_attr5 = ?");
                params.add(b2bProduct.getGoodsAttr5());
            }
            if (null != b2bProduct.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bProduct.getProColorNo());
            }
            if (null != b2bProduct.get()) {
                updateSql.add(" = ?");
                params.add(b2bProduct.get());
            }
            if (null != b2bProduct.get()) {
                updateSql.add(" = ?");
                params.add(b2bProduct.get());
            }
            if (null != b2bProduct.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bProduct.getSpecsName());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE pro_id = ?");
            params.add(b2bProduct.getProId());

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
    public int deleteByPrimaryKey(String proId) {
            if(null ==  proId) {
            return 0;
        }

        String sql = "delete from b2b_product where pro_id  = ?";
        return jdbcTemplate.update(sql, proId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_product where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bProductBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bProductBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bProductBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bProductBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bProductBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bProductBean> findAll(Pageable arg0) {
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
	public void delete(B2bProductBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bProductBean> arg0) {
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
	public Iterable<B2bProductBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bProductBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bProductBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bProductBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bProductBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bProductBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bProductBean> findAll(
			Specification<B2bProductBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bProductBean> findAll(
			Specification<B2bProductBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bProductBean> findAll(
			Specification<B2bProductBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bProductBean findOne(
			Specification<B2bProductBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
