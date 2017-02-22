package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bProductClientBean;

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

@Repository("b2bProductClientDao")
public class B2bProductClientDaoImpl implements B2bProductClientDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bProductClientBean selectByPrimaryKey(String proId) {
        try {
            String sql = "select * from b2b_product_client where pro_id = ?";

            List<B2bProductClientBean> resultList = this.jdbcTemplate.query(sql, new Object[]{proId},
                    new RowMapper<B2bProductClientBean>() {
                        @Override
                        public B2bProductClientBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bProductClientBean bean = new B2bProductClientBean();
                            bean.set(rs.getString(""));
                            bean.setSize(rs.getString("size"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.set(rs.getString(""));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setName(rs.getString("name"));
                            bean.set(rs.getDate(""));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setGoodsAttr4(rs.getString("goods_attr4"));
                            bean.setPic(rs.getString("pic"));
                            bean.setVolume(rs.getString("volume"));
                            bean.set(rs.getString(""));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setPrice(rs.getString("price"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.set(rs.getDate(""));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setGoodsAttr3(rs.getString("goods_attr3"));
                            bean.setGoodsAttr2(rs.getString("goods_attr2"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setGoodsAttr1(rs.getString("goods_attr1"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setPrimeCost(rs.getString("prime_cost"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setBaseStatus(rs.getString("base_status"));
                            bean.setGoodsAttr5(rs.getString("goods_attr5"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setPicAll(rs.getString("pic_all"));
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
    public List<B2bProductClientBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_product_client ";
            } else {
                sql = "select * from b2b_product_client where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bProductClientBean>() {
                        @Override
                        public B2bProductClientBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bProductClientBean bean = new B2bProductClientBean();
                            bean.set(rs.getString(""));
                            bean.setSize(rs.getString("size"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.set(rs.getString(""));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setName(rs.getString("name"));
                            bean.set(rs.getDate(""));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setGoodsAttr4(rs.getString("goods_attr4"));
                            bean.setPic(rs.getString("pic"));
                            bean.setVolume(rs.getString("volume"));
                            bean.set(rs.getString(""));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setPrice(rs.getString("price"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.set(rs.getDate(""));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setGoodsAttr3(rs.getString("goods_attr3"));
                            bean.setGoodsAttr2(rs.getString("goods_attr2"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setGoodsAttr1(rs.getString("goods_attr1"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setPrimeCost(rs.getString("prime_cost"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setBaseStatus(rs.getString("base_status"));
                            bean.setGoodsAttr5(rs.getString("goods_attr5"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setPicAll(rs.getString("pic_all"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bProductClientBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_product_client ";
            } else {
                sql = "select * from b2b_product_client where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bProductClientBean>() {
                        @Override
                        public B2bProductClientBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bProductClientBean bean = new B2bProductClientBean();
                            bean.set(rs.getString(""));
                            bean.setSize(rs.getString("size"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.set(rs.getString(""));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setName(rs.getString("name"));
                            bean.set(rs.getDate(""));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setGoodsAttr4(rs.getString("goods_attr4"));
                            bean.setPic(rs.getString("pic"));
                            bean.setVolume(rs.getString("volume"));
                            bean.set(rs.getString(""));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setPrice(rs.getString("price"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.set(rs.getDate(""));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setGoodsAttr3(rs.getString("goods_attr3"));
                            bean.setGoodsAttr2(rs.getString("goods_attr2"));
                            bean.setQuantity(rs.getString("quantity"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setGoodsAttr1(rs.getString("goods_attr1"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setPrimeCost(rs.getString("prime_cost"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setBaseStatus(rs.getString("base_status"));
                            bean.setGoodsAttr5(rs.getString("goods_attr5"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setPicAll(rs.getString("pic_all"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bProductClientBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_product_client ";
            } else {
                sql = "select count(1) numCount from b2b_product_client where " + whereSql;
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
    public int insertSelective(B2bProductClientBean b2bProductClient) {
        try {
            if (null == b2bProductClient) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_product_client(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bProductClient.get()) {
                columns.add("");
                values.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getSize()) {
                columns.add("size");
                values.add(b2bProductClient.getSize());
            }
            if (null != b2bProductClient.getSpuulierName()) {
                columns.add("spuulier_name");
                values.add(b2bProductClient.getSpuulierName());
            }
            if (null != b2bProductClient.get()) {
                columns.add("");
                values.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bProductClient.getSupplierId());
            }
            if (null != b2bProductClient.getName()) {
                columns.add("name");
                values.add(b2bProductClient.getName());
            }
            if (null != b2bProductClient.get()) {
                columns.add("");
                values.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getCapacity()) {
                columns.add("capacity");
                values.add(b2bProductClient.getCapacity());
            }
            if (null != b2bProductClient.getProDesc()) {
                columns.add("pro_desc");
                values.add(b2bProductClient.getProDesc());
            }
            if (null != b2bProductClient.getSupplierType()) {
                columns.add("supplier_type");
                values.add(b2bProductClient.getSupplierType());
            }
            if (null != b2bProductClient.getApplDesc()) {
                columns.add("appl_desc");
                values.add(b2bProductClient.getApplDesc());
            }
            if (null != b2bProductClient.getGoodsAttr4()) {
                columns.add("goods_attr4");
                values.add(b2bProductClient.getGoodsAttr4());
            }
            if (null != b2bProductClient.getPic()) {
                columns.add("pic");
                values.add(b2bProductClient.getPic());
            }
            if (null != b2bProductClient.getVolume()) {
                columns.add("volume");
                values.add(b2bProductClient.getVolume());
            }
            if (null != b2bProductClient.get()) {
                columns.add("");
                values.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getRestId()) {
                columns.add("rest_id");
                values.add(b2bProductClient.getRestId());
            }
            if (null != b2bProductClient.getPrice()) {
                columns.add("price");
                values.add(b2bProductClient.getPrice());
            }
            if (null != b2bProductClient.getParentIdPath()) {
                columns.add("parent_id_path");
                values.add(b2bProductClient.getParentIdPath());
            }
            if (null != b2bProductClient.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bProductClient.getProColorNo());
            }
            if (null != b2bProductClient.getRemarks()) {
                columns.add("remarks");
                values.add(b2bProductClient.getRemarks());
            }
            if (null != b2bProductClient.get()) {
                columns.add("");
                values.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bProductClient.getSpecsName());
            }
            if (null != b2bProductClient.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bProductClient.getSpecsId());
            }
            if (null != b2bProductClient.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bProductClient.getBaseProNo());
            }
            if (null != b2bProductClient.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bProductClient.getCategoryId());
            }
            if (null != b2bProductClient.getGoodsAttr3()) {
                columns.add("goods_attr3");
                values.add(b2bProductClient.getGoodsAttr3());
            }
            if (null != b2bProductClient.getGoodsAttr2()) {
                columns.add("goods_attr2");
                values.add(b2bProductClient.getGoodsAttr2());
            }
            if (null != b2bProductClient.getQuantity()) {
                columns.add("quantity");
                values.add(b2bProductClient.getQuantity());
            }
            if (null != b2bProductClient.getBarCode()) {
                columns.add("bar_code");
                values.add(b2bProductClient.getBarCode());
            }
            if (null != b2bProductClient.getGoodsAttr1()) {
                columns.add("goods_attr1");
                values.add(b2bProductClient.getGoodsAttr1());
            }
            if (null != b2bProductClient.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bProductClient.getTargetClient());
            }
            if (null != b2bProductClient.getPrimeCost()) {
                columns.add("prime_cost");
                values.add(b2bProductClient.getPrimeCost());
            }
            if (null != b2bProductClient.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bProductClient.getCategoryName());
            }
            if (null != b2bProductClient.getBaseStatus()) {
                columns.add("base_status");
                values.add(b2bProductClient.getBaseStatus());
            }
            if (null != b2bProductClient.getGoodsAttr5()) {
                columns.add("goods_attr5");
                values.add(b2bProductClient.getGoodsAttr5());
            }
            if (null != b2bProductClient.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bProductClient.getBaseProId());
            }
            if (null != b2bProductClient.getParentNamePath()) {
                columns.add("parent_name_path");
                values.add(b2bProductClient.getParentNamePath());
            }
            if (null != b2bProductClient.getProId()) {
                columns.add("pro_id");
                values.add(b2bProductClient.getProId());
            }
            if (null != b2bProductClient.getPicAll()) {
                columns.add("pic_all");
                values.add(b2bProductClient.getPicAll());
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
    public int insertSelectiveAndGetId(B2bProductClientBean b2bProductClient) {
        try {
            if (null == b2bProductClient) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_product_client(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bProductClient.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProductClient.getSize()) {
                columns.add("size");
                parameters.add(":size");
            }
            if (null != b2bProductClient.getSpuulierName()) {
                columns.add("spuulier_name");
                parameters.add(":spuulierName");
            }
            if (null != b2bProductClient.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProductClient.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bProductClient.getName()) {
                columns.add("name");
                parameters.add(":name");
            }
            if (null != b2bProductClient.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProductClient.getCapacity()) {
                columns.add("capacity");
                parameters.add(":capacity");
            }
            if (null != b2bProductClient.getProDesc()) {
                columns.add("pro_desc");
                parameters.add(":proDesc");
            }
            if (null != b2bProductClient.getSupplierType()) {
                columns.add("supplier_type");
                parameters.add(":supplierType");
            }
            if (null != b2bProductClient.getApplDesc()) {
                columns.add("appl_desc");
                parameters.add(":applDesc");
            }
            if (null != b2bProductClient.getGoodsAttr4()) {
                columns.add("goods_attr4");
                parameters.add(":goodsAttr4");
            }
            if (null != b2bProductClient.getPic()) {
                columns.add("pic");
                parameters.add(":pic");
            }
            if (null != b2bProductClient.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bProductClient.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProductClient.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bProductClient.getPrice()) {
                columns.add("price");
                parameters.add(":price");
            }
            if (null != b2bProductClient.getParentIdPath()) {
                columns.add("parent_id_path");
                parameters.add(":parentIdPath");
            }
            if (null != b2bProductClient.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bProductClient.getRemarks()) {
                columns.add("remarks");
                parameters.add(":remarks");
            }
            if (null != b2bProductClient.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProductClient.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }
            if (null != b2bProductClient.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bProductClient.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bProductClient.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bProductClient.getGoodsAttr3()) {
                columns.add("goods_attr3");
                parameters.add(":goodsAttr3");
            }
            if (null != b2bProductClient.getGoodsAttr2()) {
                columns.add("goods_attr2");
                parameters.add(":goodsAttr2");
            }
            if (null != b2bProductClient.getQuantity()) {
                columns.add("quantity");
                parameters.add(":quantity");
            }
            if (null != b2bProductClient.getBarCode()) {
                columns.add("bar_code");
                parameters.add(":barCode");
            }
            if (null != b2bProductClient.getGoodsAttr1()) {
                columns.add("goods_attr1");
                parameters.add(":goodsAttr1");
            }
            if (null != b2bProductClient.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bProductClient.getPrimeCost()) {
                columns.add("prime_cost");
                parameters.add(":primeCost");
            }
            if (null != b2bProductClient.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bProductClient.getBaseStatus()) {
                columns.add("base_status");
                parameters.add(":baseStatus");
            }
            if (null != b2bProductClient.getGoodsAttr5()) {
                columns.add("goods_attr5");
                parameters.add(":goodsAttr5");
            }
            if (null != b2bProductClient.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bProductClient.getParentNamePath()) {
                columns.add("parent_name_path");
                parameters.add(":parentNamePath");
            }
            if (null != b2bProductClient.getProId()) {
                columns.add("pro_id");
                parameters.add(":proId");
            }
            if (null != b2bProductClient.getPicAll()) {
                columns.add("pic_all");
                parameters.add(":picAll");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bProductClient);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bProductClient.setProId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bProductClientBean b2bProductClient) {
        try {
            if (null == b2bProductClient.getProId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_product_client SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bProductClient.get()) {
                updateSql.add(" = ?");
                params.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getSize()) {
                updateSql.add("size = ?");
                params.add(b2bProductClient.getSize());
            }
            if (null != b2bProductClient.getSpuulierName()) {
                updateSql.add("spuulier_name = ?");
                params.add(b2bProductClient.getSpuulierName());
            }
            if (null != b2bProductClient.get()) {
                updateSql.add(" = ?");
                params.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bProductClient.getSupplierId());
            }
            if (null != b2bProductClient.getName()) {
                updateSql.add("name = ?");
                params.add(b2bProductClient.getName());
            }
            if (null != b2bProductClient.get()) {
                updateSql.add(" = ?");
                params.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getCapacity()) {
                updateSql.add("capacity = ?");
                params.add(b2bProductClient.getCapacity());
            }
            if (null != b2bProductClient.getProDesc()) {
                updateSql.add("pro_desc = ?");
                params.add(b2bProductClient.getProDesc());
            }
            if (null != b2bProductClient.getSupplierType()) {
                updateSql.add("supplier_type = ?");
                params.add(b2bProductClient.getSupplierType());
            }
            if (null != b2bProductClient.getApplDesc()) {
                updateSql.add("appl_desc = ?");
                params.add(b2bProductClient.getApplDesc());
            }
            if (null != b2bProductClient.getGoodsAttr4()) {
                updateSql.add("goods_attr4 = ?");
                params.add(b2bProductClient.getGoodsAttr4());
            }
            if (null != b2bProductClient.getPic()) {
                updateSql.add("pic = ?");
                params.add(b2bProductClient.getPic());
            }
            if (null != b2bProductClient.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bProductClient.getVolume());
            }
            if (null != b2bProductClient.get()) {
                updateSql.add(" = ?");
                params.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bProductClient.getRestId());
            }
            if (null != b2bProductClient.getPrice()) {
                updateSql.add("price = ?");
                params.add(b2bProductClient.getPrice());
            }
            if (null != b2bProductClient.getParentIdPath()) {
                updateSql.add("parent_id_path = ?");
                params.add(b2bProductClient.getParentIdPath());
            }
            if (null != b2bProductClient.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bProductClient.getProColorNo());
            }
            if (null != b2bProductClient.getRemarks()) {
                updateSql.add("remarks = ?");
                params.add(b2bProductClient.getRemarks());
            }
            if (null != b2bProductClient.get()) {
                updateSql.add(" = ?");
                params.add(b2bProductClient.get());
            }
            if (null != b2bProductClient.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bProductClient.getSpecsName());
            }
            if (null != b2bProductClient.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bProductClient.getSpecsId());
            }
            if (null != b2bProductClient.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bProductClient.getBaseProNo());
            }
            if (null != b2bProductClient.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bProductClient.getCategoryId());
            }
            if (null != b2bProductClient.getGoodsAttr3()) {
                updateSql.add("goods_attr3 = ?");
                params.add(b2bProductClient.getGoodsAttr3());
            }
            if (null != b2bProductClient.getGoodsAttr2()) {
                updateSql.add("goods_attr2 = ?");
                params.add(b2bProductClient.getGoodsAttr2());
            }
            if (null != b2bProductClient.getQuantity()) {
                updateSql.add("quantity = ?");
                params.add(b2bProductClient.getQuantity());
            }
            if (null != b2bProductClient.getBarCode()) {
                updateSql.add("bar_code = ?");
                params.add(b2bProductClient.getBarCode());
            }
            if (null != b2bProductClient.getGoodsAttr1()) {
                updateSql.add("goods_attr1 = ?");
                params.add(b2bProductClient.getGoodsAttr1());
            }
            if (null != b2bProductClient.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bProductClient.getTargetClient());
            }
            if (null != b2bProductClient.getPrimeCost()) {
                updateSql.add("prime_cost = ?");
                params.add(b2bProductClient.getPrimeCost());
            }
            if (null != b2bProductClient.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bProductClient.getCategoryName());
            }
            if (null != b2bProductClient.getBaseStatus()) {
                updateSql.add("base_status = ?");
                params.add(b2bProductClient.getBaseStatus());
            }
            if (null != b2bProductClient.getGoodsAttr5()) {
                updateSql.add("goods_attr5 = ?");
                params.add(b2bProductClient.getGoodsAttr5());
            }
            if (null != b2bProductClient.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bProductClient.getBaseProId());
            }
            if (null != b2bProductClient.getParentNamePath()) {
                updateSql.add("parent_name_path = ?");
                params.add(b2bProductClient.getParentNamePath());
            }
            if (null != b2bProductClient.getProId()) {
                updateSql.add("pro_id = ?");
                params.add(b2bProductClient.getProId());
            }
            if (null != b2bProductClient.getPicAll()) {
                updateSql.add("pic_all = ?");
                params.add(b2bProductClient.getPicAll());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE pro_id = ?");
            params.add(b2bProductClient.getProId());

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

        String sql = "delete from b2b_product_client where pro_id  = ?";
        return jdbcTemplate.update(sql, proId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_product_client where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bProductClientBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bProductClientBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bProductClientBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bProductClientBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bProductClientBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bProductClientBean> findAll(Pageable arg0) {
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
	public void delete(B2bProductClientBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bProductClientBean> arg0) {
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
	public Iterable<B2bProductClientBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bProductClientBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bProductClientBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bProductClientBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bProductClientBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bProductClientBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bProductClientBean> findAll(
			Specification<B2bProductClientBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bProductClientBean> findAll(
			Specification<B2bProductClientBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bProductClientBean> findAll(
			Specification<B2bProductClientBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bProductClientBean findOne(
			Specification<B2bProductClientBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
