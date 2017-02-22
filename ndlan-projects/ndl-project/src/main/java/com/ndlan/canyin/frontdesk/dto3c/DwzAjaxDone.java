 package com.ndlan.canyin.frontdesk.dto3c;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 public class DwzAjaxDone
 {
   private String statusCode = "200";
   private String message = "操作成功";
   private String navTabId = "";
   private String rel = "";
   private String callbackType = "";
   private String forwardUrl = "";
   private String name = "";
   private String restName = "";
   private String adrDetail = "";
   private String restId = "";
   private String mId = "";
   private String juridicalPhone = "";
   private String sign = "";
 
   private String billId = "";
   private String type = "";
   private String restpic = "";
   private String value = "";
   private List<?> list;
   private Map<String, String> messageMap;
   private Map<String, Object> messageObject;
   private Map<String, Object> dataMap = new HashMap();
   private List<Object> dataList = new ArrayList();
 
   
   
   
   

   public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getJuridicalPhone() {
	return juridicalPhone;
}
public void setJuridicalPhone(String juridicalPhone) {
	this.juridicalPhone = juridicalPhone;
}
public String getAdrDetail() {
	return adrDetail;
}
public void setAdrDetail(String adrDetail) {
	this.adrDetail = adrDetail;
}


   public String getBillId() {
	return billId;
}
public void setBillId(String billId) {
	this.billId = billId;
}
public String getmId() {

	return mId;
}
public void setmId(String mId) {
	this.mId = mId;
}
public String getRestId() {
	return restId;
}
public void setRestId(String restId) {
	this.restId = restId;
}
public String getRestpic() {
	return restpic;
}
public void setRestpic(String restpic) {
	this.restpic = restpic;
}
public DwzAjaxDone(String statusCode, String message)
   {
     this.statusCode = statusCode;
     this.message = message;
   }
   public DwzAjaxDone() {
   }
   public String getStatusCode() {
     return this.statusCode;
   }
   public void setStatusCode(String statusCode) {
     this.statusCode = statusCode;
   }
   public String getMessage() {
     return this.message;
   }
   public void setMessage(String message) {
     this.message = message;
   }
   
   public String getRestName() {
	return restName;
}
public void setRestName(String restName) {
	this.restName = restName;
}
public String getNavTabId() {
     return this.navTabId;
   }
   public void setNavTabId(String navTabId) {
     this.navTabId = navTabId;
   }
   public String getRel() {
     return this.rel;
   }
   public void setRel(String rel) {
     this.rel = rel;
   }
   public String getCallbackType() {
     return this.callbackType;
   }
   public void setCallbackType(String callbackType) {
     this.callbackType = callbackType;
   }
   public String getForwardUrl() {
     return this.forwardUrl;
   }
   public void setForwardUrl(String forwardUrl) {
     this.forwardUrl = forwardUrl;
   }
   public String getSign() {
     return this.sign;
   }
   public void setSign(String sign) {
     this.sign = sign;
   }
   public String getType() {
     return this.type;
   }
   public void setType(String type) {
     this.type = type;
   }
   public String getValue() {
     return this.value;
   }
   public void setValue(String value) {
     this.value = value;
   }
   public List<?> getList() {
     return this.list;
   }
   public void setList(List<?> list) {
     this.list = list;
   }
   public Map<String, String> getMessageMap() {
     return this.messageMap;
   }
   public void setMessageMap(Map<String, String> messageMap) {
     this.messageMap = messageMap;
   }
   public Map<String, Object> getMessageObject() {
     return this.messageObject;
   }
   public void setMessageObject(Map<String, Object> messageObject) {
     this.messageObject = messageObject;
   }
   public Map<String, Object> getDataMap() {
     return this.dataMap;
   }
   public void setDataMap(Map<String, Object> dataMap) {
     this.dataMap = dataMap;
   }
   public List<Object> getDataList() {
     return this.dataList;
   }
   public void setDataList(List<Object> dataList) {
     this.dataList = dataList;
   }
 }

