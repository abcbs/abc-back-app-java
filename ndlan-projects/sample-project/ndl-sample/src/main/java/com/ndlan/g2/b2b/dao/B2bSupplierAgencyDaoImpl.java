package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bSupplierAgencyBean;

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

@Repository("b2bSupplierAgencyDao")
public class B2bSupplierAgencyDaoImpl implements B2bSupplierAgencyDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bSupplierAgencyBean selectByPrimaryKey(String supplierAgencyId) {
        try {
            String sql = "select * from b2b_supplier_agency where supplier_agency_id = ?";

            List<B2bSupplierAgencyBean> resultList = this.jdbcTemplate.query(sql, new Object[]{supplierAgencyId},
                    new RowMapper<B2bSupplierAgencyBean>() {
                        @Override
                        public B2bSupplierAgencyBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bSupplierAgencyBean bean = new B2bSupplierAgencyBean();
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.set(rs.getDate(""));
                            bean.setDeliveryAddress(rs.getString("delivery_address"));
                            bean.set(rs.getDate(""));
                            bean.setSendAddressId(rs.getString("send_address_id"));
                            bean.setContactUser(rs.getString("contact_user"));
                            bean.setSupplierAgencyId(rs.getString("supplier_agency_id"));
                            bean.set(rs.getString(""));
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.set(rs.getString(""));
                            bean.setRemake(rs.getString("remake"));
                            bean.setContactPhone(rs.getString("contact_phone"));
                            bean.setSupplierId(rs.getString("supplier_id"));
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
    public List<B2bSupplierAgencyBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_supplier_agency ";
            } else {
                sql = "select * from b2b_supplier_agency where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bSupplierAgencyBean>() {
                        @Override
                        public B2bSupplierAgencyBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bSupplierAgencyBean bean = new B2bSupplierAgencyBean();
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.set(rs.getDate(""));
                            bean.setDeliveryAddress(rs.getString("delivery_address"));
                            bean.set(rs.getDate(""));
                            bean.setSendAddressId(rs.getString("send_address_id"));
                            bean.setContactUser(rs.getString("contact_user"));
                            bean.setSupplierAgencyId(rs.getString("supplier_agency_id"));
                            bean.set(rs.getString(""));
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.set(rs.getString(""));
                            bean.setRemake(rs.getString("remake"));
                            bean.setContactPhone(rs.getString("contact_phone"));
                            bean.setSupplierId(rs.getString("supplier_id"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bSupplierAgencyBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_supplier_agency ";
            } else {
                sql = "select * from b2b_supplier_agency where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bSupplierAgencyBean>() {
                        @Override
                        public B2bSupplierAgencyBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bSupplierAgencyBean bean = new B2bSupplierAgencyBean();
                            bean.set(rs.getString(""));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.set(rs.getDate(""));
                            bean.setDeliveryAddress(rs.getString("delivery_address"));
                            bean.set(rs.getDate(""));
                            bean.setSendAddressId(rs.getString("send_address_id"));
                            bean.setContactUser(rs.getString("contact_user"));
                            bean.setSupplierAgencyId(rs.getString("supplier_agency_id"));
                            bean.set(rs.getString(""));
                            bean.setAgencyId(rs.getString("agency_id"));
                            bean.setAgencyName(rs.getString("agency_name"));
                            bean.set(rs.getString(""));
                            bean.setRemake(rs.getString("remake"));
                            bean.setContactPhone(rs.getString("contact_phone"));
                            bean.setSupplierId(rs.getString("supplier_id"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bSupplierAgencyBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_supplier_agency ";
            } else {
                sql = "select count(1) numCount from b2b_supplier_agency where " + whereSql;
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
    public int insertSelective(B2bSupplierAgencyBean b2bSupplierAgency) {
        try {
            if (null == b2bSupplierAgency) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_supplier_agency(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                values.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bSupplierAgency.getSupplierName());
            }
            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                values.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getDeliveryAddress()) {
                columns.add("delivery_address");
                values.add(b2bSupplierAgency.getDeliveryAddress());
            }
            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                values.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getSendAddressId()) {
                columns.add("send_address_id");
                values.add(b2bSupplierAgency.getSendAddressId());
            }
            if (null != b2bSupplierAgency.getContactUser()) {
                columns.add("contact_user");
                values.add(b2bSupplierAgency.getContactUser());
            }
            if (null != b2bSupplierAgency.getSupplierAgencyId()) {
                columns.add("supplier_agency_id");
                values.add(b2bSupplierAgency.getSupplierAgencyId());
            }
            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                values.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getAgencyId()) {
                columns.add("agency_id");
                values.add(b2bSupplierAgency.getAgencyId());
            }
            if (null != b2bSupplierAgency.getAgencyName()) {
                columns.add("agency_name");
                values.add(b2bSupplierAgency.getAgencyName());
            }
            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                values.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getRemake()) {
                columns.add("remake");
                values.add(b2bSupplierAgency.getRemake());
            }
            if (null != b2bSupplierAgency.getContactPhone()) {
                columns.add("contact_phone");
                values.add(b2bSupplierAgency.getContactPhone());
            }
            if (null != b2bSupplierAgency.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bSupplierAgency.getSupplierId());
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
    public int insertSelectiveAndGetId(B2bSupplierAgencyBean b2bSupplierAgency) {
        try {
            if (null == b2bSupplierAgency) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_supplier_agency(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierAgency.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierAgency.getDeliveryAddress()) {
                columns.add("delivery_address");
                parameters.add(":deliveryAddress");
            }
            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierAgency.getSendAddressId()) {
                columns.add("send_address_id");
                parameters.add(":sendAddressId");
            }
            if (null != b2bSupplierAgency.getContactUser()) {
                columns.add("contact_user");
                parameters.add(":contactUser");
            }
            if (null != b2bSupplierAgency.getSupplierAgencyId()) {
                columns.add("supplier_agency_id");
                parameters.add(":supplierAgencyId");
            }
            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierAgency.getAgencyId()) {
                columns.add("agency_id");
                parameters.add(":agencyId");
            }
            if (null != b2bSupplierAgency.getAgencyName()) {
                columns.add("agency_name");
                parameters.add(":agencyName");
            }
            if (null != b2bSupplierAgency.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bSupplierAgency.getRemake()) {
                columns.add("remake");
                parameters.add(":remake");
            }
            if (null != b2bSupplierAgency.getContactPhone()) {
                columns.add("contact_phone");
                parameters.add(":contactPhone");
            }
            if (null != b2bSupplierAgency.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bSupplierAgency);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bSupplierAgency.setSupplierAgencyId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bSupplierAgencyBean b2bSupplierAgency) {
        try {
            if (null == b2bSupplierAgency.getSupplierAgencyId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_supplier_agency SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bSupplierAgency.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bSupplierAgency.getSupplierName());
            }
            if (null != b2bSupplierAgency.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getDeliveryAddress()) {
                updateSql.add("delivery_address = ?");
                params.add(b2bSupplierAgency.getDeliveryAddress());
            }
            if (null != b2bSupplierAgency.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getSendAddressId()) {
                updateSql.add("send_address_id = ?");
                params.add(b2bSupplierAgency.getSendAddressId());
            }
            if (null != b2bSupplierAgency.getContactUser()) {
                updateSql.add("contact_user = ?");
                params.add(b2bSupplierAgency.getContactUser());
            }
            if (null != b2bSupplierAgency.getSupplierAgencyId()) {
                updateSql.add("supplier_agency_id = ?");
                params.add(b2bSupplierAgency.getSupplierAgencyId());
            }
            if (null != b2bSupplierAgency.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getAgencyId()) {
                updateSql.add("agency_id = ?");
                params.add(b2bSupplierAgency.getAgencyId());
            }
            if (null != b2bSupplierAgency.getAgencyName()) {
                updateSql.add("agency_name = ?");
                params.add(b2bSupplierAgency.getAgencyName());
            }
            if (null != b2bSupplierAgency.get()) {
                updateSql.add(" = ?");
                params.add(b2bSupplierAgency.get());
            }
            if (null != b2bSupplierAgency.getRemake()) {
                updateSql.add("remake = ?");
                params.add(b2bSupplierAgency.getRemake());
            }
            if (null != b2bSupplierAgency.getContactPhone()) {
                updateSql.add("contact_phone = ?");
                params.add(b2bSupplierAgency.getContactPhone());
            }
            if (null != b2bSupplierAgency.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bSupplierAgency.getSupplierId());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE supplier_agency_id = ?");
            params.add(b2bSupplierAgency.getSupplierAgencyId());

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
    public int deleteByPrimaryKey(String supplierAgencyId) {
            if(null ==  supplierAgencyId) {
            return 0;
        }

        String sql = "delete from b2b_supplier_agency where supplier_agency_id  = ?";
        return jdbcTemplate.update(sql, supplierAgencyId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_supplier_agency where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bSupplierAgencyBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bSupplierAgencyBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bSupplierAgencyBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bSupplierAgencyBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bSupplierAgencyBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bSupplierAgencyBean> findAll(Pageable arg0) {
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
	public void delete(B2bSupplierAgencyBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bSupplierAgencyBean> arg0) {
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
	public Iterable<B2bSupplierAgencyBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bSupplierAgencyBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bSupplierAgencyBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bSupplierAgencyBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bSupplierAgencyBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bSupplierAgencyBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bSupplierAgencyBean> findAll(
			Specification<B2bSupplierAgencyBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bSupplierAgencyBean> findAll(
			Specification<B2bSupplierAgencyBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bSupplierAgencyBean> findAll(
			Specification<B2bSupplierAgencyBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bSupplierAgencyBean findOne(
			Specification<B2bSupplierAgencyBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
