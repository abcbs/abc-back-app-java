package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bLogisticsRealtimeBean;

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

@Repository("b2bLogisticsRealtimeJDBCDao")
public class B2bLogisticsRealtimeJDBCDaoImpl implements B2bLogisticsRealtimeJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bLogisticsRealtimeBean selectByPrimaryKey(String logisticsRealtimeId) {
        try {
            String sql = "select * from b2b_logistics_realtime where logistics_realtime_id = ?";

            List<B2bLogisticsRealtimeBean> resultList = this.jdbcTemplate.query(sql, new Object[]{logisticsRealtimeId},
                    new RowMapper<B2bLogisticsRealtimeBean>() {
                        @Override
                        public B2bLogisticsRealtimeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bLogisticsRealtimeBean bean = new B2bLogisticsRealtimeBean();
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setLogisticsRealtimeId(rs.getString("logistics_realtime_id"));
                            bean.setLogisticsLineCode(rs.getString("logistics_line_code"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setRemake(rs.getString("remake"));
                            bean.setCurrAddress(rs.getString("curr_address"));
                            bean.setNextAddress(rs.getString("next_address"));
                            bean.setStatus(rs.getString("status"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            Timestamp currDateTimestamp = rs.getTimestamp("curr_date");
                            if (null != currDateTimestamp) {
                                bean.setCurrDate(new Date(currDateTimestamp.getTime()));
                            }
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            bean.setCreateBy(rs.getString("create_by"));
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
    public List<B2bLogisticsRealtimeBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_logistics_realtime ";
            } else {
                sql = "select * from b2b_logistics_realtime where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bLogisticsRealtimeBean>() {
                        @Override
                        public B2bLogisticsRealtimeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bLogisticsRealtimeBean bean = new B2bLogisticsRealtimeBean();
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setLogisticsRealtimeId(rs.getString("logistics_realtime_id"));
                            bean.setLogisticsLineCode(rs.getString("logistics_line_code"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setRemake(rs.getString("remake"));
                            bean.setCurrAddress(rs.getString("curr_address"));
                            bean.setNextAddress(rs.getString("next_address"));
                            bean.setStatus(rs.getString("status"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            Timestamp currDateTimestamp = rs.getTimestamp("curr_date");
                            if (null != currDateTimestamp) {
                                bean.setCurrDate(new Date(currDateTimestamp.getTime()));
                            }
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            bean.setCreateBy(rs.getString("create_by"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bLogisticsRealtimeBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_logistics_realtime ";
            } else {
                sql = "select * from b2b_logistics_realtime where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bLogisticsRealtimeBean>() {
                        @Override
                        public B2bLogisticsRealtimeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bLogisticsRealtimeBean bean = new B2bLogisticsRealtimeBean();
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setLogisticsRealtimeId(rs.getString("logistics_realtime_id"));
                            bean.setLogisticsLineCode(rs.getString("logistics_line_code"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setRemake(rs.getString("remake"));
                            bean.setCurrAddress(rs.getString("curr_address"));
                            bean.setNextAddress(rs.getString("next_address"));
                            bean.setStatus(rs.getString("status"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            Timestamp currDateTimestamp = rs.getTimestamp("curr_date");
                            if (null != currDateTimestamp) {
                                bean.setCurrDate(new Date(currDateTimestamp.getTime()));
                            }
                            bean.setLogisticsLineId(rs.getString("logistics_line_id"));
                            bean.setCreateBy(rs.getString("create_by"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bLogisticsRealtimeBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_logistics_realtime ";
            } else {
                sql = "select count(1) numCount from b2b_logistics_realtime where " + whereSql;
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
    public int insertSelective(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        try {
            if (null == b2bLogisticsRealtime) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_logistics_realtime(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bLogisticsRealtime.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bLogisticsRealtime.getCreateTime());
            }
            if (null != b2bLogisticsRealtime.getLogisticsRealtimeId()) {
                columns.add("logistics_realtime_id");
                values.add(b2bLogisticsRealtime.getLogisticsRealtimeId());
            }
            if (null != b2bLogisticsRealtime.getLogisticsLineCode()) {
                columns.add("logistics_line_code");
                values.add(b2bLogisticsRealtime.getLogisticsLineCode());
            }
            if (null != b2bLogisticsRealtime.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bLogisticsRealtime.getUpdateTime());
            }
            if (null != b2bLogisticsRealtime.getRemake()) {
                columns.add("remake");
                values.add(b2bLogisticsRealtime.getRemake());
            }
            if (null != b2bLogisticsRealtime.getCurrAddress()) {
                columns.add("curr_address");
                values.add(b2bLogisticsRealtime.getCurrAddress());
            }
            if (null != b2bLogisticsRealtime.getNextAddress()) {
                columns.add("next_address");
                values.add(b2bLogisticsRealtime.getNextAddress());
            }
            if (null != b2bLogisticsRealtime.getStatus()) {
                columns.add("status");
                values.add(b2bLogisticsRealtime.getStatus());
            }
            if (null != b2bLogisticsRealtime.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bLogisticsRealtime.getUpdateBy());
            }
            if (null != b2bLogisticsRealtime.getCurrDate()) {
                columns.add("curr_date");
                values.add(b2bLogisticsRealtime.getCurrDate());
            }
            if (null != b2bLogisticsRealtime.getLogisticsLineId()) {
                columns.add("logistics_line_id");
                values.add(b2bLogisticsRealtime.getLogisticsLineId());
            }
            if (null != b2bLogisticsRealtime.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bLogisticsRealtime.getCreateBy());
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
    public int insertSelectiveAndGetId(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        try {
            if (null == b2bLogisticsRealtime) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_logistics_realtime(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bLogisticsRealtime.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bLogisticsRealtime.getLogisticsRealtimeId()) {
                columns.add("logistics_realtime_id");
                parameters.add(":logisticsRealtimeId");
            }
            if (null != b2bLogisticsRealtime.getLogisticsLineCode()) {
                columns.add("logistics_line_code");
                parameters.add(":logisticsLineCode");
            }
            if (null != b2bLogisticsRealtime.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bLogisticsRealtime.getRemake()) {
                columns.add("remake");
                parameters.add(":remake");
            }
            if (null != b2bLogisticsRealtime.getCurrAddress()) {
                columns.add("curr_address");
                parameters.add(":currAddress");
            }
            if (null != b2bLogisticsRealtime.getNextAddress()) {
                columns.add("next_address");
                parameters.add(":nextAddress");
            }
            if (null != b2bLogisticsRealtime.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }
            if (null != b2bLogisticsRealtime.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bLogisticsRealtime.getCurrDate()) {
                columns.add("curr_date");
                parameters.add(":currDate");
            }
            if (null != b2bLogisticsRealtime.getLogisticsLineId()) {
                columns.add("logistics_line_id");
                parameters.add(":logisticsLineId");
            }
            if (null != b2bLogisticsRealtime.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bLogisticsRealtime);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bLogisticsRealtime.setLogisticsRealtimeId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        try {
            if (null == b2bLogisticsRealtime.getLogisticsRealtimeId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_logistics_realtime SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bLogisticsRealtime.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bLogisticsRealtime.getCreateTime());
            }
            if (null != b2bLogisticsRealtime.getLogisticsRealtimeId()) {
                updateSql.add("logistics_realtime_id = ?");
                params.add(b2bLogisticsRealtime.getLogisticsRealtimeId());
            }
            if (null != b2bLogisticsRealtime.getLogisticsLineCode()) {
                updateSql.add("logistics_line_code = ?");
                params.add(b2bLogisticsRealtime.getLogisticsLineCode());
            }
            if (null != b2bLogisticsRealtime.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bLogisticsRealtime.getUpdateTime());
            }
            if (null != b2bLogisticsRealtime.getRemake()) {
                updateSql.add("remake = ?");
                params.add(b2bLogisticsRealtime.getRemake());
            }
            if (null != b2bLogisticsRealtime.getCurrAddress()) {
                updateSql.add("curr_address = ?");
                params.add(b2bLogisticsRealtime.getCurrAddress());
            }
            if (null != b2bLogisticsRealtime.getNextAddress()) {
                updateSql.add("next_address = ?");
                params.add(b2bLogisticsRealtime.getNextAddress());
            }
            if (null != b2bLogisticsRealtime.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bLogisticsRealtime.getStatus());
            }
            if (null != b2bLogisticsRealtime.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bLogisticsRealtime.getUpdateBy());
            }
            if (null != b2bLogisticsRealtime.getCurrDate()) {
                updateSql.add("curr_date = ?");
                params.add(b2bLogisticsRealtime.getCurrDate());
            }
            if (null != b2bLogisticsRealtime.getLogisticsLineId()) {
                updateSql.add("logistics_line_id = ?");
                params.add(b2bLogisticsRealtime.getLogisticsLineId());
            }
            if (null != b2bLogisticsRealtime.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bLogisticsRealtime.getCreateBy());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE logistics_realtime_id = ?");
            params.add(b2bLogisticsRealtime.getLogisticsRealtimeId());

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
    public int deleteByPrimaryKey(String logisticsRealtimeId) {
            if(null ==  logisticsRealtimeId) {
            return 0;
        }

        String sql = "delete from b2b_logistics_realtime where logistics_realtime_id  = ?";
        return jdbcTemplate.update(sql, logisticsRealtimeId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_logistics_realtime where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bLogisticsRealtimeBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bLogisticsRealtimeBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bLogisticsRealtimeBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bLogisticsRealtimeBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bLogisticsRealtimeBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bLogisticsRealtimeBean> findAll(Pageable arg0) {
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
	public void delete(B2bLogisticsRealtimeBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bLogisticsRealtimeBean> arg0) {
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
	public Iterable<B2bLogisticsRealtimeBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bLogisticsRealtimeBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bLogisticsRealtimeBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bLogisticsRealtimeBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bLogisticsRealtimeBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bLogisticsRealtimeBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bLogisticsRealtimeBean> findAll(
			Specification<B2bLogisticsRealtimeBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bLogisticsRealtimeBean> findAll(
			Specification<B2bLogisticsRealtimeBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bLogisticsRealtimeBean> findAll(
			Specification<B2bLogisticsRealtimeBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bLogisticsRealtimeBean findOne(
			Specification<B2bLogisticsRealtimeBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
