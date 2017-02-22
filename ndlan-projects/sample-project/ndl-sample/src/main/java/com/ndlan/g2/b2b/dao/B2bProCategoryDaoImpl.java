package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bProCategoryBean;

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

@Repository("b2bProCategoryDao")
public class B2bProCategoryDaoImpl implements B2bProCategoryDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bProCategoryBean selectByPrimaryKey(String categoryId) {
        try {
            String sql = "select * from b2b_pro_category where category_id = ?";

            List<B2bProCategoryBean> resultList = this.jdbcTemplate.query(sql, new Object[]{categoryId},
                    new RowMapper<B2bProCategoryBean>() {
                        @Override
                        public B2bProCategoryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bProCategoryBean bean = new B2bProCategoryBean();
                            bean.setRestId(rs.getString("rest_id"));
                            bean.set(rs.getString(""));
                            bean.setCategoryGrade(rs.getString("category_grade"));
                            bean.setCategoryToneIos(rs.getString("category_tone_ios"));
                            bean.set(rs.getString(""));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setParentCategoryName(rs.getString("parent_category_name"));
                            bean.setCategoryStatus(rs.getString("category_status"));
                            bean.setParentCategoryId(rs.getString("parent_category_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getString(""));
                            bean.set(rs.getString(""));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryToneAndroid(rs.getString("category_tone_android"));
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
    public List<B2bProCategoryBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_pro_category ";
            } else {
                sql = "select * from b2b_pro_category where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bProCategoryBean>() {
                        @Override
                        public B2bProCategoryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bProCategoryBean bean = new B2bProCategoryBean();
                            bean.setRestId(rs.getString("rest_id"));
                            bean.set(rs.getString(""));
                            bean.setCategoryGrade(rs.getString("category_grade"));
                            bean.setCategoryToneIos(rs.getString("category_tone_ios"));
                            bean.set(rs.getString(""));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setParentCategoryName(rs.getString("parent_category_name"));
                            bean.setCategoryStatus(rs.getString("category_status"));
                            bean.setParentCategoryId(rs.getString("parent_category_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getString(""));
                            bean.set(rs.getString(""));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryToneAndroid(rs.getString("category_tone_android"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bProCategoryBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_pro_category ";
            } else {
                sql = "select * from b2b_pro_category where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bProCategoryBean>() {
                        @Override
                        public B2bProCategoryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bProCategoryBean bean = new B2bProCategoryBean();
                            bean.setRestId(rs.getString("rest_id"));
                            bean.set(rs.getString(""));
                            bean.setCategoryGrade(rs.getString("category_grade"));
                            bean.setCategoryToneIos(rs.getString("category_tone_ios"));
                            bean.set(rs.getString(""));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setParentCategoryName(rs.getString("parent_category_name"));
                            bean.setCategoryStatus(rs.getString("category_status"));
                            bean.setParentCategoryId(rs.getString("parent_category_id"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getString(""));
                            bean.set(rs.getString(""));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryToneAndroid(rs.getString("category_tone_android"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bProCategoryBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_pro_category ";
            } else {
                sql = "select count(1) numCount from b2b_pro_category where " + whereSql;
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
    public int insertSelective(B2bProCategoryBean b2bProCategory) {
        try {
            if (null == b2bProCategory) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_pro_category(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bProCategory.getRestId()) {
                columns.add("rest_id");
                values.add(b2bProCategory.getRestId());
            }
            if (null != b2bProCategory.get()) {
                columns.add("");
                values.add(b2bProCategory.get());
            }
            if (null != b2bProCategory.getCategoryGrade()) {
                columns.add("category_grade");
                values.add(b2bProCategory.getCategoryGrade());
            }
            if (null != b2bProCategory.getCategoryToneIos()) {
                columns.add("category_tone_ios");
                values.add(b2bProCategory.getCategoryToneIos());
            }
            if (null != b2bProCategory.get()) {
                columns.add("");
                values.add(b2bProCategory.get());
            }
            if (null != b2bProCategory.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bProCategory.getCategoryId());
            }
            if (null != b2bProCategory.getParentCategoryName()) {
                columns.add("parent_category_name");
                values.add(b2bProCategory.getParentCategoryName());
            }
            if (null != b2bProCategory.getCategoryStatus()) {
                columns.add("category_status");
                values.add(b2bProCategory.getCategoryStatus());
            }
            if (null != b2bProCategory.getParentCategoryId()) {
                columns.add("parent_category_id");
                values.add(b2bProCategory.getParentCategoryId());
            }
            if (null != b2bProCategory.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bProCategory.getCategoryName());
            }
            if (null != b2bProCategory.getParentIdPath()) {
                columns.add("parent_id_path");
                values.add(b2bProCategory.getParentIdPath());
            }
            if (null != b2bProCategory.get()) {
                columns.add("");
                values.add(b2bProCategory.get());
            }
            if (null != b2bProCategory.get()) {
                columns.add("");
                values.add(b2bProCategory.get());
            }
            if (null != b2bProCategory.getParentNamePath()) {
                columns.add("parent_name_path");
                values.add(b2bProCategory.getParentNamePath());
            }
            if (null != b2bProCategory.getCategoryToneAndroid()) {
                columns.add("category_tone_android");
                values.add(b2bProCategory.getCategoryToneAndroid());
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
    public int insertSelectiveAndGetId(B2bProCategoryBean b2bProCategory) {
        try {
            if (null == b2bProCategory) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_pro_category(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bProCategory.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bProCategory.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProCategory.getCategoryGrade()) {
                columns.add("category_grade");
                parameters.add(":categoryGrade");
            }
            if (null != b2bProCategory.getCategoryToneIos()) {
                columns.add("category_tone_ios");
                parameters.add(":categoryToneIos");
            }
            if (null != b2bProCategory.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProCategory.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bProCategory.getParentCategoryName()) {
                columns.add("parent_category_name");
                parameters.add(":parentCategoryName");
            }
            if (null != b2bProCategory.getCategoryStatus()) {
                columns.add("category_status");
                parameters.add(":categoryStatus");
            }
            if (null != b2bProCategory.getParentCategoryId()) {
                columns.add("parent_category_id");
                parameters.add(":parentCategoryId");
            }
            if (null != b2bProCategory.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bProCategory.getParentIdPath()) {
                columns.add("parent_id_path");
                parameters.add(":parentIdPath");
            }
            if (null != b2bProCategory.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProCategory.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bProCategory.getParentNamePath()) {
                columns.add("parent_name_path");
                parameters.add(":parentNamePath");
            }
            if (null != b2bProCategory.getCategoryToneAndroid()) {
                columns.add("category_tone_android");
                parameters.add(":categoryToneAndroid");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bProCategory);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bProCategory.setCategoryId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bProCategoryBean b2bProCategory) {
        try {
            if (null == b2bProCategory.getCategoryId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_pro_category SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bProCategory.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bProCategory.getRestId());
            }
            if (null != b2bProCategory.get()) {
                updateSql.add(" = ?");
                params.add(b2bProCategory.get());
            }
            if (null != b2bProCategory.getCategoryGrade()) {
                updateSql.add("category_grade = ?");
                params.add(b2bProCategory.getCategoryGrade());
            }
            if (null != b2bProCategory.getCategoryToneIos()) {
                updateSql.add("category_tone_ios = ?");
                params.add(b2bProCategory.getCategoryToneIos());
            }
            if (null != b2bProCategory.get()) {
                updateSql.add(" = ?");
                params.add(b2bProCategory.get());
            }
            if (null != b2bProCategory.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bProCategory.getCategoryId());
            }
            if (null != b2bProCategory.getParentCategoryName()) {
                updateSql.add("parent_category_name = ?");
                params.add(b2bProCategory.getParentCategoryName());
            }
            if (null != b2bProCategory.getCategoryStatus()) {
                updateSql.add("category_status = ?");
                params.add(b2bProCategory.getCategoryStatus());
            }
            if (null != b2bProCategory.getParentCategoryId()) {
                updateSql.add("parent_category_id = ?");
                params.add(b2bProCategory.getParentCategoryId());
            }
            if (null != b2bProCategory.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bProCategory.getCategoryName());
            }
            if (null != b2bProCategory.getParentIdPath()) {
                updateSql.add("parent_id_path = ?");
                params.add(b2bProCategory.getParentIdPath());
            }
            if (null != b2bProCategory.get()) {
                updateSql.add(" = ?");
                params.add(b2bProCategory.get());
            }
            if (null != b2bProCategory.get()) {
                updateSql.add(" = ?");
                params.add(b2bProCategory.get());
            }
            if (null != b2bProCategory.getParentNamePath()) {
                updateSql.add("parent_name_path = ?");
                params.add(b2bProCategory.getParentNamePath());
            }
            if (null != b2bProCategory.getCategoryToneAndroid()) {
                updateSql.add("category_tone_android = ?");
                params.add(b2bProCategory.getCategoryToneAndroid());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE category_id = ?");
            params.add(b2bProCategory.getCategoryId());

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
    public int deleteByPrimaryKey(String categoryId) {
            if(null ==  categoryId) {
            return 0;
        }

        String sql = "delete from b2b_pro_category where category_id  = ?";
        return jdbcTemplate.update(sql, categoryId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_pro_category where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bProCategoryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bProCategoryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bProCategoryBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bProCategoryBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bProCategoryBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bProCategoryBean> findAll(Pageable arg0) {
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
	public void delete(B2bProCategoryBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bProCategoryBean> arg0) {
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
	public Iterable<B2bProCategoryBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bProCategoryBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bProCategoryBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bProCategoryBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bProCategoryBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bProCategoryBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bProCategoryBean> findAll(
			Specification<B2bProCategoryBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bProCategoryBean> findAll(
			Specification<B2bProCategoryBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bProCategoryBean> findAll(
			Specification<B2bProCategoryBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bProCategoryBean findOne(
			Specification<B2bProCategoryBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
