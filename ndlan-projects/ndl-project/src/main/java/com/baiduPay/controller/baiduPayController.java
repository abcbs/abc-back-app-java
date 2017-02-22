package com.baiduPay.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiduPay.util.Configure;
import com.baiduPay.util.HttpBeg;
import com.baiduPay.util.MD5;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.front.IOS_IndexController;
import com.ndlan.canyin.frontdesk.front.bill.RunBarPayController;
import com.tenpay.util.TenpayUtil;

@Controller
@RequestMapping( { "/ios/baiduPay" })
@Lazy
public class baiduPayController extends BaseManageController {

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException, InterruptedException {
//		String destination="鍟嗗搧鎻忚堪";
//		destination=new String(destination.getBytes("GBK"), "UTF-8");
//		
//			
//			System.out.println(destination);
		
		baiduPayController ss1 = new baiduPayController();
//		 ss1.queryOne("3332312312322");
		// ss1.refund("333332222233456","1");  String order_no,String pay_code,String total_amount,String goods_name
//		ss1.Pay("222211","318142105442181478","1","111");
		// ss1.queryOne("333332222233456");
		//	
		
		
//		String s="service_code";
//		
		String sp_pass_through= "{"+'"'+"offline_pay"+'"'+":"+'"'+"1"+'"'+"}";                         //'{"offline_pay":"1"}';
		System.out.println(sp_pass_through);
		
				

	}
	
	
	/**
	 * 被扫支付
	 * @param order_no   
	 * @param total_amount //总金额
	 * @param goods_name //商品名称
	 */
	@RequestMapping(value = { "create" }, produces = { "application/json" })
	public void create(String order_no,String total_amount,String goods_name,Model model) throws UnsupportedEncodingException{
		goods_name=new String(goods_name.getBytes("iso-8859-1"), "utf-8");
//		String order_no = "333231231232222345";// 鍟嗗搧鍙�
//		String total_amount = "1";// 璁㈠崟鎬婚噾棰�
//		String goods_name = "商品描述";// 鍟嗗搧鍚嶇О
		String urlStr = Configure.CREATE;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String order_create_time = df.format(new Date());
		String goodsNameGbk = new String(goods_name.getBytes("UTF-8"), "UTF-8");
		MD5 md5 = new MD5();
		String sign = md5.MD5Encode("bank_no=101&currency=1&goods_name="+goodsNameGbk+"&input_charset=1" +
				"&order_create_time="+order_create_time+"&order_no="+order_no+"&pay_type=1&return_url=http://www.yoursite.com/return_url&" +
						"service_code=1&sign_method=1&sp_no="+Configure.getSpNo()+"&total_amount="+total_amount+"&version=2&" +
								"key="+Configure.getKey());
		
		String goodsNameURLEncoder = URLEncoder.encode(goods_name, "GBK");
		String requestUrl=urlStr+"?output_type=1&bank_no=101&currency=1&goods_name="+goodsNameURLEncoder+"" +
				"&input_charset=1&order_create_time="+order_create_time+"&order_no="+order_no+"&pay_type=1" +
				"&return_url=http://www.yoursite.com/return_url" +
				"&service_code=1&sign_method" +
				"=1&sp_no="+Configure.getSpNo()+"&total_amount="+total_amount+"&version=2&" +
				"sign="+sign;
		HttpBeg http = new HttpBeg();
		Map<Object, Object> productMap= http.networkRequest(requestUrl);
		model.addAttribute("productMap", productMap);
	}
	
	/**
	 * 查询多次
	 * @param order_no
	 * @return
	 * @throws InterruptedException
	 */
	public boolean queryRoll(String order_no) throws InterruptedException {
		int count = 60;   //2分钟
		for (int i = 0; i < count; i++) {
			DwzAjaxDone dw = queryOne(order_no);
			if (dw.getValue().equals("2")) {
				count = 0;
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param order_no      商户订单号
	 * @param pay_code      二维码号
	 * @param total_amount  订单金额
	 * @param goods_name    商品名称
	 * @return  127.0.0.1:8080/canyin-frontdesk/ios/baiduPay/Pay?order_no=2231232&pay_code=1231232&total_amount=1&goods_name=1111&returnJson=json
	 * @throws InterruptedException
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = { "Pay" }, produces = { "application/json" })
	@ResponseBody
	public DwzAjaxDone Pay(String out_trade_no,String auth_code,String total_fee,String body,String pay_type,String merchant_no,String appl_id,String payee_phone) throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		DwzAjaxDone dw = new DwzAjaxDone();
		body = new String(body.getBytes("iso-8859-1"), "UTF-8");
		RunBarPayController bs=new RunBarPayController();
		String json=bs.loadJSON(out_trade_no, auth_code, total_fee, body, "baidu_pay", merchant_no, appl_id, payee_phone);
		System.out.println("baidu"+json);
		ObjectMapper mapper = new ObjectMapper();
    	Map<Object, String> productMap = mapper.readValue(json, Map.class);
    	if(productMap!=null){
    		if(productMap.get("code").equals("SUCCESS")){
    			dw.setStatusCode("0");// 支付成功
				dw.setValue("OK");
    			
    		}else{  //其他均判断失败
    			dw.setStatusCode("2");//支付失败
				dw.setValue("支付失败");
    		}
    		
    	}
		return dw;
		/*SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String order_create_time = df.format(new Date()); 
		String urlStr = Configure.PAY_API;

		String goodsNameGbk = new String(body.getBytes("UTF-8"), "UTF-8");
		MD5 md5 = new MD5();
		String sp_pass_through= "{"+'"'+"offline_pay"+'"'+":"+'"'+"1"+'"'+"}"; 
		
		String sign = md5.MD5Encode("currency=1&goods_name=" + goodsNameGbk
				+ "&input_charset=1&order_create_time=" + order_create_time
				+ "&order_no=" + out_trade_no + "&pay_code=" + auth_code
				+ "&service_code=1&sign_method=1&sp_no=" + Configure.getSpNo()
				+ "&sp_pass_through="+sp_pass_through+"&total_amount=" + total_fee + "&version=2&key="
				+ Configure.getKey());
		String goodsNameURLEncoder = URLEncoder.encode(body, "GBK");
		String spPassThroughURLEncoder = URLEncoder.encode(sp_pass_through, "GBK");
		String requestUrl = urlStr + "?currency=1&goods_name="
				+ goodsNameURLEncoder + "&input_charset=1&order_create_time="
				+ order_create_time + "&order_no=" + out_trade_no + ""
				+ "&pay_code=" + auth_code
				+ "&service_code=1&sign_method=1&sp_no=" + Configure.getSpNo()
				+ "&sp_pass_through="+spPassThroughURLEncoder+"&total_amount=" + total_fee + "" + "&version=2&sign="
				+ sign + "";
		HttpBeg http = new HttpBeg();
		Map<Object, Object> productMap = http.networkRequest(requestUrl);
		if (productMap.get("ret").equals("69556")) {  //用户输入密码   调用轮询
			boolean result = queryRoll(out_trade_no);
			if (result) {
				dw.setStatusCode("0");// 支付成功
				dw.setValue("OK");
				return dw;
			} else {
				cancel(out_trade_no);
				dw.setStatusCode("2");//支付失败
				dw.setValue("支付失败");
				return dw;
			}
		} else {
			dw.setStatusCode(productMap.get("ret") + ""); //if ret=0  msg=OK  表示成功   用此方法判断 其他均表失败
															
			dw.setValue(productMap.get("msg") + "");
			return dw;
		}*/
//				return null;
	}

	/**
	 * 一次查询
	 * 
	 * @param order_no
	 *            
	 * @return
	 * @throws InterruptedException
	 */
	public DwzAjaxDone queryOne(String order_no) throws InterruptedException {
		String urlStr = Configure.QUERY_API;
		DwzAjaxDone dw = new DwzAjaxDone();
		MD5 md5 = new MD5();
		String sss = md5.MD5Encode("input_charset=1&order_no=" + order_no
				+ "&sign_method=1&sp_no=" + Configure.getSpNo()
				+ "&version=2&key=" + Configure.getKey());

		String requestUrl = urlStr + "?input_charset=1&order_no=" + order_no
				+ "&sign_method=1&sp_no=" + Configure.getSpNo()
				+ "&version=2&sign=" + sss + "";
		System.out.println(requestUrl);

		Thread.sleep(2000);
		HttpBeg http = new HttpBeg();
		Map<Object, Object> productMap = http.networkRequest(requestUrl);

		if (productMap.get("msg") != null && productMap.get("ret") != null) {
			if (productMap.get("msg").equals("OK")
					&& productMap.get("ret").equals("0")) {
				Map<Object, Object> productMap2 = (Map<Object, Object>) productMap
						.get("content");
				if (productMap2.get("pay_result").equals("2")) {
					System.out.println("付款成功");
					dw.setValue("2");
				}
			}
		}

		System.out.println(productMap.get("msg"));

		return dw;
	}

	/**
	 * 关闭订单
	 * @param order_no
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public DwzAjaxDone cancel(String order_no)
			throws UnsupportedEncodingException {

		String urlStr = Configure.CANCEL_API;

		MD5 md5 = new MD5();

		String sign = md5.MD5Encode("order_no=" + order_no
				+ "&output_charset=1&output_type=2&sign_method=1&sp_no="
				+ Configure.getSpNo() + "&key=" + Configure.getKey());

		String requestUrl = urlStr + "?order_no=" + order_no
				+ "&output_charset=1&output_type=2&sign_method=1&sp_no="
				+ Configure.getSpNo() + "&sign=" + sign;
		System.out.println(requestUrl);

		HttpBeg http = new HttpBeg();
		Map<Object, Object> productMap = http.networkRequest(requestUrl);
		Map<Object, Object> productMap2 = (Map<Object, Object>) productMap
				.get("response");
		System.out.println(productMap2.get("err_msg"));
		return null;

	}

	/**
	 * 退款
	 * 
	 * @param order_no
	 *            鍟嗗搧鍙�
	 * @param total_amount
	 *            璁㈠崟鎬婚噾棰�
	 * @throws UnsupportedEncodingException
	 */
	public void refund(String order_no, String total_amount)
			throws UnsupportedEncodingException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 璁剧疆鏃ユ湡鏍煎紡
		String cashback_time = df.format(new Date()); // 涓鸿幏鍙栧綋鍓嶇郴缁熸椂闂�
		String strRandom = TenpayUtil.buildRandom(4) + ""; // 閫�娴佹按鍙�
		String urlStr = Configure.REFUND_API;

		MD5 md5 = new MD5();
		String ss = "cashback_amount="
				+ total_amount
				+ "&cashback_time="
				+ cashback_time
				+ "&currency=1&input_charset=1&order_no="
				+ order_no
				+ ""
				+ "&output_charset=1&output_type=1&return_method=1&return_url=&service_code=2&sign_method=1&sp_no="
				+ Configure.getSpNo() + "" + "&sp_refund_no=" + strRandom
				+ "&version=2&key=" + Configure.getKey() + "";
		String sign = md5.MD5Encode(ss);
		String requestUrl = urlStr
				+ "?cashback_amount="
				+ total_amount
				+ "&cashback_time="
				+ cashback_time
				+ "&currency=1&input_charset=1&order_no="
				+ order_no
				+ ""
				+ "&output_charset=1&output_type=1&return_method=1&return_url=&service_code=2&sign_method=1&sp_no="
				+ Configure.getSpNo() + "" + "&sp_refund_no=" + strRandom
				+ "&version=2&sign=" + sign + "";
		System.out.println(requestUrl);

		HttpBeg http = new HttpBeg();
		http.networkXml(requestUrl);
	}

}
