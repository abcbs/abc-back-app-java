package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bPriceCategoryHeadBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ??????
     **/
    private String barCode;
    /**
     * ????°Î?
     **/
    private String pic;
    /**
     * ????°∞?ID
     **/
    private String proId;
    /**
     * ??°Ï?°∞?????°¿?????°Ï°„
     **/
    private String categoryName;
    /**
     * ???®¶°±¢„???ID
     **/
    private String agencyId;
    /**
     * ??°Ï?°∞?????°Ï°„
     **/
    private String name;
    /**
     * ?????°§??????®¶??®®?°ËCUST|BUSI??°Î
     **/
    private String targetClient;
    /**
     * ??°Ï?°∞???®C???
     **/
    private String baseProNo;
    /**
     * ??°Ï?°∞????®®?°„
     **/
    private String proDesc;
    /**
     * ???®¶??
     **/
    private String capacity;
    /**
     * ®®°Ï????
     **/
    private String specsName;
    /**
     * ?????°±???????°Ï°„
     **/
    private String spuulierName;
    /**
     * ???®¶°±¢„???????°Ï°„
     **/
    private String agencyName;
    private String parentIdPath;
    /**
     * ??°Ï?°∞?®®°Î???°§
     **/
    private String proColorNo;
    private String parentNamePath;
    /**
     * ?°Ë???°ß
     **/
    private String remarks;
    /**
     * ?????°±?????®C???
     **/
    private String supplierId;
    /**
     * ??°§??????????°Ë?ID
     **/
    private String priCatHeadId;
    /**
     * ®®°Ï????ID
     **/
    private String specsId;
    /**
     * ??°Ï?°∞?ID
     **/
    private String baseProId;
    /**
     * ??°Ï?°∞?????°¿?
     **/
    private String categoryId;
    /**
     * ??°±?°±°ß??????
     **/
    private String applDesc;
    /**
     * ??°§???
     **/
    private String price;
    /**
     * ??????
     **/
    private String volume;
    /**
     * ®®°Ï?????°„????
     **/
    private String size;


  
	
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

	
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
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

	
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyId() {
        return agencyId;
    }

	
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

	
    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getTargetClient() {
        return targetClient;
    }

	
    public void setBaseProNo(String baseProNo) {
        this.baseProNo = baseProNo;
    }

    public String getBaseProNo() {
        return baseProNo;
    }

	
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProDesc() {
        return proDesc;
    }

	
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

	

	
    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsName() {
        return specsName;
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

	
    public void setParentNamePath(String parentNamePath) {
        this.parentNamePath = parentNamePath;
    }

    public String getParentNamePath() {
        return parentNamePath;
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

	
    public void setPriCatHeadId(String priCatHeadId) {
        this.priCatHeadId = priCatHeadId;
    }

    public String getPriCatHeadId() {
        return priCatHeadId;
    }

	
    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getSpecsId() {
        return specsId;
    }

	
    public void setBaseProId(String baseProId) {
        this.baseProId = baseProId;
    }

    public String getBaseProId() {
        return baseProId;
    }

	
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

	

	

	
    public void setApplDesc(String applDesc) {
        this.applDesc = applDesc;
    }

    public String getApplDesc() {
        return applDesc;
    }

	
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

	

	
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }

	
    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }



}
