package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bInventoryHeadBean;

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

@Repository("b2bInventoryHeadDao")
public class B2bInventoryHeadDaoImpl implements B2bInventoryHeadDao {
    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public B2bInventoryHeadBean selectByPrimaryKey(String invHeadId) {
        try {
            String sql = "select * from b2b_inventory_head where inv_head_id = ?";

            List<B2bInventoryHeadBean> resultList = this.jdbcTemplate.query(sql, new Object[]{invHeadId},
                    new RowMapper<B2bInventoryHeadBean>() {
                        @Override
                        public B2bInventoryHeadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							B2bInventoryHeadBean bean = new B2bInventoryHeadBean();
                            bean.set(rs.getString(""));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setRealQty(rs.getString("real_qty"));
                            bean.set(rs.getDate(""));
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setAvailableQty(rs.getString("available_qty"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setCapacityVolume(rs.getString("capacity_volume"));
                            bean.setTotalQty(rs.getString("total_qty"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.set(rs.getString(""));
                            bean.setSafetyStock(rs.getString("safety_stock"));
                            bean.setDeliveryQty(rs.getString("delivery_qty"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.set(rs.getDate(""));
                            bean.setProName(rs.getString("pro_name"));
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
    public List<B2bInventoryHeadBean> selectByWhereSql(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_inventory_head ";
            } else {
                sql = "select * from b2b_inventory_head where " + whereSql;
            }

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bInventoryHeadBean>() {
                        @Override
                        public B2bInventoryHeadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			B2bInventoryHeadBean bean = new B2bInventoryHeadBean();
                            bean.set(rs.getString(""));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setRealQty(rs.getString("real_qty"));
                            bean.set(rs.getDate(""));
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setAvailableQty(rs.getString("available_qty"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setCapacityVolume(rs.getString("capacity_volume"));
                            bean.setTotalQty(rs.getString("total_qty"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.set(rs.getString(""));
                            bean.setSafetyStock(rs.getString("safety_stock"));
                            bean.setDeliveryQty(rs.getString("delivery_qty"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.set(rs.getDate(""));
                            bean.setProName(rs.getString("pro_name"));
			 return bean;
			}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bInventoryHeadBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select * from b2b_inventory_head ";
            } else {
                sql = "select * from b2b_inventory_head where " + whereSql;
            }

            if(null == startPos) {
                startPos = new Long(0);
            }
            if(null == num) {
                num = new Long(0);
            }

            sql += String.format(" limit %d,%d", startPos, num);

            return this.jdbcTemplate.query(sql, params,
                    new RowMapper<B2bInventoryHeadBean>() {
                        @Override
                        public B2bInventoryHeadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				B2bInventoryHeadBean bean = new B2bInventoryHeadBean();
                            bean.set(rs.getString(""));
                            bean.setSeriesId(rs.getString("series_id"));
                            bean.setBarCode(rs.getString("bar_code"));
                            bean.setSeriesName(rs.getString("series_name"));
                            bean.setBaseProNo(rs.getString("base_pro_no"));
                            bean.setVolume(rs.getString("volume"));
                            bean.setCategoryName(rs.getString("category_name"));
                            bean.setRealQty(rs.getString("real_qty"));
                            bean.set(rs.getDate(""));
                            bean.setInvHeadId(rs.getString("inv_head_id"));
                            bean.setSupplierType(rs.getString("supplier_type"));
                            bean.setAvailableQty(rs.getString("available_qty"));
                            bean.setSupplierId(rs.getString("supplier_id"));
                            bean.setApplDesc(rs.getString("appl_desc"));
                            bean.setCapacity(rs.getString("capacity"));
                            bean.setSize(rs.getString("size"));
                            bean.set(rs.getString(""));
                            bean.setBaseProId(rs.getString("base_pro_id"));
                            bean.setSupplierName(rs.getString("supplier_name"));
                            bean.setProColorNo(rs.getString("pro_color_no"));
                            bean.setCategoryId(rs.getString("category_id"));
                            bean.setCapacityVolume(rs.getString("capacity_volume"));
                            bean.setTotalQty(rs.getString("total_qty"));
                            bean.setSpecsId(rs.getString("specs_id"));
                            bean.setProDesc(rs.getString("pro_desc"));
                            bean.set(rs.getString(""));
                            bean.setSafetyStock(rs.getString("safety_stock"));
                            bean.setDeliveryQty(rs.getString("delivery_qty"));
                            bean.setRestId(rs.getString("rest_id"));
                            bean.setSpecsName(rs.getString("specs_name"));
                            bean.set(rs.getDate(""));
                            bean.setProName(rs.getString("pro_name"));
							return bean;
						}
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<B2bInventoryHeadBean> selectAll() {
        return selectByWhereSql(null, null);
    }

    @Override
    public long count(String whereSql, Object[] params) {
        try {
            String sql;
            if (StringUtils.isBlank(whereSql)) {
                sql = "select count(1) numCount from b2b_inventory_head ";
            } else {
                sql = "select count(1) numCount from b2b_inventory_head where " + whereSql;
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
    public int insertSelective(B2bInventoryHeadBean b2bInventoryHead) {
        try {
            if (null == b2bInventoryHead) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_inventory_head(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> values = new ArrayList<Object>();

            if (null != b2bInventoryHead.get()) {
                columns.add("");
                values.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getSeriesId()) {
                columns.add("series_id");
                values.add(b2bInventoryHead.getSeriesId());
            }
            if (null != b2bInventoryHead.getBarCode()) {
                columns.add("bar_code");
                values.add(b2bInventoryHead.getBarCode());
            }
            if (null != b2bInventoryHead.getSeriesName()) {
                columns.add("series_name");
                values.add(b2bInventoryHead.getSeriesName());
            }
            if (null != b2bInventoryHead.getBaseProNo()) {
                columns.add("base_pro_no");
                values.add(b2bInventoryHead.getBaseProNo());
            }
            if (null != b2bInventoryHead.getVolume()) {
                columns.add("volume");
                values.add(b2bInventoryHead.getVolume());
            }
            if (null != b2bInventoryHead.getCategoryName()) {
                columns.add("category_name");
                values.add(b2bInventoryHead.getCategoryName());
            }
            if (null != b2bInventoryHead.getRealQty()) {
                columns.add("real_qty");
                values.add(b2bInventoryHead.getRealQty());
            }
            if (null != b2bInventoryHead.get()) {
                columns.add("");
                values.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getInvHeadId()) {
                columns.add("inv_head_id");
                values.add(b2bInventoryHead.getInvHeadId());
            }
            if (null != b2bInventoryHead.getSupplierType()) {
                columns.add("supplier_type");
                values.add(b2bInventoryHead.getSupplierType());
            }
            if (null != b2bInventoryHead.getAvailableQty()) {
                columns.add("available_qty");
                values.add(b2bInventoryHead.getAvailableQty());
            }
            if (null != b2bInventoryHead.getSupplierId()) {
                columns.add("supplier_id");
                values.add(b2bInventoryHead.getSupplierId());
            }
            if (null != b2bInventoryHead.getApplDesc()) {
                columns.add("appl_desc");
                values.add(b2bInventoryHead.getApplDesc());
            }
            if (null != b2bInventoryHead.getCapacity()) {
                columns.add("capacity");
                values.add(b2bInventoryHead.getCapacity());
            }
            if (null != b2bInventoryHead.getSize()) {
                columns.add("size");
                values.add(b2bInventoryHead.getSize());
            }
            if (null != b2bInventoryHead.get()) {
                columns.add("");
                values.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getBaseProId()) {
                columns.add("base_pro_id");
                values.add(b2bInventoryHead.getBaseProId());
            }
            if (null != b2bInventoryHead.getSupplierName()) {
                columns.add("supplier_name");
                values.add(b2bInventoryHead.getSupplierName());
            }
            if (null != b2bInventoryHead.getProColorNo()) {
                columns.add("pro_color_no");
                values.add(b2bInventoryHead.getProColorNo());
            }
            if (null != b2bInventoryHead.getCategoryId()) {
                columns.add("category_id");
                values.add(b2bInventoryHead.getCategoryId());
            }
            if (null != b2bInventoryHead.getCapacityVolume()) {
                columns.add("capacity_volume");
                values.add(b2bInventoryHead.getCapacityVolume());
            }
            if (null != b2bInventoryHead.getTotalQty()) {
                columns.add("total_qty");
                values.add(b2bInventoryHead.getTotalQty());
            }
            if (null != b2bInventoryHead.getSpecsId()) {
                columns.add("specs_id");
                values.add(b2bInventoryHead.getSpecsId());
            }
            if (null != b2bInventoryHead.getProDesc()) {
                columns.add("pro_desc");
                values.add(b2bInventoryHead.getProDesc());
            }
            if (null != b2bInventoryHead.get()) {
                columns.add("");
                values.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getSafetyStock()) {
                columns.add("safety_stock");
                values.add(b2bInventoryHead.getSafetyStock());
            }
            if (null != b2bInventoryHead.getDeliveryQty()) {
                columns.add("delivery_qty");
                values.add(b2bInventoryHead.getDeliveryQty());
            }
            if (null != b2bInventoryHead.getRestId()) {
                columns.add("rest_id");
                values.add(b2bInventoryHead.getRestId());
            }
            if (null != b2bInventoryHead.getSpecsName()) {
                columns.add("specs_name");
                values.add(b2bInventoryHead.getSpecsName());
            }
            if (null != b2bInventoryHead.get()) {
                columns.add("");
                values.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getProName()) {
                columns.add("pro_name");
                values.add(b2bInventoryHead.getProName());
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
    public int insertSelectiveAndGetId(B2bInventoryHeadBean b2bInventoryHead) {
        try {
            if (null == b2bInventoryHead) {
                return 0;
            }

            StringBuilder sql = new StringBuilder("insert into b2b_inventory_head(");
            List<Object> columns = new ArrayList<Object>();
            List<Object> parameters = new ArrayList<Object>();

            if (null != b2bInventoryHead.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryHead.getSeriesId()) {
                columns.add("series_id");
                parameters.add(":seriesId");
            }
            if (null != b2bInventoryHead.getBarCode()) {
                columns.add("bar_code");
                parameters.add(":barCode");
            }
            if (null != b2bInventoryHead.getSeriesName()) {
                columns.add("series_name");
                parameters.add(":seriesName");
            }
            if (null != b2bInventoryHead.getBaseProNo()) {
                columns.add("base_pro_no");
                parameters.add(":baseProNo");
            }
            if (null != b2bInventoryHead.getVolume()) {
                columns.add("volume");
                parameters.add(":volume");
            }
            if (null != b2bInventoryHead.getCategoryName()) {
                columns.add("category_name");
                parameters.add(":categoryName");
            }
            if (null != b2bInventoryHead.getRealQty()) {
                columns.add("real_qty");
                parameters.add(":realQty");
            }
            if (null != b2bInventoryHead.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryHead.getInvHeadId()) {
                columns.add("inv_head_id");
                parameters.add(":invHeadId");
            }
            if (null != b2bInventoryHead.getSupplierType()) {
                columns.add("supplier_type");
                parameters.add(":supplierType");
            }
            if (null != b2bInventoryHead.getAvailableQty()) {
                columns.add("available_qty");
                parameters.add(":availableQty");
            }
            if (null != b2bInventoryHead.getSupplierId()) {
                columns.add("supplier_id");
                parameters.add(":supplierId");
            }
            if (null != b2bInventoryHead.getApplDesc()) {
                columns.add("appl_desc");
                parameters.add(":applDesc");
            }
            if (null != b2bInventoryHead.getCapacity()) {
                columns.add("capacity");
                parameters.add(":capacity");
            }
            if (null != b2bInventoryHead.getSize()) {
                columns.add("size");
                parameters.add(":size");
            }
            if (null != b2bInventoryHead.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryHead.getBaseProId()) {
                columns.add("base_pro_id");
                parameters.add(":baseProId");
            }
            if (null != b2bInventoryHead.getSupplierName()) {
                columns.add("supplier_name");
                parameters.add(":supplierName");
            }
            if (null != b2bInventoryHead.getProColorNo()) {
                columns.add("pro_color_no");
                parameters.add(":proColorNo");
            }
            if (null != b2bInventoryHead.getCategoryId()) {
                columns.add("category_id");
                parameters.add(":categoryId");
            }
            if (null != b2bInventoryHead.getCapacityVolume()) {
                columns.add("capacity_volume");
                parameters.add(":capacityVolume");
            }
            if (null != b2bInventoryHead.getTotalQty()) {
                columns.add("total_qty");
                parameters.add(":totalQty");
            }
            if (null != b2bInventoryHead.getSpecsId()) {
                columns.add("specs_id");
                parameters.add(":specsId");
            }
            if (null != b2bInventoryHead.getProDesc()) {
                columns.add("pro_desc");
                parameters.add(":proDesc");
            }
            if (null != b2bInventoryHead.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryHead.getSafetyStock()) {
                columns.add("safety_stock");
                parameters.add(":safetyStock");
            }
            if (null != b2bInventoryHead.getDeliveryQty()) {
                columns.add("delivery_qty");
                parameters.add(":deliveryQty");
            }
            if (null != b2bInventoryHead.getRestId()) {
                columns.add("rest_id");
                parameters.add(":restId");
            }
            if (null != b2bInventoryHead.getSpecsName()) {
                columns.add("specs_name");
                parameters.add(":specsName");
            }
            if (null != b2bInventoryHead.get()) {
                columns.add("");
                parameters.add(":");
            }
            if (null != b2bInventoryHead.getProName()) {
                columns.add("pro_name");
                parameters.add(":proName");
            }

            if (columns.isEmpty()) {
                return 0;
            }

            sql.append(StringUtils.join(columns, ',')).append(") values (").append(StringUtils.join(parameters, ',')).append(")");
			
			SqlParameterSource ps = new BeanPropertySqlParameterSource(b2bInventoryHead);
            KeyHolder keyholder = new GeneratedKeyHolder();
	    if(null==namedParameterJdbcTemplate){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

            int updateNums = namedParameterJdbcTemplate.update(sql.toString(), ps, keyholder);
            String m = keyholder.getKey().toString();
	    // String key=UUID.randomUUID().toString()
	     b2bInventoryHead.setInvHeadId(m);
			
            return updateNums;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(B2bInventoryHeadBean b2bInventoryHead) {
        try {
            if (null == b2bInventoryHead.getInvHeadId()) {
                throw new RuntimeException("updateByPrimaryKeySelective fail! ID can not be null");
            }

            StringBuilder sql = new StringBuilder("UPDATE b2b_inventory_head SET ");

            List<String> updateSql = new ArrayList<String>();
            List<Object> params = new ArrayList<Object>();
            if (null != b2bInventoryHead.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getSeriesId()) {
                updateSql.add("series_id = ?");
                params.add(b2bInventoryHead.getSeriesId());
            }
            if (null != b2bInventoryHead.getBarCode()) {
                updateSql.add("bar_code = ?");
                params.add(b2bInventoryHead.getBarCode());
            }
            if (null != b2bInventoryHead.getSeriesName()) {
                updateSql.add("series_name = ?");
                params.add(b2bInventoryHead.getSeriesName());
            }
            if (null != b2bInventoryHead.getBaseProNo()) {
                updateSql.add("base_pro_no = ?");
                params.add(b2bInventoryHead.getBaseProNo());
            }
            if (null != b2bInventoryHead.getVolume()) {
                updateSql.add("volume = ?");
                params.add(b2bInventoryHead.getVolume());
            }
            if (null != b2bInventoryHead.getCategoryName()) {
                updateSql.add("category_name = ?");
                params.add(b2bInventoryHead.getCategoryName());
            }
            if (null != b2bInventoryHead.getRealQty()) {
                updateSql.add("real_qty = ?");
                params.add(b2bInventoryHead.getRealQty());
            }
            if (null != b2bInventoryHead.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getInvHeadId()) {
                updateSql.add("inv_head_id = ?");
                params.add(b2bInventoryHead.getInvHeadId());
            }
            if (null != b2bInventoryHead.getSupplierType()) {
                updateSql.add("supplier_type = ?");
                params.add(b2bInventoryHead.getSupplierType());
            }
            if (null != b2bInventoryHead.getAvailableQty()) {
                updateSql.add("available_qty = ?");
                params.add(b2bInventoryHead.getAvailableQty());
            }
            if (null != b2bInventoryHead.getSupplierId()) {
                updateSql.add("supplier_id = ?");
                params.add(b2bInventoryHead.getSupplierId());
            }
            if (null != b2bInventoryHead.getApplDesc()) {
                updateSql.add("appl_desc = ?");
                params.add(b2bInventoryHead.getApplDesc());
            }
            if (null != b2bInventoryHead.getCapacity()) {
                updateSql.add("capacity = ?");
                params.add(b2bInventoryHead.getCapacity());
            }
            if (null != b2bInventoryHead.getSize()) {
                updateSql.add("size = ?");
                params.add(b2bInventoryHead.getSize());
            }
            if (null != b2bInventoryHead.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getBaseProId()) {
                updateSql.add("base_pro_id = ?");
                params.add(b2bInventoryHead.getBaseProId());
            }
            if (null != b2bInventoryHead.getSupplierName()) {
                updateSql.add("supplier_name = ?");
                params.add(b2bInventoryHead.getSupplierName());
            }
            if (null != b2bInventoryHead.getProColorNo()) {
                updateSql.add("pro_color_no = ?");
                params.add(b2bInventoryHead.getProColorNo());
            }
            if (null != b2bInventoryHead.getCategoryId()) {
                updateSql.add("category_id = ?");
                params.add(b2bInventoryHead.getCategoryId());
            }
            if (null != b2bInventoryHead.getCapacityVolume()) {
                updateSql.add("capacity_volume = ?");
                params.add(b2bInventoryHead.getCapacityVolume());
            }
            if (null != b2bInventoryHead.getTotalQty()) {
                updateSql.add("total_qty = ?");
                params.add(b2bInventoryHead.getTotalQty());
            }
            if (null != b2bInventoryHead.getSpecsId()) {
                updateSql.add("specs_id = ?");
                params.add(b2bInventoryHead.getSpecsId());
            }
            if (null != b2bInventoryHead.getProDesc()) {
                updateSql.add("pro_desc = ?");
                params.add(b2bInventoryHead.getProDesc());
            }
            if (null != b2bInventoryHead.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getSafetyStock()) {
                updateSql.add("safety_stock = ?");
                params.add(b2bInventoryHead.getSafetyStock());
            }
            if (null != b2bInventoryHead.getDeliveryQty()) {
                updateSql.add("delivery_qty = ?");
                params.add(b2bInventoryHead.getDeliveryQty());
            }
            if (null != b2bInventoryHead.getRestId()) {
                updateSql.add("rest_id = ?");
                params.add(b2bInventoryHead.getRestId());
            }
            if (null != b2bInventoryHead.getSpecsName()) {
                updateSql.add("specs_name = ?");
                params.add(b2bInventoryHead.getSpecsName());
            }
            if (null != b2bInventoryHead.get()) {
                updateSql.add(" = ?");
                params.add(b2bInventoryHead.get());
            }
            if (null != b2bInventoryHead.getProName()) {
                updateSql.add("pro_name = ?");
                params.add(b2bInventoryHead.getProName());
            }

            if (updateSql.isEmpty()) { // all fields is null, nothing to update
                return 0;
            }

            sql.append(StringUtils.join(updateSql, ", ")).append(" WHERE inv_head_id = ?");
            params.add(b2bInventoryHead.getInvHeadId());

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
    public int deleteByPrimaryKey(String invHeadId) {
            if(null ==  invHeadId) {
            return 0;
        }

        String sql = "delete from b2b_inventory_head where inv_head_id  = ?";
        return jdbcTemplate.update(sql, invHeadId);
    }

    @Override
    public int deleteByWhereSql(String whereSql, Object[] params) {
        if(StringUtils.isBlank(whereSql)) {
            return 0;
        }

        String sql = "DELETE from b2b_inventory_head where " + whereSql;
        return this.jdbcTemplate.update(sql, params);
    }

	//////////////////////No Used
	 @Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<B2bInventoryHeadBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<B2bInventoryHeadBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<B2bInventoryHeadBean> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveWithId(B2bInventoryHeadBean paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<B2bInventoryHeadBean> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bInventoryHeadBean> findAll(Pageable arg0) {
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
	public void delete(B2bInventoryHeadBean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends B2bInventoryHeadBean> arg0) {
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
	public Iterable<B2bInventoryHeadBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<B2bInventoryHeadBean> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bInventoryHeadBean findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bInventoryHeadBean> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends B2bInventoryHeadBean> Iterable<S> save(
			Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<B2bInventoryHeadBean> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<B2bInventoryHeadBean> findAll(
			Specification<B2bInventoryHeadBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<B2bInventoryHeadBean> findAll(
			Specification<B2bInventoryHeadBean> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2bInventoryHeadBean> findAll(
			Specification<B2bInventoryHeadBean> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2bInventoryHeadBean findOne(
			Specification<B2bInventoryHeadBean> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
