package com.ndlan.canyin.base.entity.ctzh;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * 锟斤拷锟斤拷锟绞碉拷锟�
 * 
 * @author husitong
 * 
 */

@Entity
@Table(name = "cm_chain_store")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ChainStore extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "store_id", unique = true, nullable = false, length = 36)
	private String storeId;

	@Column(name = "store_name", length = 128)
	private String storeName;

	@Column(name = "parent_store_id", length = 36)
	private String parentStoreId;

	@Column(name = "cam_level", length = 32)
	private String camLevel;
	
	@Column(name = "path", length = 3600)
	private String path;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PARENT_ID")
	@JsonIgnore
	private ChainStore parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.ALL })
	@JsonBackReference
	@JsonManagedReference
	@JsonIgnore
	private List<ChainStore> children = new ArrayList();
	
	@Column(name = "rest_id", length = 36)
	private String restId;
	
	@Column(name = "manager")
	private String manager;
	
	@Column(name = "datarole")
	private String datarole;
	
	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getDatarole() {
		return datarole;
	}

	public void setDatarole(String datarole) {
		this.datarole = datarole;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getParentStoreId() {
		return parentStoreId;
	}

	public void setParentStoreId(String parentStoreId) {
		this.parentStoreId = parentStoreId;
	}

	public String getCamLevel() {
		return camLevel;
	}

	public void setCamLevel(String camLevel) {
		this.camLevel = camLevel;
	}

	public ChainStore getParent() {
		return parent;
	}

	public void setParent(ChainStore parent) {
		this.parent = parent;
	}

	public List<ChainStore> getChildren() {
		return children;
	}

	public void setChildren(List<ChainStore> children) {
		this.children = children;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

}
