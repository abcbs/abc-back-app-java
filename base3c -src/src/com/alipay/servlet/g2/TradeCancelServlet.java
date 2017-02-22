package com.alipay.servlet.g2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
/**
 * 支付宝撤销订单
 * @author zhengjiansong
 *
 */
public class TradeCancelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String out_trade_no = request.getParameter("out_trade_no");//商户订单号
		AlipayTradeCancelResponse res = ToAlipayBarTradePay.cancelOrder(out_trade_no);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(res.getBody());
	}

	
}
