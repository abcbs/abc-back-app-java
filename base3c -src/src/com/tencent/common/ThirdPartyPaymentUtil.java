package com.tencent.common;

/**
 * 第三方支付
 * @author john
 *
 */
public class ThirdPartyPaymentUtil {
	private static String ali_pay = "http://192.168.10.101:8080/third-party-payment/barPay.do";
	

	public static String getAli_pay() {
		return ali_pay;
	}

	public static void setAli_pay(String aliPay) {
		ali_pay = aliPay;
	}

	
	

}
