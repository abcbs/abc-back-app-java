package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bPriceCategoryDetailBean;

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

@Repository("b2bPriceCategoryDetailJDBCDao")
public class B2bPriceCategoryDetailJDBCDaoImpl implements B2bPriceCategoryDetailJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bPriceCategoryDetailBean selectByPrimaryKey(String priCatLineId) {
        try {
            String sql = "select * from b2b_price_category_detail where pri_cat_line_id = ?";

            List<B2bPriceCategoryDetailBean> resultList = this.jdbcTemplate.query(sql, new Object[]{priCatLineId},
                    new RowMapper<B2bPriceCategoryDetailBean>() {
                        @Override
                        public B2bPriceCategoryDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bPriceCategoryDetailBean bean = new B2bPriceCategoryDetailBean();
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            Timestamp expiryDateTimestamp = rs.getTimestamp("expiry_date");
                            if (null != expiryDateTimestamp) {
                                bean.setExpiryDate(new Date(expiryDateTimestamp.getTime()));
                            }
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setStatus(rs.getString("status"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setName(rs.getString("name"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setPromotionPrice(rs.getString("promotion_price"));
                            Timestamp effectiveDateTimestamp = rs.getTimestamp("effective_date");
                            if (null != effectiveDateTimestamp) {
                                bean.setEffectiveDate(new Date(effectiveDateTimestamp.getTime()));
                            }
                            bean.setPrice(rs.getString("price"));
                            bean.setProId(rs.getString("pro_id"));
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
    public List<B2bPriceCategoryDetailBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_price_category_detail ";
            } else {
                sql = "select * from b2b_price_category_detail where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bPriceCategoryDetailBean>() {
                        @Override
                        public B2bPriceCategoryDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bPriceCategoryDetailBean bean = new B2bPriceCategoryDetailBean();
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            Timestamp expiryDateTimestamp = rs.getTimestamp("expiry_date");
                            if (null != expiryDateTimestamp) {
                                bean.setExpiryDate(new Date(expiryDateTimestamp.getTime()));
                            }
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setStatus(rs.getString("status"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setName(rs.getString("name"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setPromotionPrice(rs.getString("promotion_price"));
                            Timestamp effectiveDateTimestamp = rs.getTimestamp("effective_date");
                            if (null != effectiveDateTimestamp) {
                                bean.setEffectiveDate(new Date(effectiveDateTimestamp.getTime()));
                            }
                            bean.setPrice(rs.getString("price"));
                            bean.setProId(rs.getString("pro_id"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bPriceCategoryDetailBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_price_category_detail ";
            } else {
                sql = "select * from b2b_price_category_detail where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bPriceCategoryDetailBean>() {
                        @Override
                        public B2bPriceCategoryDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bPriceCategoryDetailBean bean = new B2bPriceCategoryDetailBean();
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            Timestamp expiryDateTimestamp = rs.getTimestamp("expiry_date");
                            if (null != expiryDateTimestamp) {
                                bean.setExpiryDate(new Date(expiryDateTimestamp.getTime()));
                            }
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setStatus(rs.getString("status"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setName(rs.getString("name"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setPromotionPrice(rs.getString("promotion_price"));
                            Timestamp effectiveDateTimestamp = rs.getTimestamp("effective_date");
                            if (null != effectiveDateTimestamp) {
                                bean.setEffectiveDate(new Date(effectiveDateTimestamp.getTime()));
                            }
                            bean.setPrice(rs.getString("price"));
                            bean.setProId(rs.getString("pro_id"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bPriceCategoryDetailBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_price_category_detail ";
            } else {
                sql = "select count(1) numCount from b2b_price_category_detail where " + whereSql;
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
    public int insertSelective(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        try {
            if (null == b2bPriceCategoryDetail) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_price_category_detail(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bPriceCategoryDetail.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bPriceCategoryDetail.getUpdateTime());
            }
            if (null != b2bPriceCategoryDetail.getExpiryDate()) {
                columns.add("expiry_date");
                values.add(b2bPriceCategoryDetail.getExpiryDate());
            }
            if (null != b2bPriceCategoryDetail.getAgencyId()) {
                columns.add("agency_id");
                values.add(b2bPriceCategoryDetail.getAgencyId());
            }
            if (null != b2bPriceCategoryDetail.getParentIdPath()) {
                columns.add("parent_id_path");
                values.add(b2bPriceCategoryDetail.getParentIdPath());
            }
            if (null != b2bPriceCategoryDetail.getPriCatHeadId()) {
                columns.add("pri_cat_head_id");
                values.add(b2bPriceCategoryDetail.getPriCatHeadId());
            }
            if (null != b2bPriceCategoryDetail.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bPriceCategoryDetail.getBaseProId());
            }
            if (null != b2bPriceCategoryDetail.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bPriceCategoryDetail.getBaseProNo());
            }
            if (null != b2bPriceCategoryDetail.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bPriceCategoryDetail.getSupplierId());
            }
            if (null != b2bPriceCategoryDetail.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bPriceCategoryDetail.getSpecsId());
            }
            if (null != b2bPriceCategoryDetail.getStartPointQyt()) {
                columns.add("start_point_qyt");
                values.add(b2bPriceCategoryDetail.getStartPointQyt());
            }
            if (null != b2bPriceCategoryDetail.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bPriceCategoryDetail.getProColorNo());
            }
            if (null != b2bPriceCategoryDetail.getParentNamePath()) {
                columns.add("parent_name_path");
                values.add(b2bPriceCategoryDetail.getParentNamePath());
            }
            if (null != b2bPriceCategoryDetail.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bPriceCategoryDetail.getCategoryId());
            }
            if (null != b2bPriceCategoryDetail.getRemarks()) {
                columns.add("remarks");
                values.add(b2bPriceCategoryDetail.getRemarks());
            }
            if (null != b2bPriceCategoryDetail.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bPriceCategoryDetail.getCreateBy());
            }
            if (null != b2bPriceCategoryDetail.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bPriceCategoryDetail.getCategoryName());
            }
            if (null != b2bPriceCategoryDetail.getPriCatLineId()) {
                columns.add("pri_cat_line_id");
                values.add(b2bPriceCategoryDetail.getPriCatLineId());
            }
            if (null != b2bPriceCategoryDetail.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bPriceCategoryDetail.getCreateTime());
            }
            if (null != b2bPriceCategoryDetail.getStatus()) {
                columns.add("status");
                values.add(b2bPriceCategoryDetail.getStatus());
            }
            if (null != b2bPriceCategoryDetail.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bPriceCategoryDetail.getSpecsName());
            }
            if (null != b2bPriceCategoryDetail.getAgencyName()) {
                columns.add("agency_name");
                values.add(b2bPriceCategoryDetail.getAgencyName());
            }
            if (null != b2bPriceCategoryDetail.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bPriceCategoryDetail.getUpdateBy());
            }
            if (null != b2bPriceCategoryDetail.getSpuulierName()) {
                columns.add("spuulier_name");
                values.add(b2bPriceCategoryDetail.getSpuulierName());
            }
            if (null != b2bPriceCategoryDetail.getName()) {
                columns.add("name");
                values.add(b2bPriceCategoryDetail.getName());
            }
            if (null != b2bPriceCategoryDetail.getVolume()) {
                columns.add("volume");
                values.add(b2bPriceCategoryDetail.getVolume());
            }
            if (null != b2bPriceCategoryDetail.getPromotionPrice()) {
                columns.add("promotion_price");
                values.add(b2bPriceCategoryDetail.getPromotionPrice());
            }
            if (null != b2bPriceCategoryDetail.getEffectiveDate()) {
                columns.add("effective_date");
                values.add(b2bPriceCategoryDetail.getEffectiveDate());
            }
            if (null != b2bPriceCategoryDetail.getPrice()) {
                columns.add("price");
                values.add(b2bPriceCategoryDetail.getPrice());
            }
            if (null != b2bPriceCategoryDetail.getProId()) {
                columns.add("pro_id");
                values.add(b2bPriceCategoryDetail.getProId());
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
    public int insertSelectiveAndGetId(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        try {
            if (null == b2bPriceCategoryDetail) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_price_category_detail(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bPriceCategoryDetail.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bPriceCategoryDetail.getExpiryDate()) {
                columns.add("expiry_date");
                parameters.add(":expiryDate");
            }
            if (null != b2bPriceCategoryDetail.getAgencyId()) {
                columns.add("agency_id");
                parameters.add(":agencyId");
            }
            if (null != b2bPriceCategoryDetail.getParentIdPath()) {
                columns.add("parent_id_path");
                parameters.add(":parentIdPath");
            }
            if (null != b2bPriceCategoryDetail.getPriCatHeadId()) {
                columns.add("pri_cat_head_id");
                parameters.add(":priCatHeadId");
            }
            if (null != b2bPriceCategoryDetail.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bPriceCategoryDetail.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bPriceCategoryDetail.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bPriceCategoryDetail.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bPriceCategoryDetail.getStartPointQyt()) {
                columns.add("start_point_qyt");
                parameters.add(":startPointQyt");
            }
            if (null != b2bPriceCategoryDetail.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bPriceCategoryDetail.getParentNamePath()) {
                columns.add("parent_name_path");
                parameters.add(":parentNamePath");
            }
            if (null != b2bPriceCategoryDetail.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bPriceCategoryDetail.getRemarks()) {
                columns.add("remarks");
                parameters.add(":remarks");
            }
            if (null != b2bPriceCategoryDetail.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }
            if (null != b2bPriceCategoryDetail.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bPriceCategoryDetail.getPriCatLineId()) {
                columns.add("pri_cat_line_id");
                parameters.add(":priCatLineId");
            }
            if (null != b2bPriceCategoryDetail.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bPriceCategoryDetail.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }
            if (null != b2bPriceCategoryDetail.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }
            if (null != b2bPriceCategoryDetail.getAgencyName()) {
                columns.add("agency_name");
                parameters.add(":agencyName");
            }
            if (null != b2bPriceCategoryDetail.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bPriceCategoryDetail.getSpuulierName()) {
                columns.add("spuulier_name");
                parameters.add(":spuulierName");
            }
            if (null != b2bPriceCategoryDetail.getName()) {
                columns.add("name");
                parameters.add(":name");
            }
            if (null != b2bPriceCategoryDetail.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bPriceCategoryDetail.getPromotionPrice()) {
                columns.add("promotion_price");
                parameters.add(":promotionPrice");
            }
            if (null != b2bPriceCategoryDetail.getEffectiveDate()) {
                columns.add("effective_date");
                parameters.add(":effectiveDate");
            }
            if (null != b2bPriceCategoryDetail.getPrice()) {
                columns.add("price");
                parameters.add(":price");
            }
            if (null != b2bPriceCategoryDetail.getProId()) {
                columns.add("pro_id");
                parameters.add(":proId");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bPriceCategoryDetail);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bPriceCategoryDetail.setPriCatLineId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        try {
            if (null == b2bPriceCategoryDetail.getPriCatLineId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_price_category_detail SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bPriceCategoryDetail.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bPriceCategoryDetail.getUpdateTime());
            }
            if (null != b2bPriceCategoryDetail.getExpiryDate()) {
                updateSql.add("expiry_date = ?");
                params.add(b2bPriceCategoryDetail.getExpiryDate());
            }
            if (null != b2bPriceCategoryDetail.getAgencyId()) {
                updateSql.add("agency_id = ?");
                params.add(b2bPriceCategoryDetail.getAgencyId());
            }
            if (null != b2bPriceCategoryDetail.getParentIdPath()) {
                updateSql.add("parent_id_path = ?");
                params.add(b2bPriceCategoryDetail.getParentIdPath());
            }
            if (null != b2bPriceCategoryDetail.getPriCatHeadId()) {
                updateSql.add("pri_cat_head_id = ?");
                params.add(b2bPriceCategoryDetail.getPriCatHeadId());
            }
            if (null != b2bPriceCategoryDetail.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bPriceCategoryDetail.getBaseProId());
            }
            if (null != b2bPriceCategoryDetail.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bPriceCategoryDetail.getBaseProNo());
            }
            if (null != b2bPriceCategoryDetail.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bPriceCategoryDetail.getSupplierId());
            }
            if (null != b2bPriceCategoryDetail.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bPriceCategoryDetail.getSpecsId());
            }
            if (null != b2bPriceCategoryDetail.getStartPointQyt()) {
                updateSql.add("start_point_qyt = ?");
                params.add(b2bPriceCategoryDetail.getStartPointQyt());
            }
            if (null != b2bPriceCategoryDetail.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bPriceCategoryDetail.getProColorNo());
            }
            if (null != b2bPriceCategoryDetail.getParentNamePath()) {
                updateSql.add("parent_name_path = ?");
                params.add(b2bPriceCategoryDetail.getParentNamePath());
            }
            if (null != b2bPriceCategoryDetail.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bPriceCategoryDetail.getCategoryId());
            }
            if (null != b2bPriceCategoryDetail.getRemarks()) {
                updateSql.add("remarks = ?");
                params.add(b2bPriceCategoryDetail.getRemarks());
            }
            if (null != b2bPriceCategoryDetail.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bPriceCategoryDetail.getCreateBy());
            }
            if (null != b2bPriceCategoryDetail.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bPriceCategoryDetail.getCategoryName());
            }
            if (null != b2bPriceCategoryDetail.getPriCatLineId()) {
                updateSql.add("pri_cat_line_id = ?");
                params.add(b2bPriceCategoryDetail.getPriCatLineId());
            }
            if (null != b2bPriceCategoryDetail.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bPriceCategoryDetail.getCreateTime());
            }
            if (null != b2bPriceCategoryDetail.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bPriceCategoryDetail.getStatus());
            }
            if (null != b2bPriceCategoryDetail.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bPriceCategoryDetail.getSpecsName());
            }
            if (null != b2bPriceCategoryDetail.getAgencyName()) {
                updateSql.add("agency_name = ?");
                params.add(b2bPriceCategoryDetail.getAgencyName());
            }
            if (null != b2bPriceCategoryDetail.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bPriceCategoryDetail.getUpdateBy());
            }
            if (null != b2bPriceCategoryDetail.getSpuulierName()) {
                updateSql.add("spuulier_name = ?");
                params.add(b2bPriceCategoryDetail.getSpuulierName());
            }
            if (null != b2bPriceCategoryDetail.getName()) {
                updateSql.add("name = ?");
                params.add(b2bPriceCategoryDetail.getName());
            }
            if (null != b2bPriceCategoryDetail.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bPriceCategoryDetail.getVolume());
            }
            if (null != b2bPriceCategoryDetail.getPromotionPrice()) {
                updateSql.add("promotion_price = ?");
                params.add(b2bPriceCategoryDetail.getPromotionPrice());
            }
            if (null != b2bPriceCategoryDetail.getEffectiveDate()) {
                updateSql.add("effective_date = ?");
                params.add(b2bPriceCategoryDetail.getEffectiveDate());
            }
            if (null != b2bPriceCategoryDetail.getPrice()) {
                updateSql.add("price = ?");
                params.add(b2bPriceCategoryDetail.getPrice());
            }
            if (null != b2bPriceCategoryDetail.getProId()) {
                updateSql.add("pro_id = ?");
                params.add(b2bPriceCategoryDetail.getProId());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE pri_cat_line_id = ?");
            params.add(b2bPriceCategoryDetail.getPriCatLineId());

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
    public int deleteByPrimaryKey(String priCatLineId) {
            if(null ==  priCatLineId) {
            return 0;
        }

        String sql = "delete from b2b_price_category_detail where pri_cat_line_id  = ?";
        return jdbcTemplate.update(sql, priCatLineId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_price_category_detail where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bPriceCategoryDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bPriceCategoryDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bPriceCategoryDetailBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bPriceCategoryDetailBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bPriceCategoryDetailBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bPriceCategoryDetailBean> findAll(Pageable arg0) {
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
	public void delete(B2bPriceCategoryDetailBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bPriceCategoryDetailBean> arg0) {
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
	public Iterable<B2bPriceCategoryDetailBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bPriceCategoryDetailBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bPriceCategoryDetailBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bPriceCategoryDetailBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bPriceCategoryDetailBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bPriceCategoryDetailBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bPriceCategoryDetailBean> findAll(
			Specification<B2bPriceCategoryDetailBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bPriceCategoryDetailBean> findAll(
			Specification<B2bPriceCategoryDetailBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bPriceCategoryDetailBean> findAll(
			Specification<B2bPriceCategoryDetailBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bPriceCategoryDetailBean findOne(
			Specification<B2bPriceCategoryDetailBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
