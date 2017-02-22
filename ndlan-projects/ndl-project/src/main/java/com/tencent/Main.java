package com.tencent;

import com.tencent.business.BridgeForScanPayBusinessTest;
import com.tencent.business.ScanPayBusiness;
import com.tencent.business.ScanPayBusinessImpl;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.protocol.refund_protocol.RefundReqData;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        try {
        	WXPay.initSDKConfiguration(
                    //签名算法需要用到的秘钥
                    "dfa1bbeacb77f4a79ffac2a8b37a8c49",
                    //公众账号ID，成功申请公众账号后获得
                    "wx7ea7e400019e16df",
                    //商户ID，成功申请微信支付功能之后通过官方发出的邮件获得
                    "1263472201",
                    //子商户ID，受理模式下必填；
                    "",
                    //HTTP证书在服务器中的路径，用来加载证书用E:/微信支付证书/apiclient_cert.p12
                    "E:/微信支付证书/apiclient_cert.p12",
                    //C:/Users/zgn/Desktop/apiclient_cert.p12
                    //HTTP证书的密码，默认等于MCHID
                    "1263472201"
            );
       	 BridgeForScanPayBusinessTest bridge = new BridgeForScanPayBusinessTest();
            //2）从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
            ScanPayReqData scanPayReqData = new ScanPayReqData(
                    //这个是扫码终端设备从用户手机上扫取到的支付授权号，有效期是1分钟
                    "130418033057150910",
                    //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
                    bridge.getBody(),
                    //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
                    bridge.getAttach(),
                    //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
                    bridge.getOutTradeNo(),
                    //订单总金额，单位为“分”，只能整数
                    bridge.getTotalFee(),
                    //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
                    bridge.getDeviceInfo(),
                    //订单生成的机器IP
                    bridge.getSpBillCreateIP(),
                    //订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
                    bridge.getTimeStart(),
                    //订单失效时间，格式同上
                    bridge.getTimeExpire(),
                    //商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
                    bridge.getGoodsTag()
            );
            ScanPayBusiness.ResultListener resultListener=new ScanPayBusinessImpl(); 
            //--------------------------------------------------------------------
            //-----------支付---------------------------------------------------------
//            WXPay.doScanPayBusiness(scanPayReqData, resultListener);

            //--------------------------------------------------------------------
            //------------查询--------------------------------------------------------
//            ScanPayQueryReqData scanPayQueryReqData=new ScanPayQueryReqData("1003280238201509060803942645", null);
//            String str= WXPay.requestScanPayQueryService(scanPayQueryReqData);
//            System.out.println(str);
            //--------------------------------------------------------------------
            //-------------退款-------------------------------------------------------
            RefundReqData refundReqData=new RefundReqData("1003280238201509070808881674", bridge.getOutTradeNo(), bridge.getDeviceInfo(), "1000001", 1, 1, bridge.getUserIp(), null);
            String str1=WXPay.requestRefundService(refundReqData);
            System.out.println(str1);
            //--------------------------------------------------------------------
            //-------------退款-------------------------------------------------------

            //本地通过xml进行API数据模拟的时候，先按需手动修改xml各个节点的值，然后通过以下方法对这个新的xml数据进行签名得到一串合法的签名，最后把这串签名放到这个xml里面的sign字段里，这样进行模拟的时候就可以通过签名验证了
           // Util.log(Signature.getSignFromResponseString(Util.getLocalXMLString("/test/com/tencent/business/refundqueryserviceresponsedata/refundquerysuccess2.xml")));

//            Util.log(new Date().getTime());
           // Util.log(System.currentTimeMillis());

        } catch (Exception e){
        	e.printStackTrace();
            Util.log(e.getMessage());
        }

    }

}
