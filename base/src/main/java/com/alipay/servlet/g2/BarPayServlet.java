package com.alipay.servlet.g2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.front.IOS_IndexController;
import com.ndlan.canyin.frontdesk.front.bill.RunBarPayController;
/**
 * 支付宝条码支付
 * @author zhengjiansong
 *
 */
public class BarPayServlet extends HttpServlet {


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
		 * @param auth_code 扫码枪扫描到的用户手机钱包中的付款条码
		 * @param total_fee 订单总金额
		 * @param discountable_amount 可打折金额  
		 * @param undiscountable_amount 不可打折金额  
		 * @param goods_detail 商品明细列表
		 * @param operator_id 商户操作员编号
		 * @param subject 商品或支付单简要描述
		 * @param store_id 商户门店编号
		 * @param terminal_id 机具终端编号
		 * @param body 对交易或商品的描述
		 */
		
		String out_trade_no = request.getParameter("out_trade_no");
		String auth_code = request.getParameter("auth_code");
		String total_amount =request.getParameter("total_amount");
		String subject =request.getParameter("subject");
		String pay_type =request.getParameter("pay_type");
		String merchant_no =request.getParameter("merchant_no");
		String appl_id =request.getParameter("appl_id");
		String payee_phone =request.getParameter("payee_phone");
		
//		
		
		ToAlipayBarTradePay toalipaybartradepay=new ToAlipayBarTradePay(); 
		RunBarPayController bs=new RunBarPayController();
		String json=bs.loadJSON(out_trade_no, auth_code, total_amount, subject, "ali_pay", merchant_no, appl_id, payee_phone);
		
//		AlipayTradePayResponse res = toalipaybartradepay.barPay(out_trade_no, auth_code, total_amount, subject,cptId);
		RunBarPayController dd=new RunBarPayController();
		dd.update(out_trade_no, "0");
		response.setCharacterEncoding("GBK");  
	    response.setContentType("application/json; charset=GBK");
		response.getWriter().write(json);
	}

}
