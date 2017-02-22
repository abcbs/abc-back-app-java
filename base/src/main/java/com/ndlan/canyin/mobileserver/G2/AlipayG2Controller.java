package com.ndlan.canyin.mobileserver.G2;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.f2fpay.ToAlipayBarTradePay;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
@Controller
@RequestMapping({"/g2Alipay"})
@Lazy
public class AlipayG2Controller  extends BaseManageController{
	
	@RequestMapping(value="/g2GarPay", method=RequestMethod.POST, produces={"text/html;charset=UTF-8"})
	   @ResponseBody
	   public void g2GarPay(Model model, HttpServletRequest request){
		String out_trade_no = "5"; // 商户唯一订单号
		String auth_code = "5"; // 扫码枪扫描到的用户手机钱包中的付款条码
		String total_amount = "0.01";
		String subject = "1";
		ToAlipayBarTradePay toalipaybartradepay=new ToAlipayBarTradePay(); 
		AlipayTradePayResponse res =toalipaybartradepay.barPay(out_trade_no, auth_code, total_amount, subject,"");
		String dd = "d";
	}
	   

}
