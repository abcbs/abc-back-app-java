/**
 * 
 */
package com.ndlan.canyin.frontdesk.service.service3c.billinfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.base3c.billinfo.BillEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillItemEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillReturnItemEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.ReturnReasonEntity;
import com.ndlan.canyin.base.entity.base3c.productinfo.PayItem;
import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartEntity;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.base.repository.dao3c.billinfo.BillDao;
import com.ndlan.canyin.base.repository.mybatis.EmployeeMyDao;
import com.ndlan.canyin.base.repository.mybatis.OrderMyDao;
import com.ndlan.canyin.frontdesk.common.Arith;
import com.ndlan.canyin.frontdesk.dto3c.IosReturnJson;
import com.ndlan.canyin.frontdesk.dto3c.SearchPageDto;
import com.ndlan.canyin.frontdesk.dto3c.SubCode;
import com.ndlan.canyin.frontdesk.dto3c.billinfo.BillItemDto;
import com.ndlan.canyin.frontdesk.dto3c.billinfo.ReturnReasonDto;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.service3c.productinfo.PayItemService;
import com.ndlan.canyin.frontdesk.service.service3c.productinfo.ProductService;
import com.ndlan.canyin.frontdesk.service.service3c.shoppingcart.CartItemService;
import com.ndlan.canyin.frontdesk.service.service3c.shoppingcart.CartService;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月7日 下午3:33:53  
 */
@Service
@Transactional
public class Bill3cService  extends BaseService<BillDao,BillEntity>{
private BillDao billDao;
@Autowired
private OrderMyDao orderMyDao;
@Autowired
private PayItemService payItemService;
@Autowired
private ProductService productService;
@Autowired
private BillItemService billItemService;
@Autowired
private BillReturnItemService billReturnItemService;
@Autowired
private ReturnReasonService returnReasonService;
@Autowired
private CartService cartService;
@Autowired
private CartItemService cartItemService;
@Autowired
public void setBillDao(BillDao billDao) {
	super.setDao(billDao);
	this.billDao = billDao;
}


//订单查询
public List getBillList(String isWeek,String restId, String pageNumber,String pageSize, String searchValue) {
	String startDate;
	String endDate;
	if(StringUtils.isBlank(searchValue)){
		searchValue=null;
	}else{
		searchValue="%"+searchValue+"%";
	}
	SimpleDateFormat dateGS=new SimpleDateFormat("yyyy-MM-dd");
	Calendar rightNow = Calendar.getInstance();
	
	if(isWeek.equals("1")){
		endDate=dateGS.format(rightNow.getTime());
		rightNow.add(Calendar.DAY_OF_YEAR,-7);//前一周日期
		startDate=dateGS.format(rightNow.getTime());
	}else{
		startDate=dateGS.format(rightNow.getTime());
		endDate=startDate;
	}
	String offset=Arith.mul(pageSize, Arith.sub(pageNumber, "1"));
	return this.orderMyDao.getBillList(restId,startDate,endDate,offset,pageSize,searchValue);
}

//订单明细查询
public List<BillItemDto> getBillItemListDto(String bId, String restId) {
	return this.orderMyDao.getBillItemList(bId,restId);
}


public List<BillReturnItemEntity> getBillReturnListByBId(String bId,
		String restId) {
	return this.billReturnItemService.getBillReturnItemDao().getBillReturnListByBId(bId,
			restId);
}

public List<BillReturnItemEntity> getBillReturnItemByBdId(String bdId,
		String restId) {
	return this.billReturnItemService.getBillReturnItemByBdId(bdId, restId);
}
public CartEntity getCartOne(String cartId) {
	return this.cartService.getOne(cartId);
}
public BillItemEntity getBillItemOne(String bdId) {
	return this.billItemService.getOne(bdId);
}
public BillEntity insertBillByCartId(String shopId,String cartId){
	CartEntity cart=getCartOne(cartId);
	   if(cart==null){
		   return null;
	   }
	   //save订单头信息
	   BillEntity bill=new BillEntity();
	   bill.setCartId(cartId);
	   bill.setbNo(getBillNo());
	   bill.setbName("订单"+UUID.randomUUID().toString());
	   bill.setMbId(cart.getMbId());
	   bill.setCustomerName(cart.getCustomerName());
	   bill.setbAmount(cart.getTotal());
	   bill.setAllDiscount(cart.getAllDiscount());
	   bill.setAllPrivilege(cart.getAllPrivilege());
	   bill.setCodelessSum(cart.getCodelessSum());
	   bill.setRestId(shopId);
	   bill.setOrderPrivilege(Arith.sub(cart.getBeforDiscountTotal(), cart.getTotal()));//原单优惠
	   bill.setStatus("0");
	   bill.setPayStatus("0");//未结账
	   bill.setCreateEmployee(cart.getCreateEmployee());
	   bill.setCreateTime(cart.getCreateTime());
	   BillEntity rtnBill=this.billDao.save(bill);
	return rtnBill;
}

	//保存订单明细
	public String insertBillItemByCartId(String shopId, String cartId,
			BillEntity billRtn) {
		String msg=this.billItemService.insertBillItemByCartId(shopId, cartId,billRtn);
		return msg;
	}
	/**
	 * 订单明细查询，返回dto
	 * @param bId
	 * @param restId
	 * @return
	 */
	public List getBillItemListByDto(String bId,String restId){
		List<BillItemDto> list=getBillItemListDto(bId,restId);
		int size=list.size();
		if(size==0){
			return null;
		}
			for(int i=0;i<size;i++){
				//通过bdId查询退货表
				List<BillReturnItemEntity> billReturnList =getBillReturnItemByBdId(list.get(i).getBdId(), restId);
				if(billReturnList.size()>0){
					list.get(i).setBdStatus("1");//退款状态
					list.get(i).setReturnTotal(billReturnList.get(i).getReturnTotal());
				}else{
					list.get(i).setBdStatus("0");//正常状态
				}
			}
		return list;
	}
	/**
	 * 后更新订单头
	 * @return
	 */
	public String updateBill(String bId, String amountPaid, String payType,String restId,String payStatus) {
		BillEntity bill=getOne(bId);
		if(bill==null){
			return "fail";
		}
		//更新订单头
		bill.setPayStatus(payStatus);
		if(payStatus.equals("2")){//支付失败
			bill.setStatus("1");//失败
		}else if(payStatus.equals("3")){//成功
			bill.setStatus("2");//成功
		}else if(payStatus.equals("1")){//支付中
			bill.setAmountPaid(amountPaid);
			bill.setPayType(payType);
		}
		save(bill);	
		return "success";
	}
	/**
	 * 支付完成后更新订单明细
	 * @param bId
	 * @param restId
	 * @return
	 */
	public String updateBillItem(String bId, String restId) {
		String msg=this.billItemService.updateBillItem(bId,restId);
		return msg;
	}
	/**
	 * @param bId
	 * @param amountPaid
	 * @param currency
	 * @param payStatus
	 * @param payType
	 * @param pNo
	 * @param restId
	 * @return
	 */
	public String insertPayItem(String bId, String amountPaid, String currency,
			String payStatus, String payType, String pNo, String restId) {
		String msg=this.payItemService.insertPayItem(bId,amountPaid,currency,payStatus,payType,pNo,restId);
		return msg;
	}
	/**
	 * @param 负数则为0
	 * @return
	 */
	public String getPositiveNumber(String num) {
		if(num.substring(0, 1).equals("-")){
			num="0";
		}
		return num;
	}
	/**
	 * 创建退货明细
	 * @param billItem
	 * @param restId
	 * @param returnReason
	 * @param returnTotal
	 * @param returnType
	 * @param returnQuantity
	 */
	public String insertBillReturnItem(BillItemEntity billItem, String restId,
			String returnReason, String returnTotal, String returnType,
			String returnQuantity) {
		String msg=this.billReturnItemService.insertBillReturnItem(billItem,restId,returnReason,returnTotal,returnType,returnQuantity);
		return msg;
	}
	/**
	 * 退货后更新订单头
	 * @param billItem
	 * @return 
	 */
	public String updateBillAfterReturn(BillItemEntity billItem,String returnTotal) {
		BillEntity bill=getOne(billItem.getbId());
		if(bill==null){
			return "fail";
		}
		bill.setStatus("4");
		bill.setbAmount(getPositiveNumber(Arith.sub(bill.getbAmount(), billItem.getPrice())));//金额
		bill.setAmountPaid(getPositiveNumber(Arith.sub(bill.getAmountPaid(), returnTotal)));//实收金额
		save(bill);
		return "success";
	}
	/**
	 * 退货后更新订单明细
	 * @param billItem
	 * @param returnTotal
	 */
	public String updateBillItemAfterReturn(BillItemEntity billItem,
			String returnTotal,String returnQuantity) {
		String msg=this.billItemService.updateBillItemAfterReturn(billItem,returnTotal,returnQuantity);
		return msg;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @param searchValue
	 * @param pageUpOrDown
	 * @return
	 */
	public SearchPageDto getSearchPageDto(String pageNumber, String pageSize,
			String searchValue, String pageUpOrDown) {
		SearchPageDto sp=new SearchPageDto();
		sp.setPage(pageNumber);
		sp.setPageSize(pageSize);
		sp.setSearchValue(searchValue);
		sp.setPageUpOrDown(pageUpOrDown);
		return sp;
	}


	/**
	 * 更新购物车明细
	 * @param cartId
	 * @param payStatus 
	 * @return
	 */
	public String updateCartItemStatus(String cartId, String payStatus) {
		String msg = this.cartItemService.updateItemStatus(cartId);
		return msg;
	}

	/**
	 * 更新购物车明细
	 * @param bdId
	 * @param returnQuantity
	 * @param returnTotal
	 * @return
	 */
	public String updateCartItem(String cartItemId, String returnQuantity,
			String returnTotal) {
		return this.cartItemService.updateCartItem(cartItemId,returnQuantity,returnTotal);
	}

	public int countProNum(String bId,String restId){
		List<BillItemEntity> billItemList=this.billItemService.getBillItemList(bId, restId);
		int size=billItemList.size();
		int num=0;
		for(int i=0;i<size;i++){
			String quantity=billItemList.get(i).getQuantity();
			num+=Integer.parseInt(quantity);
		}
		return num;
	}

	/**
	 * 获取默认退货金额
	 * @param bill
	 * @param restId
	 * @return
	 */
	public String getReturnTotal(BillEntity bill, String restId,String returnQuantity) {
		String privilege=bill.getOrderPrivilege();
		String num=String.valueOf(countProNum(bill.getbId(), restId));
		String total=Arith.mul(Arith.div(privilege, num, 2), returnQuantity);
		return total;
	}

	/**
	 * 获取购物车明细列表
	 * @param cartId
	 * @return
	 */
	public List<CartItemEntity> getCartItemOne(String cartId) {
		return this.cartItemService.findAllByCartId(cartId);
	}

	/**
	 * 获取product
	 * @param proId
	 * @return
	 */
	public Product getProductOne(String proId) {
		return this.productService.getOne(proId);
	}

	/**
	 * 结算时更新库存数量
	 * @param cartId
	 * @param shopId
	 * @return
	 */
	public String updateProductQuantity(String cartId, String shopId) {
		
		return this.productService.updateProductQuantity(cartId,shopId);
	}



	/**
	 * 获取退货原因
	 * @param restId
	 * @return
	 */
	public List<ReturnReasonEntity> getReturnReasonList(String restId) {
		
		return this.returnReasonService.getList(restId);
	}


	/**
	 * 获取退货原因map
	 * @param bdId
	 * @param returnQuantity
	 * @param restId
	 * @return
	 */
	public Map getReturnReasonMap(String bdId, String returnQuantity,
			String restId) {
		BillItemEntity billItem=getBillItemOne(bdId);
		if(billItem==null){
			return null;
		}
		BillEntity bill=getOne(billItem.getbId());
		if(bill==null){
			return null;
		}
		if(StringUtils.isBlank(returnQuantity)){
			returnQuantity=billItem.getQuantity();
		}
		List list = new ArrayList();
		Map map=new HashMap();
		map.put("returnType", bill.getPayType());
		String total=getReturnTotal(bill,restId,returnQuantity);
		map.put("returnTotal", total);
		List<ReturnReasonEntity> rList=getReturnReasonList(restId);
		int size=rList.size();
		if(size<1){
			return null;
		}
		for(int i=0;i<size;i++){
			ReturnReasonDto rDto=new ReturnReasonDto();
			rDto.setReasonId(rList.get(i).getReasonId());
			rDto.setReasonDesc(rList.get(i).getReasonDesc());
			list.add(rDto);
		}
		map.put("returnReason",list);
		return null;
	}


	/**
	 * @param cartId
	 * @return
	 */
	public String updateCartItemPayStatus(String cartId,String payStatus) {
		return this.cartItemService.updateCartItemPayStatus(cartId,payStatus);
	}


	/**
	 * 更新支付明细
	 * @param bId
	 * @param payStatus
	 * @param restId
	 * @return
	 */
	public String updatePayItem(String bId, String payStatus, String restId) {
		return this.payItemService.updatePayItem(bId,payStatus,restId);
	}
}
