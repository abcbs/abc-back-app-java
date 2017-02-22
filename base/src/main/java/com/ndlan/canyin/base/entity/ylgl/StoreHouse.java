 package com.ndlan.canyin.base.entity.ylgl;
 
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ylgl.RawMaterial;

 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_store_house")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class StoreHouse extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="sh_id", unique=true, nullable=false, length=36)
   private String shId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="sh_name", length=128)
   private String shName;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="manager_id")
   private Employee managerEmployee;
 
   @Column(name="manager_name", length=128)
   private String managerName;
 
   @Column(name="manager_code", length=128)
   private String managerCode;
 
   @Column(name="manager_phone", length=128)
   private String managerPhone;
 
   @OneToMany(mappedBy="storeHouse")
   private List<RawMaterial> materials = new ArrayList();
 
   public int getMaterialTypeCount() {
     return this.materials.size();
   }
   public List<RawMaterial> getMaterials() {
     return this.materials;
   }
 
   public void setMaterials(List<RawMaterial> materials)
   {
     this.materials = materials;
   }
 
   public String getShId()
   {
     return this.shId;
   }
 
   public void setShId(String shId)
   {
     this.shId = shId;
   }
 
   public String getShName() {
     return this.shName;
   }
 
   public void setShName(String shName) {
     this.shName = shName;
   }
 
   public String getManagerName() {
     return this.managerName;
   }
 
   public void setManagerName(String managerName) {
     this.managerName = managerName;
   }
 
   public String getManagerCode() {
     return this.managerCode;
   }
 
   public void setManagerCode(String managerCode) {
     this.managerCode = managerCode;
   }
 
   public String getManagerPhone() {
     return this.managerPhone;
   }
 
   public void setManagerPhone(String managerPhone) {
     this.managerPhone = managerPhone;
   }
   public String getRestId() {
     return this.restId;
   }
   public void setRestId(String restId) {
     this.restId = restId;
   }
   public Employee getManagerEmployee() {
     return this.managerEmployee;
   }
   public void setManagerEmployee(Employee managerEmployee) {
     this.managerEmployee = managerEmployee;
   }
 }

