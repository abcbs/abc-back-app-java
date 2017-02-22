package com.alipay.servlet.g2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillService;
/**
 * 支付宝订单查询
 * @author zhengjiansong
 *
 */
public class TradeQueryServlet extends HttpServlet {
	
	private DinerBillService dinerBillService=new DinerBillService();;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ToAlipayBarTradePay toalipaybartradepay=new ToAlipayBarTradePay(); 
		String out_trade_no = request.getParameter("out_trade_no");  //商户订单号
		AlipayTradeQueryResponse res = toalipaybartradepay.query(out_trade_no);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(res.getBody());
	}

	public DinerBillService getDinerBillService() {
		return dinerBillService;
	}

	public void setDinerBillService(DinerBillService dinerBillService) {
		this.dinerBillService = dinerBillService;
	}
	
	

}
