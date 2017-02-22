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
@Table(name = "b2b_logistics_realtime" )
public class B2bLogisticsRealtime  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
    /**
     * ?¡ë??????¨C??¡¤
     **/
    @Column(name = "logistics_line_code"
     ,length = 36
    
    
    
    )
    private String logisticsLineCode;
    /**
     * ??¡°?¡ë???¡ã??¢ã
     **/
    @Column(name = "curr_address"
     ,length = 255
    
    
    
    )
    private String currAddress;
    
    
    
    
    /**
     * ?¡ë????????¡ª???????ID
     **/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "logistics_realtime_id"
     ,length = 36
     ,nullable = false
    
     ,unique = true
    )
    private String logisticsRealtimeId;
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * ?????¢ã?????¡ã??¢ã
     **/
    @Column(name = "next_address"
     ,length = 255
    
    
    
    )
    private String nextAddress;
    
    
    
    
    /**
     * ?¡è???¡§
     **/
    @Column(name = "remake"
     ,length = 255
    
    
    
    )
    private String remake;
    /**
     * ?¡ë????¨¨??ID
     **/
    @Column(name = "logistics_line_id"
     ,length = 36
    
    
    
    )
    private String logisticsLineId;
    /**
     * ??¡°?¡ë??¡ª?¨¦¡ª?
     **/
    @Column(name = "curr_date"
    
    
    
    
    )
    private Date currDate;


   

    public void setLogisticsLineCode(String logisticsLineCode) {
        this.logisticsLineCode = logisticsLineCode;
    }

    public String getLogisticsLineCode() {
        return logisticsLineCode;
    }
    public void setCurrAddress(String currAddress) {
        this.currAddress = currAddress;
    }

    public String getCurrAddress() {
        return currAddress;
    }
    public void setLogisticsRealtimeId(String logisticsRealtimeId) {
        this.logisticsRealtimeId = logisticsRealtimeId;
    }

    public String getLogisticsRealtimeId() {
        return logisticsRealtimeId;
    }
    public void setNextAddress(String nextAddress) {
        this.nextAddress = nextAddress;
    }

    public String getNextAddress() {
        return nextAddress;
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
    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }

    public Date getCurrDate() {
        return currDate;
    }


}
