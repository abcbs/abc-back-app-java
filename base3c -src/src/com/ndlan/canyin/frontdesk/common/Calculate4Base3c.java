package com.ndlan.canyin.frontdesk.common;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description: Base3c的小计、总金额、退款的计算
 * @author husitong
 * @date: 2016年1月8日 上午11:55:52
 */
public class Calculate4Base3c {
	private static final Logger logger = LoggerFactory.getLogger(Calculate4Base3c.class);

	/**
	 * 
	 * @Description 小计 按折扣比例计算小计 公式 小计=（单个商品价格*折扣）*数量
	 * @param price
	 *            单个商品价格
	 * @param discount
	 *            折扣比例
	 * @param quantity
	 *            数量
	 * @return 保留4位小数
	 * @throws IllegalArgumentException
	 */

	public static String doSubtotal4Discount(String price, String discount,
			String quantity) throws Exception {
		if (StringUtils.isBlank(price) || StringUtils.isBlank(discount)
				|| StringUtils.isBlank(quantity))
		{
			logger.error("非法参数{},{},{}",price,discount,quantity);
			throw new IllegalArgumentException("非法参数");
		}
		String r1 = Arith.mul(price, discount);
		String r2 = Arith.mul(r1, quantity);
		return Arith.round(r2, 4);

	}
	
	/**
	 * 
	 * @Description 小计 无折扣无优惠计算小计 公式 小计=单个商品价格*数量
	 * @param price
	 *            单个商品价格
	 * @param quantity
	 *            数量
	 * @return 保留4位小数
	 * @throws IllegalArgumentException
	 */

	public static String doSubtotal(String price,String quantity) throws Exception {
		if (StringUtils.isBlank(price) || StringUtils.isBlank(quantity))
		{
			logger.error("非法参数{},{}",price,quantity);
			throw new IllegalArgumentException("无折扣无优惠计算小计,非法参数");
		}
		String r1 = Arith.mul(price, quantity);
		return Arith.round(r1, 4);

	}

	/**
	 * 
	 * @Description 小计 按金额优惠计算小计 公式 小计=（单个商品价格-立减金额）*数量
	 * @param price
	 *            单个商品价格
	 * @param privilege
	 *            立减金额
	 * @param quantity
	 *            数量
	 * @return 保留4位小数
	 * @throws IllegalArgumentException
	 */
	public static String doSubtotal4Privilege(String price, String privilege,
			String quantity) throws Exception {
		if (StringUtils.isBlank(price) || StringUtils.isBlank(privilege)
				|| StringUtils.isBlank(quantity))
		{
			logger.error("非法参数{},{},{}",price,privilege,quantity);
			throw new IllegalArgumentException("按金额优惠计算小计,非法参数");
		}
		String r1 = Arith.sub(price, privilege);
		String r2 = Arith.mul(r1, quantity);
		return Arith.round(r2, 4);
	}

	/**
	 * 
	 * @Description 总金额 按优惠金额计算商品总金额 公式 总金额=商品小计+无码收银-|整单优惠|
	 * @param subtotal
	 *            商品合计即小计的和
	 * @param allPrivilege
	 *            整单优惠
	 * @param codelessSum
	 *            无码收银金额
	 * @return
	 * @throws Exception
	 */
	public static String doTotal4Privilege(String subtotal,
			String allPrivilege, String codelessSum) throws Exception {
		if (StringUtils.isBlank(subtotal) || StringUtils.isBlank(allPrivilege)
				|| StringUtils.isBlank(codelessSum))
		{
			logger.error("非法参数{},{},{}",subtotal,allPrivilege,codelessSum);
			throw new IllegalArgumentException("按优惠金额计算商品总金额,非法参数");
		}
		String r1 = Arith.add(subtotal, codelessSum);
		String r2 = Arith.sub(r1, allPrivilege);
		return Arith.round(r2, 4);
	}

	/**
	 * 
	 * @Description 总金额 按折扣计算商品总金额 公式 总金额=（商品小计+无码收银）*整单折扣
	 * @param subtotal
	 *            商品合计即小计的和
	 * @param allDiscount
	 *            整单折扣
	 * @param codelessSum
	 *            无码收银金额
	 * @return
	 * @throws Exception
	 */
	public static String doTotal4Discount(String subtotal, String allDiscount,
			String codelessSum) throws Exception {
		if (StringUtils.isBlank(subtotal) || StringUtils.isBlank(allDiscount)
				|| StringUtils.isBlank(codelessSum))
		{
			logger.error("非法参数{},{},{}",subtotal,allDiscount,codelessSum);
			throw new IllegalArgumentException("按折扣计算商品总金额,非法参数");
		}
		String r1 = Arith.add(subtotal, codelessSum);
		String r2 = Arith.mul(r1, allDiscount);
		return Arith.round(r2, 4);
	}

	/**
	 * 
	 * @Description 减免 按折扣比例计算 公式 减免=单个商品价格*（1-折扣）*数量
	 * @param price
	 * @param discount
	 * @param quantity
	 * @return
	 * @throws Exception
	 */
	public static String doDerate4Discount(String price, String discount,
			String quantity) throws Exception {
		if (StringUtils.isBlank(price) || StringUtils.isBlank(discount)
				|| StringUtils.isBlank(quantity))
		{
			logger.error("非法参数{},{},{}",price,discount,quantity);
			throw new IllegalArgumentException("减免按折扣比例计算,非法参数");
		}
		String one = "1";
		String r1 = Arith.sub(one, discount);
		String r2 = Arith.mul(r1, price);
		String r3 = Arith.mul(r2, quantity);
		return Arith.round(r3, 4);

	}

	/**
	 * 
	 * @Description 减免 按折扣比例计算 公式 减免=立减金额*数量
	 * @param privilege
	 * @param quantity
	 * @return
	 * @throws Exception
	 */
	public static String doDerate4Privilege(String privilege, String quantity)
			throws Exception {
		if (StringUtils.isBlank(privilege) || StringUtils.isBlank(quantity))
		{
			logger.error("非法参数{},{}",privilege,quantity);
			throw new IllegalArgumentException("非法参数");
		}
		String r1 = Arith.mul(privilege, quantity);
		return Arith.round(r1, 4);
	}
	
	

	public static void main(String[] args) {
		String price = "4";
		String discount = "0.8";
		String quantity = null;

		try {
			System.out.println(Calculate4Base3c.doSubtotal4Discount(price,
					discount, quantity));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
