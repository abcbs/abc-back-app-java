package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bInventoryHeadBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ????¡°??????¡ªID
     **/
    private String seriesId;
    /**
     * ??????
     **/
    private String barCode;
    /**
     * ????¡°??????¡ª
     **/
    private String seriesName;
    /**
     * ??¡ì?¡°???¨C???
     **/
    private String baseProNo;
    /**
     * ??????
     **/
    private String volume;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    private String categoryName;
    /**
     * ???¨¦?¡­??¡ã¨¦??
     **/
    private String realQty;
    /**
     * ???¨¦¡±?
     **/
    private String invHeadId;
    /**
     * 0:???¨¦¡±¢ã????¢ã?1????????¡±???
     **/
    private String supplierType;
    /**
     * ????¡±?¨¦??
     **/
    private String availableQty;
    /**
     * ?????¡±?????¨C????¢ã????¨¦¡±¢ã????¢ã¡®
     **/
    private String supplierId;
    /**
     * ??¡±?¡±¡§??????
     **/
    private String applDesc;
    /**
     * ???¨¦??
     **/
    private String capacity;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    private String size;
    /**
     * ??¡ì?¡°?ID
     **/
    private String baseProId;
    /**
     * ?????¡±???????¡ì¡ã?¢ã????¨¦¡±¢ã????¢ã¡®
     **/
    private String supplierName;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    private String proColorNo;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    private String categoryId;
    private String capacityVolume;
    /**
     * ?¢ã???¡ã¨¦??
     **/
    private String totalQty;
    private String specsId;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    private String proDesc;
    /**
     * ??¡ë?¡­¡§??¡°???
     **/
    private String safetyStock;
    /**
     * ?????¡°¨¦??
     **/
    private String deliveryQty;
    private String restId;
    /**
     * ¨¨¡ì????
     **/
    private String specsName;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    private String proName;


  
	

	
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesId() {
        return seriesId;
    }

	
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

	
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesName() {
        return seriesName;
    }

	
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }

	
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }

	
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

	
    public void setRealQty(String realQty) {
        this.realQty = realQty;
    }

    public String getRealQty() {
        return realQty;
    }

	

	
    public void setInvHeadId(String invHeadId) {
        this.invHeadId = invHeadId;
    }

    public String getInvHeadId() {
        return invHeadId;
    }

	
    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierType() {
        return supplierType;
    }

	
    public void setAvailableQty(String availableQty) {
        this.availableQty = availableQty;
    }

    public String getAvailableQty() {
        return availableQty;
    }

	
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

	
    public void setApplDesc(String applDesc) {
        this.applDesc = applDesc;
    }

    public String getApplDesc() {
        return applDesc;
    }

	
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

	
    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

	

	
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }

	
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

	
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }

	
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

	
    public void setCapacityVolume(String capacityVolume) {
        this.capacityVolume = capacityVolume;
    }

    public String getCapacityVolume() {
        return capacityVolume;
    }

	
    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    public String getTotalQty() {
        return totalQty;
    }

	
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }

	
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }

	

	
    public void setSafetyStock(String safetyStock) {
        this.safetyStock = safetyStock;
    }

    public String getSafetyStock() {
        return safetyStock;
    }

	
    public void setDeliveryQty(String deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getDeliveryQty() {
        return deliveryQty;
    }

	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }

	

	
    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }



}
