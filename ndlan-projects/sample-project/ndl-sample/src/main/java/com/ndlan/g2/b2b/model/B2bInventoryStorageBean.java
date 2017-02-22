package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bInventoryStorageBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ??¡°????¡è?¨¨?¡§
     **/
    private String invHeadId;
    /**
     * ?¡­???¡ã¨¦??
     **/
    private String storageQty;
    /**
     * ?????¡±???ID
     **/
    private String supplierId;
    private String seriesId;
    /**
     * ¨¨??????¡è?ID
     **/
    private String orderHeadId;
    private String proId;
    /**
     * ¨¨???????¡­ID
     **/
    private String orderPkgId;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    private String size;
    private String parentIdPath;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    private String categoryName;
    /**
     * ¨¨¡ì????
     **/
    private String specsName;
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    private String orderPkgCode;
    /**
     * ??????
     **/
    private String volume;
    private String specsId;
    private String orderLineId;
    /**
     * ????¡ë?
     **/
    private String pic;
    /**
     * ????¡°??????¡ª
     **/
    private String seriesName;
    /**
     * ?¡è???¡§
     **/
    private String remark;
    /**
     * ??¡ì?¡°?ID
     **/
    private String baseProId;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    private String proDesc;
    /**
     * ?¡­???¡°???
     **/
    private String storageUser;
    /**
     * ???¨¦¡±?
     **/
    private String invStorageId;
    /**
     * ?¡­???¡°?¡ª????
     **/
    private Date storageDate;
    /**
     * ??????
     **/
    private String barCode;
    /**
     * ??¡ì?¡°???¨C???
     **/
    private String proCode;
    /**
     * ?¡­???¡°???????¢ã?¨¨?????|¨¨¡ã????|¨¨??¨¨?¡ì?¢ã¡®
     **/
    private String source;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    private String proColorNo;
    /**
     * ??¡ì?¡°???¨C???
     **/
    private String baseProNo;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    private String proName;
    private String parentNamePath;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    private String categoryId;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    private String supplierName;
    /**
     * ?¡­???¡°??¡¤???
     **/
    private String storagePrice;
    /**
     * ????????¨C??¡¤
     **/
    private String orderDetailNo;
    /**
     * ???¨¦??
     **/
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
