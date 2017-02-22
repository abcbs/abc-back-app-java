/**
 * 
 */
package com.ndlan.canyin.base.entity.base3c.billinfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月14日 上午10:17:51  
 */
@Entity
@Table(name = "base3c_return_reason")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class ReturnReasonEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "reason_id", unique = true, nullable = false, length = 36)
	private String reasonId;
	@Column(name = "reason_desc", length = 255)
	private String reasonDesc;
	@Column(name="rest_id",length=36)
	private String restId;
	public String getReasonId() {
		return reasonId;
	}
	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}

}
