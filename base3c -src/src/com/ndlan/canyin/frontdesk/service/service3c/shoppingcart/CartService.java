package com.ndlan.canyin.frontdesk.service.service3c.shoppingcart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.canyin.base.entity.base3c.productinfo.Product;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartEntity;
import com.ndlan.canyin.base.entity.base3c.shoppingcart.CartItemEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.repository.dao3c.shoppingcart.CartDao;
import com.ndlan.canyin.core.common.DiscountOrPrivilegeEnum43c;
import com.ndlan.canyin.frontdesk.common.Arith;
import com.ndlan.canyin.frontdesk.common.Calculate4Base3c;
import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
/**
 * 
 * @Description:购物车service
 * @author: wangwei
 * @date: 2016年1月8日 下午1:40:16
 */
@Service
@Transactional
public class CartService extends BaseService<CartDao, CartEntity> {
	private static final Logger logger = LoggerFactory.getLogger(CartService.class.getName());
	private CartDao cartDao;

	@Autowired
	public void setCartDao(CartDao cartDao) {
		super.setDao(cartDao);
		this.cartDao = cartDao;
	}
	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public CartEntity findByEmplId(String id,String restId){
		return this.cartDao.findByEmplId(id,restId);
	}
	/**
	 * @Description 添加购物车
	 * @param restId
	 * @param cashierId
	 * @return
	 */
	public Boolean isAddCart(String restId,String cashierId,String customerName,String mbId){
			//创建头信息
		try {
			Employee createEmployee=this.employeeService.getOne(cashierId).getCreateEmployee();
			if(createEmployee!=null){
				createEmployee.setEmpId(cashierId);
			}
			CartEntity cartEntity=new CartEntity();
			cartEntity.setCreateEmployee(createEmployee);
			cartEntity.setRestId(restId);
			cartEntity.setAllDiscount("100");
			cartEntity.setAllPrivilege("0.00");
			cartEntity.setCodelessSum("0.00");
			cartEntity.setCustomerName(customerName);
			cartEntity.setMbId(mbId);
			cartEntity.setStatus("1");
			cartEntity.setTotal("0.00");
			cartEntity.setBeforDiscountTotal("0.00");
			this.save(cartEntity);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("创建购物车头失败，失败原因为："+e.getLocalizedMessage());
			return false;
		}
		return true;
	}
	
	public  Boolean updateCart(String subtotalOne,CartEntity cartEntity){
		try {
			//拿折扣前总额
			String beforeTotal=Arith.add(cartEntity.getBeforDiscountTotal(), subtotalOne);
			cartEntity.setBeforDiscountTotal(beforeTotal);
			String total="0.00";
			if(DiscountOrPrivilegeEnum43c.DISCOUTN.getCode().equals(cartEntity.getDiscountType())){
				String realDiscount=Arith.div(cartEntity.getAllDiscount(),"100",4);
				total=Arith.mul(beforeTotal, realDiscount);
			}else if(DiscountOrPrivilegeEnum43c.PRIVILEGE.getCode().equals(cartEntity.getDiscountType())){
				total=Arith.sub(beforeTotal, cartEntity.getAllPrivilege());
			}else{
				total=beforeTotal;
			}
			cartEntity.setTotal(total);
			this.save(cartEntity);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新总金额失败，失败原因为:"+e.getLocalizedMessage());
			return false;
		}
		
		return true;
	}
}

