package com.tencent.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tencent.WXPay;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
/**
 * 微信查询订单
 * @author hst
 *
 */
public class WiXinQueryBarPayServlet extends HttpServlet {


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
		 * out_trade_no  商户唯一订单号
		 * transaction_id 微信订单号
		 */
		String out_trade_no = request.getParameter("out_trade_no"); // 商户唯一订单号
		String  transaction_id   = request.getParameter(" transaction_id  "); // 微信订单号	
		
		/**
		 * 【开发调试】 为方便开发调试，在BridgeForScanPayBusinessTest类中设置相关参数
		 * 从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
		 */
//   	 BridgeForScanPayBusinessTest bridge = new BridgeForScanPayBusinessTest();

   	 	/**
		 * 【诺德兰内部集成】和【开发调试】时创建对象ScanPayReqData并将相关参数传入
		 */
   	ScanPayQueryReqData scanPayQueryReqData=new ScanPayQueryReqData(
   		 //transaction_id是微信系统为每一笔支付交易分配的订单号，通过这个订单号可以标识这笔交易，它由支付订单API支付成功时返回的数据里面获取到。
//   			"1002420238201509090829308481"
   			transaction_id,
   		//商户系统自己生成的唯一的订单号（可为空，不过不建议为空）
//   			,"12333"
   			out_trade_no
   			);
   	
   	/**
	 * 【对外接口】以xml的格式传递业务参数放在biz_content参数中，此处解析xml数据映射到ScanPayReqData中做进一步处理
	 */
   	
//	String biz_content  = request.getParameter("biz_content");//业务参数
/*	String biz_content =
			"<xml><out_trade_no>12333</out_trade_no>" +
			"<transaction_id></transaction_id></xml>";*/
	 //将从API返回的XML数据映射到Java对象
//	ScanPayQueryReqData scanPayQueryReqData = (ScanPayQueryReqData) Util.getObjectFromXML(biz_content, ScanPayQueryReqData.class);
	/**
	  * 【对外接口】时此处需要调用项目持久化方法，将传入信息存入数据库中
	  */
    
	try {
		String str = WXPay.requestScanPayQueryService(scanPayQueryReqData);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(str);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
