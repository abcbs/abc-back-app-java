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
@Table(name = "b2b_shopping_cart_detail" )
public class B2bShoppingCartDetail  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    @Column(name = "pro_desc"
     ,length = 255
    
    
    
    )
    private String proDesc;
    /**
     * ??????
     **/
    @Column(name = "volume"
     ,length = 10
    
    
    
    )
    private String volume;
    /**
     * ????¡ë?
     **/
    @Column(name = "discount"
     ,length = 100
    
    
    
    )
    private String discount;
    /**
     * ??¡¤???
     **/
    @Column(name = "price"
     ,length = 100
    
    
    
    )
    private String price;
    /**
     * ?¡ã?¨¨??????¡±???¡¤-????¡­???¡ë*??¡ã¨¦??
     **/
    @Column(name = "subtotal"
     ,length = 100
    
    
    
    )
    private String subtotal;
    /**
     * ??????
     **/
    @Column(name = "bar_code"
     ,length = 36
    
    
    
    )
    private String barCode;
    /**
     * ??????
     **/
    @Column(name = "privilege"
     ,length = 100
    
    
    
    )
    private String privilege;
    /**
     * ??¡ª¨¦¡°?????¡ì¡ã
     **/
    @Column(name = "rest_name"
     ,length = 255
    
    
    
    )
    private String restName;
    /**
     * ¨¨¡ì????????¡ì¡ã
     **/
    @Column(name = "specs_name"
     ,length = 255
    
    
    
    )
    private String specsName;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    /**
     * ?????¡±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
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
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ¨¨¡ì????ID
     **/
    @Column(name = "specs_id"
     ,length = 36
    
    
    
    )
    private String specsId;
    /**
     * ¨¨???¡ë?¨¨????¡­ID
     **/
    @Column(name = "cart_pkg_id"
     ,length = 36
    
    
    
    )
    private String cartPkgId;
    /**
     * ??¡¤?????????¨¨?¡¤??????
     **/
    @Column(name = "start_point_qyt"
     ,length = 100
    
    
    
    )
    private String startPointQyt;
    /**
     * ????¡À?id
     **/
    @Column(name = "category_id"
     ,length = 36
    
    
    
    )
    private String categoryId;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    @Column(name = "pro_color_no"
     ,length = 36
    
    
    
    )
    private String proColorNo;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    @Column(name = "sls_pmtn_id"
     ,length = 2
    
    
    
    )
    private String slsPmtnId;
    
    
    
    
    /**
     * ????¡ì¡ã
     **/
    @Column(name = "name"
     ,length = 36
    
    
    
    )
    private String name;
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    @Column(name = "strategy_desc"
     ,length = 2
    
    
    
    )
    private String strategyDesc;
    
    
    
    
    /**
     * ??¡±?¡±¡§??????
     **/
    @Column(name = "appl_desc"
     ,length = 255
    
    
    
    )
    private String applDesc;
    /**
     * ??¡°??¡ª????¢ã????0?????¡°¨¨?????1?¡¤???¡°¨¨????¡ë
     **/
    @Column(name = "pay_status"
     ,length = 2
    
    
    
    )
    private String payStatus;
    /**
     * ???¨¦??
     **/
    @Column(name = "capacity"
     ,length = 100
    
    
    
    )
    private String capacity;
    /**
     * ????¡ë?
     **/
    @Column(name = "pic"
     ,length = 255
    
    
    
    )
    private String pic;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @Column(name = "base_pro_no"
     ,length = 50
    
    
    
    )
    private String baseProNo;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @Column(name = "target_client"
     ,length = 20
    
    
    
    )
    private String targetClient;
    /**
     * ¨¨???¡ë?¨¨?????¨¦¡±?
     **/
    @Column(name = "cart_id"
     ,length = 36
     ,nullable = false
    
    
    )
    private String cartId;
    /**
     * ????¡°?id
     **/
    @Column(name = "pro_id"
     ,length = 36
    
    
    
    )
    private String proId;
    /**
     * ¨¨???¡ë?¨¨???????????¨¦¡±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cart_item_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cartItemId;
    /**
     * ¨¨????¡ã??¡ã¨¦??
     **/
    @Column(name = "quantity"
     ,length = 100
    
    
    
    )
    private String quantity;
    /**
     * ??¡ì?¡°?ID
     **/
    @Column(name = "base_pro_id"
     ,length = 36
    
    
    
    )
    private String baseProId;
    /**
     * ??????????¡ª?????¡±???????1??¡ë???0?¡ª??????¡ë
     **/
    @Column(name = "is_codeless"
     ,length = 2
    
    
    
    )
    private String isCodeless;
    
    
    
    
    
    
    
    
    /**
     * ??¡¤????????????¨¦¡±¢ã???????¡ì¡ã
     **/
    @Column(name = "price_agency_name"
     ,length = 255
    
    
    
    )
    private String priceAgencyName;
    /**
     * ??¡¤????????????¨¦¡±¢ã???ID
     **/
    @Column(name = "price_agency_id"
     ,length = 36
    
    
    
    )
    private String priceAgencyId;


   

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getSubtotal() {
        return subtotal;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getPrivilege() {
        return privilege;
    }
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }
    public void setCartPkgId(String cartPkgId) {
        this.cartPkgId = cartPkgId;
    }

    public String getCartPkgId() {
        return cartPkgId;
    }
    public void setStartPointQyt(String startPointQyt) {
        this.startPointQyt = startPointQyt;
    }

    public String getStartPointQyt() {
        return startPointQyt;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }
    public void setSlsPmtnId(String slsPmtnId) {
        this.slsPmtnId = slsPmtnId;
    }

    public String getSlsPmtnId() {
        return slsPmtnId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }
    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }
    public void setApplDesc(String applDesc) {
        this.applDesc = applDesc;
    }

    public String getApplDesc() {
        return applDesc;
    }
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProId() {
        return proId;
    }
    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getCartItemId() {
        return cartItemId;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }
    public void setIsCodeless(String isCodeless) {
        this.isCodeless = isCodeless;
    }

    public String getIsCodeless() {
        return isCodeless;
    }
    public void setPriceAgencyName(String priceAgencyName) {
        this.priceAgencyName = priceAgencyName;
    }

    public String getPriceAgencyName() {
        return priceAgencyName;
    }
    public void setPriceAgencyId(String priceAgencyId) {
        this.priceAgencyId = priceAgencyId;
    }

    public String getPriceAgencyId() {
        return priceAgencyId;
    }


}
