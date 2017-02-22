package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bOrderStatusHisBean;

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

@Repository("b2bOrderStatusHisDao")
public class B2bOrderStatusHisDaoImpl implements B2bOrderStatusHisDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bOrderStatusHisBean selectByPrimaryKey(String orderStatusHisId) {
        try {
            String sql = "select * from b2b_order_status_his where order_status_his_id = ?";

            List<B2bOrderStatusHisBean> resultList = this.jdbcTemplate.query(sql, new Object[]{orderStatusHisId},
                    new RowMapper<B2bOrderStatusHisBean>() {
                        @Override
                        public B2bOrderStatusHisBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bOrderStatusHisBean bean = new B2bOrderStatusHisBean();
                            bean.setOrignVersion(rs.getLong("orign_version"));
                            bean.set(rs.getDate(""));
                            Timestamp orignUpdateTimeTimestamp = rs.getTimestamp("orign_update_time");
                            if (null != orignUpdateTimeTimestamp) {
                                bean.setOrignUpdateTime(new Date(orignUpdateTimeTimestamp.getTime()));
                            }
                            bean.set(rs.getString(""));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            Timestamp orignCreateTimeTimestamp = rs.getTimestamp("orign_create_time");
                            if (null != orignCreateTimeTimestamp) {
                                bean.setOrignCreateTime(new Date(orignCreateTimeTimestamp.getTime()));
                            }
                            bean.setOrignUpdateBy(rs.getString("orign_update_by"));
                            bean.set(rs.getDate(""));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setOrignCreateBy(rs.getString("orign_create_by"));
                            bean.setOrignSynVersion(rs.getLong("orign_syn_version"));
                            bean.setOrderStatusHisId(rs.getString("order_status_his_id"));
                            bean.set(rs.getString(""));
                            bean.setUpdateReason(rs.getString("update_reason"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.set(rs.getString(""));
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
    public List<B2bOrderStatusHisBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_status_his ";
            } else {
                sql = "select * from b2b_order_status_his where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderStatusHisBean>() {
                        @Override
                        public B2bOrderStatusHisBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bOrderStatusHisBean bean = new B2bOrderStatusHisBean();
                            bean.setOrignVersion(rs.getLong("orign_version"));
                            bean.set(rs.getDate(""));
                            Timestamp orignUpdateTimeTimestamp = rs.getTimestamp("orign_update_time");
                            if (null != orignUpdateTimeTimestamp) {
                                bean.setOrignUpdateTime(new Date(orignUpdateTimeTimestamp.getTime()));
                            }
                            bean.set(rs.getString(""));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            Timestamp orignCreateTimeTimestamp = rs.getTimestamp("orign_create_time");
                            if (null != orignCreateTimeTimestamp) {
                                bean.setOrignCreateTime(new Date(orignCreateTimeTimestamp.getTime()));
                            }
                            bean.setOrignUpdateBy(rs.getString("orign_update_by"));
                            bean.set(rs.getDate(""));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setOrignCreateBy(rs.getString("orign_create_by"));
                            bean.setOrignSynVersion(rs.getLong("orign_syn_version"));
                            bean.setOrderStatusHisId(rs.getString("order_status_his_id"));
                            bean.set(rs.getString(""));
                            bean.setUpdateReason(rs.getString("update_reason"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.set(rs.getString(""));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderStatusHisBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order_status_his ";
            } else {
                sql = "select * from b2b_order_status_his where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderStatusHisBean>() {
                        @Override
                        public B2bOrderStatusHisBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bOrderStatusHisBean bean = new B2bOrderStatusHisBean();
                            bean.setOrignVersion(rs.getLong("orign_version"));
                            bean.set(rs.getDate(""));
                            Timestamp orignUpdateTimeTimestamp = rs.getTimestamp("orign_update_time");
                            if (null != orignUpdateTimeTimestamp) {
                                bean.setOrignUpdateTime(new Date(orignUpdateTimeTimestamp.getTime()));
                            }
                            bean.set(rs.getString(""));
                            bean.setOrignStatus(rs.getString("orign_status"));
                            Timestamp orignCreateTimeTimestamp = rs.getTimestamp("orign_create_time");
                            if (null != orignCreateTimeTimestamp) {
                                bean.setOrignCreateTime(new Date(orignCreateTimeTimestamp.getTime()));
                            }
                            bean.setOrignUpdateBy(rs.getString("orign_update_by"));
                            bean.set(rs.getDate(""));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setOrignCreateBy(rs.getString("orign_create_by"));
                            bean.setOrignSynVersion(rs.getLong("orign_syn_version"));
                            bean.setOrderStatusHisId(rs.getString("order_status_his_id"));
                            bean.set(rs.getString(""));
                            bean.setUpdateReason(rs.getString("update_reason"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.set(rs.getString(""));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderStatusHisBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_order_status_his ";
            } else {
                sql = "select count(1) numCount from b2b_order_status_his where " + whereSql;
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
    public int insertSelective(B2bOrderStatusHisBean b2bOrderStatusHis) {
        try {
            if (null == b2bOrderStatusHis) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_status_his(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bOrderStatusHis.getOrignVersion()) {
                columns.add("orign_version");
                values.add(b2bOrderStatusHis.getOrignVersion());
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                values.add(b2bOrderStatusHis.get());
            }
            if (null != b2bOrderStatusHis.getOrignUpdateTime()) {
                columns.add("orign_update_time");
                values.add(b2bOrderStatusHis.getOrignUpdateTime());
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                values.add(b2bOrderStatusHis.get());
            }
            if (null != b2bOrderStatusHis.getOrignStatus()) {
                columns.add("orign_status");
                values.add(b2bOrderStatusHis.getOrignStatus());
            }
            if (null != b2bOrderStatusHis.getOrignCreateTime()) {
                columns.add("orign_create_time");
                values.add(b2bOrderStatusHis.getOrignCreateTime());
            }
            if (null != b2bOrderStatusHis.getOrignUpdateBy()) {
                columns.add("orign_update_by");
                values.add(b2bOrderStatusHis.getOrignUpdateBy());
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                values.add(b2bOrderStatusHis.get());
            }
            if (null != b2bOrderStatusHis.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                values.add(b2bOrderStatusHis.getOrderPkgCode());
            }
            if (null != b2bOrderStatusHis.getOrderPkgId()) {
                columns.add("order_pkg_id");
                values.add(b2bOrderStatusHis.getOrderPkgId());
            }
            if (null != b2bOrderStatusHis.getOrignCreateBy()) {
                columns.add("orign_create_by");
                values.add(b2bOrderStatusHis.getOrignCreateBy());
            }
            if (null != b2bOrderStatusHis.getOrignSynVersion()) {
                columns.add("orign_syn_version");
                values.add(b2bOrderStatusHis.getOrignSynVersion());
            }
            if (null != b2bOrderStatusHis.getOrderStatusHisId()) {
                columns.add("order_status_his_id");
                values.add(b2bOrderStatusHis.getOrderStatusHisId());
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                values.add(b2bOrderStatusHis.get());
            }
            if (null != b2bOrderStatusHis.getUpdateReason()) {
                columns.add("update_reason");
                values.add(b2bOrderStatusHis.getUpdateReason());
            }
            if (null != b2bOrderStatusHis.getOrderHeadId()) {
                columns.add("order_head_id");
                values.add(b2bOrderStatusHis.getOrderHeadId());
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                values.add(b2bOrderStatusHis.get());
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
    public int insertSelectiveAndGetId(B2bOrderStatusHisBean b2bOrderStatusHis) {
        try {
            if (null == b2bOrderStatusHis) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order_status_his(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bOrderStatusHis.getOrignVersion()) {
                columns.add("orign_version");
                parameters.add(":orignVersion");
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderStatusHis.getOrignUpdateTime()) {
                columns.add("orign_update_time");
                parameters.add(":orignUpdateTime");
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderStatusHis.getOrignStatus()) {
                columns.add("orign_status");
                parameters.add(":orignStatus");
            }
            if (null != b2bOrderStatusHis.getOrignCreateTime()) {
                columns.add("orign_create_time");
                parameters.add(":orignCreateTime");
            }
            if (null != b2bOrderStatusHis.getOrignUpdateBy()) {
                columns.add("orign_update_by");
                parameters.add(":orignUpdateBy");
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderStatusHis.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                parameters.add(":orderPkgCode");
            }
            if (null != b2bOrderStatusHis.getOrderPkgId()) {
                columns.add("order_pkg_id");
                parameters.add(":orderPkgId");
            }
            if (null != b2bOrderStatusHis.getOrignCreateBy()) {
                columns.add("orign_create_by");
                parameters.add(":orignCreateBy");
            }
            if (null != b2bOrderStatusHis.getOrignSynVersion()) {
                columns.add("orign_syn_version");
                parameters.add(":orignSynVersion");
            }
            if (null != b2bOrderStatusHis.getOrderStatusHisId()) {
                columns.add("order_status_his_id");
                parameters.add(":orderStatusHisId");
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrderStatusHis.getUpdateReason()) {
                columns.add("update_reason");
                parameters.add(":updateReason");
            }
            if (null != b2bOrderStatusHis.getOrderHeadId()) {
                columns.add("order_head_id");
                parameters.add(":orderHeadId");
            }
            if (null != b2bOrderStatusHis.get()) {
                columns.add("");
                parameters.add(":");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bOrderStatusHis);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bOrderStatusHis.setOrderStatusHisId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bOrderStatusHisBean b2bOrderStatusHis) {
        try {
            if (null == b2bOrderStatusHis.getOrderStatusHisId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_order_status_his SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bOrderStatusHis.getOrignVersion()) {
                updateSql.add("orign_version = ?");
                params.add(b2bOrderStatusHis.getOrignVersion());
            }
            if (null != b2bOrderStatusHis.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderStatusHis.get());
            }
            if (null != b2bOrderStatusHis.getOrignUpdateTime()) {
                updateSql.add("orign_update_time = ?");
                params.add(b2bOrderStatusHis.getOrignUpdateTime());
            }
            if (null != b2bOrderStatusHis.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderStatusHis.get());
            }
            if (null != b2bOrderStatusHis.getOrignStatus()) {
                updateSql.add("orign_status = ?");
                params.add(b2bOrderStatusHis.getOrignStatus());
            }
            if (null != b2bOrderStatusHis.getOrignCreateTime()) {
                updateSql.add("orign_create_time = ?");
                params.add(b2bOrderStatusHis.getOrignCreateTime());
            }
            if (null != b2bOrderStatusHis.getOrignUpdateBy()) {
                updateSql.add("orign_update_by = ?");
                params.add(b2bOrderStatusHis.getOrignUpdateBy());
            }
            if (null != b2bOrderStatusHis.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderStatusHis.get());
            }
            if (null != b2bOrderStatusHis.getOrderPkgCode()) {
                updateSql.add("order_pkg_code = ?");
                params.add(b2bOrderStatusHis.getOrderPkgCode());
            }
            if (null != b2bOrderStatusHis.getOrderPkgId()) {
                updateSql.add("order_pkg_id = ?");
                params.add(b2bOrderStatusHis.getOrderPkgId());
            }
            if (null != b2bOrderStatusHis.getOrignCreateBy()) {
                updateSql.add("orign_create_by = ?");
                params.add(b2bOrderStatusHis.getOrignCreateBy());
            }
            if (null != b2bOrderStatusHis.getOrignSynVersion()) {
                updateSql.add("orign_syn_version = ?");
                params.add(b2bOrderStatusHis.getOrignSynVersion());
            }
            if (null != b2bOrderStatusHis.getOrderStatusHisId()) {
                updateSql.add("order_status_his_id = ?");
                params.add(b2bOrderStatusHis.getOrderStatusHisId());
            }
            if (null != b2bOrderStatusHis.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderStatusHis.get());
            }
            if (null != b2bOrderStatusHis.getUpdateReason()) {
                updateSql.add("update_reason = ?");
                params.add(b2bOrderStatusHis.getUpdateReason());
            }
            if (null != b2bOrderStatusHis.getOrderHeadId()) {
                updateSql.add("order_head_id = ?");
                params.add(b2bOrderStatusHis.getOrderHeadId());
            }
            if (null != b2bOrderStatusHis.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrderStatusHis.get());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE order_status_his_id = ?");
            params.add(b2bOrderStatusHis.getOrderStatusHisId());

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
    public int deleteByPrimaryKey(String orderStatusHisId) {
            if(null ==  orderStatusHisId) {
            return 0;
        }

        String sql = "delete from b2b_order_status_his where order_status_his_id  = ?";
        return jdbcTemplate.update(sql, orderStatusHisId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_order_status_his where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bOrderStatusHisBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bOrderStatusHisBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bOrderStatusHisBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bOrderStatusHisBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bOrderStatusHisBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderStatusHisBean> findAll(Pageable arg0) {
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
	public void delete(B2bOrderStatusHisBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bOrderStatusHisBean> arg0) {
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
	public Iterable<B2bOrderStatusHisBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bOrderStatusHisBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderStatusHisBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderStatusHisBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderStatusHisBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bOrderStatusHisBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bOrderStatusHisBean> findAll(
			Specification<B2bOrderStatusHisBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderStatusHisBean> findAll(
			Specification<B2bOrderStatusHisBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bOrderStatusHisBean> findAll(
			Specification<B2bOrderStatusHisBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderStatusHisBean findOne(
			Specification<B2bOrderStatusHisBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
