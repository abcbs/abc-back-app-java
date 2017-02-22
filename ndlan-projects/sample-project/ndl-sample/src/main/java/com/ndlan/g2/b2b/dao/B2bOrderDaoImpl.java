package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bOrderBean;

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

@Repository("b2bOrderDao")
public class B2bOrderDaoImpl implements B2bOrderDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bOrderBean selectByPrimaryKey(String bId) {
        try {
            String sql = "select * from b2b_order where b_id = ?";

            List<B2bOrderBean> resultList = this.jdbcTemplate.query(sql, new Object[]{bId},
                    new RowMapper<B2bOrderBean>() {
                        @Override
                        public B2bOrderBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bOrderBean bean = new B2bOrderBean();
                            bean.set(rs.getString(""));
                            bean.set(rs.getDate(""));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.set(rs.getString(""));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setBId(rs.getString("b_id"));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.set(rs.getDate(""));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setBAmount(rs.getString("b_amount"));
                            bean.setBName(rs.getString("b_name"));
                            bean.set(rs.getString(""));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setBNo(rs.getString("b_no"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
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
    public List<B2bOrderBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order ";
            } else {
                sql = "select * from b2b_order where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderBean>() {
                        @Override
                        public B2bOrderBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bOrderBean bean = new B2bOrderBean();
                            bean.set(rs.getString(""));
                            bean.set(rs.getDate(""));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.set(rs.getString(""));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setBId(rs.getString("b_id"));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.set(rs.getDate(""));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setBAmount(rs.getString("b_amount"));
                            bean.setBName(rs.getString("b_name"));
                            bean.set(rs.getString(""));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setBNo(rs.getString("b_no"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_order ";
            } else {
                sql = "select * from b2b_order where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bOrderBean>() {
                        @Override
                        public B2bOrderBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bOrderBean bean = new B2bOrderBean();
                            bean.set(rs.getString(""));
                            bean.set(rs.getDate(""));
                            bean.setCodelessSum(rs.getString("codeless_sum"));
                            bean.set(rs.getString(""));
                            bean.setRestName(rs.getString("rest_name"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setBId(rs.getString("b_id"));
                            bean.setPayType(rs.getString("pay_type"));
                            bean.setTargetClient(rs.getString("target_client"));
                            bean.setAllPrivilege(rs.getString("all_privilege"));
                            bean.set(rs.getDate(""));
                            bean.setCustomerId(rs.getString("customer_id"));
                            bean.setAllDiscount(rs.getString("all_discount"));
                            bean.setCustomerName(rs.getString("customer_name"));
                            bean.setBAmount(rs.getString("b_amount"));
                            bean.setBName(rs.getString("b_name"));
                            bean.set(rs.getString(""));
                            bean.setCartId(rs.getString("cart_id"));
                            bean.setBNo(rs.getString("b_no"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setAmountPaid(rs.getString("amount_paid"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bOrderBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_order ";
            } else {
                sql = "select count(1) numCount from b2b_order where " + whereSql;
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
    public int insertSelective(B2bOrderBean b2bOrder) {
        try {
            if (null == b2bOrder) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bOrder.get()) {
                columns.add("");
                values.add(b2bOrder.get());
            }
            if (null != b2bOrder.get()) {
                columns.add("");
                values.add(b2bOrder.get());
            }
            if (null != b2bOrder.getCodelessSum()) {
                columns.add("codeless_sum");
                values.add(b2bOrder.getCodelessSum());
            }
            if (null != b2bOrder.get()) {
                columns.add("");
                values.add(b2bOrder.get());
            }
            if (null != b2bOrder.getRestName()) {
                columns.add("rest_name");
                values.add(b2bOrder.getRestName());
            }
            if (null != b2bOrder.getRemark()) {
                columns.add("remark");
                values.add(b2bOrder.getRemark());
            }
            if (null != b2bOrder.getBId()) {
                columns.add("b_id");
                values.add(b2bOrder.getBId());
            }
            if (null != b2bOrder.getPayType()) {
                columns.add("pay_type");
                values.add(b2bOrder.getPayType());
            }
            if (null != b2bOrder.getTargetClient()) {
                columns.add("target_client");
                values.add(b2bOrder.getTargetClient());
            }
            if (null != b2bOrder.getAllPrivilege()) {
                columns.add("all_privilege");
                values.add(b2bOrder.getAllPrivilege());
            }
            if (null != b2bOrder.get()) {
                columns.add("");
                values.add(b2bOrder.get());
            }
            if (null != b2bOrder.getCustomerId()) {
                columns.add("customer_id");
                values.add(b2bOrder.getCustomerId());
            }
            if (null != b2bOrder.getAllDiscount()) {
                columns.add("all_discount");
                values.add(b2bOrder.getAllDiscount());
            }
            if (null != b2bOrder.getCustomerName()) {
                columns.add("customer_name");
                values.add(b2bOrder.getCustomerName());
            }
            if (null != b2bOrder.getBAmount()) {
                columns.add("b_amount");
                values.add(b2bOrder.getBAmount());
            }
            if (null != b2bOrder.getBName()) {
                columns.add("b_name");
                values.add(b2bOrder.getBName());
            }
            if (null != b2bOrder.get()) {
                columns.add("");
                values.add(b2bOrder.get());
            }
            if (null != b2bOrder.getCartId()) {
                columns.add("cart_id");
                values.add(b2bOrder.getCartId());
            }
            if (null != b2bOrder.getBNo()) {
                columns.add("b_no");
                values.add(b2bOrder.getBNo());
            }
            if (null != b2bOrder.getRestId()) {
                columns.add("rest_id");
                values.add(b2bOrder.getRestId());
            }
            if (null != b2bOrder.getAmountPaid()) {
                columns.add("amount_paid");
                values.add(b2bOrder.getAmountPaid());
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
    public int insertSelectiveAndGetId(B2bOrderBean b2bOrder) {
        try {
            if (null == b2bOrder) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_order(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bOrder.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrder.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrder.getCodelessSum()) {
                columns.add("codeless_sum");
                parameters.add(":codelessSum");
            }
            if (null != b2bOrder.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrder.getRestName()) {
                columns.add("rest_name");
                parameters.add(":restName");
            }
            if (null != b2bOrder.getRemark()) {
                columns.add("remark");
                parameters.add(":remark");
            }
            if (null != b2bOrder.getBId()) {
                columns.add("b_id");
                parameters.add(":bId");
            }
            if (null != b2bOrder.getPayType()) {
                columns.add("pay_type");
                parameters.add(":payType");
            }
            if (null != b2bOrder.getTargetClient()) {
                columns.add("target_client");
                parameters.add(":targetClient");
            }
            if (null != b2bOrder.getAllPrivilege()) {
                columns.add("all_privilege");
                parameters.add(":allPrivilege");
            }
            if (null != b2bOrder.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrder.getCustomerId()) {
                columns.add("customer_id");
                parameters.add(":customerId");
            }
            if (null != b2bOrder.getAllDiscount()) {
                columns.add("all_discount");
                parameters.add(":allDiscount");
            }
            if (null != b2bOrder.getCustomerName()) {
                columns.add("customer_name");
                parameters.add(":customerName");
            }
            if (null != b2bOrder.getBAmount()) {
                columns.add("b_amount");
                parameters.add(":bAmount");
            }
            if (null != b2bOrder.getBName()) {
                columns.add("b_name");
                parameters.add(":bName");
            }
            if (null != b2bOrder.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bOrder.getCartId()) {
                columns.add("cart_id");
                parameters.add(":cartId");
            }
            if (null != b2bOrder.getBNo()) {
                columns.add("b_no");
                parameters.add(":bNo");
            }
            if (null != b2bOrder.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bOrder.getAmountPaid()) {
                columns.add("amount_paid");
                parameters.add(":amountPaid");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bOrder);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bOrder.setBId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bOrderBean b2bOrder) {
        try {
            if (null == b2bOrder.getBId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_order SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bOrder.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrder.get());
            }
            if (null != b2bOrder.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrder.get());
            }
            if (null != b2bOrder.getCodelessSum()) {
                updateSql.add("codeless_sum = ?");
                params.add(b2bOrder.getCodelessSum());
            }
            if (null != b2bOrder.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrder.get());
            }
            if (null != b2bOrder.getRestName()) {
                updateSql.add("rest_name = ?");
                params.add(b2bOrder.getRestName());
            }
            if (null != b2bOrder.getRemark()) {
                updateSql.add("remark = ?");
                params.add(b2bOrder.getRemark());
            }
            if (null != b2bOrder.getBId()) {
                updateSql.add("b_id = ?");
                params.add(b2bOrder.getBId());
            }
            if (null != b2bOrder.getPayType()) {
                updateSql.add("pay_type = ?");
                params.add(b2bOrder.getPayType());
            }
            if (null != b2bOrder.getTargetClient()) {
                updateSql.add("target_client = ?");
                params.add(b2bOrder.getTargetClient());
            }
            if (null != b2bOrder.getAllPrivilege()) {
                updateSql.add("all_privilege = ?");
                params.add(b2bOrder.getAllPrivilege());
            }
            if (null != b2bOrder.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrder.get());
            }
            if (null != b2bOrder.getCustomerId()) {
                updateSql.add("customer_id = ?");
                params.add(b2bOrder.getCustomerId());
            }
            if (null != b2bOrder.getAllDiscount()) {
                updateSql.add("all_discount = ?");
                params.add(b2bOrder.getAllDiscount());
            }
            if (null != b2bOrder.getCustomerName()) {
                updateSql.add("customer_name = ?");
                params.add(b2bOrder.getCustomerName());
            }
            if (null != b2bOrder.getBAmount()) {
                updateSql.add("b_amount = ?");
                params.add(b2bOrder.getBAmount());
            }
            if (null != b2bOrder.getBName()) {
                updateSql.add("b_name = ?");
                params.add(b2bOrder.getBName());
            }
            if (null != b2bOrder.get()) {
                updateSql.add(" = ?");
                params.add(b2bOrder.get());
            }
            if (null != b2bOrder.getCartId()) {
                updateSql.add("cart_id = ?");
                params.add(b2bOrder.getCartId());
            }
            if (null != b2bOrder.getBNo()) {
                updateSql.add("b_no = ?");
                params.add(b2bOrder.getBNo());
            }
            if (null != b2bOrder.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bOrder.getRestId());
            }
            if (null != b2bOrder.getAmountPaid()) {
                updateSql.add("amount_paid = ?");
                params.add(b2bOrder.getAmountPaid());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE b_id = ?");
            params.add(b2bOrder.getBId());

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
    public int deleteByPrimaryKey(String bId) {
            if(null ==  bId) {
            return 0;
        }

        String sql = "delete from b2b_order where b_id  = ?";
        return jdbcTemplate.update(sql, bId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_order where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bOrderBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bOrderBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bOrderBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bOrderBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bOrderBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderBean> findAll(Pageable arg0) {
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
	public void delete(B2bOrderBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bOrderBean> arg0) {
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
	public Iterable<B2bOrderBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bOrderBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bOrderBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bOrderBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bOrderBean> findAll(
			Specification<B2bOrderBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bOrderBean> findAll(
			Specification<B2bOrderBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bOrderBean> findAll(
			Specification<B2bOrderBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bOrderBean findOne(
			Specification<B2bOrderBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
