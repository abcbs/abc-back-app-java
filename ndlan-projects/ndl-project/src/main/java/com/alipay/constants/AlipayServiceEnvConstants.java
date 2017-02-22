/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.constants;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49 taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

    /**支付宝公钥-从支付宝服务窗获取*/
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    /**签名编码-视支付宝服务窗要求*/
     public static final String SIGN_CHARSET      = "GBK";

    /**字符编码-传递给支付宝的数据编码*/
    public static final String CHARSET           = "GBK";

    /**签名类型-视支付宝服务窗要求*/
    public static final String SIGN_TYPE         = "RSA";

    /** 服务窗appId  */
    //TODO !!!! 注：该appId必须设为开发者自己的服务窗id  这里只是个测试id
    public static final String APP_ID            = "2015062500143678";
    
    public static final String PARTNER           = "2088021065353085";

    //开发者请使用openssl生成的密钥替换此处  请看文档：https://fuwu.alipay.com/platform/doc.htm#2-1接入指南
    //TODO !!!! 注：该私钥为测试账号私钥  开发者必须设置自己的私钥 , 否则会存在安全隐患 
    public static final String PRIVATE_KEY       = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANHg+DaktYo/8ftwZGLjeERXRTj9MDwqkiAczwqtwnVaYRB3XSBr1jtNWbOWns/YlQkt3F6g4j6433kBNJ7f+sTNV5435GB9RIXCcGHncySXLqKG/YqG/NcqhuZl508xfYnF25OZO3qFP66KqfHgrtejmg2jPXP5ZkcWR6ct4d6hAgMBAAECgYB1dmqU8pQj/uvopJuaeOLLpOg6IAIJ+m10CYua12r5EQ84DwYQ6impYP4uM8NIYlfvHjqyv2pAXmm8KH+R15tZ21ds1B1ZZMCABiaT6FVkvJf7g/lmSpB9ll0JVkeCaZMaHL57y+Pkcug6A9yF9OOMr+gFNQ9CCunhFENqNh98DQJBAP5543eHcUBfuWK51+RXHEdb0miGNzsn5MhFaRqNRVvuusa1/mBPp4zPKK+tmT4vkfetC9uCoxRIAqiyi93w1fsCQQDTIrahOCnCkLfdGFP8FD4t8Kud3rXeFpEVgx6Wc67iVzSQII+WWb3NmhCyiTM/9RwJLjjq3j46KeNe2/ranmcTAkBZuxcXPb4UaeyPwGI9FO5WxyzxSGpPRKjqaXc/MpNDcHGRwfi5czzsbNu23qrLf7CjufQVagIhE6dEXvjswJBjAkEAxteFehYjthqHHpFf3yLNYISti7OIQk2se78N2pG+XWnVlfskX2vLBkwXd50hCBX2PmsqrX96yVpTyS2Rdb0CwwJBAI15kBrhE1mPfyYVWpZMvmtunWIrCuJ3Qvg++6W0KtrprExGh8/tW3O1jxFI7ZTIcU/ecjfaJ5TjB6rlhVJI7IM=";

    //TODO !!!! 注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
    public static final String PUBLIC_KEY        = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDR4Pg2pLWKP/H7cGRi43hEV0U4/TA8KpIgHM8KrcJ1WmEQd10ga9Y7TVmzlp7P2JUJLdxeoOI+uN95ATSe3/rEzVeeN+RgfUSFwnBh53Mkly6ihv2KhvzXKobmZedPMX2JxduTmTt6hT+uiqnx4K7Xo5oNoz1z+WZHFkenLeHeoQIDAQAB";

    /**支付宝网关*/
     public static final String ALIPAY_GATEWAY    = "https://openapi.alipay.com/gateway.do";
    //public static final String ALIPAY_GATEWAY    = "http://superapi-d14186.dl.alipaydev.com/gateway.do";
     // public static final String ALIPAY_GATEWAY    = "http://openapi.alipaydev.com/gateway.do";

    /**授权访问令牌的授权类型*/
    public static final String GRANT_TYPE        = "authorization_code";
}