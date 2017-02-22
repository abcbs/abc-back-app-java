package com.ndlan.canyin.frontdesk.front.bill;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.net.www.protocol.http.HttpURLConnection;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.f2fpay.ToAlipayQrTradePay;
import com.alipay.factory.AlipayAPIClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.hygl.CardPayment;
import com.ndlan.canyin.base.repository.cygl.Payment;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.DwzAjaxDone;
import com.ndlan.canyin.frontdesk.service.qtsy.DinerBillPaymentService;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;
import com.tencent.common.ThirdPartyPaymentUtil;
import com.tenpay.controller.TenPayController;
import com.tenpay.util.TenpayUtil;

@Controller
@RequestMapping( { "/run/barPay" })
@Lazy
public class RunBarPayController extends BaseManageController implements Runnable {
	

	/**、、
	 * 支付宝扫码支付
	 * @param out_trade_no   //商品订单号
	 * @param total_amount   //支付金额
	 * @param subject       //商品描述
	 * @param model
	 * @param mid  腾势的商户号
	 * @param tid  腾势的终端号
	 * @param alipayMid           支付宝商户号
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = { "qrPay" }, produces = { "application/json" })
	public void qrPay(String out_trade_no,String total_amount,String subject,String mid,String tid,String alipayMid, Model model) throws UnsupportedEncodingException {
		subject=new String(subject.getBytes("iso-8859-1"), "utf-8");
		AlipayTradePrecreateResponse res = ToAlipayQrTradePay.qrPay(out_trade_no, total_amount, subject);
		model.addAttribute("res", res);
	}
	
	/**
	 * 支付宝账单app端轮询
	 * @param id
	 * @param model
	 * @param paymentType
	 */
	@RequestMapping(value = { "queryPaymement" }, produces = { "application/json" })
	public void queryPaymement(String id, Model model, String paymentType) {
		List<Payment> list = getOne(id, paymentType);   //if  bill_status==1 表示支付成功 else 失败
		model.addAttribute("list", list);

	}
	/**
	 * 支付宝会员充值app端轮询
	 * @param id
	 * @param model
	 * @param cardId
	 */
	@RequestMapping(value = { "queryCardPaymement" }, produces = { "application/json" })
	public void queryCardPaymement(String id, Model model, String cardId) {
		List<CardPayment> list = getCardOne(id, cardId); //if  status==1 表示支付成功 else 失败
		model.addAttribute("list", list);

	}
	
	
	@Autowired
	PaymentTypeService paymentTypeService;
	@Autowired
	DinerBillPaymentService   dinerBillPaymentService;
	
	static Connection conn;
	static Statement st;

	/*
	 * List<DinerBillPayment> payment=new ArrayList<DinerBillPayment>();
	 * 
	 * @RequestMapping(value={"barPay"}, produces={"application/json"}) public
	 * void barPay( String out_trade_no,String auth_code ,String
	 * total_amount,String subject, Model model,String paymentType) throws
	 * JsonProcessingException { Threads dd=new Threads();
	 * System.out.println("线程开启"); Thread s=new Thread(dd); s.start();
	 * 
	 * }
	 */
	
	//会员充值
	/**** 
	 * @param cpt_id     支付方式id
	 * @param card_id    卡号id
	 * @param out_trade_no  商户号
	 * @param momey         交易金额
	 * @param auth_code 扫码枪扫描到的用户手机钱包中的付款条码
	 * @param subject  商品描述
	 * 
	 * 
	 **
	 * 【诺德兰内部系统集成】直接传入以下参数   微信参数
	 * @param auth_code 扫码枪扫描到的用户手机钱包中的付款条码
	 * @param total_fee 订单总金额，单位为分
	 * @param body 商品或支付单简要描述
	 * @param detail  商品名称明细列表 非必填
	 * @throws Exception 
		 */
	 
	@RequestMapping(value = { "savePaymement" }, produces = { "application/json" })
	public void savePaymement(String cpt_id,String card_id,String momey, String auth_code,String subject,
			String pay_type, String merchant_no,String appl_id,String payee_phone,Model model) throws Exception {
		
		merchant_no="tencrwin";
		appl_id="canyin";
		payee_phone="110";
		
		//充值支付方式中间表插入数据
		String strRandom =  card_id+TenpayUtil.buildRandom(4);
		int count=saveCardpayment(cpt_id,strRandom,card_id,momey);
		if(count>0){
			String type=paymentTypeService.getOne(cpt_id).getPaymentType();
			//调用支付宝支付功能
			if(type.equals("12")){   //支付宝
				String json=loadJSON(strRandom, auth_code, momey, subject, "ali_pay", merchant_no, appl_id, payee_phone);
		
//				ToAlipayBarTradePay toalipaybartradepay=new ToAlipayBarTradePay(); 
//				AlipayTradePayResponse res = toalipaybartradepay.barPay(strRandom, auth_code, momey, subject,cpt_id);
				if(json!=""){
					updateCardPayment("", "0",strRandom,card_id);
				}
				model.addAttribute("out_trade_no", strRandom);
			}else if(cpt_id.equals("1")){  //QQ支付
				TenPayController tenpay =new TenPayController();
				momey=Float.parseFloat(momey)*100+"";
				String json=loadJSON(strRandom, auth_code, momey, subject, "qq_pay", merchant_no, appl_id, payee_phone);
				DwzAjaxDone ajax=new DwzAjaxDone();
		    	ObjectMapper mapper = new ObjectMapper();
		    	Map<Object, String> productMap = mapper.readValue(json, Map.class);
		    	ajax.setStatusCode(productMap.get("retcode"));
		    	ajax.setValue(productMap.get("retmsg"));
		    	
				model.addAttribute("ajax", ajax);
			}else{
				model.addAttribute("cpt_id", "支付方式为空!");
			}
			
		}
		
		
	}
	
	
	/**
	 * 会员充值 微信支付
	 * @param cpt_id
	 * @param card_id
	 * @param momey
	 * @param auth_code
	 * @param subject
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = { "savePaymement2" }, produces = { "application/json" })
	public void savePaymement2(String cpt_id,String card_id,String momey, String auth_code,String subject,
			String pay_type, String merchant_no,String appl_id,String payee_phone,
			Model model,HttpServletResponse response) throws Exception {
		
		//充值支付方式中间表插入数据
		String strRandom =  card_id+TenpayUtil.buildRandom(4);
		strRandom=strRandom.substring(15, 35);  //微信只支持32位字符
		int count=saveCardpayment(cpt_id,strRandom,card_id,momey);
		if(count>0){
			  String type=paymentTypeService.getOne(cpt_id).getPaymentType();
			 if(type.equals("13")){  //微信支付
				
				int total_fee_int=0;
				if(momey!=null){
				 total_fee_int =(int)(Float.parseFloat(momey)*100);
				}
				String json=loadJSON(strRandom, auth_code, total_fee_int+"", subject, "weixin_pay", merchant_no, appl_id, payee_phone);
				/*BridgeForScanPayBusinessTest bridge = new BridgeForScanPayBusinessTest();
				
				*//**
				 * 【诺德兰内部集成】和【开发调试】时创建对象ScanPayReqData并将相关参数传入
				 *//*
				ScanPayReqData scanPayReqData = new ScanPayReqData(auth_code,subject, bridge.getAttach(),strRandom,total_fee_int,
						bridge.getDeviceInfo(), bridge.getSpBillCreateIP(),bridge.getTimeStart(),bridge.getTimeExpire(),bridge.getGoodsTag());
		        ScanPayBusiness.ResultListener resultListener=new ScanPayBusinessImpl(); 
				String scanPayResData =  WXPay.doScanPayBusiness(scanPayReqData, resultListener);
				System.out.println(scanPayResData);*/
				response.setCharacterEncoding("GBK");  
			    response.setContentType("application/json; charset=GBK");
				response.getWriter().write(json);
			
			}else{
				model.addAttribute("cpt_id", "支付方式为空!");
			}
			
		}
		
		
	}
	


	@Override
	public void run() {

		while (true) {
			List<Payment> list = getAll();
			List<CardPayment> cardList=getAllCardPayment();
//			System.out.println("会员充值："+cardList.size()+"---账单:"+list.size());
			AlipayClient alipayClient = AlipayAPIClientFactory
			.getAlipayClient();
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			for(int j =0; j<cardList.size(); j++){
				String biz_content = "{\"out_trade_no\":\""
					+ cardList.get(j).getOutTradeNo() + "\"}";
				request.setBizContent(biz_content);
				try {
					AlipayTradeQueryResponse response = alipayClient
					.execute(request);
					if (null != response && response.isSuccess()) {
						if (response.getCode().equals("10000")) {
							if("TRADE_SUCCESS".equalsIgnoreCase(response.getTradeStatus())){
								// 更新数据库状态 为1支付成功 
								updateCardPayment(cardList.get(j).getCardPayId(),"1","","");
							}
							//TRADE_CLOSED表示支付  未付款交易超时关闭 支付完成后全额退款
							if("TRADE_CLOSED".equalsIgnoreCase(response.getTradeStatus())){
								
								updateCardPayment(cardList.get(j).getCardPayId(),"2","","");
							}
							
						}
					}else if(response.getCode().equals("40004")){
						//交易不存在 
						updateCardPayment(cardList.get(j).getCardPayId(),"3","","");
					}
				} catch (AlipayApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for (int i = 0; i < list.size(); i++) {
				
				String biz_content = "{\"out_trade_no\":\""
						+ list.get(i).getDbpId() + "\"}";
				request.setBizContent(biz_content);
				try {
					AlipayTradeQueryResponse response = alipayClient
							.execute(request);
					if (null != response && response.isSuccess()) {
						if (response.getCode().equals("10000")) {
							if("TRADE_SUCCESS".equalsIgnoreCase(response.getTradeStatus())){
								// 更新数据库状态 为1支付成功 
								update(list.get(i).getDbpId(),"1");
							}
							//TRADE_CLOSED表示支付  未付款交易超时关闭 支付完成后全额退款
							if("TRADE_CLOSED".equalsIgnoreCase(response.getTradeStatus())){
								
								update(list.get(i).getDbpId(),"2");
							}
							
						}
					}else if(response.getCode().equals("40004")){
						//交易不存在 
						update(list.get(i).getDbpId(),"3");
					}
				} catch (AlipayApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(2000); //5秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void start() {
		RunBarPayController dd = new RunBarPayController();

		Thread s = new Thread(dd);
		s.start();
	}

	public static void main(String[] args) {
		String money="0.01";
		
		
		
		
		int ss=(int)(Float.parseFloat(money)*100);
		System.out.println(ss);
		RunBarPayController dd = new RunBarPayController();
		System.out.println("线程开启");
		

		Thread s = new Thread(dd);
		s.start();
//		dd.savePaymement("12","12","12",null);
//		List list=dd.getAllCardPayment();
		
	}

	public List<Payment> getAll() {
		conn = getConnection(); // 首先要获取连接，即连接到数据库
		List<Payment> list = new ArrayList<Payment>();

		try {
			
			//select * from cm_diner_bill_payment a where a.bill_status=0 and a.cpt_id='402881fa4fca616c014fca87c0d40001'
			String sql = " select DISTINCt a.*  from cm_diner_bill_payment a , cm_payment_type c where  a.bill_status=0  and  c.payment_type = '12' GROUP BY a.bill_id "; // 查询数据的sql语句

			st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			while (rs.next()) { // 判断是否还有下一个数据
				Payment d = new Payment();
				// 根据字段名获取相应的值
				d.setBillId(rs.getString("bill_id"));
				d.setDbpId(rs.getString("dbp_id"));

				// 输出查到的记录的各个字段的值
				list.add(d);

			}

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("查询数据失败222");
		}finally{
			close();
		}
		return list;
	}

	/**
	 * 
	 * @param id
	 *            賬單id
	 * @param paymentType
	 *            支付类型
	 * @return
	 * @throws SQLException 
	 */
	public List<Payment> getOne(String id, String paymentType)  {
		conn = getConnection(); // 首先要获取连接，即连接到数据库
		List<Payment> list = new ArrayList<Payment>();

		try {
			String sql = "select * from cm_diner_bill_payment a where a.bill_id='"
					+ id + "' and a.cpt_id='" + paymentType + "'"; // 查询数据的sql语句

			st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			while (rs.next()) { // 判断是否还有下一个数据
				Payment d = new Payment();
				// 根据字段名获取相应的值
				d.setBillId(rs.getString("bill_id"));
				d.setBillStatus(rs.getString("bill_status"));

				// 输出查到的记录的各个字段的值
				list.add(d);

			}

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("查询数据失败");
		}finally{
			close();
		}
		return list;
	}

	public void update(String dbpId,String billStatus)  {
		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "update cm_diner_bill_payment a set a.bill_status='"+billStatus+"' where a.dbp_id = '"
					+ dbpId + "'";// 更新数据的sql语句

			st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数

			

		} catch (SQLException e) {
			System.out.println("更新数据失败");
		}finally{
			close();
		}
	}
	

	public static Connection getConnection() {
		Connection con = null; // 创建用于连接数据库的Connection对象
		Properties pro = new Properties();
		try {
			pro.load(RunBarPayController.class.getResourceAsStream("/application.properties"));
			String driven=pro.getProperty("jdbc.driver");
			String url = pro.getProperty("jdbc.url");
			String user = pro.getProperty("jdbc.username");
			String password = pro.getProperty("jdbc.password");
			Class.forName(driven);// 加载Mysql数据驱动
			con = (Connection) DriverManager.getConnection(url,user, password);// 创建数据连接
		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return con; // 返回所建立的数据库连接
	}
	/**
	 * 关闭数据库连接
	 */
	public void close(){
		if(conn!=null){
			try {
				st.close();
				conn.close(); // 关闭数据库连接
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	
	//会员卡充值   begin
	
	/*   会员卡充值插入数据
	 * cpt_id     支付方式id
	 * card_id    卡号id
	 * out_trade_no  商户号
	 * momey         交易金额
	 */
	public int saveCardpayment(String cpt_id,String strRandom,String card_id,String momey)  {
		
		
		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "INSERT INTO cm_membersship_card_payment (cpt_id,card_id, out_trade_no,momey,stutas) " +
					"VALUES ('"+cpt_id+"', '"+card_id+"','"+strRandom+"','"+momey+"','-1')";// 插入数据的sql语句
			
//			System.out.println(sql);

			st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数
			
			return count;

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("更新数据失败");
			return 0;
		}finally{
			close();
		}
		
	}
	/**
	 * 修改会员卡充值状态          
	 * @param cardPayId
	 * @param billStatus
	 */
	public void updateCardPayment(String cardPayId,String billStatus,String out_trade_no,String card_id)  {
		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "update cm_membersship_card_payment a set a.stutas='"+billStatus+"' where a.card_pay_id = '"
					+ cardPayId + "'";// 更新数据的sql语句
			String sql2 = "update cm_membersship_card_payment a set a.stutas='"+billStatus+"' where a.out_trade_no = '"
			+ out_trade_no + "' and a.card_id ='"+card_id+"'";// 更新数据的sql语句

			st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量
			if(cardPayId.equals("")){
				int count = st.executeUpdate(sql2);// 执行更新操作的sql语句，返回更新数据的个数
			}else{
				int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数
			}
			

			

		} catch (SQLException e) {
			System.out.println("更新数据失败");
		}finally{
			close();
		}
	}
	
	/**
	 * 会员卡充值返回全部数据
	 * @return
	 */
	public List<CardPayment> getAllCardPayment() {
		conn = getConnection(); // 首先要获取连接，即连接到数据库
		List<CardPayment> list = new ArrayList<CardPayment>();

		try {
			
			//select * from cm_membersship_card_payment a where a.stutas=0 and a.cpt_id='402881fa4fca616c014fca87c0d40001'
			String sql = " select DISTINCt a.* from cm_membersship_card_payment a , cm_payment_type c where  a.stutas=0  and  c.payment_type = '12' "; // 查询数据的sql语句

			st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			while (rs.next()) { // 判断是否还有下一个数据
				CardPayment d = new CardPayment();
				// 根据字段名获取相应的值
				d.setCardPayId(rs.getString("card_pay_id"));
				d.setOutTradeNo(rs.getString("out_trade_no"));

				// 输出查到的记录的各个字段的值
				list.add(d);

			}

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("查询数据失败111");
		}finally{
			close();
		}
		return list;
	}
	
	/**
	 * 查询会员卡是否充值成功
	 * @param id
	 * @param paymentType
	 * @return
	 */
	public List<CardPayment> getCardOne(String id, String cardId)  {
		conn = getConnection(); // 首先要获取连接，即连接到数据库
		List<CardPayment> list = new ArrayList<CardPayment>();

		try {
			String sql = "select * from cm_membersship_card_payment a where a.out_trade_no='"
					+ id + "' and a.card_id = '"+cardId+"'"; // 查询数据的sql语句

			st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			while (rs.next()) { // 判断是否还有下一个数据
				CardPayment d = new CardPayment();
				// 根据字段名获取相应的值
				d.setCardPayId(rs.getString("card_pay_id"));
				d.setStutas(rs.getString("stutas"));

				// 输出查到的记录的各个字段的值
				list.add(d);

			}

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("查询数据失败222");
		}finally{
			close();
		}
		return list;
	}
	
	//会员卡充值  end
	
	
	

	   /**
	    * http请求
	    * @param out_trade_no   商品订单号
	    * @param auth_code      二维码
	    * @param total_amount   金额(元)   统一
	    * @param subject        商品名称
	    * @param pay_type       支付方式   （ali_pay：支付宝支付,qq_pay：qq钱包,weixin_pay：微信支付,baidu_pay：百度钱包）
	    * @param merchant_no     商户号
	    * @param appl_id        应用标识
	    * @param payee_phone    登录账号手机
	    * @return
	    */
	   
	   public  String loadJSON (String out_trade_no,String auth_code,String total_amount,String subject,String pay_type,String merchant_no,String appl_id,String payee_phone) {
	       StringBuilder json = new StringBuilder();
	       List<Restaurant> list=getMid(getCurrentUserRestId());
	       merchant_no=list.get(0).getMid();
//	       subject = "餐饮支付";
	       HttpURLConnection conn = null;
	       try {
//	    	   subject = URLEncoder.encode(subject, "utf-8");
	    	   String url=ThirdPartyPaymentUtil.getAli_pay() +
				"?out_trade_no="+out_trade_no+"&auth_code="+auth_code+"&total_amount="+total_amount+"&subject="+subject+"" +
						"&pay_type="+pay_type+"&merchant_no="+merchant_no+"&appl_id=canyin&payee_phone=110";
	           URL reqUrl = new URL(url);
	            conn = (HttpURLConnection) reqUrl.openConnection();
	            conn.setDoOutput(true);
//	           conn.setConnectTimeout(5000); //设置连接超时为5秒
	            
	           conn.setRequestMethod("POST"); //设定请求方式
	           /*conn.setRequestProperty("Content-type", "text/html");
	           conn.setRequestProperty("Accept-Charset", "utf-8");
	           conn.setRequestProperty("contentType", "utf-8");*/
	           
	           conn.connect(); //建立到远程对象的实际连接
	           BufferedReader in = new BufferedReader(new InputStreamReader(
	        		   conn.getInputStream(),"UTF-8"));
	           			String inputLine = null;
	           				while ( (inputLine = in.readLine()) != null) {
	           					json.append(inputLine);
	           				}
	         //判断是否正常响应数据 

	           if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	              System.out.println("网络错误异常！!!!");
	              json.append("网络错误异常") ;
	          }
	           
	           
	          /* URLConnection yc = oracle.openConnection();
	           yc.setRequestProperty("Charset", "utf-8");
	           BufferedReader in = new BufferedReader(new InputStreamReader(
	                                       yc.getInputStream(),"utf-8"));
	           String inputLine = null;
	           while ( (inputLine = in.readLine()) != null) {
	               json.append(inputLine);
	           }
	           in.close();*/
	       } catch (MalformedURLException e) {
	    	   e.printStackTrace();
	    	   
	       } catch (IOException e) {
	    	   e.printStackTrace();
	       }finally{
	    	   if(conn!=null){
	    		   conn.disconnect();
	    	   }
	       }
	       return json.toString();
	   }
	   
	   
	   public List<Restaurant> getMid(String restId)  {
			conn = getConnection(); // 首先要获取连接，即连接到数据库
			List<Restaurant> list = new ArrayList<Restaurant>();
			try {
				String sql = "select * from cm_restaurant a where a.rest_id='"+ restId + "'";// 查询数据的sql语句 

				st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

				ResultSet rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

				while (rs.next()) { // 判断是否还有下一个数据
					Restaurant d = new Restaurant();
					// 根据字段名获取相应的值
					d.setMid(rs.getString("mid"));

					// 输出查到的记录的各个字段的值
					list.add(d);
				}

			} catch (SQLException e) {
				System.out.println(e);
				System.out.println("查询数据失败");
			}finally{
				close();
			}
			return list;
		}

}

