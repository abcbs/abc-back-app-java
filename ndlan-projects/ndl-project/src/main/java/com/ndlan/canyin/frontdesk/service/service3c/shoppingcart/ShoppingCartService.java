package com.ndlan.canyin.frontdesk.service.service3c.shoppingcart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ndlan.canyin.base.entity.base3c.productinfo.ProCategory;
import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartEntity;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.frontdesk.common.Arith;
import com.ndlan.canyin.frontdesk.dto3c.shoppingcart.CartDto;
import com.ndlan.canyin.frontdesk.dto3c.shoppingcart.CartItemDto;
import com.ndlan.canyin.frontdesk.service.service3c.productinfo.ProCategoryService;
import com.ndlan.canyin.frontdesk.service.service3c.productinfo.ProductService;
/**
 * @Description:shoppingcart模块整合service
 * @author: wangwei
 * @date: 2016年1月8日 下午1:40:29
 */
@Service
public class ShoppingCartService {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingCartService.class.getName());
	@Autowired
	private CartService cartService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProCategoryService categoryService;
	/**
	 * 拉取购物车列表
	 * @author wangwei 
	 * @param cartId
	 * @param restId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public CartDto getItemList(String cartId,String restId,int pageNumber,int pageSize){
				//得到该购物车明细中的状态为1的数据
				Map searchParams=new HashMap();
				searchParams.put("EQ_cartId", cartId);
				searchParams.put("EQ_status", "1");
				searchParams.put("EQ_isCodeless", "0");
				Page page=this.cartItemService.search(searchParams, pageNumber, pageSize, "desc");
				List<CartItemEntity> cartItemList=page.getContent();
				//model
				List<CartItemDto> cartItemDtoList=new ArrayList<CartItemDto>();
				CartDto cartDto=new CartDto();
				SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(cartItemList!=null&&cartItemList.size()!=0){
				for (CartItemEntity cartItemEntity : cartItemList) {
					CartItemDto cartItemDto=new CartItemDto();
					cartItemDto.setBarCode(cartItemEntity.getBarCode());
					cartItemDto.setCartId(cartItemEntity.getCartId());
					cartItemDto.setCardItemId(cartItemEntity.getCartItemId());
					cartItemDto.setCategoryId(cartItemEntity.getCategoryId());
					cartItemDto.setDerate(cartItemEntity.getDerate());
					cartItemDto.setDiscount(cartItemEntity.getDiscount());
					//获取折扣类型
					cartItemDto.setDiscountType(cartItemEntity.getDiscountType());
					cartItemDto.setIsCodeless(cartItemEntity.getIsCodeless());
					cartItemDto.setName(cartItemEntity.getName());
					cartItemDto.setPayStatus(cartItemEntity.getPayStatus());
					cartItemDto.setPic(cartItemEntity.getPic());
					cartItemDto.setPrice(cartItemEntity.getPrice());
					cartItemDto.setPrivilege(cartItemEntity.getPrivilege());
					cartItemDto.setProDesc(cartItemEntity.getProDesc());
					cartItemDto.setProId(cartItemEntity.getProId());
					cartItemDto.setQuantity(cartItemEntity.getQuantity());
					cartItemDto.setStatus(cartItemEntity.getStatus());
					cartItemDto.setSubtotal(cartItemEntity.getSubtotal());
					cartItemDto.setDisAndPri(cartItemEntity.getDerate());
					cartItemDto.setInventory("");
					cartItemDto.setCategoryName("");
					cartItemDtoList.add(cartItemDto);
				}
				CartEntity cartEntity=this.cartService.getOne(cartId);
				if(cartEntity!=null){
					//后期完善之后删除
					if(StringUtils.isEmpty(cartEntity.getCreateEmployee().getEmpId())){
						cartDto.setCasherId("72e12622-f54f-11e4-af9a-02004c4f4f50");
					}else{
					cartDto.setCasherId(cartEntity.getCreateEmployee().getEmpId());
					}
					cartDto.setAllDiscount(cartEntity.getAllDiscount());
					cartDto.setAllPrefer(cartEntity.getAllPrivilege());
					cartDto.setCartId(cartEntity.getCartId());
					cartDto.setCodelessSum(cartEntity.getCodelessSum());
					cartDto.setCustomerName(cartEntity.getCustomerName());
					cartDto.setMbId(cartEntity.getMbId());
					cartDto.setStatus(cartEntity.getStatus());
					cartDto.setTotal(cartEntity.getTotal());
					String createTime=simple.format(cartEntity.getCreateTime());
					cartDto.setCreateTime(createTime);
				}else{
					return null;
				}
				cartDto.setCartItems(cartItemDtoList);
				}else{
					return null;
				}
				return cartDto;
	}
	/**
	 * 清空购物车
	 * @author wangwei
	 * @param cartId
	 * @param restId
	 * @return
	 */
	public Boolean clear(String cartId,String restId){
			if(this.cartItemService.updateItemStatus4Clear(cartId)){
				CartEntity cartEntity=this.cartService.getOne(cartId);	
				if(cartEntity!=null){
				    //清空无码支付金额
					cartEntity.setCodelessSum("0.00");
				    //清空整单优惠
					cartEntity.setAllPrivilege("0.00");
				    //整单折扣
					cartEntity.setAllDiscount("100");
				    //重置金额
					cartEntity.setTotal("0.00");
				    //清空折扣前总金额
					cartEntity.setBeforDiscountTotal("0.00");
					this.cartService.save(cartEntity);
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
	}
	/**
	 * 单条删除购物车中的商品
	 * @author wangwei
	 * @param cartId
	 * @param cartItemId
	 * @param restId
	 * @return
	 */
	public Boolean deleteOne(String cartId,String cartItemId,String restId){
			//找出对应购物车下的 对应产品且状态为1的数据
			CartItemEntity cartItemEntity= this.cartItemService.getOne(cartItemId);
			if(cartItemEntity!=null){
				if(this.cartItemService.updateItemStatus4Delete(cartItemEntity)){
					CartEntity cartEntity=this.cartService.getOne(cartId);
					//计算总金额  减去小计
					if(this.cartService.updateCart("-"+cartItemEntity.getSubtotal(), cartEntity)){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
	/**
	 * 无码支付codelessSum
	 * @author wangwei
	 * @param codelessSum
	 * @param cartId
	 * @return
	 */
	public Boolean codeless(String codelessSum, String cartId) {
		// 1.得到无码支付金额 以及购物车头对象
		CartEntity cartEntity = this.cartService.getOne(cartId);
		if (cartEntity != null) {
			// 插入明细
			if (this.cartItemService.addCodelessItem(cartEntity, codelessSum)) {
				// 计算总额
				if (this.cartService.updateCart(codelessSum,cartEntity)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	 * 整单折扣优惠
	 * @author wangwei
	 * @param cartId
	 * @param allPrivilege
	 * @param allDiscount
	 * @param isDiscountPrivilege
	 * @return
	 */
	public Boolean discount(String cartId,String allPrivilege,String allDiscount,String isDiscountPrivilege){
			//根据购物车id找到该购物车对象
			CartEntity cartEntity=this.cartService.getOne(cartId);
			if(cartEntity!=null){
				cartEntity.setAllDiscount(allDiscount);
				cartEntity.setAllPrivilege(allPrivilege);
				cartEntity.setDiscountType(isDiscountPrivilege);
				if(this.cartService.updateCart("0", cartEntity)){
					return true;
				}else{
					return false;
				}
			}
				else{
					return false;
				}
		}
	/**
	 * 获取详细信息
	 * @author wangwei
	 * @param cartId
	 * @param cartItemId
	 * @param restId
	 * @return
	 */
	public CartItemDto getItem(String cartId,String cartItemId,String restId)
		{
			CartItemEntity cartItemEntity=this.cartItemService.getOne(cartItemId);
			CartItemDto cartItemDto=null;
			if(cartItemEntity!=null){
				cartItemDto=new CartItemDto();
				cartItemDto.setBarCode(cartItemEntity.getBarCode());
				cartItemDto.setCartId(cartItemEntity.getCartId());
				cartItemDto.setCardItemId(cartItemEntity.getCartItemId());
				cartItemDto.setCategoryId(cartItemEntity.getCategoryId());
				//判断折扣类型  如果为0  DisAndPri返回折扣
				if("0".equals(cartItemEntity.getIsDiscountOrPrivate())){
					cartItemDto.setDisAndPri(cartItemEntity.getDiscount());
				}else{
					//返回优惠
					cartItemDto.setDisAndPri(cartItemEntity.getPrivilege());
				}
				cartItemDto.setDerate(cartItemEntity.getDerate());
				cartItemDto.setDiscount(cartItemEntity.getDiscount());
				cartItemDto.setDiscountType(cartItemEntity.getIsDiscountOrPrivate());
				cartItemDto.setIsCodeless(cartItemEntity.getIsCodeless());
				cartItemDto.setName(cartItemEntity.getName());
				cartItemDto.setPayStatus(cartItemEntity.getPayStatus());
				cartItemDto.setPic(cartItemEntity.getPic());
				cartItemDto.setPrice(cartItemEntity.getPrice());
				cartItemDto.setPrivilege(cartItemEntity.getPrivilege());
				cartItemDto.setProDesc(cartItemEntity.getProDesc());
				cartItemDto.setProId(cartItemEntity.getProId());
				cartItemDto.setQuantity(cartItemEntity.getQuantity());
				cartItemDto.setStatus(cartItemEntity.getStatus());
				cartItemDto.setSubtotal(cartItemEntity.getSubtotal());
				Product product=this.productService.getOne(cartItemEntity.getProId());
				if(product!=null){
					//放入库存
				cartItemDto.setInventory(product.getQuantity());
				ProCategory category=this.categoryService.getOne(cartItemEntity.getCategoryId());
				if(category!=null){
					//放入品类名
				cartItemDto.setCategoryName(category.getCategoryName());
				}else{
					return null;
				}
				}else{
					return null;
				}
			}else{
				return null;
			}
			return cartItemDto;
			
		}
	/**
	 * 立减折扣
	 * @author huqianqian
	 * @param isDiscountOrPrivate
	 * @param discount
	 * @param privilege
	 * @param cartItemId
	 * @param cartId
	 * @param quantity
	 * @return
	 */
	public Boolean updateItem(String isDiscountOrPrivate,String  discount,String privilege,String cartItemId,String cartId,String quantity){
			CartItemEntity cartItemEntity=this.cartItemService.getOne(cartItemId);
			if(cartItemEntity!=null){
				cartItemEntity.setDiscountType(isDiscountOrPrivate);
				cartItemEntity.setDiscount(discount);
				cartItemEntity.setPrivilege(privilege);
				cartItemEntity.setQuantity(quantity);
				String before=cartItemEntity.getSubtotal();
				if(this.cartItemService.updateItem(cartItemEntity)){
					
					String afert=this.cartItemService.getOne(cartItemId).getSubtotal();
					String  disparity=Arith.sub(afert, before);
					//更改总金额
					CartEntity cartEntity=this.cartService.getOne(cartId);
					if(this.cartService.updateCart(disparity, cartEntity)){
						return true;
					}else{
						return false;
					}
					}else{
						return false;
					}
				}else{
					return false;
				}
	}
	/**
	 * 添加商品到购物车
	 * @author wangwei
	 * @param proId
	 * @param restId
	 * @param cashierId
	 * @param customerName
	 * @param mbId
	 * @return
	 * @throws Exception
	 */
	public String addCartItem(String proId, String restId, String cashierId,String customerName,String mbId)
			throws Exception {
		Product product=this.productService.getOne(proId);
		if(product!=null){
			if(this.productService.productInventoryLevel(product)){
				//获取头对象
				CartEntity cartEntity=this.cartService.findByEmplId(cashierId, restId);
				if(cartEntity!=null){
					if(this.cartItemService.addCartItem(cartEntity, product)){
						CartItemEntity cartItemEntity=this.cartItemService.findByCartId(cartEntity.getCartId(), proId);
						String subtotalOne=Arith.div(cartItemEntity.getSubtotal(), cartItemEntity.getQuantity(), 4);
						if(this.cartService.updateCart(subtotalOne,cartEntity)){
							return "success";
						}else{
							return "fail";
						}
					}else{
						return "fail";
					}
				}else{
					return "fail";
				}
			}else{
				return "库存不足";
			}
		}else{
			return "fail";
		}
	}
	public CartService getCartService() {
		return cartService;
	}
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	public CartItemService getCartItemService() {
		return cartItemService;
	}
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public ProCategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(ProCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	}
