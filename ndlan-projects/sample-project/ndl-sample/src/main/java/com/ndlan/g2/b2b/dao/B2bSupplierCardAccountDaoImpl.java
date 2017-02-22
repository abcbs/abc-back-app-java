package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bSupplierCardAccountBean;

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

@Repository("b2bSupplierCardAccountDao")
public class B2bSupplierCardAccountDaoImpl implements B2bSupplierCardAccountDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bSupplierCardAccountBean selectByPrimaryKey(String supCardAcntId) {
        try {
            String sql = "select * from b2b_supplier_card_account where sup_card_acnt_id = ?";

            List<B2bSupplierCardAccountBean> resultList = this.jdbcTemplate.query(sql, new Object[]{supCardAcntId},
                    new RowMapper<B2bSupplierCardAccountBean>() {
                        @Override
                        public B2bSupplierCardAccountBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bSupplierCardAccountBean bean = new B2bSupplierCardAccountBean();
                            bean.setRemake(rs.getString("remake"));
                            bean.setCatNo(rs.getString("cat_no"));
                            bean.set(rs.getDate(""));
                            bean.setIsDefault(rs.getString("is_default"));
                            bean.set(rs.getString(""));
                            bean.setPhoneNo(rs.getString("phone_no"));
                            bean.setCardholderId(rs.getString("cardholder_id"));
                            bean.set(rs.getString(""));
                            bean.setCardinfoSummay(rs.getString("cardinfo_summay"));
                            bean.setBranch(rs.getString("branch"));
                            bean.set(rs.getDate(""));
                            bean.setSubbranch(rs.getString("subbranch"));
                            bean.setSupCardAcntId(rs.getString("sup_card_acnt_id"));
                            bean.setBank(rs.getString("bank"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCardNo(rs.getString("card_no"));
                            bean.setCardholderName(rs.getString("cardholder_name"));
                            bean.setSupplierName(rs.getString("supplier_name"));
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
    public List<B2bSupplierCardAccountBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_supplier_card_account ";
            } else {
                sql = "select * from b2b_supplier_card_account where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bSupplierCardAccountBean>() {
                        @Override
                        public B2bSupplierCardAccountBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bSupplierCardAccountBean bean = new B2bSupplierCardAccountBean();
                            bean.setRemake(rs.getString("remake"));
                            bean.setCatNo(rs.getString("cat_no"));
                            bean.set(rs.getDate(""));
                            bean.setIsDefault(rs.getString("is_default"));
                            bean.set(rs.getString(""));
                            bean.setPhoneNo(rs.getString("phone_no"));
                            bean.setCardholderId(rs.getString("cardholder_id"));
                            bean.set(rs.getString(""));
                            bean.setCardinfoSummay(rs.getString("cardinfo_summay"));
                            bean.setBranch(rs.getString("branch"));
                            bean.set(rs.getDate(""));
                            bean.setSubbranch(rs.getString("subbranch"));
                            bean.setSupCardAcntId(rs.getString("sup_card_acnt_id"));
                            bean.setBank(rs.getString("bank"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCardNo(rs.getString("card_no"));
                            bean.setCardholderName(rs.getString("cardholder_name"));
                            bean.setSupplierName(rs.getString("supplier_name"));
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
    public List<B2bSupplierCardAccountBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_supplier_card_account ";
            } else {
                sql = "select * from b2b_supplier_card_account where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bSupplierCardAccountBean>() {
                        @Override
                        public B2bSupplierCardAccountBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bSupplierCardAccountBean bean = new B2bSupplierCardAccountBean();
                            bean.setRemake(rs.getString("remake"));
                            bean.setCatNo(rs.getString("cat_no"));
                            bean.set(rs.getDate(""));
                            bean.setIsDefault(rs.getString("is_default"));
                            bean.set(rs.getString(""));
                            bean.setPhoneNo(rs.getString("phone_no"));
                            bean.setCardholderId(rs.getString("cardholder_id"));
                            bean.set(rs.getString(""));
                            bean.setCardinfoSummay(rs.getString("cardinfo_summay"));
                            bean.setBranch(rs.getString("branch"));
                            bean.set(rs.getDate(""));
                            bean.setSubbranch(rs.getString("subbranch"));
                            bean.setSupCardAcntId(rs.getString("sup_card_acnt_id"));
                            bean.setBank(rs.getString("bank"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setCardNo(rs.getString("card_no"));
                            bean.setCardholderName(rs.getString("cardholder_name"));
                            bean.setSupplierName(rs.getString("supplier_name"));
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
    public List<B2bSupplierCardAccountBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_supplier_card_account ";
            } else {
                sql = "select count(1) numCount from b2b_supplier_card_account where " + whereSql;
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
    public int insertSelective(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        try {
            if (null == b2bSupplierCardAccount) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_supplier_card_account(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bSupplierCardAccount.getRemake()) {
                columns.add("remake");
                values.add(b2bSupplierCardAccount.getRemake());
            }
            if (null != b2bSupplierCardAccount.getCatNo()) {
                columns.add("cat_no");
                values.add(b2bSupplierCardAccount.getCatNo());
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                values.add(b2bSupplierCardAccount.get());
            }
            if (null != b2bSupplierCardAccount.getIsDefault()) {
                columns.add("is_default");
                values.add(b2bSupplierCardAccount.getIsDefault());
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                values.add(b2bSupplierCardAccount.get());
            }
            if (null != b2bSupplierCardAccount.getPhoneNo()) {
                columns.add("phone_no");
                values.add(b2bSupplierCardAccount.getPhoneNo());
            }
            if (null != b2bSupplierCardAccount.getCardholderId()) {
                columns.add("cardholder_id");
                values.add(b2bSupplierCardAccount.getCardholderId());
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                values.add(b2bSupplierCardAccount.get());
            }
            if (null != b2bSupplierCardAccount.getCardinfoSummay()) {
                columns.add("cardinfo_summay");
                values.add(b2bSupplierCardAccount.getCardinfoSummay());
            }
            if (null != b2bSupplierCardAccount.getBranch()) {
                columns.add("branch");
                values.add(b2bSupplierCardAccount.getBranch());
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                values.add(b2bSupplierCardAccount.get());
            }
            if (null != b2bSupplierCardAccount.getSubbranch()) {
                columns.add("subbranch");
                values.add(b2bSupplierCardAccount.getSubbranch());
            }
            if (null != b2bSupplierCardAccount.getSupCardAcntId()) {
                columns.add("sup_card_acnt_id");
                values.add(b2bSupplierCardAccount.getSupCardAcntId());
            }
            if (null != b2bSupplierCardAccount.getBank()) {
                columns.add("bank");
                values.add(b2bSupplierCardAccount.getBank());
            }
            if (null != b2bSupplierCardAccount.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bSupplierCardAccount.getSupplierId());
            }
            if (null != b2bSupplierCardAccount.getCardNo()) {
                columns.add("card_no");
                values.add(b2bSupplierCardAccount.getCardNo());
            }
            if (null != b2bSupplierCardAccount.getCardholderName()) {
                columns.add("cardholder_name");
                values.add(b2bSupplierCardAccount.getCardholderName());
            }
            if (null != b2bSupplierCardAccount.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bSupplierCardAccount.getSupplierName());
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                values.add(b2bSupplierCardAccount.get());
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
    public int insertSelectiveAndGetId(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        try {
            if (null == b2bSupplierCardAccount) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_supplier_card_account(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bSupplierCardAccount.getRemake()) {
                columns.add("remake");
                parameters.add(":remake");
            }
            if (null != b2bSupplierCardAccount.getCatNo()) {
                columns.add("cat_no");
                parameters.add(":catNo");
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierCardAccount.getIsDefault()) {
                columns.add("is_default");
                parameters.add(":isDefault");
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierCardAccount.getPhoneNo()) {
                columns.add("phone_no");
                parameters.add(":phoneNo");
            }
            if (null != b2bSupplierCardAccount.getCardholderId()) {
                columns.add("cardholder_id");
                parameters.add(":cardholderId");
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierCardAccount.getCardinfoSummay()) {
                columns.add("cardinfo_summay");
                parameters.add(":cardinfoSummay");
            }
            if (null != b2bSupplierCardAccount.getBranch()) {
                columns.add("branch");
                parameters.add(":branch");
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierCardAccount.getSubbranch()) {
                columns.add("subbranch");
                parameters.add(":subbranch");
            }
            if (null != b2bSupplierCardAccount.getSupCardAcntId()) {
                columns.add("sup_card_acnt_id");
                parameters.add(":supCardAcntId");
            }
            if (null != b2bSupplierCardAccount.getBank()) {
                columns.add("bank");
                parameters.add(":bank");
            }
            if (null != b2bSupplierCardAccount.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bSupplierCardAccount.getCardNo()) {
                columns.add("card_no");
                parameters.add(":cardNo");
            }
            if (null != b2bSupplierCardAccount.getCardholderName()) {
                columns.add("cardholder_name");
                parameters.add(":cardholderName");
            }
            if (null != b2bSupplierCardAccount.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bSupplierCardAccount.get()) {
                columns.add("");
                parameters.add(":");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bSupplierCardAccount);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bSupplierCardAccount.setSupCardAcntId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        try {
            if (null == b2bSupplierCardAccount.getSupCardAcntId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_supplier_card_account SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bSupplierCardAccount.getRemake()) {
                updateSql.add("remake = ?");
                params.add(b2bSupplierCardAccount.getRemake());
            }
            if (null != b2bSupplierCardAccount.getCatNo()) {
                updateSql.add("cat_no = ?");
                params.add(b2bSupplierCardAccount.getCatNo());
            }
            if (null != b2bSupplierCardAccount.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierCardAccount.get());
            }
            if (null != b2bSupplierCardAccount.getIsDefault()) {
                updateSql.add("is_default = ?");
                params.add(b2bSupplierCardAccount.getIsDefault());
            }
            if (null != b2bSupplierCardAccount.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierCardAccount.get());
            }
            if (null != b2bSupplierCardAccount.getPhoneNo()) {
                updateSql.add("phone_no = ?");
                params.add(b2bSupplierCardAccount.getPhoneNo());
            }
            if (null != b2bSupplierCardAccount.getCardholderId()) {
                updateSql.add("cardholder_id = ?");
                params.add(b2bSupplierCardAccount.getCardholderId());
            }
            if (null != b2bSupplierCardAccount.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierCardAccount.get());
            }
            if (null != b2bSupplierCardAccount.getCardinfoSummay()) {
                updateSql.add("cardinfo_summay = ?");
                params.add(b2bSupplierCardAccount.getCardinfoSummay());
            }
            if (null != b2bSupplierCardAccount.getBranch()) {
                updateSql.add("branch = ?");
                params.add(b2bSupplierCardAccount.getBranch());
            }
            if (null != b2bSupplierCardAccount.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierCardAccount.get());
            }
            if (null != b2bSupplierCardAccount.getSubbranch()) {
                updateSql.add("subbranch = ?");
                params.add(b2bSupplierCardAccount.getSubbranch());
            }
            if (null != b2bSupplierCardAccount.getSupCardAcntId()) {
                updateSql.add("sup_card_acnt_id = ?");
                params.add(b2bSupplierCardAccount.getSupCardAcntId());
            }
            if (null != b2bSupplierCardAccount.getBank()) {
                updateSql.add("bank = ?");
                params.add(b2bSupplierCardAccount.getBank());
            }
            if (null != b2bSupplierCardAccount.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bSupplierCardAccount.getSupplierId());
            }
            if (null != b2bSupplierCardAccount.getCardNo()) {
                updateSql.add("card_no = ?");
                params.add(b2bSupplierCardAccount.getCardNo());
            }
            if (null != b2bSupplierCardAccount.getCardholderName()) {
                updateSql.add("cardholder_name = ?");
                params.add(b2bSupplierCardAccount.getCardholderName());
            }
            if (null != b2bSupplierCardAccount.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bSupplierCardAccount.getSupplierName());
            }
            if (null != b2bSupplierCardAccount.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierCardAccount.get());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE sup_card_acnt_id = ?");
            params.add(b2bSupplierCardAccount.getSupCardAcntId());

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
    public int deleteByPrimaryKey(String supCardAcntId) {
            if(null ==  supCardAcntId) {
            return 0;
        }

        String sql = "delete from b2b_supplier_card_account where sup_card_acnt_id  = ?";
        return jdbcTemplate.update(sql, supCardAcntId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_supplier_card_account where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bSupplierCardAccountBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bSupplierCardAccountBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bSupplierCardAccountBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bSupplierCardAccountBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bSupplierCardAccountBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bSupplierCardAccountBean> findAll(Pageable arg0) {
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
	public void delete(B2bSupplierCardAccountBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bSupplierCardAccountBean> arg0) {
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
	public Iterable<B2bSupplierCardAccountBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bSupplierCardAccountBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bSupplierCardAccountBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bSupplierCardAccountBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bSupplierCardAccountBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bSupplierCardAccountBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bSupplierCardAccountBean> findAll(
			Specification<B2bSupplierCardAccountBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bSupplierCardAccountBean> findAll(
			Specification<B2bSupplierCardAccountBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bSupplierCardAccountBean> findAll(
			Specification<B2bSupplierCardAccountBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bSupplierCardAccountBean findOne(
			Specification<B2bSupplierCardAccountBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
