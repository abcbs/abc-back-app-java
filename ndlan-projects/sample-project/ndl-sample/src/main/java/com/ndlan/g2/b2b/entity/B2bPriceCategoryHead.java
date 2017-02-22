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
@Table(name = "b2b_price_category_head" )
public class B2bPriceCategoryHead  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ??????
     **/
    @Column(name = "bar_code"
     ,length = 36
    
    
    
    )
    private String barCode;
    /**
     * ????°Î?
     **/
    @Column(name = "pic"
     ,length = 100
    
    
    
    )
    private String pic;
    /**
     * ????°∞?ID
     **/
    @Column(name = "pro_id"
     ,length = 36
    
    
    
    )
    private String proId;
    /**
     * ??°Ï?°∞?????°¿?????°Ï°„
     **/
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    /**
     * ???®¶°±¢„???ID
     **/
    @Column(name = "agency_id"
     ,length = 36
    
    
    
    )
    private String agencyId;
    /**
     * ??°Ï?°∞?????°Ï°„
     **/
    @Column(name = "name"
     ,length = 255
    
    
    
    )
    private String name;
    /**
     * ?????°§??????®¶??®®?°ËCUST|BUSI??°Î
     **/
    @Column(name = "target_client"
     ,length = 20
    
    
    
    )
    private String targetClient;
    /**
     * ??°Ï?°∞???®C???
     **/
    @Column(name = "base_pro_no"
     ,length = 50
    
    
    
    )
    private String baseProNo;
    /**
     * ??°Ï?°∞????®®?°„
     **/
    @Column(name = "pro_desc"
     ,length = 255
    
    
    
    )
    private String proDesc;
    /**
     * ???®¶??
     **/
    @Column(name = "capacity"
     ,length = 100
    
    
    
    )
    private String capacity;
    
    
    
    
    /**
     * ®®°Ï????
     **/
    @Column(name = "specs_name"
     ,length = 255
    
    
    
    )
    private String specsName;
    /**
     * ?????°±???????°Ï°„
     **/
    @Column(name = "spuulier_name"
     ,length = 255
    
    
    
    )
    private String spuulierName;
    
    
    
    
    /**
     * ???®¶°±¢„???????°Ï°„
     **/
    @Column(name = "agency_name"
     ,length = 255
    
    
    
    )
    private String agencyName;
    @Column(name = "parent_id_path"
     ,length = 255
    
    
    
    )
    private String parentIdPath;
    /**
     * ??°Ï?°∞?®®°Î???°§
     **/
    @Column(name = "pro_color_no"
     ,length = 36
    
    
    
    )
    private String proColorNo;
    @Column(name = "parent_name_path"
     ,length = 255
    
    
    
    )
    private String parentNamePath;
    /**
     * ?°Ë???°ß
     **/
    @Column(name = "remarks"
     ,length = 255
    
    
    
    )
    private String remarks;
    /**
     * ?????°±?????®C???
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ??°§??????????°Ë?ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "pri_cat_head_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String priCatHeadId;
    /**
     * ®®°Ï????ID
     **/
    @Column(name = "specs_id"
     ,length = 36
    
    
    
    )
    private String specsId;
    /**
     * ??°Ï?°∞?ID
     **/
    @Column(name = "base_pro_id"
     ,length = 36
    
    
    
    )
    private String baseProId;
    /**
     * ??°Ï?°∞?????°¿?
     **/
    @Column(name = "category_id"
     ,length = 36
    
    
    
    )
    private String categoryId;
    
    
    
    
    
    
    
    
    /**
     * ??°±?°±°ß??????
     **/
    @Column(name = "appl_desc"
     ,length = 255
    
    
    
    )
    private String applDesc;
    /**
     * ??°§???
     **/
    @Column(name = "price"
     ,length = 100
    
    
    
    )
    private String price;
    
    
    
    
    /**
     * ??????
     **/
    @Column(name = "volume"
     ,length = 10
    
    
    
    )
    private String volume;
    /**
     * ®®°Ï?????°„????
     **/
    @Column(name = "size"
     ,length = 36
    
    
    
    )
    private String size;


   

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProId() {
        return proId;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyId() {
        return agencyId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }
    public void setSpuulierName(String spuulierName) {
        this.spuulierName = spuulierName;
    }

    public String getSpuulierName() {
        return spuulierName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyName() {
        return agencyName;
    }
    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }
    public void setParentNamePath(String parentNamePath) {
        this.parentNamePath = parentNamePath;
    }

    public String getParentNamePath() {
        return parentNamePath;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setPriCatHeadId(String priCatHeadId) {
        this.priCatHeadId = priCatHeadId;
    }

    public String getPriCatHeadId() {
        return priCatHeadId;
    }
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setApplDesc(String applDesc) {
        this.applDesc = applDesc;
    }

    public String getApplDesc() {
        return applDesc;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }


}
