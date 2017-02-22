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
@Table(name = "b2b_product_client" )
public class B2bProductClient  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    
    
    
    
    /**
     * ¨¨¡ì?????¡ã????
     **/
    @Column(name = "size"
     ,length = 36
    
    
    
    )
    private String size;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "spuulier_name"
     ,length = 255
    
    
    
    )
    private String spuulierName;
    
    
    
    
    /**
     * ?????¡±?????¨C???
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    @Column(name = "name"
     ,length = 255
    
    
    
    )
    private String name;
    
    
    
    
    /**
     * ???¨¦??
     **/
    @Column(name = "capacity"
     ,length = 11
    
    
    
    )
    private String capacity;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    @Column(name = "pro_desc"
     ,length = 255
    
    
    
    )
    private String proDesc;
    /**
     * 0:???¨¦¡±¢ã????¢ã?1????????¡±???
     **/
    @Column(name = "supplier_type"
     ,length = 4
    
    
    
    )
    private String supplierType;
    /**
     * ??¡±?¡±¡§??????
     **/
    @Column(name = "appl_desc"
     ,length = 255
    
    
    
    )
    private String applDesc;
    @Column(name = "goods_attr4"
     ,length = 36
    
    
    
    )
    private String goodsAttr4;
    /**
     * ????¡ë?
     **/
    @Column(name = "pic"
     ,length = 100
    
    
    
    )
    private String pic;
    /**
     * ??????
     **/
    @Column(name = "volume"
     ,length = 36
    
    
    
    )
    private String volume;
    
    
    
    
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ¨¦???¡±???¡¤???
     **/
    @Column(name = "price"
     ,length = 100
    
    
    
    )
    private String price;
    @Column(name = "parent_id_path"
     ,length = 255
    
    
    
    )
    private String parentIdPath;
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
    @Column(name = "remarks"
     ,length = 255
    
    
    
    )
    private String remarks;
    
    
    
    
    /**
     * ¨¨¡ì????
     **/
    @Column(name = "specs_name"
     ,length = 255
    
    
    
    )
    private String specsName;
    /**
     * ¨¨¡ì????ID
     **/
    @Column(name = "specs_id"
     ,length = 36
    
    
    
    )
    private String specsId;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "base_pro_no"
     ,length = 36
    
    
    
    )
    private String baseProNo;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    @Column(name = "category_id"
     ,length = 36
    
    
    
    )
    private String categoryId;
    @Column(name = "goods_attr3"
     ,length = 36
    
    
    
    )
    private String goodsAttr3;
    @Column(name = "goods_attr2"
     ,length = 36
    
    
    
    )
    private String goodsAttr2;
    /**
     * ??¡ã¨¦??
     **/
    @Column(name = "quantity"
     ,length = 100
    
    
    
    )
    private String quantity;
    /**
     * ??????
     **/
    @Column(name = "bar_code"
     ,length = 36
    
    
    
    )
    private String barCode;
    /**
     * ????¡°??¡À??¢ã¡ì1
     **/
    @Column(name = "goods_attr1"
     ,length = 36
    
    
    
    )
    private String goodsAttr1;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @Column(name = "target_client"
     ,length = 20
    
    
    
    )
    private String targetClient;
    /**
     * ¨¨??¨¨?¡ì??¡¤
     **/
    @Column(name = "prime_cost"
     ,length = 100
    
    
    
    )
    private String primeCost;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    /**
     * 0¨¨?¡ë?¡§??¢ã?2??????????¢ã?4????????¡¯????¢ã?6??????¨¦¢ã?¨¨???¢ã?8??¡®????¢ã?9????¡±¡§
     **/
    @Column(name = "base_status"
     ,length = 20
    
    
    
    )
    private String baseStatus;
    @Column(name = "goods_attr5"
     ,length = 36
    
    
    
    )
    private String goodsAttr5;
    /**
     * ??¡ì?¡°?ID
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
     * ????¡°???????¨¨?¡§???¨¦¡±?id
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "pro_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String proId;
    /**
     * ???¨¦?¡è????¡ë??¡ª??¡±¡§??¡ã????¡­???¨C?¡ª??¡±¡§
     **/
    @Column(name = "pic_all"
     ,length = 100
    
    
    
    )
    private String picAll;


   

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    public void setSpuulierName(String spuulierName) {
        this.spuulierName = spuulierName;
    }

    public String getSpuulierName() {
        return spuulierName;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }
    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierType() {
        return supplierType;
    }
    public void setApplDesc(String applDesc) {
        this.applDesc = applDesc;
    }

    public String getApplDesc() {
        return applDesc;
    }
    public void setGoodsAttr4(String goodsAttr4) {
        this.goodsAttr4 = goodsAttr4;
    }

    public String getGoodsAttr4() {
        return goodsAttr4;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
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
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setGoodsAttr3(String goodsAttr3) {
        this.goodsAttr3 = goodsAttr3;
    }

    public String getGoodsAttr3() {
        return goodsAttr3;
    }
    public void setGoodsAttr2(String goodsAttr2) {
        this.goodsAttr2 = goodsAttr2;
    }

    public String getGoodsAttr2() {
        return goodsAttr2;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }
    public void setGoodsAttr1(String goodsAttr1) {
        this.goodsAttr1 = goodsAttr1;
    }

    public String getGoodsAttr1() {
        return goodsAttr1;
    }
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }
    public void setPrimeCost(String primeCost) {
        this.primeCost = primeCost;
    }

    public String getPrimeCost() {
        return primeCost;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setBaseStatus(String baseStatus) {
        this.baseStatus = baseStatus;
    }

    public String getBaseStatus() {
        return baseStatus;
    }
    public void setGoodsAttr5(String goodsAttr5) {
        this.goodsAttr5 = goodsAttr5;
    }

    public String getGoodsAttr5() {
        return goodsAttr5;
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
    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProId() {
        return proId;
    }
    public void setPicAll(String picAll) {
        this.picAll = picAll;
    }

    public String getPicAll() {
        return picAll;
    }


}
