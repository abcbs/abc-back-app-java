package com.ndlan.canyin.frontdesk.front.controller3c.productinfo;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ndlan.canyin.base.entity.base3c.productinfo.ProCategory;
import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.dto3c.Code;
import com.ndlan.canyin.frontdesk.dto3c.IosReturnJson;
import com.ndlan.canyin.frontdesk.dto3c.SearchPageDto;
import com.ndlan.canyin.frontdesk.dto3c.SubCode;
import com.ndlan.canyin.frontdesk.service.service3c.productinfo.ProductService;
import com.ndlan.canyin.frontdesk.util.ImageCutUtil;

/**
 * 商品信息
 * 
 * @author zhangts
 * 
 */
@Controller
@RequestMapping( { "/product" })
@Lazy
public class ProductController extends BaseManageController {

	@Autowired
	private ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());

	/**
	 * 上传图片
	 * @param request
	 * @param pic  图片名称
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "uploadPic" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public IosReturnJson uploadPic(MultipartHttpServletRequest request,String proId) {
		logger.info("商品上传图片参数为:  proId:"+proId);
		IosReturnJson json=null;
		LinkedHashMap mapUpload = new LinkedHashMap();
		List<MultipartFile> tablePics = request.getFiles("pic");
		if ((tablePics != null) && (tablePics.size() > 0)) {
			for (MultipartFile tablePic : tablePics) {
				if ((tablePic != null) && (!tablePic.isEmpty())) {
					Product product=this.productService.getOne(proId);
					product.setProId(proId);
					String uploadUrl = new ImageCutUtil().uploadTableImageFile(
							tablePic, request, getCurrentUserRestId(),
							mapUpload);
					product.setPic(uploadUrl);
					try {
						Product resproduct =this.productService.save(product);
						json=new IosReturnJson(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
						json.setObjectzJson(resproduct);
						return json;
					} catch (Exception e) {
						logger.error("图片上传失败,错误信息:"+e.getLocalizedMessage(),e);
						// TODO Auto-generated catch block
						e.printStackTrace();
						json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
						return json;
					}
				}
			}
		}else{
			logger.info("pic即图片地址不能为空,上传失败");
		}
		json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
		return json;
	}

	/**
	 * 获取商品分类信息
	 * 
	 * @return
	 */
	@RequestMapping(value = { "getCategory" })
	public IosReturnJson getCategory() {
		logger.info("开始获得商品分类信息!");
		List<ProCategory> list = this.productService.getCategory();
		if(list.size()<=0){
			logger.info("商品分类信息为空!");
		}
		IosReturnJson json=new IosReturnJson();
		json.setStatusCode(Code.SUCCESS.getCode());
		json.setMessage(Code.SUCCESS.getDesc());
		json.setObjectzJson(list);

		return json;
	}

	/**
	 * 获取商品信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param parentCategoryId
	 *            一级分类
	 * @param key
	 *            模糊搜索
	 * @param categoryId
	 *            二级分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public IosReturnJson getList(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "pageUpOrDown", defaultValue = "down") String pageUpOrDown,
			String parentCategoryId, String searchValue, String categoryId,String restId,
			HttpServletRequest request) {
		logger.info("获取商品信息,参数信息为: page:"+pageNumber+" pageSize："+pageSize+" pageUpOrDown:"+pageUpOrDown+
				" parentCategoryId:"+parentCategoryId+" searchValue:"+searchValue+" categoryId:"+categoryId+" restId:"+restId);
        if(restId ==null || restId.equals("")){
        	logger.info("restId为空,系统去session中取当前店id");
        	restId = getCurrentUserRestId()	;
        	restId="72e12515-f54f-11e4-af9a-02004c4f4f50";  //本行测试代码，完毕删除
        }

		Page page = this.productService.findProductList(restId, searchValue,
				parentCategoryId, categoryId, pageNumber, pageSize);
		IosReturnJson json=new IosReturnJson();
		json.setStatusCode(Code.SUCCESS.getCode());
		json.setMessage(Code.SUCCESS.getDesc());
		SearchPageDto searchPage = new SearchPageDto();
		searchPage.setSearchValue(searchValue);
		searchPage.setPage(pageNumber+"");
		searchPage.setPageSize(pageSize+"");
		searchPage.setPageUpOrDown(pageUpOrDown);
		json.setSearchPage(searchPage);
		json.setObjectzJson(page.getContent());
		
		return json;
	}

	/***
	 * 添加
	 * @param barCode  条码
	 * @param name 名称
	 * @param price  价格
	 * @param quantity  库存 
	 * @param categoryId 类别id
	 * @return
	 */
	@RequestMapping(value = { "add" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public IosReturnJson add(String barCode,String name,String price,String quantity,String categoryId) {
		logger.info("商品添加参数为: barCode "+barCode+",name "+name+",price "+price+",quantity "+quantity+",categoryId "+categoryId);
		Product product=new Product();
		product.setBarCode(barCode);
		product.setName(name);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setCategoryId(categoryId);
		product.setProId(null);
		product.setStatus("1");
		try {
			Product resProduct=this.productService.save(product);
			if(resProduct==null){
				IosReturnJson json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
				return json;
			}
			IosReturnJson json=new IosReturnJson(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
			json.setObjectzJson(resProduct);
			return json;
		} catch (Exception e) {
			logger.error("商品添加失败,错误信息为:"+e.getLocalizedMessage(),e);
			// TODO Auto-generated catch block
			IosReturnJson json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
			return json;
		}
	}

	/**
	 * 更新
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = { "update" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public IosReturnJson update(String proId,String barCode,String name,String price,String quantity,String categoryId,String status) {
		logger.info("商品更新参数为: barCode "+barCode+",name "+name+",price "+price+",quantity "+quantity+",categoryId "+categoryId+",proId "+proId);
			if(proId!=null){
				try {
					Product pro=this.productService.getOne(proId);
					if(barCode!=null)
						pro.setBarCode(barCode);//条码
					if(name !=null)
						pro.setName(name);//名称
					if(price !=null)
						pro.setPrice(price);//价格
					if(quantity !=null)
						pro.setQuantity(quantity); //库存
					if(categoryId !=null)
						pro.setCategoryId(categoryId);//类别
					if(status !=null)
						pro.setStatus(status);   //状态
					Product resProduct=this.productService.save(pro);
					IosReturnJson json=new IosReturnJson(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
					json.setObjectzJson(resProduct);
					return json;
				} catch (Exception e) {
					logger.error("更新商品信息失败,错误信息为:"+e.getLocalizedMessage(),e);
					// TODO Auto-generated catch block
					IosReturnJson json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
					return json;
				}
				
			}else{
				logger.info("商品信息proId不能为空,或没有此信息");
			}
			IosReturnJson json=new IosReturnJson(Code.FAILE.getCode(),Code.FAILE.getDesc());
			return json;
	}


	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
