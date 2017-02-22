package com.baiduPay.util;

/**
 *  数据源
 * @author john
 *
 */
public class Configure {
	
	

	//商户号
	private static final String sp_no = "9000100005";
	
	//秘钥
	private static final String key = "pSAw3bzfMKYAXML53dgQ3R4LsKp758Ss";
	
	


	//1）被扫支付API
	public static String PAY_API = "https://www.baifubao.com/o2o/0/b2c/0/api/0/pay/0";
	
	
	//查询API
	public static String QUERY_API = "https://www.baifubao.com/o2o/0/b2c/0/api/0/query_trans/0";

	
	//取消订单API
	public static String CANCEL_API = "https://www.baifubao.com/api/0/cancel";	
	
	
	
	//退款API
	public static String REFUND_API = "https://www.baifubao.com/api/0/refund";
	
	//被扫支付
	public static String CREATE = "https://www.baifubao.com/o2o/0/code/0/create/0";
	
	
	
		


	public static String getPAY_API() {
		return PAY_API;
	}




	public static void setPAY_API(String pAYAPI) {
		PAY_API = pAYAPI;
	}




	public static String getSpNo() {
		return sp_no;
	}




	public static String getKey() {
		return key;
	}




	public static String getQUERY_API() {
		return QUERY_API;
	}




	public static void setQUERY_API(String qUERYAPI) {
		QUERY_API = qUERYAPI;
	}




	public static String getCANCEL_API() {
		return CANCEL_API;
	}




	public static void setCANCEL_API(String cANCELAPI) {
		CANCEL_API = cANCELAPI;
	}

	
	

}
