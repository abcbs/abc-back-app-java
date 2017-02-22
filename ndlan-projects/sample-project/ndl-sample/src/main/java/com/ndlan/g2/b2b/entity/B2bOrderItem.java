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
@Table(name = "b2b_order_item" )
public class B2bOrderItem  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ??¡¤???
     **/
    @Column(name = "price"
     ,length = 100
    
    
    
    )
    private String price;
    /**
     * ¨¨??????¡è?ID
     **/
    @Column(name = "b_id"
     ,length = 36
    
    
    
    )
    private String bId;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    @Column(name = "pro_desc"
     ,length = 255
    
    
    
    )
    private String proDesc;
    /**
     * ¨¨??¨¨?????¨¨??¨¨?????¨¨??¨¨?¡ì¨¨??id
     **/
    @Column(name = "rmt_detail_id"
     ,length = 36
    
    
    
    )
    private String rmtDetailId;
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
    @Column(name = "volume"
     ,length = 10
    
    
    
    )
    private String volume;
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    @Column(name = "order_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderPkgCode;
    /**
     * ????????¡ã¨¦??
     **/
    @Column(name = "number_of_broken"
     ,length = 60
    
    
    
    )
    private String numberOfBroken;
    /**
     * ?????¡±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    /**
     * ??¡¤????????????¨¦¡±¢ã???????¡ì¡ã
     **/
    @Column(name = "price_agency_name"
     ,length = 255
    
    
    
    )
    private String priceAgencyName;
    
    
    
    
    /**
     * ??????
     **/
    @Column(name = "privilege"
     ,length = 100
    
    
    
    )
    private String privilege;
    /**
     * ????¡ë?
     **/
    @Column(name = "pic"
     ,length = 100
    
    
    
    )
    private String pic;
    /**
     * ???????¡ª?????¡±???????0??????1?????¡ë
     **/
    @Column(name = "is_codeless"
     ,length = 2
    
    
    
    )
    private String isCodeless;
    
    
    
    
    /**
     * ¨¨??¨¨?????¨¨??¨¨?????¨¨??¨¨?¡ì??¡­id
     **/
    @Column(name = "rmt_pkg_id"
     ,length = 36
    
    
    
    )
    private String rmtPkgId;
    /**
     * ????????¨C??¡¤
     **/
    @Column(name = "bd_no"
     ,length = 36
    
    
    
    )
    private String bdNo;
    /**
     * ??¡¤?????????¨¨?¡¤??????
     **/
    @Column(name = "start_point_qyt"
     ,length = 60
    
    
    
    )
    private String startPointQyt;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    @Column(name = "amount_paid"
     ,length = 100
    
    
    
    )
    private String amountPaid;
    /**
     * ???¨¦??
     **/
    @Column(name = "capacity"
     ,length = 100
    
    
    
    )
    private String capacity;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    @Column(name = "pro_color_no"
     ,length = 36
    
    
    
    )
    private String proColorNo;
    @Column(name = "rest_name"
     ,length = 255
    
    
    
    )
    private String restName;
    /**
     * ????¡ë?
     **/
    @Column(name = "discount"
     ,length = 100
    
    
    
    )
    private String discount;
    /**
     * ?????????¨¨??¨¨?¡ì??????0??????1?????¡ë
     **/
    @Column(name = "rmt_status"
     ,length = 60
    
    
    
    )
    private String rmtStatus;
    /**
     * ??¡ì?¡°?ID
     **/
    @Column(name = "base_pro_id"
     ,length = 36
    
    
    
    )
    private String baseProId;
    @Column(name = "parent_name_path"
     ,length = 3,600
    
    
    
    )
    private String parentNamePath;
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    @Column(name = "strategy_desc"
     ,length = 255
    
    
    
    )
    private String strategyDesc;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    @Column(name = "size"
     ,length = 36
    
    
    
    )
    private String size;
    @Column(name = "parent_id_path"
     ,length = 3,600
    
    
    
    )
    private String parentIdPath;
    
    
    
    
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    
    
    
    
    
    
    
    
    /**
     * ??¡±?¡±¡§??????
     **/
    @Column(name = "appl_desc"
     ,length = 255
    
    
    
    )
    private String applDesc;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    @Column(name = "sls_pmtn_id"
     ,length = 20
    
    
    
    )
    private String slsPmtnId;
    /**
     * ??¡¤????????????¨¦¡±¢ã???ID
     **/
    @Column(name = "price_agency_id"
     ,length = 36
    
    
    
    )
    private String priceAgencyId;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    @Column(name = "subtotal"
     ,length = 100
    
    
    
    )
    private String subtotal;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    @Column(name = "pro_name"
     ,length = 255
    
    
    
    )
    private String proName;
    /**
     * ¨¨¡ì????ID
     **/
    @Column(name = "specs_id"
     ,length = 36
    
    
    
    )
    private String specsId;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @Column(name = "target_client"
     ,length = 20
    
    
    
    )
    private String targetClient;
    /**
     * ¨¨???????¡­ID
     **/
    @Column(name = "order_pkg_id"
     ,length = 36
    
    
    
    )
    private String orderPkgId;
    /**
     * ????¡°???????Id
     **/
    @Column(name = "pro_id"
     ,length = 36
    
    
    
    )
    private String proId;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    /**
     * ¨¨???¡ë?¨¨????????id
     **/
    @Column(name = "cart_item_id"
     ,length = 36
    
    
    
    )
    private String cartItemId;
    /**
     * ??????¨¨????????¨¨?????¨¨??id
     **/
    @Column(name = "damage_bd_id"
     ,length = 36
    
    
    
    )
    private String damageBdId;
    /**
     * ¨¨¡ì????
     **/
    @Column(name = "specs_name"
     ,length = 255
    
    
    
    )
    private String specsName;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "base_pro_no"
     ,length = 50
    
    
    
    )
    private String baseProNo;
    /**
     * ???¨¦¡±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "bd_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String bdId;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    @Column(name = "category_id"
     ,length = 36
    
    
    
    )
    private String categoryId;
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ?¡­???¡°????¢ã?(0????¡±?¨¨?¡ì???1??¡­?¡­???¡°???2?¡¤??¡­???¡°)
     **/
    @Column(name = "storage_status"
     ,length = 20
    
    
    
    )
    private String storageStatus;
    /**
     * ??¡¤?????????
     **/
    @Column(name = "pri_cat_line_id"
     ,length = 36
    
    
    
    )
    private String priCatLineId;
    /**
     * ??????
     **/
    @Column(name = "bar_code"
     ,length = 36
    
    
    
    )
    private String barCode;


   

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    public void setBId(String bId) {
        this.bId = bId;
    }

    public String getBId() {
        return bId;
    }
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }
    public void setRmtDetailId(String rmtDetailId) {
        this.rmtDetailId = rmtDetailId;
    }

    public String getRmtDetailId() {
        return rmtDetailId;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }
    public void setNumberOfBroken(String numberOfBroken) {
        this.numberOfBroken = numberOfBroken;
    }

    public String getNumberOfBroken() {
        return numberOfBroken;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setPriceAgencyName(String priceAgencyName) {
        this.priceAgencyName = priceAgencyName;
    }

    public String getPriceAgencyName() {
        return priceAgencyName;
    }
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getPrivilege() {
        return privilege;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setIsCodeless(String isCodeless) {
        this.isCodeless = isCodeless;
    }

    public String getIsCodeless() {
        return isCodeless;
    }
    public void setRmtPkgId(String rmtPkgId) {
        this.rmtPkgId = rmtPkgId;
    }

    public String getRmtPkgId() {
        return rmtPkgId;
    }
    public void setBdNo(String bdNo) {
        this.bdNo = bdNo;
    }

    public String getBdNo() {
        return bdNo;
    }
    public void setStartPointQyt(String startPointQyt) {
        this.startPointQyt = startPointQyt;
    }

    public String getStartPointQyt() {
        return startPointQyt;
    }
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountPaid() {
        return amountPaid;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }
    public void setRmtStatus(String rmtStatus) {
        this.rmtStatus = rmtStatus;
    }

    public String getRmtStatus() {
        return rmtStatus;
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
    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public String getStrategyDesc() {
        return strategyDesc;
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
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setApplDesc(String applDesc) {
        this.applDesc = applDesc;
    }

    public String getApplDesc() {
        return applDesc;
    }
    public void setSlsPmtnId(String slsPmtnId) {
        this.slsPmtnId = slsPmtnId;
    }

    public String getSlsPmtnId() {
        return slsPmtnId;
    }
    public void setPriceAgencyId(String priceAgencyId) {
        this.priceAgencyId = priceAgencyId;
    }

    public String getPriceAgencyId() {
        return priceAgencyId;
    }
    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getSubtotal() {
        return subtotal;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
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
    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getCartItemId() {
        return cartItemId;
    }
    public void setDamageBdId(String damageBdId) {
        this.damageBdId = damageBdId;
    }

    public String getDamageBdId() {
        return damageBdId;
    }
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }
    public void setBdId(String bdId) {
        this.bdId = bdId;
    }

    public String getBdId() {
        return bdId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public String getStorageStatus() {
        return storageStatus;
    }
    public void setPriCatLineId(String priCatLineId) {
        this.priCatLineId = priCatLineId;
    }

    public String getPriCatLineId() {
        return priCatLineId;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }


}
