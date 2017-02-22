package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bPriceCategoryDetailHisBean;

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

@Repository("b2bPriceCategoryDetailHisJDBCDao")
public class B2bPriceCategoryDetailHisJDBCDaoImpl implements B2bPriceCategoryDetailHisJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bPriceCategoryDetailHisBean selectByPrimaryKey(String priCtyDtlhisId) {
        try {
            String sql = "select * from b2b_price_category_detail_his where pri_cty_dtlhis_id = ?";

            List<B2bPriceCategoryDetailHisBean> resultList = this.jdbcTemplate.query(sql, new Object[]{priCtyDtlhisId},
                    new RowMapper<B2bPriceCategoryDetailHisBean>() {
                        @Override
                        public B2bPriceCategoryDetailHisBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bPriceCategoryDetailHisBean bean = new B2bPriceCategoryDetailHisBean();
                            bean.setStatus(rs.getString("status"));
                            bean.setPriCtyDtlhisId(rs.getString("pri_cty_dtlhis_id"));
                            Timestamp expiryDateTimestamp = rs.getTimestamp("expiry_date");
                            if (null != expiryDateTimestamp) {
                                bean.setExpiryDate(new Date(expiryDateTimestamp.getTime()));
                            }
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setOrignCreateBy(rs.getString("orign_create_by"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            Timestamp effectiveDateTimestamp = rs.getTimestamp("effective_date");
                            if (null != effectiveDateTimestamp) {
                                bean.setEffectiveDate(new Date(effectiveDateTimestamp.getTime()));
                            }
                            bean.setOrignVersion(rs.getLong("orign_version"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            Timestamp orignCreateTimeTimestamp = rs.getTimestamp("orign_create_time");
                            if (null != orignCreateTimeTimestamp) {
                                bean.setOrignCreateTime(new Date(orignCreateTimeTimestamp.getTime()));
                            }
                            bean.setOrignSynVersion(rs.getLong("orign_syn_version"));
                            bean.setPrice(rs.getString("price"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            bean.setOrignUpdateBy(rs.getString("orign_update_by"));
                            Timestamp orignUpdateTimeTimestamp = rs.getTimestamp("orign_update_time");
                            if (null != orignUpdateTimeTimestamp) {
                                bean.setOrignUpdateTime(new Date(orignUpdateTimeTimestamp.getTime()));
                            }
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
    public List<B2bPriceCategoryDetailHisBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_price_category_detail_his ";
            } else {
                sql = "select * from b2b_price_category_detail_his where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bPriceCategoryDetailHisBean>() {
                        @Override
                        public B2bPriceCategoryDetailHisBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bPriceCategoryDetailHisBean bean = new B2bPriceCategoryDetailHisBean();
                            bean.setStatus(rs.getString("status"));
                            bean.setPriCtyDtlhisId(rs.getString("pri_cty_dtlhis_id"));
                            Timestamp expiryDateTimestamp = rs.getTimestamp("expiry_date");
                            if (null != expiryDateTimestamp) {
                                bean.setExpiryDate(new Date(expiryDateTimestamp.getTime()));
                            }
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setOrignCreateBy(rs.getString("orign_create_by"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            Timestamp effectiveDateTimestamp = rs.getTimestamp("effective_date");
                            if (null != effectiveDateTimestamp) {
                                bean.setEffectiveDate(new Date(effectiveDateTimestamp.getTime()));
                            }
                            bean.setOrignVersion(rs.getLong("orign_version"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            Timestamp orignCreateTimeTimestamp = rs.getTimestamp("orign_create_time");
                            if (null != orignCreateTimeTimestamp) {
                                bean.setOrignCreateTime(new Date(orignCreateTimeTimestamp.getTime()));
                            }
                            bean.setOrignSynVersion(rs.getLong("orign_syn_version"));
                            bean.setPrice(rs.getString("price"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            bean.setOrignUpdateBy(rs.getString("orign_update_by"));
                            Timestamp orignUpdateTimeTimestamp = rs.getTimestamp("orign_update_time");
                            if (null != orignUpdateTimeTimestamp) {
                                bean.setOrignUpdateTime(new Date(orignUpdateTimeTimestamp.getTime()));
                            }
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bPriceCategoryDetailHisBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_price_category_detail_his ";
            } else {
                sql = "select * from b2b_price_category_detail_his where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bPriceCategoryDetailHisBean>() {
                        @Override
                        public B2bPriceCategoryDetailHisBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bPriceCategoryDetailHisBean bean = new B2bPriceCategoryDetailHisBean();
                            bean.setStatus(rs.getString("status"));
                            bean.setPriCtyDtlhisId(rs.getString("pri_cty_dtlhis_id"));
                            Timestamp expiryDateTimestamp = rs.getTimestamp("expiry_date");
                            if (null != expiryDateTimestamp) {
                                bean.setExpiryDate(new Date(expiryDateTimestamp.getTime()));
                            }
                            bean.setRemarks(rs.getString("remarks"));
                            bean.setStartPointQyt(rs.getString("start_point_qyt"));
                            bean.setOrignCreateBy(rs.getString("orign_create_by"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            Timestamp effectiveDateTimestamp = rs.getTimestamp("effective_date");
                            if (null != effectiveDateTimestamp) {
                                bean.setEffectiveDate(new Date(effectiveDateTimestamp.getTime()));
                            }
                            bean.setOrignVersion(rs.getLong("orign_version"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setPriCatHeadId(rs.getString("pri_cat_head_id"));
                            Timestamp orignCreateTimeTimestamp = rs.getTimestamp("orign_create_time");
                            if (null != orignCreateTimeTimestamp) {
                                bean.setOrignCreateTime(new Date(orignCreateTimeTimestamp.getTime()));
                            }
                            bean.setOrignSynVersion(rs.getLong("orign_syn_version"));
                            bean.setPrice(rs.getString("price"));
                            bean.setPriCatLineId(rs.getString("pri_cat_line_id"));
                            bean.setOrignUpdateBy(rs.getString("orign_update_by"));
                            Timestamp orignUpdateTimeTimestamp = rs.getTimestamp("orign_update_time");
                            if (null != orignUpdateTimeTimestamp) {
                                bean.setOrignUpdateTime(new Date(orignUpdateTimeTimestamp.getTime()));
                            }
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bPriceCategoryDetailHisBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_price_category_detail_his ";
            } else {
                sql = "select count(1) numCount from b2b_price_category_detail_his where " + whereSql;
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
    public int insertSelective(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        try {
            if (null == b2bPriceCategoryDetailHis) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_price_category_detail_his(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bPriceCategoryDetailHis.getStatus()) {
                columns.add("status");
                values.add(b2bPriceCategoryDetailHis.getStatus());
            }
            if (null != b2bPriceCategoryDetailHis.getPriCtyDtlhisId()) {
                columns.add("pri_cty_dtlhis_id");
                values.add(b2bPriceCategoryDetailHis.getPriCtyDtlhisId());
            }
            if (null != b2bPriceCategoryDetailHis.getExpiryDate()) {
                columns.add("expiry_date");
                values.add(b2bPriceCategoryDetailHis.getExpiryDate());
            }
            if (null != b2bPriceCategoryDetailHis.getRemarks()) {
                columns.add("remarks");
                values.add(b2bPriceCategoryDetailHis.getRemarks());
            }
            if (null != b2bPriceCategoryDetailHis.getStartPointQyt()) {
                columns.add("start_point_qyt");
                values.add(b2bPriceCategoryDetailHis.getStartPointQyt());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignCreateBy()) {
                columns.add("orign_create_by");
                values.add(b2bPriceCategoryDetailHis.getOrignCreateBy());
            }
            if (null != b2bPriceCategoryDetailHis.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bPriceCategoryDetailHis.getUpdateTime());
            }
            if (null != b2bPriceCategoryDetailHis.getEffectiveDate()) {
                columns.add("effective_date");
                values.add(b2bPriceCategoryDetailHis.getEffectiveDate());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignVersion()) {
                columns.add("orign_version");
                values.add(b2bPriceCategoryDetailHis.getOrignVersion());
            }
            if (null != b2bPriceCategoryDetailHis.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bPriceCategoryDetailHis.getUpdateBy());
            }
            if (null != b2bPriceCategoryDetailHis.getPriCatHeadId()) {
                columns.add("pri_cat_head_id");
                values.add(b2bPriceCategoryDetailHis.getPriCatHeadId());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignCreateTime()) {
                columns.add("orign_create_time");
                values.add(b2bPriceCategoryDetailHis.getOrignCreateTime());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignSynVersion()) {
                columns.add("orign_syn_version");
                values.add(b2bPriceCategoryDetailHis.getOrignSynVersion());
            }
            if (null != b2bPriceCategoryDetailHis.getPrice()) {
                columns.add("price");
                values.add(b2bPriceCategoryDetailHis.getPrice());
            }
            if (null != b2bPriceCategoryDetailHis.getPriCatLineId()) {
                columns.add("pri_cat_line_id");
                values.add(b2bPriceCategoryDetailHis.getPriCatLineId());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignUpdateBy()) {
                columns.add("orign_update_by");
                values.add(b2bPriceCategoryDetailHis.getOrignUpdateBy());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignUpdateTime()) {
                columns.add("orign_update_time");
                values.add(b2bPriceCategoryDetailHis.getOrignUpdateTime());
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
    public int insertSelectiveAndGetId(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        try {
            if (null == b2bPriceCategoryDetailHis) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_price_category_detail_his(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bPriceCategoryDetailHis.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }
            if (null != b2bPriceCategoryDetailHis.getPriCtyDtlhisId()) {
                columns.add("pri_cty_dtlhis_id");
                parameters.add(":priCtyDtlhisId");
            }
            if (null != b2bPriceCategoryDetailHis.getExpiryDate()) {
                columns.add("expiry_date");
                parameters.add(":expiryDate");
            }
            if (null != b2bPriceCategoryDetailHis.getRemarks()) {
                columns.add("remarks");
                parameters.add(":remarks");
            }
            if (null != b2bPriceCategoryDetailHis.getStartPointQyt()) {
                columns.add("start_point_qyt");
                parameters.add(":startPointQyt");
            }
            if (null != b2bPriceCategoryDetailHis.getOrignCreateBy()) {
                columns.add("orign_create_by");
                parameters.add(":orignCreateBy");
            }
            if (null != b2bPriceCategoryDetailHis.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bPriceCategoryDetailHis.getEffectiveDate()) {
                columns.add("effective_date");
                parameters.add(":effectiveDate");
            }
            if (null != b2bPriceCategoryDetailHis.getOrignVersion()) {
                columns.add("orign_version");
                parameters.add(":orignVersion");
            }
            if (null != b2bPriceCategoryDetailHis.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bPriceCategoryDetailHis.getPriCatHeadId()) {
                columns.add("pri_cat_head_id");
                parameters.add(":priCatHeadId");
            }
            if (null != b2bPriceCategoryDetailHis.getOrignCreateTime()) {
                columns.add("orign_create_time");
                parameters.add(":orignCreateTime");
            }
            if (null != b2bPriceCategoryDetailHis.getOrignSynVersion()) {
                columns.add("orign_syn_version");
                parameters.add(":orignSynVersion");
            }
            if (null != b2bPriceCategoryDetailHis.getPrice()) {
                columns.add("price");
                parameters.add(":price");
            }
            if (null != b2bPriceCategoryDetailHis.getPriCatLineId()) {
                columns.add("pri_cat_line_id");
                parameters.add(":priCatLineId");
            }
            if (null != b2bPriceCategoryDetailHis.getOrignUpdateBy()) {
                columns.add("orign_update_by");
                parameters.add(":orignUpdateBy");
            }
            if (null != b2bPriceCategoryDetailHis.getOrignUpdateTime()) {
                columns.add("orign_update_time");
                parameters.add(":orignUpdateTime");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bPriceCategoryDetailHis);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bPriceCategoryDetailHis.setPriCtyDtlhisId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        try {
            if (null == b2bPriceCategoryDetailHis.getPriCtyDtlhisId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_price_category_detail_his SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bPriceCategoryDetailHis.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bPriceCategoryDetailHis.getStatus());
            }
            if (null != b2bPriceCategoryDetailHis.getPriCtyDtlhisId()) {
                updateSql.add("pri_cty_dtlhis_id = ?");
                params.add(b2bPriceCategoryDetailHis.getPriCtyDtlhisId());
            }
            if (null != b2bPriceCategoryDetailHis.getExpiryDate()) {
                updateSql.add("expiry_date = ?");
                params.add(b2bPriceCategoryDetailHis.getExpiryDate());
            }
            if (null != b2bPriceCategoryDetailHis.getRemarks()) {
                updateSql.add("remarks = ?");
                params.add(b2bPriceCategoryDetailHis.getRemarks());
            }
            if (null != b2bPriceCategoryDetailHis.getStartPointQyt()) {
                updateSql.add("start_point_qyt = ?");
                params.add(b2bPriceCategoryDetailHis.getStartPointQyt());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignCreateBy()) {
                updateSql.add("orign_create_by = ?");
                params.add(b2bPriceCategoryDetailHis.getOrignCreateBy());
            }
            if (null != b2bPriceCategoryDetailHis.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bPriceCategoryDetailHis.getUpdateTime());
            }
            if (null != b2bPriceCategoryDetailHis.getEffectiveDate()) {
                updateSql.add("effective_date = ?");
                params.add(b2bPriceCategoryDetailHis.getEffectiveDate());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignVersion()) {
                updateSql.add("orign_version = ?");
                params.add(b2bPriceCategoryDetailHis.getOrignVersion());
            }
            if (null != b2bPriceCategoryDetailHis.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bPriceCategoryDetailHis.getUpdateBy());
            }
            if (null != b2bPriceCategoryDetailHis.getPriCatHeadId()) {
                updateSql.add("pri_cat_head_id = ?");
                params.add(b2bPriceCategoryDetailHis.getPriCatHeadId());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignCreateTime()) {
                updateSql.add("orign_create_time = ?");
                params.add(b2bPriceCategoryDetailHis.getOrignCreateTime());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignSynVersion()) {
                updateSql.add("orign_syn_version = ?");
                params.add(b2bPriceCategoryDetailHis.getOrignSynVersion());
            }
            if (null != b2bPriceCategoryDetailHis.getPrice()) {
                updateSql.add("price = ?");
                params.add(b2bPriceCategoryDetailHis.getPrice());
            }
            if (null != b2bPriceCategoryDetailHis.getPriCatLineId()) {
                updateSql.add("pri_cat_line_id = ?");
                params.add(b2bPriceCategoryDetailHis.getPriCatLineId());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignUpdateBy()) {
                updateSql.add("orign_update_by = ?");
                params.add(b2bPriceCategoryDetailHis.getOrignUpdateBy());
            }
            if (null != b2bPriceCategoryDetailHis.getOrignUpdateTime()) {
                updateSql.add("orign_update_time = ?");
                params.add(b2bPriceCategoryDetailHis.getOrignUpdateTime());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE pri_cty_dtlhis_id = ?");
            params.add(b2bPriceCategoryDetailHis.getPriCtyDtlhisId());

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
    public int deleteByPrimaryKey(String priCtyDtlhisId) {
            if(null ==  priCtyDtlhisId) {
            return 0;
        }

        String sql = "delete from b2b_price_category_detail_his where pri_cty_dtlhis_id  = ?";
        return jdbcTemplate.update(sql, priCtyDtlhisId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_price_category_detail_his where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bPriceCategoryDetailHisBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bPriceCategoryDetailHisBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bPriceCategoryDetailHisBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bPriceCategoryDetailHisBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bPriceCategoryDetailHisBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bPriceCategoryDetailHisBean> findAll(Pageable arg0) {
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
	public void delete(B2bPriceCategoryDetailHisBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bPriceCategoryDetailHisBean> arg0) {
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
	public Iterable<B2bPriceCategoryDetailHisBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bPriceCategoryDetailHisBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bPriceCategoryDetailHisBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bPriceCategoryDetailHisBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bPriceCategoryDetailHisBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bPriceCategoryDetailHisBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bPriceCategoryDetailHisBean> findAll(
			Specification<B2bPriceCategoryDetailHisBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bPriceCategoryDetailHisBean> findAll(
			Specification<B2bPriceCategoryDetailHisBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bPriceCategoryDetailHisBean> findAll(
			Specification<B2bPriceCategoryDetailHisBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bPriceCategoryDetailHisBean findOne(
			Specification<B2bPriceCategoryDetailHisBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
