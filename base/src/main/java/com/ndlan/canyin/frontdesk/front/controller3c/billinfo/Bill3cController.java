/**
 * 
 */
package com.ndlan.canyin.frontdesk.front.controller3c.billinfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
















import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ndlan.canyin.base.entity.base3c.billinfo.BillEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillItemEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillReturnItemEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.ReturnReasonEntity;
import com.ndlan.canyin.base.entity.base3c.productinfo.PayItem;
import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartEntity;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.frontdesk.common.Arith;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.Code;
import com.ndlan.canyin.frontdesk.dto3c.IosReturnJson;
import com.ndlan.canyin.frontdesk.dto3c.SearchPageDto;
import com.ndlan.canyin.frontdesk.dto3c.SubCode;
import com.ndlan.canyin.frontdesk.dto3c.billinfo.BillItemDto;
import com.ndlan.canyin.frontdesk.dto3c.billinfo.ReturnReasonDto;
import com.ndlan.canyin.frontdesk.service.service3c.billinfo.Bill3cService;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月7日 下午3:41:09  
 */
@Controller
@RequestMapping({"/order"})
public class Bill3cController extends BaseManageController{
	private static final Logger logger = LoggerFactory.getLogger(Bill3cController.class.getName());
	@Autowired
	private Bill3cService billservice;
	//交易记录查询(按天数、订单名称或订单号查询)
	@RequestMapping({"/getList"})
	   public IosReturnJson getList(@RequestParam(value="isWeek", defaultValue="0") String isWeek,@RequestParam(value="shopId", defaultValue="") String restId,
			   @RequestParam(value = "page", defaultValue = "1") String pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
			   @RequestParam(value = "searchValue", defaultValue = "") String searchValue,@RequestParam(value = "pageUpOrDown", defaultValue = "down") String pageUpOrDown,
			   Model model, ServletRequest request)
 {
		logger.info("开始查询交易记录，restId={},searchValue={}",restId,searchValue);
		IosReturnJson json=new IosReturnJson();
		if(StringUtils.isBlank(restId)){
			restId=getCurrentUserRestId();
		}
		List list = this.billservice.getBillList(isWeek,restId,pageNumber,pageSize,searchValue);
		if(list.size()==0){
			logger.info("查询交易记录时，此搜索范围内的交易表无数据");
			json=new IosReturnJson(SubCode.NODATA.getCode(),SubCode.NODATA.getDesc());
			return json;
		}
		json.setObjectzJson(list);
		SearchPageDto sp=this.billservice.getSearchPageDto(pageNumber,pageSize,searchValue,pageUpOrDown);
		json.setSearchPage(sp);
		return json;
	}
	//交易详细列表查询
	@RequestMapping({"/getItemList"})
	   public IosReturnJson getItemList(
			   @RequestParam(value="bId", defaultValue="") String bId,@RequestParam(value="shopId", defaultValue="") String restId,
			   @RequestParam(value = "page", defaultValue = "1") String pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
			   Model model, ServletRequest request)
{
		logger.info("开始查询交易明细，bId={},shopId={}",bId,restId);
		IosReturnJson json = new IosReturnJson();
		if(StringUtils.isBlank(bId)){
			logger.info("交易单主键不能为空");
			json = new IosReturnJson(SubCode.DATAISNULL.getCode(),SubCode.DATAISNULL.getDesc());
			return json;
		}
		if(StringUtils.isBlank(restId)){
			restId=getCurrentUserRestId();
		}
		List list=this.billservice.getBillItemListByDto(bId, restId);
		if(list==null||list.size()==0){
			logger.info("不能查出与此bId={}相关的订单明细",bId);
			json=new IosReturnJson(SubCode.DATAERROR.getCode(),SubCode.DATAERROR.getDesc());
		}
			json.setObjectzJson(list);
		
		return json;
	}
	//结算
	@RequestMapping({ "/add" })
	public IosReturnJson add(
			@RequestParam(value = "cartId", defaultValue = "") String cartId,
			@RequestParam(value = "shopId", defaultValue = "") String shopId,
			Model model, ServletRequest request) {
		logger.info("开始进行购物车结算，cartId={},shopId={}",cartId,shopId);
		IosReturnJson json = new IosReturnJson();
		if (StringUtils.isBlank(shopId)) {
			shopId = getCurrentUserRestId();
		}
		if (StringUtils.isBlank(cartId)) {
			logger.info("cartId不能为空");
			json = new IosReturnJson(SubCode.DATAISNULL.getCode(),
					SubCode.DATAISNULL.getDesc());
			return json;
		}
		List<CartItemEntity> cartItemList=this.billservice.getCartItemOne(cartId);
		int size=cartItemList.size();
		int change = 0;
		for(int i=0;i<size;i++){
			Product pro=this.billservice.getProductOne(cartItemList.get(i).getProId());
			change=Integer.parseInt(Arith.sub(pro.getQuantity(), cartItemList.get(i).getQuantity()));
			logger.info("该商品库存量满足订单中数量,除去该订单外该种商品（proId={}）剩余数量为{}",pro.getProId(),change);
			if(change<0){
				//该商品库存量不足订单中数量
				logger.info("该商品库存量不足订单中数量,差额为{}",change);
				json=new IosReturnJson(Code.FAILE.getCode(),"该商品库存量不足订单中数量,差额为"+change+","+Code.FAILE.getDesc());
				return json;
			}
		}
		//更改商品库存
		String proMsg=this.billservice.updateProductQuantity(cartId,shopId);
		if(proMsg.equals("fail")){
			logger.info("更新商品库存时失败，原因是：无与此cartId={}相关的购物车明细信息",cartId);
			json=new IosReturnJson(SubCode.NODATA.getCode(), "可用的购物列表"+ SubCode.NODATA.getDesc());
			return json;
		}
		//保存订单头信息
		BillEntity billRtn = this.billservice.insertBillByCartId(shopId, cartId);
		if (billRtn == null) {
			logger.info("保存订单头信息时失败，无与此cartId={}相关的购物车头信息",cartId);
			json = new IosReturnJson(SubCode.NODATA.getCode(), "购物车"+ SubCode.NODATA.getDesc());
			return json;
		}
		//保存订单详细信息
		String msg = this.billservice.insertBillItemByCartId(shopId, cartId,billRtn);
		if (msg.equals("fail")) {
			logger.info("保存订单详细信息时失败，原因是：无与此cartId={}相关的购物车明细信息",cartId);
			json = new IosReturnJson(SubCode.NODATA.getCode(), "可用的购物列表"+ SubCode.NODATA.getDesc());
			return json;
		}
		//更新购物车明细信息
		String cartItemMsg=this.billservice.updateCartItemStatus(cartId,"3");
		if(cartItemMsg.equals("fail")){
			logger.info("更新购物车明细信息时失败，原因是：无与此cartId={}相关的购物车明细信息",cartId);
			json = new IosReturnJson(SubCode.NODATA.getCode(), "可用的购物列表"+ SubCode.NODATA.getDesc());
			return json;
		}
		return json;
	}
	//支付中成功或失败
	@RequestMapping({"/paying"})
	   public IosReturnJson paying( 
			   @RequestParam(value = "payStatus", defaultValue = "") String payStatus,
			   @RequestParam(value = "bId", defaultValue = "") String bId,
			   @RequestParam(value = "payType", defaultValue = "") String payType, 
			   @RequestParam(value = "amountPaid", defaultValue = "") String amountPaid,
			   @RequestParam(value = "shopId", defaultValue = "") String restId,
			   @RequestParam(value = "pNo", defaultValue = "") String pNo,
			   @RequestParam(value = "currency", defaultValue = "CNY") String currency,
			   Model model, ServletRequest request)
	   {
			logger.info("支付中，payStatus={},bId={},payType={},amountType={}",payStatus,bId,payType,amountPaid);
			IosReturnJson json=new IosReturnJson();
			if(StringUtils.isBlank(payStatus)){
				payStatus="1";//支付中
			}
			if(StringUtils.isBlank(bId)){
				logger.info("订单主键bId不能为空");
				json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"bId"+SubCode.DATAISNULL.getDesc());
				return json;
			}
			if(StringUtils.isBlank(payType)){
				logger.info("支付方式payType不能为空");
				json=new IosReturnJson(SubCode.NODATA.getCode(),"payType"+SubCode.NODATA.getDesc());
				return json;
			}
			if(StringUtils.isBlank(amountPaid)){
				logger.info("实收金额amountPaid不能为空");
				json=new IosReturnJson(SubCode.NODATA.getCode(),"amountPaid"+SubCode.NODATA.getDesc());
				return json;
			}
			if(StringUtils.isBlank(pNo)){
				pNo=this.billservice.getBillNo();
			}
			if(StringUtils.isBlank(restId)){
				restId=getCurrentUserRestId();
			}
			//更新订单头
			String msg=this.billservice.updateBill(bId,amountPaid,payType,restId,payStatus);
			if(msg.equals("fail")){
				logger.info("无与此bId={}相关的订单头信息",bId);
				json=new IosReturnJson(SubCode.DATAERROR.getCode(),SubCode.DATAERROR.getDesc());
				return json;
			}
			//更新订单明细
			/*try {
				String msg1 = this.billservice.updateBillItem(bId, restId);
				if (msg1.equals("fail")) {
					logger.info("无与此bId={}相关的订单明细信息", bId);
					json = new IosReturnJson(SubCode.NODATA.getCode(),
							SubCode.NODATA.getDesc());
					return json;
				}
			} catch (Exception e) {
				logger.error("更新订单明细时出错，原因"+e.getLocalizedMessage(), e);
				json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}*/
			//创建支付明细（无论支付成功失败）
			try{
				String payMsg=this.billservice.insertPayItem(bId,amountPaid,currency,payStatus,payType,pNo,restId);
			}catch(Exception e){
				logger.error("支付后建立支付明细表时出错"+ e.getLocalizedMessage(), e);
				json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}
			return json;
	   }
	//更改订单状态
	@RequestMapping({"/updateStatus"})
	   public IosReturnJson updateStatus( 
			   @RequestParam(value = "payStatus", defaultValue = "") String payStatus,
			   @RequestParam(value = "bId", defaultValue = "") String bId,
			   @RequestParam(value = "shopId", defaultValue = "") String restId,
			   Model model, ServletRequest request)
	   {
			logger.info("开始进行支付后的状态更新，payStatus={},bId={}",payStatus,bId);
			IosReturnJson json=new IosReturnJson();
			if(!payStatus.equals("2")&&!payStatus.equals("3")){
				logger.info("支付后状态更新时出错，输入的支付状态不适合请求此方法");
				json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}
			if(StringUtils.isBlank(bId)){
				logger.info("bId不能为空");
				json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"bId"+SubCode.DATAISNULL.getDesc());
				return json;
			}
			
			if(StringUtils.isBlank(restId)){
				restId=getCurrentUserRestId();
			}
			//更新订单头
			String msg=this.billservice.updateBill(bId,"","",restId,payStatus);
			if(msg.equals("fail")){
				logger.info("无与此bId={}相关的订单头信息",bId);
				json=new IosReturnJson(SubCode.DATAERROR.getCode(),SubCode.DATAERROR.getDesc());
				return json;
			}
			//更新订单明细
			/*try {
				String msg1 = this.billservice.updateBillItem(bId, restId);
				if (msg1.equals("fail")) {
					logger.info("无与此bId={}相关的订单明细信息", bId);
					json = new IosReturnJson(SubCode.NODATA.getCode(),
							SubCode.NODATA.getDesc());
					return json;
				}
			} catch (Exception e) {
				logger.error("更新订单明细时出错，原因"+e.getLocalizedMessage(), e);
				json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}*/
			//更新支付明细
			try{
				String payMsg=this.billservice.updatePayItem(bId,payStatus,restId);
			}catch(Exception e){
				logger.error("支付后更新支付明细表时出错"+ e.getLocalizedMessage(), e);
				json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}
			//更新购物车行支付状态
			String cartId=this.billservice.getOne(bId).getCartId();
			if(StringUtils.isBlank(cartId)){
				logger.error("支付后更新购物车行支付状态前出错，无与此bId={}相关的cartId",bId);
				json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}
			String cartItemMsg=this.billservice.updateCartItemPayStatus(cartId,payStatus);
			if(cartItemMsg.equals("fail")){
				logger.error("支付后更新购物车行支付状态时出错，无与此cartId={}相关的明细信息",cartId);
				json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}
			return json;
	   }
	//退货
	@RequestMapping({"/returnItem"})
	   public IosReturnJson returnItem( @RequestParam(value = "bdId", defaultValue = "") String bdId,
			   @RequestParam(value = "returnReason", defaultValue = "") String returnReason, 
			   @RequestParam(value = "returnTotal", defaultValue = "") String returnTotal,
			   @RequestParam(value = "returnType", defaultValue = "") String returnType,
			   @RequestParam(value = "returnQuantity", defaultValue = "") String returnQuantity,
			   @RequestParam(value = "shopId", defaultValue = "") String restId,
			   Model model, ServletRequest request)
	   {
			logger.info("开始进行退货，bdId={},returnReason={},returnTotal={},returnType={},returnQuantity={}",bdId,returnReason,returnTotal,returnType,returnQuantity);
			IosReturnJson json=new IosReturnJson();
			if(StringUtils.isBlank(restId)){
				restId=getCurrentUserRestId();
			}
			if(StringUtils.isBlank(bdId)){
				logger.info("bdId不能为空");
				json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"bdId"+SubCode.DATAISNULL.getDesc());
				return json;
			}
			BillItemEntity billItem=this.billservice.getBillItemOne(bdId);
			if(billItem==null){
				logger.info("无与此bId={}相关的订单明细信息",bdId);
				json=new IosReturnJson(SubCode.DATAERROR.getCode(),"bdId"+SubCode.DATAERROR.getDesc());
				return json;
			}
			if(billItem.getPayStatus().equals("3")){
				logger.info("该条记录已经为退货，不可重复退货");
				json=new IosReturnJson(Code.FAILE.getCode(),"该条记录已经为退货，不可重复退货，"+Code.FAILE.getDesc());
				return json;
			}
			if(Integer.parseInt(billItem.getQuantity())<Integer.parseInt(returnQuantity)){
				logger.info("退货数量超出交易数量，交易数量为{}",billItem.getQuantity());
				json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}
			//创建退货明细
			this.billservice.insertBillReturnItem(billItem,restId,returnReason,returnTotal,returnType,returnQuantity);
			//更新订单头
			String msg=this.billservice.updateBillAfterReturn(billItem,returnTotal);
			if(msg.equals("fail")){
				logger.info("无与此bdId={}相关的订单头信息",bdId);
				json=new IosReturnJson(SubCode.DATAERROR.getCode(),SubCode.DATAERROR.getDesc());
				return json;
			}
			//更新订单明细
			this.billservice.updateBillItemAfterReturn(billItem,returnTotal,returnQuantity);
			//更新购物车明细
			String msg2=this.billservice.updateCartItem(billItem.getCartItemId(),returnQuantity,returnTotal);
			
			return json;
	   }
	
	//退货原因list
		@RequestMapping({"/getReturnReasonList"})
		   public IosReturnJson getReturnReasonList(@RequestParam(value="shopId", defaultValue="") String restId,
				   @RequestParam(value = "bdId", defaultValue = "") String bdId, 
				   @RequestParam(value = "returnQuantity", defaultValue = "") String returnQuantity, 
				   Model model, ServletRequest request)
	 {
			logger.info("开始获取退货原因list");
			IosReturnJson json=new IosReturnJson();
			if(StringUtils.isBlank(bdId)){
				logger.info("订单明细主键bdId不能为空");
				json=new IosReturnJson(SubCode.DATAISNULL.getCode(),"bdId"+SubCode.DATAISNULL.getDesc());
				return json;
			}
			if(StringUtils.isBlank(restId)){
				restId=getCurrentUserRestId();
			}
			Map map=this.billservice.getReturnReasonMap(bdId,returnQuantity,restId);
			if(map.size()<1){
				logger.info("获取退货原因是出错，原因可能是无与此订单明细主键bdId={}相关的信息",bdId);
				json=new IosReturnJson(SubCode.DATAERROR.getCode(),SubCode.DATAERROR.getDesc());
				return json;
			}
			json.setObjectzJson(map);
			return json;
		}
		
}
