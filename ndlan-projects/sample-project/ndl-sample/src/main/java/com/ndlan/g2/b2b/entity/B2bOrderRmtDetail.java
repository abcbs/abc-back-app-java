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
@Table(name = "b2b_order_rmt_detail" )
public class B2bOrderRmtDetail  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    
    
    
    
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    /**
     * ??¡ã¨¦???¢ã????????¢ã¡®
     **/
    @Column(name = "quantity"
     ,length = 100
    
    
    
    )
    private String quantity;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    @Column(name = "pro_color_no"
     ,length = 36
    
    
    
    )
    private String proColorNo;
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remake"
     ,length = 255
    
    
    
    )
    private String remake;
    /**
     * ??????
     **/
    @Column(name = "volume"
     ,length = 10
    
    
    
    )
    private String volume;
    @Column(name = "parent_name_path"
     ,length = 255
    
    
    
    )
    private String parentNamePath;
    /**
     * ?????¡¤ID
     **/
    @Column(name = "customer_id"
     ,length = 36
    
    
    
    )
    private String customerId;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    @Column(name = "pro_desc"
     ,length = 255
    
    
    
    )
    private String proDesc;
    /**
     * ??¡±?¡±¡§??????
     **/
    @Column(name = "appl_desc"
     ,length = 255
    
    
    
    )
    private String applDesc;
    @Column(name = "rest_name"
     ,length = 255
    
    
    
    )
    private String restName;
    /**
     * ?????¡¤????¡ì¡ã
     **/
    @Column(name = "customer_name"
     ,length = 255
    
    
    
    )
    private String customerName;
    /**
     * ¨¨??¨¨?¡ì??¨C???
     **/
    @Column(name = "order_rmt_detail_code"
     ,length = 36
    
    
    
    )
    private String orderRmtDetailCode;
    /**
     * ???¨¨?????¨¨??ID
     **/
    @Column(name = "order_detail_id"
     ,length = 36
    
    
    
    )
    private String orderDetailId;
    
    
    
    
    /**
     * ???¨¨???????¨C??¡¤
     **/
    @Column(name = "order_detail_no"
     ,length = 36
    
    
    
    )
    private String orderDetailNo;
    /**
     * ????¡ë??¢ã????????¢ã¡®
     **/
    @Column(name = "pic"
     ,length = 100
    
    
    
    )
    private String pic;
    /**
     * ¨¨??¨¨?¡ìID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "order_rmt_detail_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String orderRmtDetailId;
    /**
     * ??¡ì?¡°?ID
     **/
    @Column(name = "base_pro_id"
     ,length = 36
    
    
    
    )
    private String baseProId;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "pro_code"
     ,length = 36
    
    
    
    )
    private String proCode;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "base_pro_no"
     ,length = 50
    
    
    
    )
    private String baseProNo;
    /**
     * ?????¡±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ¨¨¡ì????ID
     **/
    @Column(name = "specs_id"
     ,length = 36
    
    
    
    )
    private String specsId;
    /**
     * ????¡°??????¡ª
     **/
    @Column(name = "series_name"
     ,length = 255
    
    
    
    )
    private String seriesName;
    @Column(name = "parent_id_path"
     ,length = 36
    
    
    
    )
    private String parentIdPath;
    /**
     * ???¨¨???????¡­??¨C??¡¤
     **/
    @Column(name = "order_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderPkgCode;
    /**
     * ???¨¨?????????¢ã????0??¡­???????¢ã?1¨¦?¡§????????????2???????????????3??¡­??¡®¨¨?¡ì?¢ã?4?¡¤???¡®¨¨?¡ì?¢ã?5?¡¤??¡±?¨¨?¡ì?¢ã?6??¨C?????¡ë
     **/
    @Column(name = "orign_status"
     ,length = 2
    
    
    
    )
    private String orignStatus;
    /**
     * ???¨¦??
     **/
    @Column(name = "capacity"
     ,length = 100
    
    
    
    )
    private String capacity;
    
    
    
    
    /**
     * ??¡¤???
     **/
    @Column(name = "price"
     ,length = 100
    
    
    
    )
    private String price;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    @Column(name = "pro_name"
     ,length = 255
    
    
    
    )
    private String proName;
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ¨¨??¨¨?¡ì??¡­ID
     **/
    @Column(name = "order_rmt_pkg_id"
     ,length = 36
    
    
    
    )
    private String orderRmtPkgId;
    
    
    
    
    
    
    
    
    /**
     * ¨¨??¨¨?¡ì?¡è?ID
     **/
    @Column(name = "order_rmt_head_id"
     ,length = 36
    
    
    
    )
    private String orderRmtHeadId;
    /**
     * ????¡°??????¡ªID
     **/
    @Column(name = "series_id"
     ,length = 36
    
    
    
    )
    private String seriesId;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    @Column(name = "size"
     ,length = 36
    
    
    
    )
    private String size;
    /**
     * ?????????¨¨?¡ã
     **/
    @Column(name = "damage_spec"
     ,length = 255
    
    
    
    )
    private String damageSpec;
    /**
     * ???¨¨???????¡­ID
     **/
    @Column(name = "order_pkg_id"
     ,length = 36
    
    
    
    )
    private String orderPkgId;
    /**
     * ??????
     **/
    @Column(name = "bar_code"
     ,length = 36
    
    
    
    )
    private String barCode;
    /**
     * ¨¨¡ì????
     **/
    @Column(name = "specs_name"
     ,length = 255
    
    
    
    )
    private String specsName;
    /**
     * ???¨¨??????¡è?Id
     **/
    @Column(name = "order_head_id"
     ,length = 36
    
    
    
    )
    private String orderHeadId;
    /**
     * ?¡­???¡°????¢ã?(0????¡±?¨¨?¡ì???1??¡­?¡­???¡°???2?¡¤??¡­???¡°)
     **/
    @Column(name = "storage_status"
     ,length = 20
    
    
    
    )
    private String storageStatus;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    @Column(name = "category_id"
     ,length = 36
    
    
    
    )
    private String categoryId;


   

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
    public void setParentNamePath(String parentNamePath) {
        this.parentNamePath = parentNamePath;
    }

    public String getParentNamePath() {
        return parentNamePath;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }
    public void setApplDesc(String applDesc) {
        this.applDesc = applDesc;
    }

    public String getApplDesc() {
        return applDesc;
    }
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setOrderRmtDetailCode(String orderRmtDetailCode) {
        this.orderRmtDetailCode = orderRmtDetailCode;
    }

    public String getOrderRmtDetailCode() {
        return orderRmtDetailCode;
    }
    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }
    public void setOrderDetailNo(String orderDetailNo) {
        this.orderDetailNo = orderDetailNo;
    }

    public String getOrderDetailNo() {
        return orderDetailNo;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setOrderRmtDetailId(String orderRmtDetailId) {
        this.orderRmtDetailId = orderRmtDetailId;
    }

    public String getOrderRmtDetailId() {
        return orderRmtDetailId;
    }
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProCode() {
        return proCode;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesName() {
        return seriesName;
    }
    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }
    public void setOrignStatus(String orignStatus) {
        this.orignStatus = orignStatus;
    }

    public String getOrignStatus() {
        return orignStatus;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setOrderRmtPkgId(String orderRmtPkgId) {
        this.orderRmtPkgId = orderRmtPkgId;
    }

    public String getOrderRmtPkgId() {
        return orderRmtPkgId;
    }
    public void setOrderRmtHeadId(String orderRmtHeadId) {
        this.orderRmtHeadId = orderRmtHeadId;
    }

    public String getOrderRmtHeadId() {
        return orderRmtHeadId;
    }
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesId() {
        return seriesId;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    public void setDamageSpec(String damageSpec) {
        this.damageSpec = damageSpec;
    }

    public String getDamageSpec() {
        return damageSpec;
    }
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }
    public void setOrderHeadId(String orderHeadId) {
        this.orderHeadId = orderHeadId;
    }

    public String getOrderHeadId() {
        return orderHeadId;
    }
    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public String getStorageStatus() {
        return storageStatus;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }


}
