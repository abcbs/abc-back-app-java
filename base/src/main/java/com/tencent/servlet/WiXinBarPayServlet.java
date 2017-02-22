package com.tencent.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.math.RandomUtils;

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.front.IOS_IndexController;
import com.ndlan.canyin.frontdesk.front.bill.RunBarPayController;
import com.tencent.WXPay;
import com.tencent.business.BridgeForScanPayBusinessTest;
import com.tencent.business.ScanPayBusiness;
import com.tencent.business.ScanPayBusinessImpl;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.common.report.protocol.ReportReqData;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_protocol.ScanPayResData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
/**
 * 微信条码支付
 * @author hst
 *
 */
public class WiXinBarPayServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 【诺德兰内部系统集成】直接传入以下参数
		 * @param out_trade_no  商户唯一订单号
		 * @param auth_code 扫码枪扫描到的用户手机钱包中的付款条码
		 * @param total_fee 订单总金额，单位为分
		 * @param body 商品或支付单简要描述
		 * @param detail  商品名称明细列表 非必填
		 */
		String out_trade_no = request.getParameter("out_trade_no"); 
		String auth_code = request.getParameter("auth_code"); 
		String total_fee = request.getParameter("total_fee"); 
		String body = request.getParameter("body");   
		String detail = request.getParameter("detail");
		String device_info = request.getParameter("device_info");   //终端设备号(商户自定义，如门店编号)
		/*Double total_fee_int=0.00;
		if(total_fee!=null){
		 total_fee_int = (Double.parseDouble(total_fee))/100;
		}*/
		RunBarPayController bs=new RunBarPayController();
		String json=bs.loadJSON(out_trade_no, auth_code, total_fee, body, "weixin_pay", "", "", "");
		
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(json);
		
		
//		String biz_content  = request.getParameter("biz_content");
		/*
		*//**
		 * 【开发调试】 为方便开发调试，在BridgeForScanPayBusinessTest类中设置相关参数
		 * 从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
		 *//*
   	 BridgeForScanPayBusinessTest bridge = new BridgeForScanPayBusinessTest();
		
		*//**
		 * 【诺德兰内部集成】和【开发调试】时创建对象ScanPayReqData并将相关参数传入
		 *//*
		   ScanPayReqData scanPayReqData = new ScanPayReqData(
        //这个是扫码终端设备从用户手机上扫取到的支付授权号，有效期是1分钟
				   auth_code,
        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
       // bridge.getBody(),
				   body,
        //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
        bridge.getAttach(),
        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
//        bridge.getOutTradeNo(),
				   out_trade_no,
        //订单总金额，单位为“分”，只能整数
//        bridge.getTotalFee(),
				   total_fee_int,
        //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
				   device_infobridge.getDeviceInfo(),
        //订单生成的机器IP
        bridge.getSpBillCreateIP(),
        //订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
        bridge.getTimeStart(),
        //订单失效时间，格式同上
        bridge.getTimeExpire(),
        //商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        bridge.getGoodsTag()
);
		*//**
		 * 【对外接口】以xml的格式传递业务参数放在biz_content参数中，此处解析xml数据映射到ScanPayReqData中做进一步处理
		 * biz_content 业务参数
		 *//*

		String biz_content =
				"<xml><attach>订单额外描述</attach><auth_code>120269300684844649</auth_code>" +
				"<body>刷卡支付测试</body><device_info>1000</device_info><goods_tag></goods_tag>" +
				"<nonce_str>8aaee146b1dee7cec9100add9b96cbe2</nonce_str><out_trade_no>201509101013</out_trade_no>" +
				"<spbill_create_ip>14.17.22.52</spbill_create_ip><total_fee>1</total_fee></xml>";
		 //将从API返回的XML数据映射到Java对象
//        ScanPayReqData scanPayReqData = (ScanPayReqData) Util.getObjectFromXML(biz_content, ScanPayReqData.class);
        *//**
		  * 【对外接口】时此处需要调用项目持久化方法，将传入信息存入数据库中
		  *//*
     
        ScanPayBusiness.ResultListener resultListener=new ScanPayBusinessImpl(); 
        //--------------------------------------------------------------------
        //-----------支付---------------------------------------------------------
	try {
		String scanPayResData =  WXPay.doScanPayBusiness(scanPayReqData, resultListener);
	 	response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(scanPayResData);

	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
	}
	
}
