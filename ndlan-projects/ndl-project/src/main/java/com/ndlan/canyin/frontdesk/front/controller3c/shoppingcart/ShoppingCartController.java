package com.ndlan.canyin.frontdesk.front.controller3c.shoppingcart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.Code;
import com.ndlan.canyin.frontdesk.dto3c.IosReturnJson;
import com.ndlan.canyin.frontdesk.dto3c.SearchPageDto;
import com.ndlan.canyin.frontdesk.dto3c.shoppingcart.CartDto;
import com.ndlan.canyin.frontdesk.dto3c.shoppingcart.CartItemDto;
import com.ndlan.canyin.frontdesk.service.service3c.shoppingcart.ShoppingCartService;
import org.apache.commons.lang3.StringUtils;
/**
 * @Description:  购物车控制层
 * @author: wangwei
 * @date: 2016年1月8日 下午1:36:44
 */
@Controller
@RequestMapping({"/shoppingCart"})
public class ShoppingCartController extends BaseManageController{
	private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class.getName());
	@Autowired
	private ShoppingCartService shoppingCartService;
	/**
	 * husitong
	 * @Description  购物车添加商品
	 * @param proId
	 * @param shopId
	 * @param cashierId
	 * @return
	 */
	@RequestMapping({ "/addItem" })
	public IosReturnJson addItem(
			@RequestParam(value = "proId", defaultValue = "") String proId,
			@RequestParam(value = "shopId", defaultValue = "") String shopId,
			@RequestParam(value = "cashierId", defaultValue = "") String cashierId,
			@RequestParam(value = "customerName", defaultValue = "测试") String customerName,
			@RequestParam(value = "mbId", defaultValue = "123") String mbId) {
		IosReturnJson json = new IosReturnJson();
		String result;
		try {
			logger.info("开始执行添加购物车操作，传入参数为：proId="+proId+"  shopId="+shopId+"  cashierId="+cashierId+"  customerName="+customerName);
			if(StringUtils.isEmpty(proId)){
				logger.info("传入的商品Id(proId)参数不能为空");
				json.setStatusCode(Code.FAILE.getCode());
				json.setMessage("传入的商品Id(proId)参数不能为空");
				return json;
			}
		/*	if(StringUtils.isEmpty(customerName)){
				logger.info("传入的客户姓名(customerName)参数不能为空");
				json.setStatusCode(Code.FAILE.getCode());
				json.setMessage("传入的客户姓名(customerName)参数不能为空");
				return json;
			}
			if(StringUtils.isEmpty(mbId)){
				logger.info("传入的会员Id(mbId)参数不能为空");
				json.setStatusCode(Code.FAILE.getCode());
				json.setMessage("传入的会员Id(mbId)参数不能为空");
				return json;
			}*/
			if(StringUtils.isEmpty(getCurrentUserRestId())){
				logger.info("用户未登录");
				json.setStatusCode(Code.FAILE.getCode());
				json.setMessage("用户未登录");
				return json;
				//shopId="72e12515-f54f-11e4-af9a-02004c4f4f50";
			}else{
				shopId=getCurrentUserRestId();
			}
			if(StringUtils.isEmpty(getCurrentUserId())){
				logger.info("用户未登录");
				json.setStatusCode(Code.FAILE.getCode());
				json.setMessage("用户未登录");
				return json;
				//cashierId="72e12622-f54f-11e4-af9a-02004c4f4f50";
			}else{
				cashierId=getCurrentUserId();
			}
			result = this.shoppingCartService.addCartItem(proId,shopId, cashierId, customerName, mbId);
			if("success".equals(result)){
				json.setStatusCode(Code.SUCCESS.getCode());
				json.setMessage(result);
			}else{
				logger.info("添加商品到购物车失败，失败原因为"+result);
				json.setStatusCode(Code.FAILE.getCode());
				json.setMessage(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("添加购物车操作失败，失败原因是："+e.getLocalizedMessage());
			json.setStatusCode(Code.FAILE.getCode());
			json.setMessage(Code.FAILE.getDesc());
			e.printStackTrace();
		}finally{
			return json;
		}
		}	
	// 查询购物单
	@RequestMapping({ "/getItemList" })
	public IosReturnJson getItemList(
			@RequestParam(value = "page", defaultValue = "1") String pageNumber,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue,
			@RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
			@RequestParam(value = "pageUpOrDown", defaultValue = "down") String pageUpOrDown,
			@RequestParam(value = "cartId", defaultValue = "") String cartId,
			@RequestParam(value = "shopId", defaultValue = "") String shopId
			) {
		
		 IosReturnJson json=new IosReturnJson();
		 try {
			logger.info("开始拉取购物车列表操作，传入参数为：cartId="+cartId+"  shopId="+shopId+"  pageNumber="+pageNumber+"  pageSize="+pageSize+"  pageUpOrDown="+pageUpOrDown);
			if(StringUtils.isEmpty(cartId)){
				logger.info("传入的购物车Id(cartId)参数不能为空");
				json.setStatusCode(Code.FAILE.getCode());
				json.setMessage("传入的购物车Id(cartId)参数不能为空");
				return json;
			} 
			if(StringUtils.isEmpty(getCurrentUserRestId())){
				logger.info("用户未登录");
				json.setStatusCode(Code.FAILE.getCode());
				json.setMessage("用户未登录");
				return json;
				//shopId="72e12515-f54f-11e4-af9a-02004c4f4f50";
			}else{
				shopId=getCurrentUserRestId();
			}
			CartDto cartDto=this.shoppingCartService.getItemList(cartId, shopId,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			 SearchPageDto searchPageDto=new SearchPageDto();
			 searchPageDto.setPageSize(String.valueOf(pageSize));
			 searchPageDto.setPageUpOrDown(pageUpOrDown);
			 searchPageDto.setPage(String.valueOf(pageNumber));
			 searchPageDto.setSearchValue(cartId);
			 if(cartDto!=null){
					json.setObjectzJson(cartDto);
					json.setSearchPage(searchPageDto);
					json.setStatusCode(Code.SUCCESS.getCode());
					json.setMessage(Code.SUCCESS.getDesc());
			 }else{
				 json.setStatusCode(Code.FAILE.getCode());
				 json.setMessage(Code.FAILE.getDesc());
				}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("拉取购物车列表失败，失败原因是："+e.getLocalizedMessage());
			e.printStackTrace();
			 json.setStatusCode(Code.FAILE.getCode());
			 json.setMessage(Code.FAILE.getDesc());
		}finally{
			return json;
		}
	}
	// 无码支付codelessSum
	@RequestMapping({ "/codeless" })
	public IosReturnJson codeless(
			@RequestParam(value = "codelessSum", defaultValue = "") String codelessSum,
			@RequestParam(value = "cartId", defaultValue = "") String cartId
			) {
		IosReturnJson iosReturnJson=new IosReturnJson();
		try {
			logger.info("开始无码支付操作，传入参数为：codelessSum="+codelessSum+"  cartId="+cartId);
			if(StringUtils.isEmpty(cartId)){
				logger.info("传入的购物车Id(cartId)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的购物车Id(cartId)参数不能为空");
				return iosReturnJson;
			} 
			if(StringUtils.isEmpty(codelessSum)){
				logger.info("传入的无码支付金额(codelessSum)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的无码支付金额(codelessSum)参数不能为空");
				return iosReturnJson;
			} 
			if(this.shoppingCartService.codeless(codelessSum, cartId))
			{
				iosReturnJson.setStatusCode(Code.SUCCESS.getCode());
				iosReturnJson.setMessage(Code.SUCCESS.getDesc());
			}else{
				logger.info("无码支付失败，失败原因为参数错误");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage(Code.FAILE.getDesc());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("无码支付操作失败，失败原因为:"+e.getLocalizedMessage());
			e.printStackTrace();
			iosReturnJson.setStatusCode(Code.FAILE.getCode());
			iosReturnJson.setMessage(Code.FAILE.getDesc());
		}finally{
			return iosReturnJson;
		}
	}
	// 整单折扣优惠
	@RequestMapping({ "/discount" })
	public IosReturnJson discount(
			@RequestParam(value = "cartId", defaultValue = "") String cartId,
			@RequestParam(value = "allPrefer", defaultValue = "0.00") String allPrivilege,
			@RequestParam(value = "allDiscount", defaultValue = "100") String allDiscount,
			@RequestParam(value = "discountType", defaultValue = "0") String discountType
			) {

		IosReturnJson iosReturnJson=new IosReturnJson();
		try {
			logger.info("开始整单折扣优惠操作，传入参数为：cartId="+cartId+"  allPrivilege="+allPrivilege+"  allDiscount="+allDiscount+"  discountType="+discountType);
			if(StringUtils.isEmpty(cartId)){
				logger.info("传入的购物车Id(cartId)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的购物车Id(cartId)参数不能为空");
				return iosReturnJson;
			} 
			if(this.shoppingCartService.discount(cartId, allPrivilege, allDiscount, discountType))
			{
				iosReturnJson.setStatusCode(Code.SUCCESS.getCode());
				iosReturnJson.setMessage(Code.SUCCESS.getDesc());
			}else{
				logger.info("整单折扣操作失败，失败原因为参数错误");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage(Code.FAILE.getDesc());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("修改整单折扣优惠 失败，失败原因："+e.getLocalizedMessage());
			e.printStackTrace();
			iosReturnJson.setStatusCode(Code.FAILE.getCode());
			iosReturnJson.setMessage(Code.FAILE.getDesc());
		}finally{
			return iosReturnJson;
		}
	}
	// 清空购物车
	@RequestMapping({ "/clear" })
	public IosReturnJson clear(@RequestParam(value = "cartId", defaultValue = "") String cartId,
			@RequestParam(value = "shopId", defaultValue = "") String shopId
			) {
		IosReturnJson iosReturnJson=new IosReturnJson();
		try {
			logger.info("开始清空购物车操作，传入参数为：cartId="+cartId+"   shopId="+shopId);
			if(StringUtils.isEmpty(cartId)){
				logger.info("传入的购物车Id(cartId)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的购物车Id(cartId)参数不能为空");
				return iosReturnJson;
			} 
			if(this.shoppingCartService.clear(cartId,shopId))
			{
				iosReturnJson.setStatusCode(Code.SUCCESS.getCode());
				iosReturnJson.setMessage(Code.SUCCESS.getDesc());
			}else{
				logger.info("清空购物车操作失败，失败原因为参数错误");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage(Code.FAILE.getDesc());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("清空购物车操作失败，失败原因为："+e.getLocalizedMessage());
			e.printStackTrace();
			iosReturnJson.setStatusCode(Code.FAILE.getCode());
			iosReturnJson.setMessage(Code.FAILE.getDesc());
		}finally{
			return iosReturnJson;
		}
	}
	// 单条删除购物车中的商品
	@RequestMapping({ "/delete" })
	public IosReturnJson delete(@RequestParam(value = "cartId", defaultValue = "") String cartId,
			@RequestParam(value = "cartItemId", defaultValue = "") String cartItemId,
			@RequestParam(value = "shopId", defaultValue = "") String shopId
			) {
		IosReturnJson iosReturnJson=new IosReturnJson();
			try {
				logger.info("开始删除单件商品操作，传入参数为：cartId="+cartId+"  cartItemId="+cartItemId+"  shopId="+shopId);
				if(StringUtils.isEmpty(cartId)){
					logger.info("传入的购物车Id(cartId)参数不能为空");
					iosReturnJson.setStatusCode(Code.FAILE.getCode());
					iosReturnJson.setMessage("传入的购物车Id(cartId)参数不能为空");
					return iosReturnJson;
				} 
				if(StringUtils.isEmpty(cartItemId)){
					logger.info("传入的购物车明细Id(cartItemId)参数不能为空");
					iosReturnJson.setStatusCode(Code.FAILE.getCode());
					iosReturnJson.setMessage("传入的购物车明细Id(cartItemId)参数不能为空");
					return iosReturnJson;
				} 
				if(this.shoppingCartService.deleteOne(cartId, cartItemId, shopId))
				{
					iosReturnJson.setStatusCode(Code.SUCCESS.getCode());
					iosReturnJson.setMessage(Code.SUCCESS.getDesc());
				}else{
					logger.info("删除单个商品操作失败，失败原因为参数错误");
					iosReturnJson.setStatusCode(Code.FAILE.getCode());
					iosReturnJson.setMessage(Code.FAILE.getDesc());
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("删除单个商品操作失败，失败原因为:"+e.getLocalizedMessage());
				e.printStackTrace();
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage(Code.FAILE.getDesc());
			}finally{
				return iosReturnJson;
			}
			}
	// 商品详情
	@RequestMapping({ "/getItem" })
	public IosReturnJson getItem(@RequestParam(value = "cartId", defaultValue = "") String cartId,
			@RequestParam(value = "cartItemId", defaultValue = "") String cartItemId,
			@RequestParam(value = "shopId", defaultValue = "") String shopId
			) {
		IosReturnJson iosReturnJson=new IosReturnJson();
		try {
			logger.info("开始获取商品信息操作，传入参数为：cartId="+cartId+"  cartItemId="+cartItemId+"   shopId="+shopId);
			
			if(StringUtils.isEmpty(cartId)){
				logger.info("传入的购物车Id(cartId)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的购物车Id(cartId)参数不能为空");
				return iosReturnJson;
			} 
			if(StringUtils.isEmpty(cartItemId)){
				logger.info("传入的购物车明细Id(cartItemId)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的购物车明细Id(cartItemId)参数不能为空");
				return iosReturnJson;
			} 
			CartItemDto object=this.shoppingCartService.getItem(cartId, cartItemId, shopId);
			if(object!=null)
			{
				iosReturnJson.setStatusCode(Code.SUCCESS.getCode());
				iosReturnJson.setMessage(Code.SUCCESS.getDesc());
				iosReturnJson.setObjectzJson(object);
			}else{
				logger.info("获取商品信息操作失败，失败原因为参数错误");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage(Code.FAILE.getDesc());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取商品详细信息操作失败，失败原因为："+e.getLocalizedMessage());
			e.printStackTrace();
			iosReturnJson.setStatusCode(Code.FAILE.getCode());
			iosReturnJson.setMessage(Code.FAILE.getDesc());
		}finally{
			return iosReturnJson;
		}
		}
	// 立减折扣改库存
	@RequestMapping({ "/updateItem" })
	public IosReturnJson updateItem(@RequestParam(value = "discountType", defaultValue = "") String discountType,
			@RequestParam(value = "cartItemId", defaultValue = "") String cartItemId,
			@RequestParam(value = "cartId", defaultValue = "") String cartId,
			@RequestParam(value = "discount", defaultValue = "100") String discount,
			@RequestParam(value = "privilege", defaultValue = "0.00") String privilege,
			@RequestParam(value = "quantity", defaultValue = "1") String quantity
			) {
		IosReturnJson iosReturnJson=new IosReturnJson();
		try {
			logger.info("开始修改立减折扣操作，传入参数为：discountType="+discountType+"   cartItemId="+cartItemId+"   discount="+discount+"   privilege="+privilege+"   quantity="+quantity);
			if(StringUtils.isEmpty(cartId)){
				logger.info("传入的购物车Id(cartId)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的购物车Id(cartId)参数不能为空");
				return iosReturnJson;
			} 
			if(StringUtils.isEmpty(cartItemId)){
				logger.info("传入的购物车明细Id(cartItemId)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的购物车明细Id(cartItemId)参数不能为空");
				return iosReturnJson;
			} 
			if(StringUtils.isEmpty(discountType)){
				logger.info("传入的折扣类型(discountType)参数不能为空");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage("传入的折扣类型(discountType)参数不能为空");
				return iosReturnJson;
			} 
			if(this.shoppingCartService.updateItem(discountType,discount,privilege,cartItemId,cartId,quantity))
			{
				iosReturnJson.setStatusCode(Code.SUCCESS.getCode());
				iosReturnJson.setMessage(Code.SUCCESS.getDesc());
			}else{
				logger.info("修改折扣优惠操作失败，失败原因为参数错误");
				iosReturnJson.setStatusCode(Code.FAILE.getCode());
				iosReturnJson.setMessage(Code.FAILE.getDesc());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("修改立减折扣操作失败，失败原因为："+e.getLocalizedMessage());
			e.printStackTrace();
			iosReturnJson.setStatusCode(Code.FAILE.getCode());
			iosReturnJson.setMessage(Code.FAILE.getDesc());
		}finally{
			return iosReturnJson;
		}
	}
	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}
	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}
}
