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
@Table(name = "b2b_logistics_order_delivery" )
public class B2bLogisticsOrderDelivery  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    @Column(name = "order_pkg_code"
     ,length = 36
    
    
    
    )
    private String orderPkgCode;
    /**
     * ???¨¦¡±¢ã???????¡ì¡ã
     **/
    @Column(name = "custom_name"
     ,length = 255
    
    
    
    )
    private String customName;
    /**
     * ¨¨?¡ì?¡ë??¡À???????0¨¨??????¢ã?1¨¨??¨¨?¡ì??¡ë
     **/
    @Column(name = "goods_type"
     ,length = 2
    
    
    
    )
    private String goodsType;
    /**
     * ¨¨???????¡­ID
     **/
    @Column(name = "order_pkg_id"
     ,length = 36
    
    
    
    )
    private String orderPkgId;
    /**
     * ¨¨?????¨¦?¡®¨¦??
     **/
    @Column(name = "order_amount"
     ,length = 100
    
    
    
    )
    private String orderAmount;
    /**
     * ¨¦¡­?¨¦¢ã??¡­????ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "lgst_ord_dlv_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String lgstOrdDlvId;
    /**
     * ?¡±?¨¨?¡ì??¡ã??¢ãID
     **/
    @Column(name = "delivery_address_id"
     ,length = 36
    
    
    
    )
    private String deliveryAddressId;
    /**
     * ???¨¦¡±¢ã???ID
     **/
    @Column(name = "custom_id"
     ,length = 36
    
    
    
    )
    private String customId;
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remake"
     ,length = 255
    
    
    
    )
    private String remake;
    /**
     * ?¡ë????ID
     **/
    @Column(name = "logistics_line_id"
     ,length = 36
    
    
    
    )
    private String logisticsLineId;
    /**
     * ?¡ë????????¡ì¡ã
     **/
    @Column(name = "logistics_line_name"
     ,length = 255
    
    
    
    )
    private String logisticsLineName;
    /**
     * ¨¦????¨C
     **/
    @Column(name = "post_code"
     ,length = 36
    
    
    
    )
    private String postCode;
    /**
     * ?¡±?¨¨?¡ì????¡±?¨¨??
     **/
    @Column(name = "receive_tellcall"
     ,length = 12
    
    
    
    )
    private String receiveTellcall;
    /**
     * ?¡±?¨¨?¡ì???¨¨?¡±????¨C????
     **/
    @Column(name = "receive_phone"
     ,length = 11
    
    
    
    )
    private String receivePhone;
    
    
    
    
    
    
    
    
    /**
     * ¨¨?????¨¦¡­?¨¦¢ã?ID
     **/
    @Column(name = "order_delivery_id"
     ,length = 36
    
    
    
    )
    private String orderDeliveryId;
    
    
    
    
    /**
     * ?????¡±???ID
     **/
    @Column(name = "supplier_id"
     ,length = 36
    
    
    
    )
    private String supplierId;
    
    
    
    
    /**
     * ?¡ë¢ã??¡§??¡ã???
     **/
    @Column(name = "region"
     ,length = 255
    
    
    
    )
    private String region;
    
    
    
    
    /**
     * ¨¨??????¡ª????
     **/
    @Column(name = "order_date"
    
    
    
    
    )
    private Date orderDate;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @Column(name = "supplier_name"
     ,length = 255
    
    
    
    )
    private String supplierName;
    /**
     * ?¡±?¨¨?¡ì???
     **/
    @Column(name = "receive_name"
     ,length = 36
    
    
    
    )
    private String receiveName;
    /**
     * ?¡ë??????¨C??¡¤
     **/
    @Column(name = "logistics_head_code"
     ,length = 36
    
    
    
    )
    private String logisticsHeadCode;
    /**
     * ¨¨???????¡ã??¢ã
     **/
    @Column(name = "detail_address"
     ,length = 255
    
    
    
    )
    private String detailAddress;


   

    public void setOrderPkgCode(String orderPkgCode) {
        this.orderPkgCode = orderPkgCode;
    }

    public String getOrderPkgCode() {
        return orderPkgCode;
    }
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomName() {
        return customName;
    }
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsType() {
        return goodsType;
    }
    public void setOrderPkgId(String orderPkgId) {
        this.orderPkgId = orderPkgId;
    }

    public String getOrderPkgId() {
        return orderPkgId;
    }
    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderAmount() {
        return orderAmount;
    }
    public void setLgstOrdDlvId(String lgstOrdDlvId) {
        this.lgstOrdDlvId = lgstOrdDlvId;
    }

    public String getLgstOrdDlvId() {
        return lgstOrdDlvId;
    }
    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }
    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomId() {
        return customId;
    }
    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRemake() {
        return remake;
    }
    public void setLogisticsLineId(String logisticsLineId) {
        this.logisticsLineId = logisticsLineId;
    }

    public String getLogisticsLineId() {
        return logisticsLineId;
    }
    public void setLogisticsLineName(String logisticsLineName) {
        this.logisticsLineName = logisticsLineName;
    }

    public String getLogisticsLineName() {
        return logisticsLineName;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }
    public void setReceiveTellcall(String receiveTellcall) {
        this.receiveTellcall = receiveTellcall;
    }

    public String getReceiveTellcall() {
        return receiveTellcall;
    }
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone() {
        return receivePhone;
    }
    public void setOrderDeliveryId(String orderDeliveryId) {
        this.orderDeliveryId = orderDeliveryId;
    }

    public String getOrderDeliveryId() {
        return orderDeliveryId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveName() {
        return receiveName;
    }
    public void setLogisticsHeadCode(String logisticsHeadCode) {
        this.logisticsHeadCode = logisticsHeadCode;
    }

    public String getLogisticsHeadCode() {
        return logisticsHeadCode;
    }
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }


}
