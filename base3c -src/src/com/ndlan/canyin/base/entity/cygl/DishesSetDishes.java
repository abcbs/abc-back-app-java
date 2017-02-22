 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishesReplace;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.OneToOne;
 import javax.persistence.Table;
 import javax.persistence.Transient;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 /**
  * �ײ�
  * @author zhengjiansong
  *
  */
 @Entity
 @Table(name="cm_dishes_set_dishes")
 public class DishesSetDishes extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ds_dishes_id", unique=true, nullable=false, length=36)
   private String dsDishesId;
   
   //ios�������  beigen
   @Transient
   private String dishesId;
   
   
   @Transient
   private String dishesCode;
   
   //end
   
   @JsonIgnore
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="dishes_id")
   private Dishe dishe;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="dishes_name", length=128)
   private String dishesName;
 
   @Column(name="price")
   private BigDecimal price;
 
   @Column(name="unit_num")
   private BigDecimal unitNum;
 
   @Column(name="unit_name", length=128)
   private String unitName;
 
   @Transient
   private String replaceDishesStr;
 
   @Transient
   private String replaceDisheNames;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ds_id")
   @JsonIgnore
   DishesSet dishesSet;
 
   @JsonIgnore
   @OneToMany(mappedBy="dishesSetDishes", fetch=FetchType.LAZY)
   List<DishesSetDishesReplace> dishesSetDishesReplaces;
 
   public String getDsDishesId()
   {
     return this.dsDishesId;
   }
 
   public void setDsDishesId(String dsDishesId) {
     this.dsDishesId = dsDishesId;
   }
 
   public Dishe getDishe() {
     return this.dishe;
   }
 
   public void setDishe(Dishe dishe) {
     this.dishe = dishe;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public BigDecimal getPrice()
   {
     return this.price;
   }
 
   public void setPrice(BigDecimal price) {
     this.price = price;
   }
 
   public BigDecimal getUnitNum() {
     return this.unitNum;
   }
 
   public void setUnitNum(BigDecimal unitNum) {
     this.unitNum = unitNum;
   }
 
   public List<DishesSetDishesReplace> getDishesSetDishesReplaces() {
     return this.dishesSetDishesReplaces;
   }
 
   public void setDishesSetDishesReplaces(List<DishesSetDishesReplace> dishesSetDishesReplaces)
   {
     this.dishesSetDishesReplaces = dishesSetDishesReplaces;
   }
 
   public DishesSet getDishesSet() {
     return this.dishesSet;
   }
 
   public void setDishesSet(DishesSet dishesSet) {
     this.dishesSet = dishesSet;
   }
 
   public String getReplaceDishesStr() {
     StringBuilder sb = new StringBuilder();
     for (int i = 0; i < this.dishesSetDishesReplaces.size(); i++)
     {
       DishesSetDishesReplace replaceDishe = (DishesSetDishesReplace)this.dishesSetDishesReplaces.get(i);
       Dishe dishe = replaceDishe.getReplaceDishe();
       sb.append(replaceDishe.getReplaceDishe().getDishesId());
       sb.append(",");
       sb.append(dishe.getDishesName());
       sb.append(",");
       sb.append(BigDecimalUtil.format(dishe.getPrice()));
       sb.append(",");
       sb.append(replaceDishe.getUnitNum());
       sb.append(",");
       sb.append(dishe.getDishesUnit().getName());
       if (i != this.dishesSetDishesReplaces.size() - 1) {
         sb.append(":");
       }
     }
     this.replaceDishesStr = sb.toString();
     return this.replaceDishesStr;
   }
 
   public String getReplaceDisheNames() {
     StringBuilder sb = new StringBuilder();
     for (int i = 0; i < this.dishesSetDishesReplaces.size(); i++) {
       DishesSetDishesReplace replaceDishe = (DishesSetDishesReplace)this.dishesSetDishesReplaces.get(i);
       Dishe dishe = replaceDishe.getReplaceDishe();
       sb.append(dishe.getDishesName());
       sb.append(BigDecimalUtil.format(replaceDishe.getUnitNum()));
       sb.append(dishe.getDishesUnit().getName());
       if (i != this.dishesSetDishesReplaces.size() - 1) {
         sb.append(",");
       }
     }
     this.replaceDisheNames = sb.toString();
     if (StringUtils.isEmpty(this.replaceDisheNames)) {
       this.replaceDisheNames = "无";
     }
     return this.replaceDisheNames;
   }
 
   public String getDishesName() {
     return this.dishesName;
   }
 
   public void setDishesName(String dishesName) {
     this.dishesName = dishesName;
   }
 
   public String getUnitName() {
     return this.unitName;
   }
 
   public void setUnitName(String unitName) {
     this.unitName = unitName;
   }

public String getDishesId() {
	return dishesId;
}

public void setDishesId(String dishesId) {
	this.dishesId = dishesId;
}

public String getDishesCode() {
	return dishesCode;
}

public void setDishesCode(String dishesCode) {
	this.dishesCode = dishesCode;
}
   
   
 }

