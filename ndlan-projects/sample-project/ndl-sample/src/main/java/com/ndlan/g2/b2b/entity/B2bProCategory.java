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



@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "b2b_pro_category" )
public class B2bProCategory  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    
    
    
    
    @Column(name = "category_grade"
     ,length = 20
    
    
    
    )
    private String categoryGrade;
    @Column(name = "category_tone_ios"
     ,length = 36
    
    
    
    )
    private String categoryToneIos;
    
    
    
    
    /**
     * ????¡°?????¡À????¨¦¡±?id
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "category_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String categoryId;
    @Column(name = "parent_category_name"
     ,length = 255
    
    
    
    )
    private String parentCategoryName;
    @Column(name = "category_status"
     ,length = 32
    
    
    
    )
    private String categoryStatus;
    @Column(name = "parent_category_id"
     ,length = 36
    
    
    
    )
    private String parentCategoryId;
    @Column(name = "category_name"
     ,length = 255
    
    
    
    )
    private String categoryName;
    @Column(name = "parent_id_path"
     ,length = 255
    
    
    
    )
    private String parentIdPath;
    
    
    
    
    
    
    
    
    @Column(name = "parent_name_path"
     ,length = 255
    
    
    
    )
    private String parentNamePath;
    @Column(name = "category_tone_android"
     ,length = 36
    
    
    
    )
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
