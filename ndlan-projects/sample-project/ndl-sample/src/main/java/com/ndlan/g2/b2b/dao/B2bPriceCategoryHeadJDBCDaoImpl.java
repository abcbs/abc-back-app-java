package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bPriceCategoryHeadBean;

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

@Repository("b2bPriceCategoryHeadJDBCDao")
public class B2bPriceCategoryHeadJDBCDaoImpl implements B2bPriceCategoryHeadJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bPriceCategoryHeadBean selectByPrimaryKey(String priCatHeadId) {
        try {
            String sql = "select * from b2b_price_category_head where pri_cat_head_id = ?";

            List<B2bPriceCategoryHeadBean> resultList = this.jdbcTemplate.query(sql, new Object[]{priCatHeadId},
                    new RowMapper<B2bPriceCategoryHeadBean>() {
                        @Override
                        public B2bPriceCategoryHeadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bPriceCategoryHeadBean bean = new B2bPriceCategoryHeadBean();
                            bean.setVolume(rs.getString("volume"));
                            bean.setSize(rs.getString("size"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setCapacity(rs.getString("capacity"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setName(rs.getString("name"));
                            bean.setStatus(rs.getString("status"));
                            bean.setPic(rs.getString("pic"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setRemarks(rs.getString("remarks"));
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
    public List<B2bPriceCategoryHeadBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_price_category_head ";
            } else {
                sql = "select * from b2b_price_category_head where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bPriceCategoryHeadBean>() {
                        @Override
                        public B2bPriceCategoryHeadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bPriceCategoryHeadBean bean = new B2bPriceCategoryHeadBean();
                            bean.setVolume(rs.getString("volume"));
                            bean.setSize(rs.getString("size"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setCapacity(rs.getString("capacity"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setName(rs.getString("name"));
                            bean.setStatus(rs.getString("status"));
                            bean.setPic(rs.getString("pic"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setRemarks(rs.getString("remarks"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bPriceCategoryHeadBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_price_category_head ";
            } else {
                sql = "select * from b2b_price_category_head where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bPriceCategoryHeadBean>() {
                        @Override
                        public B2bPriceCategoryHeadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bPriceCategoryHeadBean bean = new B2bPriceCategoryHeadBean();
                            bean.setVolume(rs.getString("volume"));
                            bean.setSize(rs.getString("size"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setSpuulierName(rs.getString("spuulier_name"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setCapacity(rs.getString("capacity"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setName(rs.getString("name"));
                            bean.setStatus(rs.getString("status"));
                            bean.setPic(rs.getString("pic"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setCreateBy(rs.getString("create_by"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setRemarks(rs.getString("remarks"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bPriceCategoryHeadBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_price_category_head ";
            } else {
                sql = "select count(1) numCount from b2b_price_category_head where " + whereSql;
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
    public int insertSelective(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        try {
            if (null == b2bPriceCategoryHead) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_price_category_head(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bPriceCategoryHead.getVolume()) {
                columns.add("volume");
                values.add(b2bPriceCategoryHead.getVolume());
            }
            if (null != b2bPriceCategoryHead.getSize()) {
                columns.add("size");
                values.add(b2bPriceCategoryHead.getSize());
            }
            if (null != b2bPriceCategoryHead.getBarCode()) {
                columns.add("bar_code");
                values.add(b2bPriceCategoryHead.getBarCode());
            }
            if (null != b2bPriceCategoryHead.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bPriceCategoryHead.getCategoryName());
            }
            if (null != b2bPriceCategoryHead.getProDesc()) {
                columns.add("pro_desc");
                values.add(b2bPriceCategoryHead.getProDesc());
            }
            if (null != b2bPriceCategoryHead.getAgencyName()) {
                columns.add("agency_name");
                values.add(b2bPriceCategoryHead.getAgencyName());
            }
            if (null != b2bPriceCategoryHead.getApplDesc()) {
                columns.add("appl_desc");
                values.add(b2bPriceCategoryHead.getApplDesc());
            }
            if (null != b2bPriceCategoryHead.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bPriceCategoryHead.getSupplierId());
            }
            if (null != b2bPriceCategoryHead.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bPriceCategoryHead.getCreateTime());
            }
            if (null != b2bPriceCategoryHead.getPriCatHeadId()) {
                columns.add("pri_cat_head_id");
                values.add(b2bPriceCategoryHead.getPriCatHeadId());
            }
            if (null != b2bPriceCategoryHead.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bPriceCategoryHead.getUpdateBy());
            }
            if (null != b2bPriceCategoryHead.getSpuulierName()) {
                columns.add("spuulier_name");
                values.add(b2bPriceCategoryHead.getSpuulierName());
            }
            if (null != b2bPriceCategoryHead.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bPriceCategoryHead.getTargetClient());
            }
            if (null != b2bPriceCategoryHead.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bPriceCategoryHead.getProColorNo());
            }
            if (null != b2bPriceCategoryHead.getCapacity()) {
                columns.add("capacity");
                values.add(b2bPriceCategoryHead.getCapacity());
            }
            if (null != b2bPriceCategoryHead.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bPriceCategoryHead.getUpdateTime());
            }
            if (null != b2bPriceCategoryHead.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bPriceCategoryHead.getBaseProId());
            }
            if (null != b2bPriceCategoryHead.getAgencyId()) {
                columns.add("agency_id");
                values.add(b2bPriceCategoryHead.getAgencyId());
            }
            if (null != b2bPriceCategoryHead.getName()) {
                columns.add("name");
                values.add(b2bPriceCategoryHead.getName());
            }
            if (null != b2bPriceCategoryHead.getStatus()) {
                columns.add("status");
                values.add(b2bPriceCategoryHead.getStatus());
            }
            if (null != b2bPriceCategoryHead.getPic()) {
                columns.add("pic");
                values.add(b2bPriceCategoryHead.getPic());
            }
            if (null != b2bPriceCategoryHead.getProId()) {
                columns.add("pro_id");
                values.add(b2bPriceCategoryHead.getProId());
            }
            if (null != b2bPriceCategoryHead.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bPriceCategoryHead.getBaseProNo());
            }
            if (null != b2bPriceCategoryHead.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bPriceCategoryHead.getCreateBy());
            }
            if (null != b2bPriceCategoryHead.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bPriceCategoryHead.getCategoryId());
            }
            if (null != b2bPriceCategoryHead.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bPriceCategoryHead.getSpecsName());
            }
            if (null != b2bPriceCategoryHead.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bPriceCategoryHead.getSpecsId());
            }
            if (null != b2bPriceCategoryHead.getRemarks()) {
                columns.add("remarks");
                values.add(b2bPriceCategoryHead.getRemarks());
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
    public int insertSelectiveAndGetId(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        try {
            if (null == b2bPriceCategoryHead) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_price_category_head(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bPriceCategoryHead.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bPriceCategoryHead.getSize()) {
                columns.add("size");
                parameters.add(":size");
            }
            if (null != b2bPriceCategoryHead.getBarCode()) {
                columns.add("bar_code");
                parameters.add(":barCode");
            }
            if (null != b2bPriceCategoryHead.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bPriceCategoryHead.getProDesc()) {
                columns.add("pro_desc");
                parameters.add(":proDesc");
            }
            if (null != b2bPriceCategoryHead.getAgencyName()) {
                columns.add("agency_name");
                parameters.add(":agencyName");
            }
            if (null != b2bPriceCategoryHead.getApplDesc()) {
                columns.add("appl_desc");
                parameters.add(":applDesc");
            }
            if (null != b2bPriceCategoryHead.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bPriceCategoryHead.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bPriceCategoryHead.getPriCatHeadId()) {
                columns.add("pri_cat_head_id");
                parameters.add(":priCatHeadId");
            }
            if (null != b2bPriceCategoryHead.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bPriceCategoryHead.getSpuulierName()) {
                columns.add("spuulier_name");
                parameters.add(":spuulierName");
            }
            if (null != b2bPriceCategoryHead.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bPriceCategoryHead.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bPriceCategoryHead.getCapacity()) {
                columns.add("capacity");
                parameters.add(":capacity");
            }
            if (null != b2bPriceCategoryHead.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bPriceCategoryHead.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bPriceCategoryHead.getAgencyId()) {
                columns.add("agency_id");
                parameters.add(":agencyId");
            }
            if (null != b2bPriceCategoryHead.getName()) {
                columns.add("name");
                parameters.add(":name");
            }
            if (null != b2bPriceCategoryHead.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }
            if (null != b2bPriceCategoryHead.getPic()) {
                columns.add("pic");
                parameters.add(":pic");
            }
            if (null != b2bPriceCategoryHead.getProId()) {
                columns.add("pro_id");
                parameters.add(":proId");
            }
            if (null != b2bPriceCategoryHead.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bPriceCategoryHead.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }
            if (null != b2bPriceCategoryHead.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bPriceCategoryHead.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }
            if (null != b2bPriceCategoryHead.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bPriceCategoryHead.getRemarks()) {
                columns.add("remarks");
                parameters.add(":remarks");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bPriceCategoryHead);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bPriceCategoryHead.setPriCatHeadId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        try {
            if (null == b2bPriceCategoryHead.getPriCatHeadId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_price_category_head SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bPriceCategoryHead.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bPriceCategoryHead.getVolume());
            }
            if (null != b2bPriceCategoryHead.getSize()) {
                updateSql.add("size = ?");
                params.add(b2bPriceCategoryHead.getSize());
            }
            if (null != b2bPriceCategoryHead.getBarCode()) {
                updateSql.add("bar_code = ?");
                params.add(b2bPriceCategoryHead.getBarCode());
            }
            if (null != b2bPriceCategoryHead.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bPriceCategoryHead.getCategoryName());
            }
            if (null != b2bPriceCategoryHead.getProDesc()) {
                updateSql.add("pro_desc = ?");
                params.add(b2bPriceCategoryHead.getProDesc());
            }
            if (null != b2bPriceCategoryHead.getAgencyName()) {
                updateSql.add("agency_name = ?");
                params.add(b2bPriceCategoryHead.getAgencyName());
            }
            if (null != b2bPriceCategoryHead.getApplDesc()) {
                updateSql.add("appl_desc = ?");
                params.add(b2bPriceCategoryHead.getApplDesc());
            }
            if (null != b2bPriceCategoryHead.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bPriceCategoryHead.getSupplierId());
            }
            if (null != b2bPriceCategoryHead.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bPriceCategoryHead.getCreateTime());
            }
            if (null != b2bPriceCategoryHead.getPriCatHeadId()) {
                updateSql.add("pri_cat_head_id = ?");
                params.add(b2bPriceCategoryHead.getPriCatHeadId());
            }
            if (null != b2bPriceCategoryHead.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bPriceCategoryHead.getUpdateBy());
            }
            if (null != b2bPriceCategoryHead.getSpuulierName()) {
                updateSql.add("spuulier_name = ?");
                params.add(b2bPriceCategoryHead.getSpuulierName());
            }
            if (null != b2bPriceCategoryHead.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bPriceCategoryHead.getTargetClient());
            }
            if (null != b2bPriceCategoryHead.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bPriceCategoryHead.getProColorNo());
            }
            if (null != b2bPriceCategoryHead.getCapacity()) {
                updateSql.add("capacity = ?");
                params.add(b2bPriceCategoryHead.getCapacity());
            }
            if (null != b2bPriceCategoryHead.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bPriceCategoryHead.getUpdateTime());
            }
            if (null != b2bPriceCategoryHead.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bPriceCategoryHead.getBaseProId());
            }
            if (null != b2bPriceCategoryHead.getAgencyId()) {
                updateSql.add("agency_id = ?");
                params.add(b2bPriceCategoryHead.getAgencyId());
            }
            if (null != b2bPriceCategoryHead.getName()) {
                updateSql.add("name = ?");
                params.add(b2bPriceCategoryHead.getName());
            }
            if (null != b2bPriceCategoryHead.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bPriceCategoryHead.getStatus());
            }
            if (null != b2bPriceCategoryHead.getPic()) {
                updateSql.add("pic = ?");
                params.add(b2bPriceCategoryHead.getPic());
            }
            if (null != b2bPriceCategoryHead.getProId()) {
                updateSql.add("pro_id = ?");
                params.add(b2bPriceCategoryHead.getProId());
            }
            if (null != b2bPriceCategoryHead.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bPriceCategoryHead.getBaseProNo());
            }
            if (null != b2bPriceCategoryHead.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bPriceCategoryHead.getCreateBy());
            }
            if (null != b2bPriceCategoryHead.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bPriceCategoryHead.getCategoryId());
            }
            if (null != b2bPriceCategoryHead.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bPriceCategoryHead.getSpecsName());
            }
            if (null != b2bPriceCategoryHead.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bPriceCategoryHead.getSpecsId());
            }
            if (null != b2bPriceCategoryHead.getRemarks()) {
                updateSql.add("remarks = ?");
                params.add(b2bPriceCategoryHead.getRemarks());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE pri_cat_head_id = ?");
            params.add(b2bPriceCategoryHead.getPriCatHeadId());

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
    public int deleteByPrimaryKey(String priCatHeadId) {
            if(null ==  priCatHeadId) {
            return 0;
        }

        String sql = "delete from b2b_price_category_head where pri_cat_head_id  = ?";
        return jdbcTemplate.update(sql, priCatHeadId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_price_category_head where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bPriceCategoryHeadBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bPriceCategoryHeadBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bPriceCategoryHeadBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bPriceCategoryHeadBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bPriceCategoryHeadBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bPriceCategoryHeadBean> findAll(Pageable arg0) {
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
	public void delete(B2bPriceCategoryHeadBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bPriceCategoryHeadBean> arg0) {
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
	public Iterable<B2bPriceCategoryHeadBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bPriceCategoryHeadBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bPriceCategoryHeadBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bPriceCategoryHeadBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bPriceCategoryHeadBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bPriceCategoryHeadBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bPriceCategoryHeadBean> findAll(
			Specification<B2bPriceCategoryHeadBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bPriceCategoryHeadBean> findAll(
			Specification<B2bPriceCategoryHeadBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bPriceCategoryHeadBean> findAll(
			Specification<B2bPriceCategoryHeadBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bPriceCategoryHeadBean findOne(
			Specification<B2bPriceCategoryHeadBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
