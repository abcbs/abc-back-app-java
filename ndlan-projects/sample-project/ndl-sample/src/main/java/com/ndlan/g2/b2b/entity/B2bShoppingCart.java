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
@Table(name = "b2b_shopping_cart" )
public class B2bShoppingCart  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ?¢ã?¨¦?¡®¨¦??
     **/
    @Column(name = "total"
     ,length = 100
    
    
    
    )
    private String total;
    /**
     * ?????¡¤ID
     **/
    @Column(name = "customer_id"
     ,length = 50
    
    
    
    )
    private String customerId;
    /**
     * ????¡ë?
     **/
    @Column(name = "all_discount"
     ,length = 100
    
    
    
    )
    private String allDiscount;
    /**
     * ??¡ª¨¦¡°?????¡ì¡ã
     **/
    @Column(name = "rest_name"
     ,length = 255
    
    
    
    )
    private String restName;
    /**
     * ??????
     **/
    @Column(name = "all_privilege"
     ,length = 100
    
    
    
    )
    private String allPrivilege;
    
    
    
    
    /**
     * ?????¡¤?¡ì¡°???
     **/
    @Column(name = "customer_name"
     ,length = 50
    
    
    
    )
    private String customerName;
    
    
    
    
    /**
     * ??¡ª¨¦¡°?ID
     **/
    @Column(name = "rest_id"
     ,length = 36
    
    
    
    )
    private String restId;
    
    
    
    
    
    
    
    
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @Column(name = "target_client"
     ,length = 20
    
    
    
    )
    private String targetClient;
    /**
     * ¨¨???¡ë?¨¨?????¨¦¡±?
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cart_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String cartId;
    
    
    
    

      private List< B2bShoppingCartPkgBeanBean > cartPackage;

   

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setAllDiscount(String allDiscount) {
        this.allDiscount = allDiscount;
    }

    public String getAllDiscount() {
        return allDiscount;
    }
    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestName() {
        return restName;
    }
    public void setAllPrivilege(String allPrivilege) {
        this.allPrivilege = allPrivilege;
    }

    public String getAllPrivilege() {
        return allPrivilege;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
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
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

 public void setCartPackage(List< B2bShoppingCartPkgBeanBean > cartPackage) {
        this.cartPackage = cartPackage;
    }

    public List< B2bShoppingCartPkgBeanBean > getCartPackage() {
        return cartPackage;
    }

}
