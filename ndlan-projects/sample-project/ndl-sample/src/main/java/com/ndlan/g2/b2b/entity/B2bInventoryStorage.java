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
@Table(name = "b2b_inventory_storage" )
public class B2bInventoryStorage  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ??¡°????¡è?¨¨?¡§
     **/
    @Column(name = "inv_head_id"
     ,length = 36
    
    
    
    )
    private String invHeadId;
    /**
     * ?¡­???¡ã¨¦??
     **/
    @Column(name = "storage_qty"
     ,length = 100
    
    
    
    )
    private String storageQty;
    /**
     * ?????¡±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    
    
    
    
    @Column(name = "series_id"
     ,length = 36
    
    
    
    )
    private String seriesId;
    /**
     * ¨¨??????¡è?ID
     **/
    @Column(name = "order_head_id"
     ,length = 36
    
    
    
    )
    private String orderHeadId;
    @Column(name = "pro_id"
     ,length = 36
    
    
    
    )
    private String proId;
    /**
     * ¨¨???????¡­ID
     **/
    @Column(name = "order_pkg_id"
     ,length = 36
    
    
    
    )
    private String orderPkgId;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    @Column(name = "size"
     ,length = 36
    
    
    
    )
    private String size;
    @Column(name = "parent_id_path"
     ,length = 255
    
    
    
    )
    private String parentIdPath;
    
    
    
    
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    /**
     * ¨¨¡ì????
     **/
    @Column(name = "specs_name"
     ,length = 255
    
    
    
    )
    private String specsName;
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    @Column(name = "order_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderPkgCode;
    /**
     * ??????
     **/
    @Column(name = "volume"
     ,length = 10
    
    
    
    )
    private String volume;
    @Column(name = "specs_id"
     ,length = 36
    
    
    
    )
    private String specsId;
    
    
    
    
    @Column(name = "order_line_id"
     ,length = 36
    
    
    
    )
    private String orderLineId;
    /**
     * ????¡ë?
     **/
    @Column(name = "pic"
     ,length = 100
    
    
    
    )
    private String pic;
    /**
     * ????¡°??????¡ª
     **/
    @Column(name = "series_name"
     ,length = 255
    
    
    
    )
    private String seriesName;
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remark"
     ,length = 255
    
    
    
    )
    private String remark;
    /**
     * ??¡ì?¡°?ID
     **/
    @Column(name = "base_pro_id"
     ,length = 36
    
    
    
    )
    private String baseProId;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    @Column(name = "pro_desc"
     ,length = 255
    
    
    
    )
    private String proDesc;
    /**
     * ?¡­???¡°???
     **/
    @Column(name = "storage_user"
     ,length = 255
    
    
    
    )
    private String storageUser;
    
    
    
    
    /**
     * ???¨¦¡±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "inv_storage_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String invStorageId;
    /**
     * ?¡­???¡°?¡ª????
     **/
    @Column(name = "storage_date"
    
    
    
    
    )
    private Date storageDate;
    /**
     * ??????
     **/
    @Column(name = "bar_code"
     ,length = 36
    
    
    
    )
    private String barCode;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "pro_code"
     ,length = 36
    
    
    
    )
    private String proCode;
    /**
     * ?¡­???¡°???????¢ã?¨¨?????|¨¨¡ã????|¨¨??¨¨?¡ì?¢ã¡®
     **/
    @Column(name = "source"
     ,length = 36
    
    
    
    )
    private String source;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    @Column(name = "pro_color_no"
     ,length = 36
    
    
    
    )
    private String proColorNo;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "base_pro_no"
     ,length = 50
    
    
    
    )
    private String baseProNo;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    @Column(name = "pro_name"
     ,length = 255
    
    
    
    )
    private String proName;
    @Column(name = "parent_name_path"
     ,length = 255
    
    
    
    )
    private String parentNamePath;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    @Column(name = "category_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String categoryId;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ?¡­???¡°??¡¤???
     **/
    @Column(name = "storage_price"
     ,length = 100
    
    
    
    )
    private String storagePrice;
    /**
     * ????????¨C??¡¤
     **/
    @Column(name = "order_detail_no"
     ,length = 36
    
    
    
    )
    private String orderDetailNo;
    /**
     * ???¨¦??
     **/
    @Column(name = "capacity"
     ,length = 100
    
    
    
    )
    private String capacity;


   

    public void setInvHeadId(String invHeadId) {
        this.invHeadId = invHeadId;
    }

    public String getInvHeadId() {
        return invHeadId;
    }
    public void setStorageQty(String storageQty) {
        this.storageQty = storageQty;
    }

    public String getStorageQty() {
        return storageQty;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesId() {
        return seriesId;
    }
    public void setOrderHeadId(String orderHeadId) {
        this.orderHeadId = orderHeadId;
    }

    public String getOrderHeadId() {
        return orderHeadId;
    }
    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProId() {
        return proId;
    }
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }
    public void setOrderLineId(String orderLineId) {
        this.orderLineId = orderLineId;
    }

    public String getOrderLineId() {
        return orderLineId;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesName() {
        return seriesName;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }
    public void setStorageUser(String storageUser) {
        this.storageUser = storageUser;
    }

    public String getStorageUser() {
        return storageUser;
    }
    public void setInvStorageId(String invStorageId) {
        this.invStorageId = invStorageId;
    }

    public String getInvStorageId() {
        return invStorageId;
    }
    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    public Date getStorageDate() {
        return storageDate;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProCode() {
        return proCode;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }
    public void setParentNamePath(String parentNamePath) {
        this.parentNamePath = parentNamePath;
    }

    public String getParentNamePath() {
        return parentNamePath;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setStoragePrice(String storagePrice) {
        this.storagePrice = storagePrice;
    }

    public String getStoragePrice() {
        return storagePrice;
    }
    public void setOrderDetailNo(String orderDetailNo) {
        this.orderDetailNo = orderDetailNo;
    }

    public String getOrderDetailNo() {
        return orderDetailNo;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }


}
