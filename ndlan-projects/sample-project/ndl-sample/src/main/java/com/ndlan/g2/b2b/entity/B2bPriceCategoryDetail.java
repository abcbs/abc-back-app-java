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
@Table(name = "b2b_price_category_detail" )
public class B2bPriceCategoryDetail  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ¨¨?¡¤??????
     **/
    @Column(name = "start_point_qyt"
     ,length = 100
    
    
    
    )
    private String startPointQyt;
    /**
     * ??¡¤?????????ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "pri_cat_line_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String priCatLineId;
    /**
     * ?¡±?????¡ª????
     **/
    @Column(name = "effective_date"
    
    
    
    
    )
    private Date effectiveDate;
    /**
     * ??¡ì?¡°?id
     **/
    @Column(name = "base_pro_id"
     ,length = 36
    
    
    
    )
    private String baseProId;
    @Column(name = "parent_name_path"
     ,length = 255
    
    
    
    )
    private String parentNamePath;
    /**
     * ??¡¤??????????¡è?ID
     **/
    @Column(name = "pri_cat_head_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String priCatHeadId;
    /**
     * ???¨¦¡±¢ã???id
     **/
    @Column(name = "agency_id"
     ,length = 36
    
    
    
    )
    private String agencyId;
    /**
     * ?¡±?????¡ª????
     **/
    @Column(name = "expiry_date"
    
    
    
    
    )
    private Date expiryDate;
    /**
     * ??¡ì?¡°??¡À????id
     **/
    @Column(name = "specs_id"
     ,length = 36
    
    
    
    )
    private String specsId;
    @Column(name = "parent_id_path"
     ,length = 255
    
    
    
    )
    private String parentIdPath;
    /**
     * ??¡ì?¡°?????¡À?id
     **/
    @Column(name = "category_id"
     ,length = 36
    
    
    
    )
    private String categoryId;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    @Column(name = "name"
     ,length = 255
    
    
    
    )
    private String name;
    /**
     * ???¨¦¡±¢ã??¡¤
     **/
    @Column(name = "promotion_price"
     ,length = 100
    
    
    
    )
    private String promotionPrice;
    /**
     * ¨¨¡ë???¡¤
     **/
    @Column(name = "pro_color_no"
     ,length = 36
    
    
    
    )
    private String proColorNo;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    /**
     * ????¡°?id
     **/
    @Column(name = "pro_id"
     ,length = 36
    
    
    
    )
    private String proId;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "base_pro_no"
     ,length = 50
    
    
    
    )
    private String baseProNo;
    
    
    
    
    /**
     * ??¡ì?¡°??¡À????????¡ì¡ã
     **/
    @Column(name = "specs_name"
     ,length = 255
    
    
    
    )
    private String specsName;
    /**
     * ?????¡¤
     **/
    @Column(name = "price"
     ,length = 100
    
    
    
    )
    private String price;
    
    
    
    
    
    
    
    
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "spuulier_name"
     ,length = 255
    
    
    
    )
    private String spuulierName;
    /**
     * ???¨¦¡±¢ã???????¡ì¡ã
     **/
    @Column(name = "agency_name"
     ,length = 255
    
    
    
    )
    private String agencyName;
    /**
     * ??????
     **/
    @Column(name = "volume"
     ,length = 10
    
    
    
    )
    private String volume;
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remarks"
     ,length = 255
    
    
    
    )
    private String remarks;
    /**
     * ?????¡±???id
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    
    
    
    
    
    
    
    


   

    public void setStartPointQyt(String startPointQyt) {
        this.startPointQyt = startPointQyt;
    }

    public String getStartPointQyt() {
        return startPointQyt;
    }
    public void setPriCatLineId(String priCatLineId) {
        this.priCatLineId = priCatLineId;
    }

    public String getPriCatLineId() {
        return priCatLineId;
    }
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }
    public void setParentNamePath(String parentNamePath) {
        this.parentNamePath = parentNamePath;
    }

    public String getParentNamePath() {
        return parentNamePath;
    }
    public void setPriCatHeadId(String priCatHeadId) {
        this.priCatHeadId = priCatHeadId;
    }

    public String getPriCatHeadId() {
        return priCatHeadId;
    }
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyId() {
        return agencyId;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }
    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProId() {
        return proId;
    }
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
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
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
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


}
