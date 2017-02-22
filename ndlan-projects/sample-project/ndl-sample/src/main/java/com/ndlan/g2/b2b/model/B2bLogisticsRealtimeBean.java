package com.ndlan.g2.b2b.model;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class B2bLogisticsRealtimeBean implements java.io.Serializable{

	private static final long serialVersionUID =-1;
	
    /**
     * ?¡ë??????¨C??¡¤
     **/
    private String logisticsLineCode;
    /**
     * ??¡°?¡ë???¡ã??¢ã
     **/
    private String currAddress;
    /**
     * ?¡ë????????¡ª???????ID
     **/
    private String logisticsRealtimeId;
    /**
     * ?????¢ã?????¡ã??¢ã
     **/
    private String nextAddress;
    /**
     * ?¡è???¡§
     **/
    private String remake;
    /**
     * ?¡ë????¨¨??ID
     **/
    private String logisticsLineId;
    /**
     * ??¡°?¡ë??¡ª?¨¦¡ª?
     **/
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
