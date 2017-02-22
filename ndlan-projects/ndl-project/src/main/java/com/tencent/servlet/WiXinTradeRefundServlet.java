package com.tencent.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
import com.tencent.WXPay;
import com.tencent.business.BridgeForScanPayBusinessTest;
import com.tencent.business.ScanPayBusiness;
import com.tencent.business.ScanPayBusinessImpl;
import com.tencent.common.Util;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.protocol.refund_protocol.RefundReqData;
/**
 * 微信退款
 * @author hst
 *
 */
public class WiXinTradeRefundServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 【诺德兰内部系统集成】直接传入以下参数
		 * @param out_trade_no  商户唯一订单号
		 * @param transaction_id 微信订单号
		 * @param total_fee 订单总金额，单位为分
		 * @param out_refund_no 商户退款单号
		 * @param refund_fee  退款金额
		 * @param op_user_id 操作员帐号, 默认为商户号
		 * @param device_info 设备号，门店编号 作为识别接入的商户
		 */
		
		String out_trade_no = request.getParameter("out_trade_no"); 
		String  transaction_id   = request.getParameter("transaction_id"); 
		String out_refund_no = request.getParameter("out_refund_no");
		String total_fee = request.getParameter("total_fee");
		String refund_fee = request.getParameter("refund_fee");
		String op_user_id = request.getParameter("op_user_id");
		String device_info = request.getParameter("device_info");
		
		/**
		 * 【开发调试】 为方便开发调试，在BridgeForScanPayBusinessTest类中设置相关参数
		 * 从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
		 */
   	 BridgeForScanPayBusinessTest bridge = new BridgeForScanPayBusinessTest();
   	 	/**
		 * 【诺德兰内部集成】和【开发调试】时创建对象ScanPayReqData并将相关参数传入
		 */
     RefundReqData refundReqData=new RefundReqData(
    	     //transaction_id是微信系统为每一笔支付交易分配的订单号，通过这个订单号可以标识这笔交易，它由支付订单API支付成功时返回的数据里面获取到。
    		 transaction_id, 
    		//商户系统自己生成的唯一的订单号
    		 out_trade_no, 
    		 //微信支付分配的终端设备号，与下单一致bridge.getDeviceInfo()
    		 device_info,
    		 //退款单号。自己生成唯一   
    		 out_refund_no, 
    		 //订单金额，单位分
    		 Integer.parseInt(total_fee),
    		 //退款金额，单位分
    		Integer.parseInt(refund_fee), 
    		 //终端设备的ip地址  操作员帐号, 默认为商户号
    		op_user_id,
    		 null);
     
     /**
		 * 【对外接口】以xml的格式传递业务参数放在biz_content参数中，此处解析xml数据映射到ScanPayReqData中做进一步处理
		 */
//		String biz_content  = request.getParameter("biz_content");//业务参数
		/*String biz_content =
				"<xml><transaction_id>23232</transaction_id>" +
				"<out_refund_no>120269300684844649</out_refund_no>" +
				"<device_info>1000</device_info><refund_fee>1</refund_fee>" +
				"<op_user_id>8aaee146b1dee7cec9100add9b96cbe2</op_user_id>" +
				"<out_trade_no>201509101013</out_trade_no><total_fee>1</total_fee></xml> ";*/
		 //将从API返回的XML数据映射到Java对象
//		RefundReqData refundReqData = (RefundReqData) Util.getObjectFromXML(biz_content, RefundReqData.class);
		/**
		  * 【对外接口】时此处需要调用项目持久化方法，将传入信息存入数据库中
		  */
	try {
		String str = WXPay.requestRefundService(refundReqData);
		response.setCharacterEncoding("GBK");  
	    response.setContentType("application/xml; charset=GBK");
		response.getWriter().write(str);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
   	

	}
	
	

}
