package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bOrderItemBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ??¡¤???
     **/
    private String price;
    /**
     * ¨¨??????¡è?ID
     **/
    private String bId;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    private String proDesc;
    /**
     * ¨¨??¨¨?????¨¨??¨¨?????¨¨??¨¨?¡ì¨¨??id
     **/
    private String rmtDetailId;
    /**
     * ??¡ã¨¦??
     **/
    private String quantity;
    /**
     * ??????
     **/
    private String volume;
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    private String orderPkgCode;
    /**
     * ????????¡ã¨¦??
     **/
    private String numberOfBroken;
    /**
     * ?????¡±???ID
     **/
    private String supplierId;
    /**
     * ??¡¤????????????¨¦¡±¢ã???????¡ì¡ã
     **/
    private String priceAgencyName;
    /**
     * ??????
     **/
    private String privilege;
    /**
     * ????¡ë?
     **/
    private String pic;
    /**
     * ???????¡ª?????¡±???????0??????1?????¡ë
     **/
    private String isCodeless;
    /**
     * ¨¨??¨¨?????¨¨??¨¨?????¨¨??¨¨?¡ì??¡­id
     **/
    private String rmtPkgId;
    /**
     * ????????¨C??¡¤
     **/
    private String bdNo;
    /**
     * ??¡¤?????????¨¨?¡¤??????
     **/
    private String startPointQyt;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    private String amountPaid;
    /**
     * ???¨¦??
     **/
    private String capacity;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    private String proColorNo;
    private String restName;
    /**
     * ????¡ë?
     **/
    private String discount;
    /**
     * ?????????¨¨??¨¨?¡ì??????0??????1?????¡ë
     **/
    private String rmtStatus;
    /**
     * ??¡ì?¡°?ID
     **/
    private String baseProId;
    private String parentNamePath;
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    private String strategyDesc;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    private String size;
    private String parentIdPath;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    private String supplierName;
    /**
     * ??¡±?¡±¡§??????
     **/
    private String applDesc;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    private String slsPmtnId;
    /**
     * ??¡¤????????????¨¦¡±¢ã???ID
     **/
    private String priceAgencyId;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    private String subtotal;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    private String proName;
    /**
     * ¨¨¡ì????ID
     **/
    private String specsId;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    private String targetClient;
    /**
     * ¨¨???????¡­ID
     **/
    private String orderPkgId;
    /**
     * ????¡°???????Id
     **/
    private String proId;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    private String categoryName;
    /**
     * ¨¨???¡ë?¨¨????????id
     **/
    private String cartItemId;
    /**
     * ??????¨¨????????¨¨?????¨¨??id
     **/
    private String damageBdId;
    /**
     * ¨¨¡ì????
     **/
    private String specsName;
    /**
     * ??¡ì?¡°???¨C???
     **/
    private String baseProNo;
    /**
     * ???¨¦¡±?
     **/
    private String bdId;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    private String categoryId;
    private String restId;
    /**
     * ?¡­???¡°????¢ã?(0????¡±?¨¨?¡ì???1??¡­?¡­???¡°???2?¡¤??¡­???¡°)
     **/
    private String storageStatus;
    /**
     * ??¡¤?????????
     **/
    private String priCatLineId;
    /**
     * ??????
     **/
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
