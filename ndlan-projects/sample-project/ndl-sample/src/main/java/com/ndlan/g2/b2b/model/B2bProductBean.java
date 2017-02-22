package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bProductBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ¨¨¡ì?????¡ã????
     **/
    private String size;
    /**
     * 0:???¨¦¡±¢ã????¢ã?1????????¡±???
     **/
    private String supplierType;
    /**
     * ??¡ì?¡°???¨C???
     **/
    private String baseProNo;
    /**
     * ??????
     **/
    private String volume;
    /**
     * ????¡°???????¨¨?¡§???¨¦¡±?id
     **/
    private String proId;
    /**
     * ???¨¦?¡è????¡ë??¡ª??¡±¡§??¡ã????¡­???¨C?¡ª??¡±¡§
     **/
    private String picAll;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    private String name;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    private String spuulierName;
    /**
     * ?????¡±?????¨C???
     **/
    private String supplierId;
    /**
     * ¨¨??¨¨?¡ì??¡¤
     **/
    private String primeCost;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    private String proDesc;
    /**
     * ??¡±?¡±¡§??????
     **/
    private String applDesc;
    private String goodsAttr3;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    private String categoryId;
    private String parentIdPath;
    /**
     * ????¡ë?
     **/
    private String pic;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    private String categoryName;
    /**
     * ¨¦???¡±???¡¤???
     **/
    private String price;
    /**
     * ??¡ã¨¦??
     **/
    private String quantity;
    private String goodsAttr2;
    /**
     * ???¨¦??
     **/
    private String capacity;
    private String goodsAttr4;
    private String parentNamePath;
    /**
     * 0¨¨?¡ë?¡§??¢ã?2??????????¢ã?4????????¡¯????¢ã?6??????¨¦¢ã?¨¨???¢ã?8??¡®????¢ã?9????¡±¡§
     **/
    private String baseStatus;
    /**
     * ??¡ì?¡°?ID
     **/
    private String baseProId;
    /**
     * ????¡°??¡À??¢ã¡ì1
     **/
    private String goodsAttr1;
    /**
     * ??????
     **/
    private String barCode;
    /**
     * ¨¨¡ì????ID
     **/
    private String specsId;
    /**
     * ?¡è???¡§
     **/
    private String remarks;
    private String restId;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    private String targetClient;
    private String goodsAttr5;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    private String proColorNo;
    /**
     * ¨¨¡ì????
     **/
    private String specsName;


  
	
    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

	

	
    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierType() {
        return supplierType;
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

	
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

	
    public void setPrimeCost(String primeCost) {
        this.primeCost = primeCost;
    }

    public String getPrimeCost() {
        return primeCost;
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

	
    public void setGoodsAttr3(String goodsAttr3) {
        this.goodsAttr3 = goodsAttr3;
    }

    public String getGoodsAttr3() {
        return goodsAttr3;
    }

	
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

	
    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }

	
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

	

	
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

	
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

	
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

	
    public void setGoodsAttr2(String goodsAttr2) {
        this.goodsAttr2 = goodsAttr2;
    }

    public String getGoodsAttr2() {
        return goodsAttr2;
    }

	
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

	
    public void setGoodsAttr4(String goodsAttr4) {
        this.goodsAttr4 = goodsAttr4;
    }

    public String getGoodsAttr4() {
        return goodsAttr4;
    }

	
    public void setParentNamePath(String parentNamePath) {
        this.parentNamePath = parentNamePath;
    }

    public String getParentNamePath() {
        return parentNamePath;
    }

	
    public void setBaseStatus(String baseStatus) {
        this.baseStatus = baseStatus;
    }

    public String getBaseStatus() {
        return baseStatus;
    }

	
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }

	
    public void setGoodsAttr1(String goodsAttr1) {
        this.goodsAttr1 = goodsAttr1;
    }

    public String getGoodsAttr1() {
        return goodsAttr1;
    }

	
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

	
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }

	

	
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }

	
    public void setGoodsAttr5(String goodsAttr5) {
        this.goodsAttr5 = goodsAttr5;
    }

    public String getGoodsAttr5() {
        return goodsAttr5;
    }

	
    public void setProColorNo(String proColorNo) {
        this.proColorNo = proColorNo;
    }

    public String getProColorNo() {
        return proColorNo;
    }

	

	

	
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
    }



}
