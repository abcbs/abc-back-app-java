/**
 * 
 */
package com.ndlan.canyin.frontdesk.front.controller3c.productinfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.Code;
import com.ndlan.canyin.frontdesk.dto3c.IosReturnJson;
import com.ndlan.canyin.frontdesk.service.qtsy.PaymentTypeService;

/**
 * 支付方式
 * @Description:
 * @author zhangts
 * @date: 2016-1-11 下午02:42:03
 */
@Controller
@RequestMapping({"/paymentType"})
@Lazy
public class PaymentTypeController  extends BaseManageController{
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentTypeController.class.getName());
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	/**
	 * 获取当前店支付类型
	 * @param restId
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public IosReturnJson getList(String restId){
		logger.info("获得支付类型,参数信息为: restId:"+restId );
		if(restId ==null || restId.equals("")){
			logger.info("商店id为空!");
        	restId = getCurrentUserRestId();
        	restId="72e12515-f54f-11e4-af9a-02004c4f4f50";  //本行测试代码，完毕删除
        }
		List<PaymentType> list=paymentTypeService.findPaymentTypeByRestIDAndPayStatus(restId);
		IosReturnJson json=new IosReturnJson();
		if(list.size()>0){
			json.setStatusCode(Code.SUCCESS.getCode());
			json.setMessage(Code.SUCCESS.getDesc());
		}else{
			json.setStatusCode(Code.FAILE.getCode());
			json.setMessage(Code.FAILE.getDesc());
			logger.info("获得支付类型错误,或当前店没有填写支付类型数据!" );
		}
		
		json.setObjectzJson(list);

		return json;
		
	}
	

	public PaymentTypeService getPaymentTypeService() {
		return paymentTypeService;
	}

	public void setPaymentTypeService(PaymentTypeService paymentTypeService) {
		this.paymentTypeService = paymentTypeService;
	}
	
	
	
	

}
