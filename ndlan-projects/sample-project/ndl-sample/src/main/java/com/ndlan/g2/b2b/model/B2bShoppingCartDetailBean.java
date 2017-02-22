package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bShoppingCartDetailBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    private String proDesc;
    /**
     * ??????
     **/
    private String volume;
    /**
     * ????¡ë?
     **/
    private String discount;
    /**
     * ??¡¤???
     **/
    private String price;
    /**
     * ?¡ã?¨¨??????¡±???¡¤-????¡­???¡ë*??¡ã¨¦??
     **/
    private String subtotal;
    /**
     * ??????
     **/
    private String barCode;
    /**
     * ??????
     **/
    private String privilege;
    /**
     * ??¡ª¨¦¡°?????¡ì¡ã
     **/
    private String restName;
    /**
     * ¨¨¡ì????????¡ì¡ã
     **/
    private String specsName;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    private String categoryName;
    /**
     * ?????¡±???ID
     **/
    private String supplierId;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    private String size;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    private String supplierName;
    /**
     * ¨¨¡ì????ID
     **/
    private String specsId;
    /**
     * ¨¨???¡ë?¨¨????¡­ID
     **/
    private String cartPkgId;
    /**
     * ??¡¤?????????¨¨?¡¤??????
     **/
    private String startPointQyt;
    /**
     * ????¡À?id
     **/
    private String categoryId;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    private String proColorNo;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    private String slsPmtnId;
    /**
     * ????¡ì¡ã
     **/
    private String name;
    private String restId;
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    private String strategyDesc;
    /**
     * ??¡±?¡±¡§??????
     **/
    private String applDesc;
    /**
     * ??¡°??¡ª????¢ã????0?????¡°¨¨?????1?¡¤???¡°¨¨????¡ë
     **/
    private String payStatus;
    /**
     * ???¨¦??
     **/
    private String capacity;
    /**
     * ????¡ë?
     **/
    private String pic;
    /**
     * ??¡ì?¡°???¨C???
     **/
    private String baseProNo;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    private String targetClient;
    /**
     * ¨¨???¡ë?¨¨?????¨¦¡±?
     **/
    private String cartId;
    /**
     * ????¡°?id
     **/
    private String proId;
    /**
     * ¨¨???¡ë?¨¨???????????¨¦¡±?
     **/
    private String cartItemId;
    /**
     * ¨¨????¡ã??¡ã¨¦??
     **/
    private String quantity;
    /**
     * ??¡ì?¡°?ID
     **/
    private String baseProId;
    /**
     * ??????????¡ª?????¡±???????1??¡ë???0?¡ª??????¡ë
     **/
    private String isCodeless;
    /**
     * ??¡¤????????????¨¦¡±¢ã???????¡ì¡ã
     **/
    private String priceAgencyName;
    /**
     * ??¡¤????????????¨¦¡±¢ã???ID
     **/
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
