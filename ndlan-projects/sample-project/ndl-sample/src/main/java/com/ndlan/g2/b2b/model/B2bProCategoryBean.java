package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;


public class B2bProCategoryBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    private String restId;
    private String categoryGrade;
    private String categoryToneIos;
    /**
     * ????¡°?????¡À????¨¦¡±?id
     **/
    private String categoryId;
    private String parentCategoryName;
    private String categoryStatus;
    private String parentCategoryId;
    private String categoryName;
    private String parentIdPath;
    private String parentNamePath;
    private String categoryToneAndroid;


  
	
    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestId() {
        return restId;
    }

	

	
    public void setCategoryGrade(String categoryGrade) {
        this.categoryGrade = categoryGrade;
    }

    public String getCategoryGrade() {
        return categoryGrade;
    }

	
    public void setCategoryToneIos(String categoryToneIos) {
        this.categoryToneIos = categoryToneIos;
    }

    public String getCategoryToneIos() {
        return categoryToneIos;
    }

	

	
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

	
    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

	
    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

	
    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

	
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

	
    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }

	

	

	
    public void setParentNamePath(String parentNamePath) {
        this.parentNamePath = parentNamePath;
    }

    public String getParentNamePath() {
        return parentNamePath;
    }

	
    public void setCategoryToneAndroid(String categoryToneAndroid) {
        this.categoryToneAndroid = categoryToneAndroid;
    }

    public String getCategoryToneAndroid() {
        return categoryToneAndroid;
    }



}
