package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bShoppingCartBean;

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

@Repository("b2bShoppingCartJDBCDao")
public class B2bShoppingCartJDBCDaoImpl implements B2bShoppingCartJDBCDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bShoppingCartBean selectByPrimaryKey(String cartId) {
        try {
            String sql = "select * from b2b_shopping_cart where cart_id = ?";

            List<B2bShoppingCartBean> resultList = this.jdbcTemplate.query(sql, new Object[]{cartId},
                    new RowMapper<B2bShoppingCartBean>() {
                        @Override
                        public B2bShoppingCartBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bShoppingCartBean bean = new B2bShoppingCartBean();
                            bean.setStatus(rs.getString("status"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setTotal(rs.getString("total"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setTargetClient(rs.getString("target_client"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCustomerId(rs.getString("customer_id"));
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
    public List<B2bShoppingCartBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_shopping_cart ";
            } else {
                sql = "select * from b2b_shopping_cart where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bShoppingCartBean>() {
                        @Override
                        public B2bShoppingCartBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bShoppingCartBean bean = new B2bShoppingCartBean();
                            bean.setStatus(rs.getString("status"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setTotal(rs.getString("total"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setTargetClient(rs.getString("target_client"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCustomerId(rs.getString("customer_id"));
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
    public List<B2bShoppingCartBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_shopping_cart ";
            } else {
                sql = "select * from b2b_shopping_cart where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bShoppingCartBean>() {
                        @Override
                        public B2bShoppingCartBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bShoppingCartBean bean = new B2bShoppingCartBean();
                            bean.setStatus(rs.getString("status"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            Timestamp createTimeTimestamp = rs.getTimestamp("create_time");
                            if (null != createTimeTimestamp) {
                                bean.setCreateTime(new Date(createTimeTimestamp.getTime()));
                            }
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setUpdateBy(rs.getString("update_by"));
                            bean.setTotal(rs.getString("total"));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setTargetClient(rs.getString("target_client"));
                            Timestamp updateTimeTimestamp = rs.getTimestamp("update_time");
                            if (null != updateTimeTimestamp) {
                                bean.setUpdateTime(new Date(updateTimeTimestamp.getTime()));
                            }
                            bean.setCustomerId(rs.getString("customer_id"));
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
    public List<B2bShoppingCartBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_shopping_cart ";
            } else {
                sql = "select count(1) numCount from b2b_shopping_cart where " + whereSql;
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
    public int insertSelective(B2bShoppingCartBean b2bShoppingCart) {
        try {
            if (null == b2bShoppingCart) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_shopping_cart(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bShoppingCart.getStatus()) {
                columns.add("status");
                values.add(b2bShoppingCart.getStatus());
            }
            if (null != b2bShoppingCart.getAllDiscount()) {
                columns.add("all_discount");
                values.add(b2bShoppingCart.getAllDiscount());
            }
            if (null != b2bShoppingCart.getCreateTime()) {
                columns.add("create_time");
                values.add(b2bShoppingCart.getCreateTime());
            }
            if (null != b2bShoppingCart.getAllPrivilege()) {
                columns.add("all_privilege");
                values.add(b2bShoppingCart.getAllPrivilege());
            }
            if (null != b2bShoppingCart.getRestName()) {
                columns.add("rest_name");
                values.add(b2bShoppingCart.getRestName());
            }
            if (null != b2bShoppingCart.getUpdateBy()) {
                columns.add("update_by");
                values.add(b2bShoppingCart.getUpdateBy());
            }
            if (null != b2bShoppingCart.getTotal()) {
                columns.add("total");
                values.add(b2bShoppingCart.getTotal());
            }
            if (null != b2bShoppingCart.getCartId()) {
                columns.add("cart_id");
                values.add(b2bShoppingCart.getCartId());
            }
            if (null != b2bShoppingCart.getRestId()) {
                columns.add("rest_id");
                values.add(b2bShoppingCart.getRestId());
            }
            if (null != b2bShoppingCart.getCustomerName()) {
                columns.add("customer_name");
                values.add(b2bShoppingCart.getCustomerName());
            }
            if (null != b2bShoppingCart.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bShoppingCart.getTargetClient());
            }
            if (null != b2bShoppingCart.getUpdateTime()) {
                columns.add("update_time");
                values.add(b2bShoppingCart.getUpdateTime());
            }
            if (null != b2bShoppingCart.getCustomerId()) {
                columns.add("customer_id");
                values.add(b2bShoppingCart.getCustomerId());
            }
            if (null != b2bShoppingCart.getCreateBy()) {
                columns.add("create_by");
                values.add(b2bShoppingCart.getCreateBy());
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
    public int insertSelectiveAndGetId(B2bShoppingCartBean b2bShoppingCart) {
        try {
            if (null == b2bShoppingCart) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_shopping_cart(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bShoppingCart.getStatus()) {
                columns.add("status");
                parameters.add(":status");
            }
            if (null != b2bShoppingCart.getAllDiscount()) {
                columns.add("all_discount");
                parameters.add(":allDiscount");
            }
            if (null != b2bShoppingCart.getCreateTime()) {
                columns.add("create_time");
                parameters.add(":createTime");
            }
            if (null != b2bShoppingCart.getAllPrivilege()) {
                columns.add("all_privilege");
                parameters.add(":allPrivilege");
            }
            if (null != b2bShoppingCart.getRestName()) {
                columns.add("rest_name");
                parameters.add(":restName");
            }
            if (null != b2bShoppingCart.getUpdateBy()) {
                columns.add("update_by");
                parameters.add(":updateBy");
            }
            if (null != b2bShoppingCart.getTotal()) {
                columns.add("total");
                parameters.add(":total");
            }
            if (null != b2bShoppingCart.getCartId()) {
                columns.add("cart_id");
                parameters.add(":cartId");
            }
            if (null != b2bShoppingCart.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bShoppingCart.getCustomerName()) {
                columns.add("customer_name");
                parameters.add(":customerName");
            }
            if (null != b2bShoppingCart.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bShoppingCart.getUpdateTime()) {
                columns.add("update_time");
                parameters.add(":updateTime");
            }
            if (null != b2bShoppingCart.getCustomerId()) {
                columns.add("customer_id");
                parameters.add(":customerId");
            }
            if (null != b2bShoppingCart.getCreateBy()) {
                columns.add("create_by");
                parameters.add(":createBy");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bShoppingCart);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bShoppingCart.setCartId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bShoppingCartBean b2bShoppingCart) {
        try {
            if (null == b2bShoppingCart.getCartId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_shopping_cart SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bShoppingCart.getStatus()) {
                updateSql.add("status = ?");
                params.add(b2bShoppingCart.getStatus());
            }
            if (null != b2bShoppingCart.getAllDiscount()) {
                updateSql.add("all_discount = ?");
                params.add(b2bShoppingCart.getAllDiscount());
            }
            if (null != b2bShoppingCart.getCreateTime()) {
                updateSql.add("create_time = ?");
                params.add(b2bShoppingCart.getCreateTime());
            }
            if (null != b2bShoppingCart.getAllPrivilege()) {
                updateSql.add("all_privilege = ?");
                params.add(b2bShoppingCart.getAllPrivilege());
            }
            if (null != b2bShoppingCart.getRestName()) {
                updateSql.add("rest_name = ?");
                params.add(b2bShoppingCart.getRestName());
            }
            if (null != b2bShoppingCart.getUpdateBy()) {
                updateSql.add("update_by = ?");
                params.add(b2bShoppingCart.getUpdateBy());
            }
            if (null != b2bShoppingCart.getTotal()) {
                updateSql.add("total = ?");
                params.add(b2bShoppingCart.getTotal());
            }
            if (null != b2bShoppingCart.getCartId()) {
                updateSql.add("cart_id = ?");
                params.add(b2bShoppingCart.getCartId());
            }
            if (null != b2bShoppingCart.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bShoppingCart.getRestId());
            }
            if (null != b2bShoppingCart.getCustomerName()) {
                updateSql.add("customer_name = ?");
                params.add(b2bShoppingCart.getCustomerName());
            }
            if (null != b2bShoppingCart.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bShoppingCart.getTargetClient());
            }
            if (null != b2bShoppingCart.getUpdateTime()) {
                updateSql.add("update_time = ?");
                params.add(b2bShoppingCart.getUpdateTime());
            }
            if (null != b2bShoppingCart.getCustomerId()) {
                updateSql.add("customer_id = ?");
                params.add(b2bShoppingCart.getCustomerId());
            }
            if (null != b2bShoppingCart.getCreateBy()) {
                updateSql.add("create_by = ?");
                params.add(b2bShoppingCart.getCreateBy());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE cart_id = ?");
            params.add(b2bShoppingCart.getCartId());

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
    public int deleteByPrimaryKey(String cartId) {
            if(null ==  cartId) {
            return 0;
        }

        String sql = "delete from b2b_shopping_cart where cart_id  = ?";
        return jdbcTemplate.update(sql, cartId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_shopping_cart where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bShoppingCartBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bShoppingCartBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bShoppingCartBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bShoppingCartBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bShoppingCartBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bShoppingCartBean> findAll(Pageable arg0) {
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
	public void delete(B2bShoppingCartBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bShoppingCartBean> arg0) {
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
	public Iterable<B2bShoppingCartBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bShoppingCartBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bShoppingCartBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bShoppingCartBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bShoppingCartBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bShoppingCartBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bShoppingCartBean> findAll(
			Specification<B2bShoppingCartBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bShoppingCartBean> findAll(
			Specification<B2bShoppingCartBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bShoppingCartBean> findAll(
			Specification<B2bShoppingCartBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bShoppingCartBean findOne(
			Specification<B2bShoppingCartBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
