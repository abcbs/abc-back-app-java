package com.tenpay.controller;

import static java.lang.Thread.sleep;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.front.IOS_IndexController;
import com.ndlan.canyin.frontdesk.front.bill.RunBarPayController;
import com.tencent.common.Configure;
import com.tenpay.RequestHandler;
import com.tenpay.client.ClientResponseHandler;
import com.tenpay.client.TenpayHttpClient;
import com.tenpay.util.TenPayConfigure;
import com.tenpay.util.MD5Util;

@Controller
@RequestMapping( { "/tenPay" })
@Lazy
public class TenPayController {
	public static void main(String[] args) {
		
		TenPayController  ss=new TenPayController();
//		ss.urlPemPath();
//		try {
//			ss.doPayQueryLoop("shanghudingdanhao1232");
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();   7758527125
//		}
		try {
			String auth_code="910198284693598455";
//			ss.chexiao("7758527125", "1");
//			ss.revokeRefund("7758527125","1");
//			ss.payRequest(auth_code, "商品描述", "7758527125", "1", null);
			
//			ss.queryRequest("775852712");
//			 @param out_tradeNo    商户订单号
//			 * @param out_refundNo   退款批次号  不重复
//			 * @param refund_fee     退款金额
//			 * @param total_fee      订单总金额
			ss.clientRefund("402881385108f14901510997dee9004a","17","1","1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    //支付
//			DwzAjaxDone d=ss.queryRequest("shanghudingdanhao123");   //查询
//			System.out.println(d.toString());
 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	
	private HttpServletRequest request;

	private HttpServletResponse response;
	/**
	 * 付款
	 * @param auth_code     授权码
	 * @param desc          商品描述
	 * @param sp_billno     商户订单号
	 * @param total_fee     支付金额
	 * @param sp_device_id  设备ID
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = { "payRequest" }, produces = { "application/json" })
	@ResponseBody
	public DwzAjaxDone payRequest(String auth_code,String body,String out_trade_no,String total_fee, Model model)
			throws Exception {
		
		RunBarPayController bs=new RunBarPayController();
		String json=bs.loadJSON(out_trade_no, auth_code, total_fee, body, "qq_pay", "", "", "");
		DwzAjaxDone ajax=new DwzAjaxDone();
    	ObjectMapper mapper = new ObjectMapper();
    	Map<Object, String> productMap = mapper.readValue(json, Map.class);
    	if(productMap.get("code").equals("SUCCESS")){
    		ajax.setStatusCode("0");
        	ajax.setValue("ok");
    	}else{
    		ajax.setStatusCode("1");
        	ajax.setValue("付款失败");
    	}
    	
		return ajax;
//		body=new String(body.toString().getBytes("iso8859-1"), "utf-8"); 
		/*String sp_device_id="WP00000001";
		// ---------------------------------------------------------
		// 财付通网关支付请求示例，商户按照此文档进行开发即可
		// ---------------------------------------------------------
		 商户号，上线时务必将测试商户号替换为正式商户号 
		String bargainor_id = TenPayConfigure.getBargainor_id();
		// 密钥
		String key = TenPayConfigure.getKey();


		// 创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init();
		
		//通信对象
	    TenpayHttpClient httpClient = new TenpayHttpClient();
	  //应答对象
	    ClientResponseHandler resHandler = new ClientResponseHandler();
		// 设置密钥
		reqHandler.setKey(key);
		reqHandler.setGateUrl(TenPayConfigure.getPAY_API());

		// -----------------------------
		// 设置支付参数
		// -----------------------------
		reqHandler.setParameter("auth_code", auth_code);   //授权码  POS设备扫描用户手Q，得到的授权码
		reqHandler.setParameter("charset", "1");  //字符集  1 UTF-8 固定为1
		reqHandler.setParameter("ver", "2.0");   //版本号	
		reqHandler.setParameter("desc", body);  //商品描述，32个字符以内
		reqHandler.setParameter("bargainor_id", bargainor_id);  //商户号
		reqHandler.setParameter("sp_billno", out_trade_no);   //商户订单号   商户系统内部的定单号，32个字符内、可包含字母
		reqHandler.setParameter("total_fee", total_fee); // 商品金额,以分为单位   int类型
		reqHandler.setParameter("fee_type", "1");  //1：人民币
		reqHandler.setParameter("sp_device_id", sp_device_id); // POS设备的ID，作为唯一标识   设备ID  WP00000001
		reqHandler.setParameter("pay_channel", "1");  //支付渠道       目前固定为1
		reqHandler.setParameter("sign_sp_id", bargainor_id);  //交易发起商户号    合法的财付通商户号，用来代替bargainor_id发起商户请求，并使用该商户号计算sign。

		// 请求的url
		String requestUrl = reqHandler.getRequestURL();

		// 获取debug信息,建议把请求和debug信息写入日志，方便定位问题
		String debuginfo = reqHandler.getDebugInfo();
		
		httpClient.setReqContent(requestUrl);
		String rescontent = "null";
		 if(httpClient.call()) {
		    	//设置结果参数
		    	rescontent = httpClient.getResContent();
		    	DwzAjaxDone ajax=new DwzAjaxDone();
		    	ObjectMapper mapper = new ObjectMapper();
		    	Map<Object, String> productMap = mapper.readValue(rescontent, Map.class);
		    	
		    	String retmsg=productMap.get("retmsg");
				if(productMap.get("retcode").equals("66227005")){   //支付输入密码 调用轮询
					boolean result=doPayQueryLoop(out_trade_no);
					if(result){
						System.out.println(productMap.get("retcode"));
						System.out.println(retmsg);
						ajax.setStatusCode("0");
						ajax.setValue("ok");
						return ajax;
					}else{
						//撤销订单
						revokeRefund(out_trade_no,total_fee);
						ajax.setStatusCode("100001");
						ajax.setValue("用户输入密码超时");
						return ajax;
					}
				}
				
				ajax.setStatusCode(productMap.get("retcode"));
				ajax.setValue(retmsg);
				if(productMap.get("retcode").equals("7013")){
					ajax.setValue("用户余额不足");
				}
				return ajax;
		    	   	
		    	//获取返回参数
		 }
		 
		return null;
		*/
		
	}
	
	

	
	/**
	 *    支付查询
	 * @param out_trade_no             
	 * @return
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = { "queryRequest" }, produces = { "application/json" })
	@ResponseBody
	public DwzAjaxDone queryRequest(String out_trade_no)
			throws JsonProcessingException, UnsupportedEncodingException, InterruptedException {
		DwzAjaxDone d=new DwzAjaxDone();
		 //商户号 
	    String partner = TenPayConfigure.getBargainor_id();

	    //密钥 
	    String key = TenPayConfigure.getKey();
	    
	    //创建查询请求对象
	    RequestHandler reqHandler = new RequestHandler(null, null);
	    //通信对象
	    TenpayHttpClient httpClient = new TenpayHttpClient();
	    //应答对象
	    ClientResponseHandler resHandler = new ClientResponseHandler();
	    
	    //-----------------------------
	    //设置请求参数
	    //-----------------------------
	    reqHandler.init();
	    reqHandler.setKey(key);
	    reqHandler.setGateUrl(TenPayConfigure.QUWEY_API);
	    
	    //-----------------------------
	    //设置接口参数
	    //-----------------------------388
	    reqHandler.setParameter("partner", partner);    //商户号
	    
	    //out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
	    reqHandler.setParameter("out_trade_no", out_trade_no);    	    	                //商家订单号
//	    reqHandler.setParameter("transaction_id", "1900000109201510306201954177");	//财付通交易单号    

	    //-----------------------------
	    //设置通信参数
	    //-----------------------------
	    //设置请求返回的等待时间
	    httpClient.setTimeOut(5);	
	    
	    //设置请求内容
	    String requestUrl = reqHandler.getRequestURL();
	    httpClient.setReqContent(requestUrl);
	    String rescontent = "null";
	    sleep(2000);
	    //后台调用
	    if(httpClient.call()) {
	    	System.out.println("调用hhttp请求+++++++++++++++++++++++++++++++++++=");
	    	//设置结果参数
	    	rescontent = httpClient.getResContent();
	    	try {
				resHandler.setContent(rescontent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	resHandler.setKey(key);
	    	   	
	    	//获取返回参数
	    	String retcode = resHandler.getParameter("retcode");
	    	d.setStatusCode(retcode);
	    	//判断签名及结果
	    	if(resHandler.isTenpaySign()&& "0".equals(retcode)) {
	    		
	    		//商户订单号
	                String out_trade_nomsg = resHandler.getParameter("out_trade_no");
	                //财付通订单号
	                String transaction_id = resHandler.getParameter("transaction_id");
	                //金额,以分为单位
	                String total_fee = resHandler.getParameter("total_fee");
	                //如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
	                String discount = resHandler.getParameter("discount");
	                //支付结果
	                String trade_state = resHandler.getParameter("trade_state");
	                d.setStatusCode(trade_state);
	                //支付成功
	                if("0".equals(trade_state)) {
	                	d.setValue("支付成功");
	                	System.out.println("支付成功");
	                    return d;
	                                  
	                }/*else{
	                	//开启轮询
	                	boolean result=doPayQueryLoop(out_trade_nomsg);
	                	if(result){                                   
	                		d.setValue("支付成功");
		                	System.out.println("支付成功");
		                    return d;
	                	}else{
	                		//走撤销流程
	                		d.setValue("支付失败");
		                	System.out.println("支付失败");
		                    return d;
	                		
	                	}
	                }*/
	    	} else {
	    		System.out.println(resHandler.getParameter("retmsg"));
	    		System.out.println(resHandler.getParameter("retcode"));
	    		d.setStatusCode(resHandler.getParameter("retcode"));
	    		d.setValue("验证签名失败或业务错误");
	    		return d;
	    		//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
	    	}	
	    } else {
	    	System.out.println("后台调用通信失败");   	
	    	System.out.println(httpClient.getResponseCode());
	    	System.out.println(httpClient.getErrInfo());
	    	d.setStatusCode(httpClient.getResponseCode()+"");
    		d.setValue("后台调用通信失败");
    		return d;
	    	//有可能因为网络原因，请求已经处理，但未收到应答。
	    }
	    
	    //获取debug信息,建议把请求、应答内容、debug信息，通信返回码写入日志，方便定位问题
	    System.out.println("http res:" + httpClient.getResponseCode() + "," + httpClient.getErrInfo());
	    System.out.println("req url:" + requestUrl);
	    System.out.println("req debug:" + reqHandler.getDebugInfo());
	    System.out.println("res content:" + rescontent);
	    System.out.println("res debug:" + resHandler.getDebugInfo());
		return d;
	}
	
	/**
	 * 退款
	 * @param out_tradeNo    商户订单号
	 * @param out_refundNo   退款批次号  不重复
	 * @param refund_fee     退款金额
	 * @param total_fee      订单总金额
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "clientRefund" }, produces = { "application/json" })
	@ResponseBody
	public DwzAjaxDone clientRefund(String out_tradeNo,String out_refundNo,String refund_fee,String total_fee) throws Exception{
		
		 //商户号 
	    String partner = TenPayConfigure.getBargainor_id();

	    //密钥 
	    String key = TenPayConfigure.getKey();
	    
	    //创建查询请求对象
	    RequestHandler reqHandler = new RequestHandler(null, null);
	    //通信对象
	    TenpayHttpClient httpClient = new TenpayHttpClient();
	    //应答对象
	    ClientResponseHandler resHandler = new ClientResponseHandler();
	    
	    //-----------------------------
	    //设置请求参数
	    //-----------------------------
	    reqHandler.init();
	    reqHandler.setKey(key);
	    reqHandler.setGateUrl(TenPayConfigure.CLIENT_API);
	    
	    //-----------------------------
	    //设置接口参数
	    //-----------------------------
	    reqHandler.setParameter("service_version", "1.1");
	    reqHandler.setParameter("partner", partner);	
	    reqHandler.setParameter("out_trade_no", out_tradeNo);	
//	    reqHandler.setParameter("transaction_id", "1900000109201101270026218385");
	    reqHandler.setParameter("out_refund_no", out_refundNo);	
	    reqHandler.setParameter("total_fee", total_fee);	
	    reqHandler.setParameter("refund_fee", refund_fee);
	    reqHandler.setParameter("op_user_id", "1900000109");	 //操作员
	    //操作员密码,MD5处理
	    reqHandler.setParameter("op_user_passwd", MD5Util.MD5Encode("111111","UTF-8"));	
	    	
	    reqHandler.setParameter("recv_user_id", "");	
	    reqHandler.setParameter("reccv_user_name", "");
	    //-----------------------------
	    //设置通信参数
	    //-----------------------------
	    //设置请求返回的等待时间
	    httpClient.setTimeOut(5);	
	    //读取证书相关配置信息,
	    String caPath = urlPemPath();
	    String certPath	= urlPfxPath();
	    
	    
	    //证书密码,正式商户号的证书密码通过短信方式发送到合同登记的手机号，系统上线前请注意修改为正确值
	    String certPassword = "1900000109";
	    //设置ca证书
	    httpClient.setCaInfo(new File(caPath));		
	    //设置商户证书
	    httpClient.setCertInfo(new File(certPath), certPassword);
	    
	    //设置发送类型POST
	    httpClient.setMethod("POST");     
	    
	    //设置请求内容
	    String requestUrl = reqHandler.getRequestURL();
	    httpClient.setReqContent(requestUrl);
	    String rescontent = "null";

	    //后台调用
	    if(httpClient.call()) {
	    	//设置结果参数
	    	rescontent = httpClient.getResContent();
	    	resHandler.setContent(rescontent);
	    	resHandler.setKey(key);
	    	   	
	    	//获取返回参数
	    	String retcode = resHandler.getParameter("retcode");
	    	
	    	//判断签名及结果
	    	if(resHandler.isTenpaySign()&& "0".equals(retcode)) {
	    	/*退款状态	refund_status	
				4，10：退款成功。
				3，5，6：退款失败。
				8，9，11:退款处理中。
				1，2: 未确定，需要商户原退款单号重新发起。
				7：转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
				*/
	    	String refund_status=resHandler.getParameter("refund_status");
	    	String out_refund_no=resHandler.getParameter("out_refund_no");
	    	System.out.println("商户退款单号"+out_refund_no+"的退款状态是："+refund_status);
//	    	out.println("商户退款单号"+out_refund_no+"的退款状态是："+refund_status);
	    		

	    	} else {
	    		//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
	    		System.out.println("验证签名失败或业务错误");
	    		System.out.println("retcode:" + resHandler.getParameter("retcode")+
	    	    	                    " retmsg:" + resHandler.getParameter("retmsg"));
//	    	    	out.println("retcode:" + resHandler.getParameter("retcode")+
//	    	    	                    " retmsg:" + resHandler.getParameter("retmsg"));
	    	}	
	    } else {
	    	System.out.println("后台调用通信失败");   	
	    	System.out.println(httpClient.getResponseCode());
	    	System.out.println(httpClient.getErrInfo());
	    	//有可能因为网络原因，请求已经处理，但未收到应答。
	    }
	    
	    //获取debug信息,建议把请求、应答内容、debug信息，通信返回码写入日志，方便定位问题
	    System.out.println("http res:" + httpClient.getResponseCode() + "," + httpClient.getErrInfo());
	    System.out.println("req url:" + requestUrl);
	    System.out.println("req debug:" + reqHandler.getDebugInfo());
	    System.out.println("res content:" + rescontent);
	    System.out.println("res debug:" + resHandler.getDebugInfo());
		return null;
	}
	
	
	/**
	 * 关闭订单
	 * @param out_tradeNo   商户订单号
	 * @param total_fee     总金额
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "revokeRefund" }, produces = { "application/json" })
	@ResponseBody
	public DwzAjaxDone revokeRefund(String out_tradeNo,String total_fee) throws Exception{
		DwzAjaxDone d=new DwzAjaxDone();
		 //商户号 
	    String partner = TenPayConfigure.getBargainor_id();

	    //密钥 
	    String key = TenPayConfigure.getKey();
	    
	    //创建查询请求对象
	    RequestHandler reqHandler = new RequestHandler(null, null);
	    //通信对象
	    TenpayHttpClient httpClient = new TenpayHttpClient();
	    //应答对象
	    ClientResponseHandler resHandler = new ClientResponseHandler();
	    
	    //-----------------------------
	    //设置请求参数
	    //-----------------------------
	    reqHandler.init();
	    reqHandler.setKey(key);
	    reqHandler.setGateUrl("https://myun.tenpay.com/cgi-bin/scan/code_cancel.cgi");
	    
	    //-----------------------------
	    //设置接口参数
	    //-----------------------------388
	    reqHandler.setParameter("partner", partner);    //商户号
	    
	    //out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
	    reqHandler.setParameter("out_trade_no", out_tradeNo);    	    	                //商家订单号
	    reqHandler.setParameter("total_fee", total_fee); 
	    reqHandler.setParameter("chv", "9");
	    reqHandler.setParameter("charset", "UTF-8");
//	    reqHandler.setParameter("transaction_id", "1900000109201510306201954177");	//财付通交易单号    

	    //-----------------------------
	    //设置通信参数
	    //-----------------------------
	    //设置请求返回的等待时间
	    httpClient.setTimeOut(5);	
	    
	    //设置请求内容
	    String requestUrl = reqHandler.getRequestURL();
	    httpClient.setReqContent(requestUrl);
	    String rescontent = "null";
	    //后台调用
	    if(httpClient.call()) {
//	    	
//	    	//设置结果参数
	    	rescontent = httpClient.getResContent();
	    	try {
				resHandler.setContent(rescontent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	resHandler.setKey(key);
	    	//获取返回参数
	    	System.out.println(resHandler.isTenpaySign()+"===============================================================================");
	    	String retcode = resHandler.getParameter("retcode");
	    	d.setStatusCode(retcode);
	    	//判断签名及结果
	    	if(resHandler.isTenpaySign()&& "0".equals(retcode)) {
	    		
	    		System.out.println("关闭订单成功");
	    	
	    	
	    	} else {
	    		System.out.println("http res:" + httpClient.getResponseCode() + "," + httpClient.getErrInfo());
	    	    System.out.println("req url:" + requestUrl);
	    	    System.out.println("req debug:" + reqHandler.getDebugInfo());
	    	    System.out.println("res content:" + rescontent);
	    	    System.out.println("res debug:" + resHandler.getDebugInfo());
	    		System.out.println(resHandler.getParameter("retmsg"));
	    		System.out.println(resHandler.getParameter("retcode"));
	    		d.setStatusCode(resHandler.getParameter("retcode"));
	    		d.setValue("验证签名失败或业务错误");
	    		return d;
	    		//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
	    	}	
	    } else {
	    	System.out.println("后台调用通信失败");   	
	    	System.out.println(httpClient.getResponseCode());
	    	System.out.println(httpClient.getErrInfo());
	    	d.setStatusCode(httpClient.getResponseCode()+"");
  		d.setValue("后台调用通信失败");
  		return d;
	    	//有可能因为网络原因，请求已经处理，但未收到应答。
	    }
	    
	    //获取debug信息,建议把请求、应答内容、debug信息，通信返回码写入日志，方便定位问题
	    System.out.println("http res:" + httpClient.getResponseCode() + "," + httpClient.getErrInfo());
	    System.out.println("req url:" + requestUrl);
	    System.out.println("req debug:" + reqHandler.getDebugInfo());
	    System.out.println("res content:" + rescontent);
	    System.out.println("res debug:" + resHandler.getDebugInfo());
		return d;
	}
	
	
	/**
	 * 撤销订单
	 * @param out_tradeNo
	 * @param out_refundNo
	 * @param refund_fee
	 * @param total_fee
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "chexiao" }, produces = { "application/json" })
	@ResponseBody
	public DwzAjaxDone chexiao(String out_tradeNo,String total_fee) throws Exception{
		
		 //商户号 
	    String partner = TenPayConfigure.getBargainor_id();

	    //密钥 
	    String key = TenPayConfigure.getKey();
	    
	    //创建查询请求对象
	    RequestHandler reqHandler = new RequestHandler(null, null);
	    //通信对象
	    TenpayHttpClient httpClient = new TenpayHttpClient();
	    //应答对象
	    ClientResponseHandler resHandler = new ClientResponseHandler();
	    
	    //-----------------------------
	    //设置请求参数
	    //-----------------------------
	    reqHandler.init();
	    reqHandler.setKey(key);
	    reqHandler.setGateUrl("https://api.qpay.qq.com/cgi-bin/scan/code_reverse.cgi");
	    
	    //-----------------------------
	    //设置接口参数
	    //-----------------------------
	    reqHandler.setParameter("service_version", "1.1");
	    reqHandler.setParameter("partner", partner);	
	    reqHandler.setParameter("out_trade_no", out_tradeNo);	
	    reqHandler.setParameter("total_fee", total_fee);	
	    reqHandler.setParameter("op_user_id", "1900000109");	 //操作员
	    //操作员密码,MD5处理
	    reqHandler.setParameter("op_user_passwd", MD5Util.MD5Encode("111111","UTF-8"));	
	    	
	    //-----------------------------
	    //设置通信参数
	    //-----------------------------
	    //设置请求返回的等待时间
	    httpClient.setTimeOut(5);	
	    //读取证书相关配置信息,
	    String caPath = urlPemPath();
	    String certPath	= urlPfxPath();
	    
	    
	    //证书密码,正式商户号的证书密码通过短信方式发送到合同登记的手机号，系统上线前请注意修改为正确值
	    String certPassword = "1900000109";
	    //设置ca证书
	    httpClient.setCaInfo(new File(caPath));		
	    //设置商户证书
	    httpClient.setCertInfo(new File(certPath), certPassword);
	    
	    //设置发送类型POST
	    httpClient.setMethod("POST");     
	    
	    //设置请求内容
	    String requestUrl = reqHandler.getRequestURL();
	    httpClient.setReqContent(requestUrl);
	    String rescontent = "null";

	    //后台调用
	    if(httpClient.call()) {
	    	//设置结果参数
	    	rescontent = httpClient.getResContent();
	    	resHandler.setContent(rescontent);
	    	resHandler.setKey(key);
	    	   	
	    	//获取返回参数
	    	String retcode = resHandler.getParameter("retcode");
	    	
	    	//判断签名及结果
	    	if(resHandler.isTenpaySign()&& "0".equals(retcode)) {
	    	/*退款状态	refund_status	
				4，10：退款成功。
				3，5，6：退款失败。
				8，9，11:退款处理中。
				1，2: 未确定，需要商户原退款单号重新发起。
				7：转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
				*/
	    	String refund_status=resHandler.getParameter("refund_status");
	    	String out_refund_no=resHandler.getParameter("out_refund_no");
	    	System.out.println("商户退款单号"+out_refund_no+"的退款状态是："+refund_status);
//	    	out.println("商户退款单号"+out_refund_no+"的退款状态是："+refund_status);
	    		

	    	} else {
	    		//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
	    		System.out.println("验证签名失败或业务错误");
	    		System.out.println("retcode:" + resHandler.getParameter("retcode")+
	    	    	                    " retmsg:" + resHandler.getParameter("retmsg"));
//	    	    	out.println("retcode:" + resHandler.getParameter("retcode")+
//	    	    	                    " retmsg:" + resHandler.getParameter("retmsg"));
	    	}	
	    } else {
	    	System.out.println("后台调用通信失败");   	
	    	System.out.println(httpClient.getResponseCode());
	    	System.out.println(httpClient.getErrInfo());
	    	//有可能因为网络原因，请求已经处理，但未收到应答。
	    }
	    
	    //获取debug信息,建议把请求、应答内容、debug信息，通信返回码写入日志，方便定位问题
	    System.out.println("http res:" + httpClient.getResponseCode() + "," + httpClient.getErrInfo());
	    System.out.println("req url:" + requestUrl);
	    System.out.println("req debug:" + reqHandler.getDebugInfo());
	    System.out.println("res content:" + rescontent);
	    System.out.println("res debug:" + resHandler.getDebugInfo());
		return null;
	}
	
	
	
	
	/**
	 * 订单轮询
	 * @param outTradeNo
	 * @return
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 */
	public boolean doPayQueryLoop(String outTradeNo) throws JsonProcessingException, UnsupportedEncodingException, InterruptedException{
		int loopCount=30;
        //进行循环查询
        for (int i = 0; i < loopCount; i++) {
            if (queryRequest(outTradeNo).getStatusCode().equals("0")) {
            	loopCount=0;
                return true;
            }
        }
        System.out.println("轮询支付失败----------------------------------------------------------------------------");
        return false;
	}
	
	public  String urlPemPath(){
		URL xmlpath = Configure.class.getClassLoader().getResource("cacert.pem");
		String s=xmlpath.toString();
		return s.substring(6);
	}
	
	public  String urlPfxPath(){
		URL xmlpath = Configure.class.getClassLoader().getResource("1900000109.pfx");
		String s=xmlpath.toString();
		return s.substring(6);
	}
	
	

}
