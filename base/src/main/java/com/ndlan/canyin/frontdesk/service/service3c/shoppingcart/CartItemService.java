package com.ndlan.canyin.frontdesk.service.service3c.shoppingcart;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartEntity;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.base.repository.dao3c.shoppingcart.CartItemDao;
import com.ndlan.canyin.core.common.DiscountOrPrivilegeEnum43c;
import com.ndlan.canyin.frontdesk.common.Arith;
import com.ndlan.canyin.frontdesk.common.Calculate4Base3c;
import com.ndlan.canyin.frontdesk.service.BaseService;
/**
 * 
 * @Description:购物车明细service
 * @author: wangwei
 * @date: 2016年1月8日 下午1:39:37
 */
@Service
@Transactional
public class CartItemService extends BaseService<CartItemDao, CartItemEntity> {
	private CartItemDao cartItemDao;
	private static final Logger logger = LoggerFactory.getLogger(CartService.class.getName());
	@Autowired
	public void setCartItemDao(CartItemDao cartItemDao) {
		super.setDao(cartItemDao);
		this.cartItemDao = cartItemDao;
	}
	public List<CartItemEntity> findByCartId(String cartId){
		return this.cartItemDao.findAllByCartId(cartId);
	}
	public CartItemEntity findByCartId(String cartId,String proId){
		return this.cartItemDao.findByCartId(cartId,proId);
	}
	/**
	 * 更新状态
	 * @param cartId
	 * @return
	 */
	public String updateItemStatus(String cartId) {
		List<CartItemEntity> cartItemList = findAllByCartId(cartId);
		int size = cartItemList.size();
		if (size == 0) {
			return "fail";
		}
		for (int i = 0; i < size; i++) {
			cartItemList.get(i).setStatus("2");// 已下单
			save(cartItemList.get(i));
		}
		return "success";
	}
	/**
	 * 退货后更新购物车明细
	 * @param cartItemId
	 * @param returnQuantity
	 * @param returnTotal
	 * @return
	 */
	public String updateCartItem(String cartItemId, String returnQuantity,
			String returnTotal) {
		CartItemEntity cartItem=this.getOne(cartItemId);
		cartItem.setPayStatus("3");//退货
		save(cartItem);
		return "success";
	}
	/**
	 * 获取购物车明细，包括无码收银
	 * @param cartId
	 * @return
	 */
	public List<CartItemEntity> findAllByCartId(String cartId) {
		return this.cartItemDao.findAllByCartId(cartId);
	}
	public Boolean addCartItem(CartEntity cartEntity,Product product){
		try {
			CartItemEntity cartItemEntity=this.cartItemDao.findByCartId(cartEntity.getCartId(), product.getProId());
			if(cartItemEntity!=null){
				//如果不为空  数量+1
				String quantity=Arith.add(cartItemEntity.getQuantity(), "1");
				cartItemEntity.setQuantity(quantity);
			}else{
			cartItemEntity=new CartItemEntity();
			cartItemEntity.setCartId(cartEntity.getCartId());
			cartItemEntity.setProId(product.getProId());
			cartItemEntity.setCategoryId(product.getCategoryId());
			cartItemEntity.setBarCode(product.getBarCode());
			cartItemEntity.setName(product.getName());
			cartItemEntity.setProDesc(product.getProDesc());
			cartItemEntity.setPic(product.getPic());
			cartItemEntity.setQuantity("1");
			cartItemEntity.setPrice(product.getPrice());
			//给discountType赋初值  默认设置为折扣
			cartItemEntity.setDiscountType("0");
			cartItemEntity.setDiscount("100");
			cartItemEntity.setPrivilege("0.00");
			//减免
			cartItemEntity.setDerate("0");
			//状态
			cartItemEntity.setStatus("1");
			//是否无码支付  默认设为0
			cartItemEntity.setIsCodeless("0");
			//店铺id
			cartItemEntity.setRestId(cartEntity.getRestId());
			//设置支付状态为未支付
			cartItemEntity.setPayStatus("0");
			}
			String subtotal="0.00";
			//更新小计
			if(DiscountOrPrivilegeEnum43c.DISCOUTN.getCode().equals(cartItemEntity.getDiscountType())){
				cartItemEntity.setPrivilege("0.00");
				String realDiscount=Arith.div(cartItemEntity.getDiscount(),"100",4);
				subtotal=Calculate4Base3c.doSubtotal4Discount(cartItemEntity.getPrice(), realDiscount, cartItemEntity.getQuantity());
			}else if(DiscountOrPrivilegeEnum43c.PRIVILEGE.getCode().equals(cartItemEntity.getDiscountType())){
				cartItemEntity.setDiscount("100");
				subtotal=Calculate4Base3c.doSubtotal4Privilege(cartItemEntity.getPrice(), cartItemEntity.getPrivilege(), cartItemEntity.getQuantity());
			}else{
				//即没优惠又没折扣
				cartItemEntity.setDiscount("100");
				cartItemEntity.setPrivilege("0.00");
				subtotal=Calculate4Base3c.doSubtotal(cartItemEntity.getPrice(), cartItemEntity.getQuantity());
			}
			cartItemEntity.setSubtotal(subtotal);
			this.save(cartItemEntity);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("添加购物车行信息失败，失败原因为"+e.getLocalizedMessage());
			return false;
		}
		return true;
	}
	public Boolean addCodelessItem(CartEntity cartEntity, String codelessSum){
		try {
			CartItemEntity cartItemEntity=new CartItemEntity();
			cartItemEntity.setCartId(cartEntity.getCartId());
			//状态
			cartItemEntity.setStatus("1");
			cartItemEntity.setQuantity("1");
			//设为无码支付
			cartItemEntity.setIsCodeless("1");
			cartItemEntity.setName("无码支付");
			cartItemEntity.setPayStatus("0");
			//单价
			cartItemEntity.setPrice(codelessSum);
			//小计
			cartItemEntity.setSubtotal(codelessSum);
			this.cartItemDao.save(cartItemEntity);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("添加无码支付明细失败，失败原因为："+e.getLocalizedMessage());
			return false;
		}
		return true;
	}
	/**
	 * @author wangwei
	 * @param cartId
	 * @return
	 */
	
	public Boolean updateItemStatus4Clear(String cartId){
		List<CartItemEntity> list=this.cartItemDao.findAllByCartId(cartId);
		if(list.size()>0){
			for (CartItemEntity cartItem : list) {
				//改变状态
				cartItem.setStatus("0");
				this.save(cartItem);
				}
			}else{
				return false;
			}
		return true;
		
		}

	public Boolean updateItemStatus4Delete(CartItemEntity cartItemEntity){
		try {
			CartItemEntity item=this.getOne(cartItemEntity.getCartItemId());
			item.setStatus("0");
			this.save(item);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("删除单个商品失败，失败原因为："+e.getLocalizedMessage());
			return false;
		}
		
		return true;
	}
	public Boolean updateItem(CartItemEntity cartItemEntity){
		try {
			if(DiscountOrPrivilegeEnum43c.DISCOUTN.getCode().equals(cartItemEntity.getDiscountType())){
				cartItemEntity.setPrivilege("0.00");
				String realDiscount=Arith.div(cartItemEntity.getDiscount(),"100",4);
				//更新小计
				String subtotal = Calculate4Base3c.doSubtotal4Discount(cartItemEntity.getPrice(), realDiscount,cartItemEntity.getQuantity());
				cartItemEntity.setSubtotal(subtotal);
				//更新减免
				String  derate=Calculate4Base3c.doDerate4Discount(cartItemEntity.getPrice(), realDiscount, cartItemEntity.getQuantity());
				cartItemEntity.setDerate(derate);
			}else if(DiscountOrPrivilegeEnum43c.PRIVILEGE.getCode().equals(cartItemEntity.getDiscountType())){
				//更新优惠
				cartItemEntity.setDiscount("100");
				//更新小计
				String subtotal=Calculate4Base3c.doSubtotal4Privilege(cartItemEntity.getPrice(), cartItemEntity.getPrivilege(), cartItemEntity.getQuantity());
				cartItemEntity.setSubtotal(subtotal);
				//更新减免
				String derate=Calculate4Base3c.doDerate4Privilege(cartItemEntity.getPrivilege(), cartItemEntity.getQuantity());
				cartItemEntity.setDerate(derate);
			}else{
				//更新小计
				String subtotal=Calculate4Base3c.doSubtotal(cartItemEntity.getPrice(), cartItemEntity.getQuantity());
				cartItemEntity.setSubtotal(subtotal);
				//更新减免
				cartItemEntity.setDerate("-");
			}
			this.save(cartItemEntity);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新商品优惠折扣失败，失败原因为："+e.getLocalizedMessage());
			return false;
		}
		return true;
	}
	/**
	 * 支付后更新支付状态
	 * @param cartId
	 * @return
	 */
	public String updateCartItemPayStatus(String cartId,String payStatus) {
		List<CartItemEntity> cartItemList=this.cartItemDao.findAllByCartId(cartId);
		int size=cartItemList.size();
		if(size<1){
			return "fail";
		}
		for(int i=0;i<size;i++){
			if(payStatus.equals("2")){
				cartItemList.get(i).setPayStatus("1");
			}else if(payStatus.equals("3")){
				cartItemList.get(i).setPayStatus("2");
			}
			
			this.save(cartItemList.get(i));
		}
		return "success";
	}
}
