package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bInventoryStorageBean;

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

@Repository("b2bInventoryStorageDao")
public class B2bInventoryStorageDaoImpl implements B2bInventoryStorageDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bInventoryStorageBean selectByPrimaryKey(String invStorageId) {
        try {
            String sql = "select * from b2b_inventory_storage where inv_storage_id = ?";

            List<B2bInventoryStorageBean> resultList = this.jdbcTemplate.query(sql, new Object[]{invStorageId},
                    new RowMapper<B2bInventoryStorageBean>() {
                        @Override
                        public B2bInventoryStorageBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bInventoryStorageBean bean = new B2bInventoryStorageBean();
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setStorageQty(rs.getString("storage_qty"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.set(rs.getString(""));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setSize(rs.getString("size"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getDate(""));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.set(rs.getString(""));
                            bean.setOrderLineId(rs.getString("order_line_id"));
                            bean.setPic(rs.getString("pic"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setStorageUser(rs.getString("storage_user"));
                            bean.set(rs.getDate(""));
                            bean.setInvStorageId(rs.getString("inv_storage_id"));
                            Timestamp storageDateTimestamp = rs.getTimestamp("storage_date");
                            if (null != storageDateTimestamp) {
                                bean.setStorageDate(new Date(storageDateTimestamp.getTime()));
                            }
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setProCode(rs.getString("pro_code"));
                            bean.setSource(rs.getString("source"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setStoragePrice(rs.getString("storage_price"));
                            bean.setOrderDetailNo(rs.getString("order_detail_no"));
                            bean.setCapacity(rs.getString("capacity"));
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
    public List<B2bInventoryStorageBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_inventory_storage ";
            } else {
                sql = "select * from b2b_inventory_storage where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bInventoryStorageBean>() {
                        @Override
                        public B2bInventoryStorageBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bInventoryStorageBean bean = new B2bInventoryStorageBean();
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setStorageQty(rs.getString("storage_qty"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.set(rs.getString(""));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setSize(rs.getString("size"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getDate(""));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.set(rs.getString(""));
                            bean.setOrderLineId(rs.getString("order_line_id"));
                            bean.setPic(rs.getString("pic"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setStorageUser(rs.getString("storage_user"));
                            bean.set(rs.getDate(""));
                            bean.setInvStorageId(rs.getString("inv_storage_id"));
                            Timestamp storageDateTimestamp = rs.getTimestamp("storage_date");
                            if (null != storageDateTimestamp) {
                                bean.setStorageDate(new Date(storageDateTimestamp.getTime()));
                            }
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setProCode(rs.getString("pro_code"));
                            bean.setSource(rs.getString("source"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setStoragePrice(rs.getString("storage_price"));
                            bean.setOrderDetailNo(rs.getString("order_detail_no"));
                            bean.setCapacity(rs.getString("capacity"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bInventoryStorageBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_inventory_storage ";
            } else {
                sql = "select * from b2b_inventory_storage where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bInventoryStorageBean>() {
                        @Override
                        public B2bInventoryStorageBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bInventoryStorageBean bean = new B2bInventoryStorageBean();
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setStorageQty(rs.getString("storage_qty"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.set(rs.getString(""));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setOrderHeadId(rs.getString("order_head_id"));
                            bean.setProId(rs.getString("pro_id"));
                            bean.setOrderPkgId(rs.getString("order_pkg_id"));
                            bean.setSize(rs.getString("size"));
                            bean.setParentIdPath(rs.getString("parent_id_path"));
                            bean.set(rs.getDate(""));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.setOrderPkgCode(rs.getString("order_pkg_code"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.set(rs.getString(""));
                            bean.setOrderLineId(rs.getString("order_line_id"));
                            bean.setPic(rs.getString("pic"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setRemark(rs.getString("remark"));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.setStorageUser(rs.getString("storage_user"));
                            bean.set(rs.getDate(""));
                            bean.setInvStorageId(rs.getString("inv_storage_id"));
                            Timestamp storageDateTimestamp = rs.getTimestamp("storage_date");
                            if (null != storageDateTimestamp) {
                                bean.setStorageDate(new Date(storageDateTimestamp.getTime()));
                            }
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setProCode(rs.getString("pro_code"));
                            bean.setSource(rs.getString("source"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setProName(rs.getString("pro_name"));
                            bean.setParentNamePath(rs.getString("parent_name_path"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setStoragePrice(rs.getString("storage_price"));
                            bean.setOrderDetailNo(rs.getString("order_detail_no"));
                            bean.setCapacity(rs.getString("capacity"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bInventoryStorageBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_inventory_storage ";
            } else {
                sql = "select count(1) numCount from b2b_inventory_storage where " + whereSql;
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
    public int insertSelective(B2bInventoryStorageBean b2bInventoryStorage) {
        try {
            if (null == b2bInventoryStorage) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_inventory_storage(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bInventoryStorage.getInvHeadId()) {
                columns.add("inv_head_id");
                values.add(b2bInventoryStorage.getInvHeadId());
            }
            if (null != b2bInventoryStorage.getStorageQty()) {
                columns.add("storage_qty");
                values.add(b2bInventoryStorage.getStorageQty());
            }
            if (null != b2bInventoryStorage.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bInventoryStorage.getSupplierId());
            }
            if (null != b2bInventoryStorage.get()) {
                columns.add("");
                values.add(b2bInventoryStorage.get());
            }
            if (null != b2bInventoryStorage.getSeriesId()) {
                columns.add("series_id");
                values.add(b2bInventoryStorage.getSeriesId());
            }
            if (null != b2bInventoryStorage.getOrderHeadId()) {
                columns.add("order_head_id");
                values.add(b2bInventoryStorage.getOrderHeadId());
            }
            if (null != b2bInventoryStorage.getProId()) {
                columns.add("pro_id");
                values.add(b2bInventoryStorage.getProId());
            }
            if (null != b2bInventoryStorage.getOrderPkgId()) {
                columns.add("order_pkg_id");
                values.add(b2bInventoryStorage.getOrderPkgId());
            }
            if (null != b2bInventoryStorage.getSize()) {
                columns.add("size");
                values.add(b2bInventoryStorage.getSize());
            }
            if (null != b2bInventoryStorage.getParentIdPath()) {
                columns.add("parent_id_path");
                values.add(b2bInventoryStorage.getParentIdPath());
            }
            if (null != b2bInventoryStorage.get()) {
                columns.add("");
                values.add(b2bInventoryStorage.get());
            }
            if (null != b2bInventoryStorage.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bInventoryStorage.getCategoryName());
            }
            if (null != b2bInventoryStorage.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bInventoryStorage.getSpecsName());
            }
            if (null != b2bInventoryStorage.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                values.add(b2bInventoryStorage.getOrderPkgCode());
            }
            if (null != b2bInventoryStorage.getVolume()) {
                columns.add("volume");
                values.add(b2bInventoryStorage.getVolume());
            }
            if (null != b2bInventoryStorage.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bInventoryStorage.getSpecsId());
            }
            if (null != b2bInventoryStorage.get()) {
                columns.add("");
                values.add(b2bInventoryStorage.get());
            }
            if (null != b2bInventoryStorage.getOrderLineId()) {
                columns.add("order_line_id");
                values.add(b2bInventoryStorage.getOrderLineId());
            }
            if (null != b2bInventoryStorage.getPic()) {
                columns.add("pic");
                values.add(b2bInventoryStorage.getPic());
            }
            if (null != b2bInventoryStorage.getSeriesName()) {
                columns.add("series_name");
                values.add(b2bInventoryStorage.getSeriesName());
            }
            if (null != b2bInventoryStorage.getRemark()) {
                columns.add("remark");
                values.add(b2bInventoryStorage.getRemark());
            }
            if (null != b2bInventoryStorage.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bInventoryStorage.getBaseProId());
            }
            if (null != b2bInventoryStorage.getProDesc()) {
                columns.add("pro_desc");
                values.add(b2bInventoryStorage.getProDesc());
            }
            if (null != b2bInventoryStorage.getStorageUser()) {
                columns.add("storage_user");
                values.add(b2bInventoryStorage.getStorageUser());
            }
            if (null != b2bInventoryStorage.get()) {
                columns.add("");
                values.add(b2bInventoryStorage.get());
            }
            if (null != b2bInventoryStorage.getInvStorageId()) {
                columns.add("inv_storage_id");
                values.add(b2bInventoryStorage.getInvStorageId());
            }
            if (null != b2bInventoryStorage.getStorageDate()) {
                columns.add("storage_date");
                values.add(b2bInventoryStorage.getStorageDate());
            }
            if (null != b2bInventoryStorage.getBarCode()) {
                columns.add("bar_code");
                values.add(b2bInventoryStorage.getBarCode());
            }
            if (null != b2bInventoryStorage.getProCode()) {
                columns.add("pro_code");
                values.add(b2bInventoryStorage.getProCode());
            }
            if (null != b2bInventoryStorage.getSource()) {
                columns.add("source");
                values.add(b2bInventoryStorage.getSource());
            }
            if (null != b2bInventoryStorage.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bInventoryStorage.getProColorNo());
            }
            if (null != b2bInventoryStorage.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bInventoryStorage.getBaseProNo());
            }
            if (null != b2bInventoryStorage.getProName()) {
                columns.add("pro_name");
                values.add(b2bInventoryStorage.getProName());
            }
            if (null != b2bInventoryStorage.getParentNamePath()) {
                columns.add("parent_name_path");
                values.add(b2bInventoryStorage.getParentNamePath());
            }
            if (null != b2bInventoryStorage.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bInventoryStorage.getCategoryId());
            }
            if (null != b2bInventoryStorage.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bInventoryStorage.getSupplierName());
            }
            if (null != b2bInventoryStorage.getStoragePrice()) {
                columns.add("storage_price");
                values.add(b2bInventoryStorage.getStoragePrice());
            }
            if (null != b2bInventoryStorage.getOrderDetailNo()) {
                columns.add("order_detail_no");
                values.add(b2bInventoryStorage.getOrderDetailNo());
            }
            if (null != b2bInventoryStorage.getCapacity()) {
                columns.add("capacity");
                values.add(b2bInventoryStorage.getCapacity());
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
    public int insertSelectiveAndGetId(B2bInventoryStorageBean b2bInventoryStorage) {
        try {
            if (null == b2bInventoryStorage) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_inventory_storage(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bInventoryStorage.getInvHeadId()) {
                columns.add("inv_head_id");
                parameters.add(":invHeadId");
            }
            if (null != b2bInventoryStorage.getStorageQty()) {
                columns.add("storage_qty");
                parameters.add(":storageQty");
            }
            if (null != b2bInventoryStorage.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bInventoryStorage.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryStorage.getSeriesId()) {
                columns.add("series_id");
                parameters.add(":seriesId");
            }
            if (null != b2bInventoryStorage.getOrderHeadId()) {
                columns.add("order_head_id");
                parameters.add(":orderHeadId");
            }
            if (null != b2bInventoryStorage.getProId()) {
                columns.add("pro_id");
                parameters.add(":proId");
            }
            if (null != b2bInventoryStorage.getOrderPkgId()) {
                columns.add("order_pkg_id");
                parameters.add(":orderPkgId");
            }
            if (null != b2bInventoryStorage.getSize()) {
                columns.add("size");
                parameters.add(":size");
            }
            if (null != b2bInventoryStorage.getParentIdPath()) {
                columns.add("parent_id_path");
                parameters.add(":parentIdPath");
            }
            if (null != b2bInventoryStorage.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryStorage.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bInventoryStorage.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }
            if (null != b2bInventoryStorage.getOrderPkgCode()) {
                columns.add("order_pkg_code");
                parameters.add(":orderPkgCode");
            }
            if (null != b2bInventoryStorage.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bInventoryStorage.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bInventoryStorage.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryStorage.getOrderLineId()) {
                columns.add("order_line_id");
                parameters.add(":orderLineId");
            }
            if (null != b2bInventoryStorage.getPic()) {
                columns.add("pic");
                parameters.add(":pic");
            }
            if (null != b2bInventoryStorage.getSeriesName()) {
                columns.add("series_name");
                parameters.add(":seriesName");
            }
            if (null != b2bInventoryStorage.getRemark()) {
                columns.add("remark");
                parameters.add(":remark");
            }
            if (null != b2bInventoryStorage.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bInventoryStorage.getProDesc()) {
                columns.add("pro_desc");
                parameters.add(":proDesc");
            }
            if (null != b2bInventoryStorage.getStorageUser()) {
                columns.add("storage_user");
                parameters.add(":storageUser");
            }
            if (null != b2bInventoryStorage.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryStorage.getInvStorageId()) {
                columns.add("inv_storage_id");
                parameters.add(":invStorageId");
            }
            if (null != b2bInventoryStorage.getStorageDate()) {
                columns.add("storage_date");
                parameters.add(":storageDate");
            }
            if (null != b2bInventoryStorage.getBarCode()) {
                columns.add("bar_code");
                parameters.add(":barCode");
            }
            if (null != b2bInventoryStorage.getProCode()) {
                columns.add("pro_code");
                parameters.add(":proCode");
            }
            if (null != b2bInventoryStorage.getSource()) {
                columns.add("source");
                parameters.add(":source");
            }
            if (null != b2bInventoryStorage.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bInventoryStorage.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bInventoryStorage.getProName()) {
                columns.add("pro_name");
                parameters.add(":proName");
            }
            if (null != b2bInventoryStorage.getParentNamePath()) {
                columns.add("parent_name_path");
                parameters.add(":parentNamePath");
            }
            if (null != b2bInventoryStorage.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bInventoryStorage.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bInventoryStorage.getStoragePrice()) {
                columns.add("storage_price");
                parameters.add(":storagePrice");
            }
            if (null != b2bInventoryStorage.getOrderDetailNo()) {
                columns.add("order_detail_no");
                parameters.add(":orderDetailNo");
            }
            if (null != b2bInventoryStorage.getCapacity()) {
                columns.add("capacity");
                parameters.add(":capacity");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bInventoryStorage);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bInventoryStorage.setInvStorageId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bInventoryStorageBean b2bInventoryStorage) {
        try {
            if (null == b2bInventoryStorage.getInvStorageId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_inventory_storage SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bInventoryStorage.getInvHeadId()) {
                updateSql.add("inv_head_id = ?");
                params.add(b2bInventoryStorage.getInvHeadId());
            }
            if (null != b2bInventoryStorage.getStorageQty()) {
                updateSql.add("storage_qty = ?");
                params.add(b2bInventoryStorage.getStorageQty());
            }
            if (null != b2bInventoryStorage.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bInventoryStorage.getSupplierId());
            }
            if (null != b2bInventoryStorage.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryStorage.get());
            }
            if (null != b2bInventoryStorage.getSeriesId()) {
                updateSql.add("series_id = ?");
                params.add(b2bInventoryStorage.getSeriesId());
            }
            if (null != b2bInventoryStorage.getOrderHeadId()) {
                updateSql.add("order_head_id = ?");
                params.add(b2bInventoryStorage.getOrderHeadId());
            }
            if (null != b2bInventoryStorage.getProId()) {
                updateSql.add("pro_id = ?");
                params.add(b2bInventoryStorage.getProId());
            }
            if (null != b2bInventoryStorage.getOrderPkgId()) {
                updateSql.add("order_pkg_id = ?");
                params.add(b2bInventoryStorage.getOrderPkgId());
            }
            if (null != b2bInventoryStorage.getSize()) {
                updateSql.add("size = ?");
                params.add(b2bInventoryStorage.getSize());
            }
            if (null != b2bInventoryStorage.getParentIdPath()) {
                updateSql.add("parent_id_path = ?");
                params.add(b2bInventoryStorage.getParentIdPath());
            }
            if (null != b2bInventoryStorage.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryStorage.get());
            }
            if (null != b2bInventoryStorage.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bInventoryStorage.getCategoryName());
            }
            if (null != b2bInventoryStorage.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bInventoryStorage.getSpecsName());
            }
            if (null != b2bInventoryStorage.getOrderPkgCode()) {
                updateSql.add("order_pkg_code = ?");
                params.add(b2bInventoryStorage.getOrderPkgCode());
            }
            if (null != b2bInventoryStorage.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bInventoryStorage.getVolume());
            }
            if (null != b2bInventoryStorage.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bInventoryStorage.getSpecsId());
            }
            if (null != b2bInventoryStorage.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryStorage.get());
            }
            if (null != b2bInventoryStorage.getOrderLineId()) {
                updateSql.add("order_line_id = ?");
                params.add(b2bInventoryStorage.getOrderLineId());
            }
            if (null != b2bInventoryStorage.getPic()) {
                updateSql.add("pic = ?");
                params.add(b2bInventoryStorage.getPic());
            }
            if (null != b2bInventoryStorage.getSeriesName()) {
                updateSql.add("series_name = ?");
                params.add(b2bInventoryStorage.getSeriesName());
            }
            if (null != b2bInventoryStorage.getRemark()) {
                updateSql.add("remark = ?");
                params.add(b2bInventoryStorage.getRemark());
            }
            if (null != b2bInventoryStorage.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bInventoryStorage.getBaseProId());
            }
            if (null != b2bInventoryStorage.getProDesc()) {
                updateSql.add("pro_desc = ?");
                params.add(b2bInventoryStorage.getProDesc());
            }
            if (null != b2bInventoryStorage.getStorageUser()) {
                updateSql.add("storage_user = ?");
                params.add(b2bInventoryStorage.getStorageUser());
            }
            if (null != b2bInventoryStorage.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryStorage.get());
            }
            if (null != b2bInventoryStorage.getInvStorageId()) {
                updateSql.add("inv_storage_id = ?");
                params.add(b2bInventoryStorage.getInvStorageId());
            }
            if (null != b2bInventoryStorage.getStorageDate()) {
                updateSql.add("storage_date = ?");
                params.add(b2bInventoryStorage.getStorageDate());
            }
            if (null != b2bInventoryStorage.getBarCode()) {
                updateSql.add("bar_code = ?");
                params.add(b2bInventoryStorage.getBarCode());
            }
            if (null != b2bInventoryStorage.getProCode()) {
                updateSql.add("pro_code = ?");
                params.add(b2bInventoryStorage.getProCode());
            }
            if (null != b2bInventoryStorage.getSource()) {
                updateSql.add("source = ?");
                params.add(b2bInventoryStorage.getSource());
            }
            if (null != b2bInventoryStorage.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bInventoryStorage.getProColorNo());
            }
            if (null != b2bInventoryStorage.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bInventoryStorage.getBaseProNo());
            }
            if (null != b2bInventoryStorage.getProName()) {
                updateSql.add("pro_name = ?");
                params.add(b2bInventoryStorage.getProName());
            }
            if (null != b2bInventoryStorage.getParentNamePath()) {
                updateSql.add("parent_name_path = ?");
                params.add(b2bInventoryStorage.getParentNamePath());
            }
            if (null != b2bInventoryStorage.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bInventoryStorage.getCategoryId());
            }
            if (null != b2bInventoryStorage.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bInventoryStorage.getSupplierName());
            }
            if (null != b2bInventoryStorage.getStoragePrice()) {
                updateSql.add("storage_price = ?");
                params.add(b2bInventoryStorage.getStoragePrice());
            }
            if (null != b2bInventoryStorage.getOrderDetailNo()) {
                updateSql.add("order_detail_no = ?");
                params.add(b2bInventoryStorage.getOrderDetailNo());
            }
            if (null != b2bInventoryStorage.getCapacity()) {
                updateSql.add("capacity = ?");
                params.add(b2bInventoryStorage.getCapacity());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE inv_storage_id = ?");
            params.add(b2bInventoryStorage.getInvStorageId());

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
    public int deleteByPrimaryKey(String invStorageId) {
            if(null ==  invStorageId) {
            return 0;
        }

        String sql = "delete from b2b_inventory_storage where inv_storage_id  = ?";
        return jdbcTemplate.update(sql, invStorageId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_inventory_storage where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bInventoryStorageBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bInventoryStorageBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bInventoryStorageBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bInventoryStorageBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bInventoryStorageBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bInventoryStorageBean> findAll(Pageable arg0) {
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
	public void delete(B2bInventoryStorageBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bInventoryStorageBean> arg0) {
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
	public Iterable<B2bInventoryStorageBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bInventoryStorageBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bInventoryStorageBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bInventoryStorageBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bInventoryStorageBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bInventoryStorageBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bInventoryStorageBean> findAll(
			Specification<B2bInventoryStorageBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bInventoryStorageBean> findAll(
			Specification<B2bInventoryStorageBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bInventoryStorageBean> findAll(
			Specification<B2bInventoryStorageBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bInventoryStorageBean findOne(
			Specification<B2bInventoryStorageBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
