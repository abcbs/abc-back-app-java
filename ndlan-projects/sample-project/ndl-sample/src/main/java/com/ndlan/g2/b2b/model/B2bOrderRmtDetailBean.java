package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bOrderRmtDetailBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    private String categoryName;
    /**
     * ??¡ã¨¦???¢ã????????¢ã¡®
     **/
    private String quantity;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    private String proColorNo;
    /**
     * ?¡è???¡§
     **/
    private String remake;
    /**
     * ??????
     **/
    private String volume;
    private String parentNamePath;
    /**
     * ?????¡¤ID
     **/
    private String customerId;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    private String proDesc;
    /**
     * ??¡±?¡±¡§??????
     **/
    private String applDesc;
    private String restName;
    /**
     * ?????¡¤????¡ì¡ã
     **/
    private String customerName;
    /**
     * ¨¨??¨¨?¡ì??¨C???
     **/
    private String orderRmtDetailCode;
    /**
     * ???¨¨?????¨¨??ID
     **/
    private String orderDetailId;
    /**
     * ???¨¨???????¨C??¡¤
     **/
    private String orderDetailNo;
    /**
     * ????¡ë??¢ã????????¢ã¡®
     **/
    private String pic;
    /**
     * ¨¨??¨¨?¡ìID
     **/
    private String orderRmtDetailId;
    /**
     * ??¡ì?¡°?ID
     **/
    private String baseProId;
    /**
     * ??¡ì?¡°???¨C???
     **/
    private String proCode;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    private String supplierName;
    /**
     * ??¡ì?¡°???¨C???
     **/
    private String baseProNo;
    /**
     * ?????¡±???ID
     **/
    private String supplierId;
    /**
     * ¨¨¡ì????ID
     **/
    private String specsId;
    /**
     * ????¡°??????¡ª
     **/
    private String seriesName;
    private String parentIdPath;
    /**
     * ???¨¨???????¡­??¨C??¡¤
     **/
    private String orderPkgCode;
    /**
     * ???¨¨?????????¢ã????0??¡­???????¢ã?1¨¦?¡§????????????2???????????????3??¡­??¡®¨¨?¡ì?¢ã?4?¡¤???¡®¨¨?¡ì?¢ã?5?¡¤??¡±?¨¨?¡ì?¢ã?6??¨C?????¡ë
     **/
    private String orignStatus;
    /**
     * ???¨¦??
     **/
    private String capacity;
    /**
     * ??¡¤???
     **/
    private String price;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    private String proName;
    private String restId;
    /**
     * ¨¨??¨¨?¡ì??¡­ID
     **/
    private String orderRmtPkgId;
    /**
     * ¨¨??¨¨?¡ì?¡è?ID
     **/
    private String orderRmtHeadId;
    /**
     * ????¡°??????¡ªID
     **/
    private String seriesId;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    private String size;
    /**
     * ?????????¨¨?¡ã
     **/
    private String damageSpec;
    /**
     * ???¨¨???????¡­ID
     **/
    private String orderPkgId;
    /**
     * ??????
     **/
    private String barCode;
    /**
     * ¨¨¡ì????
     **/
    private String specsName;
    /**
     * ???¨¨??????¡è?Id
     **/
    private String orderHeadId;
    /**
     * ?¡­???¡°????¢ã?(0????¡±?¨¨?¡ì???1??¡­?¡­???¡°???2?¡¤??¡­???¡°)
     **/
    private String storageStatus;
    /**
     * ??¡ì?¡°?????¡À?
     **/
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
