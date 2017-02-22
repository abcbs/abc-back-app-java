/**
 * 
 */
package com.ndlan.canyin.frontdesk.service.service3c.billinfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.base3c.billinfo.BillEntity;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillItemEntity;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.base.repository.dao3c.billinfo.BillItemDao;
import com.ndlan.canyin.frontdesk.common.Arith;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.service3c.shoppingcart.CartItemService;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月7日 下午3:37:43  
 */
@Service
@Transactional
public class BillItemService extends BaseService<BillItemDao,BillItemEntity>{
	private static final Logger logger = LoggerFactory.getLogger(BillItemService.class.getName());
	private BillItemDao billItemDao;
	
	@Autowired
	private CartItemService cartItemService;

	@Autowired
	public void setBillItemDao(BillItemDao billItemDao) {
		super.setDao(billItemDao);
		this.billItemDao = billItemDao;
	}

	public List<BillItemEntity> getBillItemList(String bId, String restId) {
		logger.info("----------------------------------> getBillItemList");
		return this.billItemDao.getBillItemList(bId, restId);
	}

	/**
	 * 保存订单详细信息
	 * @param shopId
	 * @param cartId
	 * @param billRtn
	 */
	public String insertBillItemByCartId(String shopId, String cartId,
			BillEntity billRtn) {
		List<CartItemEntity> cartItemList = this.cartItemService.findAllByCartId(cartId);
		int size = cartItemList.size();
		if (size == 0) {
			return "fail";
		}
		// save订单详细信息
		
		for (int i = 0; i < size; i++) {
			BillItemEntity billItem = new BillItemEntity();
			billItem.setbId(billRtn.getbId());
			billItem.setCartItemId(cartItemList.get(i).getCartItemId());
			billItem.setBdNo(getBillNo());
			billItem.setBarCode(cartItemList.get(i).getBarCode());
			billItem.setName(cartItemList.get(i).getName());
			billItem.setProId(cartItemList.get(i).getProId());
			billItem.setProDesc(cartItemList.get(i).getProDesc());
			billItem.setPic(cartItemList.get(i).getPic());
			billItem.setQuantity(cartItemList.get(i).getQuantity());
			billItem.setCategoryId(cartItemList.get(i).getCategoryId());
			billItem.setPrice(cartItemList.get(i).getPrice());
			billItem.setDiscount(cartItemList.get(i).getDiscount());
			billItem.setDerate(cartItemList.get(i).getDerate());
			billItem.setSubtotal(cartItemList.get(i).getSubtotal());
			billItem.setPrivilege(cartItemList.get(i).getPrivilege());
			billItem.setDiscountType(cartItemList.get(i).getDiscountType());
			billItem.setIsCodeless(cartItemList.get(i).getIsCodeless());
			billItem.setRestId(shopId);
			billItem.setStatus("0");// 正常
			save(billItem);	
		}
		return "success";
	}

	/**
	 * @param bId
	 * @param restId
	 * @return
	 */
	public String updateBillItem(String bId, String restId) {
		// 获取已支付完成的交易明细list
		List<BillItemEntity> billItemList = getBillItemList(bId, restId);
		if (billItemList.size() == 0) {
			return "fail";
		}
		// 更新订单明细
		for (BillItemEntity billItem : billItemList) {
			billItem.setPayStatus("1");// 已结账
			save(billItem);
		}
		return "success";
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
	 * 退货后更新订单明细
	 * @param billItem
	 * @param returnTotal
	 * @param returnQuantity
	 */
	public String updateBillItemAfterReturn(BillItemEntity billItem,
			String returnTotal, String returnQuantity) {
		billItem.setStatus("2");//已退货
		billItem.setQuantity(getPositiveNumber(Arith.sub(billItem.getQuantity(), returnQuantity)));//数量
		billItem.setPrice(getPositiveNumber(Arith.sub(billItem.getPrice(), returnTotal)));	//金额
		billItem.setSubtotal(getPositiveNumber(Arith.sub(billItem.getSubtotal(), returnTotal)));//小计
		save(billItem);
		return "success";
	}

}
