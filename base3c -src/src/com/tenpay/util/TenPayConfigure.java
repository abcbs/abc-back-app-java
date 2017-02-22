package com.tenpay.util;

import java.net.URL;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 14:40
 * 这里放置各种配置数据
 */
public class TenPayConfigure {
//	
//	// 密钥
//	private static String bargainor_id = "1281671801";
//
//	private static String key = "48f9a94d17f74e59c8918d85af26e424";
//	
	
	// 密钥   测试商户号
	private static String bargainor_id = "1900000109";

	private static String key = "8934e7d15453e97507ef794cf7b0519d";

	

	// 刷卡支付API
	public static String PAY_API = "https://myun.tenpay.com/cgi-bin/scan/code_pay.cgi";
	
	//查询
	public static String QUWEY_API ="https://gw.tenpay.com/gateway/normalorderquery.xml";
	
	//退款
	public static String CLIENT_API ="https://mch.tenpay.com/refundapi/gateway/refund.xml";
	



	public static String getBargainor_id() {
		return bargainor_id;
	}



	public static void setBargainor_id(String bargainorId) {
		bargainor_id = bargainorId;
	}



	public static String getKey() {
		return key;
	}



	public static void setKey(String key) {
		TenPayConfigure.key = key;
	}



	public static String getPAY_API() {
		return PAY_API;
	}



	public static void setPAY_API(String pAYAPI) {
		PAY_API = pAYAPI;
	}



	public static String getQUWEY_API() {
		return QUWEY_API;
	}



	public static void setQUWEY_API(String qUWEYAPI) {
		QUWEY_API = qUWEYAPI;
	}



	public static String getCLIENT_API() {
		return CLIENT_API;
	}



	public static void setCLIENT_API(String cLIENTAPI) {
		CLIENT_API = cLIENTAPI;
	}
	
	
	

}
