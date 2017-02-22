package com.ndlan.canyin.frontdesk.service.service3c.productinfo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.base3c.productinfo.ProCategory;
import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.base.repository.dao3c.productinfo.ProductDao;
import com.ndlan.canyin.base.repository.mybatis.ProductMyDao;
import com.ndlan.canyin.frontdesk.common.Arith;
import com.ndlan.canyin.frontdesk.front.controller3c.productinfo.ProductController;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.service3c.shoppingcart.CartItemService;

@Component
@Transactional
public class ProductService extends BaseService<ProductDao, Product> {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class.getName());
	
	private ProductDao productDao;
	
	@Autowired
	private ProductMyDao productMyDao;
	
	@Autowired
	private ProCategoryService proCategoryService;
	
	@Autowired
	private CartItemService cartItemService;
	
	public Page findProductList(String restId,String key,String parentCategoryId,String categoryId,int pageNumber,int pageSize) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.ASC, new String[] { "parentCategoryId" }));
		
		List list=this.productMyDao.findProductList(restId,key,parentCategoryId,categoryId,pageRequest.getOffset(), pageRequest.getPageSize());
		int count=this.productMyDao.findProductCount(restId,key,parentCategoryId,categoryId);
		Page page = new PageImpl(list, pageRequest, count);
		return page;
	}
	
	public List<ProCategory> getCategory() {
		logger.info("----------------------------------> getCategoryService");
		// TODO Auto-generated method stub
		return this.proCategoryService.findAll();
	}
	
	
	
	@Autowired
	public void setDao(ProductDao dao) {
		super.setDao(dao);
		this.productDao=dao;
	}


	public ProductDao getProductDao() {
		return productDao;
	}


	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ProductMyDao getProductMyDao() {
		return productMyDao;
	}

	public void setProductMyDao(ProductMyDao productMyDao) {
		this.productMyDao = productMyDao;
	}


	
	/**
	 * husitong
	 * @Description 商品减库存
	 * @param proId
	 * @param quantity
	 * @return
	 */
	public String productSubQuantity(Product product){
		Product nowProduct = this.getOne(product.getProId());
		
		if(Double.valueOf(product.getQuantity()) > Double.valueOf(nowProduct.getQuantity())){
			return "库存不足";
		}else{
			product.setQuantity(Arith.sub(nowProduct.getQuantity(), product.getQuantity()));
			this.save(product);
			return "success";
		}
		
	}

	/**
	 * 结算时更新库存数量
	 * @param cartId
	 * @param shopId
	 * @return
	 */
	public String updateProductQuantity(String cartId, String shopId) {
		List<CartItemEntity> cartItemList=this.cartItemService.findAllByCartId(cartId);
		int size=cartItemList.size();
		if(size<1){
			return "fail";
		}
		for(int i=0;i<size;i++){
			Product pro=this.getOne(cartItemList.get(i).getProId());
			String quantity=Arith.sub(pro.getQuantity(), cartItemList.get(i).getQuantity());
			pro.setQuantity(quantity);
			this.save(pro);
		}
		return "success";
	}

	/**
	 * 判断库存
	 * @param product
	 * @return
	 */
	public Boolean productInventoryLevel(Product product){
		if(0<Double.valueOf(product.getQuantity())){
			return true;
		}else{
			return false;
		}
	}


	


	

}
