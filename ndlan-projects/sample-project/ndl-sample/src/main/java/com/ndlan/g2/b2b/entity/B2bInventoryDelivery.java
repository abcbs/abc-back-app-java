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

import java.util.Date;


@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "b2b_inventory_delivery" )
public class B2bInventoryDelivery  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ?????¡°?¡ª????
     **/
    @Column(name = "delivery_date"
    
    
    
    
    )
    private Date deliveryDate;
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remark"
     ,length = 255
    
    
    
    )
    private String remark;
    
    
    
    
    
    
    
    
    /**
     * ?????¡°???
     **/
    @Column(name = "delivery_user"
     ,length = 255
    
    
    
    )
    private String deliveryUser;
    /**
     * ?????¡°??¡ã¨¦??
     **/
    @Column(name = "delivery_qty"
     ,length = 100
    
    
    
    )
    private String deliveryQty;
    /**
     * ??¡°????¡è?¨¨?¡§
     **/
    @Column(name = "inv_head_id"
     ,length = 36
    
    
    
    )
    private String invHeadId;
    
    
    
    
    /**
     * ???¨¦¡±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "inv_delivery_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String invDeliveryId;
    /**
     * ?????¡°??¡¤???
     **/
    @Column(name = "delivery_price"
     ,length = 100
    
    
    
    )
    private String deliveryPrice;
    
    
    
    
    /**
     * ?????¡°???????¢ã???¨C???|¨¨¡ã????|???????¢ã¡®
     **/
    @Column(name = "source"
     ,length = 36
    
    
    
    )
    private String source;


   

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
    public void setDeliveryUser(String deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public String getDeliveryUser() {
        return deliveryUser;
    }
    public void setDeliveryQty(String deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getDeliveryQty() {
        return deliveryQty;
    }
    public void setInvHeadId(String invHeadId) {
        this.invHeadId = invHeadId;
    }

    public String getInvHeadId() {
        return invHeadId;
    }
    public void setInvDeliveryId(String invDeliveryId) {
        this.invDeliveryId = invDeliveryId;
    }

    public String getInvDeliveryId() {
        return invDeliveryId;
    }
    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }


}
