package com.ndlan.g2.b2b.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import com.ndlan.canyin.base.entity.BaseEntity;
import java.util.List;
import java.util.Map;

import java.util.Date;


@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "b2b_inventory_head" )
public class B2bInventoryHead  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    
    
    
    
    /**
     * ????¡°??????¡ªID
     **/
    @Column(name = "series_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String seriesId;
    /**
     * ??????
     **/
    @Column(name = "bar_code"
     ,length = 36
    
    
    
    )
    private String barCode;
    /**
     * ????¡°??????¡ª
     **/
    @Column(name = "series_name"
     ,length = 255
    
    
    
    )
    private String seriesName;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "base_pro_no"
     ,length = 50
    
    
    
    )
    private String baseProNo;
    /**
     * ??????
     **/
    @Column(name = "volume"
     ,length = 10
    
    
    
    )
    private String volume;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    /**
     * ???¨¦?¡­??¡ã¨¦??
     **/
    @Column(name = "real_qty"
     ,length = 100
    
    
    
    )
    private String realQty;
    
    
    
    
    /**
     * ???¨¦¡±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "inv_head_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String invHeadId;
    /**
     * 0:???¨¦¡±¢ã????¢ã?1????????¡±???
     **/
    @Column(name = "supplier_type"
     ,length = 4
    
    
    
    )
    private String supplierType;
    /**
     * ????¡±?¨¦??
     **/
    @Column(name = "available_qty"
     ,length = 100
    
    
    
    )
    private String availableQty;
    /**
     * ?????¡±?????¨C????¢ã????¨¦¡±¢ã????¢ã¡®
     **/
    @Column(name = "supplier_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String supplierId;
    /**
     * ??¡±?¡±¡§??????
     **/
    @Column(name = "appl_desc"
     ,length = 255
    
    
    
    )
    private String applDesc;
    /**
     * ???¨¦??
     **/
    @Column(name = "capacity"
     ,length = 100
    
    
    
    )
    private String capacity;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    @Column(name = "size"
     ,length = 36
    
    
    
    )
    private String size;
    
    
    
    
    /**
     * ??¡ì?¡°?ID
     **/
    @Column(name = "base_pro_id"
     ,length = 36
    
    
    
    )
    private String baseProId;
    /**
     * ?????¡±???????¡ì¡ã?¢ã????¨¦¡±¢ã????¢ã¡®
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    @Column(name = "pro_color_no"
     ,length = 36
    
    
    
    )
    private String proColorNo;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    @Column(name = "category_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String categoryId;
    @Column(name = "capacity_volume"
     ,length = 10
    
    
    
    )
    private String capacityVolume;
    /**
     * ?¢ã???¡ã¨¦??
     **/
    @Column(name = "total_qty"
     ,length = 100
    
    
    
    )
    private String totalQty;
    @Column(name = "specs_id"
     ,length = 36
    
    
    
    )
    private String specsId;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    @Column(name = "pro_desc"
     ,length = 255
    
    
    
    )
    private String proDesc;
    
    
    
    
    /**
     * ??¡ë?¡­¡§??¡°???
     **/
    @Column(name = "safety_stock"
     ,length = 100
    
    
    
    )
    private String safetyStock;
    /**
     * ?????¡°¨¦??
     **/
    @Column(name = "delivery_qty"
     ,length = 100
    
    
    
    )
    private String deliveryQty;
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ¨¨¡ì????
     **/
    @Column(name = "specs_name"
     ,length = 255
    
    
    
    )
    private String specsName;
    
    
    
    
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    @Column(name = "pro_name"
     ,length = 255
    
    
    
    )
    private String proName;


   

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesId() {
        return seriesId;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesName() {
        return seriesName;
    }
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setRealQty(String realQty) {
        this.realQty = realQty;
    }

    public String getRealQty() {
        return realQty;
    }
    public void setInvHeadId(String invHeadId) {
        this.invHeadId = invHeadId;
    }

    public String getInvHeadId() {
        return invHeadId;
    }
    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierType() {
        return supplierType;
    }
    public void setAvailableQty(String availableQty) {
        this.availableQty = availableQty;
    }

    public String getAvailableQty() {
        return availableQty;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setApplDesc(String applDesc) {
        this.applDesc = applDesc;
    }

    public String getApplDesc() {
        return applDesc;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setCapacityVolume(String capacityVolume) {
        this.capacityVolume = capacityVolume;
    }

    public String getCapacityVolume() {
        return capacityVolume;
    }
    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    public String getTotalQty() {
        return totalQty;
    }
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }
    public void setSafetyStock(String safetyStock) {
        this.safetyStock = safetyStock;
    }

    public String getSafetyStock() {
        return safetyStock;
    }
    public void setDeliveryQty(String deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getDeliveryQty() {
        return deliveryQty;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }


}
