 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.frontdesk.service.hygl.MembershipCardService;
import com.ndlan.canyin.frontdesk.service.hygl.RestMemberInfoService;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
 import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
 import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
 import com.ndlan.canyin.base.entity.sygl.CashDiscount;
 import com.ndlan.canyin.base.entity.sygl.PaymentType;
 import com.ndlan.canyin.base.repository.qtsy.DinerBillPaymentDao;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.CreditStatusEnum;
 import com.ndlan.canyin.core.common.MemberCardPayTypeEnum;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import com.ndlan.canyin.core.common.PaymentTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.math.BigDecimal;
 import java.util.Date;
 import java.util.LinkedHashMap;
 import java.util.List;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 @Service
 public class DinerBillPaymentService extends BaseService<DinerBillPaymentDao, DinerBillPayment>
 {
   private DinerBillPaymentDao dinerBillPaymentDao;
 
   @Autowired
   MembershipCardService membershipCardService;
 
   @Autowired
   DinerBillService dinerBillService;
 
   @Autowired
   PaymentTypeService paymentTypeService;
 
   @Autowired
   RestMemberInfoService restMemberInfoService;
 
   public List<DinerBillPayment> findByRestIdAndDinerBillAndPaymentType(String restId, DinerBill dinerBill, PaymentType paymentType)
   {
     return this.dinerBillPaymentDao.findByRestIdAndDinerBillAndPaymentType(restId, dinerBill, paymentType);
   }
 
   public String savePayments(String subType, String restId, String billId, String mcId, String mbId, String dbpId, String cptId, String money, String membercardPayType, LinkedHashMap<String, Object> map,int isSuc)
   {
	   
	   //isSuc=1  付款方式类型为已付款   如果没有为0
	   	//结账方式id是否为空
     if (StringUtils.isNotEmpty(cptId)) {
       DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
       PaymentType paymentType = (PaymentType)this.paymentTypeService.loadOne(cptId);
       DinerBillPayment dinerBillPayment = findByDinerBillAndRestIdAndPaymentType(dinerBill, restId, paymentType);
       if (dinerBillPayment != null) {
    	   //每次更新付款方式，先删除cm_diner_bill_payment表保存当前付款方式
         this.dinerBillPaymentDao.delete(dinerBillPayment.getDbpId());
 
         map.put(dbpId + "spd_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_payment,dbp_id," + dbpId);
       }
 
     }else{    
    	 
    	 //默认现金支付方式
    	 cptId=this.paymentTypeService.getCptId(restId).getCptId();
    	 
     }
     //需不需要新添加记录标识
	   DinerBillPayment dd=null;
     boolean isAdd = false;
     if ((StringUtils.isNotEmpty(money)) && (Double.valueOf(money).doubleValue() > 0.0D)) {
    	 	//判断是会员卡支付并且使用会员卡打折方式  不需要添加新记录
       if (("CARD".equals(subType)) && (MemberCardPayTypeEnum.DISCOUNT.getCode().equals(membercardPayType)))
         isAdd = false;
       else
         isAdd = true;
     }
     else {
       isAdd = false;
     }
 
     if (isAdd)
     {
    	//创建一个新的对象用来新添加一条cm_diner_bill_payment表支付方式数据
       DinerBillPayment dinerBillPayment = new DinerBillPayment();
       DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
       PaymentType pType = (PaymentType)this.paymentTypeService.loadOne(cptId);
       pType.setCptId(cptId);
       dinerBillPayment.setDinerBill(dinerBill);
       dinerBillPayment.setPaymentType(pType);
       dinerBillPayment.setBillNo(dinerBill.getBillNo());
       dinerBillPayment.setRestId(restId);
       dinerBillPayment.setPayTime(new Date());
       dinerBillPayment.setMoney(new BigDecimal(money));
       dinerBillPayment.setBillStatus(isSuc);
 
       if ((PaymentTypeEnum.HOTEL_CREDIT.getCode().equals(pType.getPaymentType())) || 
         (PaymentTypeEnum.TEAM_CREDIT.getCode().equals(pType.getPaymentType())) || 
         (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equals(pType.getPaymentType()))) {
         dinerBillPayment.setCreditStatus(CreditStatusEnum.CREDIT_BILL.getCode());
       }
      dd= this.self.save(dinerBillPayment);
       
 
       map.put(dinerBillPayment.getDbpId() + "c_" + OperationTypeEnum.CREATE.getCode(), dinerBillPayment);
     }
     //如果付款方式是会员卡或者挂账方式
     if (("CARD".equals(subType)) || ("CREDIT".equals(subType))) {
       DinerBill dinerBill = useMembershipCardForPay(billId, mcId);
       dinerBill.setMembercardPayType(membercardPayType);
       //保存当前账单的会员卡号等信息
       this.dinerBillService.save(dinerBill);
 
       map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
     }
     if(dd!=null){
    	 return dd.getDbpId();
     }else{
    	 return null;
     }
   }
 
   public void savePaymentsByMobile(String subType, String restId, String billId, String mcId, String mbId, String dbpId, String cptId, String money, LinkedHashMap<String, Object> map)
   {
     if (StringUtils.isNotEmpty(dbpId)) {
       this.dinerBillPaymentDao.delete(dbpId);
 
       map.put(dbpId + "d_" + OperationTypeEnum.DELETE_ID.getCode(), "cm_diner_bill_payment,dbp_id," + dbpId);
     }
 
     if ((StringUtils.isNotEmpty(money)) && (Double.valueOf(money).doubleValue() > 0.0D))
     {
       DinerBillPayment dinerBillPayment = new DinerBillPayment();
       DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
       PaymentType pType = (PaymentType)this.paymentTypeService.loadOne(cptId);
       pType.setCptId(cptId);
       dinerBillPayment.setDinerBill(dinerBill);
       dinerBillPayment.setPaymentType(pType);
       dinerBillPayment.setBillNo(dinerBill.getBillNo());
       dinerBillPayment.setRestId(restId);
       dinerBillPayment.setPayTime(new Date());
       dinerBillPayment.setMoney(new BigDecimal(money));
 
       if ((PaymentTypeEnum.HOTEL_CREDIT.getCode().equals(pType.getPaymentType())) || 
         (PaymentTypeEnum.TEAM_CREDIT.getCode().equals(pType.getPaymentType())) || 
         (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equals(pType.getPaymentType()))) {
         dinerBillPayment.setCreditStatus(CreditStatusEnum.CREDIT_BILL.getCode());
       }
       this.self.save(dinerBillPayment);
 
       map.put(dinerBillPayment.getDbpId() + "c_" + OperationTypeEnum.CREATE.getCode(), dinerBillPayment);
     }
 
     if ("CARD".equals(subType)) {
       DinerBill dinerBill = useMembershipCardForPay(billId, mcId);
       this.dinerBillService.save(dinerBill);
 
       map.put(dinerBill.getBillId() + "_" + OperationTypeEnum.UPDATE.getCode(), dinerBill);
     }
   }
 
   public DinerBill useMembershipCardForPay(String billId, String mcId)
   {
     DinerBill dinerBill = (DinerBill)this.dinerBillService.getOne(billId);
     MembershipCard membershipCard = (MembershipCard)this.membershipCardService.getOne(mcId);
     dinerBill.setMembershipCard(membershipCard);
     RestMemberInfo restMemberInfo = (RestMemberInfo)this.restMemberInfoService.getOne(membershipCard.getRestMemberInfo().getMbId());
     dinerBill.setRestMemberInfo(restMemberInfo);
     dinerBill.setCashDiscount(membershipCard.getMembershipCardClass().getCashDiscount());
     dinerBill.setDiscountName(membershipCard.getMembershipCardClass().getCashDiscount().getDiscountName());
     dinerBill.setDiscount(Integer.valueOf(0));
     dinerBill.setIsCustomDiscount(TrueFalseEnum.FALSE.getCode());
     dinerBill.setOtherDiscount("");
     return dinerBill;
   }
 
   public Object[] getMoneySumByCptId(String restId, String empId, Date createTime, String cptId) {
	   
     return this.dinerBillPaymentDao.getMoneySumByCptId(restId, empId, createTime, cptId, BillStatusEnum.SETTLE.getCode());
   }
   
   public Double getdishesSet(Date createTime,String restId){
	   return this.dinerBillPaymentDao.getdishesSet(createTime,BillStatusEnum.SETTLE.getCode(),restId);
   }
   
   public Double getdishes(Date createTime,String restId){
	   return this.dinerBillPaymentDao.getdishes(createTime,BillStatusEnum.SETTLE.getCode(),restId);
   }
   
   public DinerBillPayment findByDinerBillAndRestIdAndPaymentType(DinerBill dinerBill, String restId, PaymentType paymentType)
   {
     return this.dinerBillPaymentDao.findByDinerBillAndRestIdAndPaymentType(dinerBill, restId, paymentType);
   }
 
   public List<DinerBillPayment> findByDinerBillAndRestIdAndPaymentTypeIn(DinerBill dinerBill, String restId, List<PaymentType> paymentTypes)
   {
     return this.dinerBillPaymentDao.findByDinerBillAndRestIdAndPaymentTypeIn(dinerBill, restId, paymentTypes);
   }
 
   public BigDecimal getCurrentTotalMolingModeCost(String restId, String empId, Date createTime)
   {
     return this.dinerBillPaymentDao.getCurrentTotalMolingModeCost(restId, empId, createTime);
   }
 
   public BigDecimal getCurrentTotalUnPayBillCost(String restId, String empId, Date createTime)
   {
     return this.dinerBillPaymentDao.getCurrentTotalUnPayBillCost(restId, empId, createTime);
   }
 
   public BigDecimal getCurrentTotalSaveCost(String restId, String empId, Date createTime)
   {
     return this.dinerBillPaymentDao.getCurrentTotalSaveCost(restId, empId, createTime);
   }
 
   public BigDecimal getCurrentTotalServiceChargeMoney(String restId, String empId, Date createTime)
   {
     return this.dinerBillPaymentDao.getCurrentTotalServiceChargeMoney(restId, empId, createTime);
   }
 
   public BigDecimal getCurrentTotalTuiCaiMoney(String restId, String empId, Date createTime)
   {
     return this.dinerBillPaymentDao.getCurrentTotalTuiCaiMoney(restId, empId, createTime);
   }
 
   public BigDecimal getCurrentTotalZengCaiMoney(String restId, String empId, Date createTime)
   {
     return this.dinerBillPaymentDao.getCurrentTotalZengCaiMoney(restId, empId, createTime);
   }
   @Autowired
   public void setDao(DinerBillPaymentDao dao) {
     super.setDao(dao);
     this.dinerBillPaymentDao = dao;
   }
 }

