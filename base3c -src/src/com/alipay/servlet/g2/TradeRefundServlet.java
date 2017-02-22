package com.alipay.servlet.g2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
/**
 * 支付宝退款
 * @author zhengjiansong
 *
 */
public class TradeRefundServlet extends HttpServlet {

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
		 * @param trade_no  支付宝交易号 支付宝交易凭证号
		 * @param refund_amount 退款金额 需要退款的金额，该金额不能大于订单金额，单位为元，支持两位小数
		 * @param out_request_no 商户退款请求号 标识一次退款请求，同一笔交易多次退款需要保证唯一
		 * @param refund_reason 退款的原因说明
		 * @param store_id 商户的门店编号
		 * @param terminal_id 商户的终端编号
		 */
		
		String out_request_no = "";
		String trade_no = "";
		String refund_amount = "";
		
		/**
		 * 直接传入参数
		 */
		 out_request_no = request.getParameter("out_request_no");  //退款批次号
		 trade_no = request.getParameter("trade_no");  //支付宝订单号
		 refund_amount = request.getParameter("refund_amount");//	退款金额
		 
		 /**
			 * 【对外接口】以json的格式传递业务参数放在biz_content参数中，此处解析json数据映射到ScanPayReqData中做进一步处理
			 * biz_content 业务参数
			 */
//			String biz_content  = request.getParameter("biz_content");
//		 String biz_content = "{\"out_trade_no\": \"201503022001\",\"scene\": \"bar_code\"," +
//					"\"auth_code\": \"28763443825664394\",\"total_amount\": \"0.01\"," +
//					"\"discountable_amount\":\"8.88\",\"undiscountable_amount \": \"80\"," +
//					"\"subject\": \"当面付条码支付\",\"goods_detail\": [{\"goods_id\": \"apple-01\"," +
//					"\"goods_name\": \"ipad\",\"goods_category\": \"7788230\",\"price\": \"88.88\"," +
//					"\"quantity\": \"1\"},	{\"goods_id\": \"apple-01\",\"goods_name\": \"ipad\"," +
//					"\"goods_category\": \"7788230\",\"price\": \"88.88\",\"quantity\": \"1\"}]," +
//					"\"operator_id\": \"op001\",\"store_id\": \"pudong001\",\"terminal_id\": \"t_001\"," +
//					"\"time_expire\": \"2015-01-24 03:07:50\"}";
//		 
//		 try {
//			JSONObject contentJson = new JSONObject(biz_content);
//			 out_request_no = (String) contentJson.get("out_request_no");  //退款批次号
//			 trade_no = (String) contentJson.get("trade_no");  //支付宝订单号
//			 refund_amount = (String) contentJson.get("refund_amount");//	退款金额
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
		 
		AlipayTradeRefundResponse res = ToAlipayBarTradePay.refundOrder(trade_no, refund_amount, out_request_no);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(res.getBody());
		
	}

	
}
